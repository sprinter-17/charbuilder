package characterbuilder.sheet;

import characterbuilder.character.equipment.Equipment;
import characterbuilder.character.equipment.EquipmentCategory;

public enum EquipmentPlacement {
    FRONT,
    BACK,
    TREASURE;

    public static EquipmentPlacement forCategory(EquipmentCategory category) {
        switch (category) {
            case CUSTOM_TREASURE:
            case TREASURE:
                return TREASURE;
            case LIGHT_ARMOUR:
            case MEDIUM_ARMOUR:
            case HEAVY_ARMOUR:
            case SHIELD:
            case SIMPLE_MELEE:
            case MARTIAL_MELEE:
            case SIMPLE_RANGED:
            case MARTIAL_RANGED:
            case DRUIDIC_FOCUS:
            case AMMUNITION:
            case HOLY_SYMBOL:
            case ARCANE_FOCUS:
                return FRONT;
            case ADVENTURING_GEAR:
            case WRITING:
            case LIGHT:
            case PROVISIONS_COOKING:
            case MUSICAL_INSTRUMENT:
            case TOOL:
            case CONTAINER:
            case GAMING_SET:
            case CLOTHES:
            case EQUIPMENT_PACK:
            case TOKEN:
                return BACK;
            default:
                throw new AssertionError(category.name());
        }
    }

    public boolean isPlaceFor(Equipment equipment) {
        return forCategory(equipment.getCategory()).equals(this);
    }
}
