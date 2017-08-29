package characterbuilder.utils;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AbilityScore;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.character.choice.Option;
import characterbuilder.character.choice.OptionChoice;
import characterbuilder.character.choice.TestChoiceSelector;

public class TestCharacter extends Character {

    private final TestChoiceSelector selector = new TestChoiceSelector();

    public TestCharacter() {
        addChoiceList(selector);
    }

    public TestChoiceSelector getSelector() {
        return selector;
    }

    public TestCharacter withScores(int value) {
        AbilityScore.SCORES.forEach(as -> addAttribute(new AbilityScore(as, value)));
        return this;
    }

    public void setScore(AttributeType score, int value) {
        getAttribute(score, IntAttribute.class).setValue(value);
    }

    public void setLevel(int level) {
        if (hasAttribute(AttributeType.LEVEL))
            getAttribute(AttributeType.LEVEL, IntAttribute.class).setValue(level);
        else
            addAttribute(new IntAttribute(AttributeType.LEVEL, level));
    }

    public void selectChoice(String choice, String option) {
        selector.withChoice(option);
        selectChoice(choice);
    }

    public void selectChoice(String choice) {
        for (int i = 0; i < getChoiceCount(); i++) {
            OptionChoice optionChoice = getChoice(i);
            if (optionChoice.getName().equals(choice)) {
                selectChoice(optionChoice);
                return;
            }
        }
        throw new IllegalArgumentException("No choice " + choice);
    }

    public boolean hadOption(Option option) {
        return selector.hadOption(option);
    }
}
