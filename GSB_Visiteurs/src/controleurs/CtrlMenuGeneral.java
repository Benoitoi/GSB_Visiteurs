package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;
import vues.VueMenuGeneral;

/**
 * @author kcloarec
 * @version 7/12/2016 - 1.0
 */
public class CtrlMenuGeneral implements WindowListener, ActionListener {

    private VueMenuGeneral vue; // LA VUE
    private final CtrlPrincipal ctrlPrincipal;

    /**
     *
     * @param vue
     * @param ctrl
     */
    public CtrlMenuGeneral(VueMenuGeneral vue, CtrlPrincipal ctrl) {
        this.vue = vue;
        this.vue.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/images/gsb_logo.png")).getImage());
        this.ctrlPrincipal = ctrl;
        // le contrôleur écoute la vue
        this.vue.addWindowListener(this);
        // ajout des check box de la vue au listener
        vue.getjCheckBoxComptesRendus().addActionListener(this);
        vue.getjCheckBoxMedicaments().addActionListener(this);
        vue.getjCheckBoxPraticiens().addActionListener(this);
        vue.getjCheckBoxVisiteurs().addActionListener(this);
        vue.getjCheckBoxQuitter().addActionListener(this);
        vue.getjCheckBoxDeconnection().addActionListener(this);
    }

    // contrôle de la vue
    @Override
    public void actionPerformed(ActionEvent evenement) {
        if (evenement.getSource() == vue.getjCheckBoxComptesRendus()) {
            vue.getjCheckBoxComptesRendus().setSelected(false);
            ctrlPrincipal.afficherCompteRendu();
            //JOptionPane.showMessageDialog(vue, "La fonctionnalité " + vue.getjCheckBoxComptesRendus().getText() + " n'est pas encore disponible.");
        }
        if (evenement.getSource() == vue.getjCheckBoxMedicaments()) {
            vue.getjCheckBoxMedicaments().setSelected(false);
            ctrlPrincipal.afficherMedicament(null);
            //JOptionPane.showMessageDialog(vue, "La fonctionnalité " + vue.getjCheckBoxMedicaments().getText() + " n'est pas encore disponible.");
        }
        if (evenement.getSource() == vue.getjCheckBoxPraticiens()) {
            vue.getjCheckBoxPraticiens().setSelected(false);
            ctrlPrincipal.afficherPraticien(-1);
            //JOptionPane.showMessageDialog(vue, "La fonctionnalité " + vue.getjCheckBoxPraticiens().getText() + " n'est pas encore disponible.");
        }
        if (evenement.getSource() == vue.getjCheckBoxVisiteurs()) {
            vue.getjCheckBoxVisiteurs().setSelected(false);
            ctrlPrincipal.afficherVisiteur();
        }
        if (evenement.getSource() == vue.getjCheckBoxDeconnection()) {
            vue.getjCheckBoxDeconnection().setSelected(false);
            // Confirmer avant de se déconnecter
            String ObjButtons[] = new String[]{"Oui", "Non"};
            int rep = JOptionPane.showOptionDialog(getVue(), "Se déconnecter\nEtes-vous sûr(e) ?", "GSB - Visiteur", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, ObjButtons, ObjButtons);
            if (rep == JOptionPane.YES_OPTION) {
                // se déconnecter
                ctrlPrincipal.afficherConnexion();
            }
        }
        if (evenement.getSource() == vue.getjCheckBoxQuitter()) {
            vue.getjCheckBoxQuitter().setSelected(false);
            ctrlPrincipal.quitterApplication();
        }
    }

    // ACCESSEURS et MUTATEURS
    /**
     *
     * @return
     */
    public VueMenuGeneral getVue() {
        return vue;
    }

    /**
     *
     * @param vue
     */
    public void setVue(VueMenuGeneral vue) {
        this.vue = vue;
    }

    // REACTIONS EVENEMENTIELLES
    /**
     *
     * @param e
     */
    @Override
    public void windowOpened(WindowEvent e) {
    }

    /**
     *
     * @param e
     */
    @Override
    public void windowClosing(WindowEvent e) {
        ctrlPrincipal.quitterApplication();
    }

    /**
     *
     * @param e
     */
    @Override
    public void windowClosed(WindowEvent e) {
    }

    /**
     *
     * @param e
     */
    @Override
    public void windowIconified(WindowEvent e) {
    }

    /**
     *
     * @param e
     */
    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    /**
     *
     * @param e
     */
    @Override
    public void windowActivated(WindowEvent e) {
    }

    /**
     *
     * @param e
     */
    @Override
    public void windowDeactivated(WindowEvent e) {
    }

}
