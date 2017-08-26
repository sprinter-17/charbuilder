package characterbuilder.character.characterclass.sorcerer;

import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.IntAttribute;
import static characterbuilder.character.characterclass.sorcerer.Sorcerer.Ability.*;
import characterbuilder.character.equipment.Armour;
import static characterbuilder.character.saveload.TestDoc.doc;
import characterbuilder.utils.TestCharacter;
import characterbuilder.utils.TestUtils;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

public class SorcererTest {

    @Test
    public void testSaveAndLoadAbility() {
        assertThat(AttributeType.load(FONT_OF_MAGIC.save(doc())), is(FONT_OF_MAGIC));
    }

    @Test
    public void testDraconicResilience() {
        TestCharacter character = new TestCharacter().withScores(14);
        character.setLevel(4);
        character.addAttribute(new IntAttribute(AttributeType.HIT_POINTS, 20));
        DRACONIC_RESILIENCE.choose(character);
        assertThat(character.getIntAttribute(AttributeType.HIT_POINTS), is(24));
        assertThat(Armour.getArmourClass(character), is(15));
    }

    @Test
    public void testDescriptionExpressions() {
        TestUtils.testDescriptions(Sorcerer.Ability.values());
    }

}
