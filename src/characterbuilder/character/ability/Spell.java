package characterbuilder.character.ability;

import characterbuilder.character.Character;
import static characterbuilder.character.ability.MagicSchool.*;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.CharacterClass;
import static characterbuilder.character.attribute.CharacterClass.*;
import characterbuilder.utils.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public enum Spell implements Attribute {
    ACID_SPLASH(CONJURATION, 0, "1 action", "60'", "1-2 creatures", "V,S", "-",
        "[($level+7)/6]d6 acid damage (Dex save half)", WIZARD),
    AID(ABJURATION, 2, "1 action", "30'", "1-3 creatures", "V,S,M", "8 hours",
        "+5 hp / lev", CLERIC),
    ANTIMAGIC_FIELD(ABJURATION, 8, "1 action", "0", "10' radius", "V,S,M", "up to 1 hour",
        "prevent magic", WIZARD),
    ARCANE_EYE(MagicSchool.DIVINATION, 4, "1 action", "30'", "-", "V,S,M", "up to 1 hour",
        "remote seeing", WIZARD),
    ARCANE_LOCK(ABJURATION, 2, "1 action", "touch", "1 object", "V,S,M (25gp)", "permanent",
        "lock", WIZARD),
    ASTRAL_PROJECTION(NECROMANCY, 9, "1 hour", "10'", "1-9 creatures", "VSM (1100gp / cr)", "special",
        "travel to Astral plane", CLERIC),
    AUGURY(MagicSchool.DIVINATION, 2, "1 minute", "0", "self", "V,S,M (25gp)", "-",
        "receive omen", CLERIC),
    BEACON_OF_HOPE(ABJURATION, 3, "1 action", "30'", "all creatures in range", "V,S", "up to 1 minute",
        "advantage on save vs Wis & Death; max hp healing", CLERIC),
    BLADE_BARRIER(EVOCATION, 6, "1 action", "90'", "100' long or 60' diameter x20'x5'", "V,S", "up to 10 minutes",
        "6d6 damage (save vs Dex)", CLERIC),
    BLESS(ENCHANTMENT, 1, "1 action", "30'", "1-3 (+1 / lev) creatures", "V,S,M", "up to 1 minute",
        "+1d4 to hit / saves", CLERIC),
    BLUR(ILLUSION, 2, "1 action", "self", "-", "V", "up to 1 minute",
        "attackers disadvantaged", WIZARD),
    BOND(2, CLERIC),
    BURNING_HANDS(EVOCATION, 1, "1 action", "self", "15' cone", "V,S", "-",
        "3d6 (+1d6 / lev) dam", WIZARD),
    CHAIN_LIGHTNING(EVOCATION, 6, "1 action", "150'", "1-4 creatures (+1 / lev) within 30'",
        "V,S,M", "-", "10d8 dam (save vs Dex for half)", WIZARD),
    CHARM_PERSON(ENCHANTMENT, 1, "1 action", "30'", "1 creature / level", "V,S", "1h",
        "save vs Wis or charmed", WIZARD),
    COMMAND(ENCHANTMENT, 1, "1 action", "60'", "1 creature / lev", "V", "1 round",
        "save vs Wis or obey command", CLERIC),
    COMMUNE(MagicSchool.DIVINATION, 5, "1 minute", "self", "-", "V,S,M", "1 minute",
        "answer 1-3 yes/no questions", CLERIC),
    COMPREHEND_LANGUAGES(MagicSchool.DIVINATION, 1, "1 action", "self", "-", "V,S,M", "1 hour",
        "understand languages", WIZARD),
    CONE_OF_COLD(EVOCATION, 5, "1 action", "self", "60' cone", "V,S,M", "-",
        "8d8 dam (+1d / lev) (save vs Con 1/2 dam)", WIZARD),
    COUNTERSPELL(ABJURATION, 3, "1 reaction", "60'", "1 creature", "S", "-",
        "interrupt spell casting; automatic for spells to level three, otherwise DC 10 + spell level", WIZARD),
    CURE_WOUNDS(1, CLERIC),
    DANCING_LIGHTS(EVOCATION, 0, "1 action", "120'", "-", "V,S,M", "up to 1 minute",
        "1-4 lights", WIZARD),
    DARKNESS(2, WIZARD),
    DEATH_WARD(4, CLERIC),
    DELAYED_BLAST_FIREBALL(7, WIZARD),
    DETECT_MAGIC(MagicSchool.DIVINATION, 1, "1 action", "self", "30'", "V,S", "up to 10 minutes",
        "sense magic", CLERIC, WIZARD),
    DIMENSION_DOOR(CONJURATION, 4, "1 action", "500'", "2 creatures", "V", "-",
        "teleport", WIZARD),
    DISGUISE_SELF(1, WIZARD),
    DISINTEGRATE(6, WIZARD),
    DISPEL_MAGIC(3, CLERIC, WIZARD),
    DIVINATION(MagicSchool.DIVINATION, 4, "1 action", "self", "-", "V,S,M (25gp)", "-",
        "answer 1 question", CLERIC),
    DOMINATE_MONSTER(ENCHANTMENT, 8, "1 action", "60'", "1 creature", "V,S", "up to 1 hour",
        "control, save vs Wis", WIZARD),
    DOMINATE_PERSON(ENCHANTMENT, 5, "1 action", "60'", "1 creature", "V,S", "up to 1 minute",
        "control, save vs Wis", WIZARD),
    DREAM(ILLUSION, 5, "1 minute", "special", "1 creature", "V,S,M", "8 hours",
        "message in dream, optional 3d6 psychic dam", WIZARD),
    EARTHQUAKE(EVOCATION, 8, "1 action", "500'", "100' radius", "V,S,M", "up to 1 minute",
        "special", CLERIC),
    ETHEREALNESS(TRANSMUTATION, 7, "1 action", "self", "1 creature + 3 / lev", "V,S", "up to 8 hours",
        "enter ethereal plane", CLERIC),
    FIND_THE_PATH(6, CLERIC),
    FINGER_OF_DEATH(7, WIZARD),
    FIREBALL(3, WIZARD),
    FIRE_BOLT(EVOCATION, 0, "1 action", "120'", "1 creature or object", "V,S", "-",
        "ranged attack [($level+7)/6]d10 dam", WIZARD),
    FIRE_STORM(7, CLERIC),
    FLAME_STRIKE(5, CLERIC),
    FLAMING_SPHERE(2, WIZARD),
    FLY(3, WIZARD),
    FORESIGHT(9, WIZARD),
    FREEDOM_OF_MOVEMENT(4, CLERIC),
    GATE(9, CLERIC),
    GLOBE_OF_INVULNERABILITY(6, WIZARD),
    GREATER_INVISIBILITY(4, WIZARD),
    GREATER_RESTORATION(5, CLERIC),
    GUARDIAN_OF_FAITH(4, CLERIC),
    GUIDANCE(0, CLERIC),
    GUIDING_BOLT(1, CLERIC),
    HARM(6, CLERIC),
    HASTE(3, WIZARD),
    HEAL(6, CLERIC),
    HEALING_WORD(1, CLERIC),
    HEROES_FEAST(6, CLERIC),
    HOLD_PERSON(2, CLERIC, WIZARD),
    HOLY_AURA(8, CLERIC),
    ICE_STORM(EVOCATION, 4, "1 action", "300'", "20' radius", "V,S,M", "-",
        "2d8 bludgeon dam, 4d6 cold dam, save vs Dex half", WIZARD),
    IDENTIFY(MagicSchool.DIVINATION, 1, "1m", "T", "1 object", "V,S,M (100gp)", "I",
        "identify magic", WIZARD),
    IMPRISONMENT(9, WIZARD),
    INFLICT_WOUNDS(1, CLERIC),
    INVISIBILITY(2, WIZARD),
    KNOCK(2, WIZARD),
    LESSER_RESTORATION(2, CLERIC),
    LEVITATE(2, WIZARD),
    LIGHT(EVOCATION, 0, "1 ation", "touch", "1 object", "V,M", "1 hour",
        "light 40' radius", CLERIC, WIZARD),
    LIGHTNING_BOLT(3, WIZARD),
    LOCATE_CREATURE(4, CLERIC),
    MAGE_ARMOR(ABJURATION, 1, "1 action", "touch", "1 creature", "V,S,M", "8 hours",
        "AC 13 + Dex bonus", WIZARD),
    MAGE_HAND(CONJURATION, 0, "1 action", "30'", "-", "V,S", "1 minute",
        "spectral hand", WIZARD),
    MAGIC_MISSILE(EVOCATION, 1, "1 action", "120'", "3 darts + 1 / level", "V,S", "-",
        "1d4+1 damage", WIZARD),
    MAGIC_WEAPON(TRANSMUTATION, 2, "1 bonus action", "touch", "1 weapon", "V,S", "up to 1 hour",
        "+1 damage / attack (lev 4: +2, lev 6: +3)", WIZARD),
    MAJOR_IMAGE(ILLUSION, 3, "1 action", "120'", "20' cube", "V,S,M", "up to 10 minutes (lev 6: perm)",
        "create illusion", WIZARD),
    MASS_CURE_WOUNDS(EVOCATION, 5, "1 action", "60'", "up to 6 creature, 30' radius", "V,S", "-",
        "3d8 + spell ability heal (+1d8 / lev)", CLERIC),
    MASS_HEAL(EVOCATION, 9, "1 action", "60'", "any number of creatures", "V,S", "-",
        "700 HP total healing", CLERIC),
    MASS_HEALING_WORD(EVOCATION, 3, "1 bonus action", "60'", "up to 6 creatures", "V", "-",
        "1d4 + spell ability heal (+1d4 / lev)", CLERIC),
    MASS_SUGGESTION(6, WIZARD),
    MAZE(8, WIZARD),
    METEOR_SWARM(9, WIZARD),
    MINOR_ILLUSION(0, WIZARD),
    MISTY_STEP(2, WIZARD),
    MORDENKAINENS_SWORD(7, WIZARD),
    OTTOS_IRRESISTIBLE_DANCE(6, WIZARD),
    PASSWALL(5, WIZARD),
    POISON_SPRAY(CONJURATION, 0, "1 action", "10'", "1 creature", "V,S", "-",
        "[($level+7)/6]d12 dam", WIZARD),
    POWER_WORD_KILL(9, WIZARD),
    POWER_WORD_STUN(8, WIZARD),
    PRAYER_OF_HEALING(2, CLERIC),
    PRESTIDIGITATION(0, WIZARD),
    PROTECTION_FROM_ENERGY(3, CLERIC, WIZARD),
    RAISE_DEAD(5, CLERIC),
    RAY_OF_FROST(EVOCATION, 0, "1 action", "60'", "1 creature", "V,S", "-",
        "[($level+7)/6]d8 cold damage", WIZARD),
    REGENERATE(7, CLERIC),
    REMOVE_CURSE(3, CLERIC),
    RESISTANCE(0, CLERIC),
    RESURRECTION(7, CLERIC),
    REVIVIFY(3, CLERIC),
    SACRED_FLAME(0, CLERIC),
    SANCTUARY(1, CLERIC),
    SHATTER(2, WIZARD),
    SHIELD(ABJURATION, 1, "reaction", "Self", "-", "V,S", "1 round",
        "AC +5", WIZARD),
    SHIELD_OF_FAITH(1, CLERIC),
    SHOCKING_GRASP(0, WIZARD),
    SILENCE(2, CLERIC),
    SILENT_IMAGE(ILLUSION, 1, "1 action", "60'", "1 object", "V,S,M", "up to 10 minutes",
        "visual illusion", WIZARD),
    SLEEP(1, WIZARD),
    SPARE_THE_DYING(0, CLERIC),
    SPEAK_WITH_DEAD(3, CLERIC),
    SPIDER_CLIMB(2, WIZARD),
    SPIRITUAL_WEAPON(2, CLERIC),
    SPIRIT_GUARDIANS(3, CLERIC),
    STONESKIN(4, WIZARD),
    SUGGESTION(2, WIZARD),
    SUNBURST(8, WIZARD),
    TELEPORT(7, WIZARD),
    THAUMATURGY(0, CLERIC),
    THUNDERWAVE(1, WIZARD),
    TIME_STOP(9, WIZARD),
    TRUE_RESURRECTION(9, CLERIC),
    TRUE_SEEING(6, CLERIC, WIZARD),
    WALL_OF_FIRE(4, WIZARD),
    WALL_OF_STONE(5, WIZARD),
    WARDING_BOND(ABJURATION, 2, "1 action", "touch", "1 creature", "V,S,M", "1 hour",
        "+1 AC, saving throws; resistance to damage.", CLERIC),
    WEB(CONJURATION, 2, "1 action", "60'", "20' cube", "V,S,M", "up to 1 hour",
        "creatures in web constrained unless save vs Dex.", WIZARD),;

    public static Stream<Spell> spells(CharacterClass characterClass, int level) {
        return Arrays.stream(values())
            .filter(sp -> sp.classes.contains(characterClass))
            .filter(sp -> sp.level == level);
    }

    private final List<CharacterClass> classes = new ArrayList<>();
    private final MagicSchool school;
    private final int level;
    private final String castingTime;
    private final String range;
    private final String area;
    private final String duration;
    private final String components;
    private final Function<Character, String> effect;

    private Spell(MagicSchool school, int level, String castingTime,
        String range, String area, String components, String duration,
        Function<Character, String> effect, CharacterClass... classes) {
        this.school = school;
        this.level = level;
        this.castingTime = castingTime;
        this.range = range;
        this.area = area;
        this.duration = duration;
        this.components = components;
        this.effect = effect;
        Arrays.stream(classes).forEach(this.classes::add);
    }

    private Spell(MagicSchool school, int level, String castingTime,
        String range, String area, String components, String duration,
        String effect, CharacterClass... classes) {
        this.school = school;
        this.level = level;
        this.castingTime = castingTime;
        this.range = range;
        this.area = area;
        this.duration = duration;
        this.components = components;
        this.effect = ch -> StringUtils.expand(effect, ch);
        Arrays.stream(classes).forEach(this.classes::add);
    }

    private Spell(int level, CharacterClass... classes) {
        this.school = ABJURATION;
        this.level = level;
        this.castingTime = "I";
        this.range = "0";
        this.area = "0";
        this.duration = "1";
        this.components = "VSM";
        this.effect = ch -> "Effect";
        Arrays.stream(classes).forEach(this.classes::add);
    }

    @Override
    public AttributeType getType() {
        return AttributeType.SPELL;
    }

    public int getLevel() {
        return level;
    }

    public String getEffect(Character character) {
        return effect.apply(character);
    }

    public String getArea() {
        return area;
    }

    public String getDuration() {
        return duration;
    }

    public String getCastingTime() {
        return castingTime;
    }

    public String getRange() {
        return range;
    }

    public String getComponents() {
        return components;
    }

    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }
}
