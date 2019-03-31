package characterbuilder.character.characterclass.fighter;

import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.characterclass.CharacterClass;
import static characterbuilder.character.characterclass.fighter.Maneuver.*;
import characterbuilder.utils.TestCharacter;
import static java.util.stream.Collectors.joining;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

public class ManeuverTest {

    @Test
    public void testNames() {
        assertThat(COMMANDERS_STRIKE.toString(), is("Commander's Strike"));
        assertThat(FEINTING_ATTACK.toString(), is("Feinting Attack"));
    }

    @Test
    public void testDescription() {
        TestCharacter character = new TestCharacter().withScores(10);
        assertThat(parryDescription(character), containsString("1d6"));
        character.setLevel(CharacterClass.FIGHTER, 3);
        assertThat(parryDescription(character), containsString("1d8"));
        character.setLevel(CharacterClass.FIGHTER, 9);
        assertThat(parryDescription(character), containsString("1d8"));
        character.setLevel(CharacterClass.FIGHTER, 10);
        assertThat(parryDescription(character), containsString("1d10"));
        character.setScore(AttributeType.DEXTERITY, 15);
        assertThat(parryDescription(character), containsString("1d10+2"));
        character.setScore(AttributeType.DEXTERITY, 5);
        assertThat(parryDescription(character), containsString("1d10-3"));
    }

    private String parryDescription(TestCharacter character) {
        return PARRY.getDescription(character).collect(joining());
    }
}
