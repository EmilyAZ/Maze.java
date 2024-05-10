package tests;

import Model.Maze;
import Model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayerTest {
    Player myPlayer;
    Maze myMaze;
    @BeforeEach
    public void beforeEach(){
        myMaze = new Maze(5,6);
        myPlayer = new Player("test", myMaze);
    }
    @Test
    public void testPlayerConstructor(){
        assertEquals("test", myPlayer.getName());
        assertEquals(0, myPlayer.getScore());
    }
    @Test
    public void testPlayerMoveLeft(){
        myMaze.setCurrentRoom(1,3);
        myPlayer.moveLeft();
        assertEquals(new Point(0,3), myPlayer.getCurrentRoom());
    }
    @Test
    public void testPlayerMoveLeftInvalid(){
        myMaze.setCurrentRoom(0,0);
        assertThrows(IllegalArgumentException.class, () -> myPlayer.moveLeft());
    }
    @Test
    public void testPlayerMoveRight(){
        myMaze.setCurrentRoom(0,0);
        myPlayer.moveRight();
        assertEquals(new Point(1,0), myPlayer.getCurrentRoom());
    }
    @Test
    public void testPlayerMoveRightInvalid(){
        myMaze.setCurrentRoom(5,0);
        assertThrows(IllegalArgumentException.class,() -> myPlayer.moveRight());
    }
    @Test
    public void testPlayerMoveUp(){
        myMaze.setCurrentRoom(0,1);
        myPlayer.moveUp();
        assertEquals(new Point(0,0), myPlayer.getCurrentRoom());
    }
    @Test
    public void testPlayerMoveUpInvalid(){
        myMaze.setCurrentRoom(0,0);
        assertThrows(IllegalArgumentException.class,() -> myPlayer.moveUp());

    }
    @Test
    public void testPlayerMoveDown(){
        myMaze.setCurrentRoom(0,0);
        myPlayer.moveDown();
        assertEquals(new Point(0,1), myPlayer.getCurrentRoom());
    }
    @Test
    public void testPlayerMoveDownInvalid(){
        myMaze.setCurrentRoom(4,4);
        assertThrows(IllegalArgumentException.class,() -> myPlayer.moveDown());
    }
    @Test
    public void testSetScore(){
        myPlayer.setScore(5);
        assertEquals(5,myPlayer.getScore());
    }
    @Test
    public void testSetScoreNegative(){
        assertThrows(IllegalArgumentException.class,() -> myPlayer.setScore(-1));
    }
    @Test
    public void testSetScoreZero(){
        myPlayer.setScore(1);
        myPlayer.setScore(0);
        assertEquals(0, myPlayer.getScore());
    }
}
