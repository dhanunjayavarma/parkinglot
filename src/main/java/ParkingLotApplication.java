import java.io.BufferedReader;

import java.io.File;

import java.io.FileReader;

import java.io.IOException;

import java.util.ArrayList;

import java.util.List;

import java.util.Scanner;

public class ParkingLotApplication {
    private static List<String> inputCommands = new ArrayList<String>();
    private static AutomatedTicketSystem ticketSystem = new AutomatedTicketSystem();
    private static ParkingLot parkingLot = null;

    public static void main(String args[]) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press 1 To Run the Input File Or Press 2 To Give Commands From Promt");
        final int input = scanner.nextInt();
        if (input == 1) {
            readFromFile();
            excecuteFileCommands();
        } else if (input == 2) {
            System.out.println("Enter your Commands");
            String command = scanner.next();
            while (!command.equals("exit")) {
                excecuteCommand(command);
            }
        }
    }

    private static void readFromFile() throws IOException {

        try {
            File file = new File("bin/parking_lot file_inputs");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                inputCommands.add(line);
            }

        } catch (IOException ex) {
            System.err.println("An IOException was caught!");
            ex.printStackTrace();
        }

    }

    private static void excecuteFileCommands() {

        for (String command : inputCommands) {

            excecuteCommand(command);

        }
    }

    private static void excecuteCommand(String command) {

        if (command.startsWith("create")) {

            final String[] commandArray = command.split(" ");
            final String sizeOfParkingLotInString = commandArray[commandArray.length - 1];
            final int sizeOfParkingLot = (int) sizeOfParkingLotInString.charAt(0) - 48;
            parkingLot = ParkingLot.getInstance(ticketSystem, sizeOfParkingLot);

        } else if (command.startsWith("park")) {

            final String[] commandArray = command.split(" ");
            final String registrationNumber = commandArray[1];
            final Colour colour = Colour.valueOf(commandArray[2].toUpperCase());
            Car car = new Car(registrationNumber, colour,new Driver());
            parkingLot.park(car);

        } else if (command.startsWith("leave")) {

            final String[] commandArray = command.split(" ");
            final int slotNumber = commandArray[1].charAt(0) - 48;
            parkingLot.unPark(slotNumber);

        } else if (command.startsWith("status")) {

            parkingLot.showStatus();

        } else if (command.startsWith("registration")) {
            final String[] commandArray = command.split(" ");
            Colour colour = Colour.valueOf(commandArray[1].toUpperCase());
            ticketSystem.showRegistrationNumberOfCarsWithGivenColour(colour);
        } else if (command.startsWith("slot") && command.contains("car")) {
            final String[] commandArray = command.split(" ");
            Colour colour = Colour.valueOf(commandArray[1].toUpperCase());
            ticketSystem.showSlotNumbersOfCarsWithGivenColour(colour);
        } else if (command.startsWith("slot") && command.contains("registration")) {
            final String[] commandArray = command.split(" ");
            String registrationNumber = commandArray[1];
            ticketSystem.showSlotNumbersOfCarsWithGivenRegistrationNumber(registrationNumber);
        } else {
            System.out.println("Give right Command");
        }
    }


}

