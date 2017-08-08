package characterbuilder.character.spell;

import characterbuilder.character.spell.SpellCasting;
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
        casting = new SpellCasting(AttributeType.CHARISMA, "[$level * 2]");
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
    public void testNoSpellSlots() {
        assertThat(casting.getMaxSlot(), is(0));
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

    @Test(expected = IllegalArgumentException.class)
    public void testGetSlotUnderRange() {
        casting.getSlotsAtLevel(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSlotOverRange() {
        casting.addSlots(1, 1);
        casting.getSlotsAtLevel(2);
    }

    @Test
    public void testPreparedSpellText() {
        assertThat(casting.getPreparedSpells(character), is("8"));
        level.setValue(10);
        assertThat(casting.getPreparedSpells(character), is("20"));
    }

    @Test
    public void testAddSlots() {
        assertThat(casting.getMaxSlot(), is(0));
        casting.addSlots(1, 2);
        assertThat(casting.getMaxSlot(), is(1));
        assertThat(casting.getSlotsAtLevel(1), is(2));
        casting.addSlots(2, 3);
        assertThat(casting.getMaxSlot(), is(2));
        assertThat(casting.getSlotsAtLevel(2), is(3));
        casting.addSlots(1, 1);
        assertThat(casting.getMaxSlot(), is(2));
        assertThat(casting.getSlotsAtLevel(1), is(3));
    }

    @Test
    public void testSaveAndLoad() {
        casting.addSlots(1, 5);
        casting.addSlots(3, 2);
        assertThat(AttributeType.load(casting.save(TestDoc.doc())), is(casting));
    }
}