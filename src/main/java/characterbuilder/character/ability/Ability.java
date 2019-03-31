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
    EXTRA_ATTACK(CLASS_TALENT, "Attack two times in each attack action."),;

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
