package characterbuilder.character.characterclass.rogue;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Ability;
import characterbuilder.character.ability.Language;
import characterbuilder.character.ability.Proficiency;
import static characterbuilder.character.ability.Proficiency.ALL_SIMPLE_WEAPONS;
import static characterbuilder.character.ability.Skill.*;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.DEXTERITY;
import characterbuilder.character.characterclass.AbstractCharacterClass;
import static characterbuilder.character.characterclass.rogue.RogueAbility.*;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.character.choice.ExpertiseChoice;
import characterbuilder.character.equipment.AdventureGear;
import static characterbuilder.character.equipment.AdventureGear.ARROW;
import static characterbuilder.character.equipment.AdventureGear.QUIVER;
import static characterbuilder.character.equipment.Armour.LEATHER_ARMOUR;
import static characterbuilder.character.equipment.EquipmentPack.BUGLAR_PACK;
import static characterbuilder.character.equipment.EquipmentPack.DUNGEONEER_PACK;
import static characterbuilder.character.equipment.EquipmentPack.EXPLORER_PACK;
import static characterbuilder.character.equipment.Weapon.DAGGER;
import static characterbuilder.character.equipment.Weapon.HAND_CROSSBOW;
import static characterbuilder.character.equipment.Weapon.LONGSWORD;
import static characterbuilder.character.equipment.Weapon.RAPIER;
import static characterbuilder.character.equipment.Weapon.SHORTBOW;
import static characterbuilder.character.equipment.Weapon.SHORTSWORD;
import java.util.function.Predicate;
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
    public Predicate<Character> getMulticlassPrerequisites() {
        return ch -> ch.getScore(DEXTERITY).getValue() >= 13;
    }

    @Override
    public boolean hasSavingsThrow(AttributeType type) {
        return Stream
            .of(AttributeType.DEXTERITY, AttributeType.INTELLIGENCE, AttributeType.CHARISMA)
            .anyMatch(type::equals);
    }

    @Override
    protected void makeGenerator(ChoiceGenerator gen) {
        addAbilities(gen);
        addEquipment(gen);
    }

    private void addAbilities(ChoiceGenerator gen) {
        gen.level(1).addAttributes(Proficiency.LIGHT_ARMOUR, ALL_SIMPLE_WEAPONS,
            Proficiency.THIEVES_TOOLS);
        gen.level(1).addWeaponProficiencies(HAND_CROSSBOW, LONGSWORD, RAPIER, SHORTSWORD);
        gen.level(1).addAttributes(SNEAK_ATTACK, Language.THIEVES_CANT);
        gen.level(1).addAttributeChoice(4, "Skill", ACROBATICS, ATHLETICS, DECEPTION, INSIGHT,
            INTIMIDATION, INVESTIGATION, PERCEPTION, PERFORMANCE, PERSUASION, SLEIGHT_OF_HAND, STEALTH);
        gen.level(1).addChoice(new ExpertiseChoice().withCount(2));
        gen.level(2).addAttributes(CUNNING_ACTION);
        gen.level(3).addAttributeChoice("Roguish Archetype", RoguishArchetype.values());
        gen.level(5).addAttributes(Ability.UNCANNY_DODGE);
        gen.level(6).addChoice(new ExpertiseChoice().withCount(2));
        gen.level(7).addAttributes(Ability.EVASION);
        gen.level(11).addAttributes(RELIABLE_TALENT);
        gen.level(14).addAttributes(BLINDSENSE);
        gen.level(15).addAttributes(SLIPPERY_MIND);
        gen.level(18).addAttributes(ELUSIVE);
        gen.level(20).addAttributes(STROKE_OF_LUCK);
        gen.level(4, 8, 10, 12, 16, 19).addAbilityScoreOrFeatChoice();
    }

    private void addEquipment(ChoiceGenerator gen) {
        gen.level(1).addEquipment(LEATHER_ARMOUR)
            .addEquipment(DAGGER.makeSet(2))
            .addEquipment(AdventureGear.THIEVES_TOOLS);
        gen.level(1).addEquipmentChoice("Primary Weapon", RAPIER, SHORTSWORD);
        gen.level(1).addEquipmentChoice("Secondard Weapon")
            .with(SHORTBOW, QUIVER, ARROW.makeSet(20)).with(SHORTSWORD);
        gen.level(1)
            .addEquipmentChoice("Adventure Pack", BUGLAR_PACK, DUNGEONEER_PACK, EXPLORER_PACK);
    }
}
