package characterbuilder.character.ability;

import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Expertise implements Attribute {

    private final Attribute attribute;

    public Expertise(Attribute attribute) {
        this.attribute = attribute;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.EXPERTISE;
    }

    public boolean isFor(Attribute attribute) {
        return this.attribute.equals(attribute);
    }

    @Override
    public String toString() {
        return "Expertise in " + attribute.toString();
    }

    @Override
    public Node save(Document doc) {
        Element element = getType().save(doc);
        element.appendChild(attribute.save(doc));
        return element;
    }

    public static Expertise load(AttributeType type, Node node) {
        return new Expertise(AttributeType.load(node.getFirstChild()));
    }

    @Override
    public int hashCode() {
        return attribute.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null
            && getClass() == obj.getClass()
            && this.attribute.equals(((Expertise) obj).attribute);
    }

}
