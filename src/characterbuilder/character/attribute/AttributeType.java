package characterbuilder.character.attribute;

import characterbuilder.character.ability.Ability;
import characterbuilder.character.ability.DivineDomain;
import characterbuilder.character.ability.Expertise;
import characterbuilder.character.ability.MagicSchool;
import characterbuilder.character.ability.Proficiency;
import characterbuilder.character.ability.Skill;
import characterbuilder.character.ability.Spell;
import characterbuilder.character.equipment.Weapon;
import characterbuilder.utils.StringUtils;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public enum AttributeType {
    NAME(true, StringAttribute::new),
    RACE(Race::valueOf),
    CHARACTER_CLASS(CharacterClass::valueOf),
    BACKGROUND(Background::valueOf),
    STRENGTH(true, IntAttribute::decode),
    DEXTERITY(true, IntAttribute::decode),
    CONSTITUTION(true, IntAttribute::decode),
    INTELLIGENCE(true, IntAttribute::decode),
    WISDOM(true, IntAttribute::decode),
    CHARISMA(true, IntAttribute::decode),
    ALIGNMENT(Alignment::valueOf),
    EXPERIENCE_POINTS(true, IntAttribute::decode),
    LEVEL(true, IntAttribute::decode),
    HIT_POINTS(true, IntAttribute::decode),
    SEX(Sex::valueOf),
    AGE(true, IntAttribute::decode),
    HEIGHT(Height::decode),
    WEIGHT(Weight::decode),
    PHYSICAL_DESCRIPTION(true, StringAttribute::new),
    TRAIT(false, StringAttribute::new),
    IDEAL(false, StringAttribute::new),
    BOND(false, StringAttribute::new),
    FLAW(false, StringAttribute::new),
    DRACONIC_ANCESTORY(DraconicAncestory::valueOf),
    FIGHTING_STYLE(false, (t, c) -> Ability.valueOf(c)),
    MARTIAL_ARCHETYPE(MartialArchetype::valueOf),
    ROGUISH_ARCHETYPE(RoguishArchetype::valueOf),
    RACIAL_TALENT(false, (t, c) -> Ability.valueOf(c)),
    CLASS_TALENT(false, (t, c) -> Ability.valueOf(c)),
    SPELL_MASTERY(false, SpellMastery::decode),
    ARCANE_TRADITION(MagicSchool::valueOf),
    EVOCATION_ABILITY(false, (t, c) -> Ability.valueOf(c)),
    DIVINE_DOMAIN(DivineDomain::valueOf),
    DIVINE_DOMAIN_ABILITY(false, (t, c) -> Ability.valueOf(c)),
    SKILL(false, (t, c) -> Skill.valueOf(c)),
    EXPERTISE(false, (t, c) -> Expertise.valueOf(c)),
    SPELL(false, (t, c) -> Spell.valueOf(c)),
    WEAPON_PROFICIENCY(false, (t, c)
        -> c.startsWith("ALL") ? Proficiency.valueOf(c) : Weapon.valueOf(c).getProficiency()),
    ARMOUR_PROFICIENCY(false, (t, c) -> Proficiency.valueOf(c)),
    LANGUAGE(false, (t, c) -> Proficiency.valueOf(c)),
    ARTISAN(false, (t, c) -> Proficiency.valueOf(c)),
    TOOLS(false, (t, c) -> Proficiency.valueOf(c));

    @FunctionalInterface
    private interface Decoder {

        Attribute decode(AttributeType type, String code);
    }

    public static final List<AttributeType> ABILITY_SCORES = Arrays.asList(
        STRENGTH, DEXTERITY, CONSTITUTION, INTELLIGENCE, WISDOM, CHARISMA);

    public static final List<AttributeType> ABILITIES = Arrays.asList(
        RACIAL_TALENT, CLASS_TALENT, DIVINE_DOMAIN_ABILITY, SKILL, SPELL, WEAPON_PROFICIENCY,
        ARMOUR_PROFICIENCY, LANGUAGE, ARTISAN, TOOLS, FIGHTING_STYLE, SPELL_MASTERY,
        EVOCATION_ABILITY, EXPERTISE);

    public static final List<AttributeType> PERSONALITY = Arrays.asList(
        TRAIT, FLAW, BOND, IDEAL);

    private final boolean unique;
    private final Decoder decoder;

    private AttributeType(Function<String, Attribute> decoder) {
        this(true, (type, code) -> decoder.apply(code));
    }

    private AttributeType(boolean unique, Decoder decoder) {
        this.unique = unique;
        this.decoder = decoder;
    }

    public boolean isTypeOfAttribute(Attribute attribute) {
        return attribute.hasType(this);
    }

    public boolean isUnique() {
        return unique;
    }

    public Attribute decode(String code) {
        return decoder.decode(this, code);
    }

    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }
}
