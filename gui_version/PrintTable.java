package gui_version;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class PrintTable {
    final String CONNECTION = "jdbc:derby:FITNESSTRACKER;create=true";

    public void showTableInDialog() {
        try (Connection conn = DriverManager.getConnection(CONNECTION);
             Statement statement = conn.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM FITNESSTRACKERDATASET");

            // Column names (based on your schema)
            String[] columnNames = {
                "GYMID", "AGE", "GENDER", "WEIGHT", "HEIGHT", 
                "WATER INTAKE", "EXPERIENCE LEVEL", "SESSION DURATION"
            };

            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

            // Fill table rows
            while (resultSet.next()) {
                Object[] rowData = {
                    resultSet.getInt("GYMID"),
                    resultSet.getInt("AGE"),
                    resultSet.getString("GENDER"),
                    resultSet.getDouble("WEIGHT"),
                    resultSet.getDouble("HEIGHT"),
                    resultSet.getDouble("WATERINTAKE"),
                    resultSet.getInt("EXPERIENCELEVEL"),
                    resultSet.getDouble("SESSIONDURATION")
                };
                tableModel.addRow(rowData);
            }

            JTable table = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(800, 300));

            JOptionPane.showMessageDialog(null, scrollPane, "Fitness Tracker Data", JOptionPane.PLAIN_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public String getTableAsString() {
        StringBuilder sb = new StringBuilder();
        String DB_URL = "jdbc:derby:FITNESSTRACKER;create=true";
        String query = "SELECT * FROM FITNESSTRACKERDATASET";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            // Header
            for (int i = 1; i <= columnCount; i++) {
                sb.append(String.format("%-20s", meta.getColumnName(i)));
            }
            sb.append("\n");

            // Rows
            boolean hasRows = false;
            while (rs.next()) {
                hasRows = true;
                for (int i = 1; i <= columnCount; i++) {
                    String colName = meta.getColumnName(i);

                    switch (colName) {
                        case "GYMID":
                        case "AGE":
                        case "EXPERIENCELEVEL":
                            // Integers without decimal places
                            sb.append(String.format("%-20d", rs.getInt(i)));
                            break;

                        case "GENDER":
                            // String as is
                            sb.append(String.format("%-20s", rs.getString(i)));
                            break;

                        case "WEIGHT":
                        case "HEIGHT":
                        case "WATERINTAKE":
                            // Format with 1 decimal place
                            sb.append(String.format("%-20.1f", rs.getDouble(i)));
                            break;

                        case "SESSIONDURATION":
                            // Format with 2 decimal places as per example
                            sb.append(String.format("%-20.2f", rs.getDouble(i)));
                            break;

                        default:
                            // Fallback for any other column
                            sb.append(String.format("%-20s", rs.getString(i)));
                            break;
                    }
                }
                sb.append("\n");
            }

            if (!hasRows) {
                sb.append("Table created. No rows found.\n");
            }

        } catch (SQLException e) {
            sb.append("Error fetching table data: ").append(e.getMessage()).append("\n");
        }

        return sb.toString();
    }

    
    
}

