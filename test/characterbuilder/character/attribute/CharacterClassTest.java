package characterbuilder.character.attribute;

import characterbuilder.character.Character;
import characterbuilder.character.ability.DivineDomain;
import characterbuilder.character.ability.Spell;
import static characterbuilder.character.attribute.AttributeType.DIVINE_DOMAIN;
import static characterbuilder.character.attribute.CharacterClass.CLERIC;
import static characterbuilder.character.attribute.CharacterClass.FIGHTER;
import characterbuilder.character.choice.Choice;
import characterbuilder.character.choice.ChoiceSelector;
import characterbuilder.character.choice.TestChoiceSelector;
import java.util.List;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class CharacterClassTest {

    private Character character;

    @Before
    public void setup() {
        character = new Character();
    }

    @Test
    public void testInitialDivineDomainSpells() {
        character.addAttribute(new IntAttribute(AttributeType.LEVEL, 1));
        CharacterClass.CLERIC.generateLevelChoices(character);
        character.getChoices().getChoices()
            .filter(ch -> ch.toString().equals("Divine Domain"))
            .findAny().get()
            .makeChoice(character, new TestChoiceSelector().withAttribute(DivineDomain.LIFE));
        assertFalse(character.getChoices().getChoices()
            .anyMatch(ch -> ch.toString().equals("Divine Domain")));
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
        ChoiceSelector selector = new TestChoiceSelector();
        character.addAttribute(level);
        while (level.getValue() <= 20) {
            CharacterClass.CLERIC.generateLevelChoices(character);
            List<Choice> spellChoices = spellChoices();
            while (!spellChoices.isEmpty()) {
                spellChoices.forEach(ch -> ch.makeChoice(character, selector));
                spellChoices = spellChoices();
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

    private List<Choice> spellChoices() {
        return character.getChoices()
            .getChoices()
            .filter(ch -> ch.toString().matches("(\\dx )?(Cantrip|Level \\d+ spell)"))
            .collect(toList());
    }

    private int spellLevelCount(int level) {
        return (int) character.getAllAttributes()
            .filter(att -> att.hasType(AttributeType.SPELL) && ((Spell) att).getLevel() == level)
            .count();
    }

}
