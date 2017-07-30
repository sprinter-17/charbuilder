package characterbuilder.character.choice;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Proficiency;
import characterbuilder.character.ability.Spell;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.CharacterClass;
import characterbuilder.character.equipment.Equipment;
import characterbuilder.character.equipment.EquipmentSet;
import characterbuilder.character.equipment.Token;
import characterbuilder.character.equipment.Weapon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

public class ChoiceGenerator {

    private final Predicate<Character> condition;
    private final List<Choice> choices = new ArrayList<>();
    private final List<ChoiceGenerator> children = new ArrayList<>();

    public ChoiceGenerator() {
        this(ch -> true);
    }

    private ChoiceGenerator(Predicate<Character> condition) {
        this.condition = condition;
    }

    public ChoiceGenerator level(int... levels) {
        return cond(levels(levels));
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
        return new OptionChoice(attributeType.toString()) {
            @Override
            public void select(Character character, ChoiceSelector selector) {
                List<Attribute> attributes = Arrays.stream(Proficiency.values())
                    .filter(ab -> ab.getType() == attributeType).collect(toList());
                selector.chooseOption(attributes.stream(), att -> {
                });
            }
        };
    }

    public static Choice spellChoice(int count, CharacterClass casterClass, int level) {
        return new OptionChoice(level == 0 ? "Cantrip" : "Level " + level + " spell", count) {
            @Override
            public void select(Character character, ChoiceSelector selector
            ) {
                List<Attribute> spells = Spell.spells(casterClass, level).collect(toList());
                selector.chooseOption(spells.stream()
                    .filter(sp -> !character.hasAttribute(sp)), spell -> {
                    character.addAttribute(spell);
                });
            }
        };
    }

    public ChoiceGenerator addAction(String name, Consumer<Character> action) {
        choices.add(new Choice() {
            @Override
            public void addTo(Character character) {
                action.accept(character);
            }

            @Override
            public String getName() {
                return name;
            }
        });
        return this;
    }

    public ChoiceGenerator addEquipment(Equipment... equipment) {
        Arrays.stream(equipment)
            .forEach(eq -> addAction(eq.toString(), ch -> ch.addEquipment(eq)));
        return this;
    }

    public ChoiceGenerator addEquipment(Equipment equipment, int count) {
        return addEquipment(new EquipmentSet(equipment, count));
    }

    public ChoiceGenerator addTokens(String... names) {
        Arrays.stream(names).map(Token::new).forEach(this::addEquipment);
        return this;
    }

    public ChoiceGenerator addChoice(Choice choice) {
        choices.add(choice);
        return this;
    }

    public ChoiceGenerator addChoice(int count, OptionChoice choice) {
        choices.add(choice.withCount(count));
        return this;
    }

    public ChoiceGenerator removeAttribute(Attribute attribute) {
        return addAction("Remove " + attribute.toString(), ch -> ch.removeAttribute(attribute));
    }

    public ChoiceGenerator addAttributes(Attribute... attributes) {
        Arrays.stream(attributes)
            .forEach(attr -> addAction(attr.toString(), ch -> ch.addAttribute(attr)));
        return this;
    }

    public ChoiceGenerator addWeaponProficiencies(Weapon... weapons) {
        Arrays.stream(weapons).map(Weapon::getProficiency)
            .forEach(wp -> addAttributes(wp));
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

    public Stream<String> getDescription(Character character) {
        return choices.stream().map(Choice::getName);
    }
}
