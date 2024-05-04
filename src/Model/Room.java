package Model;

public class Room {
    private boolean myRoomProcessed;
    private Door[] myDoors;
    private final int myNumOfDoors;
    public Room(final int theNumOfDoors){
        if(theNumOfDoors <= 0){
            throw new IllegalArgumentException("number of doors is less than one");
        }else{
            myNumOfDoors = theNumOfDoors;
        }
        myRoomProcessed = false;
        myDoors = new Door[theNumOfDoors];
    }
    private void addDoors(){
        for(int i = 0; i < myNumOfDoors;i++){
            myDoors[i] = new Door(true);
        }
    }
    public boolean getRoomStatus(){
        return myRoomProcessed;
    }
    public void setMyRoomProcessed(boolean myStatus){
        myRoomProcessed = myStatus;
    }
    public int getMyNumOfDoors() {
        return myDoors.length;
    }
}
