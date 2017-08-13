package characterbuilder.character.characterclass;

import characterbuilder.character.Character;
import static characterbuilder.character.ability.Ability.*;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.characterclass.CharacterClass.WIZARD;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.ChoiceGenerator;
import static characterbuilder.character.choice.ChoiceGenerator.spellChoice;
import characterbuilder.character.choice.ReplaceAttributeChoice;
import characterbuilder.character.choice.ReplaceSpell;
import characterbuilder.character.spell.SpellCasting;
import characterbuilder.utils.StringUtils;
import java.util.function.Consumer;
import org.w3c.dom.Node;

public enum MartialArchetype implements Attribute {

    CHAMPION(gen -> {
        gen.level(3).addAttributes(IMPROVED_CRITICAL);
        gen.level(7).addAttributes(REMARKABLE_ATHLETE);
        gen.level(7).addChoice(new AttributeChoice(AttributeType.FIGHTING_STYLE));
        gen.level(15).replaceAttribute(SUPERIOR_CRITICAL, IMPROVED_CRITICAL);
        gen.level(18).addAttributes(SURVIVOR);
    }),
    BATTLE_MASTER(gen -> {
        gen.level(3).addAttributes(COMBAT_SUPERIORITY);
        gen.level(3).addChoice(new AttributeChoice(AttributeType.TOOLS));
        gen.level(3).addChoice(3, new AttributeChoice("Maneuver", Maneuver.values()));
        gen.level(7, 10, 15)
            .addChoice(new ReplaceAttributeChoice("Maneuver", Maneuver.values()))
            .addChoice(2, new AttributeChoice("Maneuver", Maneuver.values()));
        gen.level(7).addAttributes(KNOW_YOUR_ENEMY);
        gen.level(15).addAttributes(RELENTLESS);
    }),
    ELDRITCH_KNIGHT(gen -> {
        final String castingName = "Eldritch Knight";
        gen.level(3).addAttributes(new SpellCasting(castingName, AttributeType.INTELLIGENCE));
        gen.level(3).addChoice(ChoiceGenerator.cantripChoice(2, castingName + " Cantrip",
            AttributeType.INTELLIGENCE, WIZARD.getSpells()));
        gen.level(3).addSpellSlots(castingName, 1, 2);
        gen.level(3).addChoice(spellChoice(castingName, 3, castingName + " Spell",
            WIZARD.getSpells()));
        gen.level(4, 7, 8, 10, 11, 13, 14, 16, 19, 20)
            .addChoice(new ReplaceSpell(castingName, WIZARD.getSpells()))
            .addChoice(spellChoice(castingName, 1, castingName + " Spell", WIZARD.getSpells()));
        gen.level(4).addSpellSlots(castingName, 1, 1);
        gen.level(7).addAttributes(WAR_MAGIC);
        gen.level(7).addSpellSlots(castingName, 1, 1);
        gen.level(7).addSpellSlots(castingName, 2, 2);
        gen.level(10).addAttributes(ELDRITCH_STRIKE);
        gen.level(10).addChoice(ChoiceGenerator.cantripChoice(1, castingName + " Cantrip",
            AttributeType.INTELLIGENCE, WIZARD.getSpells()));
        gen.level(10).addSpellSlots(castingName, 2, 1);
        gen.level(13).addSpellSlots(castingName, 3, 2);
        gen.level(15).addAttributes(ARCANE_CHARGE);
        gen.level(16).addSpellSlots(castingName, 3, 1);
        gen.level(19).addSpellSlots(castingName, 4, 1);
    });

    private final Consumer<ChoiceGenerator> generatorMaker;

    private MartialArchetype(Consumer<ChoiceGenerator> generatorMaker) {
        this.generatorMaker = generatorMaker;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.MARTIAL_ARCHETYPE;
    }

    @Override
    public void generateLevelChoices(Character character) {
        ChoiceGenerator generator = new ChoiceGenerator();
        generatorMaker.accept(generator);
        generator.generateChoices(character);
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    public static MartialArchetype load(Node node) {
        return MartialArchetype.valueOf(node.getTextContent());
    }

}
