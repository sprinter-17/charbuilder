package characterbuilder.character.characterclass.fighter;

import characterbuilder.character.ability.FightingStyle;
import static characterbuilder.character.ability.Proficiency.ALL_ARMOUR;
import static characterbuilder.character.ability.Proficiency.ALL_WEAPONS;
import static characterbuilder.character.ability.Skill.ACROBATICS;
import static characterbuilder.character.ability.Skill.ANIMAL_HANDLING;
import static characterbuilder.character.ability.Skill.ATHLETICS;
import static characterbuilder.character.ability.Skill.HISTORY;
import static characterbuilder.character.ability.Skill.INSIGHT;
import static characterbuilder.character.ability.Skill.INTIMIDATION;
import static characterbuilder.character.ability.Skill.PERCEPTION;
import static characterbuilder.character.ability.Skill.SURVIVAL;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.characterclass.AbstractCharacterClass;
import static characterbuilder.character.characterclass.fighter.FighterAbility.ACTION_SURGE;
import static characterbuilder.character.characterclass.fighter.FighterAbility.EXTRA_ATTACK;
import static characterbuilder.character.characterclass.fighter.FighterAbility.INDOMITABLE;
import static characterbuilder.character.characterclass.fighter.FighterAbility.SECOND_WIND;
import characterbuilder.character.choice.ChoiceGenerator;
import static characterbuilder.character.choice.ChoiceGenerator.levels;
import static characterbuilder.character.equipment.AdventureGear.ARROW;
import static characterbuilder.character.equipment.AdventureGear.CROSSBOW_BOLT;
import characterbuilder.character.equipment.Armour;
import static characterbuilder.character.equipment.Armour.CHAIN_MAIL_ARMOUR;
import static characterbuilder.character.equipment.Armour.LEATHER_ARMOUR;
import characterbuilder.character.equipment.EquipmentCategory;
import static characterbuilder.character.equipment.EquipmentPack.DUNGEONEER_PACK;
import static characterbuilder.character.equipment.EquipmentPack.EXPLORER_PACK;
import characterbuilder.character.equipment.EquipmentSet;
import static characterbuilder.character.equipment.Weapon.HANDAXE;
import static characterbuilder.character.equipment.Weapon.LIGHT_CROSSBOW;
import static characterbuilder.character.equipment.Weapon.LONGBOW;
import java.util.stream.Stream;

public class Fighter extends AbstractCharacterClass {

    @Override
    public int getHitDie() {
        return 10;
    }

    @Override
    public AttributeType getClassAttribute() {
        return AttributeType.MARTIAL_ARCHETYPE;
    }

    @Override
    public Stream<AttributeType> getPrimaryAttributes() {
        return Stream.of(AttributeType.STRENGTH, AttributeType.CONSTITUTION);
    }

    @Override
    public boolean hasSavingsThrow(AttributeType type) {
        return super.hasSavingsThrow(type) || type == AttributeType.DEXTERITY;
    }

    @Override
    protected void makeGenerator(ChoiceGenerator gen) {
        gen.level(1)
            .addAttributes(ALL_ARMOUR, ALL_WEAPONS, SECOND_WIND)
            .addAttributeChoice(2, "Skill", ACROBATICS, ANIMAL_HANDLING, ATHLETICS, HISTORY,
                INSIGHT, INTIMIDATION, PERCEPTION, SURVIVAL)
            .addAttributeChoice("Fighting Style", FightingStyle.values());
        gen.level(1).addEquipmentChoice("Armour")
            .with(CHAIN_MAIL_ARMOUR).with(LEATHER_ARMOUR, LONGBOW, new EquipmentSet(ARROW, 20));
        gen.level(1).addEquipmentChoice("Primary Weapon").with(EquipmentCategory.MARTIAL_MELEE);
        gen.level(1).addEquipmentChoice("Secondary Weapon Or Shield")
            .with(Armour.SHIELD).with(EquipmentCategory.MARTIAL_MELEE);
        gen.level(1).addEquipmentChoice("Ranged Weapon")
            .with(LIGHT_CROSSBOW, new EquipmentSet(CROSSBOW_BOLT, 20))
            .with(new EquipmentSet(HANDAXE, 2));
        gen.level(1).addEquipmentChoice("Adventure Pack", DUNGEONEER_PACK, EXPLORER_PACK);
        gen.level(2).addAttributes(ACTION_SURGE);
        gen.level(3).addAttributeChoice("Martial Archetype", MartialArchetype.values());
        gen.level(5).addAttributes(EXTRA_ATTACK);
        gen.level(9).addAttributes(INDOMITABLE);
        gen.cond(levels(4, 6, 8, 12, 14, 16, 19)).addAbilityScoreOrFeatChoice();
    }
}
