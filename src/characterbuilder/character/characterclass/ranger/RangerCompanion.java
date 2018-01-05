package characterbuilder.character.characterclass.ranger;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.beast.Beast;
import static characterbuilder.character.saveload.Savable.text;
import java.util.stream.Stream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class RangerCompanion implements Attribute {

    private final Beast companion;

    public RangerCompanion(Beast companion) {
        this.companion = companion;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.RANGERS_COMPANION;
    }

    @Override
    public void generateInitialChoices(Character character) {
        character.addAttribute(RangerAbility.RANGERS_COMPANION);
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return companion.getDescription();
    }

    @Override
    public Element save(Document doc) {
        Element element = getType().save(doc);
        element.setTextContent(companion.name());
        return element;
    }

    public static RangerCompanion load(Element element) {
        return new RangerCompanion(Beast.valueOf(text(element)));
    }

    @Override
    public String toString() {
        return companion.toString();
    }

    @Override
    public int hashCode() {
        return companion.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        final RangerCompanion other = (RangerCompanion) obj;
        return this.companion == other.companion;
    }

}
