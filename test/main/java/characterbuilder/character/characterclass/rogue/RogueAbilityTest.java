package characterbuilder.character.characterclass.rogue;

import characterbuilder.utils.TestUtils;
import org.junit.jupiter.api.Test;

public class RogueAbilityTest {

    @Test
    public void testLegalDescriptionExpressions() {
        TestUtils.testDescriptions(RogueAbility.values());
    }

}
