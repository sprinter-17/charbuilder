package characterbuilder.ui.choice;

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
    		return character.filter(Character::hasChoices)
    				.map(ch -> ch.getAllowedChoices().count()).orElse(0L).intValue();
    }

    @Override
    public OptionChoice getElementAt(int index) {
        return character.get().getAllowedChoices().skip(index)
        		.findFirst().orElseThrow(IndexOutOfBoundsException::new);
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
