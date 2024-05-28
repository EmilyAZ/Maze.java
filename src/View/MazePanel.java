package View;

import Model.Door;
import Model.Maze;
import Model.Room;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class MazePanel extends JPanel implements PropertyChangeListener {
    private final Maze myMaze;
    private final Map<Door,JLabel> doorLabels;

    public MazePanel(Maze maze) {
        super();
        myMaze = maze;
        doorLabels = new HashMap<>();
        layoutComponents();
    }
    private void layoutComponents() {
        setLayout(new GridLayout(myMaze.getMyMazeRows(), myMaze.getMyMazeColumns()));
        for (int row = 0; row < myMaze.getMyMazeRows(); row++) {
            for (int col = 0; col < myMaze.getMyMazeColumns(); col++) {
                Room room = myMaze.getRoomInMaze(row, col);
                add(drawRoom(room, row,  col));
            }
        }
    }
    private JPanel drawRoom(Room room, int row, int col){
        // Create a sub-panel to represent the room
        JPanel roomPanel = new JPanel();
        roomPanel.setBackground(Color.GRAY);
        roomPanel.setLayout(new BorderLayout());
        JLabel label = new JLabel(String.valueOf(row)+ String.valueOf(col));
        JLabel topDoor = drawDoor(room.getTopDoor());
        JLabel leftDoor = drawDoor(room.getLeftDoor());
        JLabel rightDoor = drawDoor(room.getRightDoor());
        JLabel bottomDoor = drawDoor(room.getBottomDoor());
        roomPanel.add(topDoor, BorderLayout.NORTH);
        roomPanel.add(leftDoor, BorderLayout.WEST);
        roomPanel.add(rightDoor, BorderLayout.EAST);
        roomPanel.add(bottomDoor, BorderLayout.SOUTH);
        roomPanel.add(label,BorderLayout.CENTER);
        return roomPanel;
    }

    private JLabel drawDoor(Door door){
        if(doorLabels.get(door) != null){
            return doorLabels.get(door);
        }
        JLabel doorLabel = new JLabel();
        doorLabel.setPreferredSize(new Dimension(5,5));
        doorLabel.setOpaque(true);
        if(door == null){
            doorLabel.setBackground(Color.BLACK);
        }
        else if(door.getDoorLocked()){
            doorLabel.setBackground(Color.red);
            doorLabels.put(door,doorLabel);
        } else {
            doorLabel.setBackground(Color.GREEN);
            doorLabels.put(door,doorLabel);
        }
        return doorLabel;
    }
    private void updateDoorLabel(Room room){
        for(Door doors: room.getMyDoors().values()){
            JLabel doorLabel = new JLabel();
            doorLabel.setPreferredSize(new Dimension(5,5));
            doorLabel.setOpaque(true);
            if (doors.getDoorLocked()) {
                doorLabel.setBackground(Color.red);
                doorLabels.put(doors,doorLabel);
            } else {
                doorLabel.setBackground(Color.GREEN);
                doorLabels.put(doors,doorLabel);
            }

        }
    }
    @Override
    public void propertyChange(PropertyChangeEvent theEvent) {
        if("Room Change".equals(theEvent.getPropertyName())){
            updateDoorLabel(myMaze.getCurrentRoomInstance());
        }
    }
}
