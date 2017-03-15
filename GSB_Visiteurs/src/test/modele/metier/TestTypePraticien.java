package test.modele.metier;

import modele.metier.TypePraticien;

/**
 * Classe de test unitaire pour la classe TypePraticien
 *
 * @author btssio
 * @version 1.0
 *
 */
public class TestTypePraticien {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("\nTest classe TypePraticien\n");
        TypePraticien typePraticien = null;
        // Test n°1 : instanciation et accesseurs
        System.out.println("\nTest n°1 : instanciation et accesseurs");
        typePraticien = new TypePraticien("codeTypeTest", "libelleLieuTypeTest", "typeLieuTest");

        System.out.println("Etat du type praticien : " + typePraticien.toString());

        // Test n°2 : mutateurs + accesseurs
        //mutateurs
        System.out.println("\nTest n°2 : mutateurs + accesseurs");
        typePraticien.setTypeCode("test code type");
        typePraticien.setTypeLibelle("nouveau libelle");
        typePraticien.setTypeLieu("nouveau lieu");

        //accesseurs
        System.out.println("Etat du type praticien : ");
        System.out.println("code : " + typePraticien.getTypeCode());
        System.out.println("libelle : " + typePraticien.getTypeLibelle());
        System.out.println("lieu : " + typePraticien.getTypeLieu());
    }
}
