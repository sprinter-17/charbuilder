package characterbuilder.character.characterclass;

import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import org.w3c.dom.Element;

public enum EldritchInvocation implements Attribute {

    ;

    @Override
    public AttributeType getType() {
        return AttributeType.ELDRITCH_INVOCATION;
    }

    public static EldritchInvocation load(Element element) {
        return valueOf(element.getTextContent());
    }

}
