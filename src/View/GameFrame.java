package View;

import Model.Maze;

import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        Maze maze = new Maze(5,5);
        setTitle("Maze");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MazePanel mazePanel = new MazePanel(maze);
        add(mazePanel);
        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }
}
