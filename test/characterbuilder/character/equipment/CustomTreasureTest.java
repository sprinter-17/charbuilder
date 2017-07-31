package characterbuilder.character.equipment;

import static characterbuilder.character.attribute.Value.gp;
import characterbuilder.character.attribute.Weight;
import characterbuilder.character.saveload.TestDoc;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class CustomTreasureTest {

    private CustomTreasure treasure;

    @Before
    public void setup() {
        treasure = new CustomTreasure("Large golden statue", gp(100), Weight.ST);
    }

    @Test
    public void testGetCategory() {
        assertThat(treasure.getCategory(), is(EquipmentCategory.CUSTOM_TREASURE));
    }

    @Test
    public void testGetWeight() {
        assertThat(treasure.getWeight(), is(Weight.ST));
    }

    @Test
    public void testGetValue() {
        assertThat(treasure.getValue(), is(gp(100)));
    }

    @Test
    public void testToString() {
        assertThat(treasure.toString(), is("Large golden statue"));
    }

    @Test
    public void testSaveLoad() {
        assertThat(EquipmentCategory.load(treasure.save(TestDoc.doc())), is(treasure));
    }

    @Test
    public void testCustomTreasureSet() {
        EquipmentSet set = new EquipmentSet(treasure, 17);
        assertThat(set.getBaseEquipment(), is(treasure));
        assertThat(set.getValue(), is(gp(1700)));
        assertThat(set.toString(), is("17 Large golden statues"));
        set = new EquipmentSet(treasure, 1);
        assertThat(set.toString(), is("Large golden statue"));
    }
}
