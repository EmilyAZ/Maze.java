package Model;


import java.io.Serializable;

public final class Door implements Serializable {
    private static final long serialversionUID = 123459646L;

    private boolean doorLocked;

    public Door(final boolean theDoorLocked) {
        doorLocked = theDoorLocked;

    }
    public boolean getDoorLocked(){
        return doorLocked;
    }
    public void setDoorLocked(final boolean theDoorLocked){
        doorLocked = theDoorLocked;
    }
}
