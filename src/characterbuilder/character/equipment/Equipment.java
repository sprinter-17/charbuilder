package characterbuilder.character.equipment;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Value;
import characterbuilder.character.attribute.Weight;
import characterbuilder.character.choice.Option;
import java.text.NumberFormat;
import java.util.Optional;
import java.util.stream.Stream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public interface Equipment extends Option {

    default Stream<Equipment> getComponents() {
        return Stream.of(this);
    }

    EquipmentCategory getCategory();

    default Equipment getBaseEquipment() {
        return this;
    }

    default int getCount() {
        return 1;
    }

    default int getBonus() {
        return 0;
    }

    default Optional<Weapon> asWeapon() {
        return Optional.empty();
    }

    @Override
    public default void choose(Character character) {
        character.addEquipment(this);
    }

    Weight getWeight();

    Value getValue();

    public default String toString(int count, int bonus) {
        StringBuilder builder = new StringBuilder();
        if (count > 1)
            builder.append(NumberFormat.getInstance().format(count)).append(" ");
        if (bonus != 0)
            builder.append(String.format("%+d ", bonus));
        builder.append(toString());
        if (count > 1)
            builder.append("s");
        return builder.toString();
    }

    public default Stream<String> getDescription(Character character) {
        return Stream.of("Cost " + getValue().toString() + " Weight " + getWeight().toString());
    }

    @Override
    public default Element save(Document doc) {
        if (this instanceof Enum) {
            Element element = doc.createElement(getCategory().name().toLowerCase());
            element.setTextContent(((Enum) this).name());
            return element;
        } else {
            throw new AbstractMethodError("object " + getClass().getName() + " does not have save");
        }
    }
}
