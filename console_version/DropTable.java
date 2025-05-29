package console_version;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DropTable {

		 // Define the connection URL for the database
	final String CONNECTION = "jdbc:derby:FITNESSTRACKER;create=true";
	public void dropTable(){
		try (Connection conn = DriverManager.getConnection(CONNECTION);
	             Statement statement = conn.createStatement()) {

	            // SQL command to drop the table
	            statement.executeUpdate("DROP TABLE FITNESSTRACKERDATASET");

	            // Notify user
	            System.out.println("\nFITNESSTRACKERDATASET  dropped.");
	        } catch (SQLException e) {
	            // Gracefully handle case when table doesn't exist
	            if ("42Y55".equals(e.getSQLState())) {
	                System.out.println("Table does not exist — nothing to drop.");
	            } else {
	                e.printStackTrace();
	            }
	        }
 }

}
