package characterbuilder.utils;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.character.attribute.Race;
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
        assertThat(eval("$levil", context), is("[token recognition error at: '$levi']"));
    }

    @Test
    public void testSemanticError() {
        assertThat(eval("$level + + 6", context), startsWith("[mismatched input"));
    }

    @Test
    public void testWordExpression() {
        assertThat(eval("Fred", context), is("Fred"));
    }

    @Test
    public void testLevelExpression() {
        assertThat(eval("$level", context), is("1"));
    }

    @Test
    public void testRounding() {
        assertThat(eval("$level/2", context), is("0"));
        assertThat(eval("$level//2", context), is("1"));
    }

    @Test
    public void testIfExpression() {
        assertThat(eval("if(4 > 3:Foo)", context), is("Foo"));
        assertThat(eval("if(4 < 3:Foo)", context), is(""));
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

}
