package characterbuilder.character.attribute;

import characterbuilder.character.Character;
import static characterbuilder.character.ability.Ability.*;
import characterbuilder.character.ability.DivineDomain;
import characterbuilder.character.ability.MagicSchool;
import characterbuilder.character.ability.Proficiency;
import static characterbuilder.character.ability.Proficiency.*;
import characterbuilder.character.ability.Skill;
import static characterbuilder.character.ability.Skill.*;
import characterbuilder.character.ability.Spell;
import static characterbuilder.character.ability.Spell.*;
import characterbuilder.character.ability.SpellCasting;
import static characterbuilder.character.attribute.AttributeType.*;
import characterbuilder.character.choice.AbilityScoreOrFeatIncrease;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.Choice;
import characterbuilder.character.choice.ChoiceGenerator;
import static characterbuilder.character.choice.ChoiceGenerator.levels;
import static characterbuilder.character.choice.ChoiceGenerator.spellChoice;
import characterbuilder.character.choice.ChoiceSelector;
import characterbuilder.character.choice.EquipmentChoice;
import characterbuilder.character.choice.ExpertiseChoice;
import characterbuilder.character.choice.OptionChoice;
import characterbuilder.character.choice.ReplaceAttributeChoice;
import characterbuilder.character.equipment.Armour;
import static characterbuilder.character.equipment.Armour.*;
import characterbuilder.character.equipment.EquipmentCategory;
import static characterbuilder.character.equipment.EquipmentCategory.*;
import characterbuilder.character.equipment.EquipmentPack;
import static characterbuilder.character.equipment.EquipmentPack.*;
import characterbuilder.character.equipment.EquipmentSet;
import characterbuilder.character.equipment.EquipmentType;
import static characterbuilder.character.equipment.EquipmentType.*;
import characterbuilder.character.equipment.MusicalInstrument;
import static characterbuilder.character.equipment.Weapon.*;
import characterbuilder.utils.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.w3c.dom.Node;

