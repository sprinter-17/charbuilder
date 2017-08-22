package characterbuilder.character.characterclass;

import characterbuilder.character.characterclass.barbarian.PrimalPath;
import characterbuilder.character.characterclass.ranger.RangerArchetype;
import characterbuilder.character.characterclass.fighter.MartialArchetype;
import characterbuilder.character.characterclass.paladin.SacredOath;
import characterbuilder.character.characterclass.rogue.RoguishArchetype;
import characterbuilder.character.characterclass.warlock.OtherwordlyPatron;
import characterbuilder.character.characterclass.monk.MonasticTradition;
import characterbuilder.character.characterclass.wizard.MagicSchool;
import characterbuilder.character.characterclass.ranger.FavouredTerrain;
import characterbuilder.character.characterclass.ranger.FavouredEnemy;
import characterbuilder.character.characterclass.warlock.EldritchInvocation;
import characterbuilder.character.characterclass.cleric.DivineDomain;
import characterbuilder.character.characterclass.bard.BardicCollege;
import characterbuilder.character.characterclass.druid.DruidCircle;
import characterbuilder.character.Character;
import characterbuilder.character.ability.Ability;
import static characterbuilder.character.ability.Ability.*;
import characterbuilder.character.ability.Proficiency;
import static characterbuilder.character.ability.Proficiency.*;
import characterbuilder.character.ability.Skill;
import static characterbuilder.character.ability.Skill.*;
import characterbuilder.character.attribute.AbilityScore;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.*;
import characterbuilder.character.attribute.IntAttribute;
import static characterbuilder.character.characterclass.warlock.MysticArcanum.chooseArcanum;
import characterbuilder.character.choice.AbilityScoreOrFeatIncrease;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.ChoiceGenerator;
import static characterbuilder.character.choice.ChoiceGenerator.cantripChoice;
import static characterbuilder.character.choice.ChoiceGenerator.levels;
import characterbuilder.character.choice.ChoiceSelector;
import characterbuilder.character.choice.EquipmentChoice;
import characterbuilder.character.choice.ExpertiseChoice;
import characterbuilder.character.choice.OptionChoice;
import characterbuilder.character.equipment.AdventureGear;
import static characterbuilder.character.equipment.AdventureGear.*;
import characterbuilder.character.equipment.Armour;
import static characterbuilder.character.equipment.Armour.*;
import characterbuilder.character.equipment.EquipmentCategory;
import static characterbuilder.character.equipment.EquipmentCategory.*;
import characterbuilder.character.equipment.EquipmentPack;
import static characterbuilder.character.equipment.EquipmentPack.*;
import characterbuilder.character.equipment.EquipmentSet;
import characterbuilder.character.equipment.MusicalInstrument;
import characterbuilder.character.equipment.Weapon;
import static characterbuilder.character.equipment.Weapon.*;
import characterbuilder.character.spell.SignatureSpell;
import characterbuilder.character.spell.Spell;
import characterbuilder.character.spell.SpellAbility;
import characterbuilder.character.spell.SpellCasting;
import characterbuilder.character.spell.SpellClassMap;
import characterbuilder.utils.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Stream;
import org.w3c.dom.Node;

