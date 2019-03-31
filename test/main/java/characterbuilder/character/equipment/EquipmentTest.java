package characterbuilder.character.equipment;

import characterbuilder.character.attribute.Weight;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.jupiter.api.Test;

public class EquipmentTest {

    @Test
    public void testWeight() {
        assertThat(AdventureGear.ARROW.getWeight(30), is(Weight.OZ.times(24)));
    }
}
