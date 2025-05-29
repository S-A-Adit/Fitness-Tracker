package Deliverable_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateRow {

   // Define the database connection URL
   final String CONNECTION = "jdbc:derby:FITNESSTRACKER;create=true";
		 
		 
	public void updateRow(){
		 
		  @SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
		  // Get user input for each attribute
	        System.out.print("Enter GYMID of the row to update: \n");
	        int gymId = keyboard.nextInt();
	        
	        System.out.print("Enter new AGE:\n");
	        int age = keyboard.nextInt();
	        
	        System.out.print("Enter new GENDER:\n");
	        String gender = keyboard.next();
	        
	        System.out.print("Enter new WEIGHT:\n");
	        float weight = keyboard.nextFloat();
	        
	        System.out.print("Enter new HEIGHT:\n");
	        float height = keyboard.nextFloat();
	        
	        System.out.print("Enter new WATER INTAKE:\n");
	        float waterIntake = keyboard.nextFloat();
	        
	        System.out.print("Enter new EXPERIENCE LEVEL:\n");
	        int experienceLevel = keyboard.nextInt();
	        
	        System.out.print("Enter new SESSION DURATION:\n");
	        float sessionDuration = keyboard.nextFloat();

	        try (Connection conn = DriverManager.getConnection(CONNECTION);
	             Statement statement = conn.createStatement()) {

	          // Update SQL using user input
	          String updateSQL = "UPDATE FITNESSTRACKERDATASET " +
	                               "SET AGE = " + age + ", " +
	                               "GENDER = '" + gender + "', " +
	                               "WEIGHT = " + weight + ", " +
	                               "HEIGHT = " + height + ", " +
	                               "WATERINTAKE = " + waterIntake + ", " +
	                               "EXPERIENCELEVEL = " + experienceLevel + ", " +
	                               "SESSIONDURATION = " + sessionDuration + " " +
	                               "WHERE GYMID = " + gymId;

	        	
	        	
	            // Execute the SQL command to create the table
	        	statement.executeUpdate(updateSQL);
	            
	            System.out.println("Row updated in FITNESSTRACKERDATASET table.");
	        } catch (SQLException e) {
	        // Handle exceptions that might occur, such as table not existing
	            e.printStackTrace();
	        }
	        }
	 }


