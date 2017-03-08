package modele.metier;

import java.io.Serializable;
import java.sql.Date;

/**
 * Classe représentant les rapports de visite
 *
 * @author bjaouen
 * @version 1.0 :
 *
 */

public class RapportVisite implements Serializable {
    private String matriculeVisiteur; 
    private int numeroRapport;
    private int numeroPraticien;
    private Date dateRapport;
    private String motifRapport;
    private String bilanRapport;
    
    /**
     * Constructeur avec les 6 attributs
     * @param matriculeVisiteur : matricule du visiteur
     * @param numeroRapport
     * @param numeroPraticien
     * @param dateRapport
     * @param motifRapport
     * @param bilanRapport
     */
    public RapportVisite(String matriculeVisiteur, int numeroRapport, int numeroPraticien, Date dateRapport, String motifRapport, String bilanRapport) {
        this.matriculeVisiteur = matriculeVisiteur;
        this.numeroRapport = numeroRapport;
        this.numeroPraticien = numeroPraticien;
        this.dateRapport = dateRapport;
        this.motifRapport = motifRapport;
        this.bilanRapport = bilanRapport;
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
    public int getNumeroPraticien() {
        return numeroPraticien;
    }

    /**
     *
     * @param numeroPraticien
     */
    public void setNumeroPraticien(int numeroPraticien) {
        this.numeroPraticien = numeroPraticien;
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
        return "RapportVisite{" + "matriculeVisiteur=" + matriculeVisiteur + ", numeroRapport=" + numeroRapport + ", numeroPraticien=" + numeroPraticien + ", dateRapport=" + dateRapport + ", motifRapport=" + motifRapport + ", bilanRapport=" + bilanRapport + '}';
    }
}