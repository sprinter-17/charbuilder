package characterbuilder.sheet;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Proficiency;
import characterbuilder.character.ability.Skill;
import characterbuilder.character.attribute.AbilityScore;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.*;
import characterbuilder.character.attribute.StringAttribute;
import characterbuilder.character.attribute.Weight;
import characterbuilder.character.equipment.Armour;
import characterbuilder.character.equipment.Equipment;
import characterbuilder.character.equipment.EquipmentCategory;
import static characterbuilder.sheet.EquipmentPlacement.FRONT;
import static characterbuilder.sheet.PageBuilder.Align.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.joining;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainPage extends Page {

    public static Stream<PageBuilder.Container> getPages(Character character) {
        MainPage mainPage = new MainPage(character);
        return mainPage.getPages();
    }

    private MainPage(Character character) {
        super(character);
    }

    @Override
    public Stream<PageBuilder.Container> getPages() {
        PageBuilder.Container page = builder.page()
            .with(name(), classAndRace(), abilityScores(), inspiration(), proficiencyBonus(),
                savingsThrows(), skills(), armourClass(), initiative(), speed(),
                hitPoints(), hitDice(), deathSaves(), attacks(), personality(),
                proficiencies(), equipment());
        return Stream.of(page);
    }

    private PageBuilder.Component classAndRace() {
        return builder.borderedSection(28, 0, 72, 8)
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

    private PageBuilder.Component abilityScores() {
        PageBuilder.Container attributes = builder.shadedSection(1, 8, 14, 66);
        AbilityScore.SCORES.stream()
            .forEach(attr -> attributes.with(abilityScore(attr)));
        return attributes;
    }

    private PageBuilder.Component abilityScore(AttributeType abilityScore) {
        return builder.borderedSection(0, abilityScoreIndex(abilityScore) * 11, 14, 11)
            .with(builder.caption(abilityScore.toString(), 7, 2, TOP_CENTRE))
            .with(builder.circle(7, 9, 3))
            .with(builder.value(bonusText(character.getModifier(abilityScore)), 7, 9, CENTRE))
            .with(builder.value(character.getIntAttribute(abilityScore), 7, 5, CENTRE));
    }

    private PageBuilder.Component inspiration() {
        return builder.borderedSection(15, 8, 26, 4)
            .with(builder.circle(3, 2, 3))
            .with(builder.caption("Inspiration", 24, 2, CENTRE_RIGHT));
    }

    private PageBuilder.Component proficiencyBonus() {
        return builder.borderedSection(15, 12, 26, 4)
            .with(builder.circle(3, 2, 3))
            .with(builder.caption("Proficiency Bonus", 24, 2, CENTRE_RIGHT))
            .with(builder.value(character.getProficiencyBonus(), 3, 2, CENTRE));
    }

    private PageBuilder.Component savingsThrows() {
        PageBuilder.Container savingsThrows = builder.borderedSection(15, 16, 26, 17)
            .with(builder.caption("Savings Throws", 13, 15, BOTTOM_CENTRE));
        AbilityScore.SCORES.forEach(attr -> savingsThrows.with(savingsThrow(attr)));
        return savingsThrows;
    }

    private PageBuilder.Component savingsThrow(AttributeType abilityScore) {
        return builder.field(abilityScore.toString(),
            bonusText(character.getSavingsThrowBonus(abilityScore)),
            10, abilityScoreIndex(abilityScore) + 1);
    }

    private int abilityScoreIndex(AttributeType abilityScore) {
        return AbilityScore.SCORES.indexOf(abilityScore);
    }

    private PageBuilder.Component skills() {
        PageBuilder.Container skills = builder.borderedSection(15, 33, 26, 41)
            .with(builder.caption("Skills", 13, 39, BOTTOM_CENTRE));
        List<Skill> characterSkills = Arrays.stream(Skill.values())
            .sorted(Comparator.comparing(p -> p.toString())).collect(Collectors.toList());
        IntStream.range(0, characterSkills.size())
            .forEach(i -> skills.with(builder.field(characterSkills.get(i).toString(),
            bonusText(characterSkills.get(i).getBonus(character)), 10, i + 1)));
        return skills;
    }

    private String bonusText(int bonus) {
        return String.format("%+d", bonus);
    }

    private PageBuilder.Component armourClass() {
        return builder.borderedSection(41, 8, 10, 8)
            .with(builder.value(Armour.getArmourClass(character), 5, 3, CENTRE))
            .with(builder.caption("Armour\nClass", 5, 7, BOTTOM_CENTRE));
    }

    private PageBuilder.Component initiative() {
        return builder.borderedSection(51, 8, 10, 8)
            .with(builder.value(character.getModifier(DEXTERITY), 5, 3, CENTRE))
            .with(builder.caption("Initative", 5, 6, BOTTOM_CENTRE));
    }

    private PageBuilder.Component speed() {
        return builder.borderedSection(61, 8, 10, 8)
            .with(builder.value(character.getSpeed(), 5, 3, CENTRE))
            .with(builder.caption("Speed", 5, 6, BOTTOM_CENTRE));
    }

    private PageBuilder.Component hitPoints() {
        return builder.blank(41, 16).with(
            builder.borderedSection(0, 10, 30, 10)
                .with(builder.caption("Temporary Hit Points", 15, 8, BOTTOM_CENTRE)),
            builder.borderedSection(0, 0, 30, 14)
                .with(builder.field("Maximum Hit Points", character.getHitPoints(), 12, 1))
                .with(builder.caption("Current Hit Points", 15, 12, BOTTOM_CENTRE)));
    }

    private PageBuilder.Component hitDice() {
        return builder.borderedSection(41, 36, 14, 9)
            .with(builder.caption(character.getHitDice(), 7, 1, TOP_CENTRE))
            .with(builder.caption("Hit Dice", 7, 7, BOTTOM_CENTRE));
    }

    private PageBuilder.Component deathSaves() {
        return builder.borderedSection(55, 36, 16, 9)
            .with(builder.field("Success", "", 5, 1))
            .with(builder.circle(10, 2, 1))
            .with(builder.circle(12, 2, 1))
            .with(builder.circle(14, 2, 1))
            .with(builder.field("Failure", "", 5, 2))
            .with(builder.circle(10, 4, 1))
            .with(builder.circle(12, 4, 1))
            .with(builder.circle(14, 4, 1))
            .with(builder.caption("Death Saves", 8, 7, BOTTOM_CENTRE));
    }

    private PageBuilder.Component attacks() {
        return builder.borderedSection(41, 45, 59, 29)
            .with(builder.writing(attackText(), 2, 2, 56, 27))
            .with(builder.caption("Attacks", 29, 27, BOTTOM_CENTRE));
    }

    private String attackText() {
        List<String> rows = new ArrayList<>();
        rows
            .add(row("th style='padding:0px;text-align:left'",
                "Attack", "Range", "Hit", "Damage", "Type"));
        character.getAttacks().map(at -> row("td style='padding:0px'", at.getName()
            + at.getDescription().map(d -> " " + d).orElse(""),
            at.getRange(),
            String.format("%+d", at.getBonus()),
            at.getDamage(), at.getType().toString()))
            .forEach(rows::add);
        return table(rows, 220);
    }

    private PageBuilder.Component personality() {
        return builder.borderedSection(71, 8, 29, 37)
            .with(builder.caption("Personality", 14, 35, CENTRE))
            .with(builder.writing(
                html(PERSONALITY.stream().map(this::personalityElement)
                    .collect(joining(" "))), 2, 2, 24, 33));
    }

    private String personalityElement(AttributeType type) {
        if (character.hasAttribute(type)) {
            return element("p", element("b", type.toString() + "s") + ": "
                + character.getAttributes(type, StringAttribute.class)
                    .map(StringAttribute::toString)
                    .collect(joining(" ")));
        } else {
            return "";
        }
    }

    private PageBuilder.Component proficiencies() {
        return builder.borderedSection(0, 74, 41, 26)
            .with(builder.writing(otherProficiencies(), 2, 1, 38, 24))
            .with(builder.caption("Proficiencies", 21, 23, BOTTOM_CENTRE));
    }

    private String otherProficiencies() {
        StringBuilder text = new StringBuilder();
        text.append("<html>");
        character.getAllAttributes()
            .filter(AttributePlacement.SUMMARY::isPlacementFor)
            .collect(Collectors.groupingByConcurrent(Attribute::getType))
            .forEach((at, al) -> addProficiencies(text, at, al));
        text.append("</html>");
        return text.toString();
    }

    private void addProficiencies(StringBuilder text, AttributeType type,
        List<Attribute> abilities) {
        text.append("<p style=\"text-indent: -10px; padding-left: 10px\">");
        text.append("<b>").append(nbsp(type)).append(" : </b>");
        if (type == AttributeType.WEAPON_PROFICIENCY) {
            addWeaponProficiencies(text, abilities);
        } else if (abilities.size() == Proficiency.allOfType(type).count()) {
            text.append("<em>All</em>");
        } else {
            text.append(abilities.stream().map(Page::nbsp).collect(Collectors.joining(", ")));
        }
        text.append("</p>");
    }

    private void addWeaponProficiencies(StringBuilder text, List<Attribute> attributes) {
        text.append(attributes.stream().map(Attribute::toString).collect(joining(", ")));
    }

    private PageBuilder.Component equipment() {
        return builder.borderedSection(41, 74, 59, 26)
            .with(builder.writing(equipmentDescription(), 2, 1, 54, 20))
            .with(builder.field("Total Weight",
                character.getInventoryWeight().toStringInPounds(), 8, 10))
            .with(builder.field("Encumbrance", encumbrance(), 18, 10))
            .with(builder.caption("Equipment", 25, 23, BOTTOM_CENTRE));
    }

    private String equipmentDescription() {
        return html(character.getInventory()
            .filter(eq -> EquipmentPlacement.forCategory(eq.getCategory()) == FRONT)
            .collect(Collectors.groupingBy(Equipment::getCategory))
            .entrySet().stream().map(this::equipmentCategoryDescription)
            .collect(Collectors.joining()));
    }

    private String equipmentCategoryDescription(Map.Entry<EquipmentCategory, List<Equipment>> entry) {
        return "<p style=\"text-indent: -10px; padding-left: 10px\">"
            + "<b>" + entry.getKey().toString() + "</b> : "
            + entry.getValue().stream().map(Page::nbsp).collect(Collectors.joining(", "))
            + "</p>";
    }

    private String encumbrance() {
        Weight capacity = character.getCarryingCapacity().divide(3);
        return String.format("%s / %s / %s", capacity.toStringInPounds(),
            capacity.times(2).toStringInPounds(), capacity.times(3).toStringInPounds());
    }
}
