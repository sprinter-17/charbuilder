package characterbuilder.character.ability;

import characterbuilder.utils.TestUtils;
import org.junit.Test;

public class RacialTalentTest {

    @Test
    public void testLegalDescriptionExpressions() {
        TestUtils.testDescriptions(RacialTalent.values());
    }
}
