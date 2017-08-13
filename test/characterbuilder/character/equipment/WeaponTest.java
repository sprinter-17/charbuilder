package characterbuilder.character.equipment;

import characterbuilder.character.ability.Ability;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.Value;
import characterbuilder.character.attribute.Weight;
import static characterbuilder.character.equipment.Weapon.BLOWGUN;
import static characterbuilder.character.equipment.Weapon.CLUB;
import static characterbuilder.character.equipment.Weapon.LONGBOW;
import static characterbuilder.character.equipment.Weapon.SPEAR;
import characterbuilder.character.saveload.TestDoc;
import characterbuilder.utils.TestCharacter;
import java.util.Optional;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class WeaponTest {

    private TestCharacter character;

    @Before
    public void setup() {
        character = new TestCharacter().withScores(10);
    }

    @Test
    public void testGetCategory() {
        assertThat(BLOWGUN.getCategory(), is(EquipmentCategory.MARTIAL_RANGED));
    }

    @Test
    public void testAsWeapon() {
        assertThat(CLUB.asWeapon(), is(Optional.of(Weapon.CLUB)));
    }

    @Test
    public void testMultipleAttacks() {
        assertThat(SPEAR.getAttacks(character).count(), is(2L));
    }

    @Test
    public void testMeleeAttack() {
        assertThat(CLUB.getAttacks(character).findAny().get().getDamage(), is("1d4"));
        assertThat(CLUB.getAttacks(character).findAny().get().getBonus(), is(0));
        character.setScore(AttributeType.STRENGTH, 14);
        assertThat(CLUB.getAttacks(character).findAny().get().getBonus(), is(2));
    }

    @Test
    public void testRangedAttack() {
        assertThat(LONGBOW.getAttacks(character).findAny().get().getDamage(), is("1d8"));
        assertThat(LONGBOW.getAttacks(character).findAny().get().getBonus(), is(0));
        character.setScore(AttributeType.DEXTERITY, 14);
        assertThat(LONGBOW.getAttacks(character).findAny().get().getBonus(), is(2));
        character.addAttribute(Ability.ARCHERY);
        assertThat(LONGBOW.getAttacks(character).findAny().get().getBonus(), is(4));
    }

    @Test
    public void testGetWeight() {
        assertThat(Weapon.DAGGER.getWeight(), is(Weight.LB));
    }

    @Test
    public void testGetValue() {
        assertThat(Weapon.GREATSWORD.getValue(), is(Value.gp(50)));
    }

    @Test
    public void testGetProficiency() {
        assertThat(Weapon.DART.getProficiency().getType(), is(AttributeType.WEAPON_PROFICIENCY));
    }

    @Test
    public void testArchery() {

    }

    @Test
    public void testSaveAndLoad() {
        assertThat(EquipmentCategory.load(Weapon.HAND_CROSSBOW.save(TestDoc.doc())),
            is(Weapon.HAND_CROSSBOW));
    }
}
