package characterbuilder.character;

import java.util.stream.IntStream;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CharacterRandomTest {

	private final CharacterRandom random = new CharacterRandom();

	@Test
	public void testIllegalRoll() {
		assertThrows(IllegalArgumentException.class, () -> random.roll("4d11"));
	}

	@Test
	public void testFixedValue() {
		assertThat(random.roll("5"), is(5));
	}

	@Test
	public void testRange() {
		assertTrue(IntStream.range(0, 10000).map(i -> random.roll("4d4"))
				.allMatch(i -> i >= 4 && i <= 16));
	}

}
