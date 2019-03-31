package characterbuilder.character.characterclass.monk;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeDelegate;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.choice.ChoiceSelector;
import characterbuilder.character.choice.NoOption;
import characterbuilder.character.choice.OptionChoice;
import characterbuilder.character.spell.LearntSpell;
import characterbuilder.character.spell.Spell;
import characterbuilder.utils.StringUtils;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;
import org.w3c.dom.Element;

public enum ElementalDiscipline implements Attribute {
    BREATH_OF_WINTER(discipline(Spell.CONE_OF_COLD, 6)
        .withPrerequisiteLevel(17)),
    CLENCH_OF_THE_NORTH_WIND(discipline(Spell.HOLD_PERSON, 3)
        .withPrerequisiteLevel(6)),
    ELEMENTAL_ATTUNEMENT(discipline()
        .withDescription("Use action to cause one effect: harmless sensory effect, "
            + "light or extinguish a small flame, chill or warm an object, "
            + "shape an element for 1 minute.")),
    ENTERNAL_MOUNTAIN_DEFENCE(discipline(Spell.STONESKIN)
        .withDescription("Spend 5 Ki points to cast <em>Stoneskin</em> targeting self")
        .withPrerequisiteLevel(17)),
    FANGS_OF_THE_FIRE_SNAKE(discipline()
        .withDescription("Spend 1 Ki point on attack; extend unarmoured attack range by 10 feet. ")
        .withDescription("Spend 1 further Ki point to deal 1d10 extra fire damage.")),
    FIST_OF_FOUR_THUNDERS(discipline(Spell.THUNDERWAVE, 2)),
    FIST_OF_UNBROKEN_AIR(discipline()
        .withDescription("Spend at least 2 Ki points as an action. A creature within 30' takes "
            + "3d10 bludgeoning damage + 1d10 for each extra Ki point and "
            + "is pushed 20 and knocked prone. Str. save for half damage only.")),
    FLAMES_OF_THE_PHOENIX(discipline(Spell.FIREBALL, 4)
        .withPrerequisiteLevel(11)),
    GONG_OF_THE_SUMMIT(discipline(Spell.SHATTER, 3)
        .withPrerequisiteLevel(6)),
    MIST_STANCE(discipline(Spell.GASEOUS_FORM)
        .withDescription("Spend 4 Ki points to cast <em>Gaseous Form</em> on self.")
        .withPrerequisiteLevel(11)),
    RIDE_THE_WIND(discipline(Spell.FLY, 4)
        .withPrerequisiteLevel(11)),
    RIVER_OF_HUNGRY_FLAME(discipline(Spell.WALL_OF_FIRE, 5)
        .withPrerequisiteLevel(17)),
    RUSH_OF_THE_GALE_SPIRITS(discipline(Spell.GUST_OF_WIND, 2)),
    SHAPE_OF_THE_FLOWING_RIVER(discipline()
        .withDescription("Spend 1 Ki point as an action. Shape water and ice within 120'.")),
    SWEEPING_CINDER_STRIKE(discipline(Spell.BURNING_HANDS, 2)),
    WATER_WHIP(discipline()
        .withDescription("Spend at least 2 Ki points as an action. A creature within 30 feet takes "
            + "3d10 bludgeoning damage + 1d10 for each extra Ki point and is either knocked prone "
            + " or pulled 25' closer. Dex. save for half damage only.")),
    WAVE_OF_ROLLING_EARTH(discipline(Spell.WALL_OF_STONE, 6)
        .withPrerequisiteLevel(17));

    private final AttributeDelegate delegate;

    public static OptionChoice replaceDiscipline() {
        return new OptionChoice("Replace Discipline") {
            @Override
            public void select(Character character, ChoiceSelector selector) {
                selector.chooseOption(NoOption.NONE.concat(character.getAttributes(
                    AttributeType.ELEMENTAL_DISCIPLINE, ElementalDiscipline.class)),
                    opt -> {
                    if (opt != NoOption.NONE) {
                        ElementalDiscipline discipline = (ElementalDiscipline) opt;
                        character.removeAttribute(discipline);
                        Optional<OptionChoice> choice = character.getAllChoices()
                            .filter(ch -> ch.getName().equals("Elemental Discipline"))
                            .findAny();
                        if (choice.isPresent()) {
                            choice.get().addCount(1);
                        } else {
                            character.addChoice(0, chooseDiscipline());
                        }
                    }
                    selector.choiceMade();
                });
            }
        };
    }

    public static OptionChoice chooseDiscipline() {
        return new OptionChoice("Elemental Discipline") {
            @Override
            public void select(Character character, ChoiceSelector selector) {
                selector.chooseOption(Arrays.stream(values()).filter(d -> d.isAllowed(character)),
                    discipline -> discipline.choose(character));
            }
        };
    }

    private static AttributeDelegate discipline() {
        return new AttributeDelegate();
    }

    private static AttributeDelegate discipline(Spell spell) {
        return new AttributeDelegate().withAttribute(new LearntSpell(spell, AttributeType.WISDOM));
    }

    private static AttributeDelegate discipline(Spell spell, int points) {
        return discipline(spell)
            .withDescription("Spend " + points + " Ki points "
                + "to cast <em>" + spell.toString() + "</em>.");
    }

    private ElementalDiscipline(AttributeDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.ELEMENTAL_DISCIPLINE;
    }

    @Override
    public void generateInitialChoices(Character character) {
        delegate.generateChoices(character);
    }

    public boolean isAllowed(Character character) {
        return !character.hasAttribute(this) && delegate.isAllowed(character);
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return delegate.getDescription(character);
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    public static ElementalDiscipline load(Element element) {
        return valueOf(element.getTextContent());
    }
}
