package characterbuilder.character.equipment;

import characterbuilder.character.ability.Proficiency;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.utils.StringUtils;
import java.util.Optional;
import java.util.function.Function;

public enum EquipmentCategory {
    TREASURE,
    LIGHT_ARMOUR,
    MEDIUM_ARMOUR,
    HEAVY_ARMOUR,
    SHIELD,
    SIMPLE_MELEE(Proficiency.ALL_SIMPLE_MELEE, Weapon::valueOf),
    MARTIAL_MELEE(Proficiency.ALL_MARTIAL_MELEE, Weapon::valueOf),
    SIMPLE_RANGED(Proficiency.ALL_SIMPLE_RANGED, Weapon::valueOf),
    MARTIAL_RANGED(Proficiency.ALL_MARTIAL_RANGED, Weapon::valueOf),
    ADVENTURING_GEAR,
    PROVISIONS,
    MUSICAL_INSTRUMENTS,
    TOOLS,
    CONTAINERS,
    GAMING_SETS,
    CLOTHES,
    AMMUNITION,
    HOLY_SYMBOL,
    ARCANE_FOCUS,
    EQUIPMENT_PACK;

    private final Optional<Attribute> proficiency;
    private final Function<String, Equipment> decoder;

    private EquipmentCategory() {
        this.proficiency = Optional.empty();
        this.decoder = EquipmentType::valueOf;
    }

    private EquipmentCategory(Attribute proficiency, Function<String, Equipment> decoder) {
        this.proficiency = Optional.of(proficiency);
        this.decoder = decoder;
    }

    public Optional<Attribute> getProficiency() {
        return proficiency;
    }

    public Equipment decode(String code) {
        return decoder.apply(code);
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }
}
