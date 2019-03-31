package characterbuilder.character.characterclass.bard;

import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeDelegate;
import static characterbuilder.character.attribute.AttributeDelegate.delegate;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.utils.StringUtils;
import java.util.stream.Stream;
import org.w3c.dom.Element;

public enum BardAbility implements Attribute {
    BARDIC_INSPIRATION(delegate()
        .withDescription("As bonus action inspire 1 creature within 60'; "
            + "add [max($bard_level 1:d6,5:d8,10:d10,15:d12)] to one ability, attack or save; "
            + "use [$chr_mod] [plural(time,times)] between long rests")),
    JACK_OF_ALL_TRADES(delegate()
        .withDescription("Add +[$prof/2] to non-proficient ability checks.")),
    SONG_OF_REST(delegate()
        .withDescription("During short rests friends regain additional "
            + "1d[max($bard_level 2:6,9:8,13:10,17:12)]HP")),
    FONT_OF_INSPIRATION(delegate()
        .withDescription("Regain all Bardic Inspirations in short and long rests.")),
    COUNTERCHARM(delegate()
        .withDescription("As an action, all friends within 30' have advantage "
            + "on saves vs fear and charm.")),
    SUPERIOR_INSPIRATION(delegate()
        .withDescription("Regain 1 Bardic Inspiration on initiative roll, if no uses left.")),
    CUTTING_WORDS(delegate()
        .withDescription("As a reaction, use Bardic Inspiration to subtract die roll from attack, "
            + "ability, damage from creature within 60'")),
    PEERLESS_SKILL(delegate()
        .withDescription("Use Bardic Inspiration for ability checks.")),
    COMBAT_INSPIRATION(delegate()
        .withDescription("Creature with Bardic Inspiration can add roll to damage or AC as reaction.")),
    BATTLE_MAGIC(delegate()
        .withDescription("Can make one weapon attack as bonus action when casting spell."));
    private final AttributeDelegate delegate;

    private BardAbility(AttributeDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.BARD_ABILITY;
    }

    @Override
    public void generateInitialChoices(characterbuilder.character.Character character) {
        delegate.generateChoices(character);
    }

    @Override
    public String toString() {
        return delegate.getName().orElse(StringUtils.capitaliseEnumName(name()));
    }

    @Override
    public Stream<String> getDescription(characterbuilder.character.Character character) {
        return delegate.getDescription(character);
    }

    public static BardAbility load(Element element) {
        return valueOf(element.getTextContent());
    }

}
