package Model;

import java.awt.*;

public class Maze {
    private static final int MIN_ROOMS = 4;
    private static final int TWO_DOOR_ROOM = 2;
    private static final int THREE_DOOR_ROOM = 3;
    private static final int FOUR_DOOR_ROOM = 4;
    private final Room[][] myMaze;
    private final Point myExit;
    private final Point myCurrentRoom;
    private final int myMazeRows;
    private final int myMazeColumns;

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
    }

    private void createMaze(){
        for(int i = 0; i < myMazeRows; i++){
            for(int j = 0; j < myMazeColumns; j++){
                if(((i == 0) && (j == 0 || j+1 ==myMazeColumns))
                        || ((i+1 == myMazeRows)&& (j == 0 || j+1 ==myMazeColumns))){ // if top/bottom row and left and right edges
                    myMaze[i][j] = new Room(TWO_DOOR_ROOM);
                }
                else if((i > 0 && i+1 < myMazeRows) && (j == 0 || j+1 == myMazeColumns)
                        ||((i == 0 || i + 1 == myMazeRows) && (j > 0 && j +1 < myMazeColumns))){ //if edge room not a corner
                    myMaze[i][j] = new Room(THREE_DOOR_ROOM);
                }
                else{
                    myMaze[i][j] = new Room(FOUR_DOOR_ROOM);// any room surrounded by other rooms
                }

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
        myCurrentRoom.setLocation(theXCord,theYCord);
    }
    public Point getCurrentRoom(){
        return myCurrentRoom;
    }
    public Point getExit(){
        return myExit;
    }
    public int getMazeRows(){
        return myMazeRows;
    }
    public int getMazeColumns(){
        return myMazeColumns;
    }
}
