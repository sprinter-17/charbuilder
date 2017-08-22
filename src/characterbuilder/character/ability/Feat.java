package characterbuilder.character.ability;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeDelegate;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.*;
import characterbuilder.character.attribute.DamageType;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.character.choice.AbilityScoreOrFeatIncrease;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.equipment.Weapon;
import characterbuilder.utils.StringUtils;
import java.util.Arrays;
import java.util.stream.Stream;
import org.w3c.dom.Node;

public enum Feat implements Attribute {
    ALERT(feat()
        .withDescription("+5 initiative; cannot be surprised; "
            + "unseen enemies do not gain adavantage on attack.")),
    ATHLETE(feat()
        .withDescription("Standing up uses only 5 feet of movement. Climb at normal speed. "
            + "Running jump after 5 feet.")
        .withIncreaseChoice(STRENGTH, DEXTERITY)),
    ACTOR(feat()
        .withDescription("Advantage on Deception and Performance checks; "
            + "mimic speech or sounds: Insight vs. Deception to detect. ")
        .withIncrease(CHARISMA)),
    CHARGER(feat()
        .withDescription("When action Dash, make 1 melee weapon attack or shove as reaction; "
            + "+5 damage or +10 feet shove if move 10 feet in straight line.")),
    CROSSBOW_EXPERT(feat()
        .withDescription("Ignore loading cost of crossbows; not disadvantaged while engaged; "
            + "while attacking with one-handed weapon, "
            + "also attack with hand crossbow as bonus action.")),
    DEFENSIVE_DUELIST(feat()
        .withDescription("While wielding a finesse weapon, as a reaction to being hit by a "
            + "melee weapon +[$prof] AC.")
        .withPrerequisite(ch -> ch.getIntAttribute(DEXTERITY) >= 13)),
    DUAL_WIELDER(feat()
        .withDescription("+1 AC while wielding two weapons; can use two heavy weapons; draw/stow "
            + "two weapons in same time as one.")),
    DUNGEON_DELVER(feat()
        .withDescription("Advantage on Perception and Investigation checks for secret doors.")
        .withDescription("Advantage on saves and resistance to damage for traps.")
        .withDescription("Search for traps at normal pace.")),
    DURABLE(feat()
        .withIncrease(CONSTITUTION)
        .withDescription("When regaining HP during rest, minimum roll is [max(2, $con_mod * 2)].")),
    ELEMENTAL_ADEPT(feat().withDoNotAddToCharacter()
        .withPrerequisite(SPELLCASTING)
        .withPrerequisite(ch -> ch.getAttributeCount(AttributeType.ELEMENTAL_ADEPT) < 4)
        .withChoice(new AttributeChoice("Adept With Element",
            Stream.of(DamageType.ACID, DamageType.COLD, DamageType.LIGHTNING, DamageType.THUNDER)
                .map(ElementalAdept::new)))),
    GRAPPLER(feat()
        .withPrerequisiteAbilityScore(DEXTERITY, 13)
        .withDescription("Advantage on attacks against grappled creatures. ")
        .withDescription("As an action attempt to pin a grappled creature. ")),
    GREAT_WEAPON_MASTER(feat()
        .withDescription("When scoring a critical hit or reducing a creature to 0 with a "
            + "melee attack, make a second melee attack as a bonus action. ")
        .withDescription("May choose -5 attack, +10 damage on attacks with heavy weapon. ")),
    HEALER(feat()
        .withDescription("1 HP healing when using a kit to stabilize a dying creature. ")
        .withDescription("As an action cure 1d6 + 4 + target's hit dice with a healer's kit. ")),
    HEAVILY_ARMOURED(feat().withDoNotAddToCharacter()
        .withPrerequisite(ch -> ch.hasAttribute(Proficiency.MEDIUM_ARMOUR))
        .withIncrease(STRENGTH)
        .withAttribute(Proficiency.HEAVY_ARMOUR)),
    HEAVY_ARMOUR_MASTER(feat()
        .withPrerequisite(Proficiency.HEAVY_ARMOUR)
        .withIncrease(STRENGTH)
        .withDescription("-3 damage from bludgeoning, piercing and slashing while wearing "
            + "heavy armour.")),
    INSPIRING_LEADER(feat()
        .withPrerequisiteAbilityScore(CHARISMA, 13)
        .withDescription("Spend 10 minutes; 6 allies within 30 feet gain [$level + $chr_mod] HP.")),
    KEEN_MIND(feat()
        .withIncrease(INTELLIGENCE)
        .withDescription("Direction and time sense. Accurate recall of events.")),
    LIGHTLY_ARMOURED(feat().withDoNotAddToCharacter()
        .withIncreaseChoice(STRENGTH, DEXTERITY)
        .withAttribute(Proficiency.LIGHT_ARMOUR)),
    LINGUIST(feat()
        .withIncrease(INTELLIGENCE)
        .withChoice(3, Proficiency.choose(LANGUAGE))
        .withDescription("Create written ciphers. DC [$int + $prof] to decipher.")),
    LUCKY(feat()
        .withDescription("Up to 3 times between each long rest reroll a d20 for an attack, "
            + "ability check, save or an attacker's attack roll.")),
    MAGE_SLAYER(feat()
        .withDescription("As a reaction make a melee attack against a creature casting a spell. ")
        .withDescription("Disadvantage concentration checks when damage caused. ")
        .withDescription("Advantage on saves against engaged creatures. ")),
    // choose a class: bard, cleric, druid, sorcerer, warlock, wizard
    // learn two cantrips from that class. 1 first level spell cast as feat
    // spellcasting ability depends on class: chr for bard, sorcerer, warlock
    // wis for cleric or druid, int for wizard
    MAGIC_INITIATE(feat()),
    MARTIAL_ADEPT(feat()),
    MEDIUM_ARMOUR_MASTER(feat()
        .withPrerequisite(Proficiency.MEDIUM_ARMOUR)
        .withDescription("Medium armour does not impose disadvantage on Stealth.")
        .withDescription("Max +3 AC (rather than +2) for Dex. modifier.")),
    MOBILE(feat()
        .withDescription("Difficult terrain does not cost extra movement during Dash.")
        .withDescription("Do not provoke opportunity attacks from creatures attacked during turn.")),
    MODERATELY_ARMOURED(feat().withDoNotAddToCharacter()
        .withPrerequisite(Proficiency.LIGHT_ARMOUR)
        .withIncreaseChoice(STRENGTH, DEXTERITY)
        .withAttribute(Proficiency.MEDIUM_ARMOUR)),
    MOUNTED_COMBATANT(feat()),
    OBSERVANT(feat()),
    POLEARM_MASTER(feat()),
    RESILIENT(feat().withDoNotAddToCharacter()
        .withChoice(new AbilityScoreOrFeatIncrease().withProficiency())
        .withDescription("Gain proficiency in saves using chosen ability.")),
    RITUAL_CASTER(feat()
        .withDescription("Owns a ritual book and able to cast spells as rituals.")
        .withPrerequisite(
            ch -> ch.getIntAttribute(INTELLIGENCE) >= 13 || ch.getIntAttribute(WISDOM) >= 13)),
    SAVAGE_ATTACKER(feat()
        .withDescription("Once per turn, reroll melee weapon's damage dice.")),
    SENTINEL(feat()
        .withDescription("On hit of an opportunity attack, target's speed becomes 0.")),
    SHARPSHOOTER(feat()
        .withDescription("For ranged attacks: o disadvantage at long range; "
            + "ignore half and three-quarter cover; may choose -5 attack, +10 damage.")),
    SHIELD_MASTER(feat()
        .withDescription("As a bonus action attempt to shove a target when attacking. "
            + "Add shield's AC to Dex. save against spell or effect. "
            + "As a reaction, convert half damage from successful Dex. save to no damage.")),
    SKILLED(feat()
        .withDescription("Additional skills")
        .withChoice(new AttributeChoice("Skill or Tool",
            Stream.concat(Arrays.stream(Skill.values()),
                Arrays.stream(Proficiency.values()).filter(TOOLS::isTypeOfAttribute))))),
    SKULKER(feat()
        .withDescription("Attemp to hide when lightly obscured. "
            + "Missing ranged attack does not reveal position. "
            + "Dim light does not disadvantage perception checks.")
        .withPrerequisiteAbilityScore(DEXTERITY, 13)),
    SPELL_SNIPER(feat()
        .withDescription("Double range of spell attacks. "
            + "Ranged spell attacks ignore half and three-quarter cover. ")
        //        .withChoice(new AttributeChoice("Cantrip", Spell.CHILL_TOUCH,
        //            Spell.ELDRITCH_BLAST, Spell.FIRE_BOLT, Spell.RAY_OF_FROST, Spell.SHOCKING_GRASP,
        //            Spell.THORN_WHIP))
        .withPrerequisite(SPELLCASTING)),
    TAVERN_BRAWLER(feat()
        .withDescription("Proficient with improvised weapons. Unarmed strike uses d4 for damage. "
            + "Attempt to Grapple as a bonus action after hitting with "
            + "unarmed strike or improvised weapon. ")
        .withChoice(new AbilityScoreOrFeatIncrease("+1 Str. or +1 Dex.", STRENGTH, DEXTERITY))),
    TOUGH(feat()
        .withAction("+2HP / level", ch -> ch
        .getAttribute(HIT_POINTS, IntAttribute.class).addValue(2 * ch.getLevel()))),
    WAR_CASTER(feat()
        .withDescription("Advantage on Con. save for maintaining concentration when taking damage. "
            + "Perform somatic component while holding weapons and shields. "
            + "Case spells as opportunity attacks. ")
        .withPrerequisite(ch -> ch.hasAttribute(SPELLCASTING))),
    WEAPON_MASTER(feat().withDoNotAddToCharacter()
        .withIncreaseChoice(STRENGTH, DEXTERITY)
        .withChoice(4, new AttributeChoice("Weapon Proficiency",
            Arrays.stream(Weapon.values()).map(Weapon::getProficiency))));

    private static class FeatBuilder extends AttributeDelegate {

        private boolean addToCharacter = true;

        public FeatBuilder withDoNotAddToCharacter() {
            addToCharacter = false;
            return this;
        }
    }

    private final FeatBuilder delegate;

    private Feat(AttributeDelegate delegate) {
        this.delegate = (FeatBuilder) delegate;
    }

    private static FeatBuilder feat() {
        return new FeatBuilder();
    }

    @Override
    public AttributeType getType() {
        return FEAT;
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
    public void choose(Character character) {
        if (delegate.addToCharacter)
            character.addAttribute(this);
        delegate.generateChoices(character);
    }

    public final boolean isAllowed(Character character) {
        return delegate.isAllowed(character);
    }

    public static Feat load(Node node) {
        return valueOf(node.getTextContent());
    }

}
