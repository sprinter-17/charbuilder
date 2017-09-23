package characterbuilder.character.characterclass.fighter;

import characterbuilder.character.CharacterRandom;
import characterbuilder.character.characterclass.CharacterClass;
import characterbuilder.utils.TestCharacter;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class FighterTest {

    private TestCharacter character;

    @Before
    public void setup() {
        character = new TestCharacter().withScores(10);
    }

    @Test
    public void testAddEquipmentOnInitialClass() {
        character.increaseLevel(CharacterClass.FIGHTER, new CharacterRandom());
        assertTrue(character.hasChoice("Skill"));
        assertTrue(character.hasChoice("Armour"));
    }

    @Test
    public void testDoNotAddEquipmentOnMulticlass() {
        character.setLevel(CharacterClass.CLERIC, 1);
        character.increaseLevel(CharacterClass.FIGHTER, new CharacterRandom());
        assertTrue(character.hasChoice("Fighting Style"));
        assertFalse(character.hasChoice("Armour"));
    }

}
