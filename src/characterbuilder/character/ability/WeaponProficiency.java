package characterbuilder.character.ability;

import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.equipment.Weapon;
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
        return new WeaponProficiency(Weapon.load(null, node.getFirstChild()));
    }

    @Override
    public String toString() {
        return weapon.toString();
    }
}
