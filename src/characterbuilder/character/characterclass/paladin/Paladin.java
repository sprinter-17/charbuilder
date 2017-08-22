package characterbuilder.character.characterclass.paladin;

import static characterbuilder.character.ability.Ability.AURA_OF_COURAGE;
import static characterbuilder.character.ability.Ability.AURA_OF_PROTECTION;
import static characterbuilder.character.ability.Ability.CLEANSING_TOUCH;
import static characterbuilder.character.ability.Ability.DIVINE_HEALTH;
import static characterbuilder.character.ability.Ability.DIVINE_SENSE;
import static characterbuilder.character.ability.Ability.DIVINE_SMITE;
import static characterbuilder.character.ability.Ability.EXTRA_ATTACK;
import static characterbuilder.character.ability.Ability.IMPROVED_DIVINE_SMITE;
import static characterbuilder.character.ability.Ability.LAY_ON_HANDS;
import characterbuilder.character.ability.FightingStyle;
import static characterbuilder.character.ability.Proficiency.ALL_ARMOUR;
import static characterbuilder.character.ability.Proficiency.ALL_WEAPONS;
import static characterbuilder.character.ability.Skill.ATHLETICS;
import static characterbuilder.character.ability.Skill.INSIGHT;
import static characterbuilder.character.ability.Skill.INTIMIDATION;
import static characterbuilder.character.ability.Skill.MEDICINE;
import static characterbuilder.character.ability.Skill.PERSUASION;
import static characterbuilder.character.ability.Skill.RELIGION;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.CHARISMA;
import characterbuilder.character.characterclass.AbstractCharacterClass;
import characterbuilder.character.characterclass.CharacterClass;
import characterbuilder.character.choice.AbilityScoreOrFeatIncrease;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.character.choice.EquipmentChoice;
import characterbuilder.character.equipment.Armour;
import static characterbuilder.character.equipment.Armour.CHAIN_MAIL_ARMOUR;
import static characterbuilder.character.equipment.EquipmentCategory.HOLY_SYMBOL;
import static characterbuilder.character.equipment.EquipmentCategory.MARTIAL_MELEE;
import static characterbuilder.character.equipment.EquipmentCategory.MARTIAL_RANGED;
import static characterbuilder.character.equipment.EquipmentCategory.SIMPLE_MELEE;
import static characterbuilder.character.equipment.EquipmentPack.EXPLORER_PACK;
import static characterbuilder.character.equipment.EquipmentPack.PRIEST_PACK;
import static characterbuilder.character.equipment.Weapon.JAVELIN;
import java.util.stream.Stream;

public class Paladin extends AbstractCharacterClass {

    @Override
    public int getHitDie() {
        return 10;
    }

    @Override
    public AttributeType getClassAttribute() {
        return AttributeType.SACRED_OATH;
    }

    @Override
    public Stream<AttributeType> getPrimaryAttributes() {
        return Stream.of(AttributeType.WISDOM, AttributeType.CHARISMA);
    }

    @Override
    public boolean hasSavingsThrow(AttributeType type) {
        return Stream.of(AttributeType.STRENGTH, AttributeType.CHARISMA).anyMatch(type::equals);
    }

    @Override
    protected void makeGenerator(ChoiceGenerator gen) {
        final String casting = "Paladin";
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
        gen.level(2).addChoice(new AttributeChoice("Fighting Style", FightingStyle.values()));
        gen.level(2)
            .addSpellCasting(casting, CHARISMA, CharacterClass.PALADIN,
                "[max(1, $chr_mod + $level/2)]")
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
    }
}
