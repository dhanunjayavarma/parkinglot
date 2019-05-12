import java.util.HashMap;

import java.util.Map;

public class AutomatedTicketSystem {

    ParkingLot parkingLot;
    Map<Integer, ParkingLotDetalisVO> parkingLotDetalis = new HashMap<Integer, ParkingLotDetalisVO>();

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public void issueTicket(Car car) {
        final int availableParkingSlotNumber = parkingLot.getNearestParkingSlotNumberToTheEntry();
        String ticket = genarateTicket(availableParkingSlotNumber, car.getRegistrationNumber());
        car.setTicket(ticket);
        parkingLot.allocateParkingSlot(availableParkingSlotNumber, car);
        saveParkingLotDetails(availableParkingSlotNumber, car.getRegistrationNumber(), car.getColour());
    }

    private void saveParkingLotDetails(int slotNumber, String registrationNumber, Colour colour) {
        ParkingLotDetalisVO parkingLotDetalisVO = new ParkingLotDetalisVO(slotNumber, registrationNumber, colour);
        parkingLotDetalis.put(slotNumber, parkingLotDetalisVO);
    }

    private String genarateTicket(int slotNumber, String registrationNumber) {
        final String ticket = slotNumber + registrationNumber;
        return ticket;
    }

    public void collectTicket(int parkingSlotNumber) {
        parkingLotDetalis.remove(parkingSlotNumber);
        parkingLot.freeParkingSlot(parkingSlotNumber);
    }
}
