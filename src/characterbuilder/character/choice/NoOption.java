package characterbuilder.character.choice;

import characterbuilder.character.Character;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class NoOption implements Option {

    public static final NoOption NONE = new NoOption();

    private NoOption() {
    }

    @Override
    public void choose(Character character) {
        // do not do anything when chosen
    }

    @Override
    public String toString() {
        return "None";
    }

    @Override
    public Node save(Document doc) {
        throw new UnsupportedOperationException("No options cannot be saved.");
    }

}
