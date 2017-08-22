package characterbuilder.character.ability;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeDelegate;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.FIGHTING_STYLE;
import java.util.stream.Stream;
import org.w3c.dom.Element;

public enum FightingStyle implements Attribute {
    ARCHERY(style().withDescription("+2 attack bonus with ranged weapons.")),
    DEFENSE(style().withDescription("+1 AC when wearing armour.")),
    DUELING(style().withDescription("+2 damage with melee weapon when other hand is empty.")),
    GREAT_WEAPON(style().withDescription("Reroll 1s and 2s for damage when using "
        + "a two handed weapon.")),
    PROTECTION(style().withDescription("Disadvantage attacks against other targets "
        + "within 5 feet when using a shield.")),
    TWO_WEAPON(style().withDescription("Add ability modifier to damage of second weapon attack."));

    private final AttributeDelegate delegate;

    private static AttributeDelegate style() {
        return new AttributeDelegate();
    }

    private FightingStyle(AttributeDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public AttributeType getType() {
        return FIGHTING_STYLE;
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return delegate.getDescription(character);
    }

    public static FightingStyle load(Element element) {
        return valueOf(element.getTextContent());
    }
}
