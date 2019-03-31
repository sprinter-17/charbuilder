package characterbuilder.character.ability;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.ELEMENTAL_ADEPT;
import characterbuilder.character.attribute.DamageType;
import characterbuilder.character.saveload.TestDoc;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXParseException;

public class ElementalAdeptTest {

    private final Attribute adept = new ElementalAdept(DamageType.THUNDER);

    @Test
    public void testGetType() {
        assertThat(adept.getType(), is(ELEMENTAL_ADEPT));
    }

    @Test
    public void testGetName() {
        assertThat(adept.toString(), is("Thunder Element Adept"));
    }

    @Test
    public void testGetDescription() {
        Character character = new Character();
        assertTrue(adept.getDescription(character)
            .anyMatch(desc -> desc.contains("For spells causing Thunder damage")));
    }

    @Test
    public void testSaveAndLoad() throws SAXParseException {
        assertThat(AttributeType.load(adept.save(TestDoc.doc())), is(adept));
    }
}
