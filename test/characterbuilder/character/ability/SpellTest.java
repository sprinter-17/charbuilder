package characterbuilder.character.ability;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.character.saveload.TestDoc;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Test;

public class SpellTest {

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
        assertThat(AttributeType.load(Spell.BLUR.save(TestDoc.doc())), is(Spell.BLUR));
    }

    @Test
    public void testAllEffectsArePresent() {
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
        character.addAttribute(new SpellCasting(AttributeType.CONSTITUTION));
        character.addAttribute(new IntAttribute(AttributeType.CONSTITUTION, 15));
        character.addAttribute(new IntAttribute(AttributeType.DEXTERITY, 15));
        character.addAttribute(new IntAttribute(AttributeType.HIT_POINTS, 30));
        return character;
    }
}
