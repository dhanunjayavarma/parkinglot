import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;

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

    public void showRegistrationNumberOfCarsWithGivenColour(Colour colour) {
        List<String> registrationNumberList = getRegistrationNumberOfCarsWithGivenColour(colour);
        if (registrationNumberList.isEmpty()) {
            System.out.println("Not Found");
        } else {
            showList(registrationNumberList);
        }
    }

    private List<String> getRegistrationNumberOfCarsWithGivenColour(Colour colour) {
        List<String> registrationNumbersList = new ArrayList<String>();

        for (Map.Entry<Integer, ParkingLotDetalisVO> entry : parkingLotDetalis.entrySet()) {
            if (entry.getValue().getColour().equals(colour)) {
                registrationNumbersList.add(entry.getValue().getRegistrationNumber());
            }
        }
        return registrationNumbersList;
    }

    public void showSlotNumbersOfCarsWithGivenColour(Colour colour) {
        List<Integer> slotNumbersList = getSlotNumberOfCarsWithGivenColour(colour);
        if (slotNumbersList.isEmpty()) {
            System.out.println("Not Found");
        } else {
            showList(slotNumbersList);
        }
    }

    private List<Integer> getSlotNumberOfCarsWithGivenColour(Colour colour) {
        List<Integer> slotNumbersList = new ArrayList<Integer>();
        for (Map.Entry<Integer, ParkingLotDetalisVO> entry : parkingLotDetalis.entrySet()) {
            if (entry.getValue().getColour().equals(colour)) {
                slotNumbersList.add(entry.getValue().getSlotNumber());
            }
        }
        return slotNumbersList;
    }

    public void showSlotNumbersOfCarsWithGivenRegistrationNumber(String registrationNumber) {
        List<Integer> slotNumbersList = getSlotNumberOfCarsWithGivenRegistrationNumber(registrationNumber);
        if (slotNumbersList.isEmpty()) {
            System.out.println("Not Found");
        } else {
            showList(slotNumbersList);
        }

    }

    private List<Integer> getSlotNumberOfCarsWithGivenRegistrationNumber(String registrationNumber) {
        List<Integer> slotNumbersList;
        slotNumbersList = new ArrayList<Integer>();

        for (Map.Entry<Integer, ParkingLotDetalisVO> entry : parkingLotDetalis.entrySet()) {
            if (entry.getValue().getRegistrationNumber().equals(registrationNumber)) {
                slotNumbersList.add(entry.getValue().getSlotNumber());
            }
        }
        return slotNumbersList;
    }

    private void showList(List list) {
        for (int index = 0; index < list.size(); index++) {
            if (index == list.size() - 1) {
                System.out.println(list.get(index));
            } else {
                System.out.print(list.get(index) + ", ");
            }
        }
    }
}
