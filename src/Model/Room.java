package Model;

import java.io.Serializable;
import java.util.*;

public final class Room implements Serializable {
    private static final long serialversionUID = 129436332L;
    private static final String RIGHT = "right";
    private static final String LEFT = "left";
    private static final String TOP = "top";
    private static final String BOTTOM = "bottom";
    private final Boolean myExit;
    private Boolean myCurrentRoom;

    private final Map<String, Door> myDoors;
    public Room(final Door theLeftDoor,
                final Door theRightDoor, final Door theTopDoor, final Door theBottomDoor, final boolean theExit, final boolean theCurrentRoom) {
        myDoors = new HashMap<>();

        if (theLeftDoor != null) {
            myDoors.put(LEFT, theLeftDoor);
        }
        if (theRightDoor != null) {
            myDoors.put(RIGHT, theRightDoor);
        }
        if (theTopDoor != null) {
            myDoors.put(TOP, theTopDoor);
        }
        if (theBottomDoor != null) {
            myDoors.put(BOTTOM , theBottomDoor);
        }
        myExit = theExit;
        myCurrentRoom = theCurrentRoom;

    }
    public Boolean allDoorsLocked() {
        for (Door door : myDoors.values()) {
            if (!door.getMyDoorLocked()) {
                return false;
            }
        }
        return true;
    }
    public Boolean getMyExit() {
        return myExit;
    }
    public Boolean getMyCurrentRoom() {
        return myCurrentRoom;
    }
    public void setCurrentRoom(final Boolean theCurrentRoom) {
        myCurrentRoom = theCurrentRoom;
    }
    public Door getLeftDoor() {
        return myDoors.get(LEFT);
    }
    public Door getRightDoor() {
        return myDoors.get(RIGHT);
    }
    public Door getTopDoor() {
        return myDoors.get(TOP);
    }
    public Door getBottomDoor() {
        return myDoors.get(BOTTOM);
    }

}
