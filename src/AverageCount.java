package Deliverable_3;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AverageCount {
  final String CONNECTION = "jdbc:derby:GYMMEMBERSDATASET";
  	public void averageCount() {
        try (Connection conn = DriverManager.getConnection(CONNECTION); // Establish the database connection
                Statement statement = conn.createStatement()) { // Create a Statement object

               // SQL query to calculate the total count and average session duration
               String statsSQL = "SELECT COUNT(*) AS TotalMembers, AVG(SESSIONDURATION) AS AverageSessionHours FROM GYMMEMBERSTable";

               // Execute the query and retrieve the result set
               ResultSet resultSet = statement.executeQuery(statsSQL);

               // Process the result set to fetch the count and average session duration
               if (resultSet.next()) {
                   int totalMembers = resultSet.getInt("TotalMembers");
                   double averageSessionHours = resultSet.getDouble("AverageSessionHours");

                   // Print the results
                   System.out.println("Total Gym Members: " + totalMembers + "\n");
                   System.out.printf("Average Session Hours of Gym Members: %.3f%n", averageSessionHours);
               }

           } catch (SQLException e) {
               // Handle SQL exceptions, such as connection issues or query errors
               e.printStackTrace();
           }
  }
	  
	  
	  
  }

