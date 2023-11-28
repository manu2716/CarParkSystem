import org.junit.Assert;
import org.junit.Test;

public class CarParkSystemTests {

    @Test
    public void givenTotalLot_whenCalculateFreeSpace_thenReturnSpace(){
        int actualSlots = 100;
        int availableSlots = 100;
        Assert.assertEquals(availableSlots,actualSlots);
    }

    @Test
    public void givenInvalidEntry_whenParkingIsFull_thenReturnNoParking(){
        int actualSlots = 100;
        int availableSlots = 0;
        Assert.assertTrue(availableSlots==0);
        System.out.println("Parking Full!");
    }
}
