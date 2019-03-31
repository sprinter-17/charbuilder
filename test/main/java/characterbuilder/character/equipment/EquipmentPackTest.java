package characterbuilder.character.equipment;

import characterbuilder.character.attribute.Value;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

public class EquipmentPackTest {

    @Test
    public void testGetWeight() {
    }

    @Test
    public void testGetValue() {
        assertThat(EquipmentPack.DUNGEONEER_PACK.getValue(), is(Value.GP.times(12)));
    }
}
