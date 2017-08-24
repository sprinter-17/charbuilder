package characterbuilder.character.characterclass.cleric;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import java.util.stream.Stream;
import org.w3c.dom.Element;

public enum ClericAbility implements Attribute {

    ;

    @Override
    public AttributeType getType() {
        return AttributeType.CLERIC_ABILITY;
    }

    @Override
    public void generateInitialChoices(Character character) {
        Attribute.super.generateInitialChoices(character);
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return Attribute.super.getDescription(character);
    }

    public static ClericAbility load(Element element) {
        return valueOf(element.getTextContent());
    }

}
