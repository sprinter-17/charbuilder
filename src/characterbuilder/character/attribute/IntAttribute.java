package characterbuilder.character.attribute;

import java.util.Objects;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

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

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public Element save(Document doc) {
        Element element = getType().save(doc);
        element.setTextContent(String.valueOf(value));
        return element;
    }

    public static IntAttribute load(AttributeType type, Node node) {
        return new IntAttribute(type, Integer.valueOf(node.getTextContent()));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.type);
        hash = 23 * hash + this.value;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null
            && getClass() == obj.getClass()
            && ((IntAttribute) obj).type.equals(type)
            && ((IntAttribute) obj).value == value;
    }
}
