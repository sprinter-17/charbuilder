package characterbuilder.character.characterclass.cleric;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Language;
import characterbuilder.character.ability.Proficiency;
import characterbuilder.character.ability.Skill;
import static characterbuilder.character.ability.Skill.*;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeDelegate;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.characterclass.CharacterClass.DRUID;
import static characterbuilder.character.characterclass.cleric.DivineDomain.DivineDomainAbility.*;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.character.spell.Spell;
import static characterbuilder.character.spell.Spell.*;
import characterbuilder.utils.StringUtils;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.w3c.dom.Element;

public enum DivineDomain implements Attribute {
    KNOWLEDGE(gen -> {
        gen.level(1).addLearntSpells("Cleric", COMMAND, IDENTIFY);
        gen.level(1).addChoice(Language.choose(2));
        gen.level(1).addAttributeChoice(2, "Skill", ARCANA, HISTORY, Skill.NATURE, RELIGION);
        gen.level(2).addAttributes(KNOWLEDGE_OF_THE_AGES);
        gen.level(3).addLearntSpells("Cleric", AUGURY, SUGGESTION);
        gen.level(5).addLearntSpells("Cleric", NONDETECTION, SPEAK_WITH_DEAD);
        gen.level(6).addAttributes(READ_THOUGHTS);
        gen.level(7).addLearntSpells("Cleric", ARCANE_EYE, CONFUSION);
        gen.level(8).addAttributes(POTENT_SPELLCASTING);
        gen.level(9).addLearntSpells("Cleric", LEGEND_LORE, SCRYING);
        gen.level(17).addAttributes(VISIONS_OF_THE_PAST);
    }),
    LIFE(gen -> {
        gen.level(1).addAttributes(Proficiency.HEAVY_ARMOUR, DivineDomainAbility.DISCIPLE_OF_LIFE);
        gen.level(1).addLearntSpells("Cleric", BLESS, CURE_WOUNDS);
        gen.level(2).addAttributes(PRESERVE_LIFE);
        gen.level(3).addLearntSpells("Cleric", LESSER_RESTORATION, SPIRITUAL_WEAPON);
        gen.level(6).addAttributes(BLESSED_HEALER);
        gen.level(7).addLearntSpells("Cleric", BEACON_OF_HOPE, REVIVIFY);
        gen.level(8).addAttributes(DIVINE_STRIKE);
        gen.level(9).addLearntSpells("Cleric", MASS_CURE_WOUNDS, RAISE_DEAD);
        gen.level(17).addAttributes(SUPREME_HEALING);
    }),
    LIGHT(gen -> {
        gen.level(1).addSpellAbility(Spell.LIGHT, AttributeType.WISDOM);
        gen.level(1).addAttributes(WARDING_FLARE);
        gen.level(1).addLearntSpells("Cleric", Spell.BURNING_HANDS, Spell.FAERIE_FIRE);
        gen.level(2).addAttributes(RADIANCE_OF_THE_DAWN);
        gen.level(3).addLearntSpells("Cleric", Spell.FLAMING_SPHERE, Spell.SCORCHING_RAY);
        gen.level(5).addLearntSpells("Cleric", Spell.DAYLIGHT, Spell.FIREBALL);
        gen.level(6).addAttributes(IMPROVED_FLARE);
        gen.level(7).addLearntSpells("Cleric", Spell.GUARDIAN_OF_FAITH, Spell.WALL_OF_FIRE);
        gen.level(8).addAttributes(POTENT_SPELLCASTING);
        gen.level(9).addLearntSpells("Cleric", Spell.FLAME_STRIKE, Spell.SCRYING);
        gen.level(17).addAttributes(CORONA_OF_LIGHT);
    }),
    NATURE(gen -> {
        gen.level(1).addLearntSpells("Cleric", Spell.ANIMAL_FRIENDSHIP, Spell.SPEAK_WITH_ANIMALS);
        gen.level(1).addAttributes(Proficiency.HEAVY_ARMOUR);
        gen.level(1).addAttributes(ACOLYTE_OF_NATURE);
        gen.level(2).addAttributes(CHARM_ANIMALS_AND_PLANTS);
        gen.level(3).addLearntSpells("Cleric", Spell.BARKSKIN, Spell.SPIKE_GROWTH);
        gen.level(5).addLearntSpells("Cleric", Spell.PLANT_GROWTH, Spell.WIND_WALL);
        gen.level(6).addAttributes(DAMPEN_ELEMENTS);
        gen.level(7).addLearntSpells("Cleric", Spell.DOMINATE_BEAST, Spell.GRASPING_VINE);
        gen.level(8).addAttributes(DIVINE_STRIKE);
        gen.level(9).addLearntSpells("Cleric", Spell.INSECT_PLAGUE, Spell.TREE_STRIDE);
        gen.level(17).addAttributes(MASTER_OF_NATURE);
    }),
    TEMPEST(gen -> {
        gen.level(1).addLearntSpells("Cleric", Spell.FOG_CLOUD, Spell.THUNDERWAVE);
        gen.level(1).addAttributes(Proficiency.HEAVY_ARMOUR, Proficiency.ALL_MARTIAL_WEAPONS);
        gen.level(1).addAttributes(WRATH_OF_THE_STORM);
        gen.level(2).addAttributes(DESTRUCTIVE_WRATH);
        gen.level(3).addLearntSpells("Cleric", Spell.GUST_OF_WIND, Spell.SHATTER);
        gen.level(5).addLearntSpells("Cleric", Spell.CALL_LIGHTNING, Spell.SLEET_STORM);
        gen.level(6).addAttributes(THUNDERBOLT_STRIKE);
        gen.level(7).addLearntSpells("Cleric", Spell.CONTROL_WATER, Spell.ICE_STORM);
        gen.level(8).addAttributes(DIVINE_STRIKE);
        gen.level(9).addLearntSpells("Cleric", Spell.DESTRUCTIVE_WAVE, Spell.INSECT_PLAGUE);
        gen.level(17).addAttributes(STORMBORN);
    }),
    TRICKERY(gen -> {
        gen.level(1).addLearntSpells("Cleric", Spell.CHARM_PERSON, Spell.DISGUISE_SELF);
        gen.level(1).addAttributes(BLESSING_OF_THE_TRICKSTER);
        gen.level(2).addAttributes(INVOKE_DUPLICITY);
        gen.level(3).addLearntSpells("Cleric", Spell.MIRROR_IMAGE, Spell.PASS_WITHOUT_TRACE);
        gen.level(5).addLearntSpells("Cleric", Spell.BLINK, Spell.DISPEL_MAGIC);
        gen.level(6).addAttributes(CLOAK_OF_SHADOWS);
        gen.level(7).addLearntSpells("Cleric", Spell.DIMENSION_DOOR, Spell.POLYMORPH);
        gen.level(8).addAttributes(DIVINE_STRIKE);
        gen.level(9).addLearntSpells("Cleric", Spell.DOMINATE_PERSON, Spell.MODIFY_MEMORY);
    }),
    WAR(gen -> {
        gen.level(1).addLearntSpells("Cleric", Spell.DIVINE_FAVOR, Spell.SHIELD_OF_FAITH);
        gen.level(1).addAttributes(Proficiency.HEAVY_ARMOUR, Proficiency.ALL_MARTIAL_WEAPONS);
        gen.level(1).addAttributes(WAR_PRIEST);
        gen.level(2).addAttributes(GUIDED_STRIKE);
        gen.level(3).addLearntSpells("Cleric", Spell.MAGIC_WEAPON, Spell.SPIRITUAL_WEAPON);
        gen.level(5).addLearntSpells("Cleric", Spell.CRUSADERS_MANTLE, Spell.SPIRIT_GUARDIANS);
        gen.level(6).addAttributes(WAR_GODS_BLESSING);
        gen.level(7).addLearntSpells("Cleric", Spell.FREEDOM_OF_MOVEMENT, Spell.STONESKIN);
        gen.level(8).addAttributes(DIVINE_STRIKE);
        gen.level(9).addLearntSpells("Cleric", Spell.FLAME_STRIKE, Spell.HOLD_MONSTER);
        gen.level(17).addAttributes(ALTAR_OF_BATTLE);
    });

