package characterbuilder.sheet;

import characterbuilder.character.Character;
import static characterbuilder.character.attribute.AttributeType.SPELLCASTING;
import characterbuilder.character.spell.LearntSpell;
import characterbuilder.character.spell.SpellCasting;
import java.util.ArrayList;
import static java.util.Comparator.comparingInt;
import java.util.List;
import java.util.stream.Stream;

public class SpellPage extends Page {

    private final List<LearntSpell> spells = new ArrayList<>();
    private final SpellCasting casting;

    public static Stream<PageBuilder.Container> getPages(Character character) {
        return character.getAttributes(SPELLCASTING, SpellCasting.class)
            .map(sc -> new SpellPage(character, sc))
            .flatMap(SpellPage::getPages);
    }

    public SpellPage(Character character, SpellCasting casting) {
        super(character);
        this.casting = casting;
        casting.getLearntSpells()
            .sorted(comparingInt(LearntSpell::getLevel))
            .forEach(spells::add);
    }

    @Override
    public Stream<PageBuilder.Container> getPages() {
        List<PageBuilder.Container> pages = new ArrayList<>();
        TextSectionBuilder sectionBuilder = new TextSectionBuilder(character, null, 100, 80);
        sectionBuilder.addSpells(spells);
        pages.add(builder.page()
            .with(ability(), saveDC(), attackBonus(), spellSlots(), preparedSpells(),
                sectionBuilder.getSection(0, 20)));
        while (!spells.isEmpty()) {
            sectionBuilder.createNewSection(100, 100);
            sectionBuilder.addSpells(spells);
            pages.add(builder.page().with(sectionBuilder.getSection(0, 0)));
        }
        return pages.stream();
    }

    private PageBuilder.Component ability() {
        return builder.borderedSection(0, 0, 34, 6)
            .with(builder.caption("Spellcasting Ability", 17, 4, PageBuilder.Align.CENTRE))
            .with(builder.value(casting.getAbilityScore().toString(),
                17, 2, PageBuilder.Align.CENTRE));
    }

    private PageBuilder.Component saveDC() {
        int saveDC = casting.getSaveDC(character);
        return builder.borderedSection(34, 0, 22, 6)
            .with(builder.caption("Spell Save DC", 11, 4, PageBuilder.Align.CENTRE))
            .with(builder.value(saveDC, 11, 2, PageBuilder.Align.CENTRE));
    }

    private PageBuilder.Component attackBonus() {
        int attackBonus = casting.getModifier(character);
        return builder.borderedSection(56, 0, 22, 6)
            .with(builder.caption("Spell Attack Bonus", 11, 4, PageBuilder.Align.CENTRE))
            .with(builder.value(attackBonus, 11, 2, PageBuilder.Align.CENTRE));
    }

    private PageBuilder.Component preparedSpells() {
        String preparedSpells = casting.getPreparedSpellText(character);
        return builder.borderedSection(78, 0, 22, 6)
            .with(builder.caption("Prepared Spells", 11, 4, PageBuilder.Align.CENTRE))
            .with(builder.value(preparedSpells, 11, 2, PageBuilder.Align.CENTRE));
    }

    private PageBuilder.Component spellSlots() {
        final int rows = 3;
        PageBuilder.Container spellSlotPanel = builder.borderedSection(0, 6, 100, 14)
            .with(builder.caption("Spell Slots", 50, 12, PageBuilder.Align.CENTRE));
        for (int l = 1; l <= casting.getMaxSlot(); l++) {
            int yp = (l - 1) % rows * 3 + 3;
            int xp = 30 * ((l - 1) / rows) + 5;
            spellSlotPanel
                .with(builder.caption("Level " + l, xp, yp, PageBuilder.Align.CENTRE_LEFT));
            for (int s = 0; s < casting.getSlotsAtLevel(l); s++) {
                spellSlotPanel.with(builder.circle(xp + 12 + s * 4, yp, 2));
            }
        }
        return spellSlotPanel;
    }
}
