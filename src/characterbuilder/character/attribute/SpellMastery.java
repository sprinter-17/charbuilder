package characterbuilder.character.attribute;

import characterbuilder.character.spell.Spell;
import static characterbuilder.character.saveload.Savable.child;
import static characterbuilder.character.saveload.Savable.text;
import java.util.Objects;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class SpellMastery implements Attribute {

    private final String name;
    private final Spell spell;

    public SpellMastery(String name, Spell spell) {
        this.name = name;
        this.spell = spell;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.SPELL_MASTERY;
    }

    @Override
    public String toString() {
        return name + " " + spell.toString();
    }

    @Override
    public Node save(Document doc) {
        Node node = getType().save(doc);
        node.appendChild(doc.createTextNode(name));
        node.appendChild(spell.save(doc));
        return node;
    }

    public static SpellMastery load(AttributeType type, Node node) {
        return new SpellMastery(text(node), Spell.load(child(node)));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.name);
        hash = 61 * hash + Objects.hashCode(this.spell);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null
            && obj.getClass() == getClass()
            && ((SpellMastery) obj).name.equals(name)
            && ((SpellMastery) obj).spell.equals(spell);
    }

}
