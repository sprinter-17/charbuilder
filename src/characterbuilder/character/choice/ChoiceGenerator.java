package characterbuilder.character.choice;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Proficiency;
import characterbuilder.character.attribute.AbilityScore;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.characterclass.CharacterClass;
import characterbuilder.character.equipment.Equipment;
import characterbuilder.character.equipment.EquipmentCategory;
import characterbuilder.character.equipment.Token;
import characterbuilder.character.equipment.Weapon;
import characterbuilder.character.spell.Spell;
import characterbuilder.character.spell.SpellAbility;
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

    public ChoiceGenerator addTokens(String... names) {
        Arrays.stream(names).map(Token::new).forEach(this::addEquipment);
        return this;
    }

    public ChoiceGenerator addChoice(Choice choice) {
        choices.add(choice);
        return this;
    }

    public ChoiceGenerator addAttributeChoice(String name, Attribute... attributes) {
        choices.add(new AttributeChoice(name, attributes));
        return this;
    }

    public ChoiceGenerator addAttributeChoice(int count, String name, Attribute... attributes) {
        choices.add(new AttributeChoice(name, attributes).withCount(count));
        return this;
    }

    public ChoiceGenerator addAttributeChoice(int count, String name, Stream<Attribute> attributes) {
        choices.add(new AttributeChoice(name, attributes).withCount(count));
        return this;
    }

    public ChoiceGenerator addChoice(int count, OptionChoice choice) {
        choices.add(choice.withCount(count));
        return this;
    }

    public ChoiceGenerator addAbilityScoreOrFeatChoice() {
        choices.add(new AbilityScoreOrFeatIncrease().withCount(2));
        return this;
    }

    public EquipmentChoice addEquipmentChoice(String name, Equipment... equipment) {
        EquipmentChoice choice = new EquipmentChoice(name, equipment);
        choices.add(choice);
        return choice;
    }

    public ChoiceGenerator addEquipmentChoice(EquipmentCategory category) {
        choices.add(new EquipmentChoice(category));
        return this;
    }

    public ChoiceGenerator removeAttribute(Attribute attribute) {
        return addAction("Remove " + attribute.toString(), ch -> ch.removeAttribute(attribute));
    }

    public ChoiceGenerator replaceAttribute(Attribute remove, Attribute replace) {
        return addAction("Replace " + remove.toString() + " with " + replace.toString(), ch -> {
            ch.removeAttribute(remove);
            ch.addAttribute(replace);
        });
    }

    public ChoiceGenerator addAttributes(Attribute... attributes) {
        Arrays.stream(attributes)
            .forEach(attr -> addAction(attr.toString(), ch -> attr.choose(ch)));
        return this;
    }

    public ChoiceGenerator addWeaponProficiencies(Weapon... weapons) {
        Arrays.stream(weapons).map(Weapon::getProficiency)
            .forEach(wp -> addAttributes(wp));
        return this;
    }

    public ChoiceGenerator addSpellCasting(String casting, AttributeType abilityScore,
        CharacterClass spellClass, String preparedText) {
        addAction("Add Spellcasting",
            ch -> new SpellCasting(casting, abilityScore, spellClass, preparedText).choose(ch));
        return this;
    }

    public ChoiceGenerator learnAllSpells(String casting) {
        addAction("Learn all spells", ch -> getCasting(ch, casting).learnAllSpells());
        return this;
    }

    public ChoiceGenerator addSpellSlots(String casting, int level, int slots) {
        addAction("Spell Slots", ch -> getCasting(ch, casting).addSlots(level, slots));
        return this;
    }

    public ChoiceGenerator setSpellSlots(String casting, int level, int slots) {
        addAction("Spell Slots", ch -> getCasting(ch, casting).setSlots(level, slots));
        return this;
    }

    public ChoiceGenerator replaceSpell(String casting) {
        addAction("Replace Spell", ch -> getCasting(ch, casting).replaceSpell(ch));
        return this;
    }

    public ChoiceGenerator addKnownSpells(String casting, int count) {
        addAction("Add Known Spells", ch -> getCasting(ch, casting).addKnownSpells(count));
        return this;
    }

    public ChoiceGenerator addSpellAbility(Spell spell, AttributeType abilityScore) {
        return addAction("Add Spell Ability", ch -> new SpellAbility(spell, abilityScore).choose(ch));
    }

    public ChoiceGenerator addLearntSpells(String casting, Spell... spells) {
        addAction("Add Spells", ch -> Arrays.stream(spells)
            .forEach(getCasting(ch, casting)::addPreparedSpell));
        return this;
    }

    public static Choice spellChoice(String casting, int count, int level) {
        assert level > 0;
        final SpellClassMap map = new SpellClassMap();
        return new OptionChoice("Spell Level " + level, count) {
            @Override
            public void select(Character character, ChoiceSelector selector) {
                SpellCasting spellCasting = getCasting(character, casting);
                CharacterClass characterClass = character
                    .getAttribute(AttributeType.CHARACTER_CLASS);
                selector.chooseOption(map.spellsForClass(characterClass)
                    .filter(sp -> !sp.isCantrip())
                    .filter(sp -> sp.getLevel() <= level)
                    .filter(sp -> !spellCasting.hasLearntSpell(sp)),
                    spellCasting::addPreparedSpell);
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
        List<SpellAbility> cantripList = cantrips
            .filter(Spell::isCantrip)
            .map(c -> new SpellAbility(c, abilityScore))
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

    public static OptionChoice cantripChoice(int count, AttributeType abilityScore) {
        return new OptionChoice("Cantrip", count) {
            @Override
            public void select(Character character, ChoiceSelector selector) {
                selector.chooseOption(character
                    .getAttribute(AttributeType.CHARACTER_CLASS, CharacterClass.class)
                    .getSpells()
                    .filter(Spell::isCantrip)
                    .map(sp -> new SpellAbility(sp, abilityScore))
                    .filter(sp -> !character.hasAttribute(sp)),
                    character::addAttribute);
            }

            @Override
            public boolean isAllowed(Character character) {
                return AbilityScore.SCORES.stream().allMatch(character::hasAttribute);
            }
        };
    }

    public static OptionChoice spellChoice(String casting, int count, String name,
        Stream<Spell> spells) {
        List<Spell> spellList = spells.collect(toList());
        return new OptionChoice(name, count) {
            @Override
            public void select(Character character, ChoiceSelector selector) {
                SpellCasting spellCasting = getCasting(character, casting);
                selector.chooseOption(spellList.stream()
                    .filter(sp -> sp.getLevel() > 0 && sp.getLevel() <= spellCasting.getMaxSlot())
                    .filter(sp -> !spellCasting.hasLearntSpell(sp)), spellCasting::addPreparedSpell);
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
