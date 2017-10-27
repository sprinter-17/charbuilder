package characterbuilder.character.equipment;

import characterbuilder.character.attribute.Value;
import characterbuilder.character.attribute.Weight;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class EquipmentSet implements Equipment {

    private final Equipment equipment;
    private final int count;

    protected EquipmentSet(Equipment equipment, int count) {
        if (count <= 0)
            throw new IllegalArgumentException("Equipment must have count >= 1");
        this.equipment = equipment;
        this.count = count;
    }

    @Override
    public EquipmentSet makeSet(int count) {
        return new EquipmentSet(equipment, count * this.count);
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public int getBonus() {
        return equipment.getBonus();
    }

    @Override
    public EquipmentCategory getCategory() {
        return equipment.getCategory();
    }

    @Override
    public Optional<MagicItem> asMagicItem() {
        return equipment.asMagicItem();
    }

    @Override
    public Optional<Weapon> asWeapon() {
        return equipment.asWeapon();
    }

    @Override
    public Equipment getBaseEquipment() {
        return equipment.getBaseEquipment();
    }

    public boolean matches(EquipmentSet other) {
        return this.equipment.equals(other.equipment);
    }

    public EquipmentSet add(EquipmentSet other) {
        if (!matches(other))
            throw new IllegalArgumentException("Attempt to add mismatched item");
        else
            return new EquipmentSet(equipment, this.count + other.count);
    }

    public Optional<EquipmentSet> substract(EquipmentSet other) {
        if (!matches(other))
            throw new IllegalArgumentException("Attempt to subtract mismatched item");
        else if (this.count < other.count)
            throw new IllegalArgumentException("Attempt to subtract item with greater count");
        else if (this.count == other.count)
            return Optional.empty();
        else
            return Optional.of(new EquipmentSet(equipment, this.count - other.count));
    }

    public Value getValue() {
        return equipment.getValue().times(count);
    }

    public Weight getWeight() {
        return equipment.getWeight().times(count);
    }

    @Override
    public Element save(Document doc) {
        Element element = equipment.save(doc);
        if (count != 1)
            element.setAttribute("count", String.valueOf(count));
        return element;
    }

    public static EquipmentSet load(Equipment equipment, Element element) {
        int count = 1;
        if (element.hasAttribute("count"))
            count = Integer.valueOf(element.getAttribute("count"));
        return new EquipmentSet(equipment, count);
    }

    public static EquipmentSet decode(String code) {
        final Pattern PATTERN = Pattern.compile("(\\d+)x(\\w+)\\+(\\d+)");
        Matcher matcher = PATTERN.matcher(code);
        if (matcher.matches()) {
            AdventureGear type = AdventureGear.valueOf(matcher.group(2));
            int count = Integer.valueOf(matcher.group(1));
            return new EquipmentSet(type, count);
        } else {
            throw new IllegalStateException("Decoding illegal equipment code " + code);
        }
    }

    @Override
    public String toString() {
        if (count > 1)
            return count + " " + equipment.toString() + "s";
        else
            return equipment.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.equipment);
        hash = 71 * hash + this.count;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        final EquipmentSet other = (EquipmentSet) obj;
        return this.count == other.count
            && this.equipment.equals(other.equipment);
    }

}
