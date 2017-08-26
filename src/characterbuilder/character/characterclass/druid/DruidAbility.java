package characterbuilder.character.characterclass.druid;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeDelegate;
import static characterbuilder.character.attribute.AttributeDelegate.delegate;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.utils.StringUtils;
import java.util.stream.Stream;
import org.w3c.dom.Element;

public enum DruidAbility implements Attribute {
    WILD_SHAPE(delegate()
        .withDescription("As an action, assume the shape of a beast of up to "
            + "CR[max($level 2:1/4,4:1/2,8:1)].")
        .withDescription("Use [max($level 2:twice,20:unlimited number of times)] between rests.")),
    TIMELESS_BODY(delegate()
        .withDescription("Age at 1/10th normal rate.")),
    BEAST_SPELLS(delegate()
        .withDescription("Cast spells without material components while using <em>Wild Shape</em>."));
    private final AttributeDelegate delegate;

    private DruidAbility(AttributeDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.DRUID_ABILITY;
    }

    @Override
    public void generateInitialChoices(Character character) {
        delegate.generateChoices(character);
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return delegate.getDescription(character);
    }

    @Override
    public String toString() {
        return delegate.getName().orElse(StringUtils.capitaliseEnumName(name()));
    }

    public static DruidAbility load(Element element) {
        return valueOf(element.getTextContent());
    }

}
