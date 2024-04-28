package Model;
public class Door {
    private boolean doorLocked;

    public Door(boolean doorLocked) {
        this.doorLocked = doorLocked;
    }
    public boolean getDoorStatus(){
        return doorLocked;
    }
    public void setDoorStatus(boolean doorLocked){
        this.doorLocked = doorLocked;
    }
}