public enum CharacterClass implements Attribute {
    BARBARIAN(12, PRIMAL_PATH, STRENGTH, CONSTITUTION,
        Arrays.asList(STRENGTH, CONSTITUTION)) {
        @Override
        public void generateLevelChoices(Character character) {
            ChoiceGenerator gen = new ChoiceGenerator();
            gen.level(1).addAttributes(Proficiency.LIGHT_ARMOUR, Proficiency.MEDIUM_ARMOUR,
                Proficiency.SHIELD, ALL_WEAPONS);
            gen.level(1).addChoice(2, new AttributeChoice("Skill",
                ANIMAL_HANDLING, ATHLETICS, INTIMIDATION, NATURE, PERCEPTION, SURVIVAL));
            gen.level(1).addChoice(new EquipmentChoice("Primary Weapon")
                .with(GREATEAXE).with(EquipmentCategory.MARTIAL_MELEE));
            gen.level(1).addChoice(new EquipmentChoice("Secondary Weapon")
                .with(new EquipmentSet(HANDAXE, 2))
                .with(EquipmentCategory.SIMPLE_MELEE)
                .with(EquipmentCategory.SIMPLE_RANGED));
            gen.level(1).addEquipment(EXPLORER_PACK);
            gen.level(1).addEquipment(JAVELIN, 4);
            gen.level(1).addAttributes(RAGE, UNARMORED_DEFENCE_BARBARIAN);
            gen.level(2).addAttributes(RECKLESS_ATTACK, DANGER_SENSE);
            gen.level(3).addChoice(new AttributeChoice("Primal Path", PrimalPath.values()));
            gen.level(5).addAttributes(EXTRA_ATTACK, FAST_MOVEMENT);
            gen.level(7).addAttributes(FERAL_INSTINCTS);
            gen.level(9).addAttributes(BRUTAL_CRITICAL);
            gen.level(11).addAttributes(RELENTLESS_RAGE);
            gen.level(15).addAttributes(PERSISTENT_RAGE);
            gen.level(18).addAttributes(INDOMITABLE_MIGHT);
            gen.level(20).addAction("Increase Str. and Con.", ch -> {
                ch.getAttribute(STRENGTH, IntAttribute.class).addValue(4);
                ch.getAttribute(CONSTITUTION, IntAttribute.class).addValue(4);
            });
            gen.cond(levels(4, 8, 12, 16, 19)).addChoice(2, new AbilityScoreOrFeatIncrease());
            gen.generateChoices(character);
        }
    },
    BARD(8, BARDIC_COLLEGE, DEXTERITY, CHARISMA,
        Arrays.asList(CHARISMA, DEXTERITY)) {
        public void generateLevelChoices(Character character) {
            ChoiceGenerator gen = new ChoiceGenerator();
            gen.level(1).addAttributes(new SpellCasting(CHARISMA));
            gen.level(1).addAttributes(Proficiency.LIGHT_ARMOUR, ALL_SIMPLE_WEAPONS);
            gen.level(1).addWeaponProficiencies(HAND_CROSSBOW, LONGSWORD, RAPIER, SHORTSWORD);
            gen.level(1).addChoice(3, new AttributeChoice("Skill", Skill.values()));
            gen.level(1).addChoice(new EquipmentChoice("Weapon", RAPIER, LONGSWORD)
                .with(EquipmentCategory.SIMPLE_MELEE).with(EquipmentCategory.SIMPLE_RANGED));
            gen.level(1).addChoice(new EquipmentChoice("Adventure Pack",
                EquipmentPack.DIPLOMAT_PACK, EquipmentPack.ENTERTAINER_PACK));
            gen.level(1).addEquipment(LEATHER_ARMOUR, DAGGER);
            gen.level(1).addChoice(3, new AttributeChoice("Musical Instrument Proficiency",
                MusicalInstrument.getAllProficiencies()));
            gen.level(1).addChoice(new EquipmentChoice("Musical Instrument")
                .with(EquipmentCategory.MUSICAL_INSTRUMENTS));
            gen.level(1).addAttributes(BARDIC_INSPIRATION);
            gen.level(2).addAttributes(JACK_OF_ALL_TRADES);
            gen.level(2).addAttributes(SONG_OF_REST);
            gen.level(3).addChoice(new AttributeChoice("Bard College", BardicCollege.values()));
            gen.level(3, 10).addChoice(2, new ExpertiseChoice());
            gen.level(4, 8, 12, 16, 19).addChoice(2, new AbilityScoreOrFeatIncrease());
            gen.level(5).addAttributes(FONT_OF_INSPIRATION);
            gen.level(6).addAttributes(COUNTERCHARM);
            gen.level(20).addAttributes(SUPERIOR_INSPIRATION);
            final int[][] spellCount = {
                {},
                {2, 2},
                {2, 3},
                {2, 4, 2},
                {3, 4, 3},
                {3, 4, 3, 2},
                {3, 4, 3, 3},
                {3, 4, 3, 3, 1},
                {3, 4, 3, 3, 2},
                {3, 4, 3, 3, 3, 1},
                {4, 4, 3, 3, 3, 2},
                {4, 4, 3, 3, 3, 2, 1},
                {4, 4, 3, 3, 3, 2, 1},
                {4, 4, 3, 3, 3, 2, 1, 1},
                {4, 4, 3, 3, 3, 2, 1, 1},
                {4, 4, 3, 3, 3, 2, 1, 1, 1},
                {4, 4, 3, 3, 3, 2, 1, 1, 1},
                {4, 4, 3, 3, 3, 2, 1, 1, 1, 1},
                {4, 4, 3, 3, 3, 3, 1, 1, 1, 1},
                {4, 4, 3, 3, 3, 3, 2, 1, 1, 1},
                {4, 4, 3, 3, 3, 3, 2, 2, 1, 1}
            };
            gen.level(10, 14, 18).addChoice(2, new AttributeChoice("Magical Secrets",
                Arrays.stream(Spell.values())
                    .filter(sp -> sp.getLevel() <= spellCount[character.getLevel()].length)
                    .map(sp -> (Attribute) sp)));
            addSpells(gen, BARD, spellCount, character.getLevel());
            gen.generateChoices(character);
        }

        @Override
        public Stream<Spell> getSpells() {
            return Stream.of(
                BLADE_WARD, DANCING_LIGHTS, FRIENDS, LIGHT, MAGE_HAND, MENDING, MESSAGE,
                MINOR_ILLUSION, PRESTIDIGITATION, TRUE_STRIKE, VICIOUS_MOCKERY,
                ANIMAL_FRIENDSHIP, BANE, CHARM_PERSON, COMPREHEND_LANGUAGES, CURE_WOUNDS,
                DETECT_MAGIC, DISGUISE_SELF, DISSONANT_WHISPERS, FAERIE_FIRE, FEATHER_FALL,
                HEALING_WORD, HEROISM, IDENTIFY, ILLUSORY_SCRIPT, LONGSTRIDER, SILENT_IMAGE, SLEEP,
                SPEAK_WITH_ANIMALS, TASHAS_HIDEOUS_LAGHTER, THUNDERWAVE, UNSEEN_SERVANT,
                ANIMAL_MESSENGER, BLINDNESS_DEAFNESS, CALM_EMOTIONS, CLOUD_OF_DAGGERS,
                CROWN_OF_MADNESS, DETECT_THOUGHTS, ENHANCE_ABILITY, ENTHRALL, HEAT_METAL,
                HOLD_PERSON, INVISIBILITY, KNOCK, LESSER_RESTORATION, LOCATE_ANIMALS_OR_PLANTS,
                LOCATE_OBJECT, MAGIC_MOUTH, PHANTASMAL_FORCE, SEE_INVISIBILITY, SHATTER,
                SILENCE, SUGGESTION, ZONE_OF_TRUTH,
                BESTOW_CURSE, CLAIRVOYANCE, DISPEL_MAGIC, FEAR, FEIGN_DEATH, GLYPH_OF_WARDING,
                HYPNOTIC_PATTERN, TINY_HUT, MAJOR_IMAGE, NONDETECTION, PLANT_GROWTH,
                SENDING, SPEAK_WITH_DEAD, SPEAK_WITH_PLANTS, STINKING_CLOUD, TONGUES,
                COMPULSION, CONFUSION, DIMENSION_DOOR, FREEDOM_OF_MOVEMENT, GREATER_INVISIBILITY,
                HALLUCINATORY_TERRAIN, LOCATE_CREATURE, POLYMORPH,
                ANIMATE_OBJECTS, AWAKEN, DOMINATE_PERSON, DREAM, GEAS, GREATER_RESTORATION,
                HOLD_MONSTER, LEGEND_LORE, MASS_CURE_WOUNDS, MISLEAD, MODIFY_MEMORY,
                PLANAR_BINDING, RAISE_DEAD, SCRYING, SEEMING, TELEPORTATION_CIRCLE,
                EYEBITE, FIND_THE_PATH, GUARDS_AND_WARDS, MASS_SUGGESTION, IRRESISTIBLE_DANCE,
                PROGRAMMED_ILLUSION, TRUE_SEEING, ETHEREALNESS, FORCECAGE, MIRAGE_ARCANE,
                MAGNIFICENT_MANSION, MORDENKAINENS_SWORD, PROJECT_IMAGE,
                REGENERATE, RESURRECTION, SYMBOL, TELEPORT,
                DOMINATE_MONSTER, FEEBLEMIND, GLIBNESS, MIND_BLANK, POWER_WORD_STUN,
                FORESIGHT, POWER_WORD_HEAL, POWER_WORD_KILL, TRUE_POLYMORPH);
        }
    },
    CLERIC(8, DIVINE_DOMAIN, WISDOM, CHARISMA,
        Arrays.asList(WISDOM, CONSTITUTION, STRENGTH)) {
        public void generateLevelChoices(Character character) {
            ChoiceGenerator gen = new ChoiceGenerator();
            gen.level(1).addAttributes(new SpellCasting(WISDOM));
            gen.level(1).addAttributes(Proficiency.LIGHT_ARMOUR, Proficiency.MEDIUM_ARMOUR,
                Proficiency.SHIELD, ALL_SIMPLE_WEAPONS);
            gen.level(1).addChoice(new AttributeChoice("Divine Domain", DivineDomain.values()));
            gen.level(1).addChoice(new AttributeChoice("Skill",
                HISTORY, INSIGHT, MEDICINE, PERSUASION, RELIGION).withCount(2));
            gen.level(1).addChoice(new EquipmentChoice("Primary Weapon", MACE, WARHAMMER));
            gen.level(1).addChoice(new EquipmentChoice("Secondary Weapon")
                .with(EquipmentCategory.SIMPLE_MELEE)
                .with(LIGHT_CROSSBOW, new EquipmentSet(CROSSBOW_BOLT, 20)));
            gen.level(1).addChoice(new EquipmentChoice("Armour",
                SCALE_MAIL_ARMOUR, LEATHER_ARMOUR, CHAIN_MAIL_ARMOUR));
            gen.level(1).addChoice(new EquipmentChoice(EquipmentCategory.HOLY_SYMBOL));
            gen.level(2).addAttributes(TURN_UNDEAD, CHANNEL_DIVINITY);
            gen.level(4, 8, 12, 19).addChoice(2, new AbilityScoreOrFeatIncrease());
            gen.level(5).addAttributes(DESTROY_UNDEAD);
            gen.level(10).addAttributes(DIVINE_INTERVENTION);
            final int[][] spellCount = {
                {},
                {3, 2},
                {3, 3},
                {3, 4, 2},
                {4, 4, 3},
                {4, 4, 3, 2},
                {4, 4, 3, 3},
                {4, 4, 3, 3, 1},
                {4, 4, 3, 3, 2},
                {4, 4, 3, 3, 3, 1},
                {5, 4, 3, 3, 3, 2},
                {5, 4, 3, 3, 3, 2, 1},
                {5, 4, 3, 3, 3, 2, 1},
                {5, 4, 3, 3, 3, 2, 1, 1},
                {5, 4, 3, 3, 3, 2, 1, 1},
                {5, 4, 3, 3, 3, 2, 1, 1, 1},
                {5, 4, 3, 3, 3, 2, 1, 1, 1},
                {5, 4, 3, 3, 3, 2, 1, 1, 1, 1},
                {5, 4, 3, 3, 3, 3, 1, 1, 1, 1},
                {5, 4, 3, 3, 3, 3, 2, 1, 1, 1},
                {5, 4, 3, 3, 3, 3, 2, 2, 1, 1}
            };
            addSpells(gen, CLERIC, spellCount, character.getLevel());
            gen.generateChoices(character);
        }

        @Override
        public Stream<Spell> getSpells() {
            return Stream.of(
                GUIDANCE, LIGHT, MENDING, RESISTANCE, SACRED_FLAME, SPARE_THE_DYING, THAUMATURGY,
                BANE, BLESS, COMMAND, CREATE_OR_DESTROY_WATER, CURE_WOUNDS, DETECT_EVIL_AND_GOOD,
                DETECT_MAGIC, DETECT_POISON_AND_DISEASE, GUIDING_BOLT, HEALING_WORD, INFLICT_WOUNDS,
                PROTECTION_FROM_EVIL_AND_GOOD, PURIFY_FOOD_AND_DRINK, SANCTUARY, SHIELD_OF_FAITH,
                AID, AUGURY, BLINDNESS_DEAFNESS, CALM_EMOTIONS, CONTINUAL_FLAME, ENHANCE_ABILITY,
                FIND_TRAPS, GENTLE_REPOSE, HOLD_PERSON, LESSER_RESTORATION, LOCATE_OBJECT,
                PRAYER_OF_HEALING, PROTECTION_FROM_POISON, SILENCE, SPIRITUAL_WEAPON, WARDING_BOND,
                ZONE_OF_TRUTH,
                ANIMATE_DEAD, BEACON_OF_HOPE, BESTOW_CURSE, CLAIRVOYANCE, CREATE_FOOD_AND_WATER,
                DAYLIGHT, DISPEL_MAGIC, FEIGN_DEATH, GLYPH_OF_WARDING, MAGIC_CIRCLE,
                MASS_HEALING_WORD, MELD_INTO_STONE, PROTECTION_FROM_ENERGY, REMOVE_CURSE, REVIVIFY,
                SENDING, SPEAK_WITH_DEAD, SPIRIT_GUARDIANS, TONGUES, WATER_WALK,
                BANISHMENT, CONTROL_WATER, DEATH_WARD, DIVINATION, FREEDOM_OF_MOVEMENT,
                GUARDIAN_OF_FAITH, LOCATE_CREATURE, STONE_SHAPE,
                COMMUNE, CONTAGION, DISPEL_EVIL_AND_GOOD, FLAME_STRIKE, GEAS, GREATER_RESTORATION,
                HALLOW, INSECT_PLAGUE, LEGEND_LORE, MASS_CURE_WOUNDS, PLANAR_BINDING, RAISE_DEAD,
                SCRYING,
                BLADE_BARRIER, CREATE_UNDEAD, FIND_THE_PATH, FORBIDDANCE, HARM, HEAL, HEROES_FEAST,
                PLANAR_ALLY, TRUE_SEEING, WORD_OF_RECALL,
                CONJURE_CELESTIAL, DIVINE_WORD, ETHEREALNESS, FIRE_STORM, PLANE_SHIFT, REGENERATE,
                RESURRECTION, SYMBOL,
                ANTIMAGIC_FIELD, CONTROL_WEATHER, EARTHQUAKE, HOLY_AURA,
                ASTRAL_PROJECTION, GATE, MASS_HEAL, TRUE_RESURRECTION);
        }
    },
    DRUID(8, DRUID_CIRCLE, INTELLIGENCE, WISDOM, Arrays.asList(WISDOM, CONSTITUTION)) {
        public void generateLevelChoices(Character character) {
            ChoiceGenerator gen = new ChoiceGenerator();
            gen.level(1).addAttributes(new SpellCasting(WISDOM));
            gen.level(1).addAttributes(Proficiency.LIGHT_ARMOUR, Proficiency.MEDIUM_ARMOUR,
                Proficiency.SHIELD, DRUIDIC);
            gen.level(1).addWeaponProficiencies(CLUB, DAGGER, DART, JAVELIN, MACE, QUARTERSTAFF,
                SCIMITAR, SICKLE, SLING, SPEAR);
            gen.level(1).addChoice(2, new AttributeChoice("Skill", ARCANA, ANIMAL_HANDLING,
                INSIGHT, MEDICINE, NATURE, PERCEPTION, RELIGION, SURVIVAL));
            gen.level(1).addChoice(new EquipmentChoice("Weapon").with(SCIMITAR)
                .with(EquipmentCategory.SIMPLE_MELEE).with(SIMPLE_RANGED));
            gen.level(1).addChoice(new EquipmentChoice("Weapon or Shield").with(Armour.SHIELD)
                .with(EquipmentCategory.SIMPLE_MELEE).with(SIMPLE_RANGED));
            gen.level(1).addEquipment(LEATHER_ARMOUR, EXPLORER_PACK);
            gen.level(1).addChoice(new EquipmentChoice("Focus").with(DRUIDIC_FOCUS));
            gen.level(2).addAttributes(WILD_SHAPE);
            final int[][] spellCount = {
                {},
                {2, 2},
                {2, 3},
                {2, 4, 2},
                {3, 4, 3},
                {3, 4, 3, 2},
                {3, 4, 3, 3},
                {3, 4, 3, 3, 1},
                {3, 4, 3, 3, 2},
                {3, 4, 3, 3, 3, 1},
                {4, 4, 3, 3, 3, 2},
                {4, 4, 3, 3, 3, 2, 1},
                {4, 4, 3, 3, 3, 2, 1},
                {4, 4, 3, 3, 3, 2, 1, 1},
                {4, 4, 3, 3, 3, 2, 1, 1},
                {4, 4, 3, 3, 3, 2, 1, 1, 1},
                {4, 4, 3, 3, 3, 2, 1, 1, 1},
                {4, 4, 3, 3, 3, 2, 1, 1, 1, 1},
                {4, 4, 3, 3, 3, 3, 1, 1, 1, 1},
                {4, 4, 3, 3, 3, 3, 2, 1, 1, 1},
                {4, 4, 3, 3, 3, 3, 2, 2, 1, 1}
            };
            addSpells(gen, DRUID, spellCount, character.getLevel());
            gen.generateChoices(character);
        }

        @Override
        public Stream<Spell> getSpells() {
            return Stream.of(
                DRUIDCRAFT, GUIDANCE, MENDING, POISON_SPRAY, PRODUCE_FLAME, RESISTANCE, SHILLELAGH,
                THORN_WHIP,
                ANIMAL_FRIENDSHIP, CHARM_PERSON, CREATE_OR_DESTROY_WATER, CURE_WOUNDS, DETECT_MAGIC,
                DETECT_POISON_AND_DISEASE, ENTANGLE, FAERIE_FIRE, FOG_CLOUD, GOODBERRY,
                HEALING_WORD, JUMP, LONGSTRIDER, PURIFY_FOOD_AND_DRINK, SPEAK_WITH_ANIMALS,
                THUNDERWAVE,
                ANIMAL_MESSENGER, BARKSKIN, BEAST_SENSE, Spell.DARKVISION, ENHANCE_ABILITY,
                FIND_TRAPS, FLAME_BLADE, FLAMING_SPHERE, GUST_OF_WIND, HEAT_METAL, HOLD_PERSON,
                LESSER_RESTORATION, LOCATE_ANIMALS_OR_PLANTS, LOCATE_OBJECT, MOONBEAM,
                PASS_WITHOUT_TRACE, PROTECTION_FROM_POISON, SPIKE_GROWTH,
                CALL_LIGHTNING, CONJURE_ANIMALS, DAYLIGHT, DISPEL_MAGIC, FEIGN_DEATH,
                MELD_INTO_STONE, PLANT_GROWTH, PROTECTION_FROM_ENERGY, SLEET_STORM,
                SPEAK_WITH_PLANTS, WATER_BREATHING, WATER_WALK, WIND_WALL,
                BLIGHT, CONFUSION, CONJURE_MINOR_ELEMENTALS, CONJURE_WOODLAND_BEINGS, CONTROL_WATER,
                DOMINATE_BEAST, FREEDOM_OF_MOVEMENT, GIANT_INSECT, GRASPING_VINE,
                HALLUCINATORY_TERRAIN, ICE_STORM, LOCATE_CREATURE, POLYMORPH, STONE_SHAPE,
                STONESKIN, WALL_OF_FIRE,
                ANTILIFE_SHELL, AWAKEN, COMMUNE_WITH_NATURE, CONJURE_ELEMENTAL, CONTAGION, GEAS,
                GREATER_RESTORATION, INSECT_PLAGUE, MASS_CURE_WOUNDS, PLANAR_BINDING, REINCARNATE,
                SCRYING, TREE_STRIDE, WALL_OF_STONE,
                CONJURE_FEY, FIND_THE_PATH, HEAL, HEROES_FEAST, MOVE_EARTH, SUNBEAM,
                TRANSPORT_VIA_PLANTS, WALL_OF_THORNS, WIND_WALK,
                FIRE_STORM, MIRAGE_ARCANE, PLANE_SHIFT, REGENERATE, REVERSE_GRAVITY,
                ANIMAL_SHAPES, ANTIPATHY_SYMPATHY, CONTROL_WEATHER, EARTHQUAKE, FEEBLEMIND,
                SUNBURST, TSUNAMI,
                FORESIGHT, SHAPECHANGE, STORM_OF_VENGEANCE, TRUE_RESURRECTION
            );
        }
    },
    FIGHTER(10, MARTIAL_ARCHETYPE, STRENGTH, CONSTITUTION,
        Arrays.asList(STRENGTH, DEXTERITY, CONSTITUTION)) {
        public void generateLevelChoices(Character character) {
            ChoiceGenerator gen = new ChoiceGenerator();
            gen.level(1).
                addAttributes(ALL_ARMOUR, ALL_WEAPONS, SECOND_WIND);
            gen.level(1).addChoice(new AttributeChoice("Skill",
                ACROBATICS, ANIMAL_HANDLING, ATHLETICS, HISTORY, INSIGHT,
                INTIMIDATION, PERCEPTION, SURVIVAL).withCount(2))
                .addChoice(new AttributeChoice(FIGHTING_STYLE));
            gen.level(1).addChoice(new EquipmentChoice("Armour")
                .with(CHAIN_MAIL_ARMOUR).with(LEATHER_ARMOUR, LONGBOW, new EquipmentSet(ARROW, 20)));
            gen.level(1).addChoice(new EquipmentChoice("Primary Weapon")
                .with(EquipmentCategory.MARTIAL_MELEE));
            gen.level(1).addChoice(new EquipmentChoice("Secondary Weapon Or Shield")
                .with(Armour.SHIELD)
                .with(EquipmentCategory.MARTIAL_MELEE));
            gen.level(1).addChoice(new EquipmentChoice("Ranged Weapon")
                .with(LIGHT_CROSSBOW, new EquipmentSet(CROSSBOW_BOLT, 20))
                .with(new EquipmentSet(HANDAXE, 2)));
            gen.level(1).addChoice(new EquipmentChoice("Adventure Pack",
                DUNGEONEER_PACK, EXPLORER_PACK));
            gen.level(2).addAttributes(ACTION_SURGE);
            gen.level(3).addChoice(
                new AttributeChoice("Martial Archetype", MartialArchetype.values()));
            gen.level(5).addAttributes(EXTRA_ATTACK_FIGHTER);
            gen.level(9).addAttributes(INDOMITABLE);
            gen.cond(levels(4, 6, 8, 12, 14, 16, 19)).addChoice(2, new AbilityScoreOrFeatIncrease());
            gen.generateChoices(character);
        }
    },
    MONK(8, MONASTIC_TRADITION, STRENGTH, DEXTERITY,
        Arrays.asList(DEXTERITY, WISDOM)) {
        @Override
        public void generateLevelChoices(Character character) {
            ChoiceGenerator gen = new ChoiceGenerator();
            gen.level(1).addWeaponProficiencies(SHORTSWORD);
            gen.level(1).addAttributes(ALL_SIMPLE_WEAPONS);
            gen.level(1).addChoice(new AttributeChoice("Tools",
                Stream.concat(
                    MusicalInstrument.getAllProficiencies(),
                    Proficiency.allOfType(AttributeType.TOOLS))));
            gen.level(1).addChoice(2, new AttributeChoice("Skill", ACROBATICS, ATHLETICS, HISTORY,
                INSIGHT, RELIGION, STEALTH));
            gen.level(1).addChoice(new EquipmentChoice("Weapon").with(SHORTSWORD)
                .with(EquipmentCategory.SIMPLE_MELEE).with(EquipmentCategory.SIMPLE_RANGED));
            gen.level(1).addChoice(new EquipmentChoice("Adventure Pack",
                DUNGEONEER_PACK, EXPLORER_PACK));
            gen.level(1).addEquipment(DART, 10);
            gen.level(1).addAttributes(UNARMORED_DEFENCE_MONK, MARTIAL_ARTS);
            gen.level(2).addAttributes(KI, FLURRY_OF_BLOWS, PATIENT_DEFENCE, STEP_OF_THE_WIND,
                UNARMOURED_MOVEMENT);
            gen.level(3).addChoice(
                new AttributeChoice("Monastic Tradition", MonasticTradition.values()));
            gen.level(3).addAttributes(DEFLECT_MISSILES);
            gen.level(4, 8, 12, 16, 19).addChoice(2, new AbilityScoreOrFeatIncrease());
            gen.level(4).addAttributes(SLOW_FALL);
            gen.level(5).addAttributes(EXTRA_ATTACK, STUNNING_STRIKE);
            gen.level(6).addAttributes(KI_EMPOWERED_STRIKES);
            gen.level(7).addAttributes(EVASION);
            gen.level(7).addAttributes(STILLNESS_OF_MIND);
            gen.level(10).addAttributes(PURITY_OF_BODY);
            gen.level(13).addAttributes(TONGUE_OF_THE_SUN_AND_MOON);
            gen.level(14).addAttributes(DIAMOND_SOUL);
            gen.level(15).addAttributes(TIMELESS_BODY);
            gen.level(18).addAttributes(EMPTY_BODY);
            gen.level(20).addAttributes(PERFECT_SELF);
            gen.generateChoices(character);
        }
    },
    PALADIN(10, SACRED_OATH, WISDOM, CHARISMA, Arrays.asList(STRENGTH, CHARISMA)) {
        public void generateLevelChoices(Character character) {
            ChoiceGenerator gen = new ChoiceGenerator();
            gen.level(1).addAttributes(ALL_ARMOUR, ALL_WEAPONS);
            gen.level(1).addChoice(2, new AttributeChoice("Skill", ATHLETICS, INSIGHT,
                INTIMIDATION, MEDICINE, PERSUASION, RELIGION));
            gen.level(1).addChoice(new EquipmentChoice("Weapon")
                .with(MARTIAL_MELEE).with(MARTIAL_RANGED));
            gen.level(1).addChoice(new EquipmentChoice("Weapon or Shield", Armour.SHIELD)
                .with(MARTIAL_MELEE).with(MARTIAL_RANGED));
            gen.level(1).addChoice(new EquipmentChoice("Secondary Weapon")
                .with(JAVELIN, 5).with(SIMPLE_MELEE));
            gen.level(1).addChoice(new EquipmentChoice("Adventure Pack",
                PRIEST_PACK, EXPLORER_PACK));
            gen.level(1).addEquipment(CHAIN_MAIL_ARMOUR);
            gen.level(1).addChoice(new EquipmentChoice(HOLY_SYMBOL));
            gen.level(1).addAttributes(DIVINE_SENSE, LAY_ON_HANDS);
            gen.level(2).addChoice(new AttributeChoice(FIGHTING_STYLE));
            gen.level(2).addAttributes(new SpellCasting(CHARISMA), DIVINE_SMITE);
            gen.level(3).addAttributes(DIVINE_HEALTH);
            gen.level(3).addChoice(new AttributeChoice("Sacred Oath", SacredOath.values()));
            gen.level(4, 8, 12, 16, 19).addChoice(2, new AbilityScoreOrFeatIncrease());
            gen.level(5).addAttributes(EXTRA_ATTACK);
            gen.level(6).addAttributes(AURA_OF_PROTECTION);
            gen.level(10).addAttributes(AURA_OF_COURAGE);
            gen.level(11).addAttributes(IMPROVED_DIVINE_SMITE);
            gen.level(14).addAttributes(CLEANSING_TOUCH);
            final int[][] spellCount = {
                {0},
                {0},
                {0, 2},
                {0, 3},
                {0, 3},
                {0, 4, 2},
                {0, 4, 2},
                {0, 4, 3},
                {0, 4, 3},
                {0, 4, 3, 2},
                {0, 4, 3, 2},
                {0, 4, 3, 3},
                {0, 4, 3, 3},
                {0, 4, 3, 3, 1},
                {0, 4, 3, 3, 1},
                {0, 4, 3, 3, 2},
                {0, 4, 3, 3, 2},
                {0, 4, 3, 3, 3, 1},
                {0, 4, 3, 3, 3, 1},
                {0, 4, 3, 3, 3, 2},
                {0, 4, 3, 3, 3, 2}
            };
            addSpells(gen, PALADIN, spellCount, character.getLevel());
            gen.generateChoices(character);
        }

        @Override
        public Stream<Spell> getSpells() {
            return Stream.of(
                BLESS, COMMAND, COMPELLED_DUEL, CURE_WOUNDS, DETECT_EVIL_AND_GOOD, DETECT_MAGIC,
                DETECT_POISON_AND_DISEASE, DIVINE_FAVOUR, HEROISM, PROTECTION_FROM_EVIL_AND_GOOD,
                PURIFY_FOOD_AND_DRINK, SEARING_SMITE, SHIELD_OF_FAITH, THUNDEROUS_SMITE,
                WRATHFUL_SMITE, AID, BRANDING_SMITE, FIND_STEED, LESSER_RESTORATION, LOCATE_OBJECT,
                MAGIC_WEAPON, PROTECTION_FROM_POISON, ZONE_OF_TRUTH, AURA_OF_VITALITY,
                BLINDING_SMITE, CREATE_FOOD_AND_WATER, CRUSADERS_MANTLE, DAYLIGHT, DISPEL_MAGIC,
                ELEMENTAL_WEAPON, MAGIC_CIRCLE, REMOVE_CURSE, REVIVIFY, AURA_OF_LIFE,
                AURA_OF_PURITY, BANISHMENT, DEATH_WARD, LOCATE_CREATURE, STAGGERING_SMITE,
                BANISHING_SMITE, CIRCLE_OF_POWER, DESTRUCTIVE_WAVE, DISPEL_EVIL_AND_GOOD, GEAS,
                RAISE_DEAD);
        }
    },
    RANGER(10, RANGER_ARCHETYPE, STRENGTH, DEXTERITY, Arrays.asList(DEXTERITY, WISDOM)) {
        public void generateLevelChoices(Character character) {
            ChoiceGenerator gen = new ChoiceGenerator();
            gen.level(1).addAttributes(Proficiency.LIGHT_ARMOUR, Proficiency.MEDIUM_ARMOUR,
                Proficiency.SHIELD);
            gen.level(1).addAttributes(ALL_WEAPONS);
            gen.level(1).addChoice(3, new AttributeChoice("Skill", ANIMAL_HANDLING, ATHLETICS,
                INSIGHT, INVESTIGATION, NATURE, PERCEPTION, STEALTH, SURVIVAL));
            gen.level(1).addChoice(new EquipmentChoice("Armour", SCALE_MAIL_ARMOUR, LEATHER_ARMOUR));
            gen.level(1).addChoice(2, new EquipmentChoice("Weapon")
                .with(SHORTSWORD).with(EquipmentCategory.SIMPLE_MELEE));
            gen.level(1).addChoice(new EquipmentChoice("Adventure Pack",
                DUNGEONEER_PACK, EXPLORER_PACK));
            gen.level(1).addEquipment(LONGBOW).addEquipment(ARROW, 20);
            gen.level(1, 6, 14).addChoice(
                new AttributeChoice("Favoured Enemy", FavouredEnemy.values()));
            gen.level(1, 6, 10).addChoice(
                new AttributeChoice("Favoured Terrain", FavouredTerrain.values()));
            gen.level(2).addChoice(new AttributeChoice("Fighting Style",
                ARCHERY, DEFENSE, DUELING, TWO_WEAPON));
            gen.level(2).addAttributes(new SpellCasting(WISDOM));
            gen.level(3).addChoice(new AttributeChoice("Ranger Archetype",
                RangerArchetype.values()));
            gen.level(3).addAttributes(PRIMEVAL_AWARENESS);
            gen.level(4, 8, 12, 16, 19).addChoice(2, new AbilityScoreOrFeatIncrease());
            gen.level(5).addAttributes(EXTRA_ATTACK);
            gen.level(8).addAttributes(LANDS_STRIDE);
            gen.level(10).addAttributes(HIDE_IN_PLAIN_SIGHT);
            gen.level(14).addAttributes(VANISH);
            gen.level(18).addAttributes(FERAL_SENSES);
            gen.level(20).addAttributes(FOE_SLAYER);
            final int[][] spellCount = {
                {0},
                {0},
                {0, 2},
                {0, 3},
                {0, 3},
                {0, 4, 2},
                {0, 4, 2},
                {0, 4, 3},
                {0, 4, 3},
                {0, 4, 3, 2},
                {0, 4, 3, 2},
                {0, 4, 3, 3},
                {0, 4, 3, 3},
                {0, 4, 3, 3, 1},
                {0, 4, 3, 3, 1},
                {0, 4, 3, 3, 2},
                {0, 4, 3, 3, 2},
                {0, 4, 3, 3, 3, 1},
                {0, 4, 3, 3, 3, 1},
                {0, 4, 3, 3, 3, 2},
                {0, 4, 3, 3, 3, 2}
            };
            addSpells(gen, RANGER, spellCount, character.getLevel());
            gen.generateChoices(character);
        }

        @Override
        public Stream<Spell> getSpells() {
            return Stream.of(
                ALARM, ANIMAL_FRIENDSHIP, CURE_WOUNDS, DETECT_MAGIC, DETECT_POISON_AND_DISEASE,
                ENSNARING_STRIKE, FOG_CLOUD, GOODBERRY, HAIL_OF_THORNS, HUNTERS_MARK, JUMP,
                LONGSTRIDER, SPEAK_WITH_ANIMALS, ANIMAL_MESSENGER, BARKSKIN, BEAST_SENSE,
                CORDON_OF_ARROWS, Spell.DARKVISION, FIND_TRAPS, LESSER_RESTORATION,
                LOCATE_ANIMALS_OR_PLANTS, LOCATE_OBJECT, PASS_WITHOUT_TRACE, PROTECTION_FROM_POISON,
                SILENCE, SPIKE_GROWTH, CONJURE_ANIMALS, CONJURE_BARRAGE, DAYLIGHT, LIGHTNING_ARROW,
                NONDETECTION, PLANT_GROWTH, PROTECTION_FROM_ENERGY, SPEAK_WITH_PLANTS,
                WATER_BREATHING, WATER_WALK, WIND_WALL, CONJURE_WOODLAND_BEINGS,
                FREEDOM_OF_MOVEMENT, GRASPING_VINE, LOCATE_CREATURE, STONESKIN, COMMUNE_WITH_NATURE,
                CONJURE_VOLLEY, SWIFT_QUIVER, TREE_STRIDE);
        }
    },
    ROGUE(8, ROGUISH_ARCHETYPE, DEXTERITY, INTELLIGENCE,
        Arrays.asList(DEXTERITY, INTELLIGENCE, CHARISMA)) {
        public void generateLevelChoices(Character character) {
            ChoiceGenerator gen = new ChoiceGenerator();
            gen.level(1).addAttributes(Proficiency.LIGHT_ARMOUR, ALL_SIMPLE_WEAPONS,
                Proficiency.THIEVES_TOOLS);
            gen.level(1).addWeaponProficiencies(HAND_CROSSBOW, LONGSWORD, RAPIER, SHORTSWORD);
            gen.level(1).addAttributes(SNEAK_ATTACK, THIEVES_CANT);
            gen.level(1).
                addChoice(new AttributeChoice("Skill", ACROBATICS, ATHLETICS, DECEPTION, INSIGHT,
                    INTIMIDATION, INVESTIGATION, PERCEPTION, PERFORMANCE, PERSUASION,
                    SLEIGHT_OF_HAND, STEALTH).withCount(4));
            gen.level(1).addChoice(new ExpertiseChoice().withCount(2));
            gen.level(1).addEquipment(LEATHER_ARMOUR)
                .addEquipment(DAGGER, 2)
                .addEquipment(EquipmentType.THIEVES_TOOLS);
            gen.level(1).addChoice(new EquipmentChoice("Primary Weapon", RAPIER, SHORTSWORD));
            gen.level(1).addChoice(new EquipmentChoice("Secondard Weapon")
                .with(SHORTBOW, QUIVER, new EquipmentSet(ARROW, 20))
                .with(SHORTSWORD));
            gen.level(1).addChoice(new EquipmentChoice("Adventure Pack",
                BUGLAR_PACK, DUNGEONEER_PACK, EXPLORER_PACK));
            gen.level(2).addAttributes(CUNNING_ACTION);
            gen.level(3).addChoice(new AttributeChoice("Roguish Archetype",
                RoguishArchetype.values()));
            gen.level(5).addAttributes(UNCANNY_DODGE);
            gen.level(6).addChoice(new ExpertiseChoice().withCount(2));
            gen.level(7).addAttributes(EVASION);
            gen.level(11).addAttributes(RELIABLE_TALENT);
            gen.level(14).addAttributes(BLINDSENSE);
            gen.level(15).addAttributes(SLIPPERY_MIND);
            gen.level(18).addAttributes(ELUSIVE);
            gen.level(20).addAttributes(STROKE_OF_LUCK);
            gen.cond(levels(4, 8, 10, 12, 16, 19)).addChoice(2, new AbilityScoreOrFeatIncrease());
            gen.generateChoices(character);
        }
    },
    SORCERER(6, SORCEROUS_ORIGIN, CONSTITUTION, CHARISMA, Arrays.asList(CHARISMA, CONSTITUTION)) {
        public void generateLevelChoices(Character character) {
            ChoiceGenerator gen = new ChoiceGenerator();
            gen.level(1).addAttributes(new SpellCasting(CHARISMA));
            gen.level(1).addWeaponProficiencies(DAGGER, DART, SLING, QUARTERSTAFF, LIGHT_CROSSBOW);
            gen.level(1).addChoice(new AttributeChoice("Skill", ARCANA, DECEPTION, INSIGHT,
                INTIMIDATION, PERSUASION, RELIGION));
            gen.level(1).addChoice(new EquipmentChoice("Weapon")
                .with(LIGHT_CROSSBOW, new EquipmentSet(TRIDENT, 0))
                .with(SIMPLE_MELEE).with(SIMPLE_RANGED));
            gen.level(1).addChoice(new EquipmentChoice("Spellcasting")
                .with(COMPONENT_POUCH).with(ARCANE_FOCUS));
            gen.level(1).addChoice(new EquipmentChoice("Adventure Pack",
                DUNGEONEER_PACK, EXPLORER_PACK));
            gen.level(2).addAttributes(FONT_OF_MAGIC);
            final int[][] spellCount = {
                {},
                {4, 2},
                {4, 3},
                {4, 4, 2},
                {5, 4, 3},
                {5, 4, 3, 2},
                {5, 4, 3, 3},
                {5, 4, 3, 3, 1},
                {5, 4, 3, 3, 2},
                {5, 4, 3, 3, 3, 1},
                {6, 4, 3, 3, 3, 2},
                {6, 4, 3, 3, 3, 2, 1},
                {6, 4, 3, 3, 3, 2, 1},
                {6, 4, 3, 3, 3, 2, 1, 1},
                {6, 4, 3, 3, 3, 2, 1, 1},
                {6, 4, 3, 3, 3, 2, 1, 1, 1},
                {6, 4, 3, 3, 3, 2, 1, 1, 1},
                {6, 4, 3, 3, 3, 2, 1, 1, 1, 1},
                {6, 4, 3, 3, 3, 3, 1, 1, 1, 1},
                {6, 4, 3, 3, 3, 3, 2, 1, 1, 1},
                {6, 4, 3, 3, 3, 3, 2, 2, 1, 1}
            };
            addSpells(gen, SORCERER, spellCount, character.getLevel());
            gen.generateChoices(character);
        }

        @Override
        public Stream<Spell> getSpells() {
            return Stream.of(
                ACID_SPLASH, BLADE_WARD, CHILL_TOUCH, DANCING_LIGHTS, FIRE_BOLT, FRIENDS, LIGHT,
                MAGE_HAND, MENDING, MESSAGE, MINOR_ILLUSION, POISON_SPRAY, PRESTIDIGITATION,
                RAY_OF_FROST, SHOCKING_GRASP, TRUE_STRIKE, BURNING_HANDS, CHARM_PERSON,
                CHROMATIC_ORB, COLOUR_SPRAY, COMPREHEND_LANGUAGES, DETECT_MAGIC, DISGUISE_SELF,
                EXPEDITIOUS_RETREAT, FALSE_LIFE, FEATHER_FALL, FOG_CLOUD, JUMP, MAGE_ARMOR,
                MAGIC_MISSILE, RAY_OF_SICKNESS, Spell.SHIELD, SILENT_IMAGE, SLEEP, THUNDERWAVE,
                WITCH_BOLT, ALTER_SELF, BLINDNESS_DEAFNESS, BLUR, CLOUD_OF_DAGGERS,
                CROWN_OF_MADNESS, DARKNESS, Spell.DARKVISION, DETECT_THOUGHTS, ENHANCE_ABILITY,
                ENLARGE_REDUCE, GUST_OF_WIND, HOLD_PERSON, INVISIBILITY, KNOCK,
                LEVITATE, MIRROR_IMAGE, MISTY_STEP, PHANTASMAL_FORCE, SCORCHING_RAY,
                SEE_INVISIBILITY, SHATTER, SPIDER_CLIMB, SUGGESTION, WEB,
                BLINK, CLAIRVOYANCE, COUNTERSPELL, DAYLIGHT, DISPEL_MAGIC, FEAR,
                FIREBALL, FLY, GASEOUS_FORM, HASTE, HYPNOTIC_PATTERN,
                LIGHTNING_BOLT, MAJOR_IMAGE, PROTECTION_FROM_ENERGY, SLEET_STORM, SLOW,
                STINKING_CLOUD, TONGUES, WATER_BREATHING, WATER_WALK,
                BANISHMENT, BLIGHT, CONFUSION,
                DIMENSION_DOOR, DOMINATE_BEAST, GREATER_INVISIBILITY, ICE_STORM, POLYMORPH,
                STONESKIN, WALL_OF_FIRE, ANIMATE_OBJECTS, CLOUDKILL, CONE_OF_COLD, CREATION,
                DOMINATE_PERSON, HOLD_MONSTER, INSECT_PLAGUE, SEEMING, TELEKINESIS,
                TELEPORTATION_CIRCLE, WALL_OF_STONE, ARCANE_GATE, CHAIN_LIGHTNING, CIRCLE_OF_DEATH,
                DISINTEGRATE, EYEBITE, GLOBE_OF_INVULNERABILITY, MASS_SUGGESTION,
                MOVE_EARTH, SUNBEAM, TRUE_SEEING, DELAYED_BLAST_FIREBALL, ETHEREALNESS,
                FINGER_OF_DEATH, FIRE_STORM,
                PLANE_SHIFT, PRISMATIC_SPRAY, REVERSE_GRAVITY, TELEPORT,
                DOMINATE_MONSTER, EARTHQUAKE, INCENDIARY_CLOUD, POWER_WORD_STUN, SUNBURST,
                GATE, METEOR_SWARM, POWER_WORD_KILL, TIME_STOP, WISH);
        }
    },
    WARLOCK(8, OTHERWORLDLY_PATRON, WISDOM, CHARISMA, Arrays.asList(CHARISMA, CONSTITUTION)) {
        public void generateLevelChoices(Character character) {
            ChoiceGenerator gen = new ChoiceGenerator();
            gen.level(1).addAttributes(new SpellCasting(CHARISMA));
            gen.level(1).addChoice(spellChoice(2, "Cantrip", getSpells(l -> l == 0)));
            gen.level(1).addChoice(spellChoice(2, "Spell", getSpells(upTo(1))));
            gen.level(2).addChoice(new ReplaceAttributeChoice<>("Spell", 1, getSpells(upTo(1))));
            gen.level(2).addChoice(spellChoice(1, "Spell", getSpells(upTo(1))));
            gen.level(3).addChoice(spellChoice(1, "Spell", getSpells(upTo(2))));
            gen.level(4).addChoice(spellChoice(1, "Cantrip", getSpells(l -> l == 0)));
            gen.level(4).addChoice(spellChoice(1, "Spell", getSpells(upTo(2))));
            gen.level(5).addChoice(spellChoice(1, "Spell", getSpells(upTo(3))));
            gen.level(6).addChoice(spellChoice(1, "Spell", getSpells(upTo(3))));
            gen.level(7).addChoice(spellChoice(1, "Spell", getSpells(upTo(4))));
            gen.level(8).addChoice(spellChoice(1, "Spell", getSpells(upTo(4))));
            gen.level(9).addChoice(spellChoice(1, "Spell", getSpells(upTo(5))));
            gen.level(10).addChoice(spellChoice(1, "Cantrip", getSpells(l -> l == 0)));
            gen.level(11).addChoice(spellChoice(1, "Spell", getSpells(upTo(5))));
            gen.level(13).addChoice(spellChoice(1, "Spell", getSpells(upTo(5))));
            gen.level(15).addChoice(spellChoice(1, "Spell", getSpells(upTo(5))));
            gen.level(17).addChoice(spellChoice(1, "Spell", getSpells(upTo(5))));
            gen.level(19).addChoice(spellChoice(1, "Spell", getSpells(upTo(5))));
            gen.generateChoices(character);
        }

        private Predicate<Integer> upTo(int max) {
            return l -> l >= 1 && l <= max;
        }

        @Override
        public Stream<Spell> getSpells() {
            return Stream.of(
                BLADE_WARD, CHILL_TOUCH, ELDRITCH_BLAST, FRIENDS, MAGE_HAND, MINOR_ILLUSION,
                POISON_SPRAY, PRESTIDIGITATION, TRUE_STRIKE,
                ARMOUR_OF_AGATHYS, ARMS_OF_HADAR, CHARM_PERSON, COMPREHEND_LANGUAGES,
                EXPEDITIOUS_RETREAT, HELLISH_REBUKE, HEX, ILLUSORY_SCRIPT,
                PROTECTION_FROM_EVIL_AND_GOOD, UNSEEN_SERVANT, WITCH_BOLT,
                CLOUD_OF_DAGGERS, CROWN_OF_MADNESS, DARKNESS, ENTHRALL, HOLD_PERSON, INVISIBILITY,
                MIRROR_IMAGE, MISTY_STEP, RAY_OF_ENFEEBLEMENT, SHATTER, SPIDER_CLIMB, SUGGESTION,
                COUNTERSPELL, DISPEL_MAGIC, FEAR, FLY, GASEOUS_FORM, HUNGER_OF_HADAR,
                HYPNOTIC_PATTERN, MAGIC_CIRCLE, MAJOR_IMAGE, REMOVE_CURSE, TONGUES, VAMPIRIC_TOUCH,
                BANISHMENT, BLIGHT, DIMENSION_DOOR, HALLUCINATORY_TERRAIN,
                CONTACT_OTHER_PLANE, DREAM, HOLD_MONSTER, SCRYING,
                ARCANE_GATE, CIRCLE_OF_DEATH, CONJURE_FEY, CREATE_UNDEAD, EYEBITE, FLESH_TO_STONE,
                MASS_SUGGESTION, TRUE_SEEING,
                ETHEREALNESS, FINGER_OF_DEATH, FORCECAGE, PLANE_SHIFT,
                DEMIPLANE, DOMINATE_MONSTER, FEEBLEMIND, GLIBNESS, POWER_WORD_STUN,
                ASTRAL_PROJECTION, FORESIGHT, IMPRISONMENT, POWER_WORD_KILL, TRUE_POLYMORPH);
        }
    },
    WIZARD(6, ARCANE_TRADITION, INTELLIGENCE, WISDOM,
        Arrays.asList(INTELLIGENCE, DEXTERITY, CONSTITUTION)) {
        public void generateLevelChoices(Character character) {
            ChoiceGenerator gen = new ChoiceGenerator();
            gen.level(1).addAttributes(new SpellCasting(INTELLIGENCE));
            gen.level(1).addWeaponProficiencies(DAGGER, DART, SLING, QUARTERSTAFF, LIGHT_CROSSBOW);
            gen.level(1).addChoice(new AttributeChoice("Skill",
                ARCANA, HISTORY, INSIGHT, INVESTIGATION, MEDICINE, RELIGION).withCount(2));
            gen.level(1).addChoice(new EquipmentChoice("Weapon", QUARTERSTAFF, DAGGER));
            gen.level(1).addChoice(new EquipmentChoice("Wizard Gear", COMPONENT_POUCH,
                CRYSTAL, ORB, ROD, STAFF, WAND));
            gen.level(1).addChoice(new EquipmentChoice("Adventure Pack",
                SCHOLAR_PACK, EXPLORER_PACK));
            gen.level(1).addEquipment(SPELLBOOK);
            gen.level(1).addAttributes(ARCANE_RECOVERY);
            gen.level(2).addChoice(new AttributeChoice("Arcane Tradition", MagicSchool.values()));
            gen.cond(levels(4, 8, 12, 16, 19)).addChoice(2, new AbilityScoreOrFeatIncrease());
            gen.level(18).addChoice(spellMasteryChoice("Spell Mastery", 1));
            gen.level(18).addChoice(spellMasteryChoice("Spell Mastery", 2));
            gen.level(20).addChoice(spellMasteryChoice("Signature Spell", 3));
            gen.level(20).addChoice(spellMasteryChoice("Signature Spell", 3));
            final int[][] spellCount = {
                {},
                {3, 2},
                {3, 3},
                {3, 4, 2},
                {4, 4, 3},
                {4, 4, 3, 2},
                {4, 4, 3, 3},
                {4, 4, 3, 3, 1},
                {4, 4, 3, 3, 2},
                {4, 4, 3, 3, 3, 1},
                {5, 4, 3, 3, 3, 2},
                {5, 4, 3, 3, 3, 2, 1},
                {5, 4, 3, 3, 3, 2, 1},
                {5, 4, 3, 3, 3, 2, 1, 1},
                {5, 4, 3, 3, 3, 2, 1, 1},
                {5, 4, 3, 3, 3, 2, 1, 1, 1},
                {5, 4, 3, 3, 3, 2, 1, 1, 1},
                {5, 4, 3, 3, 3, 2, 1, 1, 1, 1},
                {5, 4, 3, 3, 3, 3, 1, 1, 1, 1},
                {5, 4, 3, 3, 3, 3, 2, 1, 1, 1},
                {5, 4, 3, 3, 3, 3, 2, 2, 1, 1}
            };
            addSpells(gen, this, spellCount, character.getLevel());
            gen.generateChoices(character);
        }

        @Override
        public Stream<Spell> getSpells() {
            return Stream.of(
                ACID_SPLASH, BLADE_WARD, CHILL_TOUCH, DANCING_LIGHTS, FIRE_BOLT, FRIENDS, LIGHT,
                MAGE_HAND, MENDING, MESSAGE, MINOR_ILLUSION, POISON_SPRAY, PRESTIDIGITATION,
                RAY_OF_FROST, SHOCKING_GRASP, TRUE_STRIKE,
                ALARM, BURNING_HANDS, CHARM_PERSON, CHROMATIC_ORB, COLOUR_SPRAY,
                COMPREHEND_LANGUAGES, DETECT_MAGIC, DISGUISE_SELF, EXPEDITIOUS_RETREAT,
                FALSE_LIFE, FEATHER_FALL, FIND_FAMILIAR, FOG_CLOUD, GREASE, IDENTIFY,
                ILLUSORY_SCRIPT, JUMP, LONGSTRIDER, MAGE_ARMOR, MAGIC_MISSILE,
                PROTECTION_FROM_EVIL_AND_GOOD, RAY_OF_SICKNESS, Spell.SHIELD, SILENT_IMAGE,
                SLEEP, TASHAS_HIDEOUS_LAGHTER, TENSERS_FLOATING_DISK, THUNDERWAVE,
                UNSEEN_SERVANT, WITCH_BOLT,
                ALTER_SELF, ARCANE_LOCK, BLINDNESS_DEAFNESS, BLUR, CLOUD_OF_DAGGERS,
                CONTINUAL_FLAME, CROWN_OF_MADNESS, DARKNESS, DETECT_THOUGHTS, ENLARGE_REDUCE,
                FLAMING_SPHERE, GENTLE_REPOSE, GUST_OF_WIND, HOLD_PERSON, INVISIBILITY, KNOCK,
                LEVITATE, LOCATE_OBJECT, MAGIC_MOUTH, MAGIC_WEAPON, MELFS_ACID_ARROW,
                MIRROR_IMAGE, MISTY_STEP, ARCANISTS_MAGIC_AURA, PHANTASMAL_FORCE,
                RAY_OF_ENFEEBLEMENT, ROPE_TRICK, SCORCHING_RAY, SEE_INVISIBILITY, SHATTER,
                SPIDER_CLIMB, SUGGESTION, WEB,
                ANIMATE_DEAD, BESTOW_CURSE, BLINK, CLAIRVOYANCE, COUNTERSPELL, DISPEL_MAGIC, FEAR,
                FEIGN_DEATH, FIREBALL, FLY, GASEOUS_FORM, GLYPH_OF_WARDING, HASTE, HYPNOTIC_PATTERN,
                TINY_HUT, LIGHTNING_BOLT, MAGIC_CIRCLE, MAJOR_IMAGE, NONDETECTION,
                PHANTOM_STEED, PROTECTION_FROM_ENERGY, REMOVE_CURSE, SENDING, STINKING_CLOUD,
                SENDING, SLEET_STORM, SLOW, STINKING_CLOUD, TONGUES, VAMPIRIC_TOUCH,
                WATER_BREATHING,
                ARCANE_EYE, BANISHMENT, BLIGHT, CONFUSION, CONJURE_MINOR_ELEMENTALS, CONTROL_WATER,
                DIMENSION_DOOR, EVARDS_BLACK_TENTACLES, FABRICATE, FIRE_SHIELD,
                GREATER_INVISIBILITY, HALLUCINATORY_TERRAIN, ICE_STORM, LEOMUNDS_SECRET_CHEST,
                LOCATE_CREATURE, MORDENKAINENS_FAITHFUL_HOUND, MORDENKAINENS_PRIVATE_SANCTUM,
                PHANTASMAL_KILLER, POLYMORPH, STONE_SHAPE, STONESKIN, WALL_OF_FIRE,
                ANIMATE_OBJECTS, ARCANE_HAND, CLOUDKILL, CONE_OF_COLD, CONJURE_ELEMENTAL,
                CONTACT_OTHER_PLANE, CREATION, DOMINATE_PERSON, DREAM, GEAS, HOLD_MONSTER,
                LEGEND_LORE, MISLEAD, MODIFY_MEMORY, PASSWALL, PLANAR_BINDING,
                RARYS_TELEPATHIC_BOND, SCRYING, SEEMING, TELEKINESIS, TELEPORTATION_CIRCLE,
                WALL_OF_FORCE, WALL_OF_STONE,
                ARCANE_GATE, CHAIN_LIGHTNING, CIRCLE_OF_DEATH, CONTINGENCY, CREATE_UNDEAD,
                DISINTEGRATE, DRAWMIJS_INSTANT_SUMMONS, EYEBITE, FLESH_TO_STONE,
                GLOBE_OF_INVULNERABILITY, GUARDS_AND_WARDS, MAGIC_JAR, MASS_SUGGESTION,
                MOVE_EARTH, FREEZING_SPHERE, IRRESISTIBLE_DANCE,
                PROGRAMMED_ILLUSION, SUNBEAM, TRUE_SEEING, WALL_OF_ICE,
                DELAYED_BLAST_FIREBALL, ETHEREALNESS, FINGER_OF_DEATH, FORCECAGE,
                MIRAGE_ARCANE, MAGNIFICENT_MANSION, MORDENKAINENS_SWORD,
                PLANE_SHIFT, PRISMATIC_SPRAY, PROJECT_IMAGE, REVERSE_GRAVITY, SEQUESTER,
                SIMULACRUM, SYMBOL, TELEPORT,
                ANTIMAGIC_FIELD, ANTIPATHY_SYMPATHY, CLONE, CONTROL_WEATHER, DEMIPLANE,
                DOMINATE_MONSTER, FEEBLEMIND, INCENDIARY_CLOUD, MAZE, MIND_BLANK, POWER_WORD_STUN,
                SUNBURST, TELEPATHY,
                ASTRAL_PROJECTION, FORESIGHT, GATE, IMPRISONMENT, METEOR_SWARM, POWER_WORD_KILL,
                PRISMATIC_WALL, SHAPECHANGE, TIME_STOP, TRUE_POLYMORPH, WEIRD, WISH);
        }
    };

