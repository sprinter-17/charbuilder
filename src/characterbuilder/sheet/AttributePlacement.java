package characterbuilder.sheet;

import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.*;
import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.toList;

public enum AttributePlacement {
    FIELD(RACE, CHARACTER_CLASS, SEX, BACKGROUND, AGE, ALIGNMENT, HEIGHT, WEIGHT, NAME,
        STRENGTH, DEXTERITY, CONSTITUTION, INTELLIGENCE, WISDOM, CHARISMA,
        LEVEL, EXPERIENCE_POINTS, HIT_POINTS, SPELLCASTING, SPELL, SKILL,
        PHYSICAL_DESCRIPTION, TRAIT, IDEAL, BOND, FLAW,
        MARTIAL_ARCHETYPE, ROGUISH_ARCHETYPE, MONASTIC_TRADITION, DRUID_CIRCLE, RANGER_ARCHETYPE,
        SACRED_OATH, SORCEROUS_ORIGIN, OTHERWORLDLY_PATRON, PRIMAL_PATH, OTHERWORLDLY_PATRON,
        BARDIC_COLLEGE, DIVINE_DOMAIN),
    SUMMARY(LANGUAGE, ARTISAN, WEAPON_PROFICIENCY, ARMOUR_PROFICIENCY,
        MUSICAL_INSTRUMENT_PROFICIENCY, TOOLS, ENTERTAINER_ROUTINE, ARCANE_TRADITION,
        FAVOURED_ENEMY, FAVOURED_TERRAIN, GUILD_BUSINESS),
    DETAIL(RACIAL_TALENT, CLASS_TALENT, DIVINE_DOMAIN_ABILITY,
        FIGHTING_STYLE, SPELL_MASTERY, EVOCATION_ABILITY, EXPERTISE,
        FEAT, DRACONIC_ANCESTORY, RACIAL_TALENT, BACKGROUND_FEATURE, DIVINE_DOMAIN_ABILITY,
        RANGERS_COMPANION, ELEMENTAL_ADEPT),
    HIDDEN();

    private final List<AttributeType> types;

    private AttributePlacement(AttributeType... types) {
        this.types = Arrays.stream(types).collect(toList());
    }

    public boolean isPlacementFor(Attribute attribute) {
        return types.contains(attribute.getType());
    }

    public static AttributePlacement forAttributeType(AttributeType type) {
        return Arrays.stream(values())
            .filter(ap -> ap.types.contains(type))
            .findAny()
            .orElseThrow(() -> new UnsupportedOperationException("No place for " + type));
    }
}
