package characterbuilder.character.ability;

import characterbuilder.character.attribute.AttributeType;
import characterbuilder.utils.TestCharacter;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

public class ProficiencyTest {

    private TestCharacter character;

    @Before
    public void setup() {
        character = new TestCharacter();
    }

    @Test
    public void testChoiceExcludesCurrentAttributes() {
        character.addAttribute(Proficiency.ELVISH);
        character.addChoice(Proficiency.choose(AttributeType.LANGUAGE));
        character.selectChoice("Language");
        assertFalse(character.hadOption(Proficiency.ELVISH));
    }

}
