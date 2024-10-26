package carbooking;

public class Car extends Vehicle {
    // Constructor to initialize the car's name, model, and price per day.
    public Car(String name, String model, double pricePerDay) {
        super(name, model, pricePerDay);
    }

    // Overridden method to display car details.
    @Override
    public void displayDetails() {
        System.out.println("Car: " + getName() + " " + getModel() + " - RS: " + getPricePerDay() + " per day");
    }
}
