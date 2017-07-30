package characterbuilder.character.choice;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.IntAttribute;
import java.util.stream.Stream;

public class AbilityScoreIncrease extends OptionChoice {

    public AbilityScoreIncrease() {
        super("Increase Ability Score");
    }

    @Override
    public void select(Character character, ChoiceSelector selector) {
        if (!isAllowed(character))
            throw new IllegalStateException("Attempt to increase non-existent ability scores.");
        Stream<IntAttribute> abilityScores = AttributeType.ABILITY_SCORES.stream()
            .filter(as -> character.getIntAttribute(as) < 20)
            .map(as -> new IntAttribute(as, 0) {
            @Override
            public String toString() {
                return as.toString();
            }
        });
        selector.chooseOption(abilityScores, as -> {
            IntAttribute attr = character.getAttribute(as.getType());
            attr.addValue(1);
            if (as.getType().equals(AttributeType.CONSTITUTION)
                && attr.getValue() % 2 == 0) {
                IntAttribute hp = character.getAttribute(AttributeType.HIT_POINTS);
                hp.addValue(character.getLevel());
            }
        });
    }

    @Override
    public boolean isAllowed(Character character) {
        return AttributeType.ABILITY_SCORES.stream().allMatch(character::hasAttribute);
    }
}
