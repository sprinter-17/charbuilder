package characterbuilder.character.spell;

import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.saveload.TestDoc;
import characterbuilder.utils.TestCharacter;
import static java.util.stream.Collectors.joining;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXParseException;

public class LearntSpellTest {

    private LearntSpell spell;

    @BeforeEach
    public void setup() {
        spell = new LearntSpell(Spell.SHIELD, AttributeType.CHARISMA, true);
    }

    @Test
    public void testToString() {
        assertThat(spell.toString(), is("Shield"));
    }

    @Test
    public void testDescription() {
        assertThat(spell.getDescription(new TestCharacter()).collect(joining()),
            is(Spell.SHIELD.getDescription().collect(joining())));
    }

    @Test
    public void testSaveAndLoad() throws SAXParseException {
        assertThat(AttributeType.load(spell.save(TestDoc.doc())), is(spell));
    }

}
