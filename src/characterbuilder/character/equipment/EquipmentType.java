package characterbuilder.character.equipment;

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
    BASKET(ADVENTURING_GEAR, sp(4), lb(2)),
    BEDROLL(ADVENTURING_GEAR, gp(11), lb(7)),
    BELL(ADVENTURING_GEAR, gp(1), Weight.ZERO),
    BLANKET(ADVENTURING_GEAR, sp(5), lb(3)),
    BONE_DICE(ADVENTURING_GEAR, cp(5), Weight.ZERO),
    BOOK(ADVENTURING_GEAR, gp(25), lb(5)),
    CANDLE(ADVENTURING_GEAR, cp(1), Weight.ZERO),
    CENSER(CONTAINERS, gp(1), lb(1)),
    INCENSE_BLOCK(ADVENTURING_GEAR, cp(1), Weight.ZERO),
    INK_BOTTLE(ADVENTURING_GEAR, gp(10), Weight.ZERO),
    INK_PEN(ADVENTURING_GEAR, cp(2), Weight.ZERO),
    KNIFE_SMALL(ADVENTURING_GEAR, gp(1), lb(1)),
    LAMP(ADVENTURING_GEAR, gp(5), lb(1)),
    LANTERN_HOODED(ADVENTURING_GEAR, gp(5), lb(2)),
    MESS_KIT(ADVENTURING_GEAR, sp(2), lb(1)),
    OIL_FLASK(ADVENTURING_GEAR, sp(1), lb(1)),
    PAPER_SHEET(ADVENTURING_GEAR, sp(2), Weight.ZERO),
    PARCHMENT_SHEET(ADVENTURING_GEAR, sp(1), Weight.ZERO),
    PERFUME_VIAL(ADVENTURING_GEAR, gp(5), Weight.ZERO),
    PITON(ADVENTURING_GEAR, cp(5), oz(4)),
    POLE_TEN_FOOT(ADVENTURING_GEAR, cp(5), lb(7), 1, "10' Pole"),
    RATION(ADVENTURING_GEAR, sp(5), lb(2)),
    ROPE_HEMPEN(ADVENTURING_GEAR, gp(1), lb(10), 1, "Hempen Rope"),
    ROPE_SILK(ADVENTURING_GEAR, gp(10), lb(5), 1, "Silk Rope"),
    SAND_BAG(ADVENTURING_GEAR, GP, oz(1)),
    SCROLL_CASE(ADVENTURING_GEAR, gp(1), lb(1)),
    SEALING_WAX(ADVENTURING_GEAR, sp(5), Weight.ZERO),
    SOAP(ADVENTURING_GEAR, cp(2), Weight.ZERO),
    STRING(ADVENTURING_GEAR, cp(1), Weight.ZERO),
    SPIKE(ADVENTURING_GEAR, gp(1), lb(5), 10),
    TWO_PERSON_TENT(ADVENTURING_GEAR, gp(2), lb(20)),
    TINDERBOX(ADVENTURING_GEAR, sp(5), lb(0)),
    TORCH(ADVENTURING_GEAR, CP, LB),
    WATERSKIN(ADVENTURING_GEAR, sp(2), lb(5)),
    //
    THIEVES_TOOLS(TOOLS, gp(25), lb(1)),
    POISONERS_KIT(TOOLS, gp(50), lb(2), 1, "Poisoner's Kit"),
    NAVIGATORS_TOOLS(TOOLS, gp(25), lb(2), 1, "Navigator's Tools"),
    HERBALISM_KIT(TOOLS, gp(5), lb(3)),
    FORGERY_KIT(TOOLS, gp(15), lb(5)),
    DISGUISE_KIT(TOOLS, gp(25), lb(3)),
    CROWBAR(TOOLS, gp(2), lb(5)),
    HAMMER(TOOLS, gp(1), lb(3)),
    //
    VIAL(CONTAINERS, gp(1), Weight.ZERO),
    CHEST(CONTAINERS, gp(5), lb(25)),
    ALMS_BOX(CONTAINERS, cp(1), lb(1)),
    BACKPACK(CONTAINERS, gp(2), lb(5)),
    BARREL(CONTAINERS, gp(2), lb(70)),
    //
    QUIVER(ADVENTURING_GEAR, sp(2), lb(5)),
    COMPONENT_POUCH(ADVENTURING_GEAR, sp(1), lb(1)),
    SPELLBOOK(ADVENTURING_GEAR, sp(1), lb(1)),
    //
    DICE_SET(GAMING_SETS, sp(1), Weight.ZERO),
    DRAGONCHESS_SET(GAMING_SETS, gp(1), oz(8)),
    DECK_PLAYING_CARDS(GAMING_SETS, sp(5), Weight.ZERO),
    THREE_DRAGON_ANTE_SET(GAMING_SETS, gp(1), Weight.ZERO, 1, "Three-Dragon Ante Set"),
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
    public String toString() {
        return name.orElse(StringUtils.capitaliseEnumName(name()));
    }

    public static EquipmentType load(Node node) {
        return EquipmentType.valueOf(node.getTextContent());
    }

}
