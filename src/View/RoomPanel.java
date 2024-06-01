package View;

import Model.Door;
import Model.Maze;
import Model.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RoomPanel extends JPanel implements PropertyChangeListener {
    private final Room myRoom;
    private final Map<Door, JButton> myDoorButtons;
    private final Maze myMaze;
    public RoomPanel(Room theRoom, Maze theMaze){
        super();
        myRoom = theRoom;
        myDoorButtons = new HashMap<>();
        myMaze = theMaze;
        layoutComponents();
        myMaze.addPropertyChangeListener(this);

    }
    private void layoutComponents(){
        createRoom();
    }
    private void createRoom(){
        setLayout(new BorderLayout());
        JButton topDoor = drawDoor(myRoom.getTopDoor(), "TOP");
        JButton leftDoor = drawDoor(myRoom.getLeftDoor(), "LEFT");
        JButton rightDoor = drawDoor(myRoom.getRightDoor(),"RIGHT");
        JButton bottomDoor = drawDoor(myRoom.getBottomDoor(), "BOTTOM");
        add(topDoor, BorderLayout.NORTH);
        add(leftDoor, BorderLayout.WEST);
        add(rightDoor, BorderLayout.EAST);
        add(bottomDoor, BorderLayout.SOUTH);
        if(myRoom == myMaze.getCurrentRoom()){
            setBackground(Color.YELLOW);
        } else if (myRoom == myMaze.getExit()) {
            setBackground(Color.WHITE);
            disableRoom();
        } else{
            setBackground(Color.GRAY);
            disableRoom();
        }
    }
    private JButton drawDoor(Door door, String position){
        if(myDoorButtons.get(door) != null){
            return myDoorButtons.get(door);
        }
        JButton doorButton = new JButton();
        doorButton.setPreferredSize(new Dimension(10,10));
        doorButton.setOpaque(true);

        if(door == null){
            doorButton.setBackground(Color.BLACK);
            doorButton.setEnabled(false);
        }
        else if(door.getDoorLocked()){
            doorButton.setBackground(Color.RED);
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
        try{
            myMaze.moveLeft();
        } catch (IllegalArgumentException theException){
            JOptionPane.showMessageDialog(this, "Unable to move out of bounds");
        }
    }
    private void RightDoorAction(final ActionEvent theEvent){
        try{
            myMaze.moveRight();
        } catch (IllegalArgumentException theException){
            JOptionPane.showMessageDialog(this, "Unable to move out of bounds");
        }
    }
    private void BottomDoorAction(final ActionEvent theEvent){
        try{
            myMaze.moveDown();
        } catch (IllegalArgumentException theException){
            JOptionPane.showMessageDialog(this, "Unable to move out of bounds");
        }

    }
    private void TopDoorAction(final ActionEvent theEvent){
        try{
            myMaze.moveUp();
        } catch (IllegalArgumentException theException){
            JOptionPane.showMessageDialog(this, "Unable to move out of bounds");
        }
    }
    private void disableRoom(){
        for(Component cp: getComponents()){
            cp.setEnabled(false);
        }
    }
    private void enableRoom(){
        for(Component cp: getComponents()){
            if(cp.getBackground() != Color.black) {
                cp.setEnabled(true);
            }
        }
    }
    private void changeDoorButton(Door theDoor){
        JButton changedButton = myDoorButtons.get(theDoor);
        if(theDoor.getDoorLocked()){
            changedButton.setBackground(Color.red);
        } else {
            changedButton.setBackground(Color.GREEN);
        }

    }
    @Override
    public void propertyChange(PropertyChangeEvent theEvent) {
        Room PreviousRoom = null;
        if(theEvent.getOldValue() instanceof Room){
            PreviousRoom = (Room) theEvent.getOldValue();
        }
        if("Room Change".equals(theEvent.getPropertyName())){
            if(myRoom == myMaze.getCurrentRoom()){
                setBackground(Color.YELLOW);
                enableRoom();
            }else if (myRoom == PreviousRoom){
                setBackground(Color.GRAY);
                disableRoom();
            }
        }
        if("Door Change".equals(theEvent.getPropertyName())){
            Door changedDoor;
            if(theEvent.getNewValue() instanceof Door){
                changedDoor = (Door) theEvent.getNewValue();
                if(myDoorButtons.get(changedDoor) != null){
                    changeDoorButton(changedDoor);
                }
            }
        }
    }
}
