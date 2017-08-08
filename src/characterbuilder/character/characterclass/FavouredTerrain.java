package characterbuilder.character.characterclass;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Ability;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.utils.StringUtils;
import org.w3c.dom.Node;

public enum FavouredTerrain implements Attribute {
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
        return AttributeType.FAVOURED_TERRAIN;
    }

    @Override
    public void generateInitialChoices(Character character) {
        character.addAttribute(Ability.NATURAL_EXPLORER);
    }

    public static FavouredTerrain load(Node node) {
        return valueOf(node.getTextContent());
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

}
