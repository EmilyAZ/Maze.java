package Model;


public final class Door {
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
