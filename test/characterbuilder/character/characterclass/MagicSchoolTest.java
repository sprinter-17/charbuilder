package characterbuilder.character.characterclass;

import characterbuilder.character.ability.Ability;
import characterbuilder.utils.TestCharacter;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

public class MagicSchoolTest {

    @Test
    public void testToString() {
        assertThat(MagicSchool.EVOCATION.toString(), is("Evocation"));
    }

    @Test
    public void testGenerateAttributes() {
        TestCharacter character = new TestCharacter();
        character.setLevel(2);
        MagicSchool.ABJURATION.generateLevelChoices(character);
        assertTrue(character.hasAttribute(Ability.ARCANE_WARD));
    }
}
