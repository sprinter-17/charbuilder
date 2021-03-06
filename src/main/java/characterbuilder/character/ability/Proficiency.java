package characterbuilder.character.ability;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.*;
import characterbuilder.character.choice.ChoiceSelector;
import characterbuilder.character.choice.OptionChoice;
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
    SMITH(ARTISAN),
    BREWER(ARTISAN),
    MASON(ARTISAN),
    //
    DISGUISE_KIT(TOOLS),
    FORGERY_KIT(TOOLS),
    POISONERS_KIT(TOOLS),
    HERBALISM_KIT(TOOLS),
    NAVIGATORS_TOOLS(TOOLS),
    THIEVES_TOOLS(TOOLS),
    LAND_VEHICLES(TOOLS),
    WATER_VEHICLES(TOOLS),
    CARDS(TOOLS),
    DICE(TOOLS),
    //
    ACTOR(ENTERTAINER_ROUTINE),
    DANCER(ENTERTAINER_ROUTINE),
    FIRE_EATER(ENTERTAINER_ROUTINE),
    JESTER(ENTERTAINER_ROUTINE),
    JUGGLER(ENTERTAINER_ROUTINE),
    INSTRUMENTALIST(ENTERTAINER_ROUTINE),
    POET(ENTERTAINER_ROUTINE),
    SINGER(ENTERTAINER_ROUTINE),
    STORYTELLER(ENTERTAINER_ROUTINE),
    TUMBLER(ENTERTAINER_ROUTINE),
    //
    ALCHEMISTS_APOTHECARIES(GUILD_BUSINESS),
    ARMOURERS_LOCKSMITHS_FINESMITHS(GUILD_BUSINESS),
    BREWERS_DISTILLERS_VINTERS(GUILD_BUSINESS),
    CALLIGRAPHERS_SCRIBES_SCRIVENERS(GUILD_BUSINESS),
    CARPENTERS_ROOFERS_PLASTERERS(GUILD_BUSINESS),
    CARTOGRAPHERS_SURVEYORS_CHARTMAKERS(GUILD_BUSINESS),
    COBBLERS_SHOEMAKERS(GUILD_BUSINESS),
    COOKS_BAKERS(GUILD_BUSINESS),
    GLASSBLOWERS_GLAZIERS(GUILD_BUSINESS),
    JEWELERS_GEMCUTTERS(GUILD_BUSINESS),
    LEATHERWORKERS_SKINNERS_TANNERS(GUILD_BUSINESS),
    MASONS_STONECUTTERS(GUILD_BUSINESS),
    PAINTERS_LIMNERS_SIGNMAKERS(GUILD_BUSINESS),
    POTTERS_TILE_MAKERS(GUILD_BUSINESS),
    SHIPWRIGHTS_SAILMAKERS(GUILD_BUSINESS),
    SMITHS_METALFORGERS(GUILD_BUSINESS),
    TINKERS_PEWTERERS_CASTERS(GUILD_BUSINESS),
    WAGONMAKERS_WHEELWRIGHTS(GUILD_BUSINESS),
    WEAVERS_DYERS(GUILD_BUSINESS),
    WOODCAVERS_COOPERS_BOWYERS(GUILD_BUSINESS),;

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

    public static OptionChoice choose(AttributeType type) {
        return new OptionChoice(type.toString()) {
            @Override
            public void select(Character character, ChoiceSelector selector) {
                selector.chooseOption(Arrays.stream(values())
                    .filter(attr -> attr.hasType(type))
                    .filter(attr -> !character.hasAttribute(attr)),
                    attr -> attr.choose(character));
            }
        };
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
