package characterbuilder.character.attribute;

import characterbuilder.utils.StringUtils;

public enum DamageType {
    ACID,
    COLD,
    FIRE,
    LIGHTNING,
    POISON,
    THUNDER;

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }
}
