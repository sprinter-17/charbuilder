package characterbuilder.character;

import characterbuilder.character.ability.Feat;
import characterbuilder.character.attribute.AbilityScore;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.CONSTITUTION;
import static characterbuilder.character.attribute.AttributeType.EXPERIENCE_POINTS;
import static characterbuilder.character.attribute.AttributeType.HIT_POINTS;
import static characterbuilder.character.attribute.AttributeType.NAME;
import static characterbuilder.character.attribute.AttributeType.RACE;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.character.attribute.Race;
import characterbuilder.character.attribute.StringAttribute;
import characterbuilder.character.attribute.Value;
import characterbuilder.character.attribute.Weight;
import static characterbuilder.character.attribute.Weight.LB;
import characterbuilder.character.characterclass.CharacterClass;
import characterbuilder.character.characterclass.CharacterClassLevel;
import characterbuilder.character.characterclass.barbarian.BarbarianAbility;
import characterbuilder.character.choice.TestOptionChoice;
import characterbuilder.character.equipment.Armour;
import characterbuilder.character.equipment.Attack;
import characterbuilder.character.equipment.MagicItem;
import characterbuilder.character.equipment.Weapon;
import characterbuilder.character.spell.Spell;
import characterbuilder.character.spell.SpellAbility;
import characterbuilder.utils.TestCharacter;
import java.util.Optional;
import java.util.stream.Stream;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class CharacterTest {

    private TestCharacter character;
    private CharacterRandom random;

    @Before
    public void setup() {
        random = new CharacterRandom() {
            @Override
            public int nextAbilityScore() {
                return 10;
            }

            @Override
            public int nextHitPoints(int max) {
                return max;
            }

            @Override
            public Value nextStartingWealth(int factor) {
                return Value.GP;
            }

            @Override
            public int roll(String dice) {
                return 1;
            }
        };
        character = new TestCharacter();
    }

    @Test
    public void testSwapAttributes() {
        character.addAttribute(Race.HILL_DWARF);
        character.addAttribute(new AbilityScore(AttributeType.STRENGTH, 19));
        character.addAttribute(new AbilityScore(AttributeType.CONSTITUTION, 12));
        character.swapAttributes(AttributeType.STRENGTH, AttributeType.CONSTITUTION);
        assertThat(character.getIntAttribute(AttributeType.STRENGTH), is(10));
        assertThat(character.getIntAttribute(AttributeType.CONSTITUTION), is(20));
    }

    @Test
    public void testInventoryWeight() {
        character.addEquipment(Armour.LEATHER_ARMOUR.makeSet(1));
        assertThat(character.getInventoryWeight(), is(LB.times(10)));
        character.addEquipment(Armour.LEATHER_ARMOUR.makeSet(1));
        assertThat(character.getInventoryWeight(), is(LB.times(20)));
        character.addEquipment(Armour.STUDDED_LEATHER_ARMOUR.makeSet(1));
        assertThat(character.getInventoryWeight(), is(LB.times(33)));
        character.removeEquipment(Armour.LEATHER_ARMOUR.makeSet(1));
        assertThat(character.getInventoryWeight(), is(LB.times(23)));
    }

    @Test
    public void testProficiencyBonus() {
        setLevel(CharacterClass.ROGUE, 1);
        assertThat(character.getProficiencyBonus(), is(2));
        character.increaseLevel(CharacterClass.ROGUE, random);
        assertThat(character.getProficiencyBonus(), is(2));
        character.increaseLevel(CharacterClass.ROGUE, random);
        assertThat(character.getProficiencyBonus(), is(2));
        character.increaseLevel(CharacterClass.ROGUE, random);
        assertThat(character.getProficiencyBonus(), is(2));
        character.increaseLevel(CharacterClass.ROGUE, random);
        assertThat(character.getProficiencyBonus(), is(3));
        character.increaseLevel(CharacterClass.ROGUE, random);
        assertThat(character.getProficiencyBonus(), is(3));
        character.increaseLevel(CharacterClass.ROGUE, random);
        assertThat(character.getProficiencyBonus(), is(3));
        character.increaseLevel(CharacterClass.ROGUE, random);
        assertThat(character.getProficiencyBonus(), is(3));
        character.increaseLevel(CharacterClass.ROGUE, random);
        assertThat(character.getProficiencyBonus(), is(4));
        character.increaseLevel(CharacterClass.ROGUE, random);
        assertThat(character.getProficiencyBonus(), is(4));
        character.increaseLevel(CharacterClass.ROGUE, random);
        assertThat(character.getProficiencyBonus(), is(4));
        character.increaseLevel(CharacterClass.ROGUE, random);
        assertThat(character.getProficiencyBonus(), is(4));
        character.increaseLevel(CharacterClass.ROGUE, random);
        assertThat(character.getProficiencyBonus(), is(5));
    }

    @Test
    public void testSavingThrow() {
        AbilityScore con = new AbilityScore(CONSTITUTION, 12);
        character.addAttribute(con);
        assertThat(character.getSavingsThrowBonus(CONSTITUTION), is(1));
        character.setLevel(CharacterClass.ROGUE, 4);
        con.setProficientSaves();
        assertThat(character.getSavingsThrowBonus(CONSTITUTION), is(3));
    }

    @Test
    public void testHitPointIncrease() {
        setLevel(CharacterClass.FIGHTER, 1);
        assertThat(character.getIntAttribute(AttributeType.HIT_POINTS), is(10));
        character.increaseLevel(CharacterClass.FIGHTER, random);
        assertThat(character.getIntAttribute(AttributeType.HIT_POINTS), is(20));
    }

    @Test
    public void testConstitutionBonus() {
        setLevel(CharacterClass.FIGHTER, 1);
        character.getAttribute(CONSTITUTION, IntAttribute.class).setValue(15);
        character.increaseLevel(CharacterClass.FIGHTER, random);
        assertThat(character.getIntAttribute(AttributeType.HIT_POINTS), is(22));
        character.getAttribute(CONSTITUTION, IntAttribute.class).setValue(3);
        character.increaseLevel(CharacterClass.FIGHTER, random);
        assertThat(character.getIntAttribute(AttributeType.HIT_POINTS), is(28));
    }

    @Test
    public void testHitPointIncreaseMinimum() {
        random = new CharacterRandom() {
            @Override
            public int nextHitPoints(int max) {
                return 1;
            }
        };
        setLevel(CharacterClass.FIGHTER, 1);
        character.getAttribute(CONSTITUTION, IntAttribute.class).setValue(1);
        character.increaseLevel(CharacterClass.FIGHTER, random);
        assertThat(character.getIntAttribute(AttributeType.HIT_POINTS), is(11));
    }

    @Test
    public void testHillDwarfHitPointBonus() {
        setLevel(CharacterClass.FIGHTER, 1);
        character.removeAttributesOfType(RACE);
        character.addAttribute(Race.HILL_DWARF);
        character.increaseLevel(CharacterClass.FIGHTER, random);
        assertThat(character.getIntAttribute(AttributeType.HIT_POINTS), is(21));
    }

    @Test(expected = IllegalStateException.class)
    public void testMaxLevel() {
        for (int i = 1; i < 40; i++) {
            character.increaseLevel(CharacterClass.FIGHTER, random);
            assertThat(character.getLevel(), is(Math.min(20, i)));
        }
    }

    @Test
    public void testXP() {
        setLevel(CharacterClass.FIGHTER, 1);
        for (int level = 1; level < 20; level++) {
            character.increaseLevel(CharacterClass.FIGHTER, random);
            assertThat(character.getIntAttribute(EXPERIENCE_POINTS),
                is(Character.XP_LEVELS[level]));
        }
    }

    @Test
    public void testMinXP() {
        setLevel(CharacterClass.FIGHTER, 1);
        character.getAttribute(EXPERIENCE_POINTS, IntAttribute.class).setValue(100000);
        character.increaseLevel(CharacterClass.FIGHTER, random);
        assertThat(character.getIntAttribute(EXPERIENCE_POINTS), is(100000));
    }

    @Test
    public void addingSuperAbility() {
        Attribute superSet = () -> AttributeType.SKILL;

        Attribute subSet = new Attribute() {
            public AttributeType getType() {
                return AttributeType.SKILL;
            }

            public Optional<Attribute> getSuperSet() {
                return Optional.of(superSet);
            }
        };
        character.addAttribute(subSet);
        assertTrue(character.hasAttribute(subSet));
        assertFalse(character.hasAttribute(superSet));
        character.addAttribute(superSet);
        assertTrue(character.hasAttribute(subSet));
        assertTrue(character.hasAttribute(superSet));
        assertThat(character.getAllAttributes().count(), is(1L));
        character.addAttribute(subSet);
        assertTrue(character.hasAttribute(subSet));
        assertTrue(character.hasAttribute(superSet));
        assertThat(character.getAllAttributes().count(), is(1L));
    }

    @Test
    public void testSpeed() {
        character.addAttribute(Race.MOUNTAIN_DWARF);
        assertThat(character.getSpeed(), is(25));
        character.addAttribute(BarbarianAbility.FAST_MOVEMENT);
        assertThat(character.getSpeed(), is(35));
        character.addEquipment(Armour.SHIELD);
        assertThat(character.getSpeed(), is(35));
        character.addEquipment(Armour.LEATHER_ARMOUR);
        assertThat(character.getSpeed(), is(25));
        character.addAttribute(Feat.MOBILE);
        assertThat(character.getSpeed(), is(35));
    }

    @Test
    public void testCarryingCapacity() {
        character.addAttribute(new IntAttribute(AttributeType.STRENGTH, 14));
        assertThat(character.getCarryingCapacity(), is(Weight.lb(14 * 15)));
        character.addAttribute(BarbarianAbility.ASPECT_OF_BEAST_BEAR);
        assertThat(character.getCarryingCapacity(), is(Weight.lb(14 * 15 * 2)));
    }

    @Test
    public void testHideNotAllowedChoices() {
        TestOptionChoice choice = new TestOptionChoice();
        choice.setAllowed(false);
        character.addChoice(choice);
        assertThat(character.getAllowedChoices().count(), is(0L));
        choice.setAllowed(true);
        assertThat(character.getAllowedChoices().count(), is(1L));
    }

    @Test
    public void testSpellAttacks() {
        character.addAttribute(new SpellAbility(Spell.CHILL_TOUCH, CONSTITUTION));
        Attack cantrip = character.getAttacks().filter(at -> at.getName().equals("Chill Touch"))
            .findAny().get();
        assertThat(cantrip.getRange(), is("120"));
    }

    @Test
    public void testMagicWeaponBonus() {
        character.addEquipment(new MagicItem(Weapon.MACE, 3));
        Attack attack = character.getAttacks()
            .filter(at -> at.getName().equals("Mace"))
            .findAny().get();
        assertThat(attack.getBonus(), is(3));
    }

    @Test
    public void testErrors() {
        assertThat(character.getErrors(), containsString("Name"));
        character.addAttribute(new StringAttribute(NAME, "Fred"));
        assertThat(character.getErrors(), not(containsString("Name")));
        assertThat(character.getErrors(), containsString("Trait"));
        character.addAttribute(new StringAttribute(AttributeType.TRAIT, "Funny"));
        assertThat(character.getErrors(), not(containsString("Trait")));
    }

    @Test
    public void testAllowedMultiClasses() {
        character.withScores(10);
        assertThat(character.allowedMultiClasses().count(), is(0L));
        character.setScore(AttributeType.CHARISMA, 13);
        assertThat(character.allowedMultiClasses().count(), is(3L));
        CharacterClass.WARLOCK.choose(character);
        assertThat(character.allowedMultiClasses().count(), is(2L));
    }

    private void setLevel(CharacterClass charClass, int level) {
        Stream.of(
            new CharacterClassLevel(charClass, level),
            Race.HUMAN,
            new IntAttribute(HIT_POINTS, charClass.getHitDie() * level),
            new IntAttribute(EXPERIENCE_POINTS, Character.XP_LEVELS[level - 1]),
            new AbilityScore(CONSTITUTION, 10)).forEach(attr -> attr.choose(character));
    }
}
