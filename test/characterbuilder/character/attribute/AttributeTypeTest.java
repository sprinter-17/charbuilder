package characterbuilder.character.attribute;

import static characterbuilder.character.attribute.AttributeType.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class AttributeTypeTest {

    @Test
    public void testIsTypeOfAttribute() {
        assertFalse(STRENGTH.isTypeOfAttribute(new IntAttribute(DEXTERITY, 10)));
        assertTrue(STRENGTH.isTypeOfAttribute(new IntAttribute(STRENGTH, 10)));
    }
}
