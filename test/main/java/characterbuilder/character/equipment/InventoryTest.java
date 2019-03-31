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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InventoryTest {

	private Inventory inventory;

	@BeforeEach
	public void setup() {
		inventory = new Inventory();
	}

	@Test
	public void testAddItem() {
		inventory.addItem(TRIDENT.makeSet(1));
		assertThat(inventory.getItemCount(), is(1));
		inventory.addItem(CLUB.makeSet(1));
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
		inventory.addItem(ABACUS.makeSet(7));
		assertThat(inventory.getItemCount(), is(7));
	}

	@Test
	public void testRemoveNonExistentItem() {
		assertThrows(IllegalArgumentException.class, () -> inventory.removeItem(TRIDENT.makeSet(1)));
	}

	@Test
	public void testRemoveItem() {
		inventory.addItem(AMULET.makeSet(1));
		inventory.removeItem(AMULET.makeSet(1));
		assertThat(inventory.getItemCount(), is(0));
		inventory.addItem(AMULET.makeSet(5));
		inventory.removeItem(AMULET.makeSet(2));
		assertThat(inventory.getItemCount(), is(3));
	}

	@Test
	public void testContainsItem() {
		assertFalse(inventory.containsItem(BATTLEAXE));
		inventory.addItem(BATTLEAXE.makeSet(1));
		assertTrue(inventory.containsItem(BATTLEAXE));
	}

	@Test
	public void testGetValue() {
		assertThat(inventory.getValue(), is(Value.ZERO));
		inventory.addItem(COPPER_PIECE.makeSet(1));
		assertThat(inventory.getValue(), is(Value.CP));
		inventory.addItem(COPPER_PIECE.makeSet(5));
		assertThat(inventory.getValue(), is(Value.CP.times(6)));
	}

	@Test
	public void testOnlyTreasureHasValue() {
		inventory.addItem(GREATSWORD.makeSet(1));
		assertThat(inventory.getValue(), is(Value.ZERO));
	}

	@Test
	public void testGetWeight() {
		inventory.addItem(BATTLEAXE.makeSet(4));
		assertThat(inventory.getWeight(), is(BATTLEAXE.getWeight().times(4)));
	}

	@Test
	public void testGetWeapons() {
		inventory.addItem(BATTLEAXE.makeSet(1));
		assertThat(inventory.getWeapons().count(), is(1L));
	}
}
