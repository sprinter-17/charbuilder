package characterbuilder.character.attribute;

import characterbuilder.utils.StringUtils;

public enum DamageType {
    ACID,
    BLUDGEONING,
    PIERCING,
    SLASHING,
    COLD,
    FIRE,
    FORCE,
    LIGHTNING,
    NECROTIC,
    POISON,
    THUNDER;

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }
}
