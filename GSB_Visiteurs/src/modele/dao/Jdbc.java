/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.dao;

import controleurs.CtrlConnexion;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sun.tools.jar.Main;

/**
 *
 * @author Benoit
 */
public class Jdbc {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    }
    
    public static Connection getConnexion() {
        String bdd = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("OracleBDD.data"));
            bdd = (String) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(CtrlConnexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erreur impossible de charger le fichier contenant le nom de la connection à la base de données Oracle\n"+ex);
        }
        DriverManager.setLoginTimeout(3);
        Properties propertiesJdbc; // objet de propriétés pour JDBC
        InputStream input; // flux de lecture des properties
        // Chargement des paramètres du fichier de properties
        propertiesJdbc = new Properties();
        input = Main.class.getResourceAsStream("/"+bdd+".properties");
        try {
            propertiesJdbc.load(input);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erreur impossible de charger le fichier des propriétés de la connection à la base de données Oracle\n"+ex);
        }
        try {
            input.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erreur impossible fermer le fichier des propriétés de la connection à la base de données Oracle\n"+ex);
        }
        
        Connection connexion = null;
        try {
            Class.forName(propertiesJdbc.getProperty("pilote"));
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Main - classe JDBC non trouvée");
        }
        try {
            connexion = DriverManager.getConnection(propertiesJdbc.getProperty("protocole")+propertiesJdbc.getProperty("url")+propertiesJdbc.getProperty("base"), propertiesJdbc.getProperty("utilisateur"), propertiesJdbc.getProperty("mdp"));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Main - échec de connexion");
        }
        return connexion;
//        Connection connexion = null;
//        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//        } catch (ClassNotFoundException ex) {
//            JOptionPane.showMessageDialog(null, "Main - classe JDBC non trouvée");
//        }
//        try {
//            connexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "GSB", "gsb");
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Main - échec de connexion");
//        }
//        return connexion;
    }
}
