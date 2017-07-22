package characterbuilder.sheet;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Ability;
import characterbuilder.character.ability.Proficiency;
import characterbuilder.character.ability.Skill;
import characterbuilder.character.ability.Spell;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.*;
import characterbuilder.character.attribute.Race;
import characterbuilder.character.equipment.Equipment;
import characterbuilder.character.equipment.EquipmentCategory;
import characterbuilder.sheet.PageBuilder.Align;
import characterbuilder.sheet.PageBuilder.Component;
import characterbuilder.sheet.PageBuilder.Container;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import java.util.stream.IntStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

public class CharacterSheet extends JFrame {

    private final Character character;
    private final PageBuilder builder = new PageBuilder();
    private final JButton zoomInButton = new JButton("+");
    private final JButton zoomOutButton = new JButton("-");
    private final JButton previousButton = new JButton("<<");
    private final JButton nextButton = new JButton(">>");
    private final JButton printButton = new JButton("Print");
    private final JButton closeButton = new JButton("Close");
    private final ImagePanel imagePanel = new ImagePanel();
    private final EnumMap<Page, PageBuilder.Container> pages = new EnumMap(Page.class);

    private int zoom = 1;
    private Page page = Page.ATTRIBUTES;

    private enum Page {
        ATTRIBUTES,
        BACKGROUND,
        SPELLS;
    }

    private class ImagePanel extends JPanel {

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(builder.getWidth(zoom), builder.getHeight(zoom));
        }

