package characterbuilder.character.spell;

import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.saveload.TestDoc;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class CantripTest {

    private Cantrip cantrip;

    @Before
    public void setup() {
        cantrip = new Cantrip(Spell.HEAL, AttributeType.WISDOM);
    }

    @Test
    public void testGetType() {
        assertThat(cantrip.getType(), is(AttributeType.CANTRIP));
    }

    @Test
    public void testSaveAndLoad() {
        assertThat(AttributeType.load(cantrip.save(TestDoc.doc())), is(cantrip));
    }
}
