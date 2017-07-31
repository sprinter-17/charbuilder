package characterbuilder.character.attribute;

import static characterbuilder.character.ability.Proficiency.COMMON;
import static characterbuilder.character.attribute.AttributeType.*;
import characterbuilder.character.saveload.TestDoc;
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
    public void testSaveAndLoad() {
        assertThat(AttributeType.load(COMMON.save(TestDoc.doc())), is(COMMON));
    }
}
