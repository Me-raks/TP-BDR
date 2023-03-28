import java.sql.*;
import java.util.Scanner;

public class MaxPrime2 {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Tennis";
        String user = "root";
        String password = "password";
        String requete = "SELECT MAX(Prime) FROM Gain WHERE LieuTournoi=? AND Annee=?";

        try {
            // Connexion à la base de données
            Connection connexion = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion établie avec la base de données");

            // Définition de la requête paramétrée
            PreparedStatement pstmt = connexion.prepareStatement(requete);

            // Boucle pour demander à l'utilisateur les valeurs à chaque itération
            boolean continuer = true;
            while (continuer) {
                // Demander à l'utilisateur les valeurs pour la requête
                String lieu = demanderValeur("Lieu du tournoi : ");
                if (lieu.isEmpty()) {
                    continuer = false;
                    break;
                }
                int annee = Integer.parseInt(demanderValeur("Année du tournoi : "));

                // Affecter les valeurs aux paramètres de la requête
                pstmt.setString(1, lieu);
                pstmt.setInt(2, annee);

                // Exécuter la requête
                ResultSet resultat = pstmt.executeQuery();

                // Traiter le résultat
                if (resultat.next()) {
                    int primeMax = resultat.getInt(1);
                    System.out.println("La prime maximale pour le tournoi " + lieu + " en " + annee + " est de " + primeMax + " dollars");
                } else {
                    System.out.println("Aucun résultat trouvé pour cette requête");
                }

                resultat.close();
            }

            // Fermer la connexion à la base de données
            pstmt.close();
            connexion.close();
            System.out.println("Connexion fermée");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour demander une valeur à l'utilisateur via la console
    private static String demanderValeur(String message) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }
}
