package Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Room {
    private static final String RIGHT = "right";
    private static final String LEFT = "left";
    private static final String TOP = "top";
    private static final String BOTTOM = "bottom";

    private boolean myRoomProcessed;
    private final Map<String, Door> myDoors;
    private final int myNumOfDoors;
    public Room(final Door theLeftDoor,
                final Door theRightDoor, final Door theTopDoor, final Door theBottomDoor){
        myRoomProcessed = false;
        myDoors = new HashMap<>();

        if(theLeftDoor != null){
            myDoors.put(LEFT, theLeftDoor);
        }
        if(theRightDoor != null){
            myDoors.put(RIGHT, theRightDoor);
        }
        if(theTopDoor != null){
            myDoors.put(TOP, theTopDoor);
        }
        if(theBottomDoor != null){
            myDoors.put(BOTTOM , theBottomDoor);
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
    public Map<String, Door> getMyDoors() {
        return myDoors;
    }
}
