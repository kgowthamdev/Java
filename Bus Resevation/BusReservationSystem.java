import java.util.ArrayList;
import java.util.Scanner;

class Bus {
    private String busNumber;
    private boolean[] seats;
    private int totalSeats;

    public Bus(String busNumber, int totalSeats) {
        this.busNumber = busNumber;
        this.totalSeats = totalSeats;
        seats = new boolean[totalSeats];
    }

    public String getBusNumber() {
        return busNumber;
    }

    public boolean reserveSeat(int seatNumber) {
        if (seatNumber < 1 || seatNumber > totalSeats) {
            System.out.println("Invalid seat number.");
            return false;
        }

        if (seats[seatNumber - 1]) {
            System.out.println("Seat already reserved.");
            return false;
        }

        seats[seatNumber - 1] = true;
        System.out.println("Seat " + seatNumber + " reserved successfully.");
        return true;
    }

    public void showAvailableSeats() {
        System.out.println("Available seats in bus " + busNumber + ":");
        for (int i = 0; i < totalSeats; i++) {
            if (!seats[i]) {
                System.out.print((i + 1) + " ");
            }
        }
        System.out.println();
    }
}

public class BusReservationSystem {
    private static ArrayList<Bus> buses = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeBuses();
        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            handleChoice(choice);
        } while (choice != 3);
    }

    private static void initializeBuses() {
        buses.add(new Bus("Bus001", 40));
        buses.add(new Bus("Bus002", 35));
        buses.add(new Bus("Bus003", 45));
    }

    private static void displayMenu() {
        System.out.println("Bus Reservation System");
        System.out.println("1. Reserve a seat");
        System.out.println("2. View available seats");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void handleChoice(int choice) {
        switch (choice) {
            case 1:
                reserveSeat();
                break;
            case 2:
                viewAvailableSeats();
                break;
            case 3:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }

    private static void reserveSeat() {
        showAvailableBuses();
        System.out.print("Enter bus number: ");
        String busNumber = scanner.next();
        Bus bus = findBus(busNumber);
        if (bus == null) {
            System.out.println("Bus not found.");
            return;
        }

        System.out.print("Enter seat number: ");
        int seatNumber = scanner.nextInt();
        bus.reserveSeat(seatNumber);
    }

    private static void viewAvailableSeats() {
        showAvailableBuses();
        System.out.print("Enter bus number: ");
        String busNumber = scanner.next();
        Bus bus = findBus(busNumber);
        if (bus == null) {
            System.out.println("Bus not found.");
            return;
        }

        bus.showAvailableSeats();
    }

    private static Bus findBus(String busNumber) {
        for (Bus bus : buses) {
            if (bus.getBusNumber().equals(busNumber)) {
                return bus;
            }
        }
        return null;
    }

    private static void showAvailableBuses() {
        System.out.println("Available buses:");
        for (Bus bus : buses) {
            System.out.print(bus.getBusNumber() + " ");
        }
        System.out.println();
    }
}