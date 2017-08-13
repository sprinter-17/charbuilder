package characterbuilder.character.choice;

import characterbuilder.character.attribute.AbilityScore;
import characterbuilder.character.attribute.Attribute;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

public class TestChoiceSelector implements ChoiceSelector {

    private Optional<Attribute> attribute = Optional.empty();
    private Optional<String> choiceName = Optional.empty();
    private List<Option> optionList;

    public TestChoiceSelector withAttribute(Attribute attribute) {
        this.attribute = Optional.of(attribute);
        return this;
    }

    public TestChoiceSelector withChoice(String choiceName) {
        this.choiceName = Optional.of(choiceName);
        return this;
    }

    @Override
    public <T extends Option> void chooseOption(Stream<T> options, Consumer<T> followUp) {
        optionList = options.collect(toList());
        if (attribute.isPresent())
            followUp.accept((T) attribute.get());
        else if (choiceName.isPresent())
            followUp.accept((T) optionList.stream()
                .filter(o -> o.toString().equals(choiceName.get()))
                .findAny()
                .orElseThrow(()
                    -> new IllegalArgumentException("Option " + choiceName.get()
                    + " not in list " + optionList)));
        else
            followUp.accept(((T) optionList.get(0)));
    }

    @Override
    public void generateAbilityScores(Consumer<Stream<AbilityScore>> consumer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getOptionCount() {
        return optionList.size();
    }

    public boolean hadOption(Option option) {
        return optionList.contains(option);
    }
}
