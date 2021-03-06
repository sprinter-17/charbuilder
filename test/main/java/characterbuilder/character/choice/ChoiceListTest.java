package characterbuilder.character.choice;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChoiceListTest {

    private ChoiceList choices;

    @BeforeEach
    public void setup() {
        choices = new ChoiceList(new TestChoiceSelector());
    }

    @Test
    public void testGetChoices() {
        assertThat(choices.stream().count(), is(0L));
        choices.add(new TestOptionChoice());
        assertThat(choices.stream().count(), is(1L));
    }

    @Test
    public void testChoiceRemoved() {
        OptionChoice choice = new TestOptionChoice();
        choices.add(choice);
        assertThat(choices.stream().count(), is(1L));
        choices.select(null, choice);
        assertThat(choices.stream().count(), is(0L));
    }
}
