package characterbuilder.character.characterclass.rogue;

import characterbuilder.utils.TestUtils;
import org.junit.Test;

public class RogueAbilityTest {

    @Test
    public void testLegalDescriptionExpressions() {
        TestUtils.testDescriptions(RogueAbility.values());
    }

}
