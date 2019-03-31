package characterbuilder.sheet;

import characterbuilder.character.attribute.AttributeType;
import static org.junit.Assert.*;
import org.junit.Test;

public class AttributePlacementTest {

    @Test
    public void testAllTypesHavePlacement() {
        for (AttributeType type : AttributeType.values()) {
            assertNotNull(AttributePlacement.forAttributeType(type));
        }
    }

}
