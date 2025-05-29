package gui_version;

import javax.swing.*;
import java.sql.*;

public class Query {

    private static final String CONNECTION = "jdbc:derby:FITNESSTRACKER";

    public void showAgeRange(JTextArea outputArea) {
        try (Connection conn = DriverManager.getConnection(CONNECTION)) {
            Statement maxAgeStmt = conn.createStatement();
            Statement minAgeStmt = conn.createStatement();
            Statement ageRangeStmt = conn.createStatement();

            String maxAgeQuery = "SELECT * FROM FITNESSTRACKERDATASET WHERE AGE = (SELECT MAX(AGE) FROM FITNESSTRACKERDATASET)";
            ResultSet maxAgeResult = maxAgeStmt.executeQuery(maxAgeQuery);

            String minAgeQuery = "SELECT * FROM FITNESSTRACKERDATASET WHERE AGE = (SELECT MIN(AGE) FROM FITNESSTRACKERDATASET)";
            ResultSet minAgeResult = minAgeStmt.executeQuery(minAgeQuery);

            String ageRangeQuery = "SELECT MAX(AGE) - MIN(AGE) AS AgeRange FROM FITNESSTRACKERDATASET";
            ResultSet ageRangeResult = ageRangeStmt.executeQuery(ageRangeQuery);

            StringBuilder output = new StringBuilder();
            output.append("---- MAX AGE MEMBER ----\n");
            if (maxAgeResult.next()) {
                output.append(String.format("Gym ID: %d | Age: %d | Gender: %s | Weight: %.2f | Height: %.2f | Water Intake: %.2f | Experience: %d | Duration: %.2f\n",
                        maxAgeResult.getInt("GYMID"),
                        maxAgeResult.getInt("AGE"),
                        maxAgeResult.getString("GENDER"),
                        maxAgeResult.getFloat("WEIGHT"),
                        maxAgeResult.getFloat("HEIGHT"),
                        maxAgeResult.getFloat("WATERINTAKE"),
                        maxAgeResult.getInt("EXPERIENCELEVEL"),
                        maxAgeResult.getFloat("SESSIONDURATION")));
            }

            output.append("\n---- MIN AGE MEMBER ----\n");
            if (minAgeResult.next()) {
                output.append(String.format("Gym ID: %d | Age: %d | Gender: %s | Weight: %.2f | Height: %.2f | Water Intake: %.2f | Experience: %d | Duration: %.2f\n",
                        minAgeResult.getInt("GYMID"),
                        minAgeResult.getInt("AGE"),
                        minAgeResult.getString("GENDER"),
                        minAgeResult.getFloat("WEIGHT"),
                        minAgeResult.getFloat("HEIGHT"),
                        minAgeResult.getFloat("WATERINTAKE"),
                        minAgeResult.getInt("EXPERIENCELEVEL"),
                        minAgeResult.getFloat("SESSIONDURATION")));
            }

            output.append("\n---- AGE RANGE ----\n");
            if (ageRangeResult.next()) {
                int ageRange = ageRangeResult.getInt("AgeRange");
                output.append("Age Range: ").append(ageRange).append("\n");
            }

            // Set this partial output to JTextArea, keep for now before adding avg/count results
            outputArea.setText(output.toString());

            // Clean up
            maxAgeResult.close();
            minAgeResult.close();
            ageRangeResult.close();
            maxAgeStmt.close();
            minAgeStmt.close();
            ageRangeStmt.close();

        } catch (SQLException e) {
            outputArea.setText("Database error:\n" + e.getMessage());
        }
    }

    public void showAverageCount(JTextArea outputArea) {
        try (Connection conn = DriverManager.getConnection(CONNECTION);
             Statement statement = conn.createStatement()) {

            String statsSQL = "SELECT COUNT(*) AS TotalMembers, AVG(SESSIONDURATION) AS AverageSessionHours FROM FITNESSTRACKERDATASET";
            ResultSet resultSet = statement.executeQuery(statsSQL);

            if (resultSet.next()) {
                int totalMembers = resultSet.getInt("TotalMembers");
                double averageSessionHours = resultSet.getDouble("AverageSessionHours");

                // Append these stats to the existing content of the JTextArea
                String currentText = outputArea.getText();
                String statsText = String.format(
                    "\n---- TOTAL MEMBERS & AVERAGE SESSION HOURS ----\nTotal Gym Members: %d\nAverage Session Hours: %.3f\n",
                    totalMembers, averageSessionHours);

                outputArea.setText(currentText + statsText);
            }

        } catch (SQLException e) {
            outputArea.setText("Database error:\n" + e.getMessage());
        }
    }
}
