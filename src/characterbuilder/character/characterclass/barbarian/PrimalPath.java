package characterbuilder.character.characterclass.barbarian;

import characterbuilder.character.Character;
import static characterbuilder.character.ability.Ability.*;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.STRENGTH;
import static characterbuilder.character.characterclass.CharacterClass.DRUID;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.character.spell.Spell;
import java.util.function.Supplier;
import org.w3c.dom.Node;

public enum PrimalPath implements Attribute {
    PATH_OF_THE_BESERKER("Path of the Beserker", () -> {
        ChoiceGenerator gen = new ChoiceGenerator();
        gen.level(3).addAttributes(FRENZY);
        gen.level(6).addAttributes(MINDLESS_RAGE);
        gen.level(10).addAttributes(INTIMIDATING_PRESENCE);
        gen.level(14).addAttributes(RETALIATION);
        return gen;
    }),
    PATH_OF_THE_TOTEM_WARRIOR("Path of the Totem Warrior", () -> {
        ChoiceGenerator gen = new ChoiceGenerator();
        gen.level(3)
            .addSpellCasting("Barbarian", STRENGTH, DRUID, "All")
            .addLearntSpells("Barbarian", Spell.BEAST_SENSE, Spell.SPEAK_WITH_ANIMALS);
        gen.level(3).addChoice(new AttributeChoice("Totem Spirit",
            TOTEM_SPIRIT_BEAR, TOTEM_SPIRIT_EAGLE, TOTEM_SPIRIT_WOLF));
        gen.level(6).addChoice(new AttributeChoice("Totem Spirit",
            ASPECT_OF_BEAST_BEAR, ASPECT_OF_BEAST_EAGLE, ASPECT_OF_BEAST_WOLF));
        gen.level(10).addLearntSpells("Barbarian", Spell.COMMUNE_WITH_NATURE);
        gen.level(14).addChoice(new AttributeChoice("Totem Spirit",
            TOTEMIC_ATTUNEMENT_BEAR, TOTEMIC_ATTUNEMENT_EAGLE, TOTEMIC_ATTUNEMENT_WOLF));
        return gen;
    });

    private final String name;
    private final Supplier<ChoiceGenerator> generator;

    private PrimalPath(String name, Supplier<ChoiceGenerator> generator) {
        this.name = name;
        this.generator = generator;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.PRIMAL_PATH;
    }

    @Override
    public void generateLevelChoices(Character character) {
        generator.get().generateChoices(character);
    }

    @Override
    public String toString() {
        return name;
    }

    public static PrimalPath load(Node node) {
        return PrimalPath.valueOf(node.getTextContent());
    }
}
