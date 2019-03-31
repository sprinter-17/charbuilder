package characterbuilder.character.equipment;

import characterbuilder.character.attribute.Value;
import static characterbuilder.character.equipment.AdventureGear.COPPER_PIECE;
import static characterbuilder.character.equipment.AdventureGear.GOLD_PIECE;
import static characterbuilder.character.equipment.AdventureGear.SILVER_PIECE;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

public class TreasureSpender {

    private final Inventory inventory;

    public TreasureSpender(Inventory inventory) {
        this.inventory = inventory;
    }

    public Value getValue() {
        return getTreasure().reduce(Value.ZERO, (v, t) -> v.add(t.getValue()), Value::add);
    }

    public void spend(Value value) {
        if (value.isGreaterThan(inventory.getTreasureValue()))
            throw new IllegalArgumentException("Attempt to spend more than total treasure");
        while (value.isGreaterThan(Value.ZERO)) {
            value = spendOrChangeOneUnitOfLowestValueTreasure(value);
        }

    }

    private Value spendOrChangeOneUnitOfLowestValueTreasure(Value amountToSpend) {
        Equipment treasure = getLowestValueTreasure().orElseThrow(IllegalStateException::new);
        Equipment unit = treasure.getBaseEquipment();
        inventory.removeItem(unit);
        if (unit.getValue().isGreaterThan(amountToSpend)) {
            changeCoin(unit);
        } else {
            amountToSpend = amountToSpend.subtract(unit.getValue());
        }
        return amountToSpend;
    }

    private void changeCoin(Equipment unit) throws IllegalStateException {
        final AdventureGear[] coins = {GOLD_PIECE, SILVER_PIECE, COPPER_PIECE};
        Value value = unit.getValue();
        for (int i = 0; i < coins.length; i++) {
            AdventureGear coin = coins[i];
            while (value.isGreaterThan(coin.getValue())) {
                inventory.addItem(coin);
                value = value.subtract(coin.getValue());
            }
            if (value.equals(coin.getValue())) {
                if (coin == COPPER_PIECE)
                    throw new IllegalStateException("Attempt to change copper piece");
                inventory.addItem(new EquipmentSet(coins[i + 1], 10));
                value = value.subtract(coin.getValue());
            }
        }
    }

    private Optional<Equipment> getLowestValueTreasure() {
        return getTreasure()
            .filter(eq -> eq.getValue().isGreaterThan(Value.ZERO))
            .sorted(Comparator.comparing(eq -> eq.getBaseEquipment().getValue(), Value::compareTo))
            .findAny();
    }

    private Stream<Equipment> getTreasure() {
        return inventory.getEquipment().filter(eq -> eq.getCategory().isTreasure());
    }

}
