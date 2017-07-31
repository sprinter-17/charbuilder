package characterbuilder.character.equipment;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Value;
import characterbuilder.character.attribute.Weight;
import static characterbuilder.character.equipment.EquipmentType.*;
import characterbuilder.utils.StringUtils;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public enum EquipmentPack implements Equipment {
    BUGLAR_PACK(Value.GP.times(16),
        BACKPACK, set(1000, BALL_BEARINGS), STRING, BELL, set(5, CANDLE), CROWBAR,
        HAMMER, set(10, PITON), LANTERN_HOODED, set(2, OIL_FLASK), set(5, RATION),
        TINDERBOX, WATERSKIN),
    DIPLOMAT_PACK(Value.GP.times(39),
        CHEST, set(2, SCROLL_CASE), FINE_CLOTHES, INK_BOTTLE, INK_PEN, LAMP, set(2, OIL_FLASK),
        set(5, PAPER_SHEET), PERFUME_VIAL, SEALING_WAX, SOAP),
    DUNGEONEER_PACK(Value.GP.times(12),
        BACKPACK, CROWBAR, HAMMER, set(10, PITON), set(10, TORCH), TINDERBOX,
        set(10, RATION), WATERSKIN, ROPE_HEMPEN),
    ENTERTAINER_PACK(Value.GP.times(40),
        BACKPACK, BEDROLL, set(2, COSTUME), set(5, CANDLE), set(5, CANDLE), set(5, RATION),
        WATERSKIN, DISGUISE_KIT),
    EXPLORER_PACK(Value.GP.times(10),
        BACKPACK, BEDROLL, MESS_KIT, TINDERBOX, set(10, TORCH), set(10, RATION), WATERSKIN,
        ROPE_HEMPEN),
    PRIEST_PACK(Value.GP.times(19),
        BACKPACK, BLANKET, set(10, CANDLE), TINDERBOX, ALMS_BOX, set(2, INCENSE_BLOCK),
        CENSER, VESTMENTS, set(2, RATION), WATERSKIN),
    SCHOLAR_PACK(Value.GP.times(40),
        BACKPACK, BOOK, INK_BOTTLE, INK_PEN, set(10, PARCHMENT_SHEET), SAND_BAG,
        KNIFE_SMALL);

    private static Equipment set(int count, EquipmentType equipment) {
        return new EquipmentSet(equipment, count);
    }

    private EquipmentPack(Value cost, Equipment... content) {
        this.cost = cost;
        this.content = Arrays.asList(content);
    }

    private final Value cost;
    private final List<Equipment> content;

    @Override
    public Stream<Equipment> getComponents() {
        return content.stream();
    }

    @Override
    public EquipmentCategory getCategory() {
        return EquipmentCategory.EQUIPMENT_PACK;
    }

    @Override
    public Equipment getBaseEquipment() {
        throw new IllegalStateException("Equipment packs do not have base equipment");
    }

    @Override
    public Weight getWeight() {
        return content.stream()
            .map(Equipment::getWeight)
            .reduce(Weight.ZERO, Weight::add);
    }

    @Override
    public Value getValue() {
        return cost;
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return content.stream().map(Equipment::toString);
    }

}
