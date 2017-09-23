package characterbuilder.character.characterclass.druid;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Feat;
import characterbuilder.character.ability.Language;
import characterbuilder.character.ability.Proficiency;
import static characterbuilder.character.ability.Skill.*;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.WISDOM;
import characterbuilder.character.characterclass.AbstractCharacterClass;
import characterbuilder.character.characterclass.CharacterClass;
import static characterbuilder.character.characterclass.druid.DruidAbility.*;
import characterbuilder.character.choice.ChoiceGenerator;
import static characterbuilder.character.choice.ChoiceGenerator.cantripChoice;
import characterbuilder.character.equipment.Armour;
import static characterbuilder.character.equipment.Armour.LEATHER_ARMOUR;
import characterbuilder.character.equipment.EquipmentCategory;
import static characterbuilder.character.equipment.EquipmentCategory.DRUIDIC_FOCUS;
import static characterbuilder.character.equipment.EquipmentCategory.SIMPLE_RANGED;
import static characterbuilder.character.equipment.EquipmentPack.EXPLORER_PACK;
import static characterbuilder.character.equipment.Weapon.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Druid extends AbstractCharacterClass {

    @Override
    public int getHitDie() {
        return 8;
    }

    @Override
    public AttributeType getClassAttribute() {
        return AttributeType.DRUID_CIRCLE;
    }

    @Override
    public Stream<AttributeType> getPrimaryAttributes() {
        return Stream.of(AttributeType.WISDOM, AttributeType.CONSTITUTION);
    }

    @Override
    public Predicate<Character> getMulticlassPrerequisites() {
        return ch -> ch.getScore(WISDOM).getValue() >= 13;
    }

    @Override
    public boolean hasSavingsThrow(AttributeType type) {
        return Stream.of(AttributeType.INTELLIGENCE, AttributeType.WISDOM).anyMatch(type::equals);
    }

    @Override
    protected void makeGenerator(ChoiceGenerator gen) {
        addAbilities(gen);
        addEquipment(gen.initialClass());
        addCantrips(gen);
        addSpellCasting(gen);
    }

    private void addAbilities(ChoiceGenerator gen) {
        gen.initialClass().addWeaponProficiencies(CLUB, DAGGER, DART, JAVELIN, MACE, QUARTERSTAFF,
            SCIMITAR, SICKLE, SLING, SPEAR);
        gen.initialClass().addAttributeChoice(2, "Skill", ARCANA, ANIMAL_HANDLING,
            INSIGHT, MEDICINE, NATURE, PERCEPTION, RELIGION, SURVIVAL);
        gen.level(1).addAttributes(Proficiency.LIGHT_ARMOUR, Proficiency.MEDIUM_ARMOUR,
            Proficiency.SHIELD, Language.DRUIDIC);
        gen.level(1).addAttributes(Feat.RITUAL_CASTER);
        gen.level(2).addAttributes(WILD_SHAPE);
        gen.level(2).addAttributeChoice("Druid Circle", DruidCircle.values());
        gen.level(4, 8, 12, 16, 19).addAbilityScoreOrFeatChoice();
        gen.level(18).addAttributes(TIMELESS_BODY, BEAST_SPELLS);
    }

    private void addEquipment(ChoiceGenerator gen) {
        gen.addEquipmentChoice("Weapon").with(SCIMITAR)
            .with(EquipmentCategory.SIMPLE_MELEE).with(SIMPLE_RANGED);
        gen.addEquipmentChoice("Weapon or Shield").with(Armour.SHIELD)
            .with(EquipmentCategory.SIMPLE_MELEE).with(SIMPLE_RANGED);
        gen.addEquipment(LEATHER_ARMOUR, EXPLORER_PACK);
        gen.addEquipmentChoice("Focus").with(DRUIDIC_FOCUS);
    }

    private void addSpellCasting(ChoiceGenerator gen) {
        gen.level(1).addSpellCasting("Druid", WISDOM, CharacterClass.DRUID, "[$wis_mod + $level]");
        gen.level(1).learnAllSpells("Druid");
    }

    private void addCantrips(ChoiceGenerator gen) {
        gen.level(1).addChoice(cantripChoice(2, WISDOM));
        gen.level(4, 10).addChoice(cantripChoice(1, WISDOM));
    }
}
