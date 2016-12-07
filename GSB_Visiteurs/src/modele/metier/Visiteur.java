package modele.metier;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe représentant les adresses des clients de l'agence bancaire
 *
 * @author btssio
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
    
    @Override
    public String toString() {
        return "Visiteur{" + "matricule=" + matricule + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", codePostal=" + codePostal + ", ville=" + ville + ", dateEmbauche=" + dateEmbauche + ", codeSecteur=" + codeSecteur + ", codeLaboratoire=" + codeLaboratoire + '}';
    }
    
    
//    /**
//     *
//     * @return id : identifiant de l'adresse
//     */
//    public int getId() {
//        return id;
//    }
//
//    /**
//     *
//     * @param id : identifiant BDD de l'adresse
//     */
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    /**
//     * Constructeur avec 3 paramètres : sans l'identifiant
//     * @param rue
//     * @param cp
//     * @param ville
//     */
//    public Visiteur(String rue, String cp, String ville) {
//        this.rue = rue;
//        this.cp = cp;
//        this.ville = ville;
//    }
//
//    /**
//     *
//     * @return String attributs de l'adresse
//     */
//    @Override
//    public String toString() {
//        return ("Adresse{rue: " + this.getRue() + "\tcp: " + this.getCp() + "\tville: " + this.getVille()) + "}";
//    }
//
//    /**
//     *
//     * @return
//     */
//    public String getRue() {
//        return rue;
//    }
//
//    /**
//     *
//     * @param String adresse
//     */
//    public void setRue(String adresse) {
//        this.rue = adresse;
//    }
//
//    /**
//     *
//     * @return String code postal
//     */
//    public String getCp() {
//        return cp;
//    }
//
//    /**
//     *
//     * @param cp
//     */
//    public void setCp(String cp) {
//        this.cp = cp;
//    }
//
//    /**
//     *
//     * @return
//     */
//    public String getVille() {
//        return ville;
//    }
//
//    /**
//     *
//     * @param ville
//     */
//    public void setVille(String ville) {
//        this.ville = ville;
//    }
//    
//    /**
//     * Redéfinition de la méthode equals
//     * 
//     * @param adresse
//     * @return true si 2 adresses ont le même identifiant BDD
//     */
//    @Override
//    public boolean equals(Object adresse) {
//        if (adresse == null) {
//            return false;
//        } else if (adresse instanceof Visiteur) {
//            return this.id == ((Visiteur) adresse).id;
//        } else {
//            return false;
//        }
//    }
}