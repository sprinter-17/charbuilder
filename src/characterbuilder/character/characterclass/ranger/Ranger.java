package characterbuilder.character.characterclass.ranger;

import static characterbuilder.character.ability.Ability.EXTRA_ATTACK;
import characterbuilder.character.ability.FightingStyle;
import characterbuilder.character.ability.Proficiency;
import static characterbuilder.character.ability.Proficiency.ALL_WEAPONS;
import static characterbuilder.character.ability.Skill.*;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.WISDOM;
import characterbuilder.character.characterclass.AbstractCharacterClass;
import characterbuilder.character.characterclass.CharacterClass;
import static characterbuilder.character.characterclass.ranger.RangerAbility.*;
import characterbuilder.character.choice.AbilityScoreOrFeatIncrease;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.character.choice.EquipmentChoice;
import static characterbuilder.character.equipment.AdventureGear.ARROW;
import static characterbuilder.character.equipment.Armour.LEATHER_ARMOUR;
import static characterbuilder.character.equipment.Armour.SCALE_MAIL_ARMOUR;
import characterbuilder.character.equipment.EquipmentCategory;
import static characterbuilder.character.equipment.EquipmentPack.DUNGEONEER_PACK;
import static characterbuilder.character.equipment.EquipmentPack.EXPLORER_PACK;
import static characterbuilder.character.equipment.Weapon.LONGBOW;
import static characterbuilder.character.equipment.Weapon.SHORTSWORD;
import java.util.stream.Stream;

public class Ranger extends AbstractCharacterClass {

    private static final String CASTING_NAME = "Ranger";

    @Override
    public int getHitDie() {
        return 10;
    }

    @Override
    public AttributeType getClassAttribute() {
        return AttributeType.RANGER_ARCHETYPE;
    }

    @Override
    public Stream<AttributeType> getPrimaryAttributes() {
        return Stream.of(AttributeType.STRENGTH, AttributeType.DEXTERITY);
    }

    @Override
    public boolean hasSavingsThrow(AttributeType type) {
        return Stream.of(AttributeType.DEXTERITY, AttributeType.WISDOM).anyMatch(type::equals);
    }

    @Override
    protected void makeGenerator(ChoiceGenerator gen) {
        addAbilities(gen);
        addEquipment(gen);
        addSpellCasting(gen);
    }

    private void addAbilities(ChoiceGenerator gen) {
        gen.level(1).addAttributes(Proficiency.LIGHT_ARMOUR, Proficiency.MEDIUM_ARMOUR,
            Proficiency.SHIELD);
        gen.level(1).addAttributes(ALL_WEAPONS);
        gen.level(1).addAttributeChoice(3, "Skill", ANIMAL_HANDLING, ATHLETICS,
            INSIGHT, INVESTIGATION, NATURE, PERCEPTION, STEALTH, SURVIVAL);
        gen.level(1, 6, 14).addChoice(FavouredEnemy.choice());
        gen.level(1, 6, 10).addChoice(FavouredTerrain.choose());
        gen.level(2).addAttributeChoice("Fighting Style", FightingStyle.values());
        gen.level(3).addAttributeChoice("Ranger Archetype", RangerArchetype.values());
        gen.level(3).addAttributes(PRIMEVAL_AWARENESS);
        gen.level(4, 8, 12, 16, 19).addChoice(2, new AbilityScoreOrFeatIncrease());
        gen.level(5).addAttributes(EXTRA_ATTACK);
        gen.level(8).addAttributes(LANDS_STRIDE);
        gen.level(10).addAttributes(HIDE_IN_PLAIN_SIGHT);
        gen.level(14).addAttributes(VANISH);
        gen.level(18).addAttributes(FERAL_SENSES);
        gen.level(20).addAttributes(FOE_SLAYER);
    }

    private void addEquipment(ChoiceGenerator gen) {
        gen.level(1).addEquipmentChoice("Armour", SCALE_MAIL_ARMOUR, LEATHER_ARMOUR);
        gen.level(1).addChoice(2, new EquipmentChoice("Weapon")
            .with(SHORTSWORD).with(EquipmentCategory.SIMPLE_MELEE));
        gen.level(1).addEquipmentChoice("Adventure Pack", DUNGEONEER_PACK, EXPLORER_PACK);
        gen.level(1).addEquipment(LONGBOW).addEquipment(ARROW, 20);
    }

    private void addSpellCasting(ChoiceGenerator gen) {
        gen.level(2)
            .addSpellCasting(CASTING_NAME, WISDOM, CharacterClass.RANGER, "All")
            .addSpellSlots(CASTING_NAME, 1, 2)
            .addKnownSpells(CASTING_NAME, 2);
        gen.level(3, 5).addSpellSlots(CASTING_NAME, 1, 2);
        gen.level(5).addSpellSlots(CASTING_NAME, 2, 2);
        gen.level(7).addSpellSlots(CASTING_NAME, 2, 1);
        gen.level(9).addSpellSlots(CASTING_NAME, 3, 2);
        gen.level(11).addSpellSlots(CASTING_NAME, 3, 1);
        gen.level(13, 15, 17).addSpellSlots(CASTING_NAME, 4, 1);
        gen.level(17, 19).addSpellSlots(CASTING_NAME, 5, 1);
        gen.level(3, 5, 7, 9, 11, 13, 15, 17, 19).addKnownSpells(CASTING_NAME, 1);
        gen.cond(ch -> ch.getLevel() > 2).replaceSpell(CASTING_NAME);
    }
}
