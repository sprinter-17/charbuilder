package characterbuilder.character.ability;

import static characterbuilder.character.ability.Ability.ARCHERY;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class AbilityChoiceTest {

    @Test
    public void testMandatory() {
        AttributeChoice choice = new AttributeChoice(ARCHERY);
        assertThat(choice.getName(), is("Archery"));
        assertThat(choice.getCount(), is(1));
        assertThat(choice.getAttributes().collect(toList()), is(singletonList(ARCHERY)));
    }

}
