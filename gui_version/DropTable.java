package gui_version;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DropTable {
    // Define the connection URL for the database
    final String CONNECTION = "jdbc:derby:FITNESSTRACKER;create=true";

    public void dropTable() {
        try (Connection conn = DriverManager.getConnection(CONNECTION);
             Statement statement = conn.createStatement()) {

            // SQL command to drop the table
            statement.executeUpdate("DROP TABLE FITNESSTRACKERDATASET");

            // GUI notification
            JOptionPane.showMessageDialog(null, "FITNESSTRACKERDATASET dropped successfully!");

        } catch (SQLException e) {
            if ("42Y55".equals(e.getSQLState())) {
                // Table does not exist
                JOptionPane.showMessageDialog(null,
                        "Table does not exist — nothing to drop.",
                        "Notice", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Show SQL error as dialog
                JOptionPane.showMessageDialog(null,
                        "An error occurred while dropping the table:\n" + e.getMessage(),
                        "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
