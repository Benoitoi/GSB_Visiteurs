package modele.metier;

/**
 * Classe représentant les offres
 *
 * @author bjaouen
 * @version 1.0 :
 *
 */

public class Offre {
    String matriculeVisiteur;
    int numeroRapport;
    String medicament;
    int quantiteOffre;

    /**
     *
     * @param matriculeVisiteur
     * @param numeroRapport
     * @param medicament
     * @param quantiteOffre
     */
    public Offre(String matriculeVisiteur, int numeroRapport, String medicament, int quantiteOffre) {
        this.matriculeVisiteur = matriculeVisiteur;
        this.numeroRapport = numeroRapport;
        this.medicament = medicament;
        this.quantiteOffre = quantiteOffre;
    }

    /**
     *
     * @return
     */
    public String getMatriculeVisiteur() {
        return matriculeVisiteur;
    }

    /**
     *
     * @param matriculeVisiteur
     */
    public void setMatriculeVisiteur(String matriculeVisiteur) {
        this.matriculeVisiteur = matriculeVisiteur;
    }

    /**
     *
     * @return
     */
    public int getNumeroRapport() {
        return numeroRapport;
    }

    /**
     *
     * @param numeroRapport
     */
    public void setNumeroRapport(int numeroRapport) {
        this.numeroRapport = numeroRapport;
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
        return "Offre{" + "matriculeVisiteur=" + matriculeVisiteur + ", numeroRapport=" + numeroRapport + ", medicament=" + medicament + ", quantiteOffre=" + quantiteOffre + '}';
    }


}