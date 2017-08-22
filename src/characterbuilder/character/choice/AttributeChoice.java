package characterbuilder.character.choice;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class AttributeChoice extends OptionChoice {

    private final List<Attribute> attributes = new ArrayList<>();

    public AttributeChoice(String name, Stream<Attribute> attributes) {
        super(name);
        attributes.forEach(this.attributes::add);
    }

    public AttributeChoice(String name, Attribute... attributes) {
        super(name);
        this.attributes.addAll(Arrays.asList(attributes));
    }

    @Override
    public void select(Character character, ChoiceSelector selector) {
        if (attributes.stream().allMatch(character::hasAttribute))
            selector.choiceMade();
        else
            selector.chooseOption(attributes.stream()
                .filter(attr -> !character.hasAttribute(attr)),
                attr -> attr.choose(character));
    }
}
