package characterbuilder.character.saveload;

import characterbuilder.character.Character;
import characterbuilder.character.ability.RacialTalent;
import characterbuilder.character.ability.Skill;
import static characterbuilder.character.attribute.Race.*;
import java.util.stream.Stream;

public class Format {

    public static final int CURRENT = 2;
    private final int fileFormat;

    public Format(int fileFormat) {
        this.fileFormat = fileFormat;
    }

    public void postProcess(Character character) {
        if (fileFormat < 2) {
            addElfAttributes(character);
        }
    }

    private void addElfAttributes(Character character) {
        if (Stream.of(DARK_ELF, HIGH_ELF, WOOD_ELF).anyMatch(character::hasAttribute)) {
            character.addAttributeIfNotPresent(RacialTalent.TRANCE);
            if (character.hasAttribute(DARK_ELF)) {
                character.addAttributeIfNotPresent(RacialTalent.FEY_ANCESTORY);
                character.addAttributeIfNotPresent(Skill.PERCEPTION);
            }
        }
    }
}
