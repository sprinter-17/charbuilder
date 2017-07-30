package characterbuilder.character.choice;

import characterbuilder.character.Character;
import java.util.stream.Stream;

public interface Option {

    void choose(Character character);

    default Stream<String> getDescription(Character character) {
        return Stream.empty();
    }
}
