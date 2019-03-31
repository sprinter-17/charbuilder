package characterbuilder.character.attribute;

import characterbuilder.character.saveload.TestDoc;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXParseException;

public class StringAttributeTest {

    StringAttribute attr;

    @Before
    public void setup() {
        attr = new StringAttribute(AttributeType.NAME, "Fred");
    }

    @Test
    public void testGetType() {
        assertThat(attr.getType(), is(AttributeType.NAME));
    }

    @Test
    public void testToString() {
        assertThat(attr.toString(), is("Fred"));
    }

    @Test
    public void testSetValue() {
        attr.setValue("Anne");
        assertThat(attr.toString(), is("Anne"));
    }

    @Test
    public void testCopy() {
        Attribute copy = attr.copy();
        assertThat(attr, is(copy));
        attr.setValue("Foo");
        assertThat(attr, not(is(copy)));
    }

    @Test
    public void testSaveAndLoad() throws SAXParseException {
        assertThat(AttributeType.load(attr.save(TestDoc.doc())), is(attr));
    }
}
