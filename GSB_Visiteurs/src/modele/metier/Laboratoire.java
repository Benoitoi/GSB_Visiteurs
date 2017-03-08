package modele.metier;

/**
 * Classe représentant les laboratoires
 *
 * @author bjaouen
 * @version 1.0 :
 *
 */

public class Laboratoire {
    private String codeLaboratoire;
    private String nomLaboratoire;
    private String chefDeVente;

    /**
     *
     * @param codeLaboratoire
     * @param nomLaboratoire
     * @param chefDeVente
     */
    public Laboratoire(String codeLaboratoire, String nomLaboratoire, String chefDeVente) {
        this.codeLaboratoire = codeLaboratoire;
        this.nomLaboratoire = nomLaboratoire;
        this.chefDeVente = chefDeVente;
    }

    /**
     *
     * @return
     */
    public String getCodeLaboratoire() {
        return codeLaboratoire;
    }

    /**
     *
     * @param codeLaboratoire
     */
    public void setCodeLaboratoire(String codeLaboratoire) {
        this.codeLaboratoire = codeLaboratoire;
    }

    /**
     *
     * @return
     */
    public String getNomLaboratoire() {
        return nomLaboratoire;
    }

    /**
     *
     * @param nomLaboratoire
     */
    public void setNomLaboratoire(String nomLaboratoire) {
        this.nomLaboratoire = nomLaboratoire;
    }

    /**
     *
     * @return
     */
    public String getChefDeVente() {
        return chefDeVente;
    }

    /**
     *
     * @param chefDeVente
     */
    public void setChefDeVente(String chefDeVente) {
        this.chefDeVente = chefDeVente;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Laboratoire{" + "codeLaboratoire=" + codeLaboratoire + ", nomLaboratoire=" + nomLaboratoire + ", chefDeVente=" + chefDeVente + '}';
    }
}