package characterbuilder.character.ability;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeDelegate;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.*;
import characterbuilder.utils.StringUtils;
import java.util.Arrays;
import java.util.stream.Stream;
import org.w3c.dom.Node;

public enum Ability implements Attribute {
    UNCANNY_DODGE(classTalent()
        .withDescription("Use reaction to halve attack damage from visible attacker.")),
    EVASION(classTalent()
        .withDescription("Save vs Dex for no damage, fail for half.")),

    //
    EXTRA_ATTACK(CLASS_TALENT, "Attack two times in each attack action."),
    ARCANE_RECOVERY(CLASS_TALENT, "Once per day, following a short rest, "
        + "recover [$level /^ 2] [plural(level,levels)] of expended spell slots below sixth level."),
    SPELL_MASTERY(classTalent()
        .withDescription("Choose one first and one second level spell from spellbook.")
        .withDescription("Cast at lowest level without spending a spell slot.")),
    INTIMIDATING_PRESENCE(CLASS_TALENT, "Intimidating Presence",
        "As action, one creature within 30' Wis. save vs DC[8+$prof+$chr_mod] or be frightened."),
    RETALIATION(CLASS_TALENT, "Retaliation",
        "As reaction, make melee weapon attack against creature within 5' that has caused damage."),
    TOTEM_SPIRIT_BEAR(CLASS_TALENT, "Totem Spirit (Bear)",
        "During rage, resistance to all damage except psychic."),
    TOTEM_SPIRIT_EAGLE(CLASS_TALENT, "Totem Spirit (Eagle)",
        "During rage, if not wearing heavy armour, disadvantage opportunity attacks, "
        + "take Dash bonus action."),
    TOTEM_SPIRIT_WOLF(CLASS_TALENT, "Totem Spirit (Wolf)",
        "During rage, friends have advantage on melee attacks against enemies within 5'."),
    ASPECT_OF_BEAST_BEAR(CLASS_TALENT, "Aspect of the Beast (Bear)",
        "Carrying capacity doubled. Advantage on Str. checks moving objects."),
    ASPECT_OF_BEAST_EAGLE(CLASS_TALENT, "Aspect of the Beast (Eagle)",
        "See up to 1 mile without difficulty. Dim light does not disadvantage Perception checks."),
    ASPECT_OF_BEAST_WOLF(CLASS_TALENT, "Aspect of the Beast (Wolf)",
        "Track at fast pace; stealth at normal pace."),
    TOTEMIC_ATTUNEMENT_BEAR(CLASS_TALENT, "Totemic Attunement (Bear)",
        "During rage, enemies within 5' disadvantaged on attacks on friends."),
    TOTEMIC_ATTUNEMENT_EAGLE(CLASS_TALENT, "Totemic Attunement (Eagle)",
        "During rage, fly at [$speed]'"),
    TOTEMIC_ATTUNEMENT_WOLF(CLASS_TALENT, "Totemic Attunement (Wolf)",
        "During rage, as bonus action knock enemy prone when hit with melee attack."),
    CUTTING_WORDS(CLASS_TALENT, "Cutting Words",
        "As a reaction, use Bardic Inspiration to subtract die roll from attack, ability, damage "
        + "from creature within 60'"),
    PEERLESS_SKILL(CLASS_TALENT, "Peerless Skill",
        "Use Bardic Inspiration for ability checks."),
    COMBAT_INSPIRATION(CLASS_TALENT, "Combat Inspiration",
        "Creature with Bardic Inspiration can add roll to damage or AC as reaction."),
    BATTLE_MAGIC(CLASS_TALENT, "Battle Magic",
        "Can make one weapon attack as bonus action when casting spell."),
    SHADOW_ARTS(CLASS_TALENT, "Shadow Arts",
        "Spend 2 Ki points to cast Darkness, Darkvision, Pass Without Trace or Silence."),
    FONT_OF_MAGIC(CLASS_TALENT,
        "[$level] sorcery points. As a bonus action, convert sorcery points to spell slots. "
        + "2 1st, 3 2nd, 5 3rd, 6 4th 7 5th. Or convert spell slots to sorcery points. "
        + "1 sorcery point for each level. "),
    DIVINE_SENSE(CLASS_TALENT,
        "As an action, know location of celestial, fiend or undead within 60 feet."),
    LAY_ON_HANDS(CLASS_TALENT,
        "As an action, restore HP. Restore a total of [$level * 5] HPs between each long rest. "
        + "Cure disease or neutralise poison requires 5 HP."),
    DIVINE_SMITE(CLASS_TALENT,
        "On hitting with melee attack, expend 1 spell slot to deal +1d8 radiant damage "
        + "or 2d8 to undead. +1d8 damage / extra spell slot up to 5."),
    IMPROVED_DIVINE_SMITE(CLASS_TALENT, "On hitting with melee attack, deal +1d8 radiant damage."),
    DIVINE_HEALTH(CLASS_TALENT, "Immune to disease."),
    AURA_OF_PROTECTION(CLASS_TALENT,
        "Paladin and allies within [max($level 6:10,18:30)] feet gain +[$chr_mod] to saves."),
    AURA_OF_COURAGE(CLASS_TALENT,
        "Paladin and allies within [max($level 6:10,18:30)] feet cannot be frightened."),
    CLEANSING_TOUCH(CLASS_TALENT,
        "As action, end one spell on self or willing creature touched. "
        + "Use [$chr_mod] times between long rests."),
    SACRED_WEAPON(CLASS_TALENT, "Channel Divinity: Sacred Weapon",
        "For 1 minute weapon attack +[$chr_mod] and emits bright light."),
    TURN_THE_UNHOLY(CLASS_TALENT, "Channel Divinity: Turn the Unholy",
        "Each fiend and undead within 30 feet Wis. save or turned for 1 minute."),
    AURA_OF_DEVOTION(CLASS_TALENT,
        "Paladin and allies within [max($level 7:10,18:30)] feet cannot be charmed."),
    PURITY_OF_SPIRIT(CLASS_TALENT,
        "Aberrations, celestials, elementals, fey, fiends, and undead have disadvantage on attacks "
        + "against Paladin."),
    HOLY_NIMBUS(CLASS_TALENT,
        "As an action emanate aura of sunlight. For 1 minute, within 30 feet bright light shines "
        + "and enemies take 10 radiant damage. Paladin has advantage on saves against "
        + "fiend and undead spells. Use once between each long rest. "),
    NATURES_WRATH(CLASS_TALENT, "Channel Divinity: Nature's Wrath",
        "1 creature within 10 feet restrained until Str. or Dex. save."),
    TURN_THE_FAITHLESS(CLASS_TALENT, "Channel Divinity: Turn the Faithless",
        "Fey and fiend within 30 feet Wis. save or turned for 1 minute."),
    AURA_OF_WARDING(CLASS_TALENT,
        "Paladin and allies within [max($level 7:10,18:30)] feet have resistance to "
        + "damage from spells."),
    UNDYING_SENTINEL(CLASS_TALENT,
        "When paladin would drop to 0 HP, drop to 1 HP."),
    ELDER_CHAMPION(CLASS_TALENT,
        "As an action, tranform into force of nature. For 1 minute regain 10 HP each turn,"
        + "cast spells as bonus action, enemies within 10 feet have disadvantage against "
        + "paladin spells and Channel Divinity. Use once between each long rest."),
    ABJURE_ENERGY(CLASS_TALENT, "Channel Divinity: Abjure Energy",
        "1 creature within 60 feet Wis. save or frightened for 1 minute speed 0. "
        + "On save speed halved."),
    VOW_OF_ENMITY(CLASS_TALENT, "Channel Divinity: Vow of Enmity",
        "As a bonus action mark 1 creature within 10 feet gaining advantage on attacks "
        + "for 1 minute."),
    RELENTLESS_AVENGER(CLASS_TALENT,
        "When opportunity attack hits, move up to half speed as part of reaction. "
        + "Move does not provoke opportunity attacks."),
    SOUL_OF_VENGEANCE(CLASS_TALENT,
        "As a reaction to an attack by a creature marked by Vow of Enmity attacks, "
        + "make a melee attack."),
    AVENGING_ANGEL(CLASS_TALENT,
        "As an action, transform into angelic avenger. For 1 hour fly at speed 60, "
        + "enemies coming within 30 feet Wis. save or frightened for 1 minute and "
        + "grant advantage on attacks."),
    NATURAL_EXPLORER(CLASS_TALENT,
        "Int. and Wis. checks for proficient skills related to favoured terrain are doubled. "
        + "Fast, reliable, alert, stealthy travel and effective foraging and tracking "
        + "in favoured terrain. "),

