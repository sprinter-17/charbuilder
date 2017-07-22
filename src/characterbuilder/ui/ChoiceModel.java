package characterbuilder.ui;

import characterbuilder.character.choice.Choice;
import characterbuilder.character.choice.ChoiceList;
import java.util.Optional;
import javax.swing.AbstractListModel;

public class ChoiceModel extends AbstractListModel<Choice> {

    private Optional<ChoiceList> choices = Optional.empty();

    public void setChoices(ChoiceList choices) {
        this.choices = Optional.of(choices);
    }

    @Override
    public int getSize() {
        return choices.map(ChoiceList::size).orElse(0);
    }

    @Override
    public Choice getElementAt(int index) {
        return choices.get().getChoice(index);
    }

    public void update() {
        super.fireContentsChanged(this, 0, getSize() - 1);
    }
}
