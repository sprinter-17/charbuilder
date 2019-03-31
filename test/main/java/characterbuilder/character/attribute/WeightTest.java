package characterbuilder.character.attribute;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.jupiter.api.Test;

public class WeightTest {

	@Test
	public void testIllegalConversionWithTrailingSpace() throws Weight.WeightFormatException {
		assertThrows(Weight.WeightFormatException.class, () -> Weight.valueOf("Fred"));
	}

	@Test
	public void testConversion() throws Weight.WeightFormatException {
		assertThat(Weight.valueOf("2st"), is(Weight.ST.times(2)));
		assertThat(Weight.valueOf("15st"), is(Weight.ST.times(15)));
		assertThat(Weight.valueOf("7lb"), is(Weight.LB.times(7)));
		assertThat(Weight.valueOf("1oz"), is(Weight.OZ.times(1)));
		assertThat(Weight.valueOf("1st 7lb"), is(Weight.ST.times(1.5f)));
	}

	@Test
	public void testToStringInPounds() {
		assertThat(Weight.st(2).toStringInPounds(), is("28lb"));
	}
}
