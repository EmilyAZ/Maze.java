package Model;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public final class Maze {
    private static final int MIN_ROOMS = 4;
    private final Room[][] myMaze;
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
        myChange = new PropertyChangeSupport(this);
    }
    private void createMaze(){
        int exitCol = (int)(Math.random() * myMazeColumns);
        int entranceCol = (int)(Math.random() * myMazeColumns);
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
                if (topNeighbor && (randomBoolean() || myMaze[row - 1][column].getBottomDoor() != null)) {
                    topDoor = myMaze[row - 1][column].getBottomDoor();
                }
                if (leftNeighbor && (randomBoolean() || myMaze[row][column - 1].getRightDoor() != null)) {
                    leftDoor = myMaze[row][column - 1].getRightDoor();
                }
                if (bottomNeighbor && (randomBoolean() || leftDoor == null)) {
                    bottomDoor = new Door(false);
                }

                if (rightNeighbor && (randomBoolean() || bottomDoor == null)) {
                    rightDoor = new Door(false);
                }
                if(column == exitCol && row == myMazeRows-1){
                    myMaze[row][column] = new Room(leftDoor, rightDoor,topDoor,bottomDoor,true,false);

                } else if (column == entranceCol && row == 0) {
                    myMaze[row][column] = new Room(leftDoor, rightDoor,topDoor,bottomDoor,false,true);
                    myCurrentRoom = myMaze[row][column];
                    myCurrentRoomLocation = new Point(row,column);
                } else{
                    myMaze[row][column] = new Room(leftDoor, rightDoor,topDoor,bottomDoor,false,false);
                }
            }
        }
    }
    private static boolean randomBoolean(){
        return Math.random() < 0.5;
    }


    private void moveRoom(final int theNewRow, final int theNewCol){
        if(theNewRow >= myMazeRows || theNewRow < 0 || theNewCol >= myMazeColumns || theNewCol < 0){
            throw new IllegalArgumentException("invalid coordinates out of bound for maze");
        }
        myCurrentRoom.setCurrentRoom(false);
        myCurrentRoom = myMaze[theNewRow][theNewCol];
        myCurrentRoom.setCurrentRoom(true);
        myCurrentRoomLocation.setLocation(theNewRow,theNewCol);
        fireCurrentRoomChange();
    }
    public void moveLeft(){
        int newCol = myCurrentRoomLocation.y - 1;
        if(newCol < 0){
            throw new IllegalArgumentException("trying to move out of bounds");
        }
        fireCurrentDoorChange(myCurrentRoom.getLeftDoor());
        moveRoom(myCurrentRoomLocation.x, newCol);
    }
    public void moveRight(){
        int newCol = myCurrentRoomLocation.y + 1;
        if(newCol >= myMazeColumns){
            throw new IllegalArgumentException("trying to move out of bounds");
        }
        fireCurrentDoorChange(myCurrentRoom.getRightDoor());
        moveRoom(myCurrentRoomLocation.x,newCol);


    }
    public void moveUp(){
        int newRow = myCurrentRoomLocation.x - 1;
        if(newRow < 0){
            throw new IllegalArgumentException("trying to move out of bounds");
        }
        fireCurrentDoorChange(myCurrentRoom.getTopDoor());
        moveRoom(newRow, myCurrentRoomLocation.y);


    }
    public void moveDown(){
        int newRow = myCurrentRoomLocation.x + 1;
        if(newRow >= myMazeRows){
            throw new IllegalArgumentException("trying to move out of bounds");
        }
        fireCurrentDoorChange(myCurrentRoom.getBottomDoor());
        moveRoom(newRow, myCurrentRoomLocation.y);
    }
    public Room getCurrentRoom(){
        return myMaze[myCurrentRoomLocation.x][myCurrentRoomLocation.y];
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
    private void fireCurrentRoomChange(){
        myChange.firePropertyChange("Room Change",null, myCurrentRoom);
    }
    public void addPropertyChangeListener(final PropertyChangeListener theListener){
        myChange.addPropertyChangeListener(theListener);
    }

}
