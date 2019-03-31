package characterbuilder.character.characterclass.ranger;

import characterbuilder.character.ability.Language;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.saveload.TestDoc.doc;
import characterbuilder.utils.TestCharacter;
import static characterbuilder.utils.TestMatchers.hasAttribute;
import static characterbuilder.utils.TestMatchers.hasChoice;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXParseException;

public class FavouredEnemyTest {

    private TestCharacter character;

    @Before
    public void setup() {
        character = new TestCharacter();
        character.addChoice(FavouredEnemy.choice());
    }

    @Test
    public void testNoLanguages() {
        character.selectChoice("Favoured Enemy", "Plants");
        assertFalse(character.hasAttribute(AttributeType.LANGUAGE));
    }

    @Test
    public void testOneLanguage() {
        character.selectChoice("Favoured Enemy", "Dragons");
        assertThat(character, hasAttribute("Draconic"));
    }

    @Test
    public void testOneLanguageAlreadyPresent() {
        character.addAttribute(Language.DRACONIC);
        character.selectChoice("Favoured Enemy", "Dragons");
        assertThat(character, hasAttribute("Draconic"));
    }

    @Test
    public void testTwoLanguage() {
        character.selectChoice("Favoured Enemy", "Bugbears");
        assertThat(character, not(hasAttribute("Common")));
        assertThat(character, not(hasAttribute("Goblin")));
        assertThat(character, hasChoice("Language for Bugbears"));
        character.selectChoice("Language for Bugbears", "Goblin");
        assertThat(character, not(hasAttribute("Common")));
        assertThat(character, hasAttribute("Goblin"));
    }

    @Test
    public void testTwoLanguageWithOneAlreadyPresent() {
        character.addAttribute(Language.COMMON);
        character.selectChoice("Favoured Enemy", "Bugbears");
        assertThat(character, hasAttribute("Goblin"));
    }

    @Test
    public void testSaveAndLoad() throws SAXParseException {
        FavouredEnemy favouredEnemy = new FavouredEnemy();
        favouredEnemy.addEnemy(FavouredEnemy.Enemy.FEY);
        favouredEnemy.addEnemy(FavouredEnemy.Enemy.FIENDS);
        assertThat(AttributeType.load(favouredEnemy.save(doc())), is(favouredEnemy));
    }

}
