public class ParkingLotDetalisVO {
    private String registrationNumber;
    private Colour colour;
    private int slotNumber;

    ParkingLotDetalisVO(int slotNumber,String registrationNumber, Colour colour){
        this.slotNumber=slotNumber;
        this.registrationNumber=registrationNumber;
        this.colour=colour;
    }
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public Colour getColour() {
        return colour;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

}
