package characterbuilder.character.characterclass.cleric;

import characterbuilder.character.characterclass.cleric.DivineDomain;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.characterclass.CharacterClass;
import characterbuilder.character.characterclass.cleric.DivineDomain.DivineDomainAbility;
import static characterbuilder.character.characterclass.cleric.DivineDomain.DivineDomainAbility.CHANNEL_DIVINITY;
import static characterbuilder.character.characterclass.cleric.DivineDomain.KNOWLEDGE;
import characterbuilder.character.saveload.TestDoc;
import characterbuilder.character.spell.Spell;
import static characterbuilder.character.spell.Spell.*;
import characterbuilder.character.spell.SpellCasting;
import static characterbuilder.utils.TestUtils.testDescriptions;
import characterbuilder.utils.TestCharacter;
import static java.util.stream.Collectors.joining;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class DivineDomainTest {

    private TestCharacter character;
    private SpellCasting casting;

    @Before
    public void setup() {
        character = new TestCharacter();
        CharacterClass.CLERIC.choose(character);
        casting = character.getAttribute(AttributeType.SPELLCASTING);
    }

    @Test
    public void testSpellList() {
        DivineDomain.LIFE.generateLevelChoices(character);
        assertTrue(casting.hasLearntSpell(BLESS));
        assertTrue(casting.hasLearntSpell(CURE_WOUNDS));
        assertFalse(casting.hasLearntSpell(FIREBALL));
    }

    @Test
    public void testLevelUpDivineDomainSpells() {
        character.setLevel(3);
        character.addAttribute(DivineDomain.LIFE);
        DivineDomain.LIFE.generateLevelChoices(character);
        assertTrue(casting.hasLearntSpell(Spell.LESSER_RESTORATION));
        assertTrue(casting.hasLearntSpell(Spell.SPIRITUAL_WEAPON));
    }

    @Test
    public void testChannelDivinityDescription() {
        character.setLevel(2);
        assertThat(CHANNEL_DIVINITY.getDescription(character).collect(joining()),
            startsWith("Use one channel divinity power"));
        character.setLevel(5);
        assertThat(CHANNEL_DIVINITY.getDescription(character).collect(joining()),
            startsWith("Use one channel divinity power"));
        character.setLevel(6);
        assertThat(CHANNEL_DIVINITY.getDescription(character).collect(joining()),
            startsWith("Use two channel divinity powers"));
    }

    @Test
    public void testDescriptionExpressions() {
        testDescriptions(DivineDomain.values());
        testDescriptions(DivineDomainAbility.values());
    }

    @Test
    public void testSaveAndLoad() {
        assertThat(AttributeType.load(KNOWLEDGE.save(TestDoc.doc())), is(KNOWLEDGE));
        assertThat(AttributeType.load(CHANNEL_DIVINITY.save(TestDoc.doc())), is(CHANNEL_DIVINITY));
    }
}
