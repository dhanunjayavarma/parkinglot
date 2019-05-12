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
        Car car=getCar();
        parkingLot.park(car);
        verify(ticketSystem,times(1)).allocateTicket(car);
    }

    @Test
    public void TestForIsParkingLotFull() {
        boolean parkingLotFull = parkingLot.isParkingLotFull();
        Assert.assertEquals(false, parkingLotFull);
    }

    @Test
    public void TestFor_IsParkingLotFull_Postive_Case(){
        ParkingLot parkingLot1= ParkingLot.getInstance(ticketSystem,0);
        boolean parkingLotFull=parkingLot1.isParkingLotFull();
        Assert.assertEquals(false,parkingLotFull);
    }

    @Test
    public void TestForUnPark() {
        parkingLot.unPark(1);
        verify(ticketSystem, times(1)).collectTicket(1);
    }

    public Car getCar(){
        return new Car("123",Colour.GREEN);
    }
}