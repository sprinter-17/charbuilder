package characterbuilder.character.characterclass.druid;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Proficiency;
import static characterbuilder.character.ability.Proficiency.DRUIDIC;
import static characterbuilder.character.ability.Skill.ANIMAL_HANDLING;
import static characterbuilder.character.ability.Skill.ARCANA;
import static characterbuilder.character.ability.Skill.INSIGHT;
import static characterbuilder.character.ability.Skill.MEDICINE;
import static characterbuilder.character.ability.Skill.NATURE;
import static characterbuilder.character.ability.Skill.PERCEPTION;
import static characterbuilder.character.ability.Skill.RELIGION;
import static characterbuilder.character.ability.Skill.SURVIVAL;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeDelegate;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.DRUID_ABILITY;
import static characterbuilder.character.attribute.AttributeType.WISDOM;
import characterbuilder.character.characterclass.AbstractCharacterClass;
import characterbuilder.character.characterclass.CharacterClass;
import static characterbuilder.character.characterclass.druid.Druid.Ability.*;
import characterbuilder.character.choice.AbilityScoreOrFeatIncrease;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.ChoiceGenerator;
import static characterbuilder.character.choice.ChoiceGenerator.cantripChoice;
import characterbuilder.character.choice.EquipmentChoice;
import characterbuilder.character.equipment.Armour;
import static characterbuilder.character.equipment.Armour.LEATHER_ARMOUR;
import characterbuilder.character.equipment.EquipmentCategory;
import static characterbuilder.character.equipment.EquipmentCategory.DRUIDIC_FOCUS;
import static characterbuilder.character.equipment.EquipmentCategory.SIMPLE_RANGED;
import static characterbuilder.character.equipment.EquipmentPack.EXPLORER_PACK;
import static characterbuilder.character.equipment.Weapon.CLUB;
import static characterbuilder.character.equipment.Weapon.DAGGER;
import static characterbuilder.character.equipment.Weapon.DART;
import static characterbuilder.character.equipment.Weapon.JAVELIN;
import static characterbuilder.character.equipment.Weapon.MACE;
import static characterbuilder.character.equipment.Weapon.QUARTERSTAFF;
import static characterbuilder.character.equipment.Weapon.SCIMITAR;
import static characterbuilder.character.equipment.Weapon.SICKLE;
import static characterbuilder.character.equipment.Weapon.SLING;
import static characterbuilder.character.equipment.Weapon.SPEAR;
import java.util.stream.Stream;
import org.w3c.dom.Element;

public class Druid extends AbstractCharacterClass {

    public enum Ability implements Attribute {
        WILD_SHAPE(ability()
            .withDescription("As an action, assume the shape of a beast of up to "
                + "CR[max($level 2:1/4,4:1/2,8:1)].")),;

        private final AttributeDelegate delegate;

        private Ability(AttributeDelegate delegate) {
            this.delegate = delegate;
        }

        @Override
        public AttributeType getType() {
            return DRUID_ABILITY;
        }

        @Override
        public void generateInitialChoices(Character character) {
            delegate.generateChoices(character);
        }

        @Override
        public Stream<String> getDescription(Character character) {
            return delegate.getDescription(character);
        }
    }

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
        return Stream.of(AttributeType.INTELLIGENCE, AttributeType.WISDOM);
    }

    @Override
    public boolean hasSavingsThrow(AttributeType type) {
        return Stream.of(AttributeType.WISDOM, AttributeType.CONSTITUTION).anyMatch(type::equals);
    }

    @Override
    protected void makeGenerator(ChoiceGenerator gen) {
        gen.level(1).addSpellCasting("Druid", WISDOM, CharacterClass.DRUID, "[$wis_mod + $level]");
        gen.level(1).learnAllSpells("Druid");
        gen.level(1).addChoice(cantripChoice(2, WISDOM));
        gen.level(4, 10).addChoice(cantripChoice(1, WISDOM));
        gen.level(1).addSpellSlots("Druid", 1, 2);
        gen.level(2, 3).addSpellSlots("Druid", 1, 1);
        gen.level(3).addSpellSlots("Druid", 2, 2);
        gen.level(4).addSpellSlots("Druid", 2, 1);
        gen.level(5).addSpellSlots("Druid", 3, 4);
        gen.level(6).addSpellSlots("Druid", 3, 1);
        gen.level(7, 8, 9).addSpellSlots("Druid", 4, 1);
        gen.level(9, 10, 18).addSpellSlots("Druid", 5, 1);
        gen.level(11, 19).addSpellSlots("Druid", 6, 1);
        gen.level(13, 20).addSpellSlots("Druid", 7, 1);
        gen.level(15).addSpellSlots("Druid", 8, 1);
        gen.level(17).addSpellSlots("Druid", 9, 1);
        gen.level(1).addAttributes(Proficiency.LIGHT_ARMOUR, Proficiency.MEDIUM_ARMOUR,
            Proficiency.SHIELD, DRUIDIC);
        gen.level(1).addWeaponProficiencies(CLUB, DAGGER, DART, JAVELIN, MACE, QUARTERSTAFF,
            SCIMITAR, SICKLE, SLING, SPEAR);
        gen.level(1).addChoice(2, new AttributeChoice("Skill", ARCANA, ANIMAL_HANDLING,
            INSIGHT, MEDICINE, NATURE, PERCEPTION, RELIGION, SURVIVAL));
        gen.level(1).addChoice(new EquipmentChoice("Weapon").with(SCIMITAR)
            .with(EquipmentCategory.SIMPLE_MELEE).with(SIMPLE_RANGED));
        gen.level(1).addChoice(new EquipmentChoice("Weapon or Shield").with(Armour.SHIELD)
            .with(EquipmentCategory.SIMPLE_MELEE).with(SIMPLE_RANGED));
        gen.level(1).addEquipment(LEATHER_ARMOUR, EXPLORER_PACK);
        gen.level(1).addChoice(new EquipmentChoice("Focus").with(DRUIDIC_FOCUS));
        gen.level(2).addAttributes(WILD_SHAPE);
        gen.level(2).addChoice(new AttributeChoice("Druid Circle", DruidCircle.values()));
        gen.level(4, 8, 12, 16, 19).addChoice(2, new AbilityScoreOrFeatIncrease());
    }

    public static Ability loadAbility(Element element) {
        return Ability.valueOf(element.getTextContent());
    }
}