package characterbuilder.character.attribute;

import characterbuilder.character.Character;
import characterbuilder.character.choice.AbilityScoreOrFeatIncrease;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.character.choice.OptionChoice;
import characterbuilder.utils.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import static java.util.stream.Collectors.joining;
import java.util.stream.Stream;

public class AttributeDelegate {

    private Optional<String> name = Optional.empty();
    private final List<String> description = new ArrayList<>();
    private final List<Predicate<Character>> prerequisites = new ArrayList<>();
    private final ChoiceGenerator generator = new ChoiceGenerator();

    public Optional<String> getName() {
        return name;
    }

    public Stream<String> getDescription(Character character) {
        return Stream.concat(
            description.stream(),
            generator.getDescription(character))
            .map(desc -> StringUtils.expand(desc, character));
    }

    public AttributeDelegate withName(String name) {
        this.name = Optional.of(name);
        return this;
    }

    public AttributeDelegate withDescription(String description) {
        this.description.add(description);
        return this;
    }

    public AttributeDelegate withPrerequisite(Predicate<Character> prerequisite) {
        prerequisites.add(prerequisite);
        return this;
    }

    public AttributeDelegate withPrerequisite(AttributeType score, int value) {
        return withPrerequisite(ch -> ch.getIntAttribute(score) >= value);
    }

    public AttributeDelegate withPrerequisite(AttributeType attribute) {
        return withPrerequisite(ch -> ch.hasAttribute(attribute));
    }

    public AttributeDelegate withChoice(OptionChoice choice) {
        this.generator.addChoice(choice);
        return this;
    }

    public AttributeDelegate withChoice(int count, OptionChoice choice) {
        this.generator.addChoice(count, choice);
        return this;
    }

    public AttributeDelegate withAction(String name, Consumer<Character> action) {
        generator.addAction(name, action);
        return this;
    }

    public AttributeDelegate withIncrease(AttributeType score) {
        generator.addAction("Increase " + score.toString(), ch -> {
            IntAttribute value = ch.getAttribute(score);
            value.addValue(1);
            value.setInRange(1, 20);
        });
        return this;
    }

    public AttributeDelegate withIncreaseChoice(AttributeType... scores) {
        String increaseName = "Increase "
            + Arrays.stream(scores).map(AttributeType::toString).collect(joining(" or "));
        generator.addChoice(new AbilityScoreOrFeatIncrease(increaseName, scores));
        return this;
    }

    public void generateChoices(Character character) {
        generator.generateChoices(character);
    }

    public boolean isAllowed(Character character) {
        return prerequisites.stream().allMatch(p -> p.test(character));
    }

}
