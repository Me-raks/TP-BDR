import java.sql.*;

public class Generique2 {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL1 = "jdbc:mysql://localhost/Tennis";
    static final String DB_URL2 = "jdbc:mysql://localhost/DB2";

    static final String USER = "root";
    static final String PASS = "password";

    public static void main(String[] args) {
        Connection conn1 = null;
        Connection conn2 = null;
        Statement stmt1 = null;
        Statement stmt2 = null;

        try {
            Class.forName(JDBC_DRIVER);
            long startTime = System.currentTimeMillis();
            // Connexion à la base de données tennis
            System.out.println("Connexion à la base de données tennis...");
            conn1 = DriverManager.getConnection(DB_URL1, USER, PASS);
            System.out.println("Connexion réussie à la base de données tennis !");

            // Connexion à la base de données DB2
            System.out.println("Connexion à la base de données DB2...");
            conn2 = DriverManager.getConnection(DB_URL2, "root", "password");
            System.out.println("Connexion réussie à la base de données DB2 !");

            // Exécution de la requête
            System.out.println("Exécution de la requête...");
            stmt1 = conn1.createStatement();
            stmt2 = conn2.createStatement();
            String sql = "SELECT DISTINCT j.Nom, j.Nationalite, s.Nom, s.Nationalite " +
                    "FROM joueur j, gain g, Sponsor s " +
                    "WHERE j.NuJoueur = g.NuJoueur AND g.Sponsor = s.Nom " +
                    "ORDER BY j.Nom";
            ResultSet rs = stmt2.executeQuery(sql);
            System.out.printf("%-40s %-30s %-20s %-10s%n", "Nom_JOUEUR", "Nationalite_JOUEUR","nom_Sponsor","Nationalite_Sponsor");
            System.out.println("------------------------------------------------------------------------------------------------------");
            // Affichage des résultats
            while(rs.next()) {
                String nomJoueur = rs.getString("j.Nom");
                String nationaliteJoueur = rs.getString("j.Nationalite");
                String nomSponsor = rs.getString("s.Nom");
                String nationaliteSponsor = rs.getString("s.Nationalite");

                System.out.printf("%-40s %-30s %-20s %-10s%n", nomJoueur, nationaliteJoueur,nomSponsor,nationaliteSponsor);
            }
            long endTime = System.currentTimeMillis();
            System.err.println("Time taken: " + (endTime - startTime) + "ms");
            rs.close();

            // Fermeture des connexions
            stmt1.close();
            stmt2.close();
            conn1.close();
            conn2.close();
        } catch(SQLException se) {
            // Gestion des erreurs pour JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Gestion des erreurs pour Class.forName
            e.printStackTrace();
        } finally {
            // Fermeture des ressources
            try {
                if(stmt1 != null) stmt1.close();
                if(stmt2 != null) stmt2.close();
            } catch(SQLException se2) {
            } try {
                if(conn1 != null) conn1.close();
            } catch(SQLException se) {
                se.printStackTrace();
            } try {
                if(conn2 != null) conn2.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
