/*

package Tests;
import Model.Room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class RoomTests {
    private Room myRoom;
    @BeforeEach
    public void BeforeEach(){
        myRoom = new Room(true, true, false, false);
    }

    @Test
    public void testRoomConstructor(){
        myRoom = new Room(true, true, false, false);
        assertFalse(myRoom.getRoomStatus());
        assertEquals(2,myRoom.getMyNumOfDoors());
        assertNull(myRoom.getTopDoor());
        assertNull(myRoom.getBottomDoor());
    }
    @Test
    public void testGetRoomStatus(){
        assertFalse(myRoom.getRoomStatus());
    }
    @Test
    public void testSetMyRoomProcessed(){
        myRoom.setMyRoomProcessed(true);
        assertTrue(myRoom.getRoomStatus());
    }
    @Test
    public void testGetNumOfDoors() {
        assertEquals(2, myRoom.getMyNumOfDoors());
    }

}
*/