package characterbuilder.character.spell;

import characterbuilder.character.Character;
import characterbuilder.character.characterclass.MagicSchool;
import characterbuilder.character.choice.Option;
import characterbuilder.utils.StringUtils;
import java.util.stream.Stream;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public enum Spell implements Option {
    ARCANE_EYE(MagicSchool.DIVINATION, 4, "1 action", "30 feet",
        "V, S, M ", "up to 1 hour", "You create an invisible, magical eye within range that "
        + "hovers in the air for the duration. You mentally receive visual information from the "
        + "eye, which has normal vision and darkvision out to 30 feet. The eye can look in every "
        + "direction. As an action, you can move the eye up to 30 feet in any direction. There is "
        + "no limit to how far away from you the eye can move, but it can't enter another plane "
        + "of existence. A solid barrier blocks the eye's movement, but the eye can pass through "
        + "an opening as small as 1 inch in diameter."),
    BANISHMENT(MagicSchool.ABJURATION, 4, "1 action", "60 feet",
        "V, S, M ", "up to 1 minute", "You attempt to send one creature that you can see "
        + "within range to another plane of existence. The target must succeed on a Charisma "
        + "saving throw or be banished. If the target is native to the plane of existence you're "
        + "on, you banish the target to a harmless demiplane. While there, the target is "
        + "incapacitated. The target remains there until the spell ends, at which point the "
        + "target reappears in the space it left or in the nearest unoccupied space if that space "
        + "is occupied. If the target is native to a different plane of existence than the one "
        + "you're on, the target is banished with a faint popping noise, returning to its home "
        + "plane. If the spell ends before 1 minute has passed, the target reappears in the space "
        + "it left or in the nearest unoccupied space if that space is occupied. Otherwise, the "
        + "target doesn't return. At Higher Levels. When you cast this spell using a spell slot "
        + "of 5th level or higher, you can target one additional creature for each slot level "
        + "above 4th."),
    BLACK_TENTACLES(MagicSchool.CONJURATION, 4, "1 action", "90 feet",
        "V, S, M ", "up to 1 minute", "Squirming, ebony tentacles fill a 20-foot square on "
        + "ground that you can see within range. For the duration, these tentacles turn the "
        + "ground in the area into difficult terrain. When a creature enters the affected area "
        + "for the first time on a turn or starts its turn there, the creature must succeed on a "
        + "Dexterity saving throw or take 3d6 bludgeoning damage and be restrained by the "
        + "tentacles until the spell ends. A creature that starts its turn in the area and is "
        + "already restrained by the tentacles takes 3d6 bludgeoning damage. A creature "
        + "restrained by the tentacles can use its action to make a Strength or Dexterity check "
        + "(its choice) against your spell save DC. On a success, it frees itself."),
    BLIGHT(MagicSchool.NECROMANCY, 4, "1 action", "30 feet", "V, S",
        "Instantaneous", "Necromantic energy washes over a creature of your choice that you "
        + "can see within range, draining moisture and vitality from it. The target must make a "
        + "Constitution saving throw. The target takes 8d8 necrotic damage on a failed save, or "
        + "half as much damage on a successful one. This spell has no effect on undead or "
        + "constructs. If you target a plant creature or a magical plant, it makes the saving "
        + "throw with disadvantage, and the spell deals maximum damage to it. If you target a "
        + "nonmagical plant that isn't a creature, such as a tree or shrub, it doesn't make a "
        + "saving throw; it simply withers and dies. At Higher Levels. When you cast this spell "
        + "using a spell slot of 5th level or higher, the damage increases by 1d8 for each slot "
        + "level above 4th."),
    COMPULSION(MagicSchool.ENCHANTMENT, 4, "1 action", "30 feet",
        "V, S", "up to 1 minute", "Creatures of your choice that you can see within range and "
        + "that can hear you must make a Wisdom saving throw. A target automatically succeeds on "
        + "this saving throw if it can't be charmed. On a failed save, a target is affected by "
        + "this spell. Until the spell ends, you can use a bonus action on each of your turns to "
        + "designate a direction that is horizontal to you. Each affected target must use as much "
        + "of its movement as possible to move in that direction on its next turn. It can take "
        + "its action before it moves. After moving in this way, it can make another Wisdom "
        + "saving to try to end the effect. A target isn't compelled to move into an obviously "
        + "deadly hazard, such as a fire or pit, but it will provoke opportunity attacks to move "
        + "in the designated direction."),
    CONFUSION(MagicSchool.ENCHANTMENT, 4, "1 action", "90 feet",
        "V, S, M ", "up to 1 minute", "This spell assaults and twists creatures' minds, "
        + "spawning delusions and provoking uncontrolled action. Each creature in a "
        + "10-foot-radius sphere centered on a point you choose within range must succeed on a "
        + "Wisdom saving throw when you cast this spell or be affected by it. An affected target "
        + "can't take reactions and must roll a d10 at the start of each of its turns to "
        + "determine its behavior for that turn. d10 Behavior 1 The creature uses all its "
        + "movement to move in a random direction. To determine the direction, roll a d8 and "
        + "assign a direction to each die face. The creature doesn't take an action this turn. "
        + "2-6 The creature doesn't move or take actions this turn. 7-8 The creature uses its "
        + "action to make a melee attack against a randomly determined creature within its reach. "
        + "If there is no creature within its reach, the creature does nothing this turn. 9-10 "
        + "The creature can act and move normally. At the end of each of its turns, an affected "
        + "target can make a Wisdom saving throw. If it succeeds, this effect ends for that "
        + "target. At Higher Levels. When you cast this spell using a spell slot of 5th level or "
        + "higher, the radius of the sphere increases by 5 feet for each slot level above 4th."),
    CONJURE_MINOR_ELEMENTALS(MagicSchool.CONJURATION, 4, "1 minute",
        "90 feet", "V, S", "up to 1 hour", "You summon elementals that appear in unoccupied "
        + "spaces that you can see within range. You choose one the following options for what "
        + "appears: One elemental of challenge rating 2 or lower Two elementals of challenge "
        + "rating 1 or lower Four elementals of challenge rating 1/2 or lower Eight elementals of "
        + "challenge rating 1/4 or lower. An elemental summoned by this spell disappears when it "
        + "drops to 0 hit points or when the spell ends. The summoned creatures are friendly to "
        + "you and your companions. Roll initiative for the summoned creatures as a group, which "
        + "has its own turns. They obey any verbal commands that you issue to them (no action "
        + "required by you). If you don't issue any commands to them, they defend themselves from "
        + "hostile creatures, but otherwise take no actions. The GM has the creatures' "
        + "statistics. At Higher Levels. When you cast this spell using certain higher-level "
        + "spell slots, you choose one of the summoning options above, and more creatures appear: "
        + "twice as many with a 6th-level slot and three times as many with an 8th-level slot."),
    CONJURE_WOODLAND_BEINGS(MagicSchool.CONJURATION, 4, "1 action",
        "60 feet", "V, S, M ", "up to 1 hour", "You summon fey creatures that appear in "
        + "unoccupied spaces that you can see within range. Choose one of the following options "
        + "for what appears: One fey creature of challenge rating 2 or lower Two fey creatures of "
        + "challenge rating 1 or lower Four fey creatures of challenge rating 1/2 or lower Eight "
        + "fey creatures of challenge rating 1/4 or lower A summoned creature disappears when it "
        + "drops to 0 hit points or when the spell ends. The summoned creatures are friendly to "
        + "you and your companions. Roll initiative for the summoned creatures as a group, which "
        + "have their own turns. They obey any verbal commands that you issue to them (no action "
        + "required by you). If you don't issue any commands to them, they defend themselves from "
        + "hostile creatures, but otherwise take no actions. The GM has the creatures' "
        + "statistics. At Higher Levels. When you cast this spell using certain higher-level "
        + "spell slots, you choose one of the summoning options above, and more creatures appear: "
        + "twice as many with a 6th-level slot and three times as many with an 8th-level slot."),
    CONTROL_WATER(MagicSchool.TRANSMUTATION, 4, "1 action", "300 feet",
        "V, S, M ", "up to 10 minutes", "Until the spell ends, you control any freestanding "
        + "water inside an area you choose that is a cube up to 100 feet on a side. You can "
        + "choose from any of the following effects when you cast this spell. As an action on "
        + "your turn, you can repeat the same effect or choose a different one. Flood. You cause "
        + "the water level of all standing water in the area to rise by as much as 20 feet. If "
        + "the area includes a shore, the flooding water spills over onto dry land. If you choose "
        + "an area in a large body of water, you instead create a 20-foot tall wave that travels "
        + "from one side of the area to the other and then crashes down. Any Huge or smaller "
        + "vehicles in the wave's path are carried with it to the other side. Any Huge or smaller "
        + "vehicles struck by the wave have a 25 percent chance of capsizing. The water level "
        + "remains elevated until the spell ends or you choose a different effect. If this effect "
        + "produced a wave, the wave repeats on the start of your next turn while the flood "
        + "effect lasts. Part Water. You cause water in the area to move apart and create a "
        + "trench. The trench extends across the spell's area, and the separated water forms a "
        + "wall to either side. The trench remains until the spell ends or you choose a different "
        + "effect. The water then slowly fills in the trench over the course of the next round "
        + "until the normal water level is restored. Redirect Flow. You cause flowing water in "
        + "the area to move in a direction you choose, even if the water has to flow over "
        + "obstacles, up walls, or in other unlikely directions. The water in the area moves as "
        + "you direct it, but once it moves beyond the spell's area, it resumes its flow based on "
        + "the terrain conditions. The water continues to move in the direction you chose until "
        + "the spell ends or you choose a different effect. Whirlpool. This effect requires a "
        + "body of water at least 50 feet square and 25 feet deep. You cause a whirlpool to form "
        + "in the center of the area. The whirlpool forms a vortex that is 5 feet wide at the "
        + "base, up to 50 feet wide at the top, and 25 feet tall. Any creature or object in the "
        + "water and within 25 feet of the vortex is pulled 10 feet toward it. A creature can "
        + "swim away from the vortex by making a Strength (Athletics) check against your spell "
        + "save DC. When a creature enters the vortex for the first time on a turn or starts its "
        + "turn there, it must make a Strength saving throw. On a failed save, the creature takes "
        + "2d8 bludgeoning damage and is caught in the vortex until the spell ends. On a "
        + "successful save, the creature takes half damage, and isn't caught in the vortex. A "
        + "creature caught in the vortex can use its action to try to swim away from the vortex "
        + "as described above, but has disadvantage on the Strength (Athletics) check to do so. "
        + "The first time each turn that an object enters the vortex, the object takes 2d8 "
        + "bludgeoning damage; this damage occurs each round it remains in the vortex."),
    DEATH_WARD(MagicSchool.ABJURATION, 4, "1 action", "Touch",
        "V, S", "8 hours", "You touch a creature and grant it a measure of protection from "
        + "death. The first time the target would drop to 0 hit points as a result of taking "
        + "damage, the target instead drops to 1 hit point, and the spell ends. If the spell is "
        + "still in effect when the target is subjected to an effect that would kill it "
        + "instantaneously without dealing damage, that effect is instead negated against the "
        + "target, and the spell ends."),
    DIMENSION_DOOR(MagicSchool.CONJURATION, 4, "1 action", "500 feet",
        "V", "Instantaneous Y", "ou teleport yourself from your current location to any other "
        + "spot within range. You arrive at exactly the spot desired. It can be a place you can "
        + "see, one you can visualize, or one you can describe by stating distance and direction, "
        + "such as '200 feet straight downward' or 'upward to the northwest at a 45- degree "
        + "angle, 300 feet.' You can bring along objects as long as their weight doesn't exceed "
        + "what you can carry. You can also bring one willing creature of your size or smaller "
        + "who is carrying gear up to its carrying capacity. The creature must be within 5 feet "
        + "of you when you cast this spell. If you would arrive in a place already occupied by an "
        + "object or a creature, you and any creature traveling with you each take 4d6 force "
        + "damage, and the spell fails to teleport you."),
    DIVINATION(MagicSchool.DIVINATION, 4, "1 action", "Self", "V, S, M ",
        "Instantaneous", "Your magic and an offering put you in contact with a god or a god's "
        + "servants. You ask a single question concerning a specific goal, event, or activity to "
        + "occur within 7 days. The GM offers a truthful reply. The reply might be a short "
        + "phrase, a cryptic rhyme, or an omen. The spell doesn't take into account any possible "
        + "circumstances that might change the outcome, such as the casting of additional spells "
        + "or the loss or gain of a companion. If you cast the spell two or more times before "
        + "finishing your next long rest, there is a cumulative 25 percent chance for each "
        + "casting after the first that you get a random reading. The GM makes this roll in "
        + "secret."),
    DOMINATE_BEAST(MagicSchool.ENCHANTMENT, 4, "1 action", "60 feet",
        "V, S", "up to 1 minute", "You attempt to beguile a beast that you can see within "
        + "range. It must succeed on a Wisdom saving throw or be charmed by you for the duration. "
        + "If you or creatures that are friendly to you are fighting it, it has advantage on the "
        + "saving throw. While the beast is charmed, you have a telepathic link with it as long "
        + "as the two of you are on the same plane of existence. You can use this telepathic link "
        + "to issue commands to the creature while you are conscious (no action required), which "
        + "it does its best to obey. You can specify a simple and general course of action, such "
        + "as 'Attack that creature,' 'Run over there,' or 'Fetch that object.' If the creature "
        + "completes the order and doesn't receive further direction from you, it defends and "
        + "preserves itself to the best of its ability. You can use your action to take total and "
        + "precise control of the target. Until the end of your next turn, the creature takes "
        + "only the actions you choose, and doesn't do anything that you don't allow it to do. "
        + "During this time, you can also cause the creature to use a reaction, but this requires "
        + "you to use your own reaction as well. Each time the target takes damage, it makes a "
        + "new Wisdom saving throw against the spell. If the saving throw succeeds, the spell "
        + "ends. At Higher Levels. When you cast this spell with a 5th-level spell slot, the "
        + "duration is concentration, up to 10 minutes. When you use a 6th-level spell slot, the "
        + "duration is concentration, up to 1 hour. When you use a spell slot of 7th level or "
        + "higher, the duration is concentration, up to 8 hours."),
    FABRICATE(MagicSchool.TRANSMUTATION, 4, "10 minutes", "120 feet",
        "V, S", "Instantaneous", "You convert raw materials into products of the same "
        + "material. For example, you can fabricate a wooden bridge from a clump of trees, a rope "
        + "from a patch of hemp, and clothes from flax or wool. Choose raw materials that you can "
        + "see within range. You can fabricate a Large or smaller object (contained within a "
        + "10-foot cube, or eight connected 5-foot cubes), given a sufficient quantity of raw "
        + "material. If you are working with metal, stone, or another mineral substance, however, "
        + "the fabricated object can be no larger than Medium (contained within a single 5-foot "
        + "cube). The quality of objects made by the spell is commensurate with the quality of "
        + "the raw materials. Creatures or magic items can't be created or transmuted by this "
        + "spell. You also can't use it to create items that ordinarily require a high degree of "
        + "craftsmanship, such as jewelry, weapons, glass, or armor, unless you have proficiency "
        + "with the type of artisan's tools used to craft such objects."),
    FAITHFUL_HOUND(MagicSchool.CONJURATION, 4, "1 action", "30 feet",
        "V, S, M ", "8 hours", "You conjure a phantom watchdog in an unoccupied space that "
        + "you can see within range, where it remains for the duration, until you dismiss it as "
        + "an action, or until you move more than 100 feet away from it. The hound is invisible "
        + "to all creatures except you and can't be harmed. When a Small or larger creature comes "
        + "within 30 feet of it without first speaking the password that you specify when you "
        + "cast this spell, the hound starts barking loudly. The hound sees invisible creatures "
        + "and can see into the Ethereal Plane. It ignores illusions. At the start of each of "
        + "your turns, the hound attempts to bite one creature within 5 feet of it that is "
        + "hostile to you. The hound's attack bonus is equal to your spellcasting ability "
        + "modifier + your proficiency bonus. On a hit, it deals 4d8 piercing damage."),
    FIRE_SHIELD(MagicSchool.EVOCATION, 4, "1 action", "Self", "V, S, M ",
        "10 minutes", "Thin and wispy flames wreathe your body for the duration, shedding "
        + "bright light in a 10-foot radius and dim light for an additional 10 feet. You can end "
        + "the spell early by using an action to dismiss it. The flames provide you with a warm "
        + "shield or a chill shield, as you choose. The warm shield grants you resistance to cold "
        + "damage, and the chill shield grants you resistance to fire damage. In addition, "
        + "whenever a creature within 5 feet of you hits you with a melee attack, the shield "
        + "erupts with flame. The attacker takes 2d8 fire damage from a warm shield, or 2d8 cold "
        + "damage from a cold shield."),
    FREEDOM_OF_MOVEMENT(MagicSchool.ABJURATION, 4, "1 action",
        "Touch", "V, S, M ", "1 hour", "You touch a willing creature. For the duration, the "
        + "target's movement is unaffected by difficult terrain, and spells and other magical "
        + "effects can neither reduce the target's speed nor cause the target to be paralyzed or "
        + "restrained. The target can also spend 5 feet of movement to automatically escape from "
        + "nonmagical restraints, such as manacles or a creature that has it grappled. Finally, "
        + "being underwater imposes no penalties on the target's movement or attacks."),
    GIANT_INSECT(MagicSchool.TRANSMUTATION, 4, "1 action", "30 feet",
        "V, S", "up to 10 minutes", "You transform up to ten centipedes, three spiders, five "
        + "wasps, or one scorpion within range into giant versions of their natural forms for the "
        + "duration. A centipede becomes a giant centipede, a spider becomes a giant spider, a "
        + "wasp becomes a giant wasp, and a scorpion becomes a giant scorpion. Each creature "
        + "obeys your verbal commands, and in combat, they act on your turn each round. The GM "
        + "has the statistics for these creatures and resolves their actions and movement. A "
        + "creature remains in its giant size for the duration, until it drops to 0 hit points, "
        + "or until you use an action to dismiss the effect on it. The GM might allow you to "
        + "choose different targets. For example, if you transform a bee, its giant version might "
        + "have the same statistics as a giant wasp."),
    GREATER_INVISIBILITY(MagicSchool.ILLUSION, 4, "1 action", "Touch",
        "V, S", "up to 1 minute", "You or a creature you touch becomes invisible until the "
        + "spell ends. Anything the target is wearing or carrying is invisible as long as it is "
        + "on the target's person."),
    GUARDIAN_OF_FAITH(MagicSchool.CONJURATION, 4, "1 action", "30 feet",
        "V", "8 hours", "A Large spectral guardian appears and hovers for the duration in an "
        + "unoccupied space of your choice that you can see within range. The guardian occupies "
        + "that space and is indistinct except for a gleaming sword and shield emblazoned with "
        + "the symbol of your deity. Any creature hostile to you that moves to a space within 10 "
        + "feet of the guardian for the first time on a turn must succeed on a Dexterity saving "
        + "throw. The creature takes 20 radiant damage on a failed save, or half as much damage "
        + "on a successful one. The guardian vanishes when it has dealt a total of 60 damage."),
    HALLUCINATORY_TERRAIN(MagicSchool.ILLUSION, 4, "10 minutes",
        "300 feet", "V, S, M ", "24 hours", "You make natural terrain in a 150-foot cube in "
        + "range look, sound, and smell like some other sort of natural terrain. Thus, open "
        + "fields or a road can be made to resemble a swamp, hill, crevasse, or some other "
        + "difficult or impassable terrain. A pond can be made to seem like a grassy meadow, a "
        + "precipice like a gentle slope, or a rock-strewn gully like a wide and smooth road. "
        + "Manufactured structures, equipment, and creatures within the area aren't changed in "
        + "appearance. The tactile characteristics of the terrain are unchanged, so creatures "
        + "entering the area are likely to see through the illusion. If the difference isn't "
        + "obvious by touch, a creature carefully examining the illusion can attempt an "
        + "Intelligence (Investigation) check against your spell save DC to disbelieve it. A "
        + "creature who discerns the illusion for what it is, sees it as a vague image "
        + "superimposed on the terrain."),
    ICE_STORM(MagicSchool.EVOCATION, 4, "1 action", "300 feet",
        "V, S, M ", "Instantaneous", "A hail of rock-hard ice pounds to the ground in a 20- "
        + "foot-radius, 40-foot-high cylinder centered on a point within range. Each creature in "
        + "the cylinder must make a Dexterity saving throw. A creature takes 2d8 bludgeoning "
        + "damage and 4d6 cold damage on a failed save, or half as much damage on a successful "
        + "one. Hailstones turn the storm's area of effect into difficult terrain until the end "
        + "of your next turn. At Higher Levels. When you cast this spell using a spell slot of "
        + "5th level or higher, the bludgeoning damage increases by 1d8 for each slot level above "
        + "4th."),
    LOCATE_CREATURE(MagicSchool.DIVINATION, 4, "1 action", "Self",
        "V, S, M ", "up to 1 hour", "Describe or name a creature that is familiar to you. You "
        + "sense the direction to the creature's location, as long as that creature is within "
        + "1,000 feet of you. If the creature is moving, you know the direction of its movement. "
        + "The spell can locate a specific creature known to you, or the nearest creature of a "
        + "specific kind (such as a human or a unicorn), so long as you have seen such a creature "
        + "up close--within 30 feet--at least once. If the creature you described or named is in "
        + "a different form, such as being under the effects of a polymorph spell, this spell "
        + "doesn't locate the creature. This spell can't locate a creature if running water at "
        + "least 10 feet wide blocks a direct path between you and the creature."),
    PHANTASMAL_KILLER(MagicSchool.ILLUSION, 4, "1 action", "120 feet",
        "V, S", "up to 1 minute", "You tap into the nightmares of a creature you can see "
        + "within range and create an illusory manifestation of its deepest fears, visible only "
        + "to that creature. The target must make a Wisdom saving throw. On a failed save, the "
        + "target becomes frightened for the duration. At the end of each of the target's turns "
        + "before the spell ends, the target must succeed on a Wisdom saving throw or take 4d10 "
        + "psychic damage. On a successful save, the spell ends. At Higher Levels. When you cast "
        + "this spell using a spell slot of 5th level or higher, the damage increases by 1d10 for "
        + "each slot level above 4th."),
    POLYMORPH(MagicSchool.TRANSMUTATION, 4, "1 action", "60 feet",
        "V, S, M ", "up to 1 hour", "This spell transforms a creature that you can see within "
        + "range into a new form. An unwilling creature must make a Wisdom saving throw to avoid "
        + "the effect. The spell has no effect on a shapechanger or a creature with 0 hit points. "
        + "The transformation lasts for the duration, or until the target drops to 0 hit points "
        + "or dies. The new form can be any beast whose challenge rating is equal to or less than "
        + "the target's (or the target's level, if it doesn't have a challenge rating). The "
        + "target's game statistics, including mental ability scores, are replaced by the "
        + "statistics of the chosen beast. It retains its alignment and personality. The target "
        + "assumes the hit points of its new form. When it reverts to its normal form, the "
        + "creature returns to the number of hit points it had before it transformed. If it "
        + "reverts as a result of dropping to 0 hit points, any excess damage carries over to its "
        + "normal form. As long as the excess damage doesn't reduce the creature's normal form to "
        + "0 hit points, it isn't knocked unconscious. The creature is limited in the actions it "
        + "can perform by the nature of its new form, and it can't speak, cast spells, or take "
        + "any other action that requires hands or speech. The target's gear melds into the new "
        + "form. The creature can't activate, use, wield, or otherwise benefit from any of its "
        + "equipment."),
    PRIVATE_SANCTUM(MagicSchool.ABJURATION, 4, "10 minutes", "120 feet",
        "V, S, M ", "24 hours", "You make an area within range magically secure. The area is "
        + "a cube that can be as small as 5 feet to as large as 100 feet on each side. The spell "
        + "lasts for the duration or until you use an action to dismiss it. When you cast the "
        + "spell, you decide what sort of security the spell provides, choosing any or all of the "
        + "following properties: Sound can't pass through the barrier at the edge of the warded "
        + "area. The barrier of the warded area appears dark and foggy, preventing vision "
        + "(including darkvision) through it. Sensors created by divination spells can't appear "
        + "inside the protected area or pass through the barrier at its perimeter. Creatures in "
        + "the area can't be targeted by divination spells. Nothing can teleport into or out of "
        + "the warded area. Planar travel is blocked within the warded area. Casting this spell "
        + "on the same spot every day for a year makes this effect permanent. At Higher Levels. "
        + "When you cast this spell using a spell slot of 5th level or higher, you can increase "
        + "the size of the cube by 100 feet for each slot level beyond 4th. Thus you could "
        + "protect a cube that can be up to 200 feet on one side by using a spell slot of 5th "
        + "level."),
    RESILIENT_SPHERE(MagicSchool.EVOCATION, 4, "1 action", "30 feet",
        "V, S, M ", "up to 1 minute", "A sphere of shimmering force encloses a creature or "
        + "object of Large size or smaller within range. An unwilling creature must make a "
        + "Dexterity saving throw. On a failed save, the creature is enclosed for the duration. "
        + "Nothing--not physical objects, energy, or other spell effects--can pass through the "
        + "barrier, in or out, though a creature in the sphere can breathe there. The sphere is "
        + "immune to all damage, and a creature or object inside can't be damaged by attacks or "
        + "effects originating from outside, nor can a creature inside the sphere damage anything "
        + "outside it. The sphere is weightless and just large enough to contain the creature or "
        + "object inside. An enclosed creature can use its action to push against the sphere's "
        + "walls and thus roll the sphere at up to half the creature's speed. Similarly, the "
        + "globe can be picked up and moved by other creatures. A disintegrate spell targeting "
        + "the globe destroys it without harming anything inside it."),
    SECRET_CHEST(MagicSchool.CONJURATION, 4, "1 action", "Touch",
        "V, S, M ", "Instantaneous", "You hide a chest, and all its contents, on the Ethereal "
        + "Plane. You must touch the chest and the miniature replica that serves as a material "
        + "component for the spell. The chest can contain up to 12 cubic feet of nonliving "
        + "material (3 feet by 2 feet by 2 feet). While the chest remains on the Ethereal Plane, "
        + "you can use an action and touch the replica to recall the chest. It appears in an "
        + "unoccupied space on the ground within 5 feet of you. You can send the chest back to "
        + "the Ethereal Plane by using an action and touching both the chest and the replica. "
        + "After 60 days, there is a cumulative 5 percent chance per day that the spell's effect "
        + "ends. This effect ends if you cast this spell again, if the smaller replica chest is "
        + "destroyed, or if you choose to end the spell as an action. If the spell ends and the "
        + "larger chest is on the Ethereal Plane, it is irretrievably lost."),
    STONESKIN(MagicSchool.ABJURATION, 4, "1 action", "Touch", "V, S, M ",
        "up to 1 hour", "This spell turns the flesh of a willing creature you touch as hard "
        + "as stone. Until the spell ends, the target has resistance to nonmagical bludgeoning, "
        + "piercing, and slashing damage."),
    STONE_SHAPE(MagicSchool.TRANSMUTATION, 4, "1 action", "Touch",
        "V, S, M ", "Instantaneous", "You touch a stone object of Medium size or smaller or a "
        + "section of stone no more than 5 feet in any dimension and form it into any shape that "
        + "suits your purpose. So, for example, you could shape a large rock into a weapon, idol, "
        + "or coffer, or make a small passage through a wall, as long as the wall is less than 5 "
        + "feet thick. You could also shape a stone door or its frame to seal the door shut. The "
        + "object you create can have up to two hinges and a latch, but finer mechanical detail "
        + "isn't possible."),
    WALL_OF_FIRE(MagicSchool.EVOCATION, 4, "1 action", "120 feet",
        "V, S, M ", "up to 1 minute", "You create a wall of fire on a solid surface within "
        + "range. You can make the wall up to 60 feet long, 20 feet high, and 1 foot thick, or a "
        + "ringed wall up to 20 feet in diameter, 20 feet high, and 1 foot thick. The wall is "
        + "opaque and lasts for the duration. When the wall appears, each creature within its "
        + "area must make a Dexterity saving throw. On a failed save, a creature takes 5d8 fire "
        + "damage, or half as much damage on a successful save. One side of the wall, selected by "
        + "you when you cast this spell, deals 5d8 fire damage to each creature that ends its "
        + "turn within 10 feet of that side or inside the wall. A creature takes the same damage "
        + "when it enters the wall for the first time on a turn or ends its turn there. The other "
        + "side of the wall deals no damage. At Higher Levels. When you cast this spell using a "
        + "spell slot of 5th level or higher, the damage increases by 1d8 for each slot level "
        + "above 4th."),
    ANIMATE_OBJECTS(MagicSchool.TRANSMUTATION, 5, "1 action", "120 feet",
        "V, S", "up to 1 minute", "Objects come to life at your command. Choose up to ten "
        + "nonmagical objects within range that are not being worn or carried. Medium targets "
        + "count as two objects, Large targets count as four objects, Huge targets count as eight "
        + "objects. You can't animate any object larger than Huge. Each target animates and "
        + "becomes a creature under your control until the spell ends or until reduced to 0 hit "
        + "points. As a bonus action, you can mentally command any creature you made with this "
        + "spell if the creature is within 500 feet of you (if you control multiple creatures, "
        + "you can command any or all of them at the same time, issuing the same command to each "
        + "one). You decide what action the creature will take and where it will move during its "
        + "next turn, or you can issue a general command, such as to guard a particular chamber "
        + "or corridor. If you issue no commands, the creature only defends itself against "
        + "hostile creatures. Once given an order, the creature continues to follow it until its "
        + "task is complete. Animated Object Statistics Size HP AC Str Dex Attack Tiny 20 18 4 18 "
        + "+8 to hit, 1d4 + 4 damage Small 25 16 6 14 +6 to hit, 1d8 + 2 damage Medium 40 13 10 "
        + "12 +5 to hit, 2d6 + 1 damage Large 50 10 14 10 +6 to hit, 2d10 + 2 damage Huge 80 10 "
        + "18 6 +8 to hit, 2d12 + 4 damage An animated object is a construct with AC, hit points, "
        + "attacks, Strength, and Dexterity determined by its size. Its Constitution is 10 and "
        + "its Intelligence and Wisdom are 3, and its Charisma is 1. Its speed is 30 feet; if the "
        + "object lacks legs or other appendages it can use for locomotion, it instead has a "
        + "flying speed of 30 feet and can hover. If the object is securely attached to a surface "
        + "or a larger object, such as a chain bolted to a wall, its speed is 0. It has "
        + "blindsight with a radius of 30 feet and is blind beyond that distance. When the "
        + "animated object drops to 0 hit points, it reverts to its original object form, and any "
        + "remaining damage carries over to its original object form. If you command an object to "
        + "attack, it can make a single melee attack against a creature within 5 feet of it. It "
        + "makes a slam attack with an attack bonus and bludgeoning damage determined by its "
        + "size. The GM might rule that a specific object inflicts slashing or piercing damage "
        + "based on its form. At Higher Levels. If you cast this spell using a spell slot of 6th "
        + "level or higher, you can animate two additional objects for each slot level above 5th."),
    ANTILIFE_SHELL(MagicSchool.ABJURATION, 5, "1 action", "Self (10-foot radius)",
        "V, S", "up to 1 hour", "A shimmering barrier extends out from you in a 10- foot "
        + "radius and moves with you, remaining centered on you and hedging out creatures other "
        + "than undead and constructs. The barrier lasts for the duration. The barrier prevents "
        + "an affected creature from passing or reaching through. An affected creature can cast "
        + "spells or make attacks with ranged or reach weapons through the barrier. If you move "
        + "so that an affected creature is forced to pass through the barrier, the spell ends."),
    AWAKEN(MagicSchool.TRANSMUTATION, 5, "8 hours", "Touch", "V, S, M ",
        "Instantaneous", "After spending the casting time tracing magical pathways within a "
        + "precious gemstone, you touch a Huge or smaller beast or plant. The target must have "
        + "either no Intelligence score or an Intelligence of 3 or less. The target gains an "
        + "Intelligence of 10. The target also gains the ability to speak one language you know. "
        + "If the target is a plant, it gains the ability to move its limbs, roots, vines, "
        + "creepers, and so forth, and it gains senses similar to a human's. Your GM chooses "
        + "statistics appropriate for the awakened plant, such as the statistics for the awakened "
        + "shrub or the awakened tree. The awakened beast or plant is charmed by you for 30 days "
        + "or until you or your companions do anything harmful to it. When the charmed condition "
        + "ends, the awakened creature chooses whether to remain friendly to you, based on how "
        + "you treated it while it was charmed."),
    CLOUDKILL(MagicSchool.CONJURATION, 5, "1 action", "120 feet",
        "V, S", "up to 10 minutes", "You create a 20-foot-radius sphere of poisonous, "
        + "yellow-green fog centered on a point you choose within range. The fog spreads around "
        + "corners. It lasts for the duration or until strong wind disperses the fog, ending the "
        + "spell. Its area is heavily obscured. When a creature enters the spell's area for the "
        + "first time on a turn or starts its turn there, that creature must make a Constitution "
        + "saving throw. The creature takes 5d8 poison damage on a failed save, or half as much "
        + "damage on a successful one. Creatures are affected even if they hold their breath or "
        + "don't need to breathe. The fog moves 10 feet away from you at the start of each of "
        + "your turns, rolling along the surface of the ground. The vapors, being heavier than "
        + "air, sink to the lowest level of the land, even pouring down openings. At Higher "
        + "Levels. When you cast this spell using a spell slot of 6th level or higher, the damage "
        + "increases by 1d8 for each slot level above 5th."),
    COMMUNE(MagicSchool.DIVINATION, 5, "1 minute", "Self", "V, S, M ",
        "1 minute", "You contact your deity or a divine proxy and ask up to three questions "
        + "that can be answered with a yes or no. You must ask your questions before the spell "
        + "ends. You receive a correct answer for each question. Divine beings aren't necessarily "
        + "omniscient, so you might receive 'unclear' as an answer if a question pertains to "
        + "information that lies beyond the deity's knowledge. In a case where a one-word answer "
        + "could be misleading or contrary to the deity's interests, the GM might offer a short "
        + "phrase as an answer instead. If you cast the spell two or more times before finishing "
        + "your next long rest, there is a cumulative 25 percent chance for each casting after "
        + "the first that you get no answer. The GM makes this roll in secret."),
    COMMUNE_WITH_NATURE(MagicSchool.DIVINATION, 5, "1 minute",
        "Self", "V, S", "Instantaneous", "You briefly become one with nature and gain "
        + "knowledge of the surrounding territory. In the outdoors, the spell gives you knowledge "
        + "of the land within 3 miles of you. In caves and other natural underground settings, "
        + "the radius is limited to 300 feet. The spell doesn't function where nature has been "
        + "replaced by construction, such as in dungeons and towns. You instantly gain knowledge "
        + "of up to three facts of your choice about any of the following subjects as they relate "
        + "to the area: terrain and bodies of water prevalent plants, minerals, animals, or "
        + "peoples powerful celestials, fey, fiends, elementals, or undead influence from other "
        + "planes of existence buildings For example, you could determine the location of "
        + "powerful undead in the area, the location of major sources of safe drinking water, and "
        + "the location of any nearby towns."),
    CONJURE_ELEMENTAL(MagicSchool.CONJURATION, 5, "1 minute", "90 feet",
        "V, S, M ", "up to 1 hour", "You call forth an elemental servant. Choose an area of "
        + "air, earth, fire, or water that fills a 10-foot cube within range. An elemental of "
        + "challenge rating 5 or lower appropriate to the area you chose appears in an unoccupied "
        + "space within 10 feet of it. For example, a fire elemental emerges from a bonfire, and "
        + "an earth elemental rises up from the ground. The elemental disappears when it drops to "
        + "0 hit points or when the spell ends. The elemental is friendly to you and your "
        + "companions for the duration. Roll initiative for the elemental, which has its own "
        + "turns. It obeys any verbal commands that you issue to it (no action required by you). "
        + "If you don't issue any commands to the elemental, it defends itself from hostile "
        + "creatures but otherwise takes no actions. If your concentration is broken, the "
        + "elemental doesn't disappear. Instead, you lose control of the elemental, it becomes "
        + "hostile toward you and your companions, and it might attack. An uncontrolled elemental "
        + "can't be dismissed by you, and it disappears 1 hour after you summoned it. The GM has "
        + "the elemental's statistics. At Higher Levels. When you cast this spell using a spell "
        + "slot of 6th level or higher, the challenge rating increases by 1 for each slot level "
        + "above 5th."),
    CONTACT_OTHER_PLANE(MagicSchool.DIVINATION, 5, "1 minute",
        "Self", "V", "1 minute", "You mentally contact a demigod, the spirit of a long- dead "
        + "sage, or some other mysterious entity from another plane. Contacting this extraplanar "
        + "intelligence can strain or even break your mind. When you cast this spell, make a DC "
        + "15 Intelligence saving throw. On a failure, you take 6d6 psychic damage and are insane "
        + "until you finish a long rest. While insane, you can't take actions, can't understand "
        + "what other creatures say, can't read, and speak only in gibberish. A greater "
        + "restoration spell cast on you ends this effect. On a successful save, you can ask the "
        + "entity up to five questions. You must ask your questions before the spell ends. The GM "
        + "answers each question with one word, such as 'yes,' 'no,' 'maybe,' 'never,' "
        + "'irrelevant,' or 'unclear' (if the entity doesn't know the answer to the question). If "
        + "a one-word answer would be misleading, the GM might instead offer a short phrase as an "
        + "answer."),
    CONTAGION(MagicSchool.NECROMANCY, 5, "1 action", "Touch", "V, S",
        "7 days", "Your touch inflicts disease. Make a melee spell attack against a creature "
        + "within your reach. On a hit, you afflict the creature with a disease of your choice "
        + "from any of the ones described below. At the end of each of the target's turns, it "
        + "must make a Constitution saving throw. After failing three of these saving throws, the "
        + "disease's effects last for the duration, and the creature stops making these saves. "
        + "After succeeding on three of these saving throws, the creature recovers from the "
        + "disease, and the spell ends. Since this spell induces a natural disease in its target, "
        + "any effect that removes a disease or otherwise ameliorates a disease's effects apply "
        + "to it. Blinding Sickness. Pain grips the creature's mind, and its eyes turn milky "
        + "white. The creature has disadvantage on Wisdom checks and Wisdom saving throws and is "
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
        + "creature takes damage, it is stunned until the end of its next turn."),
    CREATION(MagicSchool.ILLUSION, 5, "1 minute", "30 feet", "V, S, M ",
        "Special", "You pull wisps of shadow material from the Shadowfell to create a "
        + "nonliving object of vegetable matter within range: soft goods, rope, wood, or "
        + "something similar. You can also use this spell to create mineral objects such as "
        + "stone, crystal, or metal. The object created must be no larger than a 5-foot cube, and "
        + "the object must be of a form and material that you have seen before. The duration "
        + "depends on the object's material. If the object is composed of multiple materials, use "
        + "the shortest duration. Material Duration Vegetable matter 1 day Stone or crystal 12 "
        + "hours Precious metals 1 hour Gems 10 minutes Adamantine or mithral 1 minute Using any "
        + "material created by this spell as another spell's material component causes that spell "
        + "to fail. At Higher Levels. When you cast this spell using a spell slot of 6th level or "
        + "higher, the cube increases by 5 feet for each slot level above 5th."),
    DISPEL_EVIL_AND_GOOD(MagicSchool.ABJURATION, 5, "1 action",
        "Self", "V, S, M ", "up to 1 minute", "Shimmering energy surrounds and protects you "
        + "from fey, undead, and creatures originating from beyond the Material Plane. For the "
        + "duration, celestials, elementals, fey, fiends, and undead have disadvantage on attack "
        + "rolls against you. You can end the spell early by using either of the following "
        + "special functions. Break Enchantment. As your action, you touch a creature you can "
        + "reach that is charmed, frightened, or possessed by a celestial, an elemental, a fey, a "
        + "fiend, or an undead. The creature you touch is no longer charmed, frightened, or "
        + "possessed by such creatures. Dismissal. As your action, make a melee spell attack "
        + "against a celestial, an elemental, a fey, a fiend, or an undead you can reach. On a "
        + "hit, you attempt to drive the creature back to its home plane. The creature must "
        + "succeed on a Charisma saving throw or be sent back to its home plane (if it isn't "
        + "there already). If they aren't on their home plane, undead are sent to the Shadowfell, "
        + "and fey are sent to the Feywild."),
    DOMINATE_PERSON(MagicSchool.ENCHANTMENT, 5, "1 action", "60 feet",
        "V, S", "up to 1 minute", "You attempt to beguile a humanoid that you can see within "
        + "range. It must succeed on a Wisdom saving throw or be charmed by you for the duration. "
        + "If you or creatures that are friendly to you are fighting it, it has advantage on the "
        + "saving throw. While the target is charmed, you have a telepathic link with it as long "
        + "as the two of you are on the same plane of existence. You can use this telepathic link "
        + "to issue commands to the creature while you are conscious (no action required), which "
        + "it does its best to obey. You can specify a simple and general course of action, such "
        + "as 'Attack that creature,' 'Run over there,' or 'Fetch that object.' If the creature "
        + "completes the order and doesn't receive further direction from you, it defends and "
        + "preserves itself to the best of its ability. You can use your action to take total and "
        + "precise control of the target. Until the end of your next turn, the creature takes "
        + "only the actions you choose, and doesn't do anything that you don't allow it to do. "
        + "During this time you can also cause the creature to use a reaction, but this requires "
        + "you to use your own reaction as well. Each time the target takes damage, it makes a "
        + "new Wisdom saving throw against the spell. If the saving throw succeeds, the spell "
        + "ends. At Higher Levels. When you cast this spell using a 6th-level spell slot, the "
        + "duration is concentration, up to 10 minutes. When you use a 7th-level spell slot, the "
        + "duration is concentration, up to 1 hour. When you use a spell slot of 8th level or "
        + "higher, the duration is concentration, up to 8 hours."),
    DREAM(MagicSchool.ILLUSION, 5, "1 minute", "Special", "V, S, M ",
        "8 hours", "This spell shapes a creature's dreams. Choose a creature known to you as "
        + "the target of this spell. The target must be on the same plane of existence as you. "
        + "Creatures that don't sleep, such as elves, can't be contacted by this spell. You, or a "
        + "willing creature you touch, enters a trance state, acting as a messenger. While in the "
        + "trance, the messenger is aware of his or her surroundings, but can't take actions or "
        + "move. If the target is asleep, the messenger appears in the target's dreams and can "
        + "converse with the target as long as it remains asleep, through the duration of the "
        + "spell. The messenger can also shape the environment of the dream, creating landscapes, "
        + "objects, and other images. The messenger can emerge from the trance at any time, "
        + "ending the effect of the spell early. The target recalls the dream perfectly upon "
        + "waking. If the target is awake when you cast the spell, the messenger knows it, and "
        + "can either end the trance (and the spell) or wait for the target to fall asleep, at "
        + "which point the messenger appears in the target's dreams. You can make the messenger "
        + "appear monstrous and terrifying to the target. If you do, the messenger can deliver a "
        + "message of no more than ten words and then the target must make a Wisdom saving throw. "
        + "On a failed save, echoes of the phantasmal monstrosity spawn a nightmare that lasts "
        + "the duration of the target's sleep and prevents the target from gaining any benefit "
        + "from that rest. In addition, when the target wakes up, it takes 3d6 psychic damage. If "
        + "you have a body part, lock of hair, clipping from a nail, or similar portion of the "
        + "target's body, the target makes its saving throw with disadvantage."),
    FLAME_STRIKE(MagicSchool.EVOCATION, 5, "1 action", "60 feet",
        "V, S, M ", "Instantaneous", "A vertical column of divine fire roars down from the "
        + "heavens in a location you specify. Each creature in a 10-foot-radius, 40-foot-high "
        + "cylinder centered on a point within range must make a Dexterity saving throw. A "
        + "creature takes 4d6 fire damage and 4d6 radiant damage on a failed save, or half as "
        + "much damage on a successful one. At Higher Levels. When you cast this spell using a "
        + "spell slot of 6th level or higher, the fire damage or the radiant damage (your choice) "
        + "increases by 1d6 for each slot level above 5th."),
    GEAS(MagicSchool.ENCHANTMENT, 5, "1 minute", "60 feet", "V",
        "30 days", "You place a magical command on a creature that you can see within range, "
        + "forcing it to carry out some service or refrain from some action or course of activity "
        + "as you decide. If the creature can understand you, it must succeed on a Wisdom saving "
        + "throw or become charmed by you for the duration. While the creature is charmed by you, "
        + "it takes 5d10 psychic damage each time it acts in a manner directly counter to your "
        + "instructions, but no more than once each day. A creature that can't understand you is "
        + "unaffected by the spell. You can issue any command you choose, short of an activity "
        + "that would result in certain death. Should you issue a suicidal command, the spell "
        + "ends. You can end the spell early by using an action to dismiss it. A remove curse, "
        + "greater restoration, or wish spell also ends it. At Higher Levels. When you cast this "
        + "spell using a spell slot of 7th or 8th level, the duration is 1 year. When you cast "
        + "this spell using a spell slot of 9th level, the spell lasts until it is ended by one "
        + "of the spells mentioned above."),
    GREATER_RESTORATION(MagicSchool.ABJURATION, 5, "1 action",
        "Touch", "V, S, M ", "Instantaneous", "You imbue a creature you touch with positive "
        + "energy to undo a debilitating effect. You can reduce the target's exhaustion level by "
        + "one, or end one of the following effects on the target: One effect that charmed or "
        + "petrified the target One curse, including the target's attunement to a cursed magic "
        + "item Any reduction to one of the target's ability scores One effect reducing the "
        + "target's hit point maximum"),
    HALLOW(MagicSchool.EVOCATION, 5, "24 hours", "Touch", "V, S, M ",
        "Until dispelled", "You touch a point and infuse an area around it with holy (or "
        + "unholy) power. The area can have a radius up to 60 feet, and the spell fails if the "
        + "radius includes an area already under the effect a hallow spell. The affected area is "
        + "subject to the following effects. First, celestials, elementals, fey, fiends, and "
        + "undead can't enter the area, nor can such creatures charm, frighten, or possess "
        + "creatures within it. Any creature charmed, frightened, or possessed by such a creature "
        + "is no longer charmed, frightened, or possessed upon entering the area. You can exclude "
        + "one or more of those types of creatures from this effect. Second, you can bind an "
        + "extra effect to the area. Choose the effect from the following list, or choose an "
        + "effect offered by the GM. Some of these effects apply to creatures in the area; you "
        + "can designate whether the effect applies to all creatures, creatures that follow a "
        + "specific deity or leader, or creatures of a specific sort, such as orcs or trolls. "
        + "When a creature that would be affected enters the spell's area for the first time on a "
        + "turn or starts its turn there, it can make a Charisma saving throw. On a success, the "
        + "creature ignores the extra effect until it leaves the area. Courage. Affected "
        + "creatures can't be frightened while in the area. Darkness. Darkness fills the area. "
        + "Normal light, as well as magical light created by spells of a lower level than the "
        + "slot you used to cast this spell, can't illuminate the area. Daylight. Bright light "
        + "fills the area. Magical darkness created by spells of a lower level than the slot you "
        + "used to cast this spell can't extinguish the light. Energy Protection. Affected "
        + "creatures in the area have resistance to one damage type of your choice, except for "
        + "bludgeoning, piercing, or slashing. Energy Vulnerability. Affected creatures in the "
        + "area have vulnerability to one damage type of your choice, except for bludgeoning, "
        + "piercing, or slashing. Everlasting Rest. Dead bodies interred in the area can't be "
        + "turned into undead. Extradimensional Interference. Affected creatures can't move or "
        + "travel using teleportation or by extradimensional or interplanar means. Fear. Affected "
        + "creatures are frightened while in the area. Silence. No sound can emanate from within "
        + "the area, and no sound can reach into it. Tongues. Affected creatures can communicate "
        + "with any other creature in the area, even if they don't share a common language."),
    HOLD_MONSTER(MagicSchool.ENCHANTMENT, 5, "1 action", "90 feet",
        "V, S, M ", "up to 1 minute", "Choose a creature that you can see within range. The "
        + "target must succeed on a Wisdom saving throw or be paralyzed for the duration. This "
        + "spell has no effect on undead. At the end of each of its turns, the target can make "
        + "another Wisdom saving throw. On a success, the spell ends on the target. At Higher "
        + "Levels. When you cast this spell using a spell slot of 6th level or higher, you can "
        + "target one additional creature for each slot level above 5th. The creatures must be "
        + "within 30 feet of each other when you target them."),
    INSECT_PLAGUE(MagicSchool.CONJURATION, 5, "1 action", "300 feet",
        "V, S, M ", "up to 10 minutes", "Swarming, biting locusts fill a 20-foot-radius "
        + "sphere centered on a point you choose within range. The sphere spreads around corners. "
        + "The sphere remains for the duration, and its area is lightly obscured. The sphere's "
        + "area is difficult terrain. When the area appears, each creature in it must make a "
        + "Constitution saving throw. A creature takes 4d10 piercing damage on a failed save, or "
        + "half as much damage on a successful one. A creature must also make this saving throw "
        + "when it enters the spell's area for the first time on a turn or ends its turn there. "
        + "At Higher Levels. When you cast this spell using a spell slot of 6th level or higher, "
        + "the damage increases by 1d10 for each slot level above 5th."),
    LEGEND_LORE(MagicSchool.DIVINATION, 5, "10 minutes", "Self",
        "V, S, M ", "Instantaneous", "Name or describe a person, place, or object. The spell "
        + "brings to your mind a brief summary of the significant lore about the thing you named. "
        + "The lore might consist of current tales, forgotten stories, or even secret lore that "
        + "has never been widely known. If the thing you named isn't of legendary importance, you "
        + "gain no information. The more information you already have about the thing, the more "
        + "precise and detailed the information you receive is. The information you learn is "
        + "accurate but might be couched in figurative language. For example, if you have a "
        + "mysterious magic axe on hand, the spell might yield this information: “Woe to the "
        + "evildoer whose hand touches the axe, for even the haft slices the hand of the evil "
        + "ones. Only a true Child of Stone, lover and beloved of Moradin, may awaken the true "
        + "powers of the axe, and only with the sacred word Rudnogg on the lips.'"),
    MASS_CURE_WOUNDS(MagicSchool.EVOCATION, 5, "1 action", "60 feet",
        "V, S", "Instantaneous", "A wave of healing energy washes out from a point of your "
        + "choice within range. Choose up to six creatures in a 30-foot-radius sphere centered on "
        + "that point. Each target regains hit points equal to 3d8 + your spellcasting ability "
        + "modifier. This spell has no effect on undead or constructs. At Higher Levels. When you "
        + "cast this spell using a spell slot of 6th level or higher, the healing increases by "
        + "1d8 for each slot level above 5th."),
    MISLEAD(MagicSchool.ILLUSION, 5, "1 action", "Self", "S", "up to 1 hour",
        "You become invisible at the same time that an illusory double of you appears where "
        + "you are standing. The double lasts for the duration, but the invisibility ends if you "
        + "attack or cast a spell. You can use your action to move your illusory double up to "
        + "twice your speed and make it gesture, speak, and behave in whatever way you choose. "
        + "You can see through its eyes and hear through its ears as if you were located where it "
        + "is. On each of your turns as a bonus action, you can switch from using its senses to "
        + "using your own, or back again. While you are using its senses, you are blinded and "
        + "deafened in regard to your own surroundings."),
    MODIFY_MEMORY(MagicSchool.ENCHANTMENT, 5, "1 action", "30 feet",
        "V, S", "up to 1 minute", "You attempt to reshape another creature's memories. One "
        + "creature that you can see must make a Wisdom saving throw. If you are fighting the "
        + "creature, it has advantage on the saving throw. On a failed save, the target becomes "
        + "charmed by you for the duration. The charmed target is incapacitated and unaware of "
        + "its surroundings, though it can still hear you. If it takes any damage or is targeted "
        + "by another spell, this spell ends, and none of the target's memories are modified. "
        + "While this charm lasts, you can affect the target's memory of an event that it "
        + "experienced within the last 24 hours and that lasted no more than 10 minutes. You can "
        + "permanently eliminate all memory of the event, allow the target to recall the event "
        + "with perfect clarity and exacting detail, change its memory of the details of the "
        + "event, or create a memory of some other event. You must speak to the target to "
        + "describe how its memories are affected, and it must be able to understand your "
        + "language for the modified memories to take root. Its mind fills in any gaps in the "
        + "details of your description. If the spell ends before you have finished describing the "
        + "modified memories, the creature's memory isn't altered. Otherwise, the modified "
        + "memories take hold when the spell ends. A modified memory doesn't necessarily affect "
        + "how a creature behaves, particularly if the memory contradicts the creature's natural "
        + "inclinations, alignment, or beliefs. An illogical modified memory, such as implanting "
        + "a memory of how much the creature enjoyed dousing itself in acid, is dismissed, "
        + "perhaps as a bad dream. The GM might deem a modified memory too nonsensical to affect "
        + "a creature in a significant manner. A remove curse or greater restoration spell cast "
        + "on the target restores the creature's true memory. At Higher Levels. If you cast this "
        + "spell using a spell slot of 6th level or higher, you can alter the target's memories "
        + "of an event that took place up to 7 days ago (6th level), 30 days ago (7th level), 1 "
        + "year ago (8th level), or any time in the creature's past (9th level)."),
    PASSWALL(MagicSchool.TRANSMUTATION, 5, "1 action", "30 feet",
        "V, S, M ", "1 hour", "A passage appears at a point of your choice that you can see "
        + "on a wooden, plaster, or stone surface (such as a wall, a ceiling, or a floor) within "
        + "range, and lasts for the duration. You choose the opening's dimensions: up to 5 feet "
        + "wide, 8 feet tall, and 20 feet deep. The passage creates no instability in a structure "
        + "surrounding it. When the opening disappears, any creatures or objects still in the "
        + "passage created by the spell are safely ejected to an unoccupied space nearest to the "
        + "surface on which you cast the spell."),
    PLANAR_BINDING(MagicSchool.ABJURATION, 5, "1 hour", "60 feet",
        "V, S, M ", "24 hours", "With this spell, you attempt to bind a celestial, an "
        + "elemental, a fey, or a fiend to your service. The creature must be within range for "
        + "the entire casting of the spell. (Typically, the creature is first summoned into the "
        + "center of an inverted magic circle in order to keep it trapped while this spell is "
        + "cast.) At the completion of the casting, the target must make a Charisma saving throw. "
        + "On a failed save, it is bound to serve you for the duration. If the creature was "
        + "summoned or created by another spell, that spell's duration is extended to match the "
        + "duration of this spell. A bound creature must follow your instructions to the best of "
        + "its ability. You might command the creature to accompany you on an adventure, to guard "
        + "a location, or to deliver a message. The creature obeys the letter of your "
        + "instructions, but if the creature is hostile to you, it strives to twist your words to "
        + "achieve its own objectives. If the creature carries out your instructions completely "
        + "before the spell ends, it travels to you to report this fact if you are on the same "
        + "plane of existence. If you are on a different plane of existence, it returns to the "
        + "place where you bound it and remains there until the spell ends. At Higher Levels. "
        + "When you cast this spell using a spell slot of a higher level, the duration increases "
        + "to 10 days with a 6th-level slot, to 30 days with a 7th- level slot, to 180 days with "
        + "an 8th-level slot, and to a year and a day with a 9th-level spell slot."),
    RAISE_DEAD(MagicSchool.NECROMANCY, 5, "1 hour", "Touch", "V, S, M ",
        "Instantaneous", "You return a dead creature you touch to life, provided that it has "
        + "been dead no longer than 10 days. If the creature's soul is both willing and at "
        + "liberty to rejoin the body, the creature returns to life with 1 hit point. This spell "
        + "also neutralizes any poisons and cures nonmagical diseases that affected the creature "
        + "at the time it died. This spell doesn't, however, remove magical diseases, curses, or "
        + "similar effects; if these aren't first removed prior to casting the spell, they take "
        + "effect when the creature returns to life. The spell can't return an undead creature to "
        + "life. This spell closes all mortal wounds, but it doesn't restore missing body parts. "
        + "If the creature is lacking body parts or organs integral for its survival--its head, "
        + "for instance--the spell automatically fails. Coming back from the dead is an ordeal. "
        + "The target takes a −4 penalty to all attack rolls, saving throws, and ability checks. "
        + "Every time the target finishes a long rest, the penalty is reduced by 1 until it "
        + "disappears."),
    REINCARNATE(MagicSchool.TRANSMUTATION, 5, "1 hour", "Touch",
        "V, S, M ", "Instantaneous", "You touch a dead humanoid or a piece of a dead "
        + "humanoid. Provided that the creature has been dead no longer than 10 days, the spell "
        + "forms a new adult body for it and then calls the soul to enter that body. If the "
        + "target's soul isn't free or willing to do so, the spell fails. The magic fashions a "
        + "new body for the creature to inhabit, which likely causes the creature's race to "
        + "change. The GM rolls a d100 and consults the following table to determine what form "
        + "the creature takes when restored to life, or the GM chooses a form. d100 Race 01-04 "
        + "Dragonborn 05-13 Dwarf, hill 14-21 Dwarf, mountain 22-25 Elf, dark 26-34 Elf, high "
        + "35-42 Elf, wood 43-46 Gnome, forest 47-52 Gnome, rock 53-56 Half-elf 57-60 Half-orc "
        + "61-68 Halfling, lightfoot 69-76 Halfling, stout 77-96 Human 97-100 Tiefling The "
        + "reincarnated creature recalls its former life and experiences. It retains the "
        + "capabilities it had in its original form, except it exchanges its original race for "
        + "the new one and changes its racial traits accordingly."),
    SCRYING(MagicSchool.DIVINATION, 5, "10 minutes", "Self", "V, S, M ",
        "up to 10 minutes", "You can see and hear a particular creature you choose that is on "
        + "the same plane of existence as you. The target must make a Wisdom saving throw, which "
        + "is modified by how well you know the target and the sort of physical connection you "
        + "have to it. If a target knows you're casting this spell, it can fail the saving throw "
        + "voluntarily if it wants to be observed. Knowledge Save Modifier Secondhand (you have "
        + "heard of the target) +5 Firsthand (you have met the target) 0 Familiar (you know the "
        + "target well) -5 Connection Save Modifier Likeness or picture -2 Possession or garment "
        + "-4 Body part, lock of hair, bit of nail, or the like -10 On a successful save, the "
        + "target isn't affected, and you can't use this spell against it again for 24 hours. On "
        + "a failed save, the spell creates an invisible sensor within 10 feet of the target. You "
        + "can see and hear through the sensor as if you were there. The sensor moves with the "
        + "target, remaining within 10 feet of it for the duration. A creature that can see "
        + "invisible objects sees the sensor as a luminous orb about the size of your fist. "
        + "Instead of targeting a creature, you can choose a location you have seen before as the "
        + "target of this spell. When you do, the sensor appears at that location and doesn't "
        + "move."),
    SEEMING(MagicSchool.ILLUSION, 5, "1 action", "30 feet", "V, S",
        "8 hours", "This spell allows you to change the appearance of any number of creatures "
        + "that you can see within range. You give each target you choose a new, illusory "
        + "appearance. An unwilling target can make a Charisma saving throw, and if it succeeds, "
        + "it is unaffected by this spell. The spell disguises physical appearance as well as "
        + "clothing, armor, weapons, and equipment. You can make each creature seem 1 foot "
        + "shorter or taller and appear thin, fat, or in between. You can't change a target's "
        + "body type, so you must choose a form that has the same basic arrangement of limbs. "
        + "Otherwise, the extent of the illusion is up to you. The spell lasts for the duration, "
        + "unless you use your action to dismiss it sooner. The changes wrought by this spell "
        + "fail to hold up to physical inspection. For example, if you use this spell to add a "
        + "hat to a creature's outfit, objects pass through the hat, and anyone who touches it "
        + "would feel nothing or would feel the creature's head and hair. If you use this spell "
        + "to appear thinner than you are, the hand of someone who reaches out to touch you would "
        + "bump into you while it was seemingly still in midair. A creature can use its action to "
        + "inspect a target and make an Intelligence (Investigation) check against your spell "
        + "save DC. If it succeeds, it becomes aware that the target is disguised."),
    TELEKINESIS(MagicSchool.TRANSMUTATION, 5, "1 action", "60 feet",
        "V, S", "up to 10 minutes", "You gain the ability to move or manipulate creatures or "
        + "objects by thought. When you cast the spell, and as your action each round for the "
        + "duration, you can exert your will on one creature or object that you can see within "
        + "range, causing the appropriate effect below. You can affect the same target round "
        + "after round, or choose a new one at any time. If you switch targets, the prior target "
        + "is no longer affected by the spell. Creature. You can try to move a Huge or smaller "
        + "creature. Make an ability check with your spellcasting ability contested by the "
        + "creature's Strength check. If you win the contest, you move the creature up to 30 feet "
        + "in any direction, including upward but not beyond the range of this spell. Until the "
        + "end of your next turn, the creature is restrained in your telekinetic grip. A creature "
        + "lifted upward is suspended in mid-air. On subsequent rounds, you can use your action "
        + "to attempt to maintain your telekinetic grip on the creature by repeating the contest. "
        + "Object. You can try to move an object that weighs up to 1,000 pounds. If the object "
        + "isn't being worn or carried, you automatically move it up to 30 feet in any direction, "
        + "but not beyond the range of this spell. If the object is worn or carried by a "
        + "creature, you must make an ability check with your spellcasting ability contested by "
        + "that creature's Strength check. If you succeed, you pull the object away from that "
        + "creature and can move it up to 30 feet in any direction but not beyond the range of "
        + "this spell. You can exert fine control on objects with your telekinetic grip, such as "
        + "manipulating a simple tool, opening a door or a container, stowing or retrieving an "
        + "item from an open container, or pouring the contents from a vial."),
    TELEPATHIC_BOND(MagicSchool.DIVINATION, 5, "1 action", "30 feet",
        "V, S, M ", "1 hour", "You forge a telepathic link among up to eight willing "
        + "creatures of your choice within range, psychically linking each creature to all the "
        + "others for the duration. Creatures with Intelligence scores of 2 or less aren't "
        + "affected by this spell. Until the spell ends, the targets can communicate "
        + "telepathically through the bond whether or not they have a common language. The "
        + "communication is possible over any distance, though it can't extend to other planes of "
        + "existence."),
    TELEPORTATION_CIRCLE(MagicSchool.CONJURATION, 5, "1 minute",
        "10 feet", "V, M ", "1 round", "As you cast the spell, you draw a 10-foot-diameter "
        + "circle on the ground inscribed with sigils that link your location to a permanent "
        + "teleportation circle of your choice whose sigil sequence you know and that is on the "
        + "same plane of existence as you. A shimmering portal opens within the circle you drew "
        + "and remains open until the end of your next turn. Any creature that enters the portal "
        + "instantly appears within 5 feet of the destination circle or in the nearest unoccupied "
        + "space if that space is occupied. Many major temples, guilds, and other important "
        + "places have permanent teleportation circles inscribed somewhere within their confines. "
        + "Each such circle includes a unique sigil sequence--a string of magical runes arranged "
        + "in a particular pattern. When you first gain the ability to cast this spell, you learn "
        + "the sigil sequences for two destinations on the Material Plane, determined by the GM. "
        + "You can learn additional sigil sequences during your adventures. You can commit a new "
        + "sigil sequence to memory after studying it for 1 minute. You can create a permanent "
        + "teleportation circle by casting this spell in the same location every day for one "
        + "year. You need not use the circle to teleport when you cast the spell in this way."),
    TREE_STRIDE(MagicSchool.CONJURATION, 5, "1 action", "Self",
        "V, S", "up to 1 minute", "You gain the ability to enter a tree and move from inside "
        + "it to inside another tree of the same kind within 500 feet. Both trees must be living "
        + "and at least the same size as you. You must use 5 feet of movement to enter a tree. "
        + "You instantly know the location of all other trees of the same kind within 500 feet "
        + "and, as part of the move used to enter the tree, can either pass into one of those "
        + "trees or step out of the tree you're in. You appear in a spot of your choice within 5 "
        + "feet of the destination tree, using another 5 feet of movement. If you have no "
        + "movement left, you appear within 5 feet of the tree you entered. You can use this "
        + "transportation ability once per round for the duration. You must end each turn outside "
        + "a tree."),
    WALL_OF_FORCE(MagicSchool.EVOCATION, 5, "1 action", "120 feet",
        "V, S, M ", "up to 10 minutes", "An invisible wall of force springs into existence at "
        + "a point you choose within range. The wall appears in any orientation you choose, as a "
        + "horizontal or vertical barrier or at an angle. It can be free floating or resting on a "
        + "solid surface. You can form it into a hemispherical dome or a sphere with a radius of "
        + "up to 10 feet, or you can shape a flat surface made up of ten 10-foot-by-10-foot "
        + "panels. Each panel must be contiguous with another panel. In any form, the wall is 1/4 "
        + "inch thick. It lasts for the duration. If the wall cuts through a creature's space "
        + "when it appears, the creature is pushed to one side of the wall (your choice which "
        + "side). Nothing can physically pass through the wall. It is immune to all damage and "
        + "can't be dispelled by dispel magic. A disintegrate spell destroys the wall instantly, "
        + "however. The wall also extends into the Ethereal Plane, blocking ethereal travel "
        + "through the wall."),
    WALL_OF_STONE(MagicSchool.EVOCATION, 5, "1 action", "120 feet",
        "V, S, M ", "up to 10 minutes", "A nonmagical wall of solid stone springs into "
        + "existence at a point you choose within range. The wall is 6 inches thick and is "
        + "composed of ten 10-foot- by-10-foot panels. Each panel must be contiguous with at "
        + "least one other panel. Alternatively, you can create 10-foot-by-20-foot panels that "
        + "are only 3 inches thick. If the wall cuts through a creature's space when it appears, "
        + "the creature is pushed to one side of the wall (your choice). If a creature would be "
        + "surrounded on all sides by the wall (or the wall and another solid surface), that "
        + "creature can make a Dexterity saving throw. On a success, it can use its reaction to "
        + "move up to its speed so that it is no longer enclosed by the wall. The wall can have "
        + "any shape you desire, though it can't occupy the same space as a creature or object. "
        + "The wall doesn't need to be vertical or rest on any firm foundation. It must, however, "
        + "merge with and be solidly supported by existing stone. Thus, you can use this spell to "
        + "bridge a chasm or create a ramp. If you create a span greater than 20 feet in length, "
        + "you must halve the size of each panel to create supports. You can crudely shape the "
        + "wall to create crenellations, battlements, and so on. The wall is an object made of "
        + "stone that can be damaged and thus breached. Each panel has AC 15 and 30 hit points "
        + "per inch of thickness. Reducing a panel to 0 hit points destroys it and might cause "
        + "connected panels to collapse at the GM's discretion. If you maintain your "
        + "concentration on this spell for its whole duration, the wall becomes permanent and "
        + "can't be dispelled. Otherwise, the wall disappears when the spell ends."),
    CHAIN_LIGHTNING(MagicSchool.EVOCATION, 6, "1 action", "150 feet",
        "V, S, M ", "Instantaneous", "You create a bolt of lightning that arcs toward a "
        + "target of your choice that you can see within range. Three bolts then leap from that "
        + "target to as many as three other targets, each of which must be within 30 feet of the "
        + "first target. A target can be a creature or an object and can be targeted by only one "
        + "of the bolts. A target must make a Dexterity saving throw. The target takes 10d8 "
        + "lightning damage on a failed save, or half as much damage on a successful one. At "
        + "Higher Levels. When you cast this spell using a spell slot of 7th level or higher, one "
        + "additional bolt leaps from the first target to another target for each slot level "
        + "above 6th."),
    CIRCLE_OF_DEATH(MagicSchool.NECROMANCY, 6, "1 action", "150 feet",
        "V, S, M ", "Instantaneous", "A sphere of negative energy ripples out in a 60-foot- "
        + "radius sphere from a point within range. Each creature in that area must make a "
        + "Constitution saving throw. A target takes 8d6 necrotic damage on a failed save, or "
        + "half as much damage on a successful one. At Higher Levels. When you cast this spell "
        + "using a spell slot of 7th level or higher, the damage increases by 2d6 for each slot "
        + "level above 6th."),
    CONJURE_FEY(MagicSchool.CONJURATION, 6, "1 minute", "90 feet",
        "V, S", "up to 1 hour", "You summon a fey creature of challenge rating 6 or lower, or "
        + "a fey spirit that takes the form of a beast of challenge rating 6 or lower. It appears "
        + "in an unoccupied space that you can see within range. The fey creature disappears when "
        + "it drops to 0 hit points or when the spell ends. The fey creature is friendly to you "
        + "and your companions for the duration. Roll initiative for the creature, which has its "
        + "own turns. It obeys any verbal commands that you issue to it (no action required by "
        + "you), as long as they don't violate its alignment. If you don't issue any commands to "
        + "the fey creature, it defends itself from hostile creatures but otherwise takes no "
        + "actions. If your concentration is broken, the fey creature doesn't disappear. Instead, "
        + "you lose control of the fey creature, it becomes hostile toward you and your "
        + "companions, and it might attack. An uncontrolled fey creature can't be dismissed by "
        + "you, and it disappears 1 hour after you summoned it. The GM has the fey creature's "
        + "statistics. At Higher Levels. When you cast this spell using a spell slot of 7th level "
        + "or higher, the challenge rating increases by 1 for each slot level above 6th."),
    CONTINGENCY(MagicSchool.EVOCATION, 6, "10 minutes", "Self",
        "V, S, M ", "10 days", "Choose a spell of 5th level or lower that you can cast, that "
        + "has a casting time of 1 action, and that can target you. You cast that spell--called "
        + "the contingent spell--as part of casting contingency, expending spell slots for both, "
        + "but the contingent spell doesn't come into effect. Instead, it takes effect when a "
        + "certain circumstance occurs. You describe that circumstance when you cast the two "
        + "spells. For example, a contingency cast with water breathing might stipulate that "
        + "water breathing comes into effect when you are engulfed in water or a similar liquid. "
        + "The contingent spell takes effect immediately after the circumstance is met for the "
        + "first time, whether or not you want it to, and then contingency ends. The contingent "
        + "spell takes effect only on you, even if it can normally target others. You can use "
        + "only one contingency spell at a time. If you cast this spell again, the effect of "
        + "another contingency spell on you ends. Also, contingency ends on you if its material "
        + "component is ever not on your person."),
    CREATE_UNDEAD(MagicSchool.NECROMANCY, 6, "1 minute", "10 feet",
        "V, S, M ", "Instantaneous", "You can cast this spell only at night. Choose up to "
        + "three corpses of Medium or Small humanoids within range. Each corpse becomes a ghoul "
        + "under your control. (The GM has game statistics for these creatures.) As a bonus "
        + "action on each of your turns, you can mentally command any creature you animated with "
        + "this spell if the creature is within 120 feet of you (if you control multiple "
        + "creatures, you can command any or all of them at the same time, issuing the same "
        + "command to each one). You decide what action the creature will take and where it will "
        + "move during its next turn, or you can issue a general command, such as to guard a "
        + "particular chamber or corridor. If you issue no commands, the creature only defends "
        + "itself against hostile creatures. Once given an order, the creature continues to "
        + "follow it until its task is complete. The creature is under your control for 24 hours, "
        + "after which it stops obeying any command you have given it. To maintain control of the "
        + "creature for another 24 hours, you must cast this spell on the creature before the "
        + "current 24-hour period ends. This use of the spell reasserts your control over up to "
        + "three creatures you have animated with this spell, rather than animating new ones. At "
        + "Higher Levels. When you cast this spell using a 7th-level spell slot, you can animate "
        + "or reassert control over four ghouls. When you cast this spell using an 8th-level "
        + "spell slot, you can animate or reassert control over five ghouls or two ghasts or "
        + "wights. When you cast this spell using a 9th-level spell slot, you can animate or "
        + "reassert control over six ghouls, three ghasts or wights, or two mummies."),
    DISINTEGRATE(MagicSchool.TRANSMUTATION, 6, "1 action", "60 feet",
        "V, S, M ", "Instantaneous", "A thin green ray springs from your pointing finger to a "
        + "target that you can see within range. The target can be a creature, an object, or a "
        + "creation of magical force, such as the wall created by wall of force. A creature "
        + "targeted by this spell must make a Dexterity saving throw. On a failed save, the "
        + "target takes 10d6 + 40 force damage. If this damage reduces the target to 0 hit "
        + "points, it is disintegrated. A disintegrated creature and everything it is wearing and "
        + "carrying, except magic items, are reduced to a pile of fine gray dust. The creature "
        + "can be restored to life only by means of a true resurrection or a wish spell. This "
        + "spell automatically disintegrates a Large or smaller nonmagical object or a creation "
        + "of magical force. If the target is a Huge or larger object or creation of force, this "
        + "spell disintegrates a 10-foot- cube portion of it. A magic item is unaffected by this "
        + "spell. At Higher Levels. When you cast this spell using a spell slot of 7th level or "
        + "higher, the damage increases by 3d6 for each slot level above 6th."),
    EYEBITE(MagicSchool.NECROMANCY, 6, "1 action", "Self", "V, S",
        "up to 1 minute", "For the spell's duration, your eyes become an inky void imbued "
        + "with dread power. One creature of your choice within 60 feet of you that you can see "
        + "must succeed on a Wisdom saving throw or be affected by one of the following effects "
        + "of your choice for the duration. On each of your turns until the spell ends, you can "
        + "use your action to target another creature but can't target a creature again if it has "
        + "succeeded on a saving throw against this casting of eyebite. Asleep. The target falls "
        + "unconscious. It wakes up if it takes any damage or if another creature uses its action "
        + "to shake the sleeper awake. Panicked. The target is frightened of you. On each of its "
        + "turns, the frightened creature must take the Dash action and move away from you by the "
        + "safest and shortest available route, unless there is nowhere to move. If the target "
        + "moves to a place at least 60 feet away from you where it can no longer see you, this "
        + "effect ends. Sickened. The target has disadvantage on attack rolls and ability checks. "
        + "At the end of each of its turns, it can make another Wisdom saving throw. If it "
        + "succeeds, the effect ends."),
    FIND_THE_PATH(MagicSchool.DIVINATION, 6, "1 minute", "Self",
        "V, S, M ", "up to 1 day", "This spell allows you to find the shortest, most direct "
        + "physical route to a specific fixed location that you are familiar with on the same "
        + "plane of existence. If you name a destination on another plane of existence, a "
        + "destination that moves (such as a mobile fortress), or a destination that isn't "
        + "specific (such as 'a green dragon's lair'), the spell fails. For the duration, as long "
        + "as you are on the same plane of existence as the destination, you know how far it is "
        + "and in what direction it lies. While you are traveling there, whenever you are "
        + "presented with a choice of paths along the way, you automatically determine which path "
        + "is the shortest and most direct route (but not necessarily the safest route) to the "
        + "destination."),
    FLESH_TO_STONE(MagicSchool.TRANSMUTATION, 6, "1 action", "60 feet",
        "V, S, M ", "up to 1 minute", "You attempt to turn one creature that you can see "
        + "within range into stone. If the target's body is made of flesh, the creature must make "
        + "a Constitution saving throw. On a failed save, it is restrained as its flesh begins to "
        + "harden. On a successful save, the creature isn't affected. A creature restrained by "
        + "this spell must make another Constitution saving throw at the end of each of its "
        + "turns. If it successfully saves against this spell three times, the spell ends. If it "
        + "fails its saves three times, it is turned to stone and subjected to the petrified "
        + "condition for the duration. The successes and failures don't need to be consecutive; "
        + "keep track of both until the target collects three of a kind. If the creature is "
        + "physically broken while petrified, it suffers from similar deformities if it reverts "
        + "to its original state. If you maintain your concentration on this spell for the entire "
        + "possible duration, the creature is turned to stone until the effect is removed."),
    FORBIDDANCE(MagicSchool.ABJURATION, 6, "10 minutes", "Touch",
        "V, S, M ", "1 day", "You create a ward against magical travel that protects up to "
        + "40,000 square feet of floor space to a height of 30 feet above the floor. For the "
        + "duration, creatures can't teleport into the area or use portals, such as those created "
        + "by the gate spell, to enter the area. The spell proofs the area against planar travel, "
        + "and therefore prevents creatures from accessing the area by way of the Astral Plane, "
        + "Ethereal Plane, Feywild, Shadowfell, or the plane shift spell. In addition, the spell "
        + "damages types of creatures that you choose when you cast it. Choose one or more of the "
        + "following: celestials, elementals, fey, fiends, and undead. When a chosen creature "
        + "enters the spell's area for the first time on a turn or starts its turn there, the "
        + "creature takes 5d10 radiant or necrotic damage (your choice when you cast this spell). "
        + "When you cast this spell, you can designate a password. A creature that speaks the "
        + "password as it enters the area takes no damage from the spell. The spell's area can't "
        + "overlap with the area of another forbiddance spell. If you cast forbiddance every day "
        + "for 30 days in the same location, the spell lasts until it is dispelled, and the "
        + "material components are consumed on the last casting."),
    FREEZING_SPHERE(MagicSchool.EVOCATION, 6, "1 action", "300 feet",
        "V, S, M ", "Instantaneous", "A frigid globe of cold energy streaks from your "
        + "fingertips to a point of your choice within range, where it explodes in a "
        + "60-foot-radius sphere. Each creature within the area must make a Constitution saving "
        + "throw. On a failed save, a creature takes 10d6 cold damage. On a successful save, it "
        + "takes half as much damage. If the globe strikes a body of water or a liquid that is "
        + "principally water (not including water-based creatures), it freezes the liquid to a "
        + "depth of 6 inches over an area 30 feet square. This ice lasts for 1 minute. Creatures "
        + "that were swimming on the surface of frozen water are trapped in the ice. A trapped "
        + "creature can use an action to make a Strength check against your spell save DC to "
        + "break free. You can refrain from firing the globe after completing the spell, if you "
        + "wish. A small globe about the size of a sling stone, cool to the touch, appears in "
        + "your hand. At any time, you or a creature you give the globe to can throw the globe "
        + "(to a range of 40 feet) or hurl it with a sling (to the sling's normal range). It "
        + "shatters on impact, with the same effect as the normal casting of the spell. You can "
        + "also set the globe down without shattering it. After 1 minute, if the globe hasn't "
        + "already shattered, it explodes. At Higher Levels. When you cast this spell using a "
        + "spell slot of 7th level or higher, the damage increases by 1d6 for each slot level "
        + "above 6th."),
    GLOBE_OF_INVULNERABILITY(MagicSchool.ABJURATION, 6, "1 action",
        "Self (10-foot radius)", "V, S, M ", "up to 1 minute", "An immobile, faintly "
        + "shimmering barrier springs into existence in a 10-foot radius around you and remains "
        + "for the duration. Any spell of 5th level or lower cast from outside the barrier can't "
        + "affect creatures or objects within it, even if the spell is cast using a higher level "
        + "spell slot. Such a spell can target creatures and objects within the barrier, but the "
        + "spell has no effect on them. Similarly, the area within the barrier is excluded from "
        + "the areas affected by such spells. At Higher Levels. When you cast this spell using a "
        + "spell slot of 7th level or higher, the barrier blocks spells of one level higher for "
        + "each slot level above 6th."),
    GUARDS_AND_WARDS(MagicSchool.ABJURATION, 6, "10 minutes", "Touch",
        "V, S, M ", "24 hours", "You create a ward that protects up to 2,500 square feet of "
        + "floor space (an area 50 feet square, or one hundred 5-foot squares or twenty-five "
        + "10-foot squares). The warded area can be up to 20 feet tall, and shaped as you desire. "
        + "You can ward several stories of a stronghold by dividing the area among them, as long "
        + "as you can walk into each contiguous area while you are casting the spell. When you "
        + "cast this spell, you can specify individuals that are unaffected by any or all of the "
        + "effects that you choose. You can also specify a password that, when spoken aloud, "
        + "makes the speaker immune to these effects. Guards and wards creates the following "
        + "effects within the warded area. Corridors. Fog fills all the warded corridors, making "
        + "them heavily obscured. In addition, at each intersection or branching passage offering "
        + "a choice of direction, there is a 50 percent chance that a creature other than you "
        + "will believe it is going in the opposite direction from the one it chooses. Doors. All "
        + "doors in the warded area are magically locked, as if sealed by an arcane lock spell. "
        + "In addition, you can cover up to ten doors with an illusion (equivalent to the "
        + "illusory object function of the minor illusion spell) to make them appear as plain "
        + "sections of wall. Stairs. Webs fill all stairs in the warded area from top to bottom, "
        + "as the web spell. These strands regrow in 10 minutes if they are burned or torn away "
        + "while guards and wards lasts. Other Spell Effect. You can place your choice of one of "
        + "the following magical effects within the warded area of the stronghold. Place dancing "
        + "lights in four corridors. You can designate a simple program that the lights repeat as "
        + "long as guards and wards lasts. Place magic mouth in two locations. Place stinking "
        + "cloud in two locations. The vapors appear in the places you designate; they return "
        + "within 10 minutes if dispersed by wind while guards and wards lasts. Place a constant "
        + "gust of wind in one corridor or room. Place a suggestion in one location. You select "
        + "an area of up to 5 feet square, and any creature that enters or passes through the "
        + "area receives the suggestion mentally. The whole warded area radiates magic. A dispel "
        + "magic cast on a specific effect, if successful, removes only that effect. You can "
        + "create a permanently guarded and warded structure by casting this spell there every "
        + "day for one year."),
    HARM(MagicSchool.NECROMANCY, 6, "1 action", "60 feet", "V, S",
        "Instantaneous", "You unleash a virulent disease on a creature that you can see "
        + "within range. The target must make a Constitution saving throw. On a failed save, it "
        + "takes 14d6 necrotic damage, or half as much damage on a successful save. The damage "
        + "can't reduce the target's hit points below 1. If the target fails the saving throw, "
        + "its hit point maximum is reduced for 1 hour by an amount equal to the necrotic damage "
        + "it took. Any effect that removes a disease allows a creature's hit point maximum to "
        + "return to normal before that time passes."),
    HEAL(MagicSchool.EVOCATION, 6, "1 action", "60 feet", "V, S",
        "Instantaneous", "Choose a creature that you can see within range. A surge of "
        + "positive energy washes through the creature, causing it to regain 70 hit points. This "
        + "spell also ends blindness, deafness, and any diseases affecting the target. This spell "
        + "has no effect on constructs or undead. At Higher Levels. When you cast this spell "
        + "using a spell slot of 7th level or higher, the amount of healing increases by 10 for "
        + "each slot level above 6th."),
    HEROES_FEAST(MagicSchool.CONJURATION, 6, "10 minutes", "30 feet",
        "V, S , M ", "Instantaneous", "You bring forth a great feast, including magnificent "
        + "food and drink. The feast takes 1 hour to consume and disappears at the end of that "
        + "time, and the beneficial effects don't set in until this hour is over. Up to twelve "
        + "other creatures can partake of the feast. A creature that partakes of the feast gains "
        + "several benefits. The creature is cured of all diseases and poison, becomes immune to "
        + "poison and being frightened, and makes all Wisdom saving throws with advantage. Its "
        + "hit point maximum also increases by 2d10, and it gains the same number of hit points. "
        + "These benefits last for 24 hours."),
    INSTANT_SUMMONS(MagicSchool.CONJURATION, 6, "1 minute", "Touch",
        "V, S, M ", "Until dispelled", "You touch an object weighing 10 pounds or less whose "
        + "longest dimension is 6 feet or less. The spell leaves an invisible mark on its surface "
        + "and invisibly inscribes the name of the item on the sapphire you use as the material "
        + "component. Each time you cast this spell, you must use a different sapphire. At any "
        + "time thereafter, you can use your action to speak the item's name and crush the "
        + "sapphire. The item instantly appears in your hand regardless of physical or planar "
        + "distances, and the spell ends. If another creature is holding or carrying the item, "
        + "crushing the sapphire doesn't transport the item to you, but instead you learn who the "
        + "creature possessing the object is and roughly where that creature is located at that "
        + "moment. Dispel magic or a similar effect successfully applied to the sapphire ends "
        + "this spell's effect."),
    IRRESISTIBLE_DANCE(MagicSchool.ENCHANTMENT, 6, "1 action",
        "30 feet", "V", "up to 1 minute", "Choose one creature that you can see within range. "
        + "The target begins a comic dance in place: shuffling, tapping its feet, and capering "
        + "for the duration. Creatures that can't be charmed are immune to this spell. A dancing "
        + "creature must use all its movement to dance without leaving its space and has "
        + "disadvantage on Dexterity saving throws and attack rolls. While the target is affected "
        + "by this spell, other creatures have advantage on attack rolls against it. As an "
        + "action, a dancing creature makes a Wisdom saving throw to regain control of itself. On "
        + "a successful save, the spell ends."),
    MAGIC_JAR(MagicSchool.NECROMANCY, 6, "1 minute", "Self", "V, S, M ",
        "Until dispelled", "Your body falls into a catatonic state as your soul leaves it and "
        + "enters the container you used for the spell's material component. While your soul "
        + "inhabits the container, you are aware of your surroundings as if you were in the "
        + "container's space. You can't move or use reactions. The only action you can take is to "
        + "project your soul up to 100 feet out of the container, either returning to your living "
        + "body (and ending the spell) or attempting to possess a humanoids body. You can attempt "
        + "to possess any humanoid within 100 feet of you that you can see (creatures warded by a "
        + "protection from evil and good or magic circle spell can't be possessed). The target "
        + "must make a Charisma saving throw. On a failure, your soul moves into the target's "
        + "body, and the target's soul becomes trapped in the container. On a success, the target "
        + "resists your efforts to possess it, and you can't attempt to possess it again for 24 "
        + "hours. Once you possess a creature's body, you control it. Your game statistics are "
        + "replaced by the statistics of the creature, though you retain your alignment and your "
        + "Intelligence, Wisdom, and Charisma scores. You retain the benefit of your own class "
        + "features. If the target has any class levels, you can't use any of its class features. "
        + "Meanwhile, the possessed creature's soul can perceive from the container using its own "
        + "senses, but it can't move or take actions at all. While possessing a body, you can use "
        + "your action to return from the host body to the container if it is within 100 feet of "
        + "you, returning the host creature's soul to its body. If the host body dies while "
        + "you're in it, the creature dies, and you must make a Charisma saving throw against "
        + "your own spellcasting DC. On a success, you return to the container if it is within "
        + "100 feet of you. Otherwise, you die. If the container is destroyed or the spell ends, "
        + "your soul immediately returns to your body. If your body is more than 100 feet away "
        + "from you or if your body is dead when you attempt to return to it, you die. If another "
        + "creature's soul is in the container when it is destroyed, the creature's soul returns "
        + "to its body if the body is alive and within 100 feet. Otherwise, that creature dies. "
        + "When the spell ends, the container is destroyed."),
    MASS_SUGGESTION(MagicSchool.ENCHANTMENT, 6, "1 action", "60 feet",
        "V, M ", "24 hours", "You suggest a course of activity (limited to a sentence or two) "
        + "and magically influence up to twelve creatures of your choice that you can see within "
        + "range and that can hear and understand you. Creatures that can't be charmed are immune "
        + "to this effect. The suggestion must be worded in such a manner as to make the course "
        + "of action sound reasonable. Asking the creature to stab itself, throw itself onto a "
        + "spear, immolate itself, or do some other obviously harmful act automatically negates "
        + "the effect of the spell. Each target must make a Wisdom saving throw. On a failed "
        + "save, it pursues the course of action you described to the best of its ability. The "
        + "suggested course of action can continue for the entire duration. If the suggested "
        + "activity can be completed in a shorter time, the spell ends when the subject finishes "
        + "what it was asked to do. You can also specify conditions that will trigger a special "
        + "activity during the duration. For example, you might suggest that a group of soldiers "
        + "give all their money to the first beggar they meet. If the condition isn't met before "
        + "the spell ends, the activity isn't performed. If you or any of your companions damage "
        + "a creature affected by this spell, the spell ends for that creature. At Higher Levels. "
        + "When you cast this spell using a 7th-level spell slot, the duration is 10 days. When "
        + "you use an 8th-level spell slot, the duration is 30 days. When you use a 9th-level "
        + "spell slot, the duration is a year and a day."),
    MOVE_EARTH(MagicSchool.TRANSMUTATION, 6, "1 action", "120 feet",
        "V, S, M ", "up to 2 hours", "Choose an area of terrain no larger than 40 feet on a "
        + "side within range. You can reshape dirt, sand, or clay in the area in any manner you "
        + "choose for the duration. You can raise or lower the area's elevation, create or fill "
        + "in a trench, erect or flatten a wall, or form a pillar. The extent of any such changes "
        + "can't exceed half the area's largest dimension. So, if you affect a 40-foot square, "
        + "you can create a pillar up to 20 feet high, raise or lower the square's elevation by "
        + "up to 20 feet, dig a trench up to 20 feet deep, and so on. It takes 10 minutes for "
        + "these changes to complete. At the end of every 10 minutes you spend concentrating on "
        + "the spell, you can choose a new area of terrain to affect. Because the terrain's "
        + "transformation occurs slowly, creatures in the area can't usually be trapped or "
        + "injured by the ground's movement. This spell can't manipulate natural stone or stone "
        + "construction. Rocks and structures shift to accommodate the new terrain. If the way "
        + "you shape the terrain would make a structure unstable, it might collapse. Similarly, "
        + "this spell doesn't directly affect plant growth. The moved earth carries any plants "
        + "along with it."),
    PROGRAMMED_ILLUSION(MagicSchool.ILLUSION, 6, "1 action", "120 feet",
        "V, S, M ", "Until dispelled", "You create an illusion of an object, a creature, or "
        + "some other visible phenomenon within range that activates when a specific condition "
        + "occurs. The illusion is imperceptible until then. It must be no larger than a 30-foot "
        + "cube, and you decide when you cast the spell how the illusion behaves and what sounds "
        + "it makes. This scripted performance can last up to 5 minutes. When the condition you "
        + "specify occurs, the illusion springs into existence and performs in the manner you "
        + "described. Once the illusion finishes performing, it disappears and remains dormant "
        + "for 10 minutes. After this time, the illusion can be activated again. The triggering "
        + "condition can be as general or as detailed as you like, though it must be based on "
        + "visual or audible conditions that occur within 30 feet of the area. For example, you "
        + "could create an illusion of yourself to appear and warn off others who attempt to open "
        + "a trapped door, or you could set the illusion to trigger only when a creature says the "
        + "correct word or phrase. Physical interaction with the image reveals it to be an "
        + "illusion, because things can pass through it. A creature that uses its action to "
        + "examine the image can determine that it is an illusion with a successful Intelligence "
        + "(Investigation) check against your spell save DC. If a creature discerns the illusion "
        + "for what it is, the creature can see through the image, and any noise it makes sounds "
        + "hollow to the creature."),
    SUNBEAM(MagicSchool.EVOCATION, 6, "1 action", "Self (60-foot line)",
        "V, S, M ", "up to 1 minute", "A beam of brilliant light flashes out from your hand "
        + "in a 5-foot-wide, 60-foot-long line. Each creature in the line must make a "
        + "Constitution saving throw. On a failed save, a creature takes 6d8 radiant damage and "
        + "is blinded until your next turn. On a successful save, it takes half as much damage "
        + "and isn't blinded by this spell. Undead and oozes have disadvantage on this saving "
        + "throw. You can create a new line of radiance as your action on any turn until the "
        + "spell ends. For the duration, a mote of brilliant radiance shines in your hand. It "
        + "sheds bright light in a 30-foot radius and dim light for an additional 30 feet. This "
        + "light is sunlight."),
    TRANSPORT_VIA_PLANTS(MagicSchool.CONJURATION, 6, "1 action",
        "10 feet", "V, S", "1 round", "This spell creates a magical link between a Large or "
        + "larger inanimate plant within range and another plant, at any distance, on the same "
        + "plane of existence. You must have seen or touched the destination plant at least once "
        + "before. For the duration, any creature can step into the target plant and exit from "
        + "the destination plant by using 5 feet of movement."),
    TRUE_SEEING(MagicSchool.DIVINATION, 6, "1 action", "Touch",
        "V, S, M ", "1 hour", "This spell gives the willing creature you touch the ability to "
        + "see things as they actually are. For the duration, the creature has truesight, notices "
        + "secret doors hidden by magic, and can see into the Ethereal Plane, all out to a range "
        + "of 120 feet."),
    WALL_OF_ICE(MagicSchool.EVOCATION, 6, "1 action", "120 feet",
        "V, S, M ", "up to 10 minutes", "You create a wall of ice on a solid surface within "
        + "range. You can form it into a hemispherical dome or a sphere with a radius of up to 10 "
        + "feet, or you can shape a flat surface made up of ten 10-foot-square panels. Each panel "
        + "must be contiguous with another panel. In any form, the wall is 1 foot thick and lasts "
        + "for the duration. If the wall cuts through a creature's space when it appears, the "
        + "creature within its area is pushed to one side of the wall and must make a Dexterity "
        + "saving throw. On a failed save, the creature takes 10d6 cold damage, or half as much "
        + "damage on a successful save. The wall is an object that can be damaged and thus "
        + "breached. It has AC 12 and 30 hit points per 10-foot section, and it is vulnerable to "
        + "fire damage. Reducing a 10-foot section of wall to 0 hit points destroys it and leaves "
        + "behind a sheet of frigid air in the space the wall occupied. A creature moving through "
        + "the sheet of frigid air for the first time on a turn must make a Constitution saving "
        + "throw. That creature takes 5d6 cold damage on a failed save, or half as much damage on "
        + "a successful one. At Higher Levels. When you cast this spell using a spell slot of 7th "
        + "level or higher, the damage the wall deals when it appears increases by 2d6, and the "
        + "damage from passing through the sheet of frigid air increases by 1d6, for each slot "
        + "level above 6th."),
    WALL_OF_THORNS(MagicSchool.CONJURATION, 6, "1 action", "120 feet",
        "V, S, M ", "up to 10 minutes", "You create a wall of tough, pliable, tangled brush "
        + "bristling with needle-sharp thorns. The wall appears within range on a solid surface "
        + "and lasts for the duration. You choose to make the wall up to 60 feet long, 10 feet "
        + "high, and 5 feet thick or a circle that has a 20-foot diameter and is up to 20 feet "
        + "high and 5 feet thick. The wall blocks line of sight. When the wall appears, each "
        + "creature within its area must make a Dexterity saving throw. On a failed save, a "
        + "creature takes 7d8 piercing damage, or half as much damage on a successful save. A "
        + "creature can move through the wall, albeit slowly and painfully. For every 1 foot a "
        + "creature moves through the wall, it must spend 4 feet of movement. Furthermore, the "
        + "first time a creature enters the wall on a turn or ends its turn there, the creature "
        + "must make a Dexterity saving throw. It takes 7d8 slashing damage on a failed save, or "
        + "half as much damage on a successful one. At Higher Levels. When you cast this spell "
        + "using a spell slot of 7th level or higher, both types of damage increase by 1d8 for "
        + "each slot level above 6th."),
    WIND_WALK(MagicSchool.TRANSMUTATION, 6, "1 minute", "30 feet",
        "V, S, M ", "8 hours", "You and up to ten willing creatures you can see within range "
        + "assume a gaseous form for the duration, appearing as wisps of cloud. While in this "
        + "cloud form, a creature has a flying speed of 300 feet and has resistance to damage "
        + "from nonmagical weapons. The only actions a creature can take in this form are the "
        + "Dash action or to revert to its normal form. Reverting takes 1 minute, during which "
        + "time a creature is incapacitated and can't move. Until the spell ends, a creature can "
        + "revert to cloud form, which also requires the 1-minute transformation. If a creature "
        + "is in cloud form and flying when the effect ends, the creature descends 60 feet per "
        + "round for 1 minute until it lands, which it does safely. If it can't land after 1 "
        + "minute, the creature falls the remaining distance."),
    WORD_OF_RECALL(MagicSchool.CONJURATION, 6, "1 action", "5 feet",
        "V", "Instantaneous", "You and up to five willing creatures within 5 feet of you "
        + "instantly teleport to a previously designated sanctuary. You and any creatures that "
        + "teleport with you appear in the nearest unoccupied space to the spot you designated "
        + "when you prepared your sanctuary (see below). If you cast this spell without first "
        + "preparing a sanctuary, the spell has no effect. You must designate a sanctuary by "
        + "casting this spell within a location, such as a temple, dedicated to or strongly "
        + "linked to your deity. If you attempt to cast the spell in this manner in an area that "
        + "isn't dedicated to your deity, the spell has no effect."),
    CONJURE_CELESTIAL(MagicSchool.CONJURATION, 7, "1 minute", "90 feet",
        "V, S", "up to 1 hour", "You summon a celestial of challenge rating 4 or lower, which "
        + "appears in an unoccupied space that you can see within range. The celestial disappears "
        + "when it drops to 0 hit points or when the spell ends. The celestial is friendly to you "
        + "and your companions for the duration. Roll initiative for the celestial, which has its "
        + "own turns. It obeys any verbal commands that you issue to it (no action required by "
        + "you), as long as they don't violate its alignment. If you don't issue any commands to "
        + "the celestial, it defends itself from hostile creatures but otherwise takes no "
        + "actions. The GM has the celestial's statistics. At Higher Levels. When you cast this "
        + "spell using a 9th-level spell slot, you summon a celestial of challenge rating 5 or "
        + "lower."),
    DELAYED_BLAST_FIREBALL(MagicSchool.EVOCATION, 7, "1 action",
        "150 feet", "V, S, M ", "up to 1 minute", "A beam of yellow light flashes from your "
        + "pointing finger, then condenses to linger at a chosen point within range as a glowing "
        + "bead for the duration. When the spell ends, either because your concentration is "
        + "broken or because you decide to end it, the bead blossoms with a low roar into an "
        + "explosion of flame that spreads around corners. Each creature in a 20-foot-radius "
        + "sphere centered on that point must make a Dexterity saving throw. A creature takes "
        + "fire damage equal to the total accumulated damage on a failed save, or half as much "
        + "damage on a successful one. The spell's base damage is 12d6. If at the end of your "
        + "turn the bead has not yet detonated, the damage increases by 1d6. If the glowing bead "
        + "is touched before the interval has expired, the creature touching it must make a "
        + "Dexterity saving throw. On a failed save, the spell ends immediately, causing the bead "
        + "to erupt in flame. On a successful save, the creature can throw the bead up to 40 "
        + "feet. When it strikes a creature or a solid object, the spell ends, and the bead "
        + "explodes. The fire damages objects in the area and ignites flammable objects that "
        + "aren't being worn or carried. At Higher Levels. When you cast this spell using a spell "
        + "slot of 8th level or higher, the base damage increases by 1d6 for each slot level "
        + "above 7th."),
    DIVINE_WORD(MagicSchool.EVOCATION, 7, "1 bonus action", "30 feet",
        "V", "Instantaneous", "You utter a divine word, imbued with the power that shaped the "
        + "world at the dawn of creation. Choose any number of creatures you can see within "
        + "range. Each creature that can hear you must make a Charisma saving throw. On a failed "
        + "save, a creature suffers an effect based on its current hit points: 50 hit points or "
        + "fewer: deafened for 1 minute 40 hit points or fewer: deafened and blinded for 10 "
        + "minutes 30 hit points or fewer: blinded, deafened, and stunned for 1 hour 20 hit "
        + "points or fewer: killed instantly Regardless of its current hit points, a celestial, "
        + "an elemental, a fey, or a fiend that fails its save is forced back to its plane of "
        + "origin (if it isn't there already) and can't return to your current plane for 24 hours "
        + "by any means short of a wish spell."),
    ETHEREALNESS(MagicSchool.TRANSMUTATION, 7, "1 action", "Self",
        "V, S", "Up to 8 hours", "You step into the border regions of the Ethereal Plane, in "
        + "the area where it overlaps with your current plane. You remain in the Border Ethereal "
        + "for the duration or until you use your action to dismiss the spell. During this time, "
        + "you can move in any direction. If you move up or down, every foot of movement costs an "
        + "extra foot. You can see and hear the plane you originated from, but everything there "
        + "looks gray, and you can't see anything more than 60 feet away. While on the Ethereal "
        + "Plane, you can only affect and be affected by other creatures on that plane. Creatures "
        + "that aren't on the Ethereal Plane can't perceive you and can't interact with you, "
        + "unless a special ability or magic has given them the ability to do so. You ignore all "
        + "objects and effects that aren't on the Ethereal Plane, allowing you to move through "
        + "objects you perceive on the plane you originated from. When the spell ends, you "
        + "immediately return to the plane you originated from in the spot you currently occupy. "
        + "If you occupy the same spot as a solid object or creature when this happens, you are "
        + "immediately shunted to the nearest unoccupied space that you can occupy and take force "
        + "damage equal to twice the number of feet you are moved. This spell has no effect if "
        + "you cast it while you are on the Ethereal Plane or a plane that doesn't border it, "
        + "such as one of the Outer Planes. At Higher Levels. When you cast this spell using a "
        + "spell slot of 8th level or higher, you can target up to three willing creatures "
        + "(including you) for each slot level above 7th. The creatures must be within 10 feet of "
        + "you when you cast the spell."),
    FINGER_OF_DEATH(MagicSchool.NECROMANCY, 7, "1 action", "60 feet",
        "V, S", "Instantaneous", "You send negative energy coursing through a creature that "
        + "you can see within range, causing it searing pain. The target must make a Constitution "
        + "saving throw. It takes 7d8 + 30 necrotic damage on a failed save, or half as much "
        + "damage on a successful one. A humanoid killed by this spell rises at the start of your "
        + "next turn as a zombie that is permanently under your command, following your verbal "
        + "orders to the best of its ability."),
    FIRE_STORM(MagicSchool.EVOCATION, 7, "1 action", "150 feet",
        "V, S", "Instantaneous", "A storm made up of sheets of roaring flame appears in a "
        + "location you choose within range. The area of the storm consists of up to ten 10-foot "
        + "cubes, which you can arrange as you wish. Each cube must have at least one face "
        + "adjacent to the face of another cube. Each creature in the area must make a Dexterity "
        + "saving throw. It takes 7d10 fire damage on a failed save, or half as much damage on a "
        + "successful one. The fire damages objects in the area and ignites flammable objects "
        + "that aren't being worn or carried. If you choose, plant life in the area is unaffected "
        + "by this spell."),
    FORCECAGE(MagicSchool.EVOCATION, 7, "1 action", "100 feet",
        "V, S, M ", "1 hour", "An immobile, invisible, cube-shaped prison composed of magical "
        + "force springs into existence around an area you choose within range. The prison can be "
        + "a cage or a solid box, as you choose. A prison in the shape of a cage can be up to 20 "
        + "feet on a side and is made from 1/2-inch diameter bars spaced 1/2 inch apart. A prison "
        + "in the shape of a box can be up to 10 feet on a side, creating a solid barrier that "
        + "prevents any matter from passing through it and blocking any spells cast into or out "
        + "from the area. When you cast the spell, any creature that is completely inside the "
        + "cage's area is trapped. Creatures only partially within the area, or those too large "
        + "to fit inside the area, are pushed away from the center of the area until they are "
        + "completely outside the area. A creature inside the cage can't leave it by nonmagical "
        + "means. If the creature tries to use teleportation or interplanar travel to leave the "
        + "cage, it must first make a Charisma saving throw. On a success, the creature can use "
        + "that magic to exit the cage. On a failure, the creature can't exit the cage and wastes "
        + "the use of the spell or effect. The cage also extends into the Ethereal Plane, "
        + "blocking ethereal travel. This spell can't be dispelled by dispel magic."),
    MAGNIFICENT_MANSION(MagicSchool.CONJURATION, 7, "1 minute",
        "300 feet", "V, S, M ", "24 hours", "You conjure an extradimensional dwelling in "
        + "range that lasts for the duration. You choose where its one entrance is located. The "
        + "entrance shimmers faintly and is 5 feet wide and 10 feet tall. You and any creature "
        + "you designate when you cast the spell can enter the extradimensional dwelling as long "
        + "as the portal remains open. You can open or close the portal if you are within 30 feet "
        + "of it. While closed, the portal is invisible. Beyond the portal is a magnificent foyer "
        + "with numerous chambers beyond. The atmosphere is clean, fresh, and warm. You can "
        + "create any floor plan you like, but the space can't exceed 50 cubes, each cube being "
        + "10 feet on each side. The place is furnished and decorated as you choose. It contains "
        + "sufficient food to serve a nine-course banquet for up to 100 people. A staff of 100 "
        + "near-transparent servants attends all who enter. You decide the visual appearance of "
        + "these servants and their attire. They are completely obedient to your orders. Each "
        + "servant can perform any task a normal human servant could perform, but they can't "
        + "attack or take any action that would directly harm another creature. Thus the servants "
        + "can fetch things, clean, mend, fold clothes, light fires, serve food, pour wine, and "
        + "so on. The servants can go anywhere in the mansion but can't leave it. Furnishings and "
        + "other objects created by this spell dissipate into smoke if removed from the mansion. "
        + "When the spell ends, any creatures inside the extradimensional space are expelled into "
        + "the open spaces nearest to the entrance."),
    MIRAGE_ARCANE(MagicSchool.ILLUSION, 7, "10 minutes", "Sight",
        "V, S", "10 days", "You make terrain in an area up to 1 mile square look, sound, "
        + "smell, and even feel like some other sort of terrain. The terrain's general shape "
        + "remains the same, however. Open fields or a road could be made to resemble a swamp, "
        + "hill, crevasse, or some other difficult or impassable terrain. A pond can be made to "
        + "seem like a grassy meadow, a precipice like a gentle slope, or a rock-strewn gully "
        + "like a wide and smooth road. Similarly, you can alter the appearance of structures, or "
        + "add them where none are present. The spell doesn't disguise, conceal, or add "
        + "creatures. The illusion includes audible, visual, tactile, and olfactory elements, so "
        + "it can turn clear ground into difficult terrain (or vice versa) or otherwise impede "
        + "movement through the area. Any piece of the illusory terrain (such as a rock or stick) "
        + "that is removed from the spell's area disappears immediately. Creatures with truesight "
        + "can see through the illusion to the terrain's true form; however, all other elements "
        + "of the illusion remain, so while the creature is aware of the illusion's presence, the "
        + "creature can still physically interact with the illusion."),
    PRISMATIC_SPRAY(MagicSchool.EVOCATION, 7, "1 action", "Self (60-foot cone)",
        "V, S", "Instantaneous", "Eight multicolored rays of light flash from your hand. Each "
        + "ray is a different color and has a different power and purpose. Each creature in a "
        + "60-foot cone must make a Dexterity saving throw. For each target, roll a d8 to "
        + "determine which color ray affects it. 1. Red. The target takes 10d6 fire damage on a "
        + "failed save, or half as much damage on a successful one. 2. Orange. The target takes "
        + "10d6 acid damage on a failed save, or half as much damage on a successful one. 3. "
        + "Yellow. The target takes 10d6 lightning damage on a failed save, or half as much "
        + "damage on a successful one. 4. Green. The target takes 10d6 poison damage on a failed "
        + "save, or half as much damage on a successful one. 5. Blue. The target takes 10d6 cold "
        + "damage on a failed save, or half as much damage on a successful one. 6. Indigo. On a "
        + "failed save, the target is restrained. It must then make a Constitution saving throw "
        + "at the end of each of its turns. If it successfully saves three times, the spell ends. "
        + "If it fails its save three times, it permanently turns to stone and is subjected to "
        + "the petrified condition. The successes and failures don't need to be consecutive; keep "
        + "track of both until the target collects three of a kind. 7. Violet. On a failed save, "
        + "the target is blinded. It must then make a Wisdom saving throw at the start of your "
        + "next turn. A successful save ends the blindness. If it fails that save, the creature "
        + "is transported to another plane of existence of the GM's choosing and is no longer "
        + "blinded. (Typically, a creature that is on a plane that isn't its home plane is "
        + "banished home, while other creatures are usually cast into the Astral or Ethereal "
        + "planes.) 8. Special. The target is struck by two rays. Roll twice more, rerolling any "
        + "8."),
    PROJECT_IMAGE(MagicSchool.ILLUSION, 7, "1 action", "500 miles",
        "V, S, M ", "up to 1 day", "You create an illusory copy of yourself that lasts for "
        + "the duration. The copy can appear at any location within range that you have seen "
        + "before, regardless of intervening obstacles. The illusion looks and sounds like you "
        + "but is intangible. If the illusion takes any damage, it disappears, and the spell "
        + "ends. You can use your action to move this illusion up to twice your speed, and make "
        + "it gesture, speak, and behave in whatever way you choose. It mimics your mannerisms "
        + "perfectly. You can see through its eyes and hear through its ears as if you were in "
        + "its space. On your turn as a bonus action, you can switch from using its senses to "
        + "using your own, or back again. While you are using its senses, you are blinded and "
        + "deafened in regard to your own surroundings. Physical interaction with the image "
        + "reveals it to be an illusion, because things can pass through it. A creature that uses "
        + "its action to examine the image can determine that it is an illusion with a successful "
        + "Intelligence (Investigation) check against your spell save DC. If a creature discerns "
        + "the illusion for what it is, the creature can see through the image, and any noise it "
        + "makes sounds hollow to the creature."),
    REGENERATE(MagicSchool.TRANSMUTATION, 7, "1 minute", "Touch",
        "V, S, M ", "1 hour", "You touch a creature and stimulate its natural healing "
        + "ability. The target regains 4d8 + 15 hit points. For the duration of the spell, the "
        + "target regains 1 hit point at the start of each of its turns (10 hit points each "
        + "minute). The target's severed body members (fingers, legs, tails, and so on), if any, "
        + "are restored after 2 minutes. If you have the severed part and hold it to the stump, "
        + "the spell instantaneously causes the limb to knit to the stump."),
    RESURRECTION(MagicSchool.NECROMANCY, 7, "1 hour", "Touch",
        "V, S, M ", "Instantaneous", "You touch a dead creature that has been dead for no "
        + "more than a century, that didn't die of old age, and that isn't undead. If its soul is "
        + "free and willing, the target returns to life with all its hit points. This spell "
        + "neutralizes any poisons and cures normal diseases afflicting the creature when it "
        + "died. It doesn't, however, remove magical diseases, curses, and the like; if such "
        + "effects aren't removed prior to casting the spell, they afflict the target on its "
        + "return to life. This spell closes all mortal wounds and restores any missing body "
        + "parts. Coming back from the dead is an ordeal. The target takes a −4 penalty to all "
        + "attack rolls, saving throws, and ability checks. Every time the target finishes a long "
        + "rest, the penalty is reduced by 1 until it disappears. Casting this spell to restore "
        + "life to a creature that has been dead for one year or longer taxes you greatly. Until "
        + "you finish a long rest, you can't cast spells again, and you have disadvantage on all "
        + "attack rolls, ability checks, and saving throws."),
    REVERSE_GRAVITY(MagicSchool.TRANSMUTATION, 7, "1 action", "100 feet",
        "V, S, M ", "up to 1 minute", "This spell reverses gravity in a 50-foot-radius, 100- "
        + "foot high cylinder centered on a point within range. All creatures and objects that "
        + "aren't somehow anchored to the ground in the area fall upward and reach the top of the "
        + "area when you cast this spell. A creature can make a Dexterity saving throw to grab "
        + "onto a fixed object it can reach, thus avoiding the fall. If some solid object (such "
        + "as a ceiling) is encountered in this fall, falling objects and creatures strike it "
        + "just as they would during a normal downward fall. If an object or creature reaches the "
        + "top of the area without striking anything, it remains there, oscillating slightly, for "
        + "the duration. At the end of the duration, affected objects and creatures fall back "
        + "down."),
    SEQUESTER(MagicSchool.TRANSMUTATION, 7, "1 action", "Touch",
        "V, S, M ", "Until dispelled", "By means of this spell, a willing creature or an "
        + "object can be hidden away, safe from detection for the duration. When you cast the "
        + "spell and touch the target, it becomes invisible and can't be targeted by divination "
        + "spells or perceived through scrying sensors created by divination spells. If the "
        + "target is a creature, it falls into a state of suspended animation. Time ceases to "
        + "flow for it, and it doesn't grow older. You can set a condition for the spell to end "
        + "early. The condition can be anything you choose, but it must occur or be visible "
        + "within 1 mile of the target. Examples include 'after 1,000 years' or 'when the "
        + "tarrasque awakens.' This spell also ends if the target takes any damage."),
    SIMULACRUM(MagicSchool.ILLUSION, 7, "12 hours", "Touch", "V, S, M ",
        "Until dispelled", "You shape an illusory duplicate of one beast or humanoid that is "
        + "within range for the entire casting time of the spell. The duplicate is a creature, "
        + "partially real and formed from ice or snow, and it can take actions and otherwise be "
        + "affected as a normal creature. It appears to be the same as the original, but it has "
        + "half the creature's hit point maximum and is formed without any equipment. Otherwise, "
        + "the illusion uses all the statistics of the creature it duplicates. The simulacrum is "
        + "friendly to you and creatures you designate. It obeys your spoken commands, moving and "
        + "acting in accordance with your wishes and acting on your turn in combat. The "
        + "simulacrum lacks the ability to learn or become more powerful, so it never increases "
        + "its level or other abilities, nor can it regain expended spell slots. If the "
        + "simulacrum is damaged, you can repair it in an alchemical laboratory, using rare herbs "
        + "and minerals worth 100 gp per hit point it regains. The simulacrum lasts until it "
        + "drops to 0 hit points, at which point it reverts to snow and melts instantly. If you "
        + "cast this spell again, any currently active duplicates you created with this spell are "
        + "instantly destroyed."),
    SYMBOL(MagicSchool.ABJURATION, 7, "1 minute", "Touch", "V, S, M ",
        "Until dispelled or triggered", "When you cast this spell, you inscribe a harmful "
        + "glyph either on a surface (such as a section of floor, a wall, or a table) or within "
        + "an object that can be closed to conceal the glyph (such as a book, a scroll, or a "
        + "treasure chest). If you choose a surface, the glyph can cover an area of the surface "
        + "no larger than 10 feet in diameter. If you choose an object, that object must remain "
        + "in its place; if the object is moved more than 10 feet from where you cast this spell, "
        + "the glyph is broken, and the spell ends without being triggered. The glyph is nearly "
        + "invisible, requiring an Intelligence (Investigation) check against your spell save DC "
        + "to find it. You decide what triggers the glyph when you cast the spell. For glyphs "
        + "inscribed on a surface, the most typical triggers include touching or stepping on the "
        + "glyph, removing another object covering it, approaching within a certain distance of "
        + "it, or manipulating the object that holds it. For glyphs inscribed within an object, "
        + "the most common triggers are opening the object, approaching within a certain distance "
        + "of it, or seeing or reading the glyph. You can further refine the trigger so the spell "
        + "is activated only under certain circumstances or according to a creature's physical "
        + "characteristics (such as height or weight), or physical kind (for example, the ward "
        + "could be set to affect hags or shapechangers). You can also specify creatures that "
        + "don't trigger the glyph, such as those who say a certain password. When you inscribe "
        + "the glyph, choose one of the options below for its effect. Once triggered, the glyph "
        + "glows, filling a 60-foot-radius sphere with dim light for 10 minutes, after which time "
        + "the spell ends. Each creature in the sphere when the glyph activates is targeted by "
        + "its effect, as is a creature that enters the sphere for the first time on a turn or "
        + "ends its turn there. Death. Each target must make a Constitution saving throw, taking "
        + "10d10 necrotic damage on a failed save, or half as much damage on a successful save. "
        + "Discord. Each target must make a Constitution saving throw. On a failed save, a target "
        + "bickers and argues with other creatures for 1 minute. During this time, it is "
        + "incapable of meaningful communication and has disadvantage on attack rolls and ability "
        + "checks. Fear. Each target must make a Wisdom saving throw and becomes frightened for 1 "
        + "minute on a failed save. While frightened, the target drops whatever it is holding and "
        + "must move at least 30 feet away from the glyph on each of its turns, if able. "
        + "Hopelessness. Each target must make a Charisma saving throw. On a failed save, the "
        + "target is overwhelmed with despair for 1 minute. During this time, it can't attack or "
        + "target any creature with harmful abilities, spells, or other magical effects. "
        + "Insanity. Each target must make an Intelligence saving throw. On a failed save, the "
        + "target is driven insane for 1 minute. An insane creature can't take actions, can't "
        + "understand what other creatures say, can't read, and speaks only in gibberish. The GM "
        + "controls its movement, which is erratic. Pain. Each target must make a Constitution "
        + "saving throw and becomes incapacitated with excruciating pain for 1 minute on a failed "
        + "save. Sleep. Each target must make a Wisdom saving throw and falls unconscious for 10 "
        + "minutes on a failed save. A creature awakens if it takes damage or if someone uses an "
        + "action to shake or slap it awake. Stunning. Each target must make a Wisdom saving "
        + "throw and becomes stunned for 1 minute on a failed save."),
    ANTIMAGIC_FIELD(MagicSchool.ABJURATION, 8, "1 action", "Self (10-foot-radius sphere)",
        "V, S, M ", "up to 1 hour", "A 10-foot-radius invisible sphere of antimagic surrounds "
        + "you. This area is divorced from the magical energy that suffuses the multiverse. "
        + "Within the sphere, spells canft be cast, summoned creatures disappear, and even magic "
        + "items become mundane. Until the spell ends, the sphere moves with you, centered on "
        + "you. Spells and other magical effects, except those created by an artifact or a deity, "
        + "are suppressed in the sphere and can't protrude into it. A slot expended to cast a "
        + "suppressed spell is consumed. While an effect is suppressed, it doesn't function, but "
        + "the time it spends suppressed counts against its duration. Targeted Effects. Spells "
        + "and other magical effects, such as magic missile and charm person, that target a "
        + "creature or an object in the sphere have no effect on that target. Areas of Magic. The "
        + "area of another spell or magical effect, such as fireball, can't extend into the "
        + "sphere. If the sphere overlaps an area of magic, the part of the area that is covered "
        + "by the sphere is suppressed. For example, the flames created by a wall of fire are "
        + "suppressed within the sphere, creating a gap in the wall if the overlap is large "
        + "enough. Spells. Any active spell or other magical effect on a creature or an object in "
        + "the sphere is suppressed while the creature or object is in it. Magic Items. The "
        + "properties and powers of magic items are suppressed in the sphere. For example, a +1 "
        + "longsword in the sphere functions as a nonmagical longsword. A magic weapon's "
        + "properties and powers are suppressed if it is used against a target in the sphere or "
        + "wielded by an attacker in the sphere. If a magic weapon or a piece of magic ammunition "
        + "fully leaves the sphere (for example, if you fire a magic arrow or throw a magic spear "
        + "at a target outside the sphere), the magic of the item ceases to be suppressed as soon "
        + "as it exits. Magical Travel. Teleportation and planar travel fail to work in the "
        + "sphere, whether the sphere is the destination or the departure point for such magical "
        + "travel. A portal to another location, world, or plane of existence, as well as an "
        + "opening to an extradimensional space such as that created by the rope trick spell, "
        + "temporarily closes while in the sphere. Creatures and Objects. A creature or object "
        + "summoned or created by magic temporarily winks out of existence in the sphere. Such a "
        + "creature instantly reappears once the space the creature occupied is no longer within "
        + "the sphere. Dispel Magic. Spells and magical effects such as dispel magic have no "
        + "effect on the sphere. Likewise, the spheres created by different antimagic field "
        + "spells don't nullify each other."),
    ANTIPATHY_SYMPATHY(MagicSchool.ENCHANTMENT, 8, "1 hour", "60 feet",
        "V, S, M ", "10 days", "This spell attracts or repels creatures of your choice. You "
        + "target something within range, either a Huge or smaller object or creature or an area "
        + "that is no larger than a 200-foot cube. Then specify a kind of intelligent creature, "
        + "such as red dragons, goblins, or vampires. You invest the target with an aura that "
        + "either attracts or repels the specified creatures for the duration. Choose antipathy "
        + "or sympathy as the aura's effect. Antipathy. The enchantment causes creatures of the "
        + "kind you designated to feel an intense urge to leave the area and avoid the target. "
        + "When such a creature can see the target or comes within 60 feet of it, the creature "
        + "must succeed on a Wisdom saving throw or become frightened. The creature remains "
        + "frightened while it can see the target or is within 60 feet of it. While frightened by "
        + "the target, the creature must use its movement to move to the nearest safe spot from "
        + "which it can't see the target. If the creature moves more than 60 feet from the target "
        + "and can't see it, the creature is no longer frightened, but the creature becomes "
        + "frightened again if it regains sight of the target or moves within 60 feet of it. "
        + "Sympathy. The enchantment causes the specified creatures to feel an intense urge to "
        + "approach the target while within 60 feet of it or able to see it. When such a creature "
        + "can see the target or comes within 60 feet of it, the creature must succeed on a "
        + "Wisdom saving throw or use its movement on each of its turns to enter the area or move "
        + "within reach of the target. When the creature has done so, it can't willingly move "
        + "away from the target. If the target damages or otherwise harms an affected creature, "
        + "the affected creature can make a Wisdom saving throw to end the effect, as described "
        + "below. Ending the Effect. If an affected creature ends its turn while not within 60 "
        + "feet of the target or able to see it, the creature makes a Wisdom saving throw. On a "
        + "successful save, the creature is no longer affected by the target and recognizes the "
        + "feeling of repugnance or attraction as magical. In addition, a creature affected by "
        + "the spell is allowed another Wisdom saving throw every 24 hours while the spell "
        + "persists. A creature that successfully saves against this effect is immune to it for 1 "
        + "minute, after which time it can be affected again."),
    CLONE(MagicSchool.NECROMANCY, 8, "1 hour", "Touch", "V, S, M ",
        "Instantaneous", "This spell grows an inert duplicate of a living creature as a "
        + "safeguard against death. This clone forms inside a sealed vessel and grows to full "
        + "size and maturity after 120 days; you can also choose to have the clone be a younger "
        + "version of the same creature. It remains inert and endures indefinitely, as long as "
        + "its vessel remains undisturbed. At any time after the clone matures, if the original "
        + "creature dies, its soul transfers to the clone, provided that the soul is free and "
        + "willing to return. The clone is physically identical to the original and has the same "
        + "personality, memories, and abilities, but none of the original's equipment. The "
        + "original creature's physical remains, if they still exist, become inert and can't "
        + "thereafter be restored to life, since the creature's soul is elsewhere."),
    CONTROL_WEATHER(MagicSchool.TRANSMUTATION, 8, "10 minutes",
        "Self (5-mile radius)", "V, S, M ", "up to 8 hours", "You take control of the weather "
        + "within 5 miles of you for the duration. You must be outdoors to cast this spell. "
        + "Moving to a place where you don't have a clear path to the sky ends the spell early. "
        + "When you cast the spell, you change the current weather conditions, which are "
        + "determined by the GM based on the climate and season. You can change precipitation, "
        + "temperature, and wind. It takes 1d4 × 10 minutes for the new conditions to take "
        + "effect. Once they do so, you can change the conditions again. When the spell ends, the "
        + "weather gradually returns to normal. When you change the weather conditions, find a "
        + "current condition on the following tables and change its stage by one, up or down. "
        + "When changing the wind, you can change its direction. Precipitation Stage Condition 1 "
        + "Clear 2 Light clouds 3 Overcast or ground fog 4 Rain, hail or snow 5 Torrential rain, "
        + "driving hail, or blizzard Temperature Stage Condition 1 Unbearable heat 2 Hot 3 Warm 4 "
        + "Cool 5 Cold 6 Arctic cold Wind Stage Condition 1 Calm 2 Moderate wind 3 Strong wind 4 "
        + "Gale 5 Storm"),
    DEMIPLANE(MagicSchool.CONJURATION, 8, "1 action", "60 feet",
        "S", "1 hour", "You create a shadowy door on a flat solid surface that you can see "
        + "within range. The door is large enough to allow Medium creatures to pass through "
        + "unhindered. When opened, the door leads to a demiplane that appears to be an empty "
        + "room 30 feet in each dimension, made of wood or stone. When the spell ends, the door "
        + "disappears, and any creatures or objects inside the demiplane remain trapped there, as "
        + "the door also disappears from the other side. Each time you cast this spell, you can "
        + "create a new demiplane, or have the shadowy door connect to a demiplane you created "
        + "with a previous casting of this spell. Additionally, if you know the nature and "
        + "contents of a demiplane created by a casting of this spell by another creature, you "
        + "can have the shadowy door connect to its demiplane instead."),
    DOMINATE_MONSTER(MagicSchool.ENCHANTMENT, 8, "1 action", "60 feet",
        "V, S", "up to 1 hour", "You attempt to beguile a creature that you can see within "
        + "range. It must succeed on a Wisdom saving throw or be charmed by you for the duration. "
        + "If you or creatures that are friendly to you are fighting it, it has advantage on the "
        + "saving throw. While the creature is charmed, you have a telepathic link with it as "
        + "long as the two of you are on the same plane of existence. You can use this telepathic "
        + "link to issue commands to the creature while you are conscious (no action required), "
        + "which it does its best to obey. You can specify a simple and general course of action, "
        + "such as 'Attack that creature,' 'Run over there,' or 'Fetch that object.' If the "
        + "creature completes the order and doesn't receive further direction from you, it "
        + "defends and preserves itself to the best of its ability. You can use your action to "
        + "take total and precise control of the target. Until the end of your next turn, the "
        + "creature takes only the actions you choose, and doesn't do anything that you don't "
        + "allow it to do. During this time, you can also cause the creature to use a reaction, "
        + "but this requires you to use your own reaction as well. Each time the target takes "
        + "damage, it makes a new Wisdom saving throw against the spell. If the saving throw "
        + "succeeds, the spell ends. At Higher Levels. When you cast this spell with a 9th-level "
        + "spell slot, the duration is concentration, up to 8 hours."),
    EARTHQUAKE(MagicSchool.EVOCATION, 8, "1 action", "500 feet",
        "V, S, M ", "up to 1 minute", "You create a seismic disturbance at a point on the "
        + "ground that you can see within range. For the duration, an intense tremor rips through "
        + "the ground in a 100-foot-radius circle centered on that point and shakes creatures and "
        + "structures in contact with the ground in that area. The ground in the area becomes "
        + "difficult terrain. Each creature on the ground that is concentrating must make a "
        + "Constitution saving throw. On a failed save, the creature's concentration is broken. "
        + "When you cast this spell and at the end of each turn you spend concentrating on it, "
        + "each creature on the ground in the area must make a Dexterity saving throw. On a "
        + "failed save, the creature is knocked prone. This spell can have additional effects "
        + "depending on the terrain in the area, as determined by the GM. Fissures. Fissures open "
        + "throughout the spell's area at the start of your next turn after you cast the spell. A "
        + "total of 1d6 such fissures open in locations chosen by the GM. Each is 1d10 × 10 feet "
        + "deep, 10 feet wide, and extends from one edge of the spell's area to the opposite "
        + "side. A creature standing on a spot where a fissure opens must succeed on a Dexterity "
        + "saving throw or fall in. A creature that successfully saves moves with the fissure's "
        + "edge as it opens. A fissure that opens beneath a structure causes it to automatically "
        + "collapse (see below). Structures. The tremor deals 50 bludgeoning damage to any "
        + "structure in contact with the ground in the area when you cast the spell and at the "
        + "start of each of your turns until the spell ends. If a structure drops to 0 hit "
        + "points, it collapses and potentially damages nearby creatures. A creature within half "
        + "the distance of a structure's height must make a Dexterity saving throw. On a failed "
        + "save, the creature takes 5d6 bludgeoning damage, is knocked prone, and is buried in "
        + "the rubble, requiring a DC 20 Strength (Athletics) check as an action to escape. The "
        + "GM can adjust the DC higher or lower, depending on the nature of the rubble. On a "
        + "successful save, the creature takes half as much damage and doesn't fall prone or "
        + "become buried."),
    FEEBLEMIND(MagicSchool.ENCHANTMENT, 8, "1 action", "150 feet",
        "V, S, M ", "Instantaneous", "You blast the mind of a creature that you can see "
        + "within range, attempting to shatter its intellect and personality. The target takes "
        + "4d6 psychic damage and must make an Intelligence saving throw. On a failed save, the "
        + "creature's Intelligence and Charisma scores become 1. The creature can't cast spells, "
        + "activate magic items, understand language, or communicate in any intelligible way. The "
        + "creature can, however, identify its friends, follow them, and even protect them. At "
        + "the end of every 30 days, the creature can repeat its saving throw against this spell. "
        + "If it succeeds on its saving throw, the spell ends. The spell can also be ended by "
        + "greater restoration, heal, or wish."),
    GLIBNESS(MagicSchool.TRANSMUTATION, 8, "1 action", "Self",
        "V", "1 hour", "Until the spell ends, when you make a Charisma check, you can replace "
        + "the number you roll with a 15. Additionally, no matter what you say, magic that would "
        + "determine if you are telling the truth indicates that you are being truthful."),
    HOLY_AURA(MagicSchool.ABJURATION, 8, "1 action", "Self", "V, S, M ",
        "up to 1 minute", "Divine light washes out from you and coalesces in a soft radiance "
        + "in a 30-foot radius around you. Creatures of your choice in that radius when you cast "
        + "this spell shed dim light in a 5-foot radius and have advantage on all saving throws, "
        + "and other creatures have disadvantage on attack rolls against them until the spell "
        + "ends. In addition, when a fiend or an undead hits an affected creature with a melee "
        + "attack, the aura flashes with brilliant light. The attacker must succeed on a "
        + "Constitution saving throw or be blinded until the spell ends."),
    INCENDIARY_CLOUD(MagicSchool.CONJURATION, 8, "1 action", "150 feet",
        "V, S", "up to 1 minute", "A swirling cloud of smoke shot through with white- hot "
        + "embers appears in a 20-foot-radius sphere centered on a point within range. The cloud "
        + "spreads around corners and is heavily obscured. It lasts for the duration or until a "
        + "wind of moderate or greater speed (at least 10 miles per hour) disperses it. When the "
        + "cloud appears, each creature in it must make a Dexterity saving throw. A creature "
        + "takes 10d8 fire damage on a failed save, or half as much damage on a successful one. A "
        + "creature must also make this saving throw when it enters the spell's area for the "
        + "first time on a turn or ends its turn there. The cloud moves 10 feet directly away "
        + "from you in a direction that you choose at the start of each of your turns."),
    MAZE(MagicSchool.CONJURATION, 8, "1 action", "60 feet", "V, S",
        "up to 10 minutes", "You banish a creature that you can see within range into a "
        + "labyrinthine demiplane. The target remains there for the duration or until it escapes "
        + "the maze. The target can use its action to attempt to escape. When it does so, it "
        + "makes a DC 20 Intelligence check. If it succeeds, it escapes, and the spell ends (a "
        + "minotaur or goristro demon automatically succeeds). When the spell ends, the target "
        + "reappears in the space it left or, if that space is occupied, in the nearest "
        + "unoccupied space."),
    MIND_BLANK(MagicSchool.ABJURATION, 8, "1 action", "Touch",
        "V, S", "24 hours", "Until the spell ends, one willing creature you touch is immune "
        + "to psychic damage, any effect that would sense its emotions or read its thoughts, "
        + "divination spells, and the charmed condition. The spell even foils wish spells and "
        + "spells or effects of similar power used to affect the target's mind or to gain "
        + "information about the target."),
    POWER_WORD_STUN(MagicSchool.ENCHANTMENT, 8, "1 action", "60 feet",
        "V", "Instantaneous", "You speak a word of power that can overwhelm the mind of one "
        + "creature you can see within range, leaving it dumbfounded. If the target has 150 hit "
        + "points or fewer, it is stunned. Otherwise, the spell has no effect. The stunned target "
        + "must make a Constitution saving throw at the end of each of its turns. On a successful "
        + "save, this stunning effect ends."),
    SUNBURST(MagicSchool.EVOCATION, 8, "1 action", "150 feet",
        "V, S, M ", "Instantaneous", "Brilliant sunlight flashes in a 60-foot radius centered "
        + "on a point you choose within range. Each creature in that light must make a "
        + "Constitution saving throw. On a failed save, a creature takes 12d6 radiant damage and "
        + "is blinded for 1 minute. On a successful save, it takes half as much damage and isn't "
        + "blinded by this spell. Undead and oozes have disadvantage on this saving throw. A "
        + "creature blinded by this spell makes another Constitution saving throw at the end of "
        + "each of its turns. On a successful save, it is no longer blinded. This spell dispels "
        + "any darkness in its area that was created by a spell."),
    ASTRAL_PROJECTION(MagicSchool.NECROMANCY, 9, "1 hour", "10 feet",
        "V, S, M ", "Special", "You and up to eight willing creatures within range project "
        + "your astral bodies into the Astral Plane (the spell fails and the casting is wasted if "
        + "you are already on that plane). The material body you leave behind is unconscious and "
        + "in a state of suspended animation; it doesn't need food or air and doesn't age. Your "
        + "astral body resembles your mortal form in almost every way, replicating your game "
        + "statistics and possessions. The principal difference is the addition of a silvery cord "
        + "that extends from between your shoulder blades and trails behind you, fading to "
        + "invisibility after 1 foot. This cord is your tether to your material body. As long as "
        + "the tether remains intact, you can find your way home. If the cord is cut--something "
        + "that can happen only when an effect specifically states that it does--your soul and "
        + "body are separated, killing you instantly. Your astral form can freely travel through "
        + "the Astral Plane and can pass through portals there leading to any other plane. If you "
        + "enter a new plane or return to the plane you were on when casting this spell, your "
        + "body and possessions are transported along the silver cord, allowing you to re-enter "
        + "your body as you enter the new plane. Your astral form is a separate incarnation. Any "
        + "damage or other effects that apply to it have no effect on your physical body, nor do "
        + "they persist when you return to it. The spell ends for you and your companions when "
        + "you use your action to dismiss it. When the spell ends, the affected creature returns "
        + "to its physical body, and it awakens. The spell might also end early for you or one of "
        + "your companions. A successful dispel magic spell used against an astral or physical "
        + "body ends the spell for that creature. If a creature's original body or its astral "
        + "form drops to 0 hit points, the spell ends for that creature. If the spell ends and "
        + "the silver cord is intact, the cord pulls the creature's astral form back to its body, "
        + "ending its state of suspended animation. If you are returned to your body prematurely, "
        + "your companions remain in their astral forms and must find their own way back to their "
        + "bodies, usually by dropping to 0 hit points."),
    FORESIGHT(MagicSchool.DIVINATION, 9, "1 minute", "Touch", "V, S, M ",
        "8 hours", "You touch a willing creature and bestow a limited ability to see into the "
        + "immediate future. For the duration, the target can't be surprised and has advantage on "
        + "attack rolls, ability checks, and saving throws. Additionally, other creatures have "
        + "disadvantage on attack rolls against the target for the duration. This spell "
        + "immediately ends if you cast it again before its duration ends."),
    GATE(MagicSchool.CONJURATION, 9, "1 action", "60 feet", "V, S, M ",
        "up to 1 minute", "You conjure a portal linking an unoccupied space you can see "
        + "within range to a precise location on a different plane of existence. The portal is a "
        + "circular opening, which you can make 5 to 20 feet in diameter. You can orient the "
        + "portal in any direction you choose. The portal lasts for the duration. The portal has "
        + "a front and a back on each plane where it appears. Travel through the portal is "
        + "possible only by moving through its front. Anything that does so is instantly "
        + "transported to the other plane, appearing in the unoccupied space nearest to the "
        + "portal. Deities and other planar rulers can prevent portals created by this spell from "
        + "opening in their presence or anywhere within their domains. When you cast this spell, "
        + "you can speak the name of a specific creature (a pseudonym, title, or nickname doesn't "
        + "work). If that creature is on a plane other than the one you are on, the portal opens "
        + "in the named creature's immediate vicinity and draws the creature through it to the "
        + "nearest unoccupied space on your side of the portal. You gain no special power over "
        + "the creature, and it is free to act as the GM deems appropriate. It might leave, "
        + "attack you, or help you."),
    MASS_HEAL(MagicSchool.EVOCATION, 9, "1 action", "60 feet",
        "V, S", "Instantaneous", "A flood of healing energy flows from you into injured "
        + "creatures around you. You restore up to 700 hit points, divided as you choose among "
        + "any number of creatures that you can see within range. Creatures healed by this spell "
        + "are also cured of all diseases and any effect making them blinded or deafened. This "
        + "spell has no effect on undead or constructs."),
    METEOR_SWARM(MagicSchool.EVOCATION, 9, "1 action", "1 mile",
        "V, S", "Instantaneous", "Blazing orbs of fire plummet to the ground at four "
        + "different points you can see within range. Each creature in a 40-foot-radius sphere "
        + "centered on each point you choose must make a Dexterity saving throw. The sphere "
        + "spreads around corners. A creature takes 20d6 fire damage and 20d6 bludgeoning damage "
        + "on a failed save, or half as much damage on a successful one. A creature in the area "
        + "of more than one fiery burst is affected only once. The spell damages objects in the "
        + "area and ignites flammable objects that aren't being worn or carried."),
    POWER_WORD_KILL(MagicSchool.ENCHANTMENT, 9, "1 action", "60 feet",
        "V", "Instantaneous", "You utter a word of power that can compel one creature you can "
        + "see within range to die instantly. If the creature you choose has 100 hit points or "
        + "fewer, it dies. Otherwise, the spell has no effect."),
    PRISMATIC_WALL(MagicSchool.ABJURATION, 9, "1 action", "60 feet",
        "V, S", "10 minutes", "A shimmering, multicolored plane of light forms a vertical "
        + "opaque wall--up to 90 feet long, 30 feet high, and 1 inch thick--centered on a point "
        + "you can see within range. Alternatively, you can shape the wall into a sphere up to 30 "
        + "feet in diameter centered on a point you choose within range. The wall remains in "
        + "place for the duration. If you position the wall so that it passes through a space "
        + "occupied by a creature, the spell fails, and your action and the spell slot are "
        + "wasted. The wall sheds bright light out to a range of 100 feet and dim light for an "
        + "additional 100 feet. You and creatures you designate at the time you cast the spell "
        + "can pass through and remain near the wall without harm. If another creature that can "
        + "see the wall moves to within 20 feet of it or starts its turn there, the creature must "
        + "succeed on a Constitution saving throw or become blinded for 1 minute. The wall "
        + "consists of seven layers, each with a different color. When a creature attempts to "
        + "reach into or pass through the wall, it does so one layer at a time through all the "
        + "wall's layers. As it passes or reaches through each layer, the creature must make a "
        + "Dexterity saving throw or be affected by that layer's properties as described below. "
        + "The wall can be destroyed, also one layer at a time, in order from red to violet, by "
        + "means specific to each layer. Once a layer is destroyed, it remains so for the "
        + "duration of the spell. A rod of cancellation destroys a prismatic wall, but an "
        + "antimagic field has no effect on it. 1. Red. The creature takes 10d6 fire damage on a "
        + "failed save, or half as much damage on a successful one. While this layer is in place, "
        + "nonmagical ranged attacks can't pass through the wall. The layer can be destroyed by "
        + "dealing at least 25 cold damage to it. 2. Orange. The creature takes 10d6 acid damage "
        + "on a failed save, or half as much damage on a successful one. While this layer is in "
        + "place, magical ranged attacks can't pass through the wall. The layer is destroyed by a "
        + "strong wind. 3. Yellow. The creature takes 10d6 lightning damage on a failed save, or "
        + "half as much damage on a successful one. This layer can be destroyed by dealing at "
        + "least 60 force damage to it. 4. Green. The creature takes 10d6 poison damage on a "
        + "failed save, or half as much damage on a successful one. A passwall spell, or another "
        + "spell of equal or greater level that can open a portal on a solid surface, destroys "
        + "this layer. 5. Blue. The creature takes 10d6 cold damage on a failed save, or half as "
        + "much damage on a successful one. This layer can be destroyed by dealing at least 25 "
        + "fire damage to it. 6. Indigo. On a failed save, the creature is restrained. It must "
        + "then make a Constitution saving throw at the end of each of its turns. If it "
        + "successfully saves three times, the spell ends. If it fails its save three times, it "
        + "permanently turns to stone and is subjected to the petrified condition. The successes "
        + "and failures don't need to be consecutive; keep track of both until the creature "
        + "collects three of a kind. While this layer is in place, spells can't be cast through "
        + "the wall. The layer is destroyed by bright light shed by a daylight spell or a similar "
        + "spell of equal or higher level. 7. Violet. On a failed save, the creature is blinded. "
        + "It must then make a Wisdom saving throw at the start of your next turn. A successful "
        + "save ends the blindness. If it fails that save, the creature is transported to another "
        + "plane of the GM's choosing and is no longer blinded. (Typically, a creature that is on "
        + "a plane that isn't its home plane is banished home, while other creatures are usually "
        + "cast into the Astral or Ethereal planes.) This layer is destroyed by a dispel magic "
        + "spell or a similar spell of equal or higher level that can end spells and magical "
        + "effects."),
    SHAPECHANGE(MagicSchool.TRANSMUTATION, 9, "1 action", "Self",
        "V, S, M ", "up to 1 hour", "You assume the form of a different creature for the "
        + "duration. The new form can be of any creature with a challenge rating equal to your "
        + "level or lower. The creature can't be a construct or an undead, and you must have seen "
        + "the sort of creature at least once. You transform into an average example of that "
        + "creature, one without any class levels or the Spellcasting trait. Your game statistics "
        + "are replaced by the statistics of the chosen creature, though you retain your "
        + "alignment and Intelligence, Wisdom, and Charisma scores. You also retain all of your "
        + "skill and saving throw proficiencies, in addition to gaining those of the creature. If "
        + "the creature has the same proficiency as you and the bonus listed in its statistics is "
        + "higher than yours, use the creature's bonus in place of yours. You can't use any "
        + "legendary actions or lair actions of the new form. You assume the hit points and Hit "
        + "Dice of the new form. When you revert to your normal form, you return to the number of "
        + "hit points you had before you transformed. If you revert as a result of dropping to 0 "
        + "hit points, any excess damage carries over to your normal form. As long as the excess "
        + "damage doesn't reduce your normal form to 0 hit points, you aren't knocked "
        + "unconscious. You retain the benefit of any features from your class, race, or other "
        + "source and can use them, provided that your new form is physically capable of doing "
        + "so. You can't use any special senses you have (for example, darkvision) unless your "
        + "new form also has that sense. You can only speak if the creature can normally speak. "
        + "When you transform, you choose whether your equipment falls to the ground, merges into "
        + "the new form, or is worn by it. Worn equipment functions as normal. The GM determines "
        + "whether it is practical for the new form to wear a piece of equipment, based on the "
        + "creature's shape and size. Your equipment doesn't change shape or size to match the "
        + "new form, and any equipment that the new form can't wear must either fall to the "
        + "ground or merge into your new form. Equipment that merges has no effect in that state. "
        + "During this spell's duration, you can use your action to assume a different form "
        + "following the same restrictions and rules for the original form, with one exception: "
        + "if your new form has more hit points than your current one, your hit points remain at "
        + "their current value."),
    STORM_OF_VENGEANCE(MagicSchool.CONJURATION, 9, "1 action",
        "Sight", "V, S", "up to 1 minute", "A churning storm cloud forms, centered on a point "
        + "you can see and spreading to a radius of 360 feet. Lightning flashes in the area, "
        + "thunder booms, and strong winds roar. Each creature under the cloud (no more than "
        + "5,000 feet beneath the cloud) when it appears must make a Constitution saving throw. "
        + "On a failed save, a creature takes 2d6 thunder damage and becomes deafened for 5 "
        + "minutes. Each round you maintain concentration on this spell, the storm produces "
        + "additional effects on your turn. Round 2. Acidic rain falls from the cloud. Each "
        + "creature and object under the cloud takes 1d6 acid damage. Round 3. You call six bolts "
        + "of lightning from the cloud to strike six creatures or objects of your choice beneath "
        + "the cloud. A given creature or object can't be struck by more than one bolt. A struck "
        + "creature must make a Dexterity saving throw. The creature takes 10d6 lightning damage "
        + "on a failed save, or half as much damage on a successful one. Round 4. Hailstones rain "
        + "down from the cloud. Each creature under the cloud takes 2d6 bludgeoning damage. Round "
        + "5–10. Gusts and freezing rain assail the area under the cloud. The area becomes "
        + "difficult terrain and is heavily obscured. Each creature there takes 1d6 cold damage. "
        + "Ranged weapon attacks in the area are impossible. The wind and rain count as a severe "
        + "distraction for the purposes of maintaining concentration on spells. Finally, gusts of "
        + "strong wind (ranging from 20 to 50 miles per hour) automatically disperse fog, mists, "
        + "and similar phenomena in the area, whether mundane or magical."),
    TIME_STOP(MagicSchool.TRANSMUTATION, 9, "1 action", "Self",
        "V", "Instantaneous", "You briefly stop the flow of time for everyone but yourself. "
        + "No time passes for other creatures, while you take 1d4 + 1 turns in a row, during "
        + "which you can use actions and move as normal. This spell ends if one of the actions "
        + "you use during this period, or any effects that you create during this period, affects "
        + "a creature other than you or an object being worn or carried by someone other than "
        + "you. In addition, the spell ends if you move to a place more than 1,000 feet from the "
        + "location where you cast it."),
    TRUE_POLYMORPH(MagicSchool.TRANSMUTATION, 9, "1 action", "30 feet",
        "V, S, M ", "up to 1 hour", "Choose one creature or nonmagical object that you can "
        + "see within range. You transform the creature into a different creature, the creature "
        + "into an object, or the object into a creature (the object must be neither worn nor "
        + "carried by another creature). The transformation lasts for the duration, or until the "
        + "target drops to 0 hit points or dies. If you concentrate on this spell for the full "
        + "duration, the transformation lasts until it is dispelled. This spell has no effect on "
        + "a shapechanger or a creature with 0 hit points. An unwilling creature can make a "
        + "Wisdom saving throw, and if it succeeds, it isn't affected by this spell. Creature "
        + "into Creature. If you turn a creature into another kind of creature, the new form can "
        + "be any kind you choose whose challenge rating is equal to or less than the target's "
        + "(or its level, if the target doesn't have a challenge rating). The target's game "
        + "statistics, including mental ability scores, are replaced by the statistics of the new "
        + "form. It retains its alignment and personality. The target assumes the hit points of "
        + "its new form, and when it reverts to its normal form, the creature returns to the "
        + "number of hit points it had before it transformed. If it reverts as a result of "
        + "dropping to 0 hit points, any excess damage carries over to its normal form. As long "
        + "as the excess damage doesn't reduce the creature's normal form to 0 hit points, it "
        + "isn't knocked unconscious. The creature is limited in the actions it can perform by "
        + "the nature of its new form, and it can't speak, cast spells, or take any other action "
        + "that requires hands or speech, unless its new form is capable of such actions. The "
        + "target's gear melds into the new form. The creature can't activate, use, wield, or "
        + "otherwise benefit from any of its equipment. Object into Creature. You can turn an "
        + "object into any kind of creature, as long as the creature's size is no larger than the "
        + "object's size and the creature's challenge rating is 9 or lower. The creature is "
        + "friendly to you and your companions. It acts on each of your turns. You decide what "
        + "action it takes and how it moves. The GM has the creature's statistics and resolves "
        + "all of its actions and movement. If the spell becomes permanent, you no longer control "
        + "the creature. It might remain friendly to you, depending on how you have treated it. "
        + "Creature into Object. If you turn a creature into an object, it transforms along with "
        + "whatever it is wearing and carrying into that form. The creature's statistics become "
        + "those of the object, and the creature has no memory of time spent in this form, after "
        + "the spell ends and it returns to its normal form."),
    TRUE_RESURRECTION(MagicSchool.NECROMANCY, 9, "1 hour", "Touch",
        "V, S, M ", "Instantaneous", "You touch a creature that has been dead for no longer "
        + "than 200 years and that died for any reason except old age. If the creature's soul is "
        + "free and willing, the creature is restored to life with all its hit points. This spell "
        + "closes all wounds, neutralizes any poison, cures all diseases, and lifts any curses "
        + "affecting the creature when it died. The spell replaces damaged or missing organs and "
        + "limbs. The spell can even provide a new body if the original no longer exists, in "
        + "which case you must speak the creature's name. The creature then appears in an "
        + "unoccupied space you choose within 10 feet of you."),
    WEIRD(MagicSchool.ILLUSION, 9, "1 action", "120 feet", "V, S",
        "up to one minute", "Drawing on the deepest fears of a group of creatures, you create "
        + "illusory creatures in their minds, visible only to them. Each creature in a "
        + "30-foot-radius sphere centered on a point of your choice within range must make a "
        + "Wisdom saving throw. On a failed save, a creature becomes frightened for the duration. "
        + "The illusion calls on the creature's deepest fears, manifesting its worst nightmares "
        + "as an implacable threat. At the end of each of the frightened creature's turns, it "
        + "must succeed on a Wisdom saving throw or take 4d10 psychic damage. On a successful "
        + "save, the spell ends for that creature."),
    WISH(MagicSchool.CONJURATION, 9, "1 action", "Self", "V", "Instantaneous",
        "Wish is the mightiest spell a mortal creature can cast. By simply speaking aloud, "
        + "you can alter the very foundations of reality in accord with your desires. The basic "
        + "use of this spell is to duplicate any other spell of 8th level or lower. You don't "
        + "need to meet any requirements in that spell, including costly components. The spell "
        + "simply takes effect. Alternatively, you can create one of the following effects of "
        + "your choice: You create one object of up to 25,000 gp in value that isn't a magic "
        + "item. The object can be no more than 300 feet in any dimension, and it appears in an "
        + "unoccupied space you can see on the ground. You allow up to twenty creatures that you "
        + "can see to regain all hit points, and you end all effects on them described in the "
        + "greater restoration spell. You grant up to ten creatures that you can see resistance "
        + "to a damage type you choose. You grant up to ten creatures you can see immunity to a "
        + "single spell or other magical effect for 8 hours. For instance, you could make "
        + "yourself and all your companions immune to a lich's life drain attack. * You undo a "
        + "single recent event by forcing a reroll of any roll made within the last round "
        + "(including your last turn). Reality reshapes itself to accommodate the new result. For "
        + "example, a wish spell could undo an opponent's successful save, a foe's critical hit, "
        + "or a friend's failed save. You can force the reroll to be made with advantage or "
        + "disadvantage, and you can choose whether to use the reroll or the original roll. You "
        + "might be able to achieve something beyond the scope of the above examples. State your "
        + "wish to the GM as precisely as possible. The GM has great latitude in ruling what "
        + "occurs in such an instance; the greater the wish, the greater the likelihood that "
        + "something goes wrong. This spell might simply fail, the effect you desire might only "
        + "be partly achieved, or you might suffer some unforeseen consequence as a result of how "
        + "you worded the wish. For example, wishing that a villain were dead might propel you "
        + "forward in time to a period when that villain is no longer alive, effectively removing "
        + "you from the game. Similarly, wishing for a legendary magic item or artifact might "
        + "instantly transport you to the presence of the item's current owner. The stress of "
        + "casting this spell to produce any effect other than duplicating another spell weakens "
        + "you. After enduring that stress, each time you cast a spell until you finish a long "
        + "rest, you take 1d10 necrotic damage per level of that spell. This damage can't be "
        + "reduced or prevented in any way. In addition, your Strength drops to 3, if it isn't 3 "
        + "or lower already, for 2d4 days. For each of those days that you spend resting and "
        + "doing nothing more than light activity, your remaining recovery time decreases by 2 "
        + "days. Finally, there is a 33 percent chance that you are unable to cast wish ever "
        + "again if you suffer this stress."),
    ACID_SPLASH(MagicSchool.CONJURATION, 0, "1 action", "60 feet",
        "Up to 2 creatures within 5 feet", "V, S", "Instantaneous",
        "[max($level 1:1d6,5:2d6,11:3d6,17:4d6)] acid damage.\nDex. save for no damage."),
    BLADE_WARD(MagicSchool.ABJURATION, 0, "1 action", "Self", "-",
        "V, S ", "1 round", "Resistance against bludgeoning, piercing and slashing damage "
        + "from weapons."),
    CHILL_TOUCH(MagicSchool.NECROMANCY, 0, "1 action", "120 feet",
        "1 creature", "V, S", "1 round", "Ranged spell attack [max($level "
        + "1:1,5:2,11:3,17:4)]d8 necrotic damage. No healing for 1 turn. Undead have disadvantage "
        + "for 1 turn."),
    DANCING_LIGHTS(MagicSchool.EVOCATION, 0, "1 action", "120 feet",
        "up to 4 lights", "V, S, M ", "up to 1 minute", "Create lights. Move each up to 60 "
        + "feet as bons action."),
    DRUIDCRAFT(MagicSchool.TRANSMUTATION, 0, "1 action", "30 feet",
        "-", "V, S", "Instantaneous", "Create sensory effect, predict weather or light or "
        + "extinguish a flame."),
    ELDRITCH_BLAST(MagicSchool.EVOCATION, 0, "1 action", "120 feet",
        "-", "V, S", "Instantaneous", "[max($level 1:1,5:2,11:3,17:4)] ranged spell "
        + "[plural(attack,attacks)]. 1d10 force damage."),
    FIRE_BOLT(MagicSchool.EVOCATION, 0, "1 action", "120 feet",
        "1 creature or object", "V, S", "Instantaneous", "Ranged spell attack. [max($level "
        + "1:1d10,5:2d10,11:3d10,17:4d10)] fire damage."),
    FRIENDS(MagicSchool.ENCHANTMENT, 0, "1 action", "Self", "1 non-hostile creature",
        "S, M ", "Concentration, up to 1 minute", "Advantage on Chr. checks at target."),
    GUIDANCE(MagicSchool.DIVINATION, 0, "1 action", "Touch", "1 willing creature",
        "V, S", "up to 1 minute", "Add d4 to one ability check."),
    LIGHT(MagicSchool.EVOCATION, 0, "1 action", "Touch", "1 object",
        "V, M ", "1 hour", "Sheds bright light in a 20-foot radius and dim light for an "
        + "additional 20 feet. Dex. save."),
    MAGE_HAND(MagicSchool.CONJURATION, 0, "1 action", "30 feet",
        "-", "V, S ", "1 minute", "Use action to use floating hand."),
    MENDING(MagicSchool.TRANSMUTATION, 0, "1 minute", "Touch",
        "1 broken object", "V, S, M ", "Instantaneous", "Mends object."),
    MESSAGE(MagicSchool.TRANSMUTATION, 0, "1 action", "120 feet",
        "1 creature", "V, S, M ", "1 round", "Target hears message"),
    MINOR_ILLUSION(MagicSchool.ILLUSION, 0, "1 action", "30 feet",
        "5-foot cube", "S, M ", "1 minute", "Create a sound or an image of an object. "
        + "Investigate save."),
    POISON_SPRAY(MagicSchool.CONJURATION, 0, "1 action", "10 feet",
        "1 creature", "V, S", "Instantaneous", "[max($level 1:1d12,5:2d12,11:3d12,17:4d12)] "
        + "poison damage. Con. save."),
    PRESTIDIGITATION(MagicSchool.TRANSMUTATION, 0, "1 action",
        "10 feet", "1 cubic foot", "V, S", "Up to 1 hour", "Create a harmless sensory effect."),
    PRODUCE_FLAME(MagicSchool.CONJURATION, 0, "1 action", "Self",
        "Hand", "V, S", "10 minutes", "Flame sheds bright light in a 10-foot radius and dim "
        + "light for an additional 10 feet.\nRanged spell attack [max($level "
        + "1:1d8,5:2d8,11:3d8,17:4d8)] fire damage."),
    RAY_OF_FROST(MagicSchool.EVOCATION, 0, "1 action", "60 feet",
        "1 creature", "V, S", "Instantaneous", "Ranged spell attack. [max($level "
        + "1:1d8,5:2d8,11:3d8,17:4d8)] damage. -10 Speed for 1 turn."),
    RESISTANCE(MagicSchool.ABJURATION, 0, "1 action", "Touch",
        "1 willing creature", "V, S, M ", "up to 1 minute", "Add d4 to one saving throw."),
    SACRED_FLAME(MagicSchool.EVOCATION, 0, "1 action", "60 feet",
        "1 creature", "V, S", "Instantaneous", "[max($level 1:1d8,5:2d8,11:3d8,17:4d8)] "
        + "radiant damage. Dex. save."),
    SHILLELAGH(MagicSchool.TRANSMUTATION, 0, "1 bonus action",
        "Touch", "club or quarterstaff", "V, S, M ", "1 minute", "Use [$spell_mod] instead of "
        + "Str. and 1d8 damage on attacks with weapon."),
    SHOCKING_GRASP(MagicSchool.EVOCATION, 0, "1 action", "Touch",
        "1 creature", "V, S", "Instantaneous", "Melee spell attack. Advantage if target "
        + "wearing metal armour. [max($level 1:1d8,5:2d8,11:3d8,17:4d8)] lightning damage and no "
        + "reactions until next turn."),
    SPARE_THE_DYING(MagicSchool.NECROMANCY, 0, "1 action", "Touch",
        "1 creature with 0 HP", "V, S", "Instantaneous", "Becomes stable."),
    THAUMATURGY(MagicSchool.TRANSMUTATION, 0, "1 action", "30 feet",
        "-", "V", "Up to 1 minute", "Voice booms, flames change, tremors, create sound, "
        + "door/window opens/closes or change eyes."),
    THORN_WHIP(MagicSchool.TRANSMUTATION, 0, "1 action", "30 feet",
        "1 creature", "V, S, M", "Instantaneous", "Melee spell attack +[$spell_mod] "
        + "[max($level 1:1,5:2,11:3,17:4)]d6 piercing damage and pull target 10 feet."),
    TRUE_STRIKE(MagicSchool.DIVINATION, 0, "1 action", "30 feet",
        "1 creature", "S", "up to 1 round", "Advantage on next attack against target."),
    VICIOUS_MOCKERY(MagicSchool.ENCHANTMENT, 0, "1 action", "60 feet",
        "1 creature", "V", "Instantaneous", "[max($level 1:1d4,5:2d4,11:3d4,17:4d4)] psychic "
        + "damage and disadvantage on next attack. Wis. save."),
    ALARM(MagicSchool.ABJURATION, 1, "1 minute", "30 feet", "20-foot cube",
        "V, S, M ", "8 hours", "Audible or mental alarm when Tiny or larger creature touches "
        + "or enters."),
    ANIMAL_FRIENDSHIP(MagicSchool.ENCHANTMENT, 1, "1 action", "30 feet",
        "1 beast", "V, S, M ", "24 hours", "If Int. less than 4, Wis. save or charmed.\n+1 "
        + "beast / level above 1."),
    ARMOUR_OF_AGATHYS(MagicSchool.ABJURATION, 1, "1 action", "Self",
        "-", "V, S, M ", "1 hour", "+5 temporary HP. Attacker takes 5 cold damage.\n+5 HP and "
        + "damage / extra level."),
    ARMS_OF_HADAR(MagicSchool.CONJURATION, 1, "1 action", "Self",
        "10-foot radius", "V, S ", "Instantaneous", "2d6 necrotic damage and no reactions for "
        + "turn.\nStr save for half damage.\n+1d6 damage / extra level"),
    BANE(MagicSchool.ENCHANTMENT, 1, "1 action", "30 feet", "Up to 3 creatures",
        "V, S, M ", "up to 1 minute", "-1d4 on each attack and saving throw. Chr. save.\n+1 "
        + "creature / level above 1st"),
    BLESS(MagicSchool.ENCHANTMENT, 1, "1 action", "30 feet", "Up to 3 creatures",
        "V, S, M ", "up to 1 minute", "+1d4 on each attack and saving throw.\n+1 creature / "
        + "level above 1st."),
    BURNING_HANDS(MagicSchool.EVOCATION, 1, "1 action", "Self",
        "15-foot cone", "V, S", "Instantaneous", "3d6 fire damage. Dex. save half. +1d6 / "
        + "level above 1st."),
    CHARM_PERSON(MagicSchool.ENCHANTMENT, 1, "1 action", "30 feet",
        "1 creature", "V, S", "1 hour", "Charmed. Wis. save.\n+1 creature / level above 1st."),
    CHROMATIC_ORB(MagicSchool.EVOCATION, 1, "1 action", "90 feet",
        "-", "V, S, M ", "Instantaneous", "Ranged spell attack [$spell_mod]. 3d8 damage "
        + "(choose type). +1d8 damage / extra level."),
    COLOR_SPRAY(MagicSchool.ILLUSION, 1, "1 action", "Self", "15-foot cone",
        "V, S, M ", "1 round", "Blinds 6d10 HP of creatures, lowest first.\n+2d10 HP / extra "
        + "level."),
    COLOUR_SPRAY(MagicSchool.ILLUSION, 1, "1 action", "Self", "15-foot cone",
        "V, S, M ", "1 round", "6d10 HP of creature blinded. +2d10 / extra level."),
    COMMAND(MagicSchool.ENCHANTMENT, 1, "1 action", "60 feet",
        "1 creature", "V", "1 round", "Follow one word command. Wis. save.\n+1 target / extra "
        + "level."),
    COMPELLED_DUEL(MagicSchool.ENCHANTMENT, 1, "1 bonus action",
        "30 feet", "1 creature", "V", "Concentration, up to 1 minute",
        "Target disadvantaged on attacks against others.\nWis. save to move away."),
    COMPREHEND_LANGUAGES(MagicSchool.DIVINATION, 1, "1 action",
        "Self", "-", "V, S, M ", "1 hour", "Understand any language."),
    CREATE_OR_DESTROY_WATER(MagicSchool.TRANSMUTATION, 1, "1 action",
        "30 feet", "up to 10 gallons", "V, S, M ", "Instantaneous",
        "Create or destroy up to 10 gallons of water.\n+10 gallons / extra level."),
    CURE_WOUNDS(MagicSchool.EVOCATION, 1, "1 action", "Touch",
        "1 creature", "V, S", "Instantaneous", "Regain 1d8+[$spell_mod]HP.\n+1d8 / extra "
        + "level."),
    DETECT_EVIL_AND_GOOD(MagicSchool.DIVINATION, 1, "1 action",
        "Self", "30 feet", "V, S", "up to 10 minutes", "Detect good or evil creature or "
        + "object."),
    DETECT_MAGIC(MagicSchool.DIVINATION, 1, "1 action", "Self",
        "-", "V, S", "up to 10 minutes", "Detect magical creature or object."),
    DETECT_POISON_AND_DISEASE(MagicSchool.DIVINATION, 1, "1 action",
        "Self", "30 feet", "V, S, M ", "up to 10 minutes", "Detect poison, poisonous "
        + "creatures and disease."),
    DISGUISE_SELF(MagicSchool.ILLUSION, 1, "1 action", "Self",
        "-", "V, S", "1 hour", "Change appearance. Invesigation check against DC[$spell_dc]."),
    DISSONANT_WHISPERS(MagicSchool.ENCHANTMENT, 1, "1 action",
        "60 feet", "1 creature", "V ", "Instantaneous", "3d6 psychic damage and use reaction "
        + "to move away. Wis. save for half damage only. +1d6 damage / extra level."),
    DIVINE_FAVOR(MagicSchool.EVOCATION, 1, "1 bonus action", "Self",
        "weapon", "V, S", "up to 1 minute", "+1d4 radiant damage on hit."),
    DIVINE_FAVOUR(MagicSchool.EVOCATION, 1, "1 bonus action", "Self",
        "1 weapon", "V, S", "Concentration, up to 1 minute", "Weapon does +1d4 radiant damage."),
    ENSNARING_STRIKE(MagicSchool.CONJURATION, 1, "1 bonus action",
        "Self", "-", "V", "Concentration, up to 1 minute", "Next hit Str. save or restrained. "
        + "1d6 piercing damage each turn. Str. check DC [$spell_dc] to escape. +1d6 / extra level."),
    ENTANGLE(MagicSchool.CONJURATION, 1, "1 action", "90 feet",
        "20-foot square", "V, S", "up to 1 minute", "Restrain creatures. Str. save."),
    EXPEDITIOUS_RETREAT(MagicSchool.TRANSMUTATION, 1, "1 bonus action",
        "Self", "-", "V, S", "up to 10 minutes", "Dash as a bonus action each turn."),
    FAERIE_FIRE(MagicSchool.EVOCATION, 1, "1 action", "60 feet",
        "20' cube", "V", "up to 1 minute", "Creatures give advantage on attack. Dex. save."),
    FALSE_LIFE(MagicSchool.NECROMANCY, 1, "1 action", "Self", "-",
        "V, S, M ", "1 hour", "1d4 + 4 temporary hit points.\n+5HP / level above 1st."),
    FEATHER_FALL(MagicSchool.TRANSMUTATION, 1, "1 reaction on falling",
        "60 feet", "Up to 5 creatures", "V, M ", "1 minute", "Take no damage from fall."),
    FIND_FAMILIAR(MagicSchool.CONJURATION, 1, "1 hour", "10 feet",
        "-", "V, S, M ", "Instantaneous", "Summon a familiar."),
    FLOATING_DISK(MagicSchool.CONJURATION, 1, "1 action", "30 feet",
        "3 feet x 1\"", "V, S, M ", "1 hour", "Creates disk. Holds 500lbs. Moves with caster."),
    FOG_CLOUD(MagicSchool.CONJURATION, 1, "1 action", "120 feet",
        "20-foot-radius sphere", "V, S", "up to 1 hour", "Area heavily obscured. +20 feet "
        + "radius / extra level."),
    GOODBERRY(MagicSchool.TRANSMUTATION, 1, "1 action", "Touch",
        "Up to 10 berries", "V, S, M ", "Instantaneous", "Use action to eat 1 berry gain 1HP "
        + "and sustain creature for 1 day."),
    GREASE(MagicSchool.CONJURATION, 1, "1 action", "60 feet", "10-foot square",
        "V, S, M ", "1 minute", "Fall prone. Dex. save."),
    GUIDING_BOLT(MagicSchool.EVOCATION, 1, "1 action", "120 feet",
        "1 creature", "V, S", "1 round", "Ranged spell attack. On hit 4d6 radiant damage. "
        + "Next attack has advantage.\n+1d6 damage / extra level."),
    HAIL_OF_THORNS(MagicSchool.CONJURATION, 1, "1 bonus action",
        "Self", "-", "V", "Concentration, up to 1 minute", "Next ranged hit, creatures within "
        + "5 feet of target take 1d10 piercing damage. Dex save. for half damage."),
    HEALING_WORD(MagicSchool.EVOCATION, 1, "1 bonus action", "60 feet",
        "1 creature", "V", "Instantaneous", "1d4 + [$spell_mod] HP. +1d4 / extra level."),
    HELLISH_REBUKE(MagicSchool.EVOCATION, 1, "1 reaction on taking damage",
        "60 feet", "Attacker", "V, S", "Instantaneous", "2d10 fire damage. Dex. save for "
        + "half.\n+1d10 / extra level."),
    HEROISM(MagicSchool.ENCHANTMENT, 1, "1 action", "Touch", "1 creature",
        "V, S", "up to 1 minute", "Immune to fear and +[$spell_mod] temporary HP each "
        + "turn.\n+ 1 creature / extra level."),
    HEX(MagicSchool.ENCHANTMENT, 1, "1 bonus action", "90 feet",
        "1 creature", "V, S, M ", "Concentration, up to 1 hour", "Extra 1d6 necrotic damage "
        + "from attacks by caster on target. Disadvantage on ability checks with chosen ability. "
        + "3rd level 8 hours, 5th level 24 hours."),
    HIDEOUS_LAUGHTER(MagicSchool.ENCHANTMENT, 1, "1 action", "30 feet",
        "1 creature", "V, S, M ", "up to 1 minute", "Fall prone. Int. of 4 or less immune. "
        + "Wis. save."),
    HUNTERS_MARK(MagicSchool.DIVINATION, 1, "1 bonus action", "90 feet",
        "1 creature", "V", "up to 1 hour", "+1d6 damage on hit with a weapon attack. "
        + "Advantage on Perception and Survival to find. \n3rd level: 8 hours, 5th level: 24 "
        + "hours."),
    IDENTIFY(MagicSchool.DIVINATION, 1, "1 minute", "Touch", "1 object",
        "V, S, M ", "Instantaneous", "Learn magical properties."),
    ILLUSORY_SCRIPT(MagicSchool.ILLUSION, 1, "1 minute", "Touch",
        "Parchment", "S, M ", "10 days", "Write hidden message."),
    INFLICT_WOUNDS(MagicSchool.NECROMANCY, 1, "1 action", "Touch",
        "1 creature", "V, S", "Instantaneous", "Melee spell attack. 3d10 necrotic "
        + "damage.\n+1d10 / extra level."),
    JUMP(MagicSchool.TRANSMUTATION, 1, "1 action", "Touch", "1 creature",
        "V, S, M ", "1 minute", "Jump distance tripled."),
    LONGSTRIDER(MagicSchool.TRANSMUTATION, 1, "1 action", "Touch",
        "1 creature", "V, S, M ", "1 hour", "You touch a creature. Speed increases by 10 "
        + "feet.\n+1 creature / extra level."),
    MAGE_ARMOR(MagicSchool.ABJURATION, 1, "1 action", "Touch",
        "1 unarmoured creature", "V, S, M ", "8 hours", "AC 13 + Dex. modifier."),
    MAGIC_MISSILE(MagicSchool.EVOCATION, 1, "1 action", "120 feet",
        "3 darts", "V, S", "Instantaneous", "1d4 + 1 force damage. +1 dart / extra level."),
    PROTECTION_FROM_EVIL_AND_GOOD(MagicSchool.ABJURATION, 1, "1 action",
        "Touch", "1 willing creature", "V, S, M ", "Concentration up to 10 minutes",
        "Protected against aberrations, celestials, elementals, fey, fiends, and undead. "
        + "Can't be charmed, frightened or possessed by these creatures. "
        + "Creatures have disadvantage on attacks. "),
    PURIFY_FOOD_AND_DRINK(MagicSchool.TRANSMUTATION, 1, "1 action",
        "10 feet", "5-foot-radius sphere", "V, S", "Instantaneous",
        "Food and drink rendered free of poison and disease."),
    RAY_OF_SICKNESS(MagicSchool.NECROMANCY, 1, "1 action", "60 feet",
        "1 creature", "V, S ", "Instantaneous", "Ranged spell attack +[$spell_mod] 2d8 poison "
        + "damage. Con. save or poisoned until next turn. +1d8 damage / extra level."),
    SANCTUARY(MagicSchool.ABJURATION, 1, "1 bonus action", "30 feet",
        "1 creature", "V, S, M ", "1 minute", "Creatures attacking target Wis. save or choose "
        + "new target."),
    SEARING_SMITE(MagicSchool.EVOCATION, 1, "1 bonus action", "Self",
        "1 weapon", "V", "Concentration, up to 1 minute", "On next hit +1d6 fire damage and "
        + "target ignites. 1d6 fire damage each turn. Con. save ends. +1d6 initial damage / extra "
        + "level."),
    SHIELD(MagicSchool.ABJURATION, 1, "1 reaction", "Self", "-",
        "V, S", "1 round", "+5 bonus to AC. No damage from magic missile."),
    SHIELD_OF_FAITH(MagicSchool.ABJURATION, 1, "1 bonus action",
        "60 feet", "1 creature", "V, S, M ", "up to 10 minutes", "+2 bonus to AC."),
    SILENT_IMAGE(MagicSchool.ILLUSION, 1, "1 action", "60 feet",
        "15-foot cube", "V, S, M ", "up to 10 minutes", "Create a visual image. Investigation "
        + "check DC[$spell_dc]."),
    SLEEP(MagicSchool.ENCHANTMENT, 1, "1 action", "90 feet", "20 feet",
        "V, S, M ", "1 minute", "5d8 HP of creatures fall asleeep.\n+2d8 HP / extra level."),
    SPEAK_WITH_ANIMALS(MagicSchool.DIVINATION, 1, "1 action", "Self",
        "-", "V, S", "10 minutes", "Communicate with beasts."),
    TASHAS_HIDEOUS_LAGHTER(MagicSchool.ENCHANTMENT, 1, "1 action",
        "30 feet", "1 creature", "V, S, M", "Concentration, up to 1 minute",
        "Target incapacitated. Wis. save. Save each turn and on damage to end."),
    TENSERS_FLOATING_DISK(MagicSchool.CONJURATION, 1, "1 action",
        "30 feet", "-", "1 hour", "V, S, M", "Create a floating disk. Holds 500lbs. Remains "
        + "within 20 feet of caster."),
    THUNDEROUS_SMITE(MagicSchool.EVOCATION, 1, "1 bonus action",
        "Self", "1 weapon", "V", "Concentration, up to 1 minute", "On next hit +2d6 thunder "
        + "damage. Str. save or be pushed 10 feet and knocked prone. "),
    THUNDERWAVE(MagicSchool.EVOCATION, 1, "1 action", "Self", "15-foot cube",
        "V, S", "Instantaneous", "2d8 thunder damage and pushed 10 feet. Con. save for half "
        + "and no push.\nCreates thunderous boom audible out to 300 feet.\n+1d8 damage / extra "
        + "level."),
    UNSEEN_SERVANT(MagicSchool.CONJURATION, 1, "1 action", "60 feet",
        "-", "V, S, M ", "1 hour", "Creates force AC 10, 1 HP, Str. 2. As bonus action perfom "
        + "simple tasks."),
    WITCH_BOLT(MagicSchool.EVOCATION, 1, "1 action", "30 feet",
        "1 creature", "V, S, M", "Concentration, up to 1 minute", "Ranged spell attack "
        + "+[$spell_mod] 1d12 lightning damage. Each turn use action for 1d12 lightning damage"),
    WRATHFUL_SMITE(MagicSchool.EVOCATION, 1, "1 bonus action",
        "Self", "1 weapon", "V", "Concentration, up to 1 minute", "On next hit +1d6 psychic "
        + "damage. Wis. save DC [$spell_dc] or be frightened of caster. "),
    ACID_ARROW(MagicSchool.EVOCATION, 2, "1 action", "90 feet",
        "1 target", "V, S, M ", "Instantaneous", "Hit: 4d4 acid damage, 2d2 end of next "
        + "turn.\nMiss:half damage, none following.\n+1d4 damage / level above 2nd"),
    AID(MagicSchool.ABJURATION, 2, "1 action", "30 feet", "Up to 3 allies",
        "V, S, M ", "8 hours", "+5 temporary HP.\n+5HP / level above 2"),
    ALTER_SELF(MagicSchool.TRANSMUTATION, 2, "1 action", "Self",
        "-", "V, S", "up to 1 hour", "Assume a different form."),
    ANIMAL_MESSENGER(MagicSchool.ENCHANTMENT, 2, "1 action", "30 feet",
        "1 Tiny beast", "V, S, M ", "24 hours", "Animal delivers a message.\n+48 hours / "
        + "level above 2nd."),
    ARCANE_LOCK(MagicSchool.ABJURATION, 2, "1 action", "Touch",
        "1 entryway", "V, S, M ", "Until dispelled", "Magic lock. +10 DC to break or pick."),
    ARCANISTS_MAGIC_AURA(MagicSchool.ILLUSION, 2, "1 action", "Touch",
        "1 creature or object", "V, S, M ", "24 hours", "Cause false magical aura or mask "
        + "magical effect."),
    AUGURY(MagicSchool.DIVINATION, 2, "1 minute", "Self", "-",
        "V, S, M ", "Instantaneous", "Divine outcomes of plan: Weal, Woe, Weal and Woe or "
        + "Nothing"),
    BARKSKIN(MagicSchool.TRANSMUTATION, 2, "1 action", "Touch",
        "1 creature", "V, S, M ", "up to 1 hour", "AC16"),
    BEAST_SENSE(MagicSchool.DIVINATION, 2, "1 action", "touch",
        "1 beast", "S ", "up to 1 hour", "see and hear through beast's eyes and ears."),
    BLINDNESS_DEAFNESS(MagicSchool.NECROMANCY, 2, "1 action", "30 feet",
        "1 creature", "V", "1 minute", "Target blinded or deafened. Con. save each turn.\n+1 "
        + "creature / level above 2nd."),
    BLUR(MagicSchool.ILLUSION, 2, "1 action", "Self", "-", "V",
        "up to 1 minute", "Disadvantage attack rolls."),
    BRANDING_SMITE(MagicSchool.EVOCATION, 2, "1 bonus action",
        "Self", "1 weapon", "V ", "Concentration, up to 1 minute",
        "+2d6 radiant damage from next attack. +1d6 damage / extra level"),
    CALM_EMOTIONS(MagicSchool.ENCHANTMENT, 2, "1 action", "60 feet",
        "20-foot-radius spher", "V, S", "up to 1 minute", "Prevent charm or fear or make "
        + "indifferent. Chr. save."),
    CLOUD_OF_DAGGERS(MagicSchool.CONJURATION, 2, "1 action", "60 feet",
        "5-foot cube", "V, S, M", "Concentration, up to 1 minute",
        "4d4 slashing damage. +2d4 / extra level."),
    CONTINUAL_FLAME(MagicSchool.EVOCATION, 2, "1 action", "Touch",
        "1 object", "V, S, M ", "Until dispelled", "Flame created on object"),
    CORDON_OF_ARROWS(MagicSchool.TRANSMUTATION, 2, "1 action",
        "5 feet", "30 feet", "V, S, M ", "8 hours", "4 arrows planted in ground fire at "
        + "enemies entering area. 1d6 piercing damage. Dex. save. +1 arrow / extra level."),
    CROWN_OF_MADNESS(MagicSchool.ENCHANTMENT, 2, "1 action", "120 feet",
        "1 humanoid", "V, S ", "Concentration, up to 1 minute", "Charmed to attack chosen "
        + "targets. Wis. save each turn to end."),
    DARKNESS(MagicSchool.EVOCATION, 2, "1 action", "60 feet", "15-foot radius sphere",
        "V, M ", "up to 10 minutes", "Complete darkness in area."),
    DARKVISION(MagicSchool.TRANSMUTATION, 2, "1 action", "Touch",
        "1 creature", "V, S, M ", "8 hours", "Target has darkvision to a range of 60 feet."),
    DETECT_THOUGHTS(MagicSchool.DIVINATION, 2, "1 action", "Self",
        "1 creature within 30 feet", "V, S, M ", "up to 1 minute",
        "As action, probe mind. Wis. save."),
    ENHANCE_ABILITY(MagicSchool.TRANSMUTATION, 2, "1 action", "Touch",
        "1 creature", "V, S, M ", "up to 1 hour.", "Advantage on Con. checks and 2d6 "
        + "temporary HP, or\nAdvantage on Str. checks and double carrying capacity, or\nAdvantage "
        + "on Dex. checks and no falling damage from 20 feet, or\nAdvantage on Chr. Int. or Wis. "
        + "checks.\n+1 creature / extra level."),
    ENLARGE_REDUCE(MagicSchool.TRANSMUTATION, 2, "1 action", "30 feet",
        "1 creature or object", "V, S, M ", "up to 1 minute", "Enlarge: advantage on Str. "
        + "checks and +1d4 damage, or\nReduce: disadvantage on Str. checks and -1d4 damage."),
    ENTHRALL(MagicSchool.ENCHANTMENT, 2, "1 action", "60 feet",
        "All creatures", "V, S", "1 minute", "Disadvantage on Perception checks. Wis. save."),
    FIND_STEED(MagicSchool.CONJURATION, 2, "10 minutes", "30 feet",
        "Unoccupied space", "V, S", "Instantaneous", "Summon steed."),
    FIND_TRAPS(MagicSchool.DIVINATION, 2, "1 action", "120 feet",
        "-", "V, S", "Instantaneous", "Reveals if a trap is present."),
    FLAME_BLADE(MagicSchool.EVOCATION, 2, "1 bonus action", "Self",
        "-", "V, S, M ", "up to 10 minutes", "Create flaming blade. 3d6 fire damage on hit. "
        + "+1d6 / extra 2 levels."),
    FLAMING_SPHERE(MagicSchool.CONJURATION, 2, "1 action", "60 feet",
        "5-foot-diameter sphere", "V, S, M ", "up to 1 minute", "Creature within 5 feet of "
        + "sphere takes 2d6 fire damage. Dex. save half.\nAs a bonus action, move sphere 30 "
        + "feet.\n+1d6 damage / extra level."),
    GENTLE_REPOSE(MagicSchool.NECROMANCY, 2, "1 action", "Touch",
        "Corpse", "V, S, M ", "10 days", "Protected from decay and becoming undead."),
    GUST_OF_WIND(MagicSchool.EVOCATION, 2, "1 action", "Self",
        "60-foot line", "V, S, M ", "up to 1 minute", "Pushed 15 feet away. Str. save. "
        + "Doubles movement cost towards caster."),
    HEAT_METAL(MagicSchool.TRANSMUTATION, 2, "1 action", "60 feet",
        "1 metal object", "V, S, M ", "up to 1 minute", "Creature contacting object takes 2d8 "
        + "fire damage. Con. save or drop object.\n+1d8 / extra level."),
    HOLD_PERSON(MagicSchool.ENCHANTMENT, 2, "1 action", "60 feet",
        "1 humanoid", "V, S, M ", "up to 1 minute", "Target paralysed. Wis. save.\n+1 "
        + "humanoid / extra level."),
    INVISIBILITY(MagicSchool.ILLUSION, 2, "1 action", "Touch",
        "1 creature", "V, S, M ", "up to 1 hour", "Target becomes invisible.\n+1 creature / "
        + "extra level."),
    KNOCK(MagicSchool.TRANSMUTATION, 2, "1 action", "60 feet",
        "1 object", "V", "Instantaneous", "Unlock object. Makes loud sound."),
    LESSER_RESTORATION(MagicSchool.ABJURATION, 2, "1 action", "Touch",
        "1 creature", "V, S", "Instantaneous", "End one condition."),
    LEVITATE(MagicSchool.TRANSMUTATION, 2, "1 action", "60 feet",
        "1 creature or object up to 500lbs", "V, S, M ", "up to 10 minutes",
        "Rises vertically up to 20 feet. Con. save."),
    LOCATE_ANIMALS_OR_PLANTS(MagicSchool.DIVINATION, 2, "1 action",
        "Self", "1 kind of beast or plant", "V, S, M ", "Instantaneous",
        "Sense direction and distance to closest within 5 miles, if any."),
    LOCATE_OBJECT(MagicSchool.DIVINATION, 2, "1 action", "Self",
        "1 object", "V, S, M ", "up to 10 minutes", "Sense direction to object if within "
        + "1,000 feet"),
    MAGIC_MOUTH(MagicSchool.ILLUSION, 2, "1 minute", "30 feet",
        "1 object", "V, S, M ", "Until dispelled", "Message on trigger."),
    MAGIC_WEAPON(MagicSchool.TRANSMUTATION, 2, "1 bonus action",
        "Touch", "1 nonmagical weapon", "V, S", "up to 1 hour", "+1 bonus to attack rolls and "
        + "damage. +2 at 4th level. +3 at 6th level."),
    MELFS_ACID_ARROW(MagicSchool.EVOCATION, 2, "1 action", "90 feet",
        "1 creature", "V, S, M", "Instantaneous", "Ranged spell attack 4d4 acid damage +2d4 "
        + "end of next turn. On miss, 2d4 acid damage. +1d4 damage / extra level."),
    MIRROR_IMAGE(MagicSchool.ILLUSION, 2, "1 action", "Self", "3 duplicates",
        "V, S", "1 minute", "On being targeted, roll d20 to hit image: 6+ for 3, 8+ for 2, "
        + "11+ for 1\nImages have AC[10+$dex_mod]."),
    MISTY_STEP(MagicSchool.CONJURATION, 2, "1 bonus action", "Self",
        "-", "V", "Instantaneous", "Teleport up to 30 feet."),
    MOONBEAM(MagicSchool.EVOCATION, 2, "1 action", "120 feet",
        "5-foot-radius, 40-foot-high cylinder", "V, S, M ", "up to 1 minute",
        "Creatures entering 2d10 radiant damage. Con. save for half.\nUse an action to move "
        + "beam 60 feet.\n+1d10 damage / extra level."),
    PASS_WITHOUT_TRACE(MagicSchool.ABJURATION, 2, "1 action", "Self",
        "Allies within 30'", "V, S, M ", "up to 1 hour", "+10 bonus to Stealth checks. Cannot "
        + "be tracked."),
    PHANTASMAL_FORCE(MagicSchool.ILLUSION, 2, "1 action", "60 feet",
        "1 creature", "V, S, M ", "Concentration, up to 1 minute",
        "Int. save or create illusion for target. 1d6 psychic damage each turn."),
    PRAYER_OF_HEALING(MagicSchool.EVOCATION, 2, "10 minutes", "30 feet",
        "Up to 6 creatures", "V", "Instantaneous", "Regain 2d8+[$spell_mod]HP.\n+1d8 HP / "
        + "extra level."),
    PROTECTION_FROM_POISON(MagicSchool.ABJURATION, 2, "1 action",
        "Touch", "1 creature", "V, S", "1 hour", "Neutralize poison. Advantage on saves vs "
        + "positon. Resistance to poison damage."),
    RAY_OF_ENFEEBLEMENT(MagicSchool.NECROMANCY, 2, "1 action",
        "60 feet", "1 creature", "V, S", "up to 1 minute", "Ranged spell attack. Target deals "
        + "half damage with attacks.\nCon. save each turn to end."),
    ROPE_TRICK(MagicSchool.TRANSMUTATION, 2, "1 action", "Touch",
        "1 60-foot rope", "V, S, M ", "1 hour", "Rope ends in extradimensional space that can "
        + "hold up to 8 creatures."),
    SCORCHING_RAY(MagicSchool.EVOCATION, 2, "1 action", "120 feet",
        "3 rays of fire", "V, S", "Instantaneous", "Ranged spell attack for each ray. 2d6 "
        + "fire damage.\n+1 ray / extra level."),
    SEE_INVISIBILITY(MagicSchool.DIVINATION, 2, "1 action", "Self",
        "-", "V, S, M ", "1 hour", "See invisible creatures and objects."),
    SHATTER(MagicSchool.EVOCATION, 2, "1 action", "60 feet", "10-foot-radius sphere",
        "V, S, M ", "Instantaneous", "3d8 thunder damage. Con. save for half.\n+1d8 damage / "
        + "extra level."),
    SILENCE(MagicSchool.ILLUSION, 2, "1 action", "120 feet", "20-foot-radius sphere",
        "V, S", "up to 10 minutes", "No sound can be created within or pass through."),
    SPIDER_CLIMB(MagicSchool.TRANSMUTATION, 2, "1 action", "Touch",
        "1 willing creature", "V, S, M ", "up to 1 hour", "Move on vertical surfaces and "
        + "ceilings.\nClimbing speed equal to walking speed."),
    SPIKE_GROWTH(MagicSchool.TRANSMUTATION, 2, "1 action", "150 feet",
        "20-foot radius", "V, S, M ", "up to 10 minutes", "Difficult terrain. 2d4 piercing "
        + "damage for each 5 feet of travel.\nPerception check DC[$spell_dc] to recognize."),
    SPIRITUAL_WEAPON(MagicSchool.EVOCATION, 2, "1 bonus action",
        "60 feet", "-", "V, S", "1 minute", "Create weapon. Melee spell attack. "
        + "1d8+[$spell_mod] force damage.\nAs bonus action move weapon up to 20 feet.\n+1d8 "
        + "damage / 2 extra levels."),
    SUGGESTION(MagicSchool.ENCHANTMENT, 2, "1 action", "30 feet",
        "1 creature", "V, M ", "up to 8 hours", "Suggest action. Wis. save."),
    WARDING_BOND(MagicSchool.ABJURATION, 2, "1 action", "Touch",
        "1 willing creature", "V, S, M ", "1 hour", "+1 bonus to AC and saves, resistance to "
        + "damage.\nCaster takes same damage."),
    WEB(MagicSchool.CONJURATION, 2, "1 action", "60 feet", "20-foot cube",
        "V, S, M ", "up to 1 hour", "Restrain creatures. Dex. save. Str. check DC[$spell_dc] "
        + "to break free."),
    ZONE_OF_TRUTH(MagicSchool.ENCHANTMENT, 2, "1 action", "60 feet",
        "15-foot-radius sphere", "V, S", "10 minutes", "Creatures in area cannot lie. Chr. "
        + "save."),
    ANIMATE_DEAD(MagicSchool.NECROMANCY, 3, "1 minute", "10 feet",
        "bones or corpose", "V, S, M ", "Instantaneous", "Creates and controls a skeleton or "
        + "zombie for 24 hours.\n+2 undead / extra level."),
    AURA_OF_VITALITY(MagicSchool.EVOCATION, 3, "1 action", "Self",
        "30-foot radius", "V", "Concentration, up to 1 minute", "Use bonus action to cure 2d6 "
        + "HP."),
    BEACON_OF_HOPE(MagicSchool.ABJURATION, 3, "1 action", "30 feet",
        "Any creatures", "V, S", "up to 1 minute", "Advantage on Wis and death saves and "
        + "regains max HP from healing."),
    BESTOW_CURSE(MagicSchool.NECROMANCY, 3, "1 action", "Touch",
        "1 creature", "V, S", "up to 1 minute", "Cure creature. Wis. save. Disadvantage on "
        + "checks and saves with chosen score. Disadvantage on attacks on caster. Wis. save each "
        + "turn or do nothing. Caster's attacks cause +1d8 necrotic damage to target.4th: up to "
        + "10 minutes; 5th: 8 hours, 7th: 24 hours, 9th: until dispelled."),
    BLINDING_SMITE(MagicSchool.EVOCATION, 3, "1 bonus action",
        "Self", "-", "V", "Concentraton, up to 1 minute", "Next hit, +3d8 damage and Con. "
        + "save or blinded."),
    BLINK(MagicSchool.TRANSMUTATION, 3, "1 action", "Self", "-",
        "V, S", "1 minute", "Each turn, d20 roll 11+ disappear for 1 turn and return on "
        + "following turn to chosen space within 10'."),
    CALL_LIGHTNING(MagicSchool.CONJURATION, 3, "1 action", "120 feet",
        "10-foot tall 60-foot radius cylinder", "V, S", "up to 10 minutes",
        "Bolt of lightning. Each creatures within 5 feet takes 3d10 lightinging damage. Dex. "
        + "save for half. Call lightning as action each turn. +1d10 damage / extra level."),
    CLAIRVOYANCE(MagicSchool.DIVINATION, 3, "10 minutes", "1 mile",
        "1 location", "V, S, M ", "up to 10 minutes", "Create invisible sensor that can "
        + "either see or hear."),
    CONJURE_ANIMALS(MagicSchool.CONJURATION, 3, "1 action", "60 feet",
        "Unoccupied space", "V, S", "up to 1 hour", "Summon fey beasts: 1 CR2 or 2 CR1 or 4 "
        + "CR1/2. Increase number at levels 5th: x2, 7th: x3, 9th: x4."),
    CONJURE_BARRAGE(MagicSchool.CONJURATION, 3, "1 action", "Self",
        "60-foot cone", "V, S, M ", "Instantaneous", "3d8 damage, Dex. save for half."),
    COUNTERSPELL(MagicSchool.ABJURATION, 3, "1 reaction", "60 feet",
        "1 creature casting spell", "S", "Instantaneous", "Spells level 1-3 fail. Level 4+ "
        + "Ability check DC 10+spell level. Level of check increases with level of cast."),
    CREATE_FOOD_AND_WATER(MagicSchool.CONJURATION, 3, "1 action",
        "30 feet", "Unoccupied space", "V, S", "Instantaneous", "Create provisions for 24 "
        + "hours."),
    CRUSADERS_MANTLE(MagicSchool.EVOCATION, 3, "1 action", "Self",
        "30-foot radius", "V", "Concentration, up to 1 minute", "Allies deal +1d4 radiant "
        + "damage with weapon attacks."),
    DAYLIGHT(MagicSchool.EVOCATION, 3, "1 action", "60 feet", "60-foot-radius sphere",
        "V, S", "1 hour", "Create bright light."),
    DISPEL_MAGIC(MagicSchool.ABJURATION, 3, "1 action", "120 feet",
        "1 creature, object or effect", "V, S", "Instantaneous", "Spells to slot level end. "
        + "Otherwise ability check DC 10 + spell level."),
    ELEMENTAL_WEAPON(MagicSchool.TRANSMUTATION, 3, "1 action",
        "Touch", "1 weapon", "V, S ", "Concentration, up to 1 hour",
        "+1 attack +1d4 damage (choose type). 5th level +2 attack +2d4 damage. 7th level +3 "
        + "attack +3d4 damage."),
    FEAR(MagicSchool.ILLUSION, 3, "1 action", "Self", "30-foot cone",
        "V, S, M ", "up to 1 minute", "Wis. save or drop item and become frightened. Must "
        + "take the Dash action away from caster."),
    FEIGN_DEATH(MagicSchool.NECROMANCY, 3, "1 action", "Touch",
        "1 willing creature", "V, S, M", "1 hour", "Target appears dead."),
    FIREBALL(MagicSchool.EVOCATION, 3, "1 action", "150 feet",
        "20-foot-radius sphere", "V, S, M ", "Instantaneous", "8d6 fire damage. Dex save for "
        + "half. +1d6 / extra level."),
    FLY(MagicSchool.TRANSMUTATION, 3, "1 action", "Touch", "1 willing creature",
        "V, S, M ", "up to 10 minutes", "Flying speed of 60 feet for the duration. +1 "
        + "creature / extra level."),
    GASEOUS_FORM(MagicSchool.TRANSMUTATION, 3, "1 action", "Touch",
        "1 willing creature", "V, S, M ", "up to 1 hour", "YTransform target into misty "
        + "cloud. Fly speed 10."),
    GLYPH_OF_WARDING(MagicSchool.ABJURATION, 3, "1 hour", "Touch",
        "surface or object", "V, S, M ", "Until dispelled or triggered",
        "Hidden glyph. Investigation check to find.On trigger: explode in 20-foot-radius "
        + "sphere 5d8 (+1d8 / extra level) damage Dex. save for half, or prepare spell of same "
        + "level cast on triggerer. "),
    HASTE(MagicSchool.TRANSMUTATION, 3, "1 action", "30 feet",
        "1 willing creature", "V, S, M ", "up to 1 minute", "Double speed, +2 AC, advantage "
        + "on Dex. saves, +1 action. 1 turn of inactivity when spell ends."),
    HUNGER_OF_HADAR(MagicSchool.CONJURATION, 3, "1 action", "150 feet",
        "20-foot radius", "V, S, M", "Concentration, up to 1 minute",
        "2d6 cold damage at start of turn. Dex. save or 2d6 acid damage at end of turn."),
    HYPNOTIC_PATTERN(MagicSchool.ILLUSION, 3, "1 action", "120 feet",
        "30-foot cube", "S, M ", "up to 1 minute", "Wis. save or charmed. Incapacitated and "
        + "speed 0."),
    LIGHTNING_ARROW(MagicSchool.TRANSMUTATION, 3, "1 bonus action",
        "Self", "-", "V, S ", "Concentration, up to 1 minute", "Next ranged attack 4d8 "
        + "lightning damage on hit, half on miss.Creatures within 10 feet 2d8 lightning damage. "
        + "Dex. save for half. +1d8 damage / extra level"),
    LIGHTNING_BOLT(MagicSchool.EVOCATION, 3, "1 action", "Self",
        "100-foot line", "V, S, M ", "Instantaneous", "8d6 lightning damage. Dex. save for "
        + "half. +1d6 damage / extra level."),
    MAGIC_CIRCLE(MagicSchool.ABJURATION, 3, "1 minute", "10 feet",
        "10-foot-radius, 20-foot-tall cylinder", "V, S, M ", "1 hour",
        "Specified type of creature cannot enter cylinder. +1 hour / extra level."),
    MAJOR_IMAGE(MagicSchool.ILLUSION, 3, "1 action", "120 feet",
        "20-foot cube", "V, S, M ", "up to 10 minutes", "Create an illusion. Investigation "
        + "check DC [$spell_dc] to discern illusion. 6th level: lasts until dispelled."),
    MASS_HEALING_WORD(MagicSchool.EVOCATION, 3, "1 bonus action",
        "60 feet", "up to 6 creatures", "V", "Instantaneous", "Regain 1d4+[$spell_mod] HP. "
        + "+1d4 / extra level."),
    MELD_INTO_STONE(MagicSchool.TRANSMUTATION, 3, "1 action", "Touch",
        "Stone object or surface", "V, S", "8 hours", "Meld body and equipment. Disadvantage "
        + "on Perception checks."),
    NONDETECTION(MagicSchool.ABJURATION, 3, "1 action", "Touch",
        "willing creature, place or object", "V, S, M ", "8 hours",
        "Hide target from divination magic."),
    PHANTOM_STEED(MagicSchool.ILLUSION, 3, "1 minute", "30 feet",
        "Unoccupied space", "V, S", "1 hour", "Summon horselike creature to ride. Speed 100."),
    PLANT_GROWTH(MagicSchool.TRANSMUTATION, 3, "1 action or 8 hours",
        "150 feet", "100-foot radius", "V, S", "Instantaneous", "1 action: creatures spend 4 "
        + "feet of movement. 8 hours: enrich plants for 1 year."),
    PROTECTION_FROM_ENERGY(MagicSchool.ABJURATION, 3, "1 action",
        "Touch", "1 willing creature", "V, S", "up to 1 hour", "Resistance to acid, cold, "
        + "fire, lightning, or thunder."),
    REMOVE_CURSE(MagicSchool.ABJURATION, 3, "1 action", "Touch",
        "1 creature or object", "V, S", "Instantaneous", "All curses on creature end. Breaks "
        + "attunement to cursed object."),
    REVIVIFY(MagicSchool.NECROMANCY, 3, "1 action", "Touch", "1 creature dead less than 1 "
        + "minute", "V, S, M ", "Instantaneous", "Creature returns to life with 1 HP."),
    SENDING(MagicSchool.EVOCATION, 3, "1 action", "Unlimited",
        "1 creature", "V, S, M ", "1 round", "Send a short message to target and receive "
        + "reply."),
    SLEET_STORM(MagicSchool.CONJURATION, 3, "1 action", "150 feet",
        "20-foot-tall 40-foot radius cylinder", "V, S, M ", "up to 1 minute",
        "Freezing rain and sleet fall in area. Heavily obscured. Dex. save or fall prone. "
        + "Con. save DC [$spell_dc] or lose concentration. "),
    SLOW(MagicSchool.TRANSMUTATION, 3, "1 action", "120 feet",
        "up to 6 creature in 40-foot cube", "V, S, M ", "up to 1 minute",
        "Wis. save each turn. Speed halved -2 AC and Dex. save and no reactions. On spell "
        + "cast on 11+ on d20 spell effect next turn."),
    SPEAK_WITH_DEAD(MagicSchool.NECROMANCY, 3, "1 action", "10 feet",
        "1 corpse", "V, S, M ", "10 minutes", "Ask corpse 5 questions."),
    SPEAK_WITH_PLANTS(MagicSchool.TRANSMUTATION, 3, "1 action",
        "Self", "30-foot radius", "V, S", "10 minutes", "Question and command plants. "),
    SPIRIT_GUARDIANS(MagicSchool.CONJURATION, 3, "1 action", "Self",
        "15-foot radius", "V, S, M ", "up to 10 minutes", "Speed halved, 3d8 radiant/necrotic "
        + "damage. Wis. save for half. +1d8 / extra level."),
    STINKING_CLOUD(MagicSchool.CONJURATION, 3, "1 action", "90 feet",
        "20-foot-radius sphere", "V, S, M ", "up to 1 minute", "Incapacitated. Con. save."),
    TINY_HUT(MagicSchool.EVOCATION, 3, "1 minute", "Self", "10-foot hemisphere",
        "V, S, M ", "8 hours", "Dome of force that can contain up to 9 creatures."),
    TONGUES(MagicSchool.DIVINATION, 3, "1 action", "Touch", "1 creature",
        "V, M ", "1 hour", "Understand and speak any spoken language."),
    VAMPIRIC_TOUCH(MagicSchool.NECROMANCY, 3, "1 action", "Self",
        "1 creature", "V, S", "up to 1 minute", "Melee spell attack 3d6 necrotic damage and "
        + "regain half as HP. +1d6 / extra level."),
    WATER_BREATHING(MagicSchool.TRANSMUTATION, 3, "1 action", "30 feet",
        "10 willing creatures", "V, S, M ", "24 hours", "Breathe underwater."),
    WATER_WALK(MagicSchool.TRANSMUTATION, 3, "1 action", "30 feet",
        "10 willing creatures", "V, S, M ", "1 hour", "Move across any liquid."),
    WIND_WALL(MagicSchool.EVOCATION, 3, "1 action", "120 feet",
        "50-feet long, 15-feet high, 1-foot thick", "V, S, M ", "up to 1 minute",
        "3d8 bludgeoning damage. Str. save for half."),
    AURA_OF_LIFE(MagicSchool.ABJURATION, 4, "1 action", "Self",
        "30-foot radius", "V", "Concentration, up to 10 minutes", "Allies resistant to "
        + "necrotic damage. Allies with 0 HP at start of turn regain 1 HP."),
    AURA_OF_PURITY(MagicSchool.ABJURATION, 4, "1 action", "Self",
        "30-foot radius", "V", "Concentration, up to 10 minutes", "Allies cannot be diseased. "
        + "Resistant to poison. Advantage on save for condition."),
    EVARDS_BLACK_TENTACLES(MagicSchool.CONJURATION, 4, "1 action",
        "90 feet", "20-foot square", "V, S, M", "Concentration, up to 1 minute",
        "Dex. save DC [$spell_dc] or be restrained. 3d6 bludgeoning damage each turn. Str. or "
        + "Dex. check to escape."),
    GRASPING_VINE(MagicSchool.CONJURATION, 4, "1 bonus action",
        "30 feet", "unoccupied space", "V, S", "Concentration, up to 1 minute",
        "Dex. save or be pulled 20 feet towards vine."),
    LEOMUNDS_SECRET_CHEST(MagicSchool.CONJURATION, 4, "1 action",
        "Touch", "1 chest", "V, S, M ", "Instantaneous", "Hide chest on ethereal plane."),
    MORDENKAINENS_FAITHFUL_HOUND(MagicSchool.CONJURATION, 4, "1 action",
        "30 feet", "unoccupied space", "V, S, M ", "8 hours", "Conjures watchdog. Barks if "
        + "hostile creature approaches 30 feet. Bite +[$spell_mod+$prof] 4d8 piercing damage."),
    MORDENKAINENS_PRIVATE_SANCTUM(MagicSchool.ABJURATION, 4, "10 minutes",
        "120 feet", "up to 100 feet cube", "V, S, M", "24 hours", "Provides area protected "
        + "from observation. +100 feet area / extra level."),
    OTILUKES_RESILIENT_SPHERE(MagicSchool.EVOCATION, 4, "1 action",
        "30 feet", "1 creature or object", "V, S, M", "Concentration, up to 1 minute",
        "Target enclosed in inpenetrable sphere. Dex. save."),
    STAGGERING_SMITE(MagicSchool.EVOCATION, 4, "1 bonus action",
        "Self", "-", "V", "Concentration, up to 1 minute", "Next melee hit +4d6 psychic "
        + "damage. Wis. save or disadvantage on attack and abilities and no reactions for 1 turn."),
    ARCANE_HAND(MagicSchool.EVOCATION, 5, "1 action", "120 feet",
        "-", "V, S, M ", "up to 1 minute", "Create a hand of force. AC 20 HP [$hp] Str. 26 "
        + "Dex. 10.\nBonus action move hand up to 60 feet and attack using contested Str. Attack "
        + "for 4d8 damage, push target [5+5*$spell_mod] feet, grapple target and then crush for "
        + "2d6+[$spell_mod] or provide half cover and prevent movement towards caster.\nAttack "
        + "+2d8 and crush +2d6 / extra level."),
    BANISHING_SMITE(MagicSchool.ABJURATION, 5, "1 bonus action",
        "Self", "-", "V", "Concentration, up to 1 minute", "Next hit with weapon: +5d10 force "
        + "damage. If 50 HP or less, banish."),
    CIRCLE_OF_POWER(MagicSchool.ABJURATION, 5, "1 action", "Self",
        "30-foot radius", "V", "Concentration, up to 10 minutes", "Allies in area have "
        + "advantage on Save vs. spells. Half damage becomes no damage. "),
    CONE_OF_COLD(MagicSchool.EVOCATION, 5, "1 action", "Self",
        "60-foot cone", "V, S, M ", "Instantaneous", "8d8 cold damage. Con. save for half."),
    CONJURE_VOLLEY(MagicSchool.CONJURATION, 5, "1 action", "150 feet",
        "40-foot radius, 20-foot high cylinder", "V, S, M ", "Instantaneous",
        "8d8 damage. Dex. save for half."),
    DESTRUCTIVE_WAVE(MagicSchool.EVOCATION, 5, "1 action", "Self",
        "30-foot radius", "V", "Instantaneous", "5d6 thunder damage +5d6 radiant or necrotic "
        + "damage and knocked prone. Con. save for half."),
    RARYS_TELEPATHIC_BOND(MagicSchool.DIVINATION, 5, "1 action",
        "30 feet", "8 willing creatures", "V, S, M ", "1 hour", "Communicate telepathically "
        + "between targets."),
    SWIFT_QUIVER(MagicSchool.TRANSMUTATION, 5, "1 bonus action",
        "Touch", "quiver", "V, S, M ", "Concentration, up to 1 minute",
        "As bonus action make two attacks with weapon using ammunition from quiver."),
    ARCANE_GATE(MagicSchool.CONJURATION, 6, "1 action", "500 feet",
        "2 points", "V, S ", "Concentration, up to 10 minutes", "Create teleportation "
        + "portals. As bonus action rotate portals."),
    BLADE_BARRIER(MagicSchool.EVOCATION, 6, "1 action", "90 feet",
        "20' high x5' thick wall\n100' straight or 60' diameter ringed",
        "V, S", "up to 10 minutes", "Three-quarters cover. Difficult terrain. 6d10 slashing "
        + "damage on entering. Dex. save for half"),
    DRAWMIJS_INSTANT_SUMMONS(MagicSchool.CONJURATION, 6, "1 minute",
        "Touch", "1 object up to 10lb", "V, S, M\n(1000GP)", "Until dispelled",
        "Learn who and where holder is."),
    PLANAR_ALLY(MagicSchool.CONJURATION, 6, "10 minutes", "60 feet",
        "Otherworldly entity", "V, S", "Instantaneous", "Summon creature to request service."),
    ARCANE_SWORD(MagicSchool.EVOCATION, 7, "1 action", "60 feet",
        "", "V, S, M ", "up to 1 minute", "Melee spell attack +[$spell_mod]. 3d10 force "
        + "damage. Use bonus action to move up to 20 feet and attack."),
    MORDENKAINENS_SWORD(MagicSchool.EVOCATION, 7, "1 action", "60 feet",
        "-", "V, S, M\n250GP", "Concentration, up to 1 minute.", "Melee spell attack 3d10 "
        + "force damage. As bonus action move 20 feet and attack."),
    PLANE_SHIFT(MagicSchool.CONJURATION, 7, "1 action", "Touch",
        "Up to 8 allies or 1 enemy", "V, S, M ", "Instantaneous", "Targets transported to "
        + "different plane."),
    TELEPORT(MagicSchool.CONJURATION, 7, "1 action", "10 feet",
        "Self and up to 8 creatures or 1 object", "V", "Instantaneous",
        "Transport targets to chosen destination. Special chance of miss."),
    ANIMAL_SHAPES(MagicSchool.TRANSMUTATION, 8, "1 action", "30 feet",
        "any number of willing creatures", "V, S", "up to 24 hours",
        "Transform into beasts with CR of 4 or lower."),
    TELEPATHY(MagicSchool.EVOCATION, 8, "1 action", "Unlimited",
        "1 willing creature", "V, S, M ", "24 hours", "Create telepathic link."),
    TSUNAMI(MagicSchool.CONJURATION, 8, "1 minute", "Sight", "300-feet long, 300-feet "
        + "high, 50-feet thick", "V, S ", "Concentration, up to 6 rounds",
        "6d10 bludgeoning damage. Str. save for half. -1d10 each round."),
    IMPRISONMENT(MagicSchool.ABJURATION, 9, "1 minute", "30 feet",
        "1 creature", "V, S, M", "Until dispelled", "Imprison creature.\nWis. save."),
    POWER_WORD_HEAL(MagicSchool.EVOCATION, 9, "1 action", "Touch",
        "1 creature", "V, S ", "Instantaneous", "Regain all HP. End all conditions."),;

    private final MagicSchool school;
    private final int level;
    private final String castingTime;
    private final String range;
    private final String area;
    private final String duration;
    private final String components;
    private final String effect;

    private Spell(int level) {
        this(MagicSchool.ABJURATION, level, "TBD", "TBD", "TBD", "TBD", "TBD");
    }

    private Spell(MagicSchool school, int level, String castingTime,
        String range, String components, String duration,
        String effect) {
        this(school, level, castingTime, range, "TBD", components, duration, effect);
    }

    private Spell(MagicSchool school, int level, String castingTime,
        String range, String area, String components, String duration, String effect) {
        this.school = school;
        this.level = level;
        this.castingTime = castingTime;
        this.range = range;
        this.area = area;
        this.duration = duration;
        this.components = components;
        this.effect = effect;
    }

    public int getLevel() {
        return level;
    }

    public boolean isCantrip() {
        return level == 0;
    }

    public boolean isLevel(int level) {
        return this.level == level;
    }

    public String getEffect(Character character) {
        return StringUtils.expand(effect, character);
    }

    public String getArea() {
        return area;
    }

    public String getDuration() {
        return duration;
    }

    public String getCastingTime() {
        return castingTime;
    }

    public String getRange() {
        return range;
    }

    public String getComponents() {
        return components;
    }

    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    public Stream<String> getDescription(Character character) {
        return Stream.of(
            school.toString(),
            "<b>Casting time</b> " + castingTime,
            "<b>Range</b> " + range,
            "<b>Area of effect</b> " + area,
            "<b>Duration</b> " + duration,
            getEffect(character));
    }

    @Override
    public void choose(Character character) {
        throw new UnsupportedOperationException("Spells need spell caster context for choice.");
    }

    @Override
    public Node save(Document doc) {
        Node node = doc.createElement("spell");
        node.setTextContent(name());
        return node;
    }

    public static Spell load(Node node) {
        return Spell.valueOf(node.getTextContent());
    }

}
