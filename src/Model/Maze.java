package Model;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;


public final class Maze implements Serializable {
    private static final String OUTOFBOUNDSMESSAGE = "Trying to move out of bounds";
    private static final long serialversionUID = 2345214345L;
    private static final String FILENAME = "savedMaze.txt";
    private static final int MIN_ROOMS = 4;
    private Room[][] myMaze;
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
    private void createMaze() {
        final int exitCol = (int) (Math.random() * myMazeColumns);
        final int entranceCol = (int) (Math.random() * myMazeColumns);
        for (int row = 0; row < myMazeRows; row++) {
            for (int column = 0; column < myMazeColumns; column++) {
                final boolean topNeighbor = row != 0;
                final boolean bottomNeighbor = row + 1 != myMazeRows;
                final boolean leftNeighbor = column != 0;
                final boolean rightNeighbor = column + 1 != myMazeColumns;
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
                if (column == exitCol && row == myMazeRows - 1) {
                    myMaze[row][column] = new Room(leftDoor, rightDoor, topDoor, bottomDoor, true, false);

                } else if (column == entranceCol && row == 0) {
                    myMaze[row][column] = new Room(leftDoor, rightDoor, topDoor, bottomDoor, false, true);
                    myCurrentRoom = myMaze[row][column];
                    myCurrentRoomLocation = new Point(row, column);
                } else {
                    myMaze[row][column] = new Room(leftDoor, rightDoor, topDoor, bottomDoor, false, false);
                }
            }
        }
    }
    private static boolean randomBoolean() {
        return Math.random() < 0.5;
    }


    private void moveRoom(final int theNewRow, final int theNewCol) {
        if (theNewRow >= myMazeRows || theNewRow < 0 || theNewCol >= myMazeColumns || theNewCol < 0) {
            throw new IllegalArgumentException("invalid coordinates out of bound for maze");
        }
        myCurrentRoom.setCurrentRoom(false);
        myCurrentRoom = myMaze[theNewRow][theNewCol];
        myCurrentRoom.setCurrentRoom(true);
        myCurrentRoomLocation.setLocation(theNewRow, theNewCol);
        fireCurrentRoomChange();
    }
    public void moveLeft() {
        final int newCol = myCurrentRoomLocation.y - 1;
        if (newCol < 0) {
            throw new IllegalArgumentException(OUTOFBOUNDSMESSAGE);
        }
        fireCurrentDoorChange(myCurrentRoom.getLeftDoor());
        moveRoom(myCurrentRoomLocation.x, newCol);
    }
    public void moveRight() {
        final int newCol = myCurrentRoomLocation.y + 1;
        if (newCol >= myMazeColumns) {
            throw new IllegalArgumentException(OUTOFBOUNDSMESSAGE);
        }
        fireCurrentDoorChange(myCurrentRoom.getRightDoor());
        moveRoom(myCurrentRoomLocation.x, newCol);


    }
    public void moveUp() {
        final int newRow = myCurrentRoomLocation.x - 1;
        if (newRow < 0) {
            throw new IllegalArgumentException(OUTOFBOUNDSMESSAGE);
        }
        fireCurrentDoorChange(myCurrentRoom.getTopDoor());
        moveRoom(newRow, myCurrentRoomLocation.y);


    }
    public void moveDown() {
        final int newRow = myCurrentRoomLocation.x + 1;
        if (newRow >= myMazeRows) {
            throw new IllegalArgumentException(OUTOFBOUNDSMESSAGE);
        }
        fireCurrentDoorChange(myCurrentRoom.getBottomDoor());
        moveRoom(newRow, myCurrentRoomLocation.y);
    }
    public Room getCurrentRoom() {
        return myMaze[myCurrentRoomLocation.x][myCurrentRoomLocation.y];
    }
    public int getMyMazeRows() {
        return myMazeRows;
    }
    public int getMyMazeColumns() {
        return myMazeColumns;
    }
    public Room getRoomInMaze(final int theRow, final int theCol) {
        if (theRow >= myMazeRows || theRow < 0 || theCol >= myMazeColumns || theCol < 0) {
            throw new IllegalArgumentException("invalid coordinates out of bound for maze");
        }
        return myMaze[theRow][theCol];
    }
    private void fireCurrentDoorChange(final Door theDoor) {
        myChange.firePropertyChange("Door Change", null, theDoor);
    }
    private void fireCurrentRoomChange() {
        myChange.firePropertyChange("Room Change", null, myCurrentRoom);
    }
    private void fireLoadRoomChange() {
        myChange.firePropertyChange("Maze Loaded", null, this);
    }
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myChange.addPropertyChangeListener(theListener);
    }
    public void saveMaze() {
        try {
            final FileOutputStream file = new FileOutputStream(FILENAME);
            final ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(this);
            out.close();
            file.close();
        } catch (final IOException theException) {
            System.out.println("IOException caught: " + theException.getMessage());
        }
    }

    public void loadMaze() {
        try {
            final FileInputStream file = new FileInputStream(FILENAME);
            final ObjectInputStream in = new ObjectInputStream(file);
            final Maze loadedMaze = (Maze) in.readObject();
            myMaze = loadedMaze.myMaze;
            myCurrentRoom = loadedMaze.myCurrentRoom;
            myCurrentRoomLocation = loadedMaze.myCurrentRoomLocation;
            fireLoadRoomChange();
            in.close();
            file.close();
        } catch (final IOException | ClassNotFoundException theException) {
            System.out.println("Exception caught: " + theException.getMessage());
        }
    }
}
