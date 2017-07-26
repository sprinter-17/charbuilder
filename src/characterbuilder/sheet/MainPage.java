package characterbuilder.sheet;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Proficiency;
import characterbuilder.character.ability.Skill;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.*;
import characterbuilder.character.attribute.Race;
import characterbuilder.character.equipment.Armour;
import characterbuilder.character.equipment.Equipment;
import characterbuilder.character.equipment.EquipmentCategory;
import static characterbuilder.sheet.PageBuilder.Align.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.joining;
import java.util.stream.IntStream;

public class MainPage extends Page {

    public MainPage(Character character) {
        super(character);
    }

    @Override
    public PageBuilder.Container getPage() {
        return builder.page()
            .with(name(), classAndRace(), abilityScores(), inspiration(), proficiencyBonus(),
                savingsThrows(), skills(), armourClass(), initiative(), speed(),
                hitPoints(), hitDice(), deathSaves(), attacks(), personality(),
                proficiencies(), equipment());
    }

    private PageBuilder.Component classAndRace() {
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

    private PageBuilder.Component abilityScores() {
        PageBuilder.Container attributes = builder.shadedSection(1, 9, 14, 66);
        AttributeType.ABILITY_SCORES.stream()
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
        return builder.borderedSection(16, 9, 26, 4)
            .with(builder.circle(3, 2, 3))
            .with(builder.caption("Inspiration", 24, 2, CENTRE_RIGHT));
    }

    private PageBuilder.Component proficiencyBonus() {
        return builder.borderedSection(16, 13, 26, 4)
            .with(builder.circle(3, 2, 3))
            .with(builder.caption("Proficiency Bonus", 24, 2, CENTRE_RIGHT))
            .with(builder.value(character.getProficiencyBonus(), 3, 2, CENTRE));
    }

    private PageBuilder.Component savingsThrows() {
        PageBuilder.Container savingsThrows = builder.borderedSection(16, 17, 26, 17)
            .with(builder.caption("Savings Throws", 13, 15, BOTTOM_CENTRE));
        AttributeType.ABILITY_SCORES.forEach(attr -> savingsThrows.with(savingsThrow(attr)));
        return savingsThrows;
    }

    private PageBuilder.Component savingsThrow(AttributeType abilityScore) {
        return builder.field(abilityScore.toString(),
            bonusText(character.getSavingsThrowBonus(abilityScore)),
            10, abilityScoreIndex(abilityScore) + 1);
    }

    private int abilityScoreIndex(AttributeType abilityScore) {
        return AttributeType.ABILITY_SCORES.indexOf(abilityScore);
    }

    private PageBuilder.Component skills() {
        PageBuilder.Container skills = builder.borderedSection(16, 34, 26, 41)
            .with(builder.caption("Skills", 13, 39, BOTTOM_CENTRE));
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

    private PageBuilder.Component armourClass() {
        return builder.borderedSection(42, 9, 10, 8)
            .with(builder.value(Armour.getArmourClass(character), 5, 3, CENTRE))
            .with(builder.caption("Armour\nClass", 5, 7, BOTTOM_CENTRE));
    }

    private PageBuilder.Component initiative() {
        return builder.borderedSection(52, 9, 10, 8)
            .with(builder.value(character.getModifier(DEXTERITY), 5, 3, CENTRE))
            .with(builder.caption("Initative", 5, 6, BOTTOM_CENTRE));
    }

    private PageBuilder.Component speed() {
        Race race = character.getAttribute(RACE);
        return builder.borderedSection(62, 9, 10, 8)
            .with(builder.value(race.getSpeed(), 5, 3, CENTRE))
            .with(builder.caption("Speed", 5, 6, BOTTOM_CENTRE));
    }

    private PageBuilder.Component hitPoints() {
        return builder.blank(42, 17).with(
            builder.borderedSection(0, 10, 30, 10)
                .with(builder.caption("Temporary Hit Points", 15, 8, BOTTOM_CENTRE)),
            builder.borderedSection(0, 0, 30, 14)
                .with(builder.field("Maximum Hit Points", character.getHitPoints(), 12, 1))
                .with(builder.caption("Current Hit Points", 15, 12, BOTTOM_CENTRE)));
    }

    private PageBuilder.Component hitDice() {
        return builder.borderedSection(42, 37, 14, 9)
            .with(builder.caption(character.getHitDice(), 7, 1, TOP_CENTRE))
            .with(builder.caption("Hit Dice", 7, 7, BOTTOM_CENTRE));
    }

    private PageBuilder.Component deathSaves() {
        return builder.borderedSection(56, 37, 16, 9)
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
        return builder.borderedSection(42, 46, 30, 29)
            .with(builder.writing(attackText(), 1, 1, 28, 28))
            .with(builder.caption("Attacks", 15, 27, BOTTOM_CENTRE));
    }

    private String attackText() {
        List<String> rows = new ArrayList<>();
        rows.add(row("th style='text-align:left; height:5px'", "Attack", "Hit", "Damage"));
        character.getWeapons().flatMap(weapon -> weapon.getAttacks(character))
            .map(at -> row("td style='height:5px'", at.getName(),
            String.format("%+d", at.getBonus()), at.getDamage()))
            .forEach(rows::add);
        return table(rows);
    }

    private PageBuilder.Component personality() {
        return builder.shadedSection(72, 9, 28, 66)
            .with(builder.borderedSection(0, 0, 28, 17)
                .with(builder.caption("Personality Traits", 14, 15, CENTRE))
                .with(builder.writing(attrHTML(TRAIT), 2, 2, 24, 13)))
            .with(builder.borderedSection(0, 17, 28, 16)
                .with(builder.caption("Ideals", 14, 14, CENTRE))
                .with(builder.writing(attrHTML(IDEAL), 2, 2, 24, 12)))
            .with(builder.borderedSection(0, 33, 28, 16)
                .with(builder.caption("Bonds", 14, 14, CENTRE))
                .with(builder.writing(attrHTML(BOND), 2, 2, 24, 12)))
            .with(builder.borderedSection(0, 49, 28, 17)
                .with(builder.caption("Flaws", 14, 15, CENTRE))
                .with(builder.writing(attrHTML(FLAW), 2, 2, 24, 13)));
    }

    private PageBuilder.Component proficiencies() {
        return builder.borderedSection(0, 75, 50, 25)
            .with(builder.writing(otherProficiencies(), 2, 1, 47, 24))
            .with(builder.caption("Proficiencies", 25, 23, BOTTOM_CENTRE));
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

    private void addProficiencies(StringBuilder text, AttributeType type,
        List<Attribute> abilities) {
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

    private PageBuilder.Component equipment() {
        return builder.borderedSection(50, 75, 50, 25)
            .with(builder.writing(equipmentDescription(), 2, 1, 48, 24))
            .with(builder.caption("Equipment", 25, 23, BOTTOM_CENTRE));
    }

    private String equipmentDescription() {
        return html(character.getInventory()
            .filter(eq -> !eq.getCategory().equals(EquipmentCategory.TREASURE))
            .map(Equipment::toString)
            .collect(Collectors.joining(", ")));
    }

}
