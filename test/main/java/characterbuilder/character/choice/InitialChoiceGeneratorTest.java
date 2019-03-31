package characterbuilder.character.choice;

import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.Race;
import characterbuilder.character.characterclass.CharacterClass;
import characterbuilder.utils.TestCharacter;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class InitialChoiceGeneratorTest {

    private TestCharacter character;
    private ChoiceGenerator generator;

    @Before
    public void setup() {
        character = new TestCharacter();
        generator = new InitialChoiceGenerator();
        generator.generateChoices(character);
    }

    @Test
    public void testNewCharacterChoices() {
        assertTrue(character.hasChoice("Race"));
        assertTrue(character.hasChoice("Class"));
    }

    @Test
    public void testAttributeAdded() {
        assertFalse(character.hasAttribute(AttributeType.RACE));
        character.selectChoice("Race", "Human");
        assertThat(character.getAttribute(AttributeType.RACE), is(Race.HUMAN));
    }

    @Test
    public void testChoiceRemoved() {
        assertTrue(character.hasChoice("Race"));
        character.selectChoice(character.getChoice(1));
        assertFalse(character.hasChoice("Race"));
    }

    @Test
    public void testChooseClassesCreatesLevel() {
        assertThat(character.getLevel(), is(0));
        CharacterClass.BARD.choose(character);
        assertThat(character.getLevel(), is(1));
    }

    @Test
    public void testGenerateAbilityScores() {
        assertFalse(character.hasChoice("Generate ability scores"));
        CharacterClass.BARD.choose(character);
        assertFalse(character.hasChoice("Generate ability scores"));
        Race.MOUNTAIN_DWARF.choose(character);
        assertTrue(character.hasChoice("Generate ability scores"));
    }

    @Test
    public void testRacialBonuses() {
        Race.HUMAN.choose(character);
        CharacterClass.CLERIC.choose(character);
        character.selectChoice("Generate ability scores");
        assertThat(character.getIntAttribute(AttributeType.STRENGTH), is(11));
    }

}
