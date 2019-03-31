package characterbuilder.character.characterclass.rogue;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Proficiency;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.characterclass.CharacterClass;
import static characterbuilder.character.characterclass.CharacterClass.ROGUE;
import static characterbuilder.character.characterclass.rogue.RogueAbility.*;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.character.choice.ClassSpecificChoiceGenerator;
import characterbuilder.utils.StringUtils;
import java.util.function.Consumer;
import org.w3c.dom.Node;

public enum RoguishArchetype implements Attribute {
    THIEF(gen -> {
        gen.level(3).addAttributes(FAST_HANDS, SECOND_STORY_WORK);
        gen.level(9).addAttributes(SUPREME_SNEAK);
        gen.level(13).addAttributes(USE_MAGIC_DEVICES);
        gen.level(17).addAttributes(THIEFS_REFLEXES);
    }),
    ASSASSIN(gen -> {
        gen.level(3).addAttributes(Proficiency.DISGUISE_KIT, Proficiency.POISONERS_KIT);
        gen.level(3).addAttributes(ASSASSINATE);
        gen.level(9).addAttributes(INFILTRATION_EXPERTISE);
        gen.level(13).addAttributes(IMPOSTER);
        gen.level(17).addAttributes(DEATH_STRIKE);
    }),
    ARCANE_TRICKSTER(gen -> {
        String casting = "Arcane Trickster";
        gen.level(3).addAttributes(MAGE_HAND_LEGERDEMAIN);
        gen.level(9).addAttributes(MAGICAL_AMBUSH);
        gen.level(13).addAttributes(VERSATILE_TRICKSTER);
        gen.level(17).addAttributes(SPELL_THIEF);
        gen.level(3).addSpellCasting(casting, AttributeType.INTELLIGENCE,
            CharacterClass.ROGUE, "All");
        gen.level(3).addChoice(ChoiceGenerator.cantripChoice(3, "Arcane Trickster Cantrip",
            AttributeType.INTELLIGENCE, CharacterClass.WIZARD.getSpells()));
        gen.level(3).addKnownSpells(casting, 3);
        gen.level(4, 7, 8, 10, 11, 13, 14, 16, 19, 20).addKnownSpells(casting, 1);
        gen.level(10).addChoice(ChoiceGenerator.cantripChoice(1, "Arcane Trickster Cantrip",
            AttributeType.INTELLIGENCE, CharacterClass.WIZARD.getSpells()));
        gen.cond(ch -> ch.getLevel() > 3).replaceSpell(casting);
    });

    private final ChoiceGenerator generator = new ClassSpecificChoiceGenerator(ROGUE);

    private RoguishArchetype(Consumer<ChoiceGenerator> generator) {
        generator.accept(this.generator);
    }

    @Override
    public AttributeType getType() {
        return AttributeType.ROGUISH_ARCHETYPE;
    }

    @Override
    public void generateLevelChoices(Character character) {
        generator.generateChoices(character);
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    public static RoguishArchetype load(Node node) {
        return RoguishArchetype.valueOf(node.getTextContent());
    }

}
