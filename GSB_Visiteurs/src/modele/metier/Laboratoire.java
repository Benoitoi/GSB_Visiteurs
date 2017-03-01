package modele.metier;

/**
 * Classe représentant les laboratoires
 *
 * @author bjaouen
 * @version 1.0 :
 *
 */

public class Laboratoire {
    String codeLaboratoire;
    String nomLaboratoire;
    String chefDeVente;

    public Laboratoire(String codeLaboratoire, String nomLaboratoire, String chefDeVente) {
        this.codeLaboratoire = codeLaboratoire;
        this.nomLaboratoire = nomLaboratoire;
        this.chefDeVente = chefDeVente;
    }

    public String getCodeLaboratoire() {
        return codeLaboratoire;
    }

    public void setCodeLaboratoire(String codeLaboratoire) {
        this.codeLaboratoire = codeLaboratoire;
    }

    public String getNomLaboratoire() {
        return nomLaboratoire;
    }

    public void setNomLaboratoire(String nomLaboratoire) {
        this.nomLaboratoire = nomLaboratoire;
    }

    public String getChefDeVente() {
        return chefDeVente;
    }

    public void setChefDeVente(String chefDeVente) {
        this.chefDeVente = chefDeVente;
    }

    @Override
    public String toString() {
        return "Laboratoire{" + "codeLaboratoire=" + codeLaboratoire + ", nomLaboratoire=" + nomLaboratoire + ", chefDeVente=" + chefDeVente + '}';
    }
}