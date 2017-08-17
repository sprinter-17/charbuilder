package characterbuilder.character.equipment;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Ability;
import characterbuilder.character.ability.Feat;
import characterbuilder.character.attribute.AbilityScore;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.character.saveload.TestDoc;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class ArmourTest {

    private Character character;
    private AbilityScore dexterity;

    @Before
    public void setup() {
        character = new Character();
        dexterity = new AbilityScore(AttributeType.DEXTERITY, 10);
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
    public void testMaxTwoDexBonusForMediumArmour() {
        dexterity.setValue(18);
        character.addEquipment(Armour.HIDE_ARMOUR);
        assertThat(Armour.getArmourClass(character), is(14));
    }

    @Test
    public void testMaxThreeDexBonusForMediumArmourMaster() {
        dexterity.setValue(18);
        character.addEquipment(Armour.HIDE_ARMOUR);
        character.addAttribute(Feat.MEDIUM_ARMOUR_MASTER);
        assertThat(Armour.getArmourClass(character), is(15));
    }

    @Test
    public void testNoDexBonusForHeavyArmour() {
        dexterity.setValue(18);
        character.addEquipment(Armour.SPLINT_ARMOUR);
        assertThat(Armour.getArmourClass(character), is(17));
    }

    @Test
    public void testUnarmouredDefence() {
        IntAttribute constitution = new IntAttribute(AttributeType.CONSTITUTION, 10);
        character.addAttribute(constitution);
        character.addAttribute(Ability.UNARMORED_DEFENCE_BARBARIAN);
        assertThat(Armour.getArmourClass(character), is(10));
        constitution.setValue(14);
        assertThat(Armour.getArmourClass(character), is(12));
        constitution.setValue(18);
        assertThat(Armour.getArmourClass(character), is(14));
        dexterity.setValue(15);
        assertThat(Armour.getArmourClass(character), is(16));
        character.addEquipment(Armour.SHIELD);
        assertThat(Armour.getArmourClass(character), is(18));
        character.addEquipment(Armour.LEATHER_ARMOUR);
        assertThat(Armour.getArmourClass(character), is(15));
    }

    @Test
    public void testDefenseAbility() {
        character.addEquipment(Armour.SPLINT_ARMOUR);
        character.addAttribute(Ability.DEFENSE);
        assertThat(Armour.getArmourClass(character), is(18));
    }

    @Test
    public void testShieldBonus() {
        character.addEquipment(new EquipmentSet(Armour.SHIELD, 2, 1));
        assertThat(Armour.getArmourClass(character), is(14));
    }

    @Test
    public void testArmourBonus() {
        character.addEquipment(new EquipmentSet(Armour.LEATHER_ARMOUR, 3, 1));
        assertThat(Armour.getArmourClass(character), is(14));
    }

    @Test
    public void testSaveAndLoad() {
        assertThat(EquipmentCategory.load(Armour.SHIELD.save(TestDoc.doc())), is(Armour.SHIELD));
    }

}