    private final int hitDie;
    private final AttributeType classAttribute;
    private final List<AttributeType> savingThrows = new ArrayList<>();
    private final List<AttributeType> primaryAttributes;

    private static void addSpells(ChoiceGenerator gen, CharacterClass charClass,
        int[][] spells, int level) {
        for (int spellLev = 0; spellLev < spells[level].length; spellLev++) {
            int count = spells[level][spellLev];
            if (spellLev < spells[level - 1].length)
                count -= spells[level - 1][spellLev];
            if (count > 0) {
                String name = spellLev == 0 ? "Cantrip" : "Spell Level " + spellLev;
                final int sl = spellLev;
                gen.level(level).addChoice(spellChoice(count, name,
                    charClass.getSpells(lev -> lev == sl)));
            }
        }
    }

    private static Choice spellMasteryChoice(String name, int level) {
        return new OptionChoice(name) {
            @Override
            public void select(Character character, ChoiceSelector selector) {
                CharacterClass characterClass = character.getAttribute(CHARACTER_CLASS);
                selector.chooseOption(characterClass.getSpells()
                    .filter(sp -> sp.getLevel() == level)
                    .filter(sp
                        -> character.getAllAttributes()
                        .noneMatch(attr -> attr.toString().equals(name + " " + sp.toString()))
                    )
                    .map(sp -> (Attribute) sp),
                    spell -> {
                    character.addAttribute(new SpellMastery(name, (Spell) spell));
                });
            }
        };
    }

