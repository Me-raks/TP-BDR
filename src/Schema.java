import java.sql.*;

public class Schema {

    public static void main(String[] args) {
        String tableName = "Joueur";

        try {

            // Establish a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Tennis", "root", "password");

            // Create a statement object to execute the query
            Statement stmt = conn.createStatement();

            // Execute the query to get the column names and types for the specified table
            String query = "SELECT column_name, data_type FROM information_schema.columns WHERE table_name = '" + tableName + "'";
            ResultSet rs = stmt.executeQuery(query);


            System.out.println("java Schema " + tableName.toUpperCase());
            System.out.println("le Schema de " + tableName.toUpperCase() + " est :");
            System.out.println();
            System.out.printf("%-20s %-10s%n", "NOM", "TYPE");
            System.out.println("---------------------------------");
            // Print the column names and types
            while (rs.next()) {
                String columnName = rs.getString("column_name");
                String dataType = rs.getString("data_type");

                System.out.printf("%-20s %-10s%n", columnName.toUpperCase(), dataType.toUpperCase());
            }

            // Close the result set, statement, and connection
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
