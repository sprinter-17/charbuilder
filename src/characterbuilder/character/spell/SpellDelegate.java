package characterbuilder.character.spell;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Value;
import characterbuilder.character.characterclass.wizard.MagicSchool;
import characterbuilder.utils.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import static java.util.stream.Collectors.joining;
import java.util.stream.Stream;

public class SpellDelegate {

    private final MagicSchool school;
    private final int level;
    private boolean ritual = false;
    private String castingTime = "1 action";
    private String range = "-";
    private String area = "-";
    private String duration;
    private EnumSet<SpellComponent> components = EnumSet.noneOf(SpellComponent.class);
    private Value cost = Value.ZERO;
    private List<String> effects = new ArrayList<>();

    public static SpellDelegate spell(MagicSchool school, int level) {
        return new SpellDelegate(school, level);
    }

    private SpellDelegate(MagicSchool school, int level) {
        this.school = school;
        this.level = level;
    }

    public MagicSchool getSchool() {
        return school;
    }

    public int getLevel() {
        return level;
    }

    public SpellDelegate ritual() {
        this.ritual = true;
        return this;
    }

    public boolean isRitual() {
        return ritual;
    }

    public SpellDelegate castingTime(String castingTime) {
        this.castingTime = castingTime;
        return this;
    }

    public String getCastingTime() {
        return castingTime;
    }

    public SpellDelegate range(String range) {
        this.range = range;
        return this;
    }

    public String getRange() {
        return range;
    }

    public SpellDelegate area(String area) {
        this.area = area;
        return this;
    }

    public String getArea() {
        return area;
    }

    public SpellDelegate duration(String duration) {
        this.duration = duration;
        return this;
    }

    public String getDuration() {
        return duration;
    }

    public SpellDelegate components(SpellComponent... components) {
        Arrays.stream(components).forEach(this.components::add);
        return this;
    }

    public String getComponents() {
        return components.stream().map(SpellComponent::getAbbreviation).collect(joining(", "));
    }

    public SpellDelegate cost(Value cost) {
        this.cost = cost;
        return this;
    }

    public Value getCost() {
        return cost;
    }

    public SpellDelegate effect(String effect) {
        this.effects.add(effect);
        return this;
    }

    public Stream<String> getEffects(Character character) {
        return effects.stream().map(ef -> StringUtils.expand(ef, character));
    }

}
