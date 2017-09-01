package characterbuilder.character.spell;

import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.characterclass.CharacterClass.WIZARD;
import characterbuilder.character.saveload.TestDoc;
import characterbuilder.utils.TestCharacter;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXParseException;

public class SignatureSpellTest {

    private TestCharacter wizard;
    private SignatureSpell signature;
    private SpellCasting casting;

    @Before
    public void setup() {
        wizard = new TestCharacter().withScores(10);
        WIZARD.choose(wizard);
        casting = wizard.getAttribute(AttributeType.SPELLCASTING);
        casting.addPreparedSpell(Spell.FIREBALL);
        casting.addPreparedSpell(Spell.FLY);
        casting.addPreparedSpell(Spell.GASEOUS_FORM);
        signature = new SignatureSpell();
    }

    @Test
    public void testGetType() {
        assertThat(signature.getType(), is(AttributeType.SIGNATURE_SPELL));
    }

    @Test
    public void testChooseSpells() {
        signature.choose(wizard);
        wizard.selectChoice("Signature Spell", "Fireball");
        wizard.selectChoice("Signature Spell", "Gaseous Form");
        assertTrue(signature.hasSpell(Spell.FIREBALL));
        assertFalse(signature.hasSpell(Spell.FLY));
        assertTrue(signature.hasSpell(Spell.GASEOUS_FORM));
    }

    @Test
    public void testSaveAndLoad() throws SAXParseException {
        signature.choose(wizard);
        wizard.selectChoice("Signature Spell", "Fireball");
        wizard.selectChoice("Signature Spell", "Gaseous Form");
        assertThat(AttributeType.load(signature.save(TestDoc.doc())), is(signature));
    }
}
