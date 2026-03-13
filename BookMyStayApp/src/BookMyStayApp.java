import java.util.LinkedList;
import java.util.Queue;

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

    @Override
    public String toString() {
        return "Guest: " + guestName +
                ", Room Type: " + roomType +
                ", Nights: " + nights;
    }
}

class BookingRequestQueue {

    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    public void submitRequest(Reservation reservation) {
        requestQueue.add(reservation);
        System.out.println("Booking request received for " + reservation.getGuestName());
    }

    public void showQueuedRequests() {

        if (requestQueue.isEmpty()) {
            System.out.println("No booking requests in queue.");
            return;
        }

        System.out.println("\nCurrent Booking Request Queue:");

        for (Reservation r : requestQueue) {
            System.out.println(r);
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        BookingRequestQueue queue = new BookingRequestQueue();

        Reservation r1 = new Reservation("Alice", "Deluxe", 2);
        Reservation r2 = new Reservation("Bob", "Suite", 1);
        Reservation r3 = new Reservation("Charlie", "Standard", 3);

        queue.submitRequest(r1);
        queue.submitRequest(r2);
        queue.submitRequest(r3);

        queue.showQueuedRequests();

        System.out.println("\nRequests stored in FIFO order for future allocation.");
    }
}