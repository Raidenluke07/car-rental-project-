package com.carrental;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class RentalDAO {
    public RentalDAO() {
    }

    public int getBookedCount(int carId, Date date) throws SQLException {
        String query = "SELECT COUNT(passenger_name) FROM rental WHERE car_id = ? AND rental_date = ?";
        Connection con = DbConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, carId);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        pst.setDate(2, sqlDate);
        ResultSet rs = pst.executeQuery();
        
        if (rs.next()) {
            int count = rs.getInt(1);
            con.close();
            return count;
        } else {
            con.close();
            return 0;
        }
    }

    public void addRental(Rental rental) throws SQLException {
        String query = "INSERT INTO rental (passenger_name, car_id, rental_date) VALUES (?, ?, ?)";
        Connection con = DbConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, rental.getPassengerName());
        pst.setInt(2, rental.getCarId());
        java.sql.Date sqlDate = new java.sql.Date(rental.getRentalDate().getTime());
        pst.setDate(3, sqlDate);
        pst.executeUpdate();
        con.close();
    }
}
