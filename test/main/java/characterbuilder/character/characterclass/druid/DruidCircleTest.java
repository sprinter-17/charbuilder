package characterbuilder.character.characterclass.druid;

import characterbuilder.character.CharacterRandom;
import characterbuilder.character.characterclass.CharacterClass;
import characterbuilder.character.spell.Spell;
import characterbuilder.character.spell.SpellCasting;
import characterbuilder.utils.TestCharacter;
import static characterbuilder.utils.TestMatchers.hasPreparedSpell;
import characterbuilder.utils.TestUtils;
import static org.junit.Assert.assertThat;
import org.junit.jupiter.api.Test;

public class DruidCircleTest {

    @Test
    public void testCircleSpells() {
        TestCharacter character = new TestCharacter().withScores(10);
        character.increaseLevel(CharacterClass.DRUID, new CharacterRandom());
        character.increaseLevel(CharacterClass.DRUID, new CharacterRandom());
        character.increaseLevel(CharacterClass.DRUID, new CharacterRandom());
        SpellCasting casting = character.getSpellCasting("Druid");
        DruidCircle.Ability.CIRCLE_SPELLS.choose(character);
        character.selectChoice("Circle Spells Terrain", "Desert");
        assertThat(casting, hasPreparedSpell(Spell.BLUR));
        assertThat(casting, hasPreparedSpell(Spell.SILENCE));
    }

    @Test
    public void testLegalDescriptionExpressions() {
        TestUtils.testDescriptions(DruidCircle.values());
        TestUtils.testDescriptions(DruidCircle.Ability.values());
    }
}
