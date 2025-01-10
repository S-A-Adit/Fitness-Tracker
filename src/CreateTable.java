package Deliverable_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
   
    	 // Define the connection URL for the database
    	final String CONNECTION = "jdbc:derby:GYMMEMBERSDATASET;create=true";
    	
    	public  void createTable(){

        try (Connection conn = DriverManager.getConnection(CONNECTION); // Establish the connection
             Statement statement = conn.createStatement()) { // Create a statement object
        	
        	
        	// SQL command to create the 'GYMMEMBERSTable' table with the specified columns
            String createTableSQL = "CREATE TABLE GYMMEMBERSTable(" +
                                    "GYMID INT NOT NULL PRIMARY KEY, " +
                                    "AGE INT," +
                                    "GENDER VARCHAR(32)," +
                                    "WEIGHT FLOAT," +
                                    "HEIGHT FLOAT," +
                                    "WATERINTAKE FLOAT," +
                                    "EXPERIENCELEVEL INT," +
                                    "SESSIONDURATION FLOAT)";
            
            // Execute the SQL command to create the table
            statement.executeUpdate(createTableSQL);
            
         // Notify the user that the table has been successfully created
            System.out.println("GYM Members Dataset table created.\n");
        } catch (SQLException e) {
        	 // Print the stack trace if an SQL exception occurs
            e.printStackTrace();
        }
}}