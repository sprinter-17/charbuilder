package characterbuilder.character.equipment;

import characterbuilder.character.Character;
import characterbuilder.character.ability.FightingStyle;
import characterbuilder.character.ability.Proficiency;
import characterbuilder.character.ability.WeaponProficiency;
import characterbuilder.character.attribute.Attribute;
import static characterbuilder.character.attribute.AttributeType.DEXTERITY;
import static characterbuilder.character.attribute.AttributeType.STRENGTH;
import characterbuilder.character.attribute.DamageType;
import static characterbuilder.character.attribute.DamageType.*;
import characterbuilder.character.attribute.Value;
import static characterbuilder.character.attribute.Value.cp;
import characterbuilder.character.attribute.Weight;
import static characterbuilder.character.attribute.Weight.OZ;
import static characterbuilder.character.attribute.Weight.lb;
import static characterbuilder.character.equipment.EquipmentCategory.MARTIAL_MELEE;
import static characterbuilder.character.equipment.EquipmentCategory.MARTIAL_RANGED;
import static characterbuilder.character.equipment.EquipmentCategory.SIMPLE_MELEE;
import static characterbuilder.character.equipment.EquipmentCategory.SIMPLE_RANGED;
import characterbuilder.utils.StringUtils;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;
import org.w3c.dom.Node;

public enum Weapon implements Equipment {

    CLUB(SIMPLE_MELEE, cp(10), lb(2), attack("5", "1d4", BLUDGEONING, Weapon::melee)),
    DAGGER(SIMPLE_MELEE, cp(200), lb(1),
        attack("5", "1d4", PIERCING, Weapon::finessed),
        attack("20/60", "1d4", PIERCING, Weapon::ranged, "Thrown")),
    GREATCLUB(SIMPLE_MELEE, cp(20), lb(10), attack("5", "1d8", BLUDGEONING, Weapon::melee)),
    HANDAXE(SIMPLE_MELEE, cp(500), lb(2), attack("20/60", "1d6", SLASHING, Weapon::melee)),
    JAVELIN(SIMPLE_MELEE, cp(50), lb(2), attack("30/120", "1d6", PIERCING, Weapon::melee)),
    LIGHT_HAMMER(SIMPLE_MELEE, cp(200), lb(2), attack("20/60", "1d4", BLUDGEONING, Weapon::melee)),
    MACE(SIMPLE_MELEE, cp(500), lb(4), attack("5", "1d6", BLUDGEONING, Weapon::melee)),
    QUARTERSTAFF(SIMPLE_MELEE, cp(20), lb(4),
        attack("5", "1d6", BLUDGEONING, Weapon::melee, "1H"),
        attack("5", "1d8", BLUDGEONING, Weapon::melee, "2H")),
    SICKLE(SIMPLE_MELEE, cp(100), lb(2), attack("5", "1d4", SLASHING, Weapon::melee)),
    SPEAR(SIMPLE_MELEE, cp(100), lb(3),
        attack("20/60", "1d6", PIERCING, Weapon::melee),
        attack("5", "1d8", PIERCING, Weapon::melee, "2H")),
    BATTLEAXE(MARTIAL_MELEE, cp(1000), lb(4),
        attack("5", "1d8", SLASHING, Weapon::melee, "1H"),
        attack("5", "1d10", SLASHING, Weapon::melee, "2H")),
    FLAIL(MARTIAL_MELEE, cp(1000), lb(2), attack("5", "1d8", BLUDGEONING, Weapon::melee)),
    GLAIVE(MARTIAL_MELEE, cp(2000), lb(6), attack("10", "1d10", SLASHING, Weapon::melee)),
    GREATEAXE(MARTIAL_MELEE, cp(3000), lb(7), attack("5", "1d12", SLASHING, Weapon::melee)),
    GREATSWORD(MARTIAL_MELEE, cp(5000), lb(6), attack("5", "2d6", SLASHING, Weapon::melee)),
    HALBERD(MARTIAL_MELEE, cp(2000), lb(6), attack("10", "1d10", SLASHING, Weapon::melee)),
    LANCE(MARTIAL_MELEE, cp(1000), lb(6), attack("10", "1d12", PIERCING, Weapon::melee)),
    LONGSWORD(MARTIAL_MELEE, cp(1500), lb(3),
        attack("5", "1d8", SLASHING, Weapon::melee, "1H"),
        attack("5", "1d10", SLASHING, Weapon::melee, "2H")),
    MAUL(MARTIAL_MELEE, cp(1000), lb(10), attack("5", "2d6", BLUDGEONING, Weapon::melee)),
    MORNINGSTAR(MARTIAL_MELEE, cp(1500), lb(4), attack("5", "1d8", PIERCING, Weapon::melee)),
    PIKE(MARTIAL_MELEE, cp(500), lb(18), attack("5", "1d10", PIERCING, Weapon::melee)),
    RAPIER(MARTIAL_MELEE, cp(2500), lb(2), attack("5", "1d8", PIERCING, Weapon::finessed)),
    SCIMITAR(MARTIAL_MELEE, cp(2500), lb(3), attack("5", "1d6", SLASHING, Weapon::finessed)),
    SHORTSWORD(MARTIAL_MELEE, cp(1000), lb(2), attack("5", "1d6", PIERCING, Weapon::finessed)),
    TRIDENT(MARTIAL_MELEE, cp(500), lb(4), attack("5", "1d6", PIERCING, Weapon::melee)),
    WAR_PICK(MARTIAL_MELEE, cp(500), lb(2), attack("5", "1d8", PIERCING, Weapon::melee)),
    WARHAMMER(MARTIAL_MELEE, cp(1500), lb(2),
        attack("5", "1d8", BLUDGEONING, Weapon::melee, "1H"),
        attack("5", "1d10", BLUDGEONING, Weapon::melee, "2H")),
    WHIP(MARTIAL_MELEE, cp(200), lb(3), attack("10", "1d4", SLASHING, Weapon::finessed)),
    LIGHT_CROSSBOW(SIMPLE_RANGED, cp(2500), lb(5), attack("80/320", "1d8", PIERCING, Weapon::ranged)),
    DART(SIMPLE_RANGED, cp(5), OZ.times(4), attack("20/60", "1d4", PIERCING, Weapon::finessed)),
    SHORTBOW(SIMPLE_RANGED, cp(2500), lb(2), attack("80/320", "1d6", PIERCING, Weapon::ranged)),
    SLING(SIMPLE_RANGED, cp(10), Weight.ZERO, attack("30/120", "1d4", BLUDGEONING, Weapon::ranged)),
    BLOWGUN(MARTIAL_RANGED, cp(1000), lb(1), attack("25/100", "1", PIERCING, Weapon::ranged)),
    HAND_CROSSBOW(MARTIAL_RANGED, cp(7500), lb(3), attack("30/120", "1d6", PIERCING, Weapon::ranged)),
    HEAVY_CROSSBOW(MARTIAL_RANGED, cp(5000), lb(18), attack("100/400", "1d10", PIERCING, Weapon::ranged)),
    LONGBOW(MARTIAL_RANGED, cp(5000), lb(2), attack("150/600", "1d8", PIERCING, Weapon::ranged)),
    NET(MARTIAL_RANGED, cp(100), lb(3), attack("5/15", "special", SLASHING, Weapon::ranged));

