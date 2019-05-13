import org.junit.Assert;

import org.junit.Before;

import org.junit.Test;

import org.mockito.Mock;

import static org.mockito.Mockito.*;

public class ParkingLotTest {
    AutomatedTicketSystem ticketSystem;
    ParkingLot parkingLot;

    @Before
    public void SetUp() {
        ticketSystem = mock(AutomatedTicketSystem.class);
        parkingLot = ParkingLot.getInstance(ticketSystem, 3);
        parkingLot.ticketSystem = ticketSystem;
    }

    @Test
    public void TestPark() {
        Car car = getCar("123", Colour.WHITE);
        parkingLot.park(car);
        verify(ticketSystem, times(1)).issueTicket(car);
    }

    @Test
    public void TestForIsParkingLotFull() {
        boolean parkingLotFull = parkingLot.isParkingLotFull();
        Assert.assertEquals(false, parkingLotFull);
    }

    @Test
    public void TestForUnPark() {
        parkingLot.unPark(1);
        verify(ticketSystem, times(1)).collectTicket(1);
    }

    @Test
    public void TestFor_AllocateParkingSlot() {
        parkingLot.allocateParkingSlot(1, getCar("121", Colour.GREEN));
        parkingLot.allocateParkingSlot(2, getCar("121", Colour.GREEN));
        parkingLot.allocateParkingSlot(3, getCar("121", Colour.GREEN));
        Assert.assertEquals(true, parkingLot.isParkingLotFull());
        parkingLot.freeParkingSlot(1);
        parkingLot.freeParkingSlot(2);
        parkingLot.freeParkingSlot(3);
    }

    @Test
    public void TestFor_isParkingLotEmpty_PositiveCase() {
        boolean parkingLotEmpty = parkingLot.isParkingLotEmpty();
        Assert.assertEquals(true, parkingLotEmpty);
    }

    @Test
    public void TestFor_isParkingLotEmpty_NegativeCase() {
        parkingLot.allocateParkingSlot(1, getCar("121", Colour.GREEN));
        boolean parkingLotEmpty = parkingLot.isParkingLotEmpty();
        Assert.assertEquals(false, parkingLotEmpty);
        parkingLot.freeParkingSlot(1);
    }

    public Car getCar(String registrationNumber, Colour colour) {
        return new Car(registrationNumber, colour);
    }

}