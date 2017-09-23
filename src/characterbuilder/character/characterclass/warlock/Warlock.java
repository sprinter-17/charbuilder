package characterbuilder.character.characterclass.warlock;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Proficiency;
import static characterbuilder.character.ability.Skill.*;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.CHARISMA;
import characterbuilder.character.characterclass.AbstractCharacterClass;
import characterbuilder.character.characterclass.CharacterClass;
import static characterbuilder.character.characterclass.warlock.MysticArcanum.chooseArcanum;
import static characterbuilder.character.characterclass.warlock.WarlockAbility.ELDRITCH_MASTER;
import static characterbuilder.character.characterclass.warlock.WarlockAbility.PACT_OF_THE_CHAIN;
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
import characterbuilder.character.equipment.Weapon;
import static characterbuilder.character.equipment.Weapon.DAGGER;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Warlock extends AbstractCharacterClass {

    private static final String CASTING_NAME = "Warlock";

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
    public Predicate<Character> getMulticlassPrerequisites() {
        return ch -> ch.getScore(CHARISMA).getValue() >= 13;
    }

    @Override
    public boolean hasSavingsThrow(AttributeType type) {
        return Stream.of(AttributeType.CONSTITUTION, AttributeType.CHARISMA).anyMatch(type::equals);
    }

    @Override
    protected void makeGenerator(ChoiceGenerator gen) {
        addAbilities(gen);
        addEquipment(gen.initialClass());
        addCantrips(gen);
        addSpellCasting(gen);
    }

    private void addAbilities(ChoiceGenerator gen) {
        gen.initialClass().addChoice(2, new AttributeChoice("Skill", ARCANA, DECEPTION,
            HISTORY, INTIMIDATION, INVESTIGATION, NATURE, RELIGION));
        gen.level(1).addAttributes(Proficiency.LIGHT_ARMOUR, Proficiency.ALL_SIMPLE_WEAPONS);
        gen.level(1).addAttributeChoice("Otherworldy Patron", OtherwordlyPatron.values());
        gen.level(2).addChoice(EldritchInvocation.getChoice(2));
        gen.cond(ch -> ch.getLevel() > 2).addChoice(EldritchInvocation.getReplacement());
        gen.level(5, 7, 9, 12, 15, 18).addChoice(EldritchInvocation.getChoice(1));
        gen.level(3).addChoice(new AttributeChoice("Pact Boon",
            PACT_OF_THE_CHAIN, WarlockAbility.PACT_OF_THE_BLADE, WarlockAbility.PACT_OF_THE_TOME));
        gen.level(4, 8, 12, 16, 19).addAbilityScoreOrFeatChoice();
        gen.level(11).addChoice(chooseArcanum(CharacterClass.WARLOCK, 6));
        gen.level(13).addChoice(chooseArcanum(CharacterClass.WARLOCK, 7));
        gen.level(15).addChoice(chooseArcanum(CharacterClass.WARLOCK, 8));
        gen.level(17).addChoice(chooseArcanum(CharacterClass.WARLOCK, 9));
        gen.level(20).addAttributes(ELDRITCH_MASTER);
    }

    private void addEquipment(ChoiceGenerator gen) {
        gen.addChoice(new EquipmentChoice("Primary Weapon")
            .with(Weapon.LIGHT_CROSSBOW, ARROW.makeSet(20))
            .with(SIMPLE_MELEE).with(SIMPLE_RANGED));
        gen.addChoice(new EquipmentChoice("Secondary Weapon")
            .with(SIMPLE_MELEE).with(SIMPLE_RANGED));
        gen.addChoice(new EquipmentChoice("Magical Equipment")
            .with(COMPONENT_POUCH).with(ARCANE_FOCUS));
        gen.addChoice(new EquipmentChoice("Adventurer's Pack",
            EquipmentPack.SCHOLAR_PACK, EquipmentPack.DUNGEONEER_PACK));
        gen.addEquipment(LEATHER_ARMOUR, DAGGER.makeSet(2));
    }

    private void addCantrips(ChoiceGenerator gen) {
        gen.level(1).addChoice(cantripChoice(2, CHARISMA));
        gen.level(4, 10).addChoice(cantripChoice(1, CHARISMA));
    }

    private void addSpellCasting(ChoiceGenerator gen) {
        gen.level(1)
            .addSpellCasting(CASTING_NAME, CHARISMA, CharacterClass.WARLOCK, "All")
            .addKnownSpells(CASTING_NAME, 2);
        gen.cond(ch -> ch.getLevel() > 1).replaceSpell(CASTING_NAME);
        gen.level(2, 3, 4, 5, 6, 7, 8, 9, 11, 13, 15, 17, 19).addKnownSpells(CASTING_NAME, 1);
    }
}
