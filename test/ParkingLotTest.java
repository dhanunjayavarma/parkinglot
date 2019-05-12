import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
public class ParkingLotTest {
    AutomatedTicketSystem ticketSystem;
    ParkingLot parkingLot;
    @Before
    public void SetUp(){
        parkingLot=ParkingLot.getInstance(3);
    }

    @Test
    public void TestPark(){
        ticketSystem=mock(AutomatedTicketSystem.class);
        parkingLot.ticketSystem=ticketSystem;
        parkingLot.park();
        verify(ticketSystem,times(1)).allocateParkingSlot();
    }
}