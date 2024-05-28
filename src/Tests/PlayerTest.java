package Tests;

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
        myPlayer = new Player(myMaze);
    }
    @Test
    public void testPlayerConstructor(){
        assertEquals("DEFAULTNAME",myPlayer.getName());
        assertEquals(0, myPlayer.getScore());
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
