package characterbuilder.character.ability;

import characterbuilder.character.Character;
import static characterbuilder.character.ability.Ability.*;
import static characterbuilder.character.ability.Skill.*;
import static characterbuilder.character.ability.Spell.*;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.utils.StringUtils;
import java.util.function.Consumer;
import org.w3c.dom.Node;

public enum DivineDomain implements Attribute {
    KNOWLEDGE(gen -> {
        gen.level(1).addAttributes(COMMAND, IDENTIFY);
        gen.level(1).addChoice(2, new AttributeChoice(AttributeType.LANGUAGE));
        gen.level(1).addChoice(2, new AttributeChoice("Skill", ARCANA, HISTORY,
            Skill.NATURE, RELIGION));
        gen.level(2).addAttributes(KNOWLEDGE_OF_THE_AGES);
        gen.level(3).addAttributes(AUGURY, SUGGESTION);
        gen.level(5).addAttributes(NONDETECTION, SPEAK_WITH_DEAD);
        gen.level(6).addAttributes(READ_THOUGHTS);
        gen.level(7).addAttributes(ARCANE_EYE, CONFUSION);
        gen.level(8).addAttributes(POTENT_SPELLCASTING);
        gen.level(9).addAttributes(LEGEND_LORE, SCRYING);
        gen.level(17).addAttributes(VISIONS_OF_THE_PAST);
    }),
    LIFE(gen -> {
        gen.level(1).addAttributes(Proficiency.HEAVY_ARMOUR, DISCIPLE_OF_LIFE, BLESS, CURE_WOUNDS);
        gen.level(2).addAttributes(PRESERVE_LIFE);
        gen.level(3).addAttributes(LESSER_RESTORATION, SPIRITUAL_WEAPON);
        gen.level(6).addAttributes(BLESSED_HEALER);
        gen.level(7).addAttributes(BEACON_OF_HOPE, REVIVIFY);
        gen.level(8).addAttributes(DIVINE_STRIKE);
        gen.level(9).addAttributes(MASS_CURE_WOUNDS, RAISE_DEAD);
        gen.level(17).addAttributes(SUPREME_HEALING);
    }),
    LIGHT(gen -> {

    }),
    NATURE(gen -> {

    }),
    TEMPEST(gen -> {

    }),
    TRICKERY(gen -> {

    }),
    WAR(gen -> {

    });

    static {

    }

    private final ChoiceGenerator generator = new ChoiceGenerator();

    private DivineDomain(Consumer<ChoiceGenerator> gen) {
        gen.accept(generator);
    }

    @Override
    public AttributeType getType() {
        return AttributeType.DIVINE_DOMAIN;
    }

    @Override
    public void generateLevelChoices(Character character) {
        generator.generateChoices(character);
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    public static DivineDomain load(Node node) {
        return DivineDomain.valueOf(node.getTextContent());
    }

}
