package characterbuilder.character.characterclass.wizard;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeDelegate;
import static characterbuilder.character.attribute.AttributeDelegate.delegate;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.utils.StringUtils;
import java.util.stream.Stream;
import org.w3c.dom.Element;

public enum WizardAbility implements Attribute {
    ARCANE_RECOVERY(delegate()
        .withDescription("Once per day, following a short rest, recover [$level /^ 2] "
            + "[plural(level,levels)] of expended spell slots below sixth level.")),
    SPELL_MASTERY(delegate()
        .withDescription("Choose one first and one second level spell from spellbook.")
        .withDescription("Cast at lowest level without spending a spell slot.")),;

    private final AttributeDelegate delegate;

    private WizardAbility(AttributeDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.WIZARD_ABILITY;
    }

    @Override
    public String toString() {
        return delegate.getName().orElse(StringUtils.capitaliseEnumName(name()));
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return delegate.getDescription(character);
    }

    public static WizardAbility load(Element element) {
        return valueOf(element.getTextContent());
    }

}
