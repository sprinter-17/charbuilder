package characterbuilder.character.characterclass.paladin;

import characterbuilder.character.Character;
import static characterbuilder.character.ability.Ability.EXTRA_ATTACK;
import characterbuilder.character.ability.FightingStyle;
import characterbuilder.character.ability.Proficiency;
import static characterbuilder.character.ability.Proficiency.*;
import static characterbuilder.character.ability.Skill.*;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.*;
import characterbuilder.character.characterclass.AbstractCharacterClass;
import characterbuilder.character.characterclass.CharacterClass;
import static characterbuilder.character.characterclass.paladin.PaladinAbility.*;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.character.equipment.Armour;
import static characterbuilder.character.equipment.Armour.CHAIN_MAIL_ARMOUR;
import static characterbuilder.character.equipment.EquipmentCategory.HOLY_SYMBOL;
import static characterbuilder.character.equipment.EquipmentCategory.MARTIAL_MELEE;
import static characterbuilder.character.equipment.EquipmentCategory.MARTIAL_RANGED;
import static characterbuilder.character.equipment.EquipmentCategory.SIMPLE_MELEE;
import static characterbuilder.character.equipment.EquipmentPack.EXPLORER_PACK;
import static characterbuilder.character.equipment.EquipmentPack.PRIEST_PACK;
import static characterbuilder.character.equipment.Weapon.JAVELIN;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Paladin extends AbstractCharacterClass {

    private static final String CASTING_NAME = "Paladin";

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
    public Predicate<Character> getMulticlassPrerequisites() {
        return ch -> ch.getScore(STRENGTH).getValue() >= 13
            && ch.getScore(CHARISMA).getValue() >= 13;
    }

    @Override
    public boolean hasSavingsThrow(AttributeType type) {
        return Stream.of(AttributeType.STRENGTH, AttributeType.CHARISMA).anyMatch(type::equals);
    }

    @Override
    protected void makeGenerator(ChoiceGenerator gen) {
        addAbilities(gen);
        addEquipment(gen.initialClass());
        addSpellCasting(gen);
    }

    private void addAbilities(ChoiceGenerator gen) {
        gen.initialClass().addAttributeChoice(2, "Skill", ATHLETICS, INSIGHT, INTIMIDATION,
            MEDICINE, PERSUASION, RELIGION);
        gen.level(1).addAttributes(LIGHT_ARMOUR, MEDIUM_ARMOUR, Proficiency.SHIELD, ALL_WEAPONS);
        gen.level(1).addAttributes(DIVINE_SENSE, LAY_ON_HANDS);
        gen.level(2).addAttributeChoice("Fighting Style", FightingStyle.values());
        gen.level(2).addAttributes(DIVINE_SMITE);
        gen.level(3).addAttributes(DIVINE_HEALTH);
        gen.level(3).addAttributeChoice("Sacred Oath", SacredOath.values());
        gen.level(5).addAttributes(EXTRA_ATTACK);
        gen.level(6).addAttributes(AURA_OF_PROTECTION);
        gen.level(10).addAttributes(AURA_OF_COURAGE);
        gen.level(11).addAttributes(IMPROVED_DIVINE_SMITE);
        gen.level(14).addAttributes(CLEANSING_TOUCH);
        gen.level(4, 8, 12, 16, 19).addAbilityScoreOrFeatChoice();
    }

    private void addEquipment(ChoiceGenerator gen) {
        gen.addEquipmentChoice("Weapon").with(MARTIAL_MELEE).with(MARTIAL_RANGED);
        gen.addEquipmentChoice("Weapon or Shield", Armour.SHIELD)
            .with(MARTIAL_MELEE).with(MARTIAL_RANGED);
        gen.addEquipmentChoice("Secondary Weapon").with(JAVELIN.makeSet(5))
            .with(SIMPLE_MELEE);
        gen.addEquipmentChoice("Adventure Pack", PRIEST_PACK, EXPLORER_PACK);
        gen.addEquipment(CHAIN_MAIL_ARMOUR);
        gen.addEquipmentChoice(HOLY_SYMBOL);
    }

    private void addSpellCasting(ChoiceGenerator gen) {
        gen.level(2).addSpellCasting(CASTING_NAME, CHARISMA,
            CharacterClass.PALADIN, "[max(1, $chr_mod + $level/2)]")
            .learnAllSpells(CASTING_NAME);
    }
}
