package Controller;

import Model.Door;
import Model.Maze;
import Model.Room;
import View.RoomPanel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RoomController implements PropertyChangeListener {
    private static final String OUTBOUNDSMESSAGE = "Trying to move out of bounds";
    private static final String LOSEMESSAGE = "You Lose";
    private final Maze myMaze;
    private final RoomPanel myRoomPanel;

    public RoomController(final Maze theMaze, final RoomPanel theRoomPanel) {
        myMaze = theMaze;
        myRoomPanel = theRoomPanel;
        myMaze.addPropertyChangeListener(this);
        addDoorListeners();

    }
    private void addDoorListeners() {
        final Room room = myRoomPanel.getRoom();
        addDoorListener(room.getLeftDoor(), this::leftDoorAction);
        addDoorListener(room.getRightDoor(), this::rightDoorAction);
        addDoorListener(room.getBottomDoor(), this::bottomDoorAction);
        addDoorListener(room.getTopDoor(), this::topDoorAction);
    }
    private void addDoorListener(final Door theDoor, final ActionListener theAction) {
        if (theDoor != null) {
            final JButton doorButton = myRoomPanel.getDoorButton(theDoor);
            if (doorButton != null) {
                doorButton.addActionListener(theAction);
            }
        }
    }

    private void leftDoorAction(final ActionEvent theEvent) {
        try {
            if (myMaze.getCurrentRoom().allDoorsLocked()) {
                JOptionPane.showConfirmDialog(myRoomPanel, LOSEMESSAGE);
            } else {
                myMaze.moveLeft();
            }
        } catch (final IllegalArgumentException theException) {
            JOptionPane.showMessageDialog(myRoomPanel, OUTBOUNDSMESSAGE);
        }
    }
    private void rightDoorAction(final ActionEvent theEvent) {
        try {
            if (myMaze.getCurrentRoom().allDoorsLocked()) {
                JOptionPane.showConfirmDialog(myRoomPanel, LOSEMESSAGE);
            } else {
                myMaze.moveRight();
            }
        } catch (final IllegalArgumentException theException) {
            JOptionPane.showMessageDialog(myRoomPanel, OUTBOUNDSMESSAGE);
        }
    }
    private void bottomDoorAction(final ActionEvent theEvent) {
        try {
            if (myMaze.getCurrentRoom().allDoorsLocked()) {
                JOptionPane.showConfirmDialog(myRoomPanel, LOSEMESSAGE);
            } else {
                myMaze.moveDown();
            }
        } catch (final IllegalArgumentException theException) {
            JOptionPane.showMessageDialog(myRoomPanel, OUTBOUNDSMESSAGE);
        }

    }
    private void topDoorAction(final ActionEvent theEvent) {
        try {
            if (myMaze.getCurrentRoom().allDoorsLocked()) {
                JOptionPane.showConfirmDialog(myRoomPanel, LOSEMESSAGE);
            } else {
                myMaze.moveUp();
            }
        } catch (final IllegalArgumentException theException) {
            JOptionPane.showMessageDialog(myRoomPanel, OUTBOUNDSMESSAGE);
        }
    }
    public void updateView() {
        myRoomPanel.updateRoomState(myRoomPanel.getRoom());
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if ("Room Change".equals(theEvent.getPropertyName())) {
            updateView();
        }
        if ("Door Change".equals(theEvent.getPropertyName())) {
            final Door changedDoor = (Door) theEvent.getNewValue();
            myRoomPanel.updateDoorState(changedDoor);
        }
    }
}