    FEY_PRESENCE(classTalent()
        .withDescription("As an action, charm or frighten creatures within a 10-foot cube "
            + "around Warlock for 1 turn. Wis. save.")),
    MISTY_ESCAPE(classTalent()
        .withDescription("As a reaction to taking damage, turn invisible and teleport up to 60 feet.")),
    BEGUILING_DEFENSES(classTalent()
        .withDescription("Immune to charm. ")
        .withDescription("As a reaction to a charm attempt, Wis. save or target charmed for 1 minute.")),
    DARK_DELERIUM(classTalent()
        .withDescription("As a reaction, charm or frighten one creature within 60 feet for 1 minute. "
            + "Wis. save.")),
    DARK_ONES_BLESSING(classTalent()
        .withDescription("On reducing enemy to 0 HP, gain [$chr_mod + $level] temporary HP.")),
    DARK_ONES_OWN_LUCK(classTalent()
        .withDescription("Add 1d10 to 1 ability check or save.")
        .withDescription("Use once between each long rest.")),
    FIENDISH_RESILIENCE(classTalent()
        .withDescription("Resistance to a chosen damage type.")),
    HURL_THROUGH_HELL(classTalent()
        .withDescription("On hitting an enemy, send away for 1 turn and cause 10d10 phychic damage.")),
    AWAKENED_MIND(classTalent()
        .withDescription("Communicate telepathically with any creature within 30 feet.")),
    ENTROPIC_WARD(classTalent()
        .withDescription("As a reaction, impose disadvantage on an attack roll.")
        .withDescription("If the attack misses, next attack against target has advantage.")
        .withDescription("Use once between each rest.")),
    THOUGHT_SHIELD(classTalent()
        .withDescription("Immune to telepathy.")
        .withDescription("Resistance to psychic damage.")
        .withDescription("Creature dealing psychic attack takes equal damage.")),
    CREATE_THRALL(classTalent()
        .withDescription("As an action, charm an incapacitated humanoid with touch.")),;

