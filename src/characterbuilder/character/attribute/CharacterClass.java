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
import static characterbuilder.character.attribute.AttributeType.*;
import characterbuilder.character.choice.AbilityScoreIncrease;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.Choice;
import characterbuilder.character.choice.ChoiceGenerator;
import static characterbuilder.character.choice.ChoiceGenerator.levels;
import static characterbuilder.character.choice.ChoiceGenerator.spellChoice;
import characterbuilder.character.choice.ChoiceSelector;
import characterbuilder.character.choice.EquipmentChoice;
import characterbuilder.character.choice.ExpertiseChoice;
import characterbuilder.character.choice.OptionChoice;
import characterbuilder.character.equipment.Armour;
import static characterbuilder.character.equipment.Armour.CHAIN_MAIL_ARMOUR;
import static characterbuilder.character.equipment.Armour.LEATHER_ARMOUR;
import static characterbuilder.character.equipment.Armour.SCALE_MAIL_ARMOUR;
import characterbuilder.character.equipment.EquipmentCategory;
import characterbuilder.character.equipment.EquipmentPack;
import static characterbuilder.character.equipment.EquipmentPack.BUGLAR_PACK;
import static characterbuilder.character.equipment.EquipmentPack.DUNGEONEER_PACK;
import static characterbuilder.character.equipment.EquipmentPack.EXPLORER_PACK;
import static characterbuilder.character.equipment.EquipmentPack.SCHOLAR_PACK;
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
import java.util.stream.Stream;
import org.w3c.dom.Node;

public enum CharacterClass implements Attribute {
    BARBARIAN(12, PRIMAL_PATH, STRENGTH, CONSTITUTION,
        Arrays.asList(STRENGTH, CONSTITUTION)) {
        @Override
        public void generateLevelChoices(Character character) {
            ChoiceGenerator gen = new ChoiceGenerator();
            gen.level(1).addAttributes(LIGHT_ARMOUR, MEDIUM_ARMOUR, SHIELD, ALL_WEAPONS);
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
            gen.cond(levels(4, 8, 12, 16, 19)).addChoice(2, new AbilityScoreIncrease());
            gen.generateChoices(character);
        }
    },
    BARD(8, BARDIC_COLLEGE, DEXTERITY, CHARISMA,
        Arrays.asList(CHARISMA, DEXTERITY)) {
        public void generateLevelChoices(Character character) {
            ChoiceGenerator gen = new ChoiceGenerator();
            gen.level(1).addAttributes(LIGHT_ARMOUR, ALL_SIMPLE_WEAPONS);
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
            gen.level(4, 8, 12, 16, 19).addChoice(2, new AbilityScoreIncrease());
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
    },
    CLERIC(8, DIVINE_DOMAIN, WISDOM, CHARISMA,
        Arrays.asList(WISDOM, CONSTITUTION, STRENGTH)) {
        public void generateLevelChoices(Character character) {
            ChoiceGenerator gen = new ChoiceGenerator();
            gen.level(1).addAttributes(LIGHT_ARMOUR, MEDIUM_ARMOUR, Proficiency.SHIELD,
                ALL_SIMPLE_WEAPONS);
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
            gen.level(4, 8, 12, 19).addChoice(2, new AbilityScoreIncrease());
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
    },
    DRUID(8, DRUID_CIRCLE, INTELLIGENCE, WISDOM, Arrays.asList(WISDOM, CONSTITUTION)) {
        public void generateLevelChoices(Character character) {
            ChoiceGenerator gen = new ChoiceGenerator();
            gen.generateChoices(character);
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
            gen.cond(levels(4, 6, 8, 12, 14, 16, 19)).addChoice(2, new AbilityScoreIncrease());
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
                    Proficiency.allOfType(TOOLS))));
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
            gen.level(4, 8, 12, 16, 19).addChoice(2, new AbilityScoreIncrease());
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
            gen.generateChoices(character);
        }
    },
    RANGER(10, RANGER_ARCHETYPE, STRENGTH, DEXTERITY, Arrays.asList(DEXTERITY, WISDOM)) {
        public void generateLevelChoices(Character character) {
            ChoiceGenerator gen = new ChoiceGenerator();
            gen.generateChoices(character);
        }
    },
    ROGUE(8, ROGUISH_ARCHETYPE, DEXTERITY, INTELLIGENCE,
        Arrays.asList(DEXTERITY, INTELLIGENCE, CHARISMA)) {
        public void generateLevelChoices(Character character) {
            ChoiceGenerator gen = new ChoiceGenerator();
            gen.level(1).addAttributes(LIGHT_ARMOUR, ALL_SIMPLE_WEAPONS, Proficiency.THIEVES_TOOLS);
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
            gen.cond(levels(4, 8, 10, 12, 16, 19)).addChoice(2, new AbilityScoreIncrease());
            gen.generateChoices(character);
        }
    },
    SORCERER(6, SORCEROUS_ORIGIN, CONSTITUTION, CHARISMA, Arrays.asList(CHARISMA, CONSTITUTION)) {
        public void generateLevelChoices(Character character) {
            ChoiceGenerator gen = new ChoiceGenerator();
            gen.generateChoices(character);
        }
    },
    WARLOCK(8, OTHERWORLDLY_PATRON, WISDOM, CHARISMA, Arrays.asList(CHARISMA, CONSTITUTION)) {
        public void generateLevelChoices(Character character) {
            ChoiceGenerator gen = new ChoiceGenerator();
            gen.generateChoices(character);
        }
    },
    WIZARD(6, ARCANE_TRADITION, INTELLIGENCE, WISDOM,
        Arrays.asList(INTELLIGENCE, DEXTERITY, CONSTITUTION)) {
        public void generateLevelChoices(Character character) {
            ChoiceGenerator gen = new ChoiceGenerator();
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
            gen.cond(levels(4, 8, 12, 16, 19)).addChoice(2, new AbilityScoreIncrease());
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
            if (count > 0)
                gen.level(level).addChoice(spellChoice(count, charClass, spellLev));
        }
    }

    private static Choice spellMasteryChoice(String name, int level) {
        return new OptionChoice(name) {
            @Override
            public void select(Character character, ChoiceSelector selector) {
                selector.chooseOption(Spell.
                    spells(character.getAttribute(CHARACTER_CLASS), level)
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

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    public static CharacterClass load(Node node) {
        return CharacterClass.valueOf(node.getTextContent());
    }
}
