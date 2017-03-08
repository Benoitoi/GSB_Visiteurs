package modele.metier;

/**
 * Classe représentant les types de praticien
 *
 * @author bjaouen
 * @version 1.0 :
 *
 */

public class TypePraticien {
    String typeCode;
    String typeLibelle;
    String typeLieu;

    /**
     *
     * @param typeCode
     * @param typeLibelle
     * @param typeLieu
     */
    public TypePraticien(String typeCode, String typeLibelle, String typeLieu) {
        this.typeCode = typeCode;
        this.typeLibelle = typeLibelle;
        this.typeLieu = typeLieu;
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
    public String getTypeLibelle() {
        return typeLibelle;
    }

    /**
     *
     * @param typeLibelle
     */
    public void setTypeLibelle(String typeLibelle) {
        this.typeLibelle = typeLibelle;
    }

    /**
     *
     * @return
     */
    public String getTypeLieu() {
        return typeLieu;
    }

    /**
     *
     * @param typeLieu
     */
    public void setTypeLieu(String typeLieu) {
        this.typeLieu = typeLieu;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "TypePraticien{" + "typeCode=" + typeCode + ", typeLibelle=" + typeLibelle + ", typeLieu=" + typeLieu + '}';
    }
}