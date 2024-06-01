package View;

import Model.Door;
import Model.Room;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class RoomPanel extends JPanel {
    private final Room myRoom;
    private final Map<Door, JButton> myDoorButtons;

    public RoomPanel(Room theRoom) {
        super();
        myRoom = theRoom;
        myDoorButtons = new HashMap<>();
        layoutComponents();

    }

    private void layoutComponents() {
        createRoom();
    }

    private void createRoom() {
        setLayout(new BorderLayout());
        JButton topDoor = drawDoor(myRoom.getTopDoor());
        JButton leftDoor = drawDoor(myRoom.getLeftDoor());
        JButton rightDoor = drawDoor(myRoom.getRightDoor());
        JButton bottomDoor = drawDoor(myRoom.getBottomDoor());
        add(topDoor, BorderLayout.NORTH);
        add(leftDoor, BorderLayout.WEST);
        add(rightDoor, BorderLayout.EAST);
        add(bottomDoor, BorderLayout.SOUTH);
        if (myRoom.getMyExit()) {
            setBackground(Color.WHITE);
        }
    }

    private JButton drawDoor(Door door) {
        if (myDoorButtons.get(door) != null) {
            return myDoorButtons.get(door);
        }
        JButton doorButton = new JButton();
        doorButton.setPreferredSize(new Dimension(10, 10));
        doorButton.setOpaque(true);

        if (door == null) {
            doorButton.setBackground(Color.BLACK);
            doorButton.setEnabled(false);
        } else {
            myDoorButtons.put(door, doorButton);
            updateDoorState(door);
        }
        return doorButton;
    }

    private void disableRoom() {
        for (Component cp : getComponents()) {
            cp.setEnabled(false);
        }
    }

    private void enableRoom() {
        for (Component cp : getComponents()) {
            if (cp.getBackground() != Color.black) {
                cp.setEnabled(true);
            }
        }
    }

    public void updateRoomState(Room theRoom) {
        if (theRoom.getMyCurrentRoom()) {
            setBackground(Color.YELLOW);
            enableRoom();
        } else if (theRoom.getMyExit()) {
            setBackground(Color.WHITE);
            disableRoom();
        } else {
            setBackground(Color.GRAY);
            disableRoom();
        }
    }

    public void updateDoorState(Door door) {
        JButton DoorButton = myDoorButtons.get(door);
        if (DoorButton != null) {
            if (door.getDoorLocked()) {
                DoorButton.setBackground(Color.RED);
                DoorButton.setEnabled(false);
            } else {
                DoorButton.setBackground(Color.GREEN);
            }
        }
    }

    public Room getRoom() {
        return myRoom;
    }

    public JButton getDoorButton(Door door) {
        return myDoorButtons.get(door);
    }
}

