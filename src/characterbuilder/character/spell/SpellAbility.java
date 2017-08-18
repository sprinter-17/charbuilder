package characterbuilder.character.spell;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.saveload.Savable.text;
import java.util.stream.Stream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class SpellAbility implements Attribute {

    private final Spell spell;
    private final AttributeType abilityScore;

    public SpellAbility(Spell spell, AttributeType abilityScore) {
        this.spell = spell;
        this.abilityScore = abilityScore;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.SPELL_ABILITY;
    }

    public Spell getSpell() {
        return spell;
    }

    @Override
    public Element save(Document doc) {
        Element element = getType().save(doc);
        element.setAttribute("ability_score", abilityScore.name());
        element.setTextContent(spell.name());
        return element;
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return spell.getDescription(character);
    }

    public static SpellAbility load(Node node) {
        Spell spell = Spell.valueOf(text(node));
        AttributeType abilityScore = AttributeType.valueOf(((Element) node)
            .getAttribute("ability_score"));
        return new SpellAbility(spell, abilityScore);
    }

    @Override
    public String toString() {
        return spell.toString();
    }

    @Override
    public int hashCode() {
        return spell.hashCode() + abilityScore.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        final SpellAbility other = (SpellAbility) obj;
        return other.spell == spell && other.abilityScore == abilityScore;
    }

}
