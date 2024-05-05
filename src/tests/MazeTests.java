package tests;

import Model.Maze;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MazeTests {
    private Maze myMaze;
    @Test
    public void testConstructorMazeEntrance(){
        myMaze = new Maze(4,4);
        assertEquals(0, myMaze.getMyCurrentRoom().x);
        assertEquals(0,myMaze.getMyCurrentRoom().y);
    }
    @Test
    public void testConstructorMazeExit(){
        myMaze = new Maze(4,5);
        assertEquals(3, myMaze.getMyExit().x);
        assertEquals(4, myMaze.getMyExit().y);
    }
    @Test
    public void testConstructorInvalidRoom(){
        assertThrows(IllegalArgumentException.class, () ->myMaze = new Maze(2,3));
    }
    @Test
    public void testSetCurrentRoom(){
        myMaze = new Maze(5,5);
        myMaze.setCurrentRoom(1,2);
        assertEquals(1,myMaze.getMyCurrentRoom().x);
        assertEquals(2,myMaze.getMyCurrentRoom().y);
    }
    @Test
    public void testSetCurrentRoomInvalidInput(){
        myMaze = new Maze(4,7);
            assertThrows(IllegalArgumentException.class, () ->myMaze.setCurrentRoom(4,7));
            assertThrows(IllegalArgumentException.class, () ->myMaze.setCurrentRoom(-1,7));

    }

}
