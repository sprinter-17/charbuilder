package characterbuilder.character.characterclass;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

public class MagicSchoolTest {

    @Test
    public void testToString() {
        assertThat(MagicSchool.EVOCATION.toString(), is("Evocation"));
    }
}
