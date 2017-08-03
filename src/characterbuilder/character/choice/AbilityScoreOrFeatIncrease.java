package characterbuilder.character.choice;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Ability;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.IntAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class AbilityScoreOrFeatIncrease extends OptionChoice {

    private class AbilityScoreIncrease implements Option {

        private final AttributeType score;

        public AbilityScoreIncrease(AttributeType score) {
            this.score = score;
        }

        @Override
        public void choose(Character character) {
            IntAttribute attr = character.getAttribute(score);
            attr.addValue(1);
            if (score.equals(AttributeType.CONSTITUTION)
                && attr.getValue() % 2 == 0) {
                IntAttribute hp = character.getAttribute(AttributeType.HIT_POINTS);
                hp.addValue(character.getLevel());
            }
        }

        @Override
        public Node save(Document doc) {
            throw new UnsupportedOperationException("Cannot save ability increase option.");
        }

        @Override
        public String toString() {
            return score.toString();
        }
    }

    private boolean featsAllowed = true;
    private final List<AttributeType> scoresAllowed = new ArrayList<>();

    public AbilityScoreOrFeatIncrease() {
        super("Increase Ability Score");
        AttributeType.ABILITY_SCORES.forEach(scoresAllowed::add);
    }

    public AbilityScoreOrFeatIncrease(String name, AttributeType... scores) {
        super(name);
        Arrays.stream(scores).forEach(scoresAllowed::add);
    }

    public AbilityScoreOrFeatIncrease withoutFeats() {
        this.featsAllowed = false;
        return this;
    }

    @Override
    public void select(Character character, ChoiceSelector selector) {
        if (!isAllowed(character))
            throw new IllegalStateException("Attempt to increase non-existent ability scores.");
        Stream.Builder<Option> options = Stream.builder();
        scoresAllowed.stream()
            .filter(as -> character.getIntAttribute(as) < 20)
            .map(AbilityScoreIncrease::new)
            .forEach(options::add);
        if (featsAllowed) {
            Arrays.stream(Ability.values())
                .filter(ab -> ab.hasType(AttributeType.FEAT))
                .filter(ab -> ab.isAllowed(character))
                .forEach(options::add);
        }
        selector.chooseOption(options.build(), o -> o.choose(character));
    }

    @Override
    public boolean isAllowed(Character character) {
        return AttributeType.ABILITY_SCORES.stream().allMatch(character::hasAttribute);
    }
}