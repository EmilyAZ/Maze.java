package Controller;

import Model.*;
import View.RoomPanel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public final class RoomController implements PropertyChangeListener {
    private static final String LOSEMESSAGE = "You lost game will now close";
    private final Maze myMaze;
    private final RoomPanel myRoomPanel;

    public RoomController(final Maze theMaze, final RoomPanel theRoomPanel) {
        if(theMaze == null){
            throw new IllegalArgumentException("maze cannot be null");
        }
        if(theRoomPanel == null){
            throw new IllegalArgumentException("room panel cannot be null");
        }
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
        Room room = myMaze.getCurrentRoom();
        Door door = room.getLeftDoor();
        Questions doorQuestion = door.getMyQuestion();
        try {
            if ((!door.getMyCanPass()) && (!door.getMyDoorLocked())){
                String answer = displayQuestion(doorQuestion);
                if (doorQuestion.checkAnswer(answer)){
                    myMaze.moveLeft();
                    door.setMyCanPass(true);
                } else {
                    door.setMyDoorLocked(true);
                    myRoomPanel.updateDoorState(door);
                    if(room.allDoorsLocked()){
                        displayLose();
                    }
                }
            } else if(door.getMyCanPass()){
                myMaze.moveLeft();
            }
        } catch (final IllegalArgumentException theException) {
        }
    }
    private void rightDoorAction(final ActionEvent theEvent) {
        Room room = myMaze.getCurrentRoom();
        Door door = room.getRightDoor();
        Questions doorQuestion = door.getMyQuestion();
        try {
            if ((!door.getMyCanPass()) && (!door.getMyDoorLocked())){
                String answer = displayQuestion(doorQuestion);
                if (doorQuestion.checkAnswer(answer)){
                    myMaze.moveRight();
                    door.setMyCanPass(true);
                } else {
                    door.setMyDoorLocked(true);
                    myRoomPanel.updateDoorState(door);
                    if(room.allDoorsLocked()){
                        displayLose();
                    }
                }
            } else if (door.getMyCanPass()){
                myMaze.moveRight();
            }
        } catch (final IllegalArgumentException theException) {
        }
    }
    private void bottomDoorAction(final ActionEvent theEvent) {
        Room room = myMaze.getCurrentRoom();
        Door door = room.getBottomDoor();
        Questions doorQuestion = door.getMyQuestion();
        try {
            if ((!door.getMyCanPass()) && (!door.getMyDoorLocked())){
                String answer = displayQuestion(doorQuestion);
                if (doorQuestion.checkAnswer(answer)){
                    myMaze.moveDown();
                    door.setMyCanPass(true);
                } else {
                    door.setMyDoorLocked(true);
                    myRoomPanel.updateDoorState(door);
                    if(room.allDoorsLocked()){
                        displayLose();
                    }
                }
            } else if(door.getMyCanPass()){
                myMaze.moveDown();
            }
        } catch (final IllegalArgumentException theException) {
        }

    }
    private void topDoorAction(final ActionEvent theEvent) {
        Room room = myMaze.getCurrentRoom();
        Door door = room.getTopDoor();
        Questions doorQuestion = door.getMyQuestion();
        try {
            if ((!door.getMyCanPass()) && (!door.getMyDoorLocked())){
                String answer = displayQuestion(doorQuestion);
                if (doorQuestion.checkAnswer(answer)){
                    myMaze.moveUp();
                    door.setMyCanPass(true);
                } else {
                    door.setMyDoorLocked(true);
                    myRoomPanel.updateDoorState(door);
                    if(room.allDoorsLocked()){
                        displayLose();
                    }
                }
            } else if(door.getMyCanPass()){
                myMaze.moveUp();
            }
        } catch (final IllegalArgumentException theException) {
        }
    }
    public void updateView() {
        myRoomPanel.updateRoomState(myRoomPanel.getRoom());
    }
    private String displayQuestion(Questions theDoorQuestion){
        String userAnswer;
        if(theDoorQuestion.getType().equals("True/False")){
            int choice = JOptionPane.showConfirmDialog(myRoomPanel,theDoorQuestion.getQuestionText());
            if (choice == JOptionPane.YES_OPTION){
                userAnswer = "True";
            } else {
                userAnswer = "False";
            }
        } else if (theDoorQuestion.getType().equals("Short Answer")){
            userAnswer = JOptionPane.showInputDialog(myRoomPanel, theDoorQuestion.getQuestionText());
        } else if (theDoorQuestion.getType().equals("Multiple Choice")){
            JComboBox<String> choiceList = new JComboBox<>(theDoorQuestion.getChoices());
            choiceList.setSelectedIndex(2);
            JOptionPane.showMessageDialog(myRoomPanel,choiceList,theDoorQuestion.getQuestionText(),JOptionPane.QUESTION_MESSAGE);
            userAnswer = String.valueOf(choiceList.getSelectedIndex());
        } else {
            userAnswer = null;
        }
        return userAnswer;
    }
    private void displayLose(){
        JOptionPane.showConfirmDialog(myRoomPanel, LOSEMESSAGE,null,JOptionPane.DEFAULT_OPTION);
        System.exit(0);
    }
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if ("Room Change".equals(theEvent.getPropertyName())) {
            updateView();
        }
        if ("Door Change".equals(theEvent.getPropertyName())) {
            Door changedDoor = (Door) theEvent.getNewValue();
            myRoomPanel.updateDoorState(changedDoor);
        }
    }
}
