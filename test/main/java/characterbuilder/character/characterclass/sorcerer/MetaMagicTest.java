package characterbuilder.character.characterclass.sorcerer;

import characterbuilder.utils.TestUtils;
import org.junit.jupiter.api.Test;

public class MetaMagicTest {

    @Test
    public void testValidDescriptionExpressions() {
        TestUtils.testDescriptions(MetaMagic.values());
    }
}