    private final EquipmentCategory category;
    private final Value cost;
    private final Weight weight;
    private final Attribute proficiency;
    private final BiFunction<Weapon, Character, Attack>[] attacks;

    private static int melee(Character character) {
        return character.getModifier(STRENGTH);
    }

    private static int ranged(Character character) {
        int bonus = character.getModifier(DEXTERITY);
        if (character.hasAttribute(FightingStyle.ARCHERY))
            bonus += 2;
        return bonus;
    }

    private static int finessed(Character character) {
        return Math.max(melee(character), ranged(character));
    }

    private static BiFunction<Weapon, Character, Attack> attack(String range,
        String damage, DamageType type,
        Function<Character, Integer> bonus) {
        return attack(range, damage, type, bonus, Optional.empty());
    }

    private static BiFunction<Weapon, Character, Attack> attack(String range,
        String damage, DamageType type,
        Function<Character, Integer> bonus, String description) {
        return attack(range, damage, type, bonus, Optional.of(description));
    }

    private static BiFunction<Weapon, Character, Attack> attack(String range,
        String damage, DamageType type,
        Function<Character, Integer> bonus, Optional<String> description) {
        return (w, ch) -> {
            int mod = bonus.apply(ch);
            mod += ch.getInventory()
                .filter(eq -> eq.getBaseEquipment().equals(w))
                .mapToInt(Equipment::getBonus)
                .max().orElse(0);
            int hit = mod;
            if (ch.hasAttribute(w.getProficiency()))
                hit += ch.getProficiencyBonus();
            String name = w.toString();
            Attack attack = new Attack(name, range, hit, damageText(damage, mod), type);
            description.ifPresent(attack::setDescription);
            return attack;
        };
    }

    private static String damageText(String damage, int modifier) {
        if (modifier != 0)
            damage += String.format("%+d", modifier);
        return damage;
    }

    private Weapon(EquipmentCategory category, Value cost, Weight weight,
        BiFunction<Weapon, Character, Attack>... attacks) {
        this.category = category;
        this.cost = cost;
        this.weight = weight;
        this.proficiency = new WeaponProficiency(this);
        this.attacks = attacks;
    }

    @Override
    public EquipmentCategory getCategory() {
        return category;
    }

    @Override
    public Optional<Weapon> asWeapon() {
        return Optional.of(this);
    }

    public Stream<Attack> getAttacks(Character character) {
        return Arrays.stream(attacks).map(at -> at.apply(this, character));
    }

    @Override
    public Weight getWeight() {
        return weight;
    }

    @Override
    public Value getValue() {
        return cost;
    }

    public Attribute getProficiency() {
        return proficiency;
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return Stream.concat(Equipment.super.getDescription(character),
            getAttacks(character).map(at -> String.format("Attack %+d ", at.getBonus())
            + at.getDamage() + at.getDescription().map(d -> " (" + d + ")").orElse(null)));
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    public static Weapon load(Node node) {
        return Weapon.valueOf(node.getTextContent());
    }

    public static Attribute loadProficiency(Node node) {
        if (node.getTextContent().startsWith("ALL"))
            return Proficiency.load(node);
        else
            return WeaponProficiency.load(node);
    }

}
