package characterbuilder.character.characterclass.warlock;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Ability;
import characterbuilder.character.ability.Skill;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeDelegate;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.character.choice.ChoiceSelector;
import characterbuilder.character.choice.NoOption;
import characterbuilder.character.choice.OptionChoice;
import characterbuilder.character.spell.Spell;
import characterbuilder.utils.StringUtils;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;
import org.w3c.dom.Element;

public enum EldritchInvocation implements Attribute {
    AGONIZING_BLAST(invocation()
        .withPrerequisiteCantrip(Spell.ELDRITCH_BLAST)
        .withDescription("+[$chr_mod] damage on casting <em>Eldritch Blast</em>.")),
    ARMOUR_OF_SHADOWS(invocation()
        .withSpellAbility(Spell.MAGE_ARMOR, AttributeType.CHARISMA)
        .withDescription("Cast <em>Mage Armour</em> at will.")),
    ASCENDANT_STEP(invocation()
        .withPrerequisiteLevel(9)
        .withSpellAbility(Spell.LEVITATE, AttributeType.CHARISMA)
        .withDescription("Cast <em>Levitate</em> at will.")),
    BEAST_SPEECH(invocation()
        .withSpellAbility(Spell.SPEAK_WITH_ANIMALS, AttributeType.CHARISMA)
        .withDescription("Cast <em>Speak with Animals</em> at will.")),
    BEGUILING_INFLUENCE(invocation().withoutAdding()
        .withAttribute(Skill.DECEPTION)
        .withAttribute(Skill.PERSUASION)),
    BEWITCHING_WHISPERS(invocation()
        .withPrerequisiteLevel(7)
        .withSpellAbility(Spell.COMPULSION, AttributeType.CHARISMA)
        .withDescription("Cast <em>Compulsion</em> once between long rests using a spell slot.")),
    BOOK_OF_ANCIENT_SECRETS(invocation()
        .withPrerequisite(Ability.PACT_OF_THE_TOME)
        .withChoice(ChoiceGenerator.spellChoice("Warlock", 2, "Rituals", Spell.getSpellsAtLevel(1)))
        .withDescription("Inscribe and cast rituals in Book of Shadows.")),
    CHAINS_OF_CARCERI(invocation()
        .withPrerequisite(Ability.PACT_OF_THE_CHAIN)
        .withPrerequisiteLevel(15)
        .withSpellAbility(Spell.HOLD_MONSTER, AttributeType.CHARISMA)
        .withDescription("Cast <em>Hold Monster</em> at will, "
            + "targeting a celestial, fiend or elemental.")),
    DEVILS_SIGHT(invocation()
        .withName("Devil's Sight")
        .withDescription("See normally in darkness to 120 feet.")),
    DREADFUL_WORD(invocation()
        .withPrerequisiteLevel(7)
        .withSpellAbility(Spell.CONFUSION, AttributeType.CHARISMA)
        .withDescription("Cast <em>Confusion</em> once between long rests using a spell slot.")),
    ELDRITCH_SIGHT(invocation()
        .withSpellAbility(Spell.DETECT_MAGIC, AttributeType.CHARISMA)
        .withDescription("Cast <em>Detect Magic</em> at will.")),
    ELDRITCH_SPEAR(invocation()
        .withPrerequisiteCantrip(Spell.ELDRITCH_BLAST)
        .withDescription("Range of <em>Eldritch Blast</em> is 300 feet.")),
    EYES_OF_THE_RUNE_KEEPER(invocation()
        .withDescription("Read all writing.")),
    FIENDISH_VIGOUR(invocation()
        .withSpellAbility(Spell.FALSE_LIFE, AttributeType.CHARISMA)
        .withDescription("Cast <em>False Life</em> on self at 1st level at will.")),
    GAZE_OF_TWO_MINDS(invocation()
        .withDescription("As an action, touch an ally and perceive through its senses.")),
    LIFEDRINKER(invocation()
        .withPrerequisite(Ability.PACT_OF_THE_BLADE)
        .withPrerequisiteLevel(12)
        .withDescription("On hitting with pact weapon, [bonus(max(1, $chr_mod))] necrotic damage.")),
    MASK_OF_MANY_FACES(invocation()
        .withSpellAbility(Spell.DISGUISE_SELF, AttributeType.CHARISMA)
        .withDescription("Cast <em>Disguise Self</em> at will.")),
    MASTER_OF_MYRIAD_FORMS(invocation()
        .withPrerequisiteLevel(15)
        .withSpellAbility(Spell.ALTER_SELF, AttributeType.CHARISMA)
        .withDescription("Cast <em>Alter Self</em> at will.")),
    MINIONS_OF_CHAOS(invocation()
        .withPrerequisiteLevel(9)
        .withSpellAbility(Spell.CONJURE_ELEMENTAL, AttributeType.CHARISMA)
        .withDescription("Cast <em>Conjure Elemental</em> once between long rests using a spell slot.")),
    MIRE_THE_MIND(invocation()
        .withPrerequisiteLevel(5)
        .withSpellAbility(Spell.SLOW, AttributeType.CHARISMA)
        .withDescription("Cast <em>Slow</em> once between long rests using a spell slot.")),
    MISTY_VISIONS(invocation()
        .withSpellAbility(Spell.SILENT_IMAGE, AttributeType.CHARISMA)
        .withDescription("Cast <em>Silent Image<em> at will.")),
    ONE_WITH_SHADOWS(invocation()
        .withPrerequisiteLevel(5)
        .withDescription("As an action in an area of dim light or darkness, "
            + "become invisible until next move, action or reaction.")),
    OTHERWORLDLY_LEAP(invocation()
        .withPrerequisiteLevel(9)
        .withSpellAbility(Spell.JUMP, AttributeType.CHARISMA)
        .withDescription("Cast <em>Jump</em> at will.")),
    REPELLING_BLAST(invocation()
        .withPrerequisiteCantrip(Spell.ELDRITCH_BLAST)
        .withDescription("On hit with <em>Eldritch Blast</em>, push target 10 feet.")),
    SCULPTOR_OF_FLESH(invocation()
        .withPrerequisiteLevel(7)
        .withSpellAbility(Spell.POLYMORPH, AttributeType.CHARISMA)
        .withDescription("Cast <em>Polymorph</em> once between long rests using a spell slot.")),
    SIGN_OF_ILL_OMEN(invocation()
        .withPrerequisiteLevel(5)
        .withSpellAbility(Spell.BESTOW_CURSE, AttributeType.CHARISMA)
        .withDescription("Cast <em>Bestow Curse</em> once between long rests using a spell slot.")),
    THEIF_OF_FIVE_FATES(invocation()
        .withSpellAbility(Spell.BANE, AttributeType.CHARISMA)
        .withDescription("Cast <em>Bane</em> once between long rests using a spell slot.")),
    THIRSTING_BLADE(invocation()
        .withPrerequisite(Ability.PACT_OF_THE_BLADE)
        .withPrerequisiteLevel(5)
        .withDescription("Two attacks with pact weapon.")),
    VISIONS_OF_DISTANT_REALMS(invocation()
        .withPrerequisiteLevel(15)
        .withSpellAbility(Spell.ARCANE_EYE, AttributeType.CHARISMA)
        .withDescription("Cast <em>Arcane Eye</em> at will.")),
    VOICE_OF_THE_CHAIN_MASTER(invocation()
        .withPrerequisite(Ability.PACT_OF_THE_CHAIN)
        .withDescription("Communicate telepathically with familiar.")
        .withDescription("Speak through familiar.")),
    WHISPERS_OF_THE_GRAVE(invocation()
        .withPrerequisiteLevel(9)
        .withSpellAbility(Spell.SPEAK_WITH_DEAD, AttributeType.CHARISMA)
        .withDescription("Cast <em>Speak with Dead</em> at will.")),
    WITCH_SIGHT(invocation()
        .withPrerequisiteLevel(15)
        .withDescription("See true form of concealed or transmuted creatures within 30 feet."));

