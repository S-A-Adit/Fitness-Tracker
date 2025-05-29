package gui_version;
import javax.swing.*;
import java.sql.*;

public class UpdateRow {
    // Define the database connection URL
    private static final String CONNECTION = "jdbc:derby:FITNESSTRACKER;create=true";

    public void updateRow() {
        try {
            // 1. Ask for GYMID
            String gymIdStr = JOptionPane.showInputDialog(null, "Enter GYMID of the row to update:");
            if (gymIdStr == null) return;  // user canceled
            int gymId = Integer.parseInt(gymIdStr.trim());

            
         // 2. Ask for each field, one dialog at a time
            int age = Integer.parseInt(requireNonNullInput("Enter new AGE:"));
            String gender = capitalize(requireNonNullInput("Enter new GENDER:")); // Capitalize gender input
            float weight = roundToTwo(Float.parseFloat(requireNonNullInput("Enter new WEIGHT:")));
            float height = roundToTwo(Float.parseFloat(requireNonNullInput("Enter new HEIGHT:")));
            float waterIntake = roundToTwo(Float.parseFloat(requireNonNullInput("Enter new WATER INTAKE:")));
            int experienceLevel = Integer.parseInt(requireNonNullInput("Enter new EXPERIENCE LEVEL:"));
            float sessionDuration = roundToTwo(Float.parseFloat(requireNonNullInput("Enter new SESSION DURATION:")));


            // 3. Build and execute the UPDATE statement
            String updateSQL = """
                UPDATE FITNESSTRACKERDATASET
                SET AGE = ?, GENDER = ?, WEIGHT = ?, HEIGHT = ?, WATERINTAKE = ?, EXPERIENCELEVEL = ?, SESSIONDURATION = ?
                WHERE GYMID = ?
                """;

            try (Connection conn = DriverManager.getConnection(CONNECTION);
                 PreparedStatement ps = conn.prepareStatement(updateSQL)) {

                ps.setInt(1, age);
                ps.setString(2, gender);
                ps.setFloat(3, weight);
                ps.setFloat(4, height);
                ps.setFloat(5, waterIntake);
                ps.setInt(6, experienceLevel);
                ps.setFloat(7, sessionDuration);
                ps.setInt(8, gymId);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Row updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(null,
                        "No row found with GYMID = " + gymId,
                        "Update Failed",
                        JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null,
                "Invalid number format. Please enter numeric values where required.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null,
                "Database error:\n" + sqle.getMessage(),
                "SQL Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    private static float roundToTwo(float value) {
        return (float) (Math.round(value * 100.0) / 100.0);
    }
    private static String capitalize(String input) {
        if (input == null || input.isEmpty()) return input;
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
    /**
     * Show an input dialog, ensure it's non-null/non-empty, or throw to abort.
     */
    private String requireNonNullInput(String prompt) {
        String input = JOptionPane.showInputDialog(null, prompt);
        if (input == null) {
            // user hit Cancel → abort entire update
            throw new NumberFormatException("Operation cancelled");
        }
        input = input.trim();
        if (input.isEmpty()) {
            throw new NumberFormatException("Empty input");
        }
        return input;
    }
}


