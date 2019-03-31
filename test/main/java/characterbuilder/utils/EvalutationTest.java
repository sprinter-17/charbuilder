package characterbuilder.utils;

import static characterbuilder.utils.ExpressionParser.eval;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.DraconicAncestry;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.character.attribute.Race;
import characterbuilder.character.characterclass.CharacterClass;
import characterbuilder.character.spell.LearntSpell;
import characterbuilder.character.spell.Spell;

public class EvalutationTest {

	private TestCharacter character;
	private EvaluationContext context;

	@BeforeEach
	public void setup() {
		character = new TestCharacter();
		character.setLevel(CharacterClass.CLERIC, 1);
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
	public void testClassLevel() {
		assertThat(eval("$fighter_level", context), is("0"));
		character.setLevel(CharacterClass.FIGHTER, 4);
		assertThat(eval("$fighter_level", context), is("4"));
	}

	@Test
	public void testClassLevelMax() {
		assertThat(eval("max($fighter_level 0:1,1:2)", context), is("1"));
		character.setLevel(CharacterClass.FIGHTER, 1);
		assertThat(eval("max($fighter_level 0:1,1:2)", context), is("2"));
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
		assertThat(eval("if(4 < 3:Foo:Bar $level Bar)", context), is("Bar 1 Bar"));
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
	public void testBonus() {
		character.withScores(10);
		assertThat(eval("bonus($chr_mod)", context), is(""));
		character.setScore(AttributeType.CHARISMA, 12);
		assertThat(eval("bonus($chr_mod)", context), is("+1"));
		character.setScore(AttributeType.CHARISMA, 15);
		assertThat(eval("bonus($chr_mod)", context), is("+2"));
		character.setScore(AttributeType.CHARISMA, 7);
		assertThat(eval("bonus($chr_mod)", context), is("-2"));
	}

	@Test
	public void testSpeed() {
		character.addAttribute(Race.LIGHFOOT_HALFLING);
		assertThat(eval("$speed/2", context), is("12"));
	}

	@Test
	public void testSpellModAndDC() {
		character.addAttribute(new IntAttribute(AttributeType.DEXTERITY, 14));
		context.setSpell(new LearntSpell(Spell.SHIELD, AttributeType.DEXTERITY, true));
		assertThat(eval("$spell_mod", context), is("4"));
		assertThat(eval("$spell_dc", context), is("12"));
	}

	@Test
	public void testDraconicAncestory() {
		character.addAttribute(DraconicAncestry.COPPER);
		assertThat(eval("$draconic_damage", context), is("Acid"));
		assertThat(eval("$draconic_breath", context), is("Acid, 5x30' line, Dex. save"));
	}
}
