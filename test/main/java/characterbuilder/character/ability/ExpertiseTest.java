package characterbuilder.character.ability;

import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.characterclass.CharacterClass;
import characterbuilder.character.saveload.TestDoc;
import characterbuilder.utils.TestCharacter;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXParseException;

public class ExpertiseTest {

    private Expertise expertise;

    @Before
    public void setup() {
        expertise = new Expertise(Skill.ARCANA);
    }

    @Test
    public void testGetType() {
        assertThat(expertise.getType(), is(AttributeType.EXPERTISE));
    }

    @Test
    public void testIsFor() {
        assertTrue(expertise.isFor(Skill.ARCANA));
    }

    @Test
    public void testDescription() {
        TestCharacter character = new TestCharacter();
        character.setLevel(CharacterClass.FIGHTER, 10);
        assertThat(expertise.getDescription(character).findFirst().get(),
            is("+4 proficiency bonus."));
    }

    @Test
    public void testSaveLoad() throws SAXParseException {
        assertThat(AttributeType.load(expertise.save(TestDoc.doc())), is(expertise));
    }
}
