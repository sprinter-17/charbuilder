package characterbuilder.character.saveload;

import characterbuilder.character.Character;
import characterbuilder.character.ability.RacialTalent;
import characterbuilder.character.ability.Skill;
import static characterbuilder.character.attribute.Race.*;
import java.util.stream.Stream;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Format {

    public static final int CURRENT = 5;
    private final int fileFormat;

    public Format(int fileFormat) {
        this.fileFormat = fileFormat;
    }

    public void preProcess(Element attributeElement, Element inventoryElement) {
        if (fileFormat < 3) {
            Node levelElement = attributeElement.removeChild(
                Savable.child(attributeElement, "level"));
            Savable.child(attributeElement, "character_class")
                .setAttribute("level", levelElement.getTextContent());
        }
        if (fileFormat < 4) {
            Savable.children(attributeElement, "spellcasting").forEach(castingElement -> {
                int maxLevel = Savable.children(castingElement, "spell_slot")
                    .mapToInt(ss -> Integer.valueOf(ss.getAttribute("level")))
                    .max().orElse(0);
                castingElement.setAttribute("max_level", Integer.toString(maxLevel));
            });
        }
        if (fileFormat < 5) {
            Savable.children(attributeElement, "spell_ability").forEach(spellElement -> {
                spellElement.setAttribute("ability", spellElement.getAttribute("ability_score"));
            });
        }
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
