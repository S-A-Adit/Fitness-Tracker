package console_version;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class RetrieveTable {
	// Define the connection URL for the database
	final String CONNECTION = "jdbc:derby:FITNESSTRACKER;create=true";

    public void printTable() {
        try (Connection conn = DriverManager.getConnection(CONNECTION); // Establish the database connection
             Statement statement = conn.createStatement()) { // Create a Statement object

            // Execute a query to retrieve all rows from GYMMEMBERSTable
            ResultSet resultSet = statement.executeQuery("SELECT * FROM FITNESSTRACKERDATASET");

            // Print the column headers with proper spacing
            System.out.println("| GYMID | AGE | GENDER  | WEIGHT  | HEIGHT | WATER INTAKE | EXPERIENCE LEVEL | SESSION DURATION |");
            System.out.println("-----------------------------------------------------------------------------------------------");

            // Iterate over the result set and print each row
            while (resultSet.next()) {
                int id = resultSet.getInt(1); // Gym ID
                int age = resultSet.getInt(2); // Age
                String gender = resultSet.getString(3); // Gender
                double weight = resultSet.getDouble(4); // Weight
                double height = resultSet.getDouble(5); // Height
                double waterIntake = resultSet.getDouble(6); // Water Intake
                int experienceLevel = resultSet.getInt(7); // Experience Level
                double sessionDuration = resultSet.getDouble(8); // Session Duration

                // Print each row with the new format
                System.out.printf("| %5d | %3d | %-7s | %7.1f | %6.2f | %12.2f | %16d | %16.2f |%n",
                                  id, age, gender, weight, height, waterIntake, experienceLevel, sessionDuration);
            }
      

        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace();
        }
    }


}
