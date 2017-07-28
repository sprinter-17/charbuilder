package characterbuilder.character.attribute;

import characterbuilder.character.Character;
import characterbuilder.character.choice.Option;
import java.util.Optional;

public interface Attribute extends Option {

    public AttributeType getType();

    default public boolean hasType(AttributeType type) {
        return getType().equals(type);
    }

    default String encode() {
        if (this instanceof Enum)
            return ((Enum) this).name();
        else
            throw new AbstractMethodError("No encode method for Attribute class "
                + getClass().getName());
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

    default Optional<String> getDescription(Character character) {
        return Optional.empty();
    }

    @Override
    default void choose(Character character) {
        character.addAttribute(this);
        generateInitialChoices(character);
    }
}
