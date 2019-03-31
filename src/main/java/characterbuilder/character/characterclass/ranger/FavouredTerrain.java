package characterbuilder.character.characterclass.ranger;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.Terrain;
import characterbuilder.character.choice.ChoiceSelector;
import characterbuilder.character.choice.OptionChoice;
import characterbuilder.character.saveload.Savable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.joining;
import java.util.stream.Stream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class FavouredTerrain implements Attribute {

    private final List<Terrain> terrainList = new ArrayList<>();

    public static OptionChoice choose() {
        return new OptionChoice("Favoured Terrain") {
            @Override
            public void select(Character character, ChoiceSelector selector) {
                FavouredTerrain favouredTerrain = character.getAllAttributes()
                    .filter(attr -> attr instanceof FavouredTerrain)
                    .map(attr -> (FavouredTerrain) attr)
                    .findAny().orElse(new FavouredTerrain());
                selector.chooseOption(Arrays.stream(Terrain.values())
                    .filter(terrain -> !favouredTerrain.hasTerrain(terrain)),
                    terrain -> {
                    favouredTerrain.addTerrain(terrain);
                    if (!character.hasAttribute(favouredTerrain))
                        character.addAttribute(favouredTerrain);
                });
            }
        };
    }

    public void addTerrain(Terrain terrain) {
        terrainList.add(terrain);
    }

    public boolean hasTerrain(Terrain terrain) {
        return terrainList.contains(terrain);
    }

    @Override
    public AttributeType getType() {
        return AttributeType.RANGER_ABILITY;
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return Stream.of(
            terrainList.stream().map(Terrain::toString).sorted().collect(joining(", ")),
            "Int. and Wis. checks for proficient skills related to favoured terrain are doubled. ",
            "Fast, reliable, alert, stealthy travel and effective foraging and tracking "
            + "in favoured terrain. ");
    }

    @Override
    public Element save(Document doc) {
        Element element = getType().save(doc);
        element.setTextContent("FAVOURED_TERRAIN");
        terrainList.forEach(t -> {
            Element child = doc.createElement("terrain");
            child.setTextContent(t.name());
            element.appendChild(child);
        });
        return element;
    }

    public static FavouredTerrain load(Element element) {
        assert Savable.text(element).equals("FAVOURED_TERRAIN");
        FavouredTerrain favouredTerrain = new FavouredTerrain();
        Savable.children(element, "terrain")
            .map(Element::getTextContent)
            .map(Terrain::valueOf)
            .forEach(favouredTerrain::addTerrain);
        return favouredTerrain;
    }

    @Override
    public String toString() {
        return "Favoured Terrain";
    }

    @Override
    public int hashCode() {
        return terrainList.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        final FavouredTerrain other = (FavouredTerrain) obj;
        return this.terrainList.equals(other.terrainList);
    }

}
