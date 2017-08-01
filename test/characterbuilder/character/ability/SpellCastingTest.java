package characterbuilder.character.ability;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.character.saveload.TestDoc;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class SpellCastingTest {

    private SpellCasting casting;
    private Character character;
    private IntAttribute level;
    private IntAttribute score;

    @Before
    public void setup() {
        casting = new SpellCasting(AttributeType.CHARISMA);
        character = new Character();
        level = new IntAttribute(AttributeType.LEVEL, 4);
        character.addAttribute(level);
        score = new IntAttribute(AttributeType.CHARISMA, 16);
        character.addAttribute(score);
    }

    @Test
    public void testGetType() {
        assertThat(casting.getType(), is(AttributeType.SPELLCASTING));
    }

    @Test
    public void testAttackModifier() {
        assertThat(casting.getModifier(character), is(2 + 3));
        level.setValue(8);
        assertThat(casting.getModifier(character), is(3 + 3));
        score.setValue(20);
        assertThat(casting.getModifier(character), is(3 + 5));
    }

    @Test
    public void testSaveDC() {
        assertThat(casting.getSaveDC(character), is(8 + 2 + 3));
    }

    @Test
    public void testSaveAndLoad() {
        assertThat(AttributeType.load(casting.save(TestDoc.doc())), is(casting));
    }
}
