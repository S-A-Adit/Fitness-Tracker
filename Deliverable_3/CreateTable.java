package Deliverable_3;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
    private static final String DB_URL = "jdbc:derby:FITNESSTRACKER;create=true";
    private static final String TABLE_NAME = "FITNESSTRACKERDATASET";

    public void createTable() {
        if (tableExists(TABLE_NAME)) {
            System.out.println("Table already exists — skipping creation.");
            return;
        }

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
            System.out.println("GYM Members Dataset table created.\n");

        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean tableExists(String tableName) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            DatabaseMetaData meta = conn.getMetaData();

            try (ResultSet tables = meta.getTables(null, "APP", tableName.toUpperCase(), null)) {
                return tables.next(); // true if table exists
            }

        } catch (SQLException e) {
            System.err.println("Error checking table existence: " + e.getMessage());
            return false;
        }
    }
}

