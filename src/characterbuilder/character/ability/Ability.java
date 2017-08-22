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
    DARKVISION(RACIAL_TALENT, "Darkness within 60' appears as dim colourless light."),
    SUPERIOR_DARKVISION(RACIAL_TALENT, "Darkvision",
        "Darkness within 120' appears as dim colourless light."),
    POISON_RESISTANCE(RACIAL_TALENT, "Poison Resilience",
        "Advantage on savings throws against poison and resistance against poison damage."),
    STONECUNNING(RACIAL_TALENT, "Double proficiency on history checks related to stonework."),
    MASK_OF_THE_WILD(RACIAL_TALENT, "Can attempt to hide when obscured by natural phenomenon."),
    LUCKY(RACIAL_TALENT, "Reroll 1s on d20 for attacks, ability checks and savings throws."),
    BRAVE(RACIAL_TALENT, "Advantage on savings throws against being frightened."),
    NIMBLE(RACIAL_TALENT, "Halfling Nimbleness", "Move through the space of larger creatures."),
    STEALTHY(RACIAL_TALENT, "Naturally Stealthy",
        "Can attempt to hide when obscured by larger creature."),
    BREATH_WEAPON(RACIAL_TALENT, "Breath: [$breath] vs DC [8+$con_mod+$prof] or "
        + "[max($level 1:2d6,6:3d6,11:4d6,16:5d6)] damage"),
    SUNLIGHT_SENSITIVITY(RACIAL_TALENT,
        "Disadvantage on attack and perception when target is in direct sunlight"),
    GNOME_CUNNING(RACIAL_TALENT, "Advantage on Int., Wis. and Chr. saves vs. magic."),
    ARTIFICERS_LORE(RACIAL_TALENT, "Artificer's Lore",
        "+[2*$prof] on history check related to devices."),
    TINKER(RACIAL_TALENT, "Construct tiny clockwork devices (1 hour, 10GP)."),
    SPEAK_WITH_SMALL_BEASTS(RACIAL_TALENT, "Communicate simple ideas to small animals."),
    FEY_ANCESTORY(RACIAL_TALENT, "Avantage on save vs. charm; magic cannot cause sleep."),
    RELENTLESS_ENDURANCE(RACIAL_TALENT, "Drop to 1HP rather than 0HP once between long rests."),
    SAVAGE_ATTACKS(RACIAL_TALENT, "+1 damage die roll on critical hits."),
    HELLISH_RESISTANCE(RACIAL_TALENT, "Resistance to fire damage."),
    //
    SECOND_WIND(classTalent()
        .withDescription("Use a bonus action to regain 1d10+[$level] hit points.")
        .withDescription("Use once between each rest.")),
    ACTION_SURGE(classTalent()
        .withDescription("Take an additional action [max($level 2:one time, 17:two times)] "
            + "between each rest.")),
    IMPROVED_CRITICAL(CLASS_TALENT, "Score a critical on a roll of 19 or 20."),
    SUPERIOR_CRITICAL(CLASS_TALENT, "Improved Critical",
        "Score a critical on a roll of 18, 19 or 20."),
    SURVIVOR(CLASS_TALENT,
        "Regain [5 + $con_mod] HP each turn if no more than [$hp / 2] hit points remaining."),
    COMBAT_SUPERIORITY(classTalent()
        .withDescription("Can use one maneuver per attack.")
        .withDescription("[max($level 3:4,7:5,15:6)] [max($level 3:1d8,10:1d10,18:1d12)] "
            + "superiority dice.")
        .withDescription("Regain expended superiority dice after rest.")
        .withDescription("Maneuver save DC [8+$prof+max($str_mod,$dex_mod)].")),
    KNOW_YOUR_ENEMY(classTalent()
        .withDescription("After 1 minute of observation, "
            + "know relative strength of two characteristics: "
            + "Strength, Dexterity, Consitution, AC, Current HP, Total Levels, Fighter Level.")),
    RELENTLESS(classTalent()
        .withDescription("On initiative, regain 1 superiority die if none remaining.")),
    WAR_MAGIC(classTalent()
        .withDescription("On casting a [max($level 7:cantrip,18:spell)], "
            + "make one weapon attack as a bonus action.")),
    ELDRITCH_STRIKE(classTalent()
        .withDescription("On hitting with a weapon, "
            + "for one turn target has disadvantage on saves against spells.")),
    ARCANE_CHARGE(classTalent()
        .withDescription("On Action Surge, teleport up to 30 feet.")),
    REMARKABLE_ATHLETE(CLASS_TALENT,
        "Add [$prof / 2] to Str, Dex and Con checks. Add [$str_mod]' to running long jump length."),
    EXTRA_ATTACK_FIGHTER(CLASS_TALENT, "Extra Attack",
        "Attack [max($level 5:two, 11:three, 20:four)] times in each attack action."),
    EXTRA_ATTACK(CLASS_TALENT, "Attack two times in each attack action."),
    INDOMITABLE(CLASS_TALENT, "Reroll [max($level 9:one, 13:two, 17:three)] "
        + "failed saving [plural(throw,throws)] between each rest."),
    ARCANE_RECOVERY(CLASS_TALENT, "Once per day, following a short rest, "
        + "recover [$level /^ 2] [plural(level,levels)] of expended spell slots below sixth level."),
    SPELL_MASTERY(classTalent()
        .withDescription("Choose one first and one second level spell from spellbook.")
        .withDescription("Cast at lowest level without spending a spell slot.")),
    SNEAK_ATTACK(CLASS_TALENT,
        "Extra [$level /^ 2]d6 damage on one attack with advantage each turn."),
    CUNNING_ACTION(CLASS_TALENT,
        "Bonus dash, disengage or hide action each turn"),
    UNCANNY_DODGE(CLASS_TALENT,
        "Use reaction to halve attack damage from visible attacker."),
    EVASION(CLASS_TALENT, "Save vs Dex for no damage, fail for half."),
    RELIABLE_TALENT(CLASS_TALENT, "Treat 9 or lower as 10 on proficient ability checks."),
    BLINDSENSE(CLASS_TALENT, "Aware of invisible creatures within 10'"),
    SLIPPERY_MIND(CLASS_TALENT, "Add proficiency to save vs Wis."),
    ELUSIVE(CLASS_TALENT, "No attack roll has advantage if not incapacitated."),
    STROKE_OF_LUCK(CLASS_TALENT, "Stroke of Luck",
        "Turn a missed attack into a hit or failed ability check "
        + "into success one between each rest."),
    FAST_HANDS(CLASS_TALENT, "Fast Hands",
        "Use Cunning Action to perform sleight of hand, disarm trap, open lock or use item."),
    SECOND_STORY_WORK(CLASS_TALENT, "Second-Story Work",
        "Climb at normal speed. Add [$dex_mod]' to running long jump length."),
    SUPREME_SNEAK(CLASS_TALENT, "Supreme Sneak",
        "Advantage on all stealth checks if moving no more than [$speed/2]' on the same turn."),
    USE_MAGIC_DEVICES(CLASS_TALENT, "Use Magic Devices",
        "Ignore all class, race and level requirements on the use of magic items."),
    THIEFS_REFLEXES(CLASS_TALENT, "Thief's Reflexes",
        "Take two turns during first round of combat. Second turn is at initiative - 10."),
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
