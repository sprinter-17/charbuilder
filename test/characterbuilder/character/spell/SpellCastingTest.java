package characterbuilder.character.spell;

import characterbuilder.character.CharacterRandom;
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
import org.xml.sax.SAXParseException;

public class SpellCastingTest {

    private SpellCasting casting;
    private TestCharacter character;
    private IntAttribute score;

    @Before
    public void setup() {
        Ability.values(); // avoids initialisation errors
        casting = new SpellCasting("Spellcasting", CHARISMA, CLERIC, "[$level * 2]");
        character = new TestCharacter();
        character.setLevel(CLERIC, 4);
        casting.choose(character);
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
        character.setLevel(CLERIC, 8);
        assertThat(casting.getModifier(character), is(3 + 3));
        character.setScore(AttributeType.CHARISMA, 20);
        assertThat(casting.getModifier(character), is(3 + 5));
    }

    @Test
    public void testSaveDC() {
        assertThat(casting.getSaveDC(character), is(8 + 2 + 3));
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
    public void testMaxSpellLevel() {
        assertThat(casting.getMaxSpellLevel(), is(2));
        character.increaseLevel(CLERIC, new CharacterRandom());
        assertThat(casting.getMaxSpellLevel(), is(3));
        character.increaseLevel(CLERIC, new CharacterRandom());
        assertThat(casting.getMaxSpellLevel(), is(3));
        character.increaseLevel(CLERIC, new CharacterRandom());
        assertThat(casting.getMaxSpellLevel(), is(4));
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
        assertTrue(casting.hasLearntSpell(Spell.CURE_WOUNDS));
        assertTrue(casting.hasLearntSpell(Spell.AID));
        assertFalse(casting.hasLearntSpell(Spell.CREATE_FOOD_AND_WATER));
        assertFalse(casting.hasLearntSpell(Spell.MENDING));
    }

    @Test
    public void testKnownSpells() {
        casting.choose(character);
        assertFalse(character.hasChoice("Spellcasting Spell"));
        casting.addKnownSpells(4);
        assertTrue(character.hasChoice("Spellcasting Spell", 4));
    }

    @Test
    public void testPreparedSpellText() {
        assertThat(casting.getPreparedSpellText(character), is("8"));
        character.setLevel(CLERIC, 10);
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
        casting.addKnownSpells(4);
        casting.addPreparedSpell(Spell.ALARM);
        assertTrue(character.hasChoice("Spellcasting Spell", 3));
        casting.replaceSpell(character);
        character.selectChoice("Replace Spellcasting Spell", "Alarm");
        assertTrue(character.hasChoice("Spellcasting Spell", 4));
    }

    @Test
    public void testExpandSpellsInAllSpells() {
        casting.learnAllSpells();
        assertFalse(casting.hasLearntSpell(Spell.DARKVISION));
        casting.addExpandedSpell(Spell.DARKVISION);
        assertTrue(casting.hasLearntSpell(Spell.DARKVISION));
    }

    @Test
    public void testLearntSpellsNotInAllSpells() {
        casting.learnAllSpells();
        casting.addPreparedSpell(Spell.CURE_WOUNDS);
        assertThat(casting.getLearntSpells()
            .filter(ls -> ls.isSpell(Spell.CURE_WOUNDS)).count(), is(1L));
    }

    @Test
    public void testExpandedSpellsInChoice() {
        casting.addExpandedSpell(Spell.DARKVISION);
        casting.addKnownSpells(1);
        casting.generateLevelChoices(character);
        assertFalse(casting.hasLearntSpell(Spell.DARKVISION));
        character.selectChoice("Spellcasting Spell", "Darkvision");
        assertTrue(casting.hasLearntSpell(Spell.DARKVISION));
    }

    @Test
    public void testSaveAndLoad() throws SAXParseException {
        casting.addPreparedSpell(Spell.HEAL);
        casting.addLearntSpell(Spell.ANIMAL_MESSENGER, false);
        casting.addExpandedSpell(Spell.DARKVISION);
        casting.addKnownSpells(17);
        casting.learnAllSpells();
        assertThat(AttributeType.load(casting.save(TestDoc.doc())), is(casting));
    }
}
