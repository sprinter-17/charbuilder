package characterbuilder.character.saveload;

import characterbuilder.character.Character;
import characterbuilder.character.ability.RacialTalent;
import characterbuilder.character.ability.Skill;
import characterbuilder.character.attribute.Race;
import static org.junit.Assert.*;
import org.junit.Test;

public class FormatTest {

    @Test
    public void testUpgradeTo2() {
        Format format = new Format(1);
        Character character = new Character();
        character.addAttribute(Race.DARK_ELF);
        format.postProcess(character);
        assertTrue(character.hasAttribute(RacialTalent.TRANCE));
        assertTrue(character.hasAttribute(RacialTalent.FEY_ANCESTORY));
        assertTrue(character.hasAttribute(Skill.PERCEPTION));
    }
}
