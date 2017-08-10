package characterbuilder.utils;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AbilityScore;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.character.choice.TestChoiceSelector;

public class TestCharacter extends Character {

    private final TestChoiceSelector selector = new TestChoiceSelector();

    public TestCharacter() {
        addChoiceList(selector);
    }

    public TestCharacter withScores(int value) {
        AbilityScore.SCORES.forEach(as -> addAttribute(new AbilityScore(as, value)));
        return this;
    }

    public void setLevel(int level) {
        if (hasAttribute(AttributeType.LEVEL))
            getAttribute(AttributeType.LEVEL, IntAttribute.class).setValue(level);
        else
            addAttribute(new IntAttribute(AttributeType.LEVEL, level));
    }
}
