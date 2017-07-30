package characterbuilder.character.choice;

import characterbuilder.character.Character;

public abstract class OptionChoice implements Choice {

    private int initialCount;
    private int currentCount;

    public OptionChoice() {
        this(1);
    }

    public OptionChoice(int count) {
        this.initialCount = count;
        this.currentCount = count;
    }

    public OptionChoice withCount(int count) {
        this.initialCount = count;
        this.currentCount = count;
        return this;
    }

    public boolean useAndCheck() {
        return --currentCount <= 0;
    }

    @Override
    public void addTo(Character character) {
        this.currentCount = initialCount;
        character.addChoice(this);
    }

    public String toStringWithCount() {
        if (initialCount > 1)
            return toString() + " (x" + currentCount + ")";
        else
            return toString();
    }

    public abstract void select(Character character, ChoiceSelector selector);

}
