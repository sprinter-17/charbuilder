package characterbuilder.character.attribute;

import characterbuilder.character.saveload.TestDoc;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXParseException;

public class AbilityScoreTest {

    private AbilityScore dex;

    @Before
    public void setup() {
        dex = new AbilityScore(AttributeType.DEXTERITY, 11);
    }

    @Test
    public void testMaxValue() {
        dex.addValue(10);
        assertThat(dex.getValue(), is(20));
        dex.setMax(22);
        dex.addValue(10);
        assertThat(dex.getValue(), is(22));
    }

    @Test
    public void testCopy() {
        Attribute copy = dex.copy();
        assertThat(dex, is(copy));
        dex.addValue(1);
        assertThat(dex, not(is(copy)));
    }

    @Test
    public void testSaveAndLoad() throws SAXParseException {
        dex.setProficientSaves();
        dex.setMax(27);
        assertThat(AttributeType.load(dex.save(TestDoc.doc())), is(dex));
    }

}
