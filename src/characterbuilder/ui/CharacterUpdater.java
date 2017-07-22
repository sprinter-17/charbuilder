package characterbuilder.ui;

import characterbuilder.character.Character;

public interface CharacterUpdater {

    void update(Character character);

    void triggerChange();
}
