package Tests;

import Model.Door;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoorTests {
    private Door myDoor;
    @Test
    public void testConstructor(){
        myDoor = new Door(true);
        assertTrue(myDoor.getMyDoorLocked());
    }
    @Test
    public void testGetDoor(){
        myDoor = new Door(true);
        assertTrue(myDoor.getMyDoorLocked());
    }
    @Test
    public void testSetMyDoor(){
        myDoor = new Door(true);
        myDoor.setMyDoorLocked(false);
        assertFalse(myDoor.getMyDoorLocked());
    }


}
