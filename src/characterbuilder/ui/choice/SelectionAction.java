package characterbuilder.ui.choice;

import java.awt.BorderLayout;
import java.util.Optional;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SelectionAction {

    private final JButton button = new JButton();
    private final Runnable postSelectionAction;
    private Optional<Runnable> selectionAction = Optional.empty();

    public SelectionAction(Runnable postSelectionAction) {
        this.postSelectionAction = postSelectionAction;
    }

    public void addButton(JPanel panel) {
        panel.add(button, BorderLayout.SOUTH);
        button.addActionListener(ev -> perform());
        clear();
    }

    public void setSelectAction(String name, Runnable action) {
        this.selectionAction = Optional.of(action);
        button.setText(name);
        button.setEnabled(true);
    }

    public void perform() {
        selectionAction.orElseThrow(IllegalStateException::new).run();
        postSelectionAction.run();
    }

    public void clear() {
        button.setText(null);
        button.setAction(null);
        button.setEnabled(false);
    }
}
