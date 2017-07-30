package characterbuilder.ui;

import characterbuilder.character.Character;
import characterbuilder.character.choice.OptionChoice;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import javax.swing.AbstractListModel;

public class ChoiceModel extends AbstractListModel<OptionChoice> {

    private Optional<Character> character = Optional.empty();

    public void setCharacter(Character character) {
        this.character = Optional.of(character);
        update();
    }

    @Override
    public int getSize() {
        return character.map(Character::getChoiceCount).orElse(0);
    }

    @Override
    public OptionChoice getElementAt(int index) {
        return character.get().getChoice(index);
    }

    public OptionalInt indexOf(OptionChoice choice) {
        return IntStream.range(0, getSize())
            .filter(i -> getElementAt(i).equals(choice))
            .findFirst();
    }

    public void update() {
        super.fireContentsChanged(this, 0, getSize() - 1);
    }

    public void select(int index) {
        character.get().selectChoice(getElementAt(index));
    }
}
