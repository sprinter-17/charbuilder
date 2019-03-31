package characterbuilder.character.attribute;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

public class WealthTest {

    @Test
    public void testToString() {
        assertThat(Value.ZERO.toString(), is("-"));
        assertThat(Value.CP.times(15).toString(), is("1SP 5CP"));
        assertThat(Value.CP.times(150).toString(), is("1GP 5SP"));
        assertThat(Value.CP.times(1500).toString(), is("15GP"));
        assertThat(Value.CP.times(15000).toString(), is("150GP"));
    }

}