    private CharacterClass(int hitDie, AttributeType classAttribute,
        AttributeType savingThrow1, AttributeType savingThrow2,
        List<AttributeType> primaryAttributes) {
        this.hitDie = hitDie;
        this.classAttribute = classAttribute;
        savingThrows.add(savingThrow1);
        savingThrows.add(savingThrow2);
        this.primaryAttributes = primaryAttributes;
    }

    public Optional<AttributeType> getClassAttribute() {
        return Optional.ofNullable(classAttribute);
    }

    @Override
    public AttributeType getType() {
        return CHARACTER_CLASS;
    }

    public int getHitDie() {
        return hitDie;
    }

    public Stream<AttributeType> getPrimaryAttributes() {
        return primaryAttributes.stream();
    }

    public boolean hasSavingsThrow(AttributeType type) {
        return savingThrows.contains(type);
    }

    @Override
    public void generateInitialChoices(Character character) {
        character.addAttributes(
            new IntAttribute(AttributeType.LEVEL, 1),
            new IntAttribute(AttributeType.EXPERIENCE_POINTS, 0));
        generateLevelChoices(character);
    }

    @Override
    public abstract void generateLevelChoices(Character character);

    public Stream<Spell> getSpells() {
        return Stream.empty();
    }

    public Stream<Spell> getSpells(Predicate<Integer> test) {
        return getSpells().filter(sp -> test.test(sp.getLevel()));
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    public static CharacterClass load(Node node) {
        return CharacterClass.valueOf(node.getTextContent());
    }
}
