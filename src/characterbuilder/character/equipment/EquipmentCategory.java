package characterbuilder.character.equipment;

import characterbuilder.character.ability.Proficiency;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.utils.StringUtils;
import java.util.Optional;
import java.util.function.Function;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public enum EquipmentCategory {
    CUSTOM_TREASURE(CustomTreasure::load),
    TREASURE(EquipmentType::load),
    LIGHT_ARMOUR(Armour::load),
    MEDIUM_ARMOUR(Armour::load),
    HEAVY_ARMOUR(Armour::load),
    SHIELD(Armour::load),
    SIMPLE_MELEE(Proficiency.ALL_SIMPLE_MELEE, Weapon::load),
    MARTIAL_MELEE(Proficiency.ALL_MARTIAL_MELEE, Weapon::load),
    SIMPLE_RANGED(Proficiency.ALL_SIMPLE_RANGED, Weapon::load),
    MARTIAL_RANGED(Proficiency.ALL_MARTIAL_RANGED, Weapon::load),
    ADVENTURING_GEAR(EquipmentType::load),
    PROVISIONS(EquipmentType::load),
    MUSICAL_INSTRUMENT(MusicalInstrument::load),
    TOOLS(EquipmentType::load),
    CONTAINER(EquipmentType::load),
    GAMING_SET(EquipmentType::load),
    CLOTHES(EquipmentType::load),
    DRUIDIC_FOCUS(EquipmentType::load),
    AMMUNITION(EquipmentType::load),
    HOLY_SYMBOL(EquipmentType::load),
    ARCANE_FOCUS(EquipmentType::load),
    EQUIPMENT_PACK(EquipmentType::load),
    TOKEN(Token::load);

    @FunctionalInterface
    private interface Loader {

        Equipment load(EquipmentCategory category, Node node);
    }

    private final Optional<Attribute> proficiency;
    private Loader loader = null;

    private EquipmentCategory(Function<Node, Equipment> loader) {
        this((cat, node) -> loader.apply(node));
    }

    private EquipmentCategory(Loader loader) {
        this.proficiency = Optional.empty();
        this.loader = loader;
    }

    private EquipmentCategory(Attribute proficiency, Loader loader) {
        this.proficiency = Optional.of(proficiency);
        this.loader = loader;
    }

    private EquipmentCategory(Attribute proficiency, Function<Node, Equipment> loader) {
        this.proficiency = Optional.of(proficiency);
        this.loader = (cat, node) -> loader.apply(node);
    }

    public Optional<Attribute> getProficiency() {
        return proficiency;
    }

    protected Node save(Document doc) {
        return doc.createElement(name().toLowerCase());
    }

    public static Equipment load(Node node) {
        EquipmentCategory category = EquipmentCategory.valueOf(node.getNodeName().toUpperCase());
        assert category.loader != null : category.toString() + " does not have loader";
        Equipment equipment = category.loader.load(category, node);
        Element element = (Element) node;
        if (element.hasAttribute("count") || element.hasAttribute("bonus"))
            return EquipmentSet.load(equipment, element);
        else
            return equipment;
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }
}
