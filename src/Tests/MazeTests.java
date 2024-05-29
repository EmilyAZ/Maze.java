/*

package Tests;

import Model.Maze;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MazeTests {
    private Maze myMaze;
    @Test
    public void testConstructorMazeEntrance(){
        myMaze = new Maze(4,4);
        assertEquals(0, myMaze.getCurrentRoom().x);
        assertEquals(0,myMaze.getCurrentRoom().y);
    }
    @Test
    public void testConstructorMazeExit(){
        myMaze = new Maze(4,5);
        assertEquals(3, myMaze.getExit().x);
        assertEquals(4, myMaze.getExit().y);
    }
    @Test
    public void testCreateMaze(){
        myMaze = new Maze(4,4);

    }
    @Test
    public void testConstructorInvalidRoom(){
        assertThrows(IllegalArgumentException.class, () ->myMaze = new Maze(2,3));
    }
    @Test
    public void testSetCurrentRoom(){
        myMaze = new Maze(5,5);
        myMaze.setCurrentRoom(1,2);
        assertEquals(1,myMaze.getCurrentRoom().x);
        assertEquals(2,myMaze.getCurrentRoom().y);
    }
    @Test
    public void testSetCurrentRoomInvalidInput(){
        myMaze = new Maze(4,7);
            assertThrows(IllegalArgumentException.class, () ->myMaze.setCurrentRoom(4,7));
            assertThrows(IllegalArgumentException.class, () ->myMaze.setCurrentRoom(-1,7));

    }

}
*/