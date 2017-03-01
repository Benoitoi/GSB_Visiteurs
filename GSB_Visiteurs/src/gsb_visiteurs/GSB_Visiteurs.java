/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsb_visiteurs;

import controleurs.*;

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
        Connexion.lancerConnexion();
    }
}
