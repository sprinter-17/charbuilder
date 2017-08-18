package characterbuilder.character.characterclass;

import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.utils.StringUtils;
import org.w3c.dom.Node;

public enum OtherwordlyPatron implements Attribute {
    THE_ARCHFEY,
    THE_FIEND,
    THE_GREAT_OLD_ONE;

    @Override
    public AttributeType getType() {
        return AttributeType.OTHERWORLDLY_PATRON;
    }

    public static OtherwordlyPatron load(Node node) {
        return valueOf(node.getTextContent());
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }
}
