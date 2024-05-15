package View;

import Model.Player;

import javax.swing.*;
import java.awt.*;

public final class MainMenuPanel extends JPanel {
    private static final Dimension FIELD_SIZE = new Dimension(100, 25);
    private final JTextField myNameField;
    private final JButton mySetName;
    private final JButton myStartButton;

    public MainMenuPanel(){
        super();
        myNameField = new JTextField();
        mySetName = new JButton("Confirm");
        myStartButton = new JButton("Start");
        layoutComponents();
    }
    private void layoutComponents(){
        add(namePanel());
        add(myStartButton);
    }
    private JPanel namePanel(){
        final JPanel namePanel = new JPanel();
        myNameField.setEditable(true);
        myNameField.setPreferredSize(FIELD_SIZE);
        mySetName.setEnabled(true);
        namePanel.add(new JLabel("Username"));
        namePanel.add(myNameField);
        namePanel.add(mySetName);
        return namePanel;
    }
}
