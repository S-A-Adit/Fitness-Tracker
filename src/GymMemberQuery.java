package Deliverable_3;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class GymMemberQuery {   


	    	
	    final String CONNECTION = "jdbc:derby:GYMMEMBERSDATASET";
	    
	    public void ageRange(){
	    
	    try (Connection conn = DriverManager.getConnection(CONNECTION)) {
	        // Use separate statements for each query
	        Statement maxAgeStmt = conn.createStatement();
	        Statement minAgeStmt = conn.createStatement();
	        Statement ageRangeStmt = conn.createStatement();

	        // Query to get the row with the highest age
	        String maxAgeQuery = "SELECT * FROM GYMMEMBERSTable WHERE AGE = (SELECT MAX(AGE) FROM GYMMEMBERSTable)";
	        ResultSet maxAgeResult = maxAgeStmt.executeQuery(maxAgeQuery);

	        // Query to get the row with the lowest age
	        String minAgeQuery = "SELECT * FROM GYMMEMBERSTable WHERE AGE = (SELECT MIN(AGE) FROM GYMMEMBERSTable)";
	        ResultSet minAgeResult = minAgeStmt.executeQuery(minAgeQuery);

	        // Query to calculate the age range
	        String ageRangeQuery = "SELECT MAX(AGE) - MIN(AGE) AS AgeRange FROM GYMMEMBERSTable";
	        ResultSet ageRangeResult = ageRangeStmt.executeQuery(ageRangeQuery);

	        // Print headers
	        System.out.println("|GYMID | AGE | GENDER  | WEIGHT  | HEIGHT | WATERINTAKE | EXPERIENCELEVEL | SESSIONDURATION|");
	        System.out.println("------------------------------------------------------------------------------------------");

	        // Display the row with the highest age
	        if (maxAgeResult.next()) {
	            System.out.printf("%5d | %3d | %-7s | %7.2f | %6.2f | %11.2f | %15d | %14.2f%n",
	                    maxAgeResult.getInt("GYMID"),
	                    maxAgeResult.getInt("AGE"),
	                    maxAgeResult.getString("GENDER"),
	                    maxAgeResult.getFloat("WEIGHT"),
	                    maxAgeResult.getFloat("HEIGHT"),
	                    maxAgeResult.getFloat("WATERINTAKE"),
	                    maxAgeResult.getInt("EXPERIENCELEVEL"),
	                    maxAgeResult.getFloat("SESSIONDURATION"));
	        }
	        

	        // Display the row with the lowest age
	        if (minAgeResult.next()) {
	            System.out.printf("%5d | %3d | %-7s | %7.2f | %6.2f | %11.2f | %15d | %14.2f%n",
	                    minAgeResult.getInt("GYMID"),
	                    minAgeResult.getInt("AGE"),
	                    minAgeResult.getString("GENDER"),
	                    minAgeResult.getFloat("WEIGHT"),
	                    minAgeResult.getFloat("HEIGHT"),
	                    minAgeResult.getFloat("WATERINTAKE"),
	                    minAgeResult.getInt("EXPERIENCELEVEL"),
	                    minAgeResult.getFloat("SESSIONDURATION"));
	        }
	        System.out.println("                                                   ");

	        // Display the age range
	        if (ageRangeResult.next()) {
	            int ageRange = ageRangeResult.getInt("AgeRange");
	            System.out.println("\nAge Range: " + ageRange + "\n");
	        }

	        // Close ResultSet and Statement objects
	        maxAgeResult.close();
	        minAgeResult.close();
	        ageRangeResult.close();
	        maxAgeStmt.close();
	        minAgeStmt.close();
	        ageRangeStmt.close();

	               
	 } catch (SQLException e) {
         e.printStackTrace();
     }
	}
  }	
