package View;

import Model.Maze;

import java.awt.*;

public final class GameMain {
    private GameMain(){

    }
    public static void main(final String[] theArgs){

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MazeFrame(new Maze(4,4));
            }
        });
    }
}