    public enum DivineDomainAbility implements Attribute {
        KNOWLEDGE_OF_THE_AGES(ability()
            .withDescription("As an action, gain proficiency with one skill or tool "
                + "for 10 minutes.")),
        READ_THOUGHTS(ability()
            .withDescription("As an action, read thoughts and cast suggestion on 1 creature "
                + "within 60 feet.")
            .withDescription("Wis. save DC[$spell_dc].")),
        POTENT_SPELLCASTING(ability()
            .withDescription("Add [$wis_mod] to damage from cantrips.")),
        VISIONS_OF_THE_PAST(ability()
            .withDescription("After 1 minute of mediation, object and area reading")),
        PRESERVE_LIFE(ability()
            .withDescription("Restore [$level*5] total HP to creatures within 30' up to "
                + "half their maximum.")),
        BLESSED_HEALER(ability()
            .withDescription("Healing spell also restore 2 + spell level HP to cleric.")),
        DIVINE_STRIKE(ability()
            .withDescription("Weapon attacks cause an additional [max($level 8:1,14:2)]d8 damage.")
            .withDescription("Use once each turn.")),
        SUPREME_HEALING(ability()
            .withDescription("Healing spells restore maximum HP.")),
        DISCIPLE_OF_LIFE(ability()
            .withDescription("Healing spells restore an additional 2 + spell level HP.")),
        WARDING_FLARE(ability()
            .withDescription("As a reaction to being attacked by creature within 30 feet, "
                + "impose disadvantage on attack. ")
            .withDescription("Use [max(1,$wis_mod)] [plural(time,times)] between each long rest.")),
        RADIANCE_OF_THE_DAWN(ability()
            .withName("Channel Divinity: Radiance of the Dawn")
            .withDescription("As an action, magical darkness within 30 feet dispelled and "
                + "hostile creatures within 30 feet take 2d10+[$level] radiant damage "
                + "(Con. save for half).")),
        IMPROVED_FLARE(ability()
            .withDescription("Use <em>Warding Flare</em> when an ally is attacked.")),
        CORONA_OF_LIGHT(ability()
            .withDescription("As an action emit bright light in 60-foot radius for 1 minute. ")
            .withDescription("Enemies in light have disadvantage on saves against spells dealing "
                + "fire or radiant damage.")),
        ACOLYTE_OF_NATURE(ability()
            .withChoice(ChoiceGenerator.cantripChoice(1, "Acolyte of Nature Cantrip",
                AttributeType.WISDOM, DRUID.getSpells().filter(Spell::isCantrip)))
            .withChoice(new AttributeChoice("Acolyte of Nature Skill",
                Skill.ANIMAL_HANDLING, Skill.NATURE, Skill.SURVIVAL))
            .withDescription("")),
        CHARM_ANIMALS_AND_PLANTS(ability()
            .withName("Channel Divinity: Charm Animals and Plants")
            .withDescription("Each best and plant within 30 feet is charmed for 1 minute. "
                + "Wis. save.")),
        DAMPEN_ELEMENTS(ability()
            .withDescription("As a reaction to an ally within 30 feet taking acid, cold, fire, "
                + "lightning or thunder damage, grant resistance to that damage.")),
        MASTER_OF_NATURE(ability()
            .withDescription("As a bonus action, verbally command creatures under influence of "
                + "<em>Charm Animal and Plants</em>.")),
        WRATH_OF_THE_STORM(ability()
            .withDescription("As a reaction to being hit by an attacker within 5 feet, "
                + "attacker takes 2d8 lightning or thunder damage. Dex. save for half.")
            .withDescription("Use [max(1,$wis_mod)] [plural(time, times)].")),
        DESTRUCTIVE_WRATH(ability()
            .withName("Channel Divinity: Destructive Wrath")
            .withDescription("Deal maximum damage with <em>Wrath of the Storm</em>.")),
        THUNDERBOLT_STRIKE(ability()
            .withDescription("Push attacker up to 10 feet away with <em>Wrath of the Storm</em>.")),
        STORMBORN(ability()
            .withDescription("Able to fly when outside. Speed equal to walking speed.")),
        BLESSING_OF_THE_TRICKSTER(ability()
            .withDescription("As an action, touch an ally to give advantage on <em>Stealth</em> "
                + "checks for 1 hour.")),
        INVOKE_DUPLICITY(ability()
            .withName("Channel Divinity: Invoke Duplicity")
            .withDescription("As an action, create [max($level 2:one,17:up to four)] illusory "
                + "[plural(duplicate,duplicates)] within 30 feet for 1 minute.")
            .withDescription("As a bonus action, move the duplicate up to 30 feet.")
            .withDescription("Advantage on melee attacks on creatures within 5 feet of duplicate.")),
        CLOAK_OF_SHADOWS(ability()
            .withName("Channel Divinity: Cloak of Shadows")
            .withDescription("As an action, become invisible for 1 turn.")),
        WAR_PRIEST(ability()
            .withDescription("Make a second attack as a bonus action.")
            .withDescription("Use [max(1,$wis_mod)] [plural(time,times)] between each long rest.")),
        GUIDED_STRIKE(ability()
            .withName("Channel Divinity: Guided Strike")
            .withDescription("Gain +10 bonus to attack roll.")),
        WAR_GODS_BLESSING(ability()
            .withName("Channel Divinity: War God's Blessing")
            .withDescription("As a reaction to an ally within 30 feet attacking, "
                + "grant +10 to attack roll.")),
        ALTAR_OF_BATTLE(ability()
            .withDescription("Resistance to bludgeoning, piercing and slashing damage from "
                + "nonmagical weapons."));

