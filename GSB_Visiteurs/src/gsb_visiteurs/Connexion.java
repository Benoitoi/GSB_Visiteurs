/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsb_visiteurs;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modele.dao.Jdbc;

/**
 *
 * @author Benoit
 */
public class Connexion {

    /**
     *
     */
    public static void lancerConnexion() {
        Jdbc.creer();
        connect();
    }

    private static void connect() {
        try {
            Jdbc.getInstance().connecter();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex, "Main - classe JDBC non trouvée " , JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Main - échec de connexion " , JOptionPane.ERROR_MESSAGE);
        }
    }
}
