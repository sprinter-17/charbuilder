package characterbuilder.utils;

import characterbuilder.character.Character;
import java.util.Optional;

public class EvaluationContext {

    private Optional<Character> character = Optional.empty();
    private boolean plural = false;

    public Optional<Character> getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = Optional.of(character);
    }

    public boolean isPlural() {
        return plural;
    }

    public void setPlural(boolean plural) {
        this.plural = plural;
    }

}
