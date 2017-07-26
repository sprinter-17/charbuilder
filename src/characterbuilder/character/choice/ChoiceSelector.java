package characterbuilder.character.choice;

import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.equipment.Equipment;
import java.util.function.Consumer;
import java.util.stream.Stream;

public interface ChoiceSelector {

    void getName(Consumer<String> consumer);

    void getAttribute(Stream<Attribute> attributes, Consumer<Attribute> consumer);

    void getEquipment(Stream<Equipment> equipments, Consumer<Equipment> consumer);

    void generateAbilityScores(Consumer<Stream<Attribute>> consumer);

    default void choiceMade() {

    }
}
