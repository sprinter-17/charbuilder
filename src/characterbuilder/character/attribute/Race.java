package characterbuilder.character.attribute;

import characterbuilder.character.Character;
import characterbuilder.character.CharacterRandom;
import characterbuilder.character.ability.Language;
import static characterbuilder.character.ability.Language.*;
import static characterbuilder.character.ability.Proficiency.*;
import static characterbuilder.character.ability.RacialTalent.*;
import characterbuilder.character.ability.Skill;
import static characterbuilder.character.ability.Skill.*;
import static characterbuilder.character.attribute.AttributeType.*;
import static characterbuilder.character.characterclass.CharacterClass.WIZARD;
import characterbuilder.character.choice.AbilityScoreOrFeatIncrease;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.ChoiceGenerator;
import static characterbuilder.character.choice.ChoiceGenerator.cantripChoice;
import characterbuilder.character.choice.Option;
import characterbuilder.character.choice.OptionChoice;
import static characterbuilder.character.equipment.Weapon.*;
import characterbuilder.character.spell.Spell;
import characterbuilder.character.spell.SpellAbility;
import characterbuilder.utils.StringUtils;
import java.util.EnumMap;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public enum Race implements Attribute {
    HILL_DWARF(0, 0, 2, 0, 1, 0, 25, 50, "3'8\"", "2d4", "115lb", "2d6", (igen, lgen) -> {
        igen.addAttributes(COMMON, DWARVISH, DARKVISION, POISON_RESISTANCE, STONECUNNING);
        igen.addWeaponProficiencies(BATTLEAXE, HANDAXE, LIGHT_HAMMER, WARHAMMER);
        igen.addChoice(new AttributeChoice("Artisan talent", SMITH, BREWER, MASON));
    }),
    MOUNTAIN_DWARF(2, 0, 2, 0, 0, 0, 25, 50, "4'", "2d4", "130lb", "2d6", (gen, lgen) -> {
        gen.addAttributes(COMMON, DWARVISH, DARKVISION, POISON_RESISTANCE, STONECUNNING);
        gen.addAttributes(LIGHT_ARMOUR, MEDIUM_ARMOUR);
        gen.addWeaponProficiencies(BATTLEAXE, HANDAXE, LIGHT_HAMMER, WARHAMMER);
        gen.addChoice(new AttributeChoice("Artisan talent", SMITH, BREWER, MASON));
    }),
    HIGH_ELF(0, 2, 0, 1, 0, 0, 30, 100, "4'6\"", "2d10", "90lb", "1d4", (gen, lgen) -> {
        gen.addAttributes(DARKVISION, FEY_ANCESTORY, PERCEPTION, COMMON, ELVISH);
        gen.addWeaponProficiencies(LONGSWORD, SHORTBOW, LONGBOW);
        gen.addChoice(new AttributeChoice("Language",
            DWARVISH, GIANT, GNOMISH, HALFLING, GOBLIN, ORC));
        gen.addChoice(cantripChoice(1, "Elven Cantrip", INTELLIGENCE,
            WIZARD.getSpells().filter(Spell::isCantrip)));
    }),
    WOOD_ELF(0, 2, 0, 0, 1, 0, 35, 100, "4'6\"", "2d10", "100lb", "1d4", (gen, lgen) -> {
        gen.addAttributes(DARKVISION, FEY_ANCESTORY, PERCEPTION, COMMON, ELVISH, MASK_OF_THE_WILD);
        gen.addWeaponProficiencies(LONGSWORD, SHORTBOW, LONGBOW);
    }),
    DARK_ELF(0, 2, 0, 0, 0, 1, 30, 100, "4'6\"", "2d10", "100lb", "1d4", (igen, lgen) -> {
        igen.addAttributes(SUPERIOR_DARKVISION, SUNLIGHT_SENSITIVITY);
        igen.addSpellAbility(Spell.DANCING_LIGHTS, CHARISMA);
        igen.addWeaponProficiencies(RAPIER, SHORTSWORD, HAND_CROSSBOW);
        lgen.level(3).addSpellAbility(Spell.FAERIE_FIRE, CHARISMA);
        lgen.level(5).addSpellAbility(Spell.DARKNESS, CHARISMA);
    }),
    LIGHFOOT_HALFLING(0, 2, 0, 0, 0, 1, 25, 20, "2'7\"", "2d4", "35lb", "1", (gen, lgen) -> {
        gen.addAttributes(LUCKY, BRAVE, NIMBLE, COMMON, HALFLING, STEALTHY);
    }),
    STOUT_HALFLING(0, 2, 1, 0, 0, 0, 25, 20, "2'7\"", "2d4", "35lb", "1", (gen, lgen) -> {
        gen.addAttributes(LUCKY, BRAVE, NIMBLE, COMMON, HALFLING, POISON_RESISTANCE);
    }),
    HALF_ORC(2, 0, 1, 0, 0, 0, 30, 14, "4'10\"", "2d10", "140lb", "2d6", (gen, lgen) -> {
        gen.addAttributes(DARKVISION, INTIMIDATION, RELENTLESS_ENDURANCE, SAVAGE_ATTACKS);
        gen.addAttributes(COMMON, ORC);
    }),
    HALF_ELF(0, 0, 0, 0, 0, 2, 30, 20, "4'9\"", "2d8", "110lb", "2d4", (gen, lgen) -> {
        gen.addChoice(2, new AbilityScoreOrFeatIncrease().withoutFeats());
        gen.addAttributes(DARKVISION, FEY_ANCESTORY, COMMON, ELVISH);
        gen.addChoice(2, new AttributeChoice("Racial Skill", Skill.values()));
        gen.addChoice(Language.choose(1));
    }),
    FOREST_GNOME(0, 1, 0, 2, 0, 0, 25, 40, "2'11\"", "2d4", "35lb", "1", (gen, lgen) -> {
        gen.addAttributes(GNOME_CUNNING, COMMON, GNOMISH, SPEAK_WITH_SMALL_BEASTS);
        gen.addAttributes(new SpellAbility(Spell.MINOR_ILLUSION, INTELLIGENCE));
    }),
    ROCK_GNOME(0, 0, 1, 2, 0, 0, 25, 40, "2'11\"", "2d4", "35lb", "1", (gen, lgen) -> {
        gen.addAttributes(GNOME_CUNNING, COMMON, GNOMISH, ARTIFICERS_LORE, TINKER);
    }),
    DRAGONBORN(2, 0, 0, 0, 0, 1, 30, 15, "5'6\"", "2d8", "175lb", "2d6", (gen, lgen) -> {
        gen.addChoice(DraconicAncestry.choose());
        gen.addAttributes(COMMON, DRACONIC, BREATH_WEAPON);
    }),
    HUMAN(1, 1, 1, 1, 1, 1, 30, 18, "4'8\"", "2d10", "110lb", "2d4", (gen, lgen) -> {
        gen.addAttributes(COMMON);
        gen.addChoice(new AttributeChoice("Language",
            DWARVISH, ELVISH, GIANT, GNOMISH, HALFLING, GOBLIN, ORC));
    }),
    TIEFLING(0, 0, 0, 1, 0, 2, 30, 18, "4'9\"", "2d8", "110lb", "2d4", (igen, lgen) -> {
        igen.addAttributes(DARKVISION, HELLISH_RESISTANCE, COMMON, INFERNAL);
        igen.addSpellAbility(Spell.THAUMATURGY, CHARISMA);
        lgen.level(3).addSpellAbility(Spell.HELLISH_REBUKE, CHARISMA);
        lgen.level(5).addSpellAbility(Spell.DARKNESS, CHARISMA);
    });

    private static final CharacterRandom RANDOM = new CharacterRandom();
    private final EnumMap<AttributeType, Integer> adjustments = new EnumMap<>(AttributeType.class
    );
    private final int speed;
    private final int ageOfMaturity;
    private final Height baseHeight;
    private final String heightModifier;
    private final Weight baseWeight;
    private final String weightModifier;
    private final ChoiceGenerator initialGenerator = new ChoiceGenerator();
    private final ChoiceGenerator levelGenerator = new ChoiceGenerator();

    Race(int strength, int dexterity, int consitution, int intelligence, int wisom, int charisma,
        int speed, int ageOfMaturity,
        String baseHeight, String heightModifier,
        String baseWeight, String weightModifier,
        BiConsumer<ChoiceGenerator, ChoiceGenerator> generator) {
        try {
            adjustments.put(STRENGTH, strength);
            adjustments.put(DEXTERITY, dexterity);
            adjustments.put(CONSTITUTION, consitution);
            adjustments.put(INTELLIGENCE, intelligence);
            adjustments.put(WISDOM, wisom);
            adjustments.put(CHARISMA, charisma);
            this.speed = speed;
            this.ageOfMaturity = ageOfMaturity;
            this.baseHeight = Height.valueOf(baseHeight);
            this.heightModifier = heightModifier;
            this.baseWeight = Weight.valueOf(baseWeight);
            this.weightModifier = weightModifier;
            generator.accept(initialGenerator, levelGenerator);

        } catch (Height.HeightFormatException | Weight.WeightFormatException ex) {
            Logger.getLogger(Race.class
                .getName()).log(Level.SEVERE, null, ex);
            throw new IllegalArgumentException("Race data exception " + ex);
        }
    }

    @Override
    public AttributeType getType() {
        return AttributeType.RACE;
    }

    public int getAttributeAdjustment(AttributeType type) {
        return adjustments.get(type);
    }

    public int getSpeed() {
        return speed;
    }

    public int getAgeOfMaturity() {
        return ageOfMaturity;
    }

    @Override
    public void generateInitialChoices(Character character) {
        character.addAttribute(new IntAttribute(AttributeType.AGE, ageOfMaturity));
        int heightRoll = RANDOM.roll(heightModifier);
        int weightRoll = RANDOM.roll(weightModifier);
        character.addAttribute(baseHeight.add(Height.INCH.times(heightRoll)));
        character.addAttribute(baseWeight.add(Weight.LB.times(heightRoll * weightRoll)));
        initialGenerator.generateChoices(character);
    }

    @Override
    public void generateLevelChoices(Character character) {
        levelGenerator.generateChoices(character);
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return initialGenerator.getDescription(character);
    }

    public static Race load(Node node) {
        return Race.valueOf(node.getTextContent());

    }

    private static class SuperRace implements Option {

        private final String name;
        private final OptionChoice subRaceSelection;

        public SuperRace(String name, Race... subraces) {
            this.name = name;
            this.subRaceSelection = new AttributeChoice(name + " Subrace", subraces);
        }

        @Override
        public void choose(Character character) {
            character.addChoice(0, subRaceSelection);
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public Element save(Document doc) {
            throw new UnsupportedOperationException("Cannot save race selection option.");
        }
    }

    public static Stream<Option> initialRaceValues() {
        return Stream.of(
            HUMAN,
            new SuperRace("Elf", HIGH_ELF, WOOD_ELF, DARK_ELF),
            new SuperRace("Dwarf", HILL_DWARF, MOUNTAIN_DWARF),
            new SuperRace("Halfling", LIGHFOOT_HALFLING, STOUT_HALFLING),
            new SuperRace("Gnome", FOREST_GNOME, ROCK_GNOME),
            DRAGONBORN,
            TIEFLING,
            HALF_ELF,
            HALF_ORC
        );
    }

}
