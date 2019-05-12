import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private static ParkingLot parkingLot;
    Map<Integer, ParkingSlot> parkingSlots;
    AutomatedTicketSystem ticketSystem;
    private int maxNumberOfParkingSlots;
    private int ONE = 1;
    private ParkingLot(int maxNumberOfParkingSlots) {
        ticketSystem=new AutomatedTicketSystem();
        this.maxNumberOfParkingSlots = maxNumberOfParkingSlots;
        intializeParkingSpots(this.maxNumberOfParkingSlots);
    }

    public static ParkingLot getInstance(int maxNumberOfParkingSlots) {
        if (parkingLot == null) {
            parkingLot = new ParkingLot(maxNumberOfParkingSlots);
        }
        return parkingLot;
    }

    private void intializeParkingSpots(int maxNumberOfParkingSlots) {
        parkingSlots = new HashMap<Integer, ParkingSlot>();
        for (int slotNumber = ONE; slotNumber <= maxNumberOfParkingSlots; slotNumber++) {
            parkingSlots.put(slotNumber, new ParkingSlot());
        }
    }

    public void park(){
        ticketSystem.allocateParkingSlot();
    }

    public boolean isParkingLotFull() {
        return false;
    }
}
