package characterbuilder.character.attribute;

import characterbuilder.character.Character;
import static characterbuilder.character.ability.Ability.*;
import characterbuilder.character.ability.DivineDomain;
import characterbuilder.character.ability.MagicSchool;
import characterbuilder.character.ability.Proficiency;
import static characterbuilder.character.ability.Proficiency.*;
import static characterbuilder.character.ability.Skill.*;
import characterbuilder.character.ability.Spell;
import static characterbuilder.character.attribute.AttributeType.*;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.Choice;
import characterbuilder.character.choice.ChoiceGenerator;
import static characterbuilder.character.choice.ChoiceGenerator.abilityScoreIncrease;
import static characterbuilder.character.choice.ChoiceGenerator.levels;
import static characterbuilder.character.choice.ChoiceGenerator.spellChoice;
import characterbuilder.character.choice.ChoiceSelector;
import characterbuilder.character.choice.EquipmentChoice;
import characterbuilder.character.choice.ExpertiseChoice;
import characterbuilder.character.choice.MultiChoice;
import characterbuilder.character.equipment.Armour;
import static characterbuilder.character.equipment.Armour.CHAIN_MAIL_ARMOUR;
import static characterbuilder.character.equipment.Armour.LEATHER_ARMOUR;
import static characterbuilder.character.equipment.Armour.SCALE_MAIL_ARMOUR;
import characterbuilder.character.equipment.EquipmentCategory;
import static characterbuilder.character.equipment.EquipmentPack.BUGLAR_PACK;
import static characterbuilder.character.equipment.EquipmentPack.DUNGEONEER_PACK;
import static characterbuilder.character.equipment.EquipmentPack.EXPLORER_PACK;
import static characterbuilder.character.equipment.EquipmentPack.SCHOLAR_PACK;
import characterbuilder.character.equipment.EquipmentSet;
import characterbuilder.character.equipment.EquipmentType;
import static characterbuilder.character.equipment.EquipmentType.*;
import static characterbuilder.character.equipment.Weapon.*;
import characterbuilder.utils.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public enum CharacterClass implements Attribute {
    CLERIC(8, 5, DIVINE_DOMAIN, WISDOM, CHARISMA,
        Arrays.asList(WISDOM, CONSTITUTION, STRENGTH)) {
        public void generateLevelChoices(Character character) {
            ChoiceGenerator gen = new ChoiceGenerator();
            gen.level(1).addAttributes(LIGHT_ARMOUR, MEDIUM_ARMOUR, Proficiency.SHIELD,
                ALL_SIMPLE_MELEE, ALL_SIMPLE_RANGED);
            gen.level(1).addChoice(new AttributeChoice("Divine Domain", DivineDomain.values()));
            gen.level(1).addChoice(new MultiChoice(2, new AttributeChoice("Skill",
                HISTORY, INSIGHT, MEDICINE, PERSUASION, RELIGION)));
            gen.level(1).addChoice(new EquipmentChoice("Primary Weapon", MACE, WARHAMMER));
            gen.level(1).addChoice(new EquipmentChoice("Secondary Weapon")
                .with(EquipmentCategory.SIMPLE_MELEE)
                .with(LIGHT_CROSSBOW, new EquipmentSet(CROSSBOW_BOLT, 20)));
            gen.level(1).addChoice(new EquipmentChoice("Armour",
                SCALE_MAIL_ARMOUR, LEATHER_ARMOUR, CHAIN_MAIL_ARMOUR));
            gen.level(1).addChoice(new EquipmentChoice(EquipmentCategory.HOLY_SYMBOL));
            gen.level(2).addAttributes(TURN_UNDEAD, CHANNEL_DIVINITY);
            gen.level(4).addChoice(abilityScoreIncrease(2));
            gen.level(5).addAttributes(DESTROY_UNDEAD);
            gen.level(8).addChoice(abilityScoreIncrease(2));
            gen.level(10).addAttributes(DIVINE_INTERVENTION);
            gen.level(12).addChoice(abilityScoreIncrease(2));
            gen.level(19).addChoice(abilityScoreIncrease(2));
            addSpells(gen, this, character.getLevel());
            gen.generateChoices(character);
        }
    },
    FIGHTER(10, 5, MARTIAL_ARCHETYPE, STRENGTH, CONSTITUTION,
        Arrays.asList(STRENGTH, DEXTERITY, CONSTITUTION)) {
        public void generateLevelChoices(Character character) {
            ChoiceGenerator gen = new ChoiceGenerator();
            gen.level(1).
                addAttributes(ALL_ARMOUR, ALL_MELEE_WEAPONS, ALL_RANGED_WEAPONS, SECOND_WIND);
            gen.level(1).addChoice(new MultiChoice(2, new AttributeChoice("Skill",
                ACROBATICS, ANIMAL_HANDLING, ATHLETICS, HISTORY, INSIGHT,
                INTIMIDATION, PERCEPTION, SURVIVAL)))
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
            gen.level(5).addAttributes(EXTRA_ATTACK);
            gen.level(9).addAttributes(INDOMITABLE);
            gen.cond(levels(4, 6, 8, 12, 14, 16, 19)).addChoice(abilityScoreIncrease(2));
            gen.generateChoices(character);
        }
    },
    ROGUE(8, 4, ROGUISH_ARCHETYPE, DEXTERITY, INTELLIGENCE,
        Arrays.asList(DEXTERITY, INTELLIGENCE, CHARISMA)) {
        public void generateLevelChoices(Character character) {
            ChoiceGenerator gen = new ChoiceGenerator();
            gen.level(1).addAttributes(LIGHT_ARMOUR, ALL_SIMPLE_MELEE, ALL_SIMPLE_RANGED,
                Proficiency.THIEVES_TOOLS);
            gen.level(1).addWeaponProficiencies(HAND_CROSSBOW, LONGSWORD, RAPIER, SHORTSWORD);
            gen.level(1).addAttributes(SNEAK_ATTACK, THIEVES_CANT);
            gen.level(1).addChoice(new MultiChoice(4,
                new AttributeChoice("Skill", ACROBATICS, ATHLETICS, DECEPTION, INSIGHT, INTIMIDATION,
                    INVESTIGATION, PERCEPTION, PERFORMANCE, PERSUASION, SLEIGHT_OF_HAND, STEALTH)));
            gen.level(1).addChoice(new MultiChoice(2, new ExpertiseChoice()));
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
            gen.level(6).addChoice(new MultiChoice(2, new ExpertiseChoice()));
            gen.level(7).addAttributes(EVASION);
            gen.level(11).addAttributes(RELIABLE_TALENT);
            gen.level(14).addAttributes(BLINDSENSE);
            gen.level(15).addAttributes(SLIPPERY_MIND);
            gen.level(18).addAttributes(ELUSIVE);
            gen.level(20).addAttributes(STROKE_OF_LUCK);
            gen.cond(levels(4, 8, 10, 12, 16, 19)).addChoice(abilityScoreIncrease(2));
            gen.generateChoices(character);
        }
    },
    WIZARD(6, 4, ARCANE_TRADITION, INTELLIGENCE, WISDOM,
        Arrays.asList(INTELLIGENCE, DEXTERITY, CONSTITUTION)) {
        public void generateLevelChoices(Character character) {
            ChoiceGenerator gen = new ChoiceGenerator();
            gen.level(1).addWeaponProficiencies(DAGGER, DART, SLING, QUARTERSTAFF, LIGHT_CROSSBOW);
            gen.level(1).addChoice(new MultiChoice(2, new AttributeChoice("Skill",
                ARCANA, HISTORY, INSIGHT, INVESTIGATION, MEDICINE, RELIGION)));
            gen.level(1).addChoice(new EquipmentChoice("Weapon", QUARTERSTAFF, DAGGER));
            gen.level(1).addChoice(new EquipmentChoice("Wizard Gear", COMPONENT_POUCH,
                CRYSTAL, ORB, ROD, STAFF, WAND));
            gen.level(1).addChoice(new EquipmentChoice("Adventure Pack",
                SCHOLAR_PACK, EXPLORER_PACK));
            gen.level(1).addEquipment(SPELLBOOK);
            gen.level(1).addAttributes(ARCANE_RECOVERY);
            gen.level(2).addChoice(new AttributeChoice("Arcane Tradition", MagicSchool.values()));
            gen.cond(levels(4, 8, 12, 16, 19)).addChoice(abilityScoreIncrease(2));
            gen.level(18).addChoice(spellMasteryChoice("Spell Mastery", 1));
            gen.level(18).addChoice(spellMasteryChoice("Spell Mastery", 2));
            gen.level(20).addChoice(spellMasteryChoice("Signature Spell", 3));
            gen.level(20).addChoice(spellMasteryChoice("Signature Spell", 3));
            addSpells(gen, this, character.getLevel());
            gen.generateChoices(character);
        }
    };

    private final int hitDie;
    private final int startingWealth;
    private final AttributeType classAttribute;
    private final List<AttributeType> savingThrows = new ArrayList<>();
    private final List<AttributeType> primaryAttributes;

    private static final int[][] SPELLS = {
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

    private static void addSpells(ChoiceGenerator gen, CharacterClass charClass, int level) {
        for (int spellLev = 0; spellLev < SPELLS[level].length; spellLev++) {
            int count = SPELLS[level][spellLev];
            if (spellLev < SPELLS[level - 1].length)
                count -= SPELLS[level - 1][spellLev];
            if (count > 0)
                gen.level(level).addChoice(spellChoice(count, charClass, spellLev));
        }
    }

    private static Choice spellMasteryChoice(String name, int level) {
        return new Choice() {
            @Override
            public void makeChoice(Character character, ChoiceSelector selector) {
                selector.getAttribute(Spell.
                    spells(character.getAttribute(CHARACTER_CLASS), level)
                    .filter(sp
                        -> character.getAllAttributes()
                        .noneMatch(attr -> attr.toString().equals(name + " " + sp.toString()))
                    )
                    .map(sp -> (Attribute) sp),
                    spell -> {
                    character.addAttribute(new SpellMastery(name, (Spell) spell));
                    character.getChoices().removeChoice(this);
                });
            }

            @Override
            public String toString() {
                return name;
            }
        };
    }

    private CharacterClass(int hitDie, int startingWealth, AttributeType classAttribute,
        AttributeType savingThrow1, AttributeType savingThrow2,
        List<AttributeType> primaryAttributes) {
        this.hitDie = hitDie;
        this.startingWealth = startingWealth;
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

    public int getStartingWealth() {
        return startingWealth;
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
}
