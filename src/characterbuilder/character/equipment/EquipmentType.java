package characterbuilder.character.equipment;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Value;
import static characterbuilder.character.attribute.Value.*;
import characterbuilder.character.attribute.Weight;
import static characterbuilder.character.attribute.Weight.*;
import static characterbuilder.character.equipment.EquipmentCategory.*;
import characterbuilder.utils.StringUtils;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;
import org.w3c.dom.Node;

public enum EquipmentType implements Equipment {
    COPPER_PIECE(TREASURE, cp(1), Weight.ZERO),
    SILVER_PIECE(TREASURE, sp(1), Weight.ZERO),
    GOLD_PIECE(TREASURE, gp(1), Weight.ZERO),

    ABACUS(ADVENTURING_GEAR, gp(2), lb(2)),
    ACID_VIAL(ADVENTURING_GEAR, gp(25), lb(1)),
    ALCHEMIST_FIRE_FLASK(ADVENTURING_GEAR, gp(50), lb(1)),
    ANTITOXIN_VIAL(ADVENTURING_GEAR, gp(50), Weight.ZERO),
    BALL_BEARINGS(ADVENTURING_GEAR, gp(1), lb(2), 1000),
    BASKET(CONTAINER, sp(4), lb(2)),
    BEDROLL(ADVENTURING_GEAR, gp(11), lb(7)),
    BELL(ADVENTURING_GEAR, gp(1), Weight.ZERO),
    BLANKET(ADVENTURING_GEAR, sp(5), lb(3)),
    BONE_DICE(GAMING_SET, cp(5), Weight.ZERO),
    BOOK(WRITING, gp(25), lb(5)),
    CANDLE(LIGHT, cp(1), Weight.ZERO),
    CENSER(CONTAINER, gp(1), lb(1)),
    INCENSE_BLOCK(ADVENTURING_GEAR, cp(1), Weight.ZERO),
    INK_BOTTLE(WRITING, gp(10), Weight.ZERO),
    INK_PEN(WRITING, cp(2), Weight.ZERO),
    KNIFE_SMALL(ADVENTURING_GEAR, gp(1), lb(1)),
    LAMP(LIGHT, gp(5), lb(1)),
    LANTERN_BULLSEYE(LIGHT, gp(10), lb(2), "Bullseye Lantern"),
    LANTERN_HOODED(LIGHT, gp(5), lb(2), "Hooded Lantern"),
    LOCK(ADVENTURING_GEAR, gp(10), lb(1)),
    MAGNIFYING_GLASS(ADVENTURING_GEAR, gp(100), Weight.ZERO),
    MANACLES(ADVENTURING_GEAR, gp(2), lb(6)),
    MESS_KIT(PROVISIONS_COOKING, sp(2), lb(1)),
    MIRROR_STEEL(ADVENTURING_GEAR, gp(5), oz(8), "Steel Mirror"),
    OIL_FLASK(LIGHT, sp(1), lb(1), "Flask of Oil"),
    PAPER_SHEET(WRITING, sp(2), Weight.ZERO, "Sheet of Paper"),
    PARCHMENT_SHEET(WRITING, sp(1), Weight.ZERO, "Sheet of Parchment"),
    PERFUME_VIAL(ADVENTURING_GEAR, gp(5), Weight.ZERO),
    PICK_MINERS(TOOL, gp(2), lb(10), "Miner's Pick"),
    PITON(ADVENTURING_GEAR, cp(5), oz(4)),
    POLE_TEN_FOOT(ADVENTURING_GEAR, cp(5), lb(7), 1, "10' Pole"),
    POT_IRON(PROVISIONS_COOKING, gp(2), lb(10), "Iron Pot"),
    POTION_HEALING(ADVENTURING_GEAR, gp(50), oz(8), "Potion of Healing"),
    POUCH(CONTAINER, sp(5), lb(1)),
    RATION(PROVISIONS_COOKING, sp(5), lb(2), "Rations (1 day)"),
    ROPE_HEMPEN(ADVENTURING_GEAR, gp(1), lb(10), 1, "Hempen Rope (50')"),
    ROPE_SILK(ADVENTURING_GEAR, gp(10), lb(5), 1, "Silk Rope (50')"),
    SAND_BAG(ADVENTURING_GEAR, GP, oz(1)),
    SCROLL_CASE(CONTAINER, gp(1), lb(1)),
    SEALING_WAX(WRITING, sp(5), Weight.ZERO),
    SIGNAL_WHISTLE(ADVENTURING_GEAR, cp(5), Weight.ZERO),
    SIGNET_RING(WRITING, gp(5), Weight.ZERO),
    SOAP(ADVENTURING_GEAR, cp(2), Weight.ZERO),
    SPELLBOOK(ADVENTURING_GEAR, gp(50), lb(3)),
    BOOK_OF_SHADOWS(ADVENTURING_GEAR, gp(50), lb(3)),
    STRING(ADVENTURING_GEAR, cp(1), Weight.ZERO),
    SPIKE(ADVENTURING_GEAR, gp(1), lb(5), 10, "Iron Spike"),
    SPYGLASS(ADVENTURING_GEAR, gp(1000), lb(1)),
    TWO_PERSON_TENT(ADVENTURING_GEAR, gp(2), lb(20), "Two-person Tent"),
    TINDERBOX(PROVISIONS_COOKING, sp(5), lb(0)),
    TORCH(LIGHT, CP, LB),
    WATERSKIN(PROVISIONS_COOKING, sp(2), lb(5)),
    WHETSTONE(ADVENTURING_GEAR, cp(1), lb(1)),
    //
    ALCHEMISTS_SUPPLIES(TOOL, gp(50), lb(8), "Alchemist's Supplies"),
    BREWERS_SUPPLIES(TOOL, gp(20), lb(9), "Brewer's Supplies"),
    CALLIGRAPHERS_SUPPLIES(TOOL, gp(10), lb(5), "Calligrapher's Supplies"),
    CARPENTERS_TOOLS(TOOL, gp(8), lb(6), "Carpenter's Tools"),
    CARTOGRAPHERS_TOOLS(TOOL, gp(15), lb(6), "Cartographer's Tools"),
    COBBLERS_TOOLS(TOOL, gp(5), lb(5), "Cobbler's Tools"),
    COOKS_UTENSILS(TOOL, gp(1), lb(8), "Cook's Utensil's"),
    GLASSBLOWERS_TOOLS(TOOL, gp(30), lb(5), "Glassblower's Tools"),
    JEWELERS_TOOLS(TOOL, gp(25), lb(2), "Jeweler's Tools"),
    LEATHERWORKERS_TOOLS(TOOL, gp(5), lb(5), "Leatherworker's Tools"),
    MASONS_TOOLS(TOOL, gp(10), lb(8), "Mason's Tools"),
    PAINTERS_SUPPLIES(TOOL, gp(8), lb(10), "Painter's Supplies"),
    POTTERS_TOOLS(TOOL, gp(10), lb(3), "Potter's Tools"),
    SMITHS_TOOLS(TOOL, gp(20), lb(8), "Smith's Tools"),
    TINKERS_TOOLS(TOOL, gp(50), lb(10), "Tinker's Tools"),
    WEAVERS_TOOLS(TOOL, gp(1), lb(5), "Weaver's Tools"),
    WOODCAVERS_TOOS(TOOL, gp(1), lb(5), "Woodcaver's Tools"),
    DISGUISE_KIT(TOOL, gp(25), lb(3)),
    FORGERY_KIT(TOOL, gp(15), lb(5)),
    DICE_SET(GAMING_SET, sp(1), Weight.ZERO),
    DRAGONCHESS_SET(GAMING_SET, gp(1), oz(8)),
    DECK_PLAYING_CARDS(GAMING_SET, sp(5), Weight.ZERO),
    THREE_DRAGON_ANTE_SET(GAMING_SET, gp(1), Weight.ZERO, 1, "Three-Dragon Ante Set"),
    HERBALISM_KIT(TOOL, gp(5), lb(3)),
    NAVIGATORS_TOOLS(TOOL, gp(25), lb(2), "Navigator's Tools"),
    POISONERS_KIT(TOOL, gp(50), lb(2), "Poisoner's Kit"),
    THIEVES_TOOLS(TOOL, gp(25), lb(1)),
    CROWBAR(TOOL, gp(2), lb(5)),
    HAMMER(TOOL, gp(1), lb(3)),
    SHOVEL(TOOL, gp(2), lb(5)),
    MERCHANTS_SCALE(TOOL, gp(5), lb(3), "Merchant's Scale"),
    //
    SACK(CONTAINER, cp(1), oz(8)),
    VIAL(CONTAINER, gp(1), Weight.ZERO),
    CHEST(CONTAINER, gp(5), lb(25)),
    ALMS_BOX(CONTAINER, cp(1), lb(1)),
    BACKPACK(CONTAINER, gp(2), lb(5)),
    BARREL(CONTAINER, gp(2), lb(70)),
    QUIVER(CONTAINER, sp(2), lb(5)),
    COMPONENT_POUCH(CONTAINER, sp(1), lb(1)),
    //
    ARROW(AMMUNITION, cp(100), lb(1), 20),
    BLOWGUN_NEEDLE(AMMUNITION, cp(100), lb(1), 50),
    CROSSBOW_BOLT(AMMUNITION, cp(100), oz(24), 20),
    SLING_BULLET(AMMUNITION, cp(4), oz(24), 20),
    //
    AMULET(HOLY_SYMBOL, cp(500), lb(1)),
    EMBLEM(HOLY_SYMBOL, cp(500), Weight.ZERO),
    RELIQUARY(HOLY_SYMBOL, cp(500), lb(2)),
    //
    CRYSTAL(ARCANE_FOCUS, gp(10), lb(1)),
    ORB(ARCANE_FOCUS, gp(20), lb(3)),
    ROD(ARCANE_FOCUS, gp(10), lb(2)),
    STAFF(ARCANE_FOCUS, gp(5), lb(4)),
    WAND(ARCANE_FOCUS, gp(10), lb(1)),
    //
    SPRIG_OF_MISTLETOE(DRUIDIC_FOCUS, gp(1), Weight.ZERO),
    TOTEM(DRUIDIC_FOCUS, gp(1), Weight.ZERO),
    WOODEN_STAFF(DRUIDIC_FOCUS, gp(5), lb(4)),
    YEW_WAND(DRUIDIC_FOCUS, gp(10), lb(1)),
    //
    VESTMENTS(CLOTHES, gp(1), lb(4)),
    COMMON_CLOTHES(CLOTHES, sp(5), Weight.ZERO),
    TRAVELERS_CLOTHES(CLOTHES, sp(5), Weight.ZERO),
    FINE_CLOTHES(CLOTHES, gp(10), Weight.ZERO),
    ROBES(CLOTHES, gp(1), lb(4)),
    COSTUME(CLOTHES, gp(5), lb(4)),;

