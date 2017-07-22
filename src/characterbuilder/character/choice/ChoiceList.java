package characterbuilder.character.choice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ChoiceList {

    private final List<Choice> choices = new ArrayList<>();

    public void addChoice(Choice choice) {
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

    public Choice getChoice(int index) {
        return choices.get(index);
    }

    public Stream<Choice> getChoices() {
        return choices.stream();
    }

    public void removeChoice(Choice choice) {
        choices.remove(choice);
    }

    public boolean hasChoice(Choice choice) {
        return choices.contains(choice);
    }

}
