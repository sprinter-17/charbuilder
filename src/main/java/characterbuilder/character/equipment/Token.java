package characterbuilder.character.equipment;

import characterbuilder.character.attribute.Value;
import characterbuilder.character.attribute.Weight;
import static characterbuilder.character.saveload.Savable.child;
import java.util.Objects;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Token implements Equipment {

    private final String name;

    public Token(String name) {
        this.name = name;
    }

    @Override
    public EquipmentCategory getCategory() {
        return EquipmentCategory.TOKEN;
    }

    @Override
    public Weight getWeight() {
        return Weight.ZERO;
    }

    @Override
    public Value getValue() {
        return Value.ZERO;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Element save(Document doc) {
        Element element = getCategory().save(doc);
        element.appendChild(doc.createElement("description")).setTextContent(name);
        return element;
    }

    public static Token load(Node node) {
        return new Token(child(node).getTextContent());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        else
            return ((Token) obj).name.equals(name);
    }

}
