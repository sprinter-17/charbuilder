package characterbuilder.character.attribute;

import characterbuilder.character.Character;
import characterbuilder.character.choice.Option;
import java.util.Optional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public interface Attribute extends Option {

    public AttributeType getType();

    default public boolean hasType(AttributeType type) {
        return getType().equals(type);
    }

    default Optional<Attribute> getSuperSet() {
        return Optional.empty();
    }

    default void generateInitialChoices(Character character) {
        generateLevelChoices(character);
    }

    default void generateLevelChoices(Character character) {
    }

    @Override
    default Element save(Document doc) {
        if (this instanceof Enum) {
            Element element = getType().save(doc);
            element.setTextContent(((Enum) this).name());
            return element;
        } else {
            throw new AbstractMethodError("No save method for " + getClass().getName());
        }
    }

    @Override
    default void choose(Character character) {
        character.addAttribute(this);
        generateInitialChoices(character);
    }
}
