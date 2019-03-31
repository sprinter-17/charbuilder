package characterbuilder.character.beast;

import characterbuilder.character.attribute.DamageType;
import static characterbuilder.character.attribute.DamageType.*;
import static characterbuilder.character.beast.ChallengeRating.*;
import characterbuilder.character.equipment.Attack;
import characterbuilder.utils.StringUtils;
import static characterbuilder.utils.StringUtils.element;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

public enum Beast {
    BAT(beast(CR0, 12, 1, "5, fly 30")
        .withSense("blindsight 60 feet")
        .withSense("passive perception 11")
        .withSkill("advantage on hearing perception")),
    BLACK_BEAR(beast(CR1_2, 11, 19, "40, climb 30")
        .withSkill("perception +3").withSense("passive perception 13")),
    BOAR(beast(CR1_4, 11, 11, "40")
        .withSense("passive perception 9")
        .withSkill("If reduced to 0 HP by 7 damage or less, reduced to 1 HP instead")
        .withAttack("Tusk", "5", +3, "1d6+1", SLASHING,
            "With 20 feet charge: +1d6 damage, Str. save DC 11 or knocked prone")),
    BROWN_BEAR(beast(CR1, 11, 34, "40, climb 30")
        .withSkill("perception +3").withSense("passive perception 13")),
    CONSTRICTOR_SNAKE(beast(CR1_4, 12, 13, "30, swim 30")
        .withSense("blindsight 10 feet").withSense("passive perception 10")
        .withAttack("Constrict", "5", +4, "1d8+2", BLUDGEONING, "grappled escape DC14")
        .withAttack("Bite", "5", +4, "1d6+2", PIERCING)),
    LION(beast(CR1, 12, 26, "50")
        .withSkill("perception +3, advantage on smell").withSkill("stealth + 6")
        .withSense("passive perception 13")
        .withSkill("Advantage on attacks if ally within 5 feet of target")
        .withSkill("Can long jump up to 25 feet after 10-foot running start")
        .withAttack("Bite", "5", +5, "1d8+3", PIERCING)
        .withAttack("Claw", "5", +5, "1d6+3", SLASHING,
            "with 20 feet charge: Str. Save DC 13 or knocked prone and bite as bonus action")),
    PANTHER(beast(CR1_4, 12, 13, "50, climb 40")
        .withSkill("perception +4, advantage on smell").withSkill("stealth +6")
        .withSkill("Move 20 feet before claw attack, Str. save DC12 or knocked prone and "
            + "panther can bite as bonus action")),
    RAT(beast(CR0, 10, 1, "20")
        .withSense("darkvision 30 feet").withSense("passive perception 10")
        .withSkill("advantage on perception by smell")
        .withAttack("Bite", "5", +0, "1", PIERCING)),
    RAVEN(beast(CR0, 12, 1, "10, fly 50")
        .withSense("passive perception 13")
        .withSkill("perception +3")
        .withSkill("mimic simple sounds; insight check DC 10 to detect")
        .withAttack("Beak", "5", +4, "1", PIERCING)),
    REEF_SHARK(beast(CR1_2, 12, 22, "swim 40")
        .withSense("blindsight 30 feet").withSense("passive perception 12")
        .withSkill("perception + 2")
        .withAttack("Bite", "5", +4, "1d8+2", PIERCING, "Advantage if ally within 5 feet of target")),
    TIGER(beast(CR1, 12, 37, "40")
        .withSense("darkvision 60 feet").withSense("passive perception 13")
        .withSkill("perception +3 with advantage on smell").withSkill("stealth + 6")
        .withAttack("Bite", "5", +5, "1d8+3", PIERCING)
        .withAttack("Claw", "5", +5, "1d8+3", SLASHING,
            "Move 20 feet before attack Str. save DC 13 or knocked prone. "
            + "As bonus action on prone target, make bite attack.")),
    WOLF(beast(CR1_4, 13, 11, "40")
        .withSkill("perception +3 with advantage on hearing and smell")
        .withSkill("stealth +4")
        .withSkill("Advantage on attacks if ally within 5 feet of target")
        .withAttack("Bite", "5", +4, "2d4+2", PIERCING, "Str. save DC 11 or knocked prone."));

