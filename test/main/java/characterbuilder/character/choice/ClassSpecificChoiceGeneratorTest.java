package characterbuilder.character.choice;

import static characterbuilder.character.ability.RacialTalent.BRAVE;
import characterbuilder.character.characterclass.CharacterClass;
import characterbuilder.utils.TestCharacter;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClassSpecificChoiceGeneratorTest {

    private ClassSpecificChoiceGenerator generator;
    private TestCharacter character;

    @BeforeEach
    public void setup() {
        generator = new ClassSpecificChoiceGenerator(CharacterClass.FIGHTER);
        character = new TestCharacter();
    }

    @Test
    public void testLevel() {
        generator.level(2).addAttributes(BRAVE);
        character.setLevel(CharacterClass.BARBARIAN, 2);
        generator.generateChoices(character);
        assertFalse(character.hasAttribute(BRAVE));
        character.setLevel(CharacterClass.FIGHTER, 2);
        generator.generateChoices(character);
        assertTrue(character.hasAttribute(BRAVE));
    }
}
