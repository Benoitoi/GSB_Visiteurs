package controleurs;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modele.metier.Visiteur;
import util.FileReader;
import vues.VueConnexion;
import vues.VueMenuGeneral;
import vues.VueVisiteur;
import vues.VueMedicament;
import vues.VuePraticien;
import vues.VueRapportVisite;

/**
 *
 * @author tlauterbach
 */
public class CtrlPrincipal {

    CtrlConnexion ctrlConnexion;
    CtrlMenuGeneral ctrlMenuGeneral;
    CtrlVisiteur ctrlVisiteur;
    CtrlRapportVisite ctrlRapportVisite;
    CtrlPraticien ctrlPraticien;
    CtrlMedicament ctrlMedicament;
    JFrame currentView;
    Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(
            new javax.swing.ImageIcon(getClass().getResource("/images/cursor.png")).getImage(),
            new Point(0, 0), "custom cursor");

    /**
     *
     */
    public CtrlPrincipal() {
    }

    /**
     *
     * @param wait
     */
    public void doWait(boolean wait) {
        if (wait) {
            getCurrentView().getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        } else {
            getCurrentView().getContentPane().setCursor(null);
        }
    }

    /**
     *
     */
    public void afficherConnexion() {
        if (this.ctrlMenuGeneral != null) {
            this.ctrlMenuGeneral.getVue().setVisible(false);
            this.ctrlConnexion.getVue().setBounds(this.ctrlMenuGeneral.getVue().getBounds());
        } else {

        }
        if (this.ctrlConnexion == null) {
            this.identification();
            this.ctrlConnexion.getVue().setLocationRelativeTo(null);
            //this.ctrlConnexion.getVue().getContentPane().setCursor(cursor);
        }
        this.ctrlConnexion.getVue().setVisible(true);
        setCurrentView(this.ctrlConnexion.getVue());
    }

