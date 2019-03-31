package characterbuilder.character.attribute;

import characterbuilder.utils.StringUtils;
import java.util.EnumSet;
import org.w3c.dom.Node;

public enum Alignment implements Attribute {
    LAWFUL_GOOD,
    LAWFUL_NEUTRAL,
    LAWFUL_EVIL,
    NEUTRAL_GOOD,
    NEUTRAL,
    NEUTRAL_EVIL,
    CHAOTIC_GOOD,
    CHAOTIC_NEUTRAL,
    CHAOTIC_EVIL;

    @Override
    public AttributeType getType() {
        return AttributeType.ALIGNMENT;
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
        return EnumSet.of(CHAOTIC_GOOD, CHAOTIC_NEUTRAL, CHAOTIC_EVIL).contains(this);
    }

    public boolean isEvil() {
        return EnumSet.of(LAWFUL_EVIL, NEUTRAL_EVIL, CHAOTIC_EVIL).contains(this);
    }

    public boolean isNeutral() {
        return EnumSet.of(LAWFUL_NEUTRAL, CHAOTIC_NEUTRAL, NEUTRAL_EVIL, NEUTRAL_GOOD, NEUTRAL)
            .contains(this);
    }

    public static Alignment load(Node node) {
        return valueOf(node.getTextContent());
    }

}
