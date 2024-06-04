package Controller;

import Model.Maze;
import View.GameMenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class MenuBarController implements ActionListener {
    private final Maze myMaze;
    private final GameMenuBar myMenuBar;
    public MenuBarController(final Maze theMaze, final GameMenuBar theMenuBar) {
        if(theMaze == null){
            throw new IllegalArgumentException("Maze cannot be null");
        }
        if(theMenuBar == null){
            throw  new IllegalArgumentException("MenuBar cannot be null");
        }
        myMaze = theMaze;
        myMenuBar = theMenuBar;
        myMenuBar.addLoadListener(this);
        myMenuBar.addSaveListener(this);

    }

    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        if (theEvent.getSource() == myMenuBar.getSaveMenuItem()) {
            myMaze.saveMaze();
        } else if (theEvent.getSource() == myMenuBar.getMyLoadMenuItem()) {
            myMaze.loadMaze();
        }
    }

}
