package characterbuilder.character.attribute;

import static characterbuilder.character.attribute.AttributeType.*;
import java.util.Arrays;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class AbilityScore extends IntAttribute {

    private boolean proficientSaves = false;
    private int max = 20;

    public static final List<AttributeType> SCORES = Arrays.asList(
        STRENGTH, DEXTERITY, CONSTITUTION, INTELLIGENCE, WISDOM, CHARISMA);

    public AbilityScore(AttributeType type, int value) {
        super(type, value);
        if (!SCORES.contains(type))
            throw new IllegalArgumentException("Illegal ability score " + type);
    }

    public void setMax(int max) {
        this.max = max;
    }

    public boolean isAtMax() {
        return getValue() == max;
    }

    @Override
    public void addValue(int delta) {
        super.addValue(delta);
        if (getValue() > max)
            super.setValue(max);
    }

    @Override
    public void setValue(int value) {
        super.setValue(Math.min(value, max));
    }

    public void setProficientSaves() {
        this.proficientSaves = true;
    }

    public boolean hasProficientSaves() {
        return proficientSaves;
    }

    @Override
    public Element save(Document doc) {
        Element element = getType().save(doc);
        element.setTextContent(String.valueOf(getValue()));
        if (proficientSaves)
            element.setAttribute("proficient", "y");
        if (max != 20)
            element.setAttribute("max", String.valueOf(max));
        return element;
    }

    public static AbilityScore load(AttributeType type, Node node) {
        AbilityScore score = new AbilityScore(type, Integer.valueOf(node.getTextContent()));
        if (((Element) node).getAttribute("proficient").equals("y"))
            score.setProficientSaves();
        if (((Element) node).hasAttribute("max"))
            score.setMax(Integer.valueOf(((Element) node).getAttribute("max")));
        return score;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        final AbilityScore other = (AbilityScore) obj;
        return other.getType().equals(getType())
            && other.getValue() == getValue()
            && other.proficientSaves == proficientSaves
            && other.max == max;
    }

}
