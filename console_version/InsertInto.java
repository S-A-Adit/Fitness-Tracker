package console_version;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertInto {

		// Define the connection URL for the database
	    final String CONNECTION = "jdbc:derby:FITNESSTRACKER;create=true";
        public void insertInto(){
        
        try (Connection conn = DriverManager.getConnection(CONNECTION); // Establish the database connection
             Statement statement = conn.createStatement()) { // Create a Statement object
             
        	
        	 // Insert data into the table "GYMMEMBERSTable"
            statement.executeUpdate("INSERT INTO FITNESSTRACKERDATASET VALUES " +
                    "(0214,56, 'Male',88.3, 1.71,3.5,3,1.69)");
            statement.executeUpdate("INSERT INTO FITNESSTRACKERDATASET VALUES " +
                    "(0271,46, 'Female',74.9, 1.53,2.1,2,1.3)");
            statement.executeUpdate("INSERT INTO FITNESSTRACKERDATASET VALUES " +
                    "(0293,56, 'Female',68.1, 1.66,2.3,2,1.11)");
            statement.executeUpdate("INSERT INTO FITNESSTRACKERDATASET VALUES " +
                    "(0255,38, 'Male',46.1, 1.79,2.8,1,0.64)");
            statement.executeUpdate("INSERT INTO FITNESSTRACKERDATASET VALUES " +
                    "(0283,36, 'Female',58.0, 1.68,2.7,3,1.59)");
            statement.executeUpdate("INSERT INTO FITNESSTRACKERDATASET VALUES " +
                    "(0238,36, 'Male',70.3, 1.72,2.3,2,1.27)");
            statement.executeUpdate("INSERT INTO FITNESSTRACKERDATASET VALUES " +
                    "(0247,20, 'Male',117.7, 1.81,3.3,2,1.35)");
            
            
            // Print confirmation message for successful data insertion   
            System.out.println("Rows added.\n");
            
        } catch (SQLException e) {
        // Handle SQL exceptions, such as connection issues or errors in the SQL commands	
            e.printStackTrace();
        }
    }

}
