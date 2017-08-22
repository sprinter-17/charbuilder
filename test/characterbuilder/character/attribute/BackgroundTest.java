package characterbuilder.character.attribute;

import characterbuilder.character.attribute.Background.BackgroundAbility;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

public class BackgroundTest {

    @Test
    public void testAbilityName() {
        assertThat(BackgroundAbility.CITY_SECRETS.toString(), is("City Secrets"));
        assertThat(BackgroundAbility.SHIPS_PASSAGE.toString(), is("Ship's Passage"));
    }

}
