package characterbuilder.character.attribute;

import characterbuilder.character.Character;
import characterbuilder.character.spell.Spell;
import characterbuilder.character.saveload.TestDoc;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SpellMasteryTest {

    private SpellMastery mastery;
    private Character character;

    @Before
    public void setup() {
        character = new Character();
        character.addAttribute(CharacterClass.WIZARD);
    }

    @Test
    public void testGetType() {
        mastery = new SpellMastery("Test", Spell.AID);
        assertThat(mastery.getType(), is(AttributeType.SPELL_MASTERY));
    }

    @Test
    public void testToString() {
        mastery = new SpellMastery("Test", Spell.AID);
        assertThat(mastery.toString(), is("Test Aid"));
    }

    @Test
    public void testSaveAndLoad() {
        mastery = new SpellMastery("Test", Spell.HEAL);
        assertThat(AttributeType.load(mastery.save(TestDoc.doc())), is(mastery));
    }

}
