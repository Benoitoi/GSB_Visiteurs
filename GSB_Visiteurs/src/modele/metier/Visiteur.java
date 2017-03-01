package modele.metier;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe représentant les visiteurs
 *
 * @author kcloarec
 * @version 1.0 :
 *
 */

public class Visiteur implements Serializable {

    //touts les éléments de la table sont de type NVARCHAR2 sauf la date embauche
    private String matricule; 
    private String nom;
    private String prenom;
    private String adresse;
    private String codePostal;
    private String ville;
    private Date dateEmbauche;
    private String codeSecteur;
    private String codeLaboratoire;
    
    /**
     * Constructeur avec les 9 attributs
     * @param matricule : matricule du visiteur
     * @param nom
     * @param prenom
     * @param adresse
     * @param codePostal
     * @param ville
     * @param dateEmbauche
     * @param codeSecteur
     * @param codeLaboratoire
     */
    public Visiteur(String matricule, String nom, String prenom, String adresse, String codePostal, String ville, Date dateEmbauche, String codeSecteur, String codeLaboratoire) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.dateEmbauche = dateEmbauche;
        this.codeSecteur = codeSecteur;
        this.codeLaboratoire = codeLaboratoire;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
    
    public Date getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public String getCodeSecteur() {
        return codeSecteur;
    }

    public void setCodeSecteur(String codeSecteur) {
        this.codeSecteur = codeSecteur;
    }

    public String getCodeLaboratoire() {
        return codeLaboratoire;
    }

    public void setCodeLaboratoire(String codeLaboratoire) {
        this.codeLaboratoire = codeLaboratoire;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "Visiteur{" + "matricule=" + matricule + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", codePostal=" + codePostal + ", ville=" + ville + ", dateEmbauche=" + dateEmbauche + ", codeSecteur=" + codeSecteur + ", codeLaboratoire=" + codeLaboratoire + '}';
    }
}