    private final ChallengeRating challengeRating;
    private final String speed;
    private final String description;

    private Beast(Builder builder) {
        this.challengeRating = builder.challengeRating;
        this.speed = builder.speed;
        this.description = builder.getDescription();
    }

    private static Builder beast(ChallengeRating cr, int ac, int hp, String speed) {
        return new Builder(cr, ac, hp, speed);
    }

    public boolean hasMovement(String movement) {
        return speed.contains(movement);
    }

    public boolean challengeRatingIsNotGreaterThan(ChallengeRating cr) {
        return challengeRating.compareTo(cr) <= 0;
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    public Stream<String> getDescription() {
        return Stream.of(description);
    }

    private static class Builder {

        private final ChallengeRating challengeRating;
        private final int armourClass;
        private final int hitPoints;
        private final String speed;
        private final List<String> skills = new ArrayList<>();
        private final List<String> senses = new ArrayList<>();
        private final List<Attack> attacks = new ArrayList<>();

        public Builder(ChallengeRating challengeRating, int ArmourClass, int hitPoints, String speed) {
            this.challengeRating = challengeRating;
            this.armourClass = ArmourClass;
            this.hitPoints = hitPoints;
            this.speed = speed;
        }

        public Builder withSkill(String skill) {
            this.skills.add(skill);
            return this;
        }

        public Builder withSense(String sense) {
            this.senses.add(sense);
            return this;
        }

        public Builder withAttack(String name, String range, int bonus, String damage, DamageType type) {
            this.attacks.add(new Attack(name, range, bonus, damage, type));
            return this;
        }

        public Builder withAttack(String name, String range, int bonus, String damage,
            DamageType type, String effect) {
            Attack attack = new Attack(name, range, bonus, damage, type);
            attack.setDescription(effect);
            this.attacks.add(attack);
            return this;
        }

        public String getDescription() {
            StringBuilder description = new StringBuilder();
            description.append("<table cellpadding='0' cellspacing='0' style='width:100%'>");
            description.append(list("Attributes", Collections.singletonList(
                String.format("AC %d HP %d Speed %s", armourClass, hitPoints, speed))));
            if (!senses.isEmpty())
                description.append(list("Senses", senses));
            if (!skills.isEmpty())
                description.append(list("Skills", skills));
            if (!attacks.isEmpty())
                description.append(list("Attacks", attacks.stream()
                    .map(this::attack).collect(toList())));
            description.append("</table>");
            return description.toString();
        }

        private String list(String name, List<String> items) {
            StringBuilder html = new StringBuilder();
            html.append("<tr><th rowspan='").append(items.size())
                .append("' style='width:40px; border-bottom: 1px dotted black'>")
                .append(name).append("</th>");
            html.append("<td style='border-bottom: 1px dotted black'>");
            html.append(items.stream()
                .collect(joining("</td></tr><tr><td style='border-bottom: 1px dotted black'>")));
            html.append("</td></tr>");
            return html.toString();
        }

        private String attack(Attack attack) {
            StringBuilder html = new StringBuilder();
            html.append("<table><tr>");
            html.append(element("td style='width:40px'", element("em", attack.getName())));
            html.append("<td>");
            html.append("Range ").append(attack.getRange());
            html.append(" Attack ").append(String.format("%+d", attack.getBonus()));
            html.append(" Damage ").append(attack.getDamage()).append(" ").append(attack.getType());
            if (attack.getDescription().isPresent())
                html.append("<br>").append(attack.getDescription().get());
            html.append("</td>");
            html.append("</tr></table>");
            return html.toString();
        }

    }
}
