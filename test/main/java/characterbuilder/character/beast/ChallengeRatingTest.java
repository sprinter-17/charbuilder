package characterbuilder.character.beast;

import static characterbuilder.character.beast.ChallengeRating.*;
import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

public class ChallengeRatingTest {

    @Test
    public void testCompare() {
        assertTrue(CR1_8.compareTo(CR1_4) < 0);
        assertTrue(CR1.compareTo(CR1_2) > 0);
    }

}
