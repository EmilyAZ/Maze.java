package Model;

public class Maze {
    private static final int MIN_ROOMS = 2;
    private static final int TWO_DOOR_ROOM = 2;
    private static final int THREE_DOOR_ROOM = 3;
    private static final int FOUR_DOOR_ROOM = 4;
    private Room[][] myMaze;
    private Room myEntrance;
    private Room myExit;
    private Room myCurrentRoom;
    private int myMazeRows;
    private int myMazeColumns;

    public Maze(final int theMazeRows, final int theMazeColumns) {
        if (theMazeRows < MIN_ROOMS || theMazeColumns < MIN_ROOMS) {
            throw new IllegalArgumentException("There must be at least 2 rows and 2 columns of rooms in maze");
        } else {
            myMazeRows = theMazeRows;
            myMazeColumns = theMazeColumns;
        }
        myMaze = new Room[myMazeRows][myMazeColumns];
    }
    public void createMaze(){
        for(int i = 0; i < myMazeRows; i++){
            for(int j = 0; j < myMazeColumns; j++){
                if(((i == 0) && (j == 0 || j+1 ==myMazeColumns))
                        || ((i+1 == myMazeRows)&& (j == 0 || j+1 ==myMazeColumns))){ // if top/bottom row and left and right edges
                    myMaze[i][j] = new Room(TWO_DOOR_ROOM);
                }
                else if((i > 0 && i+1 < myMazeRows) && (j == 0 || j+1 == myMazeColumns)
                        ||((i == 0 || i + 1 == myMazeRows) && (j > 0 && j +1 < myMazeColumns))){ //if a middle row and edge room
                    myMaze[i][j] = new Room(THREE_DOOR_ROOM);
                }
                else{
                    myMaze[i][j] = new Room(FOUR_DOOR_ROOM);
                }
            }
        }
    }
}
