package characterbuilder.character.characterclass.warlock;

import characterbuilder.character.characterclass.CharacterClass;
import characterbuilder.character.spell.Spell;
import characterbuilder.utils.TestCharacter;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OtherwordlyPatronTest {

    private TestCharacter character;

    @BeforeEach
    public void setup() {
        character = new TestCharacter();
        CharacterClass.WARLOCK.choose(character);
    }

    @Test
    public void testAbility() {
        OtherwordlyPatron.THE_GREAT_OLD_ONE.choose(character);
        assertTrue(character.hasAttribute(WarlockAbility.AWAKENED_MIND));
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
