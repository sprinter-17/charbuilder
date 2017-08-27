package characterbuilder.sheet;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.AGE;
import static characterbuilder.character.attribute.AttributeType.PERSONAL_HISTORY;
import static characterbuilder.character.attribute.AttributeType.PHYSICAL_DESCRIPTION;
import static characterbuilder.character.attribute.AttributeType.WEIGHT;
import characterbuilder.character.equipment.Equipment;
import characterbuilder.character.equipment.EquipmentCategory;
import characterbuilder.character.spell.LearntSpell;
import characterbuilder.character.spell.Spell;
import characterbuilder.character.spell.SpellAbility;
import static characterbuilder.sheet.EquipmentPlacement.BACK;
import static characterbuilder.sheet.EquipmentPlacement.TREASURE;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

public class BackgroundPage extends Page {

    public static Stream<PageBuilder.Container> getPages(Character character) {
        BackgroundPage backgroundPage = new BackgroundPage(character);
        return backgroundPage.getPages();
    }

    private BackgroundPage(Character character) {
        super(character);
    }

    @Override
    public Stream<PageBuilder.Container> getPages() {
        List<PageBuilder.Container> pages = new ArrayList<>();
        pages.add(builder.page().with(name(), appearance(), description(), history(),
            gear(), treasure()));
        addAbilities(pages);
        return pages.stream();
    }

    private PageBuilder.Component appearance() {
        return builder.borderedSection(30, 0, 70, 8)
            .with(builder.field("Age",
                character.getAttribute(AGE) + "/" + character.getLevel(), 10, 1))
            .with(builder.field("Height", character.getAttribute(AttributeType.HEIGHT), 10, 2))
            .with(builder.field("Weight", character.getAttribute(WEIGHT), 28, 2));
    }

    private PageBuilder.Component description() {
        return builder.borderedSection(0, 8, 50, 18)
            .with(builder.caption("Physical Description", 25, 16, PageBuilder.Align.CENTRE))
            .with(builder.writing(attrHTML(PHYSICAL_DESCRIPTION), 2, 2, 46, 16));
    }

    private PageBuilder.Component history() {
        return builder.borderedSection(50, 8, 50, 18)
            .with(builder.caption("Personal History", 25, 16, PageBuilder.Align.CENTRE))
            .with(builder.writing(attrHTML(PERSONAL_HISTORY), 2, 2, 46, 16));
    }

    private PageBuilder.Component gear() {
        return builder.borderedSection(0, 26, 50, 18)
            .with(builder.caption("Gear", 25, 16, PageBuilder.Align.CENTRE))
            .with(builder.writing(gearText(), 2, 2, 46, 16));
    }

    private String gearText() {
        return html(character.getInventory()
            .filter(BACK::isPlaceFor)
            .map(Equipment::toString)
            .collect(joining(", ")));
    }

    private PageBuilder.Component treasure() {
        return builder.borderedSection(50, 26, 50, 18)
            .with(builder.caption("Treasure", 25, 16, PageBuilder.Align.CENTRE))
            .with(builder.writing(treasureText(), 2, 2, 46, 16));
    }

    private String treasureText() {
        return html(character.getInventory()
            .filter(TREASURE::isPlaceFor)
            .map(this::treasureText)
            .collect(joining("<br>")));
    }

    private String treasureText(Equipment treasure) {
        if (treasure.getCategory().equals(EquipmentCategory.CUSTOM_TREASURE))
            return treasure.toString() + " (" + treasure.getValue().toString() + ")";
        else
            return treasure.toString();
    }

    private void addAbilities(List<PageBuilder.Container> pages) {
        List<Attribute> abilities = new ArrayList<>(getAbilities().collect(toList()));
        List<LearntSpell> spellAbilities = new ArrayList<>(getSpells().collect(toList()));
        TextSectionBuilder sectionBuilder = new TextSectionBuilder(character, "Abilities", 100, 56);
        sectionBuilder.addAbilities(abilities);
        if (abilities.isEmpty())
            sectionBuilder.addSpells(spellAbilities);
        pages.get(0).with(sectionBuilder.getSection(0, 44));
        while (!abilities.isEmpty() || !spellAbilities.isEmpty()) {
            sectionBuilder.createNewSection(100, 100);
            if (!abilities.isEmpty())
                sectionBuilder.addAbilities(abilities);
            if (abilities.isEmpty())
                sectionBuilder.addSpells(spellAbilities);
            pages.add(builder.page().with(sectionBuilder.getSection(0, 0)));
        }
    }

    private Stream<Attribute> getAbilities() {
        return character.getAllAttributes()
            .filter(AttributePlacement.DETAIL::isPlacementFor)
            .sorted(Comparator.comparing(Attribute::getType));
    }

    private Stream<LearntSpell> getSpells() {
        return character.getAllAttributes()
            .filter(attr -> attr.hasType(AttributeType.SPELL_ABILITY))
            .map(attr -> ((SpellAbility) attr).getSpell())
            .sorted(Spell.ORDER)
            .map(sp -> new LearntSpell(sp, true));
    }
}
