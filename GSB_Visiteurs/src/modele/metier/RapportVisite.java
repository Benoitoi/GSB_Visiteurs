package modele.metier;

import java.sql.Date;

/**
 * Classe représentant les rapports de visite
 *
 * @author bjaouen
 * @version 1.0 :
 *
 */

public class RapportVisite {
    private Visiteur visiteur; 
    private int numeroRapport;
    private Praticien praticien;
    private Date dateRapport;
    private String motifRapport;
    private String bilanRapport;
    
    /**
     * Constructeur avec les 6 attributs
     * @param visiteur
     * @param praticien
     * @param numeroRapport
     * @param dateRapport
     * @param motifRapport
     * @param bilanRapport
     */
    public RapportVisite(Visiteur visiteur, int numeroRapport, Praticien praticien, Date dateRapport, String motifRapport, String bilanRapport) {
        this.visiteur = visiteur;
        this.numeroRapport = numeroRapport;
        this.praticien = praticien;
        this.dateRapport = dateRapport;
        this.motifRapport = motifRapport;
        this.bilanRapport = bilanRapport;
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
    public Praticien getPraticien() {
        return praticien;
    }

    /**
     *
     * @param praticien
     */
    public void setPraticien(Praticien praticien) {
        this.praticien = praticien;
    }

    /**
     *
     * @return
     */
    public Date getDateRapport() {
        return dateRapport;
    }

    /**
     *
     * @param dateRapport
     */
    public void setDateRapport(Date dateRapport) {
        this.dateRapport = dateRapport;
    }

    /**
     *
     * @return
     */
    public String getMotifRapport() {
        return motifRapport;
    }

    /**
     *
     * @param motifRapport
     */
    public void setMotifRapport(String motifRapport) {
        this.motifRapport = motifRapport;
    }
    
    /**
     *
     * @return
     */
    public String getBilanRapport() {
        return bilanRapport;
    }

    /**
     *
     * @param bilanRapport
     */
    public void setBilanRapport(String bilanRapport) {
        this.bilanRapport = bilanRapport;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "RapportVisite{" + "matriculeVisiteur=" + visiteur.getMatricule() + ", numeroRapport=" + numeroRapport + ", numeroPraticien=" + praticien.getNumero() + ", dateRapport=" + dateRapport + ", motifRapport=" + motifRapport + ", bilanRapport=" + bilanRapport + '}';
    }
}