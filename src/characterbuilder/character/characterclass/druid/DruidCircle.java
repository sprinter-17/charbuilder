package characterbuilder.character.characterclass.druid;

import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.utils.StringUtils;
import org.w3c.dom.Node;

public enum DruidCircle implements Attribute {
    CIRCLE_OF_THE_LAND,
    CIRCLE_OF_THE_MOON;

    @Override
    public AttributeType getType() {
        return AttributeType.DRUID_CIRCLE;
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    public static DruidCircle load(Node node) {
        return valueOf(node.getTextContent());
    }
}