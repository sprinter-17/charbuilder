package characterbuilder.ui.choice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Optional;
import static java.util.stream.Collectors.joining;
import java.util.stream.Stream;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OptionPanel extends JPanel {

    private final String name;
    private final Runnable selectAction;
    private final Optional<JLabel> description;
    private boolean selected = false;

    public OptionPanel(String name, Stream<String> description,
        Runnable selectAction, SelectionAction action) {
        super(new BorderLayout());
        this.name = name;
        String descriptionText = description.collect(joining("<br>"));
        if (descriptionText.isEmpty())
            this.description = Optional.empty();
        else
            this.description = Optional.
                of(new JLabel("<html><div style='width:130px'>" + descriptionText + "</div></html>"));
        this.selectAction = selectAction;
        JLabel label = new JLabel("<html><em>" + name.replaceAll(", ", "<br>") + "</em></html>");
        setBackground(Color.WHITE);
        add(label, BorderLayout.CENTER);
        setBorder(BorderFactory.createEtchedBorder());
        addMouseListener(optionMouseListener(action));
    }

    private MouseListener optionMouseListener(SelectionAction action) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    selectAction.run();
                } else {
                    for (Component child : getParent().getComponents()) {
                        if (child instanceof OptionPanel)
                            ((OptionPanel) child).setSelected(action, child == OptionPanel.this);
                    }
                }
            }
        };
    }

    private void setSelected(SelectionAction action, boolean selected) {
        if (this.selected == selected)
            return;
        this.selected = selected;
        if (selected) {
            setBackground(Color.LIGHT_GRAY);
            action.setSelectAction("Select " + name, selectAction);
            description.ifPresent(label -> add(label, BorderLayout.SOUTH));
        } else {
            setBackground(Color.WHITE);
            description.ifPresent(this::remove);
        }
        revalidate();
        repaint();
    }
}
