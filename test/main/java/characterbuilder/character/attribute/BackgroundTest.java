package characterbuilder.character.attribute;

import characterbuilder.character.attribute.Background.Ability;
import characterbuilder.character.saveload.TestDoc;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;
import org.xml.sax.SAXParseException;

public class BackgroundTest {

    @Test
    public void testAbilityName() {
        assertThat(Ability.CITY_SECRETS.toString(), is("City Secrets"));
        assertThat(Ability.SHIPS_PASSAGE.toString(), is("Ship's Passage"));
    }

    @Test
    public void testSaveAndLoadAbility() throws SAXParseException {
        assertThat(AttributeType.load(Ability.DISCOVERY.save(TestDoc.doc())), is(Ability.DISCOVERY));
    }
}
