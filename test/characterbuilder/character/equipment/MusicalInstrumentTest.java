package characterbuilder.character.equipment;

import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.saveload.TestDoc;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

public class MusicalInstrumentTest {

    @Test
    public void testSaveLoadProficiency() {
        Attribute prof = MusicalInstrument.BAGPIPES.getProficiency();
        assertThat(AttributeType.load(prof.save(TestDoc.doc())), is(prof));
    }

}
