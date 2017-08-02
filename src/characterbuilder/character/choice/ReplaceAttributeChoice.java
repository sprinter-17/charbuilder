package characterbuilder.character.choice;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import java.util.List;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

public class ReplaceAttributeChoice<T extends Attribute> extends OptionChoice {

    private static final Attribute NO_REPLACEMENT = new Attribute() {

        @Override
        public AttributeType getType() {
            throw new AbstractMethodError("No replacement option does not have a type");
        }

        @Override
        public void choose(Character character) {
            // do nothing on selection.
        }

        @Override
        public String toString() {
            return "None";
        }
    };
    private final List<T> attributes;
    private final OptionChoice replacementChoice;

    public ReplaceAttributeChoice(String name, int count, Stream<T> attributes) {
        super("Remove " + name, count);
        this.attributes = attributes.collect(toList());
        this.replacementChoice = new AttributeChoice("Replacement " + name,
            this.attributes.stream().map(attr -> (Attribute) attr));
    }

    @Override
    public void select(Character character, ChoiceSelector selector) {
        selector.chooseOption(
            Stream.concat(Stream.of(NO_REPLACEMENT),
                attributes.stream().filter(character::hasAttribute)),
            attr -> remove(character, attr));
    }

    private void remove(Character character, Attribute attribute) {
        if (attribute != NO_REPLACEMENT) {
            character.removeAttribute(attribute);
            character.addChoice(replacementChoice);

        }
    }

}
