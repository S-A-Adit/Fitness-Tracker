package Deliverable_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DropTable {

		 // Define the connection URL for the database
		 final String CONNECTION = "jdbc:derby:GYMMEMBERSDATASET";
		 public void dropTable(){
		 try (Connection conn = DriverManager.getConnection(CONNECTION); // Establish the connection
		 Statement statement = conn.createStatement()) {   // Create a statement object
			 	 
		// SQL command to drop the table 'GYMMEMBERSTable'	 
		 statement.executeUpdate("drop table GYMMEMBERSTable");

		 // Notify the user that the table has been successfully dropped
		 System.out.println("\nGYMMEMBERSDATASET table dropped.");
		 } catch (SQLException e) {
		// Handle exceptions that might occur, such as table not existing
		 e.printStackTrace();
	}
 }

}
