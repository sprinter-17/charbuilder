package characterbuilder.character.attribute;

import characterbuilder.character.ability.Ability;
import characterbuilder.character.ability.ElementalAdept;
import characterbuilder.character.ability.Expertise;
import characterbuilder.character.ability.Feat;
import characterbuilder.character.ability.FightingStyle;
import characterbuilder.character.ability.Language;
import characterbuilder.character.ability.Proficiency;
import characterbuilder.character.ability.RacialTalent;
import characterbuilder.character.ability.Skill;
import characterbuilder.character.characterclass.CharacterClassLevel;
import characterbuilder.character.characterclass.barbarian.BarbarianAbility;
import characterbuilder.character.characterclass.barbarian.PrimalPath;
import characterbuilder.character.characterclass.bard.BardAbility;
import characterbuilder.character.characterclass.bard.BardicCollege;
import characterbuilder.character.characterclass.cleric.ClericAbility;
import characterbuilder.character.characterclass.cleric.DivineDomain;
import characterbuilder.character.characterclass.druid.DruidAbility;
import characterbuilder.character.characterclass.druid.DruidCircle;
import characterbuilder.character.characterclass.fighter.FighterAbility;
import characterbuilder.character.characterclass.fighter.Maneuver;
import characterbuilder.character.characterclass.fighter.MartialArchetype;
import characterbuilder.character.characterclass.monk.ElementalDiscipline;
import characterbuilder.character.characterclass.monk.MonasticTradition;
import characterbuilder.character.characterclass.monk.MonkAbility;
import characterbuilder.character.characterclass.paladin.PaladinAbility;
import characterbuilder.character.characterclass.paladin.SacredOath;
import characterbuilder.character.characterclass.ranger.RangerAbility;
import characterbuilder.character.characterclass.ranger.RangerArchetype;
import characterbuilder.character.characterclass.ranger.RangerCompanion;
import characterbuilder.character.characterclass.rogue.RogueAbility;
import characterbuilder.character.characterclass.rogue.RoguishArchetype;
import characterbuilder.character.characterclass.sorcerer.MetaMagic;
import characterbuilder.character.characterclass.sorcerer.SorcererAbility;
import characterbuilder.character.characterclass.sorcerer.SorcerousOrigin;
import characterbuilder.character.characterclass.warlock.EldritchInvocation;
import characterbuilder.character.characterclass.warlock.MysticArcanum;
import characterbuilder.character.characterclass.warlock.OtherwordlyPatron;
import characterbuilder.character.characterclass.warlock.WarlockAbility;
import characterbuilder.character.characterclass.wizard.MagicSchool;
import characterbuilder.character.characterclass.wizard.WizardAbility;
import characterbuilder.character.equipment.MusicalInstrument;
import characterbuilder.character.equipment.Weapon;
import characterbuilder.character.spell.SignatureSpell;
import characterbuilder.character.spell.SpellAbility;
import characterbuilder.character.spell.SpellCasting;
import characterbuilder.utils.StringUtils;
import java.util.Arrays;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXParseException;

