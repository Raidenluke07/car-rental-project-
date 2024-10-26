package com.carrental;



import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Rental {
    private String passengerName;
    private int carId;
    private Date rentalDate;

    public Rental() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of renter: ");
        this.passengerName = scanner.nextLine();
        System.out.println("Enter car ID: ");
        this.carId = scanner.nextInt();
        System.out.println("Enter rental date (dd-MM-yyyy): ");
        String dateInput = scanner.next();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        this.rentalDate = dateFormat.parse(dateInput);
    }

    public boolean isAvailable() throws SQLException {
        CarDAO carDAO = new CarDAO();
        RentalDAO rentalDAO = new RentalDAO();
        int capacity = carDAO.getCapacity(this.carId);
        int bookedCount = rentalDAO.getBookedCount(this.carId, this.rentalDate);
        return bookedCount < capacity;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public int getCarId() {
        return carId;
    }

    public Date getRentalDate() {
        return rentalDate;
    }
}
