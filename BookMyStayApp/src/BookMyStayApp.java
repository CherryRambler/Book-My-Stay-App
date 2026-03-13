abstract class Room {
    private String roomType;
    private int numberOfBeds;
    private double pricePerNight;
    private double size; // in square meters

    public Room(String roomType, int numberOfBeds, double pricePerNight, double size) {
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.pricePerNight = pricePerNight;
        this.size = size;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public double getSize() {
        return size;
    }

    public abstract void displayRoomDetails();
}

class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 50.0, 20.0);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println("Room Type: " + getRoomType());
        System.out.println("Beds: " + getNumberOfBeds());
        System.out.println("Size: " + getSize() + " sq.m");
        System.out.println("Price: $" + getPricePerNight() + " per night");
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 80.0, 35.0);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println("Room Type: " + getRoomType());
        System.out.println("Beds: " + getNumberOfBeds());
        System.out.println("Size: " + getSize() + " sq.m");
        System.out.println("Price: $" + getPricePerNight() + " per night");
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 150.0, 60.0);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println("Room Type: " + getRoomType());
        System.out.println("Beds: " + getNumberOfBeds());
        System.out.println("Size: " + getSize() + " sq.m");
        System.out.println("Price: $" + getPricePerNight() + " per night");
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        // Static availability variables
        int singleRoomAvailability = 5;
        int doubleRoomAvailability = 3;
        int suiteRoomAvailability = 2;

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        System.out.println("Welcome to Book My Stay App (Use Case 2.1)\n");

        single.displayRoomDetails();
        System.out.println("Available: " + singleRoomAvailability + "\n");

        doubleRoom.displayRoomDetails();
        System.out.println("Available: " + doubleRoomAvailability + "\n");

        suite.displayRoomDetails();
        System.out.println("Available: " + suiteRoomAvailability + "\n");

        System.out.println("Thank you for using Book My Stay App!");
    }
}