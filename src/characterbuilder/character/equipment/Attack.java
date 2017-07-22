package characterbuilder.character.equipment;

public class Attack {

    private final String name;
    private final int bonus;
    private final String damage;

    public Attack(String name, int bonus, String damage) {
        this.name = name;
        this.bonus = bonus;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getBonus() {
        return bonus;
    }

    public String getDamage() {
        return damage;
    }
}
