package characterbuilder.character.spell;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Value;
import static characterbuilder.character.attribute.Value.gp;
import characterbuilder.character.characterclass.wizard.MagicSchool;
import characterbuilder.character.choice.Option;
import static characterbuilder.character.spell.SpellComponent.*;
import static characterbuilder.character.spell.SpellDelegate.spell;
import characterbuilder.utils.StringUtils;
import static characterbuilder.utils.StringUtils.header;
import static characterbuilder.utils.StringUtils.ol;
import static characterbuilder.utils.StringUtils.row;
import static characterbuilder.utils.StringUtils.table;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public enum Spell implements Option {
    ACID_SPLASH(spell(MagicSchool.CONJURATION, 0)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("60 feet").area("Up to 2 creatures within 5 feet").duration("Instantaneous")
        .effect("[max($level 1:1d6,5:2d6,11:3d6,17:4d6)] acid damage.")
        .effect("Dex. save for no damage.")),
    BLADE_WARD(spell(MagicSchool.ABJURATION, 0)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Self").duration("1 round")
        .effect("Resistance against bludgeoning, piercing and slashing damage from weapons.")),
    CHILL_TOUCH(spell(MagicSchool.NECROMANCY, 0)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("120 feet").area("1 creature").duration("1 round")
        .effect("Ranged spell attack [max($level 1:1,5:2,11:3,17:4)]d8 necrotic damage. ")
        .effect("No healing for 1 turn. ")
        .effect("Undead have disadvantage for 1 turn.")),
    DANCING_LIGHTS(spell(MagicSchool.EVOCATION, 0)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("120 feet").area("up to 4 lights").duration("up to 1 minute")
        .effect("Create lights. Move each up to 60 feet as bonus action.")),
    DRUIDCRAFT(spell(MagicSchool.TRANSMUTATION, 0)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("30 feet").duration("Instantaneous")
        .effect("Create sensory effect, predict weather or light or extinguish a flame.")),
    ELDRITCH_BLAST(spell(MagicSchool.EVOCATION, 0)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("120 feet").duration("Instantaneous")
        .effect("[max($level 1:1,5:2,11:3,17:4)] ranged spell [plural(attack,attacks)]. ")
        .effect("1d10 force damage.")),
    FIRE_BOLT(spell(MagicSchool.EVOCATION, 0)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("120 feet").area("1 creature or object").duration("Instantaneous")
        .effect("Ranged spell attack. [max($level 1:1d10,5:2d10,11:3d10,17:4d10)] fire damage.")),
    FRIENDS(spell(MagicSchool.ENCHANTMENT, 0)
        .castingTime("1 action").components(SOMATIC, MATERIAL)
        .range("Self").area("1 non-hostile creature").duration("Concentration, up to 1 minute")
        .effect("Advantage on Chr. checks at target.")),
    GUIDANCE(spell(MagicSchool.DIVINATION, 0)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Touch").area("1 willing creature").duration("up to 1 minute")
        .effect("Add d4 to one ability check.")),
    LIGHT(spell(MagicSchool.EVOCATION, 0)
        .castingTime("1 action").components(VERBAL, MATERIAL)
        .range("Touch").area("1 object").duration("1 hour")
        .effect("Sheds bright light in a 20-foot radius and dim light for an additional 20 feet.")
        .effect("Dex. save for no effect if cast on a creature.")),
    MAGE_HAND(spell(MagicSchool.CONJURATION, 0)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("30 feet").duration("1 minute")
        .effect("Use action to use floating hand.")),
    MENDING(spell(MagicSchool.TRANSMUTATION, 0)
        .castingTime("1 minute").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 broken object").duration("Instantaneous")
        .effect("Mends object.")),
    MESSAGE(spell(MagicSchool.TRANSMUTATION, 0)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("120 feet").area("1 creature").duration("1 round")
        .effect("Target hears message")),
    MINOR_ILLUSION(spell(MagicSchool.ILLUSION, 0)
        .castingTime("1 action").components(SOMATIC, MATERIAL)
        .range("30 feet").area("5-foot cube").duration("1 minute")
        .effect("Create a sound or an image of an object. Investigate save.")),
    POISON_SPRAY(spell(MagicSchool.CONJURATION, 0)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("10 feet").area("1 creature").duration("Instantaneous")
        .effect("[max($level 1:1d12,5:2d12,11:3d12,17:4d12)] poison damage. Con. save.")),
    PRESTIDIGITATION(spell(MagicSchool.TRANSMUTATION, 0)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("10 feet").area("1 cubic foot").duration("Up to 1 hour")
        .effect("Create a harmless sensory effect.")),
    PRODUCE_FLAME(spell(MagicSchool.CONJURATION, 0)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Self").area("Hand").duration("10 minutes")
        .effect("Flame sheds bright light in a 10-foot radius and dim light for an additional 10 "
            + "feet.\nRanged spell attack [max($level 1:1d8,5:2d8,11:3d8,17:4d8)] fire damage.")),
    RAY_OF_FROST(spell(MagicSchool.EVOCATION, 0)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("60 feet").area("1 creature").duration("Instantaneous")
        .effect("Ranged spell attack [max($level 1:1d8,5:2d8,11:3d8,17:4d8)] damage.")
        .effect("-10 Speed for 1 turn.")),
    RESISTANCE(spell(MagicSchool.ABJURATION, 0)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 willing creature").duration("up to 1 minute")
        .effect("Add d4 to one saving throw.")),
    SACRED_FLAME(spell(MagicSchool.EVOCATION, 0)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("60 feet").area("1 creature").duration("Instantaneous")
        .effect("[max($level 1:1d8,5:2d8,11:3d8,17:4d8)] radiant damage.")
        .effect("Dex. save for no damage.")),
    SHILLELAGH(spell(MagicSchool.TRANSMUTATION, 0)
        .castingTime("1 bonus action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("club or quarterstaff").duration("1 minute")
        .effect("Use [$spell_mod] instead of Str. and 1d8 damage on attacks with weapon.")),
    SHOCKING_GRASP(spell(MagicSchool.EVOCATION, 0)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Touch").area("1 creature").duration("Instantaneous")
        .effect("Melee spell attack. Advantage if target wearing metal armour. [max($level "
            + "1:1d8,5:2d8,11:3d8,17:4d8)] lightning damage and no reactions until next turn.")),
    SPARE_THE_DYING(spell(MagicSchool.NECROMANCY, 0)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Touch").area("1 creature with 0 HP").duration("Instantaneous")
        .effect("Becomes stable.")),
    THAUMATURGY(spell(MagicSchool.TRANSMUTATION, 0)
        .castingTime("1 action").components(VERBAL)
        .range("30 feet").duration("Up to 1 minute")
        .effect("Voice booms, flames change, tremors, create sound, door/window opens/closes or change "
            + "eyes.")),
    THORN_WHIP(spell(MagicSchool.TRANSMUTATION, 0)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("30 feet").area("1 creature").duration("Instantaneous")
        .effect("Melee spell attack +[$spell_mod] [max($level 1:1,5:2,11:3,17:4)]d6 piercing damage and "
            + "pull target 10 feet.")),
    TRUE_STRIKE(spell(MagicSchool.DIVINATION, 0)
        .castingTime("1 action").components(SOMATIC)
        .range("30 feet").area("1 creature").duration("up to 1 round")
        .effect("Advantage on next attack against target.")),
    VICIOUS_MOCKERY(spell(MagicSchool.ENCHANTMENT, 0)
        .castingTime("1 action").components(VERBAL)
        .range("60 feet").area("1 creature").duration("Instantaneous")
        .effect("[max($level 1:1d4,5:2d4,11:3d4,17:4d4)] psychic damage and disadvantage on next "
            + "attack. Wis. save.")),
    ALARM(spell(MagicSchool.ABJURATION, 1)
        .castingTime("1 minute").components(VERBAL, SOMATIC, MATERIAL)
        .range("30 feet").area("20-foot cube").duration("8 hours")
        .effect("Audible or mental alarm when Tiny or larger creature touches or enters.")),
    ANIMAL_FRIENDSHIP(spell(MagicSchool.ENCHANTMENT, 1)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("30 feet").area("1 beast").duration("24 hours")
        .effect("If Int. less than 4, Wis. save or charmed.\n+1 beast / level above 1.")),
    ARMOUR_OF_AGATHYS(spell(MagicSchool.ABJURATION, 1)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").duration("1 hour")
        .effect("+5 temporary HP. Attacker takes 5 cold damage.\n+5 HP and damage / extra level.")),
    ARMS_OF_HADAR(spell(MagicSchool.CONJURATION, 1)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Self").area("10-foot radius").duration("Instantaneous")
        .effect("2d6 necrotic damage and no reactions for turn.\nStr save for half damage.\n+1d6 damage "
            + "/ extra level")),
    BANE(spell(MagicSchool.ENCHANTMENT, 1)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("30 feet").area("Up to 3 creatures").duration("up to 1 minute")
        .effect("-1d4 on each attack and saving throw. Chr. save.\n+1 creature / level above 1st")),
    BLESS(spell(MagicSchool.ENCHANTMENT, 1)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("30 feet").area("Up to 3 creatures").duration("up to 1 minute")
        .effect("+1d4 on each attack and saving throw.\n+1 creature / level above 1st.")),
    BURNING_HANDS(spell(MagicSchool.EVOCATION, 1)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Self").area("15-foot cone").duration("Instantaneous")
        .effect("3d6 fire damage. Dex. save half. +1d6 / level above 1st.")),
    CHARM_PERSON(spell(MagicSchool.ENCHANTMENT, 1)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("30 feet").area("1 creature").duration("1 hour")
        .effect("Charmed. Wis. save.\n+1 creature / level above 1st.")),
    CHROMATIC_ORB(spell(MagicSchool.EVOCATION, 1)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("90 feet").duration("Instantaneous")
        .effect("Ranged spell attack [$spell_mod]. 3d8 damage (choose type). +1d8 damage / extra level.")),
    COLOUR_SPRAY(spell(MagicSchool.ILLUSION, 1)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").area("15-foot cone").duration("1 round")
        .effect("6d10 HP of creature blinded. +2d10 / extra level.")),
    COMMAND(spell(MagicSchool.ENCHANTMENT, 1)
        .castingTime("1 action").components(VERBAL)
        .range("60 feet").area("1 creature").duration("1 round")
        .effect("Follow one word command. Wis. save.\n+1 target / extra level.")),
    COMPELLED_DUEL(spell(MagicSchool.ENCHANTMENT, 1)
        .castingTime("1 bonus action").components(VERBAL)
        .range("30 feet").area("1 creature").duration("Concentration, up to 1 minute")
        .effect("Target disadvantaged on attacks against others.\nWis. save to move away.")),
    COMPREHEND_LANGUAGES(spell(MagicSchool.DIVINATION, 1).ritual()
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").duration("1 hour")
        .effect("Understand any language.")),
    CREATE_OR_DESTROY_WATER(spell(MagicSchool.TRANSMUTATION, 1)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("30 feet").area("up to 10 gallons").duration("Instantaneous")
        .effect("Create or destroy up to 10 gallons of water.\n+10 gallons / extra level.")),
    CURE_WOUNDS(spell(MagicSchool.EVOCATION, 1)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Touch").area("1 creature").duration("Instantaneous")
        .effect("Regain 1d8+[$spell_mod]HP.\n+1d8 / extra level.")),
    DETECT_EVIL_AND_GOOD(spell(MagicSchool.DIVINATION, 1)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Self").area("30 feet").duration("up to 10 minutes")
        .effect("Detect good or evil creature or object.")),
    DETECT_MAGIC(spell(MagicSchool.DIVINATION, 1).ritual()
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Self").duration("up to 10 minutes")
        .effect("Detect magical creature or object.")),
    DETECT_POISON_AND_DISEASE(spell(MagicSchool.DIVINATION, 1).ritual()
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").area("30 feet").duration("up to 10 minutes")
        .effect("Detect poison, poisonous creatures and disease.")),
    DISGUISE_SELF(spell(MagicSchool.ILLUSION, 1)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Self").duration("1 hour")
        .effect("Change appearance. Invesigation check to reveal.")),
    DISSONANT_WHISPERS(spell(MagicSchool.ENCHANTMENT, 1)
        .castingTime("1 action").components(VERBAL)
        .range("60 feet").area("1 creature").duration("Instantaneous")
        .effect("3d6 psychic damage and use reaction to move away. Wis. save for half damage only. +1d6 "
            + "damage / extra level.")),
    DIVINE_FAVOUR(spell(MagicSchool.EVOCATION, 1)
        .castingTime("1 bonus action").components(VERBAL, SOMATIC)
        .range("Self").area("1 weapon").duration("Concentration, up to 1 minute")
        .effect("Weapon does +1d4 radiant damage.")),
    ENSNARING_STRIKE(spell(MagicSchool.CONJURATION, 1)
        .castingTime("1 bonus action").components(VERBAL)
        .range("Self").duration("Concentration, up to 1 minute")
        .effect("Next hit Str. save or restrained. 1d6 piercing damage each turn. Str. check DC "
            + "[$spell_dc] to escape. +1d6 / extra level.")),
    ENTANGLE(spell(MagicSchool.CONJURATION, 1)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("90 feet").area("20-foot square").duration("up to 1 minute")
        .effect("Restrain creatures. Str. save.")),
    EXPEDITIOUS_RETREAT(spell(MagicSchool.TRANSMUTATION, 1)
        .castingTime("1 bonus action").components(VERBAL, SOMATIC)
        .range("Self").duration("up to 10 minutes")
        .effect("Dash as a bonus action each turn.")),
    FAERIE_FIRE(spell(MagicSchool.EVOCATION, 1)
        .castingTime("1 action").components(VERBAL)
        .range("60 feet").area("20' cube").duration("up to 1 minute")
        .effect("Creatures give advantage on attack. Dex. save.")),
    FALSE_LIFE(spell(MagicSchool.NECROMANCY, 1)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").duration("1 hour")
        .effect("1d4 + 4 temporary hit points.\n+5HP / level above 1st.")),
    FEATHER_FALL(spell(MagicSchool.TRANSMUTATION, 1)
        .castingTime("1 reaction on falling").components(VERBAL, MATERIAL)
        .range("60 feet").area("Up to 5 creatures").duration("1 minute")
        .effect("Take no damage from fall.")),
    FIND_FAMILIAR(spell(MagicSchool.CONJURATION, 1).ritual()
        .castingTime("1 hour").components(VERBAL, SOMATIC, MATERIAL)
        .range("10 feet").duration("Instantaneous")
        .effect("Summon a familiar.")),
    FLOATING_DISK(spell(MagicSchool.CONJURATION, 1)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("30 feet").area("3 feet x 1 inch").duration("1 hour")
        .effect("Creates disk. Holds 500lbs. Moves with caster.")),
    FOG_CLOUD(spell(MagicSchool.CONJURATION, 1)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("120 feet").area("20-foot-radius sphere").duration("up to 1 hour")
        .effect("Area heavily obscured. +20 feet radius / extra level.")),
    GOODBERRY(spell(MagicSchool.TRANSMUTATION, 1)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("Up to 10 berries").duration("Instantaneous")
        .effect("Use action to eat 1 berry gain 1HP and sustain creature for 1 day.")),
    GREASE(spell(MagicSchool.CONJURATION, 1)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("60 feet").area("10-foot square").duration("1 minute")
        .effect("Fall prone. Dex. save.")),
    GUIDING_BOLT(spell(MagicSchool.EVOCATION, 1)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("120 feet").area("1 creature").duration("1 round")
        .effect("Ranged spell attack. On hit 4d6 radiant damage. Next attack has advantage.\n+1d6 "
            + "damage / extra level.")),
    HAIL_OF_THORNS(spell(MagicSchool.CONJURATION, 1)
        .castingTime("1 bonus action").components(VERBAL)
        .range("Self").duration("Concentration, up to 1 minute")
        .effect("Next ranged hit, creatures within 5 feet of target take 1d10 piercing damage. Dex "
            + "save. for half damage.")),
    HEALING_WORD(spell(MagicSchool.EVOCATION, 1)
        .castingTime("1 bonus action").components(VERBAL)
        .range("60 feet").area("1 creature").duration("Instantaneous")
        .effect("1d4 + [$spell_mod] HP. +1d4 / extra level.")),
    HELLISH_REBUKE(spell(MagicSchool.EVOCATION, 1)
        .castingTime("1 reaction on taking damage").components(VERBAL, SOMATIC)
        .range("60 feet").area("Attacker").duration("Instantaneous")
        .effect("2d10 fire damage. Dex. save for half.\n+1d10 / extra level.")),
    HEROISM(spell(MagicSchool.ENCHANTMENT, 1)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Touch").area("1 creature").duration("up to 1 minute")
        .effect("Immune to fear and +[$spell_mod] temporary HP each turn.\n+ 1 creature / extra level.")),
    HEX(spell(MagicSchool.ENCHANTMENT, 1)
        .castingTime("1 bonus action").components(VERBAL, SOMATIC, MATERIAL)
        .range("90 feet").area("1 creature").duration("Concentration, up to 1 hour")
        .effect("Extra 1d6 necrotic damage from attacks by caster on target. Disadvantage on ability "
            + "checks with chosen ability. 3rd level 8 hours, 5th level 24 hours.")),
    HIDEOUS_LAUGHTER(spell(MagicSchool.ENCHANTMENT, 1)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("30 feet").area("1 creature with Int. over 4")
        .duration("Concentration, up to 1 minute")
        .effect("Target incapacitated. Wis. save. "
            + "Save each turn and on damage to end.")),
    HUNTERS_MARK(spell(MagicSchool.DIVINATION, 1)
        .castingTime("1 bonus action").components(VERBAL)
        .range("90 feet").area("1 creature").duration("up to 1 hour")
        .effect("+1d6 damage on hit with a weapon attack. Advantage on Perception and Survival to find. "
            + "\n3rd level: 8 hours, 5th level: 24 hours.")),
    IDENTIFY(spell(MagicSchool.DIVINATION, 1).ritual()
        .castingTime("1 minute").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 object").duration("Instantaneous")
        .effect("Learn magical properties.")),
    ILLUSORY_SCRIPT(spell(MagicSchool.ILLUSION, 1).ritual()
        .castingTime("1 minute").components(SOMATIC, MATERIAL)
        .range("Touch").area("Parchment").duration("10 days")
        .effect("Write hidden message.")),
    INFLICT_WOUNDS(spell(MagicSchool.NECROMANCY, 1)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Touch").area("1 creature").duration("Instantaneous")
        .effect("Melee spell attack. 3d10 necrotic damage.\n+1d10 / extra level.")),
    JUMP(spell(MagicSchool.TRANSMUTATION, 1)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 creature").duration("1 minute")
        .effect("Jump distance tripled.")),
    LONGSTRIDER(spell(MagicSchool.TRANSMUTATION, 1)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 creature").duration("1 hour")
        .effect("You touch a creature. Speed increases by 10 feet.\n+1 creature / extra level.")),
    MAGE_ARMOR(spell(MagicSchool.ABJURATION, 1)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 unarmoured creature").duration("8 hours")
        .effect("AC 13 + Dex. modifier.")),
    MAGIC_MISSILE(spell(MagicSchool.EVOCATION, 1)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("120 feet").area("3 darts").duration("Instantaneous")
        .effect("1d4 + 1 force damage. +1 dart / extra level.")),
    PROTECTION_FROM_EVIL_AND_GOOD(spell(MagicSchool.ABJURATION, 1)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 willing creature").duration("Concentration up to 10 minutes")
        .effect("Protected against aberrations, celestials, elementals, fey, fiends, and undead. Can't "
            + "be charmed, frightened or possessed by these creatures. Creatures have disadvantage on "
            + "attacks.")),
    PURIFY_FOOD_AND_DRINK(spell(MagicSchool.TRANSMUTATION, 1).ritual()
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("10 feet").area("5-foot-radius sphere").duration("Instantaneous")
        .effect("Food and drink rendered free of poison and disease.")),
    RAY_OF_SICKNESS(spell(MagicSchool.NECROMANCY, 1)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("60 feet").area("1 creature").duration("Instantaneous")
        .effect("Ranged spell attack +[$spell_mod] 2d8 poison damage. Con. save or poisoned until next "
            + "turn. +1d8 damage / extra level.")),
    SANCTUARY(spell(MagicSchool.ABJURATION, 1)
        .castingTime("1 bonus action").components(VERBAL, SOMATIC, MATERIAL)
        .range("30 feet").area("1 creature").duration("1 minute")
        .effect("Creatures attacking target Wis. save or choose new target.")),
    SEARING_SMITE(spell(MagicSchool.EVOCATION, 1)
        .castingTime("1 bonus action").components(VERBAL)
        .range("Self").area("1 weapon").duration("Concentration, up to 1 minute")
        .effect("On next hit +1d6 fire damage and target ignites. 1d6 fire damage each turn. Con. save "
            + "ends. +1d6 initial damage / extra level.")),
    SHIELD(spell(MagicSchool.ABJURATION, 1)
        .castingTime("1 reaction").components(VERBAL, SOMATIC)
        .range("Self").duration("1 round")
        .effect("+5 bonus to AC. No damage from magic missile.")),
    SHIELD_OF_FAITH(spell(MagicSchool.ABJURATION, 1)
        .castingTime("1 bonus action").components(VERBAL, SOMATIC, MATERIAL)
        .range("60 feet").area("1 creature").duration("up to 10 minutes")
        .effect("+2 bonus to AC.")),
    SILENT_IMAGE(spell(MagicSchool.ILLUSION, 1)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("60 feet").area("15-foot cube").duration("up to 10 minutes")
        .effect("Create a visual image. Investigation check to reveal.")),
    SLEEP(spell(MagicSchool.ENCHANTMENT, 1)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("90 feet").area("20 feet").duration("1 minute")
        .effect("5d8 HP of creatures fall asleeep.\n+2d8 HP / extra level.")),
    SPEAK_WITH_ANIMALS(spell(MagicSchool.DIVINATION, 1).ritual()
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Self").duration("10 minutes")
        .effect("Communicate with beasts.")),
    THUNDEROUS_SMITE(spell(MagicSchool.EVOCATION, 1)
        .castingTime("1 bonus action").components(VERBAL)
        .range("Self").area("1 weapon").duration("Concentration, up to 1 minute")
        .effect("On next hit +2d6 thunder damage. Str. save or be pushed 10 feet and knocked prone.")),
    THUNDERWAVE(spell(MagicSchool.EVOCATION, 1)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Self").area("15-foot cube").duration("Instantaneous")
        .effect("2d8 thunder damage and pushed 10 feet. Con. save for half and no push.\nCreates "
            + "thunderous boom audible out to 300 feet.\n+1d8 damage / extra level.")),
    UNSEEN_SERVANT(spell(MagicSchool.CONJURATION, 1).ritual()
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("60 feet").duration("1 hour")
        .effect("Creates force AC 10, 1 HP, Str. 2. As bonus action perfom simple tasks.")),
    WITCH_BOLT(spell(MagicSchool.EVOCATION, 1)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("30 feet").area("1 creature").duration("Concentration, up to 1 minute")
        .effect("Ranged spell attack +[$spell_mod] 1d12 lightning damage. Each turn use action for 1d12 "
            + "lightning damage")),
    WRATHFUL_SMITE(spell(MagicSchool.EVOCATION, 1)
        .castingTime("1 bonus action").components(VERBAL)
        .range("Self").area("1 weapon").duration("Concentration, up to 1 minute")
        .effect("On next hit +1d6 psychic damage. Wis. save DC [$spell_dc] or be frightened of caster.")),
    ACID_ARROW(spell(MagicSchool.EVOCATION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("90 feet").area("1 creature").duration("Instantaneous")
        .effect("Ranged spell attack 4d4 acid damage +2d4 end of next turn. "
            + "On miss, half damage on hit only. "
            + "+1d4 damage / extra level.")),
    AID(spell(MagicSchool.ABJURATION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("30 feet").area("Up to 3 allies").duration("8 hours")
        .effect("+5 temporary HP.\n+5HP / level above 2")),
    ALTER_SELF(spell(MagicSchool.TRANSMUTATION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Self").duration("up to 1 hour")
        .effect("Assume a different form.")),
    ANIMAL_MESSENGER(spell(MagicSchool.ENCHANTMENT, 2).ritual()
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("30 feet").area("1 Tiny beast").duration("24 hours")
        .effect("Animal delivers a message.\n+48 hours / level above 2nd.")),
    ARCANE_LOCK(spell(MagicSchool.ABJURATION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL).cost(gp(25))
        .range("Touch").area("1 entryway").duration("Until dispelled")
        .effect("Magic lock. +10 DC to break or pick.")),
    ARCANISTS_MAGIC_AURA(spell(MagicSchool.ILLUSION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 creature or object").duration("24 hours")
        .effect("Cause false magical aura or mask magical effect.")),
    AUGURY(spell(MagicSchool.DIVINATION, 2).ritual()
        .castingTime("1 minute").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").duration("Instantaneous")
        .effect("Divine outcomes of plan: Weal, Woe, Weal and Woe or Nothing")),
    BARKSKIN(spell(MagicSchool.TRANSMUTATION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 creature").duration("up to 1 hour")
        .effect("AC16")),
    BEAST_SENSE(spell(MagicSchool.DIVINATION, 2).ritual()
        .castingTime("1 action").components(SOMATIC)
        .range("touch").area("1 beast").duration("up to 1 hour")
        .effect("see and hear through beast's eyes and ears.")),
    BLINDNESS_DEAFNESS(spell(MagicSchool.NECROMANCY, 2)
        .castingTime("1 action").components(VERBAL)
        .range("30 feet").area("1 creature").duration("1 minute")
        .effect("Target blinded or deafened. Con. save each turn.\n+1 creature / level above 2nd.")),
    BLUR(spell(MagicSchool.ILLUSION, 2)
        .castingTime("1 action").components(VERBAL)
        .range("Self").duration("up to 1 minute")
        .effect("Disadvantage attack rolls.")),
    BRANDING_SMITE(spell(MagicSchool.EVOCATION, 2)
        .castingTime("1 bonus action").components(VERBAL)
        .range("Self").area("1 weapon").duration("Concentration, up to 1 minute")
        .effect("+2d6 radiant damage from next attack. +1d6 damage / extra level")),
    CALM_EMOTIONS(spell(MagicSchool.ENCHANTMENT, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("60 feet").area("20-foot-radius spher").duration("up to 1 minute")
        .effect("Prevent charm or fear or make indifferent. Chr. save.")),
    CLOUD_OF_DAGGERS(spell(MagicSchool.CONJURATION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("60 feet").area("5-foot cube").duration("Concentration, up to 1 minute")
        .effect("4d4 slashing damage. +2d4 / extra level.")),
    CONTINUAL_FLAME(spell(MagicSchool.EVOCATION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 object").duration("Until dispelled")
        .effect("Flame created on object")),
    CORDON_OF_ARROWS(spell(MagicSchool.TRANSMUTATION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("5 feet").area("30 feet").duration("8 hours")
        .effect("4 arrows planted in ground fire at enemies entering area. 1d6 piercing damage. Dex. "
            + "save. +1 arrow / extra level.")),
    CROWN_OF_MADNESS(spell(MagicSchool.ENCHANTMENT, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("120 feet").area("1 humanoid").duration("Concentration, up to 1 minute")
        .effect("Charmed to attack chosen targets. Wis. save each turn to end.")),
    DARKNESS(spell(MagicSchool.EVOCATION, 2)
        .castingTime("1 action").components(VERBAL, MATERIAL)
        .range("60 feet").area("15-foot radius sphere").duration("up to 10 minutes")
        .effect("Complete darkness in area.")),
    DARKVISION(spell(MagicSchool.TRANSMUTATION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 creature").duration("8 hours")
        .effect("Target has darkvision to a range of 60 feet.")),
    DETECT_THOUGHTS(spell(MagicSchool.DIVINATION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").area("1 creature within 30 feet").duration("up to 1 minute")
        .effect("As action, probe mind. Wis. save.")),
    ENHANCE_ABILITY(spell(MagicSchool.TRANSMUTATION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 creature").duration("up to 1 hour.")
        .effect("Advantage on Con. checks and 2d6 temporary HP, or\nAdvantage on Str. checks and double "
            + "carrying capacity, or\nAdvantage on Dex. checks and no falling damage from 20 feet, "
            + "or\nAdvantage on Chr. Int. or Wis. checks.\n+1 creature / extra level.")),
    ENLARGE_REDUCE(spell(MagicSchool.TRANSMUTATION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("30 feet").area("1 creature or object").duration("up to 1 minute")
        .effect("Enlarge: advantage on Str. checks and +1d4 damage, or\nReduce: disadvantage on Str. "
            + "checks and -1d4 damage.")),
    ENTHRALL(spell(MagicSchool.ENCHANTMENT, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("60 feet").area("All creatures").duration("1 minute")
        .effect("Disadvantage on Perception checks. Wis. save.")),
    FIND_STEED(spell(MagicSchool.CONJURATION, 2)
        .castingTime("10 minutes").components(VERBAL, SOMATIC)
        .range("30 feet").area("Unoccupied space").duration("Instantaneous")
        .effect("Summon steed.")),
    FIND_TRAPS(spell(MagicSchool.DIVINATION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("120 feet").duration("Instantaneous")
        .effect("Reveals if a trap is present.")),
    FLAME_BLADE(spell(MagicSchool.EVOCATION, 2)
        .castingTime("1 bonus action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").duration("up to 10 minutes")
        .effect("Create flaming blade. 3d6 fire damage on hit. +1d6 / extra 2 levels.")),
    FLAMING_SPHERE(spell(MagicSchool.CONJURATION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("60 feet").area("5-foot-diameter sphere").duration("up to 1 minute")
        .effect("Creature within 5 feet of sphere takes 2d6 fire damage. Dex. save half.\nAs a bonus "
            + "action, move sphere 30 feet.\n+1d6 damage / extra level.")),
    GENTLE_REPOSE(spell(MagicSchool.NECROMANCY, 2).ritual()
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("Corpse").duration("10 days")
        .effect("Protected from decay and becoming undead.")),
    GUST_OF_WIND(spell(MagicSchool.EVOCATION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").area("60-foot line").duration("up to 1 minute")
        .effect("Pushed 15 feet away. Str. save. Doubles movement cost towards caster.")),
    HEAT_METAL(spell(MagicSchool.TRANSMUTATION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("60 feet").area("1 metal object").duration("up to 1 minute")
        .effect("Creature contacting object takes 2d8 fire damage. Con. save or drop object.\n+1d8 / "
            + "extra level.")),
    HOLD_PERSON(spell(MagicSchool.ENCHANTMENT, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("60 feet").area("1 humanoid").duration("up to 1 minute")
        .effect("Target paralysed. Wis. save.\n+1 humanoid / extra level.")),
    INVISIBILITY(spell(MagicSchool.ILLUSION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 creature").duration("up to 1 hour")
        .effect("Target becomes invisible.\n+1 creature / extra level.")),
    KNOCK(spell(MagicSchool.TRANSMUTATION, 2)
        .castingTime("1 action").components(VERBAL)
        .range("60 feet").area("1 object").duration("Instantaneous")
        .effect("Unlock object. Makes loud sound.")),
    LESSER_RESTORATION(spell(MagicSchool.ABJURATION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Touch").area("1 creature").duration("Instantaneous")
        .effect("End one condition.")),
    LEVITATE(spell(MagicSchool.TRANSMUTATION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("60 feet").area("1 creature or object up to 500lbs").duration("up to 10 minutes")
        .effect("Rises vertically up to 20 feet. Con. save.")),
    LOCATE_ANIMALS_OR_PLANTS(spell(MagicSchool.DIVINATION, 2).ritual()
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").area("1 kind of beast or plant").duration("Instantaneous")
        .effect("Sense direction and distance to closest within 5 miles, if any.")),
    LOCATE_OBJECT(spell(MagicSchool.DIVINATION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").area("1 object").duration("up to 10 minutes")
        .effect("Sense direction to object if within 1,000 feet")),
    MAGIC_MOUTH(spell(MagicSchool.ILLUSION, 2).ritual()
        .castingTime("1 minute").components(VERBAL, SOMATIC, MATERIAL)
        .range("30 feet").area("1 object").duration("Until dispelled")
        .effect("Message on trigger.")),
    MAGIC_WEAPON(spell(MagicSchool.TRANSMUTATION, 2)
        .castingTime("1 bonus action").components(VERBAL, SOMATIC)
        .range("Touch").area("1 nonmagical weapon").duration("up to 1 hour")
        .effect("+1 bonus to attack rolls and damage. +2 at 4th level. +3 at 6th level.")),
    MIRROR_IMAGE(spell(MagicSchool.ILLUSION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Self").area("3 duplicates").duration("1 minute")
        .effect("On being targeted, roll d20 to hit image: 6+ for 3, 8+ for 2, 11+ for 1\nImages have "
            + "AC[10+$dex_mod].")),
    MISTY_STEP(spell(MagicSchool.CONJURATION, 2)
        .castingTime("1 bonus action").components(VERBAL)
        .range("Self").duration("Instantaneous")
        .effect("Teleport up to 30 feet.")),
    MOONBEAM(spell(MagicSchool.EVOCATION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("120 feet").area("5-foot-radius, 40-foot-high cylinder").duration("up to 1 minute")
        .effect("Creatures entering 2d10 radiant damage. Con. save for half.\nUse an action to move "
            + "beam 60 feet.\n+1d10 damage / extra level.")),
    PASS_WITHOUT_TRACE(spell(MagicSchool.ABJURATION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").area("Allies within 30'").duration("up to 1 hour")
        .effect("+10 bonus to Stealth checks. Cannot be tracked.")),
    PHANTASMAL_FORCE(spell(MagicSchool.ILLUSION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("60 feet").area("1 creature").duration("Concentration, up to 1 minute")
        .effect("Int. save or create illusion for target. 1d6 psychic damage each turn.")),
    PRAYER_OF_HEALING(spell(MagicSchool.EVOCATION, 2)
        .castingTime("10 minutes").components(VERBAL)
        .range("30 feet").area("Up to 6 creatures").duration("Instantaneous")
        .effect("Regain 2d8+[$spell_mod]HP.\n+1d8 HP / extra level.")),
    PROTECTION_FROM_POISON(spell(MagicSchool.ABJURATION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Touch").area("1 creature").duration("1 hour")
        .effect("Neutralize poison. Advantage on saves vs positon. Resistance to poison damage.")),
    RAY_OF_ENFEEBLEMENT(spell(MagicSchool.NECROMANCY, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("60 feet").area("1 creature").duration("up to 1 minute")
        .effect("Ranged spell attack. Target deals half damage with attacks.")
        .effect("Con. save each turn to end.")),
    ROPE_TRICK(spell(MagicSchool.TRANSMUTATION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 60-foot rope").duration("1 hour")
        .effect("Rope ends in extradimensional space that can hold up to 8 creatures.")),
    SCORCHING_RAY(spell(MagicSchool.EVOCATION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("120 feet").area("3 rays of fire").duration("Instantaneous")
        .effect("Ranged spell attack for each ray. 2d6 fire damage.\n+1 ray / extra level.")),
    SEE_INVISIBILITY(spell(MagicSchool.DIVINATION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").duration("1 hour")
        .effect("See invisible creatures and objects.")),
    SHATTER(spell(MagicSchool.EVOCATION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("60 feet").area("10-foot-radius sphere").duration("Instantaneous")
        .effect("3d8 thunder damage. Con. save for half.\n+1d8 damage / extra level.")),
    SILENCE(spell(MagicSchool.ILLUSION, 2).ritual()
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("120 feet").area("20-foot-radius sphere").duration("up to 10 minutes")
        .effect("No sound can be created within or pass through.")),
    SPIDER_CLIMB(spell(MagicSchool.TRANSMUTATION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 willing creature").duration("up to 1 hour")
        .effect("Move on vertical surfaces and ceilings.\nClimbing speed equal to walking speed.")),
    SPIKE_GROWTH(spell(MagicSchool.TRANSMUTATION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("150 feet").area("20-foot radius").duration("up to 10 minutes")
        .effect("Difficult terrain. 2d4 piercing damage for each 5 feet of travel. "
            + "Perception check to recognize.")),
    SPIRITUAL_WEAPON(spell(MagicSchool.EVOCATION, 2)
        .castingTime("1 bonus action").components(VERBAL, SOMATIC)
        .range("60 feet").duration("1 minute")
        .effect("Create weapon. Melee spell attack. 1d8+[$spell_mod] force damage.\nAs bonus action "
            + "move weapon up to 20 feet.\n+1d8 damage / 2 extra levels.")),
    SUGGESTION(spell(MagicSchool.ENCHANTMENT, 2)
        .castingTime("1 action").components(VERBAL, MATERIAL)
        .range("30 feet").area("1 creature").duration("up to 8 hours")
        .effect("Suggest action. Wis. save.")),
    WARDING_BOND(spell(MagicSchool.ABJURATION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 willing creature").duration("1 hour")
        .effect("+1 bonus to AC and saves, resistance to damage.\nCaster takes same damage.")),
    WEB(spell(MagicSchool.CONJURATION, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("60 feet").area("20-foot cube").duration("up to 1 hour")
        .effect("Restrain creatures. Dex. save. Str. check to break free.")),
    ZONE_OF_TRUTH(spell(MagicSchool.ENCHANTMENT, 2)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("60 feet").area("15-foot-radius sphere").duration("10 minutes")
        .effect("Creatures in area cannot lie. Chr. save.")),
    ANIMATE_DEAD(spell(MagicSchool.NECROMANCY, 3)
        .castingTime("1 minute").components(VERBAL, SOMATIC, MATERIAL)
        .range("10 feet").area("bones or corpose").duration("Instantaneous")
        .effect("Creates and controls a skeleton or zombie for 24 hours.\n+2 undead / extra level.")),
    AURA_OF_VITALITY(spell(MagicSchool.EVOCATION, 3)
        .castingTime("1 action").components(VERBAL)
        .range("Self").area("30-foot radius").duration("Concentration, up to 1 minute")
        .effect("Use bonus action to cure 2d6 HP.")),
    BEACON_OF_HOPE(spell(MagicSchool.ABJURATION, 3)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("30 feet").area("Any creatures").duration("up to 1 minute")
        .effect("Advantage on Wis and death saves and regains max HP from healing.")),
    BESTOW_CURSE(spell(MagicSchool.NECROMANCY, 3)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Touch").area("1 creature").duration("up to 1 minute")
        .effect("Cure creature. Wis. save. Disadvantage on checks and saves with chosen score. "
            + "Disadvantage on attacks on caster. Wis. save each turn or do nothing. Caster's attacks "
            + "cause +1d8 necrotic damage to target.4th: up to 10 minutes; 5th: 8 hours, 7th: 24 "
            + "hours, 9th: until dispelled.")),
    BLINDING_SMITE(spell(MagicSchool.EVOCATION, 3)
        .castingTime("1 bonus action").components(VERBAL)
        .range("Self").duration("Concentraton, up to 1 minute")
        .effect("Next hit, +3d8 damage and Con. save or blinded.")),
    BLINK(spell(MagicSchool.TRANSMUTATION, 3)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Self").duration("1 minute")
        .effect("Each turn, d20 roll 11+ disappear for 1 turn and return on following turn to chosen "
            + "space within 10'.")),
    CALL_LIGHTNING(spell(MagicSchool.CONJURATION, 3)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("120 feet").area("10-foot tall 60-foot radius cylinder").duration("up to 10 minutes")
        .effect("Bolt of lightning. Each creatures within 5 feet takes 3d10 lightinging damage. Dex. "
            + "save for half. Call lightning as action each turn. +1d10 damage / extra level.")),
    CLAIRVOYANCE(spell(MagicSchool.DIVINATION, 3)
        .castingTime("10 minutes").components(VERBAL, SOMATIC, MATERIAL)
        .range("1 mile").area("1 location").duration("up to 10 minutes")
        .effect("Create invisible sensor that can either see or hear.")),
    CONJURE_ANIMALS(spell(MagicSchool.CONJURATION, 3)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("60 feet").area("Unoccupied space").duration("up to 1 hour")
        .effect("Summon fey beasts: 1 CR2 or 2 CR1 or 4 CR1/2. Increase number at levels 5th: x2, 7th: "
            + "x3, 9th: x4.")),
    CONJURE_BARRAGE(spell(MagicSchool.CONJURATION, 3)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").area("60-foot cone").duration("Instantaneous")
        .effect("3d8 damage, Dex. save for half.")),
    COUNTERSPELL(spell(MagicSchool.ABJURATION, 3)
        .castingTime("1 reaction").components(SOMATIC)
        .range("60 feet").area("1 creature casting spell").duration("Instantaneous")
        .effect("Spells level 1-3 fail. Level 4+ Ability check DC 10+spell level. Level of check "
            + "increases with level of cast.")),
    CREATE_FOOD_AND_WATER(spell(MagicSchool.CONJURATION, 3)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("30 feet").area("Unoccupied space").duration("Instantaneous")
        .effect("Create provisions for 24 hours.")),
    CRUSADERS_MANTLE(spell(MagicSchool.EVOCATION, 3)
        .castingTime("1 action").components(VERBAL)
        .range("Self").area("30-foot radius").duration("Concentration, up to 1 minute")
        .effect("Allies deal +1d4 radiant damage with weapon attacks.")),
    DAYLIGHT(spell(MagicSchool.EVOCATION, 3)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("60 feet").area("60-foot-radius sphere").duration("1 hour")
        .effect("Create bright light.")),
    DISPEL_MAGIC(spell(MagicSchool.ABJURATION, 3)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("120 feet").area("1 creature, object or effect").duration("Instantaneous")
        .effect("Spells to slot level end. Otherwise ability check DC 10 + spell level.")),
    ELEMENTAL_WEAPON(spell(MagicSchool.TRANSMUTATION, 3)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Touch").area("1 weapon").duration("Concentration, up to 1 hour")
        .effect("+1 attack +1d4 damage (choose type). 5th level +2 attack +2d4 damage. 7th level +3 "
            + "attack +3d4 damage.")),
    FEAR(spell(MagicSchool.ILLUSION, 3)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").area("30-foot cone").duration("up to 1 minute")
        .effect("Wis. save or drop item and become frightened. Must take the Dash action away from caster.")),
    FEIGN_DEATH(spell(MagicSchool.NECROMANCY, 3).ritual()
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 willing creature").duration("1 hour")
        .effect("Target appears dead.")),
    FIREBALL(spell(MagicSchool.EVOCATION, 3)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("150 feet").area("20-foot-radius sphere").duration("Instantaneous")
        .effect("8d6 fire damage. Dex save for half. +1d6 / extra level.")),
    FLY(spell(MagicSchool.TRANSMUTATION, 3)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 willing creature").duration("up to 10 minutes")
        .effect("Flying speed of 60 feet for the duration. +1 creature / extra level.")),
    GASEOUS_FORM(spell(MagicSchool.TRANSMUTATION, 3)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 willing creature").duration("up to 1 hour")
        .effect("YTransform target into misty cloud. Fly speed 10.")),
    GLYPH_OF_WARDING(spell(MagicSchool.ABJURATION, 3)
        .castingTime("1 hour").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("surface or object").duration("Until dispelled or triggered")
        .effect("Hidden glyph. Investigation check to find.On trigger: explode in 20-foot-radius sphere "
            + "5d8 (+1d8 / extra level) damage Dex. save for half, or prepare spell of same level "
            + "cast on triggerer.")),
    HASTE(spell(MagicSchool.TRANSMUTATION, 3)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("30 feet").area("1 willing creature").duration("up to 1 minute")
        .effect("Double speed, +2 AC, advantage on Dex. saves, +1 action. 1 turn of inactivity when "
            + "spell ends.")),
    HUNGER_OF_HADAR(spell(MagicSchool.CONJURATION, 3)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("150 feet").area("20-foot radius").duration("Concentration, up to 1 minute")
        .effect("2d6 cold damage at start of turn. Dex. save or 2d6 acid damage at end of turn.")),
    HYPNOTIC_PATTERN(spell(MagicSchool.ILLUSION, 3)
        .castingTime("1 action").components(SOMATIC, MATERIAL)
        .range("120 feet").area("30-foot cube").duration("up to 1 minute")
        .effect("Wis. save or charmed. Incapacitated and speed 0.")),
    LIGHTNING_ARROW(spell(MagicSchool.TRANSMUTATION, 3)
        .castingTime("1 bonus action").components(VERBAL, SOMATIC)
        .range("Self").duration("Concentration, up to 1 minute")
        .effect("Next ranged attack 4d8 lightning damage on hit, half on miss.Creatures within 10 feet "
            + "2d8 lightning damage. Dex. save for half. +1d8 damage / extra level")),
    LIGHTNING_BOLT(spell(MagicSchool.EVOCATION, 3)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").area("100-foot line").duration("Instantaneous")
        .effect("8d6 lightning damage. Dex. save for half. +1d6 damage / extra level.")),
    MAGIC_CIRCLE(spell(MagicSchool.ABJURATION, 3)
        .castingTime("1 minute").components(VERBAL, SOMATIC, MATERIAL)
        .range("10 feet").area("10-foot-radius, 20-foot-tall cylinder").duration("1 hour")
        .effect("Specified type of creature cannot enter cylinder. +1 hour / extra level.")),
    MAJOR_IMAGE(spell(MagicSchool.ILLUSION, 3)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("120 feet").area("20-foot cube").duration("up to 10 minutes")
        .effect("Create an illusion. Investigation check DC [$spell_dc] to discern illusion. 6th level: "
            + "lasts until dispelled.")),
    MASS_HEALING_WORD(spell(MagicSchool.EVOCATION, 3)
        .castingTime("1 bonus action").components(VERBAL)
        .range("60 feet").area("up to 6 creatures").duration("Instantaneous")
        .effect("Regain 1d4+[$spell_mod] HP. +1d4 / extra level.")),
    MELD_INTO_STONE(spell(MagicSchool.TRANSMUTATION, 3).ritual()
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Touch").area("Stone object or surface").duration("8 hours")
        .effect("Meld body and equipment. Disadvantage on Perception checks.")),
    NONDETECTION(spell(MagicSchool.ABJURATION, 3)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("willing creature, place or object").duration("8 hours")
        .effect("Hide target from divination magic.")),
    PHANTOM_STEED(spell(MagicSchool.ILLUSION, 3).ritual()
        .castingTime("1 minute").components(VERBAL, SOMATIC)
        .range("30 feet").area("Unoccupied space").duration("1 hour")
        .effect("Summon horselike creature to ride. Speed 100.")),
    PLANT_GROWTH(spell(MagicSchool.TRANSMUTATION, 3)
        .castingTime("1 action or 8 hours").components(VERBAL, SOMATIC)
        .range("150 feet").area("100-foot radius").duration("Instantaneous")
        .effect("1 action: creatures spend 4 feet of movement. 8 hours: enrich plants for 1 year.")),
    PROTECTION_FROM_ENERGY(spell(MagicSchool.ABJURATION, 3)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Touch").area("1 willing creature").duration("up to 1 hour")
        .effect("Resistance to acid, cold, fire, lightning, or thunder.")),
    REMOVE_CURSE(spell(MagicSchool.ABJURATION, 3)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Touch").area("1 creature or object").duration("Instantaneous")
        .effect("All curses on creature end. Breaks attunement to cursed object.")),
    REVIVIFY(spell(MagicSchool.NECROMANCY, 3)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 creature dead less than 1 minute").duration("Instantaneous")
        .effect("Creature returns to life with 1 HP.")),
    SENDING(spell(MagicSchool.EVOCATION, 3)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Unlimited").area("1 creature").duration("1 round")
        .effect("Send a short message to target and receive reply.")),
    SLEET_STORM(spell(MagicSchool.CONJURATION, 3)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("150 feet").area("20-foot-tall 40-foot radius cylinder").duration("up to 1 minute")
        .effect("Freezing rain and sleet fall in area. Heavily obscured. Dex. save or fall prone. Con. "
            + "save DC [$spell_dc] or lose concentration.")),
    SLOW(spell(MagicSchool.TRANSMUTATION, 3)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("120 feet").area("up to 6 creature in 40-foot cube").duration("up to 1 minute")
        .effect("Wis. save each turn. Speed halved -2 AC and Dex. save and no reactions. On spell cast "
            + "on 11+ on d20 spell effect next turn.")),
    SPEAK_WITH_DEAD(spell(MagicSchool.NECROMANCY, 3)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("10 feet").area("1 corpse").duration("10 minutes")
        .effect("Ask corpse 5 questions.")),
    SPEAK_WITH_PLANTS(spell(MagicSchool.TRANSMUTATION, 3)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Self").area("30-foot radius").duration("10 minutes")
        .effect("Question and command plants.")),
    SPIRIT_GUARDIANS(spell(MagicSchool.CONJURATION, 3)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").area("15-foot radius").duration("up to 10 minutes")
        .effect("Speed halved, 3d8 radiant/necrotic damage. Wis. save for half. +1d8 / extra level.")),
    STINKING_CLOUD(spell(MagicSchool.CONJURATION, 3)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("90 feet").area("20-foot-radius sphere").duration("up to 1 minute")
        .effect("Incapacitated. Con. save.")),
    TINY_HUT(spell(MagicSchool.EVOCATION, 3).ritual()
        .castingTime("1 minute").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").area("10-foot hemisphere").duration("8 hours")
        .effect("Dome of force that can contain up to 9 creatures.")),
    TONGUES(spell(MagicSchool.DIVINATION, 3)
        .castingTime("1 action").components(VERBAL, MATERIAL)
        .range("Touch").area("1 creature").duration("1 hour")
        .effect("Understand and speak any spoken language.")),
    VAMPIRIC_TOUCH(spell(MagicSchool.NECROMANCY, 3)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Self").area("1 creature").duration("up to 1 minute")
        .effect("Melee spell attack 3d6 necrotic damage and regain half as HP. +1d6 / extra level.")),
    WATER_BREATHING(spell(MagicSchool.TRANSMUTATION, 3).ritual()
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("30 feet").area("10 willing creatures").duration("24 hours")
        .effect("Breathe underwater.")),
    WATER_WALK(spell(MagicSchool.TRANSMUTATION, 3).ritual()
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("30 feet").area("10 willing creatures").duration("1 hour")
        .effect("Move across any liquid.")),
    WIND_WALL(spell(MagicSchool.EVOCATION, 3)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("120 feet").area("50-feet long, 15-feet high, 1-foot thick")
        .duration("up to 1 minute")
        .effect("3d8 bludgeoning damage. Str. save for half.")),
    ARCANE_EYE(spell(MagicSchool.DIVINATION, 4)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("30 feet").duration("up to 1 hour")
        .effect("Create an invisible, magical eye that provides mental visual information in all "
            + "directions. As an action, move the eye up to 30 feet in any direction including "
            + "passing through an opening as small as 1 inch in diameter.")),
    AURA_OF_LIFE(spell(MagicSchool.ABJURATION, 4)
        .castingTime("1 action").components(VERBAL)
        .range("Self").area("30-foot radius").duration("Concentration, up to 10 minutes")
        .effect("Allies resistant to necrotic damage. Allies with 0 HP at start of turn regain 1 HP.")),
    AURA_OF_PURITY(spell(MagicSchool.ABJURATION, 4)
        .castingTime("1 action").components(VERBAL)
        .range("Self").area("30-foot radius").duration("Concentration, up to 10 minutes")
        .effect("Allies cannot be diseased. Resistant to poison. Advantage on save for condition.")),
    BANISHMENT(spell(MagicSchool.ABJURATION, 4)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("60 feet").area("1 creature").duration("up to 1 minute")
        .effect("Banish a creature to another plane of existence. Chr. save. \n+1 creature per extra "
            + "spell level.")),
    BLACK_TENTACLES(spell(MagicSchool.CONJURATION, 4)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("90 feet").area("20-foot square").duration("Concentration, up to 1 minute")
        .effect("Dex. save DC [$spell_dc] or be restrained. 3d6 bludgeoning damage each turn. "
            + "Str. or Dex. check to escape.")),
    BLIGHT(spell(MagicSchool.NECROMANCY, 4)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("30 feet").area("1 creature").duration("Instantaneous")
        .effect("8d8 necrotic damage. Con. save for half. No effect on undead or constructs. Maximum "
            + "damage against plants. +1d8 damage per extra level.")),
    COMPULSION(spell(MagicSchool.ENCHANTMENT, 4)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("30 feet").area("Visible creatures in range").duration("up to 1 minute")
        .effect("Cause compulsion. Wis. save. As a bonus action, effected creatures must move in a "
            + "designated direction and then make another save.")),
    CONFUSION(spell(MagicSchool.ENCHANTMENT, 4)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("90 feet").area("10-foot-radius").duration("up to 1 minute")
        .effect("Causes confusion on all creatures in area. Wis. save. each turnAffected creatures "
            + "cannot take reactions. Each turn roll d10:<br>1: move in random direction.<br>2-6: no "
            + "move or action.</br>7-8: melee attack on random creature within reach.<br>9-10: move "
            + "and act normally.<br>+5-foot-radius area per extra spell level.")),
    CONJURE_MINOR_ELEMENTALS(spell(MagicSchool.CONJURATION, 4)
        .castingTime("1 minute").components(VERBAL, SOMATIC)
        .range("90 feet").area("Unoccupied spaces").duration("up to 1 hour")
        .effect("Summon elemental: 1 CR 2, 2 CR 1, 4 CR 1/2 or 8 CR 1/4.<br> x2 creatures at 6th level, "
            + "x3 creatures at 8th level.")),
    CONJURE_WOODLAND_BEINGS(spell(MagicSchool.CONJURATION, 4)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("60 feet").area("Unoccupied spaces").duration("up to 1 hour")
        .effect("Summon fey creatures: 1 CR 2, 2 CR 1, 4 CR 1/2 or 8 CR 1/4.<br>x2 creatures at 6th "
            + "level, x3 creatures at 8th level.")),
    CONTROL_WATER(spell(MagicSchool.TRANSMUTATION, 4)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("300 feet").area("cube of water up to 100-feet").duration("up to 10 minutes")
        .effect("As an action each turn, cause the water to:"
            + "<br>Rise 20 feet, causing a flood or wave."
            + "<br>Part the water to create a trench."
            + "<br>Cause the water to flow in a new direction, including over obstacles."
            + "<br>Create a whirlpool vortex 25 feet deep and 50 feet wide. 2d8 bludgeoning damage. "
            + "Str. save for half.")),
    DEATH_WARD(spell(MagicSchool.ABJURATION, 4)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Touch").area("1 ally").duration("8 hours")
        .effect("First time target would drop to 0 HP, drops to 1 HP instead.")
        .effect("Instantaneous kill effects negated.")),
    DIMENSION_DOOR(spell(MagicSchool.CONJURATION, 4)
        .castingTime("1 action").components(VERBAL)
        .range("500 feet").area("Self and 1 ally within 5 feet").duration("Instantaneous")
        .effect("Teleport to any spont within range.")
        .effect("If spot occupied, 4d6 force damage.")),
    DIVINATION(spell(MagicSchool.DIVINATION, 4).ritual()
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").duration("Instantaneous")
        .effect("Gain answer to single question concerncing goal, event or activity within 7 days.")
        .effect("Cumulative 25% chance of incorrect answer for each additional use before long rest.")),
    DOMINATE_BEAST(spell(MagicSchool.ENCHANTMENT, 4)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("60 feet").area("1 beast").duration("up to 1 minute")
        .effect("Target charmed. Wis. save.")
        .effect("On damage to target, new Wis. save to end spell.")
        .effect("Duration at 5th: 10 minutes; 6th: 1 hour; 7th+: 8 hours.")),
    FABRICATE(spell(MagicSchool.TRANSMUTATION, 4)
        .castingTime("10 minutes").components(VERBAL, SOMATIC)
        .range("120 feet").area("Raw materials").duration("Instantaneous")
        .effect("Convert materials into products of the same material.")
        .effect("10-foot cube or 8 connected 5-foot cubes for non-mineral material.")
        .effect("1 5-foot-cube for mineral material.")),
    FAITHFUL_HOUND(spell(MagicSchool.CONJURATION, 4)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("30 feet").area("Unoccupied space").duration("8 hours")
        .effect("Conjure a invisible watchdog that barks when enemy approaches within 30 feet.")
        .effect("Bite attack +[$spell_mod + $prof] 4d8 piercing damage.")),
    FIRE_SHIELD(spell(MagicSchool.EVOCATION, 4)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").duration("10 minutes")
        .effect("Choose resistance to cold or fire damage.")
        .effect("2d8 damage to creature withing 5 feet hitting.")
        .effect("Bright light in a 10-foot radius.")),
    FREEDOM_OF_MOVEMENT(spell(MagicSchool.ABJURATION, 4)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 ally").duration("1 hour")
        .effect("Target's movement unaffected by difficult terrain and spells.")
        .effect("Target can spend 5 feet of movement to escape from nonmagical restraints.")
        .effect("Target has no penalties to movement or attack underwater.")),
    GIANT_INSECT(spell(MagicSchool.TRANSMUTATION, 4)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("30 feet").area("Up to 10 centipedes, 3 spiders, 5 wasps, or 1 scorpion")
        .duration("up to 10 minutes")
        .effect("Transform targets into giant versions of their natural forms.")
        .effect("Targets obey verbal commands.")),
    GRASPING_VINE(spell(MagicSchool.CONJURATION, 4)
        .castingTime("1 bonus action").components(VERBAL, SOMATIC)
        .range("30 feet").area("unoccupied space").duration("Concentration, up to 1 minute")
        .effect("Dex. save or be pulled 20 feet towards vine.")),
    GREATER_INVISIBILITY(spell(MagicSchool.ILLUSION, 4)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Touch").area("1 creature").duration("up to 1 minute")
        .effect("Target becomes invisible for duration.")),
    GUARDIAN_OF_FAITH(spell(MagicSchool.CONJURATION, 4)
        .castingTime("1 action").components(VERBAL)
        .range("30 feet").area("Unoccupied space").duration("8 hours")
        .effect("Summon large spectral guardian.")
        .effect("Enemies within 10 feet take 20 radiant damage. Dex. save for half.")
        .effect("Guardian vanishes when it has dealt a total of 60 damage.")),
    HALLUCINATORY_TERRAIN(spell(MagicSchool.ILLUSION, 4)
        .castingTime("10 minutes").components(VERBAL, SOMATIC, MATERIAL)
        .range("300 feet").area("Terrain in 150-foot cube").duration("24 hours")
        .effect("Area looks, sounds, and smells like a chosen natural terrain.")
        .effect("Investigation check to disbelieve on examination.")),
    ICE_STORM(spell(MagicSchool.EVOCATION, 4)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("300 feet").area("20-foot-radius, 40-foot-high cylinder").duration("Instantaneous")
        .effect("Creatures in area take 2d8 bludgeoning and 4d6 cold damage. "
            + "Dex. save for half. Area become difficult terrain for 1 turn.")
        .effect("+1d8 bludgeoning damage / extra level.")),
    LOCATE_CREATURE(spell(MagicSchool.DIVINATION, 4)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").duration("up to 1 hour")
        .effect("Sense direction to a specific creature or type of creature within 1,000 feet.")
        .effect("Blocked by running water at least 10 feet wide.")),
    PHANTASMAL_KILLER(spell(MagicSchool.ILLUSION, 4)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("120 feet").area("1 creature").duration("up to 1 minute")
        .effect("Target becomes frightened. Wis. save. "
            + "Each turn saves to end spell or takes 4d10 psychic damage.")
        .effect("+1d10 damage / extra level.")),
    POLYMORPH(spell(MagicSchool.TRANSMUTATION, 4)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("60 feet").area("1 creature").duration("up to 1 hour")
        .effect("Transforms target into a new form with same or less CR. "
            + "Wis. save if target unwilling.")),
    PRIVATE_SANCTUM(spell(MagicSchool.ABJURATION, 4)
        .castingTime("10 minutes").components(VERBAL, SOMATIC, MATERIAL)
        .range("120 feet").area("Up to 100-foot-cube").duration("24 hours")
        .effect("Area becomes magically secure. Choose any number of following:"
            + "<br>Sound cannot pass through barrier"
            + "<br>Vision is prevented through barrier"
            + "<br>Divination spells cannot target creatures inside area"
            + "<br>Planar travel is prevented"
            + "<br>Teleportation through barrier is impossible")
        .effect("+100 feet area / extra level.")),
    RESILIENT_SPHERE(spell(MagicSchool.EVOCATION, 4)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("30 feet").area("1 creature or object").duration("Concentration, up to 1 minute")
        .effect("Sphere of shimmering force encloses target. "
            + "Dex. save for unwilling targets.")
        .effect("Nothing can pass through barrier. Can be move up to half speed by target.")),
    SECRET_CHEST(spell(MagicSchool.CONJURATION, 4)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 chest").duration("Instantaneous")
        .effect("Hide chest on ethereal plane.")),
    STAGGERING_SMITE(spell(MagicSchool.EVOCATION, 4)
        .castingTime("1 bonus action").components(VERBAL)
        .range("Self").duration("Concentration, up to 1 minute")
        .effect("Next melee hit +4d6 psychic damage. Wis. save or disadvantage on attack and abilities "
            + "and no reactions for 1 turn.")),
    STONESKIN(spell(MagicSchool.ABJURATION, 4)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 willing creature").duration("up to 1 hour")
        .effect("Makes flesh of target as hard as stone. "
            + "Target has resistance to nonmagical bludgeoning, piercing, and slashing damage.")),
    STONE_SHAPE(spell(MagicSchool.TRANSMUTATION, 4)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("Stone object or section of stone up to 5 feet in any dimension")
        .duration("Instantaneous")
        .effect("Form stone into any shape with up to two hinges and a latch.")),
    WALL_OF_FIRE(spell(MagicSchool.EVOCATION, 4)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("120 feet")
        .area("Up to 60 feet long or 20 feet in diameter, 20 feet high and 1 foot thick ")
        .duration("up to 1 minute")
        .effect("Each creature within areaor 10 feet from one side takes 5d8 fire damage. "
            + "Dex. save for half. ")
        .effect("+1d8 damage / extra slot level.")),
    ANIMATE_OBJECTS(spell(MagicSchool.TRANSMUTATION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("120 feet").area("Up to 10 nonmagical objects").duration("up to 1 minute")
        .effect("Objects come to life and can be commanded as a bonus action. "
            + "Medium or larger object count as a number of smaller object. "
            + table(
                header("Size", "Count", "HP", "AC", "Str", "Dex", "Attack", "Damage"),
                row("Tiny", "1", "20", "18", "4", "18", "+8", "1d4+4"),
                row("Small", "1", "25", "16", "6", "14", "+6", "1d8+2"),
                row("Medium", "2", "40", "13", "10", "12", "+5", "2d6+1"),
                row("Large", "4", "50", "10", "14", "10", "+6", "2d10+2"),
                row("Huge", "8", "80", "10", "18", "6", "+8", "2d12+4"))
            + "Con. 10, Int. 3, Wis. 3, Chr. 1, Speed 30. Blindsight to 30 feet. Bludgeoning damage. ")
        .effect("+2 objects / extra spell level.")),
    ANTILIFE_SHELL(spell(MagicSchool.ABJURATION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Self").area("10-foot radius").duration("up to 1 hour")
        .effect("Shimmering barrier forms around caster preventing creatures from "
            + "passing or reaching through. Spells and ranged attacks can pass through barrier.")),
    ARCANE_HAND(spell(MagicSchool.EVOCATION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("120 feet").duration("up to 1 minute")
        .effect("Create a hand of force. AC 20 HP [$hp] Str. 26 Dex. 10.")
        .effect("As bonus action move hand up to 60 feet and:"
            + "<br>attack using contested Str. Attack for 4d8 damage"
            + "<br>push target [5+5*$spell_mod] feet"
            + "<br>grapple target and then crush for 2d6+[$spell_mod]"
            + "<br>provide half cover and prevent movement towards caster. ")
        .effect("Attack +2d8 and crush +2d6 / extra level.")),
    AWAKEN(spell(MagicSchool.TRANSMUTATION, 5)
        .castingTime("8 hours").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 beast or plant with Int. of 3 or less").duration("Instantaneous")
        .effect("Target gains Int. 10 and ability to speak 1 language, move and sense. "
            + "Target is charmed for 30 days.")),
    BANISHING_SMITE(spell(MagicSchool.ABJURATION, 5)
        .castingTime("1 bonus action").components(VERBAL)
        .range("Self").duration("Concentration, up to 1 minute")
        .effect("Next hit with weapon: +5d10 force damage. If 50 HP or less, banish.")),
    CIRCLE_OF_POWER(spell(MagicSchool.ABJURATION, 5)
        .castingTime("1 action").components(VERBAL)
        .range("Self").area("30-foot radius").duration("Concentration, up to 10 minutes")
        .effect("Allies in area have advantage on Save vs. spells. Half damage becomes no damage.")),
    CLOUDKILL(spell(MagicSchool.CONJURATION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("120 feet").area("20-foot-radius sphere").duration("up to 10 minutes")
        .effect("Area is heavily obscured. Creatures in area take 5d8 poison damage. "
            + "Con. save for half damage. Fog moves 10 feet away each turn.")
        .effect("+1d8 damage / extra level.")),
    COMMUNE(spell(MagicSchool.DIVINATION, 5).ritual()
        .castingTime("1 minute").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").duration("1 minute")
        .effect("Up to three questions answered with yes or no.")
        .effect("Cumulative 25% of no answer for each extra casting before long rest.")),
    COMMUNE_WITH_NATURE(spell(MagicSchool.DIVINATION, 5).ritual()
        .castingTime("1 minute").components(VERBAL, SOMATIC)
        .range("Self").area("3 miles outdoors, 300 feet underground").duration("Instantaneous")
        .effect("Gain knowledge of three facts about the surrounding territory: terrain, bodies of "
            + "water, prevalent plants, minerals, animals, people, powerful celestials, fey, fiends, "
            + "elementals or undead. ")),
    CONE_OF_COLD(spell(MagicSchool.EVOCATION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").area("60-foot cone").duration("Instantaneous")
        .effect("8d8 cold damage. Con. save for half.")),
    CONJURE_ELEMENTAL(spell(MagicSchool.CONJURATION, 5)
        .castingTime("1 minute").components(VERBAL, SOMATIC, MATERIAL)
        .range("90 feet").area("10-foot cube of air, earth, fire or water").duration("up to 1 hour")
        .effect("Matching friendly elemental of CR5 or lower appears within 10 feet of area.")
        .effect("+1 CR / extra spell slot level")),
    CONJURE_VOLLEY(spell(MagicSchool.CONJURATION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("150 feet").area("40-foot radius, 20-foot high cylinder").duration("Instantaneous")
        .effect("8d8 damage. Dex. save for half.")),
    CONTACT_OTHER_PLANE(spell(MagicSchool.DIVINATION, 5).ritual()
        .castingTime("1 minute").components(VERBAL)
        .range("Self").area("Demigod or spirit").duration("1 minute")
        .effect("Caster makes Int. save DC15 or takes 6d6 psychic damage and insane until long rest. "
            + "On successful save, ask up to five questions answered with "
            + "'yes,' 'no,' 'maybe,' 'never,' 'irrelevant,' or 'unclear'.")),
    CONTAGION(spell(MagicSchool.NECROMANCY, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Touch").area("1 creature").duration("7 days")
        .effect("Melee spell attack. Inflict disease. Con. save each turn. After 3 fails, disease "
            + "takes effect. After 3 successes, disease is cured. ")
        .effect(table(
            header("Disease", "Effect"),
            row("Blinding Sickness", "Blinded. Disadvantage on Wis. saves."),
            row("Filth Fever", "Disadvantage on Str. saves and attacks."),
            row("Flesh Rot", "Disadvantage on Chr. checks and vulnerability to all damage."),
            row("Mindfire", "Disadvantage on Int. checks and saves and behaves as <em>Confusion</em>."),
            row("Seizure", "Disadvantage on Dex. checks and saves and Dex. attacks."),
            row("Slimy Doom", "Disadvantage on Con. checks and saves and stunned on taking damage.")))),
    CREATION(spell(MagicSchool.ILLUSION, 5)
        .castingTime("1 minute").components(VERBAL, SOMATIC, MATERIAL)
        .range("30 feet").duration("Special")
        .effect("You pull wisps of shadow material from the Shadowfell to create a nonliving object of "
            + "vegetable matter within range: soft goods, rope, wood, or something similar. You can "
            + "also use this spell to create mineral objects such as stone, crystal, or metal. The "
            + "object created must be no larger than a 5-foot cube, and the object must be of a form "
            + "and material that you have seen before. The duration depends on the object's material. "
            + "If the object is composed of multiple materials, use the shortest duration. Material "
            + "Duration Vegetable matter 1 day Stone or crystal 12 hours Precious metals 1 hour Gems "
            + "10 minutes Adamantine or mithral 1 minute Using any material created by this spell as "
            + "another spell's material component causes that spell to fail. At Higher Levels. When "
            + "you cast this spell using a spell slot of 6th level or higher, the cube increases by 5 "
            + "feet for each slot level above 5th.")),
    DESTRUCTIVE_WAVE(spell(MagicSchool.EVOCATION, 5)
        .castingTime("1 action").components(VERBAL)
        .range("Self").area("30-foot radius").duration("Instantaneous")
        .effect("5d6 thunder damage +5d6 radiant or necrotic damage and knocked prone. "
            + "Con. save for half.")),
    DISPEL_EVIL_AND_GOOD(spell(MagicSchool.ABJURATION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").duration("up to 1 minute")
        .effect("Shimmering energy surrounds and protects you from fey, undead, and creatures "
            + "originating from beyond the Material Plane. For the duration, celestials, elementals, "
            + "fey, fiends, and undead have disadvantage on attack rolls against you. You can end the "
            + "spell early by using either of the following special functions. Break Enchantment. As "
            + "your action, you touch a creature you can reach that is charmed, frightened, or "
            + "possessed by a celestial, an elemental, a fey, a fiend, or an undead. The creature you "
            + "touch is no longer charmed, frightened, or possessed by such creatures. Dismissal. As "
            + "your action, make a melee spell attack against a celestial, an elemental, a fey, a "
            + "fiend, or an undead you can reach. On a hit, you attempt to drive the creature back to "
            + "its home plane. The creature must succeed on a Charisma saving throw or be sent back "
            + "to its home plane (if it isn't there already). If they aren't on their home plane, "
            + "undead are sent to the Shadowfell, and fey are sent to the Feywild.")),
    DOMINATE_PERSON(spell(MagicSchool.ENCHANTMENT, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("60 feet").duration("up to 1 minute")
        .effect("You attempt to beguile a humanoid that you can see within range. It must succeed on a "
            + "Wisdom saving throw or be charmed by you for the duration. If you or creatures that "
            + "are friendly to you are fighting it, it has advantage on the saving throw. While the "
            + "target is charmed, you have a telepathic link with it as long as the two of you are on "
            + "the same plane of existence. You can use this telepathic link to issue commands to the "
            + "creature while you are conscious (no action required), which it does its best to obey. "
            + "You can specify a simple and general course of action, such as 'Attack that creature,' "
            + "'Run over there,' or 'Fetch that object.' If the creature completes the order and "
            + "doesn't receive further direction from you, it defends and preserves itself to the "
            + "best of its ability. You can use your action to take total and precise control of the "
            + "target. Until the end of your next turn, the creature takes only the actions you "
            + "choose, and doesn't do anything that you don't allow it to do. During this time you "
            + "can also cause the creature to use a reaction, but this requires you to use your own "
            + "reaction as well. Each time the target takes damage, it makes a new Wisdom saving "
            + "throw against the spell. If the saving throw succeeds, the spell ends. At Higher "
            + "Levels. When you cast this spell using a 6th-level spell slot, the duration is "
            + "concentration, up to 10 minutes. When you use a 7th-level spell slot, the duration is "
            + "concentration, up to 1 hour. When you use a spell slot of 8th level or higher, the "
            + "duration is concentration, up to 8 hours.")),
    DREAM(spell(MagicSchool.ILLUSION, 5)
        .castingTime("1 minute").components(VERBAL, SOMATIC, MATERIAL)
        .range("Special").duration("8 hours")
        .effect("This spell shapes a creature's dreams. Choose a creature known to you as the target of "
            + "this spell. The target must be on the same plane of existence as you. Creatures that "
            + "don't sleep, such as elves, can't be contacted by this spell. You, or a willing "
            + "creature you touch, enters a trance state, acting as a messenger. While in the trance, "
            + "the messenger is aware of his or her surroundings, but can't take actions or move. If "
            + "the target is asleep, the messenger appears in the target's dreams and can converse "
            + "with the target as long as it remains asleep, through the duration of the spell. The "
            + "messenger can also shape the environment of the dream, creating landscapes, objects, "
            + "and other images. The messenger can emerge from the trance at any time, ending the "
            + "effect of the spell early. The target recalls the dream perfectly upon waking. If the "
            + "target is awake when you cast the spell, the messenger knows it, and can either end "
            + "the trance (and the spell) or wait for the target to fall asleep, at which point the "
            + "messenger appears in the target's dreams. You can make the messenger appear monstrous "
            + "and terrifying to the target. If you do, the messenger can deliver a message of no "
            + "more than ten words and then the target must make a Wisdom saving throw. On a failed "
            + "save, echoes of the phantasmal monstrosity spawn a nightmare that lasts the duration "
            + "of the target's sleep and prevents the target from gaining any benefit from that rest. "
            + "In addition, when the target wakes up, it takes 3d6 psychic damage. If you have a body "
            + "part, lock of hair, clipping from a nail, or similar portion of the target's body, the "
            + "target makes its saving throw with disadvantage.")),
    FLAME_STRIKE(spell(MagicSchool.EVOCATION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("60 feet").duration("Instantaneous")
        .effect("A vertical column of divine fire roars down from the heavens in a location you "
            + "specify. Each creature in a 10-foot-radius, 40-foot-high cylinder centered on a point "
            + "within range must make a Dexterity saving throw. A creature takes 4d6 fire damage and "
            + "4d6 radiant damage on a failed save, or half as much damage on a successful one. At "
            + "Higher Levels. When you cast this spell using a spell slot of 6th level or higher, the "
            + "fire damage or the radiant damage (your choice) increases by 1d6 for each slot level "
            + "above 5th.")),
    GEAS(spell(MagicSchool.ENCHANTMENT, 5)
        .castingTime("1 minute").components(VERBAL)
        .range("60 feet").duration("30 days")
        .effect("You place a magical command on a creature that you can see within range, forcing it to "
            + "carry out some service or refrain from some action or course of activity as you "
            + "decide. If the creature can understand you, it must succeed on a Wisdom saving throw "
            + "or become charmed by you for the duration. While the creature is charmed by you, it "
            + "takes 5d10 psychic damage each time it acts in a manner directly counter to your "
            + "instructions, but no more than once each day. A creature that can't understand you is "
            + "unaffected by the spell. You can issue any command you choose, short of an activity "
            + "that would result in certain death. Should you issue a suicidal command, the spell "
            + "ends. You can end the spell early by using an action to dismiss it. A remove curse, "
            + "greater restoration, or wish spell also ends it. At Higher Levels. When you cast this "
            + "spell using a spell slot of 7th or 8th level, the duration is 1 year. When you cast "
            + "this spell using a spell slot of 9th level, the spell lasts until it is ended by one "
            + "of the spells mentioned above.")),
    GREATER_RESTORATION(spell(MagicSchool.ABJURATION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").duration("Instantaneous")
        .effect("You imbue a creature you touch with positive energy to undo a debilitating effect. You "
            + "can reduce the target's exhaustion level by one, or end one of the following effects "
            + "on the target: One effect that charmed or petrified the target One curse, including "
            + "the target's attunement to a cursed magic item Any reduction to one of the target's "
            + "ability scores One effect reducing the target's hit point maximum")),
    HALLOW(spell(MagicSchool.EVOCATION, 5)
        .castingTime("24 hours").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").duration("Until dispelled")
        .effect("You touch a point and infuse an area around it with holy (or unholy) power. The area "
            + "can have a radius up to 60 feet, and the spell fails if the radius includes an area "
            + "already under the effect a hallow spell. The affected area is subject to the following "
            + "effects. First, celestials, elementals, fey, fiends, and undead can't enter the area, "
            + "nor can such creatures charm, frighten, or possess creatures within it. Any creature "
            + "charmed, frightened, or possessed by such a creature is no longer charmed, frightened, "
            + "or possessed upon entering the area. You can exclude one or more of those types of "
            + "creatures from this effect. Second, you can bind an extra effect to the area. Choose "
            + "the effect from the following list, or choose an effect offered by the GM. Some of "
            + "these effects apply to creatures in the area; you can designate whether the effect "
            + "applies to all creatures, creatures that follow a specific deity or leader, or "
            + "creatures of a specific sort, such as orcs or trolls. When a creature that would be "
            + "affected enters the spell's area for the first time on a turn or starts its turn "
            + "there, it can make a Charisma saving throw. On a success, the creature ignores the "
            + "extra effect until it leaves the area. Courage. Affected creatures can't be frightened "
            + "while in the area. Darkness. Darkness fills the area. Normal light, as well as magical "
            + "light created by spells of a lower level than the slot you used to cast this spell, "
            + "can't illuminate the area. Daylight. Bright light fills the area. Magical darkness "
            + "created by spells of a lower level than the slot you used to cast this spell can't "
            + "extinguish the light. Energy Protection. Affected creatures in the area have "
            + "resistance to one damage type of your choice, except for bludgeoning, piercing, or "
            + "slashing. Energy Vulnerability. Affected creatures in the area have vulnerability to "
            + "one damage type of your choice, except for bludgeoning, piercing, or slashing. "
            + "Everlasting Rest. Dead bodies interred in the area can't be turned into undead. "
            + "Extradimensional Interference. Affected creatures can't move or travel using "
            + "teleportation or by extradimensional or interplanar means. Fear. Affected creatures "
            + "are frightened while in the area. Silence. No sound can emanate from within the area, "
            + "and no sound can reach into it. Tongues. Affected creatures can communicate with any "
            + "other creature in the area, even if they don't share a common language.")),
    HOLD_MONSTER(spell(MagicSchool.ENCHANTMENT, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("90 feet").duration("up to 1 minute")
        .effect("Choose a creature that you can see within range. The target must succeed on a Wisdom "
            + "saving throw or be paralyzed for the duration. This spell has no effect on undead. At "
            + "the end of each of its turns, the target can make another Wisdom saving throw. On a "
            + "success, the spell ends on the target. At Higher Levels. When you cast this spell "
            + "using a spell slot of 6th level or higher, you can target one additional creature for "
            + "each slot level above 5th. The creatures must be within 30 feet of each other when you "
            + "target them.")),
    INSECT_PLAGUE(spell(MagicSchool.CONJURATION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("300 feet").duration("up to 10 minutes")
        .effect("Swarming, biting locusts fill a 20-foot-radius sphere centered on a point you choose "
            + "within range. The sphere spreads around corners. The sphere remains for the duration, "
            + "and its area is lightly obscured. The sphere's area is difficult terrain. When the "
            + "area appears, each creature in it must make a Constitution saving throw. A creature "
            + "takes 4d10 piercing damage on a failed save, or half as much damage on a successful "
            + "one. A creature must also make this saving throw when it enters the spell's area for "
            + "the first time on a turn or ends its turn there. At Higher Levels. When you cast this "
            + "spell using a spell slot of 6th level or higher, the damage increases by 1d10 for each "
            + "slot level above 5th.")),
    LEGEND_LORE(spell(MagicSchool.DIVINATION, 5)
        .castingTime("10 minutes").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").duration("Instantaneous")
        .effect("Name or describe a person, place, or object. The spell brings to your mind a brief "
            + "summary of the significant lore about the thing you named. The lore might consist of "
            + "current tales, forgotten stories, or even secret lore that has never been widely "
            + "known. If the thing you named isn't of legendary importance, you gain no information. "
            + "The more information you already have about the thing, the more precise and detailed "
            + "the information you receive is. The information you learn is accurate but might be "
            + "couched in figurative language. For example, if you have a mysterious magic axe on "
            + "hand, the spell might yield this information: “Woe to the evildoer whose hand touches "
            + "the axe, for even the haft slices the hand of the evil ones. Only a true Child of "
            + "Stone, lover and beloved of Moradin, may awaken the true powers of the axe, and only "
            + "with the sacred word Rudnogg on the lips.'")),
    MASS_CURE_WOUNDS(spell(MagicSchool.EVOCATION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("60 feet").duration("Instantaneous")
        .effect("A wave of healing energy washes out from a point of your choice within range. Choose "
            + "up to six creatures in a 30-foot-radius sphere centered on that point. Each target "
            + "regains hit points equal to 3d8 + your spellcasting ability modifier. This spell has "
            + "no effect on undead or constructs. At Higher Levels. When you cast this spell using a "
            + "spell slot of 6th level or higher, the healing increases by 1d8 for each slot level "
            + "above 5th.")),
    MISLEAD(spell(MagicSchool.ILLUSION, 5)
        .castingTime("1 action").components(SOMATIC)
        .range("Self").duration("up to 1 hour")
        .effect("You become invisible at the same time that an illusory double of you appears where you "
            + "are standing. The double lasts for the duration, but the invisibility ends if you "
            + "attack or cast a spell. You can use your action to move your illusory double up to "
            + "twice your speed and make it gesture, speak, and behave in whatever way you choose. "
            + "You can see through its eyes and hear through its ears as if you were located where it "
            + "is. On each of your turns as a bonus action, you can switch from using its senses to "
            + "using your own, or back again. While you are using its senses, you are blinded and "
            + "deafened in regard to your own surroundings.")),
    MODIFY_MEMORY(spell(MagicSchool.ENCHANTMENT, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("30 feet").duration("up to 1 minute")
        .effect("You attempt to reshape another creature's memories. One creature that you can see must "
            + "make a Wisdom saving throw. If you are fighting the creature, it has advantage on the "
            + "saving throw. On a failed save, the target becomes charmed by you for the duration. "
            + "The charmed target is incapacitated and unaware of its surroundings, though it can "
            + "still hear you. If it takes any damage or is targeted by another spell, this spell "
            + "ends, and none of the target's memories are modified. While this charm lasts, you can "
            + "affect the target's memory of an event that it experienced within the last 24 hours "
            + "and that lasted no more than 10 minutes. You can permanently eliminate all memory of "
            + "the event, allow the target to recall the event with perfect clarity and exacting "
            + "detail, change its memory of the details of the event, or create a memory of some "
            + "other event. You must speak to the target to describe how its memories are affected, "
            + "and it must be able to understand your language for the modified memories to take "
            + "root. Its mind fills in any gaps in the details of your description. If the spell ends "
            + "before you have finished describing the modified memories, the creature's memory isn't "
            + "altered. Otherwise, the modified memories take hold when the spell ends. A modified "
            + "memory doesn't necessarily affect how a creature behaves, particularly if the memory "
            + "contradicts the creature's natural inclinations, alignment, or beliefs. An illogical "
            + "modified memory, such as implanting a memory of how much the creature enjoyed dousing "
            + "itself in acid, is dismissed, perhaps as a bad dream. The GM might deem a modified "
            + "memory too nonsensical to affect a creature in a significant manner. A remove curse or "
            + "greater restoration spell cast on the target restores the creature's true memory. At "
            + "Higher Levels. If you cast this spell using a spell slot of 6th level or higher, you "
            + "can alter the target's memories of an event that took place up to 7 days ago (6th "
            + "level), 30 days ago (7th level), 1 year ago (8th level), or any time in the creature's "
            + "past (9th level).")),
    PASSWALL(spell(MagicSchool.TRANSMUTATION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("30 feet").duration("1 hour")
        .effect("A passage appears at a point of your choice that you can see on a wooden, plaster, or "
            + "stone surface (such as a wall, a ceiling, or a floor) within range, and lasts for the "
            + "duration. You choose the opening's dimensions: up to 5 feet wide, 8 feet tall, and 20 "
            + "feet deep. The passage creates no instability in a structure surrounding it. When the "
            + "opening disappears, any creatures or objects still in the passage created by the spell "
            + "are safely ejected to an unoccupied space nearest to the surface on which you cast the "
            + "spell.")),
    PLANAR_BINDING(spell(MagicSchool.ABJURATION, 5)
        .castingTime("1 hour").components(VERBAL, SOMATIC, MATERIAL)
        .range("60 feet").duration("24 hours")
        .effect("With this spell, you attempt to bind a celestial, an elemental, a fey, or a fiend to "
            + "your service. The creature must be within range for the entire casting of the spell. "
            + "(Typically, the creature is first summoned into the center of an inverted magic circle "
            + "in order to keep it trapped while this spell is cast.) At the completion of the "
            + "casting, the target must make a Charisma saving throw. On a failed save, it is bound "
            + "to serve you for the duration. If the creature was summoned or created by another "
            + "spell, that spell's duration is extended to match the duration of this spell. A bound "
            + "creature must follow your instructions to the best of its ability. You might command "
            + "the creature to accompany you on an adventure, to guard a location, or to deliver a "
            + "message. The creature obeys the letter of your instructions, but if the creature is "
            + "hostile to you, it strives to twist your words to achieve its own objectives. If the "
            + "creature carries out your instructions completely before the spell ends, it travels to "
            + "you to report this fact if you are on the same plane of existence. If you are on a "
            + "different plane of existence, it returns to the place where you bound it and remains "
            + "there until the spell ends. At Higher Levels. When you cast this spell using a spell "
            + "slot of a higher level, the duration increases to 10 days with a 6th-level slot, to 30 "
            + "days with a 7th- level slot, to 180 days with an 8th-level slot, and to a year and a "
            + "day with a 9th-level spell slot.")),
    RAISE_DEAD(spell(MagicSchool.NECROMANCY, 5)
        .castingTime("1 hour").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").duration("Instantaneous")
        .effect("You return a dead creature you touch to life, provided that it has been dead no longer "
            + "than 10 days. If the creature's soul is both willing and at liberty to rejoin the "
            + "body, the creature returns to life with 1 hit point. This spell also neutralizes any "
            + "poisons and cures nonmagical diseases that affected the creature at the time it died. "
            + "This spell doesn't, however, remove magical diseases, curses, or similar effects; if "
            + "these aren't first removed prior to casting the spell, they take effect when the "
            + "creature returns to life. The spell can't return an undead creature to life. This "
            + "spell closes all mortal wounds, but it doesn't restore missing body parts. If the "
            + "creature is lacking body parts or organs integral for its survival--its head, for "
            + "instance--the spell automatically fails. Coming back from the dead is an ordeal. The "
            + "target takes a −4 penalty to all attack rolls, saving throws, and ability checks. "
            + "Every time the target finishes a long rest, the penalty is reduced by 1 until it "
            + "disappears.")),
    REINCARNATE(spell(MagicSchool.TRANSMUTATION, 5)
        .castingTime("1 hour").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").duration("Instantaneous")
        .effect("You touch a dead humanoid or a piece of a dead humanoid. Provided that the creature "
            + "has been dead no longer than 10 days, the spell forms a new adult body for it and then "
            + "calls the soul to enter that body. If the target's soul isn't free or willing to do "
            + "so, the spell fails. The magic fashions a new body for the creature to inhabit, which "
            + "likely causes the creature's race to change. The GM rolls a d100 and consults the "
            + "following table to determine what form the creature takes when restored to life, or "
            + "the GM chooses a form. d100 Race 01-04 Dragonborn 05-13 Dwarf, hill 14-21 Dwarf, "
            + "mountain 22-25 Elf, dark 26-34 Elf, high 35-42 Elf, wood 43-46 Gnome, forest 47-52 "
            + "Gnome, rock 53-56 Half-elf 57-60 Half-orc 61-68 Halfling, lightfoot 69-76 Halfling, "
            + "stout 77-96 Human 97-100 Tiefling The reincarnated creature recalls its former life "
            + "and experiences. It retains the capabilities it had in its original form, except it "
            + "exchanges its original race for the new one and changes its racial traits accordingly.")),
    SCRYING(spell(MagicSchool.DIVINATION, 5)
        .castingTime("10 minutes").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").duration("up to 10 minutes")
        .effect("You can see and hear a particular creature you choose that is on the same plane of "
            + "existence as you. The target must make a Wisdom saving throw, which is modified by how "
            + "well you know the target and the sort of physical connection you have to it. If a "
            + "target knows you're casting this spell, it can fail the saving throw voluntarily if it "
            + "wants to be observed. Knowledge Save Modifier Secondhand (you have heard of the "
            + "target) +5 Firsthand (you have met the target) 0 Familiar (you know the target well) "
            + "-5 Connection Save Modifier Likeness or picture -2 Possession or garment -4 Body part, "
            + "lock of hair, bit of nail, or the like -10 On a successful save, the target isn't "
            + "affected, and you can't use this spell against it again for 24 hours. On a failed "
            + "save, the spell creates an invisible sensor within 10 feet of the target. You can see "
            + "and hear through the sensor as if you were there. The sensor moves with the target, "
            + "remaining within 10 feet of it for the duration. A creature that can see invisible "
            + "objects sees the sensor as a luminous orb about the size of your fist. Instead of "
            + "targeting a creature, you can choose a location you have seen before as the target of "
            + "this spell. When you do, the sensor appears at that location and doesn't move.")),
    SEEMING(spell(MagicSchool.ILLUSION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("30 feet").duration("8 hours")
        .effect("This spell allows you to change the appearance of any number of creatures that you can "
            + "see within range. You give each target you choose a new, illusory appearance. An "
            + "unwilling target can make a Charisma saving throw, and if it succeeds, it is "
            + "unaffected by this spell. The spell disguises physical appearance as well as clothing, "
            + "armor, weapons, and equipment. You can make each creature seem 1 foot shorter or "
            + "taller and appear thin, fat, or in between. You can't change a target's body type, so "
            + "you must choose a form that has the same basic arrangement of limbs. Otherwise, the "
            + "extent of the illusion is up to you. The spell lasts for the duration, unless you use "
            + "your action to dismiss it sooner. The changes wrought by this spell fail to hold up to "
            + "physical inspection. For example, if you use this spell to add a hat to a creature's "
            + "outfit, objects pass through the hat, and anyone who touches it would feel nothing or "
            + "would feel the creature's head and hair. If you use this spell to appear thinner than "
            + "you are, the hand of someone who reaches out to touch you would bump into you while it "
            + "was seemingly still in midair. A creature can use its action to inspect a target and "
            + "make an Intelligence (Investigation) check against your spell save DC. If it succeeds, "
            + "it becomes aware that the target is disguised.")),
    SWIFT_QUIVER(spell(MagicSchool.TRANSMUTATION, 5)
        .castingTime("1 bonus action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("quiver").duration("Concentration, up to 1 minute")
        .effect("As bonus action make two attacks with weapon using ammunition from quiver.")),
    TELEKINESIS(spell(MagicSchool.TRANSMUTATION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("60 feet").duration("up to 10 minutes")
        .effect("You gain the ability to move or manipulate creatures or objects by thought. When you "
            + "cast the spell, and as your action each round for the duration, you can exert your "
            + "will on one creature or object that you can see within range, causing the appropriate "
            + "effect below. You can affect the same target round after round, or choose a new one at "
            + "any time. If you switch targets, the prior target is no longer affected by the spell. "
            + "Creature. You can try to move a Huge or smaller creature. Make an ability check with "
            + "your spellcasting ability contested by the creature's Strength check. If you win the "
            + "contest, you move the creature up to 30 feet in any direction, including upward but "
            + "not beyond the range of this spell. Until the end of your next turn, the creature is "
            + "restrained in your telekinetic grip. A creature lifted upward is suspended in mid-air. "
            + "On subsequent rounds, you can use your action to attempt to maintain your telekinetic "
            + "grip on the creature by repeating the contest. Object. You can try to move an object "
            + "that weighs up to 1,000 pounds. If the object isn't being worn or carried, you "
            + "automatically move it up to 30 feet in any direction, but not beyond the range of this "
            + "spell. If the object is worn or carried by a creature, you must make an ability check "
            + "with your spellcasting ability contested by that creature's Strength check. If you "
            + "succeed, you pull the object away from that creature and can move it up to 30 feet in "
            + "any direction but not beyond the range of this spell. You can exert fine control on "
            + "objects with your telekinetic grip, such as manipulating a simple tool, opening a door "
            + "or a container, stowing or retrieving an item from an open container, or pouring the "
            + "contents from a vial.")),
    TELEPATHIC_BOND(spell(MagicSchool.DIVINATION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("30 feet").area("8 willing creatures").duration("1 hour")
        .effect("Communicate telepathically between targets.")),
    TELEPORTATION_CIRCLE(spell(MagicSchool.CONJURATION, 5)
        .castingTime("1 minute").components(VERBAL, MATERIAL)
        .range("10 feet").duration("1 round")
        .effect("As you cast the spell, you draw a 10-foot-diameter circle on the ground inscribed with "
            + "sigils that link your location to a permanent teleportation circle of your choice "
            + "whose sigil sequence you know and that is on the same plane of existence as you. A "
            + "shimmering portal opens within the circle you drew and remains open until the end of "
            + "your next turn. Any creature that enters the portal instantly appears within 5 feet of "
            + "the destination circle or in the nearest unoccupied space if that space is occupied. "
            + "Many major temples, guilds, and other important places have permanent teleportation "
            + "circles inscribed somewhere within their confines. Each such circle includes a unique "
            + "sigil sequence--a string of magical runes arranged in a particular pattern. When you "
            + "first gain the ability to cast this spell, you learn the sigil sequences for two "
            + "destinations on the Material Plane, determined by the GM. You can learn additional "
            + "sigil sequences during your adventures. You can commit a new sigil sequence to memory "
            + "after studying it for 1 minute. You can create a permanent teleportation circle by "
            + "casting this spell in the same location every day for one year. You need not use the "
            + "circle to teleport when you cast the spell in this way.")),
    TREE_STRIDE(spell(MagicSchool.CONJURATION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Self").duration("up to 1 minute")
        .effect("You gain the ability to enter a tree and move from inside it to inside another tree of "
            + "the same kind within 500 feet. Both trees must be living and at least the same size as "
            + "you. You must use 5 feet of movement to enter a tree. You instantly know the location "
            + "of all other trees of the same kind within 500 feet and, as part of the move used to "
            + "enter the tree, can either pass into one of those trees or step out of the tree you're "
            + "in. You appear in a spot of your choice within 5 feet of the destination tree, using "
            + "another 5 feet of movement. If you have no movement left, you appear within 5 feet of "
            + "the tree you entered. You can use this transportation ability once per round for the "
            + "duration. You must end each turn outside a tree.")),
    WALL_OF_FORCE(spell(MagicSchool.EVOCATION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("120 feet").duration("up to 10 minutes")
        .effect("An invisible wall of force springs into existence at a point you choose within range. "
            + "The wall appears in any orientation you choose, as a horizontal or vertical barrier or "
            + "at an angle. It can be free floating or resting on a solid surface. You can form it "
            + "into a hemispherical dome or a sphere with a radius of up to 10 feet, or you can shape "
            + "a flat surface made up of ten 10-foot-by-10-foot panels. Each panel must be contiguous "
            + "with another panel. In any form, the wall is 1/4 inch thick. It lasts for the "
            + "duration. If the wall cuts through a creature's space when it appears, the creature is "
            + "pushed to one side of the wall (your choice which side). Nothing can physically pass "
            + "through the wall. It is immune to all damage and can't be dispelled by dispel magic. A "
            + "disintegrate spell destroys the wall instantly, however. The wall also extends into "
            + "the Ethereal Plane, blocking ethereal travel through the wall.")),
    WALL_OF_STONE(spell(MagicSchool.EVOCATION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("120 feet").duration("up to 10 minutes")
        .effect("A nonmagical wall of solid stone springs into existence at a point you choose within "
            + "range. The wall is 6 inches thick and is composed of ten 10-foot- by-10-foot panels. "
            + "Each panel must be contiguous with at least one other panel. Alternatively, you can "
            + "create 10-foot-by-20-foot panels that are only 3 inches thick. If the wall cuts "
            + "through a creature's space when it appears, the creature is pushed to one side of the "
            + "wall (your choice). If a creature would be surrounded on all sides by the wall (or the "
            + "wall and another solid surface), that creature can make a Dexterity saving throw. On a "
            + "success, it can use its reaction to move up to its speed so that it is no longer "
            + "enclosed by the wall. The wall can have any shape you desire, though it can't occupy "
            + "the same space as a creature or object. The wall doesn't need to be vertical or rest "
            + "on any firm foundation. It must, however, merge with and be solidly supported by "
            + "existing stone. Thus, you can use this spell to bridge a chasm or create a ramp. If "
            + "you create a span greater than 20 feet in length, you must halve the size of each "
            + "panel to create supports. You can crudely shape the wall to create crenellations, "
            + "battlements, and so on. The wall is an object made of stone that can be damaged and "
            + "thus breached. Each panel has AC 15 and 30 hit points per inch of thickness. Reducing "
            + "a panel to 0 hit points destroys it and might cause connected panels to collapse at "
            + "the GM's discretion. If you maintain your concentration on this spell for its whole "
            + "duration, the wall becomes permanent and can't be dispelled. Otherwise, the wall "
            + "disappears when the spell ends.")),
    ARCANE_GATE(spell(MagicSchool.CONJURATION, 6)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("500 feet").area("2 points").duration("Concentration, up to 10 minutes")
        .effect("Create teleportation portals. As bonus action rotate portals.")),
    BLADE_BARRIER(spell(MagicSchool.EVOCATION, 6)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("90 feet").area("20' high x5' thick wall\n100' straight or 60' diameter ringed")
        .duration("up to 10 minutes")
        .effect("Three-quarters cover. Difficult terrain. 6d10 slashing damage on entering. Dex. save "
            + "for half")),
    CHAIN_LIGHTNING(spell(MagicSchool.EVOCATION, 6)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("150 feet").duration("Instantaneous")
        .effect("You create a bolt of lightning that arcs toward a target of your choice that you can "
            + "see within range. Three bolts then leap from that target to as many as three other "
            + "targets, each of which must be within 30 feet of the first target. A target can be a "
            + "creature or an object and can be targeted by only one of the bolts. A target must make "
            + "a Dexterity saving throw. The target takes 10d8 lightning damage on a failed save, or "
            + "half as much damage on a successful one. At Higher Levels. When you cast this spell "
            + "using a spell slot of 7th level or higher, one additional bolt leaps from the first "
            + "target to another target for each slot level above 6th.")),
    CIRCLE_OF_DEATH(spell(MagicSchool.NECROMANCY, 6)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("150 feet").duration("Instantaneous")
        .effect("A sphere of negative energy ripples out in a 60-foot- radius sphere from a point "
            + "within range. Each creature in that area must make a Constitution saving throw. A "
            + "target takes 8d6 necrotic damage on a failed save, or half as much damage on a "
            + "successful one. At Higher Levels. When you cast this spell using a spell slot of 7th "
            + "level or higher, the damage increases by 2d6 for each slot level above 6th.")),
    CONJURE_FEY(spell(MagicSchool.CONJURATION, 6)
        .castingTime("1 minute").components(VERBAL, SOMATIC)
        .range("90 feet").duration("up to 1 hour")
        .effect("You summon a fey creature of challenge rating 6 or lower, or a fey spirit that takes "
            + "the form of a beast of challenge rating 6 or lower. It appears in an unoccupied space "
            + "that you can see within range. The fey creature disappears when it drops to 0 hit "
            + "points or when the spell ends. The fey creature is friendly to you and your companions "
            + "for the duration. Roll initiative for the creature, which has its own turns. It obeys "
            + "any verbal commands that you issue to it (no action required by you), as long as they "
            + "don't violate its alignment. If you don't issue any commands to the fey creature, it "
            + "defends itself from hostile creatures but otherwise takes no actions. If your "
            + "concentration is broken, the fey creature doesn't disappear. Instead, you lose control "
            + "of the fey creature, it becomes hostile toward you and your companions, and it might "
            + "attack. An uncontrolled fey creature can't be dismissed by you, and it disappears 1 "
            + "hour after you summoned it. The GM has the fey creature's statistics. At Higher "
            + "Levels. When you cast this spell using a spell slot of 7th level or higher, the "
            + "challenge rating increases by 1 for each slot level above 6th.")),
    CONTINGENCY(spell(MagicSchool.EVOCATION, 6)
        .castingTime("10 minutes").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").duration("10 days")
        .effect("Choose a spell of 5th level or lower that you can cast, that has a casting time of 1 "
            + "action, and that can target you. You cast that spell--called the contingent spell--as "
            + "part of casting contingency, expending spell slots for both, but the contingent spell "
            + "doesn't come into effect. Instead, it takes effect when a certain circumstance occurs. "
            + "You describe that circumstance when you cast the two spells. For example, a "
            + "contingency cast with water breathing might stipulate that water breathing comes into "
            + "effect when you are engulfed in water or a similar liquid. The contingent spell takes "
            + "effect immediately after the circumstance is met for the first time, whether or not "
            + "you want it to, and then contingency ends. The contingent spell takes effect only on "
            + "you, even if it can normally target others. You can use only one contingency spell at "
            + "a time. If you cast this spell again, the effect of another contingency spell on you "
            + "ends. Also, contingency ends on you if its material component is ever not on your person.")),
    CREATE_UNDEAD(spell(MagicSchool.NECROMANCY, 6)
        .castingTime("1 minute").components(VERBAL, SOMATIC, MATERIAL)
        .range("10 feet").duration("Instantaneous")
        .effect("You can cast this spell only at night. Choose up to three corpses of Medium or Small "
            + "humanoids within range. Each corpse becomes a ghoul under your control. (The GM has "
            + "game statistics for these creatures.) As a bonus action on each of your turns, you can "
            + "mentally command any creature you animated with this spell if the creature is within "
            + "120 feet of you (if you control multiple creatures, you can command any or all of them "
            + "at the same time, issuing the same command to each one). You decide what action the "
            + "creature will take and where it will move during its next turn, or you can issue a "
            + "general command, such as to guard a particular chamber or corridor. If you issue no "
            + "commands, the creature only defends itself against hostile creatures. Once given an "
            + "order, the creature continues to follow it until its task is complete. The creature is "
            + "under your control for 24 hours, after which it stops obeying any command you have "
            + "given it. To maintain control of the creature for another 24 hours, you must cast this "
            + "spell on the creature before the current 24-hour period ends. This use of the spell "
            + "reasserts your control over up to three creatures you have animated with this spell, "
            + "rather than animating new ones. At Higher Levels. When you cast this spell using a "
            + "7th-level spell slot, you can animate or reassert control over four ghouls. When you "
            + "cast this spell using an 8th-level spell slot, you can animate or reassert control "
            + "over five ghouls or two ghasts or wights. When you cast this spell using a 9th-level "
            + "spell slot, you can animate or reassert control over six ghouls, three ghasts or "
            + "wights, or two mummies.")),
    DISINTEGRATE(spell(MagicSchool.TRANSMUTATION, 6)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("60 feet").duration("Instantaneous")
        .effect("A thin green ray springs from your pointing finger to a target that you can see within "
            + "range. The target can be a creature, an object, or a creation of magical force, such "
            + "as the wall created by wall of force. A creature targeted by this spell must make a "
            + "Dexterity saving throw. On a failed save, the target takes 10d6 + 40 force damage. If "
            + "this damage reduces the target to 0 hit points, it is disintegrated. A disintegrated "
            + "creature and everything it is wearing and carrying, except magic items, are reduced to "
            + "a pile of fine gray dust. The creature can be restored to life only by means of a true "
            + "resurrection or a wish spell. This spell automatically disintegrates a Large or "
            + "smaller nonmagical object or a creation of magical force. If the target is a Huge or "
            + "larger object or creation of force, this spell disintegrates a 10-foot- cube portion "
            + "of it. A magic item is unaffected by this spell. At Higher Levels. When you cast this "
            + "spell using a spell slot of 7th level or higher, the damage increases by 3d6 for each "
            + "slot level above 6th.")),
    EYEBITE(spell(MagicSchool.NECROMANCY, 6)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Self").duration("up to 1 minute")
        .effect("For the spell's duration, your eyes become an inky void imbued with dread power. One "
            + "creature of your choice within 60 feet of you that you can see must succeed on a "
            + "Wisdom saving throw or be affected by one of the following effects of your choice for "
            + "the duration. On each of your turns until the spell ends, you can use your action to "
            + "target another creature but can't target a creature again if it has succeeded on a "
            + "saving throw against this casting of eyebite. Asleep. The target falls unconscious. It "
            + "wakes up if it takes any damage or if another creature uses its action to shake the "
            + "sleeper awake. Panicked. The target is frightened of you. On each of its turns, the "
            + "frightened creature must take the Dash action and move away from you by the safest and "
            + "shortest available route, unless there is nowhere to move. If the target moves to a "
            + "place at least 60 feet away from you where it can no longer see you, this effect ends. "
            + "Sickened. The target has disadvantage on attack rolls and ability checks. At the end "
            + "of each of its turns, it can make another Wisdom saving throw. If it succeeds, the "
            + "effect ends.")),
    FIND_THE_PATH(spell(MagicSchool.DIVINATION, 6)
        .castingTime("1 minute").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").duration("up to 1 day")
        .effect("This spell allows you to find the shortest, most direct physical route to a specific "
            + "fixed location that you are familiar with on the same plane of existence. If you name "
            + "a destination on another plane of existence, a destination that moves (such as a "
            + "mobile fortress), or a destination that isn't specific (such as 'a green dragon's "
            + "lair'), the spell fails. For the duration, as long as you are on the same plane of "
            + "existence as the destination, you know how far it is and in what direction it lies. "
            + "While you are traveling there, whenever you are presented with a choice of paths along "
            + "the way, you automatically determine which path is the shortest and most direct route "
            + "(but not necessarily the safest route) to the destination.")),
    FLESH_TO_STONE(spell(MagicSchool.TRANSMUTATION, 6)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("60 feet").duration("up to 1 minute")
        .effect("You attempt to turn one creature that you can see within range into stone. If the "
            + "target's body is made of flesh, the creature must make a Constitution saving throw. On "
            + "a failed save, it is restrained as its flesh begins to harden. On a successful save, "
            + "the creature isn't affected. A creature restrained by this spell must make another "
            + "Constitution saving throw at the end of each of its turns. If it successfully saves "
            + "against this spell three times, the spell ends. If it fails its saves three times, it "
            + "is turned to stone and subjected to the petrified condition for the duration. The "
            + "successes and failures don't need to be consecutive; keep track of both until the "
            + "target collects three of a kind. If the creature is physically broken while petrified, "
            + "it suffers from similar deformities if it reverts to its original state. If you "
            + "maintain your concentration on this spell for the entire possible duration, the "
            + "creature is turned to stone until the effect is removed.")),
    FORBIDDANCE(spell(MagicSchool.ABJURATION, 6).ritual()
        .castingTime("10 minutes").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").duration("1 day")
        .effect("You create a ward against magical travel that protects up to 40,000 square feet of "
            + "floor space to a height of 30 feet above the floor. For the duration, creatures can't "
            + "teleport into the area or use portals, such as those created by the gate spell, to "
            + "enter the area. The spell proofs the area against planar travel, and therefore "
            + "prevents creatures from accessing the area by way of the Astral Plane, Ethereal Plane, "
            + "Feywild, Shadowfell, or the plane shift spell. In addition, the spell damages types of "
            + "creatures that you choose when you cast it. Choose one or more of the following: "
            + "celestials, elementals, fey, fiends, and undead. When a chosen creature enters the "
            + "spell's area for the first time on a turn or starts its turn there, the creature takes "
            + "5d10 radiant or necrotic damage (your choice when you cast this spell). When you cast "
            + "this spell, you can designate a password. A creature that speaks the password as it "
            + "enters the area takes no damage from the spell. The spell's area can't overlap with "
            + "the area of another ddance spell. If you cast forbiddance every day for 30 days "
            + "in the same location, the spell lasts until it is dispelled, and the material "
            + "components are consumed on the last casting.")),
    FREEZING_SPHERE(spell(MagicSchool.EVOCATION, 6)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("300 feet").duration("Instantaneous")
        .effect("A frigid globe of cold energy streaks from your fingertips to a point of your choice "
            + "within range, where it explodes in a 60-foot-radius sphere. Each creature within the "
            + "area must make a Constitution saving throw. On a failed save, a creature takes 10d6 "
            + "cold damage. On a successful save, it takes half as much damage. If the globe strikes "
            + "a body of water or a liquid that is principally water (not including water-based "
            + "creatures), it freezes the liquid to a depth of 6 inches over an area 30 feet square. "
            + "This ice lasts for 1 minute. Creatures that were swimming on the surface of frozen "
            + "water are trapped in the ice. A trapped creature can use an action to make a Strength "
            + "check against your spell save DC to break free. You can refrain from firing the globe "
            + "after completing the spell, if you wish. A small globe about the size of a sling "
            + "stone, cool to the touch, appears in your hand. At any time, you or a creature you "
            + "give the globe to can throw the globe (to a range of 40 feet) or hurl it with a sling "
            + "(to the sling's normal range). It shatters on impact, with the same effect as the "
            + "normal casting of the spell. You can also set the globe down without shattering it. "
            + "After 1 minute, if the globe hasn't already shattered, it explodes. At Higher Levels. "
            + "When you cast this spell using a spell slot of 7th level or higher, the damage "
            + "increases by 1d6 for each slot level above 6th.")),
    GLOBE_OF_INVULNERABILITY(spell(MagicSchool.ABJURATION, 6)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self (10-foot radius)").duration("up to 1 minute")
        .effect("An immobile, faintly shimmering barrier springs into existence in a 10-foot radius "
            + "around you and remains for the duration. Any spell of 5th level or lower cast from "
            + "outside the barrier can't affect creatures or objects within it, even if the spell is "
            + "cast using a higher level spell slot. Such a spell can target creatures and objects "
            + "within the barrier, but the spell has no effect on them. Similarly, the area within "
            + "the barrier is excluded from the areas affected by such spells. At Higher Levels. When "
            + "you cast this spell using a spell slot of 7th level or higher, the barrier blocks "
            + "spells of one level higher for each slot level above 6th.")),
    GUARDS_AND_WARDS(spell(MagicSchool.ABJURATION, 6)
        .castingTime("10 minutes").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").duration("24 hours")
        .effect("You create a ward that protects up to 2,500 square feet of floor space (an area 50 "
            + "feet square, or one hundred 5-foot squares or twenty-five 10-foot squares). The warded "
            + "area can be up to 20 feet tall, and shaped as you desire. You can ward several stories "
            + "of a stronghold by dividing the area among them, as long as you can walk into each "
            + "contiguous area while you are casting the spell. When you cast this spell, you can "
            + "specify individuals that are unaffected by any or all of the effects that you choose. "
            + "You can also specify a password that, when spoken aloud, makes the speaker immune to "
            + "these effects. Guards and wards creates the following effects within the warded area. "
            + "Corridors. Fog fills all the warded corridors, making them heavily obscured. In "
            + "addition, at each intersection or branching passage offering a choice of direction, "
            + "there is a 50 percent chance that a creature other than you will believe it is going "
            + "in the opposite direction from the one it chooses. Doors. All doors in the warded area "
            + "are magically locked, as if sealed by an arcane lock spell. In addition, you can cover "
            + "up to ten doors with an illusion (equivalent to the illusory object function of the "
            + "minor illusion spell) to make them appear as plain sections of wall. Stairs. Webs fill "
            + "all stairs in the warded area from top to bottom, as the web spell. These strands "
            + "regrow in 10 minutes if they are burned or torn away while guards and wards lasts. "
            + "Other Spell Effect. You can place your choice of one of the following magical effects "
            + "within the warded area of the stronghold. Place dancing lights in four corridors. You "
            + "can designate a simple program that the lights repeat as long as guards and wards "
            + "lasts. Place magic mouth in two locations. Place stinking cloud in two locations. The "
            + "vapors appear in the places you designate; they return within 10 minutes if dispersed "
            + "by wind while guards and wards lasts. Place a constant gust of wind in one corridor or "
            + "room. Place a suggestion in one location. You select an area of up to 5 feet square, "
            + "and any creature that enters or passes through the area receives the suggestion "
            + "mentally. The whole warded area radiates magic. A dispel magic cast on a specific "
            + "effect, if successful, removes only that effect. You can create a permanently guarded "
            + "and warded structure by casting this spell there every day for one year.")),
    HARM(spell(MagicSchool.NECROMANCY, 6)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("60 feet").duration("Instantaneous")
        .effect("You unleash a virulent disease on a creature that you can see within range. The target "
            + "must make a Constitution saving throw. On a failed save, it takes 14d6 necrotic "
            + "damage, or half as much damage on a successful save. The damage can't reduce the "
            + "target's hit points below 1. If the target fails the saving throw, its hit point "
            + "maximum is reduced for 1 hour by an amount equal to the necrotic damage it took. Any "
            + "effect that removes a disease allows a creature's hit point maximum to return to "
            + "normal before that time passes.")),
    HEAL(spell(MagicSchool.EVOCATION, 6)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("60 feet").duration("Instantaneous")
        .effect("Choose a creature that you can see within range. A surge of positive energy washes "
            + "through the creature, causing it to regain 70 hit points. This spell also ends "
            + "blindness, deafness, and any diseases affecting the target. This spell has no effect "
            + "on constructs or undead. At Higher Levels. When you cast this spell using a spell slot "
            + "of 7th level or higher, the amount of healing increases by 10 for each slot level "
            + "above 6th.")),
    HEROES_FEAST(spell(MagicSchool.CONJURATION, 6)
        .castingTime("10 minutes").components(VERBAL, SOMATIC, MATERIAL)
        .range("30 feet").duration("Instantaneous")
        .effect("You bring forth a great feast, including magnificent food and drink. The feast takes 1 "
            + "hour to consume and disappears at the end of that time, and the beneficial effects "
            + "don't set in until this hour is over. Up to twelve other creatures can partake of the "
            + "feast. A creature that partakes of the feast gains several benefits. The creature is "
            + "cured of all diseases and poison, becomes immune to poison and being frightened, and "
            + "makes all Wisdom saving throws with advantage. Its hit point maximum also increases by "
            + "2d10, and it gains the same number of hit points. These benefits last for 24 hours.")),
    INSTANT_SUMMONS(spell(MagicSchool.CONJURATION, 6).ritual()
        .castingTime("1 minute").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 object up to 10lb").duration("Until dispelled")
        .effect("Transports object or learn who and where holder is.")),
    IRRESISTIBLE_DANCE(spell(MagicSchool.ENCHANTMENT, 6)
        .castingTime("1 action").components(VERBAL)
        .range("30 feet").duration("up to 1 minute")
        .effect("Choose one creature that you can see within range. The target begins a comic dance in "
            + "place: shuffling, tapping its feet, and capering for the duration. Creatures that "
            + "can't be charmed are immune to this spell. A dancing creature must use all its "
            + "movement to dance without leaving its space and has disadvantage on Dexterity saving "
            + "throws and attack rolls. While the target is affected by this spell, other creatures "
            + "have advantage on attack rolls against it. As an action, a dancing creature makes a "
            + "Wisdom saving throw to regain control of itself. On a successful save, the spell ends.")),
    MAGIC_JAR(spell(MagicSchool.NECROMANCY, 6)
        .castingTime("1 minute").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").duration("Until dispelled")
        .effect("Your body falls into a catatonic state as your soul leaves it and enters the container "
            + "you used for the spell's material component. While your soul inhabits the container, "
            + "you are aware of your surroundings as if you were in the container's space. You can't "
            + "move or use reactions. The only action you can take is to project your soul up to 100 "
            + "feet out of the container, either returning to your living body (and ending the spell) "
            + "or attempting to possess a humanoids body. You can attempt to possess any humanoid "
            + "within 100 feet of you that you can see (creatures warded by a protection from evil "
            + "and good or magic circle spell can't be possessed). The target must make a Charisma "
            + "saving throw. On a failure, your soul moves into the target's body, and the target's "
            + "soul becomes trapped in the container. On a success, the target resists your efforts "
            + "to possess it, and you can't attempt to possess it again for 24 hours. Once you "
            + "possess a creature's body, you control it. Your game statistics are replaced by the "
            + "statistics of the creature, though you retain your alignment and your Intelligence, "
            + "Wisdom, and Charisma scores. You retain the benefit of your own class features. If the "
            + "target has any class levels, you can't use any of its class features. Meanwhile, the "
            + "possessed creature's soul can perceive from the container using its own senses, but it "
            + "can't move or take actions at all. While possessing a body, you can use your action to "
            + "return from the host body to the container if it is within 100 feet of you, returning "
            + "the host creature's soul to its body. If the host body dies while you're in it, the "
            + "creature dies, and you must make a Charisma saving throw against your own spellcasting "
            + "DC. On a success, you return to the container if it is within 100 feet of you. "
            + "Otherwise, you die. If the container is destroyed or the spell ends, your soul "
            + "immediately returns to your body. If your body is more than 100 feet away from you or "
            + "if your body is dead when you attempt to return to it, you die. If another creature's "
            + "soul is in the container when it is destroyed, the creature's soul returns to its body "
            + "if the body is alive and within 100 feet. Otherwise, that creature dies. When the "
            + "spell ends, the container is destroyed.")),
    MASS_SUGGESTION(spell(MagicSchool.ENCHANTMENT, 6)
        .castingTime("1 action").components(VERBAL, MATERIAL)
        .range("60 feet").duration("24 hours")
        .effect("You suggest a course of activity (limited to a sentence or two) and magically "
            + "influence up to twelve creatures of your choice that you can see within range and that "
            + "can hear and understand you. Creatures that can't be charmed are immune to this "
            + "effect. The suggestion must be worded in such a manner as to make the course of action "
            + "sound reasonable. Asking the creature to stab itself, throw itself onto a spear, "
            + "immolate itself, or do some other obviously harmful act automatically negates the "
            + "effect of the spell. Each target must make a Wisdom saving throw. On a failed save, it "
            + "pursues the course of action you described to the best of its ability. The suggested "
            + "course of action can continue for the entire duration. If the suggested activity can "
            + "be completed in a shorter time, the spell ends when the subject finishes what it was "
            + "asked to do. You can also specify conditions that will trigger a special activity "
            + "during the duration. For example, you might suggest that a group of soldiers give all "
            + "their money to the first beggar they meet. If the condition isn't met before the spell "
            + "ends, the activity isn't performed. If you or any of your companions damage a creature "
            + "affected by this spell, the spell ends for that creature. At Higher Levels. When you "
            + "cast this spell using a 7th-level spell slot, the duration is 10 days. When you use an "
            + "8th-level spell slot, the duration is 30 days. When you use a 9th-level spell slot, "
            + "the duration is a year and a day.")),
    MOVE_EARTH(spell(MagicSchool.TRANSMUTATION, 6)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("120 feet").duration("up to 2 hours")
        .effect("Choose an area of terrain no larger than 40 feet on a side within range. You can "
            + "reshape dirt, sand, or clay in the area in any manner you choose for the duration. You "
            + "can raise or lower the area's elevation, create or fill in a trench, erect or flatten "
            + "a wall, or form a pillar. The extent of any such changes can't exceed half the area's "
            + "largest dimension. So, if you affect a 40-foot square, you can create a pillar up to "
            + "20 feet high, raise or lower the square's elevation by up to 20 feet, dig a trench up "
            + "to 20 feet deep, and so on. It takes 10 minutes for these changes to complete. At the "
            + "end of every 10 minutes you spend concentrating on the spell, you can choose a new "
            + "area of terrain to affect. Because the terrain's transformation occurs slowly, "
            + "creatures in the area can't usually be trapped or injured by the ground's movement. "
            + "This spell can't manipulate natural stone or stone construction. Rocks and structures "
            + "shift to accommodate the new terrain. If the way you shape the terrain would make a "
            + "structure unstable, it might collapse. Similarly, this spell doesn't directly affect "
            + "plant growth. The moved earth carries any plants along with it.")),
    PLANAR_ALLY(spell(MagicSchool.CONJURATION, 6)
        .castingTime("10 minutes").components(VERBAL, SOMATIC)
        .range("60 feet").area("Otherworldly entity").duration("Instantaneous")
        .effect("Summon creature to request service.")),
    PROGRAMMED_ILLUSION(spell(MagicSchool.ILLUSION, 6)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("120 feet").duration("Until dispelled")
        .effect("You create an illusion of an object, a creature, or some other visible phenomenon "
            + "within range that activates when a specific condition occurs. The illusion is "
            + "imperceptible until then. It must be no larger than a 30-foot cube, and you decide "
            + "when you cast the spell how the illusion behaves and what sounds it makes. This "
            + "scripted performance can last up to 5 minutes. When the condition you specify occurs, "
            + "the illusion springs into existence and performs in the manner you described. Once the "
            + "illusion finishes performing, it disappears and remains dormant for 10 minutes. After "
            + "this time, the illusion can be activated again. The triggering condition can be as "
            + "general or as detailed as you like, though it must be based on visual or audible "
            + "conditions that occur within 30 feet of the area. For example, you could create an "
            + "illusion of yourself to appear and warn off others who attempt to open a trapped door, "
            + "or you could set the illusion to trigger only when a creature says the correct word or "
            + "phrase. Physical interaction with the image reveals it to be an illusion, because "
            + "things can pass through it. A creature that uses its action to examine the image can "
            + "determine that it is an illusion with a successful Intelligence (Investigation) check "
            + "against your spell save DC. If a creature discerns the illusion for what it is, the "
            + "creature can see through the image, and any noise it makes sounds hollow to the creature.")),
    SUNBEAM(spell(MagicSchool.EVOCATION, 6)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self (60-foot line)").duration("up to 1 minute")
        .effect("A beam of brilliant light flashes out from your hand in a 5-foot-wide, 60-foot-long "
            + "line. Each creature in the line must make a Constitution saving throw. On a failed "
            + "save, a creature takes 6d8 radiant damage and is blinded until your next turn. On a "
            + "successful save, it takes half as much damage and isn't blinded by this spell. Undead "
            + "and oozes have disadvantage on this saving throw. You can create a new line of "
            + "radiance as your action on any turn until the spell ends. For the duration, a mote of "
            + "brilliant radiance shines in your hand. It sheds bright light in a 30-foot radius and "
            + "dim light for an additional 30 feet. This light is sunlight.")),
    TRANSPORT_VIA_PLANTS(spell(MagicSchool.CONJURATION, 6)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("10 feet").duration("1 round")
        .effect("This spell creates a magical link between a Large or larger inanimate plant within "
            + "range and another plant, at any distance, on the same plane of existence. You must "
            + "have seen or touched the destination plant at least once before. For the duration, any "
            + "creature can step into the target plant and exit from the destination plant by using 5 "
            + "feet of movement.")),
    TRUE_SEEING(spell(MagicSchool.DIVINATION, 6)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").duration("1 hour")
        .effect("This spell gives the willing creature you touch the ability to see things as they "
            + "actually are. For the duration, the creature has truesight, notices secret doors "
            + "hidden by magic, and can see into the Ethereal Plane, all out to a range of 120 feet.")),
    WALL_OF_ICE(spell(MagicSchool.EVOCATION, 6)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("120 feet").duration("up to 10 minutes")
        .effect("You create a wall of ice on a solid surface within range. You can form it into a "
            + "hemispherical dome or a sphere with a radius of up to 10 feet, or you can shape a flat "
            + "surface made up of ten 10-foot-square panels. Each panel must be contiguous with "
            + "another panel. In any form, the wall is 1 foot thick and lasts for the duration. If "
            + "the wall cuts through a creature's space when it appears, the creature within its area "
            + "is pushed to one side of the wall and must make a Dexterity saving throw. On a failed "
            + "save, the creature takes 10d6 cold damage, or half as much damage on a successful "
            + "save. The wall is an object that can be damaged and thus breached. It has AC 12 and 30 "
            + "hit points per 10-foot section, and it is vulnerable to fire damage. Reducing a "
            + "10-foot section of wall to 0 hit points destroys it and leaves behind a sheet of "
            + "frigid air in the space the wall occupied. A creature moving through the sheet of "
            + "frigid air for the first time on a turn must make a Constitution saving throw. That "
            + "creature takes 5d6 cold damage on a failed save, or half as much damage on a "
            + "successful one. At Higher Levels. When you cast this spell using a spell slot of 7th "
            + "level or higher, the damage the wall deals when it appears increases by 2d6, and the "
            + "damage from passing through the sheet of frigid air increases by 1d6, for each slot "
            + "level above 6th.")),
    WALL_OF_THORNS(spell(MagicSchool.CONJURATION, 6)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("120 feet").area("60-foot long, 10-foot high, 5-foot thick "
        + "or 20-foot diameter, 20-foot high, 5-foot thick").duration("up to 10 minutes")
        .effect("Blocks line of sight. 7d8 piercing damage. Dex. save for half damage. "
            + "Each 1 foot through wall takes 4 feet of movement. "
            + "7d8 slashing damage to move through wall. Dex. save for half damage."
            + "+1d8 damage / extra spell slot level.")),
    WIND_WALK(spell(MagicSchool.TRANSMUTATION, 6)
        .castingTime("1 minute").components(VERBAL, SOMATIC, MATERIAL)
        .range("30 feet").area("Caster and up to 10 allies").duration("8 hours")
        .effect("Assume gaseous form. Flying speed of 300 feet. "
            + "Resistance to damage from nonmagical weapons. Reverting takes 1 minute.")),
    WORD_OF_RECALL(spell(MagicSchool.CONJURATION, 6)
        .castingTime("1 action").components(VERBAL)
        .range("5 feet").area("Caster and up to 5 allies within 5 feet").duration("Instantaneous")
        .effect("Teleport to a previously designated sanctuary linked to caster's deity. ")),
    ARCANE_SWORD(spell(MagicSchool.EVOCATION, 7)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("60 feet").area("Unoccupied Space").duration("up to 1 minute")
        .effect("Melee spell attack +[$spell_mod]. 3d10 force damage. "
            + "As bonus action, move up to 20 feet and attack.")),
    CONJURE_CELESTIAL(spell(MagicSchool.CONJURATION, 7)
        .castingTime("1 minute").components(VERBAL, SOMATIC)
        .range("90 feet").area("Unoccupied space").duration("up to 1 hour")
        .effect("Summon friendly celestial of CR4 or lower. CR5 at 9th-level spell slot.")),
    DELAYED_BLAST_FIREBALL(spell(MagicSchool.EVOCATION, 7)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("150 feet").area("20-foot-radius sphere").duration("up to 1 minute")
        .effect("12d6 damage +1d6 each turn before detonation. Dex. save for half damage. "
            + "Creature touching bead Dex. save to throw up to 40 feet.")
        .effect("+1d6 / extra spell slot level.")),
    DIVINE_WORD(spell(MagicSchool.EVOCATION, 7)
        .castingTime("1 bonus action").components(VERBAL)
        .range("30 feet").area("Any number of creatures").duration("Instantaneous")
        .effect("Chr. save for no effect. ")
        .effect(table(header("HP", "Effect"),
            row("20 or fewer", "Killed"),
            row("30 or fewer", "Deafened, blinded and stunned for 1 hour"),
            row("40 or fewer", "Deafened and blinded for 10 minutes"),
            row("50 or fewer", "Deafened for 1 minute")))
        .effect("Celestials, elementals, feys and fiends return to plane "
            + "of origin and cannot return for 24 hours. ")),
    ETHEREALNESS(spell(MagicSchool.TRANSMUTATION, 7)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Self").duration("Up to 8 hours")
        .effect("Step into Ethereal Plane. "
            + "Double movement costs. Return to original position. ")
        .effect("+3 willing creatures within 10 feet / extra spell slot level.")),
    FINGER_OF_DEATH(spell(MagicSchool.NECROMANCY, 7)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("60 feet").area("1 creature").duration("Instantaneous")
        .effect("7d8+30 necrotic damage. Con. save for half damage. "
            + "Humanoids killed by spell become zombies under caster's control. ")),
    FIRE_STORM(spell(MagicSchool.EVOCATION, 7)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("150 feet").area("10 connected 10-foot-cubes").duration("Instantaneous")
        .effect("7d10 fire damage. Dex. save for half damage. "
            + "Caster can choose to make plants unaffected. ")),
    FORCECAGE(spell(MagicSchool.EVOCATION, 7)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("100 feet").area("Up to 20-foot-cube").duration("1 hour")
        .effect("Create invisible prison around area. "
            + "Can be cage (20-foot-side) with bars "
            + "or box (10-foot-side) preventing matter and spells. "
            + "Creature within area cannot leave.")
        .effect("Immune to <em>Dispel Magic</em>.")
        .effect("Chr. save to use teleportation or interplanar travel to leave cage.")),
    MAGNIFICENT_MANSION(spell(MagicSchool.CONJURATION, 7)
        .castingTime("1 minute").components(VERBAL, SOMATIC, MATERIAL)
        .range("300 feet").area("5-feet wide, 10-feet tall").duration("24 hours")
        .effect("Create extradimensional dwelling. Contains food and furniture for 100 people.")),
    MIRAGE_ARCANE(spell(MagicSchool.ILLUSION, 7)
        .castingTime("10 minutes").components(VERBAL, SOMATIC)
        .range("Sight").area("Terrain 1-mile-square").duration("10 days")
        .effect("Area looks, sounds, smells, and feels like another sort of terrain.")),
    PLANE_SHIFT(spell(MagicSchool.CONJURATION, 7)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("Up to 8 allies or 1 enemy").duration("Instantaneous")
        .effect("Targets transported to different plane.")),
    PRISMATIC_SPRAY(spell(MagicSchool.EVOCATION, 7)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Self").area("60-foot cone").duration("Instantaneous")
        .effect("Each creature in area Dex. save. d8 for effect:")
        .effect(table(
            header("Roll", "Colour", "Effect", "Save"),
            row("1", "Red", "10d6 fire damage", "Half damage"),
            row("2", "Orange", "10d6 acid damage", "Half damage"),
            row("3", "Yellow", "10d6 lightning damage", "Half damage"),
            row("4", "Green", "10d6 poison damage", "Half damage"),
            row("5", "Blue", "10d6 cold damage", "Half damage"),
            row("6", "Indigo", "Restrained. Con. save. 3 successes: effect ends. "
                + "3 failures: petrified", "No effect"),
            row("7", "Violet", "Blinded. Tranported to another plane on next turn. Wis. save",
                "No effect"),
            row("8", "Special", "Roll twice more", "-")))),
    PROJECT_IMAGE(spell(MagicSchool.ILLUSION, 7)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("500 miles").duration("up to 1 day")
        .effect("Create illusory copy. Caster can use illusion's senses. "
            + "As an action, move illusion up to twice speed. "
            + "Investigation check to discover illusion.")),
    REGENERATE(spell(MagicSchool.TRANSMUTATION, 7)
        .castingTime("1 minute").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 creature").duration("1 hour")
        .effect("Regain 4d8+15 HP. Regain 1 HP at the start of each turn. "
            + "Restore severed limbs after 2 minutes.")),
    RESURRECTION(spell(MagicSchool.NECROMANCY, 7)
        .castingTime("1 hour").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 dead creature").duration("Instantaneous")
        .effect("Target returns to life with full HP. Neutralises poison. Cures nonmagical diseases. "
            + "Target takes a −4 penalty to all attack rolls, saves and ability checks. "
            + "Penalty reduces by 1 after a long rest.")),
    REVERSE_GRAVITY(spell(MagicSchool.TRANSMUTATION, 7)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("100 feet").area("50-foot-radius, 100-foot-high cylinder")
        .duration("up to 1 minute")
        .effect("All creatures and objects fall upward to top of the area. "
            + "Dex. save to grab fixed object within reach.")),
    SEQUESTER(spell(MagicSchool.TRANSMUTATION, 7)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 willing creature or object").duration("Until dispelled")
        .effect("Target becomes invisible and cannot be detected by divination. "
            + "Creatures enter suspended animation. "
            + "Spell ends on chosen condition or if target takes damage.")),
    SIMULACRUM(spell(MagicSchool.ILLUSION, 7)
        .castingTime("12 hours").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 beast or humanoid").duration("Until dispelled")
        .effect("Create illusory duplicate of target with half target's HP and no equipment. "
            + "Duplicate is under caster's control. "
            + "Spell ends when duplicate drops to 0 HP.")),
    SYMBOL(spell(MagicSchool.ABJURATION, 7)
        .castingTime("1 minute").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("Surface no larger than 10-feet-diameter or closable object")
        .duration("Until dispelled or triggered")
        .effect("Incribe a glyph on target. Investigation check to find. "
            + "Target object must remain in place. On chosen trigger, glyph glows with dim light "
            + "in 60-foot-radius sphere for 10 minutes. Creatures within spher suffer chosen effect:")
        .effect(table(header("Option", "Effect", "Save"),
            row("Death", "10d10 necrotic damage", "Con. save for half damage"),
            row("Discord", "Argues with other creature for 1 minute; "
                + "disadvantage on attack and ability", "Con. save"),
            row("Fear", "Move 30 feet away from glyph on each turn for 1 minute", "Wis. save"),
            row("Hopelessness", "Cannot attack for 1 minute", "Chr. save"),
            row("Insanity", "Cannot take voluntary actions for 1 minute", "Int. save"),
            row("Pain", "Incapacitated for 1 minute", "Con. save"),
            row("Sleep", "Falls unconscious for 10 minutes or until woken", "Wis. save"),
            row("Stunning", "Stunned for 1 minute", "Wis. save")))),
    TELEPORT(spell(MagicSchool.CONJURATION, 7)
        .castingTime("1 action").components(VERBAL)
        .range("10 feet").area("Self and up to 8 creatures or 1 object")
        .duration("Instantaneous")
        .effect("Transport targets to chosen destination. Special chance of miss.")),
    ANIMAL_SHAPES(spell(MagicSchool.TRANSMUTATION, 8)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("30 feet").area("any number of willing creatures").duration("up to 24 hours")
        .effect("Transform into beasts with CR of 4 or lower.")),
    ANTIMAGIC_FIELD(spell(MagicSchool.ABJURATION, 8)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").area("10-foot-radius sphere").duration("up to 1 hour")
        .effect("Spells and magical effects suppressed within area. Magic items act as equivalent "
            + "nonmagical items within area. Summoned or created creatures temporarily become "
            + "non-existent. ")),
    ANTIPATHY_SYMPATHY(spell(MagicSchool.ENCHANTMENT, 8)
        .castingTime("1 hour").components(VERBAL, SOMATIC, MATERIAL)
        .range("60 feet").area("1 object, creature or area no larger than 200-foot-cube")
        .duration("10 days")
        .effect("Target attracks or repels a chosen kind of intelligent creature. ")
        .effect("<b>Antipathy</b> Creatures of chosen kind become frightened "
            + "while within 60 feet of target. Wis. save. "
            + "Effected creatures move to a spot where they cannot see target. ")
        .effect("<b>Sympathy</b> Creatures of chosen kind feel intense urge to approach while "
            + "within 60 feet of target. Wis. save. "
            + "Effected creatures move to within reach of target. ")
        .effect("Effected creatures Wis. save every 24 hours. Successful save grants immunity for "
            + "1 minute.")),
    CLONE(spell(MagicSchool.NECROMANCY, 8)
        .castingTime("1 hour").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 creature").duration("Instantaneous")
        .effect("Grows an inert duplicate of target to full size and maturity after 120 days. "
            + "Soul transfers to clone if target dies. Equipment is not transferred.")),
    CONTROL_WEATHER(spell(MagicSchool.TRANSMUTATION, 8)
        .castingTime("10 minutes").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").area("5-mile radius").duration("up to 8 hours")
        .effect("Can only be cast outdoors. Change the current precipitation, temperature and wind. "
            + "1d4×10 minutes for the new conditions to take effect. Can be changed again.")
        .effect("Can change by one stage in either direction.")
        .effect(table(
            header("Precipitation", "Temperature", "Wind"),
            row(ol("Clear", "Light clouds", "Overcast", "Rain", "Torrential"),
                ol("Unbearable heat", "Hot", "Warm", "Cool", "Cold", "Arctic cold"),
                ol("Calm", "Moderate wind", "Strong wind", "Gale", "Storm"))))),
    DEMIPLANE(spell(MagicSchool.CONJURATION, 8)
        .castingTime("1 action").components(SOMATIC)
        .range("60 feet").area("flat solid surface").duration("1 hour")
        .effect("Create a shadowy door to an empty 30-foot cubic room.")),
    DOMINATE_MONSTER(spell(MagicSchool.ENCHANTMENT, 8)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("60 feet").area("1 creature").duration("up to 1 hour")
        .effect("Charmed. Wis. save. Advantage if in combat with caster. "
            + "Wis. save each time target takes damage to end. ")
        .effect("Duration up to 8 hours with 9th-level spell slot.")),
    EARTHQUAKE(spell(MagicSchool.EVOCATION, 8)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("500 feet").area("100-foot-radius circle").duration("up to 1 minute")
        .effect("Ground becomes difficult terrain. Concentration broken unless Con. save. "
            + "Each turn knocked prone unless Dex. save. ")
        .effect(table(
            row("Fissures", "1d6 fissures each 1d10x10 feet deep, 10 feet wide",
                "Creature on fissure falls in. Dex. save."),
            row("Structures", "50 bludgeoning damage to any structure each turn",
                "Creature near structure at 0 HP takes 5d6 bludgeoning damage and is buried. "
                + "Dex. save.")))),
    FEEBLEMIND(spell(MagicSchool.ENCHANTMENT, 8)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("150 feet").area("1 creature").duration("Instantaneous")
        .effect("4d6 psychic damage. Int. and Chr. become 1 unless Int. save. "
            + "Target cannot cast spells, activate magic items, understand language or communicate. "
            + "Repeat save every 30 days.")),
    GLIBNESS(spell(MagicSchool.TRANSMUTATION, 8)
        .castingTime("1 action").components(VERBAL)
        .range("Self").duration("1 hour")
        .effect("Can choose to replace Chr. check rolls with 15.")
        .effect("Lie detecting magic against caster indicates truth.")),
    HOLY_AURA(spell(MagicSchool.ABJURATION, 8)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").area("30-foot radius").duration("up to 1 minute")
        .effect("Allies have advantage on saves and impose disadvantage on attacks against them. "
            + "Fiends or undead hitting affected creatures with melee attack blinded. Con. save. ")),
    INCENDIARY_CLOUD(spell(MagicSchool.CONJURATION, 8)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("150 feet").area("20-foot-radius sphere").duration("up to 1 minute")
        .effect("Each creature in area takes 10d8 fire damage. Dex. save for half damage. "
            + "Cloud move 10 feet away in a chosen direction at the start of each turn.")),
    MAZE(spell(MagicSchool.CONJURATION, 8)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("60 feet").area("1 creature").duration("up to 10 minutes")
        .effect("Banish target. As action each turn, target make Int. check DC20 to reappear.")),
    MIND_BLANK(spell(MagicSchool.ABJURATION, 8)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Touch").area("1 willing creature").duration("24 hours")
        .effect("Immune to psychic damage, mind reading, divination, charm.")),
    POWER_WORD_STUN(spell(MagicSchool.ENCHANTMENT, 8)
        .castingTime("1 action").components(VERBAL)
        .range("60 feet").area("1 creature").duration("Instantaneous")
        .effect("Target stunned if it has 150 hit points or fewer. "
            + "Con. save at end of each turn to end effect. ")),
    SUNBURST(spell(MagicSchool.EVOCATION, 8)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("150 feet").area("60-foot radius").duration("Instantaneous")
        .effect("Each creature in area takes 12d6 radiant damage and blinded for 1 minute. "
            + "Con. save for half damage and not blinded. Undead and ozzes have disadvantage on save. "
            + "Blinded creatures save at end of each turn. ")),
    TELEPATHY(spell(MagicSchool.EVOCATION, 8)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Unlimited").area("1 willing creature").duration("24 hours")
        .effect("Create telepathic link.")),
    TSUNAMI(spell(MagicSchool.CONJURATION, 8)
        .castingTime("1 minute").components(VERBAL, SOMATIC)
        .range("Sight").area("300-feet long, 300-feet high, 50-feet thick")
        .duration("Concentration, up to 6 rounds")
        .effect("6d10 bludgeoning damage. Str. save for half. -1d10 each round.")),
    ASTRAL_PROJECTION(spell(MagicSchool.NECROMANCY, 9)
        .castingTime("1 hour").components(VERBAL, SOMATIC, MATERIAL).cost(Value.gp(1100))
        .range("10 feet").area("Self and up to 8 allies").duration("Special")
        .effect("Project astral bodies into the Astral Plane. Ends when astral body has 0 HP.")),
    FORESIGHT(spell(MagicSchool.DIVINATION, 9)
        .castingTime("1 minute").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 willing creature").duration("8 hours")
        .effect("Cannot be surprised. Advantage on attack rolls, ability checks, and saving throws.")
        .effect("Disadvantage on attack rolls against the target.")),
    GATE(spell(MagicSchool.CONJURATION, 9)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("60 feet").area("5 to 20 feet diameter.").duration("up to 1 minute")
        .effect("Links space to a different plane of existence. ")
        .effect("Can speak real name of a specific creature to draw it through the gate.")),
    IMPRISONMENT(spell(MagicSchool.ABJURATION, 9)
        .castingTime("1 minute").components(VERBAL, SOMATIC, MATERIAL)
        .range("30 feet").area("1 creature").duration("Until dispelled")
        .effect("Imprison creature. Wis. save.")),
    MASS_HEAL(spell(MagicSchool.EVOCATION, 9)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("60 feet").area("Any number of creatures").duration("Instantaneous")
        .effect("Restore up to 700 HP, curing all diseases, blindness and deafness.")),
    METEOR_SWARM(spell(MagicSchool.EVOCATION, 9)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("1 mile").area("4 40-foot-radius spheres").duration("Instantaneous")
        .effect("20d6 fire damage. 20d6 bludgeoning damage. Dex. save for half.")),
    POWER_WORD_HEAL(spell(MagicSchool.EVOCATION, 9)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Touch").area("1 creature").duration("Instantaneous")
        .effect("Regain all HP. End all conditions.")),
    POWER_WORD_KILL(spell(MagicSchool.ENCHANTMENT, 9)
        .castingTime("1 action").components(VERBAL)
        .range("60 feet").area("1 creature").duration("Instantaneous")
        .effect("Kills target if it has 100 HP or fewer.")),
    PRISMATIC_WALL(spell(MagicSchool.ABJURATION, 9)
        .castingTime("1 action").components(VERBAL, SOMATIC).range("60 feet")
        .area("Up to 90 feet long, 30 feet high, 1 inch thick or 30-foot diameter sphere")
        .duration("10 minutes")
        .effect("Create a seven layered wall that sheds bright light to 100 feet.")
        .effect("Enemies within 20 feet blinded for 1 minutes. Con. save.")
        .effect("Dex. save to avoid effect of each layer.")
        .effect(table(
            header("Layer", "Colour", "Effect", "Save", "Destroy"),
            row("1", "Red", "10d6 fire damage and prevents nonmagical ranged attacks", "Half",
                "25 cold damage"),
            row("2", "Orange", "10d6 acid damage", "Half", "Strong wind"),
            row("3", "Yellow", "10d6 lightning damage", "Half", "60 force damage."),
            row("4", "Green", "10d6 poison damage", "Half", "<em>Passwall</em> spell."),
            row("5", "Blue", "10d6 cold damage", "Half", "25 fire damage."),
            row("6", "Indigo", "Restrains creature. Prevents Spells.",
                "Con. save each turn. 3 success ends spell. "
                + "3 failures petrifies creature", "<em>Daylight</em> spell. "),
            row("7", "Violet", "Blinds creature", "Wis. save or be transported to another plane",
                "<em>Dispel Magic</em> spell.")))),
    SHAPECHANGE(spell(MagicSchool.TRANSMUTATION, 9)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").duration("up to 1 hour")
        .effect("Assume the form of a different creature with CR up to [$level].")
        .effect("Retain alignment, Int., Wis., Chr., skill and saving throw proficiencies.")),
    STORM_OF_VENGEANCE(spell(MagicSchool.CONJURATION, 9)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Sight").area("360-feet radius").duration("up to 1 minute")
        .effect("Forms storm cloud. Each creature under storm takes 2d6 thunder damage "
            + "and becomes defeaned for 5 minutes. Con. save.")
        .effect("Each round produces an additional effect:"
            + "<br>Round 2: acidic rain. 1d6 acid damage."
            + "<br>Round 3: six bolts of lightning 10d6 lightning damage. Dex. save for half."
            + "<br>Round 4: hailstones. 2d6 bludgeoning damage."
            + "<br>Round 5-10: gust and freezing rain. Difficult terrain and 1d6 cold damage. "
            + "Range weapon attacks impossible. Severe distraction for concentration. ")),
    TIME_STOP(spell(MagicSchool.TRANSMUTATION, 9)
        .castingTime("1 action").components(VERBAL)
        .range("Self").area("All other creatures").duration("Instantaneous")
        .effect("Take 1d4 + 1 turns. Ends if action effects other creature.")),
    TRUE_POLYMORPH(spell(MagicSchool.TRANSMUTATION, 9)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("30 feet").area("1 creature or nonmagical object").duration("up to 1 hour")
        .effect("Transform target into different creature or object. Wis. save.")
        .effect("Take all statistics of new form. Ends at 0 HP.")),
    TRUE_RESURRECTION(spell(MagicSchool.NECROMANCY, 9)
        .castingTime("1 hour").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 body").duration("Instantaneous")
        .effect("Target restored to life with all HP.")),
    WEIRD(spell(MagicSchool.ILLUSION, 9)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("120 feet").area("Each creature in a 30-foot-radius sphere")
        .duration("up to one minute")
        .effect("Targets become frightened takes 4d10 phychic damage. "
            + "Wis. save each turn.")),
    WISH(spell(MagicSchool.CONJURATION, 9)
        .castingTime("1 action").components(VERBAL)
        .range("Self").duration("Instantaneous")
        .effect("Duplicate any other spell of 8th level or lower.")
        .effect("Create one object of up to 25,000 gp in value that isn't a magic item.")
        .effect("Up to 20 creatures regain all HP and end all effects.")
        .effect("Up to 10 creatures gain resistance to a chosen damage type for 8 hours.")
        .effect("Up to 10 creatures gain immunity to a spell or magical effect for 8 hours.")
        .effect("Force a reroll of any role within the last round with advantage or disadvantage.")
        .effect("More than 1 use between a long rest causes 1d10 / spell level, "
            + "Str. 3 for 2d4 days, 33% chance of inability to cast <em>Wish</em> again.")),;

    private final SpellDelegate delegate;

    public static final Comparator<Spell> ORDER = Comparator
        .comparingInt(Spell::getLevel).thenComparing(Spell::toString);

    public static Stream<Spell> getSpellsAtLevel(int level) {
        return Arrays.stream(values()).filter(sp -> sp.isLevel(level));
    }

    private Spell(SpellDelegate delegate) {
        this.delegate = delegate;
    }

    public String getSchoolAndRitual() {
        return delegate.getSchool().toString() + (isRitual() ? "<br>Ritual" : "");
    }

    public int getLevel() {
        return delegate.getLevel();
    }

    public boolean isCantrip() {
        return getLevel() == 0;
    }

    public boolean isLevel(int level) {
        return getLevel() == level;
    }

    public boolean isRitual() {
        return delegate.isRitual();
    }

    public Stream<String> getEffects(Character character) {
        return delegate.getEffects(character);
    }

    public String getArea() {
        return delegate.getArea();
    }

    public String getDuration() {
        return delegate.getDuration();
    }

    public String getCastingTime() {
        return delegate.getCastingTime();
    }

    public String getRange() {
        return delegate.getRange();
    }

    public String getComponents() {
        return delegate.getComponents();
    }

    public Value getCost() {
        return delegate.getCost();
    }

    @Override
    public String getOptionName() {
        final String[] levelNames = {
            "Cantrip", "1st Level", "2nd Level", "3rd Level", "4th Level",
            "5th Level", "6th Level", "7th Level", "8th Level", "9th Level"
        };
        return String.format("%s (%s)", toString(), levelNames[getLevel()]);
    }

    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    public Stream<String> getDescription(Character character) {
        return Stream.concat(
            Stream.of(
                delegate.getSchool().toString(),
                "<b>Casting time</b> " + getCastingTime(),
                "<b>Range</b> " + getRange(),
                "<b>Area of effect</b> " + getArea(),
                "<b>Duration</b> " + getDuration()),
            getEffects(character));
    }

    @Override
    public void choose(Character character) {
        throw new UnsupportedOperationException("Spells need spell caster context for choice.");
    }

    @Override
    public Element save(Document doc) {
        Element element = doc.createElement("spell");
        element.setTextContent(name());
        return element;
    }

    public static Spell load(Node node) {
        return Spell.valueOf(node.getTextContent());
    }

}
