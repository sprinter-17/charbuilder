package characterbuilder.character.equipment;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Value;
import characterbuilder.character.attribute.Weight;
import characterbuilder.character.choice.Option;
import java.util.Optional;
import java.util.stream.Stream;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

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

    @Override
    public default Node save(Document doc) {
        if (this instanceof Enum) {
            Node node = doc.createElement(getCategory().name().toLowerCase());
            node.setTextContent(((Enum) this).name());
            return node;
        } else {
            throw new AbstractMethodError("object " + getClass().getName() + " does not have save");
        }
    }
}
