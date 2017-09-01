package characterbuilder.ui.character;

import characterbuilder.character.Character;
import characterbuilder.ui.ability.AbilityPanel;
import characterbuilder.ui.ability.AbilityScorePanel;
import characterbuilder.ui.equipment.InventoryPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.xml.parsers.ParserConfigurationException;

public class CharacterPanel extends JPanel {

    private final DetailsPanel levelPanel;
    private final AbilityScorePanel abilityScorePanel;
    private final PicturePanel picturePanel;
    private final AppearancePanel appearancePanel;
    private final AbilityPanel abilityPanel;
    private final InventoryPanel inventoryPanel;
    private final PersonalityPanel personalityPanel;
    private final DescriptionPanel descriptionPanel;
    private final PersonalHistoryPanel historyPanel;
    private final List<Runnable> changeListeners = new ArrayList<>();
    private final JLabel errorLabel = new JLabel("Missing Attributes");
    private final JLabel errors = new JLabel();
    private final GridBagConstraints c = new GridBagConstraints();

    private Optional<Runnable> errorUpdater = Optional.empty();

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
        picturePanel = new PicturePanel(updater);
        appearancePanel = new AppearancePanel(updater);
        abilityPanel = new AbilityPanel(updater);
        inventoryPanel = new InventoryPanel(updater);
        personalityPanel = new PersonalityPanel(updater);
        descriptionPanel = new DescriptionPanel(updater);
        historyPanel = new PersonalHistoryPanel(updater);
        setUpPanels();
        setUpErrorLabel();
    }

    private void setUpPanels() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEtchedBorder());
        add(panel, levelPanel, 0, 0, 1, 1);
        add(panel, abilityScorePanel, 1, 0, 1, 1);
        c.weighty = 1.0;
        add(panel, picturePanel, 0, 1, 1, 1);
        add(panel, appearancePanel, 1, 1, 1, 1);
        c.weighty = 0.0;
        add(panel, abilityPanel, 2, 0, 1, 2);
        add(panel, inventoryPanel, 3, 0, 1, 2);
        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Personality", personalityPanel);
        tabs.add("Physical Description", descriptionPanel);
        tabs.add("History", historyPanel);
        c.weighty = 1.0;
        add(panel, tabs, 0, 2, 4, 1);
        add(panel, BorderLayout.CENTER);
    }

    private void add(JPanel parent, JComponent child, int x, int y, int width, int height) {
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = width;
        c.gridheight = height;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        parent.add(child, c);
    }

    private void setUpErrorLabel() {
        JPanel panel = new JPanel(new BorderLayout());
        errorLabel.setBorder(BorderFactory.createEmptyBorder(2, 15, 2, 15));
        panel.add(errorLabel, BorderLayout.WEST);
        panel.add(errors, BorderLayout.CENTER);
        errors.setBorder(BorderFactory.createEmptyBorder(2, 15, 2, 15));
        panel.setBorder(BorderFactory.createEtchedBorder());
        add(panel, BorderLayout.SOUTH);
    }

    public void addChangeListener(Runnable listener) {
        changeListeners.add(listener);
    }

    public void updateCharacterData(Character character) {
        Stream.of(appearancePanel, historyPanel, abilityScorePanel, descriptionPanel,
            picturePanel, levelPanel, abilityPanel, inventoryPanel, personalityPanel)
            .forEach(panel -> panel.updateCharacter(character));
        errorUpdater = Optional.of(() -> updateErrors(character));
        triggerChange();
    }

    private void updateErrors(Character character) {
        if (character.getErrors().isEmpty()) {
            errors.setText("None");
            errors.setForeground(Color.BLACK);
        } else {
            errors.setText(character.getErrors());
            errors.setForeground(Color.RED);
        }
    }

    private void triggerChange() {
        changeListeners.forEach(Runnable::run);
        errorUpdater.ifPresent(Runnable::run);
    }
}
