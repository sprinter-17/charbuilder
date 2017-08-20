package characterbuilder.character.equipment;

import characterbuilder.character.attribute.Value;
import characterbuilder.character.attribute.Weight;

public interface EquipmentType {

    EquipmentCategory getCategory();

    Value getValue();

    Weight getWeight();
}
