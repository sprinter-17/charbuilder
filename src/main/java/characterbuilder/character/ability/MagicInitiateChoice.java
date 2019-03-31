package characterbuilder.character.ability;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.characterclass.CharacterClass;
import characterbuilder.character.choice.ChoiceSelector;
import characterbuilder.character.choice.OptionChoice;
import characterbuilder.character.spell.LearntSpell;
import java.util.EnumMap;
import java.util.stream.Stream;

public class MagicInitiateChoice extends OptionChoice {

    private static final EnumMap<CharacterClass, AttributeType> INITIATE_CLASSES
        = new EnumMap<>(CharacterClass.class);

    static {
        INITIATE_CLASSES.put(CharacterClass.BARD, AttributeType.CHARISMA);
        INITIATE_CLASSES.put(CharacterClass.CLERIC, AttributeType.WISDOM);
        INITIATE_CLASSES.put(CharacterClass.DRUID, AttributeType.WISDOM);
        INITIATE_CLASSES.put(CharacterClass.SORCERER, AttributeType.CHARISMA);
        INITIATE_CLASSES.put(CharacterClass.WARLOCK, AttributeType.CHARISMA);
        INITIATE_CLASSES.put(CharacterClass.WIZARD, AttributeType.INTELLIGENCE);
    }

    public MagicInitiateChoice() {
        super("Magic Initiate Class");
    }

    @Override
    public void select(Character character, ChoiceSelector selector) {
        selector.chooseOption(INITIATE_CLASSES.keySet().stream(), cls -> {
            addSpellChoice(character, 2, cls, 0);
            addSpellChoice(character, 1, cls, 1);
        });
    }

    private void addSpellChoice(Character character, int count, CharacterClass spellClass,
        int spellLevel) {
        String name = "Initiate " + (spellLevel == 0 ? "Cantrip" : "Spell");
        character.addChoice(new OptionChoice(name, count) {
            @Override
            public void select(Character character, ChoiceSelector selector) {
                selector.chooseOption(spells(character),
                    spl -> character.addAttribute(spl));
            }

            @Override
            public boolean isAllowed(Character character) {
                return spells(character).findAny().isPresent();
            }

            private Stream<LearntSpell> spells(Character character) {
                return spellClass.getSpells()
                    .filter(spl -> spl.isLevel(spellLevel))
                    .map(spl -> new LearntSpell(spl, INITIATE_CLASSES.get(spellClass)))
                    .filter(spl -> !LearntSpell.hasLearntSpell(character, spl));
            }
        });
    }

}
