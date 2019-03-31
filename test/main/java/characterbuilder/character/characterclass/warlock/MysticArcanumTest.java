package characterbuilder.character.characterclass.warlock;

import characterbuilder.character.ability.Ability;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.characterclass.CharacterClass;
import characterbuilder.character.saveload.TestDoc;
import characterbuilder.character.spell.Spell;
import characterbuilder.utils.TestCharacter;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXParseException;

public class MysticArcanumTest {

    private TestCharacter character;

    @Before
    public void setup() {
        Ability.values(); // initialisation error
        character = new TestCharacter();
        character.addChoice(MysticArcanum.chooseArcanum(CharacterClass.WIZARD, 1));
    }

    @Test
    public void testChooseArcanum() {
        character.selectChoice("Mystic Arcanum", "Alarm");
        assertTrue(character.hasAttribute(new MysticArcanum(Spell.ALARM)));
    }

    @Test
    public void testSaveAndLoad() throws SAXParseException {
        assertThat(AttributeType.load(new MysticArcanum(Spell.ALARM).save(TestDoc.doc())),
            is(new MysticArcanum(Spell.ALARM)));
    }

}
