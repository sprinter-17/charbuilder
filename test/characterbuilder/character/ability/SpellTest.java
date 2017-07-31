package characterbuilder.character.ability;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.character.saveload.TestDoc;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class SpellTest {

    @Test
    public void testEffect() {
        assertThat(Spell.ACID_SPLASH.getEffect(level(1)), is("1d6 acid damage (Dex save half)"));
        assertThat(Spell.ACID_SPLASH.getEffect(level(4)), is("1d6 acid damage (Dex save half)"));
        assertThat(Spell.ACID_SPLASH.getEffect(level(5)), is("2d6 acid damage (Dex save half)"));
        assertThat(Spell.ACID_SPLASH.getEffect(level(10)), is("2d6 acid damage (Dex save half)"));
        assertThat(Spell.ACID_SPLASH.getEffect(level(11)), is("3d6 acid damage (Dex save half)"));
    }

    @Test
    public void testSaveAndLoad() {
        assertThat(AttributeType.load(Spell.BLUR.save(TestDoc.doc())), is(Spell.BLUR));
    }

    private Character level(int level) {
        Character character = new Character();
        character.addAttribute(new IntAttribute(AttributeType.LEVEL, level));
        return character;
    }
}
