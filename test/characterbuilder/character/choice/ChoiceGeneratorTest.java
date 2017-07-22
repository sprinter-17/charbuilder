package characterbuilder.character.choice;

import characterbuilder.character.Character;
import static characterbuilder.character.ability.Ability.*;
import static characterbuilder.character.ability.Proficiency.*;
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

public class ChoiceGeneratorTest {

    private Character character;
    private ChoiceGenerator generator;
    private TestChoiceSelector selector;

    @Before
    public void setup() {
        character = new Character();
        generator = new ChoiceGenerator();
        selector = new TestChoiceSelector();
    }

    @Test
    public void testAbilitiesChosenOnceOnly() {
        new AttributeChoice("Test", BRAVE, CARDS).makeChoice(character, selector);
        assertTrue(character.hasAttribute(BRAVE));
        assertFalse(character.hasAttribute(CARDS));
        new AttributeChoice("Test", BRAVE, CARDS).makeChoice(character, selector);
        assertTrue(character.hasAttribute(CARDS));
    }

    @Test
    public void testAddConditionalChoice() {
        generator.cond(ch -> ch.hasAttribute(ARCHERY))
            .addChoice(() -> new AttributeFeature(BRAVE));
        generator.generateChoices(character);
        assertFalse(character.hasAttribute(BRAVE));
        character.addAttribute(ARCHERY);
        generator.generateChoices(character);
        assertTrue(character.hasAttribute(BRAVE));
    }

    @Test
    public void testLevelCondition() {
        generator.level(4).addChoice(() -> new AttributeFeature(BRAVE));
        generator.generateChoices(character);
        IntAttribute level = new IntAttribute(AttributeType.LEVEL, 3);
        character.addAttribute(level);
        generator.generateChoices(character);
        assertFalse(character.hasAttribute(BRAVE));
        level.setValue(4);
        generator.generateChoices(character);
        assertTrue(character.hasAttribute(BRAVE));
    }

    @Test
    public void testReplaceAttribute() {
        generator.replaceAttribute(BRAVE, BREWER);
        character.addAttribute(BRAVE);
        generator.generateChoices(character);
        assertFalse(character.hasAttribute(BRAVE));
        assertTrue(character.hasAttribute(BREWER));
    }

    @Test
    public void testIncreaseConstitution() {
        character.addAttribute(new IntAttribute(LEVEL, 7));
        character.addAttribute(new IntAttribute(CONSTITUTION, 10));
        character.addAttribute(new IntAttribute(AttributeType.HIT_POINTS, 0));
        selector.withAttribute(new IntAttribute(CONSTITUTION, 0));
        ChoiceGenerator.abilityScoreIncrease(1).makeChoice(character, selector);
        assertThat(character.getIntAttribute(AttributeType.HIT_POINTS), is(0));
        ChoiceGenerator.abilityScoreIncrease(1).makeChoice(character, selector);
        assertThat(character.getIntAttribute(AttributeType.HIT_POINTS), is(7));
    }

}
