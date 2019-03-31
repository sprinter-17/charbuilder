package characterbuilder.character.choice;

import characterbuilder.character.Character;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ChoiceList {

    private final ChoiceSelector selector;
    private final List<OptionChoice> choices = new ArrayList<>();
    private UndoQueue undoQueue = new UndoQueue();

    public ChoiceList(ChoiceSelector selector) {
        this.selector = selector;
    }

    public ChoiceList copy() {
        ChoiceList copy = new ChoiceList(selector);
        copy.choices.addAll(choices);
        copy.undoQueue = undoQueue;
        return copy;
    }

    public void add(OptionChoice choice) {
        if (!choices.contains(choice))
            choices.add(choice);
    }

    public void add(int index, OptionChoice choice) {
        if (!choices.contains(choice))
            choices.add(index, choice);
    }

    public boolean isEmpty() {
        return choices.isEmpty();
    }

    public int size() {
        return choices.size();
    }

    public void clear() {
        choices.clear();
    }

    public OptionChoice get(int index) {
        return choices.get(index);
    }

    public Stream<OptionChoice> stream() {
        return choices.stream();
    }

    public boolean has(OptionChoice choice) {
        return choices.contains(choice);
    }

    public void select(Character character, OptionChoice choice) {
        choice.select(character, selector.withActions(
            () -> preAction(character, choice), () -> postAction(choice)));
    }

    private void preAction(Character character, OptionChoice choice) {
        undoQueue.add(character, choice, choices.indexOf(choice));
    }

    private void postAction(OptionChoice choice) {
        if (choice.useAndCheck())
            choices.remove(choice);
    }

    public boolean canUndo() {
        return undoQueue.canUndo();
    }

    public UndoQueue.Element undo() {
        return undoQueue.undo();
    }
}
