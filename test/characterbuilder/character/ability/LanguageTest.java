package characterbuilder.character.ability;

import static characterbuilder.character.ability.Language.*;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.saveload.TestDoc.doc;
import characterbuilder.utils.TestCharacter;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

public class LanguageTest {

    @Test
    public void testGetType() {
        assertThat(COMMON.getType(), is(AttributeType.LANGUAGE));
    }

    @Test
    public void testToString() {
        assertThat(THIEVES_CANT.toString(), is("Thieves' Cant"));
    }

    @Test
    public void testSaveAndLoad() {
        assertThat(AttributeType.load(DWARVISH.save(doc())), is(DWARVISH));
    }

    @Test
    public void testChooseCommon() {
        TestCharacter character = new TestCharacter();
        character.addChoice(Language.choose(1));
        character.selectChoice("Standard Language", "Common");
        assertTrue(character.hadOption(Language.ELVISH));
        assertFalse(character.hadOption(Language.DRACONIC));
    }
}
