package characterbuilder.character.choice;

import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.CharacterClass;
import characterbuilder.character.attribute.Race;
import static characterbuilder.character.choice.InitialChoiceGenerator.CHOOSE_CLASS;
import static characterbuilder.character.choice.InitialChoiceGenerator.CHOOSE_RACE;
import static characterbuilder.character.choice.InitialChoiceGenerator.GENERATE_ABILITY_SCORES;
import static java.util.stream.Collectors.toList;
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
        assertTrue(character.getChoices().has(CHOOSE_RACE));
        assertTrue(character.getChoices().has(CHOOSE_CLASS));
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
        assertTrue(character.getChoices().has(CHOOSE_RACE));
        character.getChoices().select(character,
            character.getChoices().stream().collect(toList()).indexOf(CHOOSE_RACE));
        assertFalse(character.getChoices().has(CHOOSE_RACE));
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
        assertFalse(character.getChoices().has(GENERATE_ABILITY_SCORES));
        character.addAttribute(CharacterClass.FIGHTER);
        assertFalse(character.getChoices().has(GENERATE_ABILITY_SCORES));
        CHOOSE_RACE.select(character, selector);
        assertTrue(character.getChoices().has(GENERATE_ABILITY_SCORES));
    }

}
