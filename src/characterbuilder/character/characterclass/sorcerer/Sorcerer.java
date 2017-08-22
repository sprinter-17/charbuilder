package characterbuilder.character.characterclass.sorcerer;

import static characterbuilder.character.ability.Ability.FONT_OF_MAGIC;
import static characterbuilder.character.ability.Skill.ARCANA;
import static characterbuilder.character.ability.Skill.DECEPTION;
import static characterbuilder.character.ability.Skill.INSIGHT;
import static characterbuilder.character.ability.Skill.INTIMIDATION;
import static characterbuilder.character.ability.Skill.PERSUASION;
import static characterbuilder.character.ability.Skill.RELIGION;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.CHARISMA;
import characterbuilder.character.characterclass.AbstractCharacterClass;
import characterbuilder.character.characterclass.CharacterClass;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.character.choice.EquipmentChoice;
import static characterbuilder.character.equipment.AdventureGear.COMPONENT_POUCH;
import static characterbuilder.character.equipment.AdventureGear.CROSSBOW_BOLT;
import static characterbuilder.character.equipment.EquipmentCategory.ARCANE_FOCUS;
import static characterbuilder.character.equipment.EquipmentCategory.SIMPLE_MELEE;
import static characterbuilder.character.equipment.EquipmentCategory.SIMPLE_RANGED;
import static characterbuilder.character.equipment.EquipmentPack.DUNGEONEER_PACK;
import static characterbuilder.character.equipment.EquipmentPack.EXPLORER_PACK;
import characterbuilder.character.equipment.EquipmentSet;
import static characterbuilder.character.equipment.Weapon.DAGGER;
import static characterbuilder.character.equipment.Weapon.DART;
import static characterbuilder.character.equipment.Weapon.LIGHT_CROSSBOW;
import static characterbuilder.character.equipment.Weapon.QUARTERSTAFF;
import static characterbuilder.character.equipment.Weapon.SLING;
import java.util.stream.Stream;

public class Sorcerer extends AbstractCharacterClass {

    @Override
    public int getHitDie() {
        return 6;
    }

    @Override
    public AttributeType getClassAttribute() {
        return AttributeType.SORCEROUS_ORIGIN;
    }

    @Override
    public Stream<AttributeType> getPrimaryAttributes() {
        return Stream.of(AttributeType.CONSTITUTION, AttributeType.CHARISMA);
    }

    @Override
    protected void makeGenerator(ChoiceGenerator gen) {
        String casting = "Sorcerer";
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
            .addSpellCasting(casting, CHARISMA, CharacterClass.SORCERER, "All")
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
    }
}
