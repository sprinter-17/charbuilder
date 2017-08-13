package characterbuilder.character.choice;

import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.spell.Spell;
import characterbuilder.character.spell.SpellCasting;
import characterbuilder.utils.TestCharacter;
import java.util.stream.Stream;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ReplaceSpellTest {

    private TestCharacter character;
    private TestChoiceSelector selector;
    private ReplaceSpell replace;
    private SpellCasting casting;

    @Before
    public void setup() {
        character = new TestCharacter().withScores(10);
        casting = new SpellCasting("Casting", AttributeType.INTELLIGENCE);
        casting.addSlots(1, 2);
        character.addAttribute(casting);
        selector = new TestChoiceSelector();
        character.addChoiceList(selector);
        replace = new ReplaceSpell("Casting", Stream.of(Spell.ALARM, Spell.ANIMAL_FRIENDSHIP));
        character.addChoice(replace);
    }

    @Test(expected = IllegalStateException.class)
    public void testNoSpells() {
        character.selectChoice(replace);
    }

    @Test
    public void testName() {
        assertThat(replace.toString(), is("Remove Casting Spell"));
        assertTrue(character.hasChoice("Remove Casting Spell"));
    }

    @Test
    public void testSelectNone() {
        casting.addLearntSpell(Spell.ALARM);
        selector.withChoice("None");
        character.selectChoice(replace);
        assertFalse(character.hasChoice("Remove Casting Spell"));
        assertTrue(casting.hasLearntSpell(Spell.ALARM));
    }

    @Test
    public void testRemoveSpell() {
        casting.addLearntSpell(Spell.ALARM);
        selector.withChoice("Alarm");
        character.selectChoice(replace);
        assertFalse(character.hasChoice("Remove Casting Spell"));
        assertFalse(casting.hasLearntSpell(Spell.ALARM));
    }

    @Test
    public void testHasNewChoice() {
        casting.addLearntSpell(Spell.ALARM);
        selector.withChoice("Alarm");
        character.selectChoice(replace);
        assertTrue(character.hasChoice("Replace Casting Spell"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewChoiceDoesNotIncludeRemovedSpell() {
        casting.addLearntSpell(Spell.ALARM);
        selector.withChoice("Alarm");
        character.selectChoice(replace);
        selector.withChoice("Alarm");
        character.selectChoice(character.getChoice(0));
    }

    @Test
    public void testReplaceSpell() {
        casting.addLearntSpell(Spell.ALARM);
        selector.withChoice("Alarm");
        character.selectChoice(replace);
        selector.withChoice("Animal Friendship");
        character.selectChoice(character.getChoice(0));
        assertTrue(casting.hasLearntSpell(Spell.ANIMAL_FRIENDSHIP));
    }

}
