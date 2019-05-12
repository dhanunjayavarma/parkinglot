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
        parkingLot = ParkingLot.getInstance(ticketSystem,3);
        parkingLot.ticketSystem = ticketSystem;
    }

    @Test
    public void TestPark() {
        Car car=new Car("123",Colour.GREEN);
        parkingLot.park(car);
        verify(ticketSystem,times(1)).allocateTicket(car);
    }

    @Test
    public void TestForIsParkingLotFull() {
        boolean parkingLotFull = parkingLot.isParkingLotFull();
        Assert.assertEquals(false, parkingLotFull);
    }

    @Test
    public void TestForIsParkingLotFull_When_ParkingLot_IS_Full() {

    }

    @Test
    public void TestForUnPark() {
        parkingLot.unPark(1);
        verify(ticketSystem, times(1)).collectTicket(1);
    }
}