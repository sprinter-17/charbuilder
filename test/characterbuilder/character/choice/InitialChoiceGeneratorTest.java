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
        character.addChoiceList(selector);
    }

    @Test
    public void testNewCharacterChoices() {
        generator.generateChoices(character);
        assertTrue(character.hasChoice(CHOOSE_RACE.toString()));
        assertTrue(character.hasChoice(CHOOSE_CLASS.toString()));
    }

    @Test
    public void testAttributeAdded() {
        assertFalse(character.hasAttribute(AttributeType.RACE));
        CHOOSE_RACE.select(character, selector);
        assertThat(character.getAttribute(AttributeType.RACE), is(Race.values()[0]));
    }

    @Test
    public void testChoiceRemoved() {
        generator.generateChoices(character);
        assertTrue(character.hasChoice(CHOOSE_RACE.toString()));
        character.selectChoice(CHOOSE_RACE);
        assertFalse(character.hasChoice(CHOOSE_RACE.toString()));
    }

    @Test
    public void testChooseClassesCreatesLevelAndXP() {
        assertFalse(character.hasAttribute(AttributeType.LEVEL));
        assertFalse(character.hasAttribute(AttributeType.EXPERIENCE_POINTS));
        CHOOSE_CLASS.select(character, selector);
        assertThat(character.getIntAttribute(AttributeType.LEVEL), is(1));
        assertThat(character.getIntAttribute(AttributeType.EXPERIENCE_POINTS), is(0));
    }

    @Test
    public void testGenerateAbilityScores() {
        assertFalse(character.hasChoice(GENERATE_ABILITY_SCORES.toString()));
        character.addAttribute(CharacterClass.FIGHTER);
        assertFalse(character.hasChoice(GENERATE_ABILITY_SCORES.toString()));
        CHOOSE_RACE.select(character, selector);
        assertTrue(character.hasChoice(GENERATE_ABILITY_SCORES.toString()));
    }

}