    private static class AbilityDelegate extends AttributeDelegate {

        private final AttributeType type;

        public AbilityDelegate(AttributeType type) {
            this.type = type;
        }
    }

    private static AbilityDelegate classTalent() {
        return new AbilityDelegate(CLASS_TALENT);
    }

    private static AbilityDelegate ability(AttributeType type) {
        return new AbilityDelegate(type);
    }

    private final AbilityDelegate delegate;

    public static Stream<AttributeType> getTypes() {
        return Arrays.stream(values()).map(Ability::getType).distinct();
    }

    private Ability(AttributeDelegate delegate) {
        this.delegate = (AbilityDelegate) delegate;
    }

    private Ability(AttributeType type, String description) {
        this(ability(type).withDescription(description));
    }

    private Ability(AttributeType type, String name, String description) {
        this(ability(type).withName(name).withDescription(description));
    }

    @Override
    public AttributeType getType() {
        return delegate.type;
    }

    @Override
    public String toString() {
        return delegate.getName().orElse(StringUtils.capitaliseEnumName(name()));
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return delegate.getDescription(character);
    }

    @Override
    public void generateInitialChoices(Character character) {
        delegate.generateChoices(character);
    }

    public static Ability load(AttributeType type, Node node) {
        return Ability.valueOf(node.getTextContent());
    }
}
