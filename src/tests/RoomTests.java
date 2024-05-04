package tests;
import Model.Room;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class RoomTests {
    private Room myRoom;

    @Test
    public void testRoomConstructor(){
        myRoom = new Room(2);
        assertFalse(myRoom.getRoomStatus());
        assertEquals(2,myRoom.getMyNumOfDoors());
    }
    @Test
    public void testRoomConstructorZeroDoors(){
        assertThrows(IllegalArgumentException.class, () -> myRoom = new Room(0));
    }
    @Test
    public void testRoomConstructorNegativeDoors(){
        assertThrows(IllegalArgumentException.class, () -> myRoom = new Room(-1));
    }
    @Test
    public void testGetRoomStatus(){
        myRoom = new Room(2);
        assertFalse(myRoom.getRoomStatus());
    }
    @Test
    public void testSetMyRoomProcessed(){
        myRoom = new Room(3);
        myRoom.setMyRoomProcessed(true);
        assertTrue(myRoom.getRoomStatus());
    }
    @Test
    public void testGetNumOfDoors() {
        myRoom = new Room(2);
        assertEquals(2, myRoom.getMyNumOfDoors());
    }

}
