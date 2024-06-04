package View;

import Model.Door;
import Model.Room;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class RoomPanel extends JPanel {
    private static final Dimension DOORBUTTONSIZE = new Dimension(10,10);
    private final Room myRoom;
    private final Map<Door, JButton> myDoorButtons;

    public RoomPanel(final Room theRoom) {
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
        final JButton topDoor = drawDoor(myRoom.getTopDoor());
        final JButton leftDoor = drawDoor(myRoom.getLeftDoor());
        final JButton rightDoor = drawDoor(myRoom.getRightDoor());
        final JButton bottomDoor = drawDoor(myRoom.getBottomDoor());
        add(topDoor, BorderLayout.NORTH);
        add(leftDoor, BorderLayout.WEST);
        add(rightDoor, BorderLayout.EAST);
        add(bottomDoor, BorderLayout.SOUTH);
        if (myRoom.getMyExit()) {
            setBackground(Color.WHITE);
        }
    }

    private JButton drawDoor(final Door theDoor) {
        if (myDoorButtons.get(theDoor) != null) {
            return myDoorButtons.get(theDoor);
        }
        final JButton doorButton = new JButton();
        doorButton.setPreferredSize(new Dimension(DOORBUTTONSIZE));
        doorButton.setOpaque(true);

        if (theDoor == null) {
            doorButton.setBackground(Color.BLACK);
            doorButton.setEnabled(false);
        } else {
            myDoorButtons.put(theDoor, doorButton);
            updateDoorState(theDoor);
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

    public void updateRoomState(final Room theRoom) {
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

    public void updateDoorState(final Door theDoor) {
        final JButton doorButton = myDoorButtons.get(theDoor);
        if (doorButton != null) {
            if (theDoor.getMyDoorLocked()) {
                doorButton.setBackground(Color.RED);
                doorButton.setEnabled(false);
            } else {
                doorButton.setBackground(Color.GREEN);
            }
        }
    }

    public Room getRoom() {
        return myRoom;
    }

    public JButton getDoorButton(final Door theDoor) {
        return myDoorButtons.get(theDoor);
    }
}

