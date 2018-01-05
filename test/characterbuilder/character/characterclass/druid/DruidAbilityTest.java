package characterbuilder.character.characterclass.druid;

import characterbuilder.character.characterclass.CharacterClass;
import characterbuilder.utils.TestCharacter;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class DruidAbilityTest {

    private TestCharacter druid;

    @Before
    public void setup() {
        druid = new TestCharacter().withScores(10);
        druid.setLevel(CharacterClass.DRUID, 1);
    }

    @Test
    public void testWildShapeCR() {
        druid.setLevel(CharacterClass.DRUID, 2);
        assertThat(DruidAbility.WILD_SHAPE.getDescription(druid).findFirst().get(),
            containsString("CR&frac14"));
        druid.setLevel(CharacterClass.DRUID, 4);
        assertThat(DruidAbility.WILD_SHAPE.getDescription(druid).findFirst().get(),
            containsString("CR&frac12"));
    }
}
