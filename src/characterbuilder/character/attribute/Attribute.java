package characterbuilder.character.attribute;

import characterbuilder.character.Character;
import characterbuilder.character.choice.Option;
import java.util.Optional;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public interface Attribute extends Option {

    public AttributeType getType();

    default public boolean hasType(AttributeType type) {
        return getType().equals(type);
    }

    default boolean isVisible() {
        return true;
    }

    default boolean isPresent() {
        return true;
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
    default Node save(Document doc) {
        if (this instanceof Enum) {
            Node node = getType().save(doc);
            node.setTextContent(((Enum) this).name());
            return node;
        } else {
            throw new AbstractMethodError("No encode method for Attribute class "
                + getClass().getName());
        }
    }

    @Override
    default void choose(Character character) {
        character.addAttribute(this);
        generateInitialChoices(character);
    }
}
