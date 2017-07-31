package characterbuilder.character.attribute;

import org.w3c.dom.Node;

public enum SorcerousOrigin implements Attribute {
    DRACONIC_BLOODLINE,
    WILD_MAGIC;

    @Override
    public AttributeType getType() {
        return AttributeType.SORCEROUS_ORIGIN;
    }

    public static SorcerousOrigin load(Node node) {
        return valueOf(node.getTextContent());
    }

}
