package characterbuilder.sheet;

import characterbuilder.character.equipment.EquipmentCategory;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

public class EquipmentPlacementTest {

    @Test
    public void testAllCategoriesHavePlace() {
        for (EquipmentCategory category : EquipmentCategory.values()) {
            assertTrue(EquipmentPlacement.forCategory(category) != null);
        }
    }

}
