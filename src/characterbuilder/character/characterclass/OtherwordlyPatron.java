package characterbuilder.character.characterclass;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Ability;
import static characterbuilder.character.ability.Ability.*;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.spell.Spell;
import static characterbuilder.character.spell.Spell.*;
import characterbuilder.character.spell.SpellCasting;
import characterbuilder.utils.StringUtils;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Node;

public enum OtherwordlyPatron implements Attribute {
    THE_ARCHFEY(FEY_PRESENCE, MISTY_ESCAPE, BEGUILING_DEFENSES, DARK_DELERIUM),
    THE_FIEND(DARK_ONES_BLESSING, DARK_ONES_OWN_LUCK, FIENDISH_RESILIENCE, HURL_THROUGH_HELL),
    THE_GREAT_OLD_ONE(AWAKENED_MIND, ENTROPIC_WARD, THOUGHT_SHIELD, CREATE_THRALL);

    private static final EnumMap<OtherwordlyPatron, List<Spell>> EXPANDED_SPELL_MAP
        = new EnumMap<>(OtherwordlyPatron.class);

    static {
        EXPANDED_SPELL_MAP.put(THE_ARCHFEY, Arrays.asList(
            FAERIE_FIRE, SLEEP, CALM_EMOTIONS, PHANTASMAL_FORCE, BLINK, PLANT_GROWTH,
            DOMINATE_BEAST, GREATER_INVISIBILITY, DOMINATE_PERSON, SEEMING));
        EXPANDED_SPELL_MAP.put(THE_FIEND, Arrays.asList(
            BURNING_HANDS, COMMAND, BLINDNESS_DEAFNESS, SCORCHING_RAY, FIREBALL,
            STINKING_CLOUD, FIRE_SHIELD, WALL_OF_FIRE, FLAME_STRIKE, HALLOW));
        EXPANDED_SPELL_MAP.put(THE_GREAT_OLD_ONE, Arrays.asList(
            DISSONANT_WHISPERS, TASHAS_HIDEOUS_LAGHTER, DETECT_THOUGHTS, PHANTASMAL_FORCE,
            CLAIRVOYANCE, SENDING, DOMINATE_BEAST, EVARDS_BLACK_TENTACLES, DOMINATE_PERSON,
            TELEKINESIS));
    }

    private final Map<Integer, Ability> abilities = new HashMap<>();

    private OtherwordlyPatron(Ability... abilities) {
        this.abilities.put(1, abilities[0]);
        this.abilities.put(6, abilities[1]);
        this.abilities.put(10, abilities[2]);
        this.abilities.put(14, abilities[3]);
    }

    @Override
    public AttributeType getType() {
        return AttributeType.OTHERWORLDLY_PATRON;
    }

    @Override
    public void generateInitialChoices(Character character) {
        SpellCasting casting = character.getSpellCasting("Warlock");
        EXPANDED_SPELL_MAP.get(this).forEach(casting::addExpandedSpell);
        generateLevelChoices(character);
    }

    @Override
    public void generateLevelChoices(Character character) {
        int level = character.getLevel();
        if (abilities.containsKey(level))
            character.addAttribute(abilities.get(level));
    }

    public static OtherwordlyPatron load(Node node) {
        return valueOf(node.getTextContent());
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }
}
