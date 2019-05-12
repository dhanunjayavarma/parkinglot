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
    }

    public Map<Integer, ParkingSlot> getParkingSlots() {
        return parkingSlots;
    }

    public AutomatedTicketSystem getTicketSystem() {
        return ticketSystem;
    }

    public void park(Car car) {
        ticketSystem.allocateTicket(car);
    }

    public boolean isParkingLotFull() {
        if (occupiedParkingSlots == maxNumberOfParkingSlots) {
            return true;
        }
        return false;
    }
    public boolean isParkingLotEmpty(){
        if (occupiedParkingSlots == 0) {
            return true;
        }
        return false;
    }
    public void allocateParkingSlot(int parkingSlotNumber, Car car) {
        makeParkingSlotUnavailable(parkingSlotNumber);
        parkingSlots.get(parkingSlotNumber).setCar(car);
        occupiedParkingSlots++;
    }

    public void makeParkingSlotUnavailable(int parkingSlotNumber) {
        parkingSlots.get(parkingSlotNumber).setFree(false);
    }

    public void freeParkingSlot(int parkingSlotNumber) {
        parkingSlots.get(parkingSlotNumber).setFree(true);
        occupiedParkingSlots--;
    }

    public void unPark(int parkingSlotNumber) {
        ticketSystem.collectTicket(parkingSlotNumber);
    }

    public void showStatus() {
        if (!isParkingLotEmpty()) {
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