package characterbuilder.character.attribute;

import characterbuilder.character.Character;
import characterbuilder.character.ability.DivineDomain;
import characterbuilder.character.ability.Spell;
import static characterbuilder.character.attribute.AttributeType.DIVINE_DOMAIN;
import static characterbuilder.character.attribute.CharacterClass.CLERIC;
import static characterbuilder.character.attribute.CharacterClass.FIGHTER;
import characterbuilder.character.choice.OptionChoice;
import characterbuilder.character.choice.TestChoiceSelector;
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
        character.addAttribute(new IntAttribute(AttributeType.LEVEL, 1));
        CharacterClass.CLERIC.generateLevelChoices(character);
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

    @Test
    public void testTotalClericSpell() {
        IntAttribute level = new IntAttribute(AttributeType.LEVEL, 1);
        character.addAttribute(level);
        String spellMatch = "(Cantrip|Spell Level \\d)( \\(x\\d\\))?";
        while (level.getValue() <= 20) {
            CharacterClass.CLERIC.generateLevelChoices(character);
            while (firstChoice(spellMatch).isPresent()) {
                character.selectChoice(firstChoice(spellMatch).get());
            }
            level.addValue(1);
        }
        assertThat(spellLevelCount(0), is(5));
        assertThat(spellLevelCount(1), is(4));
        assertThat(spellLevelCount(2), is(3));
        assertThat(spellLevelCount(3), is(3));
        assertThat(spellLevelCount(4), is(3));
        assertThat(spellLevelCount(5), is(3));
        assertThat(spellLevelCount(6), is(2));
        assertThat(spellLevelCount(7), is(2));
        assertThat(spellLevelCount(8), is(1));
        assertThat(spellLevelCount(9), is(1));
    }

    private Optional<OptionChoice> firstChoice(String match) {
        return IntStream.range(0, character.getChoiceCount())
            .mapToObj(character::getChoice)
            .filter(c -> c.toString().matches(match))
            .findFirst();
    }

    private int spellLevelCount(int level) {
        return (int) character.getAllAttributes()
            .filter(att -> att.hasType(AttributeType.SPELL) && ((Spell) att).getLevel() == level)
            .count();
    }

}
