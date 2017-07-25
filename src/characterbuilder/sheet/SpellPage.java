package characterbuilder.sheet;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Spell;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SpellPage extends Page {

    public SpellPage(Character character) {
        super(character);
    }

    @Override
    public PageBuilder.Container getPage() {
        return builder.page()
            .with(builder.shadedSection(0, 0, 100, 100))
            .with(builder.borderedSection(1, 1, 98, 98)
                .with(builder.caption("Spell", 11, 2, PageBuilder.Align.CENTRE))
                .with(builder.verticalLine(22, 100))
                .with(builder.caption("Casting", 28, 2, PageBuilder.Align.CENTRE))
                .with(builder.caption("Time", 28, 3, PageBuilder.Align.CENTRE))
                .with(builder.verticalLine(33, 100))
                .with(builder.caption("Components", 39, 2, PageBuilder.Align.CENTRE))
                .with(builder.verticalLine(44, 100))
                .with(builder.caption("Range", 50, 2, PageBuilder.Align.CENTRE))
                .with(builder.verticalLine(55, 100))
                .with(builder.caption("Area", 61, 2, PageBuilder.Align.CENTRE))
                .with(builder.verticalLine(66, 100))
                .with(builder.caption("Duration", 72, 2, PageBuilder.Align.CENTRE))
                .with(builder.verticalLine(78, 100))
                .with(builder.caption("Effect", 86, 2, PageBuilder.Align.CENTRE))
                .with(builder.writing(allSpellText(), 2, 3, 96, 95)));
    }

    private String allSpellText() {
        StringBuilder text = new StringBuilder();
        text.append("<html>");
        Map<Integer, List<Spell>> spells = Arrays.stream(Spell.values())
            .filter(character::hasAttribute)
            .collect(Collectors.groupingBy(Spell::getLevel));
        spells.forEach((level, spellList) -> spellLevelText(text, level, spellList));
        text.append("</html>");
        return text.toString();
    }

    private void spellLevelText(StringBuilder text, int level, List<Spell> spells) {
        text.append("<h5 style='margin-bottom: 0px; margin-top: 3px'>");
        text.append(level > 0 ? "Level " + level : "Cantrips");
        text.append("</h5>");
        text.append("<table style=\"width:385px; padding-left:5px\">");
        spells.forEach(spell -> spellText(text, spell));
        text.append("</table>");
    }

    private void spellText(StringBuilder text, Spell spell) {
        text.append("<tr>");
        spellName(text, spell.toString(), 20);
        spellValue(text, spell.getCastingTime(), 12);
        spellValue(text, spell.getComponents(), 12);
        spellValue(text, spell.getRange(), 12);
        spellValue(text, spell.getArea(), 12);
        spellValue(text, spell.getDuration(), 12);
        spellValue(text, spell.getEffect(character), 20);
        text.append("</tr>");
    }

    private void spellName(StringBuilder text, String value, int size) {
        spellCell(text, value, "th", "left", size);
    }

    private void spellValue(StringBuilder text, String value, int size) {
        spellCell(text, value, "td", "center", size);
    }

    private void spellCell(StringBuilder text, String value, String tag, String align, int size) {
        text.append(String.format("<%s style=\"text-align:%s; height:5px; width:%d%%; padding:-15px\">",
            tag, align, size));
        text.append(value);
        text.append(String.format("</%s>", tag));
    }

}
