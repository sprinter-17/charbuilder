package characterbuilder.character.choice;

import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.CharacterClass;
import characterbuilder.character.attribute.Race;
import static characterbuilder.character.choice.InitialChoiceGenerator.CHOOSE_CLASS;
import static characterbuilder.character.choice.InitialChoiceGenerator.CHOOSE_RACE;
import static characterbuilder.character.choice.InitialChoiceGenerator.GENERATE_ABILITY_SCORES;
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
    }

    @Test
    public void testNewCharacterChoices() {
        generator.generateChoices(character);
        assertTrue(character.getChoices().hasChoice(CHOOSE_RACE));
        assertTrue(character.getChoices().hasChoice(CHOOSE_CLASS));
    }

    @Test
    public void testAttributeAdded() {
        assertFalse(character.hasAttribute(AttributeType.RACE));
        CHOOSE_RACE.makeChoice(character, selector);
        assertThat(character.getAttribute(AttributeType.RACE), is(Race.values()[0]));
    }

    @Test
    public void testChoiceRemoved() {
        generator.generateChoices(character);
        assertTrue(character.getChoices().hasChoice(CHOOSE_RACE));
        CHOOSE_RACE.makeChoice(character, selector);
        assertFalse(character.getChoices().hasChoice(CHOOSE_RACE));
    }

    @Test
    public void testChooseClassesCreatesLevelAndXP() {
        assertFalse(character.hasAttribute(AttributeType.LEVEL));
        assertFalse(character.hasAttribute(AttributeType.EXPERIENCE_POINTS));
        CHOOSE_CLASS.makeChoice(character, selector);
        assertThat(character.getIntAttribute(AttributeType.LEVEL), is(1));
        assertThat(character.getIntAttribute(AttributeType.EXPERIENCE_POINTS), is(0));
    }

    @Test
    public void testGenerateAbilityScores() {
        assertFalse(character.getChoices().hasChoice(GENERATE_ABILITY_SCORES));
        character.addAttribute(CharacterClass.FIGHTER);
        assertFalse(character.getChoices().hasChoice(GENERATE_ABILITY_SCORES));
        CHOOSE_RACE.makeChoice(character, selector);
        assertTrue(character.getChoices().hasChoice(GENERATE_ABILITY_SCORES));
    }

}
