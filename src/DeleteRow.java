package Deliverable_3;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteRow {
	
		// Define the database connection URL
		final String CONNECTION = "jdbc:derby:GYMMEMBERSDATASET;create=true";
		
		public void deleteRow(){
		
		// Initialize a Scanner to accept user input
		Scanner keyboard = new Scanner(System.in);
		
		// Get GYMID from the user to identify the row to delete
        
		 try (Connection conn = DriverManager.getConnection(CONNECTION)) { // Establish connection to the database
	            // Get GYMID from the user to identify the row to delete
	            System.out.print("Enter GYMID of the row to delete: ");
	            int gymId = keyboard.nextInt();

	            // Define the SQL command to delete a row based on the GYMID
	            String deleteSQL = "DELETE FROM GYMMEMBERSTable WHERE GYMID = ?";
	            try (PreparedStatement preparedStatement = conn.prepareStatement(deleteSQL)) {
	                // Set the GYMID value in the SQL query
	                preparedStatement.setInt(1, gymId);

	                // Execute the DELETE SQL command
	                int rowsAffected = preparedStatement.executeUpdate();

	                // Confirm whether the row was deleted
	                if (rowsAffected > 0) {
	                    System.out.println("\nRow with GYMID " + gymId + " deleted successfully.");
	                } else {
	                    System.out.println("No row found with GYMID " + gymId + ".");
	                }
	            }
	        } catch (SQLException e) {
	            // Handle any SQL exceptions that may occur, such as invalid SQL syntax or database issues
	            System.out.println("Error while trying to delete the row. Details:");
	            e.printStackTrace();
	        } catch (Exception e) {
	            // Handle any other unexpected exceptions
	            System.out.println("Unexpected error occurred. Details:");
	            e.printStackTrace();
	        }
	}

}
