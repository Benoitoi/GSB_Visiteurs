package modele.metier;

/**
 * Classe représentant les offres
 *
 * @author bjaouen
 * @version 1.0 :
 *
 */

public class Offre {
    Visiteur visiteur;
    RapportVisite rapport;
    String medicament;
    int quantiteOffre;

    /**
     *
     * @param visiteur
     * @param rapport
     * @param medicament
     * @param quantiteOffre
     */
    public Offre(Visiteur visiteur, RapportVisite rapport, String medicament, int quantiteOffre) {
        this.visiteur = visiteur;
        this.rapport = rapport;
        this.medicament = medicament;
        this.quantiteOffre = quantiteOffre;
    }

    /**
     *
     * @return
     */
    public Visiteur getVisiteur() {
        return visiteur;
    }

    /**
     *
     * @param visiteur
     */
    public void setVisiteur(Visiteur visiteur) {
        this.visiteur = visiteur;
    }

    /**
     *
     * @return
     */
    public RapportVisite getRapport() {
        return rapport;
    }

    /**
     *
     * @param rapportVisite
     */
    public void setNumeroRapport(RapportVisite rapportVisite) {
        this.rapport = rapportVisite;
    }

    /**
     *
     * @return
     */
    public String getMedicament() {
        return medicament;
    }

    /**
     *
     * @param medicament
     */
    public void setMedicament(String medicament) {
        this.medicament = medicament;
    }

    /**
     *
     * @return
     */
    public int getQuantiteOffre() {
        return quantiteOffre;
    }

    /**
     *
     * @param quantiteOffre
     */
    public void setQuantiteOffre(int quantiteOffre) {
        this.quantiteOffre = quantiteOffre;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Offre{" + "matricule visiteur=" + visiteur.getMatricule() + ", numeroRapport=" + rapport.getNumeroRapport() + ", medicament=" + medicament + ", quantiteOffre=" + quantiteOffre + '}';
    }
}