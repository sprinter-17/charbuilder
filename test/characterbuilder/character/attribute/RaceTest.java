package characterbuilder.character.attribute;

import characterbuilder.character.Character;
import static characterbuilder.character.attribute.AttributeType.HEIGHT;
import static characterbuilder.character.attribute.AttributeType.STRENGTH;
import static characterbuilder.character.attribute.AttributeType.WEIGHT;
import characterbuilder.character.choice.TestChoiceSelector;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class RaceTest {

    @Before
    public void setup() {
    }

    @Test
    public void testGetAttributeAdjustment() {
        assertThat(Race.LIGHFOOT_HALFLING.getAttributeAdjustment(STRENGTH), is(0));
        assertThat(Race.HUMAN.getAttributeAdjustment(STRENGTH), is(1));
    }

    @Test
    public void testStartingHeightAndWeight() {
        for (int i = 0; i < 10000; i++) {
            Character character = new Character();
            character.addChoiceList(new TestChoiceSelector());
            Race.HILL_DWARF.generateInitialChoices(character);
            Height height = character.getAttribute(HEIGHT);
            Weight weight = character.getAttribute(WEIGHT);
            assertTrue(height.compareTo(Height.INCH.times(46)) >= 0);
            assertTrue(height.compareTo(Height.INCH.times(52)) <= 0);
            assertTrue(weight.compareTo(Weight.LB.times(119)) >= 0);
            assertTrue(weight.compareTo(Weight.LB.times(214)) <= 0);
        }
    }
}
