package characterbuilder.character.characterclass.warlock;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeDelegate;
import static characterbuilder.character.attribute.AttributeDelegate.delegate;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.character.equipment.AdventureGear;
import characterbuilder.character.spell.Spell;
import characterbuilder.utils.StringUtils;
import java.util.stream.Stream;
import org.w3c.dom.Element;

public enum WarlockAbility implements Attribute {
    PACT_OF_THE_CHAIN(delegate()
            .withDescription("Familiar can be imp, pseudodragon, quasit or sprite.")
            .withDescription("Forgo an attack to allow familiar to make attack as reaction.")
            .withSpellAbility(Spell.FIND_FAMILIAR, AttributeType.CHARISMA)),
    PACT_OF_THE_BLADE(delegate()
            .withDescription("As an action, create a pact weapon in any form.")
            .withDescription("Gain proficiency with pact weapon.")),
    PACT_OF_THE_TOME(delegate()
            .withDescription("Cast cantrips from Book of Shadows.")
            .withEquipment(AdventureGear.BOOK_OF_SHADOWS)
            .withChoice(ChoiceGenerator
                    .cantripChoice(3, "Book of Shadow Cantrips", AttributeType.CHARISMA, Spell
                            .getSpellsAtLevel(0)))),
    ELDRITCH_MASTER(delegate()
            .withDescription("Spend 1 minute to regain all spell slots once between each long rest.")),
    FEY_PRESENCE(delegate()
            .withDescription("As an action, charm or frighten creatures within a 10-foot cube " + "around Warlock for 1 turn. Wis. save.")),
    MISTY_ESCAPE(delegate()
            .withDescription("As a reaction to taking damage, turn invisible and teleport up to 60 feet.")),
    BEGUILING_DEFENSES(delegate().withDescription("Immune to charm. ")
            .withDescription("As a reaction to a charm attempt, Wis. save or target charmed for 1 minute.")),
    DARK_DELERIUM(delegate()
            .withDescription("As a reaction, charm or frighten one creature within 60 feet for 1 minute. " + "Wis. save.")),
    DARK_ONES_BLESSING(delegate()
            .withDescription("On reducing enemy to 0 HP, gain [$chr_mod + $level] temporary HP.")),
    DARK_ONES_OWN_LUCK(delegate()
            .withDescription("Add 1d10 to 1 ability check or save.")
            .withDescription("Use once between each long rest.")),
    FIENDISH_RESILIENCE(delegate()
            .withDescription("Resistance to a chosen damage type.")),
    HURL_THROUGH_HELL(delegate()
            .withDescription("On hitting an enemy, send away for 1 turn and cause 10d10 phychic damage.")),
    AWAKENED_MIND(delegate()
            .withDescription("Communicate telepathically with any creature within 30 feet.")),
    ENTROPIC_WARD(delegate()
            .withDescription("As a reaction, impose disadvantage on an attack roll.")
            .withDescription("If the attack misses, next attack against target has advantage.")
            .withDescription("Use once between each rest.")),
    THOUGHT_SHIELD(delegate().withDescription("Immune to telepathy.")
            .withDescription("Resistance to psychic damage.")
            .withDescription("Creature dealing psychic attack takes equal damage.")),
    CREATE_THRALL(delegate()
            .withDescription("As an action, charm an incapacitated humanoid with touch."));
    private final AttributeDelegate delegate;

    private WarlockAbility(AttributeDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.WARLOCK_ABILITY;
    }

    @Override
    public void generateInitialChoices(Character character) {
        delegate.generateChoices(character);
    }

    @Override
    public String toString() {
        return delegate.getName().orElse(StringUtils.capitaliseEnumName(name()));
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return delegate.getDescription(character);
    }

    public static WarlockAbility load(Element element) {
        return valueOf(element.getTextContent());
    }
}
