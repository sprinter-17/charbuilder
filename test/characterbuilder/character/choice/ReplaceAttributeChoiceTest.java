package characterbuilder.character.choice;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import characterbuilder.character.ability.Skill;
import characterbuilder.utils.TestCharacter;

public class ReplaceAttributeChoiceTest {

    private ReplaceAttributeChoice<?> choice;
    private TestCharacter character;

    @Before
    public void setup() {
        choice = new ReplaceAttributeChoice<>("Test", ch -> Arrays.stream(Skill.values()));
        character = new TestCharacter();
        character.addChoice(choice);
    }

    @Test
    public void testSelect() {
        character.addAttribute(Skill.ATHLETICS);
        character.getSelector().withAttribute(Skill.ATHLETICS);
        character.selectChoice(choice);
        assertFalse(character.hasAttribute(Skill.ATHLETICS));
        assertTrue(character.hasChoice("Replace Test"));
        character.getSelector().withAttribute(Skill.DECEPTION);
        character.selectChoice();
        assertTrue(character.hasAttribute(Skill.DECEPTION));
    }

}