    private final Optional<String> name;
    private final EquipmentCategory category;
    private final Value value;
    private final Weight weight;
    private final int perCount;

    private EquipmentType(EquipmentCategory category, Value value, Weight weight) {
        this(category, value, weight, 1);
    }

    private EquipmentType(EquipmentCategory category, Value value, Weight weight, int perCount) {
        this(category, value, weight, perCount, null);
    }

    private EquipmentType(EquipmentCategory category, Value value, Weight weight, String name) {
        this(category, value, weight, 1, name);
    }

    private EquipmentType(EquipmentCategory category, Value value, Weight weight,
        int perCount, String name) {
        this.category = category;
        this.value = value;
        this.weight = weight;
        this.perCount = perCount;
        this.name = Optional.ofNullable(name);
    }

    @Override
    public EquipmentCategory getCategory() {
        return category;
    }

    public static Stream<EquipmentType> allOfCategory(EquipmentCategory category) {
        return Arrays.stream(values()).filter(et -> et.category == category);
    }

    public Value getValue() {
        return value;
    }

    public Weight getWeight() {
        return getWeight(1);
    }

    public Weight getWeight(int count) {
        return weight.times(1f * count / perCount);
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return Stream.of(getCategory().toString() + ": "
            + "cost " + value.toString()
            + ", weight " + weight.toString()
            + (perCount > 1 ? " /" + perCount : ""));
    }

    public int getPreferredCount() {
        return perCount;
    }

    @Override
    public String toString() {
        return name.orElse(StringUtils.capitaliseEnumName(name()));
    }

    public static EquipmentType load(Node node) {
        return EquipmentType.valueOf(node.getTextContent());
    }

}
