package characterbuilder.sheet;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.beast.Beast;
import characterbuilder.character.characterclass.druid.Druid;
import characterbuilder.character.characterclass.druid.DruidAbility;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

public class BeastPage extends Page {

    private boolean firstPage = true;

    public BeastPage(Character character) {
        super(character);
    }

    private static class WildShape implements Attribute {

        private final Beast beast;

        public WildShape(Beast beast) {
            this.beast = beast;
        }

        @Override
        public AttributeType getType() {
            return AttributeType.DRUID_ABILITY;
        }

        @Override
        public String toString() {
            return beast.toString();
        }

        @Override
        public Stream<String> getDescription(Character character) {
            return beast.getDescription();
        }
    }

    @Override
    public Stream<PageBuilder.Container> getPages() {
        firstPage = true;
        List<PageBuilder.Container> pages = new ArrayList<>();
        if (character.hasAttribute(DruidAbility.WILD_SHAPE)) {
            List<Attribute> beasts = new ArrayList<>(
                Druid.getWildShapes(character).map(WildShape::new).collect(toList()));
            PageBuilder.Container page = builder.page();
            page.with(builder.borderedSection(0, 0, 100, 100));
            TextSectionBuilder section = new TextSectionBuilder(character, "Wild Shape Beasts", 100, 100);
            section.addAbilities(beasts);
            page.with(section.getSection(0, 0));
            while (!beasts.isEmpty()) {
                pages.add(page);
                page = builder.page();
                section.createNewSection(100, 100);
                section.addAbilities(beasts);
            }
            pages.add(page);
        }
        return pages.stream();
    }

}
