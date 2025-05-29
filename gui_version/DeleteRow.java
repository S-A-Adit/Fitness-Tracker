package gui_version;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DeleteRow {
    
    // Define the database connection URL
    final String CONNECTION = "jdbc:derby:FITNESSTRACKER;create=true";
    
    public void deleteRow() {
        // Get GYMID using a GUI input dialog
        String gymIdStr = JOptionPane.showInputDialog(null, "Enter GYMID of the row to delete:", "Delete Row", JOptionPane.QUESTION_MESSAGE);

        // If the user cancels the dialog
        if (gymIdStr == null) return;

        try (Connection conn = DriverManager.getConnection(CONNECTION)) {
            int gymId = Integer.parseInt(gymIdStr); // Parse input
            
            String deleteSQL = "DELETE FROM FITNESSTRACKERDATASET WHERE GYMID = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(deleteSQL)) {
                preparedStatement.setInt(1, gymId);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Row with GYMID " + gymId + " deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No row found with GYMID " + gymId + ".", "Info", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid GYMID. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error occurred while deleting the row:\n" + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An unexpected error occurred:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
