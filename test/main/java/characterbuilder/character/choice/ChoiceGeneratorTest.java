package characterbuilder.character.choice;

import characterbuilder.character.ability.FightingStyle;
import static characterbuilder.character.ability.Proficiency.*;
import static characterbuilder.character.ability.RacialTalent.BRAVE;
import characterbuilder.character.ability.Skill;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.characterclass.CharacterClass;
import characterbuilder.utils.TestCharacter;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChoiceGeneratorTest {

    private TestCharacter character;
    private ChoiceGenerator generator;

    @BeforeEach
    public void setup() {
        character = new TestCharacter();
        generator = new ChoiceGenerator();
    }

    @Test
    public void testAbilitiesChosenOnceOnly() {
        new AttributeChoice("Test", BRAVE, CARDS).select(character, character.getSelector());
        assertTrue(character.hasAttribute(BRAVE));
        assertFalse(character.hasAttribute(CARDS));
        new AttributeChoice("Test", BRAVE, CARDS).select(character, character.getSelector());
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
        character.setLevel(CharacterClass.BARBARIAN, 3);
        generator.generateChoices(character);
        assertFalse(character.hasAttribute(BRAVE));
        character.setLevel(CharacterClass.BARBARIAN, 4);
        generator.generateChoices(character);
        assertTrue(character.hasAttribute(BRAVE));
    }

    @Test
    public void testInitialClass() {
        generator.initialClass().addAttributes(BRAVE);
        character.setLevel(CharacterClass.BARBARIAN, 2);
        generator.generateChoices(character);
        assertFalse(character.hasAttribute(BRAVE));
        character.setLevel(CharacterClass.CLERIC, 1);
        generator.generateChoices(character);
        assertFalse(character.hasAttribute(BRAVE));
        character.setLevel(CharacterClass.BARBARIAN, 1);
        character.setLevel(CharacterClass.CLERIC, 1);
        generator.generateChoices(character);
        assertFalse(character.hasAttribute(BRAVE));
        character = new TestCharacter();
        character.setLevel(CharacterClass.BARBARIAN, 1);
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
