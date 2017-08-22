package characterbuilder.character.characterclass.rogue;

import characterbuilder.character.ability.Ability;
import characterbuilder.character.ability.Proficiency;
import static characterbuilder.character.ability.Proficiency.ALL_SIMPLE_WEAPONS;
import static characterbuilder.character.ability.Proficiency.THIEVES_CANT;
import static characterbuilder.character.ability.Skill.ACROBATICS;
import static characterbuilder.character.ability.Skill.ATHLETICS;
import static characterbuilder.character.ability.Skill.DECEPTION;
import static characterbuilder.character.ability.Skill.INSIGHT;
import static characterbuilder.character.ability.Skill.INTIMIDATION;
import static characterbuilder.character.ability.Skill.INVESTIGATION;
import static characterbuilder.character.ability.Skill.PERCEPTION;
import static characterbuilder.character.ability.Skill.PERFORMANCE;
import static characterbuilder.character.ability.Skill.PERSUASION;
import static characterbuilder.character.ability.Skill.SLEIGHT_OF_HAND;
import static characterbuilder.character.ability.Skill.STEALTH;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.characterclass.AbstractCharacterClass;
import static characterbuilder.character.characterclass.rogue.RogueAbility.*;
import characterbuilder.character.choice.AbilityScoreOrFeatIncrease;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.ChoiceGenerator;
import static characterbuilder.character.choice.ChoiceGenerator.levels;
import characterbuilder.character.choice.EquipmentChoice;
import characterbuilder.character.choice.ExpertiseChoice;
import characterbuilder.character.equipment.AdventureGear;
import static characterbuilder.character.equipment.AdventureGear.ARROW;
import static characterbuilder.character.equipment.AdventureGear.QUIVER;
import static characterbuilder.character.equipment.Armour.LEATHER_ARMOUR;
import static characterbuilder.character.equipment.EquipmentPack.BUGLAR_PACK;
import static characterbuilder.character.equipment.EquipmentPack.DUNGEONEER_PACK;
import static characterbuilder.character.equipment.EquipmentPack.EXPLORER_PACK;
import characterbuilder.character.equipment.EquipmentSet;
import static characterbuilder.character.equipment.Weapon.DAGGER;
import static characterbuilder.character.equipment.Weapon.HAND_CROSSBOW;
import static characterbuilder.character.equipment.Weapon.LONGSWORD;
import static characterbuilder.character.equipment.Weapon.RAPIER;
import static characterbuilder.character.equipment.Weapon.SHORTBOW;
import static characterbuilder.character.equipment.Weapon.SHORTSWORD;
import java.util.stream.Stream;

public class Rogue extends AbstractCharacterClass {

    @Override
    public int getHitDie() {
        return 8;
    }

    @Override
    public AttributeType getClassAttribute() {
        return AttributeType.ROGUISH_ARCHETYPE;
    }

    @Override
    public Stream<AttributeType> getPrimaryAttributes() {
        return Stream.of(AttributeType.DEXTERITY, AttributeType.INTELLIGENCE);
    }

    @Override
    public boolean hasSavingsThrow(AttributeType type) {
        return Stream
            .of(AttributeType.DEXTERITY, AttributeType.INTELLIGENCE, AttributeType.CHARISMA)
            .anyMatch(type::equals);
    }

    @Override
    protected void makeGenerator(ChoiceGenerator gen) {
        gen.level(1).addAttributes(Proficiency.LIGHT_ARMOUR, ALL_SIMPLE_WEAPONS,
            Proficiency.THIEVES_TOOLS);
        gen.level(1).addWeaponProficiencies(HAND_CROSSBOW, LONGSWORD, RAPIER, SHORTSWORD);
        gen.level(1).addAttributes(SNEAK_ATTACK, THIEVES_CANT);
        gen.level(1).
            addChoice(new AttributeChoice("Skill", ACROBATICS, ATHLETICS, DECEPTION, INSIGHT,
                INTIMIDATION, INVESTIGATION, PERCEPTION, PERFORMANCE, PERSUASION,
                SLEIGHT_OF_HAND, STEALTH).withCount(4));
        gen.level(1).addChoice(new ExpertiseChoice().withCount(2));
        gen.level(1).addEquipment(LEATHER_ARMOUR)
            .addEquipment(DAGGER, 2)
            .addEquipment(AdventureGear.THIEVES_TOOLS);
        gen.level(1).addChoice(new EquipmentChoice("Primary Weapon", RAPIER, SHORTSWORD));
        gen.level(1).addChoice(new EquipmentChoice("Secondard Weapon")
            .with(SHORTBOW, QUIVER, new EquipmentSet(ARROW, 20))
            .with(SHORTSWORD));
        gen.level(1).addChoice(new EquipmentChoice("Adventure Pack",
            BUGLAR_PACK, DUNGEONEER_PACK, EXPLORER_PACK));
        gen.level(2).addAttributes(CUNNING_ACTION);
        gen.level(3).addChoice(new AttributeChoice("Roguish Archetype",
            RoguishArchetype.values()));
        gen.level(5).addAttributes(Ability.UNCANNY_DODGE);
        gen.level(6).addChoice(new ExpertiseChoice().withCount(2));
        gen.level(7).addAttributes(Ability.EVASION);
        gen.level(11).addAttributes(RELIABLE_TALENT);
        gen.level(14).addAttributes(BLINDSENSE);
        gen.level(15).addAttributes(SLIPPERY_MIND);
        gen.level(18).addAttributes(ELUSIVE);
        gen.level(20).addAttributes(STROKE_OF_LUCK);
        gen.cond(levels(4, 8, 10, 12, 16, 19)).addChoice(2, new AbilityScoreOrFeatIncrease());
    }
}
