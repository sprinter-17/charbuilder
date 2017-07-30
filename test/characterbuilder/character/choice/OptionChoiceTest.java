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
        choice = new OptionChoice("Test", 2) {
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

    @Test
    public void testResetCountOnAdd() {
        choice.useAndCheck();
        choice.useAndCheck();
        Character character = new Character();
        character.addChoiceList(new TestChoiceSelector());
        choice.addTo(character);
        assertFalse(choice.useAndCheck());
    }
}
