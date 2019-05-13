import java.util.HashMap;

import java.util.Map;

public class ParkingLot {
    private static ParkingLot parkingLot;
    private Map<Integer, ParkingSlot> parkingSlots;
    AutomatedTicketSystem ticketSystem;
    private int maxNumberOfParkingSlots;
    private int ONE = 1;
    private int occupiedParkingSlots = 0;

    private ParkingLot(AutomatedTicketSystem ticketSystem, int maxNumberOfParkingSlots) {
        this.ticketSystem = ticketSystem;
        this.maxNumberOfParkingSlots = maxNumberOfParkingSlots;
        intializeParkingSpots(this.maxNumberOfParkingSlots);
    }

    public static ParkingLot getInstance(AutomatedTicketSystem ticketSystem, int maxNumberOfParkingSlots) {
        if (parkingLot == null) {
            parkingLot = new ParkingLot(ticketSystem, maxNumberOfParkingSlots);
            ticketSystem.setParkingLot(parkingLot);
        }
        return parkingLot;
    }

    private void intializeParkingSpots(int maxNumberOfParkingSlots) {
        parkingSlots = new HashMap<Integer, ParkingSlot>();
        for (int slotNumber = ONE; slotNumber <= maxNumberOfParkingSlots; slotNumber++) {
            parkingSlots.put(slotNumber, new ParkingSlot());
        }
        System.out.println("created a parking lot with "+maxNumberOfParkingSlots);
    }

    public void park(Car car) {
        if (!isFull()) {
            ticketSystem.issueTicket(car);
        } else {
            System.out.println("Sorry, parking lot is full");
        }

    }

    public void unPark(int parkingSlotNumber) {
        ticketSystem.collectTicket(parkingSlotNumber);
    }

    public boolean isFull() {
        if (occupiedParkingSlots == maxNumberOfParkingSlots) {
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        if (occupiedParkingSlots == 0) {
            return true;
        }
        return false;
    }

    public void allocateParkingSlot(int parkingSlotNumber, Car car) {
        makeParkingSlotUnavailable(parkingSlotNumber);
        parkingSlots.get(parkingSlotNumber).setCar(car);
        occupiedParkingSlots++;
        System.out.println("Allocated slot number:  " + parkingSlotNumber);
    }

    public void makeParkingSlotUnavailable(int parkingSlotNumber) {
        parkingSlots.get(parkingSlotNumber).setFree(false);
    }

    public void freeParkingSlot(int parkingSlotNumber) {
        parkingSlots.get(parkingSlotNumber).setFree(true);
        parkingSlots.get(parkingSlotNumber).setCar(null);
        occupiedParkingSlots--;
        System.out.println("Slot number " + parkingSlotNumber + " is free");
    }

    public int getNearestParkingSlotNumberToTheEntry() {
        int nearestParkingSlotNumber = 0;
        for (int slotNumber = ONE; slotNumber <= maxNumberOfParkingSlots; slotNumber++) {
            if (parkingSlots.get(slotNumber).isFree()) {
                nearestParkingSlotNumber = slotNumber;
                break;
            }
        }
        return nearestParkingSlotNumber;
    }

    public void showStatus() {
        if (!isEmpty()) {
            System.out.println("Slot No.    RegistrationNo     Colour");
            for (Map.Entry<Integer, ParkingSlot> entry : parkingSlots.entrySet()) {
                if (!entry.getValue().isFree()) {
                    System.out.println(entry.getKey() + "           " + entry.getValue().getCar().getRegistrationNumber() + "      "
                            + entry.getValue().getCar().getColour());
                }
            }
        } else {
            System.out.println("Parking Lot is Empty");
        }

    }

}