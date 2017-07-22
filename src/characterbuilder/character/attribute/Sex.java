package characterbuilder.character.attribute;

import characterbuilder.utils.StringUtils;

public enum Sex implements Attribute {
    MALE, FEMALE;

    @Override
    public AttributeType getType() {
        return AttributeType.SEX;
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }
}
