package characterbuilder.character.characterclass;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.DIVINE_DOMAIN;
import static characterbuilder.character.characterclass.CharacterClass.CLERIC;
import static characterbuilder.character.characterclass.CharacterClass.FIGHTER;
import characterbuilder.character.choice.TestChoiceSelector;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class CharacterClassTest {

    private Character character;
    private TestChoiceSelector selector;

    @Before
    public void setup() {
        character = new Character();
        selector = new TestChoiceSelector();
        character.addChoiceList(selector);
    }

    @Test
    public void testClassAttribute() {
        assertThat(CLERIC.getClassAttribute().get(), is(DIVINE_DOMAIN));
        assertThat(FIGHTER.getClassAttribute().get(), is(AttributeType.MARTIAL_ARCHETYPE));
    }
}
