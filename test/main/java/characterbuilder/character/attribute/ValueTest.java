package characterbuilder.character.attribute;

import characterbuilder.character.saveload.TestDoc;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

public class ValueTest {

    @Test
    public void testAdd() {
        assertThat(Value.SP.add(Value.SP), is(Value.sp(2)));
    }

    @Test
    public void testSubtract() {
        assertThat(Value.SP.subtract(Value.SP), is(Value.ZERO));
    }

    @Test
    public void testTimes() {
        assertThat(Value.SP.times(10), is(Value.GP));
    }

    @Test
    public void testIsGreaterThan() {
        assertTrue(Value.GP.isGreaterThan(Value.SP));
        assertFalse(Value.GP.isGreaterThan(Value.GP));
        assertFalse(Value.SP.isGreaterThan(Value.GP));
    }

    @Test
    public void testToString() {
        assertThat(Value.GP.times(7).toString(), is("7GP"));
        assertThat(Value.SP.times(17).toString(), is("1GP 7SP"));
    }

    @Test
    public void testSaveAndLoad() {
        assertThat(Value.load(Value.sp(372).save(TestDoc.doc())), is(Value.sp(372)));
    }
}
