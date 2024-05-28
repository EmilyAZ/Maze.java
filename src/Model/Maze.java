package Model;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Objects;

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
                if (topNeighbor) {
                    Door door = myMaze[row - 1][column].getBottomDoor();
                    topDoor = Objects.requireNonNullElseGet(door, () -> new Door(true));
                }
                if (bottomNeighbor) {
                     bottomDoor = new Door(true);
                }
                if (leftNeighbor) {
                    Door door = myMaze[row][column - 1].getRightDoor();
                    leftDoor = Objects.requireNonNullElseGet(door, () -> new Door(true));
                }
                if (rightNeighbor){
                    rightDoor = new Door(true);
                }
                myMaze[row][column] = new Room(leftDoor, rightDoor,topDoor,bottomDoor);
            }
        }
    }
    private Room mazeEntrance(){ //temporary entrance
        myCurrentRoomLocation = new Point(0,0);
        return myMaze[0][0];
    }
    private Room mazeExit(){ //temporary exit
        return myMaze[myMazeRows-1][myMazeColumns-1];
    }
    public void setCurrentRoom(final int theNewRow, final int theNewCol){
        if(theNewRow >= myMazeRows || theNewRow < 0 || theNewCol >= myMazeColumns || theNewCol < 0){
            throw new IllegalArgumentException("invalid coordinates out of bound for maze");
        }
        myCurrentRoom = myMaze[theNewRow][theNewCol];
        myCurrentRoomLocation.setLocation(theNewRow,theNewCol);
        fireCurrentRoomChange();
    }
    public void moveLeft(){
        int newCol = myCurrentRoomLocation.y - 1;
        if(newCol < 0){
            throw new IllegalArgumentException("trying to move out of bounds");
        }
        setCurrentRoom(myCurrentRoomLocation.x, newCol);
        System.out.println("current Row: "+ myCurrentRoomLocation.x +"current col: "+ myCurrentRoomLocation.y);
    }
    public void moveRight(){
        int newCol = myCurrentRoomLocation.y + 1;
        if(newCol >= myMazeColumns){
            throw new IllegalArgumentException("trying to move out of bounds");
        }
        setCurrentRoom(myCurrentRoomLocation.x,newCol);
        System.out.println("current Row: "+ myCurrentRoomLocation.x +"current col: "+ myCurrentRoomLocation.y);

    }
    public void moveUp(){
        int newRow = myCurrentRoomLocation.x - 1;
        if(newRow < 0){
            throw new IllegalArgumentException("trying to move out of bounds");
        }
        setCurrentRoom(newRow, myCurrentRoomLocation.y);
        System.out.println("current Row: "+ myCurrentRoomLocation.x +"current col: "+ myCurrentRoomLocation.y);

    }
    public void moveDown(){
        int newRow = myCurrentRoomLocation.x + 1;
        if(newRow >= myMazeRows){
            throw new IllegalArgumentException("trying to move out of bounds");
        }
        setCurrentRoom(newRow, myCurrentRoomLocation.y);
        System.out.println("current Row: "+ myCurrentRoomLocation.x +"current col: "+ myCurrentRoomLocation.y);

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
    private void fireCurrentRoomChange(){
        myChange.firePropertyChange("Room Change", null, myCurrentRoomLocation);
    }
    public void addPropertyChangeListener(final PropertyChangeListener theListener){
        myChange.addPropertyChangeListener(theListener);
    }
}
