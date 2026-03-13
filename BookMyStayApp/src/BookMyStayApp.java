import java.util.*;

class Reservation {
    private String guestName;
    private String roomType;
    private int nights;

    public Reservation(String guestName, String roomType, int nights) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.nights = nights;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getNights() {
        return nights;
    }
}

class InventoryService {

    private Map<String, Integer> inventory = new HashMap<>();

    public InventoryService() {
        inventory.put("Standard", 2);
        inventory.put("Deluxe", 2);
        inventory.put("Suite", 1);
    }

    public boolean isAvailable(String roomType) {
        return inventory.getOrDefault(roomType, 0) > 0;
    }

    public void decrementRoom(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }

    public void showInventory() {
        System.out.println("\nCurrent Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + " rooms remaining: " + inventory.get(type));
        }
    }
}

class BookingService {

    private Queue<Reservation> requestQueue;
    private InventoryService inventoryService;

    private Set<String> allocatedRoomIds = new HashSet<>();
    private Map<String, Set<String>> roomTypeMap = new HashMap<>();

    private int roomCounter = 101;

    public BookingService(Queue<Reservation> queue, InventoryService inventoryService) {
        this.requestQueue = queue;
        this.inventoryService = inventoryService;
    }

    public void processBookings() {

        while (!requestQueue.isEmpty()) {

            Reservation reservation = requestQueue.poll();
            String roomType = reservation.getRoomType();

            System.out.println("\nProcessing booking for " + reservation.getGuestName());

            if (inventoryService.isAvailable(roomType)) {

                String roomId = generateRoomId(roomType);

                allocatedRoomIds.add(roomId);

                roomTypeMap.putIfAbsent(roomType, new HashSet<>());
                roomTypeMap.get(roomType).add(roomId);

                inventoryService.decrementRoom(roomType);

                System.out.println("Reservation Confirmed!");
                System.out.println("Guest: " + reservation.getGuestName());
                System.out.println("Room Type: " + roomType);
                System.out.println("Assigned Room ID: " + roomId);

            } else {
                System.out.println("Reservation Failed: No available " + roomType + " rooms.");
            }
        }
    }

    private String generateRoomId(String roomType) {
        String roomId;
        do {
            roomId = roomType.substring(0, 2).toUpperCase() + roomCounter++;
        } while (allocatedRoomIds.contains(roomId));

        return roomId;
    }

    public void showAllocatedRooms() {
        System.out.println("\nAllocated Rooms:");

        for (String type : roomTypeMap.keySet()) {
            System.out.println(type + " -> " + roomTypeMap.get(type));
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        Queue<Reservation> bookingQueue = new LinkedList<>();

        bookingQueue.add(new Reservation("Alice", "Deluxe", 2));
        bookingQueue.add(new Reservation("Bob", "Suite", 1));
        bookingQueue.add(new Reservation("Charlie", "Standard", 3));
        bookingQueue.add(new Reservation("David", "Standard", 1));
        bookingQueue.add(new Reservation("Emma", "Suite", 2));

        InventoryService inventoryService = new InventoryService();

        BookingService bookingService = new BookingService(bookingQueue, inventoryService);

        bookingService.processBookings();

        bookingService.showAllocatedRooms();

        inventoryService.showInventory();
    }
}