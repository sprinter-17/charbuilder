package characterbuilder.character.choice;

import characterbuilder.character.Character;
import characterbuilder.character.equipment.MusicalInstrument;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EquipmentChoiceTest {

    private EquipmentChoice choice;
    private Character character;

    @BeforeEach
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
