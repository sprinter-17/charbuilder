package characterbuilder.character.equipment;

import static characterbuilder.character.equipment.AdventureGear.AMULET;
import characterbuilder.character.saveload.TestDoc;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class EquipmentSetTest {

    private final int count = 7;
    private EquipmentSet set;

    @Before
    public void setup() {
        set = new EquipmentSet(AdventureGear.BARREL, count);
    }

    @Test
    public void testGetCount() {
        assertThat(set.getCount(), is(count));
    }

    @Test
    public void testToString() {
        assertThat(set.toString(), is("7 Barrels"));
    }

    @Test
    public void testMakeSetMultipliesCount() {
        assertThat(set.makeSet(2).getCount(), is(14));
    }

    @Test
    public void testGetCategory() {
        assertThat(set.getCategory(), is(EquipmentCategory.CONTAINER));
    }

    @Test
    public void testGetBaseEquipment() {
        assertThat(set.getBaseEquipment(), is(AdventureGear.BARREL));
    }

    @Test
    public void testGetBaseEquipmentForSetOfMagicItems() {
        assertThat(new MagicItem(AMULET, 2).makeSet(15).getBaseEquipment(), is(AMULET));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddMismatchedEquipment() {
        set.add(AMULET.makeSet(2));
    }

    @Test
    public void testAdd() {
        set = set.add(AdventureGear.BARREL.makeSet(4));
        assertThat(set.getCount(), is(count + 4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSubtractMismatchedEquipment() {
        set.substract(AdventureGear.BELL.makeSet(6));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSubtractTooManyElements() {
        set.substract(AdventureGear.BARREL.makeSet(count + 1)).get();
    }

    @Test
    public void testSubstract() {
        set = set.substract(AdventureGear.BARREL.makeSet(3)).get();
        assertThat(set.getCount(), is(count - 3));
        assertFalse(set.substract(AdventureGear.BARREL.makeSet(count - 3)).isPresent());
    }

    @Test
    public void testGetValue() {
        assertThat(set.getValue(), is(AdventureGear.BARREL.getValue().times(7)));
    }

    @Test
    public void testGetBonus() {
        set = new MagicItem(AdventureGear.AMULET, 4).makeSet(3);
        assertThat(set.getBonus(), is(4));
    }

    @Test
    public void testGetWeight() {
        assertThat(set.getWeight(), is(AdventureGear.BARREL.getWeight().times(7)));
    }

    @Test
    public void testSaveAndLoad() {
        assertThat(EquipmentCategory.load(set.save(TestDoc.doc())), is(set));
    }
}
