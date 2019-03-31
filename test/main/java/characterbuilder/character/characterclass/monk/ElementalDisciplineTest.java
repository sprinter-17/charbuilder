package characterbuilder.character.characterclass.monk;

import characterbuilder.character.characterclass.monk.ElementalDiscipline;
import characterbuilder.character.characterclass.wizard.MagicSchool;
import characterbuilder.character.spell.Spell;
import characterbuilder.utils.TestCharacter;
import static characterbuilder.utils.TestMatchers.hasSpellAbility;
import static org.junit.Assert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ElementalDisciplineTest {

    private TestCharacter character;

    @BeforeEach
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
