package characterbuilder.character.choice;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Proficiency;
import characterbuilder.character.attribute.AbilityScore;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.characterclass.CharacterClass;
import characterbuilder.character.equipment.Equipment;
import characterbuilder.character.equipment.EquipmentSet;
import characterbuilder.character.equipment.Token;
import characterbuilder.character.equipment.Weapon;
import characterbuilder.character.spell.Cantrip;
import characterbuilder.character.spell.Spell;
import characterbuilder.character.spell.SpellCasting;
import characterbuilder.character.spell.SpellClassMap;
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

    public ChoiceGenerator increaseAbilityScore(AttributeType abilityScore, int amount) {
        return addAction("+" + amount + " " + abilityScore.toString(), ch -> ch
            .getScore(abilityScore).addValue(amount));
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

    public ChoiceGenerator addSpellCasting(String name, AttributeType abilityScore) {
        return addAction(name, ch -> ch.addAttribute(new SpellCasting(name, abilityScore)));
    }

    public ChoiceGenerator addWeaponProficiencies(Weapon... weapons) {
        Arrays.stream(weapons).map(Weapon::getProficiency)
            .forEach(wp -> addAttributes(wp));
        return this;
    }

    public ChoiceGenerator addSpellSlots(String casting, int level, int slots) {
        addAction("Spell Slots", ch -> getCasting(ch, casting).addSlots(level, slots));
        return this;
    }

    public ChoiceGenerator addCantrip(Spell cantrip, AttributeType abilityScore) {
        return addAction("Add Cantrip", ch -> ch.addAttribute(new Cantrip(cantrip, abilityScore)));
    }

    public ChoiceGenerator addLearntSpells(String casting, Spell... spells) {
        addAction("Add Spells", ch -> Arrays.stream(spells)
            .forEach(getCasting(ch, casting)::addLearntSpell));
        return this;
    }

    public ChoiceGenerator addAllSpells(String casting) {
        addAction("Add Spells",
            ch -> {
            CharacterClass characterClass = ch.getAttribute(AttributeType.CHARACTER_CLASS);
            SpellCasting spellCasting = getCasting(ch, casting);
            characterClass.getSpells()
                .filter(sp -> sp.getLevel() > 0 && sp.getLevel() <= spellCasting.getMaxSlot())
                .filter(sp -> !spellCasting.hasLearntSpell(sp))
                .forEach(spellCasting::addLearntSpell);
        });
        return this;
    }

    public static Choice spellChoice(String casting, int count, int level) {
        assert level > 0;
        return new OptionChoice("Spell Level " + level, count) {
            @Override
            public void select(Character character, ChoiceSelector selector) {
                SpellCasting spellCasting = getCasting(character, casting);
                CharacterClass characterClass = character
                    .getAttribute(AttributeType.CHARACTER_CLASS);
                selector.chooseOption(SpellClassMap.spellsForClass(characterClass)
                    .filter(sp -> !sp.isCantrip())
                    .filter(sp -> sp.getLevel() <= level)
                    .filter(sp -> !spellCasting.hasLearntSpell(sp)),
                    spellCasting::addLearntSpell);
            }

            @Override
            public boolean isAllowed(Character character) {
                return character.hasAttribute(AttributeType.CHARACTER_CLASS)
                    && character.hasAttribute(AttributeType.SPELLCASTING)
                    && AbilityScore.SCORES.stream().allMatch(character::hasAttribute);
            }
        };
    }

    private static SpellCasting getCasting(Character character, String name) {
        return character.getAttributes(AttributeType.SPELLCASTING, SpellCasting.class)
            .filter(sc -> sc.hasName(name))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("No spellcasting of name " + name));
    }

    public static Choice cantripChoice(int count, String name,
        AttributeType abilityScore, Stream<Spell> cantrips) {
        List<Cantrip> cantripList = cantrips
            .map(c -> new Cantrip(c, abilityScore))
            .collect(toList());
        return new OptionChoice(name, count) {
            @Override
            public void select(Character character, ChoiceSelector selector) {
                selector.chooseOption(cantripList.stream()
                    .filter(sp -> !character.hasAttribute(sp)), character::addAttribute);
            }

            @Override
            public boolean isAllowed(Character character) {
                return AbilityScore.SCORES.stream().allMatch(character::hasAttribute);
            }
        };
    }

    public static Choice cantripChoice(int count, AttributeType abilityScore) {
        return new OptionChoice("Cantrip", count) {
            @Override
            public void select(Character character, ChoiceSelector selector) {
                selector.chooseOption(character
                    .getAttribute(AttributeType.CHARACTER_CLASS, CharacterClass.class)
                    .getSpells()
                    .filter(Spell::isCantrip)
                    .map(sp -> new Cantrip(sp, abilityScore))
                    .filter(sp -> !character.hasAttribute(sp)), character::addAttribute);
            }

            @Override
            public boolean isAllowed(Character character) {
                return AbilityScore.SCORES.stream().allMatch(character::hasAttribute);
            }
        };
    }

    public static Choice spellChoice(String casting, int count, String name, Stream<Spell> spells) {
        List<Spell> spellList = spells.collect(toList());
        return new OptionChoice(name, count) {
            @Override
            public void select(Character character, ChoiceSelector selector) {
                SpellCasting spellCasting = getCasting(character, casting);
                selector.chooseOption(spellList.stream()
                    .filter(sp -> sp.getLevel() > 0 && sp.getLevel() <= spellCasting.getMaxSlot())
                    .filter(sp -> !spellCasting.hasLearntSpell(sp)), spellCasting::addLearntSpell);
            }

            @Override
            public boolean isAllowed(Character character) {
                return character.hasAttribute(AttributeType.SPELLCASTING)
                    && AbilityScore.SCORES.stream().allMatch(character::hasAttribute);
            }
        };
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
