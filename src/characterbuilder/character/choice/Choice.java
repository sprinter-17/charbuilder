package characterbuilder.character.choice;

import characterbuilder.character.Character;
import java.util.function.Consumer;

public interface Choice {

    String getName();

    void addTo(Character character);

    default boolean isAllowed(Character character) {
        return true;
    }

    static Choice action(String name, Consumer<Character> action) {
        return new Choice() {
            @Override
            public String getName() {
                return name;
            }

            @Override
            public void addTo(Character character) {
                action.accept(character);
            }
        };
    }
}
