package characterbuilder.character.choice;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.CONSTITUTION;
import static characterbuilder.character.attribute.AttributeType.LEVEL;
import characterbuilder.character.attribute.IntAttribute;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class AbilityScoreIncreaseTest {

    private AbilityScoreIncrease increase;
    private Character character;
    private TestChoiceSelector selector;

    @Before
    public void setup() {
        selector = new TestChoiceSelector();
        increase = new AbilityScoreIncrease();
        character = new Character();
        character.addChoiceList(selector);
    }

    @Test
    public void testNotAllowedBeforeAbilityScoresAdded() {
        assertFalse(increase.isAllowed(character));
    }

    @Test
    public void testAllowedAfterAbilityScoresAdded() {
        AttributeType.ABILITY_SCORES.forEach(s -> character.addAttribute(new IntAttribute(s, 10)));
        assertTrue(increase.isAllowed(character));
    }

    @Test(expected = IllegalStateException.class)
    public void testSelectWhenNotAllowed() {
        increase.select(character, selector);
    }

    @Test
    public void testIncreaseStrength() {
        AttributeType.ABILITY_SCORES.forEach(s -> character.addAttribute(new IntAttribute(s, 10)));
        selector.withAttribute(new IntAttribute(AttributeType.STRENGTH, 0));
        increase.select(character, selector);
        assertThat(character.getIntAttribute(AttributeType.STRENGTH), is(11));
    }

    @Test
    public void testIncreaseConstitutionIncreaseHitPoints() {
        character.addAttribute(new IntAttribute(LEVEL, 7));
        AttributeType.ABILITY_SCORES.forEach(s -> character.addAttribute(new IntAttribute(s, 10)));
        character.addAttribute(new IntAttribute(AttributeType.HIT_POINTS, 0));
        selector.withAttribute(new IntAttribute(CONSTITUTION, 0));
        increase.select(character, selector);
        assertThat(character.getIntAttribute(AttributeType.HIT_POINTS), is(0));
        increase.select(character, selector);
        assertThat(character.getIntAttribute(AttributeType.HIT_POINTS), is(7));
    }

}
