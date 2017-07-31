package characterbuilder.character.attribute;

import org.w3c.dom.Node;

public enum SacredOath implements Attribute {
    OATH_OF_DEVOTION,
    OATH_OF_THE_ANCIENTS,
    OATH_OF_VENGEANCE,;

    @Override
    public AttributeType getType() {
        return AttributeType.SACRED_OATH;
    }

    public static SacredOath load(Node node) {
        return valueOf(node.getTextContent());
    }

}
