package characterbuilder.character.equipment;

import static characterbuilder.character.equipment.Weapon.MACE;
import characterbuilder.character.saveload.TestDoc;
import characterbuilder.utils.TestCharacter;
import java.util.Optional;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class MagicItemTest {

    private MagicItem item;

    @Before
    public void setup() {
        item = new MagicItem(MACE, 3);
    }

    @Test
    public void testAsMagicItem() {
        assertThat(item.asMagicItem(), is(Optional.of(item)));
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

    @Test
    public void testToString() {
        assertThat(item.toString(), is("+3 Mace"));
        assertThat(new MagicItem(MACE, -2).toString(), is("-2 Mace"));
        assertThat(new MagicItem(MACE, 0).toString(), is("Mace"));
    }

    @Test
    public void testCustomName() {
        item.setName("Foobar");
        assertThat(item.toString(), is("+3 Foobar"));
    }

    @Test
    public void testAbilitiesWithExpressionInDescription() {
        item.setAbility("Foo [3*4]");
        assertTrue(item.getDescription(new TestCharacter()).anyMatch("Foo 12"::equals));
    }

    @Test
    public void testSaveAndLoad() {
        item.setName("Foo");
        item.setAbility("Bar");
        assertThat(EquipmentCategory.load(item.save(TestDoc.doc())), is(item));
    }
}
