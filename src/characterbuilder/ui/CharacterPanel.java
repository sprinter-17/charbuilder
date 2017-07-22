package characterbuilder.ui;

import characterbuilder.character.Character;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;

public class CharacterPanel extends JPanel {

    private final DetailsPanel levelPanel;
    private final AbilityScorePanel abilityScorePanel;
    private final AppearancePanel appearancePanel;
    private final AbilityPanel abilityPanel;
    private final InventoryPanel inventoryPanel;
    private final PersonalityPanel personalityPanel;
    private final List<Runnable> changeListeners = new ArrayList<>();
    private final JLabel errors = new JLabel("Errors");
    private final GridBagConstraints c = new GridBagConstraints();

    public CharacterPanel() throws ParserConfigurationException {
        super(new BorderLayout());
        CharacterUpdater updater = new CharacterUpdater() {
            @Override
            public void update(Character character) {
                updateCharacterData(character);
            }

            @Override
            public void triggerChange() {
                CharacterPanel.this.triggerChange();
            }
        };
        this.levelPanel = new DetailsPanel(updater);
        this.abilityScorePanel = new AbilityScorePanel(updater);
        appearancePanel = new AppearancePanel(updater);
        abilityPanel = new AbilityPanel(updater);
        inventoryPanel = new InventoryPanel(updater);
        personalityPanel = new PersonalityPanel(updater);
        setUpPanels();
        setUpErrorLabel();
    }

    private void setUpPanels() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEtchedBorder());
        add(panel, levelPanel, 0, 0, 1, 1);
        add(panel, abilityScorePanel, 1, 0, 1, 1);
        c.weighty = 1.0;
        add(panel, appearancePanel, 0, 1, 2, 1);
        c.weighty = 0.0;
        add(panel, abilityPanel, 2, 0, 1, 2);
        add(panel, inventoryPanel, 3, 0, 1, 2);
        c.weighty = 1.0;
        add(panel, personalityPanel, 0, 2, 4, 1);
        add(panel, BorderLayout.CENTER);
    }

    private void add(JPanel parent, JPanel child, int x, int y, int width, int height) {
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = width;
        c.gridheight = height;
        c.fill = GridBagConstraints.BOTH;
        parent.add(child, c);
    }

    private void setUpErrorLabel() {
        errors.setForeground(Color.RED);
        errors.setPreferredSize(new Dimension(100, 50));
        errors.setBorder(BorderFactory.createEtchedBorder());
        add(errors, BorderLayout.SOUTH);
    }

    public void addChangeListener(Runnable listener) {
        changeListeners.add(listener);
    }

    public void updateCharacterData(Character character) {
        Stream.of(appearancePanel, abilityScorePanel,
            levelPanel, abilityPanel, inventoryPanel, personalityPanel)
            .forEach(panel -> panel.updateCharacter(character));
        triggerChange();
    }

    private void triggerChange() {
        changeListeners.forEach(Runnable::run);
    }
}