public enum CharacterClass implements Attribute {
    BARBARIAN(12, PRIMAL_PATH, STRENGTH, CONSTITUTION,
        Arrays.asList(STRENGTH, CONSTITUTION), (cls, gen) -> {
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
        gen.cond(levels(4, 8, 12, 16, 19)).addChoice(2, new AbilityScoreOrFeatIncrease());
        gen.level(5).addAttributes(EXTRA_ATTACK, FAST_MOVEMENT);
        gen.level(7).addAttributes(FERAL_INSTINCTS);
        gen.level(9).addAttributes(BRUTAL_CRITICAL);
        gen.level(11).addAttributes(RELENTLESS_RAGE);
        gen.level(15).addAttributes(PERSISTENT_RAGE);
        gen.level(18).addAttributes(INDOMITABLE_MIGHT);
        gen.level(20).addAction("Increase Str. and Con.", ch -> {
            ch.getAttribute(STRENGTH, AbilityScore.class).setMax(24);
            ch.getAttribute(STRENGTH, AbilityScore.class).addValue(4);
            ch.getAttribute(CONSTITUTION, AbilityScore.class).setMax(24);
            ch.getAttribute(CONSTITUTION, AbilityScore.class).addValue(4);
        });
    }),
    BARD(8, BARDIC_COLLEGE, DEXTERITY, CHARISMA,
        Arrays.asList(CHARISMA, DEXTERITY), (cls, gen) -> {
        gen.level(1).addSpellCasting("Bard", CHARISMA, cls, "All");
        gen.level(1).addChoice(cantripChoice(2, CHARISMA));
        gen.level(4, 10).addChoice(cantripChoice(1, CHARISMA));
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
            .with(EquipmentCategory.MUSICAL_INSTRUMENT));
        gen.level(1).addAttributes(BARDIC_INSPIRATION);
        gen.level(2).addAttributes(JACK_OF_ALL_TRADES);
        gen.level(2).addAttributes(SONG_OF_REST);
        gen.level(3).addChoice(new AttributeChoice("Bard College", BardicCollege.values()));
        gen.level(3, 10).addChoice(2, new ExpertiseChoice());
        gen.level(4, 8, 12, 16, 19).addChoice(2, new AbilityScoreOrFeatIncrease());
        gen.level(5).addAttributes(FONT_OF_INSPIRATION);
        gen.level(6).addAttributes(COUNTERCHARM);
        gen.level(20).addAttributes(SUPERIOR_INSPIRATION);
        gen.level(1).addSpellSlots("Bard", 1, 2);
        gen.level(2, 3).addSpellSlots("Bard", 1, 1);
        gen.level(3).addSpellSlots("Bard", 2, 2);
        gen.level(4).addSpellSlots("Bard", 2, 1);
        gen.level(5).addSpellSlots("Bard", 3, 2);
        gen.level(6).addSpellSlots("Bard", 3, 1);
        gen.level(7, 8, 9).addSpellSlots("Bard", 4, 1);
        gen.level(9, 10).addSpellSlots("Bard", 5, 1);
        gen.level(11, 19).addSpellSlots("Bard", 6, 1);
        gen.level(13, 12).addSpellSlots("Bard", 7, 1);
        gen.level(15).addSpellSlots("Bard", 8, 1);
        gen.level(17).addSpellSlots("Bard", 9, 1);

        gen.cond(ch -> ch.getLevel() > 1).replaceSpell("Bard");
        gen.level(1).addKnownSpells("Bard", 4);
        gen.level(2, 3, 4, 5, 6, 7, 8, 9, 11, 13, 15, 17).addKnownSpells("Bard", 1);
        gen.level(10, 14, 18)
            .addChoice(2, new OptionChoice("Magical Secrets") {
                @Override
                public void select(Character character, ChoiceSelector selector) {
                    SpellCasting casting = character.getSpellCasting("Bard");
                    selector.chooseOption(Arrays.stream(Spell.values())
                        .filter(spell -> !casting.hasLearntSpell(spell))
                        .filter(spell -> spell.getLevel() <= casting.getMaxSlot()), spell -> {
                        if (spell.isCantrip()) {
                            character.addAttribute(new SpellAbility(spell, CHARISMA));
                        } else {
                            casting.addKnownSpells(1);
                            casting.addLearntSpell(spell);
                        }
                    });
                }
            });
    }) {

    },
    CLERIC(8, DIVINE_DOMAIN, WISDOM, CHARISMA,
        Arrays.asList(WISDOM, CONSTITUTION, STRENGTH), (cls, gen) -> {
        gen.level(1).addSpellCasting("Cleric", WISDOM, cls, "[$wis_mod + $level]");
        gen.level(1).learnAllSpells("Cleric");
        gen.level(1).addChoice(cantripChoice(3, WISDOM));
        gen.level(5, 10).addChoice(cantripChoice(1, WISDOM));
        gen.level(1).addSpellSlots("Cleric", 1, 2);
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
        gen.level(2, 3).addSpellSlots("Cleric", 1, 1);
        gen.level(3).addSpellSlots("Cleric", 2, 2);
        gen.level(4, 8, 12, 16, 19).addChoice(2, new AbilityScoreOrFeatIncrease());
        gen.level(4).addSpellSlots("Cleric", 2, 1);
        gen.level(5).addSpellSlots("Cleric", 3, 2);
        gen.level(6).addSpellSlots("Cleric", 3, 1);
        gen.level(7, 8, 9).addSpellSlots("Cleric", 4, 1);
        gen.level(9, 10, 18).addSpellSlots("Cleric", 5, 1);
        gen.level(11, 19).addSpellSlots("Cleric", 6, 1);
        gen.level(13, 20).addSpellSlots("Cleric", 7, 1);
        gen.level(15).addSpellSlots("Cleric", 8, 1);
        gen.level(17).addSpellSlots("Cleric", 9, 1);
    }),
    DRUID(8, DRUID_CIRCLE, INTELLIGENCE, WISDOM, Arrays.asList(WISDOM, CONSTITUTION), (cls, gen)
        -> {
        gen.level(1).addSpellCasting("Druid", WISDOM, cls, "[$wis_mod + $level]");
        gen.level(1).learnAllSpells("Druid");
        gen.level(1).addChoice(cantripChoice(2, WISDOM));
        gen.level(4, 10).addChoice(cantripChoice(1, WISDOM));
        gen.level(1).addSpellSlots("Druid", 1, 2);
        gen.level(2, 3).addSpellSlots("Druid", 1, 1);
        gen.level(3).addSpellSlots("Druid", 2, 2);
        gen.level(4).addSpellSlots("Druid", 2, 1);
        gen.level(5).addSpellSlots("Druid", 3, 4);
        gen.level(6).addSpellSlots("Druid", 3, 1);
        gen.level(7, 8, 9).addSpellSlots("Druid", 4, 1);
        gen.level(9, 10, 18).addSpellSlots("Druid", 5, 1);
        gen.level(11, 19).addSpellSlots("Druid", 6, 1);
        gen.level(13, 20).addSpellSlots("Druid", 7, 1);
        gen.level(15).addSpellSlots("Druid", 8, 1);
        gen.level(17).addSpellSlots("Druid", 9, 1);
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
        gen.level(2).addChoice(new AttributeChoice("Druid Circle", DruidCircle.values()));
        gen.level(4, 8, 12, 16, 19).addChoice(2, new AbilityScoreOrFeatIncrease());
    }),
    FIGHTER(10, MARTIAL_ARCHETYPE, STRENGTH, CONSTITUTION,
        Arrays.asList(STRENGTH, DEXTERITY, CONSTITUTION), (cls, gen) -> {
        gen.level(1).addAttributes(ALL_ARMOUR, ALL_WEAPONS, SECOND_WIND);
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
    }),
    MONK(8, MONASTIC_TRADITION, STRENGTH, DEXTERITY,
        Arrays.asList(DEXTERITY, WISDOM), (cls, gen) -> {
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
    }),
    PALADIN(10, SACRED_OATH, WISDOM, CHARISMA, Arrays.asList(STRENGTH, CHARISMA), (cls, gen) -> {
        final String casting = cls.toString();
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
        gen.level(2)
            .addSpellCasting(casting, CHARISMA, cls, "[max(1, $chr_mod + $level/2)]")
            .learnAllSpells(casting).addSpellSlots(casting, 1, 2)
            .addAttributes(DIVINE_SMITE);
        gen.level(3).addAttributes(DIVINE_HEALTH);
        gen.level(3, 5).addSpellSlots(casting, 1, 1);
        gen.level(5).addSpellSlots(casting, 2, 2);
        gen.level(7).addSpellSlots(casting, 2, 1);
        gen.level(9).addSpellSlots(casting, 3, 2);
        gen.level(13).addSpellSlots(casting, 3, 1);
        gen.level(13, 15, 17).addSpellSlots(casting, 4, 1);
        gen.level(17, 19).addSpellSlots(casting, 5, 1);
        gen.level(3).addChoice(new AttributeChoice("Sacred Oath", SacredOath.values()));
        gen.level(4, 8, 12, 16, 19).addChoice(2, new AbilityScoreOrFeatIncrease());
        gen.level(5).addAttributes(EXTRA_ATTACK);
        gen.level(6).addAttributes(AURA_OF_PROTECTION);
        gen.level(10).addAttributes(AURA_OF_COURAGE);
        gen.level(11).addAttributes(IMPROVED_DIVINE_SMITE);
        gen.level(14).addAttributes(CLEANSING_TOUCH);
    }),
    RANGER(10, RANGER_ARCHETYPE, STRENGTH, DEXTERITY, Arrays.asList(DEXTERITY, WISDOM), (cls, gen)
        -> {
        String casting = cls.toString();
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
        gen.level(2)
            .addSpellCasting(casting, WISDOM, cls, "All")
            .addSpellSlots(casting, 1, 2)
            .addKnownSpells(casting, 2);
        gen.level(3, 5).addSpellSlots(casting, 1, 2);
        gen.level(5).addSpellSlots(casting, 2, 2);
        gen.level(7).addSpellSlots(casting, 2, 1);
        gen.level(9).addSpellSlots(casting, 3, 2);
        gen.level(11).addSpellSlots(casting, 3, 1);
        gen.level(13, 15, 17).addSpellSlots(casting, 4, 1);
        gen.level(17, 19).addSpellSlots(casting, 5, 1);
        gen.level(3, 5, 7, 9, 11, 13, 15, 17, 19).addKnownSpells(casting, 1);
        gen.cond(ch -> ch.getLevel() > 2).replaceSpell(casting);
    }),
    ROGUE(8, ROGUISH_ARCHETYPE, DEXTERITY, INTELLIGENCE,
        Arrays.asList(DEXTERITY, INTELLIGENCE, CHARISMA), (cls, gen) -> {
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
            .addEquipment(AdventureGear.THIEVES_TOOLS);
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
    }),
    SORCERER(6, SORCEROUS_ORIGIN, CONSTITUTION, CHARISMA,
        Arrays.asList(CHARISMA, CONSTITUTION), (cls, gen) -> {
        String casting = cls.toString();
        gen.level(1)
            .addWeaponProficiencies(DAGGER, DART, SLING, QUARTERSTAFF, LIGHT_CROSSBOW);
        gen.level(1).addChoice(new AttributeChoice("Skill", ARCANA, DECEPTION, INSIGHT,
            INTIMIDATION, PERSUASION, RELIGION));
        gen.level(1).addChoice(new EquipmentChoice("Weapon")
            .with(LIGHT_CROSSBOW, new EquipmentSet(CROSSBOW_BOLT, 20))
            .with(SIMPLE_MELEE).with(SIMPLE_RANGED));
        gen.level(1).addChoice(new EquipmentChoice("Spell Equipment")
            .with(COMPONENT_POUCH).with(ARCANE_FOCUS));
        gen.level(1).addChoice(new EquipmentChoice("Adventure Pack",
            DUNGEONEER_PACK, EXPLORER_PACK));
        gen.level(2).addAttributes(FONT_OF_MAGIC);

        gen.level(1)
            .addSpellCasting(casting, CHARISMA, cls, "All")
            .addSpellSlots(casting, 1, 2).addKnownSpells(casting, 2);
        gen.level(2, 3).addSpellSlots(casting, 1, 1);
        gen.level(3).addSpellSlots(casting, 2, 2);
        gen.level(4).addSpellSlots(casting, 2, 1);
        gen.level(5).addSpellSlots(casting, 3, 2);
        gen.level(6).addSpellSlots(casting, 3, 1);
        gen.level(7, 8, 9).addSpellSlots(casting, 4, 1);
        gen.level(9, 10, 18).addSpellSlots(casting, 5, 1);
        gen.level(11, 19).addSpellSlots(casting, 6, 1);
        gen.level(13, 20).addSpellSlots(casting, 7, 1);
        gen.level(15).addSpellSlots(casting, 8, 1);
        gen.level(17).addSpellSlots(casting, 9, 1);
        gen.cond(ch -> ch.getLevel() > 1).replaceSpell(casting);
        gen.level(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 15, 17).addKnownSpells(casting, 1);
    }),
    WARLOCK(8, OTHERWORLDLY_PATRON, WISDOM, CHARISMA,
        Arrays.asList(CHARISMA, CONSTITUTION), (cls, gen) -> {
        final String casting = cls.toString();
        gen.level(1).addAttributes(Proficiency.LIGHT_ARMOUR, Proficiency.ALL_SIMPLE_WEAPONS);
        gen.level(1).addChoice(2, new AttributeChoice("Skill", Skill.ARCANA, Skill.DECEPTION,
            Skill.HISTORY, Skill.INTIMIDATION, Skill.INVESTIGATION, Skill.NATURE, Skill.RELIGION));
        gen.level(1).addChoice(new EquipmentChoice("Primary Weapon")
            .with(Weapon.LIGHT_CROSSBOW, new EquipmentSet(ARROW, 20))
            .with(SIMPLE_MELEE).with(SIMPLE_RANGED));
        gen.level(1).addChoice(new EquipmentChoice("Secondary Weapon")
            .with(SIMPLE_MELEE).with(SIMPLE_RANGED));
        gen.level(1).addChoice(new EquipmentChoice("Magical Equipment")
            .with(COMPONENT_POUCH).with(ARCANE_FOCUS));
        gen.level(1).addChoice(new EquipmentChoice("Adventurer's Pack",
            EquipmentPack.SCHOLAR_PACK, EquipmentPack.DUNGEONEER_PACK));
        gen.level(1).addEquipment(LEATHER_ARMOUR, new EquipmentSet(DAGGER, 2));
        gen.level(1)
            .addChoice(new AttributeChoice("Otherworldy Patron", OtherwordlyPatron.values()));
        gen.level(2).addChoice(EldritchInvocation.getChoice(2));
        gen.cond(ch -> ch.getLevel() > 2).addChoice(EldritchInvocation.getReplacement());
        gen.level(5, 7, 9, 12, 15, 18).addChoice(EldritchInvocation.getChoice(1));

        gen.level(3).addChoice(new AttributeChoice("Pact Boon",
            Ability.PACT_OF_THE_CHAIN, Ability.PACT_OF_THE_BLADE, Ability.PACT_OF_THE_TOME));
        gen.level(4, 8, 12, 16, 19).addChoice(new AbilityScoreOrFeatIncrease());

        gen.level(11).addChoice(chooseArcanum(cls, 6));
        gen.level(13).addChoice(chooseArcanum(cls, 7));
        gen.level(15).addChoice(chooseArcanum(cls, 8));
        gen.level(17).addChoice(chooseArcanum(cls, 9));

        gen.level(20).addAttributes(ELDRITCH_MASTER);

        gen.level(1).addChoice(cantripChoice(2, CHARISMA));
        gen.level(4, 10).addChoice(cantripChoice(1, CHARISMA));
        gen.level(1).addSpellCasting(casting, CHARISMA, cls, "All")
            .setSpellSlots(casting, 1, 1).addKnownSpells(casting, 2);
        gen.level(2).setSpellSlots(casting, 1, 2);
        gen.level(3).setSpellSlots(casting, 2, 2);
        gen.level(5).setSpellSlots(casting, 3, 2);
        gen.level(7).setSpellSlots(casting, 4, 2);
        gen.level(9).setSpellSlots(casting, 5, 2);
        gen.level(11).setSpellSlots(casting, 5, 3);
        gen.level(17).setSpellSlots(casting, 5, 4);
        gen.cond(ch -> ch.getLevel() > 1).replaceSpell(casting);
        gen.level(2, 3, 4, 5, 6, 7, 8, 9, 11, 13, 15, 17, 19).addKnownSpells(casting, 1);
    }),
    WIZARD(6, ARCANE_TRADITION, INTELLIGENCE, WISDOM,
        Arrays.asList(INTELLIGENCE, DEXTERITY, CONSTITUTION), (cls, gen) -> {
        final String casting = cls.toString();
        gen.level(1).addWeaponProficiencies(DAGGER, DART, SLING, QUARTERSTAFF, LIGHT_CROSSBOW);
        gen.level(1).addChoice(new AttributeChoice("Skill",
            ARCANA, HISTORY, INSIGHT, INVESTIGATION, MEDICINE, RELIGION).withCount(2));
        gen.level(1).addChoice(new EquipmentChoice("Weapon", QUARTERSTAFF, DAGGER));
        gen.level(1).addChoice(new EquipmentChoice("Wizard Gear",
            COMPONENT_POUCH, CRYSTAL, ORB, ROD, STAFF, WAND));
        gen.level(1).addChoice(new EquipmentChoice("Adventure Pack",
            SCHOLAR_PACK, EXPLORER_PACK));
        gen.level(1).addEquipment(SPELLBOOK);
        gen.level(1).addAttributes(ARCANE_RECOVERY);
        gen.level(2).addChoice(new AttributeChoice("Arcane Tradition", MagicSchool.values()));
        gen.cond(levels(4, 8, 12, 16, 19)).addChoice(2, new AbilityScoreOrFeatIncrease());
        gen.level(18).addAttributes(Ability.SPELL_MASTERY);
        gen.level(20).addAction("Signature Spell", ch -> ch.addAttribute(new SignatureSpell()));
        gen.level(1).addChoice(cantripChoice(3, INTELLIGENCE));
        gen.level(4, 10).addChoice(cantripChoice(1, INTELLIGENCE));
        gen.level(1)
            .addSpellCasting(casting, INTELLIGENCE, cls, "[$int_mod + $level]")
            .addSpellSlots(casting, 1, 2);
        gen.level(2, 3).addSpellSlots(casting, 1, 1);
        gen.level(3).addSpellSlots(casting, 2, 2);
        gen.level(4).addSpellSlots(casting, 2, 1);
        gen.level(5).addSpellSlots(casting, 3, 2);
        gen.level(6).addSpellSlots(casting, 3, 1);
        gen.level(7, 8, 9).addSpellSlots(casting, 4, 1);
        gen.level(9, 10, 18).addSpellSlots(casting, 5, 1);
        gen.level(11, 19).addSpellSlots(casting, 6, 1);
        gen.level(13, 20).addSpellSlots(casting, 7, 1);
        gen.level(15).addSpellSlots(casting, 8, 1);
        gen.level(17).addSpellSlots(casting, 9, 1);
        gen.level(1).addKnownSpells(casting, 6);
        gen.cond(ch -> ch.getLevel() > 1).addKnownSpells(casting, 2);
    });

    private final int hitDie;
    private final AttributeType classAttribute;
    private final List<AttributeType> savingThrows = new ArrayList<>();
    private final List<AttributeType> primaryAttributes;
    private final BiConsumer<CharacterClass, ChoiceGenerator> generatorMaker;
    private Optional<ChoiceGenerator> generator = Optional.empty();

    private CharacterClass(int hitDie, AttributeType classAttribute,
        AttributeType savingThrow1, AttributeType savingThrow2,
        List<AttributeType> primaryAttributes, BiConsumer<CharacterClass, ChoiceGenerator> generator) {
        this.hitDie = hitDie;
        this.classAttribute = classAttribute;
        savingThrows.add(savingThrow1);
        savingThrows.add(savingThrow2);
        this.primaryAttributes = primaryAttributes;
        this.generatorMaker = generator;
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
    public void generateLevelChoices(Character character) {
        getGenerator().generateChoices(character);
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return getGenerator().getDescription(character);
    }

    public ChoiceGenerator getGenerator() {
        if (!generator.isPresent()) {
            generator = Optional.of(new ChoiceGenerator());
            generatorMaker.accept(this, generator.get());
        }
        return generator.get();
    }

    public Stream<Spell> getSpells() {
        final SpellClassMap map = new SpellClassMap();
        return map.spellsForClass(this);
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    public static CharacterClass load(Node node) {
        return CharacterClass.valueOf(node.getTextContent());
    }
}
