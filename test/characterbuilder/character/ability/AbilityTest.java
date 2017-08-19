package characterbuilder.character.ability;

import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.CHARISMA;
import static characterbuilder.character.attribute.AttributeType.INTELLIGENCE;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.character.attribute.Race;
import static characterbuilder.character.characterclass.CharacterClass.WIZARD;
import characterbuilder.character.characterclass.DraconicAncestory;
import characterbuilder.character.saveload.TestDoc;
import characterbuilder.character.spell.Spell;
import characterbuilder.character.spell.SpellAbility;
import characterbuilder.character.spell.SpellCasting;
import characterbuilder.utils.TestCharacter;
import static characterbuilder.utils.TestMatchers.hasChoice;
import static java.util.stream.Collectors.joining;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class AbilityTest {

    private TestCharacter character;

    @Before
    public void setup() {
        character = new TestCharacter();
        character.setLevel(1);
    }

    @Test
    public void testName() {
        assertThat(Ability.DARKVISION.toString(), is("Darkvision"));
        assertThat(Ability.SHIPS_PASSAGE.toString(), is("Ship's Passage"));
    }

    @Test
    public void testChannelDivinityDescription() {
        character.setLevel(2);
        assertThat(Ability.CHANNEL_DIVINITY.getDescription(character).collect(joining()),
            startsWith("Use one channel divinity power"));
        character.setLevel(5);
        assertThat(Ability.CHANNEL_DIVINITY.getDescription(character).collect(joining()),
            startsWith("Use one channel divinity power"));
        character.setLevel(6);
        assertThat(Ability.CHANNEL_DIVINITY.getDescription(character).collect(joining()),
            startsWith("Use two channel divinity powers"));
    }

    @Test
    public void testMultipleDescriptions() {
        assertThat(Ability.SECOND_WIND.getDescription(character).count(), is(2L));
    }

    @Test
    public void testSpellAbility() {
        Ability.PACT_OF_THE_CHAIN.choose(character);
        assertTrue(character.hasAttribute(new SpellAbility(Spell.FIND_FAMILIAR, CHARISMA)));
    }

    @Test
    public void testImprovedMinorIllusion() {
        character.withScores(10);
        Ability.IMPROVED_MINOR_ILLUSION.choose(character);
        assertTrue(character.hasAttribute(new SpellAbility(Spell.MINOR_ILLUSION, INTELLIGENCE)));
        character.removeAttribute(Ability.IMPROVED_MINOR_ILLUSION);
        Ability.IMPROVED_MINOR_ILLUSION.choose(character);
        assertThat(character, hasChoice("Cantrip"));
    }

    @Test
    public void testSaveLoad() {
        assertThat(AttributeType.load(Ability.ARCHERY.save(TestDoc.doc())), is(Ability.ARCHERY));
    }

    @Test
    public void testLegalDescriptionExpressions() {
        character.addAttribute(Race.HALF_ELF);
        character.addAttribute(DraconicAncestory.BLUE);
        character.addAttribute(new IntAttribute(AttributeType.HIT_POINTS, 10));
        character.addAttribute(new SpellCasting("Casting", INTELLIGENCE, WIZARD, "All"));
        character.withScores(14);
        for (Ability ability : Ability.values()) {
            try {
                String desc = ability.getDescription(character).collect(joining());
                assertFalse(ability.name() + ":" + desc, desc.contains("*ERROR*"));
            } catch (Exception ex) {
                fail(ability.name() + ":" + ex.toString());
            }
        }
    }
}
