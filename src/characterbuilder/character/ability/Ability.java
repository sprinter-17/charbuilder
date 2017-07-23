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
        + "recover [$level // 2] [plural(level,levels)] of expended spell slots below sixth level."),
    SNEAK_ATTACK(CLASS_TALENT, "Sneak Attack",
        "Extra [$level // 2]d6 damage on one attack with advantage each turn."),
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
    //
    EVOCATION_SAVANT(EVOCATION_ABILITY, "Evocation Savant",
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
        "Healing spells restore an additional 2 + spell level HP."),;

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

    public String getDescription(Character character) {
        return StringUtils.expand(description, character);
    }
}
