package gui_version;

import javax.swing.*;
import java.sql.*;

public class CreateTable {

    private static final String DB_URL = "jdbc:derby:FITNESSTRACKER;create=true";
    private static final String TABLE_NAME = "FITNESSTRACKERDATASET";

    public void createTable() {
        String createTableSQL = String.format("""
            CREATE TABLE %s (
                GYMID INT NOT NULL PRIMARY KEY,
                AGE INT,
                GENDER VARCHAR(32),
                WEIGHT FLOAT,
                HEIGHT FLOAT,
                WATERINTAKE FLOAT,
                EXPERIENCELEVEL INT,
                SESSIONDURATION FLOAT
            )
        """, TABLE_NAME);

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement statement = conn.createStatement()) {

            statement.executeUpdate(createTableSQL);
            JOptionPane.showMessageDialog(null, "GYM Members Dataset table created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            // Check for "table already exists" error code
            if ("X0Y32".equals(e.getSQLState())) {
                JOptionPane.showMessageDialog(null, "Table already exists.", "Notice", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error creating table: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
