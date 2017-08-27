package characterbuilder.character.characterclass.cleric;

import characterbuilder.character.ability.Proficiency;
import static characterbuilder.character.ability.Proficiency.ALL_SIMPLE_WEAPONS;
import static characterbuilder.character.ability.Skill.*;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.CHARISMA;
import static characterbuilder.character.attribute.AttributeType.CONSTITUTION;
import static characterbuilder.character.attribute.AttributeType.STRENGTH;
import static characterbuilder.character.attribute.AttributeType.WISDOM;
import characterbuilder.character.characterclass.AbstractCharacterClass;
import static characterbuilder.character.characterclass.CharacterClass.CLERIC;
import static characterbuilder.character.characterclass.cleric.ClericAbility.*;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.ChoiceGenerator;
import static characterbuilder.character.choice.ChoiceGenerator.cantripChoice;
import static characterbuilder.character.equipment.AdventureGear.CROSSBOW_BOLT;
import static characterbuilder.character.equipment.Armour.CHAIN_MAIL_ARMOUR;
import static characterbuilder.character.equipment.Armour.LEATHER_ARMOUR;
import static characterbuilder.character.equipment.Armour.SCALE_MAIL_ARMOUR;
import characterbuilder.character.equipment.EquipmentCategory;
import characterbuilder.character.equipment.EquipmentSet;
import static characterbuilder.character.equipment.Weapon.LIGHT_CROSSBOW;
import static characterbuilder.character.equipment.Weapon.MACE;
import static characterbuilder.character.equipment.Weapon.WARHAMMER;
import java.util.stream.Stream;

public class Cleric extends AbstractCharacterClass {

    private static final String CASTING_NAME = "Cleric";

    @Override
    public int getHitDie() {
        return 8;
    }

    @Override
    public AttributeType getClassAttribute() {
        return AttributeType.DIVINE_DOMAIN;
    }

    @Override
    public Stream<AttributeType> getPrimaryAttributes() {
        return Stream.of(WISDOM, CHARISMA);
    }

    @Override
    public boolean hasSavingsThrow(AttributeType type) {
        return Stream.of(WISDOM, CONSTITUTION, STRENGTH).anyMatch(type::equals);
    }

    @Override
    protected void makeGenerator(ChoiceGenerator gen) {
        addAbilities(gen);
        addEquipment(gen);
        addCantrips(gen);
        addSpellCasting(gen);
    }

    private void addAbilities(ChoiceGenerator gen) {
        gen.level(1).addAttributes(Proficiency.LIGHT_ARMOUR, Proficiency.MEDIUM_ARMOUR,
            Proficiency.SHIELD, ALL_SIMPLE_WEAPONS);
        gen.level(1).addChoice(new AttributeChoice("Divine Domain", DivineDomain.values()));
        gen.level(1).addChoice(new AttributeChoice("Skill",
            HISTORY, INSIGHT, MEDICINE, PERSUASION, RELIGION).withCount(2));
        gen.level(2).addAttributes(TURN_UNDEAD, CHANNEL_DIVINITY);
        gen.level(5).addAttributes(DESTROY_UNDEAD);
        gen.level(10).addAttributes(DIVINE_INTERVENTION);
        gen.level(4, 8, 12, 16, 19).addAbilityScoreOrFeatChoice();
    }

    private void addEquipment(ChoiceGenerator gen) {
        gen.level(1).addEquipmentChoice("Primary Weapon", MACE, WARHAMMER);
        gen.level(1).addEquipmentChoice("Secondary Weapon")
            .with(EquipmentCategory.SIMPLE_MELEE)
            .with(LIGHT_CROSSBOW, new EquipmentSet(CROSSBOW_BOLT, 20));
        gen.level(1)
            .addEquipmentChoice("Armour", SCALE_MAIL_ARMOUR, LEATHER_ARMOUR, CHAIN_MAIL_ARMOUR);
        gen.level(1).addEquipmentChoice(EquipmentCategory.HOLY_SYMBOL);
    }

    private void addCantrips(ChoiceGenerator gen) {
        gen.level(1).addChoice(cantripChoice(3, WISDOM));
        gen.level(5, 10).addChoice(cantripChoice(1, WISDOM));
    }

    private void addSpellCasting(ChoiceGenerator gen) {
        gen.level(1).addSpellCasting(CASTING_NAME, WISDOM, CLERIC, "[$wis_mod + $level]");
        gen.level(1).learnAllSpells(CASTING_NAME);
        gen.level(1).addSpellSlots(CASTING_NAME, 1, 2);
        gen.level(2, 3).addSpellSlots(CASTING_NAME, 1, 1);
        gen.level(3).addSpellSlots(CASTING_NAME, 2, 2);
        gen.level(4).addSpellSlots(CASTING_NAME, 2, 1);
        gen.level(5).addSpellSlots(CASTING_NAME, 3, 2);
        gen.level(6).addSpellSlots(CASTING_NAME, 3, 1);
        gen.level(7, 8, 9).addSpellSlots(CASTING_NAME, 4, 1);
        gen.level(9, 10, 18).addSpellSlots(CASTING_NAME, 5, 1);
        gen.level(11, 19).addSpellSlots(CASTING_NAME, 6, 1);
        gen.level(13, 20).addSpellSlots(CASTING_NAME, 7, 1);
        gen.level(15).addSpellSlots(CASTING_NAME, 8, 1);
        gen.level(17).addSpellSlots(CASTING_NAME, 9, 1);
    }
}
