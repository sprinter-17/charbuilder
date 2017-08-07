package characterbuilder.character.ability;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.character.choice.TestChoiceSelector;
import characterbuilder.utils.TestCharacter;
import java.util.stream.IntStream;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class FeatTest {

    private Character character;

    @Before
    public void setup() {
        character = new TestCharacter().withScores(10);
        character.addChoiceList(new TestChoiceSelector());
    }

    @Test
    public void testIncreaseAttribute() {
        assertTrue(Feat.ACTOR.getDescription(character)
            .anyMatch(desc -> desc.contains("Increase Charisma")));
        Feat.ACTOR.choose(character);
        assertThat(character.getIntAttribute(AttributeType.CHARISMA), is(11));
    }

    @Test
    public void testFeatAddChoice() {
        Feat.ATHLETE.choose(character);
        assertTrue(character.hasChoice("Increase Strength or Dexterity"));
    }

    @Test
    public void testFeatPrerequisite() {
        assertFalse(Feat.DEFENSIVE_DUELIST.isAllowed(character));
        character.getAttribute(AttributeType.DEXTERITY, IntAttribute.class).setValue(13);
        assertTrue(Feat.DEFENSIVE_DUELIST.isAllowed(character));
    }

    @Test
    public void testDoNotAddToCharacter() {
        Feat.WEAPON_MASTER.choose(character);
        assertFalse(character.hasAttribute(Feat.WEAPON_MASTER));
    }

    @Test
    public void testExpressionInDescription() {
        assertTrue(Feat.DURABLE.getDescription(character)
            .anyMatch(desc -> desc.contains("minimum roll is 2")));
        character.getAttribute(AttributeType.CONSTITUTION, IntAttribute.class).setValue(16);
        assertTrue(Feat.DURABLE.getDescription(character)
            .anyMatch(desc -> desc.contains("minimum roll is 6")));
    }

    @Test
    public void testElementalAdeptCanBeChosenFourTimes() {
        character.addAttribute(new SpellCasting(AttributeType.STRENGTH));
        IntStream.rangeClosed(1, 4).forEach(i -> {
            assertTrue(Feat.ELEMENTAL_ADEPT.isAllowed(character));
            Feat.ELEMENTAL_ADEPT.choose(character);
            character.selectChoice(character.getChoice(0));
            assertThat(character.getAttributeCount(AttributeType.ELEMENTAL_ADEPT), is(i));
        });
        assertFalse(Feat.ELEMENTAL_ADEPT.isAllowed(character));
    }
}
