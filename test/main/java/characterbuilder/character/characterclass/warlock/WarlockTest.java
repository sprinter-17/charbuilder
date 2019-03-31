package characterbuilder.character.characterclass.warlock;

import characterbuilder.character.spell.Spell;
import characterbuilder.utils.TestCharacter;
import static characterbuilder.utils.TestMatchers.hasSpellAbility;
import static org.junit.Assert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WarlockTest {

    private TestCharacter warlock;

    @BeforeEach
    public void setup() {
        warlock = new TestCharacter();
    }

    @Test
    public void testSpellAbility() {
        WarlockAbility.PACT_OF_THE_CHAIN.choose(warlock);
        assertThat(warlock, hasSpellAbility(Spell.FIND_FAMILIAR));
    }
}
