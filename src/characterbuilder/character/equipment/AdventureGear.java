package characterbuilder.character.equipment;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Value;
import static characterbuilder.character.attribute.Value.*;
import characterbuilder.character.attribute.Weight;
import static characterbuilder.character.attribute.Weight.*;
import static characterbuilder.character.equipment.EquipmentCategory.*;
import characterbuilder.utils.StringUtils;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;
import org.w3c.dom.Node;

public enum AdventureGear implements Equipment {
    COPPER_PIECE(new Builder(TREASURE).value(cp(1))),
    SILVER_PIECE(new Builder(TREASURE).value(sp(1))),
    GOLD_PIECE(new Builder(TREASURE).value(gp(1))),

    ABACUS(new Builder(ADVENTURING_GEAR).value(gp(2)).weight(lb(2))),
    ACID_VIAL(new Builder(ADVENTURING_GEAR).value(gp(25)).weight(lb(1))),
    ALCHEMIST_FIRE_FLASK(new Builder(ADVENTURING_GEAR).value(gp(50)).weight(lb(1))),
    ANTITOXIN_VIAL(new Builder(ADVENTURING_GEAR).value(gp(50))),
    BALL_BEARINGS(new Builder(ADVENTURING_GEAR).value(gp(1)).weight(lb(2)).perCount(1000)),
    BASKET(new Builder(CONTAINER).value(sp(4)).weight(lb(2))),
    BEDROLL(new Builder(ADVENTURING_GEAR).value(gp(11)).weight(lb(7))),
    BELL(new Builder(ADVENTURING_GEAR).value(gp(1))),
    BLANKET(new Builder(ADVENTURING_GEAR).value(sp(5)).weight(lb(3))),
    BONE_DICE(new Builder(GAMING_SET).value(cp(5))),
    BOOK(new Builder(WRITING).value(gp(25)).weight(lb(5))),
    CANDLE(new Builder(LIGHT).value(cp(1))),
    CENSER(new Builder(CONTAINER).value(gp(1)).weight(lb(1))),
    INCENSE_BLOCK(new Builder(ADVENTURING_GEAR).value(cp(1))),
    INK_BOTTLE(new Builder(WRITING).value(gp(10))),
    INK_PEN(new Builder(WRITING).value(cp(2))),
    KNIFE_SMALL(new Builder(ADVENTURING_GEAR).value(gp(1)).weight(lb(1))),
    LAMP(new Builder(LIGHT).value(gp(5)).weight(lb(1))),
    LANTERN_BULLSEYE(new Builder(LIGHT).name("Bullseye Lantern").value(gp(10)).weight(lb(2))),
    LANTERN_HOODED(new Builder(LIGHT).name("Hooded Lantern").value(gp(5)).weight(lb(2))),
    LOCK(new Builder(ADVENTURING_GEAR).value(gp(10)).weight(lb(1))),
    MAGNIFYING_GLASS(new Builder(ADVENTURING_GEAR).value(gp(100))),
    MANACLES(new Builder(ADVENTURING_GEAR).value(gp(2)).weight(lb(6))),
    MESS_KIT(new Builder(PROVISIONS_COOKING).value(sp(2)).weight(lb(1))),
    MIRROR_STEEL(new Builder(ADVENTURING_GEAR).name("Steel Mirror").value(gp(5)).weight(oz(8))),
    OIL_FLASK(new Builder(LIGHT).name("Flask of Oil").value(sp(1)).weight(lb(1))),
    PAPER_SHEET(new Builder(WRITING).name("Sheet of Paper").value(sp(2))),
    PARCHMENT_SHEET(new Builder(WRITING).name("Sheet of Parchment").value(sp(1))),
    PERFUME_VIAL(new Builder(ADVENTURING_GEAR).value(gp(5))),
    PICK_MINERS(new Builder(TOOL).name("Miner's Pick").value(gp(2)).weight(lb(10))),
    PITON(new Builder(ADVENTURING_GEAR).value(cp(5)).weight(oz(4))),
    POLE_TEN_FOOT(new Builder(ADVENTURING_GEAR).name("10' Pole").value(cp(5)).weight(lb(7))),
    POT_IRON(new Builder(PROVISIONS_COOKING).name("Iron Pot").value(gp(2)).weight(lb(10))),
    POTION_HEALING(new Builder(ADVENTURING_GEAR).name("Potion of Healing").value(gp(50))
        .weight(oz(8))),
    POUCH(new Builder(CONTAINER).value(sp(5)).weight(lb(1))),
    RATION(new Builder(PROVISIONS_COOKING).value(sp(5)).weight(lb(2))
        .namer((c, b) -> c > 1 ? String.format("Rations (%d days)", c) : "Ration (1 day)")),
    ROPE_HEMPEN(new Builder(ADVENTURING_GEAR).value(gp(1)).weight(lb(10))
        .namer((c, b) -> String.format("Hempen Rope (%d')", c * 50))),
    ROPE_SILK(new Builder(ADVENTURING_GEAR).value(gp(10)).weight(lb(5))
        .namer((c, b) -> String.format("Silk Rope (%d)", c * 50))),
    SAND_BAG(new Builder(ADVENTURING_GEAR).value(gp(1)).weight(oz(1))),
    SCROLL_CASE(new Builder(CONTAINER).value(gp(1)).weight(lb(1))),
    SEALING_WAX(new Builder(WRITING).value(sp(5))),
    SIGNAL_WHISTLE(new Builder(ADVENTURING_GEAR).value(cp(5))),
    SIGNET_RING(new Builder(WRITING).value(gp(5))),
    SOAP(new Builder(ADVENTURING_GEAR).value(cp(2))),
    SPELLBOOK(new Builder(ADVENTURING_GEAR).value(gp(50)).weight(lb(3))),
    BOOK_OF_SHADOWS(new Builder(ADVENTURING_GEAR).value(gp(50)).weight(lb(3))),
    STRING(new Builder(ADVENTURING_GEAR).value(cp(1))),
    SPIKE(new Builder(ADVENTURING_GEAR).name("Iron Spike").value(gp(1)).weight(lb(5)).perCount(10)),
    SPYGLASS(new Builder(ADVENTURING_GEAR).value(gp(1000)).weight(lb(1))),
    TWO_PERSON_TENT(new Builder(ADVENTURING_GEAR).name("Two-person Tent").value(gp(2))
        .weight(lb(20))),
    TINDERBOX(new Builder(PROVISIONS_COOKING).value(sp(5)).weight(lb(0))),
    TORCH(new Builder(LIGHT).value(cp(1)).weight(lb(1))
        .namer((c, b) -> c > 1 ? String.format("%d Torches", c) : "Torch")),
    WATERSKIN(new Builder(PROVISIONS_COOKING).value(sp(2)).weight(lb(5))),
    WHETSTONE(new Builder(ADVENTURING_GEAR).value(cp(1)).weight(lb(1))),
    //
    ALCHEMISTS_SUPPLIES(new Builder(TOOL).name("Alchemist's Supplies").value(gp(50)).weight(lb(8))),
    BREWERS_SUPPLIES(new Builder(TOOL).name("Brewer's Supplies").value(gp(20)).weight(lb(9))),
    CALLIGRAPHERS_SUPPLIES(new Builder(TOOL).name("Calligrapher's Supplies").value(gp(10))
        .weight(lb(5))),
    CARPENTERS_TOOLS(new Builder(TOOL).name("Carpenter's Tools").value(gp(8)).weight(lb(6))),
    CARTOGRAPHERS_TOOLS(new Builder(TOOL).name("Cartographer's Tools").value(gp(15)).weight(lb(6))),
    COBBLERS_TOOLS(new Builder(TOOL).name("Cobbler's Tools").value(gp(5)).weight(lb(5))),
    COOKS_UTENSILS(new Builder(TOOL).name("Cook's Utensil's").value(gp(1)).weight(lb(8))),
    GLASSBLOWERS_TOOLS(new Builder(TOOL).name("Glassblower's Tools").value(gp(30)).weight(lb(5))),
    JEWELERS_TOOLS(new Builder(TOOL).name("Jeweler's Tools").value(gp(25)).weight(lb(2))),
    LEATHERWORKERS_TOOLS(new Builder(TOOL).name("Leatherworker's Tools").value(gp(5)).weight(lb(5))),
    MASONS_TOOLS(new Builder(TOOL).name("Mason's Tools").value(gp(10)).weight(lb(8))),
    PAINTERS_SUPPLIES(new Builder(TOOL).name("Painter's Supplies").value(gp(8)).weight(lb(10))),
    POTTERS_TOOLS(new Builder(TOOL).name("Potter's Tools").value(gp(10)).weight(lb(3))),
    SMITHS_TOOLS(new Builder(TOOL).name("Smith's Tools").value(gp(20)).weight(lb(8))),
    TINKERS_TOOLS(new Builder(TOOL).name("Tinker's Tools").value(gp(50)).weight(lb(10))),
    WEAVERS_TOOLS(new Builder(TOOL).name("Weaver's Tools").value(gp(1)).weight(lb(5))),
    WOODCAVERS_TOOS(new Builder(TOOL).name("Woodcaver's Tools").value(gp(1)).weight(lb(5))),
    DISGUISE_KIT(new Builder(TOOL).value(gp(25)).weight(lb(3))),
    FORGERY_KIT(new Builder(TOOL).value(gp(15)).weight(lb(5))),
    DICE_SET(new Builder(GAMING_SET).value(sp(1))),
    DRAGONCHESS_SET(new Builder(GAMING_SET).value(gp(1)).weight(oz(8))),
    DECK_PLAYING_CARDS(new Builder(GAMING_SET).value(sp(5))),
    THREE_DRAGON_ANTE_SET(new Builder(GAMING_SET).name("Three-Dragon Ante Set").value(gp(1))),
    HERBALISM_KIT(new Builder(TOOL).value(gp(5)).weight(lb(3))),
    NAVIGATORS_TOOLS(new Builder(TOOL).name("Navigator's Tools").value(gp(25)).weight(lb(2))),
    POISONERS_KIT(new Builder(TOOL).name("Poisoner's Kit").value(gp(50)).weight(lb(2))),
    THIEVES_TOOLS(new Builder(TOOL).value(gp(25)).weight(lb(1))),
    CROWBAR(new Builder(TOOL).value(gp(2)).weight(lb(5))),
    HAMMER(new Builder(TOOL).value(gp(1)).weight(lb(3))),
    SHOVEL(new Builder(TOOL).value(gp(2)).weight(lb(5))),
    MERCHANTS_SCALE(new Builder(TOOL).name("Merchant's Scale").value(gp(5)).weight(lb(3))),
    //
    SACK(new Builder(CONTAINER).value(cp(1)).weight(oz(8))),
    VIAL(new Builder(CONTAINER).value(gp(1))),
    CHEST(new Builder(CONTAINER).value(gp(5)).weight(lb(25))),
    ALMS_BOX(new Builder(CONTAINER).value(cp(1)).weight(lb(1))),
    BACKPACK(new Builder(CONTAINER).value(gp(2)).weight(lb(5))),
    BARREL(new Builder(CONTAINER).value(gp(2)).weight(lb(70))),
    QUIVER(new Builder(CONTAINER).value(sp(2)).weight(lb(5))),
    COMPONENT_POUCH(new Builder(CONTAINER).value(sp(1)).weight(lb(1))),
    //
    ARROW(new Builder(AMMUNITION).value(cp(100)).weight(lb(1)).perCount(20)),
    BLOWGUN_NEEDLE(new Builder(AMMUNITION).value(cp(100)).weight(lb(1)).perCount(50)),
    CROSSBOW_BOLT(new Builder(AMMUNITION).value(cp(100)).weight(oz(24)).perCount(20)),
    SLING_BULLET(new Builder(AMMUNITION).value(cp(4)).weight(oz(24)).perCount(20)),
    //
    AMULET(new Builder(HOLY_SYMBOL).value(cp(500)).weight(lb(1))),
    EMBLEM(new Builder(HOLY_SYMBOL).value(cp(500))),
    RELIQUARY(new Builder(HOLY_SYMBOL).value(cp(500)).weight(lb(2))),
    //
    CRYSTAL(new Builder(ARCANE_FOCUS).value(gp(10)).weight(lb(1))),
    ORB(new Builder(ARCANE_FOCUS).value(gp(20)).weight(lb(3))),
    ROD(new Builder(ARCANE_FOCUS).value(gp(10)).weight(lb(2))),
    STAFF(new Builder(ARCANE_FOCUS).value(gp(5)).weight(lb(4))),
    WAND(new Builder(ARCANE_FOCUS).value(gp(10)).weight(lb(1))),
    //
    SPRIG_OF_MISTLETOE(new Builder(DRUIDIC_FOCUS).value(gp(1))),
    TOTEM(new Builder(DRUIDIC_FOCUS).value(gp(1))),
    WOODEN_STAFF(new Builder(DRUIDIC_FOCUS).value(gp(5)).weight(lb(4))),
    YEW_WAND(new Builder(DRUIDIC_FOCUS).value(gp(10)).weight(lb(1))),
    //
    VESTMENTS(new Builder(CLOTHES).value(gp(1)).weight(lb(4))),
    COMMON_CLOTHES(new Builder(CLOTHES).value(sp(5))),
    TRAVELERS_CLOTHES(new Builder(CLOTHES).value(sp(5))),
    FINE_CLOTHES(new Builder(CLOTHES).value(gp(10))),
    ROBES(new Builder(CLOTHES).value(gp(1)).weight(lb(4))),
    COSTUME(new Builder(CLOTHES).value(gp(5)).weight(lb(4))),;

