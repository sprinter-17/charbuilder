package characterbuilder.character.characterclass;

import characterbuilder.character.spell.Spell;
import characterbuilder.utils.TestCharacter;
import static characterbuilder.utils.TestMatchers.hasSpellAbility;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class ElementalDisciplineTest {

    private TestCharacter character;

    @Before
    public void setup() {
        MagicSchool.values();
        Spell.values();
        character = new TestCharacter();
    }

    @Test
    public void testSpellDiscipline() {
        ElementalDiscipline.FIST_OF_FOUR_THUNDERS.choose(character);
        assertThat(character, hasSpellAbility(Spell.THUNDERWAVE));
    }
}
