package View;

import Model.Door;
import Model.Maze;
import Model.Room;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.swing.*;

public class MazePanel extends JPanel implements PropertyChangeListener {
    private final Maze myMaze;
    private final Map<Door,JButton> myDoorButtons;
    private final Map<Room,JPanel> myRoomPanels;
    private final JLabel currentRoomLabel;

    public MazePanel(Maze maze) {
        super();
        myMaze = maze;
        myDoorButtons = new HashMap<>();
        myRoomPanels = new HashMap<>();
        currentRoomLabel = new JLabel("Current Room");
        layoutComponents();
    }
    private void layoutComponents() {
        setLayout(new GridLayout(myMaze.getMyMazeRows(), myMaze.getMyMazeColumns()));
        createRoomPanels();
    }
    private void createRoomPanels(){
        for (int row = 0; row < myMaze.getMyMazeRows(); row++) {
            for (int col = 0; col < myMaze.getMyMazeColumns(); col++) {
                JPanel roomPanel = new JPanel();
                Room room = myMaze.getRoomInMaze(row, col);
                roomPanel = drawRoom(room,row,col);
                add(roomPanel);
                myRoomPanels.put(room,roomPanel);
            }
        }
    }
    private JPanel drawRoom(Room room, int row, int col){
        // Create a sub-panel to represent the room
        JPanel roomPanel = new JPanel();
        roomPanel.setBackground(Color.GRAY);
        roomPanel.setLayout(new BorderLayout());
        JButton topDoor = drawDoor(room.getTopDoor(), "TOP");
        JButton leftDoor = drawDoor(room.getLeftDoor(), "LEFT");
        JButton rightDoor = drawDoor(room.getRightDoor(),"RIGHT");
        JButton bottomDoor = drawDoor(room.getBottomDoor(), "BOTTOM");
        roomPanel.add(topDoor, BorderLayout.NORTH);
        roomPanel.add(leftDoor, BorderLayout.WEST);
        roomPanel.add(rightDoor, BorderLayout.EAST);
        roomPanel.add(bottomDoor, BorderLayout.SOUTH);
        if(room == myMaze.getCurrentRoom()){
            roomPanel.add(currentRoomLabel, BorderLayout.CENTER);
        }
        return roomPanel;
    }

    private JButton drawDoor(Door door, String position){
        if(myDoorButtons.get(door) != null){
            return myDoorButtons.get(door);
        }
        JButton doorButton = new JButton();
        doorButton.setPreferredSize(new Dimension(5,5));
        doorButton.setOpaque(true);

        if(door == null){
            doorButton.setBackground(Color.BLACK);
            doorButton.setEnabled(false);
        }
        else if(door.getDoorLocked()){
            doorButton.setBackground(Color.red);
            myDoorButtons.put(door,doorButton);
        } else {
            doorButton.setBackground(Color.GREEN);
            myDoorButtons.put(door,doorButton);
        }
        if(Objects.equals(position, "LEFT")){
            doorButton.addActionListener(this::LeftDoorAction);
        } else if (Objects.equals(position,"RIGHT")) {
            doorButton.addActionListener(this::RightDoorAction);
        } else if (Objects.equals(position,"BOTTOM")) {
            doorButton.addActionListener(this::BottomDoorAction);
        } else if (Objects.equals(position,"TOP")) {
            doorButton.addActionListener(this::TopDoorAction);
        }
        return doorButton;
    }
    private void LeftDoorAction(final ActionEvent theEvent){
        myMaze.moveLeft();

    }
    private void RightDoorAction(final ActionEvent theEvent){
        myMaze.moveRight();

    }
    private void BottomDoorAction(final ActionEvent theEvent){
        myMaze.moveDown();
    }
    private void TopDoorAction(final ActionEvent theEvent){
        myMaze.moveUp();

    }

    private void updateDoorLabel(Room room){
        for(Door doors: room.getMyDoors().values()){
            JButton doorButton = myDoorButtons.get(doors);

            if (doors.getDoorLocked()) {
                doorButton.setBackground(Color.red);
                myDoorButtons.put(doors,doorButton);
            } else {
                doorButton.setBackground(Color.GREEN);
                myDoorButtons.put(doors,doorButton);
            }

        }
    }
    @Override
    public void propertyChange(PropertyChangeEvent theEvent) {
        if("Room Change".equals(theEvent.getPropertyName())){
            updateDoorLabel(myMaze.getCurrentRoom());
        }
    }
}
