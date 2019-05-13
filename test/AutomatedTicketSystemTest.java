import org.junit.Assert;

import org.junit.Before;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class AutomatedTicketSystemTest {
    AutomatedTicketSystem ticketSystem;
    ParkingLot parkingLot;

    @Before
    public void SetUp() {
        ticketSystem = new AutomatedTicketSystem();
        parkingLot = ParkingLot.getInstance(ticketSystem, 2);
    }

    @Test
    public void TestFor_AllocateTicket() {
        Car car = mock(Car.class);
        ticketSystem.setParkingLot(parkingLot);
        ticketSystem.issueTicket(car);
        Assert.assertEquals(false, parkingLot.isParkingLotEmpty());
    }

    @Test
    public void TestFor_CollectTicket() {
        ticketSystem.issueTicket(mock(Car.class));
        Assert.assertEquals(false, parkingLot.isParkingLotEmpty());
        ticketSystem.collectTicket(1);
        Assert.assertEquals(true, parkingLot.isParkingLotEmpty());
    }

}