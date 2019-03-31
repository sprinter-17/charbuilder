package characterbuilder.character.spell;

import characterbuilder.character.characterclass.CharacterClass;
import characterbuilder.character.characterclass.fighter.MartialArchetype;
import characterbuilder.character.characterclass.rogue.RoguishArchetype;
import characterbuilder.utils.TestCharacter;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SpellSlotsTest {

	private TestCharacter character;

	@BeforeEach
	public void setup() {
		character = new TestCharacter();
	}

	@Test
	public void testGetSlotsForLevelUnder1() {
		character.setLevel(CharacterClass.WIZARD, 1);
		assertThrows(IllegalArgumentException.class, () -> SpellSlots.getSlotsForSpellLevel(character, 0));
	}

	@Test
	public void testGetSlotsForLevelOver9() {
		character.setLevel(CharacterClass.WIZARD, 1);
		assertThrows(IllegalArgumentException.class, () -> SpellSlots.getSlotsForSpellLevel(character, 10));
	}

	public void testGetSlotsForNonSpellcaster() {
		character.setLevel(CharacterClass.FIGHTER, 4);
		assertThat(SpellSlots.getSlotsForSpellLevel(character, 1), is(0));
		assertThat(SpellSlots.getSlotsForSpellLevel(character, 9), is(0));
	}

	@Test
	public void testGetSlotsForWizard() {
		character.setLevel(CharacterClass.WIZARD, 1);
		assertThat(SpellSlots.getSlotsForSpellLevel(character, 1), is(2));
		assertThat(SpellSlots.getSlotsForSpellLevel(character, 2), is(0));
		assertThat(SpellSlots.getSlotsForSpellLevel(character, 9), is(0));
		character.setLevel(CharacterClass.WIZARD, 2);
		assertThat(SpellSlots.getSlotsForSpellLevel(character, 1), is(3));
		assertThat(SpellSlots.getSlotsForSpellLevel(character, 2), is(0));
		character.setLevel(CharacterClass.WIZARD, 3);
		assertThat(SpellSlots.getSlotsForSpellLevel(character, 1), is(4));
		assertThat(SpellSlots.getSlotsForSpellLevel(character, 2), is(2));
		character.setLevel(CharacterClass.WIZARD, 20);
		assertThat(SpellSlots.getSlotsForSpellLevel(character, 1), is(4));
		assertThat(SpellSlots.getSlotsForSpellLevel(character, 2), is(3));
		assertThat(SpellSlots.getSlotsForSpellLevel(character, 6), is(2));
		assertThat(SpellSlots.getSlotsForSpellLevel(character, 8), is(1));
		assertThat(SpellSlots.getSlotsForSpellLevel(character, 9), is(1));
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
		assertThat(SpellSlots.getSlotsForSpellLevel(character, 1), is(2));
		assertThat(SpellSlots.getSlotsForSpellLevel(character, 2), is(0));
		character.setLevel(CharacterClass.WARLOCK, 3);
		assertThat(SpellSlots.getSlotsForSpellLevel(character, 1), is(0));
		assertThat(SpellSlots.getSlotsForSpellLevel(character, 2), is(2));
	}

	@Test
	public void testGetSlotsForMulticlass() {
		character.setLevel(CharacterClass.WIZARD, 1);
		assertThat(SpellSlots.getSlotsForSpellLevel(character, 1), is(2));
		character.setLevel(CharacterClass.CLERIC, 1);
		assertThat(SpellSlots.getSlotsForSpellLevel(character, 1), is(3));
		character.setLevel(CharacterClass.PALADIN, 1);
		assertThat(SpellSlots.getSlotsForSpellLevel(character, 1), is(3));
		character.setLevel(CharacterClass.PALADIN, 2);
		assertThat(SpellSlots.getSlotsForSpellLevel(character, 1), is(4));
	}

	@Test
	public void testGetSlotsForMulticlassFighter() {
		character.setLevel(CharacterClass.FIGHTER, 3);
		character.setLevel(CharacterClass.WIZARD, 1);
		assertThat(SpellSlots.getSlotsForSpellLevel(character, 1), is(2));
		character.addAttribute(MartialArchetype.ELDRITCH_KNIGHT);
		assertThat(SpellSlots.getSlotsForSpellLevel(character, 1), is(3));
	}

	@Test
	public void testGetSlotsForMulticlassRogue() {
		character.setLevel(CharacterClass.ROGUE, 12);
		character.setLevel(CharacterClass.WIZARD, 1);
		assertThat(SpellSlots.getSlotsForSpellLevel(character, 3), is(0));
		character.addAttribute(RoguishArchetype.ARCANE_TRICKSTER);
		assertThat(SpellSlots.getSlotsForSpellLevel(character, 3), is(2));
	}

	@Test
	public void testGetSlotsForMulticlassWarlock() {
		character.setLevel(CharacterClass.WIZARD, 4);
		character.setLevel(CharacterClass.WARLOCK, 4);
		assertThat(SpellSlots.getSlotsForSpellLevel(character, 2), is(5));
	}
}
