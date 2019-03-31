package characterbuilder.character.characterclass.ranger;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.Terrain;
import characterbuilder.character.saveload.TestDoc;
import characterbuilder.utils.TestCharacter;
import java.util.List;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXParseException;

public class FavouredTerrainTest {

    private FavouredTerrain favouredTerrain;

    @BeforeEach
    public void setup() {
        favouredTerrain = new FavouredTerrain();
    }

    @Test
    public void testChoose() {
        TestCharacter character = new TestCharacter();
        assertFalse(character.hasAttribute(AttributeType.RANGER_ABILITY));
        character.addChoice(FavouredTerrain.choose());
        character.selectChoice("Favoured Terrain", "Grassland");
        assertTrue(character.hasAttribute(AttributeType.RANGER_ABILITY));
        assertThat(character, hasTerrain("Grassland"));
        character.addChoice(FavouredTerrain.choose());
        character.selectChoice("Favoured Terrain", "Arctic");
        assertThat(character, hasTerrain("Arctic, Grassland"));
    }

    @Test
    public void testGetType() {
        assertThat(favouredTerrain.getType(), is(AttributeType.RANGER_ABILITY));
    }

    @Test
    public void testToString() {
        assertThat(favouredTerrain.toString(), is("Favoured Terrain"));
    }

    @Test
    public void testSaveAndLoad() throws SAXParseException {
        favouredTerrain.addTerrain(Terrain.ARCTIC);
        favouredTerrain.addTerrain(Terrain.MOUNTAIN);
        assertThat(AttributeType.load(favouredTerrain.save(TestDoc.doc())), is(favouredTerrain));
    }

    private Matcher<Character> hasTerrain(String name) {
        return new TypeSafeDiagnosingMatcher<Character>() {
            @Override
            protected boolean matchesSafely(Character character, Description d) {
                List<FavouredTerrain> favouredTerrain
                    = character.getAttributes(AttributeType.RANGER_ABILITY, FavouredTerrain.class)
                        .collect(toList());
                d.appendText("Had terrain").appendValue(favouredTerrain
                    .stream().map(FavouredTerrain::toString).collect(joining()));
                return favouredTerrain.stream().flatMap(ft -> ft.getDescription(character))
                    .anyMatch(name::equals);
            }

            @Override
            public void describeTo(Description desc) {
                desc.appendText("Has terrain ").appendValue(name);
            }

        };
    }
}
