package characterbuilder.character.spell;

import characterbuilder.character.CharacterRandom;
import characterbuilder.character.characterclass.CharacterClass;
import characterbuilder.character.characterclass.fighter.MartialArchetype;
import characterbuilder.character.characterclass.rogue.RoguishArchetype;
import characterbuilder.utils.TestCharacter;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class SpellSlotsTest {

    private CharacterRandom random = new CharacterRandom();
    private TestCharacter character;

    @Before
    public void setup() {
        character = new TestCharacter();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSlotsForLevelUnder1() {
        character.setLevel(CharacterClass.WIZARD, 1);
        SpellSlots.getSlotsAtLevel(character, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSlotsForLevelOver9() {
        character.setLevel(CharacterClass.WIZARD, 1);
        SpellSlots.getSlotsAtLevel(character, 10);
    }

    public void testGetSlotsForNonSpellcaster() {
        character.setLevel(CharacterClass.FIGHTER, 4);
        assertThat(SpellSlots.getSlotsAtLevel(character, 1), is(0));
        assertThat(SpellSlots.getSlotsAtLevel(character, 9), is(0));
    }

    @Test
    public void testGetSlotsForWizard() {
        character.setLevel(CharacterClass.WIZARD, 1);
        assertThat(SpellSlots.getSlotsAtLevel(character, 1), is(2));
        assertThat(SpellSlots.getSlotsAtLevel(character, 2), is(0));
        assertThat(SpellSlots.getSlotsAtLevel(character, 9), is(0));
        character.setLevel(CharacterClass.WIZARD, 2);
        assertThat(SpellSlots.getSlotsAtLevel(character, 1), is(3));
        assertThat(SpellSlots.getSlotsAtLevel(character, 2), is(0));
        character.setLevel(CharacterClass.WIZARD, 3);
        assertThat(SpellSlots.getSlotsAtLevel(character, 1), is(4));
        assertThat(SpellSlots.getSlotsAtLevel(character, 2), is(2));
        character.setLevel(CharacterClass.WIZARD, 20);
        assertThat(SpellSlots.getSlotsAtLevel(character, 1), is(4));
        assertThat(SpellSlots.getSlotsAtLevel(character, 2), is(3));
        assertThat(SpellSlots.getSlotsAtLevel(character, 6), is(2));
        assertThat(SpellSlots.getSlotsAtLevel(character, 8), is(1));
        assertThat(SpellSlots.getSlotsAtLevel(character, 9), is(1));
    }

    @Test
    public void testGetHighestSpellLevelForWarlock() {
        character.setLevel(CharacterClass.WARLOCK, 2);
        assertThat(SpellSlots.getHighestSpellLevelForClass(character, CharacterClass.WARLOCK), is(1));
        character.setLevel(CharacterClass.WARLOCK, 3);
        assertThat(SpellSlots.getHighestSpellLevelForClass(character, CharacterClass.WARLOCK), is(2));
    }

    @Test
    public void testGetSlotsForWarlock() {
        character.setLevel(CharacterClass.WARLOCK, 2);
        assertThat(SpellSlots.getSlotsAtLevel(character, 1), is(2));
        assertThat(SpellSlots.getSlotsAtLevel(character, 2), is(0));
        character.setLevel(CharacterClass.WARLOCK, 3);
        assertThat(SpellSlots.getSlotsAtLevel(character, 1), is(0));
        assertThat(SpellSlots.getSlotsAtLevel(character, 2), is(2));
    }

    @Test
    public void testGetSlotsForMulticlass() {
        character.setLevel(CharacterClass.WIZARD, 1);
        assertThat(SpellSlots.getSlotsAtLevel(character, 1), is(2));
        character.setLevel(CharacterClass.CLERIC, 1);
        assertThat(SpellSlots.getSlotsAtLevel(character, 1), is(3));
        character.setLevel(CharacterClass.PALADIN, 1);
        assertThat(SpellSlots.getSlotsAtLevel(character, 1), is(3));
        character.setLevel(CharacterClass.PALADIN, 2);
        assertThat(SpellSlots.getSlotsAtLevel(character, 1), is(4));
    }

    @Test
    public void testGetSlotsForMulticlassFighter() {
        character.setLevel(CharacterClass.FIGHTER, 3);
        character.setLevel(CharacterClass.WIZARD, 1);
        assertThat(SpellSlots.getSlotsAtLevel(character, 1), is(2));
        character.addAttribute(MartialArchetype.ELDRITCH_KNIGHT);
        assertThat(SpellSlots.getSlotsAtLevel(character, 1), is(3));
    }

    @Test
    public void testGetSlotsForMulticlassRogue() {
        character.setLevel(CharacterClass.ROGUE, 12);
        character.setLevel(CharacterClass.WIZARD, 1);
        assertThat(SpellSlots.getSlotsAtLevel(character, 3), is(0));
        character.addAttribute(RoguishArchetype.ARCANE_TRICKSTER);
        assertThat(SpellSlots.getSlotsAtLevel(character, 3), is(2));
    }

    @Test
    public void testGetSlotsForMulticlassWarlock() {
        character.setLevel(CharacterClass.WIZARD, 4);
        character.setLevel(CharacterClass.WARLOCK, 4);
        assertThat(SpellSlots.getSlotsAtLevel(character, 2), is(5));
    }

}
