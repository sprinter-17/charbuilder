package characterbuilder.attribute;

import characterbuilder.character.attribute.Height;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

public class HeightTest {

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
