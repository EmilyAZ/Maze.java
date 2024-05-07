package tests;

import Model.Maze;
import Model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    }
    @Test
    public void testPlayerMoveLeft(){
        myMaze.setCurrentRoom(1,3);
        myPlayer.moveLeft();
        assertEquals(0, myPlayer.getCurrentRoom().x);
        assertEquals(3,myPlayer.getCurrentRoom().y);
    }


}
