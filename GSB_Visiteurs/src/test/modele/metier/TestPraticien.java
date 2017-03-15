package test.modele.metier;

import modele.metier.Praticien;
import modele.metier.TypePraticien;

/**
 * Classe de test unitaire pour la classe Praticien
 *
 * @author btssio
 * @version 1.0
 *
 */
public class TestPraticien {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("\nTest classe Praticien\n");
        Praticien praticien = null;
        // Test n°1 : instanciation et accesseurs
        System.out.println("\nTest n°1 : instanciation et accesseurs");
        TypePraticien typePraticien = new TypePraticien("codeTypeTest", "libelleLieuTypeTest", "typeLieuTest");
        praticien = new Praticien(999, "nomPraticienTest", "prenomPraticienTest", "3 rue du test", 44000, "villeTest", 0.5f, typePraticien);

        System.out.println("Etat du praticien : " + praticien.toString());

        // Test n°2 : mutateurs + accesseurs
        //mutateurs
        System.out.println("\nTest n°2 : mutateurs + accesseurs");
        praticien.setNumero(1000);
        praticien.setNom("nouveau nom");
        praticien.setPrenom("nouveau prénom");
        praticien.setAdresse("nouvelle adresse");
        praticien.setCodePostal(44444);
        praticien.setVille("nouvelle ville");
        praticien.setCoefficientNotoriete(1.2f);
        typePraticien.setTypeCode("nouveau code type");
        praticien.setType(typePraticien);

        //accesseurs
        System.out.println("Etat du praticien : ");
        System.out.println("numero : " + praticien.getNumero());
        System.out.println("nom : " + praticien.getNom());
        System.out.println("prenom : " + praticien.getPrenom());
        System.out.println("adresse : " + praticien.getAdresse());
        System.out.println("code postal : " + praticien.getCodePostal());
        System.out.println("ville : " + praticien.getVille());
        System.out.println("coéfficient de notoriété : " + praticien.getCoefficientNotoriete());
        System.out.println("code type : " + praticien.getType().getTypeCode());
    }
}
