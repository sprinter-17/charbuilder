package characterbuilder.character.characterclass;

import characterbuilder.character.ability.Ability;
import characterbuilder.character.ability.Skill;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.characterclass.EldritchInvocation.*;
import characterbuilder.utils.TestCharacter;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class EldritchInvocationTest {

    private TestCharacter character;

    @Before
    public void setup() {
        Ability.values(); // initialisation error
        character = new TestCharacter().withScores(10);
    }

    @Test
    public void testType() {
        assertThat(DEVILS_SIGHT.getType(), is(AttributeType.ELDRITCH_INVOCATION));
    }

    @Test
    public void testIsAllowed() {
        character.setLevel(8);
        assertFalse(ASCENDANT_STEP.isAllowed(character));
        character.setLevel(9);
        assertTrue(ASCENDANT_STEP.isAllowed(character));
    }

    @Test
    public void testGenerateWithoutSave() {
        BEGUILING_INFLUENCE.generateInitialChoices(character);
        assertFalse(character.hasAttribute(BEGUILING_INFLUENCE));
        assertTrue(character.hasAttribute(Skill.DECEPTION));
        assertTrue(character.hasAttribute(Skill.PERSUASION));
    }

    @Test
    public void testLifedrinkerDescription() {
        character.setScore(AttributeType.CHARISMA, 8);
        assertTrue(LIFEDRINKER.getDescription(character)
            .anyMatch(desc -> desc.contains("+1 necrotic damage")));
        character.setScore(AttributeType.CHARISMA, 16);
        assertTrue(LIFEDRINKER.getDescription(character)
            .anyMatch(desc -> desc.contains("+3 necrotic damage")));
    }

    @Test
    public void testGetChoice() {
        character.addChoice(EldritchInvocation.getChoice(2));
        assertTrue(character.hasChoice("Eldritch Invocation (x2)"));
        assertFalse(character.getAllAttributes()
            .anyMatch(attr -> attr.hasType(AttributeType.ELDRITCH_INVOCATION)));
        character.selectChoice("Eldritch Invocation", "Armour of Shadows");
        assertTrue(character.hasAttribute(ARMOUR_OF_SHADOWS));
    }

    @Test
    public void testReplacementAddsChoice() {
        character.addAttribute(ARMOUR_OF_SHADOWS);
        character.addChoice(EldritchInvocation.getReplacement());
        assertFalse(character.hasChoice("Eldritch Invocation"));
        character.selectChoice("Replace Eldritch Invocation", "Armour of Shadows");
        assertTrue(character.hasChoice("Eldritch Invocation"));
        assertFalse(character.hasAttribute(ARMOUR_OF_SHADOWS));
    }

    @Test
    public void testReplacementIncreasesChoiceCount() {
        character.addAttribute(ARMOUR_OF_SHADOWS);
        character.addChoice(EldritchInvocation.getChoice(1));
        character.addChoice(EldritchInvocation.getReplacement());
        character.selectChoice("Replace Eldritch Invocation", "Armour of Shadows");
        assertTrue(character.hasChoice("Eldritch Invocation (x2)"));
    }

    @Test
    public void testNoReplacement() {
        character.addAttribute(ARMOUR_OF_SHADOWS);
        character.addChoice(EldritchInvocation.getReplacement());
        assertFalse(character.hasChoice("Eldritch Invocation"));
        character.selectChoice("Replace Eldritch Invocation", "None");
        assertFalse(character.hasChoice("Eldritch Invocation"));
        assertTrue(character.hasAttribute(ARMOUR_OF_SHADOWS));
    }
}
