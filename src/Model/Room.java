package Model;

import java.util.*;

public final class Room {
    private static final String RIGHT = "right";
    private static final String LEFT = "left";
    private static final String TOP = "top";
    private static final String BOTTOM = "bottom";

    private final Map<String, Door> myDoors;
    public Room(final Door theLeftDoor,
                final Door theRightDoor, final Door theTopDoor, final Door theBottomDoor){
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

}
