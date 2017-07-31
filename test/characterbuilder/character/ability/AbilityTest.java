package characterbuilder.character.ability;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.character.saveload.TestDoc;
import static java.util.stream.Collectors.joining;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class AbilityTest {

    private Character character;
    private IntAttribute level;

    @Before
    public void setup() {
        character = new Character();
        level = new IntAttribute(AttributeType.LEVEL, 1);
        character.addAttribute(level);
    }

    @Test
    public void testChannelDivinityDescription() {
        level.setValue(2);
        assertThat(Ability.CHANNEL_DIVINITY.getDescription(character).collect(joining()),
            startsWith("Use one channel divinity power"));
        level.setValue(5);
        assertThat(Ability.CHANNEL_DIVINITY.getDescription(character).collect(joining()),
            startsWith("Use one channel divinity power"));
        level.setValue(6);
        assertThat(Ability.CHANNEL_DIVINITY.getDescription(character).collect(joining()),
            startsWith("Use two channel divinity powers"));
    }

    @Test
    public void testSaveLoad() {
        assertThat(AttributeType.load(Ability.ARCHERY.save(TestDoc.doc())), is(Ability.ARCHERY));
    }
}
