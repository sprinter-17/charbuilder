package characterbuilder.character.choice;

import characterbuilder.character.Character;
import java.util.function.Consumer;

public class TestOptionChoice extends OptionChoice {

    private final Consumer<Character> action;

    public TestOptionChoice(Consumer<Character> action) {
        this.action = action;
    }

    @Override
    public void select(Character character, ChoiceSelector selector) {
        action.accept(character);
        selector.choiceMade();
    }

}
