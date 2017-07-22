package characterbuilder.character.attribute;

import characterbuilder.utils.StringUtils;
import java.util.EnumSet;

public enum Alignment implements Attribute {
    LAWFUL_GOOD,
    LAWFUL_NEUTRAL,
    LAWFUL_EVIL,
    NEUTRAL_GOOD,
    NEUTRAL,
    NEUTRAL_EVIL,
    CHAOTIC_GOOD,
    CAOTIC_NEUTRAL,
    CHAOTIC_EVIL;

    @Override
    public AttributeType getType() {
        return AttributeType.ALIGNMENT;
    }

    @Override
    public String encode() {
        return name();
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    public boolean isLawful() {
        return EnumSet.of(LAWFUL_GOOD, LAWFUL_NEUTRAL, LAWFUL_EVIL).contains(this);
    }

    public boolean isGood() {
        return EnumSet.of(LAWFUL_GOOD, NEUTRAL_GOOD, CHAOTIC_GOOD).contains(this);
    }

    public boolean isChaotic() {
        return EnumSet.of(CHAOTIC_GOOD, CAOTIC_NEUTRAL, CHAOTIC_EVIL).contains(this);
    }

    public boolean isEvil() {
        return EnumSet.of(LAWFUL_EVIL, NEUTRAL_EVIL, CHAOTIC_EVIL).contains(this);
    }

}
