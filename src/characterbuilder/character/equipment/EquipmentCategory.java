package characterbuilder.character.equipment;

import characterbuilder.character.ability.Proficiency;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.utils.StringUtils;
import java.util.Optional;
import java.util.function.Function;

public enum EquipmentCategory {
    TREASURE(code -> {
        if (code.startsWith("CUSTOM")) {
            return CustomTreasure.decode(code);
        } else {
            return EquipmentType.valueOf(code);
        }
    }),
    LIGHT_ARMOUR(Armour::valueOf),
    MEDIUM_ARMOUR(Armour::valueOf),
    HEAVY_ARMOUR(Armour::valueOf),
    SHIELD(Armour::valueOf),
    SIMPLE_MELEE(Proficiency.ALL_SIMPLE_MELEE, Weapon::valueOf),
    MARTIAL_MELEE(Proficiency.ALL_MARTIAL_MELEE, Weapon::valueOf),
    SIMPLE_RANGED(Proficiency.ALL_SIMPLE_RANGED, Weapon::valueOf),
    MARTIAL_RANGED(Proficiency.ALL_MARTIAL_RANGED, Weapon::valueOf),
    ADVENTURING_GEAR(EquipmentType::valueOf),
    PROVISIONS(EquipmentType::valueOf),
    MUSICAL_INSTRUMENTS(EquipmentType::valueOf),
    TOOLS(EquipmentType::valueOf),
    CONTAINERS(EquipmentType::valueOf),
    GAMING_SETS(EquipmentType::valueOf),
    CLOTHES(EquipmentType::valueOf),
    AMMUNITION(EquipmentType::valueOf),
    HOLY_SYMBOL(EquipmentType::valueOf),
    ARCANE_FOCUS(EquipmentType::valueOf),
    EQUIPMENT_PACK(EquipmentType::valueOf);

    private final Optional<Attribute> proficiency;
    private final Function<String, Equipment> decoder;

    private EquipmentCategory(Function<String, Equipment> decoder) {
        this.proficiency = Optional.empty();
        this.decoder = decoder;
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
