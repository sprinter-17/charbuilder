package characterbuilder.character.choice;

import characterbuilder.character.Character;
import java.util.Optional;

public interface Option {

    void choose(Character character);

    default Optional<String> getDescription(Character character) {
        return Optional.empty();
    }
}
