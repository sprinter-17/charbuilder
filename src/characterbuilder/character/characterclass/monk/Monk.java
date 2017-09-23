package characterbuilder.character.characterclass.monk;

import characterbuilder.character.Character;
import static characterbuilder.character.ability.Ability.EVASION;
import static characterbuilder.character.ability.Ability.EXTRA_ATTACK;
import characterbuilder.character.ability.Proficiency;
import static characterbuilder.character.ability.Proficiency.ALL_SIMPLE_WEAPONS;
import static characterbuilder.character.ability.Skill.*;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.*;
import characterbuilder.character.characterclass.AbstractCharacterClass;
import static characterbuilder.character.characterclass.monk.MonkAbility.*;
import characterbuilder.character.choice.AbilityScoreOrFeatIncrease;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.character.equipment.EquipmentCategory;
import static characterbuilder.character.equipment.EquipmentPack.DUNGEONEER_PACK;
import static characterbuilder.character.equipment.EquipmentPack.EXPLORER_PACK;
import characterbuilder.character.equipment.MusicalInstrument;
import static characterbuilder.character.equipment.Weapon.DART;
import static characterbuilder.character.equipment.Weapon.SHORTSWORD;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Monk extends AbstractCharacterClass {

    @Override
    public int getHitDie() {
        return 8;
    }

    @Override
    public AttributeType getClassAttribute() {
        return AttributeType.MONASTIC_TRADITION;
    }

    @Override
    public Stream<AttributeType> getPrimaryAttributes() {
        return Stream.of(AttributeType.STRENGTH, AttributeType.DEXTERITY);
    }

    @Override
    public Predicate<Character> getMulticlassPrerequisites() {
        return ch -> ch.getScore(DEXTERITY).getValue() >= 13
            && ch.getScore(WISDOM).getValue() >= 13;
    }

    @Override
    public boolean hasSavingsThrow(AttributeType type) {
        return Stream.of(AttributeType.DEXTERITY, AttributeType.WISDOM).anyMatch(type::equals);
    }

    @Override
    protected void makeGenerator(ChoiceGenerator gen) {
        addAttributes(gen);
        addEquipment(gen.initialClass());
    }

    private void addAttributes(ChoiceGenerator gen) {
        gen.initialClass()
            .addAttributeChoice(1, "Tools", Stream.concat(
                MusicalInstrument.getAllProficiencies(),
                Proficiency.allOfType(AttributeType.TOOLS)))
            .addAttributeChoice(2, "Skill", ACROBATICS, ATHLETICS, HISTORY,
                INSIGHT, RELIGION, STEALTH);
        gen.level(1)
            .addWeaponProficiencies(SHORTSWORD)
            .addAttributes(ALL_SIMPLE_WEAPONS)
            .addAttributes(UNARMORED_DEFENCE, MARTIAL_ARTS);
        gen.level(2).addAttributes(KI, FLURRY_OF_BLOWS, PATIENT_DEFENCE, STEP_OF_THE_WIND,
            UNARMOURED_MOVEMENT);
        gen.level(3).addAttributeChoice("Monastic Tradition", MonasticTradition.values());
        gen.level(3).addAttributes(DEFLECT_MISSILES);
        gen.level(4, 8, 12, 16, 19).addChoice(2, new AbilityScoreOrFeatIncrease());
        gen.level(4).addAttributes(SLOW_FALL);
        gen.level(5).addAttributes(EXTRA_ATTACK, STUNNING_STRIKE);
        gen.level(6).addAttributes(KI_EMPOWERED_STRIKES);
        gen.level(7).addAttributes(EVASION);
        gen.level(7).addAttributes(STILLNESS_OF_MIND);
        gen.level(10).addAttributes(PURITY_OF_BODY);
        gen.level(13).addAttributes(TONGUE_OF_THE_SUN_AND_MOON);
        gen.level(14).addAttributes(DIAMOND_SOUL);
        gen.level(15).addAttributes(TIMELESS_BODY);
        gen.level(18).addAttributes(EMPTY_BODY);
        gen.level(20).addAttributes(PERFECT_SELF);
    }

    private void addEquipment(ChoiceGenerator gen) {
        gen.addEquipmentChoice("Weapon")
            .with(SHORTSWORD)
            .with(EquipmentCategory.SIMPLE_MELEE).with(EquipmentCategory.SIMPLE_RANGED);
        gen.addEquipmentChoice("Adventure Pack", DUNGEONEER_PACK, EXPLORER_PACK);
        gen.addEquipment(DART.makeSet(10));
    }
}
