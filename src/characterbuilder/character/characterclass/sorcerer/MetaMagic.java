package characterbuilder.character.characterclass.sorcerer;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeDelegate;
import static characterbuilder.character.attribute.AttributeDelegate.delegate;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.utils.StringUtils;
import java.util.stream.Stream;
import org.w3c.dom.Element;

public enum MetaMagic implements Attribute {
    CAREFUL_SPELL(delegate()
        .withDescription("Spend 1 sorcery point. "
            + "Choose [if($chr_mod <= 1:1 creature: up to $chr_mod creatures)] "
            + "to automatically succeed on their save for the spell.")),
    DISTANT_SPELL(delegate()
        .withDescription("Spend 1 sorcery point. Double range of spell, or extend range of "
            + "<em>Touch</em> to 30 feet.")),
    EMPOWERED_SPELL(delegate()
        .withDescription("Spend 1 sorcery point. "
            + "Reroll [if($chr_mod <= 1:1 damage die: up to $chr_mod damage dice)].")),
    EXENDED_SPELL(delegate()
        .withDescription("Spend 1 sorcery point. "
            + "Double duration of spell with duration of at least 1 minute up to 24 hours.")),
    HEIGHTENED_SPELL(delegate()
        .withDescription("Spend 3 sorcery points. One target of the spell has disadvantage on save.")),
    QUICKENED_SPELL(delegate()
        .withDescription("Spend 2 sorcery points to change casting time from 1 action "
            + "to 1 bonus action.")),
    SUBTLE_SPELL(delegate()
        .withDescription("Spend 1 sorcery point to cast without somatic or verbal components.")),
    TWINNED_SPELL(delegate()
        .withDescription("Spend 1 sorcery point per level. Target a second creature with a spell "
            + "that targets a single creature."));

    private final AttributeDelegate delegate;

    private MetaMagic(AttributeDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.META_MAGIC;
    }

    @Override
    public String toString() {
        return delegate.getName().orElse(StringUtils.capitaliseEnumName(name()));
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return delegate.getDescription(character);
    }

    public static MetaMagic load(Element element) {
        return valueOf(element.getTextContent());
    }
}
