package characterbuilder.character.characterclass.ranger;

import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.beast.Beast;
import characterbuilder.character.saveload.TestDoc;
import characterbuilder.utils.TestCharacter;
import static java.util.stream.Collectors.joining;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXParseException;

public class RangerCompanionTest {

    private RangerCompanion companion;
    private TestCharacter character;

    @BeforeEach
    public void setup() {
        companion = new RangerCompanion(Beast.BOAR);
        character = new TestCharacter();
    }

    @Test
    public void testGetType() {
        assertThat(companion.getType(), is(AttributeType.RANGERS_COMPANION));
    }

    @Test
    public void testToString() {
        assertThat(companion.toString(), is("Boar"));
    }

    @Test
    public void testGenerateInitialChoices() {
        companion.choose(character);
        assertTrue(character.hasAttribute(RangerAbility.RANGERS_COMPANION));
    }

    @Test
    public void testGetDescription() {
        assertThat(companion.getDescription(character).collect(joining()), containsString("AC 11"));
    }

    @Test
    public void testSaveAndLoad() throws SAXParseException {
        assertThat(AttributeType.load(companion.save(TestDoc.doc())), is(companion));
    }

}
