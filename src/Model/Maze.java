package Model;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public final class Maze {
    private static final int MIN_ROOMS = 4;
    private final Room[][] myMaze;
    private final Room myExit;
    private Room myCurrentRoom;
    private Point myCurrentRoomLocation;
    private final int myMazeRows;
    private final int myMazeColumns;
    private final PropertyChangeSupport myChange;

    public Maze(final int theMazeRows, final int theMazeColumns) {
        if (theMazeRows < MIN_ROOMS || theMazeColumns < MIN_ROOMS) {
            throw new IllegalArgumentException("There must be at least 4 rows and 4 columns of rooms in maze");
        } else {
            myMazeRows = theMazeRows;
            myMazeColumns = theMazeColumns;
        }
        myMaze = new Room[myMazeRows][myMazeColumns];
        createMaze();
        myCurrentRoom = mazeEntrance();
        myExit = mazeExit();
        myChange = new PropertyChangeSupport(this);
    }
    private void createMaze(){
        for(int row = 0; row < myMazeRows;row++){
            for(int column = 0; column < myMazeColumns;column++){
                boolean topNeighbor = (row!=0);
                boolean bottomNeighbor = (row+1 != myMazeRows);
                boolean leftNeighbor = (column != 0);
                boolean rightNeighbor = (column + 1 != myMazeColumns);
                Door topDoor = null;
                Door bottomDoor = null;
                Door leftDoor = null;
                Door rightDoor = null;
                int doorCount = 0;
                while (doorCount == 0) {
                    if (topNeighbor && (randomBoolean() || myMaze[row - 1][column].getBottomDoor() != null)) {
                        topDoor = myMaze[row - 1][column].getBottomDoor();
                        if (topDoor != null) {
                            doorCount++;
                        }
                    }
                    if (leftNeighbor && (randomBoolean() || myMaze[row][column - 1].getRightDoor() != null)) {
                        leftDoor = myMaze[row][column - 1].getRightDoor();
                        if (leftDoor != null) {
                            doorCount++;
                        }
                    }
                    if (bottomNeighbor && (randomBoolean() || leftDoor == null)) {
                        bottomDoor = new Door(true);
                        doorCount++;
                    }

                    if (rightNeighbor && (randomBoolean() || bottomDoor == null)) {
                        rightDoor = new Door(true);
                        doorCount ++;
                    }
                }
                myMaze[row][column] = new Room(leftDoor, rightDoor,topDoor,bottomDoor);
            }
        }
    }
    private static boolean randomBoolean(){
        return Math.random() < 0.5;
    }

    private Room mazeEntrance(){
        int entranceCol = (int)(Math.random() * myMazeColumns);
        myCurrentRoomLocation = new Point(0,entranceCol);
        return myMaze[0][entranceCol];
    }
    private Room mazeExit(){
        int entranceCol = (int)(Math.random() * myMazeColumns);
        return myMaze[myMazeRows-1][entranceCol];
    }
    public void setCurrentRoom(final int theNewRow, final int theNewCol){
        if(theNewRow >= myMazeRows || theNewRow < 0 || theNewCol >= myMazeColumns || theNewCol < 0){
            throw new IllegalArgumentException("invalid coordinates out of bound for maze");
        }
        Room PreviousRoom = myCurrentRoom;
        myCurrentRoom = myMaze[theNewRow][theNewCol];
        myCurrentRoomLocation.setLocation(theNewRow,theNewCol);
        fireCurrentRoomChange(PreviousRoom);
    }
    public void moveLeft(){
        int newCol = myCurrentRoomLocation.y - 1;
        if(newCol < 0){
            throw new IllegalArgumentException("trying to move out of bounds");
        }
        Room PreviousRoom = myCurrentRoom;
        fireCurrentDoorChange(myCurrentRoom.getLeftDoor());
        setCurrentRoom(myCurrentRoomLocation.x, newCol);
        fireCurrentRoomChange(PreviousRoom);
    }
    public void moveRight(){
        int newCol = myCurrentRoomLocation.y + 1;
        if(newCol >= myMazeColumns){
            throw new IllegalArgumentException("trying to move out of bounds");
        }
        Room PreviousRoom = myCurrentRoom;
        fireCurrentDoorChange(myCurrentRoom.getRightDoor());
        setCurrentRoom(myCurrentRoomLocation.x,newCol);
        fireCurrentRoomChange(PreviousRoom);

    }
    public void moveUp(){
        int newRow = myCurrentRoomLocation.x - 1;
        if(newRow < 0){
            throw new IllegalArgumentException("trying to move out of bounds");
        }
        Room PreviousRoom = myCurrentRoom;
        fireCurrentDoorChange(myCurrentRoom.getTopDoor());
        setCurrentRoom(newRow, myCurrentRoomLocation.y);
        fireCurrentRoomChange(PreviousRoom);


    }
    public void moveDown(){
        int newRow = myCurrentRoomLocation.x + 1;
        if(newRow >= myMazeRows){
            throw new IllegalArgumentException("trying to move out of bounds");
        }
        Room PreviousRoom = myCurrentRoom;
        fireCurrentDoorChange(myCurrentRoom.getBottomDoor());
        setCurrentRoom(newRow, myCurrentRoomLocation.y);
        fireCurrentRoomChange(PreviousRoom);

    }
    public Room getCurrentRoom(){
        return myMaze[myCurrentRoomLocation.x][myCurrentRoomLocation.y];
    }
    public Room getExit(){
        return myExit;
    }
    public int getMyMazeRows(){
        return myMazeRows;
    }
    public int getMyMazeColumns(){
        return myMazeColumns;
    }
    public Room getRoomInMaze(int theRow, int theCol){
        return myMaze[theRow][theCol];
    }
    private void fireCurrentDoorChange(Door theDoor){
        myChange.firePropertyChange("Door Change", null, theDoor);
    }
    private void fireCurrentRoomChange(Room thePreviousRoom){
        myChange.firePropertyChange("Room Change", thePreviousRoom, myCurrentRoom);
    }
    public void addPropertyChangeListener(final PropertyChangeListener theListener){
        myChange.addPropertyChangeListener(theListener);
    }
}
