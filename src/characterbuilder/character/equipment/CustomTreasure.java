package characterbuilder.character.equipment;

import characterbuilder.character.attribute.Value;
import characterbuilder.character.attribute.Weight;
import static characterbuilder.character.saveload.Savable.text;
import java.util.Objects;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

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
        return EquipmentCategory.CUSTOM_TREASURE;
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
    public Element save(Document doc) {
        Element element = getCategory().save(doc);
        element.appendChild(value.save(doc));
        element.appendChild(weight.save(doc));
        element.appendChild(doc.createElement("description")).setTextContent(description);
        return element;
    }

    public static CustomTreasure load(EquipmentCategory category, Node node) {
        Element element = (Element) node;
        Node valueNode = element.getFirstChild();
        Node weightNode = valueNode.getNextSibling();
        Node descNode = weightNode.getNextSibling();
        return new CustomTreasure(text(descNode), Value.load(valueNode), Weight.load(weightNode));
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
