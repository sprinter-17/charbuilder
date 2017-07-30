package characterbuilder.character;

import characterbuilder.character.ability.Ability;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.CONSTITUTION;
import static characterbuilder.character.attribute.AttributeType.EXPERIENCE_POINTS;
import static characterbuilder.character.attribute.AttributeType.HIT_POINTS;
import static characterbuilder.character.attribute.AttributeType.LEVEL;
import static characterbuilder.character.attribute.AttributeType.RACE;
import characterbuilder.character.attribute.CharacterClass;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.character.attribute.Race;
import characterbuilder.character.attribute.StringAttribute;
import characterbuilder.character.attribute.Value;
import characterbuilder.character.attribute.Weight;
import static characterbuilder.character.attribute.Weight.LB;
import characterbuilder.character.choice.TestChoiceSelector;
import characterbuilder.character.choice.TestOptionChoice;
import characterbuilder.character.equipment.Armour;
import characterbuilder.character.equipment.EquipmentSet;
import java.util.Optional;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CharacterTest {

    private Character character;
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
        character = new Character();
        character.addChoiceList(new TestChoiceSelector());
    }

    @Test(expected = IllegalStateException.class)
    public void testGenerateAbilityScoresBeforeRaceSet() {
        character.addAttribute(CharacterClass.ROGUE);
        character.generateAbilityScores(random);
    }

    @Test(expected = IllegalStateException.class)
    public void testGenerateAbilityScoresBeforeClassSet() {
        character.addAttribute(Race.LIGHFOOT_HALFLING);
        character.generateAbilityScores(random);
    }

    @Test
    public void testGenerateAbilityScores() {
        character.addAttribute(Race.HILL_DWARF);
        character.addAttribute(CharacterClass.WIZARD);
        assertTrue(AttributeType.ABILITY_SCORES.stream().noneMatch(character::hasAttribute));
        character.generateAbilityScores(random);
        assertTrue(AttributeType.ABILITY_SCORES.stream().allMatch(character::hasAttribute));
    }

    @Test
    public void testNullAttribute() {
        assertFalse(character.getAttribute(AttributeType.NAME).isPresent());
        character.addAttribute(new StringAttribute(AttributeType.NAME, "Fred"));
        assertTrue(character.getAttribute(AttributeType.NAME).isPresent());
    }

    @Test
    public void testHuman() {
        character.addAttribute(Race.HUMAN);
        assertThat(character.getAttribute(AttributeType.RACE), is(Race.HUMAN));
        character.addAttribute(CharacterClass.CLERIC);
        character.generateAbilityScores(random);
        assertThat(character.getIntAttribute(AttributeType.STRENGTH), is(11));
    }

    @Test
    public void testRaceBonus() {
        character.addAttribute(Race.WOOD_ELF);
        character.addAttribute(CharacterClass.ROGUE);
        character.generateAbilityScores(random);
        assertThat(character.getIntAttribute(AttributeType.WISDOM), is(11));
        assertThat(character.getIntAttribute(AttributeType.DEXTERITY), is(12));
    }

    @Test
    public void testSwapAttributes() {
        character.addAttribute(Race.HILL_DWARF);
        character.addAttribute(new IntAttribute(AttributeType.STRENGTH, 19));
        character.addAttribute(new IntAttribute(AttributeType.CONSTITUTION, 12));
        character.swapAttributes(AttributeType.STRENGTH, AttributeType.CONSTITUTION);
        assertThat(character.getIntAttribute(AttributeType.STRENGTH), is(10));
        assertThat(character.getIntAttribute(AttributeType.CONSTITUTION), is(20));
    }

    @Test
    public void testInventoryWeight() {
        character.addEquipment(new EquipmentSet(Armour.LEATHER_ARMOUR));
        assertThat(character.getInventoryWeight(), is(LB.times(10)));
        character.addEquipment(new EquipmentSet(Armour.LEATHER_ARMOUR));
        assertThat(character.getInventoryWeight(), is(LB.times(20)));
        character.addEquipment(new EquipmentSet(Armour.STUDDED_LEATHER_ARMOUR));
        assertThat(character.getInventoryWeight(), is(LB.times(33)));
        character.removeEquipment(new EquipmentSet(Armour.LEATHER_ARMOUR));
        assertThat(character.getInventoryWeight(), is(LB.times(23)));
    }

    @Test
    public void testProficiencyBonus() {
        setLevel(CharacterClass.ROGUE, 1);
        assertThat(character.getProficiencyBonus(), is(2));
        character.increaseLevel(random);
        assertThat(character.getProficiencyBonus(), is(2));
        character.increaseLevel(random);
        assertThat(character.getProficiencyBonus(), is(2));
        character.increaseLevel(random);
        assertThat(character.getProficiencyBonus(), is(2));
        character.increaseLevel(random);
        assertThat(character.getProficiencyBonus(), is(3));
        character.increaseLevel(random);
        assertThat(character.getProficiencyBonus(), is(3));
        character.increaseLevel(random);
        assertThat(character.getProficiencyBonus(), is(3));
        character.increaseLevel(random);
        assertThat(character.getProficiencyBonus(), is(3));
        character.increaseLevel(random);
        assertThat(character.getProficiencyBonus(), is(4));
        character.increaseLevel(random);
        assertThat(character.getProficiencyBonus(), is(4));
        character.increaseLevel(random);
        assertThat(character.getProficiencyBonus(), is(4));
        character.increaseLevel(random);
        assertThat(character.getProficiencyBonus(), is(4));
        character.increaseLevel(random);
        assertThat(character.getProficiencyBonus(), is(5));
    }

    @Test
    public void testHitPointIncrease() {
        setLevel(CharacterClass.FIGHTER, 1);
        assertThat(character.getIntAttribute(AttributeType.HIT_POINTS), is(10));
        character.increaseLevel(random);
        assertThat(character.getIntAttribute(AttributeType.HIT_POINTS), is(20));
    }

    @Test
    public void testConstitutionBonus() {
        setLevel(CharacterClass.FIGHTER, 1);
        character.getAttribute(CONSTITUTION, IntAttribute.class).setValue(15);
        character.increaseLevel(random);
        assertThat(character.getIntAttribute(AttributeType.HIT_POINTS), is(22));
        character.getAttribute(CONSTITUTION, IntAttribute.class).setValue(3);
        character.increaseLevel(random);
        assertThat(character.getIntAttribute(AttributeType.HIT_POINTS), is(28));
    }

    @Test
    public void testHitPointIncreaseMinimum() {
        random = mock(CharacterRandom.class);
        when(random.nextHitPoints(anyInt())).thenReturn(1);
        setLevel(CharacterClass.FIGHTER, 1);
        character.getAttribute(CONSTITUTION, IntAttribute.class).setValue(1);
        character.increaseLevel(random);
        assertThat(character.getIntAttribute(AttributeType.HIT_POINTS), is(11));
    }

    @Test
    public void testHillDwarfHitPointBonus() {
        setLevel(CharacterClass.FIGHTER, 1);
        character.removeAttributesOfType(RACE);
        character.addAttribute(Race.HILL_DWARF);
        character.increaseLevel(random);
        assertThat(character.getIntAttribute(AttributeType.HIT_POINTS), is(21));
    }

    @Test
    public void testMaxLevel() {
        setLevel(CharacterClass.CLERIC, 1);
        for (int i = 1; i < 40; i++) {
            assertThat(character.getIntAttribute(AttributeType.LEVEL), is(Math.min(20, i)));
            character.increaseLevel(random);
        }
    }

    @Test
    public void testXP() {
        setLevel(CharacterClass.FIGHTER, 1);
        for (int level = 0; level < 20; level++) {
            assertThat(character.getIntAttribute(EXPERIENCE_POINTS),
                is(Character.XP_LEVELS[level]));
            character.increaseLevel(random);
        }
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
        character.addAttribute(Ability.FAST_MOVEMENT);
        assertThat(character.getSpeed(), is(35));
        character.addEquipment(Armour.SHIELD);
        assertThat(character.getSpeed(), is(35));
        character.addEquipment(Armour.LEATHER_ARMOUR);
        assertThat(character.getSpeed(), is(25));
    }

    @Test
    public void testCarryingCapacity() {
        character.addAttribute(new IntAttribute(AttributeType.STRENGTH, 14));
        assertThat(character.getCarryingCapacity(), is(Weight.lb(14 * 15)));
        character.addAttribute(Ability.ASPECT_OF_BEAST_BEAR);
        assertThat(character.getCarryingCapacity(), is(Weight.lb(14 * 15 * 2)));
    }

    @Test
    public void testHideNotAllowedChoices() {
        TestOptionChoice choice = new TestOptionChoice();
        choice.setAllowed(false);
        character.addChoice(choice);
        assertThat(character.getChoiceCount(), is(0));
        choice.setAllowed(true);
        assertThat(character.getChoiceCount(), is(1));
    }

    private void setLevel(CharacterClass charClass, int level) {
        character.addAttributes(
            charClass,
            new IntAttribute(LEVEL, level),
            new IntAttribute(EXPERIENCE_POINTS, Character.XP_LEVELS[level - 1]),
            new IntAttribute(HIT_POINTS, charClass.getHitDie()),
            new IntAttribute(CONSTITUTION, 10));
    }
}
