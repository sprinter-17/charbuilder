package characterbuilder.character.characterclass.cleric;

import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.characterclass.CharacterClass;
import static characterbuilder.character.characterclass.cleric.ClericAbility.CHANNEL_DIVINITY;
import characterbuilder.character.characterclass.cleric.DivineDomain.DivineDomainAbility;
import static characterbuilder.character.characterclass.cleric.DivineDomain.KNOWLEDGE;
import characterbuilder.character.saveload.TestDoc;
import characterbuilder.character.spell.Spell;
import static characterbuilder.character.spell.Spell.*;
import characterbuilder.character.spell.SpellCasting;
import characterbuilder.utils.TestCharacter;
import static characterbuilder.utils.TestMatchers.hasPreparedSpell;
import static characterbuilder.utils.TestUtils.testDescriptions;
import static java.util.stream.Collectors.joining;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXParseException;

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
        character.setLevel(CharacterClass.CLERIC, 3);
        character.addAttribute(DivineDomain.LIFE);
        DivineDomain.LIFE.generateLevelChoices(character);
        assertTrue(casting.hasLearntSpell(Spell.LESSER_RESTORATION));
        assertTrue(casting.hasLearntSpell(Spell.SPIRITUAL_WEAPON));
    }

    @Test
    public void testDivineDomainSpellsForMulticlass() {
        character.setLevel(CharacterClass.FIGHTER, 10);
        character.setLevel(CharacterClass.CLERIC, 1);
        character.addAttribute(DivineDomain.LIFE);
        assertThat(casting, not(hasPreparedSpell(Spell.CURE_WOUNDS)));
        DivineDomain.LIFE.generateLevelChoices(character);
        assertThat(casting, hasPreparedSpell(Spell.CURE_WOUNDS));
    }

    @Test
    public void testChannelDivinityDescription() {
        character.setLevel(CharacterClass.CLERIC, 2);
        assertThat(CHANNEL_DIVINITY.getDescription(character).collect(joining()),
            startsWith("Use one channel divinity power"));
        character.setLevel(CharacterClass.CLERIC, 5);
        assertThat(CHANNEL_DIVINITY.getDescription(character).collect(joining()),
            startsWith("Use one channel divinity power"));
        character.setLevel(CharacterClass.CLERIC, 6);
        assertThat(CHANNEL_DIVINITY.getDescription(character).collect(joining()),
            startsWith("Use two channel divinity powers"));
    }

    @Test
    public void testDescriptionExpressions() {
        testDescriptions(DivineDomain.values());
        testDescriptions(DivineDomainAbility.values());
    }

    @Test
    public void testSaveAndLoad() throws SAXParseException {
        assertThat(AttributeType.load(KNOWLEDGE.save(TestDoc.doc())), is(KNOWLEDGE));
        assertThat(AttributeType.load(CHANNEL_DIVINITY.save(TestDoc.doc())), is(CHANNEL_DIVINITY));
    }
}
