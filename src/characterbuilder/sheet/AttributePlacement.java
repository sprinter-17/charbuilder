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
        LEVEL, EXPERIENCE_POINTS, HIT_POINTS, SPELLCASTING, SKILL,
        PHYSICAL_DESCRIPTION, PERSONAL_HISTORY, TRAIT, IDEAL, BOND, FLAW,
        MARTIAL_ARCHETYPE, ROGUISH_ARCHETYPE, MONASTIC_TRADITION, DRUID_CIRCLE, RANGER_ARCHETYPE,
        SACRED_OATH, SORCEROUS_ORIGIN, OTHERWORLDLY_PATRON, PRIMAL_PATH, OTHERWORLDLY_PATRON,
        BARDIC_COLLEGE, DIVINE_DOMAIN, SPELL_ABILITY, PICTURE),
    SUMMARY(LANGUAGE, ARTISAN, WEAPON_PROFICIENCY, ARMOUR_PROFICIENCY,
        MUSICAL_INSTRUMENT_PROFICIENCY, TOOLS, ENTERTAINER_ROUTINE, ARCANE_TRADITION, GUILD_BUSINESS),
    DETAIL(RACIAL_TALENT, CLASS_TALENT, DIVINE_DOMAIN_ABILITY,
        FIGHTING_STYLE, MANEUVER, SPELL_MASTERY, EXPERTISE,
        FEAT, RACIAL_TALENT, BACKGROUND_ABILITY, DIVINE_DOMAIN_ABILITY,
        RANGERS_COMPANION, ELEMENTAL_ADEPT, SIGNATURE_SPELL, ELDRITCH_INVOCATION,
        MYSTIC_ARCANUM, ELEMENTAL_DISCIPLINE, ARCANE_TRADITION_ABILITY,
        BARBARIAN_ABILITY, BARD_ABILITY, CLERIC_ABILITY, DRUID_ABILITY, DRUID_CIRCLE_ABILITY,
        MONK_ABILITY, RANGER_ABILITY, WARLOCK_ABILITY, ROGUE_ABILITY, FIGHTER_ABILITY,
        PALADIN_ABILITY, SORCERER_ABILITY, META_MAGIC, WIZARD_ABILITY),
    HIDDEN(TERRAIN, DRACONIC_ANCESTRY);

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
