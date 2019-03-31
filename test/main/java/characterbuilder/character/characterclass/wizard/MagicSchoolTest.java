package characterbuilder.character.characterclass.wizard;

import static characterbuilder.character.attribute.AttributeType.INTELLIGENCE;
import characterbuilder.character.characterclass.CharacterClass;
import characterbuilder.character.spell.LearntSpell;
import characterbuilder.character.spell.Spell;
import characterbuilder.utils.TestCharacter;
import static characterbuilder.utils.TestMatchers.hasAttribute;
import static characterbuilder.utils.TestMatchers.hasChoice;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

public class MagicSchoolTest {

    @Test
    public void testToString() {
        assertThat(MagicSchool.EVOCATION.toString(), is("Evocation"));
    }

    @Test
    public void testGenerateAttributes() {
        TestCharacter character = new TestCharacter();
        character.setLevel(CharacterClass.WIZARD, 2);
        MagicSchool.ABJURATION.generateLevelChoices(character);
        assertThat(character, hasAttribute("Arcane Ward"));
    }

    @Test
    public void testImprovedMinorIllusion() {
        TestCharacter character = new TestCharacter().withScores(10);
        character.setLevel(CharacterClass.WIZARD, 2);
        MagicSchool.ILLUSION.choose(character);
        assertTrue(character.hasAttribute(new LearntSpell(Spell.MINOR_ILLUSION, INTELLIGENCE)));
    }

    @Test
    public void testImprovedMinorIllusionCantrip() {
        TestCharacter character = new TestCharacter().withScores(10);
        character.setLevel(CharacterClass.WIZARD, 2);
        character.addAttribute(new LearntSpell(Spell.MINOR_ILLUSION, INTELLIGENCE));
        MagicSchool.ILLUSION.choose(character);
        assertThat(character, hasChoice("Cantrip"));
    }

}
