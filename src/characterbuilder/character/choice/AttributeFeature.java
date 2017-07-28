package characterbuilder.character.choice;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import java.util.Optional;

public class AttributeFeature implements Choice {

    private final Attribute attribute;
    private final Optional<Attribute> replace;

    public AttributeFeature(Attribute attribute) {
        this.attribute = attribute;
        this.replace = Optional.empty();
    }

    public AttributeFeature(Attribute attribute, Attribute replace) {
        this.attribute = attribute;
        this.replace = Optional.of(replace);
    }

    @Override
    public void addTo(Character character) {
        replace.ifPresent(character::removeAttribute);
        character.addAttribute(attribute);
    }
}
