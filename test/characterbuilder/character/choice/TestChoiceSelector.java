package characterbuilder.character.choice;

import characterbuilder.character.attribute.Attribute;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class TestChoiceSelector implements ChoiceSelector {

    private Optional<Attribute> attribute = Optional.empty();

    public TestChoiceSelector withAttribute(Attribute attribute) {
        this.attribute = Optional.of(attribute);
        return this;
    }

    @Override
    public <T extends Option> void chooseOption(Stream<T> options, Consumer<T> followUp) {
        if (attribute.isPresent())
            followUp.accept((T) attribute.get());
        else
            followUp.accept(options.findFirst().get());
    }

    @Override
    public void generateAbilityScores(Consumer<Stream<Attribute>> consumer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
