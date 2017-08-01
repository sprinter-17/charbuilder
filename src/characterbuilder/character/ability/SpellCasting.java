package characterbuilder.character.ability;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class SpellCasting implements Attribute {

    private final AttributeType spellAbilityScore;

    public SpellCasting(AttributeType spellAbilityScore) {
        this.spellAbilityScore = spellAbilityScore;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.SPELLCASTING;
    }

    @Override
    public String toString() {
        return "Spell casting using " + spellAbilityScore.toString();
    }

    public int getSaveDC(Character character) {
        return 8 + getModifier(character);
    }

    public int getModifier(Character character) {
        return character.getProficiencyBonus() + character.getModifier(spellAbilityScore);
    }

    @Override
    public Node save(Document doc) {
        Node node = getType().save(doc);
        node.setTextContent(spellAbilityScore.name());
        return node;
    }

    public static SpellCasting load(Node node) {
        return new SpellCasting(AttributeType.valueOf(node.getTextContent()));
    }

    @Override
    public int hashCode() {
        return spellAbilityScore.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        else
            return ((SpellCasting) obj).spellAbilityScore.equals(spellAbilityScore);
    }

}
