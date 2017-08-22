package characterbuilder.character.attribute;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Ability;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.utils.StringUtils;
import java.util.stream.Stream;
import org.w3c.dom.Node;

public enum DraconicAncestory implements Attribute {
    BLACK("Acid, 5x30' line, Dex. save"),
    BLUE("Lightning, 5x40' line, Dex. save"),
    BRASS("Fire, 5x30' line, Dex. save"),
    BRONZE("Lightning, 5x30' line, Dex. save"),
    COPPER("Acid, 5x30' line, Dex. save"),
    GOLD("Fire, 15' cone, Dex. save"),
    GREEN("Poison, 15' cone, Con. save"),
    RED("Fire, 15' cone, Dex. save"),
    SILVER("Cold, 15' cone, Con. save"),
    WHITE("Cold, 15' cone, Con. save");

    private final String breathWeapon;

    private DraconicAncestory(String breathWeapon) {
        this.breathWeapon = breathWeapon;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.DRACONIC_ANCESTORY;
    }

    public String getBreathWeapon() {
        return breathWeapon;
    }

    @Override
    public void generateInitialChoices(Character character) {
        character.addAttribute(Ability.BREATH_WEAPON);
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
