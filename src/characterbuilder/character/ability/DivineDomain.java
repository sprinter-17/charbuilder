package characterbuilder.character.ability;

import characterbuilder.character.Character;
import static characterbuilder.character.ability.Ability.BLESSED_HEALER;
import static characterbuilder.character.ability.Ability.DISCIPLE_OF_LIFE;
import static characterbuilder.character.ability.Ability.DIVINE_STRIKE;
import static characterbuilder.character.ability.Ability.PRESERVE_LIFE;
import static characterbuilder.character.ability.Ability.SUPREME_HEALING;
import static characterbuilder.character.ability.Spell.*;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.utils.StringUtils;

public enum DivineDomain implements Attribute {
    KNOWLEDGE,
    LIFE,
    LIGHT,
    NATURE,
    TEMPEST,
    TRICKERY,
    WAR;

    static {
        LIFE.generator.level(1)
            .addAttributes(Proficiency.HEAVY_ARMOUR, DISCIPLE_OF_LIFE,
                BLESS, CURE_WOUNDS);
        LIFE.generator.level(2)
            .addAttributes(PRESERVE_LIFE);
        LIFE.generator.level(3)
            .addAttributes(LESSER_RESTORATION, SPIRITUAL_WEAPON);
        LIFE.generator.level(6)
            .addAttributes(BLESSED_HEALER);
        LIFE.generator.level(7)
            .addAttributes(BEACON_OF_HOPE, REVIVIFY);
        LIFE.generator.level(8)
            .addAttributes(DIVINE_STRIKE);
        LIFE.generator.level(9)
            .addAttributes(MASS_CURE_WOUNDS, RAISE_DEAD);
        LIFE.generator.level(17)
            .addAttributes(SUPREME_HEALING);
    }

    private final ChoiceGenerator generator = new ChoiceGenerator();

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

}
