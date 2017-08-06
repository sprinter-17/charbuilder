package characterbuilder.character;

import characterbuilder.character.attribute.Alignment;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.ALIGNMENT;
import static characterbuilder.character.attribute.AttributeType.BOND;
import static characterbuilder.character.attribute.AttributeType.FLAW;
import static characterbuilder.character.attribute.AttributeType.IDEAL;
import static characterbuilder.character.attribute.AttributeType.TRAIT;
import characterbuilder.character.attribute.Background;
import characterbuilder.character.attribute.StringAttribute;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

public class CharacteristicGenerator {

    private final Random random;
    private final EnumMap<Background, EnumMap<AttributeType, List<Characteristic>>> characteristics
        = new EnumMap<>(Background.class);
    private Background background;
    private AttributeType type;

    private class Characteristic {

        private final AttributeType type;
        private final String description;
        private final Predicate<Character> condition;

        public Characteristic(AttributeType type, String description,
            Predicate<Character> condition) {
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

        background = Background.ACOLYTE;
        type = TRAIT;
        add("I idolize a particular hero of my faith, and constantly refer to that person’s "
            + "deeds and example.");
        add("I can find common ground between the fiercest enemies, empathizing with "
            + "them and always working toward peace.");
        add("I see omens in every event and action. The gods try to speak to us, we just "
            + "need to listen.");
        add("Nothing can shake my optimistic attitude.");
        add("I quote (or misquote) sacred texts and proverbs in almost every situation.");
        add("I am tolerant (or intolerant) of other faiths and respect (or condemn) the "
            + "worship of other gods.");
        add("I’ve enjoyed fine food, drink, and high society among my temple’s elite. "
            + "Rough living grates on me.");
        add("I’ve spent so long in the temple that I have little practical experience "
            + "dealing with people in the outside world.");

        type = IDEAL;
        add("The ancient traditions of worship and sacrifice must be preserved and upheld.",
            Alignment::isLawful);
        add("I always try to help those in need, no matter what the personal cost.",
            Alignment::isGood);
        add("We must help bring about the changes the gods are constantly working in "
            + "the world.", Alignment::isChaotic);
        add("I hope to one day rise to the top of my faith’s religious hierarchy.",
            Alignment::isLawful);
        add("I trust that my deity will guide my actions. I have faith that if I work "
            + "hard, things will go well.", Alignment::isLawful);
        add("I seek to prove myself worthy of my god’s favor by matching my actions against "
            + "his or her teachings.");

        type = BOND;
        add("I would die to recover and ancient relic of my faith that was lost long ago.");
        add("I will someday get revenge on the corrupt temple hierarchy who branded me a "
            + "heretic.");
        add("I owe my life to the priest who took me in when my parents died.");
        add("Everything I do is for the common people.");
        add("I will do anything to protect the temple where I served.");
        add("I seek to preserve a sacred text that my enemies consider heretical and "
            + "seek to destroy.");

        type = FLAW;
        add("I judge others harshly, and myself even more severely.");
        add("I put too much trust in those who wield power within my temple’s "
            + "hierarchy.");
        add("My piety sometimes leads me to blindly trust those that profess faith "
            + "in my god.");
        add("I am inflexible in my thinking.");
        add("I am suspicious of strangers and expect the worst of them.");
        add("Once I pick a goal, I become obsessed with it to the detriment of everything "
            + "else in my life.");

        background = Background.CHARLATAN;
        type = TRAIT;
        add("I fall in and out of love easily, and am always pursuing someone.");
        add("I have a joke for every occasion, especially occasions where humour is inappropriate.");
        add("Flattery is my preferred trick for getting what I want.");
        add("I'm a born gambler who can't resist taking a risk for a potential payoff.");
        add("I lie about almost everything, even when there's no good reason to.");
        add("Sarcasm and insults are my weapon of choice.");
        add("I keep multiple holy symbols on me and invoke whatever deity might come in useful "
            + "at any given moment");
        add("I pocket anything I see that might have some value");

        type = IDEAL;
        add("I am a free spirit - no one tells me what to do.", Alignment::isChaotic);
        add("I never target people who can't affor to lose a few coins.", Alignment::isLawful);
        add("I distribute the money I acquire to the people who really need it.", Alignment::isGood);
        add("I never run the same con twice.", Alignment::isChaotic);
        add("Material goods come and go. Bonds of friendship last forever.", Alignment::isGood);
        add("I'm determined to make something of myself.");

        type = BOND;
        add("I fleeced the wrong person and must work to ensure that this individual never crosses "
            + "paths with me or those I care about.");
        add("I owe everything to my mentor - a horrible person who's probably rotting in jain "
            + "somewhere.");
        add("Somewhere out there, I have a child who doesn't know me. I'm making the world better "
            + "for him or her");
        add("I come from a noble family and one day I'll reclaim my lands and title from those "
            + "who stole them from me.");
        add("A powerful person killed someone I love. Someday soon I'll have my revenge.");
        add("I swindled and ruined a person who didn't deserve it. I seek to atone for my misdeeds "
            + "but might never be able to forgive myself.");

        type = FLAW;
        add("I can't resist a pretty face.");
        add("I'm always in debt. I spend my ill-gotten gains on decadent luxuries faster than I "
            + "bring them in.");
        add("I'm convinced that no one could ever fool me in the way I fool others.");
        add("I'm too greedy for my own good. I can't resist taking a risk if there's money "
            + "involved.");
        add("I can't resist swindling people who are more powerful than me.");
        add("I hate to admit it and will hate myself for it, but I'll run and preserve my own hide "
            + "if the going gets tough.");

        background = Background.CRIMINAL;
        type = TRAIT;
        add("I always have a plan for what to do when things go wrong.");
        add("I am always calm, no matter what the situation. I never raise my voice or let "
            + "my emotions control me.");
        add("I would rather make a new friend than a new enemy.");
        add("The first thing I do in a new place is note the locations of everything "
            + "valuable—or where such things could be hidden.");
        add("I am incredibly slow to trust. Those who seem the fairest often have the most "
            + "to hide.");
        add("I don’t pay attention to the risks in a situation. Never tell me the odds.");
        add("The best way to get me to do something is to tell me I can’t do it.");
        add("I blow up at the slightest insult.");

        type = IDEAL;
        add("I don't steal from others in the trade.", Alignment::isLawful);
        add("Chains are meant to be broken, as are those who would forge them.",
            Alignment::isChaotic);
        add("I steal from the wealthy so that I can help people in need.", Alignment::isGood);
        add("I will do whatever it takes to become wealthy.", Alignment::isEvil);
        add("I'm loyal to my friends, not to any ideals, and everyone else can take a "
            + "trip down the Styx for all I care.", Alignment::isNeutral);
        add("There's a spark of good in everyone", Alignment::isGood);

        type = BOND;
        add("I’m trying to pay of an old debt I owe to a generous benefactor.");
        add("My ill-gotten gains go to support my family.");
        add("Something important was taken from me, and I aim to steal it back.");
        add("I will become the greatest thief that ever lived.");
        add("I’m guilty of a terrible crime. I hope I can redeem myself for it.");
        add("Someone I loved died because of I mistake I made. That will never happen again.");

        type = FLAW;
        add("When I see something valuable, I can't think about anything but how to steal it.");
        add("When faced with a choice between money and my friends, I usually choose the money.");
        add("If there's a plan, I'll forget it. If I don't forget it, I'll ignore it.");
        add("I have a “tell” that reveals when I’m lying.");
        add("I turn tail and run when things look bad.");
        add("An innocent person is in prison for a crime that I committed. I’m okay with that.");

        background = Background.ENTERTAINER;
        type = TRAIT;
        add("I know a story relevant to almost every situation.");
        add("Whenever I come to a new place, I collect local rumours and spread gossip.");
        add("I'm a hopeless romantic, always searching for that 'special someone'.");
        add("Nobody stays angry at me or around me for long, since I can defuse "
            + "any amount of tension.");
        add("I love a good insult, even one directed at me.");
        add("I get bitter if I'm not the centre of attention.");
        add("I'll settle for nothing less than perfection.");
        add("I change my mood or my mind as quickly as I change key in a song.");

        type = IDEAL;
        add("When I perform, I make the world better than it was.", Alignment::isGood);
        add("The stories, legends, and songs of the past must never be forgotten for they teach us"
            + "who we are", Alignment::isLawful);
        add("The world is in need of new ideas and bold action.", Alignment::isChaotic);
        add("I'm only in it for the money and fame.", Alignment::isEvil);
        add("I like seeing the smiles of people's faces when I perform. That's all that matters",
            Alignment::isNeutral);
        add("Art should reflect the sould; it should come from within and reveal who we really are");

        type = BOND;
        add("My instrument is my most treasured possession, and it reminds me of someone I love.");
        add("Someone stole my precious instrument, and someday I'll get it back.");
        add("I want to be famour, whatever it takes.");
        add("I idolize a hero of the old tales and measure my deeds against that person's.");
        add("I will do anything to prove myself superior to my hated rival.");
        add("I would do anything for the other members of my old troupe.");

        type = FLAW;
        add("I'll do anything to win fame and renown.");
        add("I'm a sucker for a pretty face.");
        add("A scandal prevents me from ever going home again. That kind of trouble seems to follow "
            + "me around.");
        add("I once satirized a noble who still wants my head. I was a mistake that I will likely "
            + "repeat.");
        add("I have trouble keeping my true feelings hidden. My sharp tounge lands me in trouble.");
        add("Despite my best efforts, I am unreliable to my friends.");

        background = Background.FOLK_HERO;
        type = TRAIT;
        add("I judge people by their actions, not their words.");
        add("If someone is in trouble, I’m always ready to lend help.");
        add("When I set my mind to something, I follow through no matter what gets in my way.");
        add("I have a strong sense of fair play and always try to find the most equitable "
            + "solution to arguments.");
        add("I’m confident in my own abilities and do what I can to instill confidence in others.");
        add("Thinking is for other people. I prefer action.");
        add("I misuse long words in an attempt to sound smarter.");
        add("I get bored easily. When am I going to get on with my destiny?");

        type = IDEAL;
        add("People deserve to be treated with dignity and respect.", Alignment::isGood);
        add("No one should get preferential treatment before the law, and no one "
            + "is above the law.", Alignment::isLawful);
        add("Tyrants must not be allowed to oppress the people.", Alignment::isChaotic);
        add("If I become strong, I can take what I want— what I deserve.", Alignment::isEvil);
        add("There’s no good in pretending to be something I’m not.", Alignment::isNeutral);
        add("Nothing and no one can steer me away from my higher calling.");

        type = BOND;
        add("I have a family, but I have no idea where they are. "
            + "One day, I hope to see them again.");
        add("I worked the land, I love the land, and I will protect the land.");
        add("A proud noble once gave me a horrible beating, "
            + "and I will take my revenge on any bully I encounter.");
        add("My tools are symbols of my past life, and I carry them "
            + "so that I will never forget my roots.");
        add("I protect those who cannot protect themselves.");
        add("I wish my childhood sweetheart had come with me to pursue my destiny.");

        type = FLAW;
        add("The tyrant who rules my land will stop at nothing to see me killed.");
        add("I’m convinced of the significance of my destiny, "
            + "and blind to my shortcomings and the risk of failure.");
        add("The people who knew me when I was young know my shameful secret, "
            + "so I can never go home again.");
        add("I have a weakness for the vices of the city, especially hard drink.");
        add("Secretly, I believe that things would be better "
            + "if I were a tyrant lording over the land.");
        add("I have trouble trusting in my allies.");

        background = Background.GUILD_ARTISAN;
        type = TRAIT;
        add("I believe that anything worth doing is worth doing right. I cannot help it..."
            + "I'm a perfectionist.");
        add("I'm a snob who looks down on those who cannot appreciate fine art.");
        add("I always want to know how things work and what makes people tick.");
        add("I'm full of witty aphorisms and hava a proverb for every occasion.");
        add("I'm rude to people who lack my commitment to hard work and fair play.");
        add("I like to talk at length about my profession");
        add("I don't part with my money easily and will haggle tirelessly to get the best deal "
            + "possible");
        add("I'm well know for my work, and I want to make sure everyone appreciates it. "
            + "I'm always taken aback when people haven't heard of me.");

        type = IDEAL;
        add("It is the duty of all civilized people to strengthen the bonds of community and the "
            + "security of civilization.", Alignment::isLawful);
        add("My talents were given to me so that I can use them to benefit the world. ",
            Alignment::isGood);
        add("Everyone should be free to pursue his or her own livelihood.", Alignment::isChaotic);
        add("I'm on in it for the money. ", Alignment::isEvil);
        add("I'm committed to the people I care about, not to ideals.", Alignment::isNeutral);
        add("I work hard to be the best there is at my craft.");

        type = BOND;
        add("The workshop where I learned my trade is the most important place in the world to me.");
        add("I created a great work for someone, then found them unworthy to receive it. "
            + "I'm still looking for someone worthy.");
        add("I owe my guild a great debt for forging me into the person I am today. ");
        add("I pursue wealth to secure someone's love.");
        add("One day I will return to my guild and prove that I am the greatest artisan of them all");
        add("I will get revenge on the evil forces that destroyed my place of business and ruined "
            + "my livelihood.");

        type = FLAW;
        add("I'll do anything to get my hands on something rare or priceless.");
        add("I'm quick to assume that someone is trying to cheat me.");
        add("No one must ever learn that I once stole money from guild coffers.");
        add("I'm never satisfied with what I have - I always want more.");
        add("I would kill to acquire a noble title.");
        add("I'm horribly jealous of anyone who can outshine my handiwork. "
            + "Everywhere I go, I'm surrounded by rivals.");

        background = Background.HERMIT;
        type = TRAIT;
        add("");

        type = IDEAL;
        add("");

        type = BOND;
        add("");

        type = FLAW;
        add("");

        background = Background.NOBLE;
        type = TRAIT;
        add("My eloquent flattery makes everyone I talk to feel like the most wonderful "
            + "and important person in the world.");
        add("The common folk love me for my kindness and generosity.");
        add("No one could doubt by looking at my regal bearing that I am a cut above "
            + "the unwashed masses.");
        add("I take great pains to always look my best and follow the latest fashions.");
        add("I don’t like to get my hands dirty, and I won’t be caught dead "
            + "in unsuitable accommodations.");
        add("Despite my noble birth, I do not place myself above other folk. "
            + "We all have the same blood.");
        add("My favor, once lost, is lost forever.");
        add("If you do me an injury, I will crush you, ruin your name, and salt your fields.");

        type = IDEAL;
        add("Respect is due to me because of my position, but all people "
            + "regardless of station deserve to be treated with dignity.", Alignment::isGood);
        add("It is my duty to respect the authority of those above me, "
            + "just as those below me must respect mine.", Alignment::isLawful);
        add("I must prove that I can handle myself without the coddling of my family.",
            Alignment::isChaotic);
        add("If I can attain more power, no one will tell me what to do.", Alignment::isEvil);
        add("Blood runs thicker than water.");
        add("It is my duty to protect and care for the people beneath me.", Alignment::isGood);

        type = BOND;
        add("I will face any challenge to win the approval of my family.");
        add("My house’s alliance with another noble family must be sustained at all costs.");
        add("Nothing is more important than the other members of my family.");
        add("I am in love with the heir of a family that my family despises.");
        add("My loyalty to my sovereign is unwavering.");
        add("The common folk must see me as a hero of the people.");

        type = FLAW;
        add("I secretly believe that everyone is beneath me.");
        add("I hide a truly scandalous secret that could ruin my family forever.");
        add("I too often hear veiled insults and threats in every word addressed to me, "
            + "and I’m quick to anger.");
        add("I have an insatiable desire for carnal pleasures.");
        add("In fact, the world does revolve around me.");
        add("By my words and actions, I often bring shame to my family.");

        background = Background.OUTLANDER;
        type = TRAIT;
        add("I'm driven by wanderlust that led me away from home.");
        add("I watch over my friends as if they were a liter of newborn pups.");
        add("I once ran twenty-five miles without stopping to warn my clan of an approaching "
            + "horde. I'd do it again if I had to.");
        add("I have a lesson for every situation, drawn from observing nature. ");
        add("I place no stock in wealthy or well-mannered folk. Money and manners won't save you "
            + "from a hungry owlbear.");
        add("I'm always picking things up, absently fiddling with them, and sometimes accidentally "
            + "breaking them.");
        add("I feel far more comfortable around animals than people.");
        add("I was, in fact, raised by wolves.");

        type = IDEAL;
        add("Life is like the seasons, in constant change, and we must change with it. ",
            Alignment::isChaotic);
        add("It is each person's responsibility to make the most happiness for the whole tribe.",
            Alignment::isGood);
        add("If I dishonour myself, I dishonour my whole clan. ", Alignment::isLawful);
        add("The strongest are meant to rule. ", Alignment::isEvil);
        add("The natural world is more important than all the constructs of civilization.",
            Alignment::isNeutral);
        add("I must earn glory in battle, for myself and my clan.");

        type = BOND;
        add("My family, clan, or tribe is the most important thing in my life, even when they "
            + "are far from me.");
        add("An injury to the unspoiled wilderness of my home is an injury to me.");
        add("I will bring terrible wrath down on the evildoers who destroyed my homeland.");
        add("I am the last of the trip, and it is up to me to ensure their names enter legend.");
        add("I suffer awful visions of a coming disaster and will do anything to prevent it.");
        add("It is my duty to provide children to sustain my tribe.");

        type = FLAW;
        add("I am too enamoured of ale, wine and other intoxicants.");
        add("There's no room for caution in a life lived to the fullest.");
        add("I remember every insult I've received and nurse a silent resentment towards anyone "
            + "who's every wronged me.");
        add("I am slow to trust members of other races, tribes, and societies.");
        add("Violence is my answer to almost any challenge.");
        add("Don't expect me to save those who can't save themselves. It is nature's way that "
            + "the strong thrive and the weak perish.");

        background = Background.SAGE;
        type = TRAIT;
        add("I use polysyllabic words that convey the impression of great erudition.");
        add("I’ve read every book in the world’s greatest libraries — "
            + "or I like to boast that I have.");
        add("I’m used to helping out those who aren’t as smart as I am, "
            + "and I patiently explain anything and everything to others.");
        add("There’s nothing I like more than a good mystery.");
        add("I’m willing to listen to every side of an argument before I make my own judgment.");
        add("I ... speak ... slowly ... when talking ... to idiots, "
            + "... which ... almost ... everyone ... is ... compared ... to me.");
        add("I am horribly, horribly awkward in social situations.");
        add("I’m convinced that people are always trying to steal my secrets.");

        type = IDEAL;
        add("The path to power and self-improvement is through knowledge.", Alignment::isNeutral);
        add("What is beautiful points us beyond itself toward what is true.", Alignment::isGood);
        add("Emotions must not cloud our logical thinking. ", Alignment::isLawful);
        add("Nothing should fetter the in nite possibility inherent in all existence.",
            Alignment::isChaotic);
        add("Knowledge is the path to power and domination.", Alignment::isEvil);
        add("The goal of a life of study is the betterment of oneself.");

        type = BOND;
        add("It is my duty to protect my students.");
        add("I have an ancient text that holds terrible secrets "
            + "that must not fall into the wrong hands.");
        add("I work to preserve a library, university, scriptorium, or monastery.");
        add("My life’s work is a series of tomes related to a specific field of lore.");
        add("I’ve been searching my whole life for the answer to a certain question.");
        add("I sold my soul for knowledge. I hope to do great deeds and win it back.");

        type = FLAW;
        add("I am easily distracted by the promise of information.");
        add("Most people scream and run when they see a demon. "
            + "I stop and take notes on its anatomy.");
        add("Unlocking an ancient mystery is worth the price of a civilization.");
        add("I overlook obvious solutions in favor of complicated ones.");
        add("I speak without really thinking through my words, invariably insulting others.");
        add("I can’t keep a secret to save my life, or anyone else’s.");

        background = Background.SAILOR;
        type = TRAIT;
        add("");

        type = IDEAL;
        add("");

        type = BOND;
        add("");

        type = FLAW;
        add("");

        background = Background.SOLDIER;
        type = TRAIT;
        add("I’m always polite and respectful.");
        add("I’m haunted by memories of war. I can’t get the images of violence out of my mind.");
        add("I’ve lost too many friends, and I’m slow to make new ones.");
        add("I’m full of inspiring and cautionary tales from my military experience relevant "
            + "to almost every combat situation.");
        add("I can stare down a hell hound without flinching.");
        add("I enjoy being strong and like breaking things.");
        add("I have a crude sense of humor.");

        type = IDEAL;
        add("Our lot is to lay down our lives in defense of others.", Alignment::isGood);
        add("I do what I must and obey just authority.", Alignment::isLawful);
        add("When people follow orders blindly, they embrace a kind of tyranny.",
            Alignment::isChaotic);
        add("In life as in war, the stronger force wins.", Alignment::isEvil);
        add("Ideals aren’t worth killing over or going to war for.", Alignment::isNeutral);
        add("My city, nation, or people are all that matter", Alignment::isNeutral);

        type = BOND;
        add("I would still lay down my life for the people I served with.");
        add("Someone saved my life on the battle field. "
            + "To this day, I will never leave a friend behind.");
        add("My honor is my life.");
        add("I’ll never forget the crushing defeat my company suffered "
            + "or the enemies who dealt it.");
        add("Those who  ght beside me are those worth dying for.");
        add("I fight for those who cannot fight for themselves.");

        type = FLAW;
        add("The monstrous enemy we faced in battle still leaves me quivering with fear.");
        add("I have little respect for anyone who is not a proven warrior.");
        add("I made a terrible mistake in battle that cost many lives "
            + "and I would do anything to keep that mistake secret.");
        add("My hatred of my enemies is blind and unreasoning.");
        add("I obey the law even if the law causes misery.");
        add("I’d rather eat my armor than admit when I’m wrong.");

        background = Background.URCHIN;
        type = TRAIT;
        add("");

        type = IDEAL;
        add("");

        type = BOND;
        add("");

        type = FLAW;
        add("");

    }

    private void add(String description) {
        add(description, al -> true);
    }

    private void add(String description, Predicate<Alignment> condition) {
        characteristics.computeIfAbsent(background, b -> new EnumMap<>(AttributeType.class));
        characteristics.get(background).computeIfAbsent(type, t -> new ArrayList<>());
        characteristics.get(background).get(type).add(new Characteristic(type, description,
            ch -> ch.hasAttribute(ALIGNMENT) && condition.test(ch.getAttribute(ALIGNMENT))));
    }

    public void generate(Character character) {
        Stream.of(TRAIT, BOND, IDEAL, FLAW)
            .map(type -> generateOfType(character, type))
            .filter(Optional::isPresent).map(Optional::get)
            .forEach(character::addAttribute);
    }

    private Optional<Attribute> generateOfType(Character character, AttributeType type) {
        List<Attribute> potential = characteristics
            .get(character.getAttribute(AttributeType.BACKGROUND, Background.class))
            .get(type)
            .stream()
            .filter(ch -> ch.condition.test(character))
            .map(Characteristic::toAttribute)
            .collect(toList());
        if (potential.isEmpty())
            return Optional.empty();
        else
            return Optional.of(potential.get(random.nextInt(potential.size())));
    }
}
