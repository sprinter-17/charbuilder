package characterbuilder.character.choice;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Ability;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class AttributeFeatureTest {

    @Test
    public void testReplaceAttribute() {
        AttributeFeature attrFeature = new AttributeFeature(Ability.NIMBLE, Ability.ARCHERY);
        Character character = new Character();
        character.addAttribute(Ability.ARCHERY);
        attrFeature.addTo(character);
        assertFalse(character.hasAttribute(Ability.ARCHERY));
        assertTrue(character.hasAttribute(Ability.NIMBLE));
    }

}
