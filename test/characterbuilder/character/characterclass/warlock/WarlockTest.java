package characterbuilder.character.characterclass.warlock;

import static characterbuilder.character.attribute.AttributeType.CHARISMA;
import characterbuilder.character.spell.Spell;
import characterbuilder.character.spell.SpellAbility;
import characterbuilder.utils.TestCharacter;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class WarlockTest {

    private TestCharacter warlock;

    @Before
    public void setup() {
        warlock = new TestCharacter();
    }

    @Test
    public void testSpellAbility() {
        WarlockAbility.PACT_OF_THE_CHAIN.choose(warlock);
        assertTrue(warlock.hasAttribute(new SpellAbility(Spell.FIND_FAMILIAR, CHARISMA)));
    }
}
