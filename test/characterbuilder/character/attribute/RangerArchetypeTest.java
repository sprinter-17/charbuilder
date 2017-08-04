package characterbuilder.character.attribute;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

public class RangerArchetypeTest {

    @Test
    public void testGetType() {
        assertThat(RangerArchetype.BEASTMASTER.getType(), is(AttributeType.RANGER_ARCHETYPE));
    }
}
