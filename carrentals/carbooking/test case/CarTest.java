package carbooking;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarTest {

    @Test
    public void testCarDisplayDetails() {
        Car car = new Car("Toyota", "Corolla", 2000);
        String expected = "Car: Toyota Corolla - RS: 2000.0 per day";
        assertEquals(expected, getDisplayDetailsOutput(car));
    }

    private String getDisplayDetailsOutput(Car car) {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        car.displayDetails();
        return outContent.toString().trim();
    }
}
