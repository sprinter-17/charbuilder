package characterbuilder.character.characterclass.rogue;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeDelegate;
import static characterbuilder.character.attribute.AttributeDelegate.delegate;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.utils.StringUtils;
import java.util.stream.Stream;
import org.w3c.dom.Element;

public enum RogueAbility implements Attribute {
    SNEAK_ATTACK(delegate()
        .withDescription("Extra [$rogue_level /^ 2]d6 damage on one attack with "
            + "advantage each turn.")),
    CUNNING_ACTION(delegate()
        .withDescription("Bonus dash, disengage or hide action each turn")),
    RELIABLE_TALENT(delegate()
        .withDescription("Treat 9 or lower as 10 on proficient ability checks.")),
    BLINDSENSE(delegate()
        .withDescription("Aware of invisible creatures within 10'")),
    SLIPPERY_MIND(delegate()
        .withDescription("Add proficiency to save vs Wis.")),
    ELUSIVE(delegate()
        .withDescription("No attack roll has advantage if not incapacitated.")),
    STROKE_OF_LUCK(delegate()
        .withDescription("Turn a missed attack into a hit or failed ability check "
            + "into success one between each rest.")),
    FAST_HANDS(delegate()
        .withDescription("Use Cunning Action to perform sleight of hand, disarm trap, "
            + "open lock or use item.")),
    SECOND_STORY_WORK(delegate()
        .withName("Second-Story Work")
        .withDescription("Climb at normal speed. "
            + "Add [$dex_mod]' to running long jump length.")),
    SUPREME_SNEAK(delegate()
        .withDescription("Advantage on all stealth checks if moving no more than [$speed/2]' "
            + "on the same turn.")),
    USE_MAGIC_DEVICES(delegate()
        .withDescription("Ignore all class, race and level requirements "
            + "on the use of magic items.")),
    THIEFS_REFLEXES(delegate()
        .withName("Thief's Reflexes")
        .withDescription("Take two turns during first round of combat. "
            + "Second turn is at initiative - 10.")),
    ASSASSINATE(delegate()
        .withDescription("Advantage on attack against creatures with lower initiative.")
        .withDescription("Automatic critical against surprised creatures.")),
    INFILTRATION_EXPERTISE(delegate()
        .withDescription("Spend 7 days and 25 GP in research to establish false identity.")),
    IMPOSTER(delegate()
        .withDescription("Spend three hours in study to mimic a person's "
            + "speech, writing and behaviour.")),
    DEATH_STRIKE(delegate()
        .withDescription("Double attack damage against surprised creatures. "
            + "Con. save DC[8+$dex_mod+$prof].")),
    MAGE_HAND_LEGERDEMAIN(delegate()
        .withDescription("<em>Mage Hand</em> is invisible and can perform additional tasks: "
            + "stow or retrieve object in ally's container; pick locks and disarm traps.")),
    MAGICAL_AMBUSH(delegate()
        .withDescription("If hidden, target of spell has disadvantage on save.")),
    VERSATILE_TRICKSTER(delegate()
        .withDescription("As a bonus action, use <em>Mage Hand</em> to distract a creature within "
            + "5 feet giving advantage on attack.")),
    SPELL_THIEF(delegate()
        .withDescription("As a reaction against a spell that targets thief, steal the spell. "
            + "Spell proficiency save DC[8 + $prof + $int_mod].")
        .withDescription("Spell is prepared for next 8 hours.")
        .withDescription("Use once between each long rest.")),;

    private final AttributeDelegate delegate;

    private RogueAbility(AttributeDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.ROGUE_ABILITY;
    }

    @Override
    public void generateInitialChoices(Character character) {
        delegate.generateChoices(character);
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return delegate.getDescription(character);
    }

    @Override
    public String toString() {
        return delegate.getName().orElse(StringUtils.capitaliseEnumName(name()));
    }

    public static RogueAbility load(Element element) {
        return valueOf(element.getTextContent());
    }
}
