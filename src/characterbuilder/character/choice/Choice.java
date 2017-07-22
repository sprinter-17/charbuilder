package characterbuilder.character.choice;

import characterbuilder.character.Character;

public interface Choice {

    void makeChoice(Character character, ChoiceSelector selector);

    default boolean isAutomatic() {
        return false;
    }
}
