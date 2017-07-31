package characterbuilder.character.attribute;

import org.w3c.dom.Node;

public enum DruidCircle implements Attribute {
    CIRCLE_OF_THE_LAND,
    CIRCLE_OF_THE_MOON;

    @Override
    public AttributeType getType() {
        return AttributeType.DRUID_CIRCLE;
    }

    public static DruidCircle load(Node node) {
        return valueOf(node.getTextContent());
    }
}