    /**
     *
     * @param laVue
     */
    public void afficherMenuGeneral(JFrame laVue) {
        //toutes les vues qui font appel au menu general se ferme
        laVue.dispose();
        boolean first = false;
        if (this.ctrlMenuGeneral == null) {
            first = true;
            this.menuGeneral();
            //this.ctrlMenuGeneral.getVue().getContentPane().setCursor(cursor);
        }
        this.ctrlMenuGeneral.getVue().setVisible(true);
        this.ctrlMenuGeneral.getVue().setBounds(laVue.getBounds());
        setCurrentView(this.ctrlMenuGeneral.getVue());
        if (first) {
            Visiteur visiteurLu = FileReader.getConnectedVisiteur(this.ctrlMenuGeneral.getVue());
            JOptionPane.showMessageDialog(this.ctrlMenuGeneral.getVue(), "Bienvenue sur l'application GSB Visiteur Monsieur " + visiteurLu.getNom(), "Bienvenue", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     *
     */
    public void afficherVisiteur() {
        this.ctrlMenuGeneral.getVue().setVisible(false);
        if (this.ctrlVisiteur == null) {
            this.visiteur();
            //this.ctrlVisiteur.getVue().getContentPane().setCursor(cursor);
        }
        this.ctrlVisiteur.getVue().setVisible(true);
        this.ctrlVisiteur.getVue().setBounds(this.ctrlMenuGeneral.getVue().getBounds());
        setCurrentView(this.ctrlVisiteur.getVue());
    }

    /**
     *
     */
    public void afficherCompteRendu() {
        this.ctrlMenuGeneral.getVue().setVisible(false);
        if (this.ctrlRapportVisite == null) {
            this.rapportVisite();
            //this.ctrlRapportVisite.getVue().getContentPane().setCursor(cursor);
        }
        this.ctrlRapportVisite.getVue().setVisible(true);
        this.ctrlRapportVisite.getVue().setBounds(this.ctrlMenuGeneral.getVue().getBounds());
        setCurrentView(this.ctrlRapportVisite.getVue());
    }

    /**
     *
     * @param numPraticien
     */
    public void afficherPraticien(int numPraticien) {
        this.ctrlMenuGeneral.getVue().setVisible(false);
        if (this.ctrlPraticien == null) {
            this.praticien();
            //this.ctrlPraticien.getVue().getContentPane().setCursor(cursor);
        }
        if (numPraticien != -1) {
            ctrlPraticien.detailPraticien(numPraticien);
            this.ctrlPraticien.getVue().setBounds(this.ctrlRapportVisite.getVue().getBounds());
        } else {
            this.ctrlPraticien.getVue().setBounds(this.ctrlMenuGeneral.getVue().getBounds());
            if (this.ctrlPraticien.isDetailMode()) {
                this.ctrlPraticien.setDetailMode(false);
                this.ctrlPraticien.detailMode(false);
            }
        }

        this.ctrlPraticien.getVue().setVisible(true);
        setCurrentView(this.ctrlPraticien.getVue());
    }

    /**
     *
     * @param depotLegal
     */
    public void afficherMedicament(String depotLegal) {
        this.ctrlMenuGeneral.getVue().setVisible(false);
        if (this.ctrlMedicament == null) {
            this.medicament();
            //this.ctrlMedicament.getVue().getContentPane().setCursor(cursor);
        }
        if (depotLegal != null) {
            ctrlMedicament.detailMedicament(depotLegal);
            this.ctrlMedicament.getVue().setBounds(this.ctrlRapportVisite.getVue().getBounds());
        } else {
            this.ctrlMedicament.getVue().setBounds(this.ctrlMenuGeneral.getVue().getBounds());
        }
        this.ctrlMedicament.getVue().setVisible(true);
        setCurrentView(this.ctrlMedicament.getVue());
    }

    /**
     *
     * @param laVue
     */
    public void fermer(JFrame laVue) {
        //toutes les vues qui font appel se ferme
        laVue.dispose();
    }

    /**
     *
     */
    public void quitterApplication() {
        // Confirmer avant de quitter
        String ObjButtons[] = new String[]{"Oui", "Non"};
        int rep = JOptionPane.showOptionDialog(getCurrentView(), "Quitter l'application\nEtes-vous sûr(e) ?", "GSB - Visiteur", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, ObjButtons, ObjButtons);
        if (rep == JOptionPane.YES_OPTION) {
            // mettre fin à l'application
            System.exit(0);
        }
    }

    private void identification() {
        VueConnexion laVueConnexion = new VueConnexion();
        CtrlConnexion leControleurConnexion = new CtrlConnexion(laVueConnexion, this);
        setCtrlConnexion(leControleurConnexion);
    }

    private void menuGeneral() {
        VueMenuGeneral laVueMenuGeneral = new VueMenuGeneral();
        CtrlMenuGeneral leControleurMenuGeneral = new CtrlMenuGeneral(laVueMenuGeneral, this);
        setCtrlMenuGeneral(leControleurMenuGeneral);
    }

    private void visiteur() {
        VueVisiteur laVueVisiteur = new VueVisiteur();
        CtrlVisiteur leControleurVisiteur = new CtrlVisiteur(laVueVisiteur, this);
        setCtrlVisiteur(leControleurVisiteur);
    }

    private void rapportVisite() {
        VueRapportVisite laVueRapportVisite = new VueRapportVisite();
        CtrlRapportVisite leControleurRapportVisite = new CtrlRapportVisite(laVueRapportVisite, this);
        setCtrlRapportVisite(leControleurRapportVisite);
    }

    private void praticien() {
        VuePraticien laVuePraticien = new VuePraticien();
        CtrlPraticien leControleurPraticien = new CtrlPraticien(laVuePraticien, this);
        setCtrlPraticien(leControleurPraticien);
    }

    private void medicament() {
        VueMedicament laVueMedicament = new VueMedicament();
        CtrlMedicament leControleurMedicament = new CtrlMedicament(laVueMedicament, this);
        setCtrlMedicament(leControleurMedicament);
    }

    /**
     *
     * @return
     */
    public JFrame getCurrentView() {
        return currentView;
    }

    /**
     *
     * @param currentView
     */
    public void setCurrentView(JFrame currentView) {
        this.currentView = currentView;
    }

    /**
     *
     * @return
     */
    public CtrlConnexion getCtrlConnexion() {
        return ctrlConnexion;
    }

    /**
     *
     * @param ctrlConnexion
     */
    public void setCtrlConnexion(CtrlConnexion ctrlConnexion) {
        this.ctrlConnexion = ctrlConnexion;
    }

    /**
     *
     * @return
     */
    public CtrlMenuGeneral getCtrlMenuGeneral() {
        return ctrlMenuGeneral;
    }

    /**
     *
     * @param ctrlMenuGeneral
     */
    public void setCtrlMenuGeneral(CtrlMenuGeneral ctrlMenuGeneral) {
        this.ctrlMenuGeneral = ctrlMenuGeneral;
    }

    /**
     *
     * @return
     */
    public CtrlVisiteur getCtrlVisiteur() {
        return ctrlVisiteur;
    }

    /**
     *
     * @param ctrlVisiteur
     */
    public void setCtrlVisiteur(CtrlVisiteur ctrlVisiteur) {
        this.ctrlVisiteur = ctrlVisiteur;
    }

    /**
     *
     * @return
     */
    public CtrlRapportVisite getCtrlRapportVisite() {
        return ctrlRapportVisite;
    }

    /**
     *
     * @param ctrlRapportVisite
     */
    public void setCtrlRapportVisite(CtrlRapportVisite ctrlRapportVisite) {
        this.ctrlRapportVisite = ctrlRapportVisite;
    }

    /**
     *
     * @return
     */
    public CtrlPraticien getCtrlPraticien() {
        return ctrlPraticien;
    }

    /**
     *
     * @param ctrlPraticien
     */
    public void setCtrlPraticien(CtrlPraticien ctrlPraticien) {
        this.ctrlPraticien = ctrlPraticien;
    }

    /**
     *
     * @return
     */
    public CtrlMedicament getCtrlMedicament() {
        return ctrlMedicament;
    }

    /**
     *
     * @param ctrlMedicament
     */
    public void setCtrlMedicament(CtrlMedicament ctrlMedicament) {
        this.ctrlMedicament = ctrlMedicament;
    }
}
