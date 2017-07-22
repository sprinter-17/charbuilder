package characterbuilder.character.choice;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.equipment.Equipment;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class MultiChoice implements Choice {

    private final Choice choice;
    private int count;

    public MultiChoice(int count, Choice choice) {
        this.count = count;
        this.choice = choice;
    }

    @Override
    public void makeChoice(Character character, ChoiceSelector selector) {
        choice.makeChoice(character, new ChoiceSelector() {
            @Override
            public void getName(Consumer<String> consumer) {
                selector.getName(name -> {
                    consumer.accept(name);
                    remove(character);
                });
            }

            @Override
            public void getAttribute(Stream<Attribute> attributes, Consumer<Attribute> consumer) {
                selector.getAttribute(attributes, att -> {
                    consumer.accept(att);
                    remove(character);
                });
            }

            @Override
            public void getEquipment(Stream<Equipment> equipment, Consumer<Equipment> consumer) {
                selector.getEquipment(equipment, eq -> {
                    consumer.accept(eq);
                    remove(character);
                });
            }

        });
    }

    private void remove(Character character) {
        count--;
        if (count == 0)
            character.getChoices().removeChoice(this);
    }

    @Override
    public boolean isAutomatic() {
        return choice.isAutomatic();
    }

    @Override
    public String toString() {
        if (count > 1)
            return count + "x " + choice.toString();
        else
            return choice.toString();
    }
}
