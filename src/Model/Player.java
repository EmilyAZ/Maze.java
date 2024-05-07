package Model;


import java.awt.*;

public class Player {
    private static final int MIN_ROOMS = 4;
    private Maze myMaze;
    private final String myName;
    private int myScore;
    public Player (final String theName, Maze theMaze){
        myName = theName;
        myScore = 0;
        myMaze = theMaze;
    }
    public void moveLeft(){
        int newX = myMaze.getMyCurrentRoom().x - 1;
        if(newX < 0){
            throw new IllegalArgumentException("trying to move out of bounds");
        }
        myMaze.setCurrentRoom(newX, myMaze.getMyCurrentRoom().y);
    }
    public void moveRight(){
        int newX = myMaze.getMyCurrentRoom().x + 1;
        if(newX >= myMaze.getMyMazeColumns()){
            throw new IllegalArgumentException("trying to move out of bounds");
        }
        myMaze.setCurrentRoom(newX, myMaze.getMyCurrentRoom().y);
    }
    public void moveUp(){
        int newY = myMaze.getMyCurrentRoom().y - 1;
        if(newY < 0){
            throw new IllegalArgumentException("trying to move out of bounds");
        }
        myMaze.setCurrentRoom(myMaze.getMyCurrentRoom().x, newY);
    }
    public void moveDown(){
        int newY = myMaze.getMyCurrentRoom().y + 1;
        if(newY >= myMaze.getMyMazeRows()){
            throw new IllegalArgumentException("trying to move out of bounds");
        }
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
        return myMaze.getMyCurrentRoom();
    }


}
