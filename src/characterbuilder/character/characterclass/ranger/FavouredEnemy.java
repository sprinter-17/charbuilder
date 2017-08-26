package characterbuilder.character.characterclass.ranger;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.choice.ChoiceSelector;
import characterbuilder.character.choice.Option;
import characterbuilder.character.choice.OptionChoice;
import characterbuilder.character.saveload.Savable;
import characterbuilder.utils.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.joining;
import java.util.stream.Stream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class FavouredEnemy implements Attribute {

    private final List<Enemy> enemies = new ArrayList<>();

    public enum Enemy implements Option {
        ABERRATIONS,
        BEASTS,
        CELESTIALS,
        CONSTRUCTS,
        DRAGONS,
        ELEMENTALS,
        FEY,
        FIENDS,
        GIANTS,
        MONSTROSITIES,
        OOZES,
        PLANTS,
        UNDEAD,
        BUGBEARS,
        GNOLLS,
        ORCS,
        GOBLINS,
        WEREBOAR,
        WEREBEAR,
        WERERAT,
        WERETIGER,
        WEREWOLF,
        KOBOLD,
        KENKU;

        public boolean isHumanoid() {
            return this.ordinal() >= BUGBEARS.ordinal();
        }

        @Override
        public String toString() {
            return StringUtils.capitaliseEnumName(name());
        }

        @Override
        public void choose(Character character) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public Element save(Document doc) {
            throw new UnsupportedOperationException("Not supported.");
        }
    }

    public static OptionChoice choice() {
        return new OptionChoice("Favoured Enemy") {
            private boolean humanoidOnly = false;

            @Override
            public void select(Character character, ChoiceSelector selector) {
                FavouredEnemy favouredEnemy = character.getAllAttributes()
                    .filter(attr -> attr instanceof FavouredEnemy)
                    .map(attr -> (FavouredEnemy) attr)
                    .findAny().orElse(new FavouredEnemy());
                selector.chooseOption(Arrays.stream(Enemy.values())
                    .filter(enemy -> !favouredEnemy.enemies.contains(enemy))
                    .filter(enemy -> !humanoidOnly || enemy.isHumanoid()),
                    enemy -> {
                    favouredEnemy.enemies.add(enemy);
                    if (!character.hasAttribute(favouredEnemy))
                        character.addAttribute(favouredEnemy);
                    if (enemy.isHumanoid() && !humanoidOnly) {
                        humanoidOnly = true;
                        addCount(1);
                    }
                });
            }
        };
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    @Override
    public AttributeType getType() {
        return AttributeType.RANGER_ABILITY;
    }

    @Override
    public Element save(Document doc) {
        Element element = getType().save(doc);
        element.setTextContent("FAVOURED_ENEMY");
        enemies.forEach(enemy -> {
            Element child = doc.createElement("enemy");
            child.setTextContent(enemy.name());
            element.appendChild(child);
        });
        return element;
    }

    public static FavouredEnemy load(Element element) {
        assert Savable.text(element).equals("FAVOURED_ENEMY");
        FavouredEnemy favouredEnemy = new FavouredEnemy();
        Savable.children(element, "enemy").forEach(child -> {
            favouredEnemy.enemies.add(Enemy.valueOf(child.getTextContent()));
        });
        return favouredEnemy;
    }

    @Override
    public String toString() {
        return "Favoured Enemy";
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return Stream.of(
            enemies.stream().map(Enemy::toString).sorted().collect(joining(", ")),
            "Advantage on Survival checks to track.",
            "Int. checks to recall information.");
    }

    @Override
    public int hashCode() {
        return enemies.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        final FavouredEnemy other = (FavouredEnemy) obj;
        return this.enemies.equals(other.enemies);
    }

}
