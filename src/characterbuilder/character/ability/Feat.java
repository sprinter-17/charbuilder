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
    CHARGER("When action Dash, make 1 melee weapon attack or shove as reaction; "
        + "+5 damage or +10 feet shove if move 10 feet in straight line."),
    CROSSBOW_EXPERT(
        "Ignore loading cost of crossbows; not disadvantaged while engaged; "
        + "while attacking with one-handed weapon, "
        + "also attack with hand crossbow as bonus action."),
    DEFENSIVE_DUELIST(feat()
        .withDescription("While wielding a finesse weapon, as a reaction to being hit by a "
            + "melee weapon +[$prof] AC.")
        .withPrerequisite(ch -> ch.getIntAttribute(DEXTERITY) >= 13)),
    DUAL_WIELDER("+1 AC while wielding two weapons; can use two heavy weapons; draw/stow "
        + "two weapons in same time as one."),
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
    GRAPPLER(""),
    GREAT_WEAPON_MASTER(""),
    HEALER(""),
    HEAVILY_ARMOURED(""),
    HEAVY_WEAPON_MASTER(""),
    INSPIRING_LEADER(""),
    KEEN_MIND(""),
    LIGHTLY_ARMOURED(""),
    LINGUIST(""),
    LUCKY(""),
    MAGE_SLAYER(""),
    MAGIC_INITIATE(""),
    MARTIAL_ADEPT(""),
    MEDIUM_ARMOUR_MASTER(""),
    MOBILE(""),
    MODERATELY_ARMOURED(""),
    MOUNTED_COMBATANT(""),
    OBSERVANT(""),
    POLEARM_MASTER(""),
    RESILIENT(""),
    RITUAL_CASTER(feat()
        .withDescription("Owns a ritual book and able to cast spells as rituals.")
        .withPrerequisite(
            ch -> ch.getIntAttribute(INTELLIGENCE) >= 13 || ch.getIntAttribute(WISDOM) >= 13)),
    SAVAGE_ATTACKER("Once per turn, reroll melee weapon's damage dice."),
    SENTINEL("On hit of an opportunity attack, target's speed becomes 0."),
    SHARPSHOOTER("For ranged attacks: o disadvantage at long range; "
        + "ignore half and three-quarter cover; may choose -5 attack, +10 damage."),
    SHIELD_MASTER("As a bonus action attempt to shove a target when attacking. "
        + "Add shield's AC to Dex. save against spell or effect. "
        + "As a reaction, convert half damage from successful Dex. save to no damage."),
    SKILLED(feat()
        .withDescription("Additional skills")
        .withChoice(new AttributeChoice("Skill or Tool",
            Stream.concat(Arrays.stream(Skill.values()),
                Arrays.stream(Proficiency.values()).filter(p -> p.hasType(TOOLS)))))),
    SKULKER(feat()
        .withDescription("Attemp to hide when lightly obscured. Missing ranged attack does not reveal position. "
            + "Dim light does not disadvantage perception checks.")
        .withPrerequisite(DEXTERITY, 13)),
    SPELL_SNIPER(feat()
        .withDescription("Double range of spell attacks. "
            + "Ranged spell attacks ignore half and three-quarter cover. ")
        .withChoice(new AttributeChoice("Cantrip", Spell.CHILL_TOUCH,
            Spell.ELDRITCH_BLAST, Spell.FIRE_BOLT, Spell.RAY_OF_FROST, Spell.SHOCKING_GRASP,
            Spell.THORN_WHIP))
        .withPrerequisite(SPELLCASTING)),
    TAVERN_BRAWLER(feat()
        .withDescription("Proficient with improvised weapons. Unarmed strike uses d4 for damage. "
            + "Attempt to Grapple as a bonus action after hitting with "
            + "unarmed strike or improvised weapon. ")
        .withChoice(new AbilityScoreOrFeatIncrease("+1 Str. or +1 Dex.",
            STRENGTH, DEXTERITY))),
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

    private Feat(String description) {
        this(feat().withDescription(description));
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
