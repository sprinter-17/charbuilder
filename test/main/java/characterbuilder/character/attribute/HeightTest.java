package characterbuilder.character.attribute;

import static characterbuilder.character.attribute.Height.FOOT;
import characterbuilder.character.attribute.Height.HeightFormatException;
import static characterbuilder.character.attribute.Height.INCH;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

public class HeightTest {

    @Test
    public void testCopy() {
        Height original = Height.FOOT.times(5);
        assertThat(original.copy(), is(original));
    }

    @Test
    public void testValueOf() throws HeightFormatException {
        assertThat(Height.valueOf("5' 2\""), is(FOOT.times(5).add(INCH.times(2))));
    }

    @Test
    public void testToString() {
        assertThat(Height.INCH.times(30).toString(), is("2' 6\""));
    }

    @Test
    public void testCompare() {
        assertTrue(Height.FOOT.compareTo(Height.INCH.times(11)) > 0);
        assertTrue(Height.FOOT.compareTo(Height.INCH.times(12)) == 0);
        assertTrue(Height.FOOT.compareTo(Height.INCH.times(13)) < 0);
    }
}
