/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsb_visiteurs;

import controleurs.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author pnoel
 */
public class GSB_Visiteurs {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CtrlPrincipal leControleurPrincipal = new CtrlPrincipal();
        // afficher la vue
        leControleurPrincipal.afficherConnexion();
        try {
            // Paramétrage de l'apparance système
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//getCrossPlatformLookAndFeelClassName
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(GSB_Visiteurs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
