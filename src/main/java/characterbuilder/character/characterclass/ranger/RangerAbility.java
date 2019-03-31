package characterbuilder.character.characterclass.ranger;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeDelegate;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.saveload.Savable;
import characterbuilder.utils.StringUtils;
import java.util.stream.Stream;
import org.w3c.dom.Element;

public enum RangerAbility implements Attribute {
    PRIMEVAL_AWARENESS(ability()
        .withDescription("As an action, expend a spell slot. "
            + "For 1 minute / spell level sense presence of "
            + "aberrations, celestials, dragons, elementals, fey, fiends and undead "
            + "within 1 mile or 6 miles within favoured terrain.")),
    LANDS_STRIDE(ability()
        .withName("Land's Stride")
        .withDescription("Move through difficult terrain at normal speed. ")
        .withDescription("Avoid damage from plants. "
            + "Advantage on saves vs. plants magically impeding movement. ")),
    HIDE_IN_PLAIN_SIGHT(ability()
        .withDescription("Spend 1 minute creating camouflage and hiding against solid surface.")
        .withDescription("Gain +10 to Stealth checks. ")),
    VANISH(ability()
        .withDescription("Hide as a bonus action. Cannot be tracked.")),
    FERAL_SENSES(ability()
        .withDescription("Can attack invisible creatures without disadvantage. ")
        .withDescription("Aware of invisible creatures within 30 feet")),
    FOE_SLAYER(ability()
        .withDescription("Once each turn add +[$wis_mod] to attack or damage "
            + "against favoured enemy.")),
    COLOSSUS_SLAYER(ability()
        .withDescription("Once each turn add 1d8 damage to a creature that is below maximum HP.")),
    GIANT_KILLER(ability()
        .withDescription("As a reaction attack a large creature that hits or misses.")),
    HORDE_BREAKER(ability()
        .withDescription("Once each turn make a second melee attack against a different "
            + "target within 5 feet.")),
    ESCAPE_THE_HORDE(ability()
        .withDescription("Opportunity attacks by enemies are disadvantaged.")),
    MULTIATTACK_DEFENSE(ability()
        .withDescription("+4 AC for second and subsequent attacks by an enemy within a single turn.")),
    STEEL_WILL(ability()
        .withDescription("Advantage on save vs. fear.")),
    VOLLEY(ability()
        .withDescription("Make ranged attacks against any number of targets "
            + "within 10 feet of a chosen point.")),
    WHIRLWIND_ATTACK(ability()
        .withDescription("Make melee attacks against any number of targets within 5 feet.")),
    STAND_AGAINST_THE_TIDE(ability()
        .withDescription("As a reaction force an enemy that misses to repeat the attack "
            + "on another creature.")),
    RANGERS_COMPANION(ability()
        .withName("Ranger's Companion")
        .withDescription("As an action, command the beast to "
            + "Attack, Dash, Disengage, Dodge or Help.")),
    EXCEPTIONAL_TRAINING(ability()
        .withDescription("As a bonus action on any turns when the Ranger's companion does not attack, "
            + "command the beast can Dash, Disengage, Dodge or Help.")),
    BESTIAL_FURY(ability()
        .withDescription("Ranger's companion can make two attacks.")),
    SHARE_SPELLS(ability()
        .withDescription("Any spells targeting self can also effect Ranger's companion "
            + "within 30 feet.")),;

    private final AttributeDelegate delegate;

    private static AttributeDelegate ability() {
        return new AttributeDelegate();
    }

    private RangerAbility(AttributeDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.RANGER_ABILITY;
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

    public static Attribute load(Element element) {
        switch (Savable.text(element)) {
            case "FAVOURED_TERRAIN":
                return FavouredTerrain.load(element);
            case "FAVOURED_ENEMY":
                return FavouredEnemy.load(element);
            default:
                return valueOf(element.getTextContent());
        }
    }
}
