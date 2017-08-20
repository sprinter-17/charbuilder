package characterbuilder.character.equipment;

import characterbuilder.character.attribute.Value;
import characterbuilder.character.attribute.Weight;
import static characterbuilder.character.equipment.AdventureGear.SILVER_PIECE;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Inventory {

    private final List<Equipment> items = new ArrayList<>();

    public int getItemCount() {
        return items.stream().mapToInt(Equipment::getCount).sum();
    }

    public void addItem(Equipment equipment) {
        equipment.getComponents().forEach(this::addItemComponent);
    }

    private void addItemComponent(Equipment equipment) {
        Optional<Equipment> match = items.stream()
            .filter(eq -> matches(eq, equipment))
            .findAny();
        if (match.isPresent()) {
            items.remove(match.get());
            items.add(add(match.get(), equipment));
        } else {
            items.add(equipment);
        }
    }

    public void removeItem(Equipment equipment) {
        Optional<Equipment> match = items.stream()
            .filter(it -> matches(it, equipment) && it.getCount() >= equipment.getCount())
            .findAny();
        if (match.isPresent()) {
            items.remove(match.get());
            substract(match.get(), equipment).ifPresent(items::add);
        } else {
            throw new IllegalArgumentException("Attempt to remove non-existing item");
        }
    }

    private boolean matches(Equipment eq1, Equipment eq2) {
        return eq1.getBaseEquipment().equals(eq2.getBaseEquipment())
            && eq1.getBonus() == eq2.getBonus();
    }

    private Equipment add(Equipment eq1, Equipment eq2) {
        return new EquipmentSet(eq1.getBaseEquipment(), eq1.getBonus(),
            eq1.getCount() + eq2.getCount());
    }

    private Optional<Equipment> substract(Equipment eq1, Equipment eq2) {
        if (eq1.getCount() > eq2.getCount())
            return Optional.of(new EquipmentSet(eq1.getBaseEquipment(), eq1.getBonus(),
                eq1.getCount() - eq2.getCount()));
        else
            return Optional.empty();
    }

    public boolean containsItem(Equipment equipment) {
        return getEquipment().anyMatch(eq -> eq.getBaseEquipment().equals(equipment));
    }

    public Stream<Equipment> getEquipment() {
        return items.stream();
    }

    public Stream<Weapon> getWeapons() {
        return items.stream().map(Equipment::asWeapon)
            .filter(Optional::isPresent).map(Optional::get);
    }

    public Value getTreasureValue() {
        return getTreasure()
            .map(Equipment::getValue)
            .reduce(Value.ZERO, Value::add);
    }

    public void spendTreasure(Value value) {
        if (value.isGreaterThan(getTreasureValue()))
            throw new IllegalArgumentException("Attempt to spend more than total treasure");
        while (value.isGreaterThan(Value.ZERO)) {
            Equipment treasure = getTreasure()
                .sorted(Comparator.comparing(Equipment::getValue, Value::compareTo))
                .findAny().get();
            removeItem(treasure.getBaseEquipment());
            if (treasure.getBaseEquipment().getValue().isGreaterThan(value)) {
                if (treasure.getBaseEquipment().equals(AdventureGear.GOLD_PIECE)) {
                    addItem(new EquipmentSet(SILVER_PIECE, 10));
                } else if (treasure.getBaseEquipment().equals(AdventureGear.SILVER_PIECE)) {
                    addItem(new EquipmentSet(AdventureGear.COPPER_PIECE, 10));
                } else {
                    throw new IllegalStateException("Incorrect change calculation");
                }
            } else {
                value = value.subtract(treasure.getBaseEquipment().getValue());
            }
        }
    }

    private Stream<Equipment> getTreasure() {
        return items.stream()
            .filter(eq -> eq.getCategory().equals(EquipmentCategory.TREASURE));
    }

    public Value getValue() {
        return items.stream()
            .filter(eq -> eq.getCategory().equals(EquipmentCategory.TREASURE)
            || eq.getCategory().equals(EquipmentCategory.CUSTOM_TREASURE))
            .map(Equipment::getValue).reduce(Value.ZERO, Value::add);
    }

    public Weight getWeight() {
        return items.stream().map(Equipment::getWeight).reduce(Weight.ZERO, Weight::add);
    }
}
