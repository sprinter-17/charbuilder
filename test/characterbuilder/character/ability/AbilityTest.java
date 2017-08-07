package characterbuilder.character.ability;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AbilityScore;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.DraconicAncestory;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.character.attribute.Race;
import characterbuilder.character.choice.TestChoiceSelector;
import characterbuilder.character.saveload.TestDoc;
import static java.util.stream.Collectors.joining;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
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
        character.addChoiceList(new TestChoiceSelector());
    }

    @Test
    public void testName() {
        assertThat(Ability.DARKVISION.toString(), is("Darkvision"));
        assertThat(Ability.SHIPS_PASSAGE.toString(), is("Ship's Passage"));
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

    @Test
    public void testLegalDescriptionExpressions() {
        character.addAttribute(Race.HALF_ELF);
        character.addAttribute(DraconicAncestory.BLUE);
        character.addAttribute(new IntAttribute(AttributeType.HIT_POINTS, 10));
        AbilityScore.SCORES.stream()
            .forEach(attr -> character.addAttribute(new AbilityScore(attr, 14)));
        for (Ability ability : Ability.values()) {
            try {
                String desc = ability.getDescription(character).collect(joining());
                assertFalse(ability.name() + ":" + desc, desc.contains("*ERROR*"));
            } catch (Exception ex) {
                fail(ability.name() + ":" + ex.toString());
            }
        }
    }
}
