package characterbuilder.character.equipment;

import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.Value;
import static characterbuilder.character.attribute.Value.gp;
import characterbuilder.character.attribute.Weight;
import static characterbuilder.character.attribute.Weight.lb;
import static characterbuilder.character.equipment.EquipmentCategory.MUSICAL_INSTRUMENT;
import static characterbuilder.character.saveload.Savable.child;
import characterbuilder.utils.StringUtils;
import java.util.Arrays;
import java.util.stream.Stream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public enum MusicalInstrument implements Equipment {
    BAGPIPES(gp(30), lb(6)),
    DRUM(gp(6), lb(3)),
    DULCIMER(gp(25), lb(10)),
    FLUTE(gp(2), lb(1)),
    LUTE(gp(35), lb(2)),
    LYRE(gp(30), lb(2)),
    HORN(gp(3), lb(2)),
    PAN_FLUTE(gp(12), lb(2)),
    SHAWM(gp(2), lb(1)),
    VIOL(gp(30), lb(1)),;

    private final Value cost;
    private final Weight weight;
    private final Attribute proficiency;

    private class MusicalInstrumentProficiency implements Attribute {

        @Override
        public AttributeType getType() {
            return AttributeType.MUSICAL_INSTRUMENT_PROFICIENCY;
        }

        @Override
        public Element save(Document doc) {
            Element element = getType().save(doc);
            element.appendChild(doc.createElement("instrument")).setTextContent(name());
            return element;
        }

        @Override
        public String toString() {
            return MusicalInstrument.this.toString();
        }
    }

    private MusicalInstrument(Value cost, Weight weight) {
        this.cost = cost;
        this.weight = weight;
        this.proficiency = new MusicalInstrumentProficiency();
    }

    @Override
    public EquipmentCategory getCategory() {
        return MUSICAL_INSTRUMENT;
    }

    @Override
    public Weight getWeight() {
        return weight;
    }

    @Override
    public Value getValue() {
        return cost;
    }

    public Attribute getProficiency() {
        return proficiency;
    }

    public static Stream<Attribute> getAllProficiencies() {
        return Arrays.stream(values()).map(MusicalInstrument::getProficiency);
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    public static MusicalInstrument load(Node node) {
        return MusicalInstrument.valueOf(node.getTextContent());
    }

    public static Attribute loadProficiency(Node node) {
        return MusicalInstrument.valueOf(child(node).getTextContent()).proficiency;
    }
}
