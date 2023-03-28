import java.sql.*;
import java.util.Scanner;
public class MaxPrime {
    public static void main(String[] args) {
        try {
            // Chargement du pilote JDBC


            // Paramètres de connexion à la base de données
            String server = "localhost";
            String port = "3306";
            String database = "Tennis";
            String user = "root";
            String password = "password";

            // Ouverture de la connexion à la base de données
            String url = "jdbc:mysql://" + server + ":" + port + "/" + database;
            Connection connexion = DriverManager.getConnection(url, user, password);

            // Lecture de l'année saisie par l'utilisateur
            System.out.println("Entrez l'année pour laquelle vous souhaitez calculer la prime maximale :");
            Scanner scanner = new Scanner(System.in);
            int annee = scanner.nextInt();

            // Préparation de la requête SQL avec un paramètre pour l'année
            String requete = "SELECT MAX(Prime) AS PrimeMax FROM Gain WHERE Annee = ?";
            PreparedStatement preparedStatement = connexion.prepareStatement(requete);
            preparedStatement.setInt(1, annee);

            // Exécution de la requête SQL
            ResultSet resultat = preparedStatement.executeQuery();

            // Affichage de la prime maximale pour l'année saisie
            if (resultat.next()) {
                double primeMax = resultat.getDouble("PrimeMax");
                System.out.println("La prime maximale pour l'année " + annee + " est de " + primeMax + " euros.");
            } else {
                System.out.println("Aucun enregistrement trouvé pour l'année " + annee + ".");
            }

            // Fermeture des ressources
            resultat.close();
            preparedStatement.close();
            connexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
