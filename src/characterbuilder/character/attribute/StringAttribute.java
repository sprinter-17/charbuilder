package characterbuilder.character.attribute;

import java.util.Objects;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

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
    public String toString() {
        return value;
    }

    @Override
    public Node save(Document doc) {
        Node node = getType().save(doc);
        node.setTextContent(value);
        return node;
    }

    public static StringAttribute load(AttributeType type, Node node) {
        return new StringAttribute(type, node.getTextContent());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.type);
        hash = 37 * hash + Objects.hashCode(this.value);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        StringAttribute other = (StringAttribute) obj;
        return other.type.equals(type) && other.value.equals(value);
    }

}
