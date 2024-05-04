package tests;

import Model.Door;
import Model.Maze;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MazeTests {
    private Maze myMaze;
    @Test
    public void testConstructor(){
        myMaze = new Maze(3,3);
        myMaze.createMaze();
    }
}
