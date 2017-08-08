package characterbuilder.character;

import characterbuilder.character.attribute.Alignment;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.Background;
import characterbuilder.character.characterclass.CharacterClass;
import characterbuilder.character.attribute.Race;
import java.util.Random;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CharacteristicGeneratorTest {

    private Random random;
    private CharacteristicGenerator generator;

    @Before
    public void setup() {
        random = mock(Random.class);
        generator = new CharacteristicGenerator(random);
    }

    @Test
    public void testGenerate() {
        Character character = new Character();
        character.addAttributes(Race.HUMAN, CharacterClass.CLERIC, Background.ACOLYTE);
        when(random.nextInt(anyInt())).thenReturn(0);
        character.addAttribute(Alignment.LAWFUL_GOOD);
        generator.generate(character);
        assertThat(character.getAllAttributes()
            .filter(attr -> AttributeType.PERSONALITY.contains(attr.getType()))
            .count(), is(4L));
    }

    @Test
    public void testConditions() {
        Character character = new Character();
        character.addAttributes(Race.HUMAN, CharacterClass.CLERIC, Background.ACOLYTE);
        when(random.nextInt(anyInt())).thenReturn(0);
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
