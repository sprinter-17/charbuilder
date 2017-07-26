package characterbuilder.character.choice;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Value;
import characterbuilder.character.equipment.EquipmentSet;
import characterbuilder.character.equipment.EquipmentType;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class MultiChoiceTest {

    @Test
    public void testCopy() {
        Character character = new Character();
        character.addEquipment(new EquipmentSet(EquipmentType.GOLD_PIECE, 10));
        Choice choice = (ch, sel) -> {
            ch.spendTreasure(Value.GP);
            sel.choiceMade();
        };
        MultiChoice multi = new MultiChoice(3, choice);
        character.getChoices().addChoice(multi.copy());
        character.getChoices().addChoice(multi.copy());
        while (!character.getChoices().isEmpty()) {
            character.getChoices().getChoice(0).makeChoice(character, new TestChoiceSelector());
        }
        assertThat(character.getTreasureValue(), is(Value.gp(4)));
    }

}
