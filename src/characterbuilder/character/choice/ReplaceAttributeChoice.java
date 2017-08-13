package characterbuilder.character.choice;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

public class ReplaceAttributeChoice<T extends Attribute> extends OptionChoice {

    private static final Attribute NO_REPLACEMENT = new Attribute() {

        @Override
        public AttributeType getType() {
            throw new AbstractMethodError("No replacement option does not have a type");
        }

        @Override
        public void choose(Character character) {
            // do nothing on selection other than remove choice
        }

        @Override
        public String toString() {
            return "None";
        }
    };
    private final Function<Character, Stream<T>> attributes;

    public ReplaceAttributeChoice(String name, Function<Character, Stream<T>> attributes) {
        super("Remove " + name, 1);
        this.attributes = attributes;
    }

    public ReplaceAttributeChoice(String name, T... attributes) {
        this(name, ch -> Arrays.stream(attributes));
    }

    @Override
    public void select(Character character, ChoiceSelector selector) {
        selector.chooseOption(
            Stream.concat(Stream.of(NO_REPLACEMENT),
                attributes.apply(character).filter(character::hasAttribute)),
            attr -> remove(character, attr));
    }

    private void remove(Character character, Attribute attribute) {
        if (attribute != NO_REPLACEMENT) {
            character.removeAttribute(attribute);
            character.addChoice(new AttributeChoice(getName().replace("Remove ", "Replace "),
                this.attributes.apply(character).map(attr -> (Attribute) attr)));
        }
    }

}
