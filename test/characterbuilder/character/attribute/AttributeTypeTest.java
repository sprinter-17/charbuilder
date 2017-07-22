package characterbuilder.character.attribute;

import static characterbuilder.character.attribute.AttributeType.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

public class AttributeTypeTest {

    @Test
    public void testIsTypeOfAttribute() {
        assertFalse(STRENGTH.isTypeOfAttribute(new IntAttribute(DEXTERITY, 10)));
        assertTrue(STRENGTH.isTypeOfAttribute(new IntAttribute(STRENGTH, 10)));
    }

    @Test
    public void testIntAttributeCodeDecode() {
        Attribute str = STRENGTH.decode("5");
        assertThat(str.getType(), is(STRENGTH));
        assertThat(str.toString(), is("5"));
    }

}
