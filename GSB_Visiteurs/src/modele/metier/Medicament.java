package modele.metier;

/**
 * Classe représentant les médicaments
 *
 * @author bjaouen
 * @version 1.0 :
 *
 */

public class Medicament {
    String depotLegal;
    String nomCommercial;
    Famille famille;
    String composition;
    String effets;
    String contreIndication;
    float prixEchantillon;

    /**
     *
     * @param depotLegal
     * @param nomCommercial
     * @param famille
     * @param composition
     * @param effets
     * @param contreIndication
     * @param prixEchantillon
     */
    public Medicament(String depotLegal, String nomCommercial, Famille famille, String composition, String effets, String contreIndication, float prixEchantillon) {
        this.depotLegal = depotLegal;
        this.nomCommercial = nomCommercial;
        this.famille = famille;
        this.composition = composition;
        this.effets = effets;
        this.contreIndication = contreIndication;
        this.prixEchantillon = prixEchantillon;
    }

    /**
     *
     * @return
     */
    public String getDepotLegal() {
        return depotLegal;
    }

    /**
     *
     * @param depotLegal
     */
    public void setDepotLegal(String depotLegal) {
        this.depotLegal = depotLegal;
    }

    /**
     *
     * @return
     */
    public String getNomCommercial() {
        return nomCommercial;
    }

    /**
     *
     * @param nomCommercial
     */
    public void setNomCommercial(String nomCommercial) {
        this.nomCommercial = nomCommercial;
    }

    /**
     *
     * @return
     */
    public Famille getFamille() {
        return famille;
    }

    /**
     *
     * @param famille
     */
    public void setFamille(Famille famille) {
        this.famille = famille;
    }

    /**
     *
     * @return
     */
    public String getComposition() {
        return composition;
    }

    /**
     *
     * @param composition
     */
    public void setComposition(String composition) {
        this.composition = composition;
    }

    /**
     *
     * @return
     */
    public String getEffets() {
        return effets;
    }

    /**
     *
     * @param effets
     */
    public void setEffets(String effets) {
        this.effets = effets;
    }

    /**
     *
     * @return
     */
    public String getContreIndication() {
        return contreIndication;
    }

    /**
     *
     * @param contreIndication
     */
    public void setContreIndication(String contreIndication) {
        this.contreIndication = contreIndication;
    }

    /**
     *
     * @return
     */
    public float getPrixEchantillon() {
        return prixEchantillon;
    }

    /**
     *
     * @param prixEchantillon
     */
    public void setPrixEchantillon(float prixEchantillon) {
        this.prixEchantillon = prixEchantillon;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Medicament{" + "depotLegal=" + depotLegal + ", nomCommercial=" + nomCommercial + ", codeFamille=" + famille.getCodeFamille() + ", composition=" + composition + ", effets=" + effets + ", contreIndication=" + contreIndication + ", prixEchantillon=" + prixEchantillon + '}';
    }
}