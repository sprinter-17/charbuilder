package characterbuilder.character.equipment;

import characterbuilder.character.attribute.Value;
import characterbuilder.character.attribute.Weight;
import java.util.Optional;
import java.util.stream.Stream;

public interface Equipment {

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

    Weight getWeight();

    Value getValue();

    String encode();
}
