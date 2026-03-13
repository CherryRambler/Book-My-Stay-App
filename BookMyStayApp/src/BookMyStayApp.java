import java.util.HashMap;
import java.util.Map;

class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }
    public void registerRoomType(String roomType, int count) {
        if (count < 0) {
            System.out.println("Cannot register negative room count for " + roomType);
            return;
        }
        inventory.put(roomType, count);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public boolean bookRoom(String roomType) {
        int available = getAvailability(roomType);
        if (available > 0) {
            inventory.put(roomType, available - 1);
            System.out.println("Room booked successfully: " + roomType);
            return true;
        } else {
            System.out.println("No rooms available for: " + roomType);
            return false;
        }
    }

    public void cancelBooking(String roomType) {
        int available = getAvailability(roomType);
        inventory.put(roomType, available + 1);
        System.out.println("Booking cancelled for: " + roomType);
    }

    public void displayInventory() {
        System.out.println("\nCurrent Room Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println("Room Type: " + entry.getKey() + ", Available: " + entry.getValue());
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        inventory.registerRoomType("Single", 10);
        inventory.registerRoomType("Double", 5);
        inventory.registerRoomType("Suite", 2);

        inventory.displayInventory();

        inventory.bookRoom("Single");
        inventory.bookRoom("Double");
        inventory.bookRoom("Suite");
        inventory.bookRoom("Suite");  // Book last Suite
        inventory.bookRoom("Suite");  // Attempt to book beyond availability

        inventory.displayInventory();

        inventory.cancelBooking("Suite");

        inventory.displayInventory();
    }
}