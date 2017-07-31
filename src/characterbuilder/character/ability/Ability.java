package characterbuilder.character.ability;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.*;
import characterbuilder.utils.StringUtils;
import java.util.Arrays;
import java.util.stream.Stream;
import org.w3c.dom.Node;

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
    EXTRA_ATTACK_FIGHTER(CLASS_TALENT, "Extra Attack",
        "Attack [max($level 5:two, 11:three, 20:four)] times in each attack action."),
    EXTRA_ATTACK(CLASS_TALENT, "Extra Attack",
        "Attack two times in each attack action."),
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
    UNARMORED_DEFENCE_BARBARIAN(CLASS_TALENT, "Unarmoured Defence",
        "Unarmoured AC[10+$dex_mod+$con_mod]"),
    UNARMORED_DEFENCE_MONK(CLASS_TALENT, "Unarmoured Defence",
        "Unarmoured AC[10+$dex_mod+$wis_mod]"),
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
    ELEMENTAL_ATTUNEMENT(CLASS_TALENT, "Elemental Attunement",
        "Use action to cause one effect: harmless sensory effect, "
        + "light or extinguish a small flame, chill or warm an object, "
        + "shape an element for 1 minute."),
    FANGS_OF_THE_FIRE_SNAKE(CLASS_TALENT, "Fangs of the Fire Snake",
        "Spend 1 Ki point on attack; extend unarmoured attack range by 10'. "
        + "Spend 1 further Ki point to deal 1d10 extra fire damage."),
    FIST_OF_FOUR_THUNDERS(CLASS_TALENT, "Fist of Four Thunders",
        "Spend 2 Ki ponts to cast Thunderwave") {
        @Override
        public void generateInitialChoices(Character character) {
            character.addAttribute(Spell.THUNDERWAVE);
        }
    },
    FIST_OF_UNBROKEN_AIR(CLASS_TALENT, "Fist of Unbroken Air",
        "Spend at least 2 Ki points as an action. A creature within 30' takes "
        + "3d10 bludgeoning damage + 1d10 for each extra Ki point and "
        + "is pushed 20 and knocked prone. Str. save for half damage only."),
    SHAPE_OF_THE_FLOWING_RIVER(CLASS_TALENT, "Shape of the Flowing River",
        "Spend 1 Ki point as an action. Shape water and ice within 120'."),
    WATER_WHIP(CLASS_TALENT, "Water Whip",
        "Spend at least 2 Ki points as an action. A creature within 30' takes "
        + "3d10 bludgeoning damage + 1d10 for each extra Ki point and is either knocked prone "
        + " or pulled 25' closer. Dex. save for half damage only."),
    RUSH_OF_THE_GALE_SPIRITS(CLASS_TALENT, "Rush of the Gale Spirits",
        "Spend 2 Ki points to cast Gust of Wind") {
        @Override
        public void generateInitialChoices(Character character) {
            character.addAttribute(Spell.GUST_OF_WIND);
        }
    },
    SWEEPING_CINDER_STRIKE(CLASS_TALENT, "Sweeping Cinder Strike",
        "Spend 2 Ki points to cast Burning Hands") {
        @Override
        public void generateInitialChoices(Character character) {
            character.addAttribute(Spell.BURNING_HANDS);
        }
    },
    GONG_OF_THE_SUMMIT(CLASS_TALENT, "Gong of the Summit",
        "Spend 3 Ki points to cast Shatter") {
        @Override
        public void generateInitialChoices(Character character) {
            character.addAttribute(Spell.SHATTER);
        }
    },
    CLENCH_OF_THE_NORTH_WIND(CLASS_TALENT, "Clench of the North Wind",
        "Spend 3 Ki points to cast Hold Person") {
        @Override
        public void generateInitialChoices(Character character) {
            character.addAttribute(Spell.HOLD_PERSON);
        }
    },
    FLAMES_OF_THE_PHOENIX(CLASS_TALENT, "Flames of the Phoenix",
        "Spend 4 Ki points to cast Fireball") {
        @Override
        public void generateInitialChoices(Character character) {
            character.addAttribute(Spell.SHATTER);
        }
    },
    MIST_STANCE(CLASS_TALENT, "Mist Stance",
        "Spend 4 Ki points to cast Gaseous Form on self") {
        @Override
        public void generateInitialChoices(Character character) {
            character.addAttribute(Spell.GASEOUS_FORM);
        }
    },
    RIDE_THE_WIND(CLASS_TALENT, "Ride the Wind",
        "Spend 4 Ki points to cast Fly") {
        @Override
        public void generateInitialChoices(Character character) {
            character.addAttribute(Spell.FLY);
        }
    },
    BREATH_OF_WINTER(CLASS_TALENT, "Breath of Winter",
        "Spend 6 Ki points to cast Cone of Cold") {
        @Override
        public void generateInitialChoices(Character character) {
            character.addAttribute(Spell.CONE_OF_COLD);
        }
    },
    ENTERNAL_MOUNTAIN_DEFENCE(CLASS_TALENT, "Eternal Mountain Defence",
        "Spend 5 Ki points to cast Stoneskin targeting self") {
        @Override
        public void generateInitialChoices(Character character) {
            character.addAttribute(Spell.STONESKIN);
        }
    },
    RIVER_OF_HUNGRY_FLAME(CLASS_TALENT, "River of Hungry Flame",
        "Spend 5 Ki points to cast Wall of Fire") {
        @Override
        public void generateInitialChoices(Character character) {
            character.addAttribute(Spell.WALL_OF_FIRE);
        }
    },
    WAVE_OF_ROLLING_EARTH(CLASS_TALENT, "Wave of Rolling Earth",
        "Spend 6 Ki points to cast Wall of Stone") {
        @Override
        public void generateInitialChoices(Character character) {
            character.addAttribute(Spell.WALL_OF_STONE);
        }
    },
    WILD_SHAPE(CLASS_TALENT, "Wild Shape",
        "As an action, assume the shape of a beast of up to CR[max($level 2:1/4,4:1/2,8:1)]."),
    //
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

    public static Ability load(AttributeType type, Node node) {
        return Ability.valueOf(node.getTextContent());
    }
}
