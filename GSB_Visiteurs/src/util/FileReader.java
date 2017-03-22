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
            JOptionPane.showMessageDialog(null, "Erreur impossible de trouver le fichier des propriétés de la connection à la base de données Oracle\n" + ex, "Erreur ouverture", JOptionPane.ERROR_MESSAGE);
        }

        Properties propertiesJdbc = new Properties();// objet de propriétés pour JDBC
        InputStream input = Jdbc.class.getResourceAsStream("/" + bdd + ".properties");// flux de lecture des properties
        // Chargement des paramètres du fichier de properties
        try {
            propertiesJdbc.load(input);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erreur impossible de charger le fichier des propriétés de la connection à la base de données Oracle\n" + ex, "Erreur chargement", JOptionPane.ERROR_MESSAGE);
        }
        try {
            if (input != null) {
                input.close();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erreur impossible fermer le fichier des propriétés de la connection à la base de données Oracle\n" + ex, "Erreur fermeture", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(laVue, "Impossible de lire le fichier contenant l'état de l'objet visiteur." + ex, "Erreur lecture", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(laVue, "Erreur impossible de charger le fichier contenant l'état de l'objet du visiteur\n" + ex, "Erreur chargement", JOptionPane.ERROR_MESSAGE);
        }
        return visiteurLu;
    }

    private void close(ObjectInputStream ois, JFrame laVue) {
        try {
            if (ois != null) {
                ois.close();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(laVue, "Erreur impossible de fermer le fichier contenant l'état de l'objet du visiteur\n" + ex, "Erreur fermeture", JOptionPane.ERROR_MESSAGE);
        }
    }
}
