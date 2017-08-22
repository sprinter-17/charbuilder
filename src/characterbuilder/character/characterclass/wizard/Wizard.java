package characterbuilder.character.characterclass.wizard;

import characterbuilder.character.ability.Ability;
import static characterbuilder.character.ability.Ability.ARCANE_RECOVERY;
import static characterbuilder.character.ability.Skill.ARCANA;
import static characterbuilder.character.ability.Skill.HISTORY;
import static characterbuilder.character.ability.Skill.INSIGHT;
import static characterbuilder.character.ability.Skill.INVESTIGATION;
import static characterbuilder.character.ability.Skill.MEDICINE;
import static characterbuilder.character.ability.Skill.RELIGION;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.INTELLIGENCE;
import characterbuilder.character.characterclass.AbstractCharacterClass;
import characterbuilder.character.characterclass.CharacterClass;
import characterbuilder.character.choice.AbilityScoreOrFeatIncrease;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.ChoiceGenerator;
import static characterbuilder.character.choice.ChoiceGenerator.cantripChoice;
import static characterbuilder.character.choice.ChoiceGenerator.levels;
import characterbuilder.character.choice.EquipmentChoice;
import static characterbuilder.character.equipment.AdventureGear.COMPONENT_POUCH;
import static characterbuilder.character.equipment.AdventureGear.CRYSTAL;
import static characterbuilder.character.equipment.AdventureGear.ORB;
import static characterbuilder.character.equipment.AdventureGear.ROD;
import static characterbuilder.character.equipment.AdventureGear.SPELLBOOK;
import static characterbuilder.character.equipment.AdventureGear.STAFF;
import static characterbuilder.character.equipment.AdventureGear.WAND;
import static characterbuilder.character.equipment.EquipmentPack.EXPLORER_PACK;
import static characterbuilder.character.equipment.EquipmentPack.SCHOLAR_PACK;
import static characterbuilder.character.equipment.Weapon.DAGGER;
import static characterbuilder.character.equipment.Weapon.DART;
import static characterbuilder.character.equipment.Weapon.LIGHT_CROSSBOW;
import static characterbuilder.character.equipment.Weapon.QUARTERSTAFF;
import static characterbuilder.character.equipment.Weapon.SLING;
import characterbuilder.character.spell.SignatureSpell;
import java.util.stream.Stream;

public class Wizard extends AbstractCharacterClass {

    @Override
    public int getHitDie() {
        return 6;
    }

    @Override
    public AttributeType getClassAttribute() {
        return AttributeType.ARCANE_TRADITION;
    }

    @Override
    public Stream<AttributeType> getPrimaryAttributes() {
        return Stream.of(AttributeType.INTELLIGENCE, AttributeType.WISDOM);
    }

    @Override
    public boolean hasSavingsThrow(AttributeType type) {
        return Stream
            .of(AttributeType.DEXTERITY, AttributeType.CONSTITUTION, AttributeType.INTELLIGENCE)
            .anyMatch(type::equals);
    }

    @Override
    protected void makeGenerator(ChoiceGenerator gen) {
        final String casting = "Wizard";
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
            .addSpellCasting(casting, INTELLIGENCE, CharacterClass.WIZARD, "[$int_mod + $level]")
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
    }

}