        @Override
        public void paint(Graphics g) {
            pages.get(page).paint((Graphics2D) g, zoom);
        }
    }

    public CharacterSheet(Character character) {
        super("Character Sheet (" + character.getStringAttribute(NAME) + ")");
        this.character = character;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        addTools();
        JScrollPane scroller = new JScrollPane(imagePanel);
        add(scroller, BorderLayout.CENTER);
        buildPages();
        enableButtons();
        pack();
    }

    private void addTools() {
        JToolBar tools = new JToolBar();
        tools.add(zoomInButton);
        tools.add(zoomOutButton);
        tools.add(previousButton);
        tools.add(nextButton);
        tools.add(printButton);
        tools.add(closeButton);
        zoomInButton.addActionListener(ev -> changeZoom(+1));
        zoomOutButton.addActionListener(ev -> changeZoom(-1));
        previousButton.addActionListener(ev -> movePage(-1));
        nextButton.addActionListener(ev -> movePage(+1));
        printButton.addActionListener(ev -> print());
        closeButton.addActionListener(ev -> setVisible(false));
        add(tools, BorderLayout.NORTH);
    }

    private void changeZoom(int inc) {
        zoom += inc;
        zoom = Math.min(zoom, 8);
        zoom = Math.max(zoom, 1);
        repaint();
        enableButtons();
    }

    private void movePage(int inc) {
        int pageIndex = page.ordinal() + inc;
        if (pageIndex >= 0 && pageIndex < pages.size())
            page = Page.values()[pageIndex];
        repaint();
        enableButtons();
    }

    private void enableButtons() {
        zoomInButton.setEnabled(zoom < 8);
        zoomOutButton.setEnabled(zoom > 1);
        previousButton.setEnabled(page != Page.ATTRIBUTES);
        nextButton.setEnabled(page != Page.SPELLS && pages.containsKey(Page.values()[page.ordinal() + 1]));
    }

    private void buildPages() {
        pages.put(Page.ATTRIBUTES, attributePage());
        pages.put(Page.BACKGROUND, backgroundPage());
        if (Arrays.stream(Spell.values()).anyMatch(character::hasAttribute))
            pages.put(Page.SPELLS, spellPage());
    }

    private Container attributePage() {
        return builder.page()
            .with(name(), classAndRace(), abilityScores(), inspiration(), proficiencyBonus(),
                savingsThrows(), skills(), armourClass(), initiative(), speed(),
                hitPoints(), hitDice(), deathSaves(), attacks(), personality(),
                proficiencies(), equipment());
    }

    private Component name() {
        return builder.borderedSection(0, 0, 30, 8)
            .with(builder.caption("Name", 15, 6, Align.BOTTOM_CENTRE))
            .with(builder.value(attr(NAME), 15, 3, Align.CENTRE));
    }

    private Component classAndRace() {
        return builder.borderedSection(30, 0, 70, 8)
            .with(builder.field("Class & Level",
                attr(CHARACTER_CLASS) + "/" + character.getLevel(),
                10, 1))
            .with(builder.field("Race", attr(RACE)
                + " (" + attr(SEX) + ")",
                10, 2))
            .with(builder.field("Background", attr(BACKGROUND), 28, 2))
            .with(builder.field("Alignment", attr(ALIGNMENT), 10, 3))
            .with(builder.field("Experience Points", attr(EXPERIENCE_POINTS), 28, 3));
    }

    private Component abilityScores() {
        Container attributes = builder.shadedSection(1, 9, 14, 66);
        AttributeType.ABILITY_SCORES.stream()
            .forEach(attr -> attributes.with(abilityScore(attr)));
        return attributes;
    }

    private Component abilityScore(AttributeType abilityScore) {
        return builder.borderedSection(0, abilityScoreIndex(abilityScore) * 11, 14, 11)
            .with(builder.caption(abilityScore.toString(), 7, 2, Align.TOP_CENTRE))
            .with(builder.circle(7, 9, 3))
            .with(builder.value(bonusText(character.getModifier(abilityScore)), 7, 9, Align.CENTRE))
            .with(builder.value(character.getIntAttribute(abilityScore), 7, 5, Align.CENTRE));
    }

    private Component inspiration() {
        return builder.borderedSection(16, 9, 26, 4)
            .with(builder.circle(3, 2, 3))
            .with(builder.caption("Inspiration", 24, 2, Align.CENTRE_RIGHT));
    }

    private Component proficiencyBonus() {
        return builder.borderedSection(16, 13, 26, 4)
            .with(builder.circle(3, 2, 3))
            .with(builder.caption("Proficiency Bonus", 24, 2, Align.CENTRE_RIGHT))
            .with(builder.value(character.getProficiencyBonus(), 3, 2, Align.CENTRE));
    }

    private Component savingsThrows() {
        Container savingsThrows = builder.borderedSection(16, 17, 26, 17)
            .with(builder.caption("Savings Throws", 13, 15, Align.BOTTOM_CENTRE));
        AttributeType.ABILITY_SCORES.forEach(attr -> savingsThrows.with(savingsThrow(attr)));
        return savingsThrows;
    }

    private Component savingsThrow(AttributeType abilityScore) {
        return builder.field(abilityScore.toString(),
            bonusText(character.getSavingsThrowBonus(abilityScore)),
            10, abilityScoreIndex(abilityScore) + 1);
    }

    private int abilityScoreIndex(AttributeType abilityScore) {
        return AttributeType.ABILITY_SCORES.indexOf(abilityScore);
    }

    private Component skills() {
        Container skills = builder.borderedSection(16, 34, 26, 41)
            .with(builder.caption("Skills", 13, 39, Align.BOTTOM_CENTRE));
        List<Skill> characterSkills = Arrays.stream(Skill.values())
            .sorted(Comparator.comparing(p -> p.toString())).collect(Collectors.toList());
        IntStream.range(0, characterSkills.size())
            .forEach(i -> skills.with(builder.field(characterSkills.get(i).toString(),
            bonusText(character.getSkillBonus(characterSkills.get(i))),
            10, i + 1)));
        return skills;
    }

    private String bonusText(int bonus) {
        return String.format("%+d", bonus);
    }

    private Component armourClass() {
        return builder.borderedSection(42, 9, 10, 8)
            .with(builder.value(10, 5, 3, Align.CENTRE))
            .with(builder.caption("Armour\nClass", 5, 7, Align.BOTTOM_CENTRE));
    }

    private Component initiative() {
        return builder.borderedSection(52, 9, 10, 8)
            .with(builder.value(10, 5, 3, Align.CENTRE))
            .with(builder.caption("Initative", 5, 6, Align.BOTTOM_CENTRE));
    }

    private Component speed() {
        Race race = character.getAttribute(RACE);
        return builder.borderedSection(62, 9, 10, 8)
            .with(builder.value(race.getSpeed(), 5, 3, Align.CENTRE))
            .with(builder.caption("Speed", 5, 6, Align.BOTTOM_CENTRE));
    }

    private Component hitPoints() {
        return builder.blank(42, 17).with(
            builder.borderedSection(0, 10, 30, 10)
                .with(builder.caption("Temporary Hit Points", 15, 8, Align.BOTTOM_CENTRE)),
            builder.borderedSection(0, 0, 30, 14)
                .with(builder.field("Maximum Hit Points", character.getHitPoints(), 12, 1))
                .with(builder.caption("Current Hit Points", 15, 12, Align.BOTTOM_CENTRE)));
    }

    private Component hitDice() {
        return builder.borderedSection(42, 37, 14, 9)
            .with(builder.caption(character.getHitDice(), 7, 1, Align.TOP_CENTRE))
            .with(builder.caption("Hit Dice", 7, 7, Align.BOTTOM_CENTRE));
    }

    private Component deathSaves() {
        return builder.borderedSection(56, 37, 16, 9)
            .with(builder.field("Success", "", 5, 1))
            .with(builder.circle(10, 2, 1))
            .with(builder.circle(12, 2, 1))
            .with(builder.circle(14, 2, 1))
            .with(builder.field("Failure", "", 5, 2))
            .with(builder.circle(10, 4, 1))
            .with(builder.circle(12, 4, 1))
            .with(builder.circle(14, 4, 1))
            .with(builder.caption("Death Saves", 8, 7, Align.BOTTOM_CENTRE));
    }

    private Component attacks() {
        return builder.borderedSection(42, 46, 30, 29)
            .with(builder.writing(attackText(), 1, 1, 28, 28))
            .with(builder.caption("Attacks", 15, 27, Align.BOTTOM_CENTRE));
    }

    private String attackText() {
        List<String> rows = new ArrayList<>();
        rows.add(row("th style='text-align:left; height:5px'", "Attack", "Hit", "Damage"));
        character.getWeapons().flatMap(weapon -> weapon.getAttacks(character))
            .map(at -> row("td style='height:5px'", at.getName(), String.format("%+d", at.getBonus()), at.getDamage()))
            .forEach(rows::add);
        return table(rows);
    }

    private String table(List<String> rows) {
        StringBuilder text = new StringBuilder();
        text.append("<html>").append("<table>");
        rows.forEach(text::append);
        text.append("</table>").append("</html>");
        return text.toString();
    }

    private String row(String tag, String... cells) {
        StringBuilder text = new StringBuilder();
        text.append("<tr>");
        for (String cell : cells) {
            text.append("<").append(tag).append(">")
                .append(cell)
                .append("</").append(tag).append(">");
        }
        text.append("</tr>");
        return text.toString();
    }

    private Component personality() {
        return builder.shadedSection(72, 9, 28, 66)
            .with(builder.borderedSection(0, 0, 28, 17)
                .with(builder.caption("Personality Traits", 14, 15, Align.CENTRE))
                .with(builder.writing(attrHTML(TRAIT), 2, 2, 24, 13)))
            .with(builder.borderedSection(0, 17, 28, 16)
                .with(builder.caption("Ideals", 14, 14, Align.CENTRE))
                .with(builder.writing(attrHTML(IDEAL), 2, 2, 24, 12)))
            .with(builder.borderedSection(0, 33, 28, 16)
                .with(builder.caption("Bonds", 14, 14, Align.CENTRE))
                .with(builder.writing(attrHTML(BOND), 2, 2, 24, 12)))
            .with(builder.borderedSection(0, 49, 28, 17)
                .with(builder.caption("Flaws", 14, 15, Align.CENTRE))
                .with(builder.writing(attrHTML(FLAW), 2, 2, 24, 13)));
    }

    private Component proficiencies() {
        return builder.borderedSection(0, 75, 50, 25)
            .with(builder.writing(otherProficiencies(), 2, 1, 47, 24))
            .with(builder.caption("Other Proficiencies", 18, 23, Align.BOTTOM_CENTRE));
    }

    private String otherProficiencies() {
        StringBuilder text = new StringBuilder();
        text.append("<html>");
        List<AttributeType> types = new ArrayList<>();
        Proficiency.getTypes().forEach(types::add);
        types.add(AttributeType.EXPERTISE);
        character.getAllAttributes()
            .filter(attr -> types.contains(attr.getType()))
            .collect(Collectors.groupingByConcurrent(Attribute::getType))
            .forEach((at, al) -> addProficiencies(text, at, al));
        text.append("</html>");
        return text.toString();
    }

    private void addProficiencies(StringBuilder text, AttributeType type, List<Attribute> abilities) {
        text.append("<p style=\"text-indent: -10px; padding-left: 10px\">");
        text.append("<b>").append(nbsp(type)).append(" : </b>");
        if (type == AttributeType.WEAPON_PROFICIENCY) {
            addWeaponProficiencies(text, abilities);
        } else if (abilities.size() == Proficiency.allOfType(type).count()) {
            text.append("<em>All</em>");
        } else {
            text.append(abilities.stream().map(this::nbsp).collect(Collectors.joining(", ")));
        }
        text.append("</p>");
    }

    private void addWeaponProficiencies(StringBuilder text, List<Attribute> attributes) {
        text.append(attributes.stream().map(Attribute::toString).collect(joining(", ")));
    }

    private String nbsp(Object object) {
        return object.toString().replace(" ", "&nbsp;");
    }

    private Component equipment() {
        return builder.borderedSection(50, 75, 50, 25)
            .with(builder.writing(equipmentDescription(), 2, 1, 48, 24))
            .with(builder.caption("Equipment", 18, 23, Align.BOTTOM_CENTRE));
    }

    private String equipmentDescription() {
        StringBuilder text = new StringBuilder();
        text.append("<html>");
        text.append(character.getInventory()
            .filter(eq -> !eq.getCategory().equals(EquipmentCategory.TREASURE))
            .map(Equipment::toString).collect(Collectors.joining(", ")));
        text.append("</html>");
        return text.toString();
    }

    private Container backgroundPage() {
        return builder.page()
            .with(name(), physicalAppearance(), physicalDescription(), treasure(),
                abilityDetails());
    }

    private Component physicalAppearance() {
        return builder.borderedSection(30, 0, 70, 8)
            .with(builder.field("Age",
                character.getAttribute(AGE) + "/" + character.getLevel(),
                10, 1))
            .with(builder.field("Height", character.getAttribute(AttributeType.HEIGHT),
                10, 2))
            .with(builder.field("Weight", character.getAttribute(WEIGHT), 28, 2));
    }

    private Component physicalDescription() {
        return builder.borderedSection(0, 9, 50, 22)
            .with(builder.caption("Physical Description", 25, 20, Align.CENTRE))
            .with(builder.writing(attrHTML(PHYSICAL_DESCRIPTION), 2, 2, 46, 16));
    }

    private Component treasure() {
        return builder.borderedSection(50, 9, 50, 22)
            .with(builder.caption("Treasure", 25, 20, Align.CENTRE))
            .with(builder.writing(treasureText(), 2, 2, 46, 16));
    }

    private String treasureText() {
        StringBuilder text = new StringBuilder();
        text.append("<html>");
        character.getInventory()
            .filter(eq -> eq.getCategory().equals(EquipmentCategory.TREASURE))
            .forEach(eq -> text.append("<p>").append(eq.toString()).append("</p>"));
        text.append("</html>");
        return text.toString();
    }

    private Container abilityDetails() {
        return builder.borderedSection(0, 31, 100, 69)
            .with(builder.caption("Ability Description", 50, 67, Align.CENTRE))
            .with(builder.writing(abilityDescriptions(), 2, 2, 96, 65));
    }

    private String abilityDescriptions() {
        return table(character.getAllAttributes()
            .filter(Arrays.asList(Ability.values())::contains)
            .map(ab -> row("td", ab.toString(), ((Ability) ab).getDescription(character)))
            .collect(toList()));
    }

    private Container spellPage() {
        return builder.page()
            .with(builder.shadedSection(0, 0, 100, 100))
            .with(builder.borderedSection(1, 1, 98, 98)
                .with(builder.caption("Spell", 11, 2, Align.CENTRE))
                .with(builder.verticalLine(22, 100))
                .with(builder.caption("Casting", 28, 2, Align.CENTRE))
                .with(builder.caption("Time", 28, 3, Align.CENTRE))
                .with(builder.verticalLine(33, 100))
                .with(builder.caption("Components", 39, 2, Align.CENTRE))
                .with(builder.verticalLine(44, 100))
                .with(builder.caption("Range", 50, 2, Align.CENTRE))
                .with(builder.verticalLine(55, 100))
                .with(builder.caption("Area", 61, 2, Align.CENTRE))
                .with(builder.verticalLine(66, 100))
                .with(builder.caption("Duration", 72, 2, Align.CENTRE))
                .with(builder.verticalLine(78, 100))
                .with(builder.caption("Effect", 86, 2, Align.CENTRE))
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

    private void print() {
        try {
            PrinterJob print = PrinterJob.getPrinterJob();
            print.setPrintable((Graphics g, PageFormat pageFormat, int pageIndex) -> {
                if (pageIndex > 0)
                    return Printable.NO_SUCH_PAGE;
                pages.get(Page.ATTRIBUTES).paint((Graphics2D) g, 1);
                return Printable.PAGE_EXISTS;
            });
            if (print.printDialog())
                print.print();
        } catch (PrinterException ex) {
            Logger.getLogger(CharacterSheet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String attr(AttributeType attr) {
        return character.getStringAttribute(attr);
    }

    private String attrHTML(AttributeType type) {
        return character.getAllAttributes()
            .filter(type::isTypeOfAttribute)
            .map(Attribute::toString)
            .collect(joining("<br>", "<html>", "</html>"));
    }
}
