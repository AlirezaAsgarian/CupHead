package  mainmodule.util.DatabaseConnectors;
import java.sql.*;

public class FirstExample {
    static final String DB_URL = "jdbc:mysql://localhost/alireza";
    static final String USER = "alireza";
    static final String PASS = "alireza";
    static final String QUERY = "SELECT * FROM alireza.new_table";

    public static void main(String[] args) throws SQLException {
        // Open a connection
        Driver driver = new com.mysql.jdbc.Driver();
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);) {
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                System.out.println(", Last: " + rs.getString("personName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}