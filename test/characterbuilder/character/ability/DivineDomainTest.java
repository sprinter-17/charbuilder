package characterbuilder.character.ability;

import characterbuilder.character.spell.Spell;
import characterbuilder.character.Character;
import static characterbuilder.character.spell.Spell.*;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.IntAttribute;
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class DivineDomainTest {

    private Character character;

    @Before
    public void setup() {
        character = new Character();
    }

    @Test
    public void testEmptySpellList() {
        DivineDomain.LIFE.generateLevelChoices(character);
        assertFalse(Arrays.stream(Spell.values()).anyMatch(character::hasAttribute));
    }

    @Test
    public void testSpellList() {
        character.addAttribute(new IntAttribute(AttributeType.LEVEL, 1));
        DivineDomain.LIFE.generateLevelChoices(character);
        assertTrue(character.hasAttribute(BLESS));
        assertTrue(character.hasAttribute(CURE_WOUNDS));
        assertFalse(character.hasAttribute(FIREBALL));
    }

}
