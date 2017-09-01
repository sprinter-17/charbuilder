package characterbuilder.character.spell;

import characterbuilder.character.ability.Ability;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.saveload.TestDoc;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXParseException;

public class CantripTest {

    private SpellAbility cantrip;

    @Before
    public void setup() {
        Ability.values(); // initialisation error
        cantrip = new SpellAbility(Spell.HEAL, AttributeType.WISDOM);
    }

    @Test
    public void testGetType() {
        assertThat(cantrip.getType(), is(AttributeType.SPELL_ABILITY));
    }

    @Test
    public void testSaveAndLoad() throws SAXParseException {
        assertThat(AttributeType.load(cantrip.save(TestDoc.doc())), is(cantrip));
    }
}
