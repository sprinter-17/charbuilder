package characterbuilder.character.ability;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeDelegate;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.*;
import characterbuilder.utils.StringUtils;
import java.util.Arrays;
import java.util.stream.Stream;
import org.w3c.dom.Node;

public enum Ability implements Attribute {
    UNCANNY_DODGE(classTalent()
        .withDescription("Use reaction to halve attack damage from visible attacker.")),
    EVASION(classTalent()
        .withDescription("Save vs Dex for no damage, fail for half.")),
    EXTRA_ATTACK(CLASS_TALENT, "Attack two times in each attack action."),
    ARCANE_RECOVERY(CLASS_TALENT, "Once per day, following a short rest, "
        + "recover [$level /^ 2] [plural(level,levels)] of expended spell slots below sixth level."),
    SPELL_MASTERY(classTalent()
        .withDescription("Choose one first and one second level spell from spellbook.")
        .withDescription("Cast at lowest level without spending a spell slot.")),
    INTIMIDATING_PRESENCE(CLASS_TALENT, "Intimidating Presence",
        "As action, one creature within 30' Wis. save vs DC[8+$prof+$chr_mod] or be frightened."),
    RETALIATION(CLASS_TALENT, "Retaliation",
        "As reaction, make melee weapon attack against creature within 5' that has caused damage."),;

    private static class AbilityDelegate extends AttributeDelegate {

        private final AttributeType type;

        public AbilityDelegate(AttributeType type) {
            this.type = type;
        }
    }

    private static AbilityDelegate classTalent() {
        return new AbilityDelegate(CLASS_TALENT);
    }

    private static AbilityDelegate ability(AttributeType type) {
        return new AbilityDelegate(type);
    }

    private final AbilityDelegate delegate;

    public static Stream<AttributeType> getTypes() {
        return Arrays.stream(values()).map(Ability::getType).distinct();
    }

    private Ability(AttributeDelegate delegate) {
        this.delegate = (AbilityDelegate) delegate;
    }

    private Ability(AttributeType type, String description) {
        this(ability(type).withDescription(description));
    }

    private Ability(AttributeType type, String name, String description) {
        this(ability(type).withName(name).withDescription(description));
    }

    @Override
    public AttributeType getType() {
        return delegate.type;
    }

    @Override
    public String toString() {
        return delegate.getName().orElse(StringUtils.capitaliseEnumName(name()));
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return delegate.getDescription(character);
    }

    @Override
    public void generateInitialChoices(Character character) {
        delegate.generateChoices(character);
    }

    public static Ability load(AttributeType type, Node node) {
        return Ability.valueOf(node.getTextContent());
    }
}
