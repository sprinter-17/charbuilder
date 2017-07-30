package characterbuilder.character.equipment;

import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.Value;
import static characterbuilder.character.attribute.Value.gp;
import characterbuilder.character.attribute.Weight;
import static characterbuilder.character.attribute.Weight.lb;
import static characterbuilder.character.equipment.EquipmentCategory.MUSICAL_INSTRUMENTS;
import characterbuilder.utils.StringUtils;
import java.util.Arrays;
import java.util.stream.Stream;

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
            return AttributeType.MUSICAL_INSTRUMENT;
        }

        @Override
        public String encode() {
            return MusicalInstrument.this.encode();
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
        return MUSICAL_INSTRUMENTS;
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
    public String encode() {
        return name();
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }
}
