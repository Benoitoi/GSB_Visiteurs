package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;
import vues.*;

/**
 * @author bjaouen
 * @version 7/12/2016 - 1.0
 */
public class CtrlMenuGeneral implements WindowListener {
    
    private VueMenuGeneral vue; // LA VUE
    private Ecouteur ecouteur = new Ecouteur(); //L'écouteur

    public CtrlMenuGeneral(VueMenuGeneral vue) {
        this.vue = vue;
        // le contrôleur écoute la vue
        this.vue.addWindowListener(this);
        // ajout des check box de la vue au listener
        vue.getjCheckBoxComptesRendus().addActionListener(ecouteur);
        vue.getjCheckBoxMedicaments().addActionListener(ecouteur);
        vue.getjCheckBoxPracticiens().addActionListener(ecouteur);
        vue.getjCheckBoxVisiteurs().addActionListener(ecouteur);
        vue.getjCheckBoxQuitter().addActionListener(ecouteur);
        vue.getjCheckBoxDeconnection().addActionListener(ecouteur);
    }

    // contrôle de la vue
    private class Ecouteur implements ActionListener {
    @Override
        public void actionPerformed(ActionEvent evenement) {
//            if (evenement.getSource() == vue.getjCheckBoxComptesRendus()) {
//                vue.getjCheckBoxComptesRendus().setSelected(false);
//                compteRendu();
//            }
            if (evenement.getSource() == vue.getjCheckBoxMedicaments()) {
                vue.getjCheckBoxMedicaments().setSelected(false);
                JOptionPane.showMessageDialog(null, "La fonctionnalité "+vue.getjCheckBoxMedicaments().getText()+ " n'est pas encore disponible.");
            }
            if (evenement.getSource() == vue.getjCheckBoxPracticiens()) {
                vue.getjCheckBoxPracticiens().setSelected(false);
                JOptionPane.showMessageDialog(null, "La fonctionnalité "+vue.getjCheckBoxPracticiens().getText()+ " n'est pas encore disponible.");
            }
            if (evenement.getSource() == vue.getjCheckBoxVisiteurs()) {
                vue.getjCheckBoxVisiteurs().setSelected(false);
                visiteur();
            }
            if (evenement.getSource() == vue.getjCheckBoxDeconnection()) {
                vue.getjCheckBoxDeconnection().setSelected(false);
                deconnection();
            }
            if (evenement.getSource() == vue.getjCheckBoxQuitter()) {
                vue.getjCheckBoxQuitter().setSelected(false);
                quitter();
            }
        }
    }
    /**
     * Ouvrir l'affichage des visiteurs
     */
    public void visiteur(){
        //
        VueVisiteur uneVue = new VueVisiteur();
        CtrlVisiteur unControleur = new CtrlVisiteur(uneVue);
        // afficher la vue
        uneVue.setVisible(true);
        vue.dispose();
    }
    /**
     * Se déconnecter, après demande de confirmation
     */
    public void deconnection(){
        // Confirmer avant de se déconnecter
        int rep = JOptionPane.showConfirmDialog(getVue(), "Se déconnecter\nEtes-vous sûr(e) ?", "GSB - Visiteurs", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (rep == JOptionPane.YES_OPTION) {
            // se déconnecter
            VueConnexion uneVue = new VueConnexion();
            CtrlConnexion unControleur = new CtrlConnexion(uneVue);
            // afficher la vue
            uneVue.setVisible(true);
            vue.dispose();
        }
    }
    /**
     * Quitter l'application, après demande de confirmation
     */
    private void quitter() {
        // Confirmer avant de quitter
        int rep = JOptionPane.showConfirmDialog(getVue(), "Quitter l'application\nEtes-vous sûr(e) ?", "GSB - Visiteurs", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (rep == JOptionPane.YES_OPTION) {
            // mettre fin à l'application
            System.exit(0);
        }
    }

    // ACCESSEURS et MUTATEURS
    public VueMenuGeneral getVue() {
        return vue;
    }
    
    public void setVue(VueMenuGeneral vue) {
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
