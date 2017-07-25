package characterbuilder.character.equipment;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.IntAttribute;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class ArmourTest {

    private Character character;
    private IntAttribute dexterity;

    @Before
    public void setup() {
        character = new Character();
        dexterity = new IntAttribute(AttributeType.DEXTERITY, 10);
        character.addAttribute(dexterity);
    }

    @Test
    public void testGetDefaultArmourClass() {
        assertThat(Armour.getArmourClass(character), is(10));
    }

    @Test
    public void testDexBonus() {
        dexterity.setValue(14);
        assertThat(Armour.getArmourClass(character), is(12));
    }

    @Test
    public void testDexPenalty() {
        dexterity.setValue(4);
        assertThat(Armour.getArmourClass(character), is(7));
    }

    @Test
    public void testShield() {
        character.addEquipment(Armour.SHIELD);
        assertThat(Armour.getArmourClass(character), is(12));
    }

    @Test
    public void testArmour() {
        character.addEquipment(Armour.SCALE_MAIL_ARMOUR);
        assertThat(Armour.getArmourClass(character), is(14));
    }

    @Test
    public void testMaxArmour() {
        character.addEquipment(Armour.LEATHER_ARMOUR);
        character.addEquipment(Armour.SCALE_MAIL_ARMOUR);
        assertThat(Armour.getArmourClass(character), is(14));
    }

    @Test
    public void testNoDexBonusForHeavyArmour() {
        dexterity.setValue(18);
        character.addEquipment(Armour.SPLINT_ARMOUR);
        assertThat(Armour.getArmourClass(character), is(17));
    }

}
