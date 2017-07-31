package characterbuilder.character.attribute;

import static characterbuilder.character.attribute.Alignment.LAWFUL_GOOD;
import characterbuilder.character.saveload.TestDoc;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class AlignmentTest {

    @Test
    public void testSaveAndLoad() {
        assertThat(AttributeType.load(LAWFUL_GOOD.save(TestDoc.doc())), is(LAWFUL_GOOD));
    }

}
