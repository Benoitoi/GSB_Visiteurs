package modele.metier;

/**
 * Classe représentant les praticiens
 *
 * @author bjaouen
 * @version 1.0 :
 *
 */

public class Praticien {
    int numero;
    String nom;
    String prenom;
    String adresse;
    int codePostal;
    String ville;
    float coefficientNotoriete;
    String typeCode;

    /**
     *
     * @param numero
     * @param nom
     * @param prenom
     * @param adresse
     * @param codePostal
     * @param ville
     * @param coefficientNotoriete
     * @param typeCode
     */
    public Praticien(int numero, String nom, String prenom, String adresse, int codePostal, String ville, float coefficientNotoriete, String typeCode) {
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.coefficientNotoriete = coefficientNotoriete;
        this.typeCode = typeCode;
    }

    /**
     *
     * @return
     */
    public int getNumero() {
        return numero;
    }

    /**
     *
     * @param numero
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    /**
     *
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     *
     * @return
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     *
     * @param prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     *
     * @return
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     *
     * @param adresse
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     *
     * @return
     */
    public int getCodePostal() {
        return codePostal;
    }

    /**
     *
     * @param codePostal
     */
    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    /**
     *
     * @return
     */
    public String getVille() {
        return ville;
    }

    /**
     *
     * @param ville
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     *
     * @return
     */
    public float getCoefficientNotoriete() {
        return coefficientNotoriete;
    }

    /**
     *
     * @param coefficientNotoriete
     */
    public void setCoefficientNotoriete(float coefficientNotoriete) {
        this.coefficientNotoriete = coefficientNotoriete;
    }

    /**
     *
     * @return
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     *
     * @param typeCode
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Praticien{" + "numero=" + numero + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", codePostal=" + codePostal + ", ville=" + ville + ", coefficientNotoriete=" + coefficientNotoriete + ", typeCode=" + typeCode + '}';
    }


}