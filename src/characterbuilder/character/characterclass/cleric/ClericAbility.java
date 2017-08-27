package characterbuilder.character.characterclass.cleric;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeDelegate;
import static characterbuilder.character.attribute.AttributeDelegate.delegate;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.utils.StringUtils;
import java.util.stream.Stream;
import org.w3c.dom.Element;

public enum ClericAbility implements Attribute {
    DIVINE_INTERVENTION(delegate()
        .withDescription("[if($level<20:$level% chance of deity intervening.:Deity intervenes.)] ")
        .withDescription("7 days between each successful use. "
            + "1 day between each unsuccessful use.")),
    CHANNEL_DIVINITY(delegate()
        .withDescription("Use [max($level 2:one, 6:two, 18:three)] channel divinity "
            + "[plural(power,powers)] between each rest.")),
    TURN_UNDEAD(delegate()
        .withDescription("Each undead within 30 feet turned for 1 minute. Wis. save.")),
    DESTROY_UNDEAD(delegate()
        .withDescription("Turned undead with CR of [max($level 5:1/2, 8:1, 11:2, 14:3, 17:4)] "
            + "or less are destroyed")),;

    private final AttributeDelegate delegate;

    private ClericAbility(AttributeDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.CLERIC_ABILITY;
    }

    @Override
    public String toString() {
        return delegate.getName().orElse(StringUtils.capitaliseEnumName(name()));
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return delegate.getDescription(character);
    }

    public static ClericAbility load(Element element) {
        return valueOf(element.getTextContent());
    }

}
