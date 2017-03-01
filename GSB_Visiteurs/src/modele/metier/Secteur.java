package modele.metier;

/**
 * Classe représentant les secteurs
 *
 * @author bjaouen
 * @version 1.0 :
 *
 */
public class Secteur {

    private String codeSecteur;
    private String libelleSecteur;

    public Secteur(String codeSecteur, String libelleSecteur) {
        this.codeSecteur = codeSecteur;
        this.libelleSecteur = libelleSecteur;
    }

    public String getCodeSecteur() {
        return codeSecteur;
    }

    public void setCodeSecteur(String codeSecteur) {
        this.codeSecteur = codeSecteur;
    }

    public String getLibelleSecteur() {
        return libelleSecteur;
    }

    public void setLibelleSecteur(String libelleSecteur) {
        this.libelleSecteur = libelleSecteur;
    }

    @Override
    public String toString() {
        return "Secteur{" + "codeSecteur=" + codeSecteur + ", libelleSecteur=" + libelleSecteur + '}';
    }
}
