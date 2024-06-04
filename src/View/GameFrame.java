package View;

import Controller.MenuBarController;
import Model.Maze;
import javax.swing.*;
import java.awt.*;

public final class GameFrame extends JFrame {
    private static final Dimension DIMENSION = new Dimension(500, 500);
    private static final int ROOMS = 5;
    private final GameMenuBar myGameMenu;
    private final MazePanel myMazePanel;
    private final Maze myMaze;

    public GameFrame() {
        myMaze = new Maze(ROOMS, ROOMS);
        setTitle("Maze");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myGameMenu = new GameMenuBar();
        myMazePanel = new MazePanel(myMaze);
        setSize(DIMENSION);
        setResizable(false);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
        setUpGUI();
    }
    private void setUpGUI() {
        new MenuBarController(myMaze, myGameMenu);
        setJMenuBar(myGameMenu);
        add(myMazePanel);
    }

}
