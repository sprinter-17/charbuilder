package characterbuilder.utils;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.character.attribute.Race;
import characterbuilder.character.spell.SpellCasting;
import static characterbuilder.utils.ExpressionParser.eval;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class EvalutationTest {

    private Character character;
    private IntAttribute level;
    private EvaluationContext context;

    @Before
    public void setup() {
        character = new Character();
        level = new IntAttribute(AttributeType.LEVEL, 1);
        character.addAttribute(level);
        context = new EvaluationContext();
        context.setCharacter(character);
    }

    @Test
    public void testSyntaxError() {
        assertThat(eval("$levil", context), is("[ *ERROR* token recognition error at: '$levi']"));
    }

    @Test
    public void testSemanticError() {
        assertThat(eval("$level + + 6", context), startsWith("[ *ERROR* mismatched input"));
    }

    @Test
    public void testWordExpression() {
        assertThat(eval("Fred   and albert", context), is("Fred   and albert"));
    }

    @Test
    public void testSpacesIgnored() {
        assertThat(eval("5 *6 +    2", context), is(eval("5* 6+2", context)));
    }

    @Test
    public void testLevelExpression() {
        assertThat(eval("$level", context), is("1"));
    }

    @Test
    public void testRounding() {
        assertThat(eval("$level/2", context), is("0"));
        assertThat(eval("$level/^2", context), is("1"));
    }

    @Test
    public void testIfExpression() {
        assertThat(eval("if(4 > 3:Foo)", context), is("Foo"));
        assertThat(eval("if(4 < 3:Foo)", context), is(""));
    }

    @Test
    public void testMaxExpression() {
        assertThat(eval("max(4, 1)", context), is("4"));
        assertThat(eval("max(1, 4)", context), is("4"));
    }

    @Test
    public void testPlural() {
        assertThat(eval("plural(object, objects)", context), is("object"));
        context.setPlural(true);
        assertThat(eval("plural(object, objects)", context), is("objects"));
    }

    @Test
    public void testParentheses() {
        assertThat(eval("4 + (3 * 2)", context), is("10"));
        assertThat(eval("(4 + 3) * 2", context), is("14"));
    }

    @Test
    public void testSpeed() {
        character.addAttribute(Race.LIGHFOOT_HALFLING);
        assertThat(eval("$speed/2", context), is("12"));
    }

    @Test
    public void testSpellModAndDC() {
        character.addAttribute(new SpellCasting("Spellcasting", AttributeType.DEXTERITY));
        character.addAttribute(new IntAttribute(AttributeType.DEXTERITY, 14));
        assertThat(eval("$spell_mod", context), is("4"));
        assertThat(eval("$spell_dc", context), is("12"));
    }
}
