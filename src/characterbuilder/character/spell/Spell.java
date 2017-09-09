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
import static characterbuilder.utils.StringUtils.namedRow;
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
        .effect("Create object of vgetable or mineral matter no larger than 5-foot cube.")
        .effect(table(header("Material", "Duration"),
            row("Vegetable", "1 day"),
            row("Stone or crystal", "12 hours"),
            row("Precious metals", "1 hour"),
            row("Gems", "10 minutes"),
            row("Adamantine or mithral", "1 minute")))
        .effect("Area +5-feet / extra spell slot level.")),
    DESTRUCTIVE_WAVE(spell(MagicSchool.EVOCATION, 5)
        .castingTime("1 action").components(VERBAL)
        .range("Self").area("30-foot radius").duration("Instantaneous")
        .effect("5d6 thunder damage +5d6 radiant or necrotic damage and knocked prone. "
            + "Con. save for half.")),
    DISPEL_EVIL_AND_GOOD(spell(MagicSchool.ABJURATION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").duration("up to 1 minute")
        .effect("Celestials, elementals, fey, fiends, and undead have disadvantage "
            + "on attacks against caster. Can end spell with following actions:")
        .effect(table(header("Action", "Effect"),
            row("Break enchantment", "As an action, touch ally to end charm, fear or possession"),
            row("Dismissal", "As an action, on melee spell attack hit, "
                + "sends creature back to home plane. Chr. save.")))),
    DOMINATE_PERSON(spell(MagicSchool.ENCHANTMENT, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("60 feet").area("1 humanoid").duration("up to 1 minute")
        .effect("Charm taget and control through telepathic link. "
            + "Wis. save with advantage if in combat with caster. "
            + "New save each type target takes damage. ")
        .effect("Duration 10 minutes at 6th level, 1 hour at 7th level, 8 hours at 8th+ level.")),
    DREAM(spell(MagicSchool.ILLUSION, 5)
        .castingTime("1 minute").components(VERBAL, SOMATIC, MATERIAL)
        .range("Special").area("Any known creature").duration("8 hours")
        .effect("Shapes target's dream. "
            + "Messenger can be caster or ally. "
            + "Messenger appears in target's dreams and converses. "
            + "Nightmare causes 3d6 psychic damage and prevents benefit from rest. "
            + "Wis. save has disadvantage if caster has portion of target's body.")),
    FLAME_STRIKE(spell(MagicSchool.EVOCATION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("60 feet").area("10-foot-radius, 40-foot-high cylinder ").duration("Instantaneous")
        .effect("4d6 fire damage and 4d6 radiant damage. Dex. save for half.")
        .effect("+1d6 fire or radian damage / extra spell slot level.")),
    GEAS(spell(MagicSchool.ENCHANTMENT, 5)
        .castingTime("1 minute").components(VERBAL)
        .range("60 feet").area("1 creature").duration("30 days")
        .effect("Force target to carry out service or action. "
            + "Wis. save. 5d10 psychic damage once each day for contrary acts.")
        .effect("Duration 1 year at 7th or 8th level, permanent at 9th level.")),
    GREATER_RESTORATION(spell(MagicSchool.ABJURATION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 creature").duration("Instantaneous")
        .effect("Reduce target's exhaustion level by one, or end one petrification, curse, ability "
            + "score reduction or maximum HP reduction effect. ")),
    HALLOW(spell(MagicSchool.EVOCATION, 5)
        .castingTime("24 hours").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("Up to 60-foot-radius").duration("Until dispelled")
        .effect("Celestials, elementals, fey, fiends, and undead cannot enter or charm, frighten or "
            + "possess creatures in area. "
            + "Choose an effect for selected creatures in area. Chr. save. ")
        .effect(table(
            namedRow("Courage", "Creatures cannot be frightened."),
            namedRow("Darkness", "Light cannot illuminate area."),
            namedRow("Daylight", "Bright light fills area."),
            namedRow("Energy Protection", "Resistance to one damage type except for "
                + "bludgeoning, piercing or slashing."),
            namedRow("Energy Vulnerability", "Vulnerability to one damage type except for "
                + "bludgeoning, piercing or slashing."),
            namedRow("Everlasting Rest", "Dead bodies cannot be turned into undead."),
            namedRow("Extradimensional Interference", "Creatures cannot move or travel using "
                + "teleportation or interplanar effects."),
            namedRow("Fear", "Creatures are frightened while in area."),
            namedRow("Silence", "No sound can enter into or emanate from area."),
            namedRow("Tongues", "Creature can communicate with any other creatures in area.")))),
    HOLD_MONSTER(spell(MagicSchool.ENCHANTMENT, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("90 feet").area("1 creature").duration("up to 1 minute")
        .effect("Paralysed. Wis. save. Save each turn to end spell.")
        .effect("+1 creature within 30 feet / extra spell slot level. ")),
    INSECT_PLAGUE(spell(MagicSchool.CONJURATION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("300 feet").area("20-foot-radius sphere ").duration("up to 10 minutes")
        .effect("Swarming, biting locusts fill area. Lightly obscured and difficult terrain. "
            + "4d10 piercing damage. Con. save for half damaage. ")
        .effect("+1d10 damage / extra spell slot level.")),
    LEGEND_LORE(spell(MagicSchool.DIVINATION, 5)
        .castingTime("10 minutes").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").duration("Instantaneous")
        .effect("Bring to mind a brief summary of the significant lore "
            + "about a name, person, place or object. Name or describe a person, place, or object.")),
    MASS_CURE_WOUNDS(spell(MagicSchool.EVOCATION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("60 feet").area("Up to 6 creatures in a 30-foot-radius sphere")
        .duration("Instantaneous")
        .effect("Regain 3d8+[spell_mod] HP.")
        .effect("+1d8 / extra spell slot level.")),
    MISLEAD(spell(MagicSchool.ILLUSION, 5)
        .castingTime("1 action").components(SOMATIC)
        .range("Self").duration("up to 1 hour")
        .effect("Become invisible and create illusory double. As a bonus action, use its senses.")),
    MODIFY_MEMORY(spell(MagicSchool.ENCHANTMENT, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("30 feet").area("1 creature").duration("up to 1 minute")
        .effect("Charmed. Wis. save with advantage if in combat with caster. "
            + "Change target's memory of an event within 24 hours. ")
        .effect("Age of memory is up to 7 days at 6th level, 30 days at 7th level, "
            + "1 year at 8th level, any time at 9th level. ")),
    PASSWALL(spell(MagicSchool.TRANSMUTATION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("30 feet").area("Solid wall up to 5 feet wide, 8 feet tall, 20 feet deep")
        .duration("1 hour")
        .effect("Passage appears allowing creatures to pass through.")),
    PLANAR_BINDING(spell(MagicSchool.ABJURATION, 5)
        .castingTime("1 hour").components(VERBAL, SOMATIC, MATERIAL)
        .range("60 feet").area("1 celestial, elemental, fey or fiend").duration("24 hours")
        .effect("Bind target to service. Chr. save. Target follows instructions. Reports completion. ")
        .effect("Duration 10 days (6th level), 30 days (7th level), 180 days (8th level), "
            + "year and a day (9th level).")),
    RAISE_DEAD(spell(MagicSchool.NECROMANCY, 5)
        .castingTime("1 hour").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 creature dead no longer than 10 days").duration("Instantaneous")
        .effect("Return target to life with 1 HP. Neutralises poisons and cures nonmagical diseases. "
            + "Target takes -4 penalty to attacks, saves, ability checks. Reduce by 1 each long rest.")),
    REINCARNATE(spell(MagicSchool.TRANSMUTATION, 5)
        .castingTime("1 hour").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 humanoid dead no longer than 10 days").duration("Instantaneous")
        .effect("Returns target to life in a new body with new racial traits. Roll 1d100.")
        .effect(table(
            namedRow("01-04", "Dragonborn"),
            namedRow("05-13", "Hill Dwarf"),
            namedRow("14-21", "Mountain Dwarf"),
            namedRow("22-25", "Dark Elf"),
            namedRow("26-34", "High Elf"),
            namedRow("35-42", "Wood Elf"),
            namedRow("43-46", "Forest Gnome"),
            namedRow("47-52", "Rock Gnome"),
            namedRow("53-56", "Half-Elf"),
            namedRow("57-60", "Half-Orc"),
            namedRow("61-68", "Lightfoot Halfling"),
            namedRow("69-76", "Stout Halfling"),
            namedRow("77-96", "Human"),
            namedRow("97-100", "Tiefling")))),
    SCRYING(spell(MagicSchool.DIVINATION, 5)
        .castingTime("10 minutes").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").area("1 creature or location").duration("up to 10 minutes")
        .effect("See and hear target. Wis. save. modified by knowledge of target. "
            + "Secondhand +5, Firsthand 0, Familiar -5, Picture -2, Possession -4, Bodypart -10. "
            + "Sensor moves with target and can be seen by creatures that can see invisible object.")),
    SEEMING(spell(MagicSchool.ILLUSION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("30 feet").area("Any number of creatures").duration("8 hours")
        .effect("Each target is given a chosen illusory appearance. Chr. save for unwilling targets. "
            + "Can make creatures appear taller, shorter, disguise appearance and clothing. ")
        .effect("Investigation check reveals illusion.")),
    SWIFT_QUIVER(spell(MagicSchool.TRANSMUTATION, 5)
        .castingTime("1 bonus action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("Quiver").duration("Concentration, up to 1 minute")
        .effect("As bonus action make two attacks with ranged weapon using ammunition from quiver.")),
    TELEKINESIS(spell(MagicSchool.TRANSMUTATION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("60 feet").duration("up to 10 minutes")
        .effect("As an action each round, target a creature or object up to 1,000 pounds. ")
        .effect(table(
            namedRow("Creature", "Ability check contested by target's Str. "
                + "Move target 30 feet in any direction."),
            namedRow("Object", "If worn or carried, ability check contested by owner's Str. "
                + "Move target 30 feet in any direction and control object.")))),
    TELEPATHIC_BOND(spell(MagicSchool.DIVINATION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("30 feet").area("8 willing creatures").duration("1 hour")
        .effect("Communicate telepathically between targets.")),
    TELEPORTATION_CIRCLE(spell(MagicSchool.CONJURATION, 5)
        .castingTime("1 minute").components(VERBAL, MATERIAL)
        .range("10 feet").area("1-foot-diameter circle").duration("1 round")
        .effect("Create portal to permanent teleportation circle on same plane. ")),
    TREE_STRIDE(spell(MagicSchool.CONJURATION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Self").area("1 kind of tree").duration("up to 1 minute")
        .effect("Each round, can enter trees of chosen kind as 5 feet of movement "
            + "and teleport to tree of same kind within 500 feet. ")),
    WALL_OF_FORCE(spell(MagicSchool.EVOCATION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("120 feet")
        .area("10-foot-radius sphere or hemisphere or 10 continuous 10-foot-squares")
        .duration("up to 10 minutes")
        .effect("Create invisible 1/4 inch wall. "
            + "Nothing can pass through wall and it is immune to damage.")),
    WALL_OF_STONE(spell(MagicSchool.EVOCATION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("120 feet")
        .area("10 continuous 10-foot-by-20-foot-squares")
        .duration("up to 10 minutes")
        .effect("Create 6-inch-thick stone wall joined to existing stone. "
            + "Spans greater than 20 feet halve area."
            + "Surrounded creatures Dex. save to use reaction to move up to speed. "
            + "Panels have AC 15 HP 30 per inch. "
            + "Wall becomes permanent if concentration maintained through duration. ")),
    ARCANE_GATE(spell(MagicSchool.CONJURATION, 6)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("500 feet").area("2 points").duration("Concentration, up to 10 minutes")
        .effect("Create teleportation portals between points. As bonus action rotate portals.")),
    BLADE_BARRIER(spell(MagicSchool.EVOCATION, 6)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("90 feet").area("20' high x5' thick wall\n100' straight or 60' diameter ringed")
        .duration("up to 10 minutes")
        .effect("Three-quarters cover. Difficult terrain. 6d10 slashing damage on entering. "
            + "Dex. save for half")),
    CHAIN_LIGHTNING(spell(MagicSchool.EVOCATION, 6)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("150 feet").area("1 creature or object").duration("Instantaneous")
        .effect("Bolt of lightning arcs to target and up to three others within 30 feet. "
            + "10d8 lightning damage. Dex. save for half.")
        .effect("+1 target / extra spell slot level.")),
    CIRCLE_OF_DEATH(spell(MagicSchool.NECROMANCY, 6)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("150 feet").area("60-foot-radius sphere").duration("Instantaneous")
        .effect("8d6 necrotic damage. Con. save for half damage.")
        .effect("+2d6 damage / extra spell slot level.")),
    CONJURE_FEY(spell(MagicSchool.CONJURATION, 6)
        .castingTime("1 minute").components(VERBAL, SOMATIC)
        .range("90 feet").area("Unoccupied space").duration("up to 1 hour")
        .effect("Summon a fey creature or spirit in form of beast of CR 6 or lower,."
            + "Friendly to caster. Becomes hostile if concentration broken.")
        .effect("+1 CR / extra spell slot level.")),
    CONTINGENCY(spell(MagicSchool.EVOCATION, 6)
        .castingTime("10 minutes").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").duration("10 days")
        .effect("Choose a spell of 5th level or lower, targeting self and casting time of 1 action. "
            + "Choose condition under which the contingent spell comes into effect.")),
    CREATE_UNDEAD(spell(MagicSchool.NECROMANCY, 6)
        .castingTime("1 minute").components(VERBAL, SOMATIC, MATERIAL)
        .range("10 feet").area("Up to 3 humanoid corpses").duration("Instantaneous")
        .effect("Targets becomes ghouls under caster's control. "
            + "Each turn, as a bonus action, control targets wihtin 120 feet. "
            + "Ghouls under control for 24 hours.")
        .effect("At 7th level, create 4 ghouls; at 8th level, create 4 ghouls or 2 ghasts or wights; "
            + "at 9th level, creatue 6 ghouls, 3 ghasts or wights, or 2 mummies. ")),
    DISINTEGRATE(spell(MagicSchool.TRANSMUTATION, 6)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("60 feet").area("1 creature, object or magical creation").duration("Instantaneous")
        .effect("10d6+40 force damage to creatures. Dex. save. At 0 HP target disintegrates. "
            + "Up to 10-foot-cube of objects and magical creations automatically destroyed. ")
        .effect("+3d6 / extra spell slot level.")),
    EYEBITE(spell(MagicSchool.NECROMANCY, 6)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Self").area("1 creature within 60 feet").duration("up to 1 minute")
        .effect("Target a new creature each turn. Wis. save. Choose effect:")
        .effect(table(
            namedRow("Asleep", "Target falls unconscious until awoken."),
            namedRow("Panicked", "Target is afraid and moves away each turn until "
                + "it is 60 feet from caster."),
            namedRow("Sickened", "Target has disadvantage on attack and ability checks. "
                + "Makes a Wis. save at end of each turn.")))),
    FIND_THE_PATH(spell(MagicSchool.DIVINATION, 6)
        .castingTime("1 minute").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").area("Familiar location").duration("up to 1 day")
        .effect("Know distance and direction to target. "
            + "Can automatically choose shortest route to target.")),
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
        .range("Touch").area("Up to 2,500 square feet").duration("24 hours")
        .effect("Creates following effects:")
        .effect(table(
            namedRow("Corridors", "Heavily obscured. At branches, 50% chance of thinking direction "
                + "is opposite."),
            namedRow("Doors", "Magically locked as per <em>Arcane Lock</em> spell. "
                + "Up to 10 doors appear as plan sections of wall."),
            namedRow("Stairs", "Filled with webs as per <em>Web</em> spell.")))
        .effect("Can choose one of the following effects:")
        .effect(table(
            namedRow("Dancing Lights", "Place dancing lights in 4 corridors."),
            namedRow("Magic Mouth", "Place magic mouths in 2 locations."),
            namedRow("Stinking Cloud", "Place stinking cloud in 2 locations."),
            namedRow("Wind", "Place a constant gust of wind in 1 corridor or room."),
            namedRow("Suggestion", "Place a suggestion in one area of up to 5-foot sqqure.")))),
    HARM(spell(MagicSchool.NECROMANCY, 6)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("60 feet").area("1 creature").duration("Instantaneous")
        .effect("4d6 necrotic damage and reduce maximum HP. "
            + "Con. save for half damage and avoid reduction. "
            + "Cannot reduce HP below 1. Ended by remove disease.")),
    HEAL(spell(MagicSchool.EVOCATION, 6)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("60 feet").area("1 creature").duration("Instantaneous")
        .effect("Regain 70 HP, ends blindness, deafness and any diseases.")
        .effect("+10 HP / extra spell slot level. ")),
    HEROES_FEAST(spell(MagicSchool.CONJURATION, 6)
        .castingTime("10 minutes").components(VERBAL, SOMATIC, MATERIAL)
        .range("30 feet").duration("Instantaneous")
        .effect("Create a feast for up to 12 creatures. Feast takes 1 hour to consume. "
            + "Cured of diseases and poisons, immune to poison and fear, "
            + "gain advantage on Wis. saves, +2d10 temporary HP. Effects last for 24 hours. ")),
    INSTANT_SUMMONS(spell(MagicSchool.CONJURATION, 6).ritual()
        .castingTime("1 minute").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 object up to 10lb").duration("Until dispelled")
        .effect("Transports object or learn who and where holder is.")),
    IRRESISTIBLE_DANCE(spell(MagicSchool.ENCHANTMENT, 6)
        .castingTime("1 action").components(VERBAL)
        .range("30 feet").area("1 creature").duration("up to 1 minute")
        .effect("Target begins a comic dance. Remains in place and has disadvantage on attacks and "
            + "Dex. saves. Other creatures have advantage on attacks at target. "
            + "As an action, target can make Wis. save to end spell.")),
    MAGIC_JAR(spell(MagicSchool.NECROMANCY, 6)
        .castingTime("1 minute").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").duration("Until dispelled")
        .effect("Caster's soul enters container. Can project soul up to 100 feet to return to "
            + "body or possess a humanoid's body.  Chr. save. On possession, retain alignment,"
            + "Int. Wis. and Chr. scores and class features. "
            + "If host dies, Chr. save to return to container.")),
    MASS_SUGGESTION(spell(MagicSchool.ENCHANTMENT, 6)
        .castingTime("1 action").components(VERBAL, MATERIAL)
        .range("60 feet").area("Up to 12 creatures").duration("24 hours")
        .effect("Magically influence targets through a simple suggestion. Wis. save. "
            + "Ends when command has been completed or caster or ally damages target. ")
        .effect("Duration 10 days at 7th level, 30 days at 8th level, year and a day at 9th level.")),
    MOVE_EARTH(spell(MagicSchool.TRANSMUTATION, 6)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("120 feet").area("Terrain up to 40-foot square").duration("up to 2 hours")
        .effect("Reshape dirt, sand, or clay in the area up to half the area's largest dimension. "
            + "Change takes 10 minutes.")),
    PLANAR_ALLY(spell(MagicSchool.CONJURATION, 6)
        .castingTime("10 minutes").components(VERBAL, SOMATIC)
        .range("60 feet").area("Otherworldly entity").duration("Instantaneous")
        .effect("Summon creature to request service.")),
    PROGRAMMED_ILLUSION(spell(MagicSchool.ILLUSION, 6)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("120 feet").area("Up to 30-foot cube").duration("Until dispelled")
        .effect("Create an illusion of object, creature, or other visible phenomenon. "
            + "Activates when chosen visual or audible condition occurs and performs a chosen "
            + "set of actions for up to 5 minutes. After performance illusion is dormant for 10 minutes. "
            + "As an action, Investigation check reveals illusion.")),
    SUNBEAM(spell(MagicSchool.EVOCATION, 6)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").area("60-foot line").duration("up to 1 minute")
        .effect("6d8 radiant damage and blinded for 1 turn. Con. save for half damage. "
            + "Undead and oozes have disadvantage on save. As an action each turn create a new line. "
            + "Shed bright light in 30-foot radius.")),
    TRANSPORT_VIA_PLANTS(spell(MagicSchool.CONJURATION, 6)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("10 feet").area("1 large plant").duration("1 round")
        .effect("Creates link between target plant and any other plant on same plane. "
            + "Any creature can teleport to destination plan using 5 feet of movement.")),
    TRUE_SEEING(spell(MagicSchool.DIVINATION, 6)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("1 ally").duration("1 hour")
        .effect("Target has true sight, notices secret doors hidden by magic, see into Ethereal plane "
            + "to range of 120 feet.")),
    WALL_OF_ICE(spell(MagicSchool.EVOCATION, 6)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("120 feet")
        .area("Up to 10-foot-radius hemisphere or sphere or 10 connected 10-foot-square panels")
        .duration("up to 10 minutes")
        .effect("For 1-foot-thick wall of ice on a solid surface. "
            + "If created on creature, 10d6 cold damage. Dex. save for half damage. "
            + "Wall has AC 12 HP 30 per 10-foot section and vulnerable to fire. "
            + "At 0 HP section is destroyed leaving frigid air. "
            + "5d6 cold damage to creatures passing through. Con. save for half damage. ")
        .effect("+2d6 damage on appearance and +1d6 passing through / extra spell slot level.")),
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
        .range("5 feet").area("Caster and up to 5 allies within 5 feet")
        .duration("Instantaneous")
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
