package carbooking;

import java.sql.*;
import java.util.Scanner;
import java.sql.Connection;

import java.sql.SQLException;

public class CarRentalSystem {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Rent Vehicle");
            System.out.println("2. Return Vehicle");
            System.out.println("3. View Available Vehicles");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    rentVehicle();
                    break;
                case 2:
                    returnVehicle();
                    break;
                case 3:
                    viewAvailableVehicles();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
    }

    private static void rentVehicle() {
        System.out.println("Enter Vehicle Number:");
        int vehicleNumber = scanner.nextInt();

        System.out.println("Enter your name:");
        String customerName = scanner.next();

        System.out.println("Enter number of days:");
        int days = scanner.nextInt();

        try (Connection connection = JDBCUtils.getConnection()) {
            // Check if vehicle is already rented
            String checkQuery = "SELECT IsRented, PricePerDay FROM Vehicles WHERE VehicleID = ?";
            PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
            checkStmt.setInt(1, vehicleNumber);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                boolean isRented = rs.getBoolean("IsRented");
                if (isRented) {
                    System.out.println("Vehicle Already Rented");
                    return;
                }
                double pricePerDay = rs.getDouble("PricePerDay");
                double totalPrice = days * pricePerDay;

                // Rent the vehicle
                String rentVehicleQuery = "INSERT INTO Rentals (VehicleID, CustomerName, RentalDays, TotalPrice, RentalDate) VALUES (?, ?, ?, ?, CURDATE())";
                PreparedStatement rentStmt = connection.prepareStatement(rentVehicleQuery);
                rentStmt.setInt(1, vehicleNumber);
                rentStmt.setString(2, customerName);
                rentStmt.setInt(3, days);
                rentStmt.setDouble(4, totalPrice);
                rentStmt.executeUpdate();

                // Update vehicle status
                String updateVehicleQuery = "UPDATE Vehicles SET IsRented = TRUE WHERE VehicleID = ?";
                PreparedStatement updateStmt = connection.prepareStatement(updateVehicleQuery);
                updateStmt.setInt(1, vehicleNumber);
                updateStmt.executeUpdate();

                System.out.println("Vehicle Rented Successfully");
                System.out.println("Booking Details:");
                System.out.println("Customer Name: " + customerName);
                System.out.println("Vehicle ID: " + vehicleNumber);
                System.out.println("Rental Days: " + days);
                System.out.println("Total Price: RS: " + totalPrice);
            } else {
                System.out.println("Invalid Vehicle Number");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void returnVehicle() {
        System.out.println("Enter Vehicle Number:");
        int vehicleNumber = scanner.nextInt();

        try (Connection connection = JDBCUtils.getConnection()) {
            // Check if vehicle is rented
            String checkQuery = "SELECT IsRented FROM Vehicles WHERE VehicleID = ?";
            PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
            checkStmt.setInt(1, vehicleNumber);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                boolean isRented = rs.getBoolean("IsRented");
                if (!isRented) {
                    System.out.println("Vehicle Not Rented");
                    return;
                }

                // Update vehicle status
                String updateVehicleQuery = "UPDATE Vehicles SET IsRented = FALSE WHERE VehicleID = ?";
                PreparedStatement updateStmt = connection.prepareStatement(updateVehicleQuery);
                updateStmt.setInt(1, vehicleNumber);
                updateStmt.executeUpdate();

                System.out.println("Vehicle Returned Successfully");
            } else {
                System.out.println("Invalid Vehicle Number");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewAvailableVehicles() {
        System.out.println("Available Vehicles:");

        try (Connection connection = JDBCUtils.getConnection()) {
            String query = "SELECT VehicleID, Name, Model, PricePerDay FROM Vehicles WHERE IsRented = FALSE";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int vehicleId = rs.getInt("VehicleID");
                String name = rs.getString("Name");
                String model = rs.getString("Model");
                double pricePerDay = rs.getDouble("PricePerDay");
                System.out.println(vehicleId + ". " + name + " " + model + " - RS: " + pricePerDay + " per day");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println();
    }
}
