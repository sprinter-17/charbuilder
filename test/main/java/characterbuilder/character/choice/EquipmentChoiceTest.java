package characterbuilder.character.choice;

import characterbuilder.character.Character;
import characterbuilder.character.equipment.MusicalInstrument;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class EquipmentChoiceTest {

    private EquipmentChoice choice;
    private Character character;

    @Before
    public void setup() {
        choice = new EquipmentChoice("Choice", MusicalInstrument.BAGPIPES, MusicalInstrument.LYRE);
        character = new Character();
    }

    @Test
    public void testEquipmentAddedToCharacter() {
        choice.select(character, new TestChoiceSelector());
        assertTrue(character.hasEquipment(MusicalInstrument.BAGPIPES));
    }

}
