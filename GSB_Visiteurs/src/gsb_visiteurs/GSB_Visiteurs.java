/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsb_visiteurs;

import vues.*;
import controleurs.*;

/**
 *
 * @author btssio
 */
public class GSB_Visiteurs {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        VueConnexion uneVue = new VueConnexion();
        CtrlConnexion unControleur = new CtrlConnexion(uneVue);
        // afficher la vue
        uneVue.setVisible(true);
    }
    
}
