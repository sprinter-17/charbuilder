package characterbuilder.character.spell;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Ability;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.character.characterclass.CharacterClass;
import characterbuilder.character.saveload.TestDoc;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class SpellTest {

    @Before
    public void setup() {
        Ability.values(); // initialisation error
    }

    @Test
    public void testEffect() {
        assertThat(Spell.ACID_SPLASH.getEffect(level(1)),
            is("1d6 acid damage.\nDex. save for no damage."));
        assertThat(Spell.ACID_SPLASH.getEffect(level(4)),
            is("1d6 acid damage.\nDex. save for no damage."));
        assertThat(Spell.ACID_SPLASH.getEffect(level(5)),
            is("2d6 acid damage.\nDex. save for no damage."));
        assertThat(Spell.ACID_SPLASH.getEffect(level(10)),
            is("2d6 acid damage.\nDex. save for no damage."));
        assertThat(Spell.ACID_SPLASH.getEffect(level(11)),
            is("3d6 acid damage.\nDex. save for no damage."));
    }

    @Test
    public void testSaveAndLoad() {
        assertThat(Spell.load(Spell.BLUR.save(TestDoc.doc())), is(Spell.BLUR));
    }

    @Test
    public void testSpLevEffect() {
        assertThat(Spell.SACRED_FLAME.getEffect(level(1)), containsString("1d8 radiant damage"));
        assertThat(Spell.SACRED_FLAME.getEffect(level(4)), containsString("1d8 radiant damage"));
        assertThat(Spell.SACRED_FLAME.getEffect(level(5)), containsString("2d8 radiant damage"));
        assertThat(Spell.SACRED_FLAME.getEffect(level(6)), containsString("2d8 radiant damage"));
        assertThat(Spell.SACRED_FLAME.getEffect(level(11)), containsString("3d8 radiant damage"));
        assertThat(Spell.SACRED_FLAME.getEffect(level(16)), containsString("3d8 radiant damage"));
        assertThat(Spell.SACRED_FLAME.getEffect(level(17)), containsString("4d8 radiant damage"));
    }

    @Test
    public void testAllEffectsAreLegal() {
        Character character = level(5);
        for (Spell spell : Spell.values()) {
            try {
                String effect = spell.getEffect(character);
                assertFalse(spell.name() + ":" + effect, effect.contains("*ERROR*"));
            } catch (Exception ex) {
                fail(spell.name() + ":" + ex.toString());
            }
        }
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
