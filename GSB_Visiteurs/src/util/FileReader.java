/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import controleurs.CtrlConnexion;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modele.dao.Jdbc;
import modele.metier.Visiteur;

/**
 *
 * @author Benoit
 */
public class FileReader {

    /**
     *
     * @return
     */
    public static Properties getBddProperties() {
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
        return propertiesJdbc;
    }

    /**
     *
     * @param laVue
     * @return
     */
    public static Visiteur getConnectedVisiteur(JFrame laVue) {
        Visiteur visiteurLu = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("visiteur.data"));
            visiteurLu = (Visiteur) ois.readObject();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(laVue, "Impossible de lire le fichier contenant l'état de l'objet visiteur." + ex);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(laVue, "Erreur impossible de charger le fichier contenant l'état de l'objet du visiteur\n" + ex);
        }
        return visiteurLu;
    }
}
