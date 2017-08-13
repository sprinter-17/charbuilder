package characterbuilder.character.characterclass;

import characterbuilder.character.CharacterRandom;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.Race.HUMAN;
import static characterbuilder.character.characterclass.CharacterClass.WIZARD;
import characterbuilder.character.spell.SpellCasting;
import characterbuilder.utils.TestCharacter;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class WizardTest {

    private TestCharacter character;

    @Before
    public void setup() {
        character = new TestCharacter().withScores(10);
        HUMAN.choose(character);
        WIZARD.choose(character);
        character.generateHitPoints();
    }

    @Test
    public void testTotalSpellSlots() {
        for (int level = 1; level < 20; level++) {
            character.increaseLevel(new CharacterRandom());
        }
        SpellCasting casting = character.getAttribute(AttributeType.SPELLCASTING);
        assertThat(casting.getSlotsAtLevel(1), is(4));
        assertThat(casting.getSlotsAtLevel(2), is(3));
        assertThat(casting.getSlotsAtLevel(3), is(3));
        assertThat(casting.getSlotsAtLevel(4), is(3));
        assertThat(casting.getSlotsAtLevel(5), is(3));
        assertThat(casting.getSlotsAtLevel(6), is(2));
        assertThat(casting.getSlotsAtLevel(7), is(2));
        assertThat(casting.getSlotsAtLevel(8), is(1));
        assertThat(casting.getSlotsAtLevel(9), is(1));
    }
}
