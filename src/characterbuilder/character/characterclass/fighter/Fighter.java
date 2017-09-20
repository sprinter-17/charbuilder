package characterbuilder.character.characterclass.fighter;

import characterbuilder.character.Character;
import characterbuilder.character.ability.FightingStyle;
import static characterbuilder.character.ability.Proficiency.ALL_ARMOUR;
import static characterbuilder.character.ability.Proficiency.ALL_WEAPONS;
import static characterbuilder.character.ability.Skill.*;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.*;
import characterbuilder.character.characterclass.AbstractCharacterClass;
import static characterbuilder.character.characterclass.fighter.FighterAbility.*;
import characterbuilder.character.choice.ChoiceGenerator;
import static characterbuilder.character.equipment.AdventureGear.ARROW;
import static characterbuilder.character.equipment.AdventureGear.CROSSBOW_BOLT;
import characterbuilder.character.equipment.Armour;
import static characterbuilder.character.equipment.Armour.*;
import characterbuilder.character.equipment.EquipmentCategory;
import static characterbuilder.character.equipment.EquipmentPack.*;
import static characterbuilder.character.equipment.Weapon.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Fighter extends AbstractCharacterClass {

    @Override
    public int getHitDie() {
        return 10;
    }

    @Override
    public AttributeType getClassAttribute() {
        return AttributeType.MARTIAL_ARCHETYPE;
    }

    @Override
    public Stream<AttributeType> getPrimaryAttributes() {
        return Stream.of(AttributeType.STRENGTH, AttributeType.CONSTITUTION);
    }

    @Override
    public Predicate<Character> getMulticlassPrerequisites() {
        return ch -> ch.getScore(STRENGTH).getValue() >= 13
            || ch.getScore(DEXTERITY).getValue() >= 13;
    }

    @Override
    public boolean hasSavingsThrow(AttributeType type) {
        return super.hasSavingsThrow(type) || type == AttributeType.DEXTERITY;
    }

    @Override
    protected void makeGenerator(ChoiceGenerator gen) {
        addAbilities(gen);
        addEquipment(gen.initialClass());
    }

    private void addAbilities(ChoiceGenerator gen) {
        gen.level(1)
            .addAttributes(ALL_ARMOUR, ALL_WEAPONS, SECOND_WIND)
            .addAttributeChoice(2, "Skill", ACROBATICS, ANIMAL_HANDLING, ATHLETICS, HISTORY,
                INSIGHT, INTIMIDATION, PERCEPTION, SURVIVAL)
            .addAttributeChoice("Fighting Style", FightingStyle.values());
        gen.level(2).addAttributes(ACTION_SURGE);
        gen.level(3).addAttributeChoice("Martial Archetype", MartialArchetype.values());
        gen.level(5).addAttributes(EXTRA_ATTACK);
        gen.level(9).addAttributes(INDOMITABLE);
        gen.level(4, 6, 8, 12, 14, 16, 19).addAbilityScoreOrFeatChoice();
    }

    private void addEquipment(ChoiceGenerator gen) {
        gen.addEquipmentChoice("Armour")
            .with(CHAIN_MAIL_ARMOUR).with(LEATHER_ARMOUR, LONGBOW, ARROW.makeSet(20));
        gen.addEquipmentChoice("Primary Weapon").with(EquipmentCategory.MARTIAL_MELEE);
        gen.addEquipmentChoice("Secondary Weapon Or Shield")
            .with(Armour.SHIELD).with(EquipmentCategory.MARTIAL_MELEE);
        gen.addEquipmentChoice("Ranged Weapon")
            .with(LIGHT_CROSSBOW, CROSSBOW_BOLT.makeSet(20))
            .with(HANDAXE.makeSet(2));
        gen.addEquipmentChoice("Adventure Pack", DUNGEONEER_PACK, EXPLORER_PACK);
    }
}
