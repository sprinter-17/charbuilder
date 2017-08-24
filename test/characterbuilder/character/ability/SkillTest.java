package characterbuilder.character.ability;

import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.saveload.TestDoc;
import characterbuilder.utils.TestCharacter;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SkillTest {

    private TestCharacter character;

    @Before
    public void setup() {
        character = new TestCharacter().withScores(10);
        character.setLevel(7);
    }

    @Test
    public void testBonus() {
        assertThat(Skill.ARCANA.getBonus(character), is(0));
        character.setScore(AttributeType.INTELLIGENCE, 14);
        assertThat(Skill.ARCANA.getBonus(character), is(2));
        character.addAttribute(Skill.ARCANA);
        assertThat(Skill.ARCANA.getBonus(character), is(5));
        character.addAttribute(new Expertise(Skill.ARCANA));
        assertThat(Skill.ARCANA.getBonus(character), is(8));
    }

    @Test
    public void testSaveLoad() {
        assertThat(AttributeType.load(Skill.DECEPTION.save(TestDoc.doc())), is(Skill.DECEPTION));
    }
}
