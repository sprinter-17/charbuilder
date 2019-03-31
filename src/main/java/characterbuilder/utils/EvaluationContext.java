package characterbuilder.utils;

import characterbuilder.character.Character;
import characterbuilder.character.spell.LearntSpell;
import java.util.Optional;

public class EvaluationContext {

    private Optional<Character> character = Optional.empty();
    private Optional<LearntSpell> spell = Optional.empty();
    private boolean plural = false;

    public Optional<Character> getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = Optional.of(character);
    }

    public LearntSpell getSpell() {
        return spell.orElseThrow(
            () -> new IllegalStateException("Attempt to get non-existent spell evaluation context"));
    }

    public void setSpell(LearntSpell spell) {
        this.spell = Optional.of(spell);
    }

    public boolean isPlural() {
        return plural;
    }

    public void setPlural(boolean plural) {
        this.plural = plural;
    }

}
