package characterbuilder.character.ability;

import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.*;
import characterbuilder.utils.StringUtils;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;
import org.w3c.dom.Node;

public enum Proficiency implements Attribute {
    ALL_WEAPONS(WEAPON_PROFICIENCY),
    ALL_SIMPLE_WEAPONS(WEAPON_PROFICIENCY, ALL_WEAPONS),
    ALL_SIMPLE_MELEE(WEAPON_PROFICIENCY, ALL_SIMPLE_WEAPONS),
    ALL_SIMPLE_RANGED(WEAPON_PROFICIENCY, ALL_SIMPLE_WEAPONS),
    ALL_MARTIAL_WEAPONS(WEAPON_PROFICIENCY, ALL_WEAPONS),
    ALL_MARTIAL_MELEE(WEAPON_PROFICIENCY, ALL_MARTIAL_WEAPONS),
    ALL_MARTIAL_RANGED(WEAPON_PROFICIENCY, ALL_MARTIAL_WEAPONS),
    //
    ALL_ARMOUR(ARMOUR_PROFICIENCY),
    LIGHT_ARMOUR(ARMOUR_PROFICIENCY, ALL_ARMOUR),
    MEDIUM_ARMOUR(ARMOUR_PROFICIENCY, ALL_ARMOUR),
    HEAVY_ARMOUR(ARMOUR_PROFICIENCY, ALL_ARMOUR),
    SHIELD(ARMOUR_PROFICIENCY, ALL_ARMOUR),
    //
    COMMON(LANGUAGE),
    DWARVISH(LANGUAGE),
    ELVISH(LANGUAGE),
    GIANT(LANGUAGE),
    GNOMISH(LANGUAGE),
    GOBLIN(LANGUAGE),
    HALFLING(LANGUAGE),
    ORC(LANGUAGE),
    INFERNAL(LANGUAGE),
    THIEVES_CANT(LANGUAGE),
    //
    SMITH(ARTISAN),
    BREWER(ARTISAN),
    MASON(ARTISAN),
    //
    DISGUISE_KIT(TOOLS),
    HERBALISM_KIT(TOOLS),
    NAVIGATORS_TOOLS(TOOLS),
    THIEVES_TOOLS(TOOLS),
    LAND_VEHICLES(TOOLS),
    WATER_VEHICLES(TOOLS),
    CARDS(TOOLS),
    DICE(TOOLS), //
    ;

    private final AttributeType type;
    private final Optional<String> name;
    private final Optional<Attribute> superSet;

    private Proficiency(AttributeType type, String name) {
        this.type = type;
        this.name = Optional.of(name);
        this.superSet = Optional.empty();
    }

    private Proficiency(AttributeType type) {
        this.type = type;
        this.name = Optional.empty();
        this.superSet = Optional.empty();
    }

    private Proficiency(AttributeType type, Attribute superSet) {
        this.type = type;
        this.name = Optional.empty();
        this.superSet = Optional.of(superSet);
    }

    public static Stream<Proficiency> allOfType(AttributeType type) {
        return Arrays.stream(values()).filter(p -> p.type == type);
    }

    public static Stream<AttributeType> getTypes() {
        return Arrays.stream(values()).map(Proficiency::getType).distinct();
    }

    @Override
    public AttributeType getType() {
        return type;
    }

    @Override
    public Optional<Attribute> getSuperSet() {
        return superSet;
    }

    @Override
    public String toString() {
        return name.orElse(StringUtils.capitaliseEnumName(name()));
    }

    public static Proficiency load(Node node) {
        return Proficiency.valueOf(node.getTextContent());
    }
}
