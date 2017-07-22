package characterbuilder.character.choice;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Proficiency;
import characterbuilder.character.ability.Spell;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.CharacterClass;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.character.equipment.Equipment;
import characterbuilder.character.equipment.EquipmentSet;
import characterbuilder.character.equipment.Weapon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import static java.util.stream.Collectors.toList;

public class ChoiceGenerator {

    protected static abstract class AbstractChoice implements Choice {

        private final String name;

        public AbstractChoice(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    protected static class ChoiceCounter {

        private int count;

        public ChoiceCounter(int count) {
            this.count = count;
        }

        public boolean complete() {
            count--;
            return count == 0;
        }

        public String prefix() {
            if (count > 1)
                return count + " x ";
            else
                return "";
        }
    }

    private final Predicate<Character> condition;
    private final List<Supplier<Choice>> choices = new ArrayList<>();
    private final List<ChoiceGenerator> children = new ArrayList<>();

    public ChoiceGenerator() {
        this(ch -> true);
    }

    private ChoiceGenerator(Predicate<Character> condition) {
        this.condition = condition;
    }

    public ChoiceGenerator level(int level) {
        return cond(levels(level));
    }

    public ChoiceGenerator cond(Predicate<Character> condition) {
        ChoiceGenerator child = new ChoiceGenerator(condition);
        children.add(child);
        return child;
    }

    public ChoiceGenerator generator(ChoiceGenerator child) {
        children.add(child);
        return this;
    }

    public static Choice abilityTypeChoice(AttributeType attributeType) {
        return new AbstractChoice(attributeType.toString()) {
            @Override
            public void makeChoice(Character character, ChoiceSelector selector) {
                List<Attribute> attributes = Arrays.stream(Proficiency.values())
                    .filter(ab -> ab.getType() == attributeType).collect(toList());
                selector.getAttribute(attributes.stream(), att -> {
                    character.addAttribute(att);
                    att.generateInitialChoices(character);
                    character.getChoices().removeChoice(this);
                });
            }
        };
    }

    public static Choice abilityScoreIncrease(int count) {
        return new Choice() {
            private final ChoiceCounter counter = new ChoiceCounter(count);

            @Override
            public void makeChoice(Character character, ChoiceSelector selector) {
                selector.getAttribute(AttributeType.ABILITY_SCORES.stream()
                    .filter(as -> character.getIntAttribute(as) < 20)
                    .map(as -> new IntAttribute(as, 0) {
                    @Override
                    public String toString() {
                        return as.toString();
                    }
                }),
                    as -> {
                        IntAttribute attr = character.getAttribute(as.getType());
                        attr.addValue(1);
                        if (as.getType().equals(AttributeType.CONSTITUTION)
                        && attr.getValue() % 2 == 0) {
                            IntAttribute hp = character.getAttribute(AttributeType.HIT_POINTS);
                            hp.addValue(character.getLevel());
                        }
                        if (counter.complete())
                            character.getChoices().removeChoice(this);
                    });
            }

            @Override
            public String toString() {
                return counter.prefix() + "Increase ability score";
            }
        };
    }

    public static Supplier<Choice> spellChoice(int count, CharacterClass casterClass, int level) {
        return () -> new MultiChoice(count, new Choice() {
            @Override
            public void makeChoice(Character character, ChoiceSelector selector) {
                List<Attribute> spells = Spell.spells(casterClass, level).collect(toList());
                selector.getAttribute(spells.stream()
                    .filter(sp -> !character.hasAttribute(sp)), spell -> {
                    character.addAttribute(spell);
                });
            }

            @Override
            public String toString() {
                return level == 0 ? "Cantrip" : "Level " + level + " spell";
            }
        });
    }

    public ChoiceGenerator addAction(Consumer<Character> action) {
        choices.add(() -> new Choice() {
            @Override
            public void makeChoice(Character character, ChoiceSelector selector) {
                action.accept(character);
            }

            @Override
            public boolean isAutomatic() {
                return true;
            }
        });
        return this;
    }

    public ChoiceGenerator addEquipment(Equipment equipment) {
        return addAction(ch -> ch.addEquipment(equipment));
    }

    public ChoiceGenerator addEquipment(Equipment equipment, int count) {
        return addAction(ch -> ch.addEquipment(new EquipmentSet(equipment, count)));
    }

    public ChoiceGenerator addChoice(Supplier<Choice> choice) {
        choices.add(choice);
        return this;
    }

    public ChoiceGenerator addAttributes(Attribute... attributes) {
        Arrays.stream(attributes)
            .forEach(att -> this.choices.add(() -> new AttributeFeature(att)));
        return this;
    }

    public ChoiceGenerator replaceAttribute(Attribute from, Attribute to) {
        this.choices.add(() -> new AttributeFeature(to, from));
        return this;
    }

    public ChoiceGenerator addWeaponProficiencies(Weapon... weapons) {
        Arrays.stream(weapons).map(Weapon::getProficiency)
            .forEach(att -> this.choices.add(() -> new AttributeFeature(att)));
        return this;
    }

    public void generateChoices(Character character) {
        if (condition.test(character)) {
            choices.stream()
                .forEach(chs -> {
                    Choice choice = chs.get();
                    if (choice.isAutomatic())
                        choice.makeChoice(character, null);
                    else
                        character.getChoices().addChoice(choice);
                });
            children.forEach(ch -> ch.generateChoices(character));
        }
    }

    public static Predicate<Character> always() {
        return ch -> true;
    }

    public static Predicate<Character> levels(int... levels) {
        return ch -> ch.hasAttribute(AttributeType.LEVEL)
            && Arrays.stream(levels)
                .anyMatch(lev -> ch.getIntAttribute(AttributeType.LEVEL) == lev);
    }
}