    private final AttributeDelegate delegate;

    private static AttributeDelegate invocation() {
        return new AttributeDelegate();
    }

    private EldritchInvocation(AttributeDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.ELDRITCH_INVOCATION;
    }

    public boolean isAllowed(Character character) {
        return delegate.isAllowed(character);
    }

    @Override
    public void generateLevelChoices(Character character) {
        delegate.generateChoices(character);
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return delegate.getDescription(character);
    }

    public static OptionChoice getChoice(int count) {
        return new OptionChoice("Eldritch Invocation", count) {
            @Override
            public void select(Character character, ChoiceSelector selector) {
                selector.chooseOption(Arrays.stream(values())
                    .filter(ei -> ei.isAllowed(character))
                    .filter(ei -> !character.hasAttribute(ei)),
                    ei -> ei.choose(character));
            }
        };
    }

    public static OptionChoice getReplacement() {
        return new OptionChoice("Replace Eldritch Invocation") {
            @Override
            public void select(Character character, ChoiceSelector selector) {
                selector.chooseOption(NoOption.NONE.concat(getInvocations(character)), ei -> {
                    if (ei != NoOption.NONE) {
                        character.removeAttribute((Attribute) ei);
                        Optional<OptionChoice> choice = character.getAllChoices()
                            .filter(opt -> opt.getName().equals("Eldritch Invocation"))
                            .findAny();
                        if (choice.isPresent()) {
                            choice.get().addCount(1);
                        } else {
                            character.pushChoice(getChoice(1));
                        }
                    }
                    selector.choiceMade();
                });
            }
        };
    }

    private static Stream<EldritchInvocation> getInvocations(Character character) {
        return character.getAttributes(AttributeType.ELDRITCH_INVOCATION, EldritchInvocation.class);
    }

    @Override
    public void choose(Character character) {
        if (delegate.shouldAdd())
            Attribute.super.choose(character);
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    public static EldritchInvocation load(Element element) {
        return valueOf(element.getTextContent());
    }
}
