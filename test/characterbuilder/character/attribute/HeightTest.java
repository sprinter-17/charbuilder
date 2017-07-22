package characterbuilder.character.attribute;

import static characterbuilder.character.attribute.Height.FOOT;
import characterbuilder.character.attribute.Height.HeightFormatException;
import static characterbuilder.character.attribute.Height.INCH;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class HeightTest {

    @Test
    public void testValueOf() throws HeightFormatException {
        assertThat(Height.valueOf("5' 2\""), is(FOOT.times(5).add(INCH.times(2))));
    }

}
