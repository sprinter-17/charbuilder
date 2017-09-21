package characterbuilder.sheet;

import characterbuilder.character.Character;
import static characterbuilder.character.attribute.AttributeType.SPELLCASTING;
import characterbuilder.character.spell.LearntSpell;
import characterbuilder.character.spell.SpellCasting;
import characterbuilder.character.spell.SpellSlots;
import java.util.ArrayList;
import java.util.Comparator;
import static java.util.Comparator.comparingInt;
import java.util.List;
import java.util.stream.Stream;

public class SpellPage extends Page {

    private static final Comparator<LearntSpell> SPELL_ORDER
        = comparingInt(LearntSpell::getLevel).thenComparing(ls -> ls.getSpell().toString());
    private int row = 0;
    private boolean firstPage;

    public SpellPage(Character character) {
        super(character);
    }

    @Override
    public Stream<PageBuilder.Container> getPages() {
        firstPage = true;
        List<PageBuilder.Container> pages = new ArrayList<>();
        character.getAttributes(SPELLCASTING, SpellCasting.class)
            .forEach(casting -> printSpellCasting(casting, pages));
        return pages.stream();
    }

    private void printSpellCasting(SpellCasting casting, List<PageBuilder.Container> pages) {
        row = 0;
        PageBuilder.Container page = builder.page();
        pages.add(page);
        if (firstPage)
            printSpellSlots(page);
        printCastingAttributes(page, casting);
        printLearntSpells(casting, page, pages);
    }

    private void printSpellSlots(PageBuilder.Container page) {
        page.with(spellSlots());
        row += 14;
        firstPage = false;
    }

    private PageBuilder.Component spellSlots() {
        final int rows = 3;
        PageBuilder.Container spellSlotPanel = builder.borderedSection(0, 0, 100, 14)
            .with(builder.caption("Spell Slots", 50, 12, PageBuilder.Align.CENTRE));
        for (int l = 1; l <= 9; l++) {
            int yp = (l - 1) % rows * 3 + 3;
            int xp = 30 * ((l - 1) / rows) + 5;
            spellSlotPanel
                .with(builder.caption("Level " + l, xp, yp, PageBuilder.Align.CENTRE_LEFT));
            for (int s = 0; s < SpellSlots.getSlotsAtLevel(character, l); s++) {
                spellSlotPanel.with(builder.circle(xp + 12 + s * 4, yp, 2));
            }
        }
        return spellSlotPanel;
    }

    private void printCastingAttributes(PageBuilder.Container page, SpellCasting casting) {
        page.with(ability(casting), saveDC(casting),
            attackBonus(casting), preparedSpells(casting));
        row += 6;
    }

    private PageBuilder.Component ability(SpellCasting casting) {
        return builder.borderedSection(0, row, 34, 6)
            .with(builder.caption("Spellcasting Ability", 17, 4, PageBuilder.Align.CENTRE))
            .with(builder.value(casting.getAbilityScore().toString(),
                17, 2, PageBuilder.Align.CENTRE));
    }

    private PageBuilder.Component saveDC(SpellCasting casting) {
        int saveDC = casting.getSaveDC(character);
        return builder.borderedSection(34, row, 22, 6)
            .with(builder.caption("Spell Save DC", 11, 4, PageBuilder.Align.CENTRE))
            .with(builder.value(saveDC, 11, 2, PageBuilder.Align.CENTRE));
    }

    private PageBuilder.Component attackBonus(SpellCasting casting) {
        int attackBonus = casting.getModifier(character);
        return builder.borderedSection(56, row, 22, 6)
            .with(builder.caption("Spell Attack Bonus", 11, 4, PageBuilder.Align.CENTRE))
            .with(builder.value(attackBonus, 11, 2, PageBuilder.Align.CENTRE));
    }

    private PageBuilder.Component preparedSpells(SpellCasting casting) {
        String preparedSpells = casting.getPreparedSpellText(character);
        return builder.borderedSection(78, row, 22, 6)
            .with(builder.caption("Prepared Spells", 11, 4, PageBuilder.Align.CENTRE))
            .with(builder.value(preparedSpells, 11, 2, PageBuilder.Align.CENTRE));
    }

    private void printLearntSpells(SpellCasting casting, PageBuilder.Container page,
        List<PageBuilder.Container> pages) {
        TextSectionBuilder sectionBuilder = new TextSectionBuilder(character, null, 100, 100 - row);
        List<LearntSpell> spells = getSpells(casting);
        sectionBuilder.addSpells(spells);
        page.with(sectionBuilder.getSection(0, row));
        while (!spells.isEmpty()) {
            sectionBuilder.createNewSection(100, 100);
            sectionBuilder.addSpells(spells);
            pages.add(builder.page().with(sectionBuilder.getSection(0, 0)));
        }
    }

    private List<LearntSpell> getSpells(SpellCasting casting) {
        List<LearntSpell> spells = new ArrayList<>();
        casting.getLearntSpells().sorted(SPELL_ORDER).forEach(spells::add);
        return spells;
    }
}
