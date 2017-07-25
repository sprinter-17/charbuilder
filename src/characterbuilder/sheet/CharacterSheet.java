package characterbuilder.sheet;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Spell;
import static characterbuilder.character.attribute.AttributeType.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

public class CharacterSheet extends JFrame {

    private final PageBuilder builder = new PageBuilder();
    private final JButton zoomInButton = new JButton("+");
    private final JButton zoomOutButton = new JButton("-");
    private final JButton previousButton = new JButton("<<");
    private final JButton nextButton = new JButton(">>");
    private final JButton printButton = new JButton("Print");
    private final JButton closeButton = new JButton("Close");
    private final ImagePanel imagePanel = new ImagePanel();
    private final EnumMap<Page, PageBuilder.Container> pages = new EnumMap(Page.class);

    private final MainPage mainPage;
    private final BackgroundPage backgroundPage;
    private final SpellPage spellPage;

    private int zoom = 1;
    private Page page = Page.ATTRIBUTES;

    private enum Page {
        ATTRIBUTES,
        BACKGROUND,
        SPELLS;
    }

    private class ImagePanel extends JPanel {

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(builder.getWidth(zoom), builder.getHeight(zoom));
        }

        @Override
        public void paint(Graphics g) {
            pages.get(page).paint((Graphics2D) g, zoom);
        }
    }

    public CharacterSheet(Character character) {
        super("Character Sheet (" + character.getStringAttribute(NAME) + ")");
        this.mainPage = new MainPage(character);
        this.backgroundPage = new BackgroundPage(character);
        this.spellPage = new SpellPage(character);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        addTools();
        JScrollPane scroller = new JScrollPane(imagePanel);
        add(scroller, BorderLayout.CENTER);
        buildPages(character);
        enableButtons();
        pack();
    }

    private void addTools() {
        JToolBar tools = new JToolBar();
        tools.add(zoomInButton);
        tools.add(zoomOutButton);
        tools.add(previousButton);
        tools.add(nextButton);
        tools.add(printButton);
        tools.add(closeButton);
        zoomInButton.addActionListener(ev -> changeZoom(+1));
        zoomOutButton.addActionListener(ev -> changeZoom(-1));
        previousButton.addActionListener(ev -> movePage(-1));
        nextButton.addActionListener(ev -> movePage(+1));
        printButton.addActionListener(ev -> print());
        closeButton.addActionListener(ev -> setVisible(false));
        add(tools, BorderLayout.NORTH);
    }

    private void changeZoom(int inc) {
        zoom += inc;
        zoom = Math.min(zoom, 8);
        zoom = Math.max(zoom, 1);
        repaint();
        enableButtons();
    }

    private void movePage(int inc) {
        int pageIndex = page.ordinal() + inc;
        if (pageIndex >= 0 && pageIndex < pages.size())
            page = Page.values()[pageIndex];
        repaint();
        enableButtons();
    }

    private void enableButtons() {
        zoomInButton.setEnabled(zoom < 8);
        zoomOutButton.setEnabled(zoom > 1);
        previousButton.setEnabled(page != Page.ATTRIBUTES);
        nextButton.setEnabled(page != Page.SPELLS && pages.containsKey(Page.values()[page.ordinal() + 1]));
    }

    private void buildPages(Character character) {
        pages.put(Page.ATTRIBUTES, mainPage.getPage());
        pages.put(Page.BACKGROUND, backgroundPage.getPage());
        if (Arrays.stream(Spell.values()).anyMatch(character::hasAttribute))
            pages.put(Page.SPELLS, spellPage.getPage());
    }

    private void print() {
        try {
            PrinterJob print = PrinterJob.getPrinterJob();
            print.setPrintable((Graphics g, PageFormat pageFormat, int pageIndex) -> {
                if (pageIndex > 0)
                    return Printable.NO_SUCH_PAGE;
                pages.get(Page.ATTRIBUTES).paint((Graphics2D) g, 1);
                return Printable.PAGE_EXISTS;
            });
            if (print.printDialog())
                print.print();
        } catch (PrinterException ex) {
            Logger.getLogger(CharacterSheet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
