package characterbuilder.utils;

import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.INTELLIGENCE;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.character.attribute.Race;
import static characterbuilder.character.characterclass.CharacterClass.WIZARD;
import characterbuilder.character.attribute.DraconicAncestry;
import characterbuilder.character.spell.SpellCasting;
import static java.util.stream.Collectors.joining;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class TestUtils {

    public static void testDescriptions(Attribute[] attributes) {
        TestCharacter character = new TestCharacter();
        character.setLevel(1);
        character.addAttribute(Race.HALF_ELF);
        character.addAttribute(DraconicAncestry.BLUE);
        character.addAttribute(new IntAttribute(AttributeType.HIT_POINTS, 10));
        character.addAttribute(new SpellCasting("Casting", INTELLIGENCE, WIZARD, "All"));
        character.withScores(14);
        for (Attribute attribute : attributes) {
            try {
                String desc = attribute.getDescription(character).collect(joining());
                assertThat(attribute.toString() + ":" + desc, not(containsString("*ERROR*")));
            } catch (Exception ex) {
                fail(attribute.toString() + ":" + ex.toString());
            }
        }
    }
}
