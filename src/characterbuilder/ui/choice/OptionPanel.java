package characterbuilder.ui.choice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Optional;
import static java.util.stream.Collectors.joining;
import java.util.stream.Stream;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OptionPanel extends JPanel {

    private final String name;
    private final Runnable selectAction;
    private final JButton selectButton;
    private final ActionListener actionListener = ev -> choose();
    private final Optional<JLabel> description;
    private boolean selected = false;

    public OptionPanel(String name, Stream<String> description,
        Runnable selectAction, JButton selectButton) {
        super(new BorderLayout());
        this.name = name;
        String descriptionText = description.collect(joining("<br>"));
        if (descriptionText.isEmpty())
            this.description = Optional.empty();
        else
            this.description = Optional.
                of(new JLabel("<html><div style='width:130px'>" + descriptionText + "</div></html>"));
        this.selectAction = selectAction;
        this.selectButton = selectButton;
        JLabel label = new JLabel("<html><em>" + name.replaceAll(", ", "<br>") + "</em></html>");
        setBackground(Color.WHITE);
        add(label, BorderLayout.CENTER);
        setBorder(BorderFactory.createEtchedBorder());
        addMouseListener(optionMouseListener());
    }

    private MouseListener optionMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    choose();
                } else {
                    for (Component child : getParent().getComponents()) {
                        if (child instanceof OptionPanel)
                            ((OptionPanel) child).setSelected(child == OptionPanel.this);
                    }
                    selectButton.setAction(new AbstractAction("Select " + name) {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            choose();
                        }
                    });
                    selectButton.setEnabled(true);
                }
            }
        };
    }

    private void setSelected(boolean selected) {
        if (this.selected == selected)
            return;
        this.selected = selected;
        if (selected) {
            setBackground(Color.LIGHT_GRAY);
            selectButton.addActionListener(actionListener);
            description.ifPresent(label -> add(label, BorderLayout.SOUTH));
        } else {
            setBackground(Color.WHITE);
            selectButton.removeActionListener(actionListener);
            description.ifPresent(this::remove);
        }
        revalidate();
        repaint();
    }

    public void choose() {
        // the selected check is to overcome a bug
        // the action seems to be called twice when select button is clicked
        if (selected)
            selectAction.run();
        selected = false;
    }
}
