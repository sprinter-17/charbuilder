package characterbuilder.character.choice;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Ability;
import characterbuilder.character.ability.Proficiency;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class AttributeChoice extends OptionChoice {

    private final String name;
    private final List<Attribute> attributes = new ArrayList<>();
    private final List<Consumer<Character>> actions = new ArrayList<>();

    public AttributeChoice(AttributeType type) {
        this.name = type.toString();
        Stream.concat(Arrays.stream(Ability.values()), Arrays.stream(Proficiency.values()))
            .filter(prof -> prof.hasType(type))
            .forEach(this.attributes::add);
    }

    public AttributeChoice(String name, Stream<Attribute> attributes) {
        this.name = name;
        attributes.forEach(this.attributes::add);
    }

    public AttributeChoice(String name, Attribute... attributes) {
        this.name = name;
        this.attributes.addAll(Arrays.asList(attributes));
    }

    public AttributeChoice andThen(Consumer<Character> action) {
        actions.add(action);
        return this;
    }

    @Override
    public void select(Character character, ChoiceSelector selector) {
        selector.chooseOption(attributes.stream()
            .filter(att -> !character.hasAttribute(att)),
            attr -> useChoice(character, attr));
    }

    protected void useChoice(Character character, Option attribute) {
        attribute.choose(character);
        actions.forEach(act -> act.accept(character));
    }

    @Override
    public String toString() {
        return name;
    }
}
