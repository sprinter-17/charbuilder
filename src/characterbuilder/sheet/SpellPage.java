package characterbuilder.sheet;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.spell.Spell;
import characterbuilder.character.spell.SpellCasting;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

public class SpellPage extends Page {

    private final boolean firstSpellPage;
    private final List<Spell> spells;

    public SpellPage(Character character, boolean firstSpellPage, Stream<Spell> spells) {
        super(character);
        this.firstSpellPage = firstSpellPage;
        this.spells = spells.collect(toList());
    }

    @Override
    public PageBuilder.Container getPage() {
        PageBuilder.Container page = builder.page();
        if (firstSpellPage) {
            page.with(ability(), saveDC(), attackBonus(), spellSlots(), preparedSpells(),
                spellDetails(20));
        } else {
            page.with(spellDetails(0));
        }
        return page;
    }

    private PageBuilder.Component ability() {
        return builder.borderedSection(0, 0, 34, 6)
            .with(builder.caption("Spellcasting Ability", 17, 4, PageBuilder.Align.CENTRE))
            .with(builder.value(casting().getAbilityScore().toString(),
                17, 2, PageBuilder.Align.CENTRE));
    }

    private PageBuilder.Component saveDC() {
        int saveDC = casting().getSaveDC(character);
        return builder.borderedSection(34, 0, 22, 6)
            .with(builder.caption("Spell Save DC", 11, 4, PageBuilder.Align.CENTRE))
            .with(builder.value(saveDC, 11, 2, PageBuilder.Align.CENTRE));
    }

    private PageBuilder.Component attackBonus() {
        int attackBonus = casting().getModifier(character);
        return builder.borderedSection(56, 0, 22, 6)
            .with(builder.caption("Spell Attack Bonus", 11, 4, PageBuilder.Align.CENTRE))
            .with(builder.value(attackBonus, 11, 2, PageBuilder.Align.CENTRE));
    }

    private PageBuilder.Component preparedSpells() {
        String preparedSpells = casting().getPreparedSpellText(character);
        return builder.borderedSection(78, 0, 22, 6)
            .with(builder.caption("Prepared Spells", 11, 4, PageBuilder.Align.CENTRE))
            .with(builder.value(preparedSpells, 11, 2, PageBuilder.Align.CENTRE));
    }

    private PageBuilder.Component spellSlots() {
        final int rows = 3;
        PageBuilder.Container spellSlotPanel = builder.borderedSection(0, 6, 100, 14)
            .with(builder.caption("Spell Slots", 50, 12, PageBuilder.Align.CENTRE));
        for (int l = 1; l <= casting().getMaxSlot(); l++) {
            int yp = (l - 1) % rows * 3 + 3;
            int xp = 30 * ((l - 1) / rows) + 5;
            spellSlotPanel
                .with(builder.caption("Level " + l, xp, yp, PageBuilder.Align.CENTRE_LEFT));
            for (int s = 0; s < casting().getSlotsAtLevel(l); s++) {
                spellSlotPanel.with(builder.circle(xp + 12 + s * 4, yp, 2));
            }
        }
        return spellSlotPanel;
    }

    private PageBuilder.Component spellDetails(int yp) {
        return builder.borderedSection(0, yp, 100, 100 - yp)
            .with(builder.caption("Casting Time", 32, 2, PageBuilder.Align.CENTRE))
            .with(builder.caption("Components", 48, 2, PageBuilder.Align.CENTRE))
            .with(builder.caption("Range", 64, 2, PageBuilder.Align.CENTRE))
            .with(builder.caption("Area", 76, 2, PageBuilder.Align.CENTRE))
            .with(builder.caption("Duration", 90, 2, PageBuilder.Align.CENTRE))
            .with(builder.writing(allSpellText(), 2, 3, 96, 95 - yp));
    }

    private String allSpellText() {
        StringBuilder text = new StringBuilder();
        text.append("<html>");
        spells.stream()
            .collect(Collectors.groupingBy(Spell::getLevel))
            .forEach((level, spellList) -> text.append(spellLevelText(level, spellList)));
        text.append("</html>");
        return text.toString();
    }

    private String spellLevelText(int level, List<Spell> spells) {
        StringBuilder text = new StringBuilder();
        text.append("<h2 style='margin-bottom: 0px; margin-top: 3px'>");
        text.append(level > 0 ? "Level " + level + " Spells" : "Cantrips");
        text.append("</h5>");
        text.append("<table style=\"width:385px; padding-left:10px; border-spacing:0px\">");
        spells.forEach(spell -> spellText(text, spell, spells.indexOf(spell) % 2 == 0));
        text.append("</table>");
        return text.toString();
    }

    private void spellText(StringBuilder text, Spell spell, boolean even) {
        String colour = even ? "#f0f0f0" : "#ffffff";
        String tr = String.format("<tr style='background-color:%s'>", colour);
        text.append(tr);
        spellValue(text, "&#x25A2;", 5);
        spellName(text, spell.toString(), 20);
        spellValue(text, spell.getCastingTime(), 12);
        spellValue(text, spell.getComponents(), 12);
        spellValue(text, spell.getRange(), 12);
        spellValue(text, spell.getArea(), 12);
        spellValue(text, spell.getDuration(), 12);
        text.append("</tr>");
        text.append(tr)
            .append("<td/><td colspan='6'><em>")
            .append(spell.getEffect(character))
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

    private SpellCasting casting() {
        return character.getAttribute(AttributeType.SPELLCASTING);
    }

}
