package characterbuilder.character.ability;

import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.CHARISMA;
import characterbuilder.character.saveload.TestDoc;
import characterbuilder.character.spell.Spell;
import characterbuilder.character.spell.SpellAbility;
import characterbuilder.utils.TestCharacter;
import static characterbuilder.utils.TestUtils.testDescriptions;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
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
    public void testSaveLoad() {
        assertThat(AttributeType.load(Ability.ARCHERY.save(TestDoc.doc())), is(Ability.ARCHERY));
    }

    @Test
    public void testDescriptionExpressions() {
        testDescriptions(Ability.values());
    }
}
