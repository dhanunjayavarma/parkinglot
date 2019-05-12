import org.junit.Assert;
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
        ticketSystem=mock(AutomatedTicketSystem.class);
        parkingLot.ticketSystem=ticketSystem;
    }

    @Test
    public void TestFor_Park(){
        parkingLot.park();
        verify(ticketSystem,times(1)).allocateParkingSlot();
    }

    @Test
    public void TestFor_IsParkingLotFull(){
        boolean parkingLotFull=parkingLot.isParkingLotFull();
        Assert.assertEquals(false,parkingLotFull);
    }

    @Test
    public void TestForIsParkingLotFull_When_ParkingLot_IS_Full(){

    }

    @Test
    public void TestFor_UnPark(){
        parkingLot.unPark();
        verify(ticketSystem,times(1)).freeParkingSlot();
    }

    @Test
    public void TestFor_GetNearestParkingSlotNumberToTheEntry(){
        int parkingSlotNumber=parkingLot.getNearestParkingSlotNumberToTheEntry();
        Assert.assertEquals(1,parkingSlotNumber);
    }
}