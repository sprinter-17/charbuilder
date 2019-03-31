package characterbuilder.character.attribute;

import characterbuilder.character.CharacterRandom;
import characterbuilder.character.ability.RacialTalent;
import static characterbuilder.character.attribute.AttributeType.HEIGHT;
import static characterbuilder.character.attribute.AttributeType.STRENGTH;
import static characterbuilder.character.attribute.AttributeType.WEIGHT;
import characterbuilder.character.characterclass.CharacterClass;
import characterbuilder.character.spell.Spell;
import characterbuilder.utils.TestCharacter;
import static characterbuilder.utils.TestMatchers.hasSpellAbility;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RaceTest {

    private TestCharacter character;

    @BeforeEach
    public void setup() {
        character = new TestCharacter();
    }

    @Test
    public void testGetAttributeAdjustment() {
        assertThat(Race.LIGHFOOT_HALFLING.getAttributeAdjustment(STRENGTH), is(0));
        assertThat(Race.HUMAN.getAttributeAdjustment(STRENGTH), is(1));
    }

    @Test
    public void testStartingHeightAndWeight() {
        for (int i = 0; i < 10000; i++) {
            character = new TestCharacter();
            Race.HILL_DWARF.generateInitialChoices(character);
            Height height = character.getAttribute(HEIGHT);
            Weight weight = character.getAttribute(WEIGHT);
            assertTrue(height.compareTo(Height.INCH.times(46)) >= 0);
            assertTrue(height.compareTo(Height.INCH.times(52)) <= 0);
            assertTrue(weight.compareTo(Weight.LB.times(119)) >= 0);
            assertTrue(weight.compareTo(Weight.LB.times(214)) <= 0);
        }
    }

    @Test
    public void testDragonbornHasBreathWeapon() {
        Race.DRAGONBORN.choose(character);
        assertTrue(character.hasAttribute(RacialTalent.BREATH_WEAPON));
    }

    @Test
    public void testTieflingSpells() {
        CharacterClass.FIGHTER.choose(character);
        Race.TIEFLING.choose(character);
        character.withScores(10).generateHitPoints();
        assertThat(character, hasSpellAbility(Spell.THAUMATURGY));
        character.increaseLevel(CharacterClass.FIGHTER, new CharacterRandom());
        assertThat(character, not(hasSpellAbility(Spell.HELLISH_REBUKE)));
        character.increaseLevel(CharacterClass.FIGHTER, new CharacterRandom());
        assertThat(character, hasSpellAbility(Spell.HELLISH_REBUKE));
        character.increaseLevel(CharacterClass.FIGHTER, new CharacterRandom());
        assertThat(character, not(hasSpellAbility(Spell.DARKNESS)));
        character.increaseLevel(CharacterClass.FIGHTER, new CharacterRandom());
        assertThat(character, hasSpellAbility(Spell.DARKNESS));
    }
}
