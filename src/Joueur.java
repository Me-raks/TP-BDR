import java.sql.*;

public class Joueur {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Tennis", "root", "password");
            Statement stmt = con.createStatement();
            ResultSet resultat = stmt.executeQuery("SELECT NOM, PRENOM FROM Joueur");
            while (resultat.next()) {
                String tuple = resultat.getString(1) + "        " + resultat.getString(2);
                System.out.println(tuple);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
