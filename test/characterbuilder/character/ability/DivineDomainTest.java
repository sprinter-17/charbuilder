package characterbuilder.character.ability;

import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.characterclass.CharacterClass;
import characterbuilder.character.spell.Spell;
import static characterbuilder.character.spell.Spell.*;
import characterbuilder.character.spell.SpellCasting;
import characterbuilder.utils.TestCharacter;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class DivineDomainTest {

    private TestCharacter character;
    private SpellCasting casting;

    @Before
    public void setup() {
        character = new TestCharacter();
        CharacterClass.CLERIC.choose(character);
        casting = character.getAttribute(AttributeType.SPELLCASTING);
    }

    @Test
    public void testSpellList() {
        DivineDomain.LIFE.generateLevelChoices(character);
        assertTrue(casting.hasLearntSpell(BLESS));
        assertTrue(casting.hasLearntSpell(CURE_WOUNDS));
        assertFalse(casting.hasLearntSpell(FIREBALL));
    }

    @Test
    public void testLevelUpDivineDomainSpells() {
        character.setLevel(3);
        character.addAttribute(DivineDomain.LIFE);
        DivineDomain.LIFE.generateLevelChoices(character);
        assertTrue(casting.hasLearntSpell(Spell.LESSER_RESTORATION));
        assertTrue(casting.hasLearntSpell(Spell.SPIRITUAL_WEAPON));
    }
}
