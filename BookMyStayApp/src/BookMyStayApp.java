import java.util.*;

class Room {
    private String type;
    private double price;
    private List<String> amenities;

    public Room(String type, double price, List<String> amenities) {
        this.type = type;
        this.price = price;
        this.amenities = amenities;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    @Override
    public String toString() {
        return "Room Type: " + type +
                ", Price: $" + price +
                ", Amenities: " + amenities;
    }
}

class Inventory {
    private Map<Room, Integer> roomAvailability;

    public Inventory() {
        roomAvailability = new HashMap<>();
    }

    public void addRoom(Room room, int quantity) {
        roomAvailability.put(room, quantity);
    }

    public Map<Room, Integer> getAvailableRooms() {
        Map<Room, Integer> available = new HashMap<>();
        for (Map.Entry<Room, Integer> entry : roomAvailability.entrySet()) {
            if (entry.getValue() > 0) {
                available.put(entry.getKey(), entry.getValue());
            }
        }
        return available;
    }
}

class SearchService {
    private Inventory inventory;

    public SearchService(Inventory inventory) {
        this.inventory = inventory;
    }

    public void searchAvailableRooms() {
        Map<Room, Integer> availableRooms = inventory.getAvailableRooms();
        if (availableRooms.isEmpty()) {
            System.out.println("No rooms available at the moment.");
        } else {
            System.out.println("Available Rooms:");
            for (Room room : availableRooms.keySet()) {
                System.out.println(room);
            }
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        Room single = new Room("Single", 100.0, Arrays.asList("WiFi", "TV"));
        Room doubleRoom = new Room("Double", 180.0, Arrays.asList("WiFi", "TV", "Mini Fridge"));
        Room suite = new Room("Suite", 300.0, Arrays.asList("WiFi", "TV", "Mini Fridge", "Balcony"));

        Inventory inventory = new Inventory();
        inventory.addRoom(single, 5);
        inventory.addRoom(doubleRoom, 0);
        inventory.addRoom(suite, 2);

        SearchService searchService = new SearchService(inventory);
        searchService.searchAvailableRooms();
    }
}