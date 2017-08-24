package characterbuilder.character.characterclass.druid;

import characterbuilder.utils.TestUtils;
import org.junit.Test;

public class DruidCircleTest {

    @Test
    public void testLegalDescriptionExpressions() {
        TestUtils.testDescriptions(DruidCircle.values());
        TestUtils.testDescriptions(DruidCircle.Ability.values());
    }
}
