package characterbuilder.character.choice;

import characterbuilder.character.Character;

public abstract class OptionChoice implements Choice {

    private final String name;
    private int initialCount;
    private int currentCount;

    public OptionChoice(String name) {
        this(name, 1);
    }

    public OptionChoice(String name, int count) {
        this.name = name;
        this.initialCount = count;
        this.currentCount = count;
    }

    public OptionChoice withCount(int count) {
        this.initialCount = count;
        this.currentCount = count;
        return this;
    }

    public void addCount(int count) {
        this.currentCount += count;
    }

    public boolean useAndCheck() {
        return --currentCount <= 0;
    }

    @Override
    public void addTo(Character character) {
        this.currentCount = initialCount;
        character.addChoice(this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        if (initialCount > 1 || currentCount > 1)
            return getName() + " (x" + currentCount + ")";
        else
            return getName();
    }

    public abstract void select(Character character, ChoiceSelector selector);

}
