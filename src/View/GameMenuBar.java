package View;

import javax.swing.*;
import java.awt.event.ActionListener;

public final class GameMenuBar extends JMenuBar {
    private final JMenuItem mySave;
    private final JMenuItem myLoad;
    public GameMenuBar() {
        myLoad = new JMenuItem("Load");
        mySave = new JMenuItem("Save");
        myLoad.setEnabled(true);
        mySave.setEnabled(true);
        createMenuBar();
    }
    private void createMenuBar() {
        add(myLoad);
        add(mySave);
    }
    public JMenuItem getSaveMenuItem() {
        return mySave;
    }
    public JMenuItem getMyLoadMenuItem() {
        return myLoad;
    }
    public void addSaveListener(final ActionListener theListener) {
        mySave.addActionListener(theListener);
    }
    public void addLoadListener(final ActionListener theListener) {
        myLoad.addActionListener(theListener);
    }
}
