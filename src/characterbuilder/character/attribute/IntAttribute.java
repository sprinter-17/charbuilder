package characterbuilder.character.attribute;

public class IntAttribute implements Attribute {

    private final AttributeType type;
    private int value;

    public IntAttribute(AttributeType type, int value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public AttributeType getType() {
        return type;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void addValue(int delta) {
        this.value += delta;
    }

    public int getValue() {
        return value;
    }

    public void setInRange(int min, int max) {
        value = Math.max(min, value);
        value = Math.min(max, value);
    }

    @Override
    public String encode() {
        return toString();
    }

    public static IntAttribute decode(AttributeType type, String value) {
        return new IntAttribute(type, Integer.valueOf(value));
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean isVisible() {
        return false;
    }
}
