package characterbuilder.character.equipment;

import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.Value;
import characterbuilder.character.attribute.Weight;
import characterbuilder.character.saveload.TestDoc;
import java.util.Optional;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

public class WeaponTest {

    @Test
    public void testGetCategory() {
        assertThat(Weapon.BLOWGUN.getCategory(), is(EquipmentCategory.MARTIAL_RANGED));
    }

    @Test
    public void testAsWeapon() {
        assertThat(Weapon.CLUB.asWeapon(), is(Optional.of(Weapon.CLUB)));
    }

    @Test
    public void testGetAttacks() {
//        assertThat(Weapon.FLAIL.getAttacks(character))
    }

    @Test
    public void testGetWeight() {
        assertThat(Weapon.DAGGER.getWeight(), is(Weight.LB));
    }

    @Test
    public void testGetValue() {
        assertThat(Weapon.GREATSWORD.getValue(), is(Value.gp(50)));
    }

    @Test
    public void testGetProficiency() {
        assertThat(Weapon.DART.getProficiency().getType(), is(AttributeType.WEAPON_PROFICIENCY));
    }

    @Test
    public void testSaveAndLoad() {
        assertThat(EquipmentCategory.load(Weapon.HAND_CROSSBOW.save(TestDoc.doc())),
            is(Weapon.HAND_CROSSBOW));
    }
}
