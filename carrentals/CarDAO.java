
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarDAO {
    public CarDAO() {
    }

    public void displayCarInfo() throws SQLException {
        String query = "SELECT * FROM car";
        Connection con = DbConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            System.out.println("Car ID: " + rs.getInt("id"));
            System.out.println("Model: " + rs.getString("model"));
            System.out.println("Capacity: " + rs.getInt("capacity"));
            System.out.println("------------------------------------------");
        }

        con.close();
    }

    public int getCapacity(int id) throws SQLException {
        String query = "SELECT capacity FROM car WHERE id = ?";
        Connection con = DbConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            int capacity = rs.getInt("capacity");
            con.close();
            return capacity;
        } else {
            con.close();
            throw new SQLException("Car ID " + id + " does not exist.");
        }
    }
}
