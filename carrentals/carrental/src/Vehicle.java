public abstract class Vehicle {
    private String name;
    private String model;
    private double pricePerDay;
    private boolean isRented;
    

    public Vehicle(String name, String model, double pricePerDay) {
        this.name = name;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.isRented = false;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public boolean isRented() {
        return isRented;
    }

    public void rent() {
        this.isRented = true;
    }

    public void returnVehicle() {
        this.isRented = false;
    }

    public abstract void displayDetails();
}
