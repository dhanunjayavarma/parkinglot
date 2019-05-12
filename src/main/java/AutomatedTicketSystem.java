public class AutomatedTicketSystem {
    ParkingLot parkingLot;

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public void allocateTicket(Car car) {
        final int availableParkingSlotNumber = parkingLot.getNearestParkingSlotNumberToTheEntry();
        String ticket = genarateTicket(availableParkingSlotNumber, car.getRegistrationNumber());
        car.setTicket(ticket);
        parkingLot.allocateParkingSlot(availableParkingSlotNumber, car);
    }

    private String genarateTicket(int slotNumber, String registrationNumber) {
        final String ticket = slotNumber + registrationNumber;
        return ticket;
    }

    public void collectTicket(int parkingSlotNumber) {

    }
}
