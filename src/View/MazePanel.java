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

    public MazePanel(Maze theMaze) {
        super();
        myMaze = theMaze;
        layoutComponents();
        myMaze.addPropertyChangeListener(this);
    }
    private void layoutComponents() {
        setLayout(new GridLayout(myMaze.getMyMazeRows(), myMaze.getMyMazeColumns()));
        createRoomPanels();
    }
    private void createRoomPanels(){
        for (int row = 0; row < myMaze.getMyMazeRows(); row++) {
            for (int col = 0; col < myMaze.getMyMazeColumns(); col++) {
                Room room = myMaze.getRoomInMaze(row, col);
                RoomPanel roomPanel = new RoomPanel(room);
                RoomController roomController = new RoomController(myMaze,roomPanel);
                roomController.updateView();
                add(roomPanel);
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent theEvent) {
        if("Room Change".equals(theEvent.getPropertyName())){
            if(myMaze.getCurrentRoom().getMyExit()){
                JOptionPane.showMessageDialog(this,"You Win!");
            }
        }
        if("Maze Loaded".equals(theEvent.getPropertyName())){
            removeAll();
            createRoomPanels();
            revalidate();
            repaint();
        }
    }
}
