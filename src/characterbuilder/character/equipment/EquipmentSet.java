package characterbuilder.character.equipment;

import characterbuilder.character.attribute.Value;
import characterbuilder.character.attribute.Weight;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EquipmentSet implements Equipment {

    private final Equipment equipment;
    private final int bonus;
    private final int count;

    public EquipmentSet(Equipment equipment) {
        this(equipment, 0, 1);
    }

    public EquipmentSet(Equipment equipment, int count) {
        this(equipment, 0, count);
    }

    public EquipmentSet(Equipment equipment, int bonus, int count) {
        if (count <= 0)
            throw new IllegalArgumentException("Equipment must have count >= 1");
        this.equipment = equipment;
        this.bonus = bonus;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public int getBonus() {
        return bonus;
    }

    public EquipmentCategory getCategory() {
        return equipment.getCategory();
    }

    @Override
    public Optional<Weapon> asWeapon() {
        return equipment.asWeapon();
    }

    @Override
    public Equipment getBaseEquipment() {
        return equipment;
    }

    public boolean matches(EquipmentSet other) {
        return this.equipment.equals(other.equipment) && this.bonus == other.bonus;
    }

    public EquipmentSet add(EquipmentSet other) {
        if (!matches(other))
            throw new IllegalArgumentException("Attempt to add mismatched item");
        else
            return new EquipmentSet(equipment, bonus, this.count + other.count);
    }

    public Optional<EquipmentSet> substract(EquipmentSet other) {
        if (!matches(other))
            throw new IllegalArgumentException("Attempt to subtract mismatched item");
        else if (this.count < other.count)
            throw new IllegalArgumentException("Attempt to subtract item with greater count");
        else if (this.count == other.count)
            return Optional.empty();
        else
            return Optional.of(new EquipmentSet(equipment, bonus, this.count - other.count));
    }

    public Value getValue() {
        if (equipment.getCategory().equals(EquipmentCategory.TREASURE))
            return equipment.getValue().times(count);
        else
            return Value.ZERO;
    }

    public Weight getWeight() {
        return equipment.getWeight().times(count);
    }

    public String encode() {
        return equipment.encode();
    }

    public static EquipmentSet decode(String code) {
        final Pattern PATTERN = Pattern.compile("(\\d+)x(\\w+)\\+(\\d+)");
        Matcher matcher = PATTERN.matcher(code);
        if (matcher.matches()) {
            EquipmentType type = EquipmentType.valueOf(matcher.group(2));
            int count = Integer.valueOf(matcher.group(1));
            int bonus = Integer.valueOf(matcher.group(3));
            return new EquipmentSet(type, bonus, count);
        } else {
            throw new IllegalStateException("Decoding illegal equipment code " + code);
        }
    }

    public String toString() {
        if (count == 1)
            return equipment.toString();
        else
            return count + " " + equipment.toString() + "s";
    }

}
