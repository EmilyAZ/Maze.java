package Model;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Objects;

public final class Maze {
    private static final int MIN_ROOMS = 4;
    private final Room[][] myMaze;
    private final Point myExit;
    private final Point myCurrentRoom;
    private final Point myPreviousRoom;
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
        myPreviousRoom = mazeEntrance();
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
    private Point mazeEntrance(){ //temporary entrance
        return new Point(0,0);
    }
    private Point mazeExit(){ //temporary exit
        return new Point(myMazeRows-1, myMazeColumns-1);
    }
    public void setCurrentRoom(final int theXCord, final int theYCord){
        if(theXCord >= myMazeColumns || theXCord < 0 || theYCord >= myMazeRows || theYCord < 0){
            throw new IllegalArgumentException("invalid coordinates out of bound for maze");
        }
        myPreviousRoom.setLocation(myCurrentRoom.x,myCurrentRoom.y);
        myCurrentRoom.setLocation(theXCord,theYCord);
        fireCurrentRoomChange();
    }
    public void moveLeft(){
        int newX = myCurrentRoom.x - 1;
        if(newX < 0){
            throw new IllegalArgumentException("trying to move out of bounds");
        }
        setCurrentRoom(newX,myCurrentRoom.y);
    }
    public void moveRight(){
        int newX = myCurrentRoom.x + 1;
        if(newX >= myMazeColumns){
            throw new IllegalArgumentException("trying to move out of bounds");
        }
        setCurrentRoom(newX,myCurrentRoom.y);
    }
    public void moveUp(){
        int newY = myCurrentRoom.y - 1;
        if(newY < 0){
            throw new IllegalArgumentException("trying to move out of bounds");
        }
        setCurrentRoom(myCurrentRoom.x,newY);
    }
    public void moveDown(){
        int newY = myCurrentRoom.y + 1;
        if(newY >= myMazeRows){
            throw new IllegalArgumentException("trying to move out of bounds");
        }
        setCurrentRoom(myCurrentRoom.x,newY);
    }
    public Point getCurrentRoom(){
        return myCurrentRoom;
    }
    public Room getCurrentRoomInstance(){
        return myMaze[myCurrentRoom.x][myCurrentRoom.y];
    }
    public Point getExit(){
        return myExit;
    }
    public int getMyMazeRows(){
        return myMazeRows;
    }
    public int getMyMazeColumns(){
        return myMazeColumns;
    }
    public Room getRoomInMaze(int row, int col){
        return myMaze[row][col];
    }
    private void fireCurrentRoomChange(){
        myChange.firePropertyChange("Room Change", myPreviousRoom, myCurrentRoom);
    }
    public void addPropertyChangeListener(final PropertyChangeListener theListener){
        myChange.addPropertyChangeListener(theListener);
    }
}
