package characterbuilder.character.attribute;

import characterbuilder.character.Character;
import characterbuilder.character.choice.AbilityScoreOrFeatIncrease;
import characterbuilder.character.choice.Choice;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.character.choice.OptionChoice;
import characterbuilder.character.equipment.Equipment;
import characterbuilder.character.spell.Spell;
import characterbuilder.character.spell.SpellAbility;
import characterbuilder.utils.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import static java.util.stream.Collectors.joining;
import java.util.stream.Stream;

public class AttributeDelegate {

    private Optional<String> name = Optional.empty();
    private boolean adding = true;
    private final List<String> description = new ArrayList<>();
    private final List<Predicate<Character>> prerequisites = new ArrayList<>();
    private final ChoiceGenerator generator = new ChoiceGenerator();

    public static AttributeDelegate delegate() {
        return new AttributeDelegate();
    }

    public Optional<String> getName() {
        return name;
    }

    public boolean shouldAdd() {
        return adding;
    }

    public Stream<String> getDescription(Character character) {
        return Stream.concat(description.stream(), generator.getDescription(character))
            .map(desc -> StringUtils.expand(desc, character));
    }

    public AttributeDelegate withName(String name) {
        this.name = Optional.of(name);
        return this;
    }

    public AttributeDelegate withDescription(String description) {
        this.description.add(description);
        return this;
    }

    public AttributeDelegate withPrerequisite(Predicate<Character> prerequisite) {
        prerequisites.add(prerequisite);
        return this;
    }

    public AttributeDelegate withPrerequisite(AttributeType attribute) {
        return withPrerequisite(ch -> ch.hasAttribute(attribute));
    }

    public AttributeDelegate withPrerequisite(Attribute attribute) {
        return withPrerequisite(ch -> ch.hasAttribute(attribute));
    }

    public AttributeDelegate withPrerequisiteAbilityScore(AttributeType score, int value) {
        return withPrerequisite(ch -> ch.getIntAttribute(score) >= value);
    }

    public AttributeDelegate withPrerequisiteCantrip(Spell cantrip) {
        return withPrerequisite(ch -> ch
            .getAttributes(AttributeType.SPELL_ABILITY, SpellAbility.class)
            .anyMatch(c -> c.getSpell() == cantrip));
    }

    public AttributeDelegate withPrerequisiteLevel(int level) {
        return withPrerequisite(ch -> ch.getLevel() >= level);
    }

    public AttributeDelegate withChoice(Choice choice) {
        this.generator.addChoice(choice);
        return this;
    }

    public AttributeDelegate withChoice(int count, OptionChoice choice) {
        this.generator.addChoice(count, choice);
        return this;
    }

    public AttributeDelegate withAction(String name, Consumer<Character> action) {
        generator.addAction(name, action);
        return this;
    }

    public AttributeDelegate withEquipment(Equipment equipment) {
        generator.addEquipment(equipment);
        return this;
    }

    public AttributeDelegate withLevelAttributes(int level, Attribute... attributes) {
        generator.level(level).addAttributes(attributes);
        return this;
    }

    public AttributeDelegate withAttribute(Attribute attribute) {
        return withPrerequisite(ch -> !ch.hasAttribute(attribute))
            .withAction(attribute.toString(), ch -> ch.addAttribute(attribute));
    }

    public AttributeDelegate withSpell(String casting, Spell spell) {
        generator.addLearntSpells(casting, spell);
        return this;
    }

    public AttributeDelegate withSpellAbility(Spell spell, AttributeType abilityScore) {
        generator.addSpellAbility(spell, abilityScore);
        return this;
    }

    public AttributeDelegate withIncrease(AttributeType score) {
        return withPrerequisite(ch -> ch.getScore(score).isAtMax())
            .withAction("Increase " + score.toString(), ch -> ch.getScore(score).addValue(1));
    }

    public AttributeDelegate withIncreaseChoice(AttributeType... scores) {
        String increaseName = "Increase "
            + Arrays.stream(scores).map(AttributeType::toString).collect(joining(" or "));
        generator.addChoice(new AbilityScoreOrFeatIncrease(increaseName, scores));
        return this;
    }

    public AttributeDelegate withoutAdding() {
        this.adding = false;
        return this;
    }

    public void generateChoices(Character character) {
        generator.generateChoices(character);
    }

    public boolean isAllowed(Character character) {
        return prerequisites.stream().allMatch(p -> p.test(character));
    }

}
