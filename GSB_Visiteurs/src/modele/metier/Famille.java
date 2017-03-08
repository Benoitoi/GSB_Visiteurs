package modele.metier;

/**
 * Classe représentant les familles de médicaments
 *
 * @author bjaouen
 * @version 1.0 :
 *
 */

public class Famille {
    String codeFamille;
    String libelleFamille;

    /**
     *
     * @param codeFamille
     * @param libelleFamille
     */
    public Famille(String codeFamille, String libelleFamille) {
        this.codeFamille = codeFamille;
        this.libelleFamille = libelleFamille;
    }

    /**
     *
     * @return
     */
    public String getCodeFamille() {
        return codeFamille;
    }

    /**
     *
     * @param codeFamille
     */
    public void setCodeFamille(String codeFamille) {
        this.codeFamille = codeFamille;
    }

    /**
     *
     * @return
     */
    public String getLibelleFamille() {
        return libelleFamille;
    }

    /**
     *
     * @param libelleFamille
     */
    public void setLibelleFamille(String libelleFamille) {
        this.libelleFamille = libelleFamille;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Famille{" + "codeFamille=" + codeFamille + ", libelleFamille=" + libelleFamille + '}';
    }
}