package characterbuilder.character.choice;

import characterbuilder.character.Character;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ChoiceList {

    private final ChoiceSelector selector;
    private final List<OptionChoice> choices = new ArrayList<>();

    public ChoiceList(ChoiceSelector selector) {
        this.selector = selector;
    }

    public void add(OptionChoice choice) {
        choices.add(choice);
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
        choice.select(character, selector.withAction(() -> {
            if (choice.useAndCheck())
                choices.remove(choice);
        }));
    }
}
