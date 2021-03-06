package characterbuilder.character.characterclass.barbarian;

import characterbuilder.character.Character;
import static characterbuilder.character.ability.Ability.*;
import characterbuilder.character.ability.Proficiency;
import static characterbuilder.character.ability.Proficiency.ALL_WEAPONS;
import static characterbuilder.character.ability.Skill.*;
import characterbuilder.character.attribute.AbilityScore;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.*;
import characterbuilder.character.characterclass.AbstractCharacterClass;
import static characterbuilder.character.characterclass.barbarian.BarbarianAbility.*;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.character.equipment.EquipmentCategory;
import static characterbuilder.character.equipment.EquipmentPack.EXPLORER_PACK;
import static characterbuilder.character.equipment.Weapon.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Barbarian extends AbstractCharacterClass {

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
    public Predicate<Character> getMulticlassPrerequisites() {
        return ch -> ch.getScore(STRENGTH).getValue() >= 13;
    }

    @Override
    protected void makeGenerator(ChoiceGenerator gen) {
        addEquipment(gen.initialClass());
        addAttributes(gen);
    }

    private void addEquipment(ChoiceGenerator gen) {
        gen.addEquipmentChoice("Primary Weapon")
            .with(GREATEAXE).with(EquipmentCategory.MARTIAL_MELEE);
        gen.addEquipmentChoice("Secondary Weapon")
            .with(HANDAXE.makeSet(2))
            .with(EquipmentCategory.SIMPLE_MELEE)
            .with(EquipmentCategory.SIMPLE_RANGED);
        gen.addEquipment(EXPLORER_PACK, JAVELIN.makeSet(4));
    }

    private void addAttributes(ChoiceGenerator gen) {
        gen.initialClass().addAttributes(Proficiency.LIGHT_ARMOUR, Proficiency.MEDIUM_ARMOUR);
        gen.initialClass().addAttributeChoice(2, "Skill",
            ANIMAL_HANDLING, ATHLETICS, INTIMIDATION, NATURE, PERCEPTION, SURVIVAL);
        gen.level(1).addAttributes(Proficiency.SHIELD, ALL_WEAPONS);
        gen.level(1).addAttributes(RAGE, UNARMORED_DEFENCE);
        gen.level(2).addAttributes(RECKLESS_ATTACK, DANGER_SENSE);
        gen.level(3).addAttributeChoice("Primal Path", PrimalPath.values());
        gen.level(4, 8, 12, 16, 19).addAbilityScoreOrFeatChoice();
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
