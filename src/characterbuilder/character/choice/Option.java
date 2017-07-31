package characterbuilder.character.choice;

import characterbuilder.character.Character;
import characterbuilder.character.saveload.Savable;
import java.util.stream.Stream;

public interface Option extends Savable {

    void choose(Character character);

    default Stream<String> getDescription(Character character) {
        return Stream.empty();
    }
}
