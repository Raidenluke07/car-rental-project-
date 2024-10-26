package carbooking;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class VehicleTest {

    @Test
    public void testCarDetails() {
        Car car = new Car("Toyota", "Corolla", 2000);
        assertEquals("Toyota", car.getName());
        assertEquals("Corolla", car.getModel());
        assertEquals(2000, car.getPricePerDay(), 0.01);
        assertFalse(car.isRented());
    }

    @Test
    public void testTruckDetails() {
        Truck truck = new Truck("Ford", "F-150", 5000);
        assertEquals("Ford", truck.getName());
        assertEquals("F-150", truck.getModel());
        assertEquals(5000, truck.getPricePerDay(), 0.01);
        assertFalse(truck.isRented());
    }

    @Test
    public void testRentAndReturnVehicle() {
        Car car = new Car("Honda", "Civic", 1500);
        assertFalse(car.isRented());
        car.rent();
        assertTrue(car.isRented());
        car.returnVehicle();
        assertFalse(car.isRented());
    }

    @Test
    public void testCarDisplayDetails() {
        Car car = new Car("Nissan", "Altima", 1800);
        car.displayDetails();
     
    }

    @Test
    public void testTruckDisplayDetails() {
        Truck truck = new Truck("Chevrolet", "Silverado", 4500);
        truck.displayDetails();
     
    }

   

   
    
}
