package characterbuilder.character.spell;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Value;
import static characterbuilder.character.attribute.Value.gp;
import characterbuilder.character.characterclass.wizard.MagicSchool;
import characterbuilder.character.choice.Option;
import static characterbuilder.character.spell.SpellComponent.MATERIAL;
import static characterbuilder.character.spell.SpellComponent.SOMATIC;
import static characterbuilder.character.spell.SpellComponent.VERBAL;
import static characterbuilder.character.spell.SpellDelegate.spell;
import characterbuilder.utils.StringUtils;
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
        .effect("Change appearance. Invesigation check against DC[$spell_dc].")),
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
        .effect("Target incapacitated. Wis. save DC[$spell_dc]. "
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
        .effect("Create a visual image. Investigation check DC[$spell_dc].")),
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
        .effect("Difficult terrain. 2d4 piercing damage for each 5 feet of travel.\nPerception check "
            + "DC[$spell_dc] to recognize.")),
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
        .effect("Restrain creatures. Dex. save. Str. check DC[$spell_dc] to break free.")),
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
        .effect("Target charmed. Wis. save DC[$spell_dc].")
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
        .effect("Enemies within 10 feet take 20 radiant damage. Dex. save DC[$spell_dc] for half.")
        .effect("Guardian vanishes when it has dealt a total of 60 damage.")),
    HALLUCINATORY_TERRAIN(spell(MagicSchool.ILLUSION, 4)
        .castingTime("10 minutes").components(VERBAL, SOMATIC, MATERIAL)
        .range("300 feet").area("Terrain in 150-foot cube").duration("24 hours")
        .effect("Area looks, sounds, and smells like a chosen natural terrain.")
        .effect("Investigation check DC[$spell_dc] to disbelieve on examination.")),
    ICE_STORM(spell(MagicSchool.EVOCATION, 4)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("300 feet").area("20-foot-radius, 40-foot-high cylinder").duration("Instantaneous")
        .effect("Creatures in area take 2d8 bludgeoning and 4d6 cold damage. "
            + "Dex. save DC[$spell_dc] for half. Area become difficult terrain for 1 turn.")
        .effect("+1d8 bludgeoning damage / extra level.")),
    LOCATE_CREATURE(spell(MagicSchool.DIVINATION, 4)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").duration("up to 1 hour")
        .effect("Sense direction to a specific creature or type of creature within 1,000 feet.")
        .effect("Blocked by running water at least 10 feet wide.")),
    PHANTASMAL_KILLER(spell(MagicSchool.ILLUSION, 4)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("120 feet").area("1 creature").duration("up to 1 minute")
        .effect("Target becomes frightened. Wis. save DC[$spell_dc]. "
            + "Each turn saves to end spell or takes 4d10 psychic damage.")
        .effect("+1d10 damage / extra level.")),
    POLYMORPH(spell(MagicSchool.TRANSMUTATION, 4)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("60 feet").area("1 creature").duration("up to 1 hour")
        .effect("Transforms target into a new form with same or less CR. "
            + "Wis. save DC[$spell_dc] if target unwilling.")),
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
            + "Dex. save DC[$spell_dc] for unwilling targets.")
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
        .range("Touch").duration("up to 1 hour")
        .effect("This spell turns the flesh of a willing creature you touch as hard as stone. Until the "
            + "spell ends, the target has resistance to nonmagical bludgeoning, piercing, and "
            + "slashing damage.")),
    STONE_SHAPE(spell(MagicSchool.TRANSMUTATION, 4)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").duration("Instantaneous")
        .effect("You touch a stone object of Medium size or smaller or a section of stone no more than "
            + "5 feet in any dimension and form it into any shape that suits your purpose. So, for "
            + "example, you could shape a large rock into a weapon, idol, or coffer, or make a small "
            + "passage through a wall, as long as the wall is less than 5 feet thick. You could also "
            + "shape a stone door or its frame to seal the door shut. The object you create can have "
            + "up to two hinges and a latch, but finer mechanical detail isn't possible.")),
    WALL_OF_FIRE(spell(MagicSchool.EVOCATION, 4)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("120 feet").duration("up to 1 minute")
        .effect("You create a wall of fire on a solid surface within range. You can make the wall up to "
            + "60 feet long, 20 feet high, and 1 foot thick, or a ringed wall up to 20 feet in "
            + "diameter, 20 feet high, and 1 foot thick. The wall is opaque and lasts for the "
            + "duration. When the wall appears, each creature within its area must make a Dexterity "
            + "saving throw. On a failed save, a creature takes 5d8 fire damage, or half as much "
            + "damage on a successful save. One side of the wall, selected by you when you cast this "
            + "spell, deals 5d8 fire damage to each creature that ends its turn within 10 feet of "
            + "that side or inside the wall. A creature takes the same damage when it enters the wall "
            + "for the first time on a turn or ends its turn there. The other side of the wall deals "
            + "no damage. At Higher Levels. When you cast this spell using a spell slot of 5th level "
            + "or higher, the damage increases by 1d8 for each slot level above 4th.")),
    ANIMATE_OBJECTS(spell(MagicSchool.TRANSMUTATION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("120 feet").duration("up to 1 minute")
        .effect("Objects come to life at your command. Choose up to ten nonmagical objects within range "
            + "that are not being worn or carried. Medium targets count as two objects, Large targets "
            + "count as four objects, Huge targets count as eight objects. You can't animate any "
            + "object larger than Huge. Each target animates and becomes a creature under your "
            + "control until the spell ends or until reduced to 0 hit points. As a bonus action, you "
            + "can mentally command any creature you made with this spell if the creature is within "
            + "500 feet of you (if you control multiple creatures, you can command any or all of them "
            + "at the same time, issuing the same command to each one). You decide what action the "
            + "creature will take and where it will move during its next turn, or you can issue a "
            + "general command, such as to guard a particular chamber or corridor. If you issue no "
            + "commands, the creature only defends itself against hostile creatures. Once given an "
            + "order, the creature continues to follow it until its task is complete. Animated Object "
            + "Statistics Size HP AC Str Dex Attack Tiny 20 18 4 18 +8 to hit, 1d4 + 4 damage Small "
            + "25 16 6 14 +6 to hit, 1d8 + 2 damage Medium 40 13 10 12 +5 to hit, 2d6 + 1 damage "
            + "Large 50 10 14 10 +6 to hit, 2d10 + 2 damage Huge 80 10 18 6 +8 to hit, 2d12 + 4 "
            + "damage An animated object is a construct with AC, hit points, attacks, Strength, and "
            + "Dexterity determined by its size. Its Constitution is 10 and its Intelligence and "
            + "Wisdom are 3, and its Charisma is 1. Its speed is 30 feet; if the object lacks legs or "
            + "other appendages it can use for locomotion, it instead has a flying speed of 30 feet "
            + "and can hover. If the object is securely attached to a surface or a larger object, "
            + "such as a chain bolted to a wall, its speed is 0. It has blindsight with a radius of "
            + "30 feet and is blind beyond that distance. When the animated object drops to 0 hit "
            + "points, it reverts to its original object form, and any remaining damage carries over "
            + "to its original object form. If you command an object to attack, it can make a single "
            + "melee attack against a creature within 5 feet of it. It makes a slam attack with an "
            + "attack bonus and bludgeoning damage determined by its size. The GM might rule that a "
            + "specific object inflicts slashing or piercing damage based on its form. At Higher "
            + "Levels. If you cast this spell using a spell slot of 6th level or higher, you can "
            + "animate two additional objects for each slot level above 5th.")),
    ANTILIFE_SHELL(spell(MagicSchool.ABJURATION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Self (10-foot radius)").duration("up to 1 hour")
        .effect("A shimmering barrier extends out from you in a 10- foot radius and moves with you, "
            + "remaining centered on you and hedging out creatures other than undead and constructs. "
            + "The barrier lasts for the duration. The barrier prevents an affected creature from "
            + "passing or reaching through. An affected creature can cast spells or make attacks with "
            + "ranged or reach weapons through the barrier. If you move so that an affected creature "
            + "is forced to pass through the barrier, the spell ends.")),
    ARCANE_HAND(spell(MagicSchool.EVOCATION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("120 feet").duration("up to 1 minute")
        .effect("Create a hand of force. AC 20 HP [$hp] Str. 26 Dex. 10.\nBonus action move hand up to "
            + "60 feet and attack using contested Str. Attack for 4d8 damage, push target "
            + "[5+5*$spell_mod] feet, grapple target and then crush for 2d6+[$spell_mod] or provide "
            + "half cover and prevent movement towards caster.\nAttack +2d8 and crush +2d6 / extra level.")),
    AWAKEN(spell(MagicSchool.TRANSMUTATION, 5)
        .castingTime("8 hours").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").duration("Instantaneous")
        .effect("After spending the casting time tracing magical pathways within a precious gemstone, "
            + "you touch a Huge or smaller beast or plant. The target must have either no "
            + "Intelligence score or an Intelligence of 3 or less. The target gains an Intelligence "
            + "of 10. The target also gains the ability to speak one language you know. If the target "
            + "is a plant, it gains the ability to move its limbs, roots, vines, creepers, and so "
            + "forth, and it gains senses similar to a human's. Your GM chooses statistics "
            + "appropriate for the awakened plant, such as the statistics for the awakened shrub or "
            + "the awakened tree. The awakened beast or plant is charmed by you for 30 days or until "
            + "you or your companions do anything harmful to it. When the charmed condition ends, the "
            + "awakened creature chooses whether to remain friendly to you, based on how you treated "
            + "it while it was charmed.")),
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
        .range("120 feet").duration("up to 10 minutes")
        .effect("You create a 20-foot-radius sphere of poisonous, yellow-green fog centered on a point "
            + "you choose within range. The fog spreads around corners. It lasts for the duration or "
            + "until strong wind disperses the fog, ending the spell. Its area is heavily obscured. "
            + "When a creature enters the spell's area for the first time on a turn or starts its "
            + "turn there, that creature must make a Constitution saving throw. The creature takes "
            + "5d8 poison damage on a failed save, or half as much damage on a successful one. "
            + "Creatures are affected even if they hold their breath or don't need to breathe. The "
            + "fog moves 10 feet away from you at the start of each of your turns, rolling along the "
            + "surface of the ground. The vapors, being heavier than air, sink to the lowest level of "
            + "the land, even pouring down openings. At Higher Levels. When you cast this spell using "
            + "a spell slot of 6th level or higher, the damage increases by 1d8 for each slot level "
            + "above 5th.")),
    COMMUNE(spell(MagicSchool.DIVINATION, 5).ritual()
        .castingTime("1 minute").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").duration("1 minute")
        .effect("You contact your deity or a divine proxy and ask up to three questions that can be "
            + "answered with a yes or no. You must ask your questions before the spell ends. You "
            + "receive a correct answer for each question. Divine beings aren't necessarily "
            + "omniscient, so you might receive 'unclear' as an answer if a question pertains to "
            + "information that lies beyond the deity's knowledge. In a case where a one-word answer "
            + "could be misleading or contrary to the deity's interests, the GM might offer a short "
            + "phrase as an answer instead. If you cast the spell two or more times before finishing "
            + "your next long rest, there is a cumulative 25 percent chance for each casting after "
            + "the first that you get no answer. The GM makes this roll in secret.")),
    COMMUNE_WITH_NATURE(spell(MagicSchool.DIVINATION, 5).ritual()
        .castingTime("1 minute").components(VERBAL, SOMATIC)
        .range("Self").duration("Instantaneous")
        .effect("You briefly become one with nature and gain knowledge of the surrounding territory. In "
            + "the outdoors, the spell gives you knowledge of the land within 3 miles of you. In "
            + "caves and other natural underground settings, the radius is limited to 300 feet. The "
            + "spell doesn't function where nature has been replaced by construction, such as in "
            + "dungeons and towns. You instantly gain knowledge of up to three facts of your choice "
            + "about any of the following subjects as they relate to the area: terrain and bodies of "
            + "water prevalent plants, minerals, animals, or peoples powerful celestials, fey, "
            + "fiends, elementals, or undead influence from other planes of existence buildings For "
            + "example, you could determine the location of powerful undead in the area, the location "
            + "of major sources of safe drinking water, and the location of any nearby towns.")),
    CONE_OF_COLD(spell(MagicSchool.EVOCATION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").area("60-foot cone").duration("Instantaneous")
        .effect("8d8 cold damage. Con. save for half.")),
    CONJURE_ELEMENTAL(spell(MagicSchool.CONJURATION, 5)
        .castingTime("1 minute").components(VERBAL, SOMATIC, MATERIAL)
        .range("90 feet").duration("up to 1 hour")
        .effect("You call forth an elemental servant. Choose an area of air, earth, fire, or water that "
            + "fills a 10-foot cube within range. An elemental of challenge rating 5 or lower "
            + "appropriate to the area you chose appears in an unoccupied space within 10 feet of it. "
            + "For example, a fire elemental emerges from a bonfire, and an earth elemental rises up "
            + "from the ground. The elemental disappears when it drops to 0 hit points or when the "
            + "spell ends. The elemental is friendly to you and your companions for the duration. "
            + "Roll initiative for the elemental, which has its own turns. It obeys any verbal "
            + "commands that you issue to it (no action required by you). If you don't issue any "
            + "commands to the elemental, it defends itself from hostile creatures but otherwise "
            + "takes no actions. If your concentration is broken, the elemental doesn't disappear. "
            + "Instead, you lose control of the elemental, it becomes hostile toward you and your "
            + "companions, and it might attack. An uncontrolled elemental can't be dismissed by you, "
            + "and it disappears 1 hour after you summoned it. The GM has the elemental's statistics. "
            + "At Higher Levels. When you cast this spell using a spell slot of 6th level or higher, "
            + "the challenge rating increases by 1 for each slot level above 5th.")),
    CONJURE_VOLLEY(spell(MagicSchool.CONJURATION, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("150 feet").area("40-foot radius, 20-foot high cylinder").duration("Instantaneous")
        .effect("8d8 damage. Dex. save for half.")),
    CONTACT_OTHER_PLANE(spell(MagicSchool.DIVINATION, 5).ritual()
        .castingTime("1 minute").components(VERBAL)
        .range("Self").duration("1 minute")
        .effect("You mentally contact a demigod, the spirit of a long- dead sage, or some other "
            + "mysterious entity from another plane. Contacting this extraplanar intelligence can "
            + "strain or even break your mind. When you cast this spell, make a DC 15 Intelligence "
            + "saving throw. On a failure, you take 6d6 psychic damage and are insane until you "
            + "finish a long rest. While insane, you can't take actions, can't understand what other "
            + "creatures say, can't read, and speak only in gibberish. A greater restoration spell "
            + "cast on you ends this effect. On a successful save, you can ask the entity up to five "
            + "questions. You must ask your questions before the spell ends. The GM answers each "
            + "question with one word, such as 'yes,' 'no,' 'maybe,' 'never,' 'irrelevant,' or "
            + "'unclear' (if the entity doesn't know the answer to the question). If a one-word "
            + "answer would be misleading, the GM might instead offer a short phrase as an answer.")),
    CONTAGION(spell(MagicSchool.NECROMANCY, 5)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Touch").duration("7 days")
        .effect("Your touch inflicts disease. Make a melee spell attack against a creature within your "
            + "reach. On a hit, you afflict the creature with a disease of your choice from any of "
            + "the ones described below. At the end of each of the target's turns, it must make a "
            + "Constitution saving throw. After failing three of these saving throws, the disease's "
            + "effects last for the duration, and the creature stops making these saves. After "
            + "succeeding on three of these saving throws, the creature recovers from the disease, "
            + "and the spell ends. Since this spell induces a natural disease in its target, any "
            + "effect that removes a disease or otherwise ameliorates a disease's effects apply to "
            + "it. Blinding Sickness. Pain grips the creature's mind, and its eyes turn milky white. "
            + "The creature has disadvantage on Wisdom checks and Wisdom saving throws and is "
            + "blinded. Filth Fever. A raging fever sweeps through the creature's body. The creature "
            + "has disadvantage on Strength checks, Strength saving throws, and attack rolls that use "
            + "Strength. Flesh Rot. The creature's flesh decays. The creature has disadvantage on "
            + "Charisma checks and vulnerability to all damage. Mindfire. The creature's mind becomes "
            + "feverish. The creature has disadvantage on Intelligence checks and Intelligence saving "
            + "throws, and the creature behaves as if under the effects of the confusion spell during "
            + "combat. Seizure. The creature is overcome with shaking. The creature has disadvantage "
            + "on Dexterity checks, Dexterity saving throws, and attack rolls that use Dexterity. "
            + "Slimy Doom. The creature begins to bleed uncontrollably. The creature has disadvantage "
            + "on Constitution checks and Constitution saving throws. In addition, whenever the "
            + "creature takes damage, it is stunned until the end of its next turn.")),
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
        .effect("5d6 thunder damage +5d6 radiant or necrotic damage and knocked prone. Con. save for half.")),
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
        .range("120 feet").duration("up to 10 minutes")
        .effect("You create a wall of tough, pliable, tangled brush bristling with needle-sharp thorns. "
            + "The wall appears within range on a solid surface and lasts for the duration. You "
            + "choose to make the wall up to 60 feet long, 10 feet high, and 5 feet thick or a circle "
            + "that has a 20-foot diameter and is up to 20 feet high and 5 feet thick. The wall "
            + "blocks line of sight. When the wall appears, each creature within its area must make a "
            + "Dexterity saving throw. On a failed save, a creature takes 7d8 piercing damage, or "
            + "half as much damage on a successful save. A creature can move through the wall, albeit "
            + "slowly and painfully. For every 1 foot a creature moves through the wall, it must "
            + "spend 4 feet of movement. Furthermore, the first time a creature enters the wall on a "
            + "turn or ends its turn there, the creature must make a Dexterity saving throw. It takes "
            + "7d8 slashing damage on a failed save, or half as much damage on a successful one. At "
            + "Higher Levels. When you cast this spell using a spell slot of 7th level or higher, "
            + "both types of damage increase by 1d8 for each slot level above 6th.")),
    WIND_WALK(spell(MagicSchool.TRANSMUTATION, 6)
        .castingTime("1 minute").components(VERBAL, SOMATIC, MATERIAL)
        .range("30 feet").duration("8 hours")
        .effect("You and up to ten willing creatures you can see within range assume a gaseous form for "
            + "the duration, appearing as wisps of cloud. While in this cloud form, a creature has a "
            + "flying speed of 300 feet and has resistance to damage from nonmagical weapons. The "
            + "only actions a creature can take in this form are the Dash action or to revert to its "
            + "normal form. Reverting takes 1 minute, during which time a creature is incapacitated "
            + "and can't move. Until the spell ends, a creature can revert to cloud form, which also "
            + "requires the 1-minute transformation. If a creature is in cloud form and flying when "
            + "the effect ends, the creature descends 60 feet per round for 1 minute until it lands, "
            + "which it does safely. If it can't land after 1 minute, the creature falls the "
            + "remaining distance.")),
    WORD_OF_RECALL(spell(MagicSchool.CONJURATION, 6)
        .castingTime("1 action").components(VERBAL)
        .range("5 feet").duration("Instantaneous")
        .effect("You and up to five willing creatures within 5 feet of you instantly teleport to a "
            + "previously designated sanctuary. You and any creatures that teleport with you appear "
            + "in the nearest unoccupied space to the spot you designated when you prepared your "
            + "sanctuary (see below). If you cast this spell without first preparing a sanctuary, the "
            + "spell has no effect. You must designate a sanctuary by casting this spell within a "
            + "location, such as a temple, dedicated to or strongly linked to your deity. If you "
            + "attempt to cast the spell in this manner in an area that isn't dedicated to your "
            + "deity, the spell has no effect.")),
    ARCANE_SWORD(spell(MagicSchool.EVOCATION, 7)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("60 feet").area("Unoccupied Space").duration("up to 1 minute")
        .effect("Melee spell attack +[$spell_mod]. 3d10 force damage. "
            + "As bonus action, move up to 20 feet and attack.")),
    CONJURE_CELESTIAL(spell(MagicSchool.CONJURATION, 7)
        .castingTime("1 minute").components(VERBAL, SOMATIC)
        .range("90 feet").duration("up to 1 hour")
        .effect("You summon a celestial of challenge rating 4 or lower, which appears in an unoccupied "
            + "space that you can see within range. The celestial disappears when it drops to 0 hit "
            + "points or when the spell ends. The celestial is friendly to you and your companions "
            + "for the duration. Roll initiative for the celestial, which has its own turns. It obeys "
            + "any verbal commands that you issue to it (no action required by you), as long as they "
            + "don't violate its alignment. If you don't issue any commands to the celestial, it "
            + "defends itself from hostile creatures but otherwise takes no actions. The GM has the "
            + "celestial's statistics. At Higher Levels. When you cast this spell using a 9th-level "
            + "spell slot, you summon a celestial of challenge rating 5 or lower.")),
    DELAYED_BLAST_FIREBALL(spell(MagicSchool.EVOCATION, 7)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("150 feet").duration("up to 1 minute")
        .effect("A beam of yellow light flashes from your pointing finger, then condenses to linger at "
            + "a chosen point within range as a glowing bead for the duration. When the spell ends, "
            + "either because your concentration is broken or because you decide to end it, the bead "
            + "blossoms with a low roar into an explosion of flame that spreads around corners. Each "
            + "creature in a 20-foot-radius sphere centered on that point must make a Dexterity "
            + "saving throw. A creature takes fire damage equal to the total accumulated damage on a "
            + "failed save, or half as much damage on a successful one. The spell's base damage is "
            + "12d6. If at the end of your turn the bead has not yet detonated, the damage increases "
            + "by 1d6. If the glowing bead is touched before the interval has expired, the creature "
            + "touching it must make a Dexterity saving throw. On a failed save, the spell ends "
            + "immediately, causing the bead to erupt in flame. On a successful save, the creature "
            + "can throw the bead up to 40 feet. When it strikes a creature or a solid object, the "
            + "spell ends, and the bead explodes. The fire damages objects in the area and ignites "
            + "flammable objects that aren't being worn or carried. At Higher Levels. When you cast "
            + "this spell using a spell slot of 8th level or higher, the base damage increases by 1d6 "
            + "for each slot level above 7th.")),
    DIVINE_WORD(spell(MagicSchool.EVOCATION, 7)
        .castingTime("1 bonus action").components(VERBAL)
        .range("30 feet").duration("Instantaneous")
        .effect("You utter a divine word, imbued with the power that shaped the world at the dawn of "
            + "creation. Choose any number of creatures you can see within range. Each creature that "
            + "can hear you must make a Charisma saving throw. On a failed save, a creature suffers "
            + "an effect based on its current hit points: 50 hit points or fewer: deafened for 1 "
            + "minute 40 hit points or fewer: deafened and blinded for 10 minutes 30 hit points or "
            + "fewer: blinded, deafened, and stunned for 1 hour 20 hit points or fewer: killed "
            + "instantly Regardless of its current hit points, a celestial, an elemental, a fey, or a "
            + "fiend that fails its save is forced back to its plane of origin (if it isn't there "
            + "already) and can't return to your current plane for 24 hours by any means short of a "
            + "wish spell.")),
    ETHEREALNESS(spell(MagicSchool.TRANSMUTATION, 7)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Self").duration("Up to 8 hours")
        .effect("You step into the border regions of the Ethereal Plane, in the area where it overlaps "
            + "with your current plane. You remain in the Border Ethereal for the duration or until "
            + "you use your action to dismiss the spell. During this time, you can move in any "
            + "direction. If you move up or down, every foot of movement costs an extra foot. You can "
            + "see and hear the plane you originated from, but everything there looks gray, and you "
            + "can't see anything more than 60 feet away. While on the Ethereal Plane, you can only "
            + "affect and be affected by other creatures on that plane. Creatures that aren't on the "
            + "Ethereal Plane can't perceive you and can't interact with you, unless a special "
            + "ability or magic has given them the ability to do so. You ignore all objects and "
            + "effects that aren't on the Ethereal Plane, allowing you to move through objects you "
            + "perceive on the plane you originated from. When the spell ends, you immediately return "
            + "to the plane you originated from in the spot you currently occupy. If you occupy the "
            + "same spot as a solid object or creature when this happens, you are immediately shunted "
            + "to the nearest unoccupied space that you can occupy and take force damage equal to "
            + "twice the number of feet you are moved. This spell has no effect if you cast it while "
            + "you are on the Ethereal Plane or a plane that doesn't border it, such as one of the "
            + "Outer Planes. At Higher Levels. When you cast this spell using a spell slot of 8th "
            + "level or higher, you can target up to three willing creatures (including you) for each "
            + "slot level above 7th. The creatures must be within 10 feet of you when you cast the spell.")),
    FINGER_OF_DEATH(spell(MagicSchool.NECROMANCY, 7)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("60 feet").duration("Instantaneous")
        .effect("You send negative energy coursing through a creature that you can see within range, "
            + "causing it searing pain. The target must make a Constitution saving throw. It takes "
            + "7d8 + 30 necrotic damage on a failed save, or half as much damage on a successful one. "
            + "A humanoid killed by this spell rises at the start of your next turn as a zombie that "
            + "is permanently under your command, following your verbal orders to the best of its "
            + "ability.")),
    FIRE_STORM(spell(MagicSchool.EVOCATION, 7)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("150 feet").duration("Instantaneous")
        .effect("A storm made up of sheets of roaring flame appears in a location you choose within "
            + "range. The area of the storm consists of up to ten 10-foot cubes, which you can "
            + "arrange as you wish. Each cube must have at least one face adjacent to the face of "
            + "another cube. Each creature in the area must make a Dexterity saving throw. It takes "
            + "7d10 fire damage on a failed save, or half as much damage on a successful one. The "
            + "fire damages objects in the area and ignites flammable objects that aren't being worn "
            + "or carried. If you choose, plant life in the area is unaffected by this spell.")),
    FORCECAGE(spell(MagicSchool.EVOCATION, 7)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("100 feet").duration("1 hour")
        .effect("An immobile, invisible, cube-shaped prison composed of magical force springs into "
            + "existence around an area you choose within range. The prison can be a cage or a solid "
            + "box, as you choose. A prison in the shape of a cage can be up to 20 feet on a side and "
            + "is made from 1/2-inch diameter bars spaced 1/2 inch apart. A prison in the shape of a "
            + "box can be up to 10 feet on a side, creating a solid barrier that prevents any matter "
            + "from passing through it and blocking any spells cast into or out from the area. When "
            + "you cast the spell, any creature that is completely inside the cage's area is trapped. "
            + "Creatures only partially within the area, or those too large to fit inside the area, "
            + "are pushed away from the center of the area until they are completely outside the "
            + "area. A creature inside the cage can't leave it by nonmagical means. If the creature "
            + "tries to use teleportation or interplanar travel to leave the cage, it must first make "
            + "a Charisma saving throw. On a success, the creature can use that magic to exit the "
            + "cage. On a failure, the creature can't exit the cage and wastes the use of the spell "
            + "or effect. The cage also extends into the Ethereal Plane, blocking ethereal travel. "
            + "This spell can't be dispelled by dispel magic.")),
    MAGNIFICENT_MANSION(spell(MagicSchool.CONJURATION, 7)
        .castingTime("1 minute").components(VERBAL, SOMATIC, MATERIAL)
        .range("300 feet").duration("24 hours")
        .effect("You conjure an extradimensional dwelling in range that lasts for the duration. You "
            + "choose where its one entrance is located. The entrance shimmers faintly and is 5 feet "
            + "wide and 10 feet tall. You and any creature you designate when you cast the spell can "
            + "enter the extradimensional dwelling as long as the portal remains open. You can open "
            + "or close the portal if you are within 30 feet of it. While closed, the portal is "
            + "invisible. Beyond the portal is a magnificent foyer with numerous chambers beyond. The "
            + "atmosphere is clean, fresh, and warm. You can create any floor plan you like, but the "
            + "space can't exceed 50 cubes, each cube being 10 feet on each side. The place is "
            + "furnished and decorated as you choose. It contains sufficient food to serve a "
            + "nine-course banquet for up to 100 people. A staff of 100 near-transparent servants "
            + "attends all who enter. You decide the visual appearance of these servants and their "
            + "attire. They are completely obedient to your orders. Each servant can perform any task "
            + "a normal human servant could perform, but they can't attack or take any action that "
            + "would directly harm another creature. Thus the servants can fetch things, clean, mend, "
            + "fold clothes, light fires, serve food, pour wine, and so on. The servants can go "
            + "anywhere in the mansion but can't leave it. Furnishings and other objects created by "
            + "this spell dissipate into smoke if removed from the mansion. When the spell ends, any "
            + "creatures inside the extradimensional space are expelled into the open spaces nearest "
            + "to the entrance.")),
    MIRAGE_ARCANE(spell(MagicSchool.ILLUSION, 7)
        .castingTime("10 minutes").components(VERBAL, SOMATIC)
        .range("Sight").duration("10 days")
        .effect("You make terrain in an area up to 1 mile square look, sound, smell, and even feel like "
            + "some other sort of terrain. The terrain's general shape remains the same, however. "
            + "Open fields or a road could be made to resemble a swamp, hill, crevasse, or some other "
            + "difficult or impassable terrain. A pond can be made to seem like a grassy meadow, a "
            + "precipice like a gentle slope, or a rock-strewn gully like a wide and smooth road. "
            + "Similarly, you can alter the appearance of structures, or add them where none are "
            + "present. The spell doesn't disguise, conceal, or add creatures. The illusion includes "
            + "audible, visual, tactile, and olfactory elements, so it can turn clear ground into "
            + "difficult terrain (or vice versa) or otherwise impede movement through the area. Any "
            + "piece of the illusory terrain (such as a rock or stick) that is removed from the "
            + "spell's area disappears immediately. Creatures with truesight can see through the "
            + "illusion to the terrain's true form; however, all other elements of the illusion "
            + "remain, so while the creature is aware of the illusion's presence, the creature can "
            + "still physically interact with the illusion.")),
    PLANE_SHIFT(spell(MagicSchool.CONJURATION, 7)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").area("Up to 8 allies or 1 enemy").duration("Instantaneous")
        .effect("Targets transported to different plane.")),
    PRISMATIC_SPRAY(spell(MagicSchool.EVOCATION, 7)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Self (60-foot cone)").duration("Instantaneous")
        .effect("Eight multicolored rays of light flash from your hand. Each ray is a different color "
            + "and has a different power and purpose. Each creature in a 60-foot cone must make a "
            + "Dexterity saving throw. For each target, roll a d8 to determine which color ray "
            + "affects it. 1. Red. The target takes 10d6 fire damage on a failed save, or half as "
            + "much damage on a successful one. 2. Orange. The target takes 10d6 acid damage on a "
            + "failed save, or half as much damage on a successful one. 3. Yellow. The target takes "
            + "10d6 lightning damage on a failed save, or half as much damage on a successful one. 4. "
            + "Green. The target takes 10d6 poison damage on a failed save, or half as much damage on "
            + "a successful one. 5. Blue. The target takes 10d6 cold damage on a failed save, or half "
            + "as much damage on a successful one. 6. Indigo. On a failed save, the target is "
            + "restrained. It must then make a Constitution saving throw at the end of each of its "
            + "turns. If it successfully saves three times, the spell ends. If it fails its save "
            + "three times, it permanently turns to stone and is subjected to the petrified "
            + "condition. The successes and failures don't need to be consecutive; keep track of both "
            + "until the target collects three of a kind. 7. Violet. On a failed save, the target is "
            + "blinded. It must then make a Wisdom saving throw at the start of your next turn. A "
            + "successful save ends the blindness. If it fails that save, the creature is transported "
            + "to another plane of existence of the GM's choosing and is no longer blinded. "
            + "(Typically, a creature that is on a plane that isn't its home plane is banished home, "
            + "while other creatures are usually cast into the Astral or Ethereal planes.) 8. "
            + "Special. The target is struck by two rays. Roll twice more, rerolling any 8.")),
    PROJECT_IMAGE(spell(MagicSchool.ILLUSION, 7)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("500 miles").duration("up to 1 day")
        .effect("You create an illusory copy of yourself that lasts for the duration. The copy can "
            + "appear at any location within range that you have seen before, regardless of "
            + "intervening obstacles. The illusion looks and sounds like you but is intangible. If "
            + "the illusion takes any damage, it disappears, and the spell ends. You can use your "
            + "action to move this illusion up to twice your speed, and make it gesture, speak, and "
            + "behave in whatever way you choose. It mimics your mannerisms perfectly. You can see "
            + "through its eyes and hear through its ears as if you were in its space. On your turn "
            + "as a bonus action, you can switch from using its senses to using your own, or back "
            + "again. While you are using its senses, you are blinded and deafened in regard to your "
            + "own surroundings. Physical interaction with the image reveals it to be an illusion, "
            + "because things can pass through it. A creature that uses its action to examine the "
            + "image can determine that it is an illusion with a successful Intelligence "
            + "(Investigation) check against your spell save DC. If a creature discerns the illusion "
            + "for what it is, the creature can see through the image, and any noise it makes sounds "
            + "hollow to the creature.")),
    REGENERATE(spell(MagicSchool.TRANSMUTATION, 7)
        .castingTime("1 minute").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").duration("1 hour")
        .effect("You touch a creature and stimulate its natural healing ability. The target regains 4d8 "
            + "+ 15 hit points. For the duration of the spell, the target regains 1 hit point at the "
            + "start of each of its turns (10 hit points each minute). The target's severed body "
            + "members (fingers, legs, tails, and so on), if any, are restored after 2 minutes. If "
            + "you have the severed part and hold it to the stump, the spell instantaneously causes "
            + "the limb to knit to the stump.")),
    RESURRECTION(spell(MagicSchool.NECROMANCY, 7)
        .castingTime("1 hour").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").duration("Instantaneous")
        .effect("You touch a dead creature that has been dead for no more than a century, that didn't "
            + "die of old age, and that isn't undead. If its soul is free and willing, the target "
            + "returns to life with all its hit points. This spell neutralizes any poisons and cures "
            + "normal diseases afflicting the creature when it died. It doesn't, however, remove "
            + "magical diseases, curses, and the like; if such effects aren't removed prior to "
            + "casting the spell, they afflict the target on its return to life. This spell closes "
            + "all mortal wounds and restores any missing body parts. Coming back from the dead is an "
            + "ordeal. The target takes a −4 penalty to all attack rolls, saving throws, and ability "
            + "checks. Every time the target finishes a long rest, the penalty is reduced by 1 until "
            + "it disappears. Casting this spell to restore life to a creature that has been dead for "
            + "one year or longer taxes you greatly. Until you finish a long rest, you can't cast "
            + "spells again, and you have disadvantage on all attack rolls, ability checks, and "
            + "saving throws.")),
    REVERSE_GRAVITY(spell(MagicSchool.TRANSMUTATION, 7)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("100 feet").duration("up to 1 minute")
        .effect("This spell reverses gravity in a 50-foot-radius, 100- foot high cylinder centered on a "
            + "point within range. All creatures and objects that aren't somehow anchored to the "
            + "ground in the area fall upward and reach the top of the area when you cast this spell. "
            + "A creature can make a Dexterity saving throw to grab onto a fixed object it can reach, "
            + "thus avoiding the fall. If some solid object (such as a ceiling) is encountered in "
            + "this fall, falling objects and creatures strike it just as they would during a normal "
            + "downward fall. If an object or creature reaches the top of the area without striking "
            + "anything, it remains there, oscillating slightly, for the duration. At the end of the "
            + "duration, affected objects and creatures fall back down.")),
    SEQUESTER(spell(MagicSchool.TRANSMUTATION, 7)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").duration("Until dispelled")
        .effect("By means of this spell, a willing creature or an object can be hidden away, safe from "
            + "detection for the duration. When you cast the spell and touch the target, it becomes "
            + "invisible and can't be targeted by divination spells or perceived through scrying "
            + "sensors created by divination spells. If the target is a creature, it falls into a "
            + "state of suspended animation. Time ceases to flow for it, and it doesn't grow older. "
            + "You can set a condition for the spell to end early. The condition can be anything you "
            + "choose, but it must occur or be visible within 1 mile of the target. Examples include "
            + "'after 1,000 years' or 'when the tarrasque awakens.' This spell also ends if the "
            + "target takes any damage.")),
    SIMULACRUM(spell(MagicSchool.ILLUSION, 7)
        .castingTime("12 hours").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").duration("Until dispelled")
        .effect("You shape an illusory duplicate of one beast or humanoid that is within range for the "
            + "entire casting time of the spell. The duplicate is a creature, partially real and "
            + "formed from ice or snow, and it can take actions and otherwise be affected as a normal "
            + "creature. It appears to be the same as the original, but it has half the creature's "
            + "hit point maximum and is formed without any equipment. Otherwise, the illusion uses "
            + "all the statistics of the creature it duplicates. The simulacrum is friendly to you "
            + "and creatures you designate. It obeys your spoken commands, moving and acting in "
            + "accordance with your wishes and acting on your turn in combat. The simulacrum lacks "
            + "the ability to learn or become more powerful, so it never increases its level or other "
            + "abilities, nor can it regain expended spell slots. If the simulacrum is damaged, you "
            + "can repair it in an alchemical laboratory, using rare herbs and minerals worth 100 gp "
            + "per hit point it regains. The simulacrum lasts until it drops to 0 hit points, at "
            + "which point it reverts to snow and melts instantly. If you cast this spell again, any "
            + "currently active duplicates you created with this spell are instantly destroyed.")),
    SYMBOL(spell(MagicSchool.ABJURATION, 7)
        .castingTime("1 minute").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").duration("Until dispelled or triggered")
        .effect("When you cast this spell, you inscribe a harmful glyph either on a surface (such as a "
            + "section of floor, a wall, or a table) or within an object that can be closed to "
            + "conceal the glyph (such as a book, a scroll, or a treasure chest). If you choose a "
            + "surface, the glyph can cover an area of the surface no larger than 10 feet in "
            + "diameter. If you choose an object, that object must remain in its place; if the object "
            + "is moved more than 10 feet from where you cast this spell, the glyph is broken, and "
            + "the spell ends without being triggered. The glyph is nearly invisible, requiring an "
            + "Intelligence (Investigation) check against your spell save DC to find it. You decide "
            + "what triggers the glyph when you cast the spell. For glyphs inscribed on a surface, "
            + "the most typical triggers include touching or stepping on the glyph, removing another "
            + "object covering it, approaching within a certain distance of it, or manipulating the "
            + "object that holds it. For glyphs inscribed within an object, the most common triggers "
            + "are opening the object, approaching within a certain distance of it, or seeing or "
            + "reading the glyph. You can further refine the trigger so the spell is activated only "
            + "under certain circumstances or according to a creature's physical characteristics "
            + "(such as height or weight), or physical kind (for example, the ward could be set to "
            + "affect hags or shapechangers). You can also specify creatures that don't trigger the "
            + "glyph, such as those who say a certain password. When you inscribe the glyph, choose "
            + "one of the options below for its effect. Once triggered, the glyph glows, filling a "
            + "60-foot-radius sphere with dim light for 10 minutes, after which time the spell ends. "
            + "Each creature in the sphere when the glyph activates is targeted by its effect, as is "
            + "a creature that enters the sphere for the first time on a turn or ends its turn there. "
            + "Death. Each target must make a Constitution saving throw, taking 10d10 necrotic damage "
            + "on a failed save, or half as much damage on a successful save. Discord. Each target "
            + "must make a Constitution saving throw. On a failed save, a target bickers and argues "
            + "with other creatures for 1 minute. During this time, it is incapable of meaningful "
            + "communication and has disadvantage on attack rolls and ability checks. Fear. Each "
            + "target must make a Wisdom saving throw and becomes frightened for 1 minute on a failed "
            + "save. While frightened, the target drops whatever it is holding and must move at least "
            + "30 feet away from the glyph on each of its turns, if able. Hopelessness. Each target "
            + "must make a Charisma saving throw. On a failed save, the target is overwhelmed with "
            + "despair for 1 minute. During this time, it can't attack or target any creature with "
            + "harmful abilities, spells, or other magical effects. Insanity. Each target must make "
            + "an Intelligence saving throw. On a failed save, the target is driven insane for 1 "
            + "minute. An insane creature can't take actions, can't understand what other creatures "
            + "say, can't read, and speaks only in gibberish. The GM controls its movement, which is "
            + "erratic. Pain. Each target must make a Constitution saving throw and becomes "
            + "incapacitated with excruciating pain for 1 minute on a failed save. Sleep. Each target "
            + "must make a Wisdom saving throw and falls unconscious for 10 minutes on a failed save. "
            + "A creature awakens if it takes damage or if someone uses an action to shake or slap it "
            + "awake. Stunning. Each target must make a Wisdom saving throw and becomes stunned for 1 "
            + "minute on a failed save.")),
    TELEPORT(spell(MagicSchool.CONJURATION, 7)
        .castingTime("1 action").components(VERBAL)
        .range("10 feet").area("Self and up to 8 creatures or 1 object").duration("Instantaneous")
        .effect("Transport targets to chosen destination. Special chance of miss.")),
    ANIMAL_SHAPES(spell(MagicSchool.TRANSMUTATION, 8)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("30 feet").area("any number of willing creatures").duration("up to 24 hours")
        .effect("Transform into beasts with CR of 4 or lower.")),
    ANTIMAGIC_FIELD(spell(MagicSchool.ABJURATION, 8)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self (10-foot-radius sphere)").duration("up to 1 hour")
        .effect("A 10-foot-radius invisible sphere of antimagic surrounds you. This area is divorced "
            + "from the magical energy that suffuses the multiverse. Within the sphere, spells canft "
            + "be cast, summoned creatures disappear, and even magic items become mundane. Until the "
            + "spell ends, the sphere moves with you, centered on you. Spells and other magical "
            + "effects, except those created by an artifact or a deity, are suppressed in the sphere "
            + "and can't protrude into it. A slot expended to cast a suppressed spell is consumed. "
            + "While an effect is suppressed, it doesn't function, but the time it spends suppressed "
            + "counts against its duration. Targeted Effects. Spells and other magical effects, such "
            + "as magic missile and charm person, that target a creature or an object in the sphere "
            + "have no effect on that target. Areas of Magic. The area of another spell or magical "
            + "effect, such as fireball, can't extend into the sphere. If the sphere overlaps an area "
            + "of magic, the part of the area that is covered by the sphere is suppressed. For "
            + "example, the flames created by a wall of fire are suppressed within the sphere, "
            + "creating a gap in the wall if the overlap is large enough. Spells. Any active spell or "
            + "other magical effect on a creature or an object in the sphere is suppressed while the "
            + "creature or object is in it. Magic Items. The properties and powers of magic items are "
            + "suppressed in the sphere. For example, a +1 longsword in the sphere functions as a "
            + "nonmagical longsword. A magic weapon's properties and powers are suppressed if it is "
            + "used against a target in the sphere or wielded by an attacker in the sphere. If a "
            + "magic weapon or a piece of magic ammunition fully leaves the sphere (for example, if "
            + "you fire a magic arrow or throw a magic spear at a target outside the sphere), the "
            + "magic of the item ceases to be suppressed as soon as it exits. Magical Travel. "
            + "Teleportation and planar travel fail to work in the sphere, whether the sphere is the "
            + "destination or the departure point for such magical travel. A portal to another "
            + "location, world, or plane of existence, as well as an opening to an extradimensional "
            + "space such as that created by the rope trick spell, temporarily closes while in the "
            + "sphere. Creatures and Objects. A creature or object summoned or created by magic "
            + "temporarily winks out of existence in the sphere. Such a creature instantly reappears "
            + "once the space the creature occupied is no longer within the sphere. Dispel Magic. "
            + "Spells and magical effects such as dispel magic have no effect on the sphere. "
            + "Likewise, the spheres created by different antimagic field spells don't nullify each "
            + "other.")),
    ANTIPATHY_SYMPATHY(spell(MagicSchool.ENCHANTMENT, 8)
        .castingTime("1 hour").components(VERBAL, SOMATIC, MATERIAL)
        .range("60 feet").duration("10 days")
        .effect("This spell attracts or repels creatures of your choice. You target something within "
            + "range, either a Huge or smaller object or creature or an area that is no larger than a "
            + "200-foot cube. Then specify a kind of intelligent creature, such as red dragons, "
            + "goblins, or vampires. You invest the target with an aura that either attracts or "
            + "repels the specified creatures for the duration. Choose antipathy or sympathy as the "
            + "aura's effect. Antipathy. The enchantment causes creatures of the kind you designated "
            + "to feel an intense urge to leave the area and avoid the target. When such a creature "
            + "can see the target or comes within 60 feet of it, the creature must succeed on a "
            + "Wisdom saving throw or become frightened. The creature remains frightened while it can "
            + "see the target or is within 60 feet of it. While frightened by the target, the "
            + "creature must use its movement to move to the nearest safe spot from which it can't "
            + "see the target. If the creature moves more than 60 feet from the target and can't see "
            + "it, the creature is no longer frightened, but the creature becomes frightened again if "
            + "it regains sight of the target or moves within 60 feet of it. Sympathy. The "
            + "enchantment causes the specified creatures to feel an intense urge to approach the "
            + "target while within 60 feet of it or able to see it. When such a creature can see the "
            + "target or comes within 60 feet of it, the creature must succeed on a Wisdom saving "
            + "throw or use its movement on each of its turns to enter the area or move within reach "
            + "of the target. When the creature has done so, it can't willingly move away from the "
            + "target. If the target damages or otherwise harms an affected creature, the affected "
            + "creature can make a Wisdom saving throw to end the effect, as described below. Ending "
            + "the Effect. If an affected creature ends its turn while not within 60 feet of the "
            + "target or able to see it, the creature makes a Wisdom saving throw. On a successful "
            + "save, the creature is no longer affected by the target and recognizes the feeling of "
            + "repugnance or attraction as magical. In addition, a creature affected by the spell is "
            + "allowed another Wisdom saving throw every 24 hours while the spell persists. A "
            + "creature that successfully saves against this effect is immune to it for 1 minute, "
            + "after which time it can be affected again.")),
    CLONE(spell(MagicSchool.NECROMANCY, 8)
        .castingTime("1 hour").components(VERBAL, SOMATIC, MATERIAL)
        .range("Touch").duration("Instantaneous")
        .effect("This spell grows an inert duplicate of a living creature as a safeguard against death. "
            + "This clone forms inside a sealed vessel and grows to full size and maturity after 120 "
            + "days; you can also choose to have the clone be a younger version of the same creature. "
            + "It remains inert and endures indefinitely, as long as its vessel remains undisturbed. "
            + "At any time after the clone matures, if the original creature dies, its soul transfers "
            + "to the clone, provided that the soul is free and willing to return. The clone is "
            + "physically identical to the original and has the same personality, memories, and "
            + "abilities, but none of the original's equipment. The original creature's physical "
            + "remains, if they still exist, become inert and can't thereafter be restored to life, "
            + "since the creature's soul is elsewhere.")),
    CONTROL_WEATHER(spell(MagicSchool.TRANSMUTATION, 8)
        .castingTime("10 minutes").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self (5-mile radius)").duration("up to 8 hours")
        .effect("You take control of the weather within 5 miles of you for the duration. You must be "
            + "outdoors to cast this spell. Moving to a place where you don't have a clear path to "
            + "the sky ends the spell early. When you cast the spell, you change the current weather "
            + "conditions, which are determined by the GM based on the climate and season. You can "
            + "change precipitation, temperature, and wind. It takes 1d4 × 10 minutes for the new "
            + "conditions to take effect. Once they do so, you can change the conditions again. When "
            + "the spell ends, the weather gradually returns to normal. When you change the weather "
            + "conditions, find a current condition on the following tables and change its stage by "
            + "one, up or down. When changing the wind, you can change its direction. Precipitation "
            + "Stage Condition 1 Clear 2 Light clouds 3 Overcast or ground fog 4 Rain, hail or snow 5 "
            + "Torrential rain, driving hail, or blizzard Temperature Stage Condition 1 Unbearable "
            + "heat 2 Hot 3 Warm 4 Cool 5 Cold 6 Arctic cold Wind Stage Condition 1 Calm 2 Moderate "
            + "wind 3 Strong wind 4 Gale 5 Storm")),
    DEMIPLANE(spell(MagicSchool.CONJURATION, 8)
        .castingTime("1 action").components(SOMATIC)
        .range("60 feet").duration("1 hour")
        .effect("You create a shadowy door on a flat solid surface that you can see within range. The "
            + "door is large enough to allow Medium creatures to pass through unhindered. When "
            + "opened, the door leads to a demiplane that appears to be an empty room 30 feet in each "
            + "dimension, made of wood or stone. When the spell ends, the door disappears, and any "
            + "creatures or objects inside the demiplane remain trapped there, as the door also "
            + "disappears from the other side. Each time you cast this spell, you can create a new "
            + "demiplane, or have the shadowy door connect to a demiplane you created with a previous "
            + "casting of this spell. Additionally, if you know the nature and contents of a "
            + "demiplane created by a casting of this spell by another creature, you can have the "
            + "shadowy door connect to its demiplane instead.")),
    DOMINATE_MONSTER(spell(MagicSchool.ENCHANTMENT, 8)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("60 feet").duration("up to 1 hour")
        .effect("You attempt to beguile a creature that you can see within range. It must succeed on a "
            + "Wisdom saving throw or be charmed by you for the duration. If you or creatures that "
            + "are friendly to you are fighting it, it has advantage on the saving throw. While the "
            + "creature is charmed, you have a telepathic link with it as long as the two of you are "
            + "on the same plane of existence. You can use this telepathic link to issue commands to "
            + "the creature while you are conscious (no action required), which it does its best to "
            + "obey. You can specify a simple and general course of action, such as 'Attack that "
            + "creature,' 'Run over there,' or 'Fetch that object.' If the creature completes the "
            + "order and doesn't receive further direction from you, it defends and preserves itself "
            + "to the best of its ability. You can use your action to take total and precise control "
            + "of the target. Until the end of your next turn, the creature takes only the actions "
            + "you choose, and doesn't do anything that you don't allow it to do. During this time, "
            + "you can also cause the creature to use a reaction, but this requires you to use your "
            + "own reaction as well. Each time the target takes damage, it makes a new Wisdom saving "
            + "throw against the spell. If the saving throw succeeds, the spell ends. At Higher "
            + "Levels. When you cast this spell with a 9th-level spell slot, the duration is "
            + "concentration, up to 8 hours.")),
    EARTHQUAKE(spell(MagicSchool.EVOCATION, 8)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("500 feet").duration("up to 1 minute")
        .effect("You create a seismic disturbance at a point on the ground that you can see within "
            + "range. For the duration, an intense tremor rips through the ground in a "
            + "100-foot-radius circle centered on that point and shakes creatures and structures in "
            + "contact with the ground in that area. The ground in the area becomes difficult "
            + "terrain. Each creature on the ground that is concentrating must make a Constitution "
            + "saving throw. On a failed save, the creature's concentration is broken. When you cast "
            + "this spell and at the end of each turn you spend concentrating on it, each creature on "
            + "the ground in the area must make a Dexterity saving throw. On a failed save, the "
            + "creature is knocked prone. This spell can have additional effects depending on the "
            + "terrain in the area, as determined by the GM. Fissures. Fissures open throughout the "
            + "spell's area at the start of your next turn after you cast the spell. A total of 1d6 "
            + "such fissures open in locations chosen by the GM. Each is 1d10 × 10 feet deep, 10 feet "
            + "wide, and extends from one edge of the spell's area to the opposite side. A creature "
            + "standing on a spot where a fissure opens must succeed on a Dexterity saving throw or "
            + "fall in. A creature that successfully saves moves with the fissure's edge as it opens. "
            + "A fissure that opens beneath a structure causes it to automatically collapse (see "
            + "below). Structures. The tremor deals 50 bludgeoning damage to any structure in contact "
            + "with the ground in the area when you cast the spell and at the start of each of your "
            + "turns until the spell ends. If a structure drops to 0 hit points, it collapses and "
            + "potentially damages nearby creatures. A creature within half the distance of a "
            + "structure's height must make a Dexterity saving throw. On a failed save, the creature "
            + "takes 5d6 bludgeoning damage, is knocked prone, and is buried in the rubble, requiring "
            + "a DC 20 Strength (Athletics) check as an action to escape. The GM can adjust the DC "
            + "higher or lower, depending on the nature of the rubble. On a successful save, the "
            + "creature takes half as much damage and doesn't fall prone or become buried.")),
    FEEBLEMIND(spell(MagicSchool.ENCHANTMENT, 8)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("150 feet").duration("Instantaneous")
        .effect("You blast the mind of a creature that you can see within range, attempting to shatter "
            + "its intellect and personality. The target takes 4d6 psychic damage and must make an "
            + "Intelligence saving throw. On a failed save, the creature's Intelligence and Charisma "
            + "scores become 1. The creature can't cast spells, activate magic items, understand "
            + "language, or communicate in any intelligible way. The creature can, however, identify "
            + "its friends, follow them, and even protect them. At the end of every 30 days, the "
            + "creature can repeat its saving throw against this spell. If it succeeds on its saving "
            + "throw, the spell ends. The spell can also be ended by greater restoration, heal, or wish.")),
    GLIBNESS(spell(MagicSchool.TRANSMUTATION, 8)
        .castingTime("1 action").components(VERBAL)
        .range("Self").duration("1 hour")
        .effect("Until the spell ends, when you make a Charisma check, you can replace the number you "
            + "roll with a 15. Additionally, no matter what you say, magic that would determine if "
            + "you are telling the truth indicates that you are being truthful.")),
    HOLY_AURA(spell(MagicSchool.ABJURATION, 8)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").duration("up to 1 minute")
        .effect("Divine light washes out from you and coalesces in a soft radiance in a 30-foot radius "
            + "around you. Creatures of your choice in that radius when you cast this spell shed dim "
            + "light in a 5-foot radius and have advantage on all saving throws, and other creatures "
            + "have disadvantage on attack rolls against them until the spell ends. In addition, when "
            + "a fiend or an undead hits an affected creature with a melee attack, the aura flashes "
            + "with brilliant light. The attacker must succeed on a Constitution saving throw or be "
            + "blinded until the spell ends.")),
    INCENDIARY_CLOUD(spell(MagicSchool.CONJURATION, 8)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("150 feet").duration("up to 1 minute")
        .effect("A swirling cloud of smoke shot through with white- hot embers appears in a "
            + "20-foot-radius sphere centered on a point within range. The cloud spreads around "
            + "corners and is heavily obscured. It lasts for the duration or until a wind of moderate "
            + "or greater speed (at least 10 miles per hour) disperses it. When the cloud appears, "
            + "each creature in it must make a Dexterity saving throw. A creature takes 10d8 fire "
            + "damage on a failed save, or half as much damage on a successful one. A creature must "
            + "also make this saving throw when it enters the spell's area for the first time on a "
            + "turn or ends its turn there. The cloud moves 10 feet directly away from you in a "
            + "direction that you choose at the start of each of your turns.")),
    MAZE(spell(MagicSchool.CONJURATION, 8)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("60 feet").duration("up to 10 minutes")
        .effect("You banish a creature that you can see within range into a labyrinthine demiplane. The "
            + "target remains there for the duration or until it escapes the maze. The target can use "
            + "its action to attempt to escape. When it does so, it makes a DC 20 Intelligence check. "
            + "If it succeeds, it escapes, and the spell ends (a minotaur or goristro demon "
            + "automatically succeeds). When the spell ends, the target reappears in the space it "
            + "left or, if that space is occupied, in the nearest unoccupied space.")),
    MIND_BLANK(spell(MagicSchool.ABJURATION, 8)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Touch").duration("24 hours")
        .effect("Until the spell ends, one willing creature you touch is immune to psychic damage, any "
            + "effect that would sense its emotions or read its thoughts, divination spells, and the "
            + "charmed condition. The spell even foils wish spells and spells or effects of similar "
            + "power used to affect the target's mind or to gain information about the target.")),
    POWER_WORD_STUN(spell(MagicSchool.ENCHANTMENT, 8)
        .castingTime("1 action").components(VERBAL)
        .range("60 feet").duration("Instantaneous")
        .effect("You speak a word of power that can overwhelm the mind of one creature you can see "
            + "within range, leaving it dumbfounded. If the target has 150 hit points or fewer, it is "
            + "stunned. Otherwise, the spell has no effect. The stunned target must make a "
            + "Constitution saving throw at the end of each of its turns. On a successful save, this "
            + "stunning effect ends.")),
    SUNBURST(spell(MagicSchool.EVOCATION, 8)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("150 feet").duration("Instantaneous")
        .effect("Brilliant sunlight flashes in a 60-foot radius centered on a point you choose within "
            + "range. Each creature in that light must make a Constitution saving throw. On a failed "
            + "save, a creature takes 12d6 radiant damage and is blinded for 1 minute. On a "
            + "successful save, it takes half as much damage and isn't blinded by this spell. Undead "
            + "and oozes have disadvantage on this saving throw. A creature blinded by this spell "
            + "makes another Constitution saving throw at the end of each of its turns. On a "
            + "successful save, it is no longer blinded. This spell dispels any darkness in its area "
            + "that was created by a spell.")),
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
        .effect("Imprison creature. Wis. save DC[$spell_dc].")),
    MASS_HEAL(spell(MagicSchool.EVOCATION, 9)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("60 feet").area("Any number of creatures").duration("Instantaneous")
        .effect("Restore up to 700 HP, curing all diseases, blindness and deafness.")),
    METEOR_SWARM(spell(MagicSchool.EVOCATION, 9)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("1 mile").area("4 40-foot-radius spheres").duration("Instantaneous")
        .effect("20d6 fire damage. 20d6 bludgeoning damage. Dex. save DC[$spell_dc] for half.")),
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
        .effect("Enemies within 20 feet blinded for 1 minutes. Con. save DC[$spell_dc].")
        .effect("Dex. save to avoid effect of each layer.")
        .effect("1. Red. 10d6 fire damage. Save for half. Prevents nonmagical ranged attacks. "
            + "Destroyed by 25 cold damage.")
        .effect("2. Orange. 10d6 acid damage. Save for half. Destroyed by strong wind")
        .effect("3. Yellow. 10d6 lightning damage. Save for half. Destroyed by 60 force damage.")
        .effect("4. Green. 10d6 poison damage. Save for half. Destroyed by <em>Passwall</em> spell.")
        .effect("5. Blue. 10d6 cold damage. Save for half. Destroyed by 25 fire damage.")
        .effect("6. Indigo. Restrains creature. Con. save each turn. 3 success ends spell. "
            + "3 failures petrifies creature. Destroyed by <em>Daylight</em> spell. "
            + "Prevents spells passing through wall.")
        .effect("7. Violet. Blinds creature. Wis. save or be transported to another plane. "
            + "Destroyed by <em>Dispel Magic</em> spell.")),
    SHAPECHANGE(spell(MagicSchool.TRANSMUTATION, 9)
        .castingTime("1 action").components(VERBAL, SOMATIC, MATERIAL)
        .range("Self").duration("up to 1 hour")
        .effect("Assume the form of a different creature with CR up to [$level].")
        .effect("Retain alignment, Int., Wis., Chr., skill and saving throw proficiencies.")),
    STORM_OF_VENGEANCE(spell(MagicSchool.CONJURATION, 9)
        .castingTime("1 action").components(VERBAL, SOMATIC)
        .range("Sight").area("360-feet radius").duration("up to 1 minute")
        .effect("Forms storm cloud. Each creature under storm takes 2d6 thunder damage "
            + "and becomes defeaned for 5 minutes. Con. save DC[$spell_dc].")
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
        .effect("Transform target into different creature or object. Wis. save DC[$spell_dc].")
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
            + "Wis. save DC[$spell_dc] each turn.")),
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
