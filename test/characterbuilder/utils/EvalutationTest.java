package characterbuilder.utils;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.IntAttribute;
import static characterbuilder.utils.ExpressionParser.eval;
import static org.hamcrest.CoreMatchers.is;
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
    public void testWordExpression() {
        assertThat(eval("Fred", character), is("Fred"));
    }

    @Test
    public void testLevelExpression() {
        assertThat(eval("$level", character), is("1"));
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
}
