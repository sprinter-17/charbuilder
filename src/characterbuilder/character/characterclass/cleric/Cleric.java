package characterbuilder.character.characterclass.cleric;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Feat;
import characterbuilder.character.ability.Proficiency;
import static characterbuilder.character.ability.Proficiency.ALL_SIMPLE_WEAPONS;
import static characterbuilder.character.ability.Skill.*;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.*;
import characterbuilder.character.characterclass.AbstractCharacterClass;
import static characterbuilder.character.characterclass.CharacterClass.CLERIC;
import static characterbuilder.character.characterclass.cleric.ClericAbility.*;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.ChoiceGenerator;
import static characterbuilder.character.choice.ChoiceGenerator.cantripChoice;
import static characterbuilder.character.equipment.AdventureGear.CROSSBOW_BOLT;
import static characterbuilder.character.equipment.Armour.*;
import characterbuilder.character.equipment.EquipmentCategory;
import static characterbuilder.character.equipment.Weapon.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Cleric extends AbstractCharacterClass {

    private static final String CASTING_NAME = "Cleric";

    @Override
    public int getHitDie() {
        return 8;
    }

    @Override
    public AttributeType getClassAttribute() {
        return AttributeType.DIVINE_DOMAIN;
    }

    @Override
    public Stream<AttributeType> getPrimaryAttributes() {
        return Stream.of(WISDOM, CHARISMA);
    }

    @Override
    public Predicate<Character> getMulticlassPrerequisites() {
        return ch -> ch.getScore(WISDOM).getValue() >= 13;
    }

    @Override
    public boolean hasSavingsThrow(AttributeType type) {
        return Stream.of(WISDOM, CONSTITUTION, STRENGTH).anyMatch(type::equals);
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
            .addAttributes(ALL_SIMPLE_WEAPONS)
            .addChoice(2, new AttributeChoice("Skill", HISTORY, INSIGHT, MEDICINE,
                PERSUASION, RELIGION));
        gen.level(1).addAttributes(Proficiency.LIGHT_ARMOUR, Proficiency.MEDIUM_ARMOUR,
            Proficiency.SHIELD);
        gen.level(1).addChoice(new AttributeChoice("Divine Domain", DivineDomain.values()));
        gen.level(1).addAttributes(Feat.RITUAL_CASTER);
        gen.level(2).addAttributes(TURN_UNDEAD, CHANNEL_DIVINITY);
        gen.level(5).addAttributes(DESTROY_UNDEAD);
        gen.level(10).addAttributes(DIVINE_INTERVENTION);
        gen.level(4, 8, 12, 16, 19).addAbilityScoreOrFeatChoice();
    }

    private void addEquipment(ChoiceGenerator gen) {
        gen.addEquipmentChoice("Primary Weapon", MACE, WARHAMMER);
        gen.addEquipmentChoice("Secondary Weapon")
            .with(EquipmentCategory.SIMPLE_MELEE)
            .with(LIGHT_CROSSBOW, CROSSBOW_BOLT.makeSet(20));
        gen.addEquipmentChoice("Armour", SCALE_MAIL_ARMOUR, LEATHER_ARMOUR, CHAIN_MAIL_ARMOUR);
        gen.addEquipmentChoice(EquipmentCategory.HOLY_SYMBOL);
    }

    private void addCantrips(ChoiceGenerator gen) {
        gen.level(1).addChoice(cantripChoice(3, WISDOM));
        gen.level(5, 10).addChoice(cantripChoice(1, WISDOM));
    }

    private void addSpellCasting(ChoiceGenerator gen) {
        gen.level(1).addSpellCasting(CASTING_NAME, WISDOM, CLERIC, "[$wis_mod + $level]");
        gen.level(1).learnAllSpells(CASTING_NAME);
    }
}
