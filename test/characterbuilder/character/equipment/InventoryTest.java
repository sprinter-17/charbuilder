package characterbuilder.character.equipment;

import characterbuilder.character.attribute.Value;
import static characterbuilder.character.equipment.AdventureGear.*;
import static characterbuilder.character.equipment.Weapon.BATTLEAXE;
import static characterbuilder.character.equipment.Weapon.CLUB;
import static characterbuilder.character.equipment.Weapon.GREATSWORD;
import static characterbuilder.character.equipment.Weapon.TRIDENT;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class InventoryTest {

    private Inventory inventory;

    @Before
    public void setup() {
        inventory = new Inventory();
    }

    @Test
    public void testAddItem() {
        inventory.addItem(new EquipmentSet(TRIDENT));
        assertThat(inventory.getItemCount(), is(1));
        inventory.addItem(new EquipmentSet(CLUB));
        assertThat(inventory.getItemCount(), is(2));
        assertTrue(inventory.containsItem(TRIDENT));
        assertTrue(inventory.containsItem(CLUB));
    }

    @Test
    public void testAddPack() {
        inventory.addItem(EquipmentPack.EXPLORER_PACK);
        assertTrue(inventory.containsItem(BEDROLL));
        assertTrue(inventory.containsItem(TINDERBOX));
    }

    @Test
    public void testAddItemWithCount() {
        inventory.addItem(new EquipmentSet(ABACUS, 0, 7));
        assertThat(inventory.getItemCount(), is(7));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonExistentItem() {
        inventory.removeItem(new EquipmentSet(TRIDENT, 0, 1));
    }

    @Test
    public void testRemoveItem() {
        inventory.addItem(new EquipmentSet(AMULET));
        inventory.removeItem(new EquipmentSet(AMULET));
        assertThat(inventory.getItemCount(), is(0));
        inventory.addItem(new EquipmentSet(AMULET, 0, 5));
        inventory.removeItem(new EquipmentSet(AMULET, 0, 2));
        assertThat(inventory.getItemCount(), is(3));
    }

    @Test
    public void testContainsItem() {
        assertFalse(inventory.containsItem(BATTLEAXE));
        inventory.addItem(new EquipmentSet(BATTLEAXE));
        assertTrue(inventory.containsItem(BATTLEAXE));
    }

    @Test
    public void testGetValue() {
        assertThat(inventory.getValue(), is(Value.ZERO));
        inventory.addItem(new EquipmentSet(COPPER_PIECE));
        assertThat(inventory.getValue(), is(Value.CP));
        inventory.addItem(new EquipmentSet(COPPER_PIECE, 0, 5));
        assertThat(inventory.getValue(), is(Value.CP.times(6)));
    }

    @Test
    public void testOnlyTreasureHasValue() {
        inventory.addItem(new EquipmentSet(GREATSWORD));
        assertThat(inventory.getValue(), is(Value.ZERO));
    }

    @Test
    public void testGetWeight() {
        inventory.addItem(new EquipmentSet(BATTLEAXE, 0, 4));
        assertThat(inventory.getWeight(), is(BATTLEAXE.getWeight().times(4)));
    }

    @Test
    public void testGetWeapons() {
        inventory.addItem(new EquipmentSet(BATTLEAXE, 3, 1));
        assertThat(inventory.getWeapons().count(), is(1L));
    }
}
