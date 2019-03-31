package characterbuilder.ui.choice;

import characterbuilder.character.Character;
import characterbuilder.character.choice.Option;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Optional;
import static java.util.stream.Collectors.joining;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OptionPanel extends JPanel {

    private final String name;
    private final Runnable selectAction;
    private final Optional<JLabel> description;
    private boolean selected = false;

    public OptionPanel(Option option, Character character,
        Runnable selectAction, SelectionAction action, int width) {
        super(new BorderLayout());
        this.name = option.getOptionName();
        this.description = getDescriptionLabel(option, character, width);
        this.selectAction = selectAction;
        addNameLabel(width);
        setBorder(BorderFactory.createEtchedBorder());
        addMouseListener(optionMouseListener(action));
        setBackground(Color.WHITE);
    }

    private void addNameLabel(int width) {
        JLabel label = label(widthText(width, element("em", name.replaceAll(", ", "<br>"))));
        add(label, BorderLayout.CENTER);
    }

    private MouseListener optionMouseListener(SelectionAction action) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2)
                    action.perform();
                else
                    showSelection(action);
            }
        };
    }

    private void showSelection(SelectionAction action) {
        for (Component child : getParent().getComponents()) {
            if (child instanceof OptionPanel)
                ((OptionPanel) child).setSelected(action, child == OptionPanel.this);
        }
    }

    private Optional<JLabel> getDescriptionLabel(Option option, Character character, int width) {
        String descriptionText = option.getDescription(character).collect(joining("<br>"));
        if (descriptionText.isEmpty())
            return Optional.empty();
        else
            return Optional.of(label(widthText(width, descriptionText)));
    }

    private JLabel label(String text) {
        return new JLabel(element("html", text));
    }

    private String element(String tag, String content) {
        return String.format("<%s>%s</%s>", tag, content, tag);
    }

    private String widthText(int width, String element) {
        return String.format("<div style='width:%dpx'>%s</div>", width, element);
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
