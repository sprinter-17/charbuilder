package characterbuilder.character.ability;

import characterbuilder.character.attribute.AttributeType;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ExpertiseTest {

    private Expertise expertise;

    @Before
    public void setup() {
        expertise = new Expertise(Skill.ARCANA);
    }

    @Test
    public void testGetType() {
        assertThat(expertise.getType(), is(AttributeType.EXPERTISE));
    }

    @Test
    public void testIsFor() {
        assertTrue(expertise.isFor(Skill.ARCANA));
    }

    @Test
    public void testEncodeDecode() {
        assertTrue(Expertise.valueOf(expertise.encode()).isFor(Skill.ARCANA));
    }
}
