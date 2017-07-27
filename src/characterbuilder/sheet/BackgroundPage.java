package characterbuilder.sheet;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Ability;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.AGE;
import static characterbuilder.character.attribute.AttributeType.PHYSICAL_DESCRIPTION;
import static characterbuilder.character.attribute.AttributeType.WEIGHT;
import characterbuilder.character.equipment.EquipmentCategory;
import java.util.Arrays;
import static java.util.stream.Collectors.joining;

public class BackgroundPage extends Page {

    public BackgroundPage(Character character) {
        super(character);
    }

    @Override
    public PageBuilder.Container getPage() {
        return builder.page()
            .with(name(), appearance(), description(), treasure(), abilities());
    }

    private PageBuilder.Component appearance() {
        return builder.borderedSection(30, 0, 70, 8)
            .with(builder.field("Age",
                character.getAttribute(AGE) + "/" + character.getLevel(), 10, 1))
            .with(builder.field("Height", character.getAttribute(AttributeType.HEIGHT), 10, 2))
            .with(builder.field("Weight", character.getAttribute(WEIGHT), 28, 2));
    }

    private PageBuilder.Component description() {
        return builder.borderedSection(0, 9, 50, 22)
            .with(builder.caption("Physical Description", 25, 20, PageBuilder.Align.CENTRE))
            .with(builder.writing(attrHTML(PHYSICAL_DESCRIPTION), 2, 2, 46, 16));
    }

    private PageBuilder.Component treasure() {
        return builder.borderedSection(50, 9, 50, 22)
            .with(builder.caption("Treasure", 25, 20, PageBuilder.Align.CENTRE))
            .with(builder.writing(treasureText(), 2, 2, 46, 16));
    }

    private String treasureText() {
        return html(
            elements("p", character.getInventory()
                .filter(eq -> eq.getCategory().equals(EquipmentCategory.TREASURE))
                .map(eq -> element("p", eq.toString()))));
    }

    private PageBuilder.Container abilities() {
        return builder.borderedSection(0, 31, 100, 69)
            .with(builder.caption("Ability Description", 50, 67, PageBuilder.Align.CENTRE))
            .with(builder.writing(abilityDescriptions(), 2, 2, 96, 65));
    }

    private String abilityDescriptions() {
        return html(
            element("table",
                character.getAllAttributes()
                    .filter(Arrays.asList(Ability.values())::contains)
                    .map(ab -> abilityRow((Ability) ab)).collect(joining())));
    }

    private String abilityRow(Ability ability) {
        return element("tr",
            element("th style='text-align:left'", nbsp(ability.toString()))
            + element("td", ability.getDescription(character).orElse("-")));
    }
}
