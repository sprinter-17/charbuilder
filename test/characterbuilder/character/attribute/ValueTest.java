package characterbuilder.character.attribute;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

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
    public void testEncodeDecode() throws Exception {
        for (Value v = Value.ZERO; !v.isGreaterThan(Value.cp(600)); v = v.add(Value.cp(7))) {
            assertThat(Value.valueOf(v.toString()), is(v));
        }
    }
}
