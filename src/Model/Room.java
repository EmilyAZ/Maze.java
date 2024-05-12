package Model;

import java.util.HashMap;
import java.util.Map;

public class Room {
    private static final String RIGHT = "right";
    private static final String LEFT = "left";
    private static final String TOP = "top";
    private static final String BOTTOM = "bottom";

    private boolean myRoomProcessed;
    private final Map<String, Door> myDoors;
    private final int myNumOfDoors;
    public Room(final boolean theLeftNeighbor,
                final boolean theRightNeighbor, final boolean theTopNeighbor, final boolean theBottomNeighbor){
        myRoomProcessed = false;
        myDoors = new HashMap<>();
        if(theLeftNeighbor) {
            myDoors.put(LEFT, new Door(false));
        }
        if(theRightNeighbor){
            myDoors.put(RIGHT, new Door(false));
        }
        if(theTopNeighbor){
            myDoors.put(TOP, new Door(false));
        }
        if(theBottomNeighbor){
            myDoors.put(BOTTOM, new Door(false));
        }
        myNumOfDoors = myDoors.size();

    }
    public Door getLeftDoor(){
        return myDoors.get(LEFT);
    }
    public Door getRightDoor(){
        return myDoors.get(RIGHT);
    }
    public Door getTopDoor(){
        return myDoors.get(TOP);
    }
    public Door getBottomDoor(){
        return myDoors.get(BOTTOM);
    }
    public boolean getRoomStatus(){
        return myRoomProcessed;
    }
    public void setMyRoomProcessed(boolean myStatus){
        myRoomProcessed = myStatus;
    }
    public int getMyNumOfDoors() {
        return myNumOfDoors;
    }
}
