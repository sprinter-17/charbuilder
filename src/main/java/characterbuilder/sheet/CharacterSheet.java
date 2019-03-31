package characterbuilder.sheet;

import characterbuilder.character.Character;
import static characterbuilder.character.attribute.AttributeType.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.List;
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
    private final List<PageBuilder.Container> pages = new ArrayList<>();
    private int page = 0;

    private int zoom = 1;

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
        page += inc;
        if (page < 0)
            page = 0;
        if (page >= pages.size())
            page = pages.size() - 1;
        repaint();
        enableButtons();
    }

    private void enableButtons() {
        zoomInButton.setEnabled(zoom < 8);
        zoomOutButton.setEnabled(zoom > 1);
        previousButton.setEnabled(page != 0);
        nextButton.setEnabled(page != pages.size() - 1);
    }

    private void buildPages(Character character) {
        MainPage.getPages(character).forEach(pages::add);
        BackgroundPage.getPages(character).forEach(pages::add);
        new BeastPage(character).getPages().forEach(pages::add);
        SpellPage spellPage = new SpellPage(character);
        spellPage.getPages().forEach(pages::add);
    }

    private void print() {
        try {
            PrinterJob print = PrinterJob.getPrinterJob();
            print.setPrintable((Graphics g, PageFormat pageFormat, int pageIndex) -> {
                if (pageIndex >= pages.size())
                    return Printable.NO_SUCH_PAGE;
                pages.get(pageIndex).paint((Graphics2D) g, 1);
                return Printable.PAGE_EXISTS;
            });
            if (print.printDialog())
                print.print();
        } catch (PrinterException ex) {
            Logger.getLogger(CharacterSheet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
