package characterbuilder.character.choice;

import characterbuilder.character.Character;
import static characterbuilder.character.ability.Ability.ARCHERY;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ChoiceTest {

    private Character character;

    @Before
    public void setup() {
        character = new Character();
    }

    @Test
    public void testMakeChoice() {
        Choice choice = (ch, sel) -> ch.addAttribute(ARCHERY);
        choice.makeChoice(character, new TestChoiceSelector());
        assertTrue(character.hasAttribute(ARCHERY));
    }
}
