package characterbuilder.character.ability;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeDelegate;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.*;
import static characterbuilder.character.choice.ChoiceGenerator.cantripChoice;
import characterbuilder.character.equipment.AdventureGear;
import characterbuilder.character.spell.Spell;
import static characterbuilder.character.spell.Spell.getSpellsAtLevel;
import characterbuilder.character.spell.SpellAbility;
import characterbuilder.character.spell.SpellCasting;
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
    ARCHERY(FIGHTING_STYLE, "+2 attack bonus with ranged weapons."),
    DEFENSE(FIGHTING_STYLE, "+1 AC when wearing armour."),
    DUELING(FIGHTING_STYLE, "+2 damage with melee weapon when other hand is empty."),
    GREAT_WEAPON(FIGHTING_STYLE, "Reroll 1s and 2s for damage when using a two handed weapon."),
    PROTECTION(FIGHTING_STYLE,
        "Disadvantage attacks against other targets with 5' when using a shield."),
    TWO_WEAPON(FIGHTING_STYLE, "Add ability modifier to damage of second weapon attack."),
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
    UNARMORED_DEFENCE_BARBARIAN(CLASS_TALENT, "Unarmoured Defence",
        "Unarmoured AC[10+$dex_mod+$con_mod]."),
    UNARMORED_DEFENCE_MONK(CLASS_TALENT, "Unarmoured Defence",
        "Unarmoured AC[10+$dex_mod+$wis_mod]"),
    RECKLESS_ATTACK(CLASS_TALENT, "Reckless Attack",
        "Choose to attack recklessly gaining and giving advantage on melee attacks."),
    DANGER_SENSE(CLASS_TALENT, "Danger Sense",
        "Advantage on Dex. saving throws against visible effects"),
    FAST_MOVEMENT(CLASS_TALENT, "Fast Movement",
        "+10' speed when unarmoured."),
    FERAL_INSTINCTS(CLASS_TALENT, "Feral Instincts",
        "Advantage on initiative, enter rage and act normally on surprise."),
    BRUTAL_CRITICAL(CLASS_TALENT, "Brutal Critical",
        "Roll [max($level 9:1,13:2,17:3)] extra [plural(die,dice)] damage on critical."),
    RAGE(classTalent()
        .withDescription("As bonus action, enter rage for 1 minute. ")
        .withDescription("Advantage on Str. checks and saves.")
        .withDescription("+[max($level 1:2,9:3,16:4)] dam on melee attacks. ")
        .withDescription("Resistance to bludgeoning, piercing and slashing damage. "
            + "Use [max($level 1:2,6:4,12:5,17:6)] times between long rests. ")),
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
    MARTIAL_ARTS(CLASS_TALENT, "Martial Arts",
        "Use Dex. instead of Str. for unarmed strikes and monk weapons; "
        + "can roll [max($level 1:1d4,5:1d6,11:1d8,17:1d10)] for damage; "
        + "unarmed strike as bonus action"),
    KI(CLASS_TALENT, "Ki",
        "[$level] Ki points to use between each rest; Ki Save DC[8+$prof+$wis_mod]."),
    FLURRY_OF_BLOWS(CLASS_TALENT, "Flurry of Blows",
        "Spend 1 Ki point to make two unarmed strikes as a bonus action."),
    PATIENT_DEFENCE(CLASS_TALENT, "Patient Defence",
        "Spend 1 Ki point to Dodge as a bonus action."),
    STEP_OF_THE_WIND(CLASS_TALENT, "Step of the Wind",
        "Spend 1 Ki point to Disengage or Dash as a bonus action, jump distance doubled."),
    UNARMOURED_MOVEMENT(CLASS_TALENT, "Unarmoured Movement",
        "Speed increases by [max($level 2:10,6:15,10:20,14:25,18:30)]' when not armoured. "
        + "[if($level >= 9:Move along vertical surfaces and across liquids)]"),
    DEFLECT_MISSILES(CLASS_TALENT, "Deflect Missiles",
        "Use reaction when hit by ranged attack reducing damage by 1d10+[$dex_mod+$level]. "
        + "If damage is 0, can spend 1 Ki point to make ranged attack to hit +[$prof], "
        + "martial arts damage range 20'/60'."),
    SLOW_FALL(CLASS_TALENT, "Slow Fall",
        "Use reaction to reduce falling damage by [5*$level]."),
    STUNNING_STRIKE(CLASS_TALENT, "Stunning Strike",
        "Spend 1 Ki point on melee weapon hit. "
        + "Target Con. save or stunned until end of next turn."),
    KI_EMPOWERED_STRIKES(CLASS_TALENT, "Ki-Empowered Strikes",
        "Unarmed strikes could as magical."),
    STILLNESS_OF_MIND(CLASS_TALENT, "Stillness of Mind",
        "Use action to end charm or fear effect."),
    PURITY_OF_BODY(CLASS_TALENT, "Purity of Body",
        "Immune to disease and poison."),
    TONGUE_OF_THE_SUN_AND_MOON(CLASS_TALENT, "Tongue of the Sun and Moon",
        "Understand all spoken languages."),
    DIAMOND_SOUL(CLASS_TALENT, "Diamond Soul",
        "Proficiency in all saves."),
    TIMELESS_BODY(CLASS_TALENT, "Timeless Body",
        "Cannot be magically aged. Need no food or water."),
    EMPTY_BODY(CLASS_TALENT, "Empty Body",
        "Spend 4 Ki points to become invisible for 1 minute."),
    PERFECT_SELF(CLASS_TALENT, "Perfect Self",
        "Regain 4 Ki points on initiative if none remaining."),
    OPEN_HAND_TECHNIQUE(CLASS_TALENT, "Open Hand Technique",
        "When hitting a creature with Flurry of Blows, impose one effect: "
        + "Dex. save or knocked prone; Str save or push 15'; no reactons until end of next turn."),
    WHOLENESS_OF_BODY(CLASS_TALENT, "Wholeness of Body",
        "As an action, regain [$level*3]HP once between each long rest."),
    TRANQUILITY(CLASS_TALENT, "Tranquility",
        "At the end of a long rest, gain sanctuary. "
        + "Attacker must make Wis. save DC[8+$wis_mod+$prof] or target another. "
        + "Attacking ends the effect."),
    QUIVERING_PALM(CLASS_TALENT, "Quivering Palm",
        "Spend 3 Ki points to start vibrations that last for [$level] days. "
        + "At end, Con. save or reduce to 0HP. Succeed on save 10d10 necrotic dam."),
    SHADOW_ARTS(CLASS_TALENT, "Shadow Arts",
        "Spend 2 Ki points to cast Darkness, Darkvision, Pass Without Trace or Silence."),
    SHADOW_STEP(CLASS_TALENT, "Shadow Step",
        "When in dim light or darkness, as a bonus action teleport up to 60'."),
    CLOAK_OF_SHADOWS(CLASS_TALENT, "Cloak of Shadows",
        "When in dim light or darkness, as an action become invisible."),
    OPPORTUNIST(CLASS_TALENT, "Opportunist",
        "As a reaction, when a creature within 5' that is hit, make a melee attack."),
    DISCIPLE_OF_THE_ELEMENTS(CLASS_TALENT, "Disciple of the Elements",
        "Spend up to [max($level 3:2,5:3,9:4,13:5,17:7)] Ki points to cast an elemental spell."),
    WILD_SHAPE(CLASS_TALENT,
        "As an action, assume the shape of a beast of up to CR[max($level 2:1/4,4:1/2,8:1)]."),
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
    PRIMEVAL_AWARENESS(CLASS_TALENT,
        "As an action, expend a spell slot. For 1 minute / spell level sense presence of "
        + "aberrations, celestials, dragons, elementals, fey, fiends and undead within 1 mile or "
        + "6 miles within favoured terrain."),
    LANDS_STRIDE(CLASS_TALENT, "Land's Stride",
        "Move through difficult terrain at normal speed. Avoid damage from plants. "
        + "Advantage on saves vs. plants magically impeding movement. "),
    HIDE_IN_PLAIN_SIGHT(CLASS_TALENT,
        "Spend 1 minute creating camouflage and hiding against solid surface to gain "
        + "+10 to Stealth checks. "),
    VANISH(CLASS_TALENT,
        "Hide as a bonus action. Cannot be tracked."),
    FERAL_SENSES(CLASS_TALENT,
        "Can attack invisible creatures without disadvantage. "
        + "Aware of invisible creatures within 30 feet"),
    FOE_SLAYER(CLASS_TALENT,
        "Once each turn add +[$wis_mod] to attack or damage against favoured enemy."),
    COLOSSUS_SLAYER(CLASS_TALENT,
        "Once each turn add 1d8 damage to a creature that is below maximum HP."),
    GIANT_KILLER(CLASS_TALENT, "As a reaction attack a large creature that hits or misses."),
    HORDE_BREAKER(CLASS_TALENT,
        "Once each turn make a second melee attack against a different target within 5 feet."),
    ESCAPE_THE_HORDE(CLASS_TALENT, "Opportunity attacks by enemies are disadvantaged."),
    MULTIATTACK_DEFENSE(CLASS_TALENT, "Multiattck Defence",
        "+4 AC for second and subsequent attacks by an enemy within a single turn."),
    STEEL_WILL(CLASS_TALENT, "Advantage on save vs. fear."),
    VOLLEY(CLASS_TALENT,
        "Make ranged attacks against any number of targets within 10 feet of a chosen point."),
    WHIRLWIND_ATTACK(CLASS_TALENT,
        "Make melee attacks against any number of targets within 5 feet."),
    STAND_AGAINST_THE_TIDE(CLASS_TALENT,
        "As a reaction force an enemy that misses to repeat the attack on another creature."),
    RANGERS_COMPANION(CLASS_TALENT, "Ranger's Companion",
        "As an action, command the beast to Attack, Dash, Disengage, Dodge or Help."),
    EXCEPTIONAL_TRAINING(CLASS_TALENT,
        "On any turns when the Ranger's companion does not attack, as a bonus action command the "
        + "beast can Dash, Disengage, Dodge or Help."),
    BESTIAL_FURY(CLASS_TALENT, "Ranger's companion can make two attacks."),
    SHARE_SPELLS(CLASS_TALENT,
        "Any spells targeting self can also effect Ranger's companion within 30 feet."),

    // Warlock abilities
    PACT_OF_THE_CHAIN(classTalent()
        .withDescription("Familiar can be imp, pseudodragon, quasit or sprite.")
        .withDescription("Forgo an attack to allow familiar to make attack as reaction.")
        .withSpellAbility(Spell.FIND_FAMILIAR, CHARISMA)),
    PACT_OF_THE_BLADE(classTalent()
        .withDescription("As an action, create a pact weapon in any form.")
        .withDescription("Gain proficiency with pact weapon.")),
    PACT_OF_THE_TOME(classTalent()
        .withDescription("Cast cantrips from Book of Shadows.")
        .withEquipment(AdventureGear.BOOK_OF_SHADOWS)
        .withChoice(cantripChoice(3, "Book of Shadow Cantrips", CHARISMA, getSpellsAtLevel(0)))),
    ELDRITCH_MASTER(classTalent()
        .withDescription("Spend 1 minute to regain all spell slots once between each long rest.")),

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
        .withDescription("As an action, charm an incapacitated humanoid with touch.")),

    /*
     * Divine domain abilities
    **/
    KNOWLEDGE_OF_THE_AGES(DIVINE_DOMAIN_ABILITY, "Channel Divinity: Knowledge of the Ages",
        "As an action, gain proficiency with one skill or tool for 10 minutes."),
    READ_THOUGHTS(DIVINE_DOMAIN_ABILITY, "Channel Divinity: Read Thoughts",
        "As an action, choose 1 creature within 60'. Wis. save or read thoughts "
        + "and cast suggestion."),
    POTENT_SPELLCASTING(DIVINE_DOMAIN_ABILITY, "Channel Divinity: Potent Spellcasting",
        "Add [$wis_mod] to damage from cantrips."),
    VISIONS_OF_THE_PAST(DIVINE_DOMAIN_ABILITY, "Visions of the Past",
        "After 1 minute of mediation, object and area reading"),
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

    MAGIC_SCHOOL_SAVANT(classTalent()
        .withDescription("Halve gold and time to copy [$magic_school] spells into spellbook.")),
    ARCANE_WARD(classTalent()
        .withDescription("On casting Abjuration spell of 1st level or higher, "
            + "gain [$level * 2 + $int_mod] temporary HP.")
        .withDescription("When at 0 HP, regain 2x spell level when casting Abjuration spell.")),
    PROJECTED_WARD(classTalent()
        .withDescription("As a reaction, use Arcane Ward to absorb damage from ally within 30 feet.")),
    IMPROVED_ABJURATION(classTalent()
        .withDescription("Add [$prof] to ability checks when casting Abjuration spells.")),
    SPELL_RESISTANCE(classTalent()
        .withDescription("Advantage on saves vs. spells.")
        .withDescription("Resistance to spell damage.")),

    SCULPT_SPELLS(classTalent()
        .withDescription("Up to 1 + spell level allies automatically save on evocation spells.")),
    POTENT_CANTRIP(classTalent()
        .withDescription("Targets savings against cantrips take half damage.")),
    EMPOWERED_EVOCATION(classTalent()
        .withDescription("Add [$int_mode] to damage of evocation spells.")),
    OVERCHANNEL(classTalent()
        .withDescription("Deal maximum damage on spell of level 5 or less.")
        .withDescription("After first use take 2d12 + 1d12 / use necrotic damage per spell level.")),

    MINOR_CONJURATION(classTalent()
        .withDescription("As an action, conjure an inanimate object within 10 feet.")),
    BENIGN_TRANSPOSITION(classTalent()
        .withDescription("As an action, teleport up to 30 feet or swap places with ally.")
        .withDescription("Use once between each long rest or each conjuration spell.")),
    FOCUSED_CONJURATION(classTalent()
        .withDescription("Concentration for a conjuration spell cannot be broken by damage.")),
    DURABLE_SUMMONS(classTalent()
        .withDescription("Conjured or created creatures have 30 temporary HP.")),

    PORTENT(classTalent()
        .withDescription("Roll [max($level 1:2,14:3)]d20 after each long rest. "
            + "Cause any rolls to use one of these values.")),
    EXPERT_DIVINATION(classTalent()
        .withDescription("On casting a divination spell beween 2nd and 5th level, "
            + "regain a lower level spell slot.")),
    THE_THIRD_EYE(classTalent()
        .withDescription("As an action, gain <em>Darkvision</em>, <em>Ethereal Sight</em>, "
            + "<em>Greater Comprehension</em> or <em>See Invisibility</em> until next rest.")
        .withDescription("Use once between each long rest.")),
    GREATER_PORTENT(classTalent()),
    HYPNOTIC_GAZE(classTalent()
        .withDescription("As an action, attempt to charm 1 creature within 5 feet.")
        .withDescription("Wis. save DC[$spell_dc] or charmed for 1 turn, speed 0, incapacitated.")),
    INSTINCTIVE_CHARM(classTalent()
        .withDescription("As a reaction on being attacked, divert attack to closest creature. "
            + "Wis. save DC[$spell_dc].")),
    SPLIT_ENCHANTMENT(classTalent()
        .withDescription("Enchantment spells that target 1 creature can target a 2nd creature.")),
    ALERT_MEMORIES(classTalent()
        .withDescription("Charmed creatures are unaware of echantment.")
        .withDescription("As an action, force charmed creature to lose up to [1+$chr_mod] hours "
            + "of duration of enchantment. Int. save DC[$spell_dc]")),
    IMPROVED_MINOR_ILLUSION(classTalent()
        .withDescription("On casting <em>Minor Illusion</em>, both sound and image created.")
        .withAction("Cantrip", ch -> {
            Attribute minorIllusion = new SpellAbility(Spell.MINOR_ILLUSION, INTELLIGENCE);
            if (ch.hasAttribute(minorIllusion))
                ch.pushChoice(cantripChoice(1, INTELLIGENCE));
            else
                ch.addAttribute(minorIllusion);
        })),
    MALLEABLE_ILLUSIONS(classTalent()
        .withDescription("As an action, change the nature of illusion with duration "
            + "of at least 1 minute.")),
    ILLUSORY_SELF(classTalent()
        .withDescription("As a reaction on being attacked, interpose illusory duplicate that causes "
            + "attack to miss.")
        .withDescription("Use once between each rest.")),
    ILLUSORY_REALITY(classTalent()
        .withDescription("As a bonus action, make an illusion real for 1 minute.")),
    GRIM_HARVEST(classTalent()
        .withDescription("On killing a creature with a spell, regain 2x spell level HP, "
            + "or 3x for Necromancy spells.")
        .withDescription("No effect on killing undead or constructs.")),
    UNDEAD_THRALLS(classTalent()
        .withDescription("When casting <em>Animate Dead</em>, target an additional corpse or bones.")
        .withDescription("Undead created with Necromancy have +[$level] HP and +[$prof] weapon damage.")
        .withAction("Gain Animate Dead", ch -> {
            SpellCasting wizardCasting = ch.getSpellCasting("Wizard");
            if (!wizardCasting.hasLearntSpell(Spell.ANIMATE_DEAD))
                wizardCasting.addLearntSpell(Spell.ANIMATE_DEAD);
        })),
    INURED_TO_UNDEATH(classTalent()
        .withDescription("Resistance to necrotic damage and maximum HP cannot be reduced.")),
    COMMAND_UNDEAD(classTalent()
        .withDescription("As an action, control 1 undead within 60 feet. Chr. save DC[$spell_dc].")
        .withDescription("Undead with Int. of 8 have advantage on save. Int. of 12 can repeat save "
            + "each hour.")),
    MINOR_ALCHEMY(classTalent()
        .withDescription("Change the substance of 1 physical object for 1 hour. "
            + "Requires 10 minutes per cubic foot of material.")),
    TRANSMUTERS_STONE(classTalent()
        .withName("Transmuter's Stone")
        .withDescription("Spend 8 hours to create a transmuter's stone. ")
        .withDescription("Possessor gains one of the following benefits: darkvision to 60 feet, "
            + "speed +10 feet, proficiency in Con. saves, resistance to acid, cold, fire, "
            + "lightning or thunder.")
        .withDescription("Benefit can be changed each time Transmutation spell is cast.")),
    SHAPECHANGER(classTalent()
        .withDescription("Cast <em>Polymorph</em> to transform into a beast with CR 1 or lower "
            + "without spending a spell slot.")
        .withDescription("Use once between rests.")
        .withAction("Gain Polymorph", ch -> {
            SpellCasting wizardCasting = ch.getSpellCasting("Wizard");
            if (!wizardCasting.hasLearntSpell(Spell.POLYMORPH))
                wizardCasting.addLearntSpell(Spell.POLYMORPH);
        })),
    MASTER_TRANSMUTER(classTalent()
        .withDescription("As an action, consume transmuter's stone to "
            + "transform one object to another, "
            + "remove curses, diseases and poisons and restore all hit points for a creature, "
            + "cast <em>Raise Dead</em> without expending a spell slot, or "
            + "reduce apparent age by 3d10 years (minimum 13).")
        .withDescription("Use once between long rests.")),
    /*
     * Background abilities
    **/
    CITY_SECRETS(BACKGROUND_FEATURE, "City Secrets",
        "When not in combat, travel at double speed between locations in city."),
    SHIPS_PASSAGE(BACKGROUND_FEATURE, "Ship's Passage",
        "Can secure free passage on sailing ships."),
    RESEARCHER(BACKGROUND_FEATURE, "Researcher",
        "Can attempt to obtain a piece of lore."),
    WANDERER(BACKGROUND_FEATURE, "Wanderer",
        "Excellent memory for maps and geography. Can find food if available."),
    DISCOVERY(BACKGROUND_FEATURE, "Discovery",
        "Have made a unique and powerful discovery."),
    BY_POPULAR_DEMAND(BACKGROUND_FEATURE, "By Popular Demand",
        "Find a place to perform is each town, receiving free lodging."),
    FALSE_IDENTITY(BACKGROUND_FEATURE, "False Identity",
        "Can assume persona of second identity. "
        + "Can forge official papers and letters if samples are available. "),
    GUILD_MEMBERSHIP(BACKGROUND_FEATURE, "Guild Membership",
        "Guild will provide lodging and food. Must pay dues of 5GP each month."),;

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
