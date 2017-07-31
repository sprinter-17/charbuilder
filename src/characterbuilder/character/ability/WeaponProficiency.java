package characterbuilder.character.ability;

import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.equipment.Weapon;
import static characterbuilder.character.saveload.Savable.child;
import java.util.Optional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class WeaponProficiency implements Attribute {

    private final Weapon weapon;

    public WeaponProficiency(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.WEAPON_PROFICIENCY;
    }

    @Override
    public Optional<Attribute> getSuperSet() {
        return weapon.getCategory().getProficiency();
    }

    @Override
    public Node save(Document doc) {
        Element element = getType().save(doc);
        element.appendChild(weapon.save(doc));
        return element;
    }

    public static WeaponProficiency load(Node node) {
        return new WeaponProficiency(Weapon.load(child(node)));
    }

    @Override
    public String toString() {
        return weapon.toString();
    }

    @Override
    public int hashCode() {
        return weapon.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        final WeaponProficiency other = (WeaponProficiency) obj;
        return this.weapon.equals(other.weapon);
    }

}
