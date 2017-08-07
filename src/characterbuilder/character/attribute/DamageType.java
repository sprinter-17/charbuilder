package characterbuilder.character.attribute;

import characterbuilder.utils.StringUtils;

public enum DamageType {
    ACID,
    COLD,
    LIGHTNING,
    THUNDER;

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }
}
