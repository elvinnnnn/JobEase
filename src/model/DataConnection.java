package src.model;
import java.sql.*;

public class DataConnection {
    private static final String url = "jdbc:sqlite:jobEaseDB.db";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    
}
