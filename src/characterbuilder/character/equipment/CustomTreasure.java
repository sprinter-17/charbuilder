package characterbuilder.character.equipment;

import characterbuilder.character.attribute.Value;
import characterbuilder.character.attribute.Weight;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomTreasure implements Equipment {

    private final String description;
    private final Value value;
    private final Weight weight;

    public CustomTreasure(String description, Value value, Weight weight) {
        this.description = description;
        this.value = value;
        this.weight = weight;
    }

    @Override
    public EquipmentCategory getCategory() {
        return EquipmentCategory.TREASURE;
    }

    @Override
    public Weight getWeight() {
        return weight;
    }

    @Override
    public Value getValue() {
        return value;
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public String encode() {
        return "CUSTOM" + description + "VAL" + value.toString() + "WT" + weight.encode();
    }

    public static CustomTreasure decode(String code) {
        final Pattern pattern = Pattern.compile("CUSTOM(.*)VAL(.*)WT(.*)");
        Matcher matcher = pattern.matcher(code);
        if (matcher.matches()) {
            try {
                String description = matcher.group(1);
                Value value = Value.valueOf(matcher.group(2));
                Weight weight = Weight.decode(matcher.group(3));
                return new CustomTreasure(description, value, weight);
            } catch (Value.ValueFormatException ex) {
                throw new IllegalArgumentException(ex);
            }
        }
        throw new IllegalArgumentException(code + " is not a valid custom treasure encoding");
    }

    @Override
    public int hashCode() {
        return 61 * 3 + Objects.hashCode(this.description);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        final CustomTreasure other = (CustomTreasure) obj;
        return this.description.equals(other.description)
            && this.value.equals(other.value)
            && this.weight.equals(other.weight);
    }

}
