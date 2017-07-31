package characterbuilder.character.equipment;

import static characterbuilder.character.equipment.EquipmentType.CROWBAR;
import characterbuilder.character.saveload.TestDoc;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

public class EquipmentTypeTest {

    @Test
    public void testSaveAndLoad() {
        assertThat(EquipmentType.load(CROWBAR.save(TestDoc.doc())), is(CROWBAR));
    }

}
