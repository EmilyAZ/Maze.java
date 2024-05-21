package View;

import Model.Player;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

public class GameFrame extends JFrame implements PropertyChangeListener {
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    private static final int HORIZONTAL_MARGIN = 700;
    private static final int VERTICAL_MARGIN = 500;
    public GameFrame(){
        super("Trivia Maze Game");
        setupGUI();
    }

    private void setupGUI(){
        add(new MainMenuPanel());

        setSize(HORIZONTAL_MARGIN, VERTICAL_MARGIN);
        setLocation(SCREEN_SIZE.width / 2 - getWidth() / 2,
                SCREEN_SIZE.height / 2 - getHeight() / 2);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }
    private void addListeners() {

    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
