package characterbuilder.character.equipment;

import characterbuilder.character.Character;
import static characterbuilder.character.equipment.AdventureGear.BLOWGUN_NEEDLE;
import static characterbuilder.character.equipment.AdventureGear.CROWBAR;
import characterbuilder.character.saveload.TestDoc;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

public class EquipmentTypeTest {

    private final Character character = new Character();

    @Test
    public void testSaveAndLoad() {
        assertThat(AdventureGear.load(CROWBAR.save(TestDoc.doc())), is(CROWBAR));
    }

    @Test
    public void testDescription() {
        assertTrue(CROWBAR.getDescription(character)
            .anyMatch("Tool: cost 2GP, weight 5lb"::equals));
    }

    @Test
    public void testPerCountDescription() {
        assertTrue(BLOWGUN_NEEDLE.getDescription(character)
            .anyMatch("Ammunition: cost 1GP, weight 1lb /50"::equals));
    }

}
