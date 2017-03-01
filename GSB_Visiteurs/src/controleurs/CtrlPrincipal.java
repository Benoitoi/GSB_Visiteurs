package controleurs;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import vues.VueConnexion;
//import vues.VueMedicament;
import vues.VueMenuGeneral;
//import vues.VuePraticien;
//import vues.VueRapportVisite;
import vues.VueVisiteur;

/**
 *
 * @author tlauterbach
 */
public class CtrlPrincipal {

    CtrlConnexion ctrlConnexion;
    CtrlMenuGeneral ctrlMenuGeneral;
    CtrlVisiteur ctrlVisiteur;
    /*CtrlRapportVisite ctrlRapportVisite;
     CtrlPraticien ctrlPraticien;
     CtrlMedicament ctrlMedicament;*/

    public void afficherConnexion() {
        if (this.ctrlMenuGeneral != null) {
            this.ctrlMenuGeneral.getVue().setVisible(false);
        }
        this.identification();
        this.ctrlConnexion.getVue().setVisible(true);
    }

    public void afficherMenuGeneral(JFrame laVue) {
        //toutes les vues qui font appel au menu general se ferme
        laVue.dispose();
        if (this.ctrlMenuGeneral == null) {
            this.menuGeneral();
        }
        this.ctrlMenuGeneral.getVue().setVisible(true);
    }

    public void afficherVisiteur() {
        this.ctrlMenuGeneral.getVue().setVisible(false);
        if (this.ctrlVisiteur == null) {
            this.visiteur();
        }
        this.ctrlVisiteur.getVue().setVisible(true);
    }

    /* public void afficherCompteRendu() {
     this.ctrlMenuGeneral.getVue().setVisible(false);
     if (this.ctrlRapportVisite == null) {
     this.rapportVisite();
     }
     this.ctrlRapportVisite.getVue().setVisible(true);
     }

     public void afficherPraticien(int numPraticien) {
     this.ctrlMenuGeneral.getVue().setVisible(false);
     if (this.ctrlPraticien == null) {
     this.praticien();
     }
     if (numPraticien != -1) {
     ctrlPraticien.detailPraticien(numPraticien);
     }
     this.ctrlPraticien.getVue().setVisible(true);
     }

     public void afficherMedicament(String depotLegal) {
     this.ctrlMenuGeneral.getVue().setVisible(false);
     if (this.ctrlMedicament == null) {
     this.medicament();
     }
     if (depotLegal != null) {
     ctrlMedicament.detailMedicament(depotLegal);
     }
     this.ctrlMedicament.getVue().setVisible(true);
     }*/
    public void fermer(JFrame laVue) {
        //toutes les vues qui font appel se ferme
        laVue.dispose();
    }

    public void quitterApplication() {
        // Confirmer avant de quitter
        int rep = JOptionPane.showConfirmDialog(null, "Quitter l'application\nEtes-vous sûr(e) ?", "GSB - Visiteur", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
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

    /*private void rapportVisite() {
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
     }*/
    public CtrlConnexion getCtrlConnexion() {
        return ctrlConnexion;
    }

    public void setCtrlConnexion(CtrlConnexion ctrlConnexion) {
        this.ctrlConnexion = ctrlConnexion;
    }

    public CtrlMenuGeneral getCtrlMenuGeneral() {
        return ctrlMenuGeneral;
    }

    public void setCtrlMenuGeneral(CtrlMenuGeneral ctrlMenuGeneral) {
        this.ctrlMenuGeneral = ctrlMenuGeneral;
    }

    public CtrlVisiteur getCtrlVisiteur() {
        return ctrlVisiteur;
    }

    public void setCtrlVisiteur(CtrlVisiteur ctrlVisiteur) {
        this.ctrlVisiteur = ctrlVisiteur;
    }

    /* public CtrlRapportVisite getCtrlRapportVisite() {
     return ctrlRapportVisite;
     }

     public void setCtrlRapportVisite(CtrlRapportVisite ctrlRapportVisite) {
     this.ctrlRapportVisite = ctrlRapportVisite;
     }

     public CtrlPraticien getCtrlPraticien() {
     return ctrlPraticien;
     }

     public void setCtrlPraticien(CtrlPraticien ctrlPraticien) {
     this.ctrlPraticien = ctrlPraticien;
     }

     public CtrlMedicament getCtrlMedicament() {
     return ctrlMedicament;
     }

     public void setCtrlMedicament(CtrlMedicament ctrlMedicament) {
     this.ctrlMedicament = ctrlMedicament;
     }*/
}