    private static class Builder {

        private final EquipmentCategory category;
        private Value value = Value.ZERO;
        private Weight weight = Weight.ZERO;
        private int perCount = 1;
        private Optional<Namer> namer = Optional.empty();

        public Builder(EquipmentCategory category) {
            this.category = category;
        }

        public Builder value(Value value) {
            this.value = value;
            return this;
        }

        public Builder weight(Weight weight) {
            this.weight = weight;
            return this;
        }

        public Builder name(String name) {
            return namer((count, bonus) -> {
                StringBuilder builder = new StringBuilder();
                if (count > 1)
                    builder.append(NumberFormat.getInstance().format(count)).append(" ");
                if (bonus != 0)
                    builder.append(String.format("%+d ", bonus));
                builder.append(name);
                if (count > 1)
                    builder.append("s");
                return builder.toString();
            });
        }

        public Builder namer(Namer namer) {
            this.namer = Optional.of(namer);
            return this;
        }

        public Builder perCount(int count) {
            this.perCount = count;
            return this;
        }
    }

    @FunctionalInterface
    private interface Namer {

        String toString(int count, int bonus);
    }

    private final Optional<Namer> namer;
    private final EquipmentCategory category;
    private final Value value;
    private final Weight weight;
    private final int perCount;

    private AdventureGear(Builder builder) {
        this.namer = builder.namer;
        this.category = builder.category;
        this.value = builder.value;
        this.weight = builder.weight;
        this.perCount = builder.perCount;
    }

    @Override
    public EquipmentCategory getCategory() {
        return category;
    }

    public static Stream<AdventureGear> allOfCategory(EquipmentCategory category) {
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
    public String toString(int count, int bonus) {
        return namer.map(n -> n.toString(count, bonus))
            .orElse(Equipment.super.toString(count, bonus));
    }

    @Override
    public String toString() {
        return namer.map(n -> n.toString(1, 0))
            .orElse(StringUtils.capitaliseEnumName(name()));
    }

    public static AdventureGear load(Node node) {
        return AdventureGear.valueOf(node.getTextContent());
    }

}
