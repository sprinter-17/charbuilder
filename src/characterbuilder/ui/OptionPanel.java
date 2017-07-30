package characterbuilder.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Optional;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OptionPanel extends JPanel {

    private final Optional<String> description;
    private final Runnable selectAction;
    private final JButton selectButton;
    private final ActionListener actionListener = ev -> choose();
    private boolean selected = false;

    public OptionPanel(String name, Optional<String> description,
        Runnable selectAction, JButton selectButton) {
        super(new BorderLayout());
        this.description = description;
        this.selectAction = selectAction;
        this.selectButton = selectButton;
        JLabel label = new JLabel("<html>" + name + "</html>");
        setPreferredSize(new Dimension(190, 25));
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
            description.ifPresent(desc -> {
                setPreferredSize(new Dimension(190, 80));
                add(new JLabel(desc), BorderLayout.SOUTH);
            });
        } else {
            setBackground(Color.WHITE);
            selectButton.removeActionListener(actionListener);
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
