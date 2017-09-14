package characterbuilder.character.equipment;

import static characterbuilder.character.equipment.Weapon.MACE;
import java.util.Optional;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class MagicItemTest {

    private MagicItem item;

    @Before
    public void setup() {
        item = new MagicItem(MACE, 3);
    }

    @Test
    public void testBaseEquipment() {
        assertThat(item.getBaseEquipment(), is(MACE));
    }

    @Test
    public void testCategory() {
        assertThat(item.getCategory(), is(MACE.getCategory()));
    }

    @Test
    public void testWeight() {
        assertThat(item.getWeight(), is(MACE.getWeight()));
    }

    @Test
    public void testValue() {
        assertThat(item.getValue(), is(MACE.getValue()));
    }

    @Test
    public void testBonus() {
        assertThat(item.getBonus(), is(3));
    }

    @Test
    public void testAsWeapon() {
        assertThat(item.asWeapon(), is(Optional.of(MACE)));
    }
}
