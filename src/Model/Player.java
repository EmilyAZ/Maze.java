package Model;


import java.awt.*;

public final class Player {
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
