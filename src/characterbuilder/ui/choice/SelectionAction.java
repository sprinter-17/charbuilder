package characterbuilder.ui.choice;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SelectionAction {

    private final JButton button = new JButton();

    public void addButton(JPanel panel) {
        panel.add(button, BorderLayout.SOUTH);
        clear();
    }

    public void setSelectAction(String name, Runnable action) {
        button.setAction(new AbstractAction(name) {
            @Override
            public void actionPerformed(ActionEvent e) {
                action.run();
                clear();
            }
        });
        button.setEnabled(true);
    }

    public void clear() {
        button.setText(null);
        button.setAction(null);
        button.setEnabled(false);
    }
}
