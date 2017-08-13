package characterbuilder.character.characterclass;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.DamageType;
import java.util.stream.Stream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ElementalAdept implements Attribute {

    private final DamageType type;

    public ElementalAdept(DamageType type) {
        this.type = type;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.ELEMENTAL_ADEPT;
    }

    @Override
    public String toString() {
        return type.toString() + " Element Adept";
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return Stream.of("For spells causing " + type.toString() + " damage "
            + "ignore resistance to that damage and treat 1s as 2s on damage dice");
    }

    @Override
    public Element save(Document doc) {
        Element element = getType().save(doc);
        element.setTextContent(type.name());
        return element;
    }

    public static ElementalAdept load(Node node) {
        return new ElementalAdept(DamageType.valueOf(node.getTextContent()));
    }

    @Override
    public int hashCode() {
        return type.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        final ElementalAdept other = (ElementalAdept) obj;
        return other.type == this.type;
    }
}
