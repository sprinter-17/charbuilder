package characterbuilder.character.characterclass.ranger;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Language;
import static characterbuilder.character.ability.Language.*;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.ChoiceSelector;
import characterbuilder.character.choice.Option;
import characterbuilder.character.choice.OptionChoice;
import characterbuilder.character.saveload.Savable;
import characterbuilder.utils.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class FavouredEnemy implements Attribute {

    private final List<Enemy> enemies = new ArrayList<>();

    public enum Enemy implements Option {
        ABERRATIONS(),
        BEASTS(),
        CELESTIALS(CELESTIAL),
        CONSTRUCTS(),
        DRAGONS(DRACONIC),
        ELEMENTALS(PRIMORDIAL),
        FEY(SYLVAN),
        FIENDS(INFERNAL),
        GIANTS(GIANT),
        MONSTROSITIES(),
        OOZES(),
        PLANTS(),
        UNDEAD(),

        // All humanoids below this point
        BUGBEARS(COMMON, GOBLIN),
        BULLYWUG(Language.BULLYWUG),
        DEEP_GNOME(GNOMISH, TERRAN, UNDERCOMMON),
        DUERGAR(DWARVISH, UNDERCOMMON),
        DROW(ELVISH, UNDERCOMMON),
        GNOLLS(Language.GNOLL),
        GOBLINS(COMMON, GOBLIN),
        GRIMLOCK(UNDERCOMMON),
        HOBGOBLINS(COMMON, GOBLIN),
        JACKALWERE(COMMON),
        KENKU(),
        KOBOLDS(COMMON, DRACONIC),
        KUO_TOA(UNDERCOMMON),
        LIZARDFOLK(DRACONIC),
        ORCS(COMMON, ORC),
        SAHUAGIN(Language.SAHUAGIN),
        TROGLODYTE(Language.TROGLODYTE),
        WEREBOAR(COMMON),
        WEREBEAR(COMMON),
        WERERAT(COMMON),
        WERETIGER(COMMON),
        WEREWOLF(COMMON),;

        private final Language[] languages;

        private Enemy(Language... languages) {
            this.languages = languages;
        }

        public boolean isHumanoid() {
            return this.ordinal() >= BUGBEARS.ordinal();
        }

        @Override
        public String getOptionName() {
            if (isHumanoid())
                return toString() + " (humanoid)";
            else
                return toString();
        }

        @Override
        public String toString() {
            return StringUtils.capitaliseEnumName(name());
        }

        @Override
        public Stream<String> getDescription(Character character) {
            switch (languages.length) {
                case 0:
                    return Stream.of("No languages");
                case 1:
                    return Stream.of("Language: " + languages[0].toString());
                default:
                    return Stream.of("Languages: " + Arrays.stream(languages)
                        .map(Language::toString).collect(joining(", ")));
            }
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

    private static class EnemyChoice extends OptionChoice {

        private boolean humanoidOnly = false;

        public EnemyChoice() {
            super("Favoured Enemy");
        }

        public void select(Character character, ChoiceSelector selector) {
            FavouredEnemy favouredEnemy = character.getAllAttributes()
                .filter(attr -> attr instanceof FavouredEnemy)
                .map(attr -> (FavouredEnemy) attr)
                .findAny().orElse(new FavouredEnemy());
            selector.chooseOption(Arrays.stream(Enemy.values())
                .filter(enemy -> !favouredEnemy.enemies.contains(enemy))
                .filter(enemy -> !humanoidOnly || enemy.isHumanoid()),
                enemy -> favouredEnemy.chooseEnemy(character, this, enemy));
        }
    }

    public static OptionChoice choice() {
        return new EnemyChoice();
    }

    private void chooseEnemy(Character character, EnemyChoice choice, Enemy enemy) {
        enemies.add(enemy);
        character.addAttributeIfNotPresent(this);
        List<Attribute> languages = Arrays.stream(enemy.languages)
            .filter(l -> !character.hasAttribute(l)).collect(toList());
        if (languages.size() == 1)
            languages.forEach(character::addAttribute);
        else if (languages.size() > 1)
            character.addChoice(0, new AttributeChoice("Language for " + enemy.toString(),
                languages.stream()));
        if (enemy.isHumanoid() && !choice.humanoidOnly) {
            choice.addCount(1);
            choice.humanoidOnly = true;
        }
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
