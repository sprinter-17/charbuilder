package characterbuilder.character.ability;

import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.OptionChoice;
import characterbuilder.utils.StringUtils;
import java.util.Arrays;
import java.util.Optional;
import org.w3c.dom.Element;

public enum Language implements Attribute {
    ABYSSAL(false),
    BULLYWUG(false),
    CELESTIAL(false),
    COMMON(true),
    DEEP_SPEECH(false),
    DWARVISH(true),
    DRACONIC(false),
    DRUIDIC(false),
    ELVISH(true),
    GIANT(true),
    GNOLL(true),
    GNOMISH(true),
    GOBLIN(true),
    HALFLING(true),
    INFERNAL(false),
    ORC(true),
    PRIMORDIAL(false),
    SAHUAGIN(false),
    TROGLODYTE(false),
    SYLVAN(false),
    TERRAN(false),
    THIEVES_CANT("Thieves' Cant", false),
    UNDERCOMMON(false),;

    public static OptionChoice choose(int count) {
        final Language[] standardLanguages = Arrays.stream(values())
            .filter(l -> l.standard).toArray(Language[]::new);
        return new AttributeChoice("Standard Language", standardLanguages);
    }

    private final Optional<String> name;
    private final boolean standard;

    private Language(boolean standard) {
        this.name = Optional.empty();
        this.standard = standard;
    }

    private Language(String name, boolean standard) {
        this.name = Optional.of(name);
        this.standard = standard;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.LANGUAGE;
    }

    @Override
    public String toString() {
        return name.orElse(StringUtils.capitaliseEnumName(name()));
    }

    public static Language load(Element element) {
        return valueOf(element.getTextContent());
    }
}
