package characterbuilder.character.equipment;

import characterbuilder.character.attribute.Value;
import characterbuilder.character.attribute.Weight;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

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
        return equipment.getValue().times(count);
    }

    public Weight getWeight() {
        return equipment.getWeight().times(count);
    }

    @Override
    public Node save(Document doc) {
        Element element = (Element) equipment.save(doc);
        element.setAttribute("bonus", String.valueOf(bonus));
        element.setAttribute("count", String.valueOf(count));
        return element;
    }

    public static EquipmentSet load(Equipment equipment, Element element) {
        int bonus = Integer.valueOf(element.getAttribute("bonus"));
        int count = Integer.valueOf(element.getAttribute("count"));
        return new EquipmentSet(equipment, bonus, count);
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

    @Override
    public String toString() {
        if (count == 1)
            return equipment.toString();
        else
            return count + " " + equipment.toString() + "s";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.equipment);
        hash = 71 * hash + this.bonus;
        hash = 71 * hash + this.count;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        final EquipmentSet other = (EquipmentSet) obj;
        return this.bonus == other.bonus
            && this.count == other.count
            && this.equipment.equals(other.equipment);
    }

}
