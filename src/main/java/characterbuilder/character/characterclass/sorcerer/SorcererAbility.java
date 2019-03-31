package characterbuilder.character.characterclass.sorcerer;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeDelegate;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.utils.StringUtils;
import java.util.stream.Stream;
import org.w3c.dom.Element;

public enum SorcererAbility implements Attribute {
    FONT_OF_MAGIC(AttributeDelegate.delegate()
        .withDescription("[$sorcerer_level] sorcery points. ")
        .withDescription("As a bonus action, convert sorcery points to spell slots. "
            + "2 1st, 3 2nd, 5 3rd, 6 4th 7 5th. ")
        .withDescription("Or convert spell slots to sorcery points. "
            + "1 sorcery point for each level. ")),
    SORCEROUS_RESTORATIONS(AttributeDelegate.delegate()
        .withDescription("Regain 4 sorcery points after short rest.")),
    DRACONIC_RESILIENCE(AttributeDelegate.delegate()
        .withDescription("Increase in HP and unarmoured AC [13+$dex_mod].")
        .withAction("Increase HP", (characterbuilder.character.Character ch) -> {
            if (ch.hasAttribute(AttributeType.HIT_POINTS))
                ch.getAttribute(AttributeType.HIT_POINTS, IntAttribute.class)
                    .addValue(ch.getLevel());
        })),
    ELEMENTAL_AFFINITY(AttributeDelegate.delegate()
        .withDescription("+[$chr_mod] to one damage roll of spells dealing [$draconic_damage] damage.")
        .withDescription("Spend 1 sorcery point to gain resistance to [$draconic_damage] for 1 hour.")),
    DRAGON_WINGS(AttributeDelegate.delegate()
        .withDescription("As a bonus action, sprout or dismiss dragon wings if not wearing armour.")
        .withDescription("Gain flying speed equal to current speed.")),
    DRACONIC_PRESENCE(AttributeDelegate.delegate()
        .withDescription("As an action, spend 5 sorcery points to exude aura of awe or fear "
            + "to 60 feet for 1 minute. Creatures in aura are charmed or frightened. Wis save.")),
    WILD_MAGIC_SURGE(AttributeDelegate.delegate()
        .withDescription("Each spell can cause a <em>Wild Magic</em> surge on a roll of 1 on d20.")),
    TIDES_OF_CHAOS(AttributeDelegate.delegate()
        .withDescription("Gain advantage on one attack, ability check or save.")
        .withDescription("Use once between each long rest.")),
    BEND_LUCK(AttributeDelegate.delegate()
        .withDescription("As a reaction to an attack, ability check or save, "
            + "spend 2 sorcery points to apply a d4 penalty to roll.")),
    CONTROLLED_CHAOS(AttributeDelegate.delegate()
        .withDescription("On each <em>Wild Magic</em> surge, roll twice and use either number.")),
    SPELL_BOMBARDMENT(AttributeDelegate.delegate()
        .withDescription("On rolling maximum damage on at least one die for a spell, "
            + "roll the same die again and add to the damage."));

    private final AttributeDelegate delegate;

    private SorcererAbility(AttributeDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.SORCERER_ABILITY;
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

    public static SorcererAbility load(Element element) {
        return valueOf(element.getTextContent());
    }

}
