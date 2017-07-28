package characterbuilder.character.choice;

import characterbuilder.character.Character;

public abstract class OptionChoice implements Choice {

    private int count;

    public OptionChoice() {
        this(1);
    }

    public OptionChoice(int count) {
        this.count = count;
    }

    public OptionChoice withCount(int count) {
        this.count = count;
        return this;
    }

    public boolean useAndCheck() {
        return --count <= 0;
    }

    @Override
    public void addTo(Character character) {
        character.addChoice(this);
    }

    public abstract void select(Character character, ChoiceSelector selector);

}
