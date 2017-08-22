package characterbuilder.character.characterclass.warlock;

import characterbuilder.character.ability.Ability;
import static characterbuilder.character.ability.Ability.ELDRITCH_MASTER;
import characterbuilder.character.ability.Proficiency;
import characterbuilder.character.ability.Skill;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.CHARISMA;
import characterbuilder.character.characterclass.AbstractCharacterClass;
import characterbuilder.character.characterclass.CharacterClass;
import static characterbuilder.character.characterclass.warlock.MysticArcanum.chooseArcanum;
import characterbuilder.character.choice.AbilityScoreOrFeatIncrease;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.ChoiceGenerator;
import static characterbuilder.character.choice.ChoiceGenerator.cantripChoice;
import characterbuilder.character.choice.EquipmentChoice;
import static characterbuilder.character.equipment.AdventureGear.ARROW;
import static characterbuilder.character.equipment.AdventureGear.COMPONENT_POUCH;
import static characterbuilder.character.equipment.Armour.LEATHER_ARMOUR;
import static characterbuilder.character.equipment.EquipmentCategory.ARCANE_FOCUS;
import static characterbuilder.character.equipment.EquipmentCategory.SIMPLE_MELEE;
import static characterbuilder.character.equipment.EquipmentCategory.SIMPLE_RANGED;
import characterbuilder.character.equipment.EquipmentPack;
import characterbuilder.character.equipment.EquipmentSet;
import characterbuilder.character.equipment.Weapon;
import static characterbuilder.character.equipment.Weapon.DAGGER;
import java.util.stream.Stream;

public class Warlock extends AbstractCharacterClass {

    @Override
    public int getHitDie() {
        return 8;
    }

    @Override
    public AttributeType getClassAttribute() {
        return AttributeType.OTHERWORLDLY_PATRON;
    }

    @Override
    public Stream<AttributeType> getPrimaryAttributes() {
        return Stream.of(AttributeType.WISDOM, AttributeType.CHARISMA);
    }

    @Override
    public boolean hasSavingsThrow(AttributeType type) {
        return Stream.of(AttributeType.CONSTITUTION, AttributeType.CHARISMA).anyMatch(type::equals);
    }

    @Override
    protected void makeGenerator(ChoiceGenerator gen) {
        final String casting = "Warlock";
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

        gen.level(11).addChoice(chooseArcanum(CharacterClass.WARLOCK, 6));
        gen.level(13).addChoice(chooseArcanum(CharacterClass.WARLOCK, 7));
        gen.level(15).addChoice(chooseArcanum(CharacterClass.WARLOCK, 8));
        gen.level(17).addChoice(chooseArcanum(CharacterClass.WARLOCK, 9));

        gen.level(20).addAttributes(ELDRITCH_MASTER);

        gen.level(1).addChoice(cantripChoice(2, CHARISMA));
        gen.level(4, 10).addChoice(cantripChoice(1, CHARISMA));
        gen.level(1).addSpellCasting(casting, CHARISMA, CharacterClass.WARLOCK, "All")
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
    }
}
