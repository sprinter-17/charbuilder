package characterbuilder.character.ability;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeDelegate;
import static characterbuilder.character.attribute.AttributeDelegate.delegate;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.utils.StringUtils;
import java.util.stream.Stream;
import org.w3c.dom.Element;

public enum RacialTalent implements Attribute {
    DARKVISION(delegate()
        .withDescription("Darkness within 60' appears as dim colourless light.")),
    SUPERIOR_DARKVISION(delegate()
        .withName("Darkvision")
        .withDescription("Darkness within 120' appears as dim colourless light.")),
    POISON_RESISTANCE(delegate()
        .withDescription("Advantage on savings throws against poison "
            + "and resistance against poison damage.")),
    STONECUNNING(delegate()
        .withDescription("Double proficiency on history checks related to stonework.")),
    MASK_OF_THE_WILD(delegate()
        .withDescription("Can attempt to hide when obscured by natural phenomenon.")),
    LUCKY(delegate()
        .withDescription("Reroll 1s on d20 for attacks, ability checks and savings throws.")),
    BRAVE(delegate()
        .withDescription("Advantage on savings throws against being frightened.")),
    NIMBLE(delegate()
        .withName("Halfling Nimbleness")
        .withDescription("Move through the space of larger creatures.")),
    STEALTHY(delegate()
        .withDescription("Can attempt to hide when obscured by larger creature.")),
    BREATH_WEAPON(delegate()
        .withDescription("Breath: [$draconic_breath] vs DC [8+$con_mod+$prof] or "
            + "[max($level 1:2d6,6:3d6,11:4d6,16:5d6)] damage")),
    SUNLIGHT_SENSITIVITY(delegate()
        .withDescription("Disadvantage on attack and perception when target is in direct sunlight")),
    GNOME_CUNNING(delegate()
        .withDescription("Advantage on Int., Wis. and Chr. saves vs. magic.")),
    ARTIFICERS_LORE(delegate()
        .withName("Artificer's Lore")
        .withDescription("+[2*$prof] on history check related to devices.")),
    TINKER(delegate()
        .withDescription("Construct tiny clockwork devices (1 hour, 10GP).")),
    SPEAK_WITH_SMALL_BEASTS(delegate()
        .withDescription("Communicate simple ideas to small animals.")),
    FEY_ANCESTORY(delegate()
        .withDescription("Avantage on save vs. charm; magic cannot cause sleep.")),
    RELENTLESS_ENDURANCE(delegate()
        .withDescription("Drop to 1HP rather than 0HP once between long rests.")),
    SAVAGE_ATTACKS(delegate()
        .withDescription("+1 damage die roll on critical hits.")),
    HELLISH_RESISTANCE(delegate()
        .withDescription("Resistance to fire damage.")),;

    private final AttributeDelegate delegate;

    private RacialTalent(AttributeDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.RACIAL_TALENT;
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return delegate.getDescription(character);
    }

    @Override
    public String toString() {
        return delegate.getName().orElse(StringUtils.capitaliseEnumName(name()));
    }

    public static RacialTalent load(Element element) {
        return valueOf(element.getTextContent());
    }

}
