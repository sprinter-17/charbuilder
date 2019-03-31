package characterbuilder.character.beast;

import static characterbuilder.character.beast.Beast.*;
import static java.util.stream.Collectors.joining;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import static org.junit.Assert.assertThat;
import org.junit.jupiter.api.Test;

public class BeastTest {

    @Test
    public void testAC() {
        assertThat(BROWN_BEAR, containsDescription("AC 11"));
        assertThat(PANTHER, containsDescription("AC 12"));
    }

    @Test
    public void testHP() {
        assertThat(WOLF, containsDescription("HP 11"));
        assertThat(BLACK_BEAR, containsDescription("HP 19"));
    }

    @Test
    public void testSpeed() {
        assertThat(BOAR, containsDescription("Speed 40"));
        assertThat(PANTHER, containsDescription("Speed 50, climb 40"));
    }

    @Test
    public void testSenses() {
        assertThat(BAT, containsDescription("blindsight 60 feet"));
        assertThat(BAT, containsDescription("passive perception 11"));
    }

    @Test
    public void testSkills() {
        assertThat(BOAR, containsDescription("If reduced to 0 HP"));
    }

    @Test
    public void testAttacks() {
        assertThat(WOLF, containsDescription("Bite"));
        assertThat(WOLF, containsDescription("Range 5"));
        assertThat(WOLF, containsDescription("Attack +4"));
        assertThat(WOLF, containsDescription("Damage 2d4+2 Piercing"));
    }

    private Matcher<Beast> containsDescription(String description) {
        return new TypeSafeDiagnosingMatcher<Beast>() {
            @Override
            protected boolean matchesSafely(Beast t, Description d) {
                d.appendText("Description is")
                    .appendValue(t.getDescription().collect(joining("\n")));
                return t.getDescription().anyMatch(desc -> desc.contains(description));
            }

            @Override
            public void describeTo(Description d) {
                d.appendText("Description includes").appendValue(description);
            }
        };
    }
}
