package characterbuilder.character.equipment;

import characterbuilder.character.attribute.Value;
import characterbuilder.character.attribute.Weight;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Inventory {

    private final List<Equipment> items = new ArrayList<>();
    private final TreasureSpender spender;

    public Inventory() {
        this.spender = new TreasureSpender(this);
    }

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
        return new EquipmentSet(eq1.getBaseEquipment(), eq1.getCount() + eq2.getCount());
    }

    private Optional<Equipment> substract(Equipment eq1, Equipment eq2) {
        if (eq1.getCount() > eq2.getCount())
            return Optional.of(new EquipmentSet(eq1.getBaseEquipment(),
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
        return spender.getValue();
    }

    public void spendTreasure(Value value) {
        spender.spend(value);
    }

    public Value getValue() {
        return getTreasureValue();
    }

    public Weight getWeight() {
        return items.stream().map(Equipment::getWeight).reduce(Weight.ZERO, Weight::add);
    }

    public void copyFrom(Inventory other) {
        items.addAll(other.items);
    }
}
