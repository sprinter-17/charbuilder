package characterbuilder.sheet;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.spell.LearntSpell;
import static characterbuilder.sheet.Page.element;
import static characterbuilder.sheet.Page.html;
import static characterbuilder.sheet.Page.nbsp;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;

public class TextSectionBuilder {

    private final PageBuilder builder = new PageBuilder();
    private final Character character;
    private final Optional<String> caption;

    private int wp;
    private int hp;
    private String content;

    public TextSectionBuilder(Character character, String caption, int wp, int hp) {
        this.character = character;
        this.caption = Optional.ofNullable(caption);
        createNewSection(wp, hp);
    }

    public final void createNewSection(int wp, int hp) {
        this.content = "";
        this.wp = wp;
        this.hp = hp;
    }

    public PageBuilder.Container getSection(int xp, int yp) {
        PageBuilder.Container section = builder.borderedSection(xp, yp, wp, hp)
            .with(builder.writing(html(content), 2, 2, wp - 4, hp - 4));
        if (caption.isPresent())
            section.with(builder.caption(caption.get(), wp / 2, hp - 2, PageBuilder.Align.CENTRE));
        return section;
    }

    public void addAbilities(List<Attribute> abilities) {
        int count = 0;
        while (count < abilities.size()
            && builder
                .fits(html(content
                    + abilityDescriptions(abilities.subList(0, count + 1))), wp - 4, hp - 4)) {
            count++;
        }
        content += abilityDescriptions(abilities.subList(0, count));
        abilities.subList(0, count).clear();
    }

    private String abilityDescriptions(List<Attribute> abilities) {
        return element("table style=\"width:385px; padding-left:10px; border-spacing:0px\"",
            abilities.stream()
                .filter(AttributePlacement.DETAIL::isPlacementFor)
                .sorted(Comparator.comparing(Attribute::getType))
                .map(ab -> abilityRow(ab, abilities.indexOf(ab) % 2 == 0))
                .collect(joining()));
    }

    private String abilityRow(Attribute ability, boolean even) {
        String colour = even ? "#f0f0f0" : "#ffffff";
        return element(String.format("tr style='background-color:%s'", colour),
            element("th style='text-align:left'", nbsp(ability.toString()))
            + element("td", ability.getDescription(character).collect(joining("<br>"))));
    }

    public void addSpells(List<LearntSpell> spells) {
        int count = 0;
        while (count < spells.size()
            && builder.fits(html(content
                + spellDescription(spells.subList(0, count + 1))), wp, hp)) {
            count++;
        }
        content += spellDescription(spells.subList(0, count));
        spells.subList(0, count).clear();
    }

    private String spellDescription(List<LearntSpell> spells) {
        return spells.stream().collect(groupingBy(LearntSpell::getLevel))
            .entrySet().stream()
            .map(e -> spellLevelText(e.getKey(), e.getValue()))
            .collect(joining());
    }

    private String spellLevelText(int level, List<LearntSpell> spells) {
        StringBuilder text = new StringBuilder();
        text.append("<h4 style='margin-bottom: -5px; margin-top: 10px'>");
        text.append(level > 0 ? "Level " + level + " Spells" : "Cantrips");
        text.append("</h4>");
        text.append("<table style=\"width:385px; padding-left:10px; border-spacing:0px\">");
        text.append("<tr><td/><th>Name</th><th>Casting Time</th><th>Components</th>"
            + "<th>Range</th><th>Area</th><th>Duration</th></tr>");
        spells.forEach(spell -> spellText(text, spell, spells.indexOf(spell) % 2 == 0));
        text.append("</table>");
        return text.toString();
    }

    private void spellText(StringBuilder text, LearntSpell spell, boolean even) {
        String colour = even ? "#f0f0f0" : "#ffffff";
        String tr = String.format("<tr style='background-color:%s'>", colour);
        text.append(tr);
        spellValue(text, spell.isPrepared() ? "&#x25A3;" : "&#x25A2;", 5);
        spellName(text, spell.toString(), 20);
        spellValue(text, spell.getSpell().getCastingTime(), 12);
        spellValue(text, spell.getSpell().getComponents(), 12);
        spellValue(text, spell.getSpell().getRange(), 12);
        spellValue(text, spell.getSpell().getArea(), 12);
        spellValue(text, spell.getSpell().getDuration(), 12);
        text.append("</tr>");
        text.append(tr)
            .append("<td/><td colspan='6'><em>")
            .append(spell.getSpell().getEffect(character))
            .append("</em></td></tr>");
    }

    private void spellName(StringBuilder text, String value, int size) {
        spellCell(text, value, "th", "left", size);
    }

    private void spellValue(StringBuilder text, String value, int size) {
        spellCell(text, value, "td", "center", size);
    }

    private void spellCell(StringBuilder text, String value, String tag, String align, int size) {
        text.append(String
            .format("<%s style=\"text-align:%s; height:5px; width:%d%%; padding:-15px\">",
                tag, align, size));
        text.append(value);
        text.append(String.format("</%s>", tag));
    }
}
