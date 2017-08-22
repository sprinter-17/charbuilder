package characterbuilder.character.choice;

import characterbuilder.character.Character;
import characterbuilder.character.ability.FightingStyle;
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
        Choice choice = Choice.action("Test", ch -> ch.addAttribute(FightingStyle.ARCHERY));
        choice.addTo(character);
        assertTrue(character.hasAttribute(FightingStyle.ARCHERY));
    }
}
