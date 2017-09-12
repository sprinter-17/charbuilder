package characterbuilder.character.equipment;

import characterbuilder.character.attribute.DamageType;
import java.util.Optional;

public class Attack {

    private final String name;
    private final String range;
    private final int bonus;
    private final String damage;
    private final DamageType type;
    private Optional<String> description = Optional.empty();

    public Attack(String name, String range, int bonus, String damage, DamageType type) {
        this.name = name;
        this.range = range;
        this.bonus = bonus;
        this.damage = damage;
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = Optional.of(description);
    }

    public String getName() {
        return name;
    }

    public String getRange() {
        return range;
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

    public DamageType getType() {
        return type;
    }
}
