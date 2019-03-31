package characterbuilder.character.ability;

import static characterbuilder.character.characterclass.CharacterClass.FIGHTER;
import static characterbuilder.utils.TestUtils.testDescriptions;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.xml.sax.SAXParseException;

import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.characterclass.fighter.FighterAbility;
import characterbuilder.character.saveload.TestDoc;
import characterbuilder.utils.TestCharacter;

public class AbilityTest {

	private TestCharacter character;

	@BeforeEach
	public void setup() {
		character = new TestCharacter();
		character.setLevel(FIGHTER, 1);
	}

	@Test
	public void testName() {
		assertThat(RacialTalent.DARKVISION.toString(), is("Darkvision"));
	}

	@Test
	public void testMultipleDescriptions() {
		assertThat(FighterAbility.SECOND_WIND.getDescription(character).count(), is(2L));
	}

	@Test
	public void testSaveLoad() throws SAXParseException {
		assertThat(AttributeType.load(RacialTalent.DARKVISION.save(TestDoc.doc())),
				is(RacialTalent.DARKVISION));
	}

	@Test
	public void testDescriptionExpressions() {
		testDescriptions(Ability.values());
	}
}
