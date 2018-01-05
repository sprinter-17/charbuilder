package characterbuilder.character.choice;

import characterbuilder.character.Character;

public interface Choice {

    String getName();

    void addTo(Character character);

    default boolean isAllowed(Character character) {
        return true;
    }
}
