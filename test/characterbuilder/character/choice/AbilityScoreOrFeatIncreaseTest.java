package characterbuilder.character.choice;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Feat;
import characterbuilder.character.attribute.AbilityScore;
import static characterbuilder.character.attribute.AttributeType.*;
import characterbuilder.character.attribute.IntAttribute;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class AbilityScoreOrFeatIncreaseTest {

    private AbilityScoreOrFeatIncrease increase;
    private Character character;
    private TestChoiceSelector selector;

    @Before
    public void setup() {
        selector = new TestChoiceSelector();
        increase = new AbilityScoreOrFeatIncrease();
        character = new Character();
        character.addChoiceList(selector);
    }

    @Test
    public void testNotAllowedBeforeAbilityScoresAdded() {
        assertFalse(increase.isAllowed(character));
    }

    @Test
    public void testAllowedAfterAbilityScoresAdded() {
        AbilityScore.SCORES.forEach(s -> character.addAttribute(new AbilityScore(s, 10)));
        assertTrue(increase.isAllowed(character));
    }

    @Test(expected = IllegalStateException.class)
    public void testSelectWhenNotAllowed() {
        increase.select(character, selector);
    }

    @Test
    public void testIncreaseStrength() {
        AbilityScore.SCORES.forEach(s -> character.addAttribute(new AbilityScore(s, 10)));
        selector.withChoice("+1 Intelligence");
        increase.select(character, selector);
        assertThat(character.getIntAttribute(INTELLIGENCE), is(11));
    }

    @Test
    public void testIncreaseConstitutionIncreaseHitPoints() {
        character.addAttribute(new IntAttribute(LEVEL, 7));
        AbilityScore.SCORES.forEach(s -> character.addAttribute(new AbilityScore(s, 10)));
        character.addAttribute(new IntAttribute(HIT_POINTS, 0));
        selector.withChoice("+1 Constitution");
        increase.select(character, selector);
        assertThat(character.getIntAttribute(HIT_POINTS), is(0));
        increase.select(character, selector);
        assertThat(character.getIntAttribute(HIT_POINTS), is(7));
    }

    @Test
    public void testAllowSpecificScores() {
        AbilityScore.SCORES.forEach(s -> character.addAttribute(new AbilityScore(s, 12)));
        increase = new AbilityScoreOrFeatIncrease("Test", INTELLIGENCE, WISDOM);
        character.addChoice(increase);
        assertTrue(character.hasChoice("Test"));
        character.selectChoice(increase);
        assertThat(character.getIntAttribute(INTELLIGENCE), is(13));
    }

    @Test
    public void testWithFeats() {
        assertThat(increase.toString(), is("Feat or Ability Score Increase"));
        AbilityScore.SCORES.forEach(s -> character.addAttribute(new AbilityScore(s, 12)));
        character.addChoice(increase);
        character.selectChoice(increase);
        assertTrue(selector.hadOption(Feat.ATHLETE));
    }

    @Test
    public void testWithoutFeats() {
        increase = increase.withoutFeats();
        assertThat(increase.toString(), is("Ability Score Increase"));
        AbilityScore.SCORES.forEach(s -> character.addAttribute(new AbilityScore(s, 12)));
        character.addChoice(increase);
        character.selectChoice(increase);
        assertFalse(selector.hadOption(Feat.ATHLETE));
    }

    @Test
    public void testExistingFeatsExcluded() {
        AbilityScore.SCORES.forEach(s -> character.addAttribute(new AbilityScore(s, 12)));
        character.addAttribute(Feat.ATHLETE);
        character.addChoice(increase);
        character.selectChoice(increase);
        assertFalse(selector.hadOption(Feat.ATHLETE));
    }

}
