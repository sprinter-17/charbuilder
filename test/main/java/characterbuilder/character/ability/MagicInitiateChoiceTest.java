package characterbuilder.character.ability;

import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.spell.LearntSpell;
import characterbuilder.character.spell.Spell;
import characterbuilder.utils.TestCharacter;
import static characterbuilder.utils.TestMatchers.hasChoice;
import static characterbuilder.utils.TestMatchers.hasSpellAbility;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MagicInitiateChoiceTest {

    private TestCharacter character;

    @BeforeEach
    public void setup() {
        character = new TestCharacter().withScores(10);
    }

    @Test
    public void testMagicInitiate() {
        character.addChoice(new MagicInitiateChoice());
        assertThat(character, hasChoice("Magic Initiate Class"));
        character.selectChoice("Magic Initiate Class", "Wizard");
        assertThat(character, hasChoice("Initiate Cantrip"));
        assertThat(character, hasChoice("Initiate Spell"));
        character.selectChoice("Initiate Cantrip", "Mage Hand");
        assertThat(character, hasSpellAbility(Spell.MAGE_HAND));
        character.selectChoice("Initiate Cantrip", "Acid Splash");
        assertThat(character, hasSpellAbility(Spell.ACID_SPLASH));
        character.selectChoice("Initiate Spell", "Magic Missile");
        assertThat(character, hasSpellAbility(Spell.MAGIC_MISSILE));
    }

    @Test
    public void testCannotSelectExistingCantrip() {
        LearntSpell spell = new LearntSpell(Spell.SHOCKING_GRASP, AttributeType.INTELLIGENCE);
        character.addAttribute(spell);
        character.addChoice(new MagicInitiateChoice());
        character.selectChoice("Magic Initiate Class", "Wizard");
        character.selectChoice("Initiate Cantrip", "Acid Splash");
        assertFalse(character.hadOption(spell));
    }

}
