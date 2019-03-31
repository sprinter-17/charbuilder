package characterbuilder.ui.choice;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class DetailPanel extends JPanel {

    public DetailPanel() {
        super(new GridBagLayout());
    }

    public GridBagConstraints columnPosition(int column) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = column;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.ipady = 10;
        return constraints;
    }

    public void fill() {
        GridBagConstraints c = columnPosition(0);
        c.weighty = 1.0;
        add(new JPanel(), c);
    }
}
