package characterbuilder.character.characterclass.sorcerer;

import characterbuilder.utils.TestUtils;
import org.junit.Test;

public class MetaMagicTest {

    @Test
    public void testValidDescriptionExpressions() {
        TestUtils.testDescriptions(MetaMagic.values());
    }
}
