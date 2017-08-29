package characterbuilder.character.choice;

import characterbuilder.character.Character;
import java.util.Deque;
import java.util.LinkedList;

public class UndoQueue {

    private final Deque<Element> queue = new LinkedList<>();

    public class Element {

        private final Character character;
        private final OptionChoice choice;
        private final int choicePosition;

        public Element(Character character, OptionChoice choice, int choicePosition) {
            this.character = character.copy();
            this.choice = choice;
            this.choicePosition = choicePosition;
        }

        private void restore() {
            if (choice.unUseAndCheck())
                character.addChoice(choicePosition, choice);
        }

        public Character getCharacter() {
            return character;
        }

        public OptionChoice getChoice() {
            return choice;
        }
    }

    public void add(Character character, OptionChoice choice, int choicePosition) {
        queue.addFirst(new Element(character, choice, choicePosition));
    }

    public boolean canUndo() {
        return !queue.isEmpty();
    }

    public Element undo() {
        queue.peekFirst().restore();
        return queue.removeFirst();
    }
}
