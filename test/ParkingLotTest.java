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
        parkingLot.park();
        verify(ticketSystem, times(1)).allocateParkingSlot();
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
        parkingLot.unPark();
        verify(ticketSystem, times(1)).freeParkingSlot();
    }
}