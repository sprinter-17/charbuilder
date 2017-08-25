package characterbuilder.character.attribute;

import characterbuilder.utils.StringUtils;
import org.w3c.dom.Element;

public enum Terrain implements Attribute {
    ARCTIC,
    COAST,
    DESERT,
    FOREST,
    GRASSLAND,
    MOUNTAIN,
    SWAMP,
    UNDERDARK;

    public AttributeType getType() {
        return AttributeType.TERRAIN;
    }

    public static Terrain load(Element element) {
        return valueOf(element.getTextContent());
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

}
