package characterbuilder.character.choice;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Skill;
import java.util.Arrays;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ReplaceAttributeChoiceTest {

    private ReplaceAttributeChoice choice;
    private Character character;
    private TestChoiceSelector selector;

    @Before
    public void setup() {
        choice = new ReplaceAttributeChoice("Test", 5, Arrays.stream(Skill.values()));
        character = new Character();
        selector = new TestChoiceSelector();
        character.addChoiceList(selector);
        character.addChoice(choice);
    }

    @Test
    public void testSelect() {
        character.addAttribute(Skill.ATHLETICS);
        selector.withAttribute(Skill.ATHLETICS);
        character.selectChoice(choice);
        assertFalse(character.hasAttribute(Skill.ATHLETICS));
        assertTrue(character.hasChoice("Replacement Test"));
        selector.withAttribute(Skill.DECEPTION);
        character.selectChoice(character.getChoice(1));
        assertTrue(character.hasAttribute(Skill.DECEPTION));
    }

}