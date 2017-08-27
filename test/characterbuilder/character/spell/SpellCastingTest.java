package characterbuilder.character.spell;

import characterbuilder.character.ability.Ability;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.CHARISMA;
import characterbuilder.character.attribute.IntAttribute;
import static characterbuilder.character.characterclass.CharacterClass.CLERIC;
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
        Ability.values(); // avoids initialisation errors
        casting = new SpellCasting("Spellcasting", CHARISMA, CLERIC, "[$level * 2]");
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
        casting.addPreparedSpell(Spell.MAGE_HAND);
    }

    @Test
    public void testLearnSpell() {
        assertFalse(casting.hasLearntSpell(Spell.AID));
        casting.addPreparedSpell(Spell.AID);
        assertTrue(casting.hasLearntSpell(Spell.AID));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotLearnSpellTwice() {
        casting.addPreparedSpell(Spell.AID);
        casting.addPreparedSpell(Spell.AID);
    }

    @Test
    public void testHasLearntSpell() {
        assertFalse(casting.hasLearntSpell(Spell.FIREBALL));
        casting.addPreparedSpell(Spell.FIREBALL);
        assertTrue(casting.hasLearntSpell(Spell.FIREBALL));
    }

    @Test
    public void testLearnAllSpells() {
        casting.learnAllSpells();
        casting.addSlots(1, 1);
        assertTrue(casting.hasLearntSpell(Spell.CURE_WOUNDS));
        assertFalse(casting.hasLearntSpell(Spell.AID));
        assertFalse(casting.hasLearntSpell(Spell.MENDING));
    }

    @Test
    public void testKnownSpells() {
        casting.choose(character);
        casting.addSlots(1, 3);
        assertFalse(character.hasChoice("Spellcasting Spell"));
        casting.addKnownSpells(4);
        assertTrue(character.hasChoice("Spellcasting Spell (x4)"));
    }

    @Test
    public void testPreparedSpellText() {
        assertThat(casting.getPreparedSpellText(character), is("8"));
        character.setLevel(10);
        assertThat(casting.getPreparedSpellText(character), is("20"));
    }

    @Test
    public void testSelectNone() {
        casting.addPreparedSpell(Spell.ALARM);
        casting.replaceSpell(character);
        character.selectChoice("Replace Spellcasting Spell", "None");
        assertFalse(character.hasChoice("Remove Spellcasting Spell"));
        assertTrue(casting.hasLearntSpell(Spell.ALARM));
    }

    @Test
    public void testRemoveSpell() {
        casting.addPreparedSpell(Spell.ALARM);
        casting.replaceSpell(character);
        character.selectChoice("Replace Spellcasting Spell", "Alarm");
        assertFalse(character.hasChoice("Remove Spellcasting Spell"));
        assertFalse(casting.hasLearntSpell(Spell.ALARM));
    }

    @Test
    public void testHasNewChoiceAfterRemoval() {
        casting.choose(character);
        casting.addSlots(1, 1);
        casting.addKnownSpells(4);
        casting.addPreparedSpell(Spell.ALARM);
        assertTrue(character.hasChoice("Spellcasting Spell (x3)"));
        casting.replaceSpell(character);
        character.selectChoice("Replace Spellcasting Spell", "Alarm");
        assertTrue(character.hasChoice("Spellcasting Spell (x4)"));
    }

    @Test
    public void testExpandSpellsInAllSpells() {
        casting.learnAllSpells();
        casting.addSlots(2, 1);
        assertFalse(casting.hasLearntSpell(Spell.DARKVISION));
        casting.addExpandedSpell(Spell.DARKVISION);
        assertTrue(casting.hasLearntSpell(Spell.DARKVISION));
    }

    @Test
    public void testLearntSpellsNotInAllSpells() {
        casting.learnAllSpells();
        casting.addSlots(1, 1);
        casting.addPreparedSpell(Spell.CURE_WOUNDS);
        assertThat(casting.getLearntSpells()
            .filter(ls -> ls.isSpell(Spell.CURE_WOUNDS)).count(), is(1L));
    }

    @Test
    public void testExpandedSpellsInChoice() {
        casting.addExpandedSpell(Spell.DARKVISION);
        casting.addSlots(2, 1);
        casting.addKnownSpells(1);
        casting.generateLevelChoices(character);
        assertFalse(casting.hasLearntSpell(Spell.DARKVISION));
        character.selectChoice("Spellcasting Spell", "Darkvision");
        assertTrue(casting.hasLearntSpell(Spell.DARKVISION));
    }

    @Test
    public void testSaveAndLoad() {
        casting.addSlots(1, 5);
        casting.addSlots(3, 2);
        casting.addPreparedSpell(Spell.HEAL);
        casting.addLearntSpell(Spell.ANIMAL_MESSENGER, false);
        casting.addExpandedSpell(Spell.DARKVISION);
        casting.addKnownSpells(17);
        casting.learnAllSpells();
        assertThat(AttributeType.load(casting.save(TestDoc.doc())), is(casting));
    }
}
