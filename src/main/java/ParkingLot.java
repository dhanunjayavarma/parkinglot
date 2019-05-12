import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private static ParkingLot parkingLot;
    private Map<Integer, ParkingSlot> parkingSlots;
    AutomatedTicketSystem ticketSystem;
    private int maxNumberOfParkingSlots;
    private int ONE = 1;
    private int occupiedParkingSlots = 0;

    private ParkingLot(AutomatedTicketSystem ticketSystem,int maxNumberOfParkingSlots) {
        this.ticketSystem =ticketSystem;
        this.maxNumberOfParkingSlots = maxNumberOfParkingSlots;
        intializeParkingSpots(this.maxNumberOfParkingSlots);
    }

    public static ParkingLot getInstance(AutomatedTicketSystem ticketSystem,int maxNumberOfParkingSlots) {
        if (parkingLot == null) {
            parkingLot = new ParkingLot(ticketSystem,maxNumberOfParkingSlots);
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

    public void unPark(int parkingSlotNumber) {
        ticketSystem.collectTicket(parkingSlotNumber);
    }
}