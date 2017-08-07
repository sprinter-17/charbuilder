package characterbuilder.character.attribute;

import characterbuilder.character.saveload.TestDoc;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

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
    public void testSaveAndLoad() {
        dex.setProficientSaves();
        dex.setMax(27);
        assertThat(AttributeType.load(dex.save(TestDoc.doc())), is(dex));
    }

}
