package characterbuilder.character.characterclass.barbarian;

import static characterbuilder.character.ability.Ability.*;
import characterbuilder.character.ability.Proficiency;
import static characterbuilder.character.ability.Proficiency.ALL_WEAPONS;
import static characterbuilder.character.ability.Skill.*;
import characterbuilder.character.attribute.AbilityScore;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.*;
import characterbuilder.character.characterclass.CharacterClassDelegate;
import characterbuilder.character.choice.AbilityScoreOrFeatIncrease;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.ChoiceGenerator;
import static characterbuilder.character.choice.ChoiceGenerator.levels;
import characterbuilder.character.choice.EquipmentChoice;
import characterbuilder.character.equipment.EquipmentCategory;
import static characterbuilder.character.equipment.EquipmentPack.EXPLORER_PACK;
import characterbuilder.character.equipment.EquipmentSet;
import static characterbuilder.character.equipment.Weapon.*;
import java.util.stream.Stream;

public class Barbarian extends CharacterClassDelegate {

    @Override
    public int getHitDie() {
        return 12;
    }

    @Override
    public AttributeType getClassAttribute() {
        return PRIMAL_PATH;
    }

    @Override
    public Stream<AttributeType> getPrimaryAttributes() {
        return Stream.of(STRENGTH, CONSTITUTION);
    }

    @Override
    public boolean hasSavingsThrow(AttributeType type) {
        return getPrimaryAttributes().anyMatch(type::equals);
    }

    @Override
    protected void makeGenerator(ChoiceGenerator gen) {
        gen.level(1).addAttributes(Proficiency.LIGHT_ARMOUR, Proficiency.MEDIUM_ARMOUR,
            Proficiency.SHIELD, ALL_WEAPONS);
        gen.level(1).addChoice(2, new AttributeChoice("Skill",
            ANIMAL_HANDLING, ATHLETICS, INTIMIDATION, NATURE, PERCEPTION, SURVIVAL));
        gen.level(1).addChoice(new EquipmentChoice("Primary Weapon")
            .with(GREATEAXE).with(EquipmentCategory.MARTIAL_MELEE));
        gen.level(1).addChoice(new EquipmentChoice("Secondary Weapon")
            .with(new EquipmentSet(HANDAXE, 2))
            .with(EquipmentCategory.SIMPLE_MELEE)
            .with(EquipmentCategory.SIMPLE_RANGED));
        gen.level(1).addEquipment(EXPLORER_PACK);
        gen.level(1).addEquipment(JAVELIN, 4);
        gen.level(1).addAttributes(RAGE, UNARMORED_DEFENCE_BARBARIAN);
        gen.level(2).addAttributes(RECKLESS_ATTACK, DANGER_SENSE);
        gen.level(3).addChoice(new AttributeChoice("Primal Path", PrimalPath.values()));
        gen.cond(levels(4, 8, 12, 16, 19)).addChoice(2, new AbilityScoreOrFeatIncrease());
        gen.level(5).addAttributes(EXTRA_ATTACK, FAST_MOVEMENT);
        gen.level(7).addAttributes(FERAL_INSTINCTS);
        gen.level(9).addAttributes(BRUTAL_CRITICAL);
        gen.level(11).addAttributes(RELENTLESS_RAGE);
        gen.level(15).addAttributes(PERSISTENT_RAGE);
        gen.level(18).addAttributes(INDOMITABLE_MIGHT);
        gen.level(20).addAction("Increase Str. and Con.", ch -> {
            ch.getAttribute(STRENGTH, AbilityScore.class).setMax(24);
            ch.getAttribute(STRENGTH, AbilityScore.class).addValue(4);
            ch.getAttribute(CONSTITUTION, AbilityScore.class).setMax(24);
            ch.getAttribute(CONSTITUTION, AbilityScore.class).addValue(4);
        });
    }
}
