package Controller;

import View.GameFrame;

import java.awt.*;

public final class TriviaMazeGame {
    private TriviaMazeGame(){

    }
    public static void main(final String[] theArgs){

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameFrame();
            }
        });
    }
}