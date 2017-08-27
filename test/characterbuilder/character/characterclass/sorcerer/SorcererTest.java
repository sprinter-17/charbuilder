package characterbuilder.character.characterclass.sorcerer;

import characterbuilder.character.ability.RacialTalent;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.character.characterclass.CharacterClass;
import static characterbuilder.character.characterclass.sorcerer.SorcererAbility.*;
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
    public void testDraconicSorcerersDoNotHaveBreathWeapon() {
        TestCharacter character = new TestCharacter();
        CharacterClass.SORCERER.choose(character);
        SorcerousOrigin.DRACONIC_BLOODLINE.choose(character);
        character.selectChoice("Draconic Ancestry", "Black");
        assertFalse(character.hasAttribute(RacialTalent.BREATH_WEAPON));
    }

    @Test
    public void testDescriptionExpressions() {
        TestUtils.testDescriptions(SorcererAbility.values());
    }

}
