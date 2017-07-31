package characterbuilder.character.attribute;

import characterbuilder.character.ability.Ability;
import characterbuilder.character.ability.DivineDomain;
import characterbuilder.character.ability.Expertise;
import characterbuilder.character.ability.MagicSchool;
import characterbuilder.character.ability.Proficiency;
import characterbuilder.character.ability.Skill;
import characterbuilder.character.ability.Spell;
import characterbuilder.character.equipment.MusicalInstrument;
import characterbuilder.character.equipment.Weapon;
import characterbuilder.utils.StringUtils;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public enum AttributeType {
    NAME(true, StringAttribute::load),
    RACE(true, Race::load),
    CHARACTER_CLASS(true, CharacterClass::load),
    BACKGROUND(true, Background::load),
    STRENGTH(true, IntAttribute::load),
    DEXTERITY(true, IntAttribute::load),
    CONSTITUTION(true, IntAttribute::load),
    INTELLIGENCE(true, IntAttribute::load),
    WISDOM(true, IntAttribute::load),
    CHARISMA(true, IntAttribute::load),
    ALIGNMENT(true, Alignment::load),
    EXPERIENCE_POINTS(true, IntAttribute::load),
    LEVEL(true, IntAttribute::load),
    HIT_POINTS(true, IntAttribute::load),
    SEX(true, Sex::load),
    AGE(true, IntAttribute::load),
    HEIGHT(true, Height::load),
    WEIGHT(true, Weight::load),
    PHYSICAL_DESCRIPTION(true, StringAttribute::load),
    TRAIT(false, StringAttribute::load),
    IDEAL(false, StringAttribute::load),
    BOND(false, StringAttribute::load),
    FLAW(false, StringAttribute::load),
    DRACONIC_ANCESTORY(true, DraconicAncestory::load),
    FIGHTING_STYLE(false, Ability::load),
    MARTIAL_ARCHETYPE(true, MartialArchetype::load),
    ROGUISH_ARCHETYPE(true, RoguishArchetype::load),
    MONASTIC_TRADITION(true, MonasticTradition::load),
    RACIAL_TALENT(false, Ability::load),
    CLASS_TALENT(false, Ability::load),
    BACKGROUND_FEATURE(false, Ability::load),
    SPELL_MASTERY(false, SpellMastery::load),
    ARCANE_TRADITION(true, MagicSchool::load),
    PRIMAL_PATH(true, PrimalPath::load),
    BARDIC_COLLEGE(true, BardicCollege::load),
    EVOCATION_ABILITY(false, Ability::load),
    DIVINE_DOMAIN(true, DivineDomain::load),
    DIVINE_DOMAIN_ABILITY(false, Ability::load),
    SKILL(false, Skill::load),
    EXPERTISE(false, Expertise::load),
    SPELL(false, Spell::load),
    WEAPON_PROFICIENCY(false, Weapon::loadProficiency),
    MUSICAL_INSTRUMENT(false, MusicalInstrument::loadProficiency),
    ARMOUR_PROFICIENCY(false, Proficiency::load),
    LANGUAGE(false, Proficiency::load),
    ARTISAN(false, Proficiency::load),
    TOOLS(false, Proficiency::load);

    @FunctionalInterface
    private interface Loader {

        Attribute load(AttributeType type, Node node);
    }

    public static final List<AttributeType> ABILITY_SCORES = Arrays.asList(
        STRENGTH, DEXTERITY, CONSTITUTION, INTELLIGENCE, WISDOM, CHARISMA);

    public static final List<AttributeType> ABILITIES = Arrays.asList(
        RACIAL_TALENT, CLASS_TALENT, DIVINE_DOMAIN_ABILITY, SKILL, SPELL, WEAPON_PROFICIENCY,
        ARMOUR_PROFICIENCY, LANGUAGE, ARTISAN, TOOLS, FIGHTING_STYLE, SPELL_MASTERY,
        EVOCATION_ABILITY, EXPERTISE, MUSICAL_INSTRUMENT);

    public static final List<AttributeType> PERSONALITY = Arrays.asList(
        TRAIT, FLAW, BOND, IDEAL);

    private final boolean unique;
    private final Loader loader;

    private AttributeType(boolean unique, Function<Node, Attribute> loader) {
        this(unique, (t, n) -> loader.apply(n));
    }

    private AttributeType(boolean unique, Loader loader) {
        this.unique = unique;
        this.loader = loader;
    }

    public boolean isTypeOfAttribute(Attribute attribute) {
        return attribute.hasType(this);
    }

    public boolean isUnique() {
        return unique;
    }

    public Element save(Document doc) {
        return doc.createElement(name().toLowerCase());
    }

    public static Attribute load(Node node) {
        AttributeType type = AttributeType.valueOf(node.getNodeName().toUpperCase());
        return type.loader.load(type, node);
    }

    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }
}
