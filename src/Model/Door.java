package Model;
public class Door {
    private boolean doorLocked;

    public Door(final boolean theDoorLocked) {
        doorLocked = theDoorLocked;
    }
    public boolean getDoorStatus(){
        return doorLocked;
    }
    public void setDoorStatus(final boolean theDoorLocked){
        doorLocked = theDoorLocked;
    }
}
