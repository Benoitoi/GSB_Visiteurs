package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
//import modele.dao.DaoVisiteur;
import modele.metier.Visiteur;
import vues.VueVisiteur;

/**
 * @author bjaouen
 * @version decemnre 2016 - 1
 */
public class CtrlVisiteur implements WindowListener {
    
    private VueVisiteur vue; // LA VUE
    //private Ecouteur ecouteur = new Ecouteur();
        
    public CtrlVisiteur(VueVisiteur vue) {
        this.vue = vue;
        // le contrôleur écoute la vue
        this.vue.addWindowListener(this);
//        vue.getjTextFieldVille().addActionListener(action);
//        vue.getjButtonRechercher().addActionListener(ecouteur);
//        // préparer l'état iniitial de la vue
//        rechercherToutes();
    }
    
//    public Action action = new AbstractAction() {   
//            public void actionPerformed(ActionEvent ae) {
//                rechercherVille(vue.getjTextFieldVilleText());
//            }
//    };
//    
//      private class Ecouteur implements ActionListener {
//     @Override
//        public void actionPerformed(ActionEvent evenement) {
//            if (evenement.getSource() == vue.getjButtonRechercher()) {
//                rechercherVille(vue.getjTextFieldVilleText());
//            }
//        }
//      }
//
//    // contrôle de la vue
//    /**
//     * Remplir le composant JTable avec les clients
//     *
//     * @param desAdresses liste des clients à afficher
//     */
//    private final void afficherLesAdresses(List<Visiteur> desAdresses) {
//        getVue().getModeleTableAdresses().setRowCount(0);
//        String[] titresColonnes = {"RUE", "CDP", "VILLE"};
//        getVue().getModeleTableAdresses().setColumnIdentifiers(titresColonnes);
//        String[] ligneDonnees = new String[3];
//        for (Visiteur uneAdresse : desAdresses) {
//            ligneDonnees[0] = uneAdresse.getRue();
//            ligneDonnees[1] = uneAdresse.getCp();
//            ligneDonnees[2] = uneAdresse.getVille();
//            getVue().getModeleTableAdresses().addRow(ligneDonnees);            
//        }       
//    }
//
//    // méthodes d'action
//    private void rechercherToutes() {
//        List<Visiteur> lesAdresses = null;
//        try {
//            lesAdresses = DaoVisiteur.selectAll();
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(getVue(), "CtrlLesAdresses - échec de sélection des clients");
//        }
//        afficherLesAdresses(lesAdresses);
//    }
//    
//    // méthodes d'action
//    public void rechercherVille(String ville) {
//        List<Visiteur> lesAdresses = null;
//        try {
//            lesAdresses = DaoVisiteur.selectAllByVille(ville);
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(getVue(), "CtrlLesAdresses - échec de sélection des clients"+ex);
//        }
//        afficherLesAdresses(lesAdresses);
//    }

    /**
     * Quitter l'application, après demande de confirmation
     */
    private void quitter() {
        // Confirmer avant de quitter
        int rep = JOptionPane.showConfirmDialog(getVue(), "Quitter l'application\nEtes-vous sûr(e) ?", "GSB - Visiteur", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (rep == JOptionPane.YES_OPTION) {
            // mettre fin à l'application
            System.exit(0);
        }
    }

    // ACCESSEURS et MUTATEURS
    public VueVisiteur getVue() {
        return vue;
    }
    
    public void setVue(VueVisiteur vue) {
        this.vue = vue;
    }

    // REACTIONS EVENEMENTIELLES
    @Override
    public void windowOpened(WindowEvent e) {
    }
    
    @Override
    public void windowClosing(WindowEvent e) {        
        quitter();
    }
    
    @Override
    public void windowClosed(WindowEvent e) {
    }
    
    @Override
    public void windowIconified(WindowEvent e) {
    }
    
    @Override
    public void windowDeiconified(WindowEvent e) {
    }
    
    @Override
    public void windowActivated(WindowEvent e) {
    }
    
    @Override
    public void windowDeactivated(WindowEvent e) {
    }
    
}
