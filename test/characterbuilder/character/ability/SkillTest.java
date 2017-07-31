package characterbuilder.character.ability;

import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.saveload.TestDoc;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

public class SkillTest {

    @Test
    public void testSaveLoad() {
        assertThat(AttributeType.load(Skill.DECEPTION.save(TestDoc.doc())), is(Skill.DECEPTION));
    }
}
