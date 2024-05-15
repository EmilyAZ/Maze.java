package Model;


import java.awt.*;

public class Player {
    private static final String DEFAULTNAME = "Player One";
    private final Maze myMaze;
    private String myName;
    private int myScore;
    public Player (Maze theMaze){
        myName = DEFAULTNAME;
        myScore = 0;
        myMaze = theMaze;
    }
    public void setName(final String theName){
        myName = theName;
    }
    public void moveLeft(){
        int newX = myMaze.getCurrentRoom().x - 1;
        if(newX < 0){
            throw new IllegalArgumentException("trying to move out of bounds");
        }
        myMaze.setCurrentRoom(newX, myMaze.getCurrentRoom().y);
    }
    public void moveRight(){
        int newX = myMaze.getCurrentRoom().x + 1;
        if(newX >= myMaze.getMazeColumns()){
            throw new IllegalArgumentException("trying to move out of bounds");
        }
        myMaze.setCurrentRoom(newX, myMaze.getCurrentRoom().y);
    }
    public void moveUp(){
        int newY = myMaze.getCurrentRoom().y - 1;
        if(newY < 0){
            throw new IllegalArgumentException("trying to move out of bounds");
        }
        myMaze.setCurrentRoom(myMaze.getCurrentRoom().x, newY);
    }
    public void moveDown(){
        int newY = myMaze.getCurrentRoom().y + 1;
        if(newY >= myMaze.getMazeRows()){
            throw new IllegalArgumentException("trying to move out of bounds");
        }
        myMaze.setCurrentRoom(myMaze.getCurrentRoom().x, newY);
    }
    public void setScore(int newScore){
        if(newScore < 0){
            throw new IllegalArgumentException("cannot set a score less than zero");
        }
        myScore = newScore;
    }
    public String getName(){
        return myName;
    }
    public int getScore(){
        return myScore;
    }
    public Point getCurrentRoom(){
        return myMaze.getCurrentRoom();
    }


}
