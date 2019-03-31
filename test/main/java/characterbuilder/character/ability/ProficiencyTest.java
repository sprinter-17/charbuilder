package characterbuilder.character.ability;

import characterbuilder.character.attribute.AttributeType;
import characterbuilder.utils.TestCharacter;
import static org.junit.Assert.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProficiencyTest {

    private TestCharacter character;

    @BeforeEach
    public void setup() {
        character = new TestCharacter();
    }

    @Test
    public void testChoiceExcludesCurrentAttributes() {
        character.addAttribute(Proficiency.ACTOR);
        character.addChoice(Proficiency.choose(AttributeType.ENTERTAINER_ROUTINE));
        character.selectChoice("Entertainer Routine");
        assertFalse(character.hadOption(Proficiency.ACTOR));
    }

}
