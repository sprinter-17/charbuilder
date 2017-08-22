package characterbuilder.character.characterclass;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeDelegate;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.INTELLIGENCE;
import static characterbuilder.character.choice.ChoiceGenerator.cantripChoice;
import characterbuilder.character.spell.Spell;
import characterbuilder.character.spell.SpellAbility;
import characterbuilder.character.spell.SpellCasting;
import characterbuilder.utils.StringUtils;
import java.util.stream.Stream;
import org.w3c.dom.Element;

public enum MagicSchool implements Attribute {
    ABJURATION(magicSchool()
        .withLevelAttributes(2, ArcaneTradition.ARCANE_WARD)
        .withLevelAttributes(6, ArcaneTradition.PROJECTED_WARD)
        .withLevelAttributes(10, ArcaneTradition.IMPROVED_ABJURATION)
        .withLevelAttributes(16, ArcaneTradition.SPELL_RESISTANCE)),
    CONJURATION(magicSchool()
        .withLevelAttributes(2, ArcaneTradition.MINOR_CONJURATION)
        .withLevelAttributes(6, ArcaneTradition.BENIGN_TRANSPOSITION)
        .withLevelAttributes(10, ArcaneTradition.FOCUSED_CONJURATION)
        .withLevelAttributes(16, ArcaneTradition.DURABLE_SUMMONS)),
    DIVINATION(magicSchool()
        .withLevelAttributes(2, ArcaneTradition.PORTENT)
        .withLevelAttributes(6, ArcaneTradition.EXPERT_DIVINATION)
        .withLevelAttributes(10, ArcaneTradition.THE_THIRD_EYE)
        .withLevelAttributes(16, ArcaneTradition.GREATER_PORTENT)),
    ENCHANTMENT(magicSchool()
        .withLevelAttributes(2, ArcaneTradition.HYPNOTIC_GAZE)
        .withLevelAttributes(6, ArcaneTradition.INSTINCTIVE_CHARM)
        .withLevelAttributes(10, ArcaneTradition.SPLIT_ENCHANTMENT)
        .withLevelAttributes(16, ArcaneTradition.ALERT_MEMORIES)),
    EVOCATION(magicSchool()
        .withLevelAttributes(2, ArcaneTradition.SCULPT_SPELLS)
        .withLevelAttributes(6, ArcaneTradition.POTENT_CANTRIP)
        .withLevelAttributes(10, ArcaneTradition.EMPOWERED_EVOCATION)
        .withLevelAttributes(16, ArcaneTradition.OVERCHANNEL)),
    ILLUSION(magicSchool()
        .withLevelAttributes(2, ArcaneTradition.IMPROVED_MINOR_ILLUSION)
        .withLevelAttributes(6, ArcaneTradition.MALLEABLE_ILLUSIONS)
        .withLevelAttributes(10, ArcaneTradition.ILLUSORY_SELF)
        .withLevelAttributes(16, ArcaneTradition.ILLUSORY_REALITY)),
    NECROMANCY(magicSchool()
        .withLevelAttributes(2, ArcaneTradition.GRIM_HARVEST)
        .withLevelAttributes(6, ArcaneTradition.UNDEAD_THRALLS)
        .withLevelAttributes(10, ArcaneTradition.INURED_TO_UNDEATH)
        .withLevelAttributes(16, ArcaneTradition.COMMAND_UNDEAD)),
    TRANSMUTATION(magicSchool()
        .withLevelAttributes(2, ArcaneTradition.MINOR_ALCHEMY)
        .withLevelAttributes(6, ArcaneTradition.TRANSMUTERS_STONE)
        .withLevelAttributes(10, ArcaneTradition.SHAPECHANGER)
        .withLevelAttributes(16, ArcaneTradition.MASTER_TRANSMUTER));

    private final AttributeDelegate delegate;

