
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class CarDemo {
    public CarDemo() {
    }

    public static void main(String[] args) {
        CarDAO carDAO = new CarDAO();
        try {
            carDAO.displayCarInfo();
            int userOpt = 1;
            Scanner scanner = new Scanner(System.in);

            while (userOpt == 1) {
                System.out.println("Enter 1 to Rent a car and 2 to exit");
                userOpt = scanner.nextInt();
                if (userOpt == 1) {
                    
                        Rental rental = new Rental();
                        if (rental.isAvailable()) {
                            RentalDAO rentalDAO = new RentalDAO();
                            rentalDAO.addRental(rental);
                            System.out.println("Your rental is confirmed");
                        } else {
                            System.out.println("Sorry. Car is unavailable. Try another car or date.");
                        }
                    }
                }
            

            scanner.close();
        }  catch (SQLException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        
    }
}
}