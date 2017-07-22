package characterbuilder.character.choice;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class ChoiceListTest {

    private ChoiceList choices;

    @Before
    public void setup() {
        choices = new ChoiceList();
    }

    @Test
    public void testGetChoices() {
        assertThat(choices.getChoices().count(), is(0L));
        choices.addChoice((character, selector) -> {
        });
        assertThat(choices.getChoices().count(), is(1L));
    }

}
