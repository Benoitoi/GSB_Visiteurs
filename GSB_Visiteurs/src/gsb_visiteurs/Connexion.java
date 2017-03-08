/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsb_visiteurs;

import java.sql.SQLException;
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
            JOptionPane.showMessageDialog(null, "Main - classe JDBC non trouvée "+ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Main - échec de connexion "+ex);
        }
    }
}
