package characterbuilder.character.equipment;

import characterbuilder.character.attribute.Value;
import characterbuilder.character.attribute.Weight;
import static characterbuilder.character.equipment.AdventureGear.GOLD_PIECE;
import static characterbuilder.character.equipment.AdventureGear.SILVER_PIECE;
import static java.util.stream.Collectors.joining;
import static org.hamcrest.CoreMatchers.is;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TreasureSpenderTest {

	private Inventory inventory;

	@BeforeEach
	public void setup() {
		inventory = new Inventory();
	}

	@Test
	public void testSpendTooMuchTreasure() {
		inventory.addItem(SILVER_PIECE);
		assertThrows(IllegalArgumentException.class, () -> inventory.spendTreasure(Value.GP));
	}

	@Test
	public void testSpendExactMatchTreasure() {
		inventory.addItem(SILVER_PIECE);
		inventory.spendTreasure(Value.SP);
		assertThat(inventory.getTreasureValue(), is(Value.ZERO));
	}

	@Test
	public void testSpendPartOfTreasure() {
		inventory.addItem(new EquipmentSet(SILVER_PIECE, 5));
		inventory.spendTreasure(Value.SP);
		assertThat(inventory.getTreasureValue(), is(Value.SP.times(4)));
	}

	@Test
	public void testSpendWithChange() {
		inventory.addItem(GOLD_PIECE);
		inventory.spendTreasure(Value.SP.times(7));
		assertThat(inventory.getTreasureValue(), is(Value.SP.times(3)));
	}

	@Test
	public void testIgnoreZeroValueTreasure() {
		Equipment treasure = new CustomTreasure("Treasure", Value.ZERO, Weight.ZERO);
		inventory.addItem(GOLD_PIECE);
		inventory.addItem(treasure);
		inventory.spendTreasure(Value.sp(7));
		assertThat(inventory.getTreasureValue(), is(Value.sp(3)));
		assertTrue(inventory.containsItem(treasure));
	}

	@Test
	public void testSpendCustomTreasure() {
		Equipment treasure = new CustomTreasure("Treasure", Value.GP, Weight.ZERO);
		inventory.addItem(treasure);
		inventory.spendTreasure(Value.sp(7));
		assertThat(inventory.getTreasureValue(), is(Value.sp(3)));
		assertFalse(inventory.containsItem(treasure));
	}

	@Test
	public void testSpendLowestValueTreasureByUnit() {
		inventory.addItem(new EquipmentSet(SILVER_PIECE, 100));
		inventory.addItem(new EquipmentSet(GOLD_PIECE, 5));
		inventory.spendTreasure(Value.gp(1));
		assertThat(inventory, containsTreasure(SILVER_PIECE, 90));
		assertThat(inventory, containsTreasure(GOLD_PIECE, 5));
	}

	private Matcher<Inventory> containsTreasure(AdventureGear coin, int amount) {
		return new TypeSafeDiagnosingMatcher<Inventory>() {
			@Override
			protected boolean matchesSafely(Inventory t, Description d) {
				d.appendText("contains").appendValue(t.getEquipment().map(Equipment::toString)
						.collect(joining(",")));
				EquipmentSet set = new EquipmentSet(coin, amount);
				return t.getEquipment().anyMatch(set::equals);
			}

			@Override
			public void describeTo(Description d) {
				d.appendText("contains ").appendValue(amount).appendText("x").appendValue(coin);
			}
		};
	}
}
