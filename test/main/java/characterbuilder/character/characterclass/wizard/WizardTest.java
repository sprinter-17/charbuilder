package characterbuilder.character.characterclass.wizard;

import static characterbuilder.character.attribute.Race.HUMAN;
import static characterbuilder.character.characterclass.CharacterClass.WIZARD;
import characterbuilder.character.spell.LearntSpell;
import characterbuilder.character.spell.Spell;
import characterbuilder.character.spell.SpellCasting;
import characterbuilder.utils.TestCharacter;
import junit.framework.AssertionFailedError;
import static org.junit.Assert.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WizardTest {

    private TestCharacter character;

    @BeforeEach
    public void setup() {
        character = new TestCharacter().withScores(10);
        HUMAN.choose(character);
        WIZARD.choose(character);
        character.generateHitPoints();
    }

    @Test
    public void testWizardSpellUnprepared() {
        SpellCasting casting = character.getSpellCasting("Wizard");
        character.selectChoice("Wizard Spell", "Alarm");
        LearntSpell alarm = casting.getLearntSpells().filter(ls -> ls.isSpell(Spell.ALARM))
            .findAny().orElseThrow(() -> new AssertionFailedError("No alarm spell"));
        assertFalse(alarm.isPrepared());
    }
}
