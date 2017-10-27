package characterbuilder.character;

import characterbuilder.character.attribute.Alignment;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.Background;
import characterbuilder.character.attribute.Race;
import characterbuilder.character.characterclass.CharacterClass;
import characterbuilder.character.characterclass.CharacterClassLevel;
import java.util.Random;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class CharacteristicGeneratorTest {

    private Random random;
    private CharacteristicGenerator generator;

	@Before
    public void setup() {
        random = new Random() {
            @Override
            public int nextInt(int bound) {
                return 0;
            }
        };
        generator = new CharacteristicGenerator(random);
    }

    @Test
    public void testGenerate() {
        Character character = new Character();
        character.addAttributes(Race.HUMAN, Background.ACOLYTE);
        character.addAttribute(new CharacterClassLevel(CharacterClass.CLERIC, 1));
        character.addAttribute(Alignment.LAWFUL_GOOD);
        generator.generate(character);
        assertThat(character.getAllAttributes()
            .filter(attr -> AttributeType.PERSONALITY.contains(attr.getType()))
            .count(), is(4L));
    }

    @Test
    public void testConditions() {
        Character character = new Character();
        character.addAttributes(Race.HUMAN, Background.ACOLYTE);
        character.addAttribute(new CharacterClassLevel(CharacterClass.CLERIC, 1));
        character.addAttribute(Alignment.LAWFUL_GOOD);
        generator.generate(character);
        assertTrue(character.getAllAttributes().anyMatch(
            ch -> ch.toString().startsWith("The ancient traditions")));
        character.removeAttributesOfType(AttributeType.ALIGNMENT);
        character.addAttribute(Alignment.CHAOTIC_GOOD);
        generator.generate(character);
        assertTrue(character.getAllAttributes().anyMatch(
            ch -> ch.toString().startsWith("I always try to help")));
    }

}
