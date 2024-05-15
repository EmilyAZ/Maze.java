package Tests;

import Model.Door;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoorTests {
    private Door myDoor;
    @Test
    public void testConstructor(){
        myDoor = new Door(true);
        assertTrue(myDoor.getDoorStatus());
    }
    @Test
    public void testGetDoor(){
        myDoor = new Door(true);
        assertTrue(myDoor.getDoorStatus());
    }
    @Test
    public void testSetMyDoor(){
        myDoor = new Door(true);
        myDoor.setDoorStatus(false);
        assertFalse(myDoor.getDoorStatus());
    }


}
