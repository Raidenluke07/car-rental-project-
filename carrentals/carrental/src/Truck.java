public class Truck extends Vehicle {
    // Constructor to initialize the truck's name, model, and price per day.
    public Truck(String name, String model, double pricePerDay) {
        super(name, model, pricePerDay);
    }

    // Overridden method to display truck details.
    @Override
    public void displayDetails() {
        System.out.println("Truck: " + getName() + " " + getModel() + " - RS: " + getPricePerDay() + " per day");
    }
}
