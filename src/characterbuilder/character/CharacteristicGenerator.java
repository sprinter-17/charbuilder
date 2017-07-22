package characterbuilder.character;

import characterbuilder.character.attribute.Alignment;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.ALIGNMENT;
import static characterbuilder.character.attribute.AttributeType.BACKGROUND;
import static characterbuilder.character.attribute.AttributeType.BOND;
import static characterbuilder.character.attribute.AttributeType.FLAW;
import static characterbuilder.character.attribute.AttributeType.IDEAL;
import static characterbuilder.character.attribute.AttributeType.TRAIT;
import characterbuilder.character.attribute.Background;
import characterbuilder.character.attribute.StringAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

public class CharacteristicGenerator {

    private final Random random;
    private final List<Characteristic> characteristics = new ArrayList<>();

    private class Characteristic {

        private final AttributeType type;
        private final String description;
        private final Predicate<Character> condition;

        public Characteristic(AttributeType type, String description, Predicate<Character> condition) {
            this.type = type;
            this.description = description;
            this.condition = condition;
        }

        public Attribute toAttribute() {
            return new StringAttribute(type, description);
        }
    }

    public CharacteristicGenerator(Random random) {
        this.random = random;
        add(TRAIT, "I idolize a particular hero of my faith, and constantly refer to that person’s deeds and example.", Background.ACOLYTE);
        add(TRAIT, "I can find common ground between the fiercest enemies, empathizing with them and always working toward peace.", Background.ACOLYTE);
        add(TRAIT, "I see omens in every event and action. The gods try to speak to us, we just need to listen.", Background.ACOLYTE);
        add(TRAIT, "Nothing can shake my optimistic attitude.", Background.ACOLYTE);
        add(TRAIT, "I quote (or misquote) sacred texts and proverbs in almost every situation.",
            Background.ACOLYTE);
        add(TRAIT, "I am tolerant (or intolerant) of other faiths and respect (or condemn) the worship of other gods.", Background.ACOLYTE);
        add(TRAIT, "I’ve enjoyed fine food, drink, and high society among my temple’s elite. Rough living grates on me.", Background.ACOLYTE);
        add(TRAIT, "I’ve spent so long in the temple that I have little practical experience dealing with people in the outside world.", Background.ACOLYTE);
        add(IDEAL, "The ancient traditions of worship and sacrifice must be preserved and upheld.",
            Background.ACOLYTE, Alignment::isLawful);
        add(IDEAL, "I always try to help those in need, no matter what the personal cost.",
            Background.ACOLYTE, Alignment::isGood);
        add(IDEAL, "We must help bring about the changes the gods are constantly working in the world.", Background.ACOLYTE, Alignment::isChaotic);
        add(IDEAL, "I hope to one day rise to the top of my faith’s religious hierarchy.", Background.ACOLYTE, Alignment::isLawful);
        add(IDEAL, "I trust that my deity will guide my actions. I have faith that if I work hard, things will go well.", Background.ACOLYTE, Alignment::isLawful);
        add(IDEAL, "I seek to prove myself worthy of my god’s favor by matching my actions against his or her teachings.", Background.ACOLYTE);
        add(BOND, "I would die to recover and ancient relic of my faith that was lost long ago.",
            Background.ACOLYTE);
        add(BOND, "I will someday get revenge on the corrupt temple hierarchy who branded me a heretic.", Background.ACOLYTE);
        add(BOND, "I owe my life to the priest who took me in when my parents died.", Background.ACOLYTE);
        add(BOND, "Everything I do is for the common people.", Background.ACOLYTE);
        add(BOND, "I will do anything to protect the temple where I served.", Background.ACOLYTE);
        add(BOND, "I seek to preserve a sacred text that my enemies consider heretical and seek to destroy.", Background.ACOLYTE);
        add(FLAW, "I judge others harshly, and myself even more severely.", Background.ACOLYTE);
        add(FLAW, "I put too much trust in those who wield power within my temple’s hierarchy.", Background.ACOLYTE);
        add(FLAW, "My piety sometimes leads me to blindly trust those that profess faith in my god.", Background.ACOLYTE);
        add(FLAW, "I am inflexible in my thinking.", Background.ACOLYTE);
        add(FLAW, "I am suspicious of strangers and expect the worst of them.", Background.ACOLYTE);
        add(FLAW, "Once I pick a goal, I become obsessed with it to the detriment of everything else in my life.", Background.ACOLYTE);

        add(TRAIT, "I always have a plan for what to do when things go wrong.", Background.CRIMINAL);
        add(TRAIT, "I am always calm, no matter what the situation. I never raise my voice or let my emotions control me.", Background.CRIMINAL);
        add(TRAIT, "I would rather make a new friend than a new enemy.", Background.CRIMINAL);
        add(TRAIT, "The first thing I do in a new place is note the locations of everything valuable—or where such things could be hidden.", Background.CRIMINAL);
        add(TRAIT, "I am incredibly slow to trust. Those who seem the fairest often have the most to hide.", Background.CRIMINAL);
        add(TRAIT, "I don’t pay attention to the risks in a situation. Never tell me the odds.", Background.CRIMINAL);
        add(TRAIT, "The best way to get me to do something is to tell me I can’t do it.", Background.CRIMINAL);
        add(TRAIT, "I blow up at the slightest insult.", Background.CRIMINAL);

        add(IDEAL, "I don't steal from others in the trade", Background.CRIMINAL, Alignment::isLawful);
        add(IDEAL, "Chains are meant to be broken, as are those who would forge them", Background.CRIMINAL, Alignment::isChaotic);
        add(IDEAL, "I steal from the wealthy so that I can help people in need.", Background.CRIMINAL, Alignment::isGood);
        add(IDEAL, "I will do whatever it takes to become wealthy.", Background.CRIMINAL, Alignment::isEvil);
        add(IDEAL, "I'm loyal to my friends, not to any ideals, and everyone else can take a trip down the Styx for all I care.", Background.CRIMINAL, Alignment.NEUTRAL::equals);
        add(IDEAL, "There's a spark of good in everyone", Background.CRIMINAL, Alignment::isGood);
        add(BOND, "I’m trying to pay o  an old debt I owe to a generous benefactor.", Background.CRIMINAL);
        add(BOND, "My ill-gotten gains go to support my family.", Background.CRIMINAL);
        add(BOND, "Something important was taken from me, and I aim to steal it back.", Background.CRIMINAL);
        add(BOND, "I will become the greatest thief that ever lived.", Background.CRIMINAL);
        add(BOND, "I’m guilty of a terrible crime. I hope I can redeem myself for it.", Background.CRIMINAL);
        add(BOND, "Someone I loved died because of I mistake I made. That will never happen again.", Background.CRIMINAL);
        add(FLAW, "When I see something valuable, I can't think about anything but how to steal it.", Background.CRIMINAL);
        add(FLAW, "When faced with a choice between money and my friends, I usually choose the money.", Background.CRIMINAL);
        add(FLAW, "If there's a plan, I'll forget it. If I don't forget it, I'll ignore it.", Background.CRIMINAL);
        add(FLAW, "I have a “tell” that reveals when I’m lying.", Background.CRIMINAL);
        add(FLAW, "I turn tail and run when things look bad.", Background.CRIMINAL);
        add(FLAW, "An innocent person is in prison for a crime that I committed. I’m okay with that.", Background.CRIMINAL);
    }

    private void add(AttributeType type, String description, Background background) {
        characteristics.add(new Characteristic(type, description,
            ch -> ch.hasAttribute(background)));
    }

    private void add(AttributeType type, String description, Background background,
        Predicate<Alignment> condition) {
        characteristics.add(new Characteristic(type, description,
            ch -> ch.hasAttribute(background)
            && ch.hasAttribute(BACKGROUND)
            && ch.hasAttribute(ALIGNMENT)
            && condition.test(ch.getAttribute(ALIGNMENT))));
    }

    public void generate(Character character) {
        Stream.of(TRAIT, BOND, IDEAL, FLAW)
            .map(type -> generateOfType(character, type))
            .filter(Optional::isPresent).map(Optional::get)
            .forEach(character::addAttribute);
    }

    private Optional<Attribute> generateOfType(Character character, AttributeType type) {
        List<Attribute> potential = characteristics.stream()
            .filter(ch -> ch.type.equals(type))
            .filter(ch -> ch.condition.test(character))
            .map(Characteristic::toAttribute)
            .collect(toList());
        if (potential.isEmpty())
            return Optional.empty();
        else
            return Optional.of(potential.get(random.nextInt(potential.size())));
    }
}
