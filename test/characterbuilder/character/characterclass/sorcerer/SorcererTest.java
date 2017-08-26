package characterbuilder.character.characterclass.sorcerer;

import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.characterclass.sorcerer.Sorcerer.Ability.FONT_OF_MAGIC;
import static characterbuilder.character.saveload.TestDoc.doc;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

public class SorcererTest {

    @Test
    public void testSaveAndLoadAbility() {
        assertThat(AttributeType.load(FONT_OF_MAGIC.save(doc())), is(FONT_OF_MAGIC));
    }

}
