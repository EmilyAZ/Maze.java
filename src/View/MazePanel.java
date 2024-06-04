package View;

import Controller.RoomController;
import Model.Maze;
import Model.Room;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

public class MazePanel extends JPanel implements PropertyChangeListener {
    private final Maze myMaze;

    public MazePanel(final Maze theMaze) {
        super();
        myMaze = theMaze;
        layoutComponents();
        myMaze.addPropertyChangeListener(this);
    }
    private void layoutComponents() {
        setLayout(new GridLayout(myMaze.getMyMazeRows(), myMaze.getMyMazeColumns()));
        createRoomPanels();
    }
    private void createRoomPanels() {
        for (int row = 0; row < myMaze.getMyMazeRows(); row++) {
            for (int col = 0; col < myMaze.getMyMazeColumns(); col++) {
                final Room room = myMaze.getRoomInMaze(row, col);
                final RoomPanel roomPanel = new RoomPanel(room);
                final RoomController roomController = new RoomController(myMaze, roomPanel);
                roomController.updateView();
                add(roomPanel);
            }
        }
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if ("Room Change".equals(theEvent.getPropertyName())) {
            if (myMaze.getCurrentRoom().getMyExit()) {
                JOptionPane.showMessageDialog(this, "You Win!");
            }
        }
        if ("Maze Loaded".equals(theEvent.getPropertyName())) {
            removeAll();
            createRoomPanels();
            revalidate();
            repaint();
        }
    }
}