        private final AttributeDelegate delegate;

        private static AttributeDelegate ability() {
            return new AttributeDelegate();
        }

        private DivineDomainAbility(AttributeDelegate delegate) {
            this.delegate = delegate;
        }

        @Override
        public AttributeType getType() {
            return AttributeType.DIVINE_DOMAIN_ABILITY;
        }

        @Override
        public Stream<String> getDescription(Character character) {
            return delegate.getDescription(character);
        }

        @Override
        public String toString() {
            return delegate.getName().orElse(StringUtils.capitaliseEnumName(name()));
        }
    }

    private final Consumer<ChoiceGenerator> generatorMaker;
    private Optional<ChoiceGenerator> generator = Optional.empty();

    private DivineDomain(Consumer<ChoiceGenerator> consumer) {
        this.generatorMaker = gen -> {
            consumer.accept(gen);
        };
    }

    @Override
    public AttributeType getType() {
        return AttributeType.DIVINE_DOMAIN;
    }

    @Override
    public void generateLevelChoices(Character character) {
        getGenerator().generateChoices(character);
    }

    private ChoiceGenerator getGenerator() {
        if (!generator.isPresent()) {
            generator = Optional.of(new ChoiceGenerator());
            generatorMaker.accept(generator.get());
        }
        return generator.get();
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return getGenerator().getDescription(character);
    }

    public static DivineDomain load(Element element) {
        return DivineDomain.valueOf(element.getTextContent());
    }

    public static Attribute loadAbility(Element element) {
        return DivineDomainAbility.valueOf(element.getTextContent());
    }

}
