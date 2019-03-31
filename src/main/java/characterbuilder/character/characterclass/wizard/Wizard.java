package characterbuilder.character.characterclass.wizard;

import characterbuilder.character.Character;
import static characterbuilder.character.ability.Skill.ARCANA;
import static characterbuilder.character.ability.Skill.HISTORY;
import static characterbuilder.character.ability.Skill.INSIGHT;
import static characterbuilder.character.ability.Skill.INVESTIGATION;
import static characterbuilder.character.ability.Skill.MEDICINE;
import static characterbuilder.character.ability.Skill.RELIGION;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.INTELLIGENCE;
import characterbuilder.character.characterclass.AbstractCharacterClass;
import static characterbuilder.character.characterclass.CharacterClass.WIZARD;
import static characterbuilder.character.characterclass.wizard.WizardAbility.*;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.ChoiceGenerator;
import static characterbuilder.character.choice.ChoiceGenerator.cantripChoice;
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
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Wizard extends AbstractCharacterClass {

    private static final String CASTING_NAME = "Wizard";

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
    public Predicate<Character> getMulticlassPrerequisites() {
        return ch -> ch.getScore(INTELLIGENCE).getValue() >= 13;
    }

    @Override
    public boolean hasSavingsThrow(AttributeType type) {
        return Stream
            .of(AttributeType.DEXTERITY, AttributeType.CONSTITUTION, AttributeType.INTELLIGENCE)
            .anyMatch(type::equals);
    }

    @Override
    protected void makeGenerator(ChoiceGenerator gen) {
        addAbilities(gen);
        addEquipment(gen.initialClass());
        addCantrips(gen);
        addSpellCasting(gen);
    }

    private void addAbilities(ChoiceGenerator gen) {
        gen.initialClass()
            .addWeaponProficiencies(DAGGER, DART, SLING, QUARTERSTAFF, LIGHT_CROSSBOW)
            .addAttributeChoice(2, "Skill", ARCANA, HISTORY, INSIGHT, INVESTIGATION,
                MEDICINE, RELIGION);
        gen.level(1).addAttributes(ARCANE_RECOVERY);
        gen.level(2).addChoice(new AttributeChoice("Arcane Tradition", MagicSchool.values()));
        gen.level(4, 8, 12, 16, 19).addAbilityScoreOrFeatChoice();
        gen.level(18).addAttributes(SPELL_MASTERY);
        gen.level(20).addAction("Signature Spell", ch -> ch.addAttribute(new SignatureSpell()));
    }

    private void addEquipment(ChoiceGenerator gen) {
        gen.addEquipmentChoice("Weapon", QUARTERSTAFF, DAGGER);
        gen.addEquipmentChoice("Wizard Gear", COMPONENT_POUCH, CRYSTAL, ORB, ROD, STAFF, WAND);
        gen.addEquipmentChoice("Adventure Pack", SCHOLAR_PACK, EXPLORER_PACK);
        gen.addEquipment(SPELLBOOK);
    }

    private void addCantrips(ChoiceGenerator gen) {
        gen.level(1).addChoice(cantripChoice(3, INTELLIGENCE));
        gen.level(4, 10).addChoice(cantripChoice(1, INTELLIGENCE));
    }

    private void addSpellCasting(ChoiceGenerator gen) {
        gen.level(1).addSpellCasting(CASTING_NAME, INTELLIGENCE, WIZARD, "[$int_mod + $level]");
        gen.level(1).addKnownSpells(CASTING_NAME, 6);
        gen.cond(ch -> ch.getLevel() > 1).addKnownSpells(CASTING_NAME, 2);
    }

}
