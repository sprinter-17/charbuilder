package characterbuilder.character.choice;

import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.equipment.Equipment;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class TestChoiceSelector implements ChoiceSelector {

    private Optional<Attribute> attribute = Optional.empty();

    public TestChoiceSelector withAttribute(Attribute attribute) {
        this.attribute = Optional.of(attribute);
        return this;
    }

    @Override
    public void getName(Consumer<String> consumer) {
        consumer.accept("Fred");
        choiceMade();
    }

    @Override
    public void getAttribute(Stream<Attribute> attributes, Consumer<Attribute> consumer) {
        if (attribute.isPresent())
            consumer.accept(attribute.get());
        else
            consumer.accept(attributes.findFirst().get());
        choiceMade();
    }

    @Override
    public void getEquipment(Stream<Equipment> equipment, Consumer<Equipment> consumer) {
        consumer.accept(equipment.findFirst().get());
    }

    @Override
    public void generateAbilityScores(Consumer<Stream<Attribute>> consumer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
