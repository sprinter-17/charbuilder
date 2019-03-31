package characterbuilder.character.ability;

import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.equipment.Weapon;
import characterbuilder.character.saveload.TestDoc;
import java.util.Optional;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXParseException;

public class WeaponProficiencyTest {

    private WeaponProficiency prof;

    @BeforeEach
    public void setup() {
        prof = new WeaponProficiency(Weapon.WHIP);
    }

    @Test
    public void testGetType() {
        assertThat(prof.getType(), is(AttributeType.WEAPON_PROFICIENCY));
    }

    @Test
    public void testGetSuperSet() {
        assertThat(prof.getSuperSet(), is(Optional.of(Proficiency.ALL_MARTIAL_MELEE)));
    }

    @Test
    public void testSaveAndLoad() throws SAXParseException {
        assertThat(AttributeType.load(prof.save(TestDoc.doc())), is(prof));
    }
}
