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

    @Before
    public void setup() {
        character = new Character();
        level = new IntAttribute(AttributeType.LEVEL, 1);
        character.addAttribute(level);
    }

    @Test
    public void testSyntaxError() {
        assertThat(eval("$levil", character), is("[token recognition error at: '$levi']"));
    }

    @Test
    public void testSemanticError() {
        assertThat(eval("$level + + 6", character), startsWith("[mismatched input"));
    }

    @Test
    public void testWordExpression() {
        assertThat(eval("Fred", character), is("Fred"));
    }

    @Test
    public void testLevelExpression() {
        assertThat(eval("$level", character), is("1"));
    }

    @Test
    public void testRounding() {
        assertThat(eval("$level/2", character), is("0"));
        assertThat(eval("$level//2", character), is("1"));
    }

    @Test
    public void testIfExpression() {
        assertThat(eval("if(4 > 3:Foo)", character), is("Foo"));
        assertThat(eval("if(4 < 3:Foo)", character), is(""));
    }

    @Test
    public void testPlural() {
        assertThat(eval("plural(object, objects)", character, "1"), is("object"));
        assertThat(eval("plural(object, objects)", character, "one"), is("object"));
        assertThat(eval("plural(object, objects)", character, "2"), is("objects"));
        assertThat(eval("plural(object, objects)", character, "two"), is("objects"));
    }

    @Test
    public void testParentheses() {
        assertThat(eval("4 + (3 * 2)", character), is("10"));
        assertThat(eval("(4 + 3) * 2", character), is("14"));
    }

    @Test
    public void testSpeed() {
        character.addAttribute(Race.LIGHFOOT_HALFLING);
        assertThat(eval("$speed/2", character), is("12"));
    }

}
