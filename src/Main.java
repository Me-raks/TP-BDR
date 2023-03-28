import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Tennis", "root", "password");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Joueur");
            while (rs.next()) {
                System.out.println(rs.getString("column1") + " " + rs.getString("column2"));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
