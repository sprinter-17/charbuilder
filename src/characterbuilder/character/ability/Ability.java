package characterbuilder.character.ability;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.*;
import characterbuilder.utils.StringUtils;
import java.util.Arrays;
import java.util.stream.Stream;

public enum Ability implements Attribute {
    DARKVISION(RACIAL_TALENT, "Darkvision",
        "Darkness within 60' appears as dim colourless light."),
    SUPERIOR_DARKVISION(RACIAL_TALENT, "Darkvision",
        "Darkness within 120' appears as dim colourless light."),
    POISON_RESISTANCE(RACIAL_TALENT, "Stout Resilience",
        "Advantage on savings throws against poison and resistance against poison damage."),
    STONECUNNING(RACIAL_TALENT, "Stonecunning",
        "Double proficiency on history checks related to stonework."),
    MASK_OF_THE_WILD(RACIAL_TALENT, "Mask of the Wild",
        "Can attempt to hide when obscured by natural phenomenon."),
    LUCKY(RACIAL_TALENT, "Lucky",
        "Reroll 1s on d20 for attacks, ability checks and savings throws."),
    BRAVE(RACIAL_TALENT, "Brave",
        "Advantage on savings throws against being frightened."),
    NIMBLE(RACIAL_TALENT, "Halfling Nimbleness",
        "Move through the space of larger creatures."),
    STEALTHY(RACIAL_TALENT, "Naturally Stealthy",
        "Can attempt to hide when obscured by larger creature."),
    BREATH_WEAPON(RACIAL_TALENT, "Breath Weapon",
        "Breath: [$breath] vs DC [8+$con_mod+$prof] or "
        + "[max($level 1:2d6,6:3d6,11:4d6,16:5d6)] damage"),
    SUNLIGHT_SENSITIVITY(RACIAL_TALENT, "Sunlight Sensitivity",
        "Disadvantage on attack and perception when target is in direct sunlight"),
    GNOME_CUNNING(RACIAL_TALENT, "Gnome Cunning",
        "Advantage on Int., Wis. and Chr. saves vs. magic."),
    ARTIFICERS_LORE(RACIAL_TALENT, "Artificer's Lore",
        "+[2*$prof] on history check related to devices."),
    TINKER(RACIAL_TALENT, "Tinker",
        "Construct tiny clockwork devices (1 hour, 10GP)."),
    SPEAK_WITH_SMALL_BEASTS(RACIAL_TALENT, "Speak with Small Beasts",
        "Communicate simple ideas to small animals."),
    FEY_ANCESTORY(RACIAL_TALENT, "Fey Ancestory",
        "Avantage on save vs. charm; magic cannot cause sleep."),
    RELENTLESS_ENDURANCE(RACIAL_TALENT, "Relentless Endurance",
        "Drop to 1HP rather than 0HP once between long rests."),
    SAVAGE_ATTACKS(RACIAL_TALENT, "Savage Attacks",
        "+1 damage die roll on critical hits."),
    HELLISH_RESISTANCE(RACIAL_TALENT, "Hellish Resistance",
        "Resistance to fire damage."),
    //
    ARCHERY(FIGHTING_STYLE, "Archery",
        "+2 attack bonus with ranged weapons."),
    DEFENSE(FIGHTING_STYLE, "Defense",
        "+1 AC when wearing armour."),
    DUELING(FIGHTING_STYLE, "Dueling",
        "+2 damage with melee weapon when other hand is empty."),
    GREAT_WEAPON(FIGHTING_STYLE, "Great Weapon",
        "Reroll 1s and 2s for damage when using a two handed weapon."),
    PROTECTION(FIGHTING_STYLE, "Protection",
        "Disadvantage attacks against other targets with 5' when using a shield."),
    TWO_WEAPON(FIGHTING_STYLE, "Two Weapon",
        "Add ability modifier to damage of second weapon attack."),
    //
    SECOND_WIND(CLASS_TALENT, "Second Wind",
        "Use a bonus action to regain 1d10+[$level] hit points. Use once between each rest. "),
    ACTION_SURGE(CLASS_TALENT, "Action Surge",
        "Take an additional action [max($level 2:one time, 17:two times)] between each rest."),
    IMPROVED_CRITICAL(CLASS_TALENT, "Improved Critical",
        "Score a critical on a roll of 19 or 20."),
    SUPERIOR_CRITICAL(CLASS_TALENT, "Improved Critical",
        "Score a critical on a roll of 18, 19 or 20."),
    SURVIVOR(CLASS_TALENT, "Survivor",
        "Regain [5 + $con_mod] HP each turn if no more than [$hp / 2] hit points remaining."),
    REMARKABLE_ATHLETE(CLASS_TALENT, "Remarkable Athlete",
        "Add [$prof / 2] to Str, Dex and Con checks. Add [$str_mod]' to running long jump length."),
    EXTRA_ATTACK(CLASS_TALENT, "Extra Attack", "Attack [max($level 5:two, 11:three, 20:four)] "
        + "times in each attack action."),
    INDOMITABLE(CLASS_TALENT, "Indomitable", "Reroll [max($level 9:one, 13:two, 17:three)] "
        + "failed saving [plural(throw,throws)] between each rest."),
    ARCANE_RECOVERY(CLASS_TALENT, "Arcane recovery", "Once per day, following a short rest, "
        + "recover [$level /^ 2] [plural(level,levels)] of expended spell slots below sixth level."),
    SNEAK_ATTACK(CLASS_TALENT, "Sneak Attack",
        "Extra [$level /^ 2]d6 damage on one attack with advantage each turn."),
    CUNNING_ACTION(CLASS_TALENT, "Cunning Action",
        "Bonus dash, disengage or hide action each turn"),
    UNCANNY_DODGE(CLASS_TALENT, "Uncanny Dodge",
        "Use reaction to halve attack damage from visible attacker."),
    EVASION(CLASS_TALENT, "Evasion",
        "Save vs Dex for no damage, fail for half."),
    RELIABLE_TALENT(CLASS_TALENT, "Reliable Talent",
        "Treat 9 or lower as 10 on proficient ability checks."),
    BLINDSENSE(CLASS_TALENT, "Blindsense", "Aware of invisible creatures within 10'"),
    SLIPPERY_MIND(CLASS_TALENT, "Slippery Mind", "Add proficiency to save vs Wis."),
    ELUSIVE(CLASS_TALENT, "Elusive",
        "No attack roll has advantage if not incapacitated."),
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
    UNARMORED_DEFENCE(CLASS_TALENT, "Unarmoured Defence",
        "Unarmoured AC[10+$dex_mod+$con_mod]"),
    RECKLESS_ATTACK(CLASS_TALENT, "Reckless Attack",
        "Choose to lower defence: advantage melee attacks for, all attacks against"),
    DANGER_SENSE(CLASS_TALENT, "Danger Sense",
        "Advantage on Dex. saving throws against visible effects"),
    FAST_MOVEMENT(CLASS_TALENT, "Fast Movement",
        "+10' speed when unarmoured."),
    FERAL_INSTINCTS(CLASS_TALENT, "Feral Instincts",
        "Advantage on initiative, enter rage and act normally on surprise."),
    BRUTAL_CRITICAL(CLASS_TALENT, "Brutal Critical",
        "Roll [max($level 9:1,13:2,17:3)] extra [plural(die,dice)] damage on critical."),
    RAGE(CLASS_TALENT, "Rage",
        "Bonus action enter rage for 1 minute. "
        + "Advantage Str. checks, +[max($level 1:2,9:3,16:4)] dam on melee attacks, "
        + "resistance to damage. "
        + "Use [max($level 1:2,6:4,12:5,17:6)] times between long rests; "),
    RELENTLESS_RAGE(CLASS_TALENT, "Relentless Rage",
        "When dropping to 0HP during rage, make Con. save vs DC10 (+5 per use) "
        + "to drop to 1HP instead."),
    PERSISTENT_RAGE(CLASS_TALENT, "Persistent Rage",
        "Rage continues until ended voluntarily or falls unconscious."),
    INDOMITABLE_MIGHT(CLASS_TALENT, "Indomitable Might",
        "Str check minimum [$str]."),
    FRENZY(CLASS_TALENT, "Frenzy",
        "Can enter frenzy during rage. Melee weapon attack as bonus action each turn. "
        + "Exhaustion when rage ends."),
    MINDLESS_RAGE(CLASS_TALENT, "Mindless Rage",
        "Cannot be charmed or frightened during rage."),
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
    BARDIC_INSPIRATION(CLASS_TALENT, "Bardic Inspiration",
        "As bonus action inspire 1 creature within 60'; "
        + "add [max($level 1:d6,5:d8,10:d10,15:d12)] to one ability, attack or save; "
        + "use [$chr_mod] [plural(time,times)] between long rests"),
    JACK_OF_ALL_TRADES(CLASS_TALENT, "Jack of All Trades",
        "Add +[$prof/2] to non-proficient ability checks."),
    SONG_OF_REST(CLASS_TALENT, "Song of Rest",
        "During short rests friends regain additional 1d[max($level 2:6,9:8,13:10,17:12)]HP"),
    FONT_OF_INSPIRATION(CLASS_TALENT, "Font of Inspiration",
        "Regain all Bardic Inspirations in short and long rests."),
    COUNTERCHARM(CLASS_TALENT, "Counter-charm",
        "As an action, all friends within 30' have advantage of saves vs fear and charm."),
    SUPERIOR_INSPIRATION(CLASS_TALENT, "Superior Inspiration",
        "Regain 1 Bardic Inspiration on initiative roll, if no uses left."),
    CUTTING_WORDS(CLASS_TALENT, "Cutting Words",
        "As a reaction, use Bardic Inspiration to subtract die roll from attack, ability, damage "
        + "from creature within 60'"),
    PEERLESS_SKILL(CLASS_TALENT, "Peerless Skill",
        "Use Bardic Inspiration for ability checks."),
    COMBAT_INSPIRATION(CLASS_TALENT, "Combat Inspiration",
        "Creature with Bardic Inspiration can add roll to damage or AC as reaction."),
    BATTLE_MAGIC(CLASS_TALENT, "Battle Magic",
        "Can make one weapon attack as bonus action when casting spell."),
    KNOWLEDGE_OF_THE_AGES(DIVINE_DOMAIN_ABILITY, "Channel Divinity: Knowledge of the Ages",
        "As an action, gain proficiency with one skill or tool for 10 minutes."),
    READ_THOUGHTS(DIVINE_DOMAIN_ABILITY, "Channel Divinity: Read Thoughts",
        "As an action, choose 1 creature within 60'. Wis. save or read thoughts "
        + "and cast suggestion."),
    POTENT_SPELLCASTING(DIVINE_DOMAIN_ABILITY, "Channel Divinity: Potent Spellcasting",
        "Add [$wis_mod] to damage from cantrips."),
    VISIONS_OF_THE_PAST(DIVINE_DOMAIN_ABILITY, "Visions of the Past",
        "After 1 minute of mediation, object and area reading"),
    //
    EVOCATION_SAVANT(EVOCATION_ABILITY,
        "Evocation Savant",
        "Halve the time and cost to copy evocation spells."),
    SCULPT_SPELLS(EVOCATION_ABILITY, "Sculpt Spells",
        "Cause up to 1 + spell level creatures automatically save on evocation spells."),
    POTENT_CANTRIP(EVOCATION_ABILITY, "Potent Cantrip",
        "Targets savings against cantrips take half damage."),
    EMPOWERED_EVOCATION(EVOCATION_ABILITY, "Empowered Evocation",
        "Add [$int_mode] to damage of evocation spells."),
    OVERCHANNEL(EVOCATION_ABILITY, "Overchannel",
        "Deal maximum damage on spell of level 5 or less. "
        + "More than one use between rests causes necrotic damage."),
    //
    TURN_UNDEAD(DIVINE_DOMAIN_ABILITY, "Channel Divinity: Turn Undead",
        "Each undead within 30' save vs Wis or turned for 1 minute."),
    DESTROY_UNDEAD(DIVINE_DOMAIN_ABILITY, "Destroy Undead",
        "Turned undead with CR of [max($level 5:1/2, 8:1, 11:2, 14:3, 17:4)] or less are destroyed"),
    DIVINE_INTERVENTION(DIVINE_DOMAIN_ABILITY, "Divine Intervention",
        "[if($level<20:$level% chance of deity intervening.:Deity intervenes.)] "
        + "7 days between each successful use. 1 day between each unsuccessful use."),
    CHANNEL_DIVINITY(DIVINE_DOMAIN_ABILITY, "Channel Divinity",
        "Use [max($level 2:one, 6:two, 18:three)] channel divinity [plural(power,powers)] "
        + "between each rest."),
    PRESERVE_LIFE(DIVINE_DOMAIN_ABILITY, "Channel Divinity: Preserve Life",
        "Restore [$level*5] total HP to creatures within 30' up to half their maximum."),
    BLESSED_HEALER(DIVINE_DOMAIN_ABILITY, "Blessed Healer",
        "Healing spell also restore 2 + spell level HP to cleric."),
    DIVINE_STRIKE(DIVINE_DOMAIN_ABILITY, "Divine Strike",
        "Weapon attacks cause an additional [max($level 8:1,14:2)]d8 radiant damage."),
    SUPREME_HEALING(DIVINE_DOMAIN_ABILITY, "Supreme Healing",
        "Healing spells restore maximum HP."),
    DISCIPLE_OF_LIFE(DIVINE_DOMAIN_ABILITY, "Disciple of life",
        "Healing spells restore an additional 2 + spell level HP."),
    //
    CITY_SECRETS(BACKGROUND_FEATURE, "City Secrets",
        "When not in combat, travel at double speed between locations in city."),
    SHIPS_PASSAGE(BACKGROUND_FEATURE, "Ship's Passage",
        "Can secure free passage on sailing ships."),
    RESEARCHER(BACKGROUND_FEATURE, "Researcher",
        "Can attempt to obtain a piece of lore."),
    WANDERER(BACKGROUND_FEATURE, "Wanderer",
        "Excellent memory for maps and geography. Can find food if available."),
    DISCOVERY(BACKGROUND_FEATURE, "Discovery",
        "Have made a unique and powerful discovery.");

    private final AttributeType type;
    private final String name;
    private final String description;

    public static Stream<AttributeType> getTypes() {
        return Arrays.stream(values()).map(Ability::getType).distinct();
    }

    private Ability(AttributeType type, String name, String description) {
        this.type = type;
        this.name = name;
        this.description = description;
    }

    @Override
    public AttributeType getType() {
        return type;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return Stream.of(StringUtils.expand(description, character));
    }
}
