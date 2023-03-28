import java.sql.*;

public class Generique {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Tennis";
        String user = "root";
        String password = "password";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            String query = "select * from Joueur where annaiss = 1972";

            PreparedStatement stmt = conn.prepareStatement(query);

            for (int i = 1; i < args.length; i++) {
                stmt.setString(i, args[i]);
            }

            ResultSet rs = stmt.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumns = rsmd.getColumnCount();
            String[] columnNames = new String[numColumns];
            int[] columnWidths = new int[numColumns];

            for (int i = 1; i <= numColumns; i++) {
                columnNames[i-1] = rsmd.getColumnName(i);
                columnWidths[i-1] = Math.max(columnNames[i-1].length(), rsmd.getColumnDisplaySize(i));
            }

            // Print the header row
            for (int i = 0; i < numColumns; i++) {
                System.out.format("%-" + columnWidths[i] + "s ", columnNames[i]);
            }
            System.out.println();
            System.out.println("---------------------------------------------------------------");
            // Print the rows
            while (rs.next()) {
                for (int i = 1; i <= numColumns; i++) {
                    System.out.format("%-" + columnWidths[i-1] + "s ", rs.getString(i));
                }
                System.out.println();
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
