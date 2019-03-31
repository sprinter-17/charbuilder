package characterbuilder.character.attribute;

import characterbuilder.character.ability.Proficiency;
import static characterbuilder.character.ability.Proficiency.ALL_WEAPONS;
import static characterbuilder.character.attribute.AttributeType.STRENGTH;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AttributeSetTest {

	private AttributeSet attributes;

	@BeforeEach
	public void setup() {
		attributes = new AttributeSet();
	}

	@Test
	public void testEmptyAttributeSet() {
		assertThat(attributes.getAllAttributes().count(), is(0L));
	}

	@Test
	public void testAddAttribute() {
		Attribute attribute = new IntAttribute(STRENGTH, 10);
		attributes.addAttribute(attribute);
		assertThat(attributes.getAllAttributes().count(), is(1L));
		assertTrue(attributes.getAllAttributes().anyMatch(attribute::equals));
	}

	@Test
	public void testHasAttribute() {
		assertFalse(attributes.hasAttribute(STRENGTH));
		attributes.addAttribute(new IntAttribute(STRENGTH, 10));
		assertTrue(attributes.hasAttribute(STRENGTH));
	}

	@Test
	public void testHasSuperSetAttribute() {
		attributes.addAttribute(ALL_WEAPONS);
		assertTrue(attributes.hasAttribute(Proficiency.ALL_SIMPLE_WEAPONS));
		assertTrue(attributes.hasAttribute(Proficiency.ALL_SIMPLE_MELEE));
	}

	@Test
	public void testGetNonExistentAttribute() {
		assertThat(attributes.getAttribute(STRENGTH), is(AttributeSet.NULL_ATTRIBUTE));
	}

	@Test
	public void testGetAttribute() {
		IntAttribute strength = new IntAttribute(STRENGTH, 10);
		strength.setValue(17);
		attributes.addAttribute(strength);
		assertThat(attributes.getAttribute(STRENGTH).toString(), is("17"));
	}

	@Test
	public void testAddUniqueAttributeTwice() {
		attributes.addAttribute(new IntAttribute(STRENGTH, 10));
		assertThrows(IllegalStateException.class, () -> attributes.addAttribute(new IntAttribute(STRENGTH, 10)));
	}
}
