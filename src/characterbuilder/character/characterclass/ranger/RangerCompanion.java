package characterbuilder.character.characterclass.ranger;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Ability;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.utils.StringUtils;
import java.util.Arrays;
import java.util.stream.Stream;
import org.w3c.dom.Node;

public enum RangerCompanion implements Attribute {
    BOAR("AC 11, HP 11, Speed 40",
        "Tusk +3 1d6+1 slashing",
        "charge +1d6 Str. save DC 11 or knocked prone",
        "1 HP if takes 7 damage or less to 0 HP."),
    CONSTRICTOR_SNAKE("AC 12, HP 13, Speed 30 Swim 30",
        "Bite +4 1d6+2 piercing",
        "Constrict +4 1d8+2 bludgeoning and grappled escape DC 14."),
    PANTHER("AC 12, HP 13, Speed 50 Climb 40",
        "Bite +4 1d6+2 piercing",
        "Claw +4 1d4+2 slashing",
        "Advantage on Perception to smell",
        "Pounce 20 feet move then hit with claw Str. save DC 12 or knocked prone and "
        + "panther makes bite attack as bonus action."),
    WOLF("AC 13, HP 11, Speed 40",
        "Bite +4 2d4+2 Str. save DC11 or knocked prone",
        "Advantage on Perception to smell or hear",
        "Advantage on attack if ally within 5 feet.");

    private final String[] description;

    private RangerCompanion(String... description) {
        this.description = description;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.RANGERS_COMPANION;
    }

    @Override
    public void generateInitialChoices(Character character) {
        character.addAttribute(Ability.RANGERS_COMPANION);
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return Arrays.stream(description);
    }

    public static RangerCompanion load(Node node) {
        return valueOf(node.getTextContent());
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

}
