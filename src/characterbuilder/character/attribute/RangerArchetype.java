package characterbuilder.character.attribute;

import org.w3c.dom.Node;

public enum RangerArchetype implements Attribute {
    HUNTER,
    BEASTMASTER;

    @Override
    public AttributeType getType() {
        return AttributeType.ROGUISH_ARCHETYPE;
    }

    public static RangerArchetype load(Node node) {
        return valueOf(node.getTextContent());
    }

}
