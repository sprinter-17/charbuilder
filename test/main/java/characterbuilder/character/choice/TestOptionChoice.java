package characterbuilder.character.choice;

import characterbuilder.character.Character;
import java.util.function.Consumer;

public class TestOptionChoice extends OptionChoice {

    private final Consumer<Character> action;
    private boolean allowed = true;

    public TestOptionChoice() {
        this(ch -> {
        });
    }

    public TestOptionChoice(Consumer<Character> action) {
        super("Test");
        this.action = action;
    }

    @Override
    public void select(Character character, ChoiceSelector selector) {
        action.accept(character);
        selector.choiceMade();
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }

    @Override
    public boolean isAllowed(Character character) {
        return allowed;
    }

}
