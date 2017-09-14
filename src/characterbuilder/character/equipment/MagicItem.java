package characterbuilder.character.equipment;

import characterbuilder.character.attribute.Value;
import characterbuilder.character.attribute.Weight;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MagicItem implements Equipment {

    private final Equipment baseItem;
    private final int bonus;
    private final List<String> abilities = new ArrayList<>();

    public MagicItem(Equipment baseItem, int bonus) {
        this.baseItem = baseItem;
        this.bonus = bonus;
    }

    @Override
    public int getBonus() {
        return bonus;
    }

    @Override
    public Equipment getBaseEquipment() {
        return baseItem;
    }

    @Override
    public EquipmentCategory getCategory() {
        return baseItem.getCategory();
    }

    @Override
    public Weight getWeight() {
        return baseItem.getWeight();
    }

    @Override
    public Value getValue() {
        return baseItem.getValue();
    }

    @Override
    public Optional<Weapon> asWeapon() {
        return baseItem.asWeapon();
    }
}
