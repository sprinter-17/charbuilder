package characterbuilder.character.characterclass.fighter;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.utils.StringUtils;
import java.util.Arrays;
import java.util.stream.Stream;
import org.w3c.dom.Node;

public enum Maneuver implements Attribute {
    COMMANDERS_STRIKE("As attack and bonus actions, "
        + "ally uses reaction to make weapon attack +1d8 damage") {
        @Override
        public String toString() {
            return "Commander's Strike";
        }
    },
    DISARMING_STRIKE("On hit, damage +ROLL, target Str. save or drop weapon."),
    DISTRACTING_STRIKE("On hit, damage +ROLL,  next attack on target by ally has advantage."),
    EVASIVE_FOOTWORK("On move, AC +ROLL"),
    FEINTING_ATTACK("As bonus action, advantage on next attack and damage +ROLL on hit."),
    GOADING_ATTACK("On hit, damage +ROLL, target Wis. save or disadvantage on attacks on allies."),
    LUNGING_ATTACK("Increase reach 5 feet, damage +ROLL on hit."),
    MANEUVERING_ATTACK("On hit, damage +ROLL, "
        + "ally uses reaction to move half speed without opportunity attacks."),
    MENANCING_ATTACK("On hit, damage +ROLL, target Wis. save or frightened for 1 turn."),
    PARRY("On being hit, use reaction to reduce damage by ROLL[bonus($dex_mod)]."),
    PRECISION_ATTACK("Attack +ROLL before or after roll."),
    PUSHING_ATTACK("On hit, damage +ROLL, target Str. save or pushed up to 15 feet away."),
    RALLY("As bonus action, ally gains ROLL[bonus($chr_mod)] temporary HP."),
    RIPOSTE("On being missed, as reaction make attack, damage +ROLL on hit."),
    SWEEPING_ATTACK("On hit, cause ROLL damage to second target within 5 feet if attack would hit."),
    TRIP_ATTACK("On hit, damage +ROLL, target Str. save or knocked prone.");

    private final String[] descriptions;

    private Maneuver(String... descriptions) {
        this.descriptions = descriptions;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.MANEUVER;
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return Stream.concat(
            Stream.of("Use 1 superiority die."),
            Arrays.stream(descriptions)
                .map(desc -> desc
                .replace("ROLL", "1d[max($fighter_level 0:6,3:8,10:10,18:12)]"))
                .map(desc -> StringUtils.expand(desc, character)));
    }

    public static Maneuver load(Node node) {
        return valueOf(node.getTextContent());
    }
}
