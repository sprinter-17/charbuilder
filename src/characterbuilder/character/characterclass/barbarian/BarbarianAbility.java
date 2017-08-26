package characterbuilder.character.characterclass.barbarian;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeDelegate;
import static characterbuilder.character.attribute.AttributeDelegate.delegate;
import characterbuilder.character.attribute.AttributeType;
import java.util.stream.Stream;
import org.w3c.dom.Element;

public enum BarbarianAbility implements Attribute {
    RAGE(delegate()
        .withDescription("As bonus action, enter rage for 1 minute. ")
        .withDescription("Advantage on Str. checks and saves.")
        .withDescription("+[max($level 1:2,9:3,16:4)] dam on melee attacks. ")
        .withDescription("Resistance to bludgeoning, piercing and slashing damage. "
            + "Use [max($level 1:2,6:4,12:5,17:6)] times between long rests. ")),
    UNARMORED_DEFENCE(delegate()
        .withDescription("Unarmoured AC[10+$dex_mod+$con_mod].")),
    RECKLESS_ATTACK(delegate()
        .withDescription("Choose to attack recklessly gaining and giving advantage "
            + "on melee attacks.")),
    DANGER_SENSE(delegate()
        .withDescription("Advantage on Dex. saving throws against visible effects")),
    FAST_MOVEMENT(delegate()
        .withDescription("+10' speed when unarmoured.")),
    FERAL_INSTINCTS(delegate()
        .withDescription("Advantage on initiative, enter rage and act normally on surprise.")),
    BRUTAL_CRITICAL(delegate()
        .withDescription("Roll [max($level 9:1,13:2,17:3)] extra [plural(die,dice)] "
            + "damage on critical.")),
    RELENTLESS_RAGE(delegate()
        .withDescription("When dropping to 0HP during rage, make Con. save vs DC10 (+5 per use) "
            + "to drop to 1HP instead.")),
    PERSISTENT_RAGE(delegate()
        .withDescription("Rage continues until ended voluntarily or falls unconscious.")),
    INDOMITABLE_MIGHT(delegate().withDescription("Str check minimum [$str].")),
    FRENZY(delegate().withDescription("Can enter frenzy during rage. ")
        .withDescription("Melee weapon attack as bonus action each turn. ")
        .withDescription("Exhaustion when rage ends.")),
    MINDLESS_RAGE(delegate()
        .withDescription("Cannot be charmed or frightened during rage.")),
    TOTEM_SPIRIT_BEAR(delegate().withName("Totem Spirit (Bear)")
        .withDescription("During rage, resistance to all damage except psychic.")),
    TOTEM_SPIRIT_EAGLE(delegate().withName("Totem Spirit (Eagle)")
        .withDescription("During rage, if not wearing heavy armour, "
            + "disadvantage opportunity attacks, " + "take Dash bonus action.")),
    TOTEM_SPIRIT_WOLF(delegate().withName("Totem Spirit (Wolf)")
        .withDescription("During rage, friends have advantage on melee attacks "
            + "against enemies within 5'.")),
    ASPECT_OF_BEAST_BEAR(delegate().withName("Aspect of the Beast (Bear)")
        .withDescription("Carrying capacity doubled. Advantage on Str. checks moving objects.")),
    ASPECT_OF_BEAST_EAGLE(delegate().withName("Aspect of the Beast (Eagle)")
        .withDescription("See up to 1 mile without difficulty. "
            + "Dim light does not disadvantage Perception checks.")),
    ASPECT_OF_BEAST_WOLF(delegate().withName("Aspect of the Beast (Wolf)")
        .withDescription("Track at fast pace; stealth at normal pace.")),
    TOTEMIC_ATTUNEMENT_BEAR(delegate().withName("Totemic Attunement (Bear)")
        .withDescription("During rage, enemies within 5' disadvantaged on attacks on friends.")),
    TOTEMIC_ATTUNEMENT_EAGLE(delegate().withName("Totemic Attunement (Eagle)")
        .withDescription("During rage, fly at [$speed]'")),
    TOTEMIC_ATTUNEMENT_WOLF(delegate().withName("Totemic Attunement (Wolf)")
        .withDescription("During rage, as bonus action knock enemy prone when hit "
            + "with melee attack.")),
    INTIMIDATING_PRESENCE(delegate()
        .withDescription("As action, one creature within 30' Wis. save vs DC[8+$prof+$chr_mod] "
            + "or be frightened.")),
    RETALIATION(delegate()
        .withDescription("As a reaction, make a melee weapon attack against a creature "
            + "within 5 feet that has caused damage.")),;
    private final AttributeDelegate delegate;

    private BarbarianAbility(AttributeDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.BARBARIAN_ABILITY;
    }

    @Override
    public void generateInitialChoices(Character character) {
        delegate.generateChoices(character);
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return delegate.getDescription(character);
    }

    public static BarbarianAbility load(Element element) {
        return valueOf(element.getTextContent());
    }
}
