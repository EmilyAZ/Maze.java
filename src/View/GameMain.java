package View;


import java.awt.*;

public final class GameMain {
    private GameMain(){

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
