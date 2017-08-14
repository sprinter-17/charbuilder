package characterbuilder.character.equipment;

import java.util.Optional;

public class Attack {

    private final String name;
    private final int bonus;
    private final String damage;
    private Optional<String> description = Optional.empty();

    public Attack(String name, int bonus, String damage) {
        this.name = name;
        this.bonus = bonus;
        this.damage = damage;
    }

    public void setDescription(String description) {
        this.description = Optional.of(description);
    }

    public String getName() {
        return name;
    }

    public Optional<String> getDescription() {
        return description;
    }

    public int getBonus() {
        return bonus;
    }

    public String getDamage() {
        return damage;
    }
}
