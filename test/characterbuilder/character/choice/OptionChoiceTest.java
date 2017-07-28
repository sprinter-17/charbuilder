package characterbuilder.character.choice;

import characterbuilder.character.Character;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class OptionChoiceTest {

    private OptionChoice choice;

    @Before
    public void setup() {
        choice = new OptionChoice(2) {
            @Override
            public void select(Character character, ChoiceSelector selector) {
            }
        };
    }

    @Test
    public void testUseAndCheck() {
        assertFalse(choice.useAndCheck());
        assertTrue(choice.useAndCheck());
    }
}
