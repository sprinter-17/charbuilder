package characterbuilder.character.ability;

import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.equipment.Weapon;
import java.util.Optional;

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
    public String encode() {
        return weapon.encode();
    }

    @Override
    public String toString() {
        return weapon.toString();
    }
}
