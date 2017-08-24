package characterbuilder.character.attribute;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Ability;
import characterbuilder.utils.StringUtils;
import org.w3c.dom.Node;

public enum Terrain implements Attribute {
    ARCTIC,
    COAST,
    DESERT,
    FOREST,
    GRASSLAND,
    MOUNTAIN,
    SWAMP,
    UNDERDARK;

    @Override
    public AttributeType getType() {
        return AttributeType.TERRAIN;
    }

    @Override
    public void generateInitialChoices(Character character) {
        character.addAttribute(Ability.NATURAL_EXPLORER);
    }

    public static Terrain load(Node node) {
        return valueOf(node.getTextContent());
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

}