package characterbuilder.character.choice;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Proficiency;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class AbilityFeatureTest {

    @Test
    public void testMakeChoice() {
        AttributeFeature feature = new AttributeFeature(Proficiency.BREWER);
        Character character = new Character();
        assertFalse(character.hasAttribute(Proficiency.BREWER));
        feature.makeChoice(character, null);
        assertTrue(character.hasAttribute(Proficiency.BREWER));
    }

}
