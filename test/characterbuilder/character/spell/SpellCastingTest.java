package characterbuilder.character.spell;

import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.character.saveload.TestDoc;
import characterbuilder.utils.TestCharacter;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SpellCastingTest {

    private SpellCasting casting;
    private TestCharacter character;
    private IntAttribute score;

    @Before
    public void setup() {
        casting = new SpellCasting("Spellcasting", AttributeType.CHARISMA, "[$level * 2]");
        character = new TestCharacter();
        character.setLevel(4);
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
        character.setLevel(8);
        assertThat(casting.getModifier(character), is(3 + 3));
        character.setScore(AttributeType.CHARISMA, 20);
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

    @Test(expected = IllegalArgumentException.class)
    public void testCannotAddCantripToSpellCasting() {
        casting.addLearntSpell(Spell.MAGE_HAND);
    }

    @Test
    public void testLearnSpell() {
        assertFalse(casting.getLearntSpells().anyMatch(Spell.AID::equals));
        casting.addLearntSpell(Spell.AID);
        assertTrue(casting.getLearntSpells().anyMatch(Spell.AID::equals));
    }

    @Test
    public void testHasLearntSpell() {
        assertFalse(casting.hasLearntSpell(Spell.FIREBALL));
        casting.addLearntSpell(Spell.FIREBALL);
        assertTrue(casting.hasLearntSpell(Spell.FIREBALL));
    }

    @Test
    public void testSaveAndLoad() {
        casting.addSlots(1, 5);
        casting.addSlots(3, 2);
        casting.addLearntSpell(Spell.HEAL);
        casting.addLearntSpell(Spell.ANIMAL_MESSENGER);
        assertThat(AttributeType.load(casting.save(TestDoc.doc())), is(casting));
    }

    @Test
    public void testPreparedSpellText() {
        assertThat(casting.getPreparedSpellText(character), is("8"));
        character.setLevel(10);
        assertThat(casting.getPreparedSpellText(character), is("20"));
    }
}
