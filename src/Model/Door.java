package Model;


import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public final class Door implements Serializable {
    private static final long serialversionUID = 123459646L;
    private final Questions myQuestion;
    private Boolean myCanPass;
    private boolean myDoorLocked;
    private final PropertyChangeSupport myChange;


    public Door(final boolean theDoorLocked) {
        myDoorLocked = theDoorLocked;
        myQuestion = QuestionFactory.getRandomQuestion();
        myCanPass = false;
        myChange = new PropertyChangeSupport(this);
    }
    public boolean getMyDoorLocked() {
        return myDoorLocked;
    }
    public Questions getMyQuestion(){
        return myQuestion;
    }
    public void setMyDoorLocked(final boolean theDoorLocked){
        myDoorLocked = theDoorLocked;
        fireCurrentDoorChange();
    }
    public void setMyCanPass(final boolean thePass){
        myCanPass = thePass;
    }
    public boolean getMyCanPass(){
        return myCanPass;
    }
    private void fireCurrentDoorChange() {
        myChange.firePropertyChange("Door Change", null, this);
    }
}
