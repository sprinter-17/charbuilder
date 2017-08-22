package characterbuilder.character.choice;

import static characterbuilder.character.ability.Ability.*;
import characterbuilder.character.ability.FightingStyle;
import static characterbuilder.character.ability.Proficiency.*;
import characterbuilder.character.ability.Skill;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.utils.TestCharacter;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ChoiceGeneratorTest {

    private TestCharacter character;
    private ChoiceGenerator generator;
    private TestChoiceSelector selector;

    @Before
    public void setup() {
        character = new TestCharacter();
        generator = new ChoiceGenerator();
        selector = new TestChoiceSelector();
        character.addChoiceList(selector);
    }

    @Test
    public void testAbilitiesChosenOnceOnly() {
        new AttributeChoice("Test", BRAVE, CARDS).select(character, selector);
        assertTrue(character.hasAttribute(BRAVE));
        assertFalse(character.hasAttribute(CARDS));
        new AttributeChoice("Test", BRAVE, CARDS).select(character, selector);
        assertTrue(character.hasAttribute(CARDS));
    }

    @Test
    public void testAddConditionalChoice() {
        generator.cond(ch -> ch.hasAttribute(FightingStyle.ARCHERY)).addAttributes(BRAVE);
        generator.generateChoices(character);
        assertFalse(character.hasAttribute(BRAVE));
        character.addAttribute(FightingStyle.ARCHERY);
        generator.generateChoices(character);
        assertTrue(character.hasAttribute(BRAVE));
    }

    @Test
    public void testLevelCondition() {
        generator.level(4).addAttributes(BRAVE);
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
        generator.removeAttribute(BRAVE).addAttributes(BREWER);
        character.addAttribute(BRAVE);
        generator.generateChoices(character);
        assertFalse(character.hasAttribute(BRAVE));
        assertTrue(character.hasAttribute(BREWER));
    }

    @Test
    public void testMultipleChoices() {
        generator.addChoice(4, new AttributeChoice("Test",
            Skill.DECEPTION, Skill.ARCANA, Skill.HISTORY, Skill.INVESTIGATION));
        generator.generateChoices(character);
        character.selectChoice(character.getChoice(0));
        character.selectChoice(character.getChoice(0));
        character.selectChoice(character.getChoice(0));
        character.selectChoice(character.getChoice(0));
        assertThat(character.getAttributes(AttributeType.SKILL, Skill.class).count(), is(4L));
    }
}
