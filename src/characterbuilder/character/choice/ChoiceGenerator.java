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
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

public class ChoiceGenerator {

    protected static abstract class NamedChoice extends OptionChoice {

        private final String name;

        public NamedChoice(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private final Predicate<Character> condition;
    private final List<Choice> choices = new ArrayList<>();
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
        return new NamedChoice(attributeType.toString()) {
            @Override
            public void select(Character character, ChoiceSelector selector) {
                List<Attribute> attributes = Arrays.stream(Proficiency.values())
                    .filter(ab -> ab.getType() == attributeType).collect(toList());
                selector.chooseOption(attributes.stream(), att -> {
                });
            }
        };
    }

    public static OptionChoice abilityScoreIncrease() {
        return new OptionChoice() {
            @Override
            public void select(Character character, ChoiceSelector selector) {
                Stream<IntAttribute> abilityScores = AttributeType.ABILITY_SCORES.stream()
                    .filter(as -> character.getIntAttribute(as) < 20)
                    .map(as -> new IntAttribute(as, 0) {
                    @Override
                    public String toString() {
                        return as.toString();
                    }
                });
                selector.chooseOption(abilityScores, as -> {
                    IntAttribute attr = character.getAttribute(as.getType());
                    attr.addValue(1);
                    if (as.getType().equals(AttributeType.CONSTITUTION)
                        && attr.getValue() % 2 == 0) {
                        IntAttribute hp = character.getAttribute(AttributeType.HIT_POINTS);
                        hp.addValue(character.getLevel());
                    }
                });
            }

            @Override
            public String toString() {
                return "Increase ability score";
            }
        };
    }

    public static Choice spellChoice(int count, CharacterClass casterClass, int level) {
        return new OptionChoice(count) {
            @Override
            public void select(Character character, ChoiceSelector selector) {
                List<Attribute> spells = Spell.spells(casterClass, level).collect(toList());
                selector.chooseOption(spells.stream()
                    .filter(sp -> !character.hasAttribute(sp)), spell -> {
                    character.addAttribute(spell);
                });
            }

            @Override
            public String toString() {
                return level == 0 ? "Cantrip" : "Level " + level + " spell";
            }
        };
    }

    public ChoiceGenerator addAction(Consumer<Character> action) {
        choices.add(action::accept);
        return this;
    }

    public ChoiceGenerator addEquipment(Equipment equipment) {
        return addAction(ch -> ch.addEquipment(equipment));
    }

    public ChoiceGenerator addEquipment(Equipment equipment, int count) {
        return addAction(ch -> ch.addEquipment(new EquipmentSet(equipment, count)));
    }

    public ChoiceGenerator addChoice(Choice choice) {
        choices.add(choice);
        return this;
    }

    public ChoiceGenerator addChoice(int count, OptionChoice choice) {
        choices.add(choice.withCount(2));
        return this;
    }

    public ChoiceGenerator removeAttribute(Attribute attribute) {
        choices.add(ch -> ch.removeAttribute(attribute));
        return this;
    }

    public ChoiceGenerator addAttributes(Attribute... attributes) {
        Arrays.stream(attributes)
            .forEach(att -> this.choices.add(ch -> ch.addAttribute(att)));
        return this;
    }

    public ChoiceGenerator addWeaponProficiencies(Weapon... weapons) {
        Arrays.stream(weapons).map(Weapon::getProficiency)
            .forEach(att -> this.choices.add(ch -> ch.addAttribute(att)));
        return this;
    }

    public void generateChoices(Character character) {
        if (condition.test(character)) {
            choices.forEach(choice -> choice.addTo(character));
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