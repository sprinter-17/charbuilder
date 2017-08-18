package characterbuilder.character.characterclass;

import characterbuilder.character.ability.Ability;
import characterbuilder.character.spell.Spell;
import characterbuilder.utils.TestCharacter;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class OtherwordlyPatronTest {

    private TestCharacter character;

    @Before
    public void setup() {
        character = new TestCharacter();
        CharacterClass.WARLOCK.choose(character);
    }

    @Test
    public void testAbility() {
        OtherwordlyPatron.THE_GREAT_OLD_ONE.choose(character);
        assertTrue(character.hasAttribute(Ability.AWAKENED_MIND));
    }

    @Test
    public void testExpandedSpellList() {
        assertFalse(character.getSpellCasting("Warlock").getAvailableSpells()
            .anyMatch(Spell.BLINK::equals));
        OtherwordlyPatron.THE_ARCHFEY.choose(character);
        assertTrue(character.getSpellCasting("Warlock").getAvailableSpells()
            .anyMatch(Spell.BLINK::equals));
    }
}
