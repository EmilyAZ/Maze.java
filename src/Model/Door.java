package Model;


import java.io.Serializable;

public final class Door implements Serializable {
    private static final long serialversionUID = 123459646L;

    private boolean myDoorLocked;

    public Door(final boolean theDoorLocked) {
        myDoorLocked = theDoorLocked;

    }
    public boolean getMyDoorLocked() {
        return myDoorLocked;
    }
    public void setMyDoorLocked(final boolean theDoorLocked) {
        myDoorLocked = theDoorLocked;
    }
}
