package characterbuilder.character.characterclass;

import characterbuilder.character.Character;
import characterbuilder.character.ability.DivineDomain;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.DIVINE_DOMAIN;
import characterbuilder.character.attribute.IntAttribute;
import static characterbuilder.character.characterclass.CharacterClass.CLERIC;
import static characterbuilder.character.characterclass.CharacterClass.FIGHTER;
import characterbuilder.character.choice.OptionChoice;
import characterbuilder.character.choice.TestChoiceSelector;
import characterbuilder.character.spell.Spell;
import java.util.Optional;
import java.util.stream.IntStream;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
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
    public void testInitialDivineDomainSpells() {
        CLERIC.choose(character);
        selector.withAttribute(DivineDomain.LIFE);
        character.selectChoice(firstChoice("Divine Domain").get());
        assertFalse(firstChoice("Divine Domain").isPresent());
        assertTrue(character.hasAttribute(DivineDomain.LIFE));
        assertTrue(character.hasAttribute(Spell.BLESS));
        assertTrue(character.hasAttribute(Spell.CURE_WOUNDS));
    }

    @Test
    public void testClassAttribute() {
        assertThat(CLERIC.getClassAttribute().get(), is(DIVINE_DOMAIN));
        assertThat(FIGHTER.getClassAttribute().get(), is(AttributeType.MARTIAL_ARCHETYPE));
    }

    @Test
    public void testLevelUpDivineDomainSpells() {
        character.addAttribute(new IntAttribute(AttributeType.LEVEL, 3));
        character.addAttribute(DivineDomain.LIFE);
        DivineDomain.LIFE.generateLevelChoices(character);
        assertTrue(character.hasAttribute(Spell.LESSER_RESTORATION));
        assertTrue(character.hasAttribute(Spell.SPIRITUAL_WEAPON));
    }

    private Optional<OptionChoice> firstChoice(String match) {
        return IntStream.range(0, character.getChoiceCount())
            .mapToObj(character::getChoice)
            .filter(c -> c.toString().matches(match))
            .findFirst();
    }
}
