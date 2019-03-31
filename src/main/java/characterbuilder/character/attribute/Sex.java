package characterbuilder.character.attribute;

import characterbuilder.utils.StringUtils;
import org.w3c.dom.Node;

public enum Sex implements Attribute {
    MALE,
    FEMALE;

    @Override
    public AttributeType getType() {
        return AttributeType.SEX;
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    public static Sex load(Node node) {
        return Sex.valueOf(node.getTextContent());
    }
}
