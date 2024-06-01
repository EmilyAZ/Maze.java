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
    private final Maze myMaze;
    private final RoomPanel myRoomPanel;

    public RoomController(Maze maze, RoomPanel roomPanel) {
        myMaze = maze;
        myRoomPanel = roomPanel;
        myMaze.addPropertyChangeListener(this);
        addDoorListeners();

    }
    private void addDoorListeners(){
        Room room = myRoomPanel.getRoom();
        addDoorListener(room.getLeftDoor(), this::LeftDoorAction);
        addDoorListener(room.getRightDoor(), this::RightDoorAction);
        addDoorListener(room.getBottomDoor(), this::BottomDoorAction);
        addDoorListener(room.getTopDoor(), this::TopDoorAction);
    }
    private void addDoorListener(Door door, ActionListener theAction) {
        if (door != null) {
            JButton doorButton = myRoomPanel.getDoorButton(door);
            if (doorButton != null) {
                doorButton.addActionListener(theAction);
            }
        }
    }

    private void LeftDoorAction(final ActionEvent theEvent) {
        try {
            if(myMaze.getCurrentRoom().allDoorsLocked()){
                JOptionPane.showConfirmDialog(myRoomPanel,"you lose!");
            }else {
                myMaze.moveLeft();
            }
        } catch (IllegalArgumentException theException) {
            JOptionPane.showMessageDialog(myRoomPanel, "Unable to move out of bounds");
        }
    }
    private void RightDoorAction(final ActionEvent theEvent){
        try{
            if(myMaze.getCurrentRoom().allDoorsLocked()){
                JOptionPane.showConfirmDialog(myRoomPanel,"you lose!");
            }else{
                myMaze.moveRight();
            }
        } catch (IllegalArgumentException theException){
            JOptionPane.showMessageDialog(myRoomPanel, "Unable to move out of bounds");
        }
    }
    private void BottomDoorAction(final ActionEvent theEvent){
        try{
            if(myMaze.getCurrentRoom().allDoorsLocked()){
                JOptionPane.showConfirmDialog(myRoomPanel,"you lose!");
            }else{
                myMaze.moveDown();
            }
        } catch (IllegalArgumentException theException){
            JOptionPane.showMessageDialog(myRoomPanel, "Unable to move out of bounds");
        }

    }
    private void TopDoorAction(final ActionEvent theEvent){
        try{
            if(myMaze.getCurrentRoom().allDoorsLocked()){
                JOptionPane.showConfirmDialog(myRoomPanel,"you lose!");
            }else{
                myMaze.moveUp();
            }
        } catch (IllegalArgumentException theException){
            JOptionPane.showMessageDialog(myRoomPanel, "Unable to move out of bounds");
        }
    }
    public void updateView() {
        myRoomPanel.updateRoomState(myRoomPanel.getRoom());
    }

    @Override
    public void propertyChange(PropertyChangeEvent theEvent) {
        if ("Room Change".equals(theEvent.getPropertyName())) {
            updateView();
        }
        if ("Door Change".equals(theEvent.getPropertyName())) {
            Door changedDoor = (Door) theEvent.getNewValue();
            myRoomPanel.updateDoorState(changedDoor);
        }
    }
}
