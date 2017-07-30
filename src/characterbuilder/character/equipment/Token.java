package characterbuilder.character.equipment;

import characterbuilder.character.attribute.Value;
import characterbuilder.character.attribute.Weight;

public class Token implements Equipment {

    private final String name;

    public Token(String name) {
        this.name = name;
    }

    @Override
    public EquipmentCategory getCategory() {
        return EquipmentCategory.TOKEN;
    }

    @Override
    public Weight getWeight() {
        return Weight.ZERO;
    }

    @Override
    public Value getValue() {
        return Value.ZERO;
    }

    @Override
    public String encode() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}
