package characterbuilder.character.characterclass.barbarian;

import characterbuilder.character.Character;
import static characterbuilder.character.ability.Ability.*;
import characterbuilder.character.ability.Proficiency;
import static characterbuilder.character.ability.Proficiency.ALL_WEAPONS;
import static characterbuilder.character.ability.Skill.*;
import characterbuilder.character.attribute.AbilityScore;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeDelegate;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.*;
import characterbuilder.character.characterclass.AbstractCharacterClass;
import static characterbuilder.character.characterclass.barbarian.Barbarian.Ability.*;
import characterbuilder.character.choice.AbilityScoreOrFeatIncrease;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.ChoiceGenerator;
import static characterbuilder.character.choice.ChoiceGenerator.levels;
import characterbuilder.character.choice.EquipmentChoice;
import characterbuilder.character.equipment.EquipmentCategory;
import static characterbuilder.character.equipment.EquipmentPack.EXPLORER_PACK;
import characterbuilder.character.equipment.EquipmentSet;
import static characterbuilder.character.equipment.Weapon.*;
import java.util.stream.Stream;
import org.w3c.dom.Element;

public class Barbarian extends AbstractCharacterClass {

    public enum Ability implements Attribute {
        RAGE(ability()
            .withDescription("As bonus action, enter rage for 1 minute. ")
            .withDescription("Advantage on Str. checks and saves.")
            .withDescription("+[max($level 1:2,9:3,16:4)] dam on melee attacks. ")
            .withDescription("Resistance to bludgeoning, piercing and slashing damage. "
                + "Use [max($level 1:2,6:4,12:5,17:6)] times between long rests. ")),
        UNARMORED_DEFENCE(ability()
            .withDescription("Unarmoured AC[10+$dex_mod+$con_mod].")),
        RECKLESS_ATTACK(ability()
            .withDescription("Choose to attack recklessly gaining and giving advantage "
                + "on melee attacks.")),
        DANGER_SENSE(ability()
            .withDescription("Advantage on Dex. saving throws against visible effects")),
        FAST_MOVEMENT(ability()
            .withDescription("+10' speed when unarmoured.")),
        FERAL_INSTINCTS(ability()
            .withDescription("Advantage on initiative, enter rage and act normally on surprise.")),
        BRUTAL_CRITICAL(ability()
            .withDescription("Roll [max($level 9:1,13:2,17:3)] extra [plural(die,dice)] "
                + "damage on critical.")),
        RELENTLESS_RAGE(ability()
            .withDescription("When dropping to 0HP during rage, make Con. save vs DC10 (+5 per use) "
                + "to drop to 1HP instead.")),
        PERSISTENT_RAGE(ability()
            .withDescription("Rage continues until ended voluntarily or falls unconscious.")),
        INDOMITABLE_MIGHT(ability()
            .withDescription("Str check minimum [$str].")),
        FRENZY(ability()
            .withDescription("Can enter frenzy during rage. ")
            .withDescription("Melee weapon attack as bonus action each turn. ")
            .withDescription("Exhaustion when rage ends.")),
        MINDLESS_RAGE(ability()
            .withDescription("Cannot be charmed or frightened during rage.")),;

        private final AttributeDelegate delegate;

        private Ability(AttributeDelegate delegate) {
            this.delegate = delegate;
        }

        @Override
        public AttributeType getType() {
            return AttributeType.BARBARIAN_ABILITY;
        }

        @Override
        public void generateInitialChoices(Character character) {
            delegate.generateChoices(character);
        }

        @Override
        public Stream<String> getDescription(Character character) {
            return delegate.getDescription(character);
        }
    }

    @Override
    public int getHitDie() {
        return 12;
    }

    @Override
    public AttributeType getClassAttribute() {
        return PRIMAL_PATH;
    }

    @Override
    public Stream<AttributeType> getPrimaryAttributes() {
        return Stream.of(STRENGTH, CONSTITUTION);
    }

    @Override
    protected void makeGenerator(ChoiceGenerator gen) {
        gen.level(1).addAttributes(Proficiency.LIGHT_ARMOUR, Proficiency.MEDIUM_ARMOUR,
            Proficiency.SHIELD, ALL_WEAPONS);
        gen.level(1).addChoice(2, new AttributeChoice("Skill",
            ANIMAL_HANDLING, ATHLETICS, INTIMIDATION, NATURE, PERCEPTION, SURVIVAL));
        gen.level(1).addChoice(new EquipmentChoice("Primary Weapon")
            .with(GREATEAXE).with(EquipmentCategory.MARTIAL_MELEE));
        gen.level(1).addChoice(new EquipmentChoice("Secondary Weapon")
            .with(new EquipmentSet(HANDAXE, 2))
            .with(EquipmentCategory.SIMPLE_MELEE)
            .with(EquipmentCategory.SIMPLE_RANGED));
        gen.level(1).addEquipment(EXPLORER_PACK);
        gen.level(1).addEquipment(JAVELIN, 4);
        gen.level(1).addAttributes(RAGE, UNARMORED_DEFENCE);
        gen.level(2).addAttributes(RECKLESS_ATTACK, DANGER_SENSE);
        gen.level(3).addChoice(new AttributeChoice("Primal Path", PrimalPath.values()));
        gen.cond(levels(4, 8, 12, 16, 19)).addChoice(2, new AbilityScoreOrFeatIncrease());
        gen.level(5).addAttributes(EXTRA_ATTACK, FAST_MOVEMENT);
        gen.level(7).addAttributes(FERAL_INSTINCTS);
        gen.level(9).addAttributes(BRUTAL_CRITICAL);
        gen.level(11).addAttributes(RELENTLESS_RAGE);
        gen.level(15).addAttributes(PERSISTENT_RAGE);
        gen.level(18).addAttributes(INDOMITABLE_MIGHT);
        gen.level(20).addAction("Increase Str. and Con.", ch -> {
            ch.getAttribute(STRENGTH, AbilityScore.class).setMax(24);
            ch.getAttribute(STRENGTH, AbilityScore.class).addValue(4);
            ch.getAttribute(CONSTITUTION, AbilityScore.class).setMax(24);
            ch.getAttribute(CONSTITUTION, AbilityScore.class).addValue(4);
        });
    }

    public static Ability loadAbility(Element element) {
        return Ability.valueOf(element.getTextContent());
    }
}
