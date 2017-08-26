package characterbuilder.character.attribute;

import characterbuilder.character.Character;
import characterbuilder.character.ability.RacialTalent;
import characterbuilder.utils.StringUtils;
import java.util.stream.Stream;
import org.w3c.dom.Node;

public enum DraconicAncestory implements Attribute {
    BLACK(DamageType.ACID, "5x30' line, Dex. save"),
    BLUE(DamageType.LIGHTNING, "5x40' line, Dex. save"),
    BRASS(DamageType.FIRE, "5x30' line, Dex. save"),
    BRONZE(DamageType.LIGHTNING, "5x30' line, Dex. save"),
    COPPER(DamageType.ACID, "5x30' line, Dex. save"),
    GOLD(DamageType.FIRE, "15' cone, Dex. save"),
    GREEN(DamageType.POISON, "15' cone, Con. save"),
    RED(DamageType.FIRE, "15' cone, Dex. save"),
    SILVER(DamageType.COLD, "15' cone, Con. save"),
    WHITE(DamageType.COLD, "15' cone, Con. save");

    private final DamageType damage;
    private final String breathWeapon;

    private DraconicAncestory(DamageType damage, String breathWeapon) {
        this.damage = damage;
        this.breathWeapon = breathWeapon;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.DRACONIC_ANCESTORY;
    }

    public DamageType getDamage() {
        return damage;
    }

    public String getBreathWeapon() {
        return damage.toString() + ", " + breathWeapon;
    }

    @Override
    public void generateInitialChoices(Character character) {
        character.addAttribute(RacialTalent.BREATH_WEAPON);
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return Stream.of("Breath " + breathWeapon);
    }

    public static DraconicAncestory load(Node node) {
        return DraconicAncestory.valueOf(node.getTextContent());
    }

}
