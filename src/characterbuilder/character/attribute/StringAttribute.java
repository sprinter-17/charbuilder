package characterbuilder.character.attribute;

public class StringAttribute implements Attribute {

    private final AttributeType type;
    private String value;

    public StringAttribute(AttributeType type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public AttributeType getType() {
        return type;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String encode() {
        return toString();
    }

    @Override
    public String toString() {
        return value;
    }
}
