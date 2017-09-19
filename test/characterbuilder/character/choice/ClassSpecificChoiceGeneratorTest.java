package characterbuilder.character.choice;

import static characterbuilder.character.ability.RacialTalent.BRAVE;
import characterbuilder.character.characterclass.CharacterClass;
import characterbuilder.character.characterclass.CharacterClassLevel;
import characterbuilder.utils.TestCharacter;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ClassSpecificChoiceGeneratorTest {

    private ClassSpecificChoiceGenerator generator;
    private TestCharacter character;

    @Before
    public void setup() {
        generator = new ClassSpecificChoiceGenerator(CharacterClass.FIGHTER);
        character = new TestCharacter();
    }

    @Test
    public void testLevel() {
        generator.level(2).addAttributes(BRAVE);
        character.addAttribute(new CharacterClassLevel(CharacterClass.BARBARIAN, 2));
        generator.generateChoices(character);
        assertFalse(character.hasAttribute(BRAVE));
        character.addAttribute(new CharacterClassLevel(CharacterClass.FIGHTER, 2));
        generator.generateChoices(character);
        assertTrue(character.hasAttribute(BRAVE));
    }

}
