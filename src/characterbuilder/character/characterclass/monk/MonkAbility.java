package characterbuilder.character.characterclass.monk;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeDelegate;
import static characterbuilder.character.attribute.AttributeDelegate.delegate;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.utils.StringUtils;
import java.util.stream.Stream;
import org.w3c.dom.Element;

public enum MonkAbility implements Attribute {
    UNARMORED_DEFENCE(delegate()
        .withDescription("Unarmoured AC[10+$dex_mod+$wis_mod]")),
    MARTIAL_ARTS(delegate()
        .withDescription("Use Dex. instead of Str. for unarmed strikes and monk weapons.")
        .withDescription("Can roll [max($level 1:1d4,5:1d6,11:1d8,17:1d10)] for damage.")
        .withDescription("Unarmed strike as bonus action")),
    KI(delegate()
        .withDescription("[$level] Ki points to use between each rest.")
        .withDescription("Ki Save DC[8+$prof+$wis_mod].")),
    FLURRY_OF_BLOWS(delegate()
        .withDescription("Spend 1 Ki point to make two unarmed strikes as a bonus action.")),
    PATIENT_DEFENCE(delegate()
        .withDescription("Spend 1 Ki point to Dodge as a bonus action.")),
    STEP_OF_THE_WIND(delegate()
        .withDescription("Spend 1 Ki point to Disengage or Dash as a bonus action.")
        .withDescription("Jump distance doubled.")),
    UNARMOURED_MOVEMENT(delegate()
        .withDescription("Speed increases by [max($level 2:10,6:15,10:20,14:25,18:30)]' "
            + "when not armoured. ")
        .withDescription("[if($level >= 9:Move along vertical surfaces and across liquids)]")),
    DEFLECT_MISSILES(delegate()
        .withDescription("Use reaction when hit by ranged attack to reduce damage "
            + "by 1d10+[$dex_mod+$level].")
        .withDescription("If damage is 0, spend 1 Ki point to make ranged attack to hit +[$prof]"
            + "martial arts damage range 20'/60'.")),
    SLOW_FALL(delegate()
        .withDescription("Use reaction to reduce falling damage by [5*$level].")),
    STUNNING_STRIKE(delegate()
        .withDescription("Spend 1 Ki point on melee weapon hit. ")
        .withDescription("Target Con. save or stunned until end of next turn.")),
    KI_EMPOWERED_STRIKES(delegate()
        .withDescription("Unarmed strikes count as magical.")),
    STILLNESS_OF_MIND(delegate()
        .withDescription("Use action to end charm or fear effect.")),
    PURITY_OF_BODY(delegate().withDescription("Immune to disease and poison.")),
    TONGUE_OF_THE_SUN_AND_MOON(delegate()
        .withDescription("Understand all spoken languages.")),
    DIAMOND_SOUL(delegate().withDescription("Proficiency in all saves.")),
    TIMELESS_BODY(delegate()
        .withDescription("Cannot be magically aged. Need no food or water.")),
    EMPTY_BODY(delegate()
        .withDescription("Spend 4 Ki points to become invisible for 1 minute.")),
    PERFECT_SELF(delegate()
        .withDescription("Regain 4 Ki points on initiative if none remaining.")),
    OPEN_HAND_TECHNIQUE(delegate()
        .withDescription("When hitting a creature with Flurry of Blows, impose one effect: "
            + "Dex. save or knocked prone; Str save or push 15'; "
            + "no reactons until end of next turn.")),
    WHOLENESS_OF_BODY(delegate()
        .withDescription("As an action, regain [$level*3]HP once between each long rest.")),
    TRANQUILITY(delegate()
        .withDescription("At the end of a long rest, gain sanctuary. "
            + "Attacker must make Wis. save DC[8+$wis_mod+$prof] or target another. "
            + "Attacking ends the effect.")),
    QUIVERING_PALM(delegate()
        .withDescription("Spend 3 Ki points to start vibrations that last for [$level] days. "
            + "At end, Con. save or reduce to 0HP. Succeed on save 10d10 necrotic dam.")),
    SHADOW_STEP(delegate()
        .withDescription("When in dim light or darkness, as a bonus action teleport up to 60'.")),
    CLOAK_OF_SHADOWS(delegate()
        .withDescription("When in dim light or darkness, as an action become invisible.")),
    OPPORTUNIST(delegate()
        .withDescription("As a reaction, when a creature within 5' that is hit, "
            + "make a melee attack.")),
    DISCIPLE_OF_THE_ELEMENTS(delegate()
        .withDescription("Spend up to [max($level 3:2,5:3,9:4,13:5,17:7)] Ki points "
            + "to cast an elemental spell."));
    private final AttributeDelegate delegate;

    private MonkAbility(AttributeDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.MONK_ABILITY;
    }

    @Override
    public void generateInitialChoices(Character character) {
        delegate.generateChoices(character);
    }

    @Override
    public String toString() {
        return delegate.getName().orElse(StringUtils.capitaliseEnumName(name()));
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return delegate.getDescription(character);
    }

    public static MonkAbility load(Element element) {
        return valueOf(element.getTextContent());
    }

}
