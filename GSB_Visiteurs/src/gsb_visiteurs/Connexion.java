/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsb_visiteurs;

import controleurs.CtrlConnexion;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modele.dao.Jdbc;

/**
 *
 * @author Benoit
 */
public class Connexion {

    public static Jdbc getInstance() {
        if(Jdbc.getInstance() == null){
            lancerConnexion();
        }
        return Jdbc.getInstance();
    }

    public static void lancerConnexion() {
        String bdd = null;
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(new FileInputStream("OracleBDD.data"));
            bdd = (String) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(CtrlConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }

        Properties propertiesJdbc = new Properties();// objet de propriétés pour JDBC
        InputStream input = Jdbc.class.getResourceAsStream("/" + bdd + ".properties");// flux de lecture des properties
        // Chargement des paramètres du fichier de properties
        try {
            propertiesJdbc.load(input);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erreur impossible de charger le fichier des propriétés de la connection à la base de données Oracle\n" + ex);
        }
        try {
            input.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erreur impossible fermer le fichier des propriétés de la connection à la base de données Oracle\n" + ex);
        }

        connect(propertiesJdbc.getProperty("pilote"), propertiesJdbc.getProperty("protocole"), propertiesJdbc.getProperty("url"), propertiesJdbc.getProperty("base"), propertiesJdbc.getProperty("utilisateur"), propertiesJdbc.getProperty("mdp"));
    }

    private static void connect(String pilote, String protocole, String url, String base, String utilisateur, String mdp) {
        DriverManager.setLoginTimeout(3);//temps d'attente de 3 seconde au lieu de 10 par défaut avant d'afficher echec de la connexion
        Jdbc.creer(pilote, protocole, url, base, utilisateur, mdp);
        try {
            Jdbc.getInstance().connecter();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Main - classe JDBC non trouvée");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Main - échec de connexion");
        }
    }
}
