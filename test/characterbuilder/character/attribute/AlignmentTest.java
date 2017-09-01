package characterbuilder.character.attribute;

import static characterbuilder.character.attribute.Alignment.LAWFUL_GOOD;
import characterbuilder.character.saveload.TestDoc;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.xml.sax.SAXParseException;

public class AlignmentTest {

    @Test
    public void testCopy() {
        assertThat(LAWFUL_GOOD.copy(), is(LAWFUL_GOOD));
    }

    @Test
    public void testSaveAndLoad() throws SAXParseException {
        assertThat(AttributeType.load(LAWFUL_GOOD.save(TestDoc.doc())), is(LAWFUL_GOOD));
    }

}
