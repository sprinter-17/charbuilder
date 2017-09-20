package characterbuilder.character.characterclass;

import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.characterclass.CharacterClass.BARBARIAN;
import characterbuilder.character.saveload.TestDoc;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXParseException;

public class CharacterClassLevelTest {

    private CharacterClassLevel classLevel;

    @Before
    public void setup() {
        classLevel = new CharacterClassLevel(BARBARIAN, 7);
    }

    @Test
    public void testToString() {
        assertThat(classLevel.toString(), is("Barbarian 7"));
    }

    @Test
    public void testGetType() {
        assertThat(classLevel.getCharacterClass(), is(BARBARIAN));
        assertThat(classLevel.getLevel(), is(7));
    }

    @Test
    public void testIncreaseLevel() {
        classLevel.increaseLevel();
        assertThat(classLevel.getLevel(), is(8));
    }

    @Test
    public void testSaveAndLoad() throws SAXParseException {
        assertThat(AttributeType.load(classLevel.save(TestDoc.doc())), is(classLevel));
    }
}