    private enum ArcaneTradition implements Attribute {
        ARCANE_WARD(magicSchool()
            .withDescription("On casting Abjuration spell of 1st level or higher, "
                + "gain [$level * 2 + $int_mod] temporary HP.")
            .withDescription("When at 0 HP, regain 2x spell level when casting Abjuration spell.")),
        PROJECTED_WARD(magicSchool()
            .withDescription("As a reaction, use Arcane Ward to absorb damage from ally within 30 feet.")),
        IMPROVED_ABJURATION(magicSchool()
            .withDescription("Add [$prof] to ability checks when casting Abjuration spells.")),
        SPELL_RESISTANCE(magicSchool()
            .withDescription("Advantage on saves vs. spells.")
            .withDescription("Resistance to spell damage.")),
        MINOR_CONJURATION(magicSchool()
            .withDescription("As an action, conjure an inanimate object within 10 feet.")),
        BENIGN_TRANSPOSITION(magicSchool()
            .withDescription("As an action, teleport up to 30 feet or swap places with ally.")
            .withDescription("Use once between each long rest or each conjuration spell.")),
        FOCUSED_CONJURATION(magicSchool()
            .withDescription("Concentration for a conjuration spell cannot be broken by damage.")),
        DURABLE_SUMMONS(magicSchool()
            .withDescription("Conjured or created creatures have 30 temporary HP.")),
        PORTENT(magicSchool()
            .withDescription("Roll [max($level 1:2,14:3)]d20 after each long rest. "
                + "Cause any rolls to use one of these values.")),
        EXPERT_DIVINATION(magicSchool()
            .withDescription("On casting a divination spell beween 2nd and 5th level, "
                + "regain a lower level spell slot.")),
        THE_THIRD_EYE(magicSchool()
            .withDescription("As an action, gain <em>Darkvision</em>, <em>Ethereal Sight</em>, "
                + "<em>Greater Comprehension</em> or <em>See Invisibility</em> until next rest.")
            .withDescription("Use once between each long rest.")),
        GREATER_PORTENT(magicSchool()),
        HYPNOTIC_GAZE(magicSchool()
            .withDescription("As an action, attempt to charm 1 creature within 5 feet.")
            .withDescription("Wis. save DC[$spell_dc] or charmed for 1 turn, speed 0, incapacitated.")),
        INSTINCTIVE_CHARM(magicSchool()
            .withDescription("As a reaction on being attacked, divert attack to closest creature. "
                + "Wis. save DC[$spell_dc].")),
        SPLIT_ENCHANTMENT(magicSchool()
            .withDescription("Enchantment spells that target 1 creature can target a 2nd creature.")),
        ALERT_MEMORIES(magicSchool()
            .withDescription("Charmed creatures are unaware of echantment.")
            .withDescription("As an action, force charmed creature to lose up to [1+$chr_mod] hours "
                + "of duration of enchantment. Int. save DC[$spell_dc]")),
        SCULPT_SPELLS(magicSchool()
            .withDescription("Up to 1 + spell level allies automatically save on evocation spells.")),
        POTENT_CANTRIP(magicSchool()
            .withDescription("Targets savings against cantrips take half damage.")),
        EMPOWERED_EVOCATION(magicSchool()
            .withDescription("Add [$int_mode] to damage of evocation spells.")),
        OVERCHANNEL(magicSchool()
            .withDescription("Deal maximum damage on spell of level 5 or less.")
            .withDescription("After first use take 2d12 + 1d12 / use necrotic damage per spell level.")),
        IMPROVED_MINOR_ILLUSION(magicSchool()
            .withDescription("On casting <em>Minor Illusion</em>, both sound and image created.")
            .withAction("Cantrip", ch -> {
                Attribute minorIllusion = new SpellAbility(Spell.MINOR_ILLUSION, INTELLIGENCE);
                if (ch.hasAttribute(minorIllusion))
                    ch.pushChoice(cantripChoice(1, INTELLIGENCE));
                else
                    ch.addAttribute(minorIllusion);
            })),
        MALLEABLE_ILLUSIONS(magicSchool()
            .withDescription("As an action, change the nature of illusion with duration "
                + "of at least 1 minute.")),
        ILLUSORY_SELF(magicSchool()
            .withDescription("As a reaction on being attacked, interpose illusory duplicate that causes "
                + "attack to miss.")
            .withDescription("Use once between each rest.")),
        ILLUSORY_REALITY(magicSchool()
            .withDescription("As a bonus action, make an illusion real for 1 minute.")),
        GRIM_HARVEST(magicSchool()
            .withDescription("On killing a creature with a spell, regain 2x spell level HP, "
                + "or 3x for Necromancy spells.")
            .withDescription("No effect on killing undead or constructs.")),
        UNDEAD_THRALLS(magicSchool()
            .withDescription("When casting <em>Animate Dead</em>, target an additional corpse or bones.")
            .withDescription("Undead created with Necromancy have +[$level] HP and +[$prof] weapon damage.")
            .withAction("Gain Animate Dead", ch -> {
                SpellCasting wizardCasting = ch.getSpellCasting("Wizard");
                if (!wizardCasting.hasLearntSpell(Spell.ANIMATE_DEAD))
                    wizardCasting.addLearntSpell(Spell.ANIMATE_DEAD);
            })),
        INURED_TO_UNDEATH(magicSchool()
            .withDescription("Resistance to necrotic damage and maximum HP cannot be reduced.")),
        COMMAND_UNDEAD(magicSchool()
            .withDescription("As an action, control 1 undead within 60 feet. Chr. save DC[$spell_dc].")
            .withDescription("Undead with Int. of 8 have advantage on save. Int. of 12 can repeat save "
                + "each hour.")),
        MINOR_ALCHEMY(magicSchool()
            .withDescription("Change the substance of 1 physical object for 1 hour. "
                + "Requires 10 minutes per cubic foot of material.")),
        TRANSMUTERS_STONE(magicSchool()
            .withName("Transmuter's Stone")
            .withDescription("Spend 8 hours to create a transmuter's stone. ")
            .withDescription("Possessor gains one of the following benefits: darkvision to 60 feet, "
                + "speed +10 feet, proficiency in Con. saves, resistance to acid, cold, fire, "
                + "lightning or thunder.")
            .withDescription("Benefit can be changed each time Transmutation spell is cast.")),
        SHAPECHANGER(magicSchool()
            .withDescription("Cast <em>Polymorph</em> to transform into a beast with CR 1 or lower "
                + "without spending a spell slot.")
            .withDescription("Use once between rests.")
            .withAction("Gain Polymorph", ch -> {
                SpellCasting wizardCasting = ch.getSpellCasting("Wizard");
                if (!wizardCasting.hasLearntSpell(Spell.POLYMORPH))
                    wizardCasting.addLearntSpell(Spell.POLYMORPH);
            })),
        MASTER_TRANSMUTER(magicSchool()
            .withDescription("As an action, consume transmuter's stone to "
                + "transform one object to another, "
                + "remove curses, diseases and poisons and restore all hit points for a creature, "
                + "cast <em>Raise Dead</em> without expending a spell slot, or "
                + "reduce apparent age by 3d10 years (minimum 13).")
            .withDescription("Use once between long rests.")),;

        private final AttributeDelegate delegate;

        private ArcaneTradition(AttributeDelegate delegate) {
            this.delegate = delegate;
        }

        @Override
        public AttributeType getType() {
            return AttributeType.ARCANE_TRADITION_ABILITY;
        }

        @Override
        public void generateInitialChoices(Character character) {
            delegate.generateChoices(character);
        }

        @Override
        public String toString() {
            return StringUtils.capitaliseEnumName(name());
        }

        @Override
        public Stream<String> getDescription(Character character) {
            return delegate.getDescription(character);
        }
    }

    private static AttributeDelegate magicSchool() {
        return new AttributeDelegate();
    }

    private MagicSchool(AttributeDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.ARCANE_TRADITION;
    }

    @Override
    public void generateLevelChoices(Character character) {
        delegate.generateChoices(character);
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    public static MagicSchool load(Element element) {
        return MagicSchool.valueOf(element.getTextContent());
    }

    public static Attribute loadAbility(Element element) {
        return ArcaneTradition.valueOf(element.getTextContent());
    }
}
