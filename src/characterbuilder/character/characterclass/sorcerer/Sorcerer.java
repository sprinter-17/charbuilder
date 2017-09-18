package characterbuilder.character.characterclass.sorcerer;

import characterbuilder.character.Character;
import static characterbuilder.character.ability.Skill.*;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.CHARISMA;
import characterbuilder.character.characterclass.AbstractCharacterClass;
import characterbuilder.character.characterclass.CharacterClass;
import static characterbuilder.character.characterclass.sorcerer.SorcererAbility.FONT_OF_MAGIC;
import static characterbuilder.character.characterclass.sorcerer.SorcererAbility.SORCEROUS_RESTORATIONS;
import characterbuilder.character.choice.ChoiceGenerator;
import static characterbuilder.character.equipment.AdventureGear.COMPONENT_POUCH;
import static characterbuilder.character.equipment.AdventureGear.CROSSBOW_BOLT;
import static characterbuilder.character.equipment.EquipmentCategory.ARCANE_FOCUS;
import static characterbuilder.character.equipment.EquipmentCategory.SIMPLE_MELEE;
import static characterbuilder.character.equipment.EquipmentCategory.SIMPLE_RANGED;
import static characterbuilder.character.equipment.EquipmentPack.DUNGEONEER_PACK;
import static characterbuilder.character.equipment.EquipmentPack.EXPLORER_PACK;
import static characterbuilder.character.equipment.Weapon.DAGGER;
import static characterbuilder.character.equipment.Weapon.DART;
import static characterbuilder.character.equipment.Weapon.LIGHT_CROSSBOW;
import static characterbuilder.character.equipment.Weapon.QUARTERSTAFF;
import static characterbuilder.character.equipment.Weapon.SLING;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Sorcerer extends AbstractCharacterClass {

    private static final String CASTING_NAME = "Sorcerer";

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
    public Predicate<Character> getMulticlassPrerequisites() {
        return ch -> ch.getScore(CHARISMA).getValue() >= 13;
    }

    @Override
    protected void makeGenerator(ChoiceGenerator gen) {
        addAbilities(gen);
        addEquipment(gen);
        addSpellCasting(gen);
    }

    private void addAbilities(ChoiceGenerator gen) {
        gen.level(1).addAttributeChoice("Sorcerous Origin", SorcerousOrigin.values());
        gen.level(1).addWeaponProficiencies(DAGGER, DART, SLING, QUARTERSTAFF, LIGHT_CROSSBOW);
        gen.level(1).addAttributeChoice("Skill", ARCANA, DECEPTION, INSIGHT, INTIMIDATION,
            PERSUASION, RELIGION);
        gen.level(2).addAttributes(FONT_OF_MAGIC);
        gen.level(3).addAttributeChoice(2, "Metamagic", MetaMagic.values());
        gen.level(10, 17).addAttributeChoice("Metamagic", MetaMagic.values());
        gen.level(4, 8, 12, 16, 19).addAbilityScoreOrFeatChoice();
        gen.level(20).addAttributes(SORCEROUS_RESTORATIONS);
    }

    private void addEquipment(ChoiceGenerator gen) {
        gen.level(1).addEquipmentChoice("Weapon")
            .with(LIGHT_CROSSBOW, CROSSBOW_BOLT.makeSet(20))
            .with(SIMPLE_MELEE).with(SIMPLE_RANGED);
        gen.level(1).addEquipmentChoice("Spell Equipment").with(COMPONENT_POUCH).with(ARCANE_FOCUS);
        gen.level(1).addEquipmentChoice("Adventure Pack", DUNGEONEER_PACK, EXPLORER_PACK);
    }

    private void addSpellCasting(ChoiceGenerator gen) {
        gen.level(1).addSpellCasting(CASTING_NAME, CHARISMA, CharacterClass.SORCERER, "All");
        addSpellSlots(gen);
        addKnownSpells(gen);
        gen.cond(ch -> ch.getLevel() > 1).replaceSpell(CASTING_NAME);
    }

    private void addSpellSlots(ChoiceGenerator gen) {
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

    private void addKnownSpells(ChoiceGenerator gen) {
        gen.level(1).addKnownSpells(CASTING_NAME, 2);
        gen.level(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 15, 17).addKnownSpells(CASTING_NAME, 1);
    }
}
