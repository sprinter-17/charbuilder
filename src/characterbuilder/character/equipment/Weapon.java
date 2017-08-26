package characterbuilder.character.equipment;

import characterbuilder.character.Character;
import characterbuilder.character.ability.FightingStyle;
import characterbuilder.character.ability.Proficiency;
import characterbuilder.character.ability.WeaponProficiency;
import characterbuilder.character.attribute.Attribute;
import static characterbuilder.character.attribute.AttributeType.DEXTERITY;
import static characterbuilder.character.attribute.AttributeType.STRENGTH;
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

    CLUB(SIMPLE_MELEE, cp(10), lb(2),
        attack("1d4", Weapon::melee)),
    DAGGER(SIMPLE_MELEE, cp(200), lb(1),
        attack("1d4", Weapon::finessed, "20/60")),
    GREATCLUB(SIMPLE_MELEE, cp(20), lb(10),
        attack("1d8", Weapon::melee)),
    HANDAXE(SIMPLE_MELEE, cp(500), lb(2), attack("1d6", Weapon::melee, "20/60")),
    JAVELIN(SIMPLE_MELEE, cp(50), lb(2), attack("1d6", Weapon::melee, "30/120")),
    LIGHT_HAMMER(SIMPLE_MELEE, cp(200), lb(2), attack("1d4", Weapon::melee, "20/60")),
    MACE(SIMPLE_MELEE, cp(500), lb(4), attack("1d6", Weapon::melee)),
    QUARTERSTAFF(SIMPLE_MELEE, cp(20), lb(4),
        attack("1d6", Weapon::melee, "1H"),
        attack("1d8", Weapon::melee, "2H")),
    SICKLE(SIMPLE_MELEE, cp(100), lb(2),
        attack("1d4", Weapon::melee)),
    SPEAR(SIMPLE_MELEE, cp(100), lb(3),
        attack("1d6", Weapon::melee, "20/60"),
        attack("1d8", Weapon::melee, "2H")),
    BATTLEAXE(MARTIAL_MELEE, cp(1000), lb(4),
        attack("1d8", Weapon::melee, "1H"),
        attack("1d10", Weapon::melee, "2H")),
    FLAIL(MARTIAL_MELEE, cp(1000), lb(2), attack("1d8", Weapon::melee)),
    GLAIVE(MARTIAL_MELEE, cp(2000), lb(6), attack("1d10", Weapon::melee, "reach")),
    GREATEAXE(MARTIAL_MELEE, cp(3000), lb(7), attack("1d12", Weapon::melee)),
    GREATSWORD(MARTIAL_MELEE, cp(5000), lb(6), attack("2d6", Weapon::melee)),
    HALBERD(MARTIAL_MELEE, cp(2000), lb(6), attack("1d10", Weapon::melee, "reach")),
    LANCE(MARTIAL_MELEE, cp(1000), lb(6), attack("1d12", Weapon::melee, "reach")),
    LONGSWORD(MARTIAL_MELEE, cp(1500), lb(3),
        attack("1d8", Weapon::melee, "1H"),
        attack("1d10", Weapon::melee, "2H")),
    MAUL(MARTIAL_MELEE, cp(1000), lb(10), attack("2d6", Weapon::melee)),
    MORNINGSTAR(MARTIAL_MELEE, cp(1500), lb(4), attack("1d8", Weapon::melee)),
    PIKE(MARTIAL_MELEE, cp(500), lb(18), attack("1d10", Weapon::melee)),
    RAPIER(MARTIAL_MELEE, cp(2500), lb(2), attack("1d8", Weapon::finessed)),
    SCIMITAR(MARTIAL_MELEE, cp(2500), lb(3), attack("1d6", Weapon::finessed)),
    SHORTSWORD(MARTIAL_MELEE, cp(1000), lb(2), attack("1d6", Weapon::finessed)),
    TRIDENT(MARTIAL_MELEE, cp(500), lb(4), attack("1d6", Weapon::melee)),
    WAR_PICK(MARTIAL_MELEE, cp(500), lb(2), attack("1d8", Weapon::melee)),
    WARHAMMER(MARTIAL_MELEE, cp(1500), lb(2),
        attack("1d8", Weapon::melee, "1H"),
        attack("1d10", Weapon::melee, "2H")),
    WHIP(MARTIAL_MELEE, cp(200), lb(3), attack("1d4", Weapon::finessed, "reach")),
    LIGHT_CROSSBOW(SIMPLE_RANGED, cp(2500), lb(5), attack("1d8", Weapon::ranged, "80/320")),
    DART(SIMPLE_RANGED, cp(5), OZ.times(4), attack("1d4", Weapon::finessed, "20/60")),
    SHORTBOW(SIMPLE_RANGED, cp(2500), lb(2), attack("1d6", Weapon::ranged, "80/320")),
    SLING(SIMPLE_RANGED, cp(10), Weight.ZERO, attack("1d4", Weapon::ranged, "30/120")),
    BLOWGUN(MARTIAL_RANGED, cp(1000), lb(1), attack("1", Weapon::ranged, "25/100")),
    HAND_CROSSBOW(MARTIAL_RANGED, cp(7500), lb(3), attack("1d6", Weapon::ranged, "30/120")),
    HEAVY_CROSSBOW(MARTIAL_RANGED, cp(5000), lb(18), attack("1d10", Weapon::ranged, "100/400")),
    LONGBOW(MARTIAL_RANGED, cp(5000), lb(2), attack("1d8", Weapon::ranged, "150/600")),
    NET(MARTIAL_RANGED, cp(100), lb(3), attack("special", Weapon::ranged, "5/15"));

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

    private static BiFunction<Weapon, Character, Attack> attack(String damage,
        Function<Character, Integer> bonus) {
        return attack(damage, bonus, "");
    }

    private static BiFunction<Weapon, Character, Attack> attack(String damage,
        Function<Character, Integer> bonus, String description) {
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
            Attack attack = new Attack(name, hit, damageText(damage, mod));
            attack.setDescription(description);
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
