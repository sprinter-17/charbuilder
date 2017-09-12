package characterbuilder.character.spell;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Ability;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.DamageType;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.character.attribute.Value;
import characterbuilder.character.characterclass.CharacterClass;
import characterbuilder.character.equipment.Attack;
import characterbuilder.character.saveload.TestDoc;
import characterbuilder.utils.TestCharacter;
import java.util.List;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class SpellTest {

    @Before
    public void setup() {
        Ability.values();
    }

    @Test
    public void testRituals() {
        assertTrue(Spell.FIND_FAMILIAR.isRitual());
        assertFalse(Spell.FIREBALL.isRitual());
    }

    @Test
    public void testCost() {
        assertThat(Spell.AID.getCost(), is(Value.ZERO));
        assertThat(Spell.ASTRAL_PROJECTION.getCost(), is(Value.gp(1100)));
    }

    @Test
    public void testEffect() {
        assertThat(Spell.ACID_SPLASH, hasEffectAtLevel("1d6 acid damage.", 1));
        assertThat(Spell.ACID_SPLASH, hasEffectAtLevel("1d6 acid damage.", 4));
        assertThat(Spell.ACID_SPLASH, hasEffectAtLevel("2d6 acid damage.", 5));
        assertThat(Spell.ACID_SPLASH, hasEffectAtLevel("2d6 acid damage.", 10));
        assertThat(Spell.ACID_SPLASH, hasEffectAtLevel("3d6 acid damage.", 11));
    }

    @Test
    public void testSaveAndLoad() {
        assertThat(Spell.load(Spell.BLUR.save(TestDoc.doc())), is(Spell.BLUR));
    }

    @Test
    public void testSpLevEffect() {
        assertThat(Spell.SACRED_FLAME, hasEffectAtLevel("1d8 radiant damage.", 1));
        assertThat(Spell.SACRED_FLAME, hasEffectAtLevel("1d8 radiant damage.", 4));
        assertThat(Spell.SACRED_FLAME, hasEffectAtLevel("2d8 radiant damage.", 5));
        assertThat(Spell.SACRED_FLAME, hasEffectAtLevel("2d8 radiant damage.", 6));
        assertThat(Spell.SACRED_FLAME, hasEffectAtLevel("3d8 radiant damage.", 11));
        assertThat(Spell.SACRED_FLAME, hasEffectAtLevel("3d8 radiant damage.", 16));
        assertThat(Spell.SACRED_FLAME, hasEffectAtLevel("4d8 radiant damage.", 17));
    }

    @Test
    public void testRangedSpellAttack() {
        TestCharacter character = new TestCharacter();
        character.setLevel(11);
        Attack attack = Spell.CHILL_TOUCH.getAttack(character, AttributeType.CHARISMA).get();
        assertThat(attack.getName(), is("Chill Touch"));
        assertThat(attack.getRange(), is("120"));
        assertThat(attack.getDamage(), is("3d8"));
        assertThat(attack.getType(), is(DamageType.NECROTIC));
    }

    @Test
    public void testAllEffectsAreLegal() {
        Character character = level(5);
        for (Spell spell : Spell.values()) {
            try {
                String effect = spell.getEffects(character).collect(joining());
                assertFalse(spell.name() + ":" + effect, effect.contains("*ERROR*"));
            } catch (Exception ex) {
                fail(spell.name() + ":" + ex.toString());
            }
        }
    }

    private Matcher<Spell> hasEffectAtLevel(String effect, int level) {
        return new TypeSafeDiagnosingMatcher<Spell>() {
            @Override
            protected boolean matchesSafely(Spell spell, Description d) {
                List<String> effects = spell.getEffects(level(level)).collect(toList());
                d.appendText("Has effects ").appendValue(effects);
                return effects.contains(effect);
            }

            @Override
            public void describeTo(Description d) {
                d.appendText("Has effect ").appendValue(effect)
                    .appendText(" at level ").appendValue(level);
            }
        };
    }

    private Character level(int level) {
        Character character = new Character();
        character.addAttribute(new IntAttribute(AttributeType.LEVEL, level));
        character.addAttribute(new SpellCasting("SpellCasting", AttributeType.CONSTITUTION,
            CharacterClass.WIZARD, "All"));
        character.addAttribute(new IntAttribute(AttributeType.CONSTITUTION, 15));
        character.addAttribute(new IntAttribute(AttributeType.DEXTERITY, 15));
        character.addAttribute(new IntAttribute(AttributeType.HIT_POINTS, 30));
        return character;
    }
}
