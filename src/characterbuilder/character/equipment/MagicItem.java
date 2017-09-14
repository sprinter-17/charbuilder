package characterbuilder.character.equipment;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Value;
import characterbuilder.character.attribute.Weight;
import characterbuilder.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class MagicItem implements Equipment {

    private final Equipment baseItem;
    private final int bonus;
    private final List<String> abilities = new ArrayList<>();

    public MagicItem(Equipment baseItem, int bonus) {
        this.baseItem = baseItem;
        this.bonus = bonus;
    }

    public void addAbility(String ability) {
        this.abilities.add(ability);
    }

    @Override
    public Equipment getBaseEquipment() {
        return baseItem;
    }

    @Override
    public int getBonus() {
        return bonus;
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

    public Stream<String> getAbilities(Character character) {
        return abilities.stream().map(ab -> StringUtils.expand(ab, character));
    }

    @Override
    public String toString() {
        if (bonus != 0)
            return String.format("%+d ", bonus) + baseItem.toString();
        else
            return baseItem.toString();
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return Stream.concat(Equipment.super.getDescription(character), getAbilities(character));
    }
}
