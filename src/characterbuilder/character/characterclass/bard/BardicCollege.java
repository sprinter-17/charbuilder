package characterbuilder.character.characterclass.bard;

import characterbuilder.character.Character;
import static characterbuilder.character.ability.Ability.*;
import static characterbuilder.character.ability.Proficiency.*;
import characterbuilder.character.ability.Skill;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.character.spell.Spell;
import characterbuilder.utils.StringUtils;
import java.util.Arrays;
import org.w3c.dom.Node;

public enum BardicCollege implements Attribute {
    COLLEGE_OF_LORE {
        @Override
        public void generateLevelChoices(Character character) {
            ChoiceGenerator gen = new ChoiceGenerator();
            gen.level(3).addChoice(3, new AttributeChoice("Bonus Proficiencies", Skill.values()));
            gen.level(3).addAttributes(CUTTING_WORDS);
            gen.level(6).addChoice(ChoiceGenerator.spellChoice("Bard", 2, "Magical Secrets",
                Arrays.stream(Spell.values())));
            gen.level(14).addAttributes(PEERLESS_SKILL);
            gen.generateChoices(character);
        }
    },
    COLLEGE_OF_VALOUR {
        public void generateLevelChoices(Character character) {
            ChoiceGenerator gen = new ChoiceGenerator();
            gen.level(3).addAttributes(MEDIUM_ARMOUR, SHIELD,
                ALL_MARTIAL_MELEE, ALL_MARTIAL_RANGED);
            gen.level(3).addAttributes(COMBAT_INSPIRATION);
            gen.level(6).addAttributes(EXTRA_ATTACK);
            gen.level(14).addAttributes(BATTLE_MAGIC);
            gen.generateChoices(character);
        }
    };

    @Override
    public AttributeType getType() {
        return AttributeType.BARDIC_COLLEGE;
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    public static BardicCollege load(Node node) {
        return BardicCollege.valueOf(node.getTextContent());
    }
}
