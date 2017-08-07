package characterbuilder.character.attribute;

import characterbuilder.character.saveload.TestDoc;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class IntAttributeTest {

    private IntAttribute attr;

    @Before
    public void setup() {
        attr = new IntAttribute(AttributeType.LEVEL, 17);
    }

    @Test
    public void testGetType() {
        assertThat(attr.getType(), is(AttributeType.LEVEL));
    }

    @Test
    public void testGetValue() {
        assertThat(attr.getValue(), is(17));
    }

    @Test
    public void testSetValue() {
        attr.setValue(11);
        assertThat(attr.getValue(), is(11));
    }

    @Test
    public void testAddValue() {
        attr.addValue(2);
        assertThat(attr.getValue(), is(19));
        attr.addValue(-10);
        assertThat(attr.getValue(), is(9));
    }

    @Test
    public void testToString() {
        assertThat(attr.toString(), is("17"));
    }

    @Test
    public void testSaveAndLoad() {
        assertThat(AttributeType.load(attr.save(TestDoc.doc())), is(attr));
    }
}
