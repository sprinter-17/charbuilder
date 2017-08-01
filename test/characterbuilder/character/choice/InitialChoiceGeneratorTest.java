package characterbuilder.character.choice;

import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.CharacterClass;
import characterbuilder.character.attribute.Race;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class InitialChoiceGeneratorTest {

    private characterbuilder.character.Character character;
    private ChoiceGenerator generator;
    private ChoiceSelector selector;

    @Before
    public void setup() {
        character = new characterbuilder.character.Character();
        generator = new InitialChoiceGenerator();
        selector = new TestChoiceSelector();
        character.addChoiceList(selector);
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
        character.getChoice(0).select(character, selector);
        assertThat(character.getAttribute(AttributeType.RACE), is(Race.values()[0]));
    }

    @Test
    public void testChoiceRemoved() {
        assertTrue(character.hasChoice("Race"));
        character.selectChoice(character.getChoice(0));
        assertFalse(character.hasChoice("Race"));
    }

    @Test
    public void testChooseClassesCreatesLevelAndXP() {
        assertFalse(character.hasAttribute(AttributeType.LEVEL));
        assertFalse(character.hasAttribute(AttributeType.EXPERIENCE_POINTS));
        CharacterClass.BARD.choose(character);
        assertThat(character.getIntAttribute(AttributeType.LEVEL), is(1));
        assertThat(character.getIntAttribute(AttributeType.EXPERIENCE_POINTS), is(0));
    }

    @Test
    public void testGenerateAbilityScores() {
        assertFalse(character.hasChoice("Generate ability scores"));
        CharacterClass.BARD.choose(character);
        assertFalse(character.hasChoice("Generate ability scores"));
        Race.MOUNTAIN_DWARF.choose(character);
        assertTrue(character.hasChoice("Generate ability scores"));
    }

}