public enum AttributeType {
    NAME(true, StringAttribute::load),
    RACE(true, Race::load),
    CHARACTER_CLASS(false, CharacterClassLevel::load),
    BACKGROUND(true, Background::load),
    STRENGTH(true, AbilityScore::load),
    DEXTERITY(true, AbilityScore::load),
    CONSTITUTION(true, AbilityScore::load),
    INTELLIGENCE(true, AbilityScore::load),
    WISDOM(true, AbilityScore::load),
    CHARISMA(true, AbilityScore::load),
    ALIGNMENT(true, Alignment::load),
    EXPERIENCE_POINTS(true, IntAttribute::load),
    HIT_POINTS(true, IntAttribute::load),
    SEX(true, Sex::load),
    AGE(true, IntAttribute::load),
    HEIGHT(true, Height::load),
    WEIGHT(true, Weight::load),
    PICTURE(true, Picture::load),
    PHYSICAL_DESCRIPTION(true, StringAttribute::load),
    PERSONAL_HISTORY(true, StringAttribute::load),
    TRAIT(false, StringAttribute::load),
    IDEAL(false, StringAttribute::load),
    BOND(false, StringAttribute::load),
    FLAW(false, StringAttribute::load),
    RACIAL_TALENT(false, RacialTalent::load),
    DRACONIC_ANCESTRY(true, DraconicAncestry::load),
    CLASS_TALENT(false, Ability::load),
    MANEUVER(false, Maneuver::load),
    MARTIAL_ARCHETYPE(true, MartialArchetype::load),
    FIGHTING_STYLE(false, FightingStyle::load),
    BARBARIAN_ABILITY(false, BarbarianAbility::load),
    BARD_ABILITY(false, BardAbility::load),
    CLERIC_ABILITY(false, ClericAbility::load),
    DRUID_ABILITY(false, DruidAbility::load),
    FIGHTER_ABILITY(false, FighterAbility::load),
    MONK_ABILITY(false, MonkAbility::load),
    RANGER_ABILITY(false, RangerAbility::load),
    ROGUE_ABILITY(false, RogueAbility::load),
    SORCERER_ABILITY(false, SorcererAbility::load),
    WIZARD_ABILITY(false, WizardAbility::load),
    META_MAGIC(false, MetaMagic::load),
    ROGUISH_ARCHETYPE(true, RoguishArchetype::load),
    MONASTIC_TRADITION(true, MonasticTradition::load),
    ELEMENTAL_DISCIPLINE(false, ElementalDiscipline::load),
    DRUID_CIRCLE(true, DruidCircle::load),
    DRUID_CIRCLE_ABILITY(false, DruidCircle.Ability::load),
    PALADIN_ABILITY(false, PaladinAbility::load),
    SACRED_OATH(true, SacredOath::load),
    RANGER_ARCHETYPE(true, RangerArchetype::load),
    SORCEROUS_ORIGIN(true, SorcerousOrigin::load),
    OTHERWORLDLY_PATRON(true, OtherwordlyPatron::load),
    WARLOCK_ABILITY(false, WarlockAbility::load),
    ELDRITCH_INVOCATION(false, EldritchInvocation::load),
    MYSTIC_ARCANUM(false, MysticArcanum::load),
    BACKGROUND_ABILITY(false, Background.Ability::load),
    ENTERTAINER_ROUTINE(false, Proficiency::load),
    GUILD_BUSINESS(false, Proficiency::load),
    SPELL_ABILITY(false, SpellAbility::load),
    SPELLCASTING(false, SpellCasting::load),
    SPELL_MASTERY(false, Ability::load),
    SIGNATURE_SPELL(true, SignatureSpell::load),
    ARCANE_TRADITION(true, MagicSchool::load),
    ARCANE_TRADITION_ABILITY(false, MagicSchool::loadAbility),
    PRIMAL_PATH(true, PrimalPath::load),
    BARDIC_COLLEGE(true, BardicCollege::load),
    DIVINE_DOMAIN(true, DivineDomain::load),
    DIVINE_DOMAIN_ABILITY(false, DivineDomain::loadAbility),
    TERRAIN(false, Terrain::load),
    RANGERS_COMPANION(true, RangerCompanion::load),
    SKILL(false, Skill::load),
    FEAT(false, Feat::load),
    ELEMENTAL_ADEPT(false, ElementalAdept::load),
    EXPERTISE(false, Expertise::load),
    WEAPON_PROFICIENCY(false, Weapon::loadProficiency),
    MUSICAL_INSTRUMENT_PROFICIENCY(false, MusicalInstrument::loadProficiency),
    ARMOUR_PROFICIENCY(false, Proficiency::load),
    LANGUAGE(false, Language::load),
    ARTISAN(false, Proficiency::load),
    TOOLS(false, Proficiency::load);

    @FunctionalInterface
    private interface TypedLoader {

        Attribute load(AttributeType type, Element element) throws SAXParseException;
    }

    @FunctionalInterface
    private interface TypelessLoader {

        Attribute load(Element element) throws SAXParseException;
    }

    public static final List<AttributeType> PERSONALITY = Arrays.asList(
        TRAIT, FLAW, BOND, IDEAL);

    private final boolean unique;
    private final TypedLoader loader;

    private AttributeType(boolean unique, TypelessLoader loader) {
        this(unique, (t, n) -> loader.load(n));
    }

    private AttributeType(boolean unique, TypedLoader loader) {
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

    public static Attribute load(Element element) throws SAXParseException {
        AttributeType type = AttributeType.valueOf(element.getTagName().toUpperCase());
        return type.loader.load(type, element);
    }

    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }
}
