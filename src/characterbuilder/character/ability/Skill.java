package characterbuilder.character.ability;

import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.*;
import characterbuilder.utils.StringUtils;
import org.w3c.dom.Node;

public enum Skill implements Attribute {
    PERCEPTION(WISDOM),
    HISTORY(INTELLIGENCE),
    INVESTIGATION(INTELLIGENCE),
    INSIGHT(WISDOM),
    MEDICINE(WISDOM),
    PERSUASION(CHARISMA),
    RELIGION(INTELLIGENCE),
    ACROBATICS(DEXTERITY),
    ANIMAL_HANDLING(WISDOM),
    ATHLETICS(STRENGTH),
    INTIMIDATION(CHARISMA),
    SURVIVAL(WISDOM),
    ARCANA(INTELLIGENCE),
    DECEPTION(CHARISMA),
    NATURE(INTELLIGENCE),
    PERFORMANCE(CHARISMA),
    SLEIGHT_OF_HAND(DEXTERITY),
    STEALTH(DEXTERITY);

    private final AttributeType relatedAttribute;

    private Skill(AttributeType relatedAttribute) {
        this.relatedAttribute = relatedAttribute;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.SKILL;
    }

    public AttributeType getRelatedAttribute() {
        return relatedAttribute;
    }

    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    public static Skill load(AttributeType type, Node node) {
        return Skill.valueOf(node.getTextContent());
    }

}
