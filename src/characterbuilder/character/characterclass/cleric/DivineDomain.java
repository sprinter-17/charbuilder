package characterbuilder.character.characterclass.cleric;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Proficiency;
import characterbuilder.character.ability.Skill;
import static characterbuilder.character.ability.Skill.*;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeDelegate;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.characterclass.cleric.DivineDomain.DivineDomainAbility.CHANNEL_DIVINITY;
import static characterbuilder.character.characterclass.cleric.DivineDomain.DivineDomainAbility.DESTROY_UNDEAD;
import static characterbuilder.character.characterclass.cleric.DivineDomain.DivineDomainAbility.DIVINE_INTERVENTION;
import static characterbuilder.character.characterclass.cleric.DivineDomain.DivineDomainAbility.TURN_UNDEAD;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.ChoiceGenerator;
import static characterbuilder.character.spell.Spell.*;
import characterbuilder.utils.StringUtils;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.w3c.dom.Element;

public enum DivineDomain implements Attribute {
    KNOWLEDGE(gen -> {
        gen.level(1).addLearntSpells("Cleric", COMMAND, IDENTIFY);
        gen.level(1).addChoice(2, new AttributeChoice(AttributeType.LANGUAGE));
        gen.level(1).addChoice(2, new AttributeChoice("Skill", ARCANA, HISTORY,
            Skill.NATURE, RELIGION));
        gen.level(2).addAttributes(DivineDomainAbility.KNOWLEDGE_OF_THE_AGES);
        gen.level(3).addLearntSpells("Cleric", AUGURY, SUGGESTION);
        gen.level(5).addLearntSpells("Cleric", NONDETECTION, SPEAK_WITH_DEAD);
        gen.level(6).addAttributes(DivineDomainAbility.READ_THOUGHTS);
        gen.level(7).addLearntSpells("Cleric", ARCANE_EYE, CONFUSION);
        gen.level(8).addAttributes(DivineDomainAbility.POTENT_SPELLCASTING);
        gen.level(9).addLearntSpells("Cleric", LEGEND_LORE, SCRYING);
        gen.level(17).addAttributes(DivineDomainAbility.VISIONS_OF_THE_PAST);
    }),
    LIFE(gen -> {
        gen.level(1).addAttributes(Proficiency.HEAVY_ARMOUR, DivineDomainAbility.DISCIPLE_OF_LIFE);
        gen.level(1).addLearntSpells("Cleric", BLESS, CURE_WOUNDS);
        gen.level(2).addAttributes(DivineDomainAbility.PRESERVE_LIFE);
        gen.level(3).addLearntSpells("Cleric", LESSER_RESTORATION, SPIRITUAL_WEAPON);
        gen.level(6).addAttributes(DivineDomainAbility.BLESSED_HEALER);
        gen.level(7).addLearntSpells("Cleric", BEACON_OF_HOPE, REVIVIFY);
        gen.level(8).addAttributes(DivineDomainAbility.DIVINE_STRIKE);
        gen.level(9).addLearntSpells("Cleric", MASS_CURE_WOUNDS, RAISE_DEAD);
        gen.level(17).addAttributes(DivineDomainAbility.SUPREME_HEALING);
    }),
    LIGHT(gen -> {

    }),
    NATURE(gen -> {

    }),
    TEMPEST(gen -> {

    }),
    TRICKERY(gen -> {

    }),
    WAR(gen -> {

    });

    public enum DivineDomainAbility implements Attribute {
        /*
     * Divine domain abilities
    **/
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
        TURN_UNDEAD(ability()
            .withDescription("Each undead within 30' save vs Wis or turned for 1 minute.")),
        DESTROY_UNDEAD(ability()
            .withDescription("Turned undead with CR of [max($level 5:1/2, 8:1, 11:2, 14:3, 17:4)] "
                + "or less are destroyed")),
        DIVINE_INTERVENTION(ability()
            .withDescription("[if($level<20:$level% chance of deity intervening.:Deity intervenes.)] ")
            .withDescription("7 days between each successful use. "
                + "1 day between each unsuccessful use.")),
        CHANNEL_DIVINITY(ability()
            .withDescription("Use [max($level 2:one, 6:two, 18:three)] channel divinity "
                + "[plural(power,powers)] between each rest.")),
        PRESERVE_LIFE(ability()
            .withDescription("Restore [$level*5] total HP to creatures within 30' up to "
                + "half their maximum.")),
        BLESSED_HEALER(ability()
            .withDescription("Healing spell also restore 2 + spell level HP to cleric.")),
        DIVINE_STRIKE(ability()
            .withDescription("Weapon attacks cause an additional [max($level 8:1,14:2)]d8 "
                + "radiant damage.")),
        SUPREME_HEALING(ability()
            .withDescription("Healing spells restore maximum HP.")),
        DISCIPLE_OF_LIFE(ability()
            .withDescription("Healing spells restore an additional 2 + spell level HP.")),;

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
    }

    private final Consumer<ChoiceGenerator> generatorMaker;
    private Optional<ChoiceGenerator> generator = Optional.empty();

    private DivineDomain(Consumer<ChoiceGenerator> consumer) {
        this.generatorMaker = gen -> {
            consumer.accept(gen);
            gen.level(2).addAttributes(TURN_UNDEAD, CHANNEL_DIVINITY);
            gen.level(5).addAttributes(DESTROY_UNDEAD).addSpellSlots("Cleric", 3, 2);
            gen.level(10).addAttributes(DIVINE_INTERVENTION);
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
