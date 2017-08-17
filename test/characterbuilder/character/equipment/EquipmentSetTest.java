package characterbuilder.character.equipment;

import characterbuilder.character.saveload.TestDoc;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class EquipmentSetTest {

    private final int count = 7;
    private final int bonus = 3;
    private EquipmentSet set;

    @Before
    public void setup() {
        set = new EquipmentSet(EquipmentType.BARREL, bonus, count);
    }

    @Test
    public void testGetCount() {
        assertThat(set.getCount(), is(count));
    }

    @Test
    public void testGetBonus() {
        assertThat(set.getBonus(), is(bonus));
    }

    @Test
    public void testToString() {
        assertThat(set.toString(), is("7 +3 Barrels"));
    }

    @Test
    public void testGetCategory() {
        assertThat(set.getCategory(), is(EquipmentCategory.CONTAINER));
    }

    @Test
    public void testGetBaseEquipment() {
        assertThat(set.getBaseEquipment(), is(EquipmentType.BARREL));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddMismatchedEquipment() {
        set.add(new EquipmentSet(EquipmentType.AMULET, 2, 17));
    }

    @Test
    public void testAdd() {
        set = set.add(new EquipmentSet(EquipmentType.BARREL, bonus, 4));
        assertThat(set.getCount(), is(count + 4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSubtractMismatchedEquipment() {
        set.substract(new EquipmentSet(EquipmentType.BELL, 4, 6));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSubtractTooManyElements() {
        set.substract(new EquipmentSet(EquipmentType.BARREL, bonus, count + 1)).get();
    }

    @Test
    public void testSubstract() {
        set = set.substract(new EquipmentSet(EquipmentType.BARREL, bonus, 3)).get();
        assertThat(set.getCount(), is(count - 3));
        assertFalse(set.substract(
            new EquipmentSet(EquipmentType.BARREL, bonus, count - 3)).isPresent());
    }

    @Test
    public void testGetValue() {
        assertThat(set.getValue(), is(EquipmentType.BARREL.getValue().times(7)));
    }

    @Test
    public void testGetWeight() {
        assertThat(set.getWeight(), is(EquipmentType.BARREL.getWeight().times(7)));
    }

    @Test
    public void testSaveAndLoad() {
        assertThat(EquipmentCategory.load(set.save(TestDoc.doc())), is(set));
    }
}
