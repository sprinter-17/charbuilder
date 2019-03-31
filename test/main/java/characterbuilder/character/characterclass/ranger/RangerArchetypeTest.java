package characterbuilder.character.characterclass.ranger;

import characterbuilder.character.characterclass.ranger.RangerArchetype;
import characterbuilder.character.attribute.AttributeType;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

public class RangerArchetypeTest {

    @Test
    public void testGetType() {
        assertThat(RangerArchetype.BEASTMASTER.getType(), is(AttributeType.RANGER_ARCHETYPE));
    }
}
