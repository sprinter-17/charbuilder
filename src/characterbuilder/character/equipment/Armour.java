package characterbuilder.character.equipment;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.Value;
import static characterbuilder.character.attribute.Value.gp;
import characterbuilder.character.attribute.Weight;
import static characterbuilder.character.attribute.Weight.lb;
import static characterbuilder.character.equipment.EquipmentCategory.HEAVY_ARMOUR;
import static characterbuilder.character.equipment.EquipmentCategory.LIGHT_ARMOUR;
import static characterbuilder.character.equipment.EquipmentCategory.MEDIUM_ARMOUR;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public enum Armour implements Equipment {
    //
    PADDED_ARMOUR(LIGHT_ARMOUR, gp(5), lb(8), 1),
    LEATHER_ARMOUR(LIGHT_ARMOUR, gp(10), lb(10), 1),
    STUDDED_LEATHER_ARMOUR(LIGHT_ARMOUR, gp(45), lb(13), 2),
    //
    HIDE_ARMOUR(MEDIUM_ARMOUR, gp(10), lb(12), 2),
    CHAIN_SHIRT_ARMOUR(MEDIUM_ARMOUR, gp(50), lb(20), 3),
    SCALE_MAIL_ARMOUR(MEDIUM_ARMOUR, gp(50), lb(45), 4),
    BREASTPLATE_ARMOUR(MEDIUM_ARMOUR, gp(400), lb(20), 4),
    HALF_PLATE_ARMOUR(MEDIUM_ARMOUR, gp(750), lb(40), 5),
    //
    RING_MAIL_ARMOUR(HEAVY_ARMOUR, gp(30), lb(40), 4),
    CHAIN_MAIL_ARMOUR(HEAVY_ARMOUR, gp(75), lb(55), 6),
    SPLINT_ARMOUR(HEAVY_ARMOUR, gp(200), lb(60), 7),
    PLATE_ARMOUR(HEAVY_ARMOUR, gp(1500), lb(65), 8),
    //
    SHIELD(EquipmentCategory.SHIELD, gp(10), lb(6), 2), //
    ;

    private final EquipmentCategory category;
    private final Value cost;
    private final Weight weight;
    private final int armourClass;

    private Armour(EquipmentCategory category, Value cost, Weight weight, int armourClass) {
        this.category = category;
        this.cost = cost;
        this.weight = weight;
        this.armourClass = armourClass;
    }

    @Override
    public EquipmentCategory getCategory() {
        return category;
    }

    @Override
    public Weight getWeight() {
        return weight;
    }

    @Override
    public Value getValue() {
        return cost;
    }

    @Override
    public String encode() {
        return name();
    }

    public static int getArmourClass(Character character) {
        int ac = 10;
        Optional<Armour> bestArmour = Arrays.stream(values())
            .filter(arm -> !arm.equals(SHIELD))
            .filter(character::hasEquipment)
            .max(Comparator.comparing(arm -> arm.armourClass));
        ac += bestArmour.map(arm -> arm.armourClass).orElse(0);
        if (character.hasEquipment(SHIELD))
            ac += 2;
        if (!(bestArmour.isPresent() && bestArmour.get().category.equals(HEAVY_ARMOUR)))
            ac += character.getModifier(AttributeType.DEXTERITY);
        return ac;
    }

}
