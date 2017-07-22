package characterbuilder.utils;

import characterbuilder.utils.StringUtils.UnitBuilder;
import static characterbuilder.utils.StringUtils.capitaliseEnumName;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class StringUtilsTest {

    private enum TestEnum {
        AA, BB, AA_BB
    };

    @Test
    public void testCapitaliseEnumName() {
        assertThat(capitaliseEnumName(TestEnum.AA.name()), is("Aa"));
        assertThat(capitaliseEnumName(TestEnum.AA_BB.name()), is("Aa Bb"));
    }

    @Test
    public void UnitBuilder() {
        assertThat(new UnitBuilder(0).toString(), is("-"));
        assertThat(new UnitBuilder(7).withUnit(2, "B2").withUnit(1, "B1").toString(), is("3B2 1B1"));
        assertThat(new UnitBuilder(13).withUnit(6, "B3").withUnit(2, "B2")
            .withUnit(1, "B1").toString(), is("2B3 1B1"));
    }
